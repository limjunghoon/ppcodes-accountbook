package ppcodes.android.common;

import ppcodes.accountbook.app.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class gvImageAdapter extends BaseAdapter
{

   // 定义Context
   private Context mContext;

   // 定义整型数组 即图片源

   private Integer[] mImageIds = { R.drawable.icon_jjwy, R.drawable.icon_jjwy_fz, R.drawable.icon_jjwy_rcyp, R.drawable.icon_jjwy_sdmq, R.drawable.icon_jjwy_wygl, R.drawable.icon_jjwy_yxby,
		 R.drawable.icon_jltx, R.drawable.icon_jltx_sjf, R.drawable.icon_jltx_swf, R.drawable.icon_jltx_yjf, R.drawable.icon_jltx_zjf, R.drawable.icon_jrbx, R.drawable.icon_jrbx,
		 R.drawable.icon_jrbx_ajhk, R.drawable.icon_jrbx_lxzc, R.drawable.icon_jrbx_pcfk, R.drawable.icon_jrbx_tzks, R.drawable.icon_jrbx_xfss, R.drawable.icon_jrbx_yhsx, R.drawable.icon_lend_out,
		 R.drawable.icon_qtsr, R.drawable.icon_qtsr_jysd, R.drawable.icon_qtsr_ljsr, R.drawable.icon_qtsr_ywlq, R.drawable.icon_qtsr_zjsr, R.drawable.icon_qtzx, R.drawable.icon_qtzx_lzss,
		 R.drawable.icon_qtzx_qtzc, R.drawable.icon_qtzx_ywds, R.drawable.icon_repay_in, R.drawable.icon_repay_out, R.drawable.icon_rqwl, R.drawable.icon_rqwl_csjz, R.drawable.icon_rqwl_hrqc,
		 R.drawable.icon_rqwl_slqk, R.drawable.icon_rqwl_xjjz, R.drawable.icon_spjs, R.drawable.icon_spjs_sgls, R.drawable.icon_spjs_yjc, R.drawable.icon_spjs_zwwc, R.drawable.icon_transfer_in,
		 R.drawable.icon_transfer_out, R.drawable.icon_xcjt, R.drawable.icon_xcjt_dczc, R.drawable.icon_xcjt_ggjt, R.drawable.icon_xcjt_sjcfy, R.drawable.icon_xxjx, R.drawable.icon_xxjx_pxjx,
		 R.drawable.icon_xxjx_sbzz, R.drawable.icon_xxjx_smzb, R.drawable.icon_xxyl, R.drawable.icon_xxyl_cwbb, R.drawable.icon_xxyl_fbjh, R.drawable.icon_xxyl_lydj, R.drawable.icon_xxyl_xxwl,
		 R.drawable.icon_xxyl_ydjs, R.drawable.icon_yfsp, R.drawable.icon_yfsp_hzsp, R.drawable.icon_yfsp_xmbb, R.drawable.icon_yfsp_yfkz, R.drawable.icon_ylbj, R.drawable.icon_ylbj_bjf,
		 R.drawable.icon_ylbj_mrf, R.drawable.icon_ylbj_ypf, R.drawable.icon_ylbj_zlf, R.drawable.icon_zysr, R.drawable.icon_zysr_gzsr, R.drawable.icon_zysr_jbsr, R.drawable.icon_zysr_jjsr,
		 R.drawable.icon_zysr_jzsr, R.drawable.icon_zysr_lxsr, R.drawable.icon_zysr_tzsr };

   public gvImageAdapter(Context c)
   {
	  mContext = c;
   }

   // 获取图片的个数
   @Override
   public int getCount()
   {
	  return mImageIds.length;
   }

   // 获取图片在库中的位置
   @Override
   public Object getItem(int position)
   {
	  return position;
   }

   // 获取图片ID
   public long getItemId(int position)
   {
	  return position;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent)
   {
	  ImageView imageView;

	  if (convertView == null)
	  {
		 // 给ImageView设置资源
		 imageView = new ImageView(mContext);

		 // 设置布局 图片32×32显示
		 // imageView.setLayoutParams(new GridView.LayoutParams(70, 70));

		 // 设置显示比例类型
		 imageView.setScaleType(ImageView.ScaleType.CENTER);
		 imageView.setPadding(5, 5, 5, 5);
	  }
	  else
	  {
		 imageView = (ImageView) convertView;
	  }

	  imageView.setImageResource(mImageIds[position]);

	  return imageView;
   }
}
