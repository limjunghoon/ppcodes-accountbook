package ppcodes.accountbook.entity.model;



public class ModUserInfo
{
  private Integer UserId;
  private String UserName;
  private String UserKey;
  private String CreateTime;
  private String ModifyTime;
  private Integer Disabled;
  
  public ModUserInfo()
  {
	
  }
  
  public ModUserInfo(String _UserName,String _UserKey,String _CreateTime,String _ModifyTime,Integer _Disabled)
  {
	UserName=_UserName;
    UserKey=_UserKey;
    CreateTime=_CreateTime;
	ModifyTime=_ModifyTime;
	Disabled=_Disabled;
  }
  
  public Integer getUserId()
  {
    return UserId;
  }
  public void setUserId(Integer userId)
  {
    this.UserId = userId;
  }
  
  public String getUserName()
  {
    return UserName;
  }
  public void setUserName(String userName)
  {
    UserName = userName;
  }
  public String getUserKey()
  {
    return UserKey;
  }
  public void setUserKey(String userKey)
  {
    UserKey = userKey;
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
}
