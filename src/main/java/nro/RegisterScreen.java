package nro;

import main.GameCanvas;
import main.GameMidlet;

public final class RegisterScreen extends mScreen implements IActionListener {
   private TField tfUser;
   private TField tfNgay;
   private TField tfThang;
   private TField tfNam;
   private TField tfDiachi;
   private TField tfCMND;
   private TField tfNgayCap;
   private TField tfNoiCap;
   private TField tfSodt;
   private static boolean field_j_fba2325f = false;
   private int focus;
   private int yL;
   private int defYL;
   private boolean isCheck = false;
   private boolean isRes = false;
   private Command cmdLogin;
   private Command cmdFogetPass;
   private Command cmdRes;
   private Command cmdMenu;
   private Command cmdBackFromRegister;
   private int yt;
   private Command cmdOK;
   private int xLog;
   private int yLog;
   private int wP;
   private int tipid = -1;
   private boolean field_A_1514f099 = false;
   private int v = 2;
   private int g = 0;
   private int ylogo = -40;
   private int dir = 1;

   static {
      int[] intValues = new int[]{0, 8, 2, 6, 9};
   }

   public final void switchToMe() {
      System.out.println("Res switch");
      SoundMn.gI();
      SoundMn.stopAll();
      this.tfUser.isFocus = true;
      this.tfNgay.isFocus = false;
      if (GameCanvas.isTouch) {
         this.tfUser.isFocus = false;
      }

      super.switchToMe();
   }

   public RegisterScreen(byte haveName) {
      this.yLog = 130;
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
      this.yt = GameCanvas.hh - mScreen.ITEM_HEIGHT - 5;
      if (GameCanvas.h <= 160) {
         this.yt = 20;
      }

      this.tfSodt = new TField();
      this.tfSodt.setIputType(1);
      this.tfSodt.width = 220;
      this.tfSodt.height = mScreen.ITEM_HEIGHT + 2;
      this.tfSodt.name = "Số điện thoại";
      if (haveName == 1) {
         this.tfSodt.setText("01234567890");
      }

      this.tfUser = new TField();
      this.tfUser.width = 220;
      this.tfUser.height = mScreen.ITEM_HEIGHT + 2;
      this.tfUser.isFocus = true;
      this.tfUser.name = "Họ và tên";
      if (haveName == 1) {
         this.tfUser.setText("Nguyễn Văn A");
      }

      this.tfUser.setIputType(0);
      this.tfNgay = new TField();
      this.tfNgay.setIputType(1);
      this.tfNgay.width = 70;
      this.tfNgay.height = mScreen.ITEM_HEIGHT + 2;
      this.tfNgay.name = "Ngày sinh";
      if (haveName == 1) {
         this.tfNgay.setText("01");
      }

      this.tfThang = new TField();
      this.tfThang.setIputType(1);
      this.tfThang.width = 70;
      this.tfThang.height = mScreen.ITEM_HEIGHT + 2;
      this.tfThang.name = "Tháng sinh";
      if (haveName == 1) {
         this.tfThang.setText("01");
      }

      this.tfNam = new TField();
      this.tfNam.setIputType(1);
      this.tfNam.width = 70;
      this.tfNam.height = mScreen.ITEM_HEIGHT + 2;
      this.tfNam.name = "Năm sinh";
      if (haveName == 1) {
         this.tfNam.setText("1980");
      }

      this.tfDiachi = new TField();
      this.tfDiachi.setIputType(0);
      this.tfDiachi.width = 220;
      this.tfDiachi.height = mScreen.ITEM_HEIGHT + 2;
      this.tfDiachi.name = "Địa chỉ đăng ký thường trú";
      if (haveName == 1) {
         this.tfDiachi.setText("123 đường số 1, Quận 1, TP.HCM");
      }

      this.tfCMND = new TField();
      this.tfCMND.setIputType(1);
      this.tfCMND.width = 220;
      this.tfCMND.height = mScreen.ITEM_HEIGHT + 2;
      this.tfCMND.name = "Số Chứng minh nhân dân hoặc số hộ chiếu";
      if (haveName == 1) {
         this.tfCMND.setText("123456789");
      }

      this.tfNgayCap = new TField();
      this.tfNgayCap.setIputType(0);
      this.tfNgayCap.width = 220;
      this.tfNgayCap.height = mScreen.ITEM_HEIGHT + 2;
      this.tfNgayCap.name = "Ngày cấp";
      if (haveName == 1) {
         this.tfNgayCap.setText("01/01/1990");
      }

      this.tfNoiCap = new TField();
      this.tfNoiCap.setIputType(0);
      this.tfNoiCap.width = 220;
      this.tfNoiCap.height = mScreen.ITEM_HEIGHT + 2;
      this.tfNoiCap.name = "Nơi cấp";
      if (haveName == 1) {
         this.tfNoiCap.setText("TP.HCM");
      }

      this.yt += 35;
      this.isCheck = true;
      this.focus = 0;
      this.cmdLogin = new Command(
         GameCanvas.w > 200 ? mResources.login : mResources.login2, GameCanvas.instance, 888393, null
      );
      new Command(mResources.remember, this, 2001, null);
      this.cmdRes = new Command(mResources.register, this, 2002, null);
      this.cmdBackFromRegister = new Command(mResources.CANCEL, this, 10021, null);
      super.left = this.cmdMenu = new Command(mResources.MENU, this, 2003, null);
      if (GameCanvas.isTouch) {
         this.cmdLogin.x = GameCanvas.w / 2 - 100;
         this.cmdMenu.x = GameCanvas.w / 2 - mScreen.cmdW - 8;
         if (GameCanvas.h >= 200) {
            this.cmdLogin.y = GameCanvas.h / 2 - 40;
            this.cmdMenu.y = this.yLog + 110;
         }

         this.cmdBackFromRegister.x = GameCanvas.w / 2 + 3;
         this.cmdBackFromRegister.y = this.yLog + 110;
         this.cmdRes.x = GameCanvas.w / 2 - 84;
         this.cmdRes.y = this.cmdMenu.y;
      }

      this.wP = 170;
      short shortValue = 184;
      if (184 >= GameCanvas.w) {
         shortValue = 152;
      }

      this.xLog = GameCanvas.w / 2 - shortValue / 2;
      this.yLog = 5;
      if (GameCanvas.w < 200) {
         ;
      }

      this.tfUser.x = this.xLog + 10;
      this.tfUser.y = this.yLog + 20;
      this.cmdOK = new Command(mResources.OK, this, 2008, null);
      this.cmdOK.x = 260;
      this.cmdOK.y = GameCanvas.h - 60;
      this.cmdFogetPass = new Command("Thoát", this, 1003, null);
      this.cmdFogetPass.x = 260;
      this.cmdFogetPass.y = GameCanvas.h - 30;
      this.cmdOK.x = GameCanvas.w / 2 - 80;
      this.cmdFogetPass.x = GameCanvas.w / 2 + 10;
      this.cmdFogetPass.y = this.cmdOK.y = GameCanvas.h - 25;
      super.center = this.cmdOK;
      super.left = this.cmdFogetPass;
   }

   public final void update() {
      this.tfUser.update();
      this.tfSodt.update();
      this.tfNgay.update();
      this.tfThang.update();
      this.tfNam.update();

      for (int effect2Index = 0; effect2Index < Effect2.vEffect2.size(); effect2Index++) {
         ((Effect2)Effect2.vEffect2.elementAt(effect2Index)).update();
      }

      if (++GameScr.cmx > GameCanvas.w * 3 + 100) {
         GameScr.cmx = 100;
      }

      if (ChatPopup.currChatPopup == null) {
         GameCanvas.debug();
         GameCanvas.debug();
         GameCanvas.debug();
         RegisterScreen registerScreen = this;
         if (this.defYL != registerScreen.yL) {
            registerScreen.yL = registerScreen.yL + (registerScreen.defYL - registerScreen.yL >> 1);
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
            registerScreen = this;
            this.tipid++;
            if (registerScreen.tipid >= mResources.tips.length) {
               registerScreen.tipid = 0;
            }

            if (GameCanvas.currentDialog == GameCanvas.msgdlg && GameCanvas.msgdlg.isWait) {
               GameCanvas.msgdlg.setInfo(mResources.tips[registerScreen.tipid]);
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
      }
   }

   public final void keyPress(int keyCode) {
      if (this.tfUser.isFocus) {
         this.tfUser.keyPressed(keyCode);
      } else if (this.tfNgay.isFocus) {
         this.tfNgay.keyPressed(keyCode);
      }

      super.keyPress(keyCode);
   }

   public final void paint(mGraphics g) {
      GameCanvas.debug();
      GameCanvas.paintBGGameScr(g);
      GameCanvas.debug();
      if (ChatPopup.currChatPopup == null) {
         if (ChatPopup.serverChatPopUp == null) {
            if (GameCanvas.currentDialog == null) {
               this.xLog = GameCanvas.hw - 120;
               this.yLog = (GameCanvas.h - 110) / 2;
               PopUp.paintPopUp(g, this.xLog, this.yLog, 240, 110, -1, true);
               GameCanvas.debug();
               this.tfUser.x = this.xLog + 10;
               this.tfUser.y = this.yLog + 15;
               this.tfSodt.x = this.tfUser.x;
               this.tfSodt.y = this.tfUser.y + 30;
               this.tfNgay.x = this.xLog + 10;
               this.tfNgay.y = this.tfSodt.y + 30;
               this.tfThang.x = this.tfNgay.x + 75;
               this.tfThang.y = this.tfNgay.y;
               this.tfNam.x = this.tfThang.x + 75;
               this.tfNam.y = this.tfThang.y;
               mFont.tahoma_7b_focus.drawString(g, "Cập nhật thông tin", GameCanvas.hw, this.yLog + 2, 2);
               this.tfUser.paint(g);
               this.tfSodt.paint(g);
               this.tfNgay.paint(g);
               this.tfThang.paint(g);
               this.tfNam.paint(g);
            }

            GameCanvas.resetTrans(g);
            String text = "2.5.2";
            g.setColor(GameCanvas.skyColor);
            g.fillRect(GameCanvas.w - 40, 4, 36, 11);
            mFont.tahoma_7_grey.drawString(g, text, GameCanvas.w - 22, 4, 2);
            g.drawImage(GameCanvas.img18, ((int) (10)), ((int) (10)), 0);
            super.paint(g);
         }
      }
   }

   public final void updateKey() {
      if (!GameCanvas.isTouch) {
         if (this.tfUser.isFocus) {
            super.right = this.tfUser.cmdClear;
         } else {
            super.right = this.tfNgay.cmdClear;
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
         if (this.focus == 1) {
            this.tfUser.isFocus = false;
            this.tfNgay.isFocus = true;
         } else if (this.focus == 0) {
            this.tfUser.isFocus = true;
            this.tfNgay.isFocus = false;
         } else {
            this.tfUser.isFocus = false;
            this.tfNgay.isFocus = false;
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

      if (GameCanvas.isPointerJustRelease) {
         if (GameCanvas.isPointerHoldIn(
            this.tfUser.x,
            this.tfUser.y,
            this.tfUser.width,
            this.tfUser.height
         )) {
            this.focus = 0;
         } else if (GameCanvas.isPointerHoldIn(
            this.tfNgay.x,
            this.tfNgay.y,
            this.tfNgay.width,
            this.tfNgay.height
         )) {
            this.focus = 1;
         }
      }

      super.updateKey();
      GameCanvas.clearKeyPressed();
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 1000:
            try {
               GameMidlet.instance.platformRequest((String)p);
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
            return;
         case 1003:
            GameCanvas.serverScreen.switchToMe();
            Session_ME.gI().close();
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
            RegisterScreen registerScreen = this;
            if (this.tfUser.getText().equals("")) {
               GameCanvas.startOKDlg(mResources.userBlank);
            } else {
               registerScreen.tfUser.getText().toCharArray();
               if (registerScreen.tfNgay.getText().equals("")) {
                  GameCanvas.startOKDlg(mResources.passwordBlank);
               } else {
                  if (registerScreen.tfUser.getText().length() >= 5) {
                     String info = null;
                     boolean condition;
                     if (mResources.language == 2) {
                        if (registerScreen.tfUser.getText().indexOf("@") == -1 || registerScreen.tfUser.getText().indexOf(".") == -1) {
                           info = mResources.emailInvalid;
                        }

                        condition = false;
                     } else {
                        try {
                           Long.parseLong(registerScreen.tfUser.getText());
                           if (registerScreen.tfUser.getText().length() < 8
                              || registerScreen.tfUser.getText().length() > 12
                              || !registerScreen.tfUser.getText().startsWith("0") && !registerScreen.tfUser.getText().startsWith("84")) {
                              info = mResources.phoneInvalid;
                           }

                           condition = true;
                        } catch (Exception ignoredException) {
                           if (registerScreen.tfUser.getText().indexOf("@") == -1 || registerScreen.tfUser.getText().indexOf(".") == -1) {
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
                                 + registerScreen.tfUser.getText()
                                 + "\n"
                                 + mResources.password
                                 + ": "
                                 + registerScreen.tfNgay.getText(),
                              new Command(mResources.ACCEPT, registerScreen, 4000, null),
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
            RegisterScreen registerScreen2 = this;
            MyVector menuItems;
            (menuItems = new MyVector("vMenu Login")).addElement(new Command(mResources.registerNewAcc, registerScreen2, 2004, null));
            menuItems.addElement(new Command(mResources.selectServer, registerScreen2, 1004, null));
            menuItems.addElement(new Command(mResources.forgetPass, registerScreen2, 1003, null));
            menuItems.addElement(new Command(mResources.website, registerScreen2, 1005, null));
            if (Rms.loadRMSInt("lowGraphic") == 1) {
               menuItems.addElement(new Command(mResources.increase_vga, registerScreen2, 10041, null));
            } else {
               menuItems.addElement(new Command(mResources.decrease_vga, registerScreen2, 10042, null));
            }

            menuItems.addElement(new Command(mResources.EXIT, GameCanvas.instance, 8885, null));
            GameCanvas.menu.startAt(menuItems);
            return;
         case 2004:
            RegisterScreen registerScreen3 = this;
            GameCanvas.endDlg();
            GameCanvas.startOKDlg(mResources.regNote);
            registerScreen3.isRes = true;
            registerScreen3.tfNgay.isFocus = false;
            registerScreen3.tfUser.isFocus = true;
            return;
         case 2008:
            if (!this.tfNgay.getText().equals("")
               && !this.tfThang.getText().equals("")
               && !this.tfNam.getText().equals("")
               && !this.tfSodt.getText().equals("")
               && !this.tfUser.getText().equals("")) {
               GameCanvas.startOKDlg(mResources.PLEASEWAIT);
               Service.gI()
                  .charInfo(
                     this.tfNgay.getText(),
                     this.tfThang.getText(),
                     this.tfNam.getText(),
                     "",
                     "",
                     "",
                     "",
                     this.tfSodt.getText(),
                     this.tfUser.getText()
                  );
               return;
            }

            GameCanvas.startOKDlg("Vui lòng điền đầy đủ thông tin");
            return;
         case 4000:
            this.tfUser.getText();
            break;
         case 10021:
            RegisterScreen registerScreen4 = this;
            this.isRes = false;
            registerScreen4.tfNgay.isFocus = false;
            registerScreen4.tfUser.isFocus = true;
            registerScreen4.left = registerScreen4.cmdMenu;
            return;
         case 10041:
            return;
         case 10042:
            return;
      }
   }
}
