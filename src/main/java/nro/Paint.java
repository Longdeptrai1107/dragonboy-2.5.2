package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Paint {
   private static int COLORBACKGROUND = 15787715;
   private static Image[] goc = new Image[6];
   public static int hTab;
   private static int lenCaption;

   static {
      for (int gocIndex = 0; gocIndex < goc.length; gocIndex++) {
         goc[gocIndex] = mSystem.loadImage("/mainImage/myTexture2dgoc" + (gocIndex + 1) + ".png");
      }

      hTab = 24;
      lenCaption = 0;
      mSystem.loadImage("/mainImage/myTexture2dcheck.png");
   }

   public Paint() {
      int[] intValues = new int[]{15970400, -844109861, 2250052, 16374659, 15906669, 12931125, 3108954};
   }

   public static void paintCmdBar(mGraphics g, Command left, Command center, Command right) {
      mFont tahoma_7b_dark = mFont.tahoma_7b_dark;
      if (left != null && (lenCaption = tahoma_7b_dark.getWidth(left.caption)) > 0) {
         if (left.x >= 0 && left.y > 0) {
            left.paint(g);
         } else {
            g.drawImage(
               mScreen.keyTouch == 0 ? GameScr.imgLbtnFocus : GameScr.imgLbtn,
               ((int) (1)),
               ((int) (GameCanvas.h - mScreen.cmdH - 1)),
               0
            );
            tahoma_7b_dark.drawString(g, left.caption, 35, GameCanvas.h - mScreen.cmdH + 3 + 3, 2);
         }
      }

      if (center != null && (lenCaption = tahoma_7b_dark.getWidth(center.caption)) > 0) {
         if (center.x > 0 && center.y > 0) {
            center.paint(g);
         } else {
            g.drawImage(
               mScreen.keyTouch == 1 ? GameScr.imgLbtnFocus : GameScr.imgLbtn,
               ((int) (GameCanvas.hw - 35)),
               ((int) (GameCanvas.h - mScreen.cmdH - 1)),
               0
            );
            tahoma_7b_dark.drawString(
               g, center.caption, GameCanvas.hw, GameCanvas.h - mScreen.cmdH + 3 + 3, 2
            );
         }
      }

      if (right != null && (lenCaption = tahoma_7b_dark.getWidth(right.caption)) > 0) {
         if (right.x > 0 && right.y > 0) {
            right.paint(g);
            return;
         }

         g.drawImage(
            mScreen.keyTouch == 2 ? GameScr.imgLbtnFocus : GameScr.imgLbtn,
            ((int) (GameCanvas.w - 71)),
            ((int) (GameCanvas.h - mScreen.cmdH - 1)),
            0
         );
         tahoma_7b_dark.drawString(
            g, right.caption, GameCanvas.w - 35, GameCanvas.h - mScreen.cmdH + 3 + 3, 2
         );
      }
   }

   public static void paintTabSoft() {
   }

   public final void paintInputDlg(mGraphics g, int x, int y, int w, int h, String[] str) {
      paintFrame(x, y, w, 69, g);
      y = y + 20 - mFont.tahoma_8b.getHeight();
      int strIndex = 0;

      for (int y2 = y; strIndex < str.length; y2 += mFont.tahoma_8b.getHeight()) {
         mFont.tahoma_8b.drawString(g, str[strIndex], x + w / 2, y2, 2);
         strIndex++;
      }
   }

   public static void paintPopUp(int x, int y, int w, int h, mGraphics g) {
      g.setColor(9340251);
      g.drawRect(x + 18, y, (w - 36) / 2 - 32, h);
      g.drawRect(x + 18 + (w - 36) / 2 + 32, y, (w - 36) / 2 - 22, h);
      g.drawRect(x, y + 8, w, h - 17);
      g.setColor(COLORBACKGROUND);
      g.fillRect(x + 18, y + 3, (w - 36) / 2 - 32, h - 4);
      g.fillRect(x + 18 + (w - 36) / 2 + 31, y + 3, (w - 38) / 2 - 22, h - 4);
      g.fillRect(x + 1, y + 6, w - 1, h - 11);
      g.setColor(14667919);
      g.fillRect(x + 18, y + 1, (w - 36) / 2 - 32, 2);
      g.fillRect(x + 18 + (w - 36) / 2 + 32, y + 1, (w - 36) / 2 - 12, 2);
      g.fillRect(x + 18, y + h - 2, (w - 36) / 2 - 31, 2);
      g.fillRect(x + 18 + (w - 36) / 2 + 32, y + h - 2, (w - 36) / 2 - 31, 2);
      g.fillRect(x + 1, y + 11, 2, h - 18);
      g.fillRect(x + w - 2, y + 11, 2, h - 18);
      g.drawImage(goc[0], ((int) (x - 3)), ((int) (y - 2)), 20);
      g.drawImage(goc[2], ((int) (x + w + 3)), ((int) (y - 2)), StaticObj.TOP_RIGHT);
      g.drawImage(goc[1], ((int) (x - 3)), ((int) (y + h + 3)), StaticObj.BOTTOM_LEFT);
      g.drawImage(goc[3], ((int) (x + w + 4)), ((int) (y + h + 2)), StaticObj.BOTTOM_RIGHT);
      g.drawImage(goc[4], ((int) (x + w / 2)), ((int) (y)), StaticObj.TOP_CENTER);
      g.drawImage(goc[5], ((int) (x + w / 2)), ((int) (y + h + 1)), StaticObj.BOTTOM_HCENTER);
   }

   public static void paintFrame(int x, int y, int w, int h, mGraphics g) {
      g.setColor(13524492);
      g.drawRect(x + 6, y, w - 12, h);
      g.drawRect(x, y + 6, w, h - 12);
      g.drawRect(x + 7, y + 1, w - 14, h - 2);
      g.drawRect(x + 1, y + 7, w - 2, h - 14);
      g.setColor(14338484);
      g.fillRect(x + 8, y + 2, w - 16, h - 3);
      g.fillRect(x + 2, y + 8, w - 3, h - 14);
      g.drawImage(GameCanvas.imgBorder[2], ((int) (x)), ((int) (y)), 20);
      g.drawRegion(GameCanvas.imgBorder[2], 0, 0, 16, 16, 2, x + w + 1, y, StaticObj.TOP_RIGHT);
      g.drawRegion(GameCanvas.imgBorder[2], 0, 0, 16, 16, 1, x, y + h + 1, StaticObj.BOTTOM_LEFT);
      g.drawRegion(GameCanvas.imgBorder[2], 0, 0, 16, 16, 3, x + w + 1, y + h + 1, StaticObj.BOTTOM_RIGHT);
   }

   public static void paintFrameSimple(int x, int y, int width, int height, mGraphics graphics) {
      graphics.setColor(6702080);
      graphics.fillRect(x, y, width, height);
      graphics.setColor(14338484);
      graphics.fillRect(x + 1, y + 1, width - 2, height - 2);
   }
}
