package ppcodes.accountbook.app;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.entity.model.ModGvTab;
import ppcodes.android.common.gvTabAdapter;
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
import android.widget.TextView;
import android.widget.Toast;

public class ActNewRecording extends ActivityGroup
{
   /** Called when the activity is first created. */

   // private LinearLayout tabOut;
   // private LinearLayout tabIn;
   // private FrameLayout tabContent;
   //
   // private LinearLayout layOutType;
   // private LinearLayout layOutChildType;
   // private LinearLayout layOutAccount;
   // private LinearLayout layOutBusiness;
   // private LinearLayout layOutItem;
   // private LinearLayout layOutDate;
   // private LinearLayout layOutComment;
   // private EditText edtOutMoney;
   // private TextView txtOutType;
   // private TextView txtOutChildType;
   // private TextView txtOutAccount;
   // private TextView txtOutBusiness;
   // private TextView txtOutItem;
   // private TextView txtOutDate;
   // private TextView txtOutComment;
   //
   // private LinearLayout layInType;
   // private LinearLayout layInChildType;
   // private LinearLayout layInAccount;
   // private LinearLayout layInItem;
   // private LinearLayout layInDate;
   // private LinearLayout layInComment;
   // private EditText edtInMoney;
   // private TextView txtInType;
   // private TextView txtInChildType;
   // private TextView txtInAccount;
   // private TextView txtInItem;
   // private TextView txtInDate;
   // private TextView txtInComment;
   //
   // private Button btnOutOK;
   // private Button btnOutCancel;
   // private Button btnInOK;
   // private Button btnInCancel;
   //
   // LayoutInflater inflater;
   //
   //
   // //选项单击事件
   // private OnTouchListener touchListener=new OnTouchListener()
   // {
   // @Override
   // public boolean onTouch(View v, MotionEvent event)
   // {
   // // TODO Auto-generated method stub
   //
   // if (event.getAction()==MotionEvent.ACTION_DOWN)
   // {
   // v.setBackgroundResource(R.color.LightBlue);
   // }
   // if(event.getAction()==MotionEvent.ACTION_UP)
   // {
   // v.setBackgroundColor(Color.TRANSPARENT);
   // switch (v.getId())
   // {
   // case R.id.layOutType:
   // txtOutType.setText("111");
   // break;
   // case R.id.layOutChildType:
   // txtOutChildType.setText("111");
   // break;
   // case R.id.layOutAccount:
   // txtOutAccount.setText("111");
   // break;
   // case R.id.layBusiness:
   // txtOutBusiness.setText("111");
   // break;
   // case R.id.layOutDate:
   // txtOutDate.setText("111");
   // break;
   // case R.id.layOutItem:
   // txtOutItem.setText("111");
   // break;
   // case R.id.layOutComment:
   // txtOutComment.setText("111");
   // break;
   // case R.id.layInType:
   // txtInType.setText("111");
   // break;
   // case R.id.layInChildType:
   // txtInChildType.setText("111");
   // break;
   // case R.id.layInAccount:
   // txtInAccount.setText("111");
   // break;
   // case R.id.layInItem:
   // txtInItem.setText("111");
   // break;
   // case R.id.layInDate:
   // txtInDate.setText("111");
   // break;
   // case R.id.layInComment:
   // txtInComment.setText("111");
   // break;
   // default:
   // break;
   // }
   // }
   // /*if(event.getAction()==MotionEvent.ACTION_MOVE)
   // {
   // int[] location = new int[2];
   // v.getLocationInWindow(location);
   // if(event.getX()>location[0]+v.getWidth()
   // ||event.getX()<location[0]
   // ||event.getY()>location[1]+v.getHeight()
   // ||event.getY()<location[1])
   // {
   // v.setBackgroundColor(Color.TRANSPARENT);
   // Toast.makeText(act_NewRecording.this,"out", 1).show();
   // }
   // }*/
   // return true;
   // }
   // };
   // //提交按钮单击事件
   // private OnClickListener clickListener=new OnClickListener()
   // {
   // @Override
   // public void onClick(View v)
   // {
   // // TODO Auto-generated method stub
   // switch(v.getId())
   // {
   // case R.id.btnNewOutOK:
   // Toast.makeText(ActNewRecording.this,"OutOK", 500).show();
   // break;
   // case R.id.btnNewOutCancel:
   // Toast.makeText(ActNewRecording.this,"OutCancel", 500).show();
   // break;
   // case R.id.btnNewInOK:
   // Toast.makeText(ActNewRecording.this,"InOK", 500).show();
   // break;
   // case R.id.btnNewInCancel:
   // Toast.makeText(ActNewRecording.this,"InCancel", 500).show();
   // break;
   // default:
   // break;
   // }
   // }
   // };
   // //tab页切换事件
   // private OnClickListener tabClickListener=new OnClickListener()
   // {
   // @Override
   // public void onClick(View v)
   // {
   // // TODO Auto-generated method stub
   // if(v.getId()==R.id.tabOut)
   // {
   // tabOut.setBackgroundColor(Color.TRANSPARENT);
   // tabIn.setBackgroundResource(R.color.LightBrown);
   // LoadOutView();
   // }
   // if(v.getId()==R.id.tabIn)
   // {
   // tabOut.setBackgroundResource(R.color.LightBrown);
   // tabIn.setBackgroundColor(Color.TRANSPARENT);
   // LoadInView();
   // }
   // }
   // };
   //
   // //加载支出View
   // private void LoadOutView()
   // {
   // try
   // {
   // View viewOut=inflater.inflate(R.layout.act_newrecout, null);
   // LinearLayout tabLin=(LinearLayout)viewOut.findViewById(R.id.tabOutLin);
   //
   // layOutType=(LinearLayout)viewOut.findViewById(R.id.layOutType);
   // layOutChildType=(LinearLayout)viewOut.findViewById(R.id.layOutChildType);
   // layOutAccount=(LinearLayout)viewOut.findViewById(R.id.layOutAccount);
   // layOutBusiness=(LinearLayout)viewOut.findViewById(R.id.layBusiness);
   // layOutItem=(LinearLayout)viewOut.findViewById(R.id.layOutItem);
   // layOutDate=(LinearLayout)viewOut.findViewById(R.id.layOutDate);
   // layOutComment=(LinearLayout)viewOut.findViewById(R.id.layOutComment);
   // btnOutOK=(Button)viewOut.findViewById(R.id.btnNewOutOK);
   // btnOutCancel=(Button)viewOut.findViewById(R.id.btnNewOutCancel);
   //
   // edtOutMoney=(EditText)viewOut.findViewById(R.id.edtOutMoney);
   // txtOutType=(TextView)viewOut.findViewById(R.id.txtOutType);
   // txtOutChildType=(TextView)viewOut.findViewById(R.id.txtOutChildType);
   // txtOutAccount=(TextView)viewOut.findViewById(R.id.txtOutAccount);
   // txtOutBusiness=(TextView)viewOut.findViewById(R.id.txtBusiness);
   // txtOutItem=(TextView)viewOut.findViewById(R.id.txtOutItem);
   // txtOutDate=(TextView)viewOut.findViewById(R.id.txtOutDate);
   // txtOutComment=(TextView)viewOut.findViewById(R.id.txtOutComment);
   //
   //
   // layOutType.setOnTouchListener(touchListener);
   // layOutChildType.setOnTouchListener(touchListener);
   // layOutAccount.setOnTouchListener(touchListener);
   // layOutBusiness.setOnTouchListener(touchListener);
   // layOutItem.setOnTouchListener(touchListener);
   // layOutDate.setOnTouchListener(touchListener);
   // layOutComment.setOnTouchListener(touchListener);
   // btnOutOK.setOnClickListener(clickListener);
   // btnOutCancel.setOnClickListener(clickListener);
   //
   // tabContent.removeAllViews();
   // tabContent.addView(tabLin);
   // }
   // catch (Exception e)
   // {
   // // TODO: handle exception
   // Log.e("ERROR","LoadOutView"+e.getMessage()+ e.getStackTrace());
   // }
   //
   //
   // }
   //
   // //加载收入View
   // private void LoadInView()
   // {
   // try
   // {
   // View viewIn=inflater.inflate(R.layout.act_newrecin, null);
   // LinearLayout tabLin=(LinearLayout)viewIn.findViewById(R.id.tabInLin);
   // layInType=(LinearLayout)viewIn.findViewById(R.id.layInType);
   // layInChildType=(LinearLayout)viewIn.findViewById(R.id.layInChildType);
   // layInAccount=(LinearLayout)viewIn.findViewById(R.id.layInAccount);
   // layInItem=(LinearLayout)viewIn.findViewById(R.id.layInItem);
   // layInDate=(LinearLayout)viewIn.findViewById(R.id.layInDate);
   // layInComment=(LinearLayout)viewIn.findViewById(R.id.layInComment);
   // btnInOK=(Button)viewIn.findViewById(R.id.btnNewInOK);
   // btnInCancel=(Button)viewIn.findViewById(R.id.btnNewInCancel);
   //
   // edtInMoney=(EditText)viewIn.findViewById(R.id.edtInMoney);
   // txtInType=(TextView)viewIn.findViewById(R.id.txtInType);
   // txtInChildType=(TextView)viewIn.findViewById(R.id.txtInChildType);
   // txtInAccount=(TextView)viewIn.findViewById(R.id.txtInAccount);
   // txtInItem=(TextView)viewIn.findViewById(R.id.txtInItem);
   // txtInDate=(TextView)viewIn.findViewById(R.id.txtInDate);
   // txtInComment=(TextView)viewIn.findViewById(R.id.txtInComment);
   //
   // layInType.setOnTouchListener(touchListener);
   // layInChildType.setOnTouchListener(touchListener);
   // layInAccount.setOnTouchListener(touchListener);
   // layInItem.setOnTouchListener(touchListener);
   // layInDate.setOnTouchListener(touchListener);
   // layInComment.setOnTouchListener(touchListener);
   // btnInOK.setOnClickListener(clickListener);
   // btnInCancel.setOnClickListener(clickListener);
   //
   // tabContent.removeAllViews();
   // tabContent.addView(tabLin);
   // }
   // catch (Exception e)
   // {
   // // TODO: handle exception
   // Log.e("ERROR", "LoadInView"+e.getMessage()+ e.getStackTrace());
   // }
   //
   // }

   GridView gvTab;
   gvTabAdapter tabAdapter;
   FrameLayout container;

   private List<ModGvTab> GetTabItem()
   {
	  List<ModGvTab> list = new ArrayList<ModGvTab>();
	  list.add(new ModGvTab(R.drawable.setting_category_income_icon, "新增收入"));
	  list.add(new ModGvTab(R.drawable.setting_category_payout_icon, "新增支出"));
	  return list;
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.act_newrecord);
	  try
	  {
		 List<ModGvTab> list = GetTabItem();
		 gvTab = (GridView) findViewById(R.id.gvTab_Act_newrecord);
		 gvTab.setNumColumns(list.size());// 设置每行列数
		 gvTab.setSelector(new ColorDrawable(Color.TRANSPARENT));// 选中的时候为透明色
		 int width = this.getWindowManager().getDefaultDisplay().getWidth() / list.size();
		 tabAdapter = new gvTabAdapter(this, list, width, 80);
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

		 // inflater=LayoutInflater.from(this);
		 // tabIn=(LinearLayout)findViewById(R.id.tabIn);
		 // tabOut=(LinearLayout)findViewById(R.id.tabOut);
		 // tabContent=(FrameLayout)findViewById(R.id.tabContent);
		 // tabIn.setOnClickListener(tabClickListener);
		 // tabOut.setOnClickListener(tabClickListener);
		 //
		 // //默认是支出框
		 // LoadOutView();
	  }
	  catch (Exception e)
	  {
		 // TODO: handle exception
		 Log.e("ERROR", "onCreate" + e.getStackTrace() + e.getMessage());
	  }

	  /*
	   * listView2=(ListView)findViewById(R.id.listView2); SimpleAdapter
	   * sAdapter=new SimpleAdapter(this,null, R.layout.newrecordinglistview1,
	   * null, null); listView2.setAdapter(sAdapter);
	   */
   }

   /**
    * 根据ID打开指定的Activity
    * 
    * @param id
    *           GridView选中项的序号
    */
   void SwitchActivity(int id)
   {
	  tabAdapter.SetFocus(id);// 选中项获得高亮
//	  container.removeAllViews();// 必须先清除容器中所有的View
//	  Intent intent = null;
//	  if (id == 0 || id == 2)
//	  {
//		 intent = new Intent(ActivityGroupDemo.this, ActivityA.class);
//	  }
//	  else if (id == 1 || id == 3)
//	  {
//		 intent = new Intent(ActivityGroupDemo.this, ActivityB.class);
//	  }
//	  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//	  // Activity 转为 View
//	  Window subActivity = getLocalActivityManager().startActivity("subActivity", intent);
//	  // 容器添加View
//	  container.addView(subActivity.getDecorView(), LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
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
