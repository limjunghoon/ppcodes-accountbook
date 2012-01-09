package ppcodes.android.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.provider.ContactsContract.Contacts.Data;

public class StringHelper
{
   static SimpleDateFormat tempDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
   static SimpleDateFormat tempDate=new SimpleDateFormat("yyyy-MM-dd");
   static SimpleDateFormat tempDateNoSplit=new SimpleDateFormat("yyyyMMdd");
   
   public static String FormatDateTime(Date date)
   {
	  return tempDateTime.format(date);  
   }
   
   public static String FormatDate(Date date)
   {
	  return tempDate.format(date);
   }
   
   public static String FormatDateNoSplit(Data date)
   {
	  return tempDateNoSplit.format(date);
   }
   
   public static String CovertDateToNoSplit(String Date)
   {
	  return Date.replace("-", "");
   }
}
