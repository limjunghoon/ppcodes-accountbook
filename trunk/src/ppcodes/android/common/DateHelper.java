package ppcodes.android.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class DateHelper
{
   static SimpleDateFormat tempDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
   static SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
   static SimpleDateFormat tempDateNoSplit = new SimpleDateFormat("yyyyMMdd");
   static SimpleDateFormat tempDateChinese = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
   static SimpleDateFormat tempDateChineseDayOnly = new SimpleDateFormat("MM" + "月" + "dd" + "日");

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

   public static Date[] GetThisWeekDate(Date date)
   {
	  Date begin = null;
	  Date end = null;
	  
	  if(GetDayOfWeek(date)==1)
	  {
		 begin=date;
		 end=AddDay(date, 6);
	  }
	  else if (GetDayOfWeek(date)==7)
	  {
		 begin=AddDay(date, -6);
		 end=date;
	  }
	  else
	  {
		 begin=AddDay(date, -(GetDayOfWeek(date)-1));
	     end=AddDay(date, 7-GetDayOfWeek(date));
	  }
	  return new Date[]{begin,end};
   }

   public static Date[] GetThisMonthDate(Date date)
   {
	  Date begin=new Date(date.getYear(), date.getMonth(), 1);
	  Date end=new Date(date.getYear(), date.getMonth(), GetMonthMaxDay(date.getYear(), date.getMonth()));
	  return new Date[]{begin,end};
   }
   
   public static Date AddDay(Date date, int num)
   {
	  try
	  {
		 Calendar cd = Calendar.getInstance();
		 cd.setTime(date);
		 cd.add(Calendar.DATE, num);// 增加一天
		 return cd.getTime();
	  }
	  catch (Exception e)
	  {
		 return null;
	  }
   }

   public static Date AddMonth(Date date, int num)
   {
	  try
	  {
		 Calendar cd = Calendar.getInstance();
		 cd.setTime(date);
		 cd.add(Calendar.MONTH, num);//增加一个月
		 return cd.getTime();
	  }
	  catch (Exception e)
	  {
		 return null;
	  }
   } 
   
   public static int GetMonthMaxDay(int year,int month)
   {
	    Calendar c = Calendar.getInstance();  
	    c.set(Calendar.YEAR, year);  
	    c.set(Calendar.MONTH, month - 1);  
	    c.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    c.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = c.get(Calendar.DATE);  
	    return maxDate;  
   }
   
   /**
    * 返回的数字1代表星期一，7代表星期天，以此类推
    * 
    * @param pTime
    * @return
    */
   public static int GetDayOfWeek(Date date)
   {
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar c = Calendar.getInstance();
	  // try
	  // {
	  // c.setTime(format.parse(pTime));
	  // }
	  // catch (ParseException e)
	  // {
	  // // TODO Auto-generated catch block
	  // e.printStackTrace();
	  // }
	  c.setTime(date);
	  int dayForWeek = 0;
	  if (c.get(Calendar.DAY_OF_WEEK) == 1)
	  {
		 dayForWeek = 7;
	  }
	  else
	  {
		 dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
	  }
	  return dayForWeek;
   }
}
