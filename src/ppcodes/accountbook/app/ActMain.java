package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.common.Session;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.StringHelper;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ActMain extends Activity
{
   /** Called when the activity is first created. */

   Dialogs _dialogs;
   Dialogs getDialogs()
   {
	  if(_dialogs==null)
		 _dialogs=new Dialogs(this);
	  return _dialogs;
   }

   private ImageView imgMainStream;
   private ImageView imgMainBuddgetBottom;
   private ImageView imgMainConfig;
   private ImageView imgMainAccount;
   private ImageView imgMainReport;
   private ListView listView;
private ScrollView scrollView;
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

	  getDialogs().ShowOKCancelAlertDialog("提示", "您打算退出程序吗？", listener);
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
      listView=(ListView)findViewById(R.id.listviewJournal_Act_main);
	  scrollView=(ScrollView)findViewById(R.id.scrollView1);
      
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

   void LoadExpenseState()
   {
	  List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	  Map<String, Object> map=new HashMap<String, Object>();
	  map.put("img", R.drawable.main_today);
	  map.put("day", (new Date()).getDay());
	  map.put("dayName", "今天");
	  map.put("dayDetails", StringHelper.ToDateChinese(new Date()));
	  map.put("incoming", 8000);      
	  map.put("payout", 10000);
	  list.add(map);
	  
	  map=new HashMap<String, Object>();
	  map.put("img", R.drawable.main_week);
	  map.put("day", "");
	  map.put("dayName", "本周");
	  map.put("dayDetails", StringHelper.ToDateChineseDay(new Date()));
	  map.put("incoming", 28000);
	  map.put("payout", 310000);
	  list.add(map);
	  
	  map=new HashMap<String, Object>();
	  map.put("img", R.drawable.main_week);
	  map.put("day", "");
	  map.put("dayName", "本月");
	  map.put("dayDetails", StringHelper.ToDateChineseDay(new Date()));
	  map.put("incoming", 428000);
	  map.put("payout", 5310000);
	  list.add(map);
	
	  SimpleAdapter sAdapter=new SimpleAdapter(ActMain.this, list, R.layout.listview_main, 
			new String[]{"img","day","dayName","dayDetails","incoming","payout"}, 
			new int[]{R.id.img_listview_main,R.id.txtDate_listview_main,R.id.txtDate2_listview_main,
			R.id.txtDateDetails_listview_main,R.id.txtIncoming_listview_main,R.id.txtPayout_listview_main});
	  listView.setAdapter(sAdapter);
	  
//      scrollView.fullScroll(View.FOCUS_UP);
//      scrollView.scrollTo(0, 0);
   }
   
   // nCreate
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_main);
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
	  LoadExpenseState();
      scrollView.fullScroll(View.FOCUS_UP);
      scrollView.scrollBy(0, 0);
      scrollView.scrollTo(0, 0);
      
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