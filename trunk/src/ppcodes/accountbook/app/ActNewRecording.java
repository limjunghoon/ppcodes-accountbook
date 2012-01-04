package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoAccount;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.dao.DaoCategory;
import ppcodes.accountbook.dao.DaoProfile;
import ppcodes.accountbook.dao.DaoProject;
import ppcodes.accountbook.entity.model.ModAccount;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.accountbook.entity.model.ModCategory;
import ppcodes.accountbook.entity.model.ModGvTab;
import ppcodes.accountbook.entity.model.ModProfileForAdd;
import ppcodes.accountbook.entity.model.ModProject;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.StringHelper;
import ppcodes.android.common.gvTabAdapter;
import android.R.integer;
import android.app.ActivityGroup;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ActNewRecording extends ActivityGroup
{
   /** Called when the activity is first created. */

   // 控件
   GridView gvTab;
   gvTabAdapter tabAdapter;
   FrameLayout container;
   ListView listView;

   // 属性
   Session _session;
   Session getSession()
   {
	  if (_session == null)
		 _session = (Session) getApplicationContext();
	  return _session;
   }

   Dialogs _dialogs;
   Dialogs getDialogs()
   {
	  if (_dialogs == null)
		 _dialogs = new Dialogs(this);
	  return _dialogs;
   }

   DaoProfile _daoProfile;
   DaoProfile getDaoProfile()
   {
	  if (_daoProfile == null)
		 _daoProfile = new DaoProfile(this);
	  return _daoProfile;
   }

   DaoBusiness _daoBusiness;
   DaoBusiness getDaoBusiness()
   {
	  if (_daoBusiness == null)
	  {
		 _daoBusiness = new DaoBusiness(this);
	  }
	  return _daoBusiness;
   }

   DaoProject _daoProject;
   DaoProject getDaoProject()
   {
	  if (_daoProject == null)
	  {
		 _daoProject = new DaoProject(this);
	  }
	  return _daoProject;
   }

   DaoAccount _daoAccount;
   DaoAccount getDaoAccount()
   {
	  if (_daoAccount == null)
	  {
		 _daoAccount = new DaoAccount(this);
	  }
	  return _daoAccount;
   }

   DaoCategory _daoCategory;
   DaoCategory getDaoCategory()
   {
	  if (_daoCategory == null)
	  {
		 _daoCategory = new DaoCategory(this);
	  }
	  return _daoCategory;
   }

   // 字段
   int CURRENT_TAB;
   String PARENT_NAME;

   void InitControls()
   {
	  listView = (ListView) findViewById(R.id.listview_Act_newrecord);
	  gvTab = (GridView) findViewById(R.id.gvTab_Act_newrecord);
   }

   List<Map<String, Object>> getData(int typeId, Object dataList)
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  if (typeId == Enums.AddRecord.Category.getValue())
	  {
		 for (ModCategory modCategory : (List<ModCategory>) dataList)
		 {
		   map = new HashMap<String, Object>();
           map.put("name", modCategory.getCategoryName());
           map.put("id", modCategory.getCategoryId());
           list.add(map);
		 }
	  }
	  else if (typeId == Enums.AddRecord.ChildCategory.getValue())
	  {
		 for (ModCategory modCategory : (List<ModCategory>) dataList)
		 {
		   map = new HashMap<String, Object>();
           map.put("name", modCategory.getCategoryName());
           map.put("id", modCategory.getCategoryId());
           list.add(map);
		 }
	  }
	  else if (typeId == Enums.AddRecord.Account.getValue())
	  {
		 for (ModAccount modAccount : (List<ModAccount>) dataList)
		 {
		   map = new HashMap<String, Object>();	
           map.put("name", modAccount.getAccountName());
           map.put("id", modAccount.getAccountId());
           list.add(map);
		 }
	  }
	  else if (typeId == Enums.AddRecord.Project.getValue())
	  {
		 for (ModProject modProject : (List<ModProject>) dataList)
		 {
		   map = new HashMap<String, Object>();
           map.put("name", modProject.getProjectName());
           map.put("id", modProject.getProjectId());
           list.add(map);
		 }
	  }
	  else if (typeId == Enums.AddRecord.Business.getValue())
	  {
		 for (ModBusiness modBusiness : (List<ModBusiness>) dataList)
		 {
		   map = new HashMap<String, Object>();
           map.put("name", modBusiness.getBusinessName());
           map.put("id", modBusiness.getBusinessId());
           list.add(map);
		 }
	  }
	  return list;
   }

   void InitControlsListener()
   {
	  listView.setOnItemClickListener(new OnItemClickListener()
	  {
		 @Override
		 public void onItemClick(AdapterView<?> parentView, View currentView, int position, long arg3)
		 {
			// TODO Auto-generated method stub
			switch (position)
			{
			   case 0:// 父类
				  List<ModCategory> list = getDaoCategory()
						.GetAllParentCategorysAndId(getSession().getUserId(), CURRENT_TAB == 0 ? Enums.InOrOut.Incoming.getValue() : Enums.InOrOut.Payout.getValue());

				  SimpleAdapter sAdapter=new SimpleAdapter(ActNewRecording.this,
						getData(Enums.AddRecord.Category.getValue(), list), 
						R.layout.listview_text_id, 
						new String[]{"name","id"}, 
						new int[]{R.id.txtName_listview_text_id,R.id.txtId_listview_text_id});
				  ListView listView=new ListView(ActNewRecording.this);
				  listView.setBackgroundResource(android.R.color.white);
				  listView.setAdapter(sAdapter);
				  listView.setOnItemClickListener(getOnItemClickListener(parentView,currentView,position));
				  getDialogs().ShowCustomViewDialog(null,listView, 50, 50);
				  break;
			   
			   case 1:// 子类
				  List<ModCategory> list2 = getDaoCategory().GetAllChildrenCategorysAndId(getSession().getUserId(),
						CURRENT_TAB == 0 ? Enums.InOrOut.Incoming.getValue() : Enums.InOrOut.Payout.getValue(), PARENT_NAME);
				  SimpleAdapter sAdapter2=new SimpleAdapter(ActNewRecording.this,
						getData(Enums.AddRecord.ChildCategory.getValue(), list2), 
						R.layout.listview_text_id, 
						new String[]{"name","id"}, 
						new int[]{R.id.txtName_listview_text_id,R.id.txtId_listview_text_id});
				  ListView listView2=new ListView(ActNewRecording.this);
				  listView2.setBackgroundResource(android.R.color.white);
				  listView2.setAdapter(sAdapter2);
				  listView2.setOnItemClickListener(getOnItemClickListener(parentView,currentView,position));
				  getDialogs().ShowCustomViewDialog(null,listView2, 50, 50);
				  break;
				  
			   
			   case 2:// 项目
				  List<ModProject> list3 = getDaoProject().GetAllProjectByUserIdForAdd(getSession().getUserId());
				  SimpleAdapter sAdapter3=new SimpleAdapter(ActNewRecording.this,
						getData(Enums.AddRecord.Project.getValue(), list3), 
						R.layout.listview_text_id, 
						new String[]{"name","id"}, 
						new int[]{R.id.txtName_listview_text_id,R.id.txtId_listview_text_id});
				  ListView listView3=new ListView(ActNewRecording.this);
				  listView3.setBackgroundResource(android.R.color.white);
				  listView3.setAdapter(sAdapter3);
				  listView3.setOnItemClickListener(getOnItemClickListener(parentView,currentView,position));
				  getDialogs().ShowCustomViewDialog(null,listView3, 50, 50);
				  break;
				  
				  
			   case 3:// 账户
				  List<ModAccount> list4 = getDaoAccount().GetAllAccountByUserIdForAdd(getSession().getUserId());
				  SimpleAdapter sAdapter4=new SimpleAdapter(ActNewRecording.this,
						getData(Enums.AddRecord.Account.getValue(), list4), 
						R.layout.listview_text_id, 
						new String[]{"name","id"}, 
						new int[]{R.id.txtName_listview_text_id,R.id.txtId_listview_text_id});
				  ListView listView4=new ListView(ActNewRecording.this);
				  listView4.setBackgroundResource(android.R.color.white);
				  listView4.setAdapter(sAdapter4);
				  listView4.setOnItemClickListener(getOnItemClickListener(parentView,currentView,position));
				  getDialogs().ShowCustomViewDialog(null,listView4, 50, 50);
				  break;
				  
				  
			   case 4:
				  if (CURRENT_TAB == 0)// 收入的商家
				  {
					  List<ModBusiness> list5 = getDaoBusiness().GetAllBusinessByUserIdForAdd(getSession().getUserId());
					  SimpleAdapter sAdapter5=new SimpleAdapter(ActNewRecording.this,
							getData(Enums.AddRecord.Business.getValue(), list5), 
							R.layout.listview_text_id, 
							new String[]{"name","id"}, 
							new int[]{R.id.txtName_listview_text_id,R.id.txtId_listview_text_id});
					  ListView listView5=new ListView(ActNewRecording.this);
					  listView5.setBackgroundResource(android.R.color.white);
					  listView5.setAdapter(sAdapter5);
					  listView5.setOnItemClickListener(getOnItemClickListener(parentView,currentView,position));
					  getDialogs().ShowCustomViewDialog(null,listView5, 50, 50);
					  break;
				  }
				  else if (CURRENT_TAB == 1)// 支出的日期
				  {

				  }
				  break;
				  
				  
			   case 5:
				  if (CURRENT_TAB == 0)// 收入的日期
				  {

				  }
				  else if (CURRENT_TAB == 1)// 支出的备注
				  {

				  }
				  break;
			   case 6:// 收入的备注

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

   OnItemClickListener getOnItemClickListener(final AdapterView<?> OutParentView, final View OutView,final int OutPosition)
   {
     OnItemClickListener ListItemClickListener=new OnItemClickListener()
     { @Override
	   public void onItemClick(AdapterView<?> parentView, View selectView, int position, long arg3)
	   {
	     // TODO Auto-generated method stub
	     TextView txtName=(TextView)selectView.findViewById(R.id.txtName_listview_text_id);
	     TextView txtId=(TextView)selectView.findViewById(R.id.txtId_listview_text_id);
	     
	     TextView txtNameOut=(TextView)OutView.findViewById(R.id.txtName_listview_newrecord);
	     TextView txtIdOut=(TextView)OutView.findViewById(R.id.txtId_listview_newrecord);
	    
	     txtNameOut.setText(txtName.getText().toString());
	     txtIdOut.setText(txtId.getText().toString());
	     if(OutPosition==0)
	     {
		   PARENT_NAME=txtName.getText().toString();
	       View childView=OutParentView.getChildAt(1);
		   TextView txtNameChild=(TextView)childView.findViewById(R.id.txtName_listview_newrecord);
		   TextView txtIdChild=(TextView)childView.findViewById(R.id.txtId_listview_newrecord);
		   txtNameChild.setText("");
		   txtIdChild.setText("");
	     }
	     getDialogs().dismissAlertDialog();
	   }
     };
     return ListItemClickListener;
   }
   /**
    * 绑定Tab选项
    * 
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
    * 
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

	  PARENT_NAME=modProfileForAdd.getParentCategoryName();
	  
	  map.put("name", getResources().getString(R.string.d_category));
	  map.put("item", modProfileForAdd.getParentCategoryName());
	  map.put("id", modProfileForAdd.getParentCategoryId());
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_categorychild));
	  map.put("item", modProfileForAdd.getCategoryName());
	  map.put("id", modProfileForAdd.getCategoryId());
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_project));
	  map.put("item", modProfileForAdd.getProjectName());
	  map.put("id", modProfileForAdd.getProjectId());
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_account));
	  map.put("item", modProfileForAdd.getAccountName());
	  map.put("id", modProfileForAdd.getAccountId());
	  list.add(map);

	  if (index == 0)
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", getResources().getString(R.string.d_business));
		 map.put("item", modProfileForAdd.getBusinessName());
		 map.put("id", modProfileForAdd.getBusinessId());
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
	  CURRENT_TAB = id;
	  tabAdapter.SetFocus(id);// 选中项获得高亮
	  SimpleAdapter sAdapter = new SimpleAdapter(this, GetLisViewItem(id), R.layout.listview_newrecord, new String[] { "name", "item", "id" }, new int[] { R.id.txtTypeName_listview_newrecord,
			R.id.txtName_listview_newrecord, R.id.txtId_listview_newrecord });
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
