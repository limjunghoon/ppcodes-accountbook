package ppcodes.android.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper
{
   static SimpleDateFormat tempDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
   static SimpleDateFormat tempDate=new SimpleDateFormat("yyyy-MM-dd");
   
   public static String FormatDateTime(Date date)
   {
	  return tempDateTime.format(date);  
   }
   
   public static String FormatDate(Date date)
   {
	  return tempDate.format(date);
   }
}
