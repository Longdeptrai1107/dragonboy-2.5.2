package nro;

import main.GameCanvas;

public final class InfoDlg {
   public static boolean isShow;
   private static String title;
   private static String subtitke;
   private static int delay;
   public static boolean isLock;

   public static void show(String titleValue, String subtitle, int delayValue) {
      if (titleValue != null) {
         isShow = true;
         title = titleValue;
         subtitke = subtitle;
         delay = delayValue;
      }
   }

   public static void showWait() {
      show(mResources.PLEASEWAIT, null, 1000);
      isLock = true;
   }

   public static void paint(mGraphics g) {
      if (isShow) {
         if (!isLock || delay <= 4990) {
            Paint.paintPopUp(GameCanvas.hw - 75, 10, 150, 55, g);
            if (isLock) {
               GameCanvas.paintShukiren(GameCanvas.hw - mFont.tahoma_8b.getWidth(title) / 2 - 10, 38, g);
               mFont.tahoma_8b.drawString(g, title, GameCanvas.hw + 5, 31, 2);
            } else if (subtitke != null) {
               mFont.tahoma_8b.drawString(g, title, GameCanvas.hw, 23, 2);
               mFont.tahoma_7_green2.drawString(g, subtitke, GameCanvas.hw, 40, 2);
            } else {
               mFont.tahoma_8b.drawString(g, title, GameCanvas.hw, 31, 2);
            }
         }
      }
   }

   public static void update() {
      if (isShow) {
         if (delay > 0 && --delay <= 0) {
            hide();
         }
      }
   }

   public static void hide() {
      title = "";
      subtitke = null;
      isLock = false;
      delay = 0;
      isShow = false;
   }
}
