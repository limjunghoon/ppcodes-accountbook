package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.common.Enums.ItemType;
import ppcodes.accountbook.dao.DaoProfile;
import ppcodes.accountbook.entity.model.ModProfile;
import ppcodes.android.common.Dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ActSetting extends Activity
{
   private TextView itemTextView;
   private ListView settingItemListVew;
   private LayoutInflater mInflater;
   private TextView txtUserName;
   
   Dialogs dialogs;
   Session session;
   DaoProfile daoProfile;
   
   void InitControls()
   {
	  settingItemListVew = (ListView) findViewById(R.id.settingItemListVew);
	  txtUserName=(TextView)findViewById(R.id.txtUserName_Act_setting);
	  txtUserName.setText(session.getUserName());
   }

   void IniteControlsListener()
   {
	  settingItemListVew.setOnItemClickListener(new AdapterView.OnItemClickListener()
	    {
		  @Override
		  public void onItemClick(AdapterView<?> parentView,View itemView, int position, long index)
		    {
			  // TODO Auto-generated method stub
			  Intent intent = new Intent();
			  if (position == Enums.ItemType.Incoming.getValue())
			  {
				intent.setClass(ActSetting.this, TmpSettingItem.class);
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Incoming.getChineseName());
				intent.putExtra(Enums.ItemTypeValue, position);
				intent.putExtra("IsParent", true);
				startActivityForResult(intent, position);
			  }
			  else if (position == Enums.ItemType.Payout.getValue())
			  {
				intent.setClass(ActSetting.this, TmpSettingItem.class);
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Payout.getChineseName());
				intent.putExtra(Enums.ItemTypeValue, position);
				intent.putExtra("IsParent", true);
				startActivityForResult(intent, position);
			  }
			  else if (position == Enums.ItemType.Project.getValue())
			  {
				intent.setClass(ActSetting.this, TmpSettingItem.class);
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Project.getChineseName());
				intent.putExtra(Enums.ItemTypeValue, position);
				startActivityForResult(intent, position);
			  }
			  else if (position == Enums.ItemType.Account.getValue())
			  {
				intent.setClass(ActSetting.this, TmpSettingItem.class);
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Account.getChineseName());
				intent.putExtra(Enums.ItemTypeValue, position);
				startActivityForResult(intent, position);
			  }
			  else if (position == Enums.ItemType.Business.getValue())
			  {
				intent.setClass(ActSetting.this, TmpSettingItem.class);
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Business.getChineseName());
				intent.putExtra(Enums.ItemTypeValue, position);
				startActivityForResult(intent, position);
			  }
			  else if (position == Enums.ItemType.DataManage.getValue())
			  {
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.DataManage.getChineseName());

			  }
			  else if (position == Enums.ItemType.OtherSetting.getValue())
			  {
				intent.putExtra(Enums.ItemTypeName, Enums.ItemType.OtherSetting.getChineseName());

		      }
			  else if (position == Enums.ItemType.About.getValue())
			  {
				intent.putExtra(Enums.ItemTypeName, "关于");
				intent.setClass(ActSetting.this, ActAbout.class);
				startActivity(intent);
			  }
		   }
		});
   }

   void BindData()
   {
	  SimpleAdapter sAdapter = new SimpleAdapter(this, getData(),
			R.layout.listview_setting_item, 
			new String[] { "img", "name","default" }, 
		    new int[] { R.id.img_listview_setting_item,R.id.txtItemtype_listview_setting_item,R.id.txtDefault_listview_setting_item});
	  settingItemListVew.setAdapter(sAdapter);
   }
   


   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_setting);

	  dialogs = new Dialogs(ActSetting.this);
	  session=(Session)getApplicationContext();
	  InitControls();
	  IniteControlsListener();
	  BindData();
   }

   
   
   @Override
   protected void onResume()
   {
	  // TODO Auto-generated method stub
	  super.onResume();
	  BindData();
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
   {
	  // TODO Auto-generated method stub

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

   /**
    * 绑定列表选项
    * 
    * @return
    */
   public List<Map<String, Object>> getData()
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  daoProfile=new DaoProfile(ActSetting.this);
	  
	  List<String> sList=daoProfile.GetProfileNameByUserId(session.getUserId());
	  
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_category_income_icon);
	  map.put("name",getString(R.string.d_incoming));
	  map.put("default", sList.get(0));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_category_payout_icon);
	  map.put("name", getString(R.string.d_payout));
	  map.put("default", sList.get(1));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_project_icon);
	  map.put("name",getString(R.string.d_project));
	  map.put("default", sList.get(2));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_account_icon);
	  map.put("name", getString(R.string.d_account));
	  map.put("default", sList.get(3));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_corp_icon);
	  map.put("name", getString(R.string.d_business));
	  map.put("default", sList.get(4));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_icon_data_manage);
	  map.put("name", getString(R.string.d_dataManage));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_icon_othersetting);
	  map.put("name", getString(R.string.d_otherSetting));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("img", R.drawable.setting_aotu_upgrade_icon);
	  map.put("name", getString(R.string.d_about));
	  list.add(map);

	  return list;
   }
}
