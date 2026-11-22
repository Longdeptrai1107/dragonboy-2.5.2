package nro;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class mGraphics {
   public Graphics graphics;
   public static int zoomLevel = 1;

   public final void fillArg(int value, int value2, int value3, int value4, int value5, int value6) {
      value *= zoomLevel;
      value2 *= zoomLevel;
      value3 *= zoomLevel;
      value4 *= zoomLevel;
      this.graphics.fillArc(value, value2, value3, value4, 0, 360);
   }

   public final void drawImage(Image image, int x, int y, int anchor) {
      if (image != null) {
         x *= zoomLevel;
         y *= zoomLevel;
         this.graphics.drawImage(image, x, y, anchor);
      }
   }

   public final void drawImage(Image image, float x, float y, int anchor) {
      int anchor2 = anchor;
      float y2 = y;
      y = x;
      Image image2 = image;
      mGraphics graphicsValue = this;
      if (image2 != null) {
         y *= zoomLevel;
         float y3 = y2 * zoomLevel;
         graphicsValue.graphics.drawImage(image2, (int)y, (int)y3, anchor2);
      }
   }

   public final void drawLine(int x1, int y1, int x2, int y2) {
      x1 *= zoomLevel;
      y1 *= zoomLevel;
      x2 *= zoomLevel;
      y2 *= zoomLevel;
      this.graphics.drawLine(x1, y1, x2, y2);
   }

   public final void fillRect(int x, int y, int width, int height, int rgb) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.setColor(rgb);
      this.graphics.fillRect(x, y, width, height);
   }

   public final void fillRectDelegated(int x, int y, int width, int height) {
      this.fillRect(x, y, width, height);
   }

   public final void drawRect(int x, int y, int width, int height) {
      this.fillRect(x, y, 1, height);
      this.fillRect(x + width, y, 1, height);
      this.fillRect(x, y, width, 1);
      this.fillRect(x, y + height, width + 1, 1);
   }

   public final void drawRegion(Image image, int x0, int y0, int w0, int h0, int value, int x, int y, int value2) {
      if (image != null) {
         x *= zoomLevel;
         y *= zoomLevel;
         x0 *= zoomLevel;
         y0 *= zoomLevel;
         w0 *= zoomLevel;
         h0 *= zoomLevel;
         this.graphics.drawRegion(image, x0, y0, w0, h0, value, x, y, value2);
      }
   }

   public final void drawRegionAtLeftEdge(Image image, int value, int sourceY, int width, int height, int transform, int x, int y, int anchor) {
      if (image != null) {
         this.drawRegion(image, 0, sourceY, width, height, transform, x, y, anchor);
      }
   }

   public final void fillTrans(Image imgTrans, int x, int y, int width, int height) {
      for (int index = 0; index < width / getImageWidth(imgTrans) + 1; index++) {
         for (int index2 = 0; index2 < height / getImageHeight(imgTrans) + 1; index2++) {
            this.graphics.drawImage(imgTrans, 0 + index * getImageWidth(imgTrans), 0 + index2 * getImageHeight(imgTrans), 0);
         }
      }
   }

   public static int blendColor(float level, int color, int colorBlend) {
      Res.outz("blend color");
      level = colorBlend >> 16 & 0xFF;
      float floatValue = colorBlend >> 8 & 0xFF;
      float floatValue2 = colorBlend & 0xFF;
      float floatValue3 = (level + 0.0F) * 0.4F + 0.0F;
      float floatValue4 = (floatValue + 0.0F) * 0.4F + 0.0F;
      float floatValue5 = (floatValue2 + 0.0F) * 0.4F + 0.0F;
      if (floatValue3 > 255.0F) {
         floatValue3 = 255.0F;
      }

      if (floatValue3 < 0.0F) {
         floatValue3 = 0.0F;
      }

      if (floatValue4 > 255.0F) {
         floatValue4 = 255.0F;
      }

      if (floatValue4 < 0.0F) {
         floatValue4 = 0.0F;
      }

      if (floatValue5 < 0.0F) {
         floatValue5 = 0.0F;
      }

      if (floatValue5 > 255.0F) {
         floatValue5 = 255.0F;
      }

      return 0xFF000000 | (int)floatValue3 << 16 | (int)floatValue4 << 8 | (int)floatValue5 & 0xFF;
   }

   public final void fillRect(int x, int y, int width, int height) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.fillRect(x, y, width, height);
   }

   public final int getTranslateX() {
      return this.graphics.getTranslateX() / zoomLevel;
   }

   public final int getTranslateY() {
      return this.graphics.getTranslateY() / zoomLevel;
   }

   public final void setClip(int x, int y, int width, int height) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.setClip(x, y, width, height);
   }

   public final int getClipX() {
      return this.graphics.getClipX();
   }

   public final int getClipY() {
      return this.graphics.getClipY();
   }

   public final int getClipWidth() {
      return this.graphics.getClipWidth();
   }

   public final int getClipHeight() {
      return this.graphics.getClipHeight();
   }

   public final void setColor(int rgb) {
      this.graphics.setColor(rgb);
   }

   public final void translate(int tx, int ty) {
      tx *= zoomLevel;
      ty *= zoomLevel;
      this.graphics.translate(tx, ty);
   }

   public static int getImageWidth(Image image) {
      return image.getWidth() / zoomLevel;
   }

   public static int getImageHeight(Image image) {
      return image.getHeight() / zoomLevel;
   }

   public static int getRealImageWidth(Image img) {
      return img.getWidth();
   }

   public static int getRealImageHeight(Image img) {
      return img.getHeight();
   }
}
