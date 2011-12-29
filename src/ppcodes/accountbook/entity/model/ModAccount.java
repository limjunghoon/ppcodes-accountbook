package ppcodes.accountbook.entity.model;


public class ModAccount
{
   public ModAccount()
   {
	  
   }
   
   public ModAccount(Integer _UserId,String _AccountName,Integer _UseCount,Integer _Disabled,String _CreateTime,String _ModifyTime)
   {
	 UserId=_UserId;
	 AccountName=_AccountName;
	 UseCount=_UseCount;
	 Disabled=_Disabled;
	 CreateTime=_CreateTime;
	 ModifyTime=_ModifyTime;
   }
   
   private Integer AccountId;
   private Integer UserId;
   private String AccountName;
   private Integer UseCount;
   private Integer Disabled;
   private String CreateTime;
   private String ModifyTime;
   
   public Integer getAccountId()
   {
      return AccountId;
   }
   public void setAccountId(Integer accountId)
   {
      AccountId = accountId;
   }
   public Integer getUserId()
   {
      return UserId;
   }
   public void setUserId(Integer userId)
   {
      UserId = userId;
   }
   public String getAccountName()
   {
      return AccountName;
   }
   public void setAccountName(String accountName)
   {
      AccountName = accountName;
   }
   public Integer getUseCount()
   {
      return UseCount;
   }
   public void setUseCount(Integer useCount)
   {
      UseCount = useCount;
   }
   public Integer getDisabled()
   {
      return Disabled;
   }
   public void setDisabled(Integer disabled)
   {
      Disabled = disabled;
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
  
}
