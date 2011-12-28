package ppcodes.accountbook.app;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import ppcodes.accountbook.common.Enums;
import ppcodes.accountbook.dao.DaoBusiness;
import ppcodes.accountbook.entity.model.ModBusiness;
import ppcodes.android.common.gvImageAdapter;
import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.sax.Element;
import android.view.View;
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
   String itemName;
   
   final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   void InitControls()
   {
	 gvIcon=(GridView)findViewById(R.id.gvIcon_Act_Tmp_setting_item_edit);
	 
	 imgCurrent=(ImageView)findViewById(R.id.imgCurrent_Act_Tmp_setting_item_edit);
	 imgCurrent.setImageResource(imgId);
	 
	 lLay=(LinearLayout)findViewById(R.id.lay_Act_Tmp_setting_item_edit);
	 
	 tRow=(TableRow)findViewById(R.id.row_Act_Tmp_setting_item_edit);
	 
	 edtName=(EditText)findViewById(R.id.edtName_Act_Tmp_setting_item_edit);
	 edtName.setText(itemName);
	 
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
			 ModBusiness modBusiness=new ModBusiness();
			 modBusiness.setBusinessName(edtName.getText().toString().trim());
			 modBusiness.setModifyTime(dateFormat.format(new Date()));
			 DaoBusiness daoBusiness=new DaoBusiness(TmpSettingItemEdit.this);
			 daoBusiness.UpdateBusinessName(modBusiness, itemName);
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

	  InitControls();
	  InitControlsListener();

	  SetIconState();

//      Class<?> cl=R.drawable.class;
//      Field ff=null;
//	  try
//	  {
//		 ff=cl.getField("icon_jltx_zjf");
//	  }
//	  catch (SecurityException e)
//	  {
//		 // TODO Auto-generated catch block
//		e.printStackTrace();
//	  }
//	  catch (NoSuchFieldException e)
//	  {
//		 // TODO Auto-generated catch block
//		e.printStackTrace();
//	  }
//      int aa=0;
//      try
//	  {
//		 aa=ff.getInt(cl);
//	  }
//	  catch (IllegalArgumentException e)
//	  {
//		 // TODO Auto-generated catch block
//		e.printStackTrace();
//	  }
//	  catch (IllegalAccessException e)
//	  {
//		 // TODO Auto-generated catch block
//		e.printStackTrace();
//	  }
//      
//	  imgCurrent.setImageResource(aa);

	 
   }
}
