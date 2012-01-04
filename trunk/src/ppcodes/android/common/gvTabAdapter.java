package ppcodes.android.common;

import java.util.List;

import ppcodes.accountbook.app.R;
import ppcodes.accountbook.entity.model.ModGvTab;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class gvTabAdapter extends BaseAdapter
{
   private Context mContext;
   private RelativeLayout[] lays;
   
   public gvTabAdapter(Context c, List<ModGvTab> list,int width,int height)
   {
	  mContext=c;
	  lays=new RelativeLayout[list.size()];
	  LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  
	  for (int i = 0; i < list.size(); i++)
	  {
		 lays[i]=(RelativeLayout)inflater.inflate(R.layout.gridview_newrec_tab, null);
//		 lays[i].setLayoutParams(new GridView.LayoutParams(width, height));
		 ImageView img=(ImageView)lays[i].findViewById(R.id.img_gridview_newrec_tab);
		 img.setImageResource(list.get(i).getImgId());
		 
		 TextView txt=(TextView)lays[i].findViewById(R.id.txt_gridview_newrec_tab);		 
	     txt.setText(list.get(i).getTxtName());
	  }
   }
   
   @Override
   public int getCount()
   {
	  // TODO Auto-generated method stub
	  return lays.length;
   }

   @Override
   public Object getItem(int position)
   {
	  // TODO Auto-generated method stub
	  return position;
   }

   @Override
   public long getItemId(int position)
   {
	  // TODO Auto-generated method stub
	  return position;
   }

   public void SetFocus(int index)
   {
	  for (int i = 0; i < lays.length; i++)
	  {
		 if(i!=index)
		 {
			lays[i].setBackgroundColor(R.color.LightBrown); 
		 }
	  }
	  lays[index].setBackgroundColor(android.R.color.white);
   }
   
   @Override
   public View getView(int position, View convertView, ViewGroup parent)
   {
	  // TODO Auto-generated method stub
	  RelativeLayout lay;
	  if(convertView==null)
	  {
		 lay=lays[position];
	  }
	  else 
	  {
	     lay=(RelativeLayout)convertView;	
	  }
	  return lay;
   }
}
