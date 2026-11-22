package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class TransportScr extends mScreen implements IActionListener {
   private static TransportScr instance;
   private static Image ship = mSystem.loadImage("/mainImage/myTexture2dfutherShip.png");
   private static Image taungam = mSystem.loadImage("/mainImage/taungam.png");
   public byte type;
   private int speed = 5;
   private int[] posX;
   private int[] posY;
   private int[] posX2;
   private int[] posY2;
   private int cmx;
   private int n = 20;
   public short time;
   public short maxTime;
   public long last;
   public long curr;
   private boolean isSpeed;
   private boolean transNow;
   private int currSpeed;

   public TransportScr() {
      this.posX = new int[this.n];
      this.posY = new int[this.n];

      for (int posXIndex = 0; posXIndex < this.n; posXIndex++) {
         this.posX[posXIndex] = Res.random(0, GameCanvas.w);
         this.posY[posXIndex] = posXIndex * (GameCanvas.h / this.n);
      }

      this.posX2 = new int[this.n];
      this.posY2 = new int[this.n];

      for (int posX2Index = 0; posX2Index < this.n; posX2Index++) {
         this.posX2[posX2Index] = Res.random(0, GameCanvas.w);
         this.posY2[posX2Index] = posX2Index * (GameCanvas.h / this.n);
      }
   }

   public static TransportScr gI() {
      if (instance == null) {
         instance = new TransportScr();
      }

      return instance;
   }

   public final void switchToMe() {
      this.isSpeed = false;
      this.transNow = false;
      if (Char.myCharz().checkLuong() > 0 && this.type == 0) {
         super.center = new Command(mResources.faster, this, 1, null);
      } else {
         super.center = null;
      }

      this.currSpeed = 0;
      super.switchToMe();
   }

   public final void paint(mGraphics g) {
      g.setColor(this.type == 0 ? 0 : 3056895);
      g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);

      for (int posXIndex = 0; posXIndex < this.n; posXIndex++) {
         g.setColor(this.type == 0 ? 14802654 : 11140863);
         g.fillRect(this.posX[posXIndex], this.posY[posXIndex], 10, 2);
      }

      if (this.type == 0) {
         g.drawRegion(ship, 0, 0, 72, 95, 7, this.cmx + this.currSpeed, GameCanvas.h / 2, 3);
      }

      if (this.type == 1) {
         g.drawRegion(taungam, 0, 0, 144, 79, 2, this.cmx + this.currSpeed, GameCanvas.h / 2, 3);
      }

      for (int posX2Index = 0; posX2Index < this.n; posX2Index++) {
         g.setColor(this.type == 0 ? 14935011 : 7536127);
         g.fillRect(this.posX2[posX2Index], this.posY2[posX2Index], 18, 3);
      }

      super.paint(g);
   }

   public final void update() {
      if (this.type == 0) {
         if (!this.isSpeed) {
            this.currSpeed = GameCanvas.w / 2 * this.time / this.maxTime;
         }
      } else {
         this.currSpeed += 2;
      }

      Controller.isStopReadMessage = false;
      this.cmx = (((GameCanvas.w / 2 + this.cmx) / 2 + this.cmx) / 2 + this.cmx) / 2;
      if (this.type == 1) {
         this.cmx = 0;
      }

      for (int posXIndex = 0; posXIndex < this.n; posXIndex++) {
         this.posX[posXIndex] = this.posX[posXIndex] - this.speed / 2;
         if (this.posX[posXIndex] < -20) {
            this.posX[posXIndex] = GameCanvas.w;
         }
      }

      for (int posX2Index = 0; posX2Index < this.n; posX2Index++) {
         this.posX2[posX2Index] = this.posX2[posX2Index] - this.speed;
         if (this.posX2[posX2Index] < -20) {
            this.posX2[posX2Index] = GameCanvas.w;
         }
      }

      if (GameCanvas.gameTick % 3 == 0) {
         this.speed = this.speed + (this.isSpeed ? 2 : 1);
      }

      if (this.speed > (this.isSpeed ? 80 : 25)) {
         this.speed = this.isSpeed ? 80 : 25;
      }

      this.curr = System.currentTimeMillis();
      if (this.curr - this.last >= 1000L) {
         this.time++;
         this.last = this.curr;
      }

      if (this.isSpeed) {
         this.currSpeed += 3;
      }

      if (this.currSpeed >= GameCanvas.w / 2 + 30 && !this.transNow) {
         this.transNow = true;
         Service.gI().transportNow();
      }

      super.update();
   }

   public final void updateKey() {
      super.updateKey();
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 1) {
         GameCanvas.startYesNoDlg(
            mResources.fasterQuestion,
            new Command(mResources.YES, this, 2, null),
            new Command(mResources.NO, this, 3, null)
         );
      }

      if (idAction == 2 && Char.myCharz().checkLuong() > 0) {
         this.isSpeed = true;
         GameCanvas.endDlg();
         super.center = null;
      }

      if (idAction == 3) {
         GameCanvas.endDlg();
      }
   }
}
