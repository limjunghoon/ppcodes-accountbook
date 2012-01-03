package ppcodes.accountbook.entity.model;

public class ModGvTab
{
   private Integer imgId;
   private String txtName;
   
   public ModGvTab()
   {
	  
   }
   public ModGvTab(Integer _imgId,String _txtName)
   {
	  imgId=_imgId;
	  txtName=_txtName;
   }
   public Integer getImgId()
   {
      return imgId;
   }
   public void setImgId(Integer imgId)
   {
      this.imgId = imgId;
   }
   public String getTxtName()
   {
      return txtName;
   }
   public void setTxtName(String txtName)
   {
      this.txtName = txtName;
   }
}
