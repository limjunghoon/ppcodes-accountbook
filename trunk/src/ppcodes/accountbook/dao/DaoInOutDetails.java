package ppcodes.accountbook.dao;

import java.util.ArrayList;
import java.util.List;

import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.accountbook.entity.model.ModInOutDetails;
import ppcodes.android.common.DBHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DaoInOutDetails extends DaoBase
{
   private DBHelper dbHelper;

   public DaoInOutDetails(Context context)
   {
	  dbHelper = new DBHelper(context);
   }
   
   public List<ModInOutDetails> GetAllInOutDetailsByUserId(int UserId)
   {
	  SQLiteDatabase db = null;
	  Cursor cursor = null;
	  try
	  {
		 db = dbHelper.getReadableDatabase();
		 cursor = db.rawQuery("Select b.[CategoryName] as CategoryName,a.[CategoryId]," +
		 		             "c.[CategoryName] as CategoryChildName,a.[CategoryChildId]," +
		 		             "d.[ProjectName],a.[ProjectId]," +
		 		             "e.[AccountName],a.[AccountId]," +
		 		             "f.[BusinessName],a.[BusinessId]," +
		 		             "a.[UserId]"+ 
			                 " From [InOutDetails] a"+ 
			                 " Inner Join Category b On b.CategoryId=a.CategoryId"+ 
			                 " Inner Join Category c On c.CategoryId=a.CategoryChildId"+ 
			                 " Inner Join Project d On d.ProjectId  =a.ProjectId"+ 
			                 " Inner Join Account e On e.AccountId  =a.AccountId"+ 
			                 " Inner Join Business f On f.BusinessId=a.BusinessId"+ 
			                 " Where a.[UserId]=? And a.[Disabled]=0", 
			                 new String[] { String.valueOf(UserId) });
		 if (cursor.moveToFirst() && cursor.getCount() > 0)// 判断不为空
		 {
			List<ModInOutDetails> list = new ArrayList<ModInOutDetails>();
			do
			{
			   ModInOutDetails modInOutDetails=new ModInOutDetails();
			   modInOutDetails.setCategoryName(cursor.getString(0));
			   modInOutDetails.setCategoryId(cursor.getInt(1));
			   modInOutDetails.setCategoryChildName(cursor.getString(2));
			   modInOutDetails.setCategoryChildId(cursor.getInt(3));
			   modInOutDetails.setProjectName(cursor.getString(4));
			   modInOutDetails.setProjectId(cursor.getInt(5));
			   modInOutDetails.setAccountName(cursor.getString(6));
			   modInOutDetails.setAccountId(cursor.getInt(7));
			   modInOutDetails.setBusinessName(cursor.getString(8));
			   modInOutDetails.setBusinessId(cursor.getInt(9));
			   modInOutDetails.setUserId(cursor.getInt(10));
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
		 Log.e("ERROR", e.getMessage() + "DaoBusiness.GetAllInOutDetailsByUserId（）");
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
    * @param modInOutDetails
    * @return false表示插入失败或者已经存在用户 true表示插入成功
    */
   public boolean InsertInOutDetails(ModInOutDetails modInOutDetails)
   {
	  SQLiteDatabase db = null;
	  try
	  {
		 db = dbHelper.getWritableDatabase();

		String sqlString = "INSERT INTO [InOutDetails] ([UserId],[CategoryId],[CategoryChildId],[AccountId],[BusinessId],[ProjectId],[Amount],[InOrOut],[Remarks],[Date],[CreateTime],[ModifyTime],[Disabled]) Values (%s,%s,%s,%s,%s,%s,%s,%s,'%s',%s,%s,%s,%s)";
		sqlString = String.format(sqlString, modInOutDetails.getUserId(), modInOutDetails.getCategoryId(), modInOutDetails.getCategoryChildId(),modInOutDetails.getAccountId(),modInOutDetails.getBusinessId(),
			  modInOutDetails.getProjectId(),modInOutDetails.getAmount(),modInOutDetails.getInOrOut(),modInOutDetails.getRemarks(),modInOutDetails.getDate(),modInOutDetails.getCreateTime(),modInOutDetails.getModifyTime(),modInOutDetails.getDisabled());
		db.execSQL(sqlString);
		return true;
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

}
