package ppcodes.accountbook.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.entity.model.ModProfile;
import ppcodes.android.common.DBHelper;

public class DaoProfile extends DaoBase
{
   private DBHelper dbHelper;

   public DaoProfile(Context context)
   {
	  dbHelper = new DBHelper(context);
   }

   // /**
   // * 初始化配置设置
   // * @param UserId
   // */
   // public void InitProfile(int UserId)
   // {
   // SQLiteDatabase db = null;
   //
   // String nowTime=StringHelper.FormatDate(new Date());
   // try
   // {
   // String sqlString =
   // "INSERT INTO [Profile] ([UserId],[InCategoryId],[OutCategoryId],[BusinessId],[BudgetId],[ProjectId],[CreateTime],[ModifyTime]) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)";
   // sqlString=String.format(sqlString, UserId,1,1,1,1,1,nowTime,nowTime);
   // db = dbHelper.getWritableDatabase();
   // db.execSQL(sqlString);
   // }
   // catch (Exception e)
   // {
   // // TODO: handle exception
   // e.printStackTrace();
   // }
   // finally
   // {
   // if (db != null)
   // {
   // db.close();
   // }
   // }
   // }

   /**
    * 返回值的顺序为0收入，1支出，2项目，3账户，4商家
    * 
    * @param UserId
    * @return
    */
   public List<String> GetProfileNameByUserId(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {

		 db = dbHelper.getReadableDatabase();
		 String sqlString = "Select b.[CategoryName] as CategoryIn,c.[CategoryName] as CategoryOut,d.[ProjectName],e.[AccountName],f.[BusinessName],a.[UserId]" + 
		                   " From [Profile] a"+ 
			               " Inner Join Category b On b.CategoryId=a.InCategoryId" + 
		                   " Inner Join Category c On c.CategoryId=a.OutCategoryId" + 
			               " Inner Join Project d On d.ProjectId=a.ProjectId" + 
		                   " Inner Join Account e On e.AccountId=a.AccountId" + 
			               " Inner Join Business f On f.BusinessId=a.BusinessId" + 
		                   " Where a.[UserId]=? And a.[Disabled]=0";

		 cursor = db.rawQuery(sqlString, new String[] { String.valueOf(UserId) });

		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			List<String> list = new ArrayList<String>();
			do
			{
			   list.add(cursor.getString(0));
			   list.add(cursor.getString(1));
			   list.add(cursor.getString(2));
			   list.add(cursor.getString(3));
			   list.add(cursor.getString(4));
			   list.add(cursor.getString(5));

			} while (cursor.moveToNext());
			return list;
		 }
		 else
		 {
			return null;// cursor为空，表示出了异常
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 Log.e("ERROR", e.getMessage() + "DaoBusiness.GetAllBusinessByUserId（）");
		 e.printStackTrace();
	  }
	  finally
	  {
		 if (db != null)
		 {
			db.close();
		 }
		 if (cursor != null)
		 {
			cursor.close();
		 }
	  }
	  return null;// 默认为存在防止多重添加
   }

   /**
    * 更新配置 需要赋值ModifyTime,UserId,要更新的id对应的名称，UpdateField要更新的字段名字
    * 
    * @param modProfile
    *           ，name,updateField
    */
   public void UpdateProfile(ModProfile modProfile, String tablaName, String fieldName, String name, String updateField)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 int sId = super.getIdByName(db, updateField, tablaName, fieldName, name);
		 String sqlString = "UPDATE [Profile] SET [%s]=%s,[ModifyTime]=%s WHERE [Disabled]=0 AND [UserId]=%s";
		 if(modProfile.isCategory())
		 {
            if(modProfile.getCategoryType()==Enums.InOrOut.Incoming.getValue())//in
            {
    		   sqlString = String.format(sqlString, "InCategoryId", sId, modProfile.getModifyTime(), modProfile.getUserId());
            }
            else if(modProfile.getCategoryType()==Enums.InOrOut.Payout.getValue())//out
            {
    		   sqlString = String.format(sqlString, "OutCategoryId", sId, modProfile.getModifyTime(), modProfile.getUserId());	
            }
		 }
		 else 
		 {
		   sqlString = String.format(sqlString, updateField, sId, modProfile.getModifyTime(), modProfile.getUserId());
		 }
		 
db.execSQL(sqlString);
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 e.printStackTrace();
	  }
	  finally
	  {
		 if (db != null)
		 {
			db.close();
		 }
	  }
   }

   /**
    * 删除一个配置，UserId,ModifyTime必须赋值
    * 
    * @param modProfile
    */
   public void DeleteProfile(ModProfile modProfile)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 String sqlString = "UPDATE [Profile] SET [Disabled]=1,[ModifyTime]=%s WHERE [UserId]=%s";
		 sqlString = String.format(sqlString, modProfile.getModifyTime(), modProfile.getUserId());
		 db.execSQL(sqlString);
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 e.printStackTrace();
	  }
	  finally
	  {
		 if (db != null)
		 {
			db.close();
		 }
	  }
   }

}
