package nro;

import main.GameCanvas;

public final class ServerEffect extends Effect2 {
   private EffectCharPaint eff;
   private int i0;
   private int x;
   private int y;
   private Char c;
   private Mob m;
   private short loopCount = 0;
   private long endTime = 0L;
   private int trans;

   public static void addServerEffect(int id, int cx, int cy, int loopCount) {
      ServerEffect effect2;
      (effect2 = new ServerEffect()).eff = GameScr.efs[id - 1];
      effect2.x = cx;
      effect2.y = cy;
      effect2.loopCount = (short)loopCount;
      Effect2.vEffect2.addElement(effect2);
   }

   public static void addServerEffect(int id, int cx, int cy, int loopCount, int trans) {
      ServerEffect effect2;
      (effect2 = new ServerEffect()).eff = GameScr.efs[id - 1];
      effect2.x = cx;
      effect2.y = cy;
      effect2.loopCount = 1;
      effect2.trans = trans;
      Effect2.vEffect2.addElement(effect2);
   }

   public static void addServerEffect(int id, Mob m, int loopCount) {
      ServerEffect effect2;
      (effect2 = new ServerEffect()).eff = GameScr.efs[id - 1];
      effect2.m = m;
      effect2.loopCount = 1;
      Effect2.vEffect2.addElement(effect2);
   }

   public static void addServerEffect(int id, Char c, int loopCount) {
      ServerEffect effect2;
      (effect2 = new ServerEffect()).eff = GameScr.efs[id - 1];
      effect2.c = c;
      effect2.loopCount = (short)loopCount;
      Effect2.vEffect2.addElement(effect2);
   }

   public final void paint(mGraphics g) {
      if (mGraphics.zoomLevel == 1) {
         GameScr.countEff++;
      }

      if (GameScr.countEff < 11) {
         if (this.c != null) {
            this.x = this.c.cx;
            this.y = this.c.cy + GameCanvas.transY;
         }

         if (this.m != null) {
            this.x = this.m.x;
            this.y = this.m.y + GameCanvas.transY;
         }

         int xValue = this.x + this.eff.arrEfInfo[this.i0].dx;
         int yValue = this.y + this.eff.arrEfInfo[this.i0].dy;
         if (GameCanvas.isPaint(xValue, yValue)) {
            SmallImage.drawSmallImage(
               g, this.eff.arrEfInfo[this.i0].idImg, xValue, yValue, this.trans, 3
            );
         }
      }
   }

   public final void update() {
      if (0L != 0L) {
         this.i0++;
         if (this.i0 >= this.eff.arrEfInfo.length) {
            this.i0 = 0;
         }

         if (System.currentTimeMillis() > 0L) {
            Effect2.vEffect2.removeElement(this);
         }
      } else {
         this.i0++;
         if (this.i0 >= this.eff.arrEfInfo.length) {
            this.loopCount--;
            if (this.loopCount <= 0) {
               Effect2.vEffect2.removeElement(this);
            } else {
               this.i0 = 0;
            }
         }
      }

      if (GameCanvas.gameTick % 11 == 0
         && this.c != null
         && this.c != Char.myCharz()
         && !GameScr.vCharInMap.contains(this.c)) {
         Effect2.vEffect2.removeElement(this);
      }
   }
}
