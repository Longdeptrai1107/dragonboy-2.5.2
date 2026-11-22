package nro;

import main.GameCanvas;

public abstract class Dialog {
   public Command left;
   public Command center;
   public Command right;

   public void paint(mGraphics graphics) {
      graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
      graphics.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      Paint.paintTabSoft();
      Paint.paintCmdBar(graphics, this.left, this.center, this.right);
   }

   public void keyPress(int keyCode) {
      switch (keyCode) {
         case -39:
         case -2:
            GameCanvas.keyHold[8] = true;
            GameCanvas.keyPressed[8] = true;
            return;
         case -38:
         case -1:
            GameCanvas.keyHold[2] = true;
            GameCanvas.keyPressed[2] = true;
            return;
         case -27:
            return;
         case -22:
         case -7:
            GameCanvas.keyHold[13] = true;
            GameCanvas.keyPressed[13] = true;
            return;
         case -21:
         case -6:
            GameCanvas.keyHold[12] = true;
            GameCanvas.keyPressed[12] = true;
            return;
         case -5:
         case 10:
            GameCanvas.keyHold[5] = true;
            GameCanvas.keyPressed[5] = true;
            return;
      }
   }

   public void update() {
      if (this.center != null && (GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(this.center))) {
         GameCanvas.keyPressed[5] = false;
         GameCanvas.isPointerClick = false;
         mScreen.keyTouch = -1;
         GameCanvas.isPointerJustRelease = false;
         if (this.center != null) {
            this.center.performAction();
         }

         mScreen.keyTouch = -1;
      }

      if (this.left != null && (GameCanvas.keyPressed[12] || mScreen.getCmdPointerLast(this.left))) {
         GameCanvas.keyPressed[12] = false;
         GameCanvas.isPointerClick = false;
         mScreen.keyTouch = -1;
         GameCanvas.isPointerJustRelease = false;
         if (this.left != null) {
            this.left.performAction();
         }

         mScreen.keyTouch = -1;
      }

      if (this.right != null && (GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(this.right))) {
         GameCanvas.keyPressed[13] = false;
         GameCanvas.isPointerClick = false;
         GameCanvas.isPointerJustRelease = false;
         mScreen.keyTouch = -1;
         if (this.right != null) {
            this.right.performAction();
         }

         mScreen.keyTouch = -1;
      }

      GameCanvas.clearKeyPressed();
      GameCanvas.clearKeyHold();
   }
}
