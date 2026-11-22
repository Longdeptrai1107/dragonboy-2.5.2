package nro;

import main.GameCanvas;

public final class MsgDlg extends Dialog {
   private String[] info;
   public boolean isWait;
   private int h;
   private int padLeft;
   private long time = -1L;

   public MsgDlg() {
      this.padLeft = 35;
      if (GameCanvas.w <= 176) {
         this.padLeft = 10;
      }

      if (GameCanvas.w > 320) {
         this.padLeft = 80;
      }
   }

   public final void pleasewait() {
      this.setInfo(mResources.PLEASEWAIT, null, null, null);
      GameCanvas.currentDialog = this;
      this.time = mSystem.currentTimeMillis() + 5000L;
   }

   public final void show() {
      GameCanvas.currentDialog = this;
      this.time = -1L;
   }

   public final void setInfo(String info) {
      this.info = mFont.tahoma_8b.splitFontArray(info, GameCanvas.w - ((this.padLeft << 1) + 20));
      this.h = 80;
      if (this.info.length >= 5) {
         this.h = this.info.length * mFont.tahoma_8b.getHeight() + 20;
      }
   }

   public final void setInfo(String info, Command left, Command center, Command right) {
      this.info = mFont.tahoma_8b.splitFontArray(info, GameCanvas.w - ((this.padLeft << 1) + 20));
      super.left = left;
      super.center = center;
      super.right = right;
      this.h = 80;
      if (this.info.length >= 5) {
         this.h = this.info.length * mFont.tahoma_8b.getHeight() + 20;
      }

      if (GameCanvas.isTouch) {
         if (left != null) {
            super.left.x = GameCanvas.w / 2 - 68 - 5;
            super.left.y = GameCanvas.h - 50;
         }

         if (right != null) {
            super.right.x = GameCanvas.w / 2 + 5;
            super.right.y = GameCanvas.h - 50;
         }

         if (center != null) {
            super.center.x = GameCanvas.w / 2 - 35;
            super.center.y = GameCanvas.h - 50;
         }
      }

      this.isWait = false;
      this.time = -1L;
   }

   public final void paint(mGraphics graphics) {
      graphics.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      if (!LoginScr.isContinueToLogin) {
         int y = GameCanvas.h - this.h - 38;
         int w = GameCanvas.w - (this.padLeft << 1);
         Paint.paintPopUp(this.padLeft, y, w, this.h, graphics);
         int value = y + (this.h - this.info.length * mFont.tahoma_8b.getHeight()) / 2 - 2;
         if (this.isWait) {
            value += 8;
            GameCanvas.paintShukiren(GameCanvas.hw, value - 12, graphics);
         }

         int infoIndex = 0;

         for (int y2 = value; infoIndex < this.info.length; y2 += mFont.tahoma_8b.getHeight()) {
            mFont.tahoma_7b_dark.drawString(graphics, this.info[infoIndex], GameCanvas.hw, y2, 2);
            infoIndex++;
         }

         super.paint(graphics);
      }
   }

   public final void update() {
      super.update();
      if (this.time != -1L && mSystem.currentTimeMillis() > this.time) {
         GameCanvas.endDlg();
      }
   }
}
