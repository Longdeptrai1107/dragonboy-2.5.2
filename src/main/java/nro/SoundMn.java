package nro;

import main.GameCanvas;

public final class SoundMn {
   public static boolean IsDelAcc = false;
   private static SoundMn gIz;
   public static float volume = 0.5F;
   public static int FIREWORK = 30;
   public static int KAMEX10_0 = 31;
   public static int KAMEX10_1 = 32;
   public static int DESTROY_0 = 33;
   public static int DESTROY_1 = 34;
   public static int MAFUBA_0 = 35;
   public static int MAFUBA_1 = 36;
   public static int MAFUBA_2 = 37;
   public static int DESTROY_2 = 38;
   public static int gameSpeed = 0;
   private static boolean hasLoadedOptions = false;

   public static SoundMn gI() {
      if (gIz == null) {
         gIz = new SoundMn();
      }

      return gIz;
   }

   public final void AuraToolOption() {
      if (Char.isPaintAura) {
         Rms.saveRMSInt("isPaintAura", 0);
         Char.isPaintAura = false;
      } else {
         Rms.saveRMSInt("isPaintAura", 1);
         Char.isPaintAura = true;
      }

      getStrOption();
   }

   public final void analogToolOption() {
      if (!GameCanvas.isTouch) {
         if (GameScr.isPaintChatVip = !GameScr.isPaintChatVip) {
            Rms.saveRMSInt("serverchat", 0);
         } else {
            Rms.saveRMSInt("serverchat", 1);
         }

         getStrOption();
      } else {
         if (GameScr.isAnalog == 0) {
            GameScr.isAnalog = 1;
            Rms.saveRMSInt("analog", GameScr.isAnalog);
            GameScr.setSkillBarPosition();
         } else {
            GameScr.isAnalog = 0;
            Rms.saveRMSInt("analog", GameScr.isAnalog);
            GameScr.setSkillBarPosition();
         }

         getStrOption();
      }
   }

   public final void CaseSizeScr() {
      if (GameCanvas.lowGraphic) {
         Rms.saveRMSInt("lowGraphic", 0);
         GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
      } else {
         Rms.saveRMSInt("lowGraphic", 1);
         GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
      }

      getStrOption();
   }

   public final void AuraToolOption2() {
      if (Char.isPaintAura2) {
         Rms.saveRMSInt("isPaintAura2", 0);
         Char.isPaintAura2 = false;
      } else {
         Rms.saveRMSInt("isPaintAura2", 1);
         Char.isPaintAura2 = true;
      }

      getStrOption();
   }

   public static void loadSound() {
   }

   public static void charPunch() {
   }

   public static void traidatKame() {
   }

   public static void namekKame() {
   }

   public static void xaydaKame() {
   }

   public static void charRun() {
   }

   public static void charFall() {
   }

   public static void charJump() {
   }

   public static void panelOpen() {
   }

   public static void buttonClose() {
   }

   public static void buttonClick() {
   }

   public static void charFly() {
   }

   public static void openMenu() {
   }

   public static void panelClick() {
   }

   public static void eatPeans() {
   }

   public static void openDialog() {
   }

   public static void update() {
   }

   public static void explode_1() {
   }

   public static void airShip() {
   }

   public static void HP_MPup() {
   }

   public static void getItem() {
   }

   public static void rain() {
   }

   public static void mobKame() {
   }

   public static void stopAll() {
   }

   public static void explode_2() {
   }

   public static void pauseAirShip() {
   }

   public static void taitaoPause() {
   }

   public static void thaiduonghasan() {
   }

   public static void hoisinh() {
   }

   public static boolean isPlayAirShip() {
      return false;
   }

   public static void resumeAirShip() {
   }

   public static boolean isPlayRain() {
      return false;
   }

   public static void getSoundOption() {
      if (GameCanvas.loginScr.isLogin2
         && Char.myCharz().taskMaint != null
         && Char.myCharz().taskMaint.taskId >= 2) {
         Panel.strTool = new String[]{
            mResources.radaCard,
            mResources.quayso,
            mResources.gameInfo,
            mResources.change_flag,
            mResources.change_zone,
            mResources.chat_world,
            mResources.account,
            mResources.option,
            mResources.change_account,
            mResources.REGISTOPROTECT
         };
         if (Char.myCharz().havePet) {
            Panel.strTool = new String[]{
               mResources.radaCard,
               mResources.quayso,
               mResources.gameInfo,
               mResources.pet,
               mResources.change_flag,
               mResources.change_zone,
               mResources.chat_world,
               mResources.account,
               mResources.option,
               mResources.change_account,
               mResources.REGISTOPROTECT
            };
         }
      } else {
         Panel.strTool = new String[]{
            mResources.radaCard,
            mResources.quayso,
            mResources.gameInfo,
            mResources.change_flag,
            mResources.change_zone,
            mResources.chat_world,
            mResources.account,
            mResources.option,
            mResources.change_account
         };
         if (Char.myCharz().havePet) {
            Panel.strTool = new String[]{
               mResources.radaCard,
               mResources.quayso,
               mResources.gameInfo,
               mResources.pet,
               mResources.change_flag,
               mResources.change_zone,
               mResources.chat_world,
               mResources.account,
               mResources.option,
               mResources.change_account
            };
         }
      }

      if (IsDelAcc) {
         String[] strTool = new String[Panel.strTool.length + 1];

         for (int strToolIndex = 0; strToolIndex < Panel.strTool.length; strToolIndex++) {
            strTool[strToolIndex] = Panel.strTool[strToolIndex];
         }

         strTool[Panel.strTool.length] = mResources.delacc;
         Panel.strTool = strTool;
      }
   }

   public static String formatGameSpeedChoices() {
      String text = gameSpeed == 0 ? "[x]x1" : "[ ]x1";
      String text2 = gameSpeed == 1 ? "[x]x1.5" : "[ ]x1.5";
      String text3 = gameSpeed == 2 ? "[x]x2" : "[ ]x2";
      return text + "    " + text2 + "    " + text3;
   }

   public static String formatGameSpeedOption() {
      String text = gameSpeed == 0 ? "[x]x1" : "[ ]x1";
      String text2 = gameSpeed == 1 ? "[x]x1.5" : "[ ]x1.5";
      String text3 = gameSpeed == 2 ? "[x]x2" : "[ ]x2";
      return mResources.graphicSpeed + "  " + text + " " + text2 + " " + text3;
   }

   public static boolean doesGameSpeedOptionFit() {
      if (GameCanvas.panel == null) {
         return false;
      }

      int imageWidth = mFont.tahoma_7b_dark.getWidth(formatGameSpeedOption());
      int value = GameCanvas.panel.wScroll - 30;
      return imageWidth <= value;
   }

   public final void selectGameSpeed() {
      int value = GameCanvas.panel.xScroll + 25;
      int px = GameCanvas.px;
      String text = gameSpeed == 0 ? "[x]x1" : "[ ]x1";
      String text2 = gameSpeed == 1 ? "[x]x1.5" : "[ ]x1.5";
      int value2;
      int value3;
      if (doesGameSpeedOptionFit()) {
         String text3 = mResources.graphicSpeed + "  ";
         String text4 = " ";
         int imageWidth = mFont.tahoma_7b_dark.getWidth(text3);
         int imageWidth2 = mFont.tahoma_7b_dark.getWidth(text + text4);
         int imageWidth3 = mFont.tahoma_7b_dark.getWidth(text2 + text4);
         value3 = (value = (value2 = value + imageWidth) + imageWidth2) + imageWidth3;
      } else {
         String text5 = "    ";
         int imageWidth4 = mFont.tahoma_7b_dark.getWidth(text + text5);
         int imageWidth5 = mFont.tahoma_7b_dark.getWidth(text2 + text5);
         value2 = value;
         value3 = (value = value + imageWidth4) + imageWidth5;
      }

      if (px >= value3 - 6) {
         gameSpeed = 2;
      } else if (px >= value - 6) {
         gameSpeed = 1;
      } else if (px >= value2 - 8) {
         gameSpeed = 0;
      } else {
         gameSpeed = (gameSpeed + 1) % 3;
      }

      Rms.saveRMSInt("gameSpeed", gameSpeed);
      getStrOption();
   }

   public static void getStrOption() {
      if (!hasLoadedOptions) {
         hasLoadedOptions = true;
         if ((gameSpeed = Rms.loadRMSInt("gameSpeed")) < 0 || gameSpeed > 2) {
            gameSpeed = 0;
         }
      }

      String text = "[x]   ";
      String text2 = "[  ]   ";
      String text3 = GameScr.isAnalog == 0 ? text2 + mResources.turnOnAnalog : text + mResources.turnOffAnalog;
      if (!GameCanvas.isTouch) {
         text3 = !GameScr.isPaintChatVip ? text2 + mResources.serverchat_off : text + mResources.serverchat_off;
      }

      Panel.strCauhinh = new String[]{
         Char.isPaintAura ? text + mResources.specialAura.trim() : text2 + mResources.specialAura.trim(),
         Char.isPaintAura2 ? text + mResources.powerAura.trim() : text2 + mResources.powerAura.trim(),
         GameCanvas.isPlaySound ? text + mResources.turnOffSound.trim() : text2 + mResources.turnOffSound.trim(),
         GameCanvas.lowGraphic ? text + mResources.cauhinhthap.trim() : text2 + mResources.cauhinhthap.trim(),
         text3,
         mResources.graphicSpeed
      };
   }

   public static void gong() {
   }

   public static void nameLazer() {
   }

   public static void monkeyRun() {
   }

   public static void gongName() {
   }

   public static void bigeExlode() {
   }

   public static void backToRegister() {
      Session_ME.gI().close();
      GameCanvas.panel.hide();
      GameCanvas.loginScr.actRegister();
      GameCanvas.loginScr.switchToMe();
   }

   public static void newKame() {
   }

   public static void radarClick() {
   }

   public static void radarItem() {
   }

   public static void playSound() {
   }
}
