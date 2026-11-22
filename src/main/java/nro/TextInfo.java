package nro;

import main.GameCanvas;

public final class TextInfo {
   private static int dx;
   private static int tx;
   private static int wStr;
   private static boolean isBack;
   private static String laststring = "";

   public static void reset() {
      dx = 0;
      tx = 0;
      isBack = false;
   }

   public static void paint(mGraphics g, String str, int x, int y, int w, int h, mFont f) {
      if (wStr != f.getWidth(str) || !laststring.equals(str)) {
         laststring = str;
         dx = 0;
         wStr = f.getWidth(str);
         isBack = false;
         tx = 0;
      }

      g.setClip(x, y, w, h);
      if (wStr > w) {
         f.drawString(g, str, x - dx, y, 0);
      } else {
         f.drawString(g, str, x + w / 2, y, 2);
      }

      GameCanvas.resetTrans(g);
      if (wStr > w) {
         if (!isBack) {
            if (++tx > 50 && ++dx >= wStr) {
               tx = 0;
               dx = -w + 30;
               isBack = true;
               return;
            }
         } else {
            if (dx < 0) {
               int dxValue = w + dx >> 1;
               dx += dxValue;
            }

            if (dx > 0) {
               dx = 0;
            }

            if (dx == 0 && ++tx == 50) {
               tx = 0;
               isBack = false;
            }
         }
      }
   }
}
