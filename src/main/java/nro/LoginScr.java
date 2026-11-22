package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class LoginScr extends mScreen implements IActionListener {
   public TField tfUser;
   public TField tfPass;
   public static boolean isContinueToLogin = false;
   private int focus;
   private int wC;
   private int yL;
   private int defYL;
   private boolean isCheck = false;
   private boolean isRes = false;
   private Command cmdLogin;
   private Command cmdFogetPass;
   private Command cmdRes;
   private Command cmdMenu;
   private Command cmdBackFromRegister;
   private Command cmdBack;
   public static boolean isUpdateAll;
   public static boolean isUpdateData;
   public static boolean isUpdateMap;
   public static boolean isUpdateSkill;
   public static boolean isUpdateItem;
   public static String serverName;
   public static Image imgTitle;
   public static short timeLogin;
   public static long lastTimeLogin;
   public static long currTimeLogin;
   private int yt;
   private Command cmdOK;
   private int xLog;
   private int yLog;
   private int wP;
   private int tipid = -1;
   public boolean isLogin2 = false;
   private int v = 2;
   private int g = 0;
   private int ylogo = -40;
   private int dir = 1;
   private Command cmdCallHotline;
   public static boolean isLoggingIn;

   static {
      int[] intValues = new int[]{0, 8, 2, 6, 9};
   }

   public final void switchToMe() {
      isLoggingIn = false;
      SoundMn.gI();
      SoundMn.stopAll();
      this.tfUser.isFocus = true;
      this.tfPass.isFocus = false;
      if (GameCanvas.isTouch) {
         this.tfUser.isFocus = false;
      }

      GameCanvas.loadBG(0);
      super.left = new Command(mResources.BACK, this, 101, null);
      super.switchToMe();
   }

   public LoginScr() {
      this.yLog = GameCanvas.hh - 30;
      if ((TileMap.bgID = (byte)(System.currentTimeMillis() % 9L)) == 5 || TileMap.bgID == 6) {
         TileMap.bgID = 4;
      }

      GameScr.loadCamera(-1, -1);
      GameScr.cmx = 100;
      GameScr.cmy = 200;
      if (GameCanvas.h > 200) {
         this.defYL = GameCanvas.hh - 80;
      } else {
         this.defYL = GameCanvas.hh - 65;
      }

      this.yL = -50;
      int wCValue = GameCanvas.w >= 200 ? 160 : 140;
      this.wC = wCValue;
      this.yt = GameCanvas.hh - mScreen.ITEM_HEIGHT - 5;
      if (GameCanvas.h <= 160) {
         this.yt = 20;
      }

      this.tfUser = new TField();
      this.tfUser.y = GameCanvas.hh - mScreen.ITEM_HEIGHT - 9;
      this.tfUser.width = this.wC;
      this.tfUser.height = mScreen.ITEM_HEIGHT + 2;
      this.tfUser.isFocus = true;
      this.tfUser.setIputType(0);
      this.tfUser.name = (mResources.language == 2 ? "" : mResources.phone + "/") + mResources.email;
      this.tfPass = new TField();
      this.tfPass.y = GameCanvas.hh - 4;
      this.tfPass.setIputType(2);
      this.tfPass.width = this.wC;
      this.tfPass.height = mScreen.ITEM_HEIGHT + 2;
      this.yt += 35;
      this.isCheck = true;
      if ((wCValue = Rms.loadRMSInt(Rms.RMS_check)) == 1) {
         this.isCheck = true;
      } else if (wCValue == 2) {
         this.isCheck = false;
      }

      this.tfUser.setText(Rms.loadRMSString(Rms.RMS_acc));
      this.tfPass.setText(Rms.loadRMSString(Rms.RMS_pass));
      this.tfUser.setText(null);
      this.tfPass.setText(null);
      if (this.cmdCallHotline == null) {
         this.cmdCallHotline = new Command("Gọi hotline", this, 13, null);
         this.cmdCallHotline.x = GameCanvas.w - 75;
         if (mSystem.clientType == 1 && !GameCanvas.isTouch) {
            this.cmdCallHotline.y = GameCanvas.h - 20;
         } else {
            this.cmdCallHotline.y = 8;
         }
      }

      this.focus = 0;
      this.cmdLogin = new Command(
         GameCanvas.w > 200 ? mResources.login : mResources.login2, GameCanvas.instance, 888393, null
      );
      new Command(mResources.remember, this, 2001, null);
      this.cmdRes = new Command(mResources.register, this, 2002, null);
      this.cmdBackFromRegister = new Command(mResources.CANCEL, this, 10021, null);
      this.cmdBack = new Command(mResources.BACK, this, 101, null);
      super.left = this.cmdMenu = new Command(mResources.MENU, this, 2003, null);
      if (GameCanvas.isTouch) {
         this.cmdLogin.x = GameCanvas.w / 2 + 8;
         this.cmdMenu.x = GameCanvas.w / 2 - mScreen.cmdW - 8;
         if (GameCanvas.h >= 200) {
            this.cmdLogin.y = this.yLog + 110;
            this.cmdMenu.y = this.yLog + 110;
         }

         this.cmdBackFromRegister.x = GameCanvas.w / 2 + 3;
         this.cmdBackFromRegister.y = this.yLog + 110;
         this.cmdRes.x = GameCanvas.w / 2 - 84;
         this.cmdRes.y = this.cmdMenu.y;
         this.cmdBack.x = 2;
         this.cmdBack.y = GameCanvas.h - mScreen.cmdH;
      }

      this.wP = 170;
      short shortValue = 184;
      if (184 >= GameCanvas.w) {
         shortValue = 152;
      }

      this.xLog = GameCanvas.w / 2 - shortValue / 2;
      this.yLog = GameCanvas.hh - 30;
      if (GameCanvas.w < 200) {
         ;
      }

      this.tfUser.x = this.xLog + 10;
      this.tfUser.y = this.yLog + 20;
      this.cmdOK = new Command(mResources.OK, this, 2008, null);
      this.cmdOK.x = GameCanvas.w / 2 - 84;
      this.cmdOK.y = this.cmdLogin.y;
      this.cmdFogetPass = new Command(mResources.forgetPass, this, 1003, null);
      this.cmdFogetPass.x = GameCanvas.w / 2 + 3;
      this.cmdFogetPass.y = this.cmdLogin.y;
      super.center = this.cmdOK;
      super.left = this.cmdFogetPass;
   }

   public final void doLogin() {
      if (!isLoggingIn || !Session_ME.gI().isConnected()) {
         Res.outz("user ao= " + Rms.loadRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect));
         String username = Rms.loadRMSString(Rms.RMS_acc);
         String pass = Rms.loadRMSString(Rms.RMS_pass);
         if ((username == null || username.equals(""))
            && Rms.loadRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect) != null
            && !Rms.loadRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect).equals("")) {
            this.isLogin2 = true;
         } else {
            this.isLogin2 = false;
         }

         Res.outz("isLogin 2= " + this.isLogin2);
         if ((username == null || username.equals("")) && this.isLogin2) {
            username = Rms.loadRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect);
            pass = "a";
         }

         Res.outz("user = " + username + " pass= " + pass);
         if (username != null && pass != null && !username.equals("")) {
            if (pass.equals("")) {
               this.focus = 1;
               this.tfUser.isFocus = false;
               this.tfPass.isFocus = true;
               if (!GameCanvas.isTouch) {
                  super.right = this.tfPass.cmdClear;
               }
            } else {
               if (!Session_ME.gI().isConnected()) {
                  GameCanvas.connect();
               }

               isLoggingIn = true;
               Service.gI().login(username, pass, "2.5.2", (byte)(this.isLogin2 ? 1 : 0));
               Rms.saveRMSInt(ServerListScreen.RMS_svselect, ServerListScreen.ipSelect);
               if (Session_ME.gI().connected) {
                  GameCanvas.startWaitDlg();
               } else {
                  GameCanvas.startOK(mResources.maychutathoacmatsong + " [0]", 8884, null);
               }

               this.focus = 0;
               if (!this.isLogin2) {
                  this.actRegisterLeft();
               }

               GameCanvas.timeBreakLoading = mSystem.currentTimeMillis() + 30000L;
            }
         }
      }
   }

   public final void savePass() {
      if (this.isCheck) {
         Rms.saveRMSInt(Rms.RMS_check, 1);
         Rms.saveRMSString(Rms.RMS_acc, this.tfUser.getText().toLowerCase().trim());
         Rms.saveRMSString(Rms.RMS_pass, this.tfPass.getText().toLowerCase().trim());
      } else {
         Rms.saveRMSInt(Rms.RMS_check, 2);
         Rms.saveRMSString(Rms.RMS_acc, "");
         Rms.saveRMSString(Rms.RMS_pass, "");
      }
   }

   public final void update() {
      if (timeLogin > 0) {
         GameCanvas.startWaitDlg();
         if ((currTimeLogin = System.currentTimeMillis()) - lastTimeLogin >= 1000L) {
            if (--timeLogin == 0) {
               GameCanvas.loginScr.doLogin();
            }

            lastTimeLogin = currTimeLogin;
         }
      }

      if (this.isLogin2 && !this.isRes) {
         this.tfUser.name = (mResources.language == 2 ? "" : mResources.phone + "/")
            + mResources.email;
         this.tfPass.name = mResources.password;
         this.tfUser.isPaintCarret = false;
         this.tfPass.isPaintCarret = false;
         this.tfUser.update();
         this.tfPass.update();
      } else {
         this.tfUser.name = (mResources.language == 2 ? "" : mResources.phone + "/")
            + mResources.email;
         this.tfPass.name = mResources.password;
         this.tfUser.update();
         this.tfPass.update();
      }

      for (int effect2Index = 0; effect2Index < Effect2.vEffect2.size(); effect2Index++) {
         ((Effect2)Effect2.vEffect2.elementAt(effect2Index)).update();
      }

      if (isUpdateAll && !isUpdateData && !isUpdateItem && !isUpdateMap && !isUpdateSkill) {
         isUpdateAll = false;
         System.gc();
         Service.gI().finishUpdate();
      }

      if (++GameScr.cmx > GameCanvas.w * 3 + 100) {
         GameScr.cmx = 100;
      }

      if (ChatPopup.currChatPopup == null) {
         GameCanvas.debug();
         GameCanvas.debug();
         GameCanvas.debug();
         LoginScr loginScr = this;
         if (this.defYL != loginScr.yL) {
            loginScr.yL = loginScr.yL + (loginScr.defYL - loginScr.yL >> 1);
         }

         GameCanvas.debug();
         GameCanvas.debug();
         if (this.g >= 0) {
            this.ylogo = this.ylogo + this.dir * this.g;
            this.g = this.g + this.dir * this.v;
            if (this.g <= 0) {
               this.dir = -this.dir;
            }

            if (this.ylogo > 0) {
               this.dir = -this.dir;
               this.g = this.g - 2 * this.v;
            }
         }

         GameCanvas.debug();
         if (this.tipid >= 0 && GameCanvas.gameTick % 100 == 0) {
            loginScr = this;
            this.tipid++;
            if (loginScr.tipid >= mResources.tips.length) {
               loginScr.tipid = 0;
            }

            if (GameCanvas.currentDialog == GameCanvas.msgdlg && GameCanvas.msgdlg.isWait) {
               GameCanvas.msgdlg.setInfo(mResources.tips[loginScr.tipid]);
            }
         }

         if (this.isLogin2 && !this.isRes) {
            this.tfUser.isPaintCarret = false;
            this.tfPass.isPaintCarret = false;
            this.tfUser.update();
            this.tfPass.update();
         } else {
            this.tfUser.name = (mResources.language == 2 ? "" : mResources.phone + "/")
               + mResources.email;
            this.tfPass.name = mResources.password;
            this.tfUser.update();
            this.tfPass.update();
         }

         if (GameCanvas.isTouch) {
            if (this.isRes) {
               super.center = this.cmdRes;
               super.left = this.cmdBackFromRegister;
            } else {
               super.center = this.cmdOK;
               super.left = this.cmdFogetPass;
            }

            if (this.cmdBack != null && this.cmdBack.isPointerPressInside()) {
               this.cmdBack.performAction();
               return;
            }
         } else {
            if (this.isRes) {
               super.center = this.cmdRes;
               super.left = this.cmdBackFromRegister;
               return;
            }

            super.center = this.cmdOK;
            super.left = this.cmdFogetPass;
         }
      }
   }

   public final void keyPress(int keyCode) {
      if (this.tfUser.isFocus) {
         this.tfUser.keyPressed(keyCode);
      } else if (this.tfPass.isFocus) {
         this.tfPass.keyPressed(keyCode);
      }

      super.keyPress(keyCode);
   }

   public final void paint(mGraphics g) {
      GameCanvas.debug();
      GameCanvas.paintBGGameScr(g);
      GameCanvas.debug();
      int y = this.tfUser.y - 50;
      if (GameCanvas.h <= 220) {
         y += 5;
      }

      mFont.tahoma_7_white.drawString(g, "v2.5.2", GameCanvas.w - 2, 17, 1, mFont.tahoma_7_grey);
      if (mSystem.clientType == 1 && !GameCanvas.isTouch) {
         mFont.tahoma_7_white
            .drawString(
               g, ServerListScreen.linkweb, GameCanvas.w - 2, GameCanvas.h - 15, 1, mFont.tahoma_7_grey
            );
      } else {
         mFont.tahoma_7_white
            .drawString(g, ServerListScreen.linkweb, GameCanvas.w - 2, 2, 1, mFont.tahoma_7_grey);
      }

      if (GameCanvas.currentDialog == null) {
         int w = GameCanvas.w >= 200 ? 180 : 160;
         PopUp.paintPopUp(g, this.xLog, this.yLog - 10, w, 105, -1, true);
         if (GameCanvas.h > 160 && imgTitle != null) {
            g.drawImage(imgTitle, ((int) (GameCanvas.hw)), ((int) (y)), 3);
         }

         GameCanvas.debug();
         short shortValue = 184;
         if (184 >= GameCanvas.w) {
            shortValue = 152;
         }

         this.xLog = GameCanvas.w / 2 - shortValue / 2;
         this.tfUser.x = this.xLog + 10;
         this.tfUser.y = this.yLog + 20;
         this.tfPass.x = this.xLog + 10;
         this.tfPass.y = this.yLog + 55;
         this.tfUser.paint(g);
         this.tfPass.paint(g);
         if (GameCanvas.w < 176) {
            mFont.tahoma_7b_green2
               .drawString(
                  g, mResources.acc + ":", this.tfUser.x - 35, this.tfUser.y + 7, 0
               );
            mFont.tahoma_7b_green2
               .drawString(
                  g, mResources.pwd + ":", this.tfPass.x - 35, this.tfPass.y + 7, 0
               );
            mFont.tahoma_7b_green2
               .drawString(
                  g,
                  mResources.server + ": " + serverName,
                  GameCanvas.w / 2,
                  this.tfPass.y + 32,
                  2
               );
         }
      }

      super.paint(g);
      this.cmdBack.paint(g);
   }

   public final void updateKey() {
      if (GameCanvas.isTouch && this.cmdCallHotline != null && this.cmdCallHotline.isPointerPressInside()) {
         this.cmdCallHotline.performAction();
      }

      if (!isContinueToLogin) {
         if (!GameCanvas.isTouch) {
            if (this.tfUser.isFocus) {
               super.right = this.tfUser.cmdClear;
            } else {
               super.right = this.tfPass.cmdClear;
            }
         }

         if (GameCanvas.keyPressed[2]) {
            this.focus--;
            if (this.focus < 0) {
               this.focus = 1;
            }
         } else if (GameCanvas.keyPressed[8]) {
            this.focus++;
            if (this.focus > 1) {
               this.focus = 1;
            }
         }

         if (GameCanvas.keyPressed[2] || GameCanvas.keyPressed[8]) {
            GameCanvas.clearKeyPressed();
            if (!this.isLogin2 || this.isRes) {
               if (this.focus == 1) {
                  this.tfUser.isFocus = false;
                  this.tfPass.isFocus = true;
               } else if (this.focus == 0) {
                  this.tfUser.isFocus = true;
                  this.tfPass.isFocus = false;
               } else {
                  this.tfUser.isFocus = false;
                  this.tfPass.isFocus = false;
               }
            }
         }

         if (GameCanvas.isTouch) {
            if (this.isRes) {
               super.center = this.cmdRes;
               super.left = this.cmdBackFromRegister;
            } else {
               super.center = this.cmdOK;
               super.left = this.cmdFogetPass;
            }
         } else if (this.isRes) {
            super.center = this.cmdRes;
            super.left = this.cmdBackFromRegister;
         } else {
            super.center = this.cmdOK;
            super.left = this.cmdFogetPass;
         }

         if (GameCanvas.isPointerJustRelease && (!this.isLogin2 || this.isRes)) {
            if (GameCanvas.isPointerHoldIn(
               this.tfUser.x,
               this.tfUser.y,
               this.tfUser.width,
               this.tfUser.height
            )) {
               this.focus = 0;
            } else if (GameCanvas.isPointerHoldIn(
               this.tfPass.x,
               this.tfPass.y,
               this.tfPass.width,
               this.tfPass.height
            )) {
               this.focus = 1;
            }
         }

         super.updateKey();
         GameCanvas.clearKeyPressed();
      }
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 13:
            switch (mSystem.clientType) {
               case 1:
                  mSystem.callHotlineJava();
                  return;
               case 2:
                  mSystem.callHotlineAndroid();
               case 4:
               default:
                  return;
               case 3:
               case 5:
                  mSystem.callHotlineIphone();
                  return;
               case 6:
                  mSystem.callHotlineWindowsPhone();
                  return;
            }
         case 101:
            Service.gI().clearPendingLogin();
            ServerListScreen.clearPendingLoginAction();
            GameCanvas.serverScreen.switchToMe();
            return;
         case 1000:
            try {
               GameMidlet.instance.platformRequest((java.lang.String) p);
            } catch (Exception exception) {
               exception.printStackTrace();
            }

            GameCanvas.endDlg();
            return;
         case 1001:
            GameCanvas.endDlg();
            this.isRes = false;
            return;
         case 1002:
            GameCanvas.startWaitDlg();
            String username;
            if ((username = Rms.loadRMSString("userAo" + ServerListScreen.ipSelect)) != null && !username.equals("")) {
               GameCanvas.loginScr.isLogin2 = true;
               isLoggingIn = true;
               GameCanvas.connect();
               Service.gI().login(username, "", "2.5.2", (byte)1);
               return;
            }

            isLoggingIn = true;
            Service.gI().login2("");
            return;
         case 1003:
            GameCanvas.startOKDlg(mResources.goToWebForPassword);
            return;
         case 1004:
            ServerListScreen.doUpdateServer();
            GameCanvas.serverScreen.switchToMe();
            return;
         case 1005:
            try {
               GameMidlet.instance.platformRequest("http://ngocrongonline.com");
               return;
            } catch (Exception exception2) {
               exception2.printStackTrace();
               return;
            }
         case 2000:
            return;
         case 2001:
            if (this.isCheck) {
               this.isCheck = false;
               return;
            }

            this.isCheck = true;
            return;
         case 2002:
            LoginScr loginScr = this;
            if (this.tfUser.getText().equals("")) {
               GameCanvas.startOKDlg(mResources.userBlank);
            } else {
               loginScr.tfUser.getText().toCharArray();
               if (loginScr.tfPass.getText().equals("")) {
                  GameCanvas.startOKDlg(mResources.passwordBlank);
               } else {
                  if (loginScr.tfUser.getText().length() >= 5) {
                     String info = null;
                     boolean condition;
                     if (mResources.language == 2) {
                        if (loginScr.tfUser.getText().indexOf("@") == -1 || loginScr.tfUser.getText().indexOf(".") == -1) {
                           info = mResources.emailInvalid;
                        }

                        condition = false;
                     } else {
                        try {
                           Long.parseLong(loginScr.tfUser.getText());
                           if (loginScr.tfUser.getText().length() < 8
                              || loginScr.tfUser.getText().length() > 12
                              || !loginScr.tfUser.getText().startsWith("0") && !loginScr.tfUser.getText().startsWith("84")) {
                              info = mResources.phoneInvalid;
                           }

                           condition = true;
                        } catch (Exception ignoredException) {
                           if (loginScr.tfUser.getText().indexOf("@") == -1 || loginScr.tfUser.getText().indexOf(".") == -1) {
                              info = mResources.emailInvalid;
                           }

                           condition = false;
                        }
                     }

                     if (info != null) {
                        GameCanvas.startOKDlg(info);
                     } else {
                        GameCanvas.msgdlg
                           .setInfo(
                              mResources.plsCheckAcc
                                 + (condition ? mResources.phone + ": " : mResources.email + ": ")
                                 + loginScr.tfUser.getText()
                                 + "\n"
                                 + mResources.password
                                 + ": "
                                 + loginScr.tfPass.getText(),
                              new Command(mResources.ACCEPT, loginScr, 4000, null),
                              null,
                              new Command(mResources.NO, GameCanvas.instance, 8882, null)
                           );
                     }

                     GameCanvas.currentDialog = GameCanvas.msgdlg;
                     return;
                  }

                  GameCanvas.startOKDlg(mResources.accTooShort);
               }
            }
            break;
         case 2003:
            LoginScr loginScr2 = this;
            MyVector menuItems;
            (menuItems = new MyVector("vMenu Login")).addElement(new Command(mResources.registerNewAcc, loginScr2, 2004, null));
            if (!loginScr2.isLogin2) {
               menuItems.addElement(new Command(mResources.selectServer, loginScr2, 1004, null));
            }

            menuItems.addElement(new Command(mResources.forgetPass, loginScr2, 1003, null));
            menuItems.addElement(new Command(mResources.website, loginScr2, 1005, null));
            if (Rms.loadRMSInt("lowGraphic") == 1) {
               menuItems.addElement(new Command(mResources.increase_vga, loginScr2, 10041, null));
            } else {
               menuItems.addElement(new Command(mResources.decrease_vga, loginScr2, 10042, null));
            }

            menuItems.addElement(new Command(mResources.EXIT, GameCanvas.instance, 8885, null));
            GameCanvas.menu.startAt(menuItems);
            return;
         case 2004:
            this.actRegister();
            return;
         case 2008:
            Rms.saveRMSString(Rms.RMS_acc, this.tfUser.getText().trim());
            Rms.saveRMSString(Rms.RMS_pass, this.tfPass.getText().trim());
            if (ServerListScreen.loadScreen) {
               GameCanvas.serverScreen.switchToMe();
               return;
            }

            GameCanvas.serverScreen.show2();
            return;
         case 4000:
            p = this.tfUser.getText();
            LoginScr loginScr3 = this;
            GameCanvas.startWaitDlg(mResources.CONNECTING);
            GameCanvas.connect();
            GameCanvas.startWaitDlg(mResources.REGISTERING);
            loginScr3.tfPass.getText();
            Service service = Service.gI();
            String text = loginScr3.tfPass.getText();
            String rMSString = Rms.loadRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect);
            Rms.loadRMSString("passAo");
            service.requestRegister((java.lang.String) p, text, rMSString, "2.5.2");
            Rms.saveRMSString("acc", (java.lang.String) p);
            Rms.saveRMSString(Rms.RMS_pass, loginScr3.tfPass.getText());
            break;
         case 10021:
            this.actRegisterLeft();
            return;
         case 10041:
            Rms.saveRMSInt("lowGraphic", 0);
            GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
            return;
         case 10042:
            Rms.saveRMSInt("lowGraphic", 1);
            GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
            return;
      }
   }

   private void actRegisterLeft() {
      if (this.isLogin2) {
         this.doLogin();
      } else {
         this.isRes = false;
         this.tfPass.isFocus = false;
         this.tfUser.isFocus = true;
         super.left = this.cmdMenu;
      }
   }

   public final void actRegister() {
      GameCanvas.endDlg();
      GameCanvas.startOKDlg(mResources.regNote);
      this.isRes = true;
      this.tfPass.isFocus = false;
      this.tfUser.isFocus = true;
   }

   public static void backToRegister() {
      GameCanvas.timeBreakLoading = mSystem.currentTimeMillis() + 30000L;
      ServerListScreen.countDieConnect = 0;
      if (GameCanvas.loginScr.isLogin2) {
         GameCanvas.startYesNoDlg(
            mResources.note,
            new Command(mResources.YES, GameCanvas.panel, 10019, null),
            new Command(mResources.NO, GameCanvas.panel, 10020, null)
         );
      } else {
         ServerListScreen.isAutoLogin = false;
         ServerScr.isShowSv_HaveChar = false;
         GameCanvas.doResetToLoginScr();
      }
   }
}
