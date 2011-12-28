package ppcodes.accountbook.dao;

import android.content.Context;

public class DaoFactory
{
   private static DaoFactory _Instance;
   public static DaoFactory getInstance()
   {
	  if (_Instance == null)
	  {
		 _Instance = new DaoFactory();
	  }
	  return _Instance;
   }
   
   public DaoBusiness CreateBusinessInstance(Context context)
   {
	  return new DaoBusiness(context);
   }
   
   public DaoUserInfo CreateUserInfoInstance(Context context)
   {
	  return new DaoUserInfo(context);
   }

}
