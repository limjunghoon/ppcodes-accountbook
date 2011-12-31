package ppcodes.android.common;

import java.util.ArrayList;
import java.util.List;
import ppcodes.accountbook.app.R;
import ppcodes.accountbook.entity.model.ModImage;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class gvImageAdapter extends BaseAdapter
{

   // 定义Context
   private Context mContext;

   // 定义整型数组 即图片源

//   private Integer[] mImageIds = { R.drawable.icon_jjwy, R.drawable.icon_jjwy_fz, R.drawable.icon_jjwy_rcyp, R.drawable.icon_jjwy_sdmq, R.drawable.icon_jjwy_wygl, R.drawable.icon_jjwy_yxby,
//		 R.drawable.icon_jltx, R.drawable.icon_jltx_sjf, R.drawable.icon_jltx_swf, R.drawable.icon_jltx_yjf, R.drawable.icon_jltx_zjf, R.drawable.icon_jrbx,
//		 R.drawable.icon_jrbx_ajhk, R.drawable.icon_jrbx_lxzc, R.drawable.icon_jrbx_pcfk, R.drawable.icon_jrbx_tzks, R.drawable.icon_jrbx_xfss, R.drawable.icon_jrbx_yhsx, R.drawable.icon_lend_out,
//		 R.drawable.icon_qtsr, R.drawable.icon_qtsr_jysd, R.drawable.icon_qtsr_ljsr, R.drawable.icon_qtsr_ywlq, R.drawable.icon_qtsr_zjsr, R.drawable.icon_category_default, 
//		 R.drawable.icon_qtzx,R.drawable.icon_qtzx_lzss,R.drawable.icon_qtzx_qtzc, R.drawable.icon_qtzx_ywds, R.drawable.icon_repay_in, R.drawable.icon_repay_out, 
//		 R.drawable.icon_rqwl, R.drawable.icon_rqwl_csjz, R.drawable.icon_rqwl_hrqc,R.drawable.icon_rqwl_slqk, R.drawable.icon_rqwl_xjjz, 
//		 R.drawable.icon_spjs, R.drawable.icon_spjs_sgls, R.drawable.icon_spjs_yjc, R.drawable.icon_spjs_zwwc, R.drawable.icon_transfer_in,R.drawable.icon_transfer_out,
//		 R.drawable.icon_xcjt, R.drawable.icon_xcjt_dczc, R.drawable.icon_xcjt_ggjt, R.drawable.icon_xcjt_sjcfy, R.drawable.icon_xxjx, R.drawable.icon_xxjx_pxjx,
//		 R.drawable.icon_xxjx_sbzz, R.drawable.icon_xxjx_smzb, R.drawable.icon_xxyl, R.drawable.icon_xxyl_cwbb, R.drawable.icon_xxyl_fbjh, R.drawable.icon_xxyl_lydj, R.drawable.icon_xxyl_xxwl,
//		 R.drawable.icon_xxyl_ydjs, R.drawable.icon_yfsp, R.drawable.icon_yfsp_hzsp, R.drawable.icon_yfsp_xmbb, R.drawable.icon_yfsp_yfkz, R.drawable.icon_ylbj, R.drawable.icon_ylbj_bjf,
//		 R.drawable.icon_ylbj_mrf, R.drawable.icon_ylbj_ypf, R.drawable.icon_ylbj_zlf, R.drawable.icon_zysr, R.drawable.icon_zysr_gzsr, R.drawable.icon_zysr_jbsr, R.drawable.icon_zysr_jjsr,
//		 R.drawable.icon_zysr_jzsr, R.drawable.icon_zysr_lxsr, R.drawable.icon_zysr_tzsr };
   
   private List<ModImage> imgList=new ArrayList<ModImage>();
      
   public gvImageAdapter(Context c)
   {
	  mContext = c;
	  imgList=new ArrayList<ModImage>();
	  imgList.add(new ModImage(R.drawable.icon_jjwy,"icon_jjwy"));
	  imgList.add(new ModImage(R.drawable.icon_jjwy_fz,"icon_jjwy_fz"));
	  imgList.add(new ModImage(R.drawable.icon_jjwy_rcyp,"icon_jjwy_rcyp"));
      imgList.add(new ModImage(R.drawable.icon_jjwy_sdmq,"icon_jjwy_sdmq")); 
      imgList.add(new ModImage(R.drawable.icon_jjwy_wygl,"icon_jjwy_wygl"));
	  imgList.add(new ModImage(R.drawable.icon_jjwy_yxby,"icon_jjwy_yxby"));
	  imgList.add(new ModImage(R.drawable.icon_jltx,"icon_jltx")); 
	  imgList.add(new ModImage(R.drawable.icon_jltx_sjf,"icon_jltx_sjf"));
	  imgList.add(new ModImage(R.drawable.icon_jltx_swf,"icon_jltx_swf"));
	  imgList.add(new ModImage(R.drawable.icon_jltx_yjf,"icon_jltx_yjf"));
	  imgList.add(new ModImage(R.drawable.icon_jltx_zjf,"icon_jltx_zjf"));
	  imgList.add(new ModImage(R.drawable.icon_jrbx,"icon_jrbx")); 
	  imgList.add(new ModImage(R.drawable.icon_jrbx_ajhk,"icon_jrbx_ajhk"));
	  imgList.add(new ModImage(R.drawable.icon_jrbx_lxzc,"icon_jrbx_lxzc")); 
	  imgList.add(new ModImage(R.drawable.icon_jrbx_pcfk,"icon_jrbx_pcfk"));
	  imgList.add(new ModImage(R.drawable.icon_jrbx_tzks,"icon_jrbx_tzks")); 
	  imgList.add(new ModImage(R.drawable.icon_jrbx_xfss,"icon_jrbx_xfss"));
	  imgList.add(new ModImage(R.drawable.icon_jrbx_yhsx,"icon_jrbx_yhsx")); 
	  imgList.add(new ModImage(R.drawable.icon_lend_out,"icon_lend_out"));
	  imgList.add(new ModImage(R.drawable.icon_qtsr,"icon_qtsr")); 
	  imgList.add(new ModImage(R.drawable.icon_qtsr_jysd,"icon_qtsr_jysd"));
	  imgList.add(new ModImage(R.drawable.icon_qtsr_ljsr,"icon_qtsr_ljsr")); 
	  imgList.add(new ModImage(R.drawable.icon_qtsr_ywlq,"icon_qtsr_ywlq"));
	  imgList.add(new ModImage(R.drawable.icon_qtsr_zjsr,"icon_qtsr_zjsr")); 
	  imgList.add(new ModImage(R.drawable.icon_category_default,"icon_category_default"));
	  imgList.add(new ModImage(R.drawable.icon_qtzx,"icon_qtzx")); 
	  imgList.add(new ModImage(R.drawable.icon_qtzx_lzss,"icon_qtzx_lzss"));
	  imgList.add(new ModImage(R.drawable.icon_qtzx_qtzc,"icon_qtzx_qtzc")); 
	  imgList.add(new ModImage(R.drawable.icon_qtzx_ywds,"icon_qtzx_ywds"));
	  imgList.add(new ModImage(R.drawable.icon_repay_in,"icon_repay_in"));
	  imgList.add(new ModImage(R.drawable.icon_repay_out,"icon_repay_out"));
	  imgList.add(new ModImage(R.drawable.icon_rqwl,"icon_rqwl"));
	  imgList.add(new ModImage(R.drawable.icon_rqwl_csjz,"icon_rqwl_csjz"));
	  imgList.add(new ModImage(R.drawable.icon_rqwl_hrqc,"icon_rqwl_hrqc"));
	  imgList.add(new ModImage(R.drawable.icon_rqwl_slqk,"icon_rqwl_slqk"));
	  imgList.add(new ModImage(R.drawable.icon_rqwl_xjjz,"icon_rqwl_xjjz"));
	  imgList.add(new ModImage(R.drawable.icon_spjs,"icon_spjs"));
	  imgList.add(new ModImage(R.drawable.icon_spjs_sgls,"icon_spjs_sgls"));
	  imgList.add(new ModImage(R.drawable.icon_spjs_yjc,"icon_spjs_yjc"));
	  imgList.add(new ModImage(R.drawable.icon_spjs_zwwc,"icon_spjs_zwwc"));
	  imgList.add(new ModImage(R.drawable.icon_transfer_in,"icon_transfer_in"));
	  imgList.add(new ModImage(R.drawable.icon_transfer_out,"icon_transfer_out"));
	  imgList.add(new ModImage(R.drawable.icon_xcjt,"icon_xcjt"));
	  imgList.add(new ModImage(R.drawable.icon_xcjt_dczc,"icon_xcjt_dczc"));
	  imgList.add(new ModImage(R.drawable.icon_xcjt_ggjt,"icon_xcjt_ggjt"));
      imgList.add(new ModImage(R.drawable.icon_xcjt_sjcfy,"icon_xcjt_sjcfy"));
      imgList.add(new ModImage(R.drawable.icon_xxjx,"icon_xxjx"));
	  imgList.add(new ModImage(R.drawable.icon_xxjx_pxjx,"icon_xxjx_pxjx"));
	  imgList.add(new ModImage(R.drawable.icon_xxjx_sbzz,"icon_xxjx_sbzz"));
	  imgList.add(new ModImage(R.drawable.icon_xxjx_smzb,"icon_xxjx_smzb"));
	  imgList.add(new ModImage(R.drawable.icon_xxyl,"icon_xxyl"));
	  imgList.add(new ModImage(R.drawable.icon_xxyl_cwbb,"icon_xxyl_cwbb"));
	  imgList.add(new ModImage(R.drawable.icon_xxyl_fbjh,"icon_xxyl_fbjh"));
	  imgList.add(new ModImage(R.drawable.icon_xxyl_lydj,"icon_xxyl_lydj"));
	  imgList.add(new ModImage(R.drawable.icon_xxyl_xxwl,"icon_xxyl_xxwl"));
	  imgList.add(new ModImage(R.drawable.icon_xxyl_ydjs,"icon_xxyl_ydjs"));
	  imgList.add(new ModImage(R.drawable.icon_yfsp,"icon_yfsp"));
	  imgList.add(new ModImage(R.drawable.icon_yfsp_hzsp,"icon_yfsp_hzsp"));
	  imgList.add(new ModImage(R.drawable.icon_yfsp_xmbb,"icon_yfsp_xmbb"));
	  imgList.add(new ModImage(R.drawable.icon_yfsp_yfkz,"icon_yfsp_yfkz"));
	  imgList.add(new ModImage(R.drawable.icon_ylbj,"icon_ylbj"));
      imgList.add(new ModImage(R.drawable.icon_ylbj_bjf,"icon_ylbj_bjf"));
      imgList.add(new ModImage(R.drawable.icon_ylbj_mrf,"icon_ylbj_mrf"));
	  imgList.add(new ModImage(R.drawable.icon_ylbj_ypf,"icon_ylbj_ypf"));
	  imgList.add(new ModImage(R.drawable.icon_ylbj_zlf,"icon_ylbj_zlf"));
	  imgList.add(new ModImage(R.drawable.icon_zysr,"icon_zysr"));
	  imgList.add(new ModImage(R.drawable.icon_zysr_gzsr,"icon_zysr_gzsr"));
	  imgList.add(new ModImage(R.drawable.icon_zysr_jbsr,"icon_zysr_jbsr"));
	  imgList.add(new ModImage(R.drawable.icon_zysr_jjsr,"icon_zysr_jjsr"));
	  imgList.add(new ModImage(R.drawable.icon_zysr_jzsr,"icon_zysr_jzsr"));
	  imgList.add(new ModImage(R.drawable.icon_zysr_lxsr,"icon_zysr_lxsr"));
	  imgList.add(new ModImage(R.drawable.icon_zysr_tzsr,"icon_zysr_tzsr"));
   }

   // 获取图片的个数
   @Override
   public int getCount()
   {
//	  return mImageIds.length;
	  return imgList.size();
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

	  imageView.setImageResource(imgList.get(position).getId());
      
	  imageView.setTag(imgList.get(position).getName());
      
	  return imageView;
   }
}
