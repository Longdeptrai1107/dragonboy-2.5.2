package nro;

public final class ItemTemplate {
   public short id;
   public byte type;
   public byte gender;
   public String name;
   public String description;
   public short iconID;
   public short part;
   public boolean isUpToUp;
   public int strRequire;

   public ItemTemplate(short templateID, byte typeValue, byte genderValue, String nameValue, String descriptionValue, byte level, int strRequireValue, short iconIDValue, short partValue, boolean isUpToUpValue) {
      this.id = templateID;
      this.type = typeValue;
      this.gender = genderValue;
      this.name = nameValue;
      this.name = Res.changeString(this.name);
      this.description = descriptionValue;
      this.description = Res.changeString(this.description);
      this.strRequire = strRequireValue;
      this.iconID = iconIDValue;
      this.part = partValue;
      this.isUpToUp = isUpToUpValue;
   }
}
