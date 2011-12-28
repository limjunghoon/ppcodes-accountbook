package ppcodes.accountbook.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ppcodes.accountbook.entity.dictionary.DicBusiness;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.android.common.DBHelper;

public class DaoBusiness extends DaoBase
{
   private DBHelper dbHelper;

   public DaoBusiness(Context context)
   {
	  dbHelper = new DBHelper(context);
   }

  
   
//   public void InitBusiness(int UserId)
//   {
//	  SQLiteDatabase db = null;
//
//	  String nowTime=StringHelper.FormatDate(new Date());
//	  try
//	  {
//		 String sqlString = "INSERT INTO [Business] (UserId,BusinessName,CreateTime,ModifyTime,Disabled,UseCount)" +
//	                        " SELECT "+UserId+",'无',"+nowTime+","+nowTime+",0,0" +
//	                        " UNION ALL " +            
//			                " SELECT "+UserId+",'肯德基',"+nowTime+","+nowTime+",0,0" +
//		 		            " UNION ALL " +
//		 		            " SELECT "+UserId+",'京东',"+nowTime+","+nowTime+",0,0" +
//		 		            " UNION ALL " +
//		 		            " SELECT "+UserId+",'易迅',"+nowTime+","+nowTime+",0,0" +
//		 		            " UNION ALL " +
//		 		            " SELECT "+UserId+",'新蛋',"+nowTime+","+nowTime+",0,0";
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

   public List<String> GetAllBusinessByUserId(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(
			   "Select BusinessName From [Business] Where UserId=? And Disabled=0",
			   new String[] { String.valueOf(UserId) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			List<String> list=new ArrayList<String>();
			do
			{
			  list.add(cursor.getString(0));
			} 
			while (cursor.moveToNext());
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
   * 判断是否存在此商家的名称，存在则添加失败，不存在则添加新商家
   * 需要BusinessName和UserId两个值
   * @param modBusiness
   * @return false表示插入失败或者已经存在用户 
   * true表示插入成功 
   */
   public boolean InsertBusiness(ModBusiness modBusiness)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery(
			   "Select count(*) as num From [Business] Where [BusinessName]=? And [UserId]=? And [Disabled]=0",
			   new String[] { modBusiness.getBusinessName(),String.valueOf(modBusiness.getUserId())});
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   int num = Integer.valueOf(cursor.getString(0));
			   if (num > 0)
			   {
				  return false;// 存在
			   }
			} 
			while (cursor.moveToNext());
			
			String sqlString = "INSERT INTO [Business] ([UserId],[BusinessName],[CreateTime],[ModifyTime],[Disabled],[UseCount]) Values (%s,'%s',%s,%s,%s,%s)";
			sqlString = String.format(sqlString,
			                       modBusiness.getUserId(),
			                       modBusiness.getBusinessName(),
			                       modBusiness.getCreateTime(),
			                       modBusiness.getModifyTime(),
			                       modBusiness.getDisabled(),
			                       modBusiness.getUseCount());
			db.execSQL(sqlString); 
			return true;
		 }
		 else
		 {
			return false;// cursor为空，表示出了异常
		 }
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
	  return false;
   }

  
   
   /**
    * 更新商家
    * 需要赋值BusinessName,ModifyTime,
    * @param modBusiness
    */
   public void UpdateBusinessName(ModBusiness modBusiness,String OldName)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		db=dbHelper.getWritableDatabase();
		int sID=super.getIdByName(db, DicBusiness.BusinessId, DicBusiness.TableName, DicBusiness.BusinessName, OldName);
		String sqlString="UPDATE [Business] SET [BusinessName]='%s',[ModifyTime]=%s WHERE [Disabled]=0 AND [BusinessId]=%s";
        sqlString=String.format(sqlString, modBusiness.getBusinessName(),modBusiness.getModifyTime(),sID);
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
    * 更新商家
    * 需要赋值BusinessName,UserCount,ModifyTime
    * @param modBusiness
    */
//   public void UpdateBusinessUserCount(ModBusiness modBusiness)
//   {
//	  SQLiteDatabase db = null;
//	  Cursor cursor = null;
//	  try
//	  {
//		 db = dbHelper.getReadableDatabase();
//		 cursor = db.rawQuery(
//			   "Select BusinessName From [Business] Where UserId=? And Disabled=0",
//			   new String[] { String.valueOf(UserId) });
//		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
//		 {
//			List<String> list=new ArrayList<String>();
//			do
//			{
//			  list.add(cursor.getString(0));
//			} 
//			while (cursor.moveToNext());
//		 }
//		 else
//		 {
//			return null;// cursor为空，表示出了异常
//		 }
//	  }
//	  catch (Exception e)
//	  {
//		 // TODO: handle exception
//		 Log.e("ERROR", e.getMessage() + "DaoBusiness.GetAllBusinessByUserId（）");
//		 e.printStackTrace();
//	  }
//	  finally
//	  {
//		 if (db != null)
//		 {
//			db.close();
//		 }
//		 if (cursor != null)
//		 {
//			cursor.close();
//		 }
//	  }
//   }


   
   /**
    * 删除一个商家，BusinessName,ModifyTime必须赋值
    * @param modBusiness
    */
   public void DeleteBusiness(ModBusiness modBusiness)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db=dbHelper.getWritableDatabase();
		 int sID=super.getIdByName(db, DicBusiness.BusinessId, DicBusiness.TableName, DicBusiness.BusinessName, modBusiness.getBusinessName());
		 String sqlString="UPDATE [Business] SET [Disabled]=1,[ModifyTime]=%s WHERE [BusinessId]=%s";
         sqlString=String.format(sqlString, modBusiness.getModifyTime(),String.valueOf(sID));
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
