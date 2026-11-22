package nro;

import javax.microedition.lcdui.Image;

public final class MainImage {
   public Image img;
   public long count = -1L;
   public int timeImageNull = 0;
   public byte nFrame = 1;

   public MainImage() {
      mSystem.currentTimeMillis();
   }

   public MainImage(Image im, byte nFrameValue) {
      mSystem.currentTimeMillis();
      this.img = im;
      this.count = 0L;
      this.nFrame = nFrameValue;
   }
}
