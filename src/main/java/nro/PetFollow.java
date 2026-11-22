package nro;

import main.GameCanvas;

public final class PetFollow {
   public short smallID;
   public int dir;
   public int f;
   public int cmtoY;
   public int cmy;
   private int cmdy;
   private int cmvy;
   public int cmtoX;
   public int cmx;
   private int cmdx;
   private int cmvx;
   public int fimg;
   public int wimg;
   public int himg;
   private int[] frame;
   private int count;

   public final void SetImg(int fimg, int[] frameNew, int wimg, int himg) {
      if (fimg > 0) {
         this.fimg = fimg;
         this.frame = frameNew;
         this.wimg = wimg;
         this.himg = himg;
      }
   }

   public PetFollow() {
      new Info();
      this.fimg = -1;
      this.wimg = 0;
      this.himg = 0;
      this.frame = new int[]{0, 1, 2, 1};
      this.f = Res.random(0, 3);
   }

   public final void update() {
      PetFollow petFollow = this;
      if (this.cmy != petFollow.cmtoY) {
         petFollow.cmvy = petFollow.cmtoY - petFollow.cmy << 2;
         petFollow.cmdy = petFollow.cmdy + petFollow.cmvy;
         petFollow.cmy = petFollow.cmy + (petFollow.cmdy >> 4);
         petFollow.cmdy &= 15;
      }

      if (petFollow.cmx != petFollow.cmtoX) {
         petFollow.cmvx = petFollow.cmtoX - petFollow.cmx << 2;
         petFollow.cmdx = petFollow.cmdx + petFollow.cmvx;
         petFollow.cmx = petFollow.cmx + (petFollow.cmdx >> 4);
         petFollow.cmdx &= 15;
      }

      if (GameCanvas.gameTick % 3 == 0) {
         this.f = this.frame[this.count];
         this.count++;
      }

      if (this.count >= this.frame.length) {
         this.count = 0;
      }
   }

   public final void remove() {
      ServerEffect.addServerEffect(60, this.cmx, this.cmy + 3 + (GameCanvas.gameTick % 10 > 5 ? 1 : 0), 1);
   }
}
