package ppcodes.accountbook.dao;

import java.util.ArrayList;
import java.util.List;

import ppcodes.accountbook.entity.dictionary.DicCategory;
import ppcodes.accountbook.entity.model.ModCategory;
import ppcodes.android.common.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DaoCategory extends DaoBase
{
   private DBHelper dbHelper;

   public DaoCategory(Context context)
   {
	  dbHelper = new DBHelper(context);
   }
    
   /**
    * 默认返回0
    * @param CategoryName
    * @return
    */
   public int GetCategoryId(String CategoryName)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 int sID = super.getIdByName(db, DicCategory.CategoryId, DicCategory.TableName, DicCategory.CategoryName, CategoryName);
         return sID;
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
	  return 0;
   }
   
   /**
    * InOrOut 2代表支出1代表收入
    * 
    * @param UserId
    * @param InOrOut
    * @return
    */
   public List<ModCategory> GetAllParentCategorys(int UserId, int InOrOut)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  List<ModCategory> list = null;
	  try
	  {
		 String sqlString = "Select [CategoryName],[Icon] From [Category] Where [UserId]=? And [InOrOut]=? And [ParentCategoryId]=0 And [Disabled]=0";
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(sqlString, new String[] { String.valueOf(UserId), String.valueOf(InOrOut) });
		 list = new ArrayList<ModCategory>();
		 if (cursor.moveToFirst() && cursor.getCount() > 0)
		 {
			do
			{
			   ModCategory modCategory=new ModCategory();	
			   modCategory.setCategoryName(cursor.getString(0));
			   modCategory.setIcon(cursor.getString(1));
			   list.add(modCategory);
			} while (cursor.moveToNext());
			return list;
		 }
		 else
		 {
			return null;
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
	  return null;
   }

   /**
    * CategoryName, UserId, InOrOut必填 2代表支出1代表收入
    * 
    * @param modCategory
    * @return
    */
   public List<ModCategory> GetAllChildrenCategorys(int UserId, int InOrOut, String name)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  List<ModCategory> list = null;
	  try
	  {
		 String sqlString = "Select [CategoryName],[Icon] From [Category] Where [UserId]=? And [ParentCategoryId]=? And [InOrOut]=?  And [Disabled]=0";
		 db = dbHelper.getReadableDatabase();
		 int sID = super.getIdByName(db, DicCategory.CategoryId, DicCategory.TableName, DicCategory.CategoryName, name);
		 cursor = db.rawQuery(sqlString, new String[] { String.valueOf(UserId), String.valueOf(sID), String.valueOf(InOrOut) });
		 list = new ArrayList<ModCategory>();
		 if (cursor.moveToFirst() && cursor.getCount() > 0)
		 {
			do
			{
			   ModCategory modCategory=new ModCategory();	
			   modCategory.setCategoryName(cursor.getString(0));
			   modCategory.setIcon(cursor.getString(1));
			   list.add(modCategory);
			} while (cursor.moveToNext());
			return list;
		 }
		 else
		 {
			return null;
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
	  return null;
   }
   
   /**
    * InOrOut 2代表支出1代表收入
    * 
    * @param UserId
    * @param InOrOut
    * @return
    */
   public List<ModCategory> GetAllParentCategorysAndId(int UserId, int InOrOut)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  List<ModCategory> list = null;
	  try
	  {
		 String sqlString = "Select [CategoryName],[CategoryId] From [Category] Where [UserId]=? And [InOrOut]=? And [ParentCategoryId]=0 And [Disabled]=0";
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(sqlString, new String[] { String.valueOf(UserId), String.valueOf(InOrOut) });
		 list = new ArrayList<ModCategory>();
		 if (cursor.moveToFirst() && cursor.getCount() > 0)
		 {
			do
			{
			   ModCategory modCategory=new ModCategory();	
			   modCategory.setCategoryName(cursor.getString(0));
			   modCategory.setCategoryId(cursor.getInt(1));
			   list.add(modCategory);
			} while (cursor.moveToNext());
			return list;
		 }
		 else
		 {
			return null;
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
	  return null;
   }
   
   /**
    * CategoryName, UserId, InOrOut必填 2代表支出1代表收入
    * 
    * @param modCategory
    * @return
    */
   public List<ModCategory> GetAllChildrenCategorysAndId(int UserId, int InOrOut, String name)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  List<ModCategory> list = null;
	  try
	  {
		 String sqlString = "Select [CategoryName],[CategoryId] From [Category] Where [UserId]=? And [ParentCategoryId]=? And [InOrOut]=?  And [Disabled]=0";
		 db = dbHelper.getReadableDatabase();
		 int sID = super.getIdByName(db, DicCategory.CategoryId, DicCategory.TableName, DicCategory.CategoryName, name);
		 cursor = db.rawQuery(sqlString, new String[] { String.valueOf(UserId), String.valueOf(sID), String.valueOf(InOrOut) });
		 list = new ArrayList<ModCategory>();
		 if (cursor.moveToFirst() && cursor.getCount() > 0)
		 {
			do
			{
			   ModCategory modCategory=new ModCategory();	
			   modCategory.setCategoryName(cursor.getString(0));
			   modCategory.setCategoryId(cursor.getInt(1));
			   list.add(modCategory);
			} while (cursor.moveToNext());
			return list;
		 }
		 else
		 {
			return null;
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
	  return null;
   }


   /**
    * CategoryName, UserId, InOrOut必填 2代表支出1代表收入 0为异常
    * 
    * @param modCategory
    * @return
    */
   public int GetRandomChildrenCategoryId(int UserId, int InOrOut)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  int id = 0;
	  try
	  {
		 String sqlString = "Select [CategoryId] From [Category] Where [UserId]=? And [InOrOut]=? And [ParentCategoryId]！=0  And [Disabled]=0";
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery(sqlString, new String[] { String.valueOf(UserId), String.valueOf(InOrOut) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)
		 {
			do
			{
			   id = cursor.getInt(0);
			   break;
			} while (cursor.moveToNext());
			return id;
		 }
		 else
		 {
			return 0;
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
	  return id;
   }

   
   /**
    * 判断是否存在此种类的名称，存在则添加失败，不存在则添加新种类
    * 需要ParentCategoryId,CategoryName,Icon,InOrOut,UserId
    * 
    * @param modCategory
    * @return false表示插入失败或者已经存在用户 true表示插入成功
    */
   public boolean InsertCategory(ModCategory modCategory)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;

	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Category] Where [CategoryName]=? And [UserId]=? And [Disabled]=0",
			   new String[] { modCategory.getCategoryName(), String.valueOf(modCategory.getUserId()) });
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

			String sqlString = "INSERT INTO [Category] ([ParentCategoryId],[UserId],[InOrOut],[CategoryName],[UseCount],[Icon],[CreateTime],[ModifyTime],[Disabled]) Values (%s,%s,%s,'%s',%s,'%s',%s,%s,%s)";
			sqlString = String.format(sqlString, modCategory.getParentCategoryId(), modCategory.getUserId(), modCategory.getInOrOut(), modCategory.getCategoryName(), modCategory.getUseCount(),
				  modCategory.getIcon(), modCategory.getCreateTime(), modCategory.getModifyTime(), modCategory.getDisabled());
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
    * 更新种类 需要赋值CategoryName,ModifyTime,UserId,Icon,
    * oldname为之前的值，categoryname为之后的值
    * 
    * @param modBusiness
    */
   public boolean UpdateCategory(ModCategory modCategory, String OldName)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 cursor = db.rawQuery("Select count(*) as num From [Category] Where [CategoryName]=? And [UserId]=? And [Icon]=? And [Disabled]=0",
			   new String[] { modCategory.getCategoryName(), String.valueOf(modCategory.getUserId()),modCategory.getIcon()});
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

			int sID = super.getIdByName(db, DicCategory.CategoryId, DicCategory.TableName, DicCategory.CategoryName, OldName);
			String sqlString = "UPDATE [Category] SET [CategoryName]='%s',[ModifyTime]=%s,[Icon]='%s' WHERE [Disabled]=0 AND [CategoryId]=%s";
			sqlString = String.format(sqlString, modCategory.getCategoryName(), modCategory.getModifyTime(), modCategory.getIcon(), sID);
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
    * 删除种类 需要赋值CategoryName,ParentCategoryId,ModifyTime,UserId
    * 
    * @param modBusiness
    */
   public boolean DeleteCategory(ModCategory modCategory)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();
		 int sID = super.getIdByName(db, DicCategory.CategoryId, DicCategory.TableName, DicCategory.CategoryName, modCategory.getCategoryName());

		 String sqlString = "UPDATE [Category] SET [Disabled]=1,[ModifyTime]=%s WHERE [CategoryId]=%s";
		 sqlString = String.format(sqlString, modCategory.getModifyTime(),sID);

		 // 如果是父种类，则检查其下是不是有子节点，有的话不让删，没有才能删，
		 if (modCategory.getParentCategoryId() == 0)
		 {
			cursor = db.rawQuery("Select count(*) as num From [Category] Where [ParentCategoryId]=? And [UserId]=? And [Disabled]=0",
				  new String[] { modCategory.getCategoryName(), String.valueOf(modCategory.getUserId()), String.valueOf(modCategory.getInOrOut()) });
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

			   db.execSQL(sqlString);
			   return true;
			}
			else
			{
			   return false;
			}
		 }
		 // 如果是子节点则直接删除
		 else
		 {
			db.execSQL(sqlString);
			return true;
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
