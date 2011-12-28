package ppcodes.android.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
   // private String TABLE_NAME;
   private final static String DATABASE_NAME = "pp.accountbook.dd";
   private final static int DATABASE_VERSION = 1;

   private final  String CREATE_UserInfo_SQL = "CREATE TABLE [UserInfo] ([Disabled] INTEGER NOT NULL DEFAULT (0), [UserId] INTEGER NOT NULL PRIMARY KEY, [UserName] TEXT NOT NULL,[UserKey] TEXT NOT NULL,[CreateTime] LONG, [ModifyTime] LONG)";
   private final  String CREATE_Budget_SQL = "CREATE TABLE [Budget] ([CategoryChildId] INTEGER NOT NULL, [CategoryId] INTEGER NOT NULL, [UserId] INTEGER NOT NULL, [Disabled] INTEGER NOT NULL DEFAULT (0), [BudgetId] INTEGER NOT NULL PRIMARY KEY, [CreateTime] LONG, [ModifyTime] LONG, [Date] TEXT, [Amount] REAL)";
   private final  String CREATE_Account_SQL = "CREATE TABLE [Account] ([Disabled] INTEGER NOT NULL DEFAULT (0), [UserId] INTEGER NOT NULL, [AccountId] INTEGER NOT NULL PRIMARY KEY, [AccountName] TEXT NOT NULL, [CreateTime] LONG, [ModifyTime] LONG, [UseCount] INTEGER DEFAULT (0))";
   private final  String CREATE_Business_SQL = "CREATE TABLE [Business] ([BusinessId] INTEGER NOT NULL PRIMARY KEY, [UserId] INTEGER NOT NULL, [BusinessName] TEXT NOT NULL, [CreateTime] LONG, [ModifyTime] LONG, [Disabled] INTEGER NOT NULL DEFAULT (0), [UseCount] INTEGER DEFAULT (0))";
   private final  String CREATE_Category_SQL = "CREATE TABLE [Category] ([InOrOut] INTEGER NOT NULL, [Disabled] INTEGER NOT NULL DEFAULT (0), [CategoryId] INTEGER NOT NULL PRIMARY KEY, [ParentCategoryId] INTEGER NOT NULL DEFAULT (0), [CatagoryName] TEXT NOT NULL, [UserId] INTEGER NOT NULL, [CreateTime] LONG, [ModifyTime] LONG, [UseCount] INTEGER DEFAULT (0),[Icon] TEXT)";
   private final  String CREATE_InOutDetails_SQL = "CREATE TABLE [InOutDetails] ([BusinessId] INTEGER, [ProjectId] INTEGER, [Amount] REAL, [InOrOut] INTEGER NOT NULL, [Remarks] TEXT, [Date] TEXT NOT NULL, [CreateTime] LONG, [ModifyTime] LONG, [Disabled] INTEGER NOT NULL DEFAULT (0), [Id] INTEGER NOT NULL PRIMARY KEY,[UserId] INTEGER NOT NULL, [CategoryId] INTEGER, [CategoryChildId] INTEGER)";
   private final  String CREATE_Project_SQL = "CREATE TABLE [Project] ([ProjectId] INTEGER NOT NULL PRIMARY KEY, [UserId] INTEGER NOT NULL, [ProjectName] TEXT NOT NULL, [CreateTime] LONG, [ModifyTime] LONG, [Disabled] INTEGER NOT NULL DEFAULT (0), [UseCount] INTEGER DEFAULT (0))";
   private final  String CREATE_Profile_SQL="CREATE TABLE [Profile] ([_id] INTEGER NOT NULL PRIMARY KEY, [UserId] INTEGER NOT NULL, [CreateTime] LONG, [ModifyTime] LONG,  [InCategoryId] INTEGER NOT NULL DEFAULT (1), [OutCategoryId] INTEGER NOT NULL DEFAULT (1), [ProjectId] INTEGER NOT NULL DEFAULT (1), [BusinessId] INTEGER NOT NULL DEFAULT (1), [BudgetId] INTEGER NOT NULL DEFAULT (1), [Disabled] INTEGER NOT NULL DEFAULT (0));";
   
   private final  String DROP_UserInfo_SQL = "DROP TABLE IF EXISTS UserInfo";
   private final  String DROP_Budget_SQL = "DROP TABLE IF EXISTS Budget";
   private final  String DROP_Account_SQL = "DROP TABLE IF EXISTS Account";
   private final  String DROP_Business_SQL = "DROP TABLE IF EXISTS Business";
   private final  String DROP_Category_SQL = "DROP TABLE IF EXISTS Category";
   private final  String DROP_InOutDetails_SQL = "DROP TABLE IF EXISTS InOutDetails";
   private final  String DROP_Project_SQL = "DROP TABLE IF EXISTS Project";
   private final  String DROP_Profile_SQL = "DROP TABLE IF EXISTS Profile";

   public DBHelper(Context context)
   {
	  // TODO Auto-generated constructor stub
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  Log.e("ERROR", "初始化成功");
   }

   /**
    * 执行Insert/Update/Delete语句
    * 
    * @param sql
    * @param selectionArgs
    */
   public void exeOtherSQL(String sql, String[] selectionArgs)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = this.getWritableDatabase();
		 db.execSQL(sql, selectionArgs);
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
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
    * 执行Insert/Update/Delete语句
    * 
    * @param sql
    */
   public void exeOtherSQL(String sql)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = this.getWritableDatabase();
		 db.execSQL(sql);
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
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
    * 
    * @param cValues
    * @param table_name
    * @return
    */
   public long insert(ContentValues cValues, String table_name)
   {
	  SQLiteDatabase db = null;
	  long row = 0;
	  try
	  {
		 db = this.getWritableDatabase();
		 row = db.insert(table_name, null, cValues);
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
	  return row;
   }

   /**
    * 
    * @param table_name
    * @param field_where
    * @param field_whereValue
    */
   public void delete(String table_name, String field_where,
		 String field_whereValue)
   {
	  String where = field_where + "=?";
	  String[] whereValue = { field_whereValue };
	  SQLiteDatabase db = null;
	  try
	  {
		 db = this.getWritableDatabase();
		 db.delete(table_name, where, whereValue);
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
    * 
    * @param table_name
    * @param field_where
    * @param field_whereValue
    * @param cValues
    */
   public void update(String table_name, String field_where,
		 String field_whereValue, ContentValues cValues)
   {
	  String where = field_where + "=?";
	  String[] whereValue = { field_whereValue };
	  SQLiteDatabase db = null;
	  try
	  {
		 db = this.getWritableDatabase();
		 db.update(table_name, cValues, where, whereValue);
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

   
   @Override
   public void onCreate(SQLiteDatabase db)
   {
	  try
	  {
		 db.beginTransaction();
		 db.execSQL(CREATE_UserInfo_SQL);
		 db.execSQL(CREATE_Budget_SQL);
		 db.execSQL(CREATE_Account_SQL);
		 db.execSQL(CREATE_Business_SQL);
		 db.execSQL(CREATE_Category_SQL);
		 db.execSQL(CREATE_InOutDetails_SQL);
		 db.execSQL(CREATE_Project_SQL);
		 db.execSQL(CREATE_Profile_SQL);
		 Log.e("ERROR", "数据库创建成功");
		 db.setTransactionSuccessful();
		 db.endTransaction();
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 e.printStackTrace();
	  }
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   {
	  // TODO Auto-generated method stub

	  // db.beginTransaction();
	  db.execSQL(DROP_UserInfo_SQL);
	  db.execSQL(DROP_Budget_SQL);
	  db.execSQL(DROP_Account_SQL);
	  db.execSQL(DROP_Business_SQL);
	  db.execSQL(DROP_Category_SQL);
	  db.execSQL(DROP_InOutDetails_SQL);
	  db.execSQL(DROP_Project_SQL);
	  db.execSQL(DROP_Profile_SQL);

	  onCreate(db);
	  Log.e("ERROR", "数据库更新成功");
	  // db.execSQL(CREATE_UserInfo_SQL);
	  // db.execSQL(CREATE_Budget_SQL);
	  // db.execSQL(CREATE_Account_SQL);
	  // db.execSQL(CREATE_Business_SQL);
	  // db.execSQL(CREATE_Category_SQL);
	  // db.execSQL(CREATE_InOutDetails_SQL);
	  // db.execSQL(CREATE_Project_SQL);

	  // db.setTransactionSuccessful();
	  // db.endTransaction();
   }

   // /**
   // * 专门执行select语句如果没有第二的参数则为Null
   // * @param sql
   // * @param selectionArgs
   // * @return
   // */
   // public Cursor exeSelectSQL(String sql,String[] selectionArgs)
   // {
   // SQLiteDatabase db=null;
   // Cursor cursor=null;
   // try
   // {
   // db=baseHelper.getReadableDatabase();
   // return cursor = db.rawQuery(sql, selectionArgs);
   // }
   // catch (Exception e)
   // {
   // // TODO: handle exception
   // }
   // return cursor;
   // }

}
