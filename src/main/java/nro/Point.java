package nro;

public final class Point {
   public byte type;
   public int x;
   public int y;
   public int vMax;
   public int vx;
   public int vy;
   public int toX;
   public int toY;
   public int f;
   public int fRe;
   public int frame;
   public int id;
   public boolean isRemove = false;
   public boolean isPaint = false;
   public boolean isChange = false;
   public FrameImage fraImgEff;
   public FrameImage fraImgEff_2;
   private byte[] mpaintone_Arrow = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
   public byte[] mImageArrow = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
   public byte[] mXoayArrow = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};

   public final void update() {
      this.f++;
      this.x = this.x + this.vx;
      this.y = this.y + this.vy;
   }

   public final int setFrameAngle(int goc) {
      int mpaintone_ArrowIndex;
      if (goc > 15 && goc <= 345) {
         if ((mpaintone_ArrowIndex = (goc - 15) / 15 + 1) > 24) {
            mpaintone_ArrowIndex = 24;
         }

         mpaintone_ArrowIndex = this.mpaintone_Arrow[mpaintone_ArrowIndex];
      } else {
         mpaintone_ArrowIndex = 12;
      }

      return mpaintone_ArrowIndex;
   }
}
