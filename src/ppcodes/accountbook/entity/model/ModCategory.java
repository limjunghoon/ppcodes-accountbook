package ppcodes.accountbook.entity.model;

public class ModCategory
{
   public ModCategory()
   {
	  
   }
   
   public ModCategory(Integer _ParentCategoryId,Integer _UserId,Integer _InOrOut,String _CategoryName,String _Icon,String _CreateTime,String _ModifyTime,Integer _Disabled,Integer _UseCount)
   {
	  ParentCategoryId=_ParentCategoryId;
	  UserId=_UserId;
	  InOrOut=_InOrOut;
	  CategoryName=_CategoryName;
	  Icon=_Icon;
	  UseCount=_UseCount;
	  Disabled=_Disabled;
	  CreateTime=_CreateTime;
	  ModifyTime=_ModifyTime;
   }
   
   private Integer CategoryId;
   private Integer ParentCategoryId;
   private Integer UserId;
   private Integer InOrOut;
   private String CategoryName;   
   private String Icon;
   private Integer UseCount;
   private Integer Disabled;
   private String CreateTime;
   private String ModifyTime;
   
   public Integer getCategoryId()
   {
      return CategoryId;
   }
   public void setCategoryId(Integer categoryId)
   {
      CategoryId = categoryId;
   }
   public Integer getParentCategoryId()
   {
      return ParentCategoryId;
   }
   public void setParentCategoryId(Integer parentCategoryId)
   {
      ParentCategoryId = parentCategoryId;
   }
   public Integer getUserId()
   {
      return UserId;
   }
   public void setUserId(Integer userId)
   {
      UserId = userId;
   }
   public Integer getInOrOut()
   {
      return InOrOut;
   }
   public void setInOrOut(Integer inOrOut)
   {
      InOrOut = inOrOut;
   }
   public String getCategoryName()
   {
      return CategoryName;
   }
   public void setCategoryName(String categoryName)
   {
      CategoryName = categoryName;
   }
   public Integer getUseCount()
   {
      return UseCount;
   }
   public void setUseCount(Integer useCount)
   {
      UseCount = useCount;
   }
   public String getIcon()
   {
      return Icon;
   }
   public void setIcon(String icon)
   {
      Icon = icon;
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
