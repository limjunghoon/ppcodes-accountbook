package ppcodes.accountbook.dao;

import ppcodes.android.common.DBHelper;
import android.content.Context;
import ppcodes.accountbook.entity.dictionary.DicAccount;
import ppcodes.accountbook.entity.dictionary.DicProject;
import ppcodes.accountbook.entity.model.ModAccount;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DaoAccount extends DaoBase
{
   private DBHelper dbHelper;

   public DaoAccount(Context context)
   {
	  dbHelper = new DBHelper(context);
   }

   public List<ModAccount> GetAllAccountByUserIdForAdd(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery("Select AccountName,AccountId From [Account] Where UserId=? And Disabled=0", new String[] { String.valueOf(UserId) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			List<ModAccount> list = new ArrayList<ModAccount>();
			do
			{
			   ModAccount modAccount=new ModAccount();
			   modAccount.setAccountName(cursor.getString(0));
			   modAccount.setAccountId(cursor.getInt(1));
			   list.add(modAccount);
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
		 Log.e("ERROR", e.getMessage() + "DaoAccount.GetAllAccountByUserId（）");
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
   
   public List<String> GetAllAccountByUserId(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery("Select AccountName From [Account] Where UserId=? And Disabled=0", new String[] { String.valueOf(UserId) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			List<String> list = new ArrayList<String>();
			do
			{
			   list.add(cursor.getString(0));
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
		 Log.e("ERROR", e.getMessage() + "DaoAccount.GetAllAccountByUserId（）");
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
    * 判断是否存在此账户的名称，存在则添加失败，不存在则添加新项目 需要AccountName和UserId两个值
    * 
    * @param modAccount
    * @return false表示插入失败或者已经存在用户 true表示插入成功
    */
   public boolean InsertAccount(ModAccount modAccount)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;

	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Account] Where [AccountName]=? And [UserId]=? And [Disabled]=0",
			   new String[] { modAccount.getAccountName(), String.valueOf(modAccount.getUserId()) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   int num = Integer.valueOf(cursor.getString(0));
			   if (num > 0)
			   {
				  return false;// 存在
			   }
			} while (cursor.moveToNext());

			String sqlString = "INSERT INTO [Account] ([UserId],[AccountName],[CreateTime],[ModifyTime],[Disabled],[UseCount]) Values (%s,'%s',%s,%s,%s,%s)";
			sqlString = String.format(sqlString, modAccount.getUserId(), modAccount.getAccountName(), modAccount.getCreateTime(), modAccount.getModifyTime(), modAccount.getDisabled(),
				  modAccount.getUseCount());
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
		 if (cursor != null)
		 {
			cursor.close();
		 }
	  }
	  return false;
   }

   /**
    * 更新账户 需要赋值AccountName,ModifyTime,UserId,
    * 
    * @param modAccount
    */
   public boolean UpdateAccountName(ModAccount modAccount, String OldName)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Account] Where [AccountName]=? And [UserId]=? And [Disabled]=0",
			   new String[] { modAccount.getAccountName(), String.valueOf(modAccount.getUserId()) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   int num = Integer.valueOf(cursor.getString(0));
			   if (num > 0)
			   {
				  return false;// 存在
			   }
			} while (cursor.moveToNext());

			int sID = super.getIdByName(db, DicAccount.AccountId, DicAccount.TableName, DicAccount.AccountName, OldName);
			String sqlString = "UPDATE [Account] SET [AccountName]='%s',[ModifyTime]=%s WHERE [Disabled]=0 AND [AccountId]=%s";
			sqlString = String.format(sqlString, modAccount.getAccountName(), modAccount.getModifyTime(), sID);
			db.execSQL(sqlString);
			return true;
		 }
		 else
		 {
			return false;
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
    * 删除一个账户，AccountName,ModifyTime必须赋值
    * 如果只剩一个不让删
    * @param modAccount
    */
   public boolean DeleteAccount(ModAccount modAccount)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Account] Where [Disabled]=0", null);
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			do
			{
			   int num = Integer.valueOf(cursor.getString(0));
			   if (num == 1)
			   {
				  return false;//只剩一个
			   }
			} while (cursor.moveToNext());

			int sID = super.getIdByName(db, DicAccount.AccountId, DicProject.TableName, DicAccount.AccountName, modAccount.getAccountName());
			String sqlString = "UPDATE [Account] SET [Disabled]=1,[ModifyTime]=%s WHERE [ProjectId]=%s";
			sqlString = String.format(sqlString, modAccount.getModifyTime(), String.valueOf(sID));
			db.execSQL(sqlString);
			return true;
		 }
		 else
		 {
			return false;
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
}
