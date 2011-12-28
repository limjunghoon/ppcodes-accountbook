package ppcodes.accountbook.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ppcodes.accountbook.entity.model.ModProfile;
import ppcodes.android.common.DBHelper;

public class DaoProfile extends DaoBase
{
   private DBHelper dbHelper;

   public DaoProfile(Context context)
   {
	  dbHelper = new DBHelper(context);
   }

  
//   /**
//    * 初始化配置设置
//    * @param UserId
//    */
//   public void InitProfile(int UserId)
//   {
//	  SQLiteDatabase db = null;
//
//	  String nowTime=StringHelper.FormatDate(new Date());
//	  try
//	  {
//		 String sqlString = "INSERT INTO [Profile] ([UserId],[InCategoryId],[OutCategoryId],[BusinessId],[BudgetId],[ProjectId],[CreateTime],[ModifyTime]) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)";
//	     sqlString=String.format(sqlString, UserId,1,1,1,1,1,nowTime,nowTime);
//		 db = dbHelper.getWritableDatabase();
//		 db.execSQL(sqlString);
//	  }
//	  catch (Exception e)
//	  {
//		 // TODO: handle exception
//		 e.printStackTrace();
//	  }
//	  finally
//	  {
//		 if (db != null)
//		 {
//			db.close();
//		 }
//	  }
//   }

   
   /**
    * 
    * @param UserId
    * @return
    */
   public ModProfile GetProfileByUserId(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(
			   "Select [UserId],[InCategoryId],[OutCategoryId],[BusinessId],[AccountId],[ProjectId] From [Profile] Where [UserId]=? And [Disabled]=0",
			   new String[] { String.valueOf(UserId) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			ModProfile modProfile=new ModProfile();
			do
			{
			  modProfile.setUserId(cursor.getInt(0));
			  modProfile.setInCategoryId(cursor.getInt(1));
			  modProfile.setOutCategoryId(cursor.getInt(2));
			  modProfile.setBusinessId(cursor.getInt(3));
			  modProfile.setAccountId(cursor.getInt(4));
			  modProfile.setProjectId(cursor.getInt(5));
			} 
			while (cursor.moveToNext());
			return modProfile;
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
    * 更新配置
    * 需要赋值ModifyTime,UserId,要更新的id对应的名称，UpdateField要更新的字段名字
    * @param modProfile，name,updateField
    */
   public void UpdateProfile(ModProfile modProfile,String tablaName,String fieldName,String name,String updateField)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		db=dbHelper.getWritableDatabase();
		int sId=super.getIdByName(db, updateField, tablaName, fieldName, name);
        String sqlString="UPDATE [Profile] SET [%s]=%s,[ModifyTime]=%s WHERE [Disabled]=0 AND [UserId]=%s";
        sqlString=String.format(sqlString, updateField,sId ,modProfile.getModifyTime(),modProfile.getUserId());
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
    * @param modProfile
    */
   public void DeleteProfile(ModProfile modProfile)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db=dbHelper.getWritableDatabase();
		 String sqlString="UPDATE [Profile] SET [Disabled]=1,[ModifyTime]=%s WHERE [UserId]=%s";
         sqlString=String.format(sqlString, modProfile.getModifyTime(),modProfile.getUserId());
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
