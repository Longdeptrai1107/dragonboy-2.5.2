package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class SplashScr extends mScreen {
   private static int splashScrStat;
   private boolean isCheckConnect = false;
   private static int nData = -1;
   private static int maxData = -1;
   public static SplashScr instance;
   private static boolean hasSwitchedToServerList = false;
   public static Image imgLogo = mSystem.loadImage("/gamelogo.png");

   public SplashScr() {
      instance = this;
   }

   public static void loadSplashScr() {
      splashScrStat = 0;
      hasSwitchedToServerList = false;
   }

   public final void update() {
      if (++splashScrStat == 30 && !this.isCheckConnect) {
         this.isCheckConnect = true;
         if (Rms.loadRMSInt("serverchat") != -1) {
            GameScr.isPaintChatVip = Rms.loadRMSInt("serverchat") == 0;
         }

         if (Rms.loadRMSInt("isPlaySound") != -1) {
            GameCanvas.isPlaySound = Rms.loadRMSInt("isPlaySound") == 1;
         }

         if (GameCanvas.isPlaySound) {
            SoundMn.gI();
            SoundMn.loadSound();
         }

         SoundMn.gI();
         SoundMn.getStrOption();
         ServerListScreen.loadIP();
      }

      if (splashScrStat >= 150 && !hasSwitchedToServerList) {
         hasSwitchedToServerList = true;
         if (Session_ME.gI().isConnected()) {
            ServerListScreen.loadScreen = true;
         } else {
            mSystem.onDisconnected(true);
            if (GameCanvas.serverScreen == null) {
               GameCanvas.serverScreen = new ServerListScreen();
            }
         }

         GameCanvas.serverScreen.switchToMe();
      }

      ServerListScreen.updateDeleteData();
   }

   public static void loadIP() {
      Res.err(">>>>>loadIP:  svselect == " + Rms.loadRMSInt(ServerListScreen.RMS_svselect));
      ServerListScreen.SetIpSelect(Rms.loadRMSInt(ServerListScreen.RMS_svselect), false);
      if (ServerListScreen.ipSelect == -1) {
         Res.err(">>>loadIP:  svselect == -1");
         byte byteValue = -1;
         ServerListScreen.SetIpSelect(ServerListScreen.serverPriority, true);
      }

      ServerListScreen.ConnectIP();
   }

   public final void paint(mGraphics g) {
      if (imgLogo != null && splashScrStat < 30) {
         g.setColor(16777215);
         g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
         g.drawImage(imgLogo, ((int) (GameCanvas.w / 2)), ((int) (GameCanvas.h / 2)), 3);
      }

      if (nData != -1) {
         g.setColor(0);
         g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
         g.drawImage(
            LoginScr.imgTitle, ((int) (GameCanvas.w / 2)), ((int) (GameCanvas.h / 2 - 24)), StaticObj.BOTTOM_HCENTER
         );
         GameCanvas.paintShukiren(GameCanvas.hw, GameCanvas.h / 2 + 24, g);
         mFont.tahoma_7b_white
            .drawString(
               g,
               mResources.downloading_data + nData * 100 / maxData + "%",
               GameCanvas.w / 2,
               GameCanvas.h / 2,
               2
            );
      } else {
         if (splashScrStat >= 30) {
            g.setColor(0);
            g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
            GameCanvas.paintShukiren(GameCanvas.hw, GameCanvas.hh, g);
            ServerListScreen.paintDeleteData(g);
         }
      }
   }

   public static void loadImg() {
      imgLogo = mSystem.loadImage("/gamelogo.png");
   }
}
