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
	  {  //初始化Business
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
		 //初始化Project
		 String sqlProject = "INSERT INTO [Project] (UserId,ProjectName,CreateTime,ModifyTime,Disabled,UseCount)" +
	                        " SELECT "+UserId+",'无',"+nowTime+","+nowTime+",0,0" +
	                        " UNION ALL " +            
			                " SELECT "+UserId+",'出差',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'其他',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'其他2',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'其他3',"+nowTime+","+nowTime+",0,0";
		 //初始化Account
		 String sqlAccount = "INSERT INTO [Account] (UserId,AccountName,CreateTime,ModifyTime,Disabled,UseCount)" +
	                        " SELECT "+UserId+",'中国银行',"+nowTime+","+nowTime+",0,0" +
	                        " UNION ALL " +            
			                " SELECT "+UserId+",'建设银行',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'工商银行',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'招行金卡',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT "+UserId+",'招行工资卡',"+nowTime+","+nowTime+",0,0";
		 //初始化Category
		 String sqlCategory = "INSERT INTO [Category] (ParentCategoryId,InOrOut,Icon,UserId,CategoryName,CreateTime,ModifyTime,Disabled,UseCount)" +
	                        " SELECT 0,0,'icon_xcjt',"+UserId+",'交通支出',"+nowTime+","+nowTime+",0,0" +
	                        " UNION ALL " +            
			                " SELECT 0,0,'icon_spjs',"+UserId+",'餐饮支出',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT 0,1,'icon_jrbx',"+UserId+",'职业收入',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT 0,1,'icon_qtsr',"+UserId+",'其他收入',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
	                        " SELECT 1,0,'icon_xcjt_dczc',"+UserId+",'公交支出',"+nowTime+","+nowTime+",0,0" +
	                        " UNION ALL " +            
			                " SELECT 1,0,'icon_xcjt_ggjt',"+UserId+",'地铁支出',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT 2,0,'icon_spjs_sgls',"+UserId+",'中饭支出',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT 2,0,'icon_spjs_zwwc',"+UserId+",'晚饭支出',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
	                        " SELECT 3,1,'icon_jrbx_xfss',"+UserId+",'工资收入',"+nowTime+","+nowTime+",0,0" +
	                        " UNION ALL " +            
			                " SELECT 3,1,'icon_rqwl_hrqc',"+UserId+",'兼职收入',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT 4,1,'icon_qtsr_ywlq',"+UserId+",'意外来钱',"+nowTime+","+nowTime+",0,0" +
		 		            " UNION ALL " +
		 		            " SELECT 4,1,'icon_qtsr_zjsr',"+UserId+",'中奖所得',"+nowTime+","+nowTime+",0,0"; 
		 //初始化Profile
		 String sqlProfile = "INSERT INTO [Profile] ([UserId],[InCategoryId],[OutCategoryId],[BusinessId],[AccountId],[ProjectId],[CreateTime],[ModifyTime],[Disabled]) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)";
		 sqlProfile=String.format(sqlProfile, UserId,7,9,1,1,1,nowTime,nowTime,0);
	
		 db = dbHelper.getWritableDatabase();
		 db.beginTransaction();
		 
		 db.execSQL(sqlBusiness);
		 db.execSQL(sqlProject);
		 db.execSQL(sqlAccount);
		 db.execSQL(sqlProfile);
		 db.execSQL(sqlCategory);
		 
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
