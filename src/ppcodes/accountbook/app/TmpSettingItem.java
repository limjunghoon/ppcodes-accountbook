package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppcodes.accountbook.common.DataHelper;
import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.common.Session;
import ppcodes.accountbook.dao.DaoAccount;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.dao.DaoCategory;
import ppcodes.accountbook.dao.DaoProfile;
import ppcodes.accountbook.dao.DaoProject;
import ppcodes.accountbook.entity.dictionary.DicAccount;
import ppcodes.accountbook.entity.dictionary.DicBusiness;
import ppcodes.accountbook.entity.dictionary.DicCategory;
import ppcodes.accountbook.entity.dictionary.DicProject;
import ppcodes.accountbook.entity.model.ModAccount;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.accountbook.entity.model.ModCategory;
import ppcodes.accountbook.entity.model.ModProfile;
import ppcodes.accountbook.entity.model.ModProject;
import ppcodes.android.common.Dialogs;
import ppcodes.android.common.StringHelper;
import ppcodes.android.common.gvImageAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TmpSettingItem extends Activity
{
   // 控件定义
   private TextView txtTitle;
   private ListView listView;
   private ImageButton imgNew;
   private EditText edtNewItem;
   private Button btnNewItem;
   
   //属性
   Dialogs _dialogs;
   private Dialogs getDialogs()
   {
	  if(_dialogs==null)
		 _dialogs=new Dialogs(this);
	  return _dialogs;
   }
   
   Session _session;
   private Session getSession()
   {
	  if(_session==null)
		 _session=(Session)getApplicationContext();
	  return _session;
   }
   
   DaoProfile _daoProfile;
   private DaoProfile getDaoProfile()
   {
	 if(_daoProfile==null)
	 {	
	   _daoProfile=new DaoProfile(this);
	 }
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
   
   // 字段
   int SETTING_TYPE;
   boolean IS_PARENT;
   String PARENT_NAME;
   int CATEGORY_TYPE=999;
   String imgName="icon_category_default";
  
   void InitControls()
   {
	  listView = (ListView) findViewById(R.id.listView_Act_Tmp_setting_item);
	  imgNew = (ImageButton) findViewById(R.id.btnNewImg_Act_Tmp_setting);
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

	  imgNew.setOnClickListener(new OnClickListener()
	  {
	     @Override
	     public void onClick(View v)
	     {
	  	  // TODO Auto-generated method stub
	      //显示一个图标的列表供选择
	    	LayoutInflater inflater = getLayoutInflater();
	    	View layout = (View)inflater.inflate(R.layout.dialog_image_gridview,(ViewGroup)findViewById(R.id.gvImage_dialog));
	  	    gvImageAdapter gvAdapter=new gvImageAdapter(TmpSettingItem.this);
//	  	    gvImg.setAdapter(gvAdapter);
//	  	    gvImg.setOnItemClickListener(new AdapterView.OnItemClickListener()
//		    {		 @Override
//			  public void onItemClick(AdapterView<?> gView, View gItem, int position, long index)
//			  {
//				// TODO Auto-generated method stub
//			   ImageView imgView=(ImageView)gItem;
//			   imgNew.setImageDrawable(imgView.getDrawable());
//			   //获得图片的名称
//			   imgName=imgView.getTag()==null?imgName:imgView.getTag().toString();
//			   getDialogs().setDialogDismiss();
//			 } 
//		  });
//	    	new AlertDialog.Builder(TmpSettingItem.this).setTitle("单击一个图标以选择")
//	    	.setView(gvImg).show();
//	  	    getDialogs().ShowCustomViewDialog("单击一个图标以选择", gvImg);
	     }
	  });
	  
	  listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
	  {
		 @Override
		 public void onItemClick(AdapterView<?> parentView, View itemView, int posion, long index)
		 {
			// TODO Auto-generated method stub
			String sName = ((TextView) itemView.findViewById(R.id.txtItemtype_listview_setting_item_details)).getText().toString();
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

   //加载界面数据
   void LoadSetting()
   {
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
		 imgNew.setVisibility(View.VISIBLE);
		 if (IS_PARENT)
		 {
			 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllIncomingCategory(), R.layout.listview_setting_item_details, new String[] { "name","img","imgName" },
				   new int[] { R.id.txtItemtype_listview_setting_item_details,R.id.img_listview_setting_item_details,R.id.txtImgName_listview_setting_item_details });
			 listView.setAdapter(sAdapter);
		 }
		 else
		 {
			 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllIncomingChildCategory(PARENT_NAME), R.layout.listview_setting_item_details, new String[] { "name","img","imgName"  },
				   new int[] { R.id.txtItemtype_listview_setting_item_details,R.id.img_listview_setting_item_details,R.id.txtImgName_listview_setting_item_details });
			 listView.setAdapter(sAdapter);
		 }
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
		 imgNew.setVisibility(View.VISIBLE);
		 if (IS_PARENT)
		 {
			 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllPayoutCategory(), R.layout.listview_setting_item_details, new String[] { "name","img","imgName"  },
				   new int[] { R.id.txtItemtype_listview_setting_item_details,R.id.img_listview_setting_item_details,R.id.txtImgName_listview_setting_item_details });
			 listView.setAdapter(sAdapter);
		 }
		 else
		 {
			 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllPayoutChildCategory(PARENT_NAME), R.layout.listview_setting_item_details, new String[] { "name","img","imgName"  },
				   new int[] { R.id.txtItemtype_listview_setting_item_details,R.id.img_listview_setting_item_details,R.id.txtImgName_listview_setting_item_details });
			 listView.setAdapter(sAdapter);
		 }
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 imgNew.setVisibility(View.GONE);
		 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllAccount(), R.layout.listview_setting_item_details, new String[] { "name" },
			   new int[] { R.id.txtItemtype_listview_setting_item_details });
		 listView.setAdapter(sAdapter);
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
		 imgNew.setVisibility(View.GONE);
		 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllProject(), R.layout.listview_setting_item_details, new String[] { "name" },
			   new int[] { R.id.txtItemtype_listview_setting_item_details });
		 listView.setAdapter(sAdapter);
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 imgNew.setVisibility(View.GONE);
		 SimpleAdapter sAdapter = new SimpleAdapter(TmpSettingItem.this, getAllBusiness(), R.layout.listview_setting_item_details, new String[] { "name" },
			   new int[] { R.id.txtItemtype_listview_setting_item_details });
		 listView.setAdapter(sAdapter);
	  }
   }

   //添加新项目
   void AddNewItem()
   {
	  if (edtNewItem.getText().toString().equals(""))
	  {
		 getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_notBeEmpty));
	  }
	  // ---------------------------------添加收入种类
	  else if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
		ModCategory modCategory=new ModCategory(0,getSession().getUserId(), Enums.InOrOut.Incoming.getValue(), edtNewItem.getText().toString(), "", StringHelper.FormatDateTime(new Date()), StringHelper.FormatDateTime(new Date()), 0, 0); 
        
		if(IS_PARENT)
        {
           modCategory.setParentCategoryId(0);
        }
        else 
        {
           modCategory.setParentCategoryId(getDaoCategory().GetCategoryId(PARENT_NAME));
	    }
		if(getDaoCategory().InsertCategory(modCategory))
		{
		   LoadSetting();
		}
		else 
		{
			getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "种类" + getString(R.string.alert_existOrError));
		}
	  }
        
	  // ---------------------------------添加支出种类
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
		ModCategory modCategory=new ModCategory(0,getSession().getUserId(), Enums.InOrOut.Payout.getValue(), edtNewItem.getText().toString(), "", StringHelper.FormatDateTime(new Date()), StringHelper.FormatDateTime(new Date()), 0, 0);  
	    if(IS_PARENT)
	    {
	      modCategory.setParentCategoryId(0);
        }
	    else 
	    {
	      modCategory.setParentCategoryId(getDaoCategory().GetCategoryId(PARENT_NAME));
	    }
		if(getDaoCategory().InsertCategory(modCategory))
		{
		   LoadSetting();
		}
		else 
		{
		   getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "种类" + getString(R.string.alert_existOrError));
		}
	  }
	  // ---------------------------------添加账户
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 ModAccount modAccount = new ModAccount(getSession().getUserId(), edtNewItem.getText().toString().trim(), 0, 0, StringHelper.FormatDateTime(new Date()), StringHelper.FormatDateTime(new Date()));
		 if (getDaoAccount().InsertAccount(modAccount))
		 {
			LoadSetting();
		 }
		 else
		 {
			getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "账户" + getString(R.string.alert_existOrError));
		 }
	  }
	  // ---------------------------------添加项目
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
		 ModProject modProject = new ModProject(getSession().getUserId(), edtNewItem.getText().toString().trim(), 0, 0, StringHelper.FormatDateTime(new Date()), StringHelper.FormatDateTime(new Date()));
		 if (getDaoProject().InsertProject(modProject))
		 {
			LoadSetting();
		 }
		 else
		 {
			getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "项目" + getString(R.string.alert_existOrError));
		 }
	  }
	  // ---------------------------------添加商家
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 ModBusiness modBusiness = new ModBusiness(getSession().getUserId(), edtNewItem.getText().toString().trim(), StringHelper.FormatDateTime(new Date()), StringHelper.FormatDateTime(new Date()), 0, 0);
		 if (getDaoBusiness().InsertBusiness(modBusiness))
		 {
			LoadSetting();
		 }
		 else
		 {
			getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "商家" + getString(R.string.alert_existOrError));
		 }
	  }

	  edtNewItem.setText("");
	  edtNewItem.clearFocus();
   }

   //单击设置为默认事件
   void SetDefault(String sName)
   {
	  ModProfile modProfile = new ModProfile();
	  modProfile.setUserId(getSession().getUserId());
	  modProfile.setModifyTime(StringHelper.FormatDate(new Date()));
	  
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
	    if(IS_PARENT)
	    {
	        Intent intent = new Intent();
			intent.setClass(TmpSettingItem.this, TmpSettingItem.class);
			intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Incoming.getChineseName());
			intent.putExtra(Enums.ItemTypeValue,Enums.ItemType.Incoming.getValue());
			intent.putExtra("IsParent", false);
			intent.putExtra("ParentName", sName);
			startActivityForResult(intent, CATEGORY_TYPE);   
        }
	    else 
	    {
	      modProfile.setCategory(true);
	      modProfile.setCategoryType(Enums.InOrOut.Incoming.getValue());//1为in
		  getDaoProfile().UpdateProfile(modProfile, DicCategory.TableName, DicCategory.CategoryName, sName, DicCategory.CategoryId);	  
		  setResult(RESULT_OK);
		  finish();
	    }
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
	    if(IS_PARENT)
	    {  
	       Intent intent = new Intent();
			intent.setClass(TmpSettingItem.this, TmpSettingItem.class);
			intent.putExtra(Enums.ItemTypeName, Enums.ItemType.Payout.getChineseName());
			intent.putExtra(Enums.ItemTypeValue,Enums.ItemType.Payout.getValue());
			intent.putExtra("IsParent", false);
			intent.putExtra("ParentName", sName);
			startActivityForResult(intent,CATEGORY_TYPE ); 
	    }
	    else 
	    {
		  modProfile.setCategory(true);
		  modProfile.setCategoryType(Enums.InOrOut.Payout.getValue());//2为out
		  getDaoProfile().UpdateProfile(modProfile, DicCategory.TableName, DicCategory.CategoryName, sName, DicCategory.CategoryId);	  
		  setResult(RESULT_OK);
		  finish();
	    }
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 getDaoProfile().UpdateProfile(modProfile, DicAccount.TableName, DicAccount.AccountName, sName, DicAccount.AccountId);
		 finish();
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
		 getDaoProfile().UpdateProfile(modProfile, DicProject.TableName, DicProject.ProjectName, sName, DicProject.ProjectId);
		 finish();
	  }
	  
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 getDaoProfile().UpdateProfile(modProfile, DicBusiness.TableName, DicBusiness.BusinessName, sName, DicBusiness.BusinessId);
		 finish();
	  }
   }

   //长按项目显示的菜单
   void ShowEditMenu(View itemView)
   {
	  final View itemView2 = itemView;
	  
	  //================================收入=========================================
	  if (SETTING_TYPE == Enums.ItemType.Incoming.getValue())
	  {
		 // TODO Auto-generated method stub
		 DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener()
		 {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			   // TODO Auto-generated method stub
			   TextView txtName = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
			   String sName = txtName.getText().toString();
			   
			   TextView txtImgNameTextView=(TextView)itemView2.findViewById(R.id.txtImgName_listview_setting_item_details);
			   String imgName=txtImgNameTextView.getText().toString();
			   switch (which)
			   {
				  case 0:// 编辑

                     Intent intent = new Intent();
					 intent.setClass(TmpSettingItem.this, TmpSettingItemEdit.class);
					 intent.putExtra("name", sName);
					 intent.putExtra(Enums.ItemTypeValue, Enums.ItemType.Incoming.getValue());
					 intent.putExtra("img",imgName);
					 if(IS_PARENT)
					 {
						intent.putExtra("IsParent", true);
					 }
					 else 
					 {
						intent.putExtra("IsParent", false);
					 }
					 startActivity(intent);
					 break;

				  case 1:// 删除
	
					 ModCategory modCategory = new ModCategory();
					 modCategory.setCategoryName(sName);
					 modCategory.setParentCategoryId(getDaoCategory().GetCategoryId(sName));
					 modCategory.setModifyTime(StringHelper.FormatDate(new Date()));
					 modCategory.setUserId(getSession().getUserId());
					 if(getDaoCategory().DeleteCategory(modCategory))
					 {
                        LoadSetting();
					 }
					 else
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "失败");
					 }

				  case 2:// 设为默认（父种类没有这个选项）
					 SetDefault(sName);
					 break;
			   }
			}
		 };
		 if(IS_PARENT)
		 {
		   getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete)}, onClickListener);
		 }
		 else
		 {	   
		   getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete), getString(R.string.c_setDefault) }, onClickListener);
		 }
	  }
	  
	  //================================支出=========================================
	  else if (SETTING_TYPE == Enums.ItemType.Payout.getValue())
	  {
		 // TODO Auto-generated method stub
		 DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener()
		 {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			   // TODO Auto-generated method stub
			   TextView txtName = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
			   String sName = txtName.getText().toString();
              
			   TextView txtImgNameTextView=(TextView)itemView2.findViewById(R.id.txtImgName_listview_setting_item_details);
			   String imgName=txtImgNameTextView.getText().toString();
			   switch (which)
			   {
				  case 0:// 编辑

                     Intent intent = new Intent();
					 intent.setClass(TmpSettingItem.this, TmpSettingItemEdit.class);
					 intent.putExtra("name", sName);
					 intent.putExtra(Enums.ItemTypeValue, Enums.ItemType.Payout.getValue());
					 intent.putExtra("img", imgName);
					 if(IS_PARENT)
					 {
						intent.putExtra("IsParent", true);
					 }
					 else 
					 {
						intent.putExtra("IsParent", false);
					 }
					 startActivity(intent);
					 break;

				  case 1:// 删除
	
					 ModCategory modCategory = new ModCategory();
					 modCategory.setCategoryName(sName);
					 modCategory.setParentCategoryId(getDaoCategory().GetCategoryId(sName));
					 modCategory.setModifyTime(StringHelper.FormatDate(new Date()));
					 modCategory.setUserId(getSession().getUserId());
					 if(getDaoCategory().DeleteCategory(modCategory))
					 {
                        LoadSetting();
					 }
					 else
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), "失败");
					 }

				  case 2:// 设为默认（父种类没有这个选项）
					 SetDefault(sName);
					 break;
			   }
			}
		 };
		 if(IS_PARENT)
		 {
		   getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete)}, onClickListener);
		 }
		 else
		 {	   
		   getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete), getString(R.string.c_setDefault) }, onClickListener);
		 }
	  }
	  

	  //================================项目=========================================
	  else if (SETTING_TYPE == Enums.ItemType.Project.getValue())
	  {
		 // TODO Auto-generated method stub
		 DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener()
		 {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			   // TODO Auto-generated method stub	
			   TextView txtName = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
			   String sName = txtName.getText().toString();
			   switch (which)
			   {
				  case 0:// 编辑
					 if (sName.equals(getString(R.string.d_none)))
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
						break;
					 }
					 Intent intent = new Intent();
					 intent.setClass(TmpSettingItem.this, TmpSettingItemEdit.class);
					 intent.putExtra("name", sName);
					 intent.putExtra(Enums.ItemTypeValue, Enums.ItemType.Project.getValue());
					 startActivity(intent);
					 break;

				  case 1:// 删除
                     if (sName.equals(getString(R.string.d_none)))
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
						break;
					 }
					 ModProject modProject = new ModProject();
					 modProject.setProjectName(sName);
					 modProject.setModifyTime(StringHelper.FormatDate(new Date()));
					 getDaoProject().DeleteProject(modProject);
					 LoadSetting();
					 break;

				  case 2:// 设为默认
					 SetDefault(sName);
					 break;
			   }
			}
		 };
		 getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete), getString(R.string.c_setDefault) }, onClickListener);
	  
	  }
	  
	  //================================账户=========================================
	  else if (SETTING_TYPE == Enums.ItemType.Account.getValue())
	  {
		 // TODO Auto-generated method stub
		 DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener()
		 {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			   // TODO Auto-generated method stub	
			   TextView txtName = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
			   String sName = txtName.getText().toString();
			   switch (which)
			   {
				  case 0:// 编辑
					 if (sName.equals(getString(R.string.d_none)))
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
						break;
					 }
					 Intent intent = new Intent();
					 intent.setClass(TmpSettingItem.this, TmpSettingItemEdit.class);
					 intent.putExtra("name", sName);
					 intent.putExtra(Enums.ItemTypeValue, Enums.ItemType.Account.getValue());
					 startActivity(intent);
					 break;

				  case 1:// 删除
                     if (sName.equals(getString(R.string.d_none)))
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
						break;
					 }
					 ModAccount modAccount = new ModAccount();
					 modAccount.setAccountName(sName);
					 modAccount.setModifyTime(StringHelper.FormatDate(new Date()));
					 getDaoAccount().DeleteAccount(modAccount);
					 LoadSetting();
					 break;

				  case 2:// 设为默认
					 SetDefault(sName);
					 break;
			   }
			}
		 };
		 getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete), getString(R.string.c_setDefault) }, onClickListener);
	  
	  }
	  
	  //================================商家=========================================
	  else if (SETTING_TYPE == Enums.ItemType.Business.getValue())
	  {
		 // TODO Auto-generated method stub
		 DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener()
		 {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			   // TODO Auto-generated method stub
			   TextView txtName = (TextView) itemView2.findViewById(R.id.txtItemtype_listview_setting_item_details);
			   String sName = txtName.getText().toString();
			   switch (which)
			   {
				  case 0:// 编辑
					 if (sName.equals(getString(R.string.d_none)))
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
						break;
					 }
					 Intent intent = new Intent();
					 intent.setClass(TmpSettingItem.this, TmpSettingItemEdit.class);
					 intent.putExtra("name", sName);
					 intent.putExtra(Enums.ItemTypeValue, Enums.ItemType.Business.getValue());
					 startActivity(intent);
					 break;

				  case 1:// 删除
                     if (sName.equals(getString(R.string.d_none)))
					 {
						getDialogs().ShowOKAlertDialog(getString(R.string.alert_tip), getString(R.string.alert_canNotDel));
						break;
					 }
					 ModBusiness modBusiness = new ModBusiness();
					 modBusiness.setBusinessName(txtName.getText().toString());
					 modBusiness.setModifyTime(StringHelper.FormatDate(new Date()));
					 getDaoBusiness().DeleteBusiness(modBusiness);
					 LoadSetting();
					 break;

				  case 2:// 设为默认
					 SetDefault(sName);
					 break;
			   }
			}
		 };
		 getDialogs().ShowItemsDialog(new String[] { getString(R.string.c_edit), getString(R.string.c_delete), getString(R.string.c_setDefault) }, onClickListener);
	  }
   }



   /**
    * 加载所有商家
    * 
    * @return
    */
   public List<Map<String, Object>> getAllBusiness()
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : getDaoBusiness().GetAllBusinessByUserId(getSession().getUserId()))
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
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : getDaoProject().GetAllProjectByUserId(getSession().getUserId()))
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
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
	  for (String bName : getDaoAccount().GetAllAccountByUserId(getSession().getUserId()))
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
   public List<Map<String, Object>> getAllIncomingCategory()
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
//	  Class<?> cl = R.drawable.class;
//	  Field ff = null;
	  try
	  {
		 for (String[] bName : getDaoCategory().GetAllParentCategorys(getSession().getUserId(), Enums.InOrOut.Incoming.getValue()))
		 {
//			ff = cl.getField(bName[1]);
//			int rId = ff.getInt(cl);
			map = new HashMap<String, Object>();
			map.put("name", bName[0]);
			map.put("img", DataHelper.GetDrawIdByName(bName[1]));
			map.put("imgName", bName[1]);
			list.add(map);
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
	  }
	  return list;
   }

   /**
    * 加载收入类型的所有子类
    * 
    * @return
    */
   public List<Map<String, Object>> getAllIncomingChildCategory(String parentName)
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
//	  Class<?> cl = R.drawable.class;
//	  Field ff = null;
	  try
	  {
		 for (String[] bName : getDaoCategory().GetAllChildrenCategorys(getSession().getUserId(), Enums.InOrOut.Incoming.getValue(),parentName))
		 {
//			ff = cl.getField(bName[1]);
//			int rId = ff.getInt(cl);
			map = new HashMap<String, Object>();
			map.put("name", bName[0]);
			map.put("img", DataHelper.GetDrawIdByName(bName[1]));
			map.put("imgName", bName[1]);
			list.add(map);
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
	  }
	  return list;
   }

   /**
    * 加载所有支出类型
    * 
    * @return
    */
   public List<Map<String, Object>> getAllPayoutCategory()
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
//	  Class<?> cl = R.drawable.class;
//	  Field ff = null;
	  try
	  {
		 for (String[] bName : getDaoCategory().GetAllParentCategorys(getSession().getUserId(), Enums.InOrOut.Payout.getValue()))
		 {
//			ff = cl.getField(bName[1]);
//			int rId = ff.getInt(cl);
			map = new HashMap<String, Object>();
			map.put("name", bName[0]);
			map.put("img", DataHelper.GetDrawIdByName(bName[1]));
			map.put("imgName", bName[1]);
			list.add(map);
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
	  }
	  return list;
   }

   /**
    * 加载支出类型的所有子类
    * 
    * @return
    */
   public List<Map<String, Object>> getAllPayoutChildCategory(String parentName)
   {
	  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Map<String, Object> map = null;
//	  Class<?> cl = R.drawable.class;
//	  Field ff = null;
	  try
	  {
		 for (String[] bName : getDaoCategory().GetAllChildrenCategorys(getSession().getUserId(), Enums.InOrOut.Payout.getValue(),parentName))
		 {
//			ff = cl.getField(bName[1]);
//			int rId = ff.getInt(cl);
			map = new HashMap<String, Object>();
			map.put("name", bName[0]);
			map.put("img", DataHelper.GetDrawIdByName(bName[1]));
			map.put("imgName", bName[1]);
			list.add(map);
		 }
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
	  }
	  return list;
   }

   
   
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_tmp_setting_item);
	  SETTING_TYPE = getIntent().getIntExtra(Enums.ItemTypeValue, 0);
	  IS_PARENT = getIntent().getBooleanExtra("IsParent", true);
	  PARENT_NAME=getIntent().getStringExtra("ParentName");

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

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data)
   {
	  // TODO Auto-generated method stub
         if(requestCode==CATEGORY_TYPE)
         {
        	if(resultCode==RESULT_OK)
        	{
        	   finish();
        	}
		 }
   }
   
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
   {
	  if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
	  { // 按下的如果是BACK，同时没有重复
		 this.finish();
	  }
	  return false;
   }
   
   
}
