package ppcodes.accountbook.app;

import java.util.Date;

import ppcodes.accountbook.common.DataHelper;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoAccount;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.dao.DaoCategory;
import ppcodes.accountbook.dao.DaoProject;
import ppcodes.accountbook.entity.model.ModAccount;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.accountbook.entity.model.ModCategory;
import ppcodes.accountbook.entity.model.ModProject;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.DateHelper;
import ppcodes.android.common.gvImageAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
public class TmpSettingItemEdit extends Activity
{
   //控件
   private GridView gvIcon;
   private ImageView imgCurrent;
   private LinearLayout lLay;
   private TableRow tRow;
   private EditText edtName;
   private ImageView imgOK;
   
   // 字段
   int SETTING_TYPE;
   int imgId;
   String imgName;
   String itemName;
   
   Session _session;
   Session getSession()
   {
	  if(_session==null)
		 _session=(Session)getApplicationContext();
	  return _session;
   }

   Dialogs _dialogs;
   Dialogs getDialogs()
   {
	  if(_dialogs==null)
		 _dialogs=new Dialogs(this);
	  return _dialogs;
   }
   
   DaoBusiness _daoBusiness;
   private DaoBusiness getDaoBusiness()
   {
		 if(_daoBusiness==null)
		 {	
			_daoBusiness=new DaoBusiness(this);
		 }
	     return _daoBusiness;
   }
   
   DaoProject _daoProject;   
   private DaoProject getDaoProject()
   {
		 if(_daoProject==null)
		 {	
			_daoProject=new DaoProject(this);
		 }
	     return _daoProject;
   }
   
   DaoAccount _daoAccount;
   private DaoAccount getDaoAccount()
   {
		 if(_daoAccount==null)
		 {	
			_daoAccount=new DaoAccount(this);
		 }
	     return _daoAccount;
   }
   
   DaoCategory _daoCategory;
   private DaoCategory getDaoCategory()
   {
		 if(_daoCategory==null)
		 {	
			_daoCategory=new DaoCategory(this);
		 }
	     return _daoCategory;
   }

   
   
   void InitControls()
   {
	 gvIcon=(GridView)findViewById(R.id.gvIcon_Act_Tmp_setting_item_edit);
	 
	 imgCurrent=(ImageView)findViewById(R.id.imgCurrent_Act_Tmp_setting_item_edit);
	 imgCurrent.setImageResource(imgId);
	 
	 lLay=(LinearLayout)findViewById(R.id.lay_Act_Tmp_setting_item_edit);
	 
	 tRow=(TableRow)findViewById(R.id.row_Act_Tmp_setting_item_edit);
	 
	 edtName=(EditText)findViewById(R.id.edtName_Act_Tmp_setting_item_edit);
	 edtName.setText(itemName);
	 //将光标移动到字符串最后一位
	 Editable mEditable=edtName.getText();
	 Selection.setSelection(mEditable, mEditable.length());
	 
	 imgOK=(ImageView)findViewById(R.id.imgOK_Act_Tmp_setting_item_edit);

   }
   
   
   void InitControlsListener()
   {
      imgOK.setOnClickListener(new View.OnClickListener()
	  {
	     @Override
	     public void onClick(View v)
	     {
	  	  // TODO Auto-generated method stub
	    	
	      String sName=edtName.getText().toString().trim();
	      
	      if(sName.equals("")||sName==null)//不能为空
	      {
	    	 getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_notBeEmpty));	
	      }
	      
		  else if (sName.equals(itemName)&&imgName.equals(getIntent().getStringExtra("img")))//无更改直接返回
		  {
			 finish();
		  }
	      
	      else if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
		  {
			 ModCategory modCategory=new ModCategory();
			 modCategory.setCategoryName(sName);
			 modCategory.setUserId(getSession().getUserId());
			 modCategory.setModifyTime(DateHelper.ToDateTime(new Date()));
			 modCategory.setIcon(imgName);
			 
			 if(getDaoCategory().UpdateCategory(modCategory, itemName))
			 {
				finish();
			 }
			 else 
			 {	
				getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip),"种类"+getString(R.string.alert_existOrError));
			 }
		  }
	      
		  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
		  {
			 ModCategory modCategory=new ModCategory();
			 modCategory.setCategoryName(sName);
			 modCategory.setUserId(getSession().getUserId());
			 modCategory.setModifyTime(DateHelper.ToDateTime(new Date()));
			 modCategory.setIcon(imgName);
			 
			 if(getDaoCategory().UpdateCategory(modCategory, itemName))
			 {
				finish();
			 }
			 else 
			 {	
				getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip),"种类"+getString(R.string.alert_existOrError));
			 }
		  }
	      
		  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
		  {
			 ModAccount modAccount=new ModAccount();
			 modAccount.setAccountName(sName);
			 modAccount.setModifyTime(DateHelper.ToDateTime(new Date()));
			 modAccount.setUserId(getSession().getUserId());
			 
			 if(getDaoAccount().UpdateAccountName(modAccount, itemName))
			 {
				finish();
			 }
			 else
			 {
				getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip),"账户"+getString(R.string.alert_existOrError));
			 };
		  }
	      
		  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
		  {
			 ModProject modProject=new ModProject();
			 modProject.setProjectName(sName);
			 modProject.setModifyTime(DateHelper.ToDateTime(new Date()));
			 modProject.setUserId(getSession().getUserId());
			 
			 if(getDaoProject().UpdateProjectName(modProject, itemName))
			 {
				finish();
			 }
			 else
			 {
				getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip),"项目"+getString(R.string.alert_existOrError));
			 }
		  }
	      
		  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
		  {
			 ModBusiness modBusiness=new ModBusiness();
			 modBusiness.setBusinessName(sName);
			 modBusiness.setModifyTime(DateHelper.ToDateTime(new Date()));
			 modBusiness.setUserId(getSession().getUserId());
			 
			 if(getDaoBusiness().UpdateBusinessName(modBusiness, itemName))
			 {
				finish();
			 }
			 else
			 {
				getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip),"商家"+getString(R.string.alert_existOrError));
			 }
	      }	 
	     }
	  });

   }
   
   void SetIconState()
   {
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
		 IsShowIconAndLoadIconList(true);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
		 IsShowIconAndLoadIconList(true);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 IsShowIconAndLoadIconList(false);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
		 IsShowIconAndLoadIconList(false);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 IsShowIconAndLoadIconList(false);
      }	 
   }
   
   void IsShowIconAndLoadIconList(boolean isShow)
   {
	 if(isShow)
	 {
		lLay.setVisibility(View.VISIBLE);
		tRow.setVisibility(View.VISIBLE);
		LoadingImageAndInitGV();
	 }
	 else 
	 {
		lLay.setVisibility(View.GONE);
		tRow.setVisibility(View.GONE);
     }
   }
   
   //加载图片到列表并初始化单击事件
   void LoadingImageAndInitGV()
   {
	  gvImageAdapter gvAdapter=new gvImageAdapter(this);
	  gvIcon.setAdapter(gvAdapter);
	  gvIcon.setOnItemClickListener(new AdapterView.OnItemClickListener()
	  {		 @Override
		 public void onItemClick(AdapterView<?> gView, View gItem, int position, long index)
		 {
			// TODO Auto-generated method stub
		   ImageView imgView=(ImageView)gItem;
		   imgCurrent.setImageDrawable(imgView.getDrawable());
		   //获得图片的名称
		   imgName=imgView.getTag()==null?imgName:imgView.getTag().toString();
		 } 
	  });
   }
   
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
	  // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_tmp_setting_item_edit);
	  
	  SETTING_TYPE = getIntent().getIntExtra(Enums.ItemTypeValue, 0);
	  itemName=getIntent().getStringExtra("name");
	  imgName=getIntent().getStringExtra("img");
      imgId=DataHelper.GetDrawIdByName(imgName);
      
	  InitControls();
	  InitControlsListener();
          
	  SetIconState();
	  
	  new Handler().postDelayed(new Runnable()// 界面进入时就自动打开输入法
			{
			   @Override
			   public void run()
			   {
				  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				  imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			   }
			}, 300);
   }
}
