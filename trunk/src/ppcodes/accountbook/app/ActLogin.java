package ppcodes.accountbook.app;

import java.security.Key;
import java.text.SimpleDateFormat;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.dao.DaoUserInfo;
import ppcodes.accountbook.entity.model.ModUserInfo;
import ppcodes.android.common.Dialogs;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

public class ActLogin extends Activity
{
   // 字段
   private Button btnLogin;
   private ImageView imgLoginHelp;
   private CheckBox chkShowKey;
   private EditText edtUserName;
   private EditText edtUserKey;
   private CheckBox chkRememberName;

   // 类
   private Dialogs dialogs;
   private DaoUserInfo daoLogin;
   private DaoBusiness daoBusiness;

   private final String CACHE_USERNAME = "UserName";

   int uId;

   // 开始加载需要的资源
   void LoadingThread()
   {
	  // 打开进度条
	  dialogs.ShowLoadingDialogNoTitle("载入中。。。");
	  Thread thread = new Thread(new Runnable()
	  {
		 @Override
		 public void run()
		 {
			// TODO Auto-generated method stub
			try
			{
			   // 加载，初始化
			   daoLogin = new DaoUserInfo(ActLogin.this);
			   if (daoLogin.IsUserExist(edtUserName.getText().toString().trim()))
			   {
				  ModUserInfo modUserInfo = new ModUserInfo();
				  modUserInfo.setUserName(edtUserName.getText().toString().trim());
				  modUserInfo.setUserKey(edtUserKey.getText().toString().trim());
				  if (daoLogin.IsUserKeyRight(modUserInfo))
				  {
					 uId = daoLogin.GetUserIdByUserName(modUserInfo.getUserName());
					 LoginSuccess();
				  }
				  else
				  {
					 LoginFailed();
				  }
			   }
			   else
			   {
				  dialogs.setDialogDismiss();
				  Looper.prepare();
				  dialogs.ShowOKCancelAlertDialog("提示", "该用户名不存在，将创建一个新的用户，你确定吗？", new OnClickListener()
				  {
					 @Override
					 public void onClick(DialogInterface dialog, int which)
					 {
						// TODO Auto-generated method stub
						dialog.dismiss();
						dialogs.ShowLoadingDialogNoTitle("请稍候。。。");
						SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						ModUserInfo modUserInfo = new ModUserInfo(edtUserName.getText().toString().trim(), edtUserKey.getText().toString().trim(), tempDate.format(new java.util.Date()), tempDate
							  .format(new java.util.Date()), 0);
						daoLogin.InsertUser(modUserInfo);
						uId = daoLogin.GetUserIdByUserName(modUserInfo.getUserName());
						daoBusiness = new DaoBusiness(ActLogin.this);
						daoBusiness.InitBusiness(uId);

						LoginSuccess();
					 }
				  });
				  Looper.loop();
			   }
			}
			catch (Exception e)
			{
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			}
		 }
	  });
	  thread.start();
   }

   // 登录成功
   void LoginSuccess()
   {
	  // 进度条结束
	  dialogs.setDialogDismiss();
	  // 将登陆加入全局变量
	  Session session = (Session) getApplicationContext();
	  session.setUserName(edtUserName.getText().toString().trim());
	  session.setUserKey(edtUserKey.getText().toString().trim());
	  session.setUserId(uId);
	  // 跳转
	  Intent intent = new Intent();
	  intent.setClass(ActLogin.this, ActMain.class);
	  startActivity(intent);
	  ActLogin.this.finish();
   }

   // 登录失败
   void LoginFailed()
   {
	  dialogs.setDialogDismiss();
	  Looper.prepare();
	  dialogs.ShowOKAlertDialog("提示", "登录失败，请确定密码正确");
	  Looper.loop();
   }

   // 显示帮助
   void ShowHelpDialog()
   {
	  dialogs.ShowOKAlertDialog("提示", "使用不存在的账户和密码会自动创建一个新的账户");
   }

   // 初始化数据库数据
   void InitDataBase()
   {

   }

   // 初始化控件
   void InitControls()
   {
	  imgLoginHelp = (ImageView) findViewById(R.id.imgHelp_Act_login);
	  btnLogin = (Button) findViewById(R.id.btnLogin_Act_login);
	  chkShowKey = (CheckBox) findViewById(R.id.chkShowKey_Act_login);
	  chkRememberName = (CheckBox) findViewById(R.id.chkRememberName_Act_login);
	  edtUserName = (EditText) findViewById(R.id.edtUserName_Act_login);
	  edtUserKey = (EditText) findViewById(R.id.edtUserKey_Act_login);
   }

   // 初始化控件监听器
   void InitControlsListener()
   {
	  btnLogin.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			LoadingThread();

		 }
	  });

	  //当光标在密码框时按下输入法的回车键就登录
	  edtUserKey.setOnKeyListener(new View.OnKeyListener()
	  {
		 @Override
		 public boolean onKey(View v, int keyCode, KeyEvent event)
		 {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_ENTER)
			{
			   LoadingThread();
			}
			return false;
		 }
	  });

	  imgLoginHelp.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			ShowHelpDialog();
		 }
	  });

	  chkShowKey.setOnCheckedChangeListener(new OnCheckedChangeListener()
	  {
		 @Override
		 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		 {
			// TODO Auto-generated method stub
			if (isChecked)
			{
			   // edtUserKey.setInputType(0x90);//密码可见
			   edtUserKey.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			}
			else
			{
			   // edtUserKey.setInputType(0x81);//密码不可见
			   edtUserKey.setTransformationMethod(PasswordTransformationMethod.getInstance());
			}
		 }
	  });
   }

   // 退出提示框
   void FinishDialog()
   {
	  OnClickListener listener = new OnClickListener()
	  {
		 @Override
		 public void onClick(DialogInterface dialog, int which)
		 {
			// TODO Auto-generated method stub

			dialog.dismiss();
			android.os.Process.killProcess(android.os.Process.myPid());
		 }
	  };

	  dialogs.ShowOKCancelAlertDialog("提示", "您打算退出程序吗？", listener);
   }

   // onCreate
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_login);

	  SharedPreferences userSetting = getPreferences(Activity.MODE_PRIVATE);
	  String userNameString = userSetting.getString(CACHE_USERNAME, "");

	  dialogs = new Dialogs(ActLogin.this);
	  InitControls();
	  InitControlsListener();

	  if (!userNameString.equals(""))
	  {
		 chkRememberName.setChecked(true);
	  }
	  edtUserName.setText(userNameString);
   }

   // onDestory
   @Override
   protected void onDestroy()
   {
	  // 保存上一次用过的用户名
	  if (chkRememberName.isChecked())
	  {
		 SharedPreferences.Editor editor = getPreferences(0).edit();
		 editor.putString(CACHE_USERNAME, edtUserName.getText().toString().trim());
		 editor.commit();
	  }
	  else
	  {
		 SharedPreferences.Editor editor = getPreferences(0).edit();
		 editor.clear();
		 editor.commit();
	  }
	  super.onDestroy();
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
