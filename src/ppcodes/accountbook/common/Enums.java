package ppcodes.accountbook.common;

import android.app.Application;


public  class Enums
{
   Application application;
  /**
   * 基本设置的种类 Incoming收入 Payout支出 Account账户 Project项目 Business商家 DataManage数据管理
   * OtherSetting其他设置 About关于
   * 
   * @author lichen
   */
  public enum ItemType
  {
	Incoming(0), 
	Payout(1), 
	Account(2), 
	Project(3), 
	Business(4), 
	DataManage(5), 
	OtherSetting(6), 
	About(7);

	//构造器
	ItemType(int value)
	{
	  this.value = value;
	}
	private final int value;
	public int getValue()
	{
	  return value;
	}
	
	public String getChineseName()
	{
	   switch(value)
	   {
		  case 0:
			 return "收入类型";
		  case 1:
			 return "支出类型";
		  case 2:
			 return "账户";
		  case 3:
			 return "项目";
		  case 4:
			 return "商家";
		  case 5:
			 return "数据管理";
		  case 6:
			 return "其他设置";
		  case 7:
			 return "关于";
		  default:
			return "";
	   }
	}
  }
  public static String ItemTypeName="ItemTypeName";
  public static String ItemTypeValue="ItemTypeValue";
}
