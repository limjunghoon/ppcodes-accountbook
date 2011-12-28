package ppcodes.accountbook.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ppcodes.accountbook.entity.model.ModUserInfo;
import ppcodes.android.common.DBHelper;
import ppcodes.android.common.Encrypt;

public class DaoUserInfo extends DaoBase
{
   private DBHelper dbHelper;

   public DaoUserInfo(Context context)
   {
	  // TODO Auto-generated constructor stub
	  dbHelper = new DBHelper(context);
   }

   /**
    * 创建一个新用户(包括新建初始的项目类别，商家等等等)
    * 
    * @param userInfo
    */
   public void InsertUser(ModUserInfo userInfo)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 String sqlString = "INSERT INTO [UserInfo] ([UserName],[UserKey],[CreateTime],[ModifyTime],[Disabled]) Values ('%s','%s','%s','%s',%s)";
		 sqlString = String.format(sqlString, userInfo.getUserName(),
			   Encrypt.MD5(userInfo.getUserKey()), userInfo.getCreateTime(),
			   userInfo.getModifyTime(), userInfo.getDisabled());
		 db = dbHelper.getWritableDatabase();
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
    * 判断是否存在该用户名
    * 
    * @param UserName
    * @return
    */
   public boolean IsUserExist(String UserName)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(
			   "SELECT count(*) as num FROM [UserInfo] WHERE [UserName]=? AND [Disabled]=0",
			   new String[] { UserName });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   int num = Integer.valueOf(cursor.getString(0));
			   if (num > 0)
			   {
				  return true;// 存在
			   }
			   else
			   {
				  return false;// 不存在
			   }
			} while (cursor.moveToNext());
		 }
		 else
		 {
			return false;// cursor为空，表示出了异常
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 Log.e("ERROR", e.getMessage() + "DAOLogin.IsUserExist（）");
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
	  return true;// 默认为存在防止多重添加
   }

   /**
    * 判断是否密码正确
    * 
    * @param userInfo
    * @return
    */
   public boolean IsUserKeyRight(ModUserInfo userInfo)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 String inputKey = Encrypt.MD5(userInfo.getUserKey());
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery("SELECT [UserKey] FROM [UserInfo] WHERE [UserName]=? AND [Disabled]=0",
			   new String[] { userInfo.getUserName() });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   String key = cursor.getString(0);
			   if (key.equals(inputKey))
			   {
				  return true;
			   }
			} while (cursor.moveToNext());
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
		 if (cursor != null)
		 {
			cursor.close();
		 }
	  }
	  return false;
   }

   /**
    * 通过用户名获取用户唯一Id号
    * 
    * @param UserName
    * @return
    */
   public int GetUserIdByUserName(String UserName)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(
			   "SELECT [UserId] FROM [UserInfo] WHERE [UserName]=? AND [Disabled]=0",
			   new String[] { UserName });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			  Integer UserId = cursor.getInt(0);
			  return UserId;
			} while (cursor.moveToNext());
		 }
		 else
		 {
			return 0;// cursor为空，表示出了异常
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 Log.e("ERROR", e.getMessage() + "DAOLogin.IsUserExist（）");
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
	  return 0;// 默认为存在防止多重添加
   }

}
