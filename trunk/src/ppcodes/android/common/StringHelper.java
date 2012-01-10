package ppcodes.android.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.provider.ContactsContract.Contacts.Data;

public class StringHelper
{
   static SimpleDateFormat tempDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
   static SimpleDateFormat tempDate=new SimpleDateFormat("yyyy-MM-dd");
   static SimpleDateFormat tempDateNoSplit=new SimpleDateFormat("yyyyMMdd");
   static SimpleDateFormat tempDateChinese=new SimpleDateFormat("yyyy"+"年"+"MM"+"月"+"dd"+"日");
   static SimpleDateFormat tempDateChineseDayOnly=new SimpleDateFormat("MM"+"月"+"dd"+"日");
   
   public static String ToDateTime(Date date)
   {
	  return tempDateTime.format(date);  
   }
   
   public static String ToDate(Date date)
   {
	  return tempDate.format(date);
   }
   
   public static String ToDateNoSplit(Date date)
   {
	  return tempDateNoSplit.format(date);
   }
   
   public static String DelDateSplit(String Date)
   {
	  return Date.replace("-", "");
   }
   
   public static String ToDateChinese(Date date)
   {
	  return tempDateChinese.format(date);
   }
   
   public static String ToDateChineseDay(Date date)
   {
	  return tempDateChineseDayOnly.format(date);
   }
}
