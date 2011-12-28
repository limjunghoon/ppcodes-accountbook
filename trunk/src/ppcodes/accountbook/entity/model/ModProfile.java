package ppcodes.accountbook.entity.model;

public class ModProfile
{
   public ModProfile()
   {
	  
   }
   
   public ModProfile(Integer _UserId,Integer _InCategoryId,Integer _OutCategoryId,Integer _ProjectId,Integer _BusinessId,Integer _BudgetId,String _CreateTime,String _ModifyTime)
   {
	  UserId=_UserId;
	  InCategoryId=_InCategoryId;
	  OutCategoryId=_OutCategoryId;
	  ProjectId=_ProjectId;
	  BusinessId=_BusinessId;
	  BudgetId=_BudgetId;
	  CreateTime=_CreateTime;
	  ModifyTime=_ModifyTime;
   }
   
   private Integer id;
   private Integer UserId; 
   private Integer InCategoryId;
   private Integer OutCategoryId;
   private Integer ProjectId;
   private Integer BusinessId;
   private Integer BudgetId;
   private String CreateTime;
   private String ModifyTime;
   private Integer Disabled;
   
   public Integer getDisabled()
   {
      return Disabled;
   }

   public void setDisabled(Integer disabled)
   {
      Disabled = disabled;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public Integer getUserId()
   {
      return UserId;
   }

   public void setUserId(Integer userId)
   {
      UserId = userId;
   }

   public Integer getInCategoryId()
   {
      return InCategoryId;
   }

   public void setInCategoryId(Integer inCategoryId)
   {
      InCategoryId = inCategoryId;
   }

   public Integer getOutCategoryId()
   {
      return OutCategoryId;
   }

   public void setOutCategoryId(Integer outCategoryId)
   {
      OutCategoryId = outCategoryId;
   }

   public Integer getProjectId()
   {
      return ProjectId;
   }

   public void setProjectId(Integer projectId)
   {
      ProjectId = projectId;
   }

   public Integer getBusinessId()
   {
      return BusinessId;
   }

   public void setBusinessId(Integer businessId)
   {
      BusinessId = businessId;
   }

   public Integer getBudgetId()
   {
      return BudgetId;
   }

   public void setBudgetId(Integer budgetId)
   {
      BudgetId = budgetId;
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
