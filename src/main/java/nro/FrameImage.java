package nro;

import javax.microedition.lcdui.Image;

public final class FrameImage {
   public int frameWidth;
   public int frameHeight;
   public int nFrame;
   public Image imgFrame;

   public FrameImage(int iD) {
      Image imgFrameValue;
      if ((imgFrameValue = Effect_End.getImage(iD)) != null) {
         this.imgFrame = imgFrameValue;
         this.frameWidth = Effect_End.arrInfoEff[iD][0];
         this.frameHeight = Effect_End.arrInfoEff[iD][1] / Effect_End.arrInfoEff[iD][2];
         this.nFrame = Effect_End.arrInfoEff[iD][2];
      }
   }

   public FrameImage(Image img, int width, int height) {
      if (img != null) {
         this.imgFrame = img;
         this.frameWidth = width;
         this.frameHeight = height;
         this.nFrame = img.getHeight() / height;
         if (this.nFrame <= 0) {
            this.nFrame = 1;
         }
      }
   }

   public final void drawFrame(int idx, int x, int y, int trans, int anchor, mGraphics g) {
      try {
         if (this.imgFrame != null) {
            if (idx > this.nFrame) {
               idx = this.nFrame;
            }

            int value;
            if ((value = idx * this.frameHeight) > this.frameHeight * (this.nFrame - 1) || value < 0) {
               ;
            }

            g.drawRegion(this.imgFrame, 0, idx * this.frameHeight, this.frameWidth, this.frameHeight, trans, x, y, anchor);
            return;
         }
      } catch (Exception ignoredException) {
      }
   }
}
