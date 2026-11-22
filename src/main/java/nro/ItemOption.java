package nro;

public final class ItemOption {
   public int param;
   public byte activeCard;
   public ItemOptionTemplate optionTemplate;

   public ItemOption() {
   }

   public ItemOption(int optionTemplateId, int paramValue) {
      if (optionTemplateId == 22) {
         optionTemplateId = 6;
         paramValue *= 1000;
      }

      if (optionTemplateId == 23) {
         optionTemplateId = 7;
         paramValue *= 1000;
      }

      this.param = paramValue;
      this.optionTemplate = GameScr.gI().iOptionTemplates[optionTemplateId];
   }

   public final String getOptionString() {
      return Firework.replaceAllLiteral(this.optionTemplate.name, "#", String.valueOf(this.param));
   }

   public final String getOptiongColor() {
      return Firework.replaceAllLiteral(this.optionTemplate.name, "$", "");
   }
}
