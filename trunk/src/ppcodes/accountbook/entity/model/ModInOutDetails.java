package ppcodes.accountbook.entity.model;

public class ModInOutDetails
{
   public ModInOutDetails()
   {
	  
   }
      
   private Integer InOutDetailsId;
   private Integer UserId;
   private Integer CategoryId;
   private String  CategoryName;
   private Integer CategoryChildId;
   private String  CategoryChildName;
   private Integer AccountId;
   private String  AccountName;
   private Integer BusinessId;
   private String  BusinessName;
   private Integer ProjectId;
   private String  ProjectName;
   private Integer InOrOut;
   private Float   Amount;
   private String  Remarks;
   private String  Date;
   private String  CreateTime;
   private String  ModifyTime;
   private Integer Disabled;
   
   public Integer getInOutDetailsId()
   {
      return InOutDetailsId;
   }
   public void setInOutDetailsId(Integer inOutDetailsId)
   {
      InOutDetailsId = inOutDetailsId;
   }
   public Integer getUserId()
   {
      return UserId;
   }
   public void setUserId(Integer userId)
   {
      UserId = userId;
   }
   public Integer getCategoryId()
   {
      return CategoryId;
   }
   public void setCategoryId(Integer categoryId)
   {
      CategoryId = categoryId;
   }
   public Integer getCategoryChildId()
   {
      return CategoryChildId;
   }
   public void setCategoryChildId(Integer categoryChildId)
   {
      CategoryChildId = categoryChildId;
   }
   public Integer getBusinessId()
   {
      return BusinessId;
   }
   public void setBusinessId(Integer businessId)
   {
      BusinessId = businessId;
   }
   public Integer getProjectId()
   {
      return ProjectId;
   }
   public void setProjectId(Integer projectId)
   {
      ProjectId = projectId;
   }
   public Integer getInOrOut()
   {
      return InOrOut;
   }
   public void setInOrOut(Integer inOrOut)
   {
      InOrOut = inOrOut;
   }
   public Float getAmount()
   {
      return Amount;
   }
   public void setAmount(Float amount)
   {
      Amount = amount;
   }
   public String getRemarks()
   {
      return Remarks;
   }
   public void setRemarks(String remarks)
   {
      Remarks = remarks;
   }
   public String getDate()
   {
      return Date;
   }
   public void setDate(String date)
   {
      Date = date;
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
   public String getCategoryName()
   {
      return CategoryName;
   }
   public void setCategoryName(String categoryName)
   {
      CategoryName = categoryName;
   }
   public String getCategoryChildName()
   {
      return CategoryChildName;
   }
   public void setCategoryChildName(String categoryChildName)
   {
      CategoryChildName = categoryChildName;
   }
   public String getBusinessName()
   {
      return BusinessName;
   }
   public void setBusinessName(String businessName)
   {
      BusinessName = businessName;
   }
   public String getProjectName()
   {
      return ProjectName;
   }
   public void setProjectName(String projectName)
   {
      ProjectName = projectName;
   }
   public Integer getAccountId()
   {
      return AccountId;
   }
   public void setAccountId(Integer accountId)
   {
      AccountId = accountId;
   }
   public String getAccountName()
   {
      return AccountName;
   }
   public void setAccountName(String accountName)
   {
      AccountName = accountName;
   }
   
}
