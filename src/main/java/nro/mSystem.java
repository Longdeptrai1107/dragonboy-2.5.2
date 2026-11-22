package nro;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class mSystem {
   public static boolean field_a_cd5f4f86 = false;
   public static boolean field_b_91c0e29e = false;
   private static Image infoBackgroundTile = loadImage("/mainImage/black.png");
   public static int clientType = 1;
   public static byte LANGUAGE = 0;
   public static byte curINAPP = 0;
   public static byte maxINAPP = 5;
   public static String strAdmob;
   public static String publicID;
   public static boolean field_i_9420c8f3 = false;

   public static void AddIpTest() {
   }

   public static void resetCurInapp() {
      curINAPP = 0;
   }

   public static int getWidth(Image img) {
      return clientType == 5 ? img.getWidth() : img.getWidth();
   }

   public static int getHeight(Image img) {
      return clientType == 5 ? img.getHeight() : img.getWidth();
   }

   public static String getTimeCountDown(long timeStart, int secondCount, boolean isOnlySecond, boolean isShortText) {
      long longValue;
      return (longValue = (timeStart + secondCount * 1000 - System.currentTimeMillis()) / 1000L) <= 0L ? "" : String.valueOf(longValue);
   }

   public static String numberTostring(long number) {
      String result = "" + number;
      boolean condition = false;

      try {
         Object entry = null;
         if (number < 0L) {
            condition = true;
            number = -number;
            result = "" + number;
         }

         int endIndex;
         if (number >= 1000000000L) {
            entry = "b";
            number /= 1000000000L;
            endIndex = ("" + number).length();
         } else if (number >= 1000000L) {
            entry = "m";
            number /= 1000000L;
            endIndex = ("" + number).length();
         } else {
            if (number < 1000L) {
               if (condition) {
                  return "-" + result;
               }

               return result;
            }

            entry = "k";
            number /= 1000L;
            endIndex = ("" + number).length();
         }

         int value;
         if ((value = Integer.parseInt(result.substring(endIndex, 2))) == 0) {
            result = result.substring(0, endIndex) + entry;
         } else if (value % 10 == 0) {
            result = result.substring(0, endIndex) + "," + result.substring(endIndex, 1) + entry;
         } else {
            result = result.substring(0, endIndex) + "," + result.substring(endIndex, 2) + entry;
         }
      } catch (Exception ignoredException) {
      }

      return condition ? "-" + result : result;
   }

   public static void callHotlineJava() {
      try {
         GameMidlet.instance.platformRequest(ServerListScreen.linkweb);
      } catch (ConnectionNotFoundException connectionNotFoundException) {
         connectionNotFoundException.printStackTrace();
      }
   }

   public static void callHotlineIphone() {
   }

   public static void callHotlineWindowsPhone() {
   }

   public static void paintTiledInfoBackground(mGraphics graphics, int value, int y, int value2) {
      value2 = value2 / mGraphics.getImageWidth(infoBackgroundTile) + 1;

      for (int index = 0; index < value2; index++) {
         graphics.drawImage(infoBackgroundTile, ((int) (value + index * mGraphics.getImageWidth(infoBackgroundTile))), ((int) (y)), 0);
      }
   }

   public static void waitWhileMessageReadingPaused() {
      while (Controller.isStopReadMessage) {
         try {
            Thread.sleep(1000L);
         } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
         }
      }
   }

   public static void onConnectionFail(boolean isMain) {
      if (ServerListScreen.isWaitingForConnection > 0) {
         if (isMain) {
            ServerListScreen.testConnect = 0;
         }
      } else if (GameCanvas.currentScreen.equals(SplashScr.instance)) {
         GameCanvas.startOK(mResources.maychutathoacmatsong + " [1]", 8884, null);
      } else {
         Session_ME.gI().clearSendingMessage();
         Session_ME.gI2().clearSendingMessage();
         ServerListScreen.isWait = false;
         if (Controller.isLoadingData) {
            GameCanvas.startOK(mResources.maychutathoacmatsong, 8885, null);
         } else {
            GameCanvas.connectionErrorOccurred = true;
            LoginScr.isContinueToLogin = false;
            LoginScr.serverName = ServerListScreen.nameServer[ServerListScreen.ipSelect];
            if (GameCanvas.currentScreen != GameCanvas.serverScreen) {
               Res.outz("===========> 2 vào OnconnectFail");
               ServerListScreen.countDieConnect = 0;
            } else {
               ServerListScreen.loadScreen = true;
            }

            Char.isLoadingMap = false;
            if (isMain) {
               ServerListScreen.testConnect = 0;
            }
         }
      }
   }

   public static void onDisconnected(boolean isMain) {
      if (ServerListScreen.isWaitingForConnection > 0) {
         if (isMain) {
            ServerListScreen.testConnect = 0;
         }
      } else {
         GameCanvas.connectionErrorOccurred = true;
         Session_ME.gI().clearSendingMessage();
         Session_ME.gI2().clearSendingMessage();
         if (Controller.isLoadingData) {
            GameCanvas.queueResetDialog(mResources.pls_restart_game_error, 8885, null);
         } else {
            if (GameCanvas.currentScreen != GameCanvas.serverScreen) {
               GameCanvas.queueResetDialog(mResources.maychutathoacmatsong + " [2]", 8884, null);
            }

            Char.isLoadingMap = false;
            if (isMain) {
               ServerListScreen.testConnect = 0;
            }
         }
      }
   }

   public static Image loadImageRMS(String path) {
      String original = "/x" + mGraphics.zoomLevel + path;
      Image image = null;
      Res.outz("path load rms= " + path);

      try {
         String[] parts = Res.split(Res.split(original, ".", 0)[0], "/", 0);
         String text;
         byte[] rMS;
         if ((rMS = Rms.loadRMS(text = "x" + mGraphics.zoomLevel + parts[parts.length - 1])) == null) {
            rMS = Rms.loadRMS(text + ".png");
         }

         if (rMS == null) {
            rMS = Rms.loadRMS(mGraphics.zoomLevel + parts[parts.length - 1]);
         }

         if (rMS == null) {
            rMS = Rms.loadRMS(mGraphics.zoomLevel + parts[parts.length - 1] + ".png");
         }

         if (rMS != null) {
            image = Image.createImage(rMS, 0, rMS.length);
         }
      } catch (Exception ignoredException) {
      }

      if (image != null) {
         return image;
      }

      try {
         image = ImageLoader.loadImage(path);
      } catch (Exception ignoredException2) {
      }

      return image;
   }

   public static Image loadImage(String path) {
      Image image = null;

      try {
         image = ImageLoader.loadImage(path);
      } catch (Exception ignoredException) {
      }

      return image;
   }

   public static Image loadZoomedResourceImage(String text) {
      String text2 = "/x" + mGraphics.zoomLevel + text;
      Image image = null;

      try {
         image = Image.createImage(text2);
      } catch (Exception ignoredException) {
      }

      return image;
   }

   public static void paintFlyText(mGraphics g) {
      for (int flyTextColorIndex = 0; flyTextColorIndex < 5; flyTextColorIndex++) {
         if (GameScr.flyTextState[flyTextColorIndex] != -1 && GameCanvas.isPaint(GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex])) {
            if (GameScr.flyTextColor[flyTextColorIndex] == 0) {
               mFont.bigNumber_red
                  .drawStringBorder(g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2);
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 1) {
               mFont.bigNumber_yellow
                  .drawStringBorder(g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2);
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 2) {
               mFont.bigNumber_green
                  .drawStringBorder(g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2);
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 3) {
               mFont.bigNumber_yellow
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.bigNumber_green
                  );
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 8) {
               mFont.tahoma_7_green
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.tahoma_7b_dark
                  );
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 4) {
               mFont.tahoma_7_white
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.tahoma_7_grey
                  );
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 5) {
               mFont.bigNumber_orange
                  .drawStringBorder(g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2);
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 6) {
               mFont.bigNumber_yellow
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.bigNumber_red
                  );
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 7) {
               mFont.tahoma_7b_white
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.tahoma_7b_dark
                  );
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 9) {
               mFont.tahoma_7_red
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.tahoma_7b_dark
                  );
            } else if (GameScr.flyTextColor[flyTextColorIndex] == 10) {
               mFont.tahoma_7_blue1
                  .drawStringBorder(
                     g, GameScr.flyTextString[flyTextColorIndex], GameScr.flyTextX[flyTextColorIndex], GameScr.flyTextY[flyTextColorIndex], 2, mFont.tahoma_7b_dark
                  );
            }
         }
      }
   }

   public static void closeBanner() {
   }

   public static void createAdmob() {
   }

   public static void checkAdComlete() {
   }

   public static long currentTimeMillis() {
      return System.currentTimeMillis();
   }

   public static void callHotlineAndroid() {
   }

   public static void endKey() {
   }

   public static FrameImage getFraImage(String nameImg) {
      FrameImage frameImage = null;
      MainImage imagePath = null;
      if ((imagePath = ImgByName.getImagePath(nameImg, ImgByName.hashImagePath)).img != null) {
         int height;
         if ((height = imagePath.img.getHeight() / imagePath.nFrame) <= 0) {
            height = 1;
         }

         frameImage = new FrameImage(imagePath.img, imagePath.img.getWidth(), height);
      }

      return frameImage;
   }

   public static void println(String text) {
   }
}
