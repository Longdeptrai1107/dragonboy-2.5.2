package nro;

import main.GameCanvas;

public final class ClientInput extends mScreen implements IActionListener {
   private static ClientInput instance;
   public TField[] tf;
   private int x;
   private int y;
   private int w;
   private int h;
   private String[] strPaint;
   private String inputTitle;
   private ChatTextField singleFieldChatInput;
   private int focus;
   private int nTf;

   public static ClientInput gI() {
      if (instance == null) {
         instance = new ClientInput();
      }

      return instance;
   }

   public final void switchToMe() {
      if (mSystem.clientType == 5 && this.tf.length == 1 || mSystem.clientType == 3 && this.tf.length == 1) {
         this.singleFieldChatInput = new ChatTextField();
         this.singleFieldChatInput.strChat = this.inputTitle;
         this.singleFieldChatInput.tfChat.name = this.tf[0].title;
         this.singleFieldChatInput.to = "";
         this.singleFieldChatInput.isShow = true;
         this.singleFieldChatInput.tfChat.setIputType(0);
         if (GameCanvas.isTouch) {
            TField.openTouchKeyboard();
            return;
         }
      } else {
         this.focus = 0;
         super.switchToMe();
      }
   }

   public final void setInput(int type, String title) {
      this.nTf = type;
      String inputTitleValue = title;
      ClientInput clientInput = this;
      this.inputTitle = inputTitleValue;
      clientInput.w = GameCanvas.w - 20;
      if (clientInput.w > 320) {
         clientInput.w = 320;
      }

      Res.outz("title= " + inputTitleValue);
      clientInput.strPaint = mFont.tahoma_7b_dark.splitFontArray(inputTitleValue, clientInput.w - 20);
      clientInput.x = (GameCanvas.w - clientInput.w) / 2;
      clientInput.tf = new TField[clientInput.nTf];
      clientInput.h = clientInput.tf.length * 35 + (clientInput.strPaint.length - 1) * 20 + 40;
      clientInput.y = GameCanvas.h - clientInput.h - 40 - (clientInput.strPaint.length - 1) * 20;

      for (int tfIndex = 0; tfIndex < clientInput.tf.length; tfIndex++) {
         clientInput.tf[tfIndex] = new TField();
         clientInput.tf[tfIndex].name = "";
         clientInput.tf[tfIndex].x = clientInput.x + 10;
         clientInput.tf[tfIndex].y = clientInput.y + 35 + (clientInput.strPaint.length - 1) * 20 + tfIndex * 35;
         clientInput.tf[tfIndex].width = clientInput.w - 20;
         clientInput.tf[tfIndex].height = mScreen.ITEM_HEIGHT + 2;
         if (GameCanvas.isTouch) {
            clientInput.tf[0].isFocus = false;
         } else {
            clientInput.tf[0].isFocus = true;
         }

         if (!GameCanvas.isTouch) {
            clientInput.right = clientInput.tf[0].cmdClear;
         }
      }

      clientInput.left = new Command(mResources.CLOSE, clientInput, 1, null);
      clientInput.center = new Command(mResources.OK, clientInput, 2, null);
      if (GameCanvas.isTouch) {
         clientInput.center.x = GameCanvas.w / 2 + 18;
         clientInput.left.x = GameCanvas.w / 2 - 85;
         clientInput.center.y = clientInput.left.y = clientInput.y + clientInput.h + 5;
      }

      this.switchToMe();
   }

   public final void paint(mGraphics g) {
      GameScr.gI().paint(g);
      PopUp.paintPopUp(g, this.x, this.y, this.w, this.h, -1, true);

      for (int strPaintIndex = 0; strPaintIndex < this.strPaint.length; strPaintIndex++) {
         mFont.tahoma_7b_green2
            .drawString(g, this.strPaint[strPaintIndex], GameCanvas.w / 2, this.y + 15 + strPaintIndex * 20, 2);
      }

      for (int tfIndex = 0; tfIndex < this.tf.length; tfIndex++) {
         this.tf[tfIndex].paint(g);
      }

      super.paint(g);
   }

   public final void update() {
      GameScr.gI().update();

      for (int tfIndex = 0; tfIndex < this.tf.length; tfIndex++) {
         this.tf[tfIndex].update();
      }
   }

   public final void keyPress(int keyCode) {
      for (int tfIndex = 0; tfIndex < this.tf.length; tfIndex++) {
         if (this.tf[tfIndex].isFocus) {
            this.tf[tfIndex].keyPressed(keyCode);
            break;
         }
      }

      super.keyPress(keyCode);
   }

   public final void updateKey() {
      if (GameCanvas.keyPressed[2]) {
         this.focus--;
         if (this.focus < 0) {
            this.focus = this.tf.length - 1;
         }
      } else if (GameCanvas.keyPressed[8]) {
         this.focus++;
         if (this.focus > this.tf.length - 1) {
            this.focus = 0;
         }
      }

      if (GameCanvas.keyPressed[2] || GameCanvas.keyPressed[8]) {
         GameCanvas.clearKeyPressed();

         for (int focusValue = 0; focusValue < this.tf.length; focusValue++) {
            if (this.focus == focusValue) {
               this.tf[focusValue].isFocus = true;
               if (!GameCanvas.isTouch) {
                  super.right = this.tf[focusValue].cmdClear;
               }
            } else {
               this.tf[focusValue].isFocus = false;
            }

            if (GameCanvas.isPointerJustRelease
               && GameCanvas.isPointerHoldIn(
                  this.tf[focusValue].x,
                  this.tf[focusValue].y,
                  this.tf[focusValue].width,
                  this.tf[focusValue].height
               )) {
               this.focus = focusValue;
               break;
            }
         }
      }

      super.updateKey();
      GameCanvas.clearKeyPressed();
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 1) {
         GameScr.instance.switchToMe();
         instance = null;
      }

      if (idAction == 2) {
         if (mSystem.clientType == 5 && this.tf.length == 1 || mSystem.clientType == 3 && this.tf.length == 1) {
            if (this.singleFieldChatInput.tfChat.getText() != null && !this.singleFieldChatInput.tfChat.getText().equals("")) {
               this.tf[0].setText(this.singleFieldChatInput.tfChat.getText());
               Service.gI().sendClientInput(this.tf);
               GameScr.instance.switchToMe();
               return;
            }

            GameCanvas.startOKDlg(mResources.vuilongnhapduthongtin);
            return;
         }

         for (int tfIndex = 0; tfIndex < this.tf.length; tfIndex++) {
            if (this.tf[tfIndex].getText() == null || this.tf[tfIndex].getText().equals("")) {
               GameCanvas.startOKDlg(mResources.vuilongnhapduthongtin);
               return;
            }
         }

         Service.gI().sendClientInput(this.tf);
         GameScr.instance.switchToMe();
      }
   }
}
