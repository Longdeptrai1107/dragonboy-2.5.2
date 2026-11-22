package nro;

public final class Skill {
   public SkillTemplate template;
   public short skillId;
   public int point;
   public long powRequire;
   public int coolDown;
   public long lastTimeUseThisSkill;
   public int dx;
   public int dy;
   public int manaUse;
   public boolean paintCanNotUseSkill = false;
   public short damage;
   public String moreInfo;
   public short curExp;

   public final String strTimeReplay() {
      if (this.coolDown % 1000 == 0) {
         return String.valueOf(this.coolDown / 1000);
      }

      int value = this.coolDown % 1000;
      return this.coolDown / 1000 + "." + (value % 100 == 0 ? value / 100 : value / 10);
   }
}
