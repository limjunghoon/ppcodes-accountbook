package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.PrivateCredentialPermission;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoAccount;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.dao.DaoCategory;
import ppcodes.accountbook.dao.DaoProfile;
import ppcodes.accountbook.dao.DaoProject;
import ppcodes.accountbook.entity.model.ModGvTab;
import ppcodes.accountbook.entity.model.ModProfileForAdd;
import ppcodes.android.common.StringHelper;
import ppcodes.android.common.gvTabAdapter;
import android.R.integer;
import android.app.Activity;
import android.app.ActivityGroup;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ActNewRecording extends ActivityGroup
{
   /** Called when the activity is first created. */
   
   //控件
   GridView gvTab;
   gvTabAdapter tabAdapter;
   FrameLayout container;
   ListView listView;

   //属性
   Session _session;
   Session getSession()
   {
	  if (_session == null)
		 _session = (Session) getApplicationContext();
	  return _session;
   }

   DaoProfile _daoProfile;
   DaoProfile getDaoProfile()
   {
	  if (_daoProfile == null)
		 _daoProfile = new DaoProfile(this);
	  return _daoProfile;
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
   

   //字段
   int CURRENT_TAB;
   
   void InitControls()
   {
	  listView = (ListView) findViewById(R.id.listview_Act_newrecord);
	  gvTab = (GridView) findViewById(R.id.gvTab_Act_newrecord);
   }

   void InitControlsListener()
   {
	  listView.setOnItemClickListener(new OnItemClickListener()
	  {  @Override
		 public void onItemClick(AdapterView<?> parentView, View currentView, int position, long arg3)
		 {
			// TODO Auto-generated method stub
		   switch (position)
		   {
			case 0://父类
			   getDaoCategory().GetAllParentCategorys(getSession().getUserId(), CURRENT_TAB==0?Enums.InOrOut.Incoming.getValue():Enums.InOrOut.Payout.getValue());
			   break;
			case 1://子类
			   
			   break;
			case 2://项目
			   
			   break;
			case 3://账户
			   
			   break;
			case 4:
			   if(CURRENT_TAB==0)//收入的商家
			   {
				  
			   }
			   else if(CURRENT_TAB==1)//支出的日期
			   {
				  
			   }
			   break;
			case 5:
			   if(CURRENT_TAB==0)//收入的日期
			   {
				  
			   }
			   else if(CURRENT_TAB==1)//支出的备注
			   {
				  
			   }
			   break;
			case 6://收入的备注
			   
			   break;
		   }
		 }
	  });
   }

   void InitTab()
   {
	  try
	  {
		 List<ModGvTab> list = GetTabItem();
		 gvTab.setNumColumns(list.size());// 设置每行列数
		 gvTab.setSelector(new ColorDrawable(Color.TRANSPARENT));// 选中的时候为透明色
		 int width = this.getWindowManager().getDefaultDisplay().getWidth() / list.size();
		 tabAdapter = new gvTabAdapter(this, list, width, 40);
		 gvTab.setAdapter(tabAdapter);
		 gvTab.setGravity(Gravity.CENTER);// 位置居中
		 gvTab.setVerticalSpacing(0);// 垂直间隔
		 gvTab.setOnItemClickListener(new OnItemClickListener()
		 {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
			{
			   // TODO Auto-generated method stub
			   SwitchActivity(position);
			}
		 });
		 container = (FrameLayout) findViewById(R.id.container_Act_newrecord);
		 SwitchActivity(0);
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
	  }
   }
   /**
    * 绑定Tab选项
    * @return
    */
   private List<ModGvTab> GetTabItem()
   {
	  List<ModGvTab> list = new ArrayList<ModGvTab>();
	  list.add(new ModGvTab(R.drawable.icon_incoming2, "新增收入"));
	  list.add(new ModGvTab(R.drawable.icon_payout, "新增支出"));
	  return list;
   }

   /**
    * 绑定种类等选项，有一个隐藏的textview存Id
    * @param index
    * @return
    */
   private List<Map<String, Object>> GetLisViewItem(int index)
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = new HashMap<String, Object>();
	  ModProfileForAdd modProfileForAdd = null;
	  switch (index)
	  {
		 case 0:
			modProfileForAdd = getDaoProfile().GetProfileNameByUserId2(getSession().getUserId(), Enums.InOrOut.Incoming.getValue());
			break;
		 case 1:
			modProfileForAdd = getDaoProfile().GetProfileNameByUserId2(getSession().getUserId(), Enums.InOrOut.Payout.getValue());
			break;
	  }

	  map.put("name", getResources().getString(R.string.d_category));
	  map.put("item", modProfileForAdd.getParentCategoryName());
	  map.put("id",modProfileForAdd.getParentCategoryId());
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_categorychild));
	  map.put("item", modProfileForAdd.getCategoryName());
	  map.put("id",modProfileForAdd.getCategoryId());
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_project));
	  map.put("item", modProfileForAdd.getProjectName());
	  map.put("id",modProfileForAdd.getProjectId());
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_account));
	  map.put("item", modProfileForAdd.getAccountName());
	  map.put("id",modProfileForAdd.getAccountId());
	  list.add(map);

	  if (index == 0)
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", getResources().getString(R.string.d_business));
		  map.put("item", modProfileForAdd.getBusinessName());
		  map.put("id",modProfileForAdd.getBusinessId());
		 list.add(map);
	  }

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_date));
	  map.put("item", StringHelper.FormatDate(new Date()));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_comment));
	  map.put("item", "");
	  list.add(map);

	  return list;
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_newrecord);
	  try
	  {
		 InitControls();
		 InitTab();
		 InitControlsListener();
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 Log.e("ERROR", "onCreate" + e.getStackTrace() + e.getMessage());
	  }
   }

   /**
    * 根据ID打开指定的Activity
    * 
    * @param id
    *           GridView选中项的序号
    */
   void SwitchActivity(int id)
   {
	  CURRENT_TAB=id;
	  tabAdapter.SetFocus(id);// 选中项获得高亮
	  SimpleAdapter sAdapter = new SimpleAdapter(this, GetLisViewItem(id), 
			R.layout.listview_newrecord, 
			new String[] { "name", "item","id" }, 
			new int[] { R.id.txtTypeName_listview_newrecord,R.id.txtName_listview_newrecord,R.id.txtId_listview_newrecord });
	  listView.setAdapter(sAdapter);

	  // container.removeAllViews();// 必须先清除容器中所有的View
	  // Intent intent = null;
	  // if (id == 0 || id == 2)
	  // {
	  // intent = new Intent(ActivityGroupDemo.this, ActivityA.class);
	  // }
	  // else if (id == 1 || id == 3)
	  // {
	  // intent = new Intent(ActivityGroupDemo.this, ActivityB.class);
	  // }
	  // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  // // Activity 转为 View
	  // Window subActivity =
	  // getLocalActivityManager().startActivity("subActivity", intent);
	  // // 容器添加View
	  // container.addView(subActivity.getDecorView(), LayoutParams.FILL_PARENT,
	  // LayoutParams.FILL_PARENT);
   }

   // 按下返回键时，回收所有资源
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
   {
	  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	  { // 按下的如果是BACK，同时没有重复
		 finish();
	  }
	  return false;
   }
}
