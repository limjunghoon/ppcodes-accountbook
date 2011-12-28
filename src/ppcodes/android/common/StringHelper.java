package ppcodes.android.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper
{
   static SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
   
   public static String FormatDateTime(Date date)
   {
	  return tempDate.format(date);  
   }
}
