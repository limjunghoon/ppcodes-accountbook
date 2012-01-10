package ppcodes.accountbook.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class ActAbout extends Activity
{

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_about);
   }

   // 按下返回键退出
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
   {
	  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	  { // 按下的如果是BACK，同时没有重复
		 this.finish();
	  }
	  return false;
   }
  
}
