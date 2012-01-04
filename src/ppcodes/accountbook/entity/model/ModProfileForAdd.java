package ppcodes.accountbook.entity.model;

public class ModProfileForAdd
{
   public ModProfileForAdd()
   {
	  
   }
   
   public ModProfileForAdd(Integer _UserId,Integer _CategoryId,String _CategoryName,
                           Integer _ParentCategoryId, String _ParentCategoryName, 
                           Integer _ProjectId, String _ProjectName,
                           Integer _BusinessId,String _BusinessName,
                           Integer _AccountId, String _AccountName,
                           String _CreateTime,String _ModifyTime)
   {
	  UserId=_UserId;
	  CategoryId=_CategoryId;
	  CategoryName=_CategoryName;
	  ParentCategoryId=_ParentCategoryId;
	  parentCategoryName=_ParentCategoryName;
	  ProjectId=_ProjectId;
	  ProjectName=_ProjectName;
	  BusinessId=_BusinessId;
	  BusinessName=_BusinessName;
	  AccountId=_AccountId;
	  AccountName=_AccountName;
	  CreateTime=_CreateTime;
	  ModifyTime=_ModifyTime;
   }
   
   private Integer id;
   private Integer UserId; 
   private Integer CategoryId;
   private String  CategoryName;
   private Integer ParentCategoryId;   
   private String  parentCategoryName;
   private Integer ProjectId;
   private String  ProjectName;
   private Integer BusinessId;
   private String  BusinessName;
   private Integer AccountId;
   private String  AccountName;
   private String CreateTime;
   private String ModifyTime;
   private Integer Disabled;
//   private boolean isCategory=false;
//   private Integer CategoryType;
   
   public Integer getCategoryId()
   {
      return CategoryId;
   }

   public void setCategoryId(Integer categoryId)
   {
      CategoryId = categoryId;
   }

   public String getCategoryName()
   {
      return CategoryName;
   }

   public void setCategoryName(String categoryName)
   {
      CategoryName = categoryName;
   }

   public Integer getParentCategoryId()
   {
      return ParentCategoryId;
   }

   public void setParentCategoryId(Integer parentCategoryId)
   {
      ParentCategoryId = parentCategoryId;
   }

   public String getParentCategoryName()
   {
      return parentCategoryName;
   }

   public void setParentCategoryName(String parentCategoryName)
   {
      this.parentCategoryName = parentCategoryName;
   }

   
//   /**
//    * 1为In2为out
//    * @return
//    */
//   public Integer getCategoryType()
//   {
//      return CategoryType;
//   }
//   /**
//    *  1为In2为out
//    * @param categoryType
//    */
//   public void setCategoryType(Integer categoryType)
//   {
//      CategoryType = categoryType;
//   }
//
//   public boolean isCategory()
//   {
//      return isCategory;
//   }
//
//   public void setCategory(boolean _isCategory)
//   {
//	  isCategory = _isCategory;
//   }



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

   public Integer getAccountId()
   {
      return AccountId;
   }

   public void setAccountId(Integer accountId)
   {
	  AccountId = accountId;
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

   public String getProjectName()
   {
      return ProjectName;
   }

   public void setProjectName(String projectName)
   {
      ProjectName = projectName;
   }

   public String getBusinessName()
   {
      return BusinessName;
   }

   public void setBusinessName(String businessName)
   {
      BusinessName = businessName;
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
