package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Calendar;
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
import ppcodes.accountbook.dao.DaoInOutDetails;
import ppcodes.accountbook.dao.DaoInitDataBase;
import ppcodes.accountbook.dao.DaoProfile;
import ppcodes.accountbook.dao.DaoProject;
import ppcodes.accountbook.entity.model.ModAccount;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.accountbook.entity.model.ModCategory;
import ppcodes.accountbook.entity.model.ModGvTab;
import ppcodes.accountbook.entity.model.ModInOutDetails;
import ppcodes.accountbook.entity.model.ModProfileForAdd;
import ppcodes.accountbook.entity.model.ModProject;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.DateHelper;
import ppcodes.android.common.gvTabAdapter;
import android.app.ActivityGroup;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
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
   Button btnOK;
   Button btnCancel;
   EditText edtMoney;

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

   DaoInOutDetails _daoInOutDetails;
   DaoInOutDetails getDaoInOutDetails()
   {
	  if (_daoInOutDetails == null)
	  {
		 _daoInOutDetails = new DaoInOutDetails(this);
	  }
	  return _daoInOutDetails;
   }

   // 字段
   int currentTab;
   int INCOMING_TAB = 0;
   int PAYOUT_TAB = 1;
   String parentName;
   String newItemName;
   Map<Integer, Object> listCache = new HashMap<Integer, Object>();

   void InitControls()
   {
	  listView = (ListView) findViewById(R.id.listview_Act_newrecord);
	  gvTab = (GridView) findViewById(R.id.gvTab_Act_newrecord);
	  btnOK = (Button) findViewById(R.id.btnOK_Act_newrecord);
	  btnCancel = (Button) findViewById(R.id.btnCancel_Act_newrecord);
	  edtMoney=(EditText) findViewById(R.id.edtMoney_Act_newrecord);
   }

   void InitControlsListener()
   {
	  listView.setOnItemClickListener(new OnItemClickListener()
	  {
		 @Override
		 public void onItemClick(AdapterView<?> parentView, View currentView, int position, long arg3)
		 {
			// TODO Auto-generated method stub
			try
			{
			   ListViewClick(parentView, currentView, position);
			}
			catch (Exception e)
			{
			   // TODO: handle exception
			   getDialogs().ShowOKAlertDialog("ERROR", "ListViewClick-"+e.getMessage());
			}
		 }
	  });

	  btnOK.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			try
			{
			   SimpleAdapter sa = (SimpleAdapter) listView.getAdapter();
			   Map<String, Object> map = (HashMap<String, Object>) sa.getItem(1);
			   if (edtMoney.getText() == null||edtMoney.getText().toString().equals(""))
			   {
				  getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "金额"+getString(R.string.alert_notBeEmpty));
			   }
			   else if (map.get("item") == null || map.get("item").toString().equals(""))
			   {
				  getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "子类"+getString(R.string.alert_notBeEmpty));
			   }
			   else
			   {
				  ModInOutDetails modInOutDetails = new ModInOutDetails();
				  modInOutDetails.setAmount(Float.valueOf(edtMoney.getText().toString()));
				  modInOutDetails.setUserId(getSession().getUserId());
				  modInOutDetails.setCreateTime(DateHelper.ToDateTime(new Date()));
				  modInOutDetails.setModifyTime(DateHelper.ToDateTime(new Date()));
				  modInOutDetails.setDisabled(0);
				  modInOutDetails.setCategoryId(Integer.parseInt((((HashMap<String, Object>) sa.getItem(0)).get("id")).toString()));
				  modInOutDetails.setCategoryChildId(Integer.parseInt((((HashMap<String, Object>) sa.getItem(1)).get("id")).toString()));
				  modInOutDetails.setProjectId(Integer.parseInt((((HashMap<String, Object>) sa.getItem(2)).get("id")).toString()));
				  modInOutDetails.setAccountId(Integer.parseInt((((HashMap<String, Object>) sa.getItem(3)).get("id")).toString()));
				  if (currentTab == INCOMING_TAB)
				  {
					 modInOutDetails.setInOrOut(Enums.InOrOut.Incoming.getValue());
					 modInOutDetails.setBusinessId(1);
					 modInOutDetails.setDate(DateHelper.DelDateSplit(((HashMap<String, Object>) sa.getItem(4)).get("item").toString()));
					 modInOutDetails.setRemarks(((HashMap<String, Object>) sa.getItem(5)).get("item").toString());
				  }
				  else if (currentTab == PAYOUT_TAB)
				  {
					 modInOutDetails.setInOrOut(Enums.InOrOut.Payout.getValue());
					 modInOutDetails.setBusinessId(Integer.parseInt((((HashMap<String, Object>) sa.getItem(4)).get("id")).toString()));
					 modInOutDetails.setDate(DateHelper.DelDateSplit(((HashMap<String, Object>) sa.getItem(5)).get("item").toString()));
					 modInOutDetails.setRemarks(((HashMap<String,Object>) sa.getItem(6)).get("item").toString());
				  }
				  getDaoInOutDetails().InsertInOutDetails(modInOutDetails);
				  setResult(RESULT_OK);
				  finish();
			   }
			}                                        
			catch (Exception e)
			{
			   // TODO: handle exception
			   getDialogs().ShowOKAlertDialog("ERROR", "btnOK.setOnClickListener-"+e.getMessage());
			   e.printStackTrace();
			}
		 }
	  });

	  btnCancel.setOnClickListener(new View.OnClickListener()
	  {
		 @Override
		 public void onClick(View v)
		 {
			// TODO Auto-generated method stub
			finish();
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
			   if (position != currentTab)
			   {
				  SwitchActivity(position);
			   }
			}
		 });
		 // container = (FrameLayout)
		 // findViewById(R.id.container_Act_newrecord);
		 SwitchActivity(INCOMING_TAB);
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
	  }
   }

   /**
    * 最外层列表的单击事件方法
    * @param parentView
    * @param currentView
    * @param position
    */
   void ListViewClick(AdapterView<?> parentView, View currentView, int position)
   {
	  final View currentView2 = currentView;

	  switch (position)
	  {
		 case 0:// 父类======================================
			List<ModCategory> list = getDaoCategory().GetAllParentCategorysAndId(getSession().getUserId(),
				  currentTab == INCOMING_TAB ? Enums.InOrOut.Incoming.getValue() : Enums.InOrOut.Payout.getValue());

			SimpleAdapter sAdapter = new SimpleAdapter(ActNewRecording.this, getData(Enums.AddRecord.Category.getValue(), list), R.layout.listview_text_id, new String[] { "name", "id" }, new int[] {
				  R.id.txtName_listview_text_id, R.id.txtId_listview_text_id });
			ListView listView1 = new ListView(ActNewRecording.this);
			listView1.setBackgroundResource(android.R.color.white);
			listView1.setAdapter(sAdapter);
			listView1.setOnItemClickListener(getOnItemClickListener(parentView, currentView, position));
			getDialogs().ShowCustomViewDialog(null, listView1, 80, 70);
			break;

		 case 1:// 子类========================================
			List<ModCategory> list2 = getDaoCategory().GetAllChildrenCategorysAndId(getSession().getUserId(),
				  currentTab == INCOMING_TAB ? Enums.InOrOut.Incoming.getValue() : Enums.InOrOut.Payout.getValue(), parentName);
			SimpleAdapter sAdapter2 = new SimpleAdapter(ActNewRecording.this, getData(Enums.AddRecord.ChildCategory.getValue(), list2), R.layout.listview_text_id, new String[] { "name", "id" },
				  new int[] { R.id.txtName_listview_text_id, R.id.txtId_listview_text_id });
			ListView listView2 = new ListView(ActNewRecording.this);
			listView2.setBackgroundResource(android.R.color.white);
			listView2.setAdapter(sAdapter2);
			listView2.setOnItemClickListener(getOnItemClickListener(parentView, currentView, position));
			getDialogs().ShowCustomViewDialog(null, listView2, 80, 70);
			break;

		 case 2:// 项目===========================================
			List<ModProject> list3 = getDaoProject().GetAllProjectByUserIdForAdd(getSession().getUserId());
			SimpleAdapter sAdapter3 = new SimpleAdapter(ActNewRecording.this, getData(Enums.AddRecord.Project.getValue(), list3), R.layout.listview_text_id, new String[] { "name", "id" }, new int[] {
				  R.id.txtName_listview_text_id, R.id.txtId_listview_text_id });
			ListView listView3 = new ListView(ActNewRecording.this);
			listView3.setBackgroundResource(android.R.color.white);
			listView3.setAdapter(sAdapter3);
			listView3.setOnItemClickListener(getOnItemClickListener(parentView, currentView, position));
			getDialogs().ShowCustomViewDialog(null, listView3, 80, 70);
			break;

		 case 3:// 账户=============================================
			List<ModAccount> list4 = getDaoAccount().GetAllAccountByUserIdForAdd(getSession().getUserId());
			SimpleAdapter sAdapter4 = new SimpleAdapter(ActNewRecording.this, getData(Enums.AddRecord.Account.getValue(), list4), R.layout.listview_text_id, new String[] { "name", "id" }, new int[] {
				  R.id.txtName_listview_text_id, R.id.txtId_listview_text_id });
			ListView listView4 = new ListView(ActNewRecording.this);
			listView4.setBackgroundResource(android.R.color.white);
			listView4.setAdapter(sAdapter4);
			listView4.setOnItemClickListener(getOnItemClickListener(parentView, currentView, position));
			getDialogs().ShowCustomViewDialog(null, listView4, 80, 70);
			break;

		 case 4:
			if (currentTab == PAYOUT_TAB)// 支出的商家====================
			{
			   List<ModBusiness> list5 = getDaoBusiness().GetAllBusinessByUserIdForAdd(getSession().getUserId());
			   SimpleAdapter sAdapter5 = new SimpleAdapter(ActNewRecording.this, getData(Enums.AddRecord.Business.getValue(), list5), R.layout.listview_text_id, new String[] { "name", "id" },
					 new int[] { R.id.txtName_listview_text_id, R.id.txtId_listview_text_id });
			   ListView listView5 = new ListView(ActNewRecording.this);
			   listView5.setBackgroundResource(android.R.color.white);
			   listView5.setAdapter(sAdapter5);
			   listView5.setOnItemClickListener(getOnItemClickListener(parentView, currentView, position));
			   getDialogs().ShowCustomViewDialog(null, listView5, 80, 70);
			   break;
			}
			else if (currentTab == INCOMING_TAB)// 收入的日期==================
			{
			   final Calendar cd = Calendar.getInstance();
			   cd.setTime(new Date());
			   new DatePickerDialog(ActNewRecording.this, new DatePickerDialog.OnDateSetListener()
			   {
				  @Override
				  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
				  {
					 // TODO Auto-generated method stub
					 TextView txtDate = (TextView) currentView2.findViewById(R.id.txtName_listview_newrecord);
					 txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
				  }
			   }, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
			}
			break;

		 case 5:
			if (currentTab == PAYOUT_TAB)// 支出的日期====================
			{
			   final Calendar cd = Calendar.getInstance();
			   cd.setTime(new Date());
			   new DatePickerDialog(ActNewRecording.this, new DatePickerDialog.OnDateSetListener()
			   {
				  @Override
				  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
				  {
					 // TODO Auto-generated method stub
					 TextView txtDate = (TextView) currentView2.findViewById(R.id.txtName_listview_newrecord);
					 txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
				  }
			   }, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
			}
			else if (currentTab == INCOMING_TAB)// 收入的备注=================
			{
			   final EditText edtComment = new EditText(ActNewRecording.this);
			   edtComment.setSingleLine(false);
			   final SimpleAdapter sa = (SimpleAdapter) listView.getAdapter();
			   final HashMap<String, Object> map = (HashMap<String, Object>) sa.getItem(position);

			   DialogInterface.OnClickListener clickListener = new OnClickListener()
			   {
				  @Override
				  public void onClick(DialogInterface dialog, int which)
				  {
					 // TODO Auto-generated method stub
					 map.put("item", edtComment.getText().toString().trim());
					 sa.notifyDataSetChanged();
					 dialog.dismiss();
				  }
			   };
			   getDialogs().ShowCustomViewDialogWithOKCancel("请输入备注，回车换行", edtComment, 90, 90, clickListener);

			}
			break;

		 case 6:// 支出的备注
			final EditText edtComment = new EditText(ActNewRecording.this);
			edtComment.setSingleLine(false);
			final SimpleAdapter sa = (SimpleAdapter) listView.getAdapter();
			final HashMap<String, Object> map = (HashMap<String, Object>) sa.getItem(position);

			DialogInterface.OnClickListener clickListener = new OnClickListener()
			{
			   @Override
			   public void onClick(DialogInterface dialog, int which)
			   {
				  // TODO Auto-generated method stub
				  map.put("item", edtComment.getText().toString().trim());
				  sa.notifyDataSetChanged();
				  dialog.dismiss();
			   }
			};
			getDialogs().ShowCustomViewDialogWithOKCancel("请输入备注，回车换行", edtComment, 90, 90, clickListener);
			break;
	  }
   }

   /**
    * 单击ListView选项后，在dialog中获取该选项的列表
    * 
    * @param typeId
    * @param dataList
    * @return
    */
   @SuppressWarnings("unchecked")
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

   /**
    * Dialog列表数据的单击事件
    * @param OutParentView
    * @param OutView
    * @param OutPosition
    * @return
    */
   // ListView的单击事件
   OnItemClickListener getOnItemClickListener(final AdapterView<?> OutParentView, final View OutView, final int OutPosition)
   {
	  OnItemClickListener ListItemClickListener = new OnItemClickListener()
	  {
		 @Override
		 public void onItemClick(AdapterView<?> parentView, View selectView, int position, long arg3)
		 {
			// TODO Auto-generated method stub
			TextView txtName = (TextView) selectView.findViewById(R.id.txtName_listview_text_id);
			TextView txtId = (TextView) selectView.findViewById(R.id.txtId_listview_text_id);

			SimpleAdapter sa = (SimpleAdapter) listView.getAdapter();
			Map<String, Object> map = (HashMap<String, Object>) sa.getItem(OutPosition);
			map.put("item", txtName.getText().toString());
			map.put("id", txtId.getText().toString());

			if (OutPosition == 0)
			{
			   parentName = txtName.getText().toString();
			   Map<String, Object> map2 = (HashMap<String, Object>) sa.getItem(1);
			   map2.put("item", "");
			   map2.put("id", "");
			}
			sa.notifyDataSetChanged();

			getDialogs().dismissAlertDialog();
		 }
	  };
	  return ListItemClickListener;
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
    * 绑定最外层列表项，有一个隐藏的textview存Id
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

	  parentName = modProfileForAdd.getParentCategoryName();

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

	  if (index == 1)
	  {
		 map = new HashMap<String, Object>();
		 map.put("name", getResources().getString(R.string.d_business));
		 map.put("item", modProfileForAdd.getBusinessName());
		 map.put("id", modProfileForAdd.getBusinessId());
		 list.add(map);
	  }

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_date));
	  map.put("item", DateHelper.ToDate(new Date()));
	  list.add(map);

	  map = new HashMap<String, Object>();
	  map.put("name", getResources().getString(R.string.d_comment));
	  map.put("item", "");
	  list.add(map);

	  return list;
   }

   /**
    * 根据ID打开指定的Activity,并保存之前的界面数据
    * @param id
    *           GridView选中项的序号
    */
   void SwitchActivity(int id)
   {
	  final ListAdapter currentAdapter = listView.getAdapter();
	  if (id == INCOMING_TAB)
	  {
		 if (listCache.get(INCOMING_TAB) == null)
		 {
			SimpleAdapter sAdapter = new SimpleAdapter(this, GetLisViewItem(id), R.layout.listview_newrecord, new String[] { "name", "item", "id" }, new int[] { R.id.txtTypeName_listview_newrecord,
				  R.id.txtName_listview_newrecord, R.id.txtId_listview_newrecord });
			listCache.put(INCOMING_TAB, sAdapter);
		 }
		 else
		 {
			listCache.remove(PAYOUT_TAB);
			listCache.put(PAYOUT_TAB, currentAdapter);
			
		 }
		 listView.setAdapter((SimpleAdapter) listCache.get(INCOMING_TAB));
      }
	  else if (id == PAYOUT_TAB)
	  {
		 if (listCache.get(PAYOUT_TAB) == null)
		 {
			SimpleAdapter sAdapter = new SimpleAdapter(this, GetLisViewItem(id), R.layout.listview_newrecord, new String[] { "name", "item", "id" }, new int[] { R.id.txtTypeName_listview_newrecord,
				  R.id.txtName_listview_newrecord, R.id.txtId_listview_newrecord });
			listCache.put(PAYOUT_TAB, sAdapter);
			listCache.remove(INCOMING_TAB);
			listCache.put(INCOMING_TAB, currentAdapter);
		 }
		 else
		 {
			listCache.remove(INCOMING_TAB);
			listCache.put(INCOMING_TAB, currentAdapter);
			
		 }
		 listView.setAdapter((SimpleAdapter) listCache.get(PAYOUT_TAB));
	  }		 
	  parentName=((HashMap<String, Object>)listView.getAdapter().getItem(0)).get("item").toString();
	  currentTab = id;
	  tabAdapter.SetFocus(id);// 选中项获得高亮
   }

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
    * 按下返回键时，回收所有资源
    */
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
   {
	  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	  { // 按下的如果是BACK，同时没有重复
		 setResult(RESULT_CANCELED);
		 finish();
	  }
	  return false;
   }
}
