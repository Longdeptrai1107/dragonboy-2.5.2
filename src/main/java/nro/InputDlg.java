package nro;

import main.GameCanvas;

public final class InputDlg extends Dialog {
   private String[] info;
   public TField tfInput;
   private int padLeft = 40;

   public InputDlg() {
      if (GameCanvas.w <= 176) {
         this.padLeft = 10;
      }

      this.tfInput = new TField();
      this.tfInput.x = this.padLeft + 10;
      this.tfInput.y = GameCanvas.h - mScreen.ITEM_HEIGHT - 43;
      this.tfInput.width = GameCanvas.w - 2 * (this.padLeft + 10);
      this.tfInput.height = mScreen.ITEM_HEIGHT + 2;
      this.tfInput.isFocus = true;
      super.right = this.tfInput.cmdClear;
   }

   public final void show(String info, Command ok, int type) {
      this.tfInput.setText("");
      this.tfInput.setIputType(type);
      this.info = mFont.tahoma_8b.splitFontArray(info, GameCanvas.w - (this.padLeft << 1));
      super.left = new Command(mResources.CLOSE, GameCanvas.gI(), 8882, null);
      super.center = ok;
      GameCanvas.currentDialog = this;
   }

   public final void paint(mGraphics graphics) {
      GameCanvas.paintz
         .paintInputDlg(
            graphics,
            this.padLeft,
            GameCanvas.h - 77 - mScreen.cmdH,
            GameCanvas.w - (this.padLeft << 1),
            69,
            this.info
         );
      this.tfInput.paint(graphics);
      super.paint(graphics);
   }

   public final void keyPress(int keyCode) {
      this.tfInput.keyPressed(keyCode);
      super.keyPress(keyCode);
   }

   public final void update() {
      this.tfInput.update();
      super.update();
   }
}
