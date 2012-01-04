package ppcodes.accountbook.dao;

import java.util.ArrayList;
import java.util.List;

import ppcodes.accountbook.entity.dictionary.DicProject;
import ppcodes.accountbook.entity.model.ModProject;
import ppcodes.android.common.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DaoProject extends DaoBase
{
   private DBHelper dbHelper;

   public DaoProject(Context context)
   {
	  dbHelper = new DBHelper(context);
   }

   public List<ModProject> GetAllProjectByUserIdForAdd(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery("Select ProjectName,ProjectId From [Project] Where UserId=? And Disabled=0", new String[] { String.valueOf(UserId) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			List<ModProject> list = new ArrayList<ModProject>();
			do
			{
			   ModProject modProject=new ModProject();
			   modProject.setProjectName(cursor.getString(0));
			   modProject.setProjectId(cursor.getInt(1));
			   list.add(modProject);
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
		 Log.e("ERROR", e.getMessage() + "DaoProject.GetAllProjectByUserId（）");
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
   
   public List<String> GetAllProjectByUserId(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery("Select ProjectName From [Project] Where UserId=? And Disabled=0", new String[] { String.valueOf(UserId) });
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
		 Log.e("ERROR", e.getMessage() + "DaoProject.GetAllProjectByUserId（）");
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
    * 判断是否存在此项目的名称，存在则添加失败，不存在则添加新项目 需要ProjectName和UserId两个值
    * 
    * @param modProject
    * @return false表示插入失败或者已经存在用户 true表示插入成功
    */
   public boolean InsertProject(ModProject modProject)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;

	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Project] Where [ProjectName]=? And [UserId]=? And [Disabled]=0",
			   new String[] { modProject.getProjectName(), String.valueOf(modProject.getUserId()) });
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

			String sqlString = "INSERT INTO [Project] ([UserId],[ProjectName],[CreateTime],[ModifyTime],[Disabled],[UseCount]) Values (%s,'%s',%s,%s,%s,%s)";
			sqlString = String.format(sqlString, modProject.getUserId(), modProject.getProjectName(), modProject.getCreateTime(), modProject.getModifyTime(), modProject.getDisabled(),
				  modProject.getUseCount());
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
    * 更新项目 需要赋值ProjectName,ModifyTime,UserId,
    * 
    * @param modProject
    */
   public boolean UpdateProjectName(ModProject modProject, String OldName)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Project] Where [ProjectName]=? And [UserId]=? And [Disabled]=0",
			   new String[] { modProject.getProjectName(), String.valueOf(modProject.getUserId()) });
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

			int sID = super.getIdByName(db, DicProject.ProjectId, DicProject.TableName, DicProject.ProjectName, OldName);
			String sqlString = "UPDATE [Project] SET [ProjectName]='%s',[ModifyTime]=%s WHERE [Disabled]=0 AND [ProjectId]=%s";
			sqlString = String.format(sqlString, modProject.getProjectName(), modProject.getModifyTime(), sID);
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
    * 删除一个项目，ProjectName,ModifyTime必须赋值
    * 
    * @param modProject
    */
   public void DeleteProject(ModProject modProject)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 int sID = super.getIdByName(db, DicProject.ProjectId, DicProject.TableName, DicProject.ProjectName, modProject.getProjectName());
		 String sqlString = "UPDATE [Project] SET [Disabled]=1,[ModifyTime]=%s WHERE [ProjectId]=%s";
		 sqlString = String.format(sqlString, modProject.getModifyTime(), String.valueOf(sID));
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
