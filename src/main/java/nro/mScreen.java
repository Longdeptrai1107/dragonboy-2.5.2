package nro;

import main.GameCanvas;

public class mScreen {
   public Command left;
   public Command center;
   public Command right;
   public static int ITEM_HEIGHT;
   public static int cmdW = 68;
   public static int cmdH = 26;
   public static int keyTouch = -1;

   public void switchToMe() {
      GameCanvas.clearKeyPressed();
      GameCanvas.clearKeyHold();
      if (GameCanvas.currentScreen != null) {
         ;
      }

      GameCanvas.currentScreen = this;
      GameCanvas.instance.setFullScreenMode(true);
   }

   public void keyPress(int keyCode) {
   }

   public void update() {
   }

   public void updateKey() {
      if (GameCanvas.keyPressed[5] || getCmdPointerLast(GameCanvas.currentScreen.center)) {
         GameCanvas.keyPressed[5] = false;
         keyTouch = -1;
         GameCanvas.isPointerJustRelease = false;
         if (this.center != null) {
            this.center.performAction();
         }
      }

      if (GameCanvas.keyPressed[12] || getCmdPointerLast(GameCanvas.currentScreen.left)) {
         GameCanvas.keyPressed[12] = false;
         keyTouch = -1;
         GameCanvas.isPointerJustRelease = false;
         if (ChatTextField.gI().isShow) {
            if (ChatTextField.gI().left != null) {
               ChatTextField.gI().left.performAction();
            }
         } else if (this.left != null) {
            this.left.performAction();
         }
      }

      if (GameCanvas.keyPressed[13] || getCmdPointerLast(GameCanvas.currentScreen.right)) {
         GameCanvas.keyPressed[13] = false;
         keyTouch = -1;
         GameCanvas.isPointerJustRelease = false;
         if (ChatTextField.gI().isShow) {
            if (ChatTextField.gI().right != null) {
               ChatTextField.gI().right.performAction();
               return;
            }
         } else if (this.right != null) {
            this.right.performAction();
         }
      }
   }

   public static boolean getCmdPointerLast(Command cmd) {
      if (cmd == null) {
         return false;
      }

      if (cmd.x >= 0 && cmd.y != 0) {
         return cmd.isPointerPressInside();
      }

      if (GameCanvas.currentDialog != null) {
         if (GameCanvas.currentDialog.center != null
            && GameCanvas.isPointerHoldIn(
               GameCanvas.w - cmdW >> 1,
               GameCanvas.h - cmdH - 5,
               cmdW,
               cmdH + 10
            )) {
            keyTouch = 1;
            if (cmd == GameCanvas.currentDialog.center && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
               return true;
            }
         }

         if (GameCanvas.currentDialog.left != null
            && GameCanvas.isPointerHoldIn(0, GameCanvas.h - cmdH - 5, cmdW, cmdH + 10)) {
            keyTouch = 0;
            if (cmd == GameCanvas.currentDialog.left && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
               return true;
            }
         }

         if (GameCanvas.currentDialog.right != null
            && GameCanvas.isPointerHoldIn(
               GameCanvas.w - cmdW, GameCanvas.h - cmdH - 5, cmdW, cmdH + 10
            )) {
            keyTouch = 2;
            if ((cmd == GameCanvas.currentDialog.right || cmd == ChatTextField.gI().right)
               && GameCanvas.isPointerClick
               && GameCanvas.isPointerJustRelease) {
               return true;
            }
         }
      } else {
         if (cmd == GameCanvas.currentScreen.left
            && GameCanvas.isPointerHoldIn(0, GameCanvas.h - cmdH - 5, cmdW, cmdH + 10)) {
            keyTouch = 0;
            if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
               return true;
            }
         }

         if (cmd == GameCanvas.currentScreen.right
            && GameCanvas.isPointerHoldIn(
               GameCanvas.w - cmdW, GameCanvas.h - cmdH - 5, cmdW, cmdH + 10
            )) {
            keyTouch = 2;
            if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
               return true;
            }
         }

         if ((cmd == GameCanvas.currentScreen.center || ChatPopup.currChatPopup != null)
            && GameCanvas.isPointerHoldIn(
               GameCanvas.w - cmdW >> 1,
               GameCanvas.h - cmdH - 5,
               cmdW,
               cmdH + 10
            )) {
            keyTouch = 1;
            if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
               return true;
            }
         }
      }

      return false;
   }

   public void paint(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h + 1);
      if (!ChatTextField.gI().isShow && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu) {
         Paint.paintCmdBar(g, this.left, this.center, this.right);
      }
   }
}
