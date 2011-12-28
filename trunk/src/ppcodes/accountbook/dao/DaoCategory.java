package ppcodes.accountbook.dao;

import ppcodes.android.common.DBHelper;
import android.content.Context;

public class DaoCategory
{
   private DBHelper dbHelper;

   public DaoCategory(Context context)
   {
	  dbHelper = new DBHelper(context);
   }
}
