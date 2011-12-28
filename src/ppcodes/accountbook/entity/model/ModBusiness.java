package ppcodes.accountbook.entity.model;

public class ModBusiness
{
   public ModBusiness()
   {
	  
   }
   
 
   
   public ModBusiness(Integer _UserId,String _BusinessName,String _CreateTime,String _ModifyTime,Integer _Disabled,Integer _UseCount)
   {
	  UserId=_UserId;
	  BusinessName=_BusinessName;
	  CreateTime=_CreateTime;
	  ModifyTime=_ModifyTime;
	  Disabled=_Disabled;
	  UseCount=_UseCount;
   }
   
   public Integer getUserId()
   {
      return UserId;
   }
   public void setUserId(Integer userId)
   {
      UserId = userId;
   }
   public Integer getBusinessId()
   {
      return BusinessId;
   }
   public void setBusinessId(Integer businessId)
   {
      BusinessId = businessId;
   }
   public String getBusinessName()
   {
      return BusinessName;
   }
   public void setBusinessName(String businessName)
   {
      BusinessName = businessName;
   }
   public String getCreateTime()
   {
      return CreateTime;
   }
   public void setCreateTime(String createTime)
   {
      CreateTime = createTime;
   }
   public String getModifyTime()
   {
      return ModifyTime;
   }
   public void setModifyTime(String modifyTime)
   {
      ModifyTime = modifyTime;
   }
   public Integer getDisabled()
   {
      return Disabled;
   }
   public void setDisabled(Integer disabled)
   {
      Disabled = disabled;
   }
   public Integer getUseCount()
   {
      return UseCount;
   }
   public void setUseCount(Integer useCount)
   {
      UseCount = useCount;
   }
   private Integer UserId;
   private Integer BusinessId;
   private String BusinessName;
   private String CreateTime;
   private String ModifyTime;
   private Integer Disabled;
   private Integer UseCount;
  

}
