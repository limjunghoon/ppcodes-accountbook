package ppcodes.accountbook.entity.model;

public class ModProject
{
   public ModProject()
   {
	  
   }
   
   public ModProject(Integer _UserId,String _ProjectName,Integer _UseCount,Integer _Disabled,String _CreateTime,String _ModifyTime)
   {
	  UserId=_UserId;
	  ProjectName=_ProjectName;
	  UseCount=_UseCount;
	  Disabled=_Disabled;
	  CreateTime=_CreateTime;
	  ModifyTime=_ModifyTime;
   }
   
   private Integer ProjectId;
   private Integer UserId;
   private String ProjectName;
   private Integer UseCount;
   private Integer Disabled;
   private String CreateTime;
   private String ModifyTime;
   
   public Integer getProjectId()
   {
      return ProjectId;
   }
   public void setProjectId(Integer projectId)
   {
      ProjectId = projectId;
   }
   public Integer getUserId()
   {
      return UserId;
   }
   public void setUserId(Integer userId)
   {
      UserId = userId;
   }
   public String getProjectName()
   {
      return ProjectName;
   }
   public void setProjectName(String projectName)
   {
      ProjectName = projectName;
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
