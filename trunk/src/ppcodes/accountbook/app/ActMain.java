package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoInOutDetails;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.DateHelper;
import android.R.integer;
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

   //属性
   Dialogs _dialogs;
   Dialogs getDialogs()
   {
	  if(_dialogs==null)
		 _dialogs=new Dialogs(this);
	  return _dialogs;
   }
   
   Session _session;
   Session getSession()
   {
	 if(_session==null)
		_session=(Session)getApplicationContext();
	 return _session;
   }
   
   
   DaoInOutDetails _daoInOutDetails;
   DaoInOutDetails getDaoInOutDetails()
   {
	  if(_daoInOutDetails==null)
	  {
		 _daoInOutDetails=new DaoInOutDetails(this);
	  }
	  return _daoInOutDetails;
   }
   
   

   //空件
   ImageView imgMainStream;
   ImageView imgMainBuddgetBottom;
   ImageView imgMainConfig;
   ImageView imgMainAccount;
   ImageView imgMainReport;
   ListView listView;
   ScrollView scrollView;
   ProgressBar proACTMainBudget;
   TextView txtInMoney;
   TextView txtOutMoney;
   Button btnNew;

   //字段
   Date[] week=DateHelper.GetThisWeekDate(new Date());//得到本周起始结束日期
   Date[] month=DateHelper.GetThisMonthDate(new Date());//得到本月起始结束日期
   Float dayMoneyIn;
   Float weekMoneyIn;
   Float monthMoneyIn;
   Float dayMoneyOut;
   Float weekMoneyOut;
   Float monthMoneyOut;
   final int NEWRECORD=1;
   
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
	  btnNew = (Button) findViewById(R.id.btnNewAccount_Act_main);
	  txtInMoney=(TextView)findViewById(R.id.txtInMoney_Act_main);
	  txtOutMoney=(TextView)findViewById(R.id.txtOutMoney_Act_main);
	  
	  proACTMainBudget = (ProgressBar) findViewById(R.id.proBudget_Act_main);
      listView=(ListView)findViewById(R.id.listviewJournal_Act_main);
	  scrollView=(ScrollView)findViewById(R.id.scrollView1);
      scrollView.post(new Runnable()
      {
    	 @Override
    	 public void run()
    	 {
    		// TODO Auto-generated method stub
    		scrollView.fullScroll(View.FOCUS_UP); 
    	 }
      });
      
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
	  //新记一笔帐
	  btnNew.setOnClickListener(new Button.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(ActMain.this, ActNewRecording.class);
			startActivityForResult(intent, NEWRECORD);
		 }
	  });
	  //设置
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
      //流水
	  imgMainStream.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			getDialogs().ShowOKAlertDialog("", "建设中");
		 }
	  });
      //预算
	  imgMainBuddgetBottom.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			getDialogs().ShowOKAlertDialog("", "建设中");
		 }
	  });
      //报表
	  imgMainReport.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			getDialogs().ShowOKAlertDialog("", "建设中");
		 }
	  });
      //贷款
	  imgMainAccount.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			getDialogs().ShowOKAlertDialog("", "建设中");
		 }
	  });
   }

   //返回今天本周和本月的情况
   List<Map<String, Object>> GetData()
   {  
      List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	  Map<String, Object> map=new HashMap<String, Object>();
	  map.put("img", R.drawable.main_today);
	  map.put("day", (new Date()).getDay());
	  map.put("dayName", "今天");
	  map.put("dayDetails", DateHelper.ToDateChinese(new Date()));
	  map.put("incoming", dayMoneyIn);      
	  map.put("payout", dayMoneyOut);
	  list.add(map);
	  
	  map=new HashMap<String, Object>();
	  map.put("img", R.drawable.main_week);
	  map.put("day", "");
	  map.put("dayName", "本周");
	  map.put("dayDetails", DateHelper.ToDateChineseDay(week[0])+"-"+DateHelper.ToDateChineseDay(week[1]));
	  map.put("incoming", weekMoneyIn);
	  map.put("payout", weekMoneyOut);
	  list.add(map);
	  
	  map=new HashMap<String, Object>();
	  map.put("img", R.drawable.main_week);
	  map.put("day", "");
	  map.put("dayName", "本月");
	  map.put("dayDetails", DateHelper.ToDateChineseDay(month[0])+"-"+DateHelper.ToDateChineseDay(month[1]));
	  map.put("incoming", monthMoneyIn);
	  map.put("payout", monthMoneyOut);
	  list.add(map);
	  return list;
   }
  
   //将今天本周和本月的情况加载列表
   void LoadExpenseState()
   {
	  List<Map<String, Object>> list=GetData();

	  SimpleAdapter sAdapter=new SimpleAdapter(ActMain.this, list, R.layout.listview_main, 
			new String[]{"img","day","dayName","dayDetails","incoming","payout"}, 
			new int[]{R.id.img_listview_main,R.id.txtDate_listview_main,R.id.txtDate2_listview_main,
			R.id.txtDateDetails_listview_main,R.id.txtIncoming_listview_main,R.id.txtPayout_listview_main});
	  listView.setAdapter(sAdapter);
	  
	  txtInMoney.setText(String.valueOf(monthMoneyIn));
	  txtOutMoney.setText(String.valueOf(monthMoneyOut));
   }
   
   //获取今天本周本月的钱数 
   void LoadExpenseMoney()
   {
	  try
      {
      	  dayMoneyIn=getDaoInOutDetails().GetInOutAmount(getSession().getUserId(),Enums.InOrOut.Incoming.getValue(), new Date());
      	  weekMoneyIn=getDaoInOutDetails().GetInOutAmount(getSession().getUserId(),Enums.InOrOut.Incoming.getValue(), week[0], week[1]);
      	  monthMoneyIn=getDaoInOutDetails().GetInOutAmount(getSession().getUserId(),Enums.InOrOut.Incoming.getValue(), month[0], month[1]);
      	  dayMoneyOut=getDaoInOutDetails().GetInOutAmount(getSession().getUserId(),Enums.InOrOut.Payout.getValue(), new Date());
      	  weekMoneyOut=getDaoInOutDetails().GetInOutAmount(getSession().getUserId(),Enums.InOrOut.Payout.getValue(), week[0], week[1]);
      	  monthMoneyOut=getDaoInOutDetails().GetInOutAmount(getSession().getUserId(),Enums.InOrOut.Payout.getValue(), month[0], month[1]);
      }
      catch (Exception e)
      {
	    // TODO: handle exception
	    getDialogs().ShowOKAlertDialog("ERROR", "LoadExpenseMoney-"+e.getMessage()+e.getStackTrace());
      }
   }
   
   //=====================onCreate===========================
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_main);



	  InitControls();
	  InitControlsListener();

	  try
	  {
		 LoadingAnimation();
	     LoadExpenseMoney();	  
	     LoadExpenseState();
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 getDialogs().ShowOKAlertDialog("ERROR","onCreate-"+e.getMessage()+e.getStackTrace());
	  }
	  


   }

   
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
   {
	  // TODO Auto-generated method stub
      switch (requestCode)
	  {
		 case NEWRECORD://从增加页面返回时，更新数据
			if(resultCode==RESULT_OK)
			{
			  LoadExpenseMoney();
			  LoadExpenseState();
			}
			break;

		 default:
			break;
	  }
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