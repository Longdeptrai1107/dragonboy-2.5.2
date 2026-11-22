package nro;

import main.GameCanvas;

public final class InfoPhuBan {
   public int type_PB;
   public int maxPoint;
   public int pointTeam1;
   public int pointTeam2;
   public int color_1;
   public int color_2;
   public int maxLife = 1;
   public int lifeTeam1;
   public int lifeTeam2;
   public String nameTeam1;
   public String nameTeam2;
   public short idmapPaint;
   public short timeSecond;
   public long timeStart;

   public InfoPhuBan(int type_PBValue, short idmapPaintValue, String nameTeam1Value, String nameTeam2Value, int maxPointValue, short timeSecondValue) {
      new MyVector("vecInfo chientruong");
      this.type_PB = type_PBValue;
      this.idmapPaint = idmapPaintValue;
      this.nameTeam1 = nameTeam1Value;
      this.nameTeam2 = nameTeam2Value;
      this.timeSecond = timeSecondValue;
      this.timeStart = GameCanvas.timeNow;
      this.maxPoint = maxPointValue;
      if (this.maxPoint <= 0) {
         this.maxPoint = 1;
      }

      this.pointTeam1 = 0;
      this.pointTeam2 = 0;
      this.color_1 = 4;
      this.color_2 = 6;
   }

   public final void updateLife(int type_PB, int lifeTeam1, int lifeTeam2) {
      this.type_PB = type_PB;
      this.lifeTeam1 = lifeTeam1;
      this.lifeTeam2 = lifeTeam2;
   }
}
