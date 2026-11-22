package nro;

import main.GameCanvas;
import main.GameMidlet;

public final class MoneyCharge extends mScreen implements IActionListener {
   public static MoneyCharge instance;
   private TField tfSerial;
   private TField tfCode;
   private int x;
   private int y;
   private int w = GameCanvas.w - 20;
   private int h;
   private String[] strPaint;
   private int focus;

   public MoneyCharge() {
      if (this.w > 320) {
         this.w = 320;
      }

      this.strPaint = mFont.tahoma_7b_green2.splitFontArray(mResources.pay_card, this.w - 20);
      this.x = (GameCanvas.w - this.w) / 2;
      this.y = GameCanvas.h - 150 - (this.strPaint.length - 1) * 20;
      this.h = 110 + (this.strPaint.length - 1) * 20;
      this.tfSerial = new TField();
      this.tfSerial.name = mResources.SERI_NUM;
      this.tfSerial.x = this.x + 10;
      this.tfSerial.y = this.y + 35 + (this.strPaint.length - 1) * 20;
      this.tfSerial.width = this.w - 20;
      this.tfSerial.height = mScreen.ITEM_HEIGHT + 2;
      if (GameCanvas.isTouch) {
         this.tfSerial.isFocus = false;
      } else {
         this.tfSerial.isFocus = true;
      }

      this.tfSerial.setIputType(0);
      if (!GameCanvas.isTouch) {
         super.right = this.tfSerial.cmdClear;
      }

      this.tfCode = new TField();
      this.tfCode.name = mResources.CARD_CODE;
      this.tfCode.x = this.x + 10;
      this.tfCode.y = this.tfSerial.y + 35;
      this.tfCode.width = this.w - 20;
      this.tfCode.height = mScreen.ITEM_HEIGHT + 2;
      this.tfCode.isFocus = false;
      this.tfCode.setIputType(0);
      super.left = new Command(mResources.CLOSE, this, 1, null);
      super.center = new Command(mResources.pay_card2, this, 2, null);
      if (GameCanvas.isTouch) {
         super.center.x = GameCanvas.w / 2 + 18;
         super.left.x = GameCanvas.w / 2 - 85;
         super.center.y = super.left.y = this.y + this.h + 5;
      }
   }

   public static MoneyCharge gI() {
      if (instance == null) {
         instance = new MoneyCharge();
      }

      return instance;
   }

   public final void switchToMe() {
      this.focus = 0;

      try {
         if (mResources.language == 0) {
            GameMidlet.instance.platformRequest("http://ngocrongonline.com/");
         }

         if (mResources.language == 2) {
            GameMidlet.instance.platformRequest("http://dragonball.indonaga.com/");
         }

         if (mResources.language == 1) {
            GameMidlet.instance.platformRequest("http://world.teamobi.com/games-page-0.html");
            return;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public final void paint(mGraphics g) {
      GameScr.gI().paint(g);
      PopUp.paintPopUp(g, this.x, this.y, this.w, this.h, -1, true);

      for (int strPaintIndex = 0; strPaintIndex < this.strPaint.length; strPaintIndex++) {
         mFont.tahoma_7b_green2
            .drawString(g, this.strPaint[strPaintIndex], GameCanvas.w / 2, this.y + 15 + strPaintIndex * 20, 2);
      }

      this.tfSerial.paint(g);
      this.tfCode.paint(g);
      super.paint(g);
   }

   public final void update() {
      GameScr.gI().update();
      this.tfSerial.update();
      this.tfCode.update();
   }

   public final void keyPress(int keyCode) {
      if (this.tfSerial.isFocus) {
         this.tfSerial.keyPressed(keyCode);
      } else if (this.tfCode.isFocus) {
         this.tfCode.keyPressed(keyCode);
      }

      super.keyPress(keyCode);
   }

   public final void updateKey() {
      if (GameCanvas.keyPressed[2]) {
         this.focus--;
         if (this.focus < 0) {
            this.focus = 1;
         }
      } else if (GameCanvas.keyPressed[8]) {
         this.focus++;
         if (this.focus > 1) {
            this.focus = 1;
         }
      }

      if (GameCanvas.keyPressed[2] || GameCanvas.keyPressed[8]) {
         GameCanvas.clearKeyPressed();
         if (this.focus == 1) {
            this.tfSerial.isFocus = false;
            this.tfCode.isFocus = true;
            if (!GameCanvas.isTouch) {
               super.right = this.tfCode.cmdClear;
            }
         } else if (this.focus == 0) {
            this.tfSerial.isFocus = true;
            this.tfCode.isFocus = false;
            if (!GameCanvas.isTouch) {
               super.right = this.tfSerial.cmdClear;
            }
         } else {
            this.tfSerial.isFocus = false;
            this.tfCode.isFocus = false;
         }
      }

      if (GameCanvas.isPointerJustRelease) {
         if (GameCanvas.isPointerHoldIn(
            this.tfSerial.x,
            this.tfSerial.y,
            this.tfSerial.width,
            this.tfSerial.height
         )) {
            this.focus = 0;
         } else if (GameCanvas.isPointerHoldIn(
            this.tfCode.x,
            this.tfCode.y,
            this.tfCode.width,
            this.tfCode.height
         )) {
            this.focus = 1;
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
         if (this.tfSerial.getText() == null || this.tfSerial.getText().equals("")) {
            GameCanvas.startOKDlg(mResources.serial_blank);
            return;
         }

         if (this.tfCode.getText() == null || this.tfCode.getText().equals("")) {
            GameCanvas.startOKDlg(mResources.card_code_blank);
            return;
         }

         Service.gI().sendCardInfo(this.tfSerial.getText(), this.tfCode.getText());
         GameScr.instance.switchToMe();
         instance = null;
      }
   }
}
