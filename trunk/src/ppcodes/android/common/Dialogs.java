/**
    Dialog dialogs=new Dialogs(ActBarcode.this);
    dialogs.CreateLoadingDialog();弹出
    dialogs.setDialogDismiss();销毁
 */

package ppcodes.android.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class Dialogs
{
   public Dialogs(Context _context)
   {
	  context = _context;
   }

   public Dialogs()
   {

   }

   AlertDialog dialog;
   AlertDialog.Builder builder;
   ProgressDialog mDialog;
   Context context;

   private static final int loadingDialogNoTitle = 0;
   private static final int loadingDialogTitle = 1;

   // private static final int okAlertDialog=0;
   // private static final int okcancelAlertDialog=1;

   /**
    * 创建一个带有OK按钮的提示窗
    * 
    * @param title
    *           标题
    * @param message
    *           信息
    */
   public void ShowOKAlertDialog(String title, String message)
   {
	  builder = new AlertDialog.Builder(context);
	  builder.setTitle(title);
	  builder.setMessage(message);
	  builder.setPositiveButton("确定", new OnClickListener()
	  {
		 @Override
		 public void onClick(DialogInterface dialog, int which)
		 {
			dialog.dismiss();
		 }
	  });
	  builder.create().show();
   }

   /**
    * 创建一个带有OK,Cancel按钮的提示窗
    * 
    * @param title
    *           标题
    * @param message
    *           信息
    * @param OKButtonOnClickListener
    *           OK按钮的监听器
    */
   public void ShowOKCancelAlertDialog(String title, String message, OnClickListener OKButtonOnClickListener)
   {
	  builder = new AlertDialog.Builder(context);
	  builder.setTitle(title);
	  builder.setMessage(message);
	  builder.setPositiveButton("确定", OKButtonOnClickListener);
	  builder.setNegativeButton("取消", new OnClickListener()
	  {
		 @Override
		 public void onClick(DialogInterface dialog, int which)
		 {
			// TODO Auto-generated method stub
			dialog.dismiss();
		 }
	  });
	  builder.create().show();
   }

   public void ShowItemsDialog(String[] items, DialogInterface.OnClickListener onClickListener)
   {
	  builder = new AlertDialog.Builder(context);
	  builder.setItems(items, onClickListener);
	  builder.create().show();
   }

   /**
    * 
    * @param title 标题，如果没有则留null或者""
    * @param view 需要加载的自定义布局
    * @param height 高度占整个屏幕的百分比 如90代表90%
    * @param width  宽度占整个屏幕的百分比
    */
   public void ShowCustomViewDialog(String title, View view, int height, int width)
   {
	  dialog = new AlertDialog.Builder(context).create();
	  if (title == null || title.equals(""))
	  {
		 dialog.setView(view);
	  }
	  else
	  {
		 dialog.setTitle(title);
		 dialog.setView(view);
	  }
	  dialog.show();
	  DisplayMetrics dm = new DisplayMetrics();
	  Activity act = (Activity) context;
	  act.getWindowManager().getDefaultDisplay().getMetrics(dm);
	  WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
	  params.width = dm.widthPixels * width / 100;
	  params.height = dm.heightPixels * height / 100;
	  dialog.getWindow().setAttributes(params);
   }

   public void dismissAlertDialog()
   {
	  if (dialog.isShowing())
	  {
		 dialog.dismiss();
	  }
   }

   /**
    * 创建一个没有标题栏的loading窗口
    * 
    * @param message
    *           信息
    */
   public void ShowLoadingDialogNoTitle(String message)
   {
	  CreateLoadingDialog(loadingDialogNoTitle, message);
   }

   public void ShowLoadingDialog(String message)
   {
	  CreateLoadingDialog(loadingDialogTitle, message);
   }

   public void setDialogDismiss()
   {
	  if (mDialog.isShowing())
	  {
		 mDialog.dismiss();
	  }
   }

   protected void CreateLoadingDialog(int id, String message)
   {
	  mDialog = new ProgressDialog(context);
	  switch (id)
	  {
		 case loadingDialogTitle:
			mDialog.setTitle("Indeterminate");
			mDialog.setMessage(message);
			mDialog.setIndeterminate(true);
			mDialog.setCancelable(true);
			break;
		 case loadingDialogNoTitle:
			mDialog.setMessage(message);
			mDialog.setIndeterminate(true);
			mDialog.setCancelable(true);
			break;
	  }
	  mDialog.show();
   }
}
