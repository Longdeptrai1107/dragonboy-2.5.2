package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Small {
   public Image img;
   public int id;
   public int timePaint;
   public int timeUpdate;

   public Small(Image imgValue, int idValue) {
      this.img = imgValue;
      this.id = idValue;
      this.timePaint = 0;
      this.timeUpdate = 0;
   }

   public final void paint(mGraphics g, int transform, int x, int y, int anchor) {
      boolean condition = false;
      int anchor2 = anchor;
      anchor = y;
      y = x;
      x = transform;
      mGraphics g2 = g;
      Small small = this;
      if (g2 != null && small.img != null) {
         g2.drawRegionAtLeftEdge(
            small.img,
            0,
            0,
            mGraphics.getImageWidth(small.img),
            mGraphics.getImageHeight(small.img),
            x,
            y,
            anchor,
            anchor2
         );
         if (GameCanvas.gameTick % 1000 == 0) {
            small.timePaint++;
            small.timeUpdate = small.timePaint;
         }
      }
   }

   public final void paint(mGraphics graphics, int transform, int transform2, int x, int y, int width, int height, int anchor) {
      boolean condition = false;
      int anchor2 = anchor;
      anchor = height;
      height = width;
      width = y;
      y = x;
      x = transform2;
      transform2 = transform;
      mGraphics graphics2 = graphics;
      Small small = this;
      if (graphics2 != null && small.img != null) {
         if (mGraphics.getImageWidth(small.img) != 1) {
            graphics2.drawRegionAtLeftEdge(small.img, 0, x * height, height, anchor, transform2, y, width, anchor2);
            if (GameCanvas.gameTick % 1000 == 0) {
               small.timePaint++;
               small.timeUpdate = small.timePaint;
            }
         }
      }
   }
}
