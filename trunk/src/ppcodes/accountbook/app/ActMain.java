package ppcodes.accountbook.app;

import java.util.Date;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.common.Session;
import ppcodes.android.common.Dialogs;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ActMain extends Activity
{
   /** Called when the activity is first created. */

   Dialogs dialogs;

   private ImageView imgMainStream;
   private ImageView imgMainBuddgetBottom;
   private ImageView imgMainConfig;
   private ImageView imgMainAccount;
   private ImageView imgMainReport;

   private ProgressBar proACTMainBudget;

   
   // 动画效果
   void LoadingAnimation()
   {
	  new Thread(new Runnable()
	  {
		 @Override
		 public void run()
		 {
			// TODO Auto-generated method stub
			for (int i = 0; i < 100; i++)
			{
			   try
			   {
				  Thread.sleep(i);
			   }
			   catch (InterruptedException e)
			   {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			   }
			   proACTMainBudget.setProgress(i);
			}
		 }
	  }).start();
   }

   // 退出程序提示
   void FinishDialog()
   {
	  OnClickListener listener = new OnClickListener()
	  {
		 @Override
		 public void onClick(DialogInterface dialog, int which)
		 {
			// TODO Auto-generated method stub
			dialog.dismiss();
			// Act_YiguoMain.this.finish();
			android.os.Process.killProcess(android.os.Process.myPid());
		 }
	  };

	  dialogs.ShowOKCancelAlertDialog("提示", "您打算退出程序吗？", listener);
   }

   // 初始化控件
   void InitControls()
   {
	  // 下面导航
	  imgMainStream = (ImageView) findViewById(R.id.imgStream_Act_main);
	  imgMainBuddgetBottom = (ImageView) findViewById(R.id.imgBudgetBottom_Act_main);
	  imgMainAccount = (ImageView) findViewById(R.id.imgAccount_Act_main);
	  imgMainReport = (ImageView) findViewById(R.id.imgReport_Act_main);
	  imgMainConfig = (ImageView) findViewById(R.id.imgConfig_Act_main);

	  // 正文部分
	  proACTMainBudget = (ProgressBar) findViewById(R.id.proBudget_Act_main);

	  // 顶部
	  TextView txtACTMainView = (TextView) findViewById(R.id.txtDate_Act_main);
	  txtACTMainView.setText(String.valueOf(new Date().getMonth() + 1) + "月");

	  Session session = (Session) getApplicationContext();
	  TextView txtUserName = (TextView) findViewById(R.id.txtUserName_Act_main);
	  txtUserName.setText("当前帐号:" + session.getUserName());
   }

   // 初始化监视器
   void InitControlsListener()
   {
	  imgMainConfig.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(ActMain.this, ActSetting.class);
			startActivity(intent);
		 }
	  });

	  imgMainStream.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Toast.makeText(ActMain.this, "OK1", 500).show();
		 }
	  });

	  imgMainBuddgetBottom.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Toast.makeText(ActMain.this, "OK2", 500).show();
		 }
	  });

	  imgMainReport.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Toast.makeText(ActMain.this, "OK3", 500).show();
		 }
	  });

	  imgMainAccount.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Toast.makeText(ActMain.this, "OK4", 500).show();
		 }
	  });

	  
	 
   }

   // nCreate
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_main);

	  dialogs = new Dialogs(ActMain.this);

	  InitControls();
	  InitControlsListener();

	  Button btnNew = (Button) findViewById(R.id.btnNewAccount_Act_main);
	  btnNew.setOnClickListener(new Button.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(ActMain.this, ActNewRecording.class);
			startActivity(intent);
		 }
	  });

	  LoadingAnimation();
   }

   // 按下返回键退出
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
   {
	  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	  { // 按下的如果是BACK，同时没有重复
		 FinishDialog();
	  }
	  return false;
   }
}