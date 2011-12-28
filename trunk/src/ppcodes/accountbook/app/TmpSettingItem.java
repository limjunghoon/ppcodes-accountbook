package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoAccount;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.dao.DaoProfile;
import ppcodes.accountbook.dao.DaoProject;
import ppcodes.accountbook.entity.dictionary.DicBusiness;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.accountbook.entity.model.ModProfile;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.StringHelper;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TmpSettingItem extends Activity
{
   // 控件定义
   private TextView txtTitle;
   private ListView listView;
   private ImageView imgNew;
   private EditText edtNewItem;
   private Button btnNewItem;

   // 类
   Dialogs dialogs;
   Session session;
   DaoProfile daoProfile;
   DaoBusiness daoBusiness;
   DaoProject daoProject;
   DaoAccount daoAccount;

   
   // 字段
   int SETTING_TYPE;

   void InitControls()
   {
	  listView = (ListView) findViewById(R.id.listView_Act_Tmp_setting_item);
	  imgNew = (ImageView) findViewById(R.id.imgNew_Act_Tmp_setting);
	  edtNewItem = (EditText) findViewById(R.id.edtNewItem_Act_Tmp_setting);
      edtNewItem.clearFocus();
      btnNewItem = (Button) findViewById(R.id.btnNewItem_Act_Tmp_setting);
	  txtTitle = (TextView) findViewById(R.id.txtTitle_Act_Tmp_setting);
	  txtTitle.setText(getIntent().getStringExtra("TypeName"));

   }



   void InitControlsListener()
   {
	  btnNewItem.setOnClickListener(new OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			AddNewItem();
		 }
	  });

	  listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
	  {
		 @Override
		 public void onItemClick(AdapterView<?> parentView, View itemView, int posion, long index)
		 {
			// TODO Auto-generated method stubposion
			String sName=((TextView)itemView.findViewById(R.id.txtItemtype_listview_setting_item_details)).getText().toString();
			SetDefault(sName);
		 }
	  });

	  listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
	  {
		 @Override
		 public boolean onItemLongClick(AdapterView<?> parentView, View itemView, int posion, long index)
		 {
			// TODO Auto-generated method stub
			ShowEditMenu(itemView);
			return false;
		 }

	  });
   }

   void LoadSetting()
   {
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
		 imgNew.setVisibility(View.VISIBLE);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
		 imgNew.setVisibility(View.VISIBLE);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 imgNew.setVisibility(View.GONE);

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
		 imgNew.setVisibility(View.GONE);

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 imgNew.setVisibility(View.GONE);
		 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllBusiness(), R.layout.listview_setting_item_details, new String[] { "name" },
			   new int[] { R.id.txtItemtype_listview_setting_item_details });
		 listView.setAdapter(sAdapter);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.DataManage.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.OtherSetting.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.About.getValue())
	  {

	  }
   }

   void AddNewItem()
   {
	  if(edtNewItem.getText().toString().equals(""))
	  {
		 dialogs.ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_notBeEmpty));
	  }

      else if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 ModBusiness modBusiness = new ModBusiness(session.getUserId(), edtNewItem.getText().toString().trim(), StringHelper.FormatDateTime(new Date()), StringHelper.FormatDateTime(new Date()), 0, 0);
		 if (daoBusiness.InsertBusiness(modBusiness))
		 {
			LoadSetting();
		 }
		 else
		 {
			dialogs.ShowOKAlertDialog(getString(R.string.alert_tip), "商家已经存在或者添加出错");
		 }
	  }
	  else if (SETTING_TYPE == Enums.ItemType.DataManage.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.OtherSetting.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.About.getValue())
	  {

	  }
	  edtNewItem.setText("");
	  edtNewItem.clearFocus();
   }

   void SetDefault(String sName)
   {
	  ModProfile modProfile=new ModProfile();
	  modProfile.setUserId(session.getUserId());
	  modProfile.setModifyTime(StringHelper.FormatDateTime(new Date()));
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
		 
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
		 
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
        
	  }
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		daoProfile=new DaoProfile(TmpSettingItem.this);
        daoProfile.UpdateProfile(modProfile, DicBusiness.TableName, DicBusiness.BusinessName, sName, DicBusiness.BusinessId);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.DataManage.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.OtherSetting.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.About.getValue())
	  {

	  }
	  finish();
   }

   void ShowEditMenu(View itemView)
   {
	  final View itemView2 = itemView;
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 // TODO Auto-generated method stub
		 DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener()
		 {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			   // TODO Auto-generated method stub
			   switch (which)
			   {
				  case 0:// 编辑
					 TextView txtName1 = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
					 String sName=txtName1.getText().toString();
					 if(sName.equals(getString(R.string.d_none)))
					 {
						dialogs.ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
					    break;
					 }
                     Intent intent=new Intent();
                     intent.setClass(TmpSettingItem.this, TmpSettingItemEdit.class);
                     intent.putExtra("name", sName);
                     intent.putExtra(Enums.ItemTypeValue, Enums.ItemType.Business.getValue());
                     startActivity(intent);
					 break;
					 
				  case 1:// 删除
					 TextView txtName = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
					 if(txtName.getText().toString().equals(getString(R.string.d_none)))
					 {
						dialogs.ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
					    break;
					 }	
					 ModBusiness modBusiness = new ModBusiness();
					 modBusiness.setBusinessName(txtName.getText().toString());
					 modBusiness.setModifyTime(StringHelper.FormatDateTime(new Date()));
					 daoBusiness.DeleteBusiness(modBusiness);
					 LoadSetting();
					 break;

				  case 2://设为默认
					 
					 break;
			   }
			}
		 };
		 dialogs.ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete),getString(R.string.c_setDefault) }, onClickListener);
	  }
	  else if (SETTING_TYPE == Enums.ItemType.DataManage.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.OtherSetting.getValue())
	  {

	  }
	  else if (SETTING_TYPE == Enums.ItemType.About.getValue())
	  {

	  }
   }

   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_tmp_setting_item);
	  SETTING_TYPE = getIntent().getIntExtra(Enums.ItemTypeValue, 0);
	  dialogs = new Dialogs(TmpSettingItem.this);
	  session = (Session) getApplicationContext();

	  InitControls();
	  InitControlsListener();
	  LoadSetting();
   }

   @Override
   protected void onResume()
   {
	  // TODO Auto-generated method stub
	  super.onResume();
	  LoadSetting();
   }
   
   /**
    * 加载所有商家
    * 
    * @return
    */
   public List<Map<String, Object>> getAllBusiness()
   {
	  daoBusiness = new DaoBusiness(TmpSettingItem.this);
	  Session session = (Session) getApplicationContext();
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : daoBusiness.GetAllBusinessByUserId(session.getUserId()))
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", bName);
		 list.add(map);
	  }

	  return list;
   }

   /**
    * 加载所有项目
    * 
    * @return
    */
   public List<Map<String, Object>> getAllProject()
   {
	  daoBusiness = new DaoBusiness(TmpSettingItem.this);
	  Session session = (Session) getApplicationContext();
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : daoBusiness.GetAllBusinessByUserId(session.getUserId()))
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", bName);
		 list.add(map);
	  }

	  return list;
   }

   /**
    * 加载所有账户
    * 
    * @return
    */
   public List<Map<String, Object>> getAllAccount()
   {
	  daoBusiness = new DaoBusiness(TmpSettingItem.this);
	  Session session = (Session) getApplicationContext();
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : daoBusiness.GetAllBusinessByUserId(session.getUserId()))
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", bName);
		 list.add(map);
	  }

	  return list;
   }

   /**
    * 加载所有收入类型
    * 
    * @return
    */
   public List<Map<String, Object>> getAllIncomingCatagory()
   {
	  daoBusiness = new DaoBusiness(TmpSettingItem.this);
	  Session session = (Session) getApplicationContext();
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : daoBusiness.GetAllBusinessByUserId(session.getUserId()))
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", bName);
		 list.add(map);
	  }

	  return list;
   }

   /**
    * 加载所有支出类型
    * 
    * @return
    */
   public List<Map<String, Object>> getAllPayoutCatagory()
   {
	  daoBusiness = new DaoBusiness(TmpSettingItem.this);
	  Session session = (Session) getApplicationContext();
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : daoBusiness.GetAllBusinessByUserId(session.getUserId()))
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", bName);
		 list.add(map);
	  }

	  return list;
   }
}
