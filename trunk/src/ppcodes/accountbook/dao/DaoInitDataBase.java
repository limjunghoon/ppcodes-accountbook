package ppcodes.accountbook.dao;

import java.util.Date;

import ppcodes.android.common.DBHelper;
import ppcodes.android.common.StringHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DaoInitDataBase extends DaoBase
{
   private DBHelper dbHelper;

   public DaoInitDataBase(Context context)
   {
	  dbHelper = new DBHelper(context);
   }
   
   public void InitDataBase(int UserId)
   {
	  SQLiteDatabase db = null;

	  String nowTime=StringHelper.FormatDateTime(new Date());
	  try
	  {
		 
		 //初始化Business
		 String sqlBusiness = "INSERT INTO [Business] (UserId,BusinessName,CreateTime,ModifyTime,Disabled,UseCount)" +
	                        " SELECT "+UserId+",'无',"+nowTime+","+nowTime+",0,0" +
	                        " UNION ALL " +            
			                " SELECT "+UserId+",'肯德基',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'京东',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'易迅',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'新蛋',"+nowTime+","+nowTime+",0,0";
		 
		 //初始化Profile
		 String sqlProfile = "INSERT INTO [Profile] ([UserId],[InCategoryId],[OutCategoryId],[BusinessId],[AccountId],[ProjectId],[CreateTime],[ModifyTime]) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)";
		 sqlProfile=String.format(sqlProfile, UserId,1,1,1,1,1,nowTime,nowTime);
		 
		 db = dbHelper.getWritableDatabase();
		 db.beginTransaction();
		 
		 db.execSQL(sqlBusiness);
		 
		 db.execSQL(sqlProfile);
		 
		 db.setTransactionSuccessful();
		 db.endTransaction();
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
