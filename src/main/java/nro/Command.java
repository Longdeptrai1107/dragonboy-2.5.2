package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Command {
   public boolean isDisplay = false;
   public String caption;
   public String[] subCaption;
   public IActionListener actionListener;
   public int idAction;
   public boolean isPlaySoundButton = true;
   public Image img;
   public Image imgFocus;
   public Image imgBtn;
   public int x = 0;
   public int y = 0;
   public int w = mScreen.cmdW;
   public int h = mScreen.cmdH;
   public boolean isFocus = false;
   public Object p;
   public String caption2 = "";
   private static Image btn0left = mSystem.loadImage("/mainImage/btn0left.png");
   private static Image btn0mid = mSystem.loadImage("/mainImage/btn0mid.png");
   private static Image btn0right = mSystem.loadImage("/mainImage/btn0right.png");
   private static Image btn1left = mSystem.loadImage("/mainImage/btn1left.png");
   private static Image btn1mid = mSystem.loadImage("/mainImage/btn1mid.png");
   private static Image btn1right = mSystem.loadImage("/mainImage/btn1right.png");
   public boolean cmdClosePanel;
   public boolean isPaintNew;

   public Command(String captionValue, IActionListener actionListenerValue, int action, Object entry, int xValue, int yValue) {
      this.caption = captionValue;
      this.idAction = action;
      this.actionListener = actionListenerValue;
      this.p = null;
      this.x = xValue;
      this.y = yValue;
   }

   public Command(String captionValue, IActionListener actionListenerValue, int action, Object pValue) {
      this.caption = captionValue;
      this.idAction = action;
      this.actionListener = actionListenerValue;
      this.p = pValue;
   }

   public Command(String captionValue, int action, Object pValue) {
      this.caption = captionValue;
      this.idAction = action;
      this.p = pValue;
   }

   public Command(String captionValue, int action) {
      this.caption = captionValue;
      this.idAction = action;
   }

   public Command(String captionValue, int action, int xValue, int yValue) {
      this.caption = captionValue;
      this.idAction = 0;
      this.x = xValue;
      this.y = yValue;
   }

   public final void performAction() {
      GameCanvas.clearAllPointerEvent();
      if (this.isPlaySoundButton
         && (
            this.caption != null && !this.caption.equals("") && !this.caption.equals(mResources.saying)
               || this.img != null
         )) {
         SoundMn.gI();
         SoundMn.buttonClick();
      }

      if (this.idAction > 0) {
         if (this.actionListener != null) {
            this.actionListener.perform(this.idAction, this.p);
            return;
         }

         GameScr.gI().actionPerform(this.idAction, this.p);
      }
   }

   public final void setType() {
      this.w = 160;
   }

   public final void paint(mGraphics g) {
      if (this.img != null) {
         g.drawImage(this.img, ((int) (this.x)), ((int) (this.y)), 0);
         if (this.isFocus) {
            if (this.imgFocus == null) {
               if (this.cmdClosePanel) {
                  g.drawImage(ItemMap.imageFlare, ((int) (this.x + 8)), ((int) (this.y + 8)), 3);
               } else {
                  g.drawImage(
                     ItemMap.imageFlare,
                     ((int) (this.x - (this.img.equals(GameScr.imgMenu) ? 10 : 0))),
                     ((int) (this.y)),
                     0
                  );
               }
            } else {
               g.drawImage(this.imgFocus, ((int) (this.x)), ((int) (this.y)), 0);
            }
         }

         if (this.caption != "" && this.caption != null) {
            if (!this.isFocus) {
               mFont.tahoma_7b_dark
                  .drawString(
                     g,
                     this.caption,
                     this.x + mGraphics.getImageWidth(this.img) / 2,
                     this.y + mGraphics.getImageHeight(this.img) / 2 - 5,
                     2
                  );
               return;
            }

            mFont.tahoma_7b_green2
               .drawString(
                  g,
                  this.caption,
                  this.x + mGraphics.getImageWidth(this.img) / 2,
                  this.y + mGraphics.getImageHeight(this.img) / 2 - 5,
                  2
               );
         }
      } else {
         if (this.caption != "") {
            if (!this.isFocus) {
               paintOngMau(
                  btn0left, btn0mid, btn0right, this.x, this.y, this.w, g
               );
            } else {
               paintOngMau(
                  btn1left, btn1mid, btn1right, this.x, this.y, this.w, g
               );
            }
         }

         int imageWidth = 0;
         int xValue = this.x + this.w / 2;
         if (this.imgBtn != null) {
            imageWidth = this.imgBtn.getWidth();
            xValue = this.x + imageWidth;
            if (!this.isFocus) {
               g.drawImage(this.imgBtn, ((int) (this.x)), ((int) (this.y)), 0);
            } else {
               g.drawImage(this.imgBtn, ((int) (this.x)), ((int) (this.y + 1)), 0);
            }
         }

         if (!this.isFocus) {
            mFont.tahoma_7b_dark.drawString(g, this.caption, xValue, this.y + 7, imageWidth == 0 ? 2 : 0);
         } else {
            mFont.tahoma_7b_green2.drawString(g, this.caption, xValue, this.y + 7, imageWidth == 0 ? 2 : 0);
         }
      }
   }

   private static void paintOngMau(Image img0, Image img1, Image img2, int xValue, int yValue, int size, mGraphics graphics) {
      for (int value = 10; value <= size - 20; value += 10) {
         graphics.drawImage(img1, ((int) (xValue + value)), ((int) (yValue)), 0);
      }

      int width;
      if ((width = size % 10) > 0) {
         graphics.drawRegion(img1, 0, 0, width, 24, 0, xValue + size - 10 - width, yValue, 0);
      }

      graphics.drawImage(img0, ((int) (xValue)), ((int) (yValue)), 0);
      graphics.drawImage(img2, ((int) (xValue + size - 10)), ((int) (yValue)), 0);
   }

   public final boolean isPointerPressInside() {
      this.isFocus = false;
      if (GameCanvas.isPointerHoldIn(this.x, this.y, this.w, this.h)) {
         if (GameCanvas.isPointerDown) {
            this.isFocus = true;
         }

         if (GameCanvas.isPointerJustRelease) {
            return true;
         }
      }

      return false;
   }

   public final boolean isPointerPressInsideCamera(int cmx, int cmy) {
      this.isFocus = false;
      if (GameCanvas.isPointerHoldIn(this.x, this.y - cmy, this.w, this.h)) {
         if (GameCanvas.isPointerDown) {
            this.isFocus = true;
         }

         if (GameCanvas.isPointerJustRelease) {
            return true;
         }
      }

      return false;
   }
}
