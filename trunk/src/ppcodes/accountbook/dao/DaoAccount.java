package ppcodes.accountbook.dao;

import ppcodes.android.common.DBHelper;
import android.content.Context;

public class DaoAccount
{
   private DBHelper dbHelper;

   public DaoAccount(Context context)
   {
	  dbHelper = new DBHelper(context);
   }
}