package nro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import main.GameCanvas;
import main.GameMidlet;

public final class ServerListScreen extends mScreen implements IActionListener {
   public static String[] nameServer;
   private static String[] address;
   public static byte serverPriority;
   private static short[] port;
   private static int selected;
   public static boolean isWait;
   public static byte[] language;
   public static byte[] typeClass;
   public static Char[] listChar;
   public static boolean isHaveChar;
   private Command[] cmd;
   private Command cmdCallHotline;
   private int nCmdPlay;
   public static byte[] typeSv;
   public static byte[] isNew;
   public static Command cmdDeleteRMS;
   private static String smartPhoneVN = "Vũ trụ 1:dragon1.teamobi.com:14445:0:0:0,Vũ trụ 2:dragon2.teamobi.com:14445:0:0:0,Vũ trụ 3:dragon3.teamobi.com:14445:0:0:0,Vũ trụ 4:dragon4.teamobi.com:14445:0:0:0,Vũ trụ 5:dragon5.teamobi.com:14445:0:0:0,Vũ trụ 6:dragon6.teamobi.com:14445:0:0:0,Vũ trụ 7:dragon7.teamobi.com:14445:0:0:0,Vũ trụ 8:dragon10.teamobi.com:14446:0:0:0,Vũ trụ 9:dragon10.teamobi.com:14447:0:0:0,Vũ trụ 10:dragon10.teamobi.com:14445:0:0:0,Vũ trụ 11:dragon11.teamobi.com:14445:0:0:0,Võ đài liên vũ trụ:dragonwar.teamobi.com:20000:0:0:0,Universe 1:dragon.indonaga.com:14445:1:0:0,Naga:dragon.indonaga.com:14446:2:0:0,0,0";
   private static String javaVN = "Vũ trụ 1:112.213.94.23:14445:0:0:0,Vũ trụ 2:210.211.109.199:14445:0:0:0,Vũ trụ 3:112.213.85.88:14445:0:0:0,Vũ trụ 4:27.0.12.164:14445:0:0:0,Vũ trụ 5:27.0.12.16:14445:0:0:0,Vũ trụ 6:27.0.12.173:14445:0:0:0,Vũ trụ 7:112.213.94.223:14445:0:0:0,Vũ trụ 8:27.0.14.66:14446:0:0:0,Vũ trụ 9:27.0.14.66:14447:0:0:0,Vũ trụ 10:27.0.14.66:14445:0:0:0,Vũ trụ 11:112.213.85.35:14445:0:0:0,Võ đài liên vũ trụ:27.0.12.173:20000:0:0:0,Universe 1:52.74.230.22:14445:1:0:0,Naga:52.74.230.22:14446:2:0:0,0,0";
   private static String smartPhoneIn = "Naga:dragon.indonaga.com:14446:2:0:0,2,0";
   private static String javaIn = "Naga:52.74.230.22:14446:2:0:0,2,0";
   private static String smartPhoneE = "Universe 1:dragon.indonaga.com:14445:1:0:0,1,0";
   private static String javaE = "Universe 1:52.74.230.22:14445:1:0:0,1,0";
   public static String linkDefault = javaVN;
   public static boolean stopDownload;
   public static String linkweb;
   public static int countDieConnect;
   public static boolean waitToLogin;
   private static int tWaitToLogin;
   private static String RMS_NRlink = "NRlink3";
   public static int ipSelect;
   public static volatile int isWaitingForConnection;
   public static int field_r_b491fafc;
   public static long connectionFailureCheckAt;
   public static int loginActionAfterConnect;
   private static int pendingLoginAction;
   private static long pendingLoginStartedAt;
   public static boolean bigOk;
   public static int percent;
   public static String strWait;
   public static int nBig;
   public static int demPercent;
   public static boolean isGetData = false;
   private static Command cmdDownload;
   public static volatile int testConnect = -1;
   private static long nextResourceRequestAt;
   public static boolean loadScreen;
   public static boolean isAutoConect;
   public static String RMS_svselect = "svselect";
   private static String RMS_NR_Extralink = "NRlink_extra";
   public static boolean isAutoLogin;

   private static void createDeleteRMS() {
      if (cmdDeleteRMS == null) {
         if (GameCanvas.serverScreen == null) {
            GameCanvas.serverScreen = new ServerListScreen();
         }

         cmdDeleteRMS = new Command("", GameCanvas.serverScreen, 14, null);
      }

      cmdDeleteRMS.idAction = 14;
      if (cmdDeleteRMS.actionListener == null && GameCanvas.serverScreen != null) {
         cmdDeleteRMS.actionListener = GameCanvas.serverScreen;
      }

      cmdDeleteRMS.x = GameCanvas.w - 85;
      cmdDeleteRMS.y = GameCanvas.h - 30;
      cmdDeleteRMS.w = 85;
      cmdDeleteRMS.h = 30;
   }

   public ServerListScreen() {
      System.getProperty("com.sonyericsson.imei");
      this.initCommand();
      if (!GameCanvas.isTouch) {
         selected = 0;
         this.processInput();
      }

      GameScr.loadCamera(-1, -1);
      GameScr.cmx = 100;
      GameScr.cmy = 200;
      if (this.cmdCallHotline == null) {
         this.cmdCallHotline = new Command("Gọi hotline", this, 13, null);
         this.cmdCallHotline.x = GameCanvas.w - 75;
         if (mSystem.clientType == 1 && !GameCanvas.isTouch) {
            this.cmdCallHotline.y = GameCanvas.h - 20;
         } else {
            this.cmdCallHotline.y = 8;
         }
      }

      boolean condition = false;
      if (0 == 2) {
         if (mSystem.clientType == 1) {
            linkDefault = javaIn;
         } else {
            linkDefault = smartPhoneIn;
         }
      } else if (condition) {
         linkDefault = javaE;
         if (mSystem.clientType == 1) {
            linkDefault = javaE;
         } else {
            linkDefault = smartPhoneE;
         }
      } else {
         linkDefault = javaVN;
         if (mSystem.clientType == 1) {
            linkDefault = javaVN;
         } else {
            linkDefault = smartPhoneVN;
         }
      }

      mSystem.AddIpTest();
   }

   private void initCommand() {
      this.nCmdPlay = 0;
      String storedAccount;
      if ((storedAccount = Rms.loadRMSString(Rms.RMS_acc)) == null
         ? Rms.loadRMS(Rms.RMS_userAo + ipSelect) != null
         : !storedAccount.equals("") || Rms.loadRMS(Rms.RMS_userAo + ipSelect) != null) {
         this.nCmdPlay = 1;
      }

      String text = nameServer != null && ipSelect >= 0 && ipSelect < nameServer.length ? nameServer[ipSelect] : "";
      String caption = mResources.server + ": " + text;
      boolean condition = true;
      this.cmd = new Command[4 + this.nCmdPlay];
      int y = GameCanvas.hh - 15 * this.cmd.length + 28;

      for (int cmdIndex = 0; cmdIndex < this.cmd.length; cmdIndex++) {
         switch (cmdIndex) {
            case 0:
               this.cmd[0] = new Command("", this, 3, null);
               if (storedAccount == null) {
                  this.cmd[0].caption = mResources.playNew;
                  if (Rms.loadRMS(Rms.RMS_userAo + ipSelect) != null) {
                     this.cmd[0].caption = mResources.choitiep;
                  }
               } else if (storedAccount.equals("")) {
                  this.cmd[0].caption = mResources.playNew;
                  if (Rms.loadRMS(Rms.RMS_userAo + ipSelect) != null) {
                     this.cmd[0].caption = mResources.choitiep;
                  }
               } else {
                  this.cmd[0].caption = mResources.playAcc + ": " + storedAccount;
                  if (this.cmd[0].caption.length() > 23) {
                     this.cmd[0].caption = this.cmd[0].caption.substring(0, 23);
                     this.cmd[0].caption = this.cmd[0].caption + "...";
                  }
               }
               break;
            case 1:
               if (this.nCmdPlay == 1) {
                  this.cmd[1] = new Command("", this, 10100, null);
                  this.cmd[1].caption = mResources.playNew;
               } else {
                  this.cmd[1] = new Command(mResources.change_account, this, 7, null);
               }
               break;
            case 2:
               if (this.nCmdPlay == 1) {
                  this.cmd[2] = new Command(mResources.change_account, this, 7, null);
               } else {
                  this.cmd[2] = new Command(caption, this, 17, null);
               }
               break;
            case 3:
               if (this.nCmdPlay == 1) {
                  this.cmd[3] = new Command(caption, this, 17, null);
               } else {
                  this.cmd[3] = new Command(mResources.option, this, 8, null);
               }
               break;
            case 4:
               this.cmd[4] = new Command(mResources.option, this, 8, null);
         }

         this.cmd[cmdIndex].y = y;
         this.cmd[cmdIndex].setType();
         this.cmd[cmdIndex].x = (GameCanvas.w - this.cmd[cmdIndex].w) / 2;
         y += 30;
      }

      if (this.cmd != null && this.cmd.length > 2 + this.nCmdPlay && this.cmd[2 + this.nCmdPlay] != null
         )
       {
         this.cmd[2 + this.nCmdPlay].caption = caption;
      }
   }

   public static void doUpdateServer() {
      getServerList(linkDefault);
   }

   public static void getServerList(String str) {
      String[] parts = Res.split(str.trim(), ",", 0);
      Res.outz(">>> getServerList= " + str);
      mResources.loadLanguague(Byte.parseByte(parts[parts.length - 2]));
      nameServer = new String[parts.length - 2];
      address = new String[parts.length - 2];
      port = new short[parts.length - 2];
      language = new byte[parts.length - 2];
      typeSv = new byte[parts.length - 2];
      isNew = new byte[parts.length - 2];

      for (int addressIndex = 0; addressIndex < parts.length - 2; addressIndex++) {
         String[] parts2 = Res.split(parts[addressIndex].trim(), ":", 0);
         nameServer[addressIndex] = parts2[0];
         address[addressIndex] = parts2[1];
         port[addressIndex] = Short.parseShort(parts2[2]);
         language[addressIndex] = Byte.parseByte(parts2[3].trim());

         try {
            typeSv[addressIndex] = Byte.parseByte(parts2[4].trim());
         } catch (Exception ignoredException) {
            typeSv[addressIndex] = 0;
         }

         try {
            isNew[addressIndex] = Byte.parseByte(parts2[5].trim());
         } catch (Exception ignoredException2) {
            isNew[addressIndex] = 0;
         }
      }

      serverPriority = Byte.parseByte(parts[parts.length - 1]);
      Res.outz(">>> getServerList= serverPriority: " + serverPriority);
      int ipSelectValue = -1;
      if (GameMidlet.IP != null && GameMidlet.PORT > 0) {
         if (ipSelect >= 0
            && ipSelect < address.length
            && GameMidlet.IP.equals(address[ipSelect])
            && GameMidlet.PORT == port[ipSelect]) {
            ipSelectValue = ipSelect;
         } else {
            for (int addressIndex2 = 0; addressIndex2 < address.length; addressIndex2++) {
               if (GameMidlet.IP.equals(address[addressIndex2]) && GameMidlet.PORT == port[addressIndex2]) {
                  ipSelectValue = addressIndex2;
                  break;
               }
            }
         }
      }

      if (ipSelectValue >= 0) {
         ipSelect = ipSelectValue;
      } else if (ipSelect < 0 || ipSelect >= nameServer.length) {
         int ipSelectValue2;
         if ((ipSelectValue2 = Rms.loadRMSInt(RMS_svselect)) < 0 || ipSelectValue2 >= nameServer.length) {
            ipSelectValue2 = serverPriority >= 0 && serverPriority < nameServer.length ? serverPriority : 0;
         }

         ipSelect = ipSelectValue2;
      }

      if (ipSelect >= 0 && ipSelect < nameServer.length) {
         Rms.saveRMSInt(RMS_svselect, ipSelect);
      }

      saveIP();
   }

   public final void paint(mGraphics g) {
      if (!loadScreen) {
         g.setColor(0);
         g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
      } else {
         if (!GameCanvas.paintBG || GameCanvas.imgBG == null) {
            GameCanvas.loadBG(0);
         }

         GameCanvas.paintBGGameScr(g);
      }

      mFont.tahoma_7_white
         .drawString(g, "v2.5.2(" + mGraphics.zoomLevel + ")", GameCanvas.w - 2, 17, 1, mFont.tahoma_7_grey);

      try {
         String st = "";
         if (testConnect == 0) {
            st = st + nameServer[ipSelect] + " [disconnect]";
         } else if (testConnect == -1) {
            st = st + nameServer[ipSelect] + " [connecting...]";
         } else {
            st = st + nameServer[ipSelect] + " [connected]";
         }

         mFont.tahoma_7_white.drawString(g, st, GameCanvas.w - 2, 32, 1, mFont.tahoma_7_grey);
      } catch (Exception ignoredException) {
      }

      if (isGetData && !loadScreen) {
         mFont.tahoma_7_white.drawString(g, linkweb, GameCanvas.w - 2, 2, 1, mFont.tahoma_7_grey);
      } else if (mSystem.clientType == 1 && !GameCanvas.isTouch) {
         mFont.tahoma_7_white
            .drawString(g, linkweb, GameCanvas.w - 2, GameCanvas.h - 15, 1, mFont.tahoma_7_grey);
      } else {
         mFont.tahoma_7_white.drawString(g, linkweb, GameCanvas.w - 2, 2, 1, mFont.tahoma_7_grey);
      }

      int x = 0;
      paintDeleteData(g);
      if (!loadScreen) {
         if (!bigOk) {
            g.drawImage(LoginScr.imgTitle, ((int) (GameCanvas.hw)), ((int) (GameCanvas.hh - 32)), 3);
            if (isGetData) {
               if (cmdDownload != null) {
                  cmdDownload.paint(g);
               }

               g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
               mFont.tahoma_7b_white
                  .drawString(
                     g, mResources.downloading_data + percent + "%", GameCanvas.w / 2, GameCanvas.hh + 24, 2
                  );
               GameScr.paintOngMauPercent(
                  GameScr.frBarPow20,
                  GameScr.frBarPow21,
                  GameScr.frBarPow22,
                  GameCanvas.w / 2 - 50,
                  GameCanvas.hh + 45,
                  100,
                  100.0F,
                  g
               );
               GameScr.paintOngMauPercent(
                  GameScr.frBarPow0,
                  GameScr.frBarPow1,
                  GameScr.frBarPow2,
                  GameCanvas.w / 2 - 50,
                  GameCanvas.hh + 45,
                  100,
                  percent,
                  g
               );
               return;
            }

            mFont.tahoma_7b_white
               .drawString(g, mResources.taidulieudechoi, GameCanvas.hw, GameCanvas.hh + 24, 2);
            if (cmdDownload != null) {
               cmdDownload.paint(g);
               return;
            }
         }
      } else {
         if ((x = GameCanvas.hh - 15 * this.cmd.length - 15) < 25) {
            x = 25;
         }

         if (LoginScr.imgTitle != null) {
            g.drawImage(LoginScr.imgTitle, ((int) (GameCanvas.hw)), ((int) (x)), 3);
         }

         x = this.cmd.length;

         for (int cmdIndex = 0; cmdIndex < x; cmdIndex++) {
            this.cmd[cmdIndex].paint(g);
         }

         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         if (this.cmd != null
            && this.cmd.length > 2 + this.nCmdPlay
            && this.cmd[2 + this.nCmdPlay] != null) {
            if (GameScr.imgRoomStat == null) {
               GameScr.imgRoomStat = mSystem.loadImage("/mainImage/myTexture2dstat.png");
            }

            if (GameScr.imgRoomStat != null
               && this.cmd[2 + this.nCmdPlay].caption != null
               && !this.cmd[2 + this.nCmdPlay].caption.equals("")) {
               byte byteValue = 0;
               if (Session_ME.gI().connecting || Session_ME.gI().isConnected()) {
                  byteValue = 1;
               }

               if (testConnect == 2 && Session_ME.gI().isConnected()) {
                  byteValue = 2;
               }

               x = mFont.tahoma_7b_dark.getWidth(this.cmd[2 + this.nCmdPlay].caption);
               x = (GameCanvas.w - x >> 1) - 10;
               int y = this.cmd[2 + this.nCmdPlay].y + 9;
               g.drawRegion(GameScr.imgRoomStat, 0, byteValue * 7, 7, 7, 0, x, y, 0);
            }
         }
      }
   }

   public final void update() {
      if (waitToLogin) {
         if (++tWaitToLogin == 50) {
            GameCanvas.serverScreen.selectServer();
            waitToLogin = false;
         }

         if (tWaitToLogin == 100) {
            if (GameCanvas.loginScr == null) {
               GameCanvas.loginScr = new LoginScr();
            }

            GameCanvas.loginScr.doLogin();
            Service.gI().finishUpdate();
            waitToLogin = false;
         }
      }

      if (isWaitingForConnection > 0) {
         if (testConnect == 2 && Session_ME.gI().isConnected()) {
            isWaitingForConnection = 0;
            connectionFailureCheckAt = 0L;
            if (loginActionAfterConnect > 0) {
               pendingLoginAction = loginActionAfterConnect;
               loginActionAfterConnect = 0;
            }

            GameCanvas.endDlg();
         } else if (mSystem.currentTimeMillis() >= connectionFailureCheckAt
            && !Session_ME.gI().isConnected()
            && !Session_ME.gI().connecting) {
            isWaitingForConnection = 0;
            connectionFailureCheckAt = 0L;
            GameCanvas.endDlg();
            Command cmdYes = new Command(mResources.retry, this, 20, null);
            Command cmdNo = new Command(mResources.CLOSE, this, 21, null);
            GameCanvas.startYesNoDlg(mResources.cannotConnectServer, cmdYes, cmdNo);
         }
      }

      if (pendingLoginAction > 0
         && pendingLoginStartedAt > 0L
         && loadScreen
         && testConnect == 2
         && Session_ME.gI().isConnected()
         && mSystem.currentTimeMillis() - pendingLoginStartedAt >= 25000L
         && !Service.gI().isMainClientInfoAcknowledged()) {
         pendingLoginStartedAt = 0L;
         isWaitingForConnection = 0;
         connectionFailureCheckAt = 0L;
         GameCanvas.endDlg();
         Command cmdYes2 = new Command(mResources.retry, this, 20, null);
         Command cmdNo2 = new Command(mResources.CLOSE, this, 21, null);
         GameCanvas.startYesNoDlg(mResources.cannotConnectServer, cmdYes2, cmdNo2);
      }

      for (int cmdIndex = 0; cmdIndex < this.cmd.length; cmdIndex++) {
         if (cmdIndex == selected) {
            this.cmd[cmdIndex].isFocus = true;
         } else {
            this.cmd[cmdIndex].isFocus = false;
         }
      }

      GameScr.cmx++;
      if (!loadScreen && (bigOk || percent == 100)) {
         cmdDownload = null;
      }

      if (!loadScreen
         && isGetData
         && demPercent == 0
         && !stopDownload
         && testConnect == 2
         && Session_ME.gI().isConnected()
         && mSystem.currentTimeMillis() > nextResourceRequestAt) {
         nextResourceRequestAt = mSystem.currentTimeMillis() + 3000L;
         Service.gI().getResource((byte)1, null);
      }

      super.update();
   }

   private void processInput() {
      Res.outz("load Screen= " + loadScreen + " select= " + selected);
      if (loadScreen) {
         super.center = new Command("", this, this.cmd[selected].idAction, null);
      } else {
         super.center = cmdDownload;
      }
   }

   public static void paintDeleteData(mGraphics g) {
      if (cmdDeleteRMS != null) {
         mFont.tahoma_7_white
            .drawString(
               g, mResources.xoadulieu, GameCanvas.w - 2, GameCanvas.h - 15, 1, mFont.tahoma_7_grey
            );
      }
   }

   public static void updateDeleteData() {
      if (cmdDeleteRMS == null) {
         createDeleteRMS();
      }

      if (cmdDeleteRMS != null) {
         if (cmdDeleteRMS.actionListener == null) {
            cmdDeleteRMS.actionListener = GameCanvas.serverScreen != null
               ? GameCanvas.serverScreen
               : (GameCanvas.currentScreen instanceof ServerListScreen ? (ServerListScreen)GameCanvas.currentScreen : null);
         }

         cmdDeleteRMS.idAction = 14;
         cmdDeleteRMS.x = GameCanvas.w - 85;
         cmdDeleteRMS.y = GameCanvas.h - 30;
         cmdDeleteRMS.w = 85;
         cmdDeleteRMS.h = 30;
         if (cmdDeleteRMS.isPointerPressInside()) {
            cmdDeleteRMS.performAction();
         }
      }
   }

   public final void updateKey() {
      if (GameCanvas.isTouch) {
         updateDeleteData();
         if (this.cmdCallHotline != null && this.cmdCallHotline.isPointerPressInside()) {
            this.cmdCallHotline.performAction();
         }

         if (!loadScreen) {
            if (cmdDownload != null && cmdDownload.isPointerPressInside()) {
               cmdDownload.performAction();
            }

            super.updateKey();
            return;
         }

         int cmdCount = this.cmd.length;

         for (int cmdIndex = 0; cmdIndex < cmdCount; cmdIndex++) {
            if (this.cmd[cmdIndex] != null && this.cmd[cmdIndex].isPointerPressInside()) {
               this.cmd[cmdIndex].performAction();
            }
         }
      } else {
         if (mSystem.clientType == 1 && GameCanvas.keyPressed[13]) {
            GameCanvas.keyPressed[13] = false;
            this.cmdCallHotline.performAction();
         }

         if (loadScreen) {
            if (GameCanvas.keyPressed[8]) {
               int value = this.cmd.length - 1;
               GameCanvas.keyPressed[8] = false;
               if (++selected > value) {
                  selected = 0;
               }

               this.processInput();
            }

            if (GameCanvas.keyPressed[2]) {
               int selectedValue = this.cmd.length - 1;
               GameCanvas.keyPressed[2] = false;
               if (--selected < 0) {
                  selected = selectedValue;
               }

               this.processInput();
            }
         }
      }

      super.updateKey();
   }

   private static void saveIP() {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      DataOutputStream output = new DataOutputStream(buffer);

      try {
         output.writeByte(mResources.language);
         output.writeByte(nameServer.length);

         for (int addressIndex = 0; addressIndex < nameServer.length; addressIndex++) {
            output.writeUTF(nameServer[addressIndex]);
            output.writeUTF(address[addressIndex]);
            output.writeShort(port[addressIndex]);
            output.writeByte(language[addressIndex]);

            try {
               output.writeByte(typeSv[addressIndex]);
            } catch (Exception ignoredException) {
               output.writeByte(0);
            }

            try {
               output.writeByte(isNew[addressIndex]);
            } catch (Exception ignoredException2) {
               output.writeByte(0);
            }
         }

         output.writeByte(serverPriority);
         Rms.saveRMS(RMS_NRlink, buffer.toByteArray());
         output.close();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static void loadIP() {
      byte[] storedNRlink;
      if ((storedNRlink = Rms.loadRMS(RMS_NRlink)) == null) {
         getServerList(linkDefault);
         SplashScr.loadIP();
      } else {
         ByteArrayInputStream inputBytes = new ByteArrayInputStream(storedNRlink);
         DataInputStream input = new DataInputStream(inputBytes);

         try {
            mResources.loadLanguague(input.readByte());
            byte textCount;
            nameServer = new String[textCount = input.readByte()];
            address = new String[textCount];
            port = new short[textCount];
            language = new byte[textCount];
            typeSv = new byte[textCount];
            isNew = new byte[textCount];

            for (int addressIndex = 0; addressIndex < textCount; addressIndex++) {
               nameServer[addressIndex] = input.readUTF();
               address[addressIndex] = input.readUTF();
               port[addressIndex] = input.readShort();
               language[addressIndex] = input.readByte();

               try {
                  typeSv[addressIndex] = input.readByte();
               } catch (Exception ignoredException) {
                  typeSv[addressIndex] = 0;
               }

               try {
                  isNew[addressIndex] = input.readByte();
               } catch (Exception ignoredException2) {
                  isNew[addressIndex] = 0;
               }
            }

            serverPriority = input.readByte();
            input.close();
            SplashScr.loadIP();
         } catch (IOException ignoredIOException) {
         }
      }
   }

   public final void switchToMe() {
      loginActionAfterConnect = 0;
      GameCanvas.clearTransientOverlays();
      createDeleteRMS();
      EffectManager.remove();
      GameScr.cmy = 0;
      GameScr.cmx = 0;
      this.initCommand();
      isWait = false;
      GameCanvas.loginScr = null;
      String storedResVersion;
      if (((storedResVersion = Rms.loadRMSString(Rms.RMS_ResVersion)) != null && storedResVersion != "" ? Integer.parseInt(storedResVersion) : -1) > 0) {
         loadScreen = true;
         GameCanvas.loadBG(0);
      }

      bigOk = true;
      storedResVersion = nameServer != null && ipSelect >= 0 && ipSelect < nameServer.length ? nameServer[ipSelect] : "";
      if (this.cmd != null && this.cmd.length > 2 + this.nCmdPlay && this.cmd[2 + this.nCmdPlay] != null
         )
       {
         this.cmd[2 + this.nCmdPlay].caption = mResources.server + ": " + storedResVersion;
      }

      if (this.cmd != null
         && selected >= 0
         && selected < this.cmd.length
         && this.cmd[selected] != null) {
         super.center = new Command("", this, this.cmd[selected].idAction, null);
      }

      if (this.cmd != null && this.cmd.length > 1 + this.nCmdPlay && this.cmd[1 + this.nCmdPlay] != null
         )
       {
         this.cmd[1 + this.nCmdPlay].caption = mResources.change_account;
      }

      if (this.cmd != null
         && this.cmd.length == 4 + this.nCmdPlay
         && this.cmd[3 + this.nCmdPlay] != null) {
         this.cmd[3 + this.nCmdPlay].caption = mResources.option;
      }

      Char.isLoadingMap = false;
      mSystem.resetCurInapp();
      super.switchToMe();
   }

   public final void switchToMe2() {
      loginActionAfterConnect = 0;
      GameScr.cmy = 0;
      GameScr.cmx = 0;
      this.initCommand();
      isWait = false;
      GameCanvas.loginScr = null;
      String storedResVersion;
      if (((storedResVersion = Rms.loadRMSString(Rms.RMS_ResVersion)) != null && storedResVersion != "" ? Integer.parseInt(storedResVersion) : -1) > 0) {
         loadScreen = true;
         GameCanvas.loadBG(0);
      }

      bigOk = true;
      storedResVersion = nameServer != null && ipSelect >= 0 && ipSelect < nameServer.length ? nameServer[ipSelect] : "";
      if (this.cmd != null && this.cmd.length > 2 + this.nCmdPlay && this.cmd[2 + this.nCmdPlay] != null
         )
       {
         this.cmd[2 + this.nCmdPlay].caption = mResources.server + ": " + storedResVersion;
      }

      if (this.cmd != null
         && selected >= 0
         && selected < this.cmd.length
         && this.cmd[selected] != null) {
         super.center = new Command("", this, this.cmd[selected].idAction, null);
      }

      if (this.cmd != null && this.cmd.length > 1 + this.nCmdPlay && this.cmd[1 + this.nCmdPlay] != null
         )
       {
         this.cmd[1 + this.nCmdPlay].caption = mResources.change_account;
      }

      if (this.cmd != null
         && this.cmd.length == 4 + this.nCmdPlay
         && this.cmd[3 + this.nCmdPlay] != null) {
         this.cmd[3 + this.nCmdPlay].caption = mResources.option;
      }

      super.switchToMe();
   }

   public final void cancel() {
      if (GameCanvas.serverScreen == null) {
         GameCanvas.serverScreen = new ServerListScreen();
      }

      demPercent = 0;
      percent = 0;
      stopDownload = true;
      GameCanvas.serverScreen.show2();
      isGetData = false;
      cmdDownload.isFocus = true;
      super.center = new Command("", this, 2, null);
   }

   public final void selectServer() {
      clearPendingLoginAction();
      loginActionAfterConnect = 0;
      if (address != null
         && port != null
         && language != null
         && ipSelect >= 0
         && ipSelect < address.length
         && ipSelect < port.length
         && ipSelect < language.length
         && GameMidlet.IP != null
         && GameMidlet.IP.equals(address[ipSelect])
         && GameMidlet.PORT == port[ipSelect]
         && testConnect == 2
         && Session_ME.gI().isConnected()
         && Service.gI().isMainClientInfoAcknowledged()) {
         isWaitingForConnection = 0;
         connectionFailureCheckAt = 0L;
         GameMidlet.LANGUAGE = language[ipSelect];
         if (language[ipSelect] != mResources.language) {
            mResources.loadLanguague(language[ipSelect]);
         }

         Rms.saveRMSInt(RMS_svselect, ipSelect);
         this.initCommand();
         loadScreen = true;
         isWait = false;
         GameCanvas.endDlg();
      } else {
         isWaitingForConnection = 1;
         connectionFailureCheckAt = mSystem.currentTimeMillis() + 1000L;
         GameCanvas.startWaitDlg(mResources.PLEASEWAIT);
         Service.gI().resetClientInfoAndPendingLogin();
         Session_ME.gI().close();
         Session_ME.gI2().close();
         GameMidlet.IP2 = null;
         GameMidlet.PORT2 = 0;
         GameMidlet.isConnect2 = false;
         GameMidlet.IP = address[ipSelect];
         GameMidlet.PORT = port[ipSelect];
         GameMidlet.LANGUAGE = language[ipSelect];
         Rms.saveRMSInt(RMS_svselect, ipSelect);
         if (language[ipSelect] != mResources.language) {
            mResources.loadLanguague(language[ipSelect]);
         }

         this.initCommand();
         if (this.cmd != null
            && this.cmd.length > 2 + this.nCmdPlay
            && this.cmd[2 + this.nCmdPlay] != null
            && nameServer != null
            && ipSelect >= 0
            && ipSelect < nameServer.length) {
            this.cmd[2 + this.nCmdPlay].caption = mResources.server + ": " + nameServer[ipSelect];
         }

         loadScreen = true;
         testConnect = -1;
         ConnectIP();
      }
   }

   public final void perform(int idAction, Object p) {
      Res.outz("perform trong ServerListScreen" + idAction);
      if (idAction == 1000) {
         GameCanvas.connect();
      }

      if (idAction == 1 || idAction == 4) {
         clearPendingLoginAction();
         Service.gI().resetClientInfoAndPendingLogin();
         stopDownload = true;
         isGetData = false;
         demPercent = 0;
         percent = 0;
         Session_ME.gI().close();
         Session_ME.gI2().close();
         testConnect = 0;
         isWaitingForConnection = 0;
         connectionFailureCheckAt = 0L;
         loginActionAfterConnect = 0;
         loadScreen = true;
         bigOk = true;
         isWait = false;
         GameCanvas.loginScr = null;
         GameCanvas.endDlg();
         GameScr.cmx = 0;
         GameScr.cmy = 0;
         Char.isLoadingMap = false;
         this.initCommand();
         p = nameServer != null && ipSelect >= 0 && ipSelect < nameServer.length ? nameServer[ipSelect] : "";
         if (this.cmd != null
            && this.cmd.length > 2 + this.nCmdPlay
            && this.cmd[2 + this.nCmdPlay] != null) {
            this.cmd[2 + this.nCmdPlay].caption = mResources.server + ": " + p;
         }

         mSystem.resetCurInapp();
         GameCanvas.loadBG(0);
         super.switchToMe();
         ConnectIP();
      }

      if (idAction == 2) {
         stopDownload = false;
         (cmdDownload = new Command(mResources.huy, this, 4, null)).x = GameCanvas.w / 2
            - mScreen.cmdW / 2;
         cmdDownload.y = GameCanvas.hh + 65;
         super.right = null;
         if (!GameCanvas.isTouch) {
            cmdDownload.x = GameCanvas.w / 2 - mScreen.cmdW / 2;
            cmdDownload.y = GameCanvas.h - mScreen.cmdH - 1;
         }

         super.center = new Command("", this, 4, null);
         if (!isGetData) {
            if (!Session_ME.gI().isConnected()) {
               ConnectIP();
            }

            Service.gI().getResource((byte)1, null);
            nextResourceRequestAt = mSystem.currentTimeMillis() + 3000L;
            if (!GameCanvas.isTouch) {
               cmdDownload.isFocus = true;
               super.center = new Command("", this, 4, null);
            }

            isGetData = true;
         }
      }

      if (idAction == 3) {
         this.requestLoginAction(3);
      }

      if (idAction == 10100) {
         this.requestLoginAction(10100);
      }

      if (idAction == 5) {
         doUpdateServer();
         if (nameServer.length == 1) {
            return;
         }

         MyVector menuItems = new MyVector("");

         for (int nameServerIndex = 0; nameServerIndex < nameServer.length; nameServerIndex++) {
            menuItems.addElement(new Command(nameServer[nameServerIndex], this, 6, null));
         }

         GameCanvas.menu.startAt(menuItems);
         if (!GameCanvas.isTouch) {
            GameCanvas.menu.menuSelectedItem = ipSelect;
         }
      }

      if (idAction == 6) {
         SetIpSelect(GameCanvas.menu.menuSelectedItem, false);
         this.selectServer();
      }

      if (idAction == 7) {
         clearPendingLoginAction();
         Service.gI().clearPendingLogin();
         loginActionAfterConnect = 0;
         if (GameCanvas.loginScr == null) {
            GameCanvas.loginScr = new LoginScr();
         }

         GameCanvas.loginScr.switchToMe();
      }

      if (idAction == 8) {
         boolean condition = Rms.loadRMSInt("lowGraphic") == 1;
         MyVector menuItems2;
         (menuItems2 = new MyVector("cau hinh")).addElement(new Command(mResources.cauhinhthap, this, 9, null));
         menuItems2.addElement(new Command(mResources.cauhinhcao, this, 10, null));
         menuItems2.addElement(new Command(mResources.fixError, this, 14, null));
         GameCanvas.menu.startAt(menuItems2);
         if (condition) {
            GameCanvas.menu.menuSelectedItem = 0;
         } else {
            GameCanvas.menu.menuSelectedItem = 1;
         }
      }

      if (idAction == 9) {
         Rms.saveRMSInt("lowGraphic", 1);
         GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
      }

      if (idAction == 10) {
         Rms.saveRMSInt("lowGraphic", 0);
         GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
      }

      if (idAction == 11) {
         if (GameCanvas.loginScr == null) {
            GameCanvas.loginScr = new LoginScr();
         }

         GameCanvas.loginScr.switchToMe();
         if ((p = Rms.loadRMSString(Rms.RMS_userAo + ipSelect)) != null && !p.equals("")) {
            GameCanvas.loginScr.isLogin2 = true;
            LoginScr.isLoggingIn = true;
            GameCanvas.connect();
            Service.gI().login((java.lang.String) p, "", "2.5.2", (byte)1);
         } else {
            LoginScr.isLoggingIn = true;
            Service.gI().login2("");
         }

         GameCanvas.startWaitDlg(mResources.PLEASEWAIT);
         Res.outz("tao user ao");
      }

      if (idAction == 12) {
         GameMidlet.instance.exit();
      }

      if (idAction == 13 && (!isGetData || loadScreen)) {
         switch (mSystem.clientType) {
            case 1:
               mSystem.callHotlineJava();
               break;
            case 2:
               mSystem.callHotlineAndroid();
               break;
            case 3:
            case 5:
               mSystem.callHotlineIphone();
            case 4:
            default:
               break;
            case 6:
               mSystem.callHotlineWindowsPhone();
         }
      }

      if (idAction == 14) {
         Command cmdYes = new Command(mResources.YES, this, 15, null);
         Command cmdNo = new Command(mResources.NO, this, 16, null);
         GameCanvas.startYesNoDlg(mResources.deletaDataNote, cmdYes, cmdNo);
      }

      if (idAction == 15) {
         Rms.clearAll();
         GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
      }

      if (idAction == 16) {
         InfoDlg.hide();
         GameCanvas.currentDialog = null;
      }

      if (idAction == 17) {
         loginActionAfterConnect = 0;
         if (GameCanvas.serverScr == null) {
            GameCanvas.serverScr = new ServerScr();
         }

         GameCanvas.serverScr.switchToMe();
      }

      if (idAction == 18) {
         GameCanvas.endDlg();
         InfoDlg.hide();
         if (GameCanvas.serverScr == null) {
            GameCanvas.serverScr = new ServerScr();
         }

         GameCanvas.serverScr.switchToMe();
      }

      if (idAction == 19) {
         if (mSystem.clientType == 1) {
            InfoDlg.hide();
            GameCanvas.currentDialog = null;
         } else {
            testConnect = 0;
         }
      }

      if (idAction == 20) {
         int pendingLoginActionValue;
         if ((pendingLoginActionValue = loginActionAfterConnect > 0 ? loginActionAfterConnect : pendingLoginAction) > 0) {
            clearPendingLoginAction();
            Service.gI().clearPendingLogin();
            Service.gI().resetClientInfoAndPendingLogin();
            Session_ME.gI().close();
            Session_ME.gI2().close();
            this.requestLoginAction(pendingLoginActionValue);
         } else {
            this.selectServer();
         }
      }

      if (idAction == 21) {
         clearPendingLoginAction();
         Service.gI().clearPendingLogin();
         Service.gI().resetClientInfoAndPendingLogin();
         Session_ME.gI().close();
         Session_ME.gI2().close();
         isWaitingForConnection = 0;
         connectionFailureCheckAt = 0L;
         loginActionAfterConnect = 0;
         GameCanvas.endDlg();
      }
   }

   private void requestLoginAction(int loginActionAfterConnectValue) {
      if (nameServer != null && nameServer.length > 0 && (ipSelect < 0 || ipSelect >= nameServer.length)) {
         SetIpSelect(serverPriority >= 0 && serverPriority < nameServer.length ? serverPriority : 0, true);
      }

      boolean condition = pendingLoginAction == loginActionAfterConnectValue;
      if (pendingLoginAction == 0 && loginActionAfterConnect == 0 || condition) {
         if (Service.gI().isMainClientInfoAcknowledged()) {
            this.executeLoginAction(loginActionAfterConnectValue);
         } else {
            pendingLoginAction = loginActionAfterConnectValue;
            pendingLoginStartedAt = mSystem.currentTimeMillis();
            loginActionAfterConnect = loginActionAfterConnectValue;
            GameCanvas.startWaitDlg(mResources.PLEASEWAIT);
            if (!Session_ME.gI().isConnected()) {
               if (Session_ME.gI().connecting) {
                  isWaitingForConnection = 1;
                  connectionFailureCheckAt = mSystem.currentTimeMillis() + 1000L;
               } else {
                  isWaitingForConnection = 1;
                  connectionFailureCheckAt = mSystem.currentTimeMillis() + 1000L;
                  GameCanvas.startWaitDlg(mResources.PLEASEWAIT);
                  Service.gI().resetClientInfoAndPendingLogin();
                  Session_ME.gI().close();
                  testConnect = 0;
                  ConnectIP();
               }
            }
         }
      }
   }

   private void executeLoginAction(int value) {
      if (value == 3) {
         Login_New();
      } else {
         if (value == 10100) {
            GameScr.resetClientDataReadiness();
            if (GameCanvas.loginScr == null) {
               GameCanvas.loginScr = new LoginScr();
            }

            GameCanvas.loginScr.switchToMe();
            LoginScr.isLoggingIn = true;
            Service.gI().login2("");
            Res.outz("tao user ao");
            GameCanvas.startWaitDlg();
            if (nameServer != null && ipSelect >= 0 && ipSelect < nameServer.length) {
               LoginScr.serverName = nameServer[ipSelect];
            }
         }
      }
   }

   public static boolean consumePendingLoginAction(boolean condition) {
      if (pendingLoginAction != 0 && Service.gI().isMainClientInfoAcknowledged()) {
         int pendingLoginActionValue = pendingLoginAction;
         clearPendingLoginAction();
         if (!condition) {
            return true;
         }

         if (GameCanvas.serverScreen == null) {
            GameCanvas.serverScreen = new ServerListScreen();
         }

         GameCanvas.serverScreen.executeLoginAction(pendingLoginActionValue);
         return true;
      } else {
         return false;
      }
   }

   public static void clearPendingLoginAction() {
      pendingLoginAction = 0;
      pendingLoginStartedAt = 0L;
      loginActionAfterConnect = 0;
   }

   public final void show2() {
      loginActionAfterConnect = 0;
      isWaitingForConnection = 0;
      connectionFailureCheckAt = 0L;
      GameCanvas.endDlg();
      createDeleteRMS();
      GameScr.cmx = 0;
      GameScr.cmy = 0;
      this.initCommand();
      loadScreen = false;
      percent = 0;
      bigOk = false;
      isGetData = false;
      demPercent = 0;
      Char.isLoadingMap = false;
      ServerListScreen serverListScreen = this;
      if (!loadScreen) {
         (cmdDownload = new Command(mResources.taidulieu, serverListScreen, 2, null)).isFocus = true;
         cmdDownload.x = GameCanvas.w / 2 - mScreen.cmdW / 2;
         cmdDownload.y = GameCanvas.hh + 45;
         if (cmdDownload.y > GameCanvas.h - 26) {
            cmdDownload.y = GameCanvas.h - 26;
         }
      }

      if (!GameCanvas.isTouch) {
         selected = 0;
         serverListScreen.processInput();
      }

      super.switchToMe();
   }

   public static void ConnectIP() {
      if (address != null
         && port != null
         && ipSelect >= 0
         && ipSelect < address.length
         && ipSelect < port.length) {
         GameMidlet.IP = address[ipSelect];
         GameMidlet.PORT = port[ipSelect];
         if (language != null && ipSelect < language.length) {
            mResources.loadLanguague(language[ipSelect]);
         }

         if (nameServer != null && ipSelect < nameServer.length) {
            LoginScr.serverName = nameServer[ipSelect];
         }

         GameCanvas.connect();
      } else {
         Res.err("ConnectIP: invalid ipSelect = " + ipSelect);
      }
   }

   public static void SetIpSelect(int index, boolean issave) {
      Res.err(">>>>SetIpSelect: " + index + "  save:" + issave);
      int ipSelectValue = ipSelect;
      if (nameServer != null && nameServer.length > 0) {
         if (index < 0 || index >= nameServer.length) {
            index = serverPriority >= 0 && serverPriority < nameServer.length ? serverPriority : 0;
         }
      } else if (index < 0) {
         index = 0;
      }

      ipSelect = index;
      if (ipSelectValue != index) {
         Service.gI().resetClientInfoAndPendingLogin();
         clearPendingLoginAction();
      }

      if (issave) {
         Rms.saveRMSInt(RMS_svselect, ipSelect);
         Res.err("2>>>saveRMSInt:  RMS_svselect == " + ipSelect);
      }
   }

   public static void Login_New() {
      GameScr.resetClientDataReadiness();
      if (GameCanvas.loginScr == null) {
         GameCanvas.loginScr = new LoginScr();
      }

      GameCanvas.loginScr.switchToMe();
      boolean condition = false;
      boolean condition2 = false;
      String username = Rms.loadRMSString(Rms.RMS_userAo + ipSelect);

      try {
         if (!Rms.loadRMSString(Rms.RMS_acc).equals("")) {
            condition = true;
         }

         if (!username.equals("")) {
            condition2 = true;
         }
      } catch (Exception ignoredException) {
      }

      GameCanvas.connect();
      if (!condition && !condition2) {
         GameCanvas.connect();
         if (username != null && !username.equals("")) {
            GameCanvas.loginScr.isLogin2 = true;
            LoginScr.isLoggingIn = true;
            Service.gI().login(username, "", "2.5.2", (byte)1);
         } else {
            LoginScr.isLoggingIn = true;
            Service.gI().login2("");
         }

         Rms.saveRMSInt(RMS_svselect, ipSelect);
         if (Session_ME.gI().connected) {
            GameCanvas.startWaitDlg();
         } else {
            GameCanvas.startOK(mResources.plsRestartGame, 8884, null);
         }
      } else {
         GameCanvas.loginScr.doLogin();
      }

      LoginScr.serverName = nameServer[ipSelect];
   }

   public static void saveRMS_ExtraLink() {
      if (typeClass != null) {
         ByteArrayOutputStream buffer = new ByteArrayOutputStream();
         DataOutputStream output = new DataOutputStream(buffer);

         try {
            output.writeByte((byte)typeClass.length);

            for (int listCharIndex = 0; listCharIndex < typeClass.length; listCharIndex++) {
               output.writeByte(typeClass[listCharIndex]);
               if (typeClass[listCharIndex] >= 0 && listChar != null && listChar[listCharIndex] != null) {
                  output.writeShort((short)listChar[listCharIndex].head);
                  output.writeShort((short)listChar[listCharIndex].body);
                  output.writeShort((short)listChar[listCharIndex].leg);
                  output.writeShort((short)listChar[listCharIndex].bag);
                  output.writeUTF(listChar[listCharIndex].cName);
               }
            }

            Rms.saveRMS(RMS_NR_Extralink, buffer.toByteArray());
            output.close();
         } catch (Exception ignoredException) {
         }
      }
   }
}
