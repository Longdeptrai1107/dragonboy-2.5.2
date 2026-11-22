package nro;

public final class Arrow {
   public int life = 0;
   public int ax;
   public int ay;
   private int axTo;
   private int ayTo;
   private int avx;
   private int avy;
   private int adx;
   private int ady;
   private Char charBelong;
   private Arrowpaint arrp = null;
   private static byte[] FRAME = new byte[]{0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};
   private static int[] ARROWINDEX = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
   private static int[] TRANSFORM = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};

   public Arrow(Char charBelongValue, Arrowpaint arrpValue) {
      this.charBelong = charBelongValue;
      this.arrp = arrpValue;
   }

   public final void update() {
      if (this.charBelong.mobFocus != null || this.charBelong.charFocus != null) {
         if (this.charBelong.mobFocus != null) {
            this.axTo = this.charBelong.mobFocus.x;
            this.ayTo = this.charBelong.mobFocus.y - this.charBelong.mobFocus.h / 4;
         } else if (this.charBelong.charFocus != null) {
            this.axTo = this.charBelong.charFocus.cx;
            this.ayTo = this.charBelong.charFocus.cy - this.charBelong.charFocus.ch / 4;
         }

         int value = this.axTo - this.ax;
         int value2 = this.ayTo - this.ay;
         byte byteValue = 4;
         if (value + value2 < 60) {
            byteValue = 3;
         } else if (value + value2 < 30) {
            byteValue = 2;
         }

         if (this.ax != this.axTo) {
            if (value > 0 && value < 5) {
               this.ax = this.axTo;
            } else if (value < 0 && value > -5) {
               this.ax = this.axTo;
            } else {
               this.avx = this.axTo - this.ax << 2;
               this.adx = this.adx + this.avx;
               this.ax = this.ax + (this.adx >> byteValue);
               this.adx &= 15;
            }
         }

         if (this.ay != this.ayTo) {
            if (value2 > 0 && value2 < 5) {
               this.ay = this.ayTo;
            } else if (value2 < 0 && value2 > -5) {
               this.ay = this.ayTo;
            } else {
               this.avy = this.ayTo - this.ay << 2;
               this.ady = this.ady + this.avy;
               this.ay = this.ay + (this.ady >> byteValue);
               this.ady &= 15;
            }
         }

         int value3 = 0;
         int value4 = 0;
         int value5 = 0;
         int value6 = 0;
         if (this.charBelong.mobFocus != null) {
            value3 = this.axTo - this.charBelong.mobFocus.w / 4;
            value5 = this.axTo + this.charBelong.mobFocus.w / 4;
            value4 = this.ayTo - this.charBelong.mobFocus.h / 4;
            value6 = this.ayTo + this.charBelong.mobFocus.h / 4;
         } else if (this.charBelong.charFocus != null) {
            value3 = this.axTo - this.charBelong.charFocus.cw / 4;
            value5 = this.axTo + this.charBelong.charFocus.cw / 4;
            value4 = this.ayTo - this.charBelong.charFocus.ch / 4;
            value6 = this.ayTo + this.charBelong.charFocus.ch / 4;
         }

         if (this.life > 0) {
            this.life--;
         }

         if (this.life != 0
            && (this.ax < value3 || this.ax > value5 || this.ay < value4 || this.ay > value6)) {
            return;
         }
      }

      this.endMe();
   }

   private void endMe() {
      this.charBelong.arr = null;
      this.ax = this.ay = this.axTo = this.ayTo = this.avx = this.avy = this.adx = this.ady = 0;
      this.charBelong.setAttack();
      if (this.charBelong.me) {
         Char.saveLoadPreviousSkill();
      }
   }

   public final void paint(mGraphics g) {
      int deltaX = this.axTo - this.ax;
      int value = this.ayTo - this.ay;
      deltaX = Res.angle(deltaX, -value);
      int aRROWINDEXIndex = 0;

      byte byteValue;
      while (true) {
         if (aRROWINDEXIndex >= ARROWINDEX.length - 1) {
            byteValue = 0;
            break;
         }

         if (deltaX >= ARROWINDEX[aRROWINDEXIndex] && deltaX <= ARROWINDEX[aRROWINDEXIndex + 1]) {
            byteValue = (byte)(aRROWINDEXIndex >= 16 ? 0 : aRROWINDEXIndex);
            break;
         }

         aRROWINDEXIndex++;
      }

      byte fRAMEIndex = byteValue;
      SmallImage.drawSmallImage(
         g, this.arrp.imgId[FRAME[fRAMEIndex]], this.ax, this.ay, TRANSFORM[fRAMEIndex], 3
      );
   }
}
