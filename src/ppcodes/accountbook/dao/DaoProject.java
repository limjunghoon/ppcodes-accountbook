package ppcodes.accountbook.dao;

import ppcodes.android.common.DBHelper;
import android.content.Context;

public class DaoProject
{
   private DBHelper dbHelper;

   public DaoProject(Context context)
   {
	  dbHelper = new DBHelper(context);
   }
}
