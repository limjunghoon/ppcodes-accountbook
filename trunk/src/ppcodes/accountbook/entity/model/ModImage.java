package ppcodes.accountbook.entity.model;

public class ModImage
{
   public ModImage(Integer _Id,String _Name)
   {
	  Id=_Id;
	  Name=_Name;
   }
   
   public ModImage()
   {
	  
   }
   
   private Integer Id;
   private String Name;
   public Integer getId()
   {
      return Id;
   }
   public void setId(Integer id)
   {
      Id = id;
   }
   public String getName()
   {
      return Name;
   }
   public void setName(String name)
   {
      Name = name;
   }
}
