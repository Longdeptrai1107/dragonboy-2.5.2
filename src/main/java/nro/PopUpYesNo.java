package nro;

import main.GameCanvas;

public final class PopUpYesNo implements IActionListener {
   public Command cmdYes;
   private Command cmdNo;
   private String[] info;
   private int X;
   private int Y;
   private int W = 120;
   private int H;
   private int dem;
   private long last;
   private long curr;

   public final void setPopUp(String info, Command cmdYes, Command cmdNo) {
      this.info = new String[]{info};
      this.H = 29;
      this.cmdYes = cmdYes;
      this.cmdNo = cmdNo;
      this.cmdYes.img = this.cmdNo.img = GameScr.imgNut;
      this.cmdYes.imgFocus = this.cmdNo.imgFocus = GameScr.imgNutF;
      this.cmdYes.w = mGraphics.getImageWidth(cmdYes.img);
      this.cmdNo.w = mGraphics.getImageWidth(cmdYes.img);
      this.cmdYes.h = mGraphics.getImageHeight(cmdYes.img);
      this.cmdNo.h = mGraphics.getImageHeight(cmdYes.img);
      this.last = System.currentTimeMillis();
      this.dem = this.info[0].length() / 3;
      if (this.dem < 15) {
         this.dem = 15;
      }

      TextInfo.reset();
   }

   public final void paint(mGraphics g) {
      PopUp.paintPopUp(
         g,
         this.X,
         this.Y,
         this.W,
         this.H + (!GameCanvas.isTouch ? 10 : 0),
         16777215,
         false
      );
      if (this.info != null) {
         TextInfo.paint(
            g,
            this.info[0],
            this.X + 5,
            this.Y + this.H / 2 - (GameCanvas.isTouch ? 4 : 6),
            this.W - 10,
            this.H,
            mFont.tahoma_7
         );
         if (GameCanvas.isTouch) {
            this.cmdYes.paint(g);
            mFont.tahoma_7_yellow
               .drawString(
                  g,
                  String.valueOf(this.dem),
                  this.cmdYes.x + this.cmdYes.w / 2,
                  this.cmdYes.y + this.cmdYes.h + 5,
                  2,
                  mFont.tahoma_7_grey
               );
            return;
         }

         if (TField.isQwerty) {
            mFont.tahoma_7b_blue
               .drawString(
                  g,
                  mResources.do_accept_qwerty + this.dem + ")",
                  this.X + this.W / 2,
                  this.Y + this.H,
                  2
               );
            return;
         }

         mFont.tahoma_7b_blue
            .drawString(
               g,
               mResources.do_accept + this.dem + ")",
               this.X + this.W / 2,
               this.Y + this.H,
               2
            );
      }
   }

   public final void update() {
      if (this.info != null) {
         this.X = GameCanvas.w - 5 - this.W;
         this.Y = 45;
         if (GameCanvas.w - 50 > 155 + this.W) {
            this.X = GameCanvas.w - 55 - this.W;
            this.Y = 5;
         }

         this.cmdYes.x = this.X - 35;
         this.cmdYes.y = this.Y;
         this.curr = System.currentTimeMillis();
         Res.outz("curr - last= " + (this.curr - this.last));
         if (this.curr - this.last >= 1000L) {
            this.last = System.currentTimeMillis();
            this.dem--;
         }

         if (this.dem == 0) {
            GameScr.gI().popUpYesNo = null;
         }
      }
   }

   public final void perform(int idAction, Object p) {
   }
}
