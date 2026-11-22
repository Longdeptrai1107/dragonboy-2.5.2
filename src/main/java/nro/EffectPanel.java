package nro;

public final class EffectPanel extends Effect2 {
   private EffectCharPaint eff;
   private int i0;
   private int x;
   private int y;
   private short loopCount = 0;
   private long endTime = 0L;

   public static void addServerEffect(int id, int cx, int cy, int loopCount) {
      EffectPanel effect3;
      (effect3 = new EffectPanel()).eff = GameScr.efs[id - 1];
      effect3.x = cx;
      effect3.y = cy;
      effect3.loopCount = 2;
      Effect2.vEffect3.addElement(effect3);
   }

   public final void paint(mGraphics g) {
      if (mGraphics.zoomLevel == 1) {
         GameScr.countEff++;
      }

      if (GameScr.countEff < 8) {
         int xValue = this.x + this.eff.arrEfInfo[this.i0].dx;
         int yValue = this.y + this.eff.arrEfInfo[this.i0].dy;
         SmallImage.drawSmallImage(g, this.eff.arrEfInfo[this.i0].idImg, xValue, yValue, 0, 3);
      }
   }

   public final void update() {
      if (0L != 0L) {
         this.i0++;
         if (this.i0 >= this.eff.arrEfInfo.length) {
            this.i0 = 0;
         }

         if (System.currentTimeMillis() > 0L) {
            Effect2.vEffect3.removeElement(this);
         }
      } else {
         this.i0++;
         if (this.i0 >= this.eff.arrEfInfo.length) {
            this.loopCount--;
            if (this.loopCount <= 0) {
               Effect2.vEffect3.removeElement(this);
            } else {
               this.i0 = 0;
            }
         }
      }
   }
}
