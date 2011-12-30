package ppcodes.accountbook.common;

import java.lang.reflect.Field;

import android.R.integer;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import ppcodes.accountbook.app.R;

public class DataHelper extends Activity
{
   public static int GetDrawIdByName(String name)
   {
	  Field ff;
	  try
	  {
		 ff = R.drawable.class.getField(name);		 
		 int rId = ff.getInt(R.drawable.class);
		 return rId;
	  }
	  catch (SecurityException e1)
	  {
		 // TODO Auto-generated catch block
		e1.printStackTrace();
	  }
	  catch (NoSuchFieldException e1)
	  {
		 // TODO Auto-generated catch block
		e1.printStackTrace();
	  }
	  catch (IllegalArgumentException e)
	  {
		 // TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  catch (IllegalAccessException e)
	  {
		 // TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  return R.drawable.icon_category_default;
   }
   
   public static String GetDrawName(Drawable draw)
   {
	  
   }

}
