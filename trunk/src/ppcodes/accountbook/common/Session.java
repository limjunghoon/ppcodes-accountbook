package ppcodes.accountbook.common;

import android.app.Application;

public class Session extends Application
{
   public String getUserName()
   {
	  return UserName;
   }

   public void setUserName(String userName)
   {
	  UserName = userName;
   }

   public String getUserKey()
   {
	  return UserKey;
   }

   public void setUserKey(String userKey)
   {
	  UserKey = userKey;
   }

   public int getUserId()
   {
	  return UserId;
   }

   public void setUserId(int userId)
   {
	  UserId = userId;
   }

   private String UserName;
   private String UserKey;
   private int UserId;
}
