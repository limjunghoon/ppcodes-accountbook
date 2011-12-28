package ppcodes.accountbook.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class DaoBase
{
   /**
    * 通过NAME获取ID
    * @param db SQLiteDatabase实例，未作销毁处理
    * @param idFieldName 要获取的ID字段的名字
    * @param tableName 表名
    * @param nameFieldName 名称字段的名字
    * @param nameFieldValue 名称字段的值
    * @return 返回0表示出错或没搜到，其他则为正常
    */
   protected int getIdByName(SQLiteDatabase db, String idFieldName, String tableName, String nameFieldName, String nameFieldValue)
   {
	  Cursor cursor = null;
	  try
	  {
		 cursor = db.rawQuery("Select " + idFieldName + " From " + tableName + " Where " + nameFieldName + "=? And Disabled=0", new String[] { nameFieldValue });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   Integer sid = cursor.getInt(0);
			   return sid;
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
		if (cursor != null)
		{
		  cursor.close();
		}
	  }
	  return 0;// 默认为存在防止多重添加
   }

   /**
    * 通过NAME获取ID(获取已经删除的记录)
    * @param db SQLiteDatabase实例，未作销毁处理
    * @param idFieldName 要获取的ID字段的名字
    * @param tableName 表名
    * @param nameFieldName 名称字段的名字
    * @param nameFieldValue 名称字段的值
    * @return 返回0表示出错或没搜到，其他则为正常
    */
   protected int getDeletedIdByName(SQLiteDatabase db, String idFieldName, String tableName, String nameFieldName, String nameFieldValue)
   {
	  Cursor cursor = null;
	  try
	  {
		 cursor = db.rawQuery("Select " + idFieldName + " From " + tableName + " Where " + nameFieldValue + "=? And Disabled=1", new String[] { nameFieldValue });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   Integer sid = cursor.getInt(0);
			   return sid;
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
		if (cursor != null)
		{
		  cursor.close();
		}
	  }
	  return 0;// 默认为存在防止多重添加
   }
   
}
