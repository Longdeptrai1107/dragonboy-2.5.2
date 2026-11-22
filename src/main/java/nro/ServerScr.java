package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class ServerScr extends mScreen implements IActionListener {
   private int mainSelect = 0;
   private MyVector vecServer = new MyVector("");
   private Command cmdCheck;
   private int wc;
   private int hc;
   private int w2c;
   private int numw;
   private int numh;
   private Command cmdGlobal;
   private Command cmdVietNam;
   private static String RMS_SELECT_AREA = "area_select";
   private boolean isChooseArea;
   private boolean isPaintNewUi;
   private ListNew list;
   private byte select_Area = 0;
   private byte select_Lang = 0;
   private byte select_typeSv = 0;
   private Command cmdChooseArea;
   private boolean isPaint_select_area;
   private int x;
   private int y;
   private int w;
   private int h;
   private int xName;
   private int yName;
   private int xsub;
   private int ysub;
   private int wsub;
   private int hsub;
   private int xsubpaint;
   private int ysubpaint;
   private int xPop;
   private int yPop;
   private int wPop;
   private int hPop;
   private int xinfo;
   private int yinfo;
   private int winfo;
   private int hinfo;
   private int yBox;
   private int wBox;
   private int hBox;
   private int ntypeSv;
   private int wCheck;
   private int xPopUp_Area;
   private int yPopUp_Area;
   private int htext = 15;
   private String[] strArea;
   private String[] strTypeSV;
   private String[] strTypeSV_info;
   private String strShowAll;
   private static Image[] iconHead;
   public static boolean isShowSv_HaveChar;

   public final void switchToMe() {
      GameCanvas.clearTransientOverlays();
      SoundMn.gI();
      SoundMn.stopAll();
      super.switchToMe();
      if (iconHead == null) {
         iconHead = new Image[3];

         for (int iconHeadIndex = 0; iconHeadIndex < iconHead.length; iconHeadIndex++) {
            iconHead[iconHeadIndex] = mSystem.loadImage("/iconHead_" + iconHeadIndex + ".png");
         }
      }

      this.mainSelect = ServerListScreen.ipSelect;
      this.numw = 1;
      this.numh = 1;
      ServerScr serverScr = this;
      if (GameCanvas.isTouch) {
         if (Rms.loadRMS(RMS_SELECT_AREA) == null) {
            serverScr.isChooseArea = true;
            serverScr.cmdChooseArea = new Command(mResources.OK, serverScr, 999, null);
            serverScr.cmdChooseArea.x = GameCanvas.hw - 38;
            serverScr.cmdChooseArea.y = GameCanvas.hh + 50;
            serverScr.vecServer = new MyVector("");
            serverScr.vecServer.addElement(serverScr.cmdChooseArea);
            serverScr.yBox = GameCanvas.hh - 30;
            serverScr.wBox = 70;
            serverScr.hBox = 20;
         } else {
            serverScr.isChooseArea = false;
            serverScr.Load_RMS_Area();
            serverScr.SetNewSelectMenu(serverScr.select_Area, serverScr.select_typeSv);
         }
      }

      if (!this.isPaintNewUi && !this.isChooseArea) {
         this.cmdGlobal = new Command(this.strArea[0], this, 98, null);
         this.cmdGlobal.x = 0;
         this.cmdGlobal.y = 0;
         this.cmdVietNam = new Command(this.strArea[1], this, 97, null);
         this.cmdVietNam.x = 50;
         this.cmdVietNam.y = 0;
         this.vecServer = new MyVector("");
         this.vecServer.addElement(this.cmdGlobal);
         this.vecServer.addElement(this.cmdVietNam);
         this.sort();
      }
   }

   public ServerScr() {
      String[] texts = new String[]{"Tiếng Việt", "English", "Indo"};
      this.strArea = new String[]{"VIỆT NAM", "GLOBAL"};
      this.strTypeSV = new String[]{"Máy chủ tiêu chuẩn", "Máy chủ Super"};
      this.strTypeSV_info = new String[]{
         "Máy chủ tiêu chuẩn:\nTiến trình game bình thường.",
         "Máy chủ Super:\n -Không thể giao dịch vàng.\n x3 Sức mạnh\n x3 Tiềm năng\n x3 Vàng\n x3 Vật phẩm khác"
      };
      this.strShowAll = "Chỉ hiện thị máy chủ đã chơi.";
      if ((TileMap.bgID = (byte)(System.currentTimeMillis() % 9L)) == 5 || TileMap.bgID == 6) {
         TileMap.bgID = 4;
      }

      GameScr.loadCamera(-1, -1);
      GameScr.cmx = 100;
      GameScr.cmy = 200;
   }

   private void sort() {
      this.mainSelect = ServerListScreen.ipSelect;
      this.w2c = 5;
      this.wc = 90;
      this.hc = mScreen.cmdH;
      this.numw = 2;
      if (this.vecServer.size() > 2) {
         this.numw = GameCanvas.w / (this.wc + this.w2c);
      }

      this.numh = this.vecServer.size() / this.numw + (this.vecServer.size() % this.numw == 0 ? 0 : 1);

      for (int vecServerIndex = 0; vecServerIndex < this.vecServer.size(); vecServerIndex++) {
         Command vecServerValue;
         if ((vecServerValue = (Command)this.vecServer.elementAt(vecServerIndex)) != null) {
            int xValue;
            xValue = (xValue = GameCanvas.hw - this.numw * (this.wc + this.w2c) / 2)
               + vecServerIndex % this.numw * (this.wc + this.w2c);
            int yValue;
            yValue = (yValue = GameCanvas.hh - this.numh * (this.hc + this.w2c) / 2)
               + vecServerIndex / this.numw * (this.hc + this.w2c);
            vecServerValue.x = xValue;
            vecServerValue.y = yValue;
            vecServerValue.w = this.wc;
         }
      }
   }

   public final void update() {
      if (++GameScr.cmx > GameCanvas.w * 3 + 100) {
         GameScr.cmx = 100;
      }

      if (!this.isPaintNewUi) {
         for (int vecServerIndex = 0; vecServerIndex < this.vecServer.size(); vecServerIndex++) {
            Command vecServerValue = (Command)this.vecServer.elementAt(vecServerIndex);
            if (!GameCanvas.isTouch) {
               if (vecServerIndex == this.mainSelect) {
                  if (GameCanvas.gameTick % 10 < 4) {
                     vecServerValue.isFocus = true;
                  } else {
                     vecServerValue.isFocus = false;
                  }

                  this.cmdCheck = new Command(mResources.SELECT, this, vecServerValue.idAction, null);
                  super.center = this.cmdCheck;
               } else {
                  vecServerValue.isFocus = false;
               }
            } else if (vecServerValue != null && vecServerValue.isPointerPressInside()) {
               Res.outz("sellllect      " + this.mainSelect + "   " + vecServerValue.caption);
               vecServerValue.performAction();
            }
         }
      }

      this.UpdTouch_NewUI();
      this.UpdTouch_NewUI_Popup();
      ServerListScreen.updateDeleteData();
   }

   public final void paint(mGraphics g) {
      GameCanvas.paintBGGameScr(g);
      if (this.isChooseArea) {
         mGraphics g2 = g;
         ServerScr serverScr = this;
         if (this.isChooseArea) {
            serverScr.paint_Area(g2, GameCanvas.hw - serverScr.wBox / 2, serverScr.yBox);
            serverScr.cmdChooseArea.paint(g2);
         }
      } else if (this.isPaintNewUi) {
         mGraphics graphics = g;
         ServerScr serverScr2 = this;
         if (this.isPaintNewUi) {
            graphics.setColor(14601141);
            graphics.fillRect(serverScr2.x, serverScr2.y, serverScr2.w, serverScr2.h);
            PopUp.paintPopUp(graphics, serverScr2.xName - 50, serverScr2.yName, 100, 20, 0, true);
            mFont.tahoma_7b_dark.drawString(graphics, mResources.selectServer2, serverScr2.xName, serverScr2.yName + 5, 2);

            for (int strTypeSVIndex = 0; strTypeSVIndex < serverScr2.ntypeSv; strTypeSVIndex++) {
               int yValue = serverScr2.yPop + strTypeSVIndex * (serverScr2.hPop + 5);
               PopUp.paintPopUp(
                  graphics, serverScr2.xPop, yValue, serverScr2.wPop, serverScr2.hPop, serverScr2.select_typeSv == strTypeSVIndex ? 1 : 0, true
               );
               mFont.tahoma_7b_dark
                  .drawString(graphics, serverScr2.strTypeSV[strTypeSVIndex], serverScr2.xPop + serverScr2.wPop / 2, yValue + 5, 2);
            }

            graphics.setColor(10254674);
            graphics.fillRect(serverScr2.xinfo, serverScr2.yinfo, serverScr2.winfo, serverScr2.hinfo);
            String[] texts = mFont.tahoma_7.splitFontArray(serverScr2.strTypeSV_info[serverScr2.select_typeSv], serverScr2.winfo - 10);

            for (int textIndex = 0; textIndex < texts.length; textIndex++) {
               mFont.tahoma_7_white.drawString(graphics, texts[textIndex], serverScr2.xinfo + 5, serverScr2.yinfo + 5 + textIndex * 11, 0);
            }

            mGraphics graphics2 = graphics;
            ServerScr serverScr3 = serverScr2;
            int xValue = serverScr2.xinfo;
            int yValue2 = serverScr3.yinfo + serverScr3.hinfo + 2;
            graphics2.setColor(16777215);
            graphics2.fillRect(xValue, yValue2, serverScr3.wCheck, serverScr3.wCheck);
            if (isShowSv_HaveChar) {
               graphics2.setColor(3329330);
               graphics2.fillRect(xValue + 1, yValue2 + 1, serverScr3.wCheck - 2, serverScr3.wCheck - 2);
            }

            mFont.tahoma_7b_dark.drawString(graphics2, serverScr3.strShowAll, xValue + serverScr3.wCheck + 2, yValue2, 0);
            serverScr2.paint_Area(graphics, 10, serverScr2.yBox);
            graphics.setColor(10254674);
            graphics.fillRect(serverScr2.xsub, serverScr2.ysub, serverScr2.wsub, serverScr2.hsub);
            graphics.setClip(serverScr2.xsub, serverScr2.ysub, serverScr2.wsub, serverScr2.hsub);
            graphics.translate(0, -serverScr2.list.cmx);

            for (int vecServerIndex = 0; vecServerIndex < serverScr2.vecServer.size(); vecServerIndex++) {
               Command vecServerValue;
               if ((vecServerValue = (Command)serverScr2.vecServer.elementAt(vecServerIndex)) != null) {
                  vecServerValue.paint(graphics);
                  if (vecServerValue.isPaintNew && GameCanvas.gameTick % 10 > 1) {
                     graphics.drawImage(Panel.imgNew, ((int) (vecServerValue.x + 60)), ((int) (vecServerValue.y)), 0);
                  }
               }
            }

            GameCanvas.resetTrans(graphics);
         }

         if (ServerListScreen.cmdDeleteRMS != null) {
            mFont.tahoma_7_white
               .drawString(
                  g, mResources.xoadulieu, GameCanvas.w - 2, GameCanvas.h - 15, 1, mFont.tahoma_7_grey
               );
         }
      } else {
         for (int vecServerIndex2 = 0; vecServerIndex2 < this.vecServer.size(); vecServerIndex2++) {
            if (this.vecServer.elementAt(vecServerIndex2) != null) {
               ((Command)this.vecServer.elementAt(vecServerIndex2)).paint(g);
            }
         }
      }

      super.paint(g);
   }

   public final void updateKey() {
      super.updateKey();
      int vecServerIndex = this.mainSelect % this.numw;
      int value = this.mainSelect / this.numw;
      if (GameCanvas.keyPressed[4]) {
         if (vecServerIndex > 0) {
            this.mainSelect--;
         }

         GameCanvas.keyPressed[4] = false;
      } else if (GameCanvas.keyPressed[6]) {
         if (vecServerIndex < this.numw - 1) {
            this.mainSelect++;
         }

         GameCanvas.keyPressed[6] = false;
      } else if (GameCanvas.keyPressed[2]) {
         if (value > 0) {
            this.mainSelect = this.mainSelect - this.numw;
         }

         GameCanvas.keyPressed[2] = false;
      } else if (GameCanvas.keyPressed[8]) {
         System.out.println("-----------vo mainSelect: " + this.mainSelect);
         if (value < this.numh - 1) {
            this.mainSelect = this.mainSelect + this.numw;
         }

         GameCanvas.keyPressed[8] = false;
      }

      if (this.mainSelect < 0) {
         this.mainSelect = 0;
      }

      if (this.mainSelect >= this.vecServer.size()) {
         this.mainSelect = this.vecServer.size() - 1;
      }

      if (GameCanvas.keyPressed[5]) {
         ((Command)this.vecServer.elementAt(vecServerIndex)).performAction();
         GameCanvas.keyPressed[5] = false;
      }

      GameCanvas.clearKeyPressed();
   }

   public final void perform(int idAction, Object p) {
      Res.outz("perform trong ServerScr:" + idAction);
      switch (idAction) {
         case 97:
            if (!this.isPaintNewUi) {
               this.vecServer.removeAllElements();

               for (int languageIndex = 0; languageIndex < ServerListScreen.nameServer.length; languageIndex++) {
                  if (ServerListScreen.language[languageIndex] != 0) {
                     this.vecServer.addElement(new Command(ServerListScreen.nameServer[languageIndex], this, languageIndex + 100, null));
                  }
               }

               this.sort();
               return;
            }
            break;
         case 98:
            if (!this.isPaintNewUi) {
               this.vecServer.removeAllElements();

               for (int languageIndex2 = 0; languageIndex2 < ServerListScreen.nameServer.length; languageIndex2++) {
                  if (ServerListScreen.language[languageIndex2] == 0) {
                     this.vecServer.addElement(new Command(ServerListScreen.nameServer[languageIndex2], this, languageIndex2 + 100, null));
                  }
               }

               this.sort();
               return;
            }
            break;
         case 99:
            Session_ME.gI().clearSendingMessage();
            ServerListScreen.SetIpSelect(this.mainSelect, false);
            if (GameCanvas.serverScreen == null) {
               GameCanvas.serverScreen = new ServerListScreen();
            }

            GameCanvas.serverScreen.selectServer();
            GameCanvas.serverScreen.switchToMe();
            return;
         case 999:
            ServerScr serverScr = this;
            ((byte[])(p = new byte[2]))[0] = serverScr.select_Area;
            ((byte[])p)[1] = serverScr.select_Lang;
            Rms.saveRMS(RMS_SELECT_AREA, (byte[]) p);
            this.SetNewSelectMenu(this.select_Area, 0);
            return;
         default:
            ServerListScreen.SetIpSelect(idAction - 100, true);
            if (GameCanvas.serverScreen == null) {
               GameCanvas.serverScreen = new ServerListScreen();
            }

            GameCanvas.serverScreen.selectServer();
            GameCanvas.serverScreen.switchToMe();
      }
   }

   private void SetNewSelectMenu(int area, int typeSv) {
      this.isChooseArea = false;
      if (mSystem.clientType != 1) {
         this.isPaintNewUi = true;
      }

      this.wCheck = 10;
      this.w = GameCanvas.w / 3 << 1;
      this.h = GameCanvas.h / 3 << 1;
      this.x = (GameCanvas.w - this.w) / 2;
      this.y = (GameCanvas.h - this.h) / 2 + 20;
      this.xName = GameCanvas.w / 2;
      this.yName = this.y - 30;
      this.wsub = this.w / 3 << 1;
      this.wPop = this.w - this.wsub - 15;
      if (this.wPop < 80) {
         this.wPop = 80;
         this.wsub = this.w - this.wPop - 15;
      }

      this.hsub = this.h - 10 - this.wCheck;
      this.xsub = this.x + this.w - this.wsub - 5;
      this.ysub = this.y + 5;
      this.xPop = this.x + 5;
      this.yPop = this.y + 5;
      this.hPop = 20;
      this.xinfo = this.x + 5;
      this.yinfo = this.y + this.strTypeSV.length * (this.hPop + 5) + 5;
      this.winfo = this.wPop;
      this.hinfo = this.h - (5 + this.strTypeSV.length * (this.hPop + 5) + 5) - this.wCheck;
      this.yBox = 10;
      this.wBox = 70;
      this.hBox = 20;
      this.GetVecTypeSv((byte)area, (byte)typeSv);
   }

   private void GetVecTypeSv(byte area, byte typeSv) {
   int intSlot2;
   this.vecServer.removeAllElements();
      this.ntypeSv = 1;
      this.select_Area = area;
      mResources.loadLanguague(area);

      for (int languageIndex = 0; languageIndex < ServerListScreen.nameServer.length; languageIndex++) {
         if (area == 1) {
            if (ServerListScreen.language[languageIndex] != 0 && ServerListScreen.typeSv[languageIndex] == 1) {
               this.ntypeSv = 2;
            }
         } else if (ServerListScreen.typeSv[languageIndex] == 1) {
            this.ntypeSv = 2;
         }
      }

      if (typeSv > (byte)(this.ntypeSv - 1)) {
         typeSv = (byte)(this.ntypeSv - 1);
      }

      this.select_typeSv = (byte)typeSv;

      for (int isNewIndex = 0; isNewIndex < ServerListScreen.nameServer.length; isNewIndex++) {
         if (area == 1) {
            if (ServerListScreen.language[isNewIndex] != 0) {
               if (ServerListScreen.typeSv[isNewIndex] == 1) {
                  this.ntypeSv = 2;
               }

               if (ServerListScreen.typeSv[isNewIndex] == typeSv) {
                  byte iconHeadIndex = -1;
                  if (ServerListScreen.typeClass != null && isNewIndex < ServerListScreen.typeClass.length) {
                     iconHeadIndex = ServerListScreen.typeClass[isNewIndex];
                  }

                  if (!isShowSv_HaveChar || iconHeadIndex != -1) {
                     Command entry;
                     (entry = new Command(ServerListScreen.nameServer[isNewIndex], this, isNewIndex + 100, null)).isPaintNew = ServerListScreen.isNew[isNewIndex]
                        == 1;
                     if (iconHeadIndex >= 0) {
                        entry.imgBtn = iconHead[iconHeadIndex];
                     }

                     this.vecServer.addElement(entry);
                  }
               }
            }
         } else {
            if (ServerListScreen.typeSv[isNewIndex] == 1) {
               this.ntypeSv = 2;
            }

            if (ServerListScreen.language[isNewIndex] == 0 && ServerListScreen.typeSv[isNewIndex] == typeSv) {
               byte iconHeadIndex2 = -1;
               if (ServerListScreen.typeClass != null && isNewIndex < ServerListScreen.typeClass.length) {
                  iconHeadIndex2 = ServerListScreen.typeClass[isNewIndex];
               }

               if (!isShowSv_HaveChar || iconHeadIndex2 != -1) {
                  Command entry2;
                  (entry2 = new Command(ServerListScreen.nameServer[isNewIndex], this, isNewIndex + 100, null)).isPaintNew = ServerListScreen.isNew[isNewIndex]
                     == 1;
                  if (iconHeadIndex2 >= 0) {
                     entry2.imgBtn = iconHead[iconHeadIndex2];
                  }

                  this.vecServer.addElement(entry2);
               }
            }
         }
      }

      ServerScr serverScr = this;

      for (int vecServerIndex = 0; vecServerIndex < serverScr.vecServer.size() - 1; vecServerIndex++) {
         Command vecServerValue = (Command)serverScr.vecServer.elementAt(vecServerIndex);

         for (int vecServerIndex2 = vecServerIndex + 1; vecServerIndex2 < serverScr.vecServer.size(); vecServerIndex2++) {
            Command vecServerValue2;
            if ((vecServerValue2 = (Command)serverScr.vecServer.elementAt(vecServerIndex2)).isPaintNew && !vecServerValue.isPaintNew) {
               Command vecServerValue3 = vecServerValue2;
               vecServerValue2 = vecServerValue;
               vecServerValue = vecServerValue3;
               serverScr.vecServer.setElementAt(vecServerValue, vecServerIndex);
               serverScr.vecServer.setElementAt(vecServerValue2, vecServerIndex2);
            }
         }
      }

      ServerScr serverScr2 = this;
      this.mainSelect = ServerListScreen.ipSelect;
      serverScr2.w2c = 5;
      serverScr2.wc = 76;
      serverScr2.hc = mScreen.cmdH;
      serverScr2.numw = 1;
      intSlot2 = serverScr2.xsub + serverScr2.wsub / 2 + 3;
      serverScr2.ysubpaint = serverScr2.ysub + 5;
      serverScr2.numw = serverScr2.wsub / (serverScr2.wc + serverScr2.w2c);
      if (serverScr2.numw <= 0) {
         serverScr2.numw = 1;
      }

      serverScr2.numh = serverScr2.vecServer.size() / serverScr2.numw + (serverScr2.vecServer.size() % serverScr2.numw == 0 ? 0 : 1);
      serverScr2.xsubpaint = intSlot2 - serverScr2.numw * (serverScr2.wc + serverScr2.w2c) / 2;

      for (int vecServerIndex3 = 0; vecServerIndex3 < serverScr2.vecServer.size(); vecServerIndex3++) {
         Command vecServerValue4;
         if ((vecServerValue4 = (Command)serverScr2.vecServer.elementAt(vecServerIndex3)) != null) {
            int xValue = serverScr2.xsubpaint + vecServerIndex3 % serverScr2.numw * (serverScr2.wc + serverScr2.w2c);
            int yValue = serverScr2.ysubpaint + vecServerIndex3 / serverScr2.numw * (serverScr2.hc + serverScr2.w2c);
            vecServerValue4.x = xValue;
            vecServerValue4.y = yValue;
            vecServerValue4.w = serverScr2.wc;
         }
      }

      serverScr2.list = new ListNew(serverScr2.xsub, serverScr2.ysub, serverScr2.wsub, serverScr2.hsub, 0, 0, 0, true);
      int cmxLim = serverScr2.numh * (serverScr2.hc + serverScr2.w2c) - serverScr2.hsub;
      ListNew listValue = serverScr2.list;
      if (cmxLim < 0) {
         cmxLim = 0;
      }

      listValue.cmxLim = cmxLim;
      if (listValue.cmx > listValue.cmxLim) {
         listValue.cmx = listValue.cmxLim;
      }

      if (listValue.cmtoX > listValue.cmxLim) {
         listValue.cmtoX = listValue.cmxLim;
      }

      ListNew listValue2 = serverScr2.list;
      serverScr2.list.cmtoX = 0;
   }

   private void paint_Area(mGraphics g, int x, int y) {
      int xPopUp_AreaValue = x - 5;
      this.xPopUp_Area = xPopUp_AreaValue;
      PopUp.paintPopUp(g, xPopUp_AreaValue, y, this.wBox, this.hBox, 0, true);
      mFont.tahoma_7b_dark.drawString(g, this.strArea[this.select_Area], xPopUp_AreaValue + (this.wBox - 10) / 2, y + 5, 2);
      g.drawRegion(Mob.imgHP, 0, 30, 9, 6, 0, xPopUp_AreaValue + this.wBox - 10, y + 14, 33);
      if (this.isPaint_select_area) {
         this.yPopUp_Area = y + this.hBox + 5;
         g.setColor(10254674);
         g.fillRect(xPopUp_AreaValue, this.yPopUp_Area, this.wBox, this.strArea.length * this.htext + 1);

         for (int strAreaIndex = 0; strAreaIndex < this.strArea.length; strAreaIndex++) {
            mFont.tahoma_7_white
               .drawString(
                  g, this.strArea[strAreaIndex], xPopUp_AreaValue + this.wBox / 2, this.yPopUp_Area + strAreaIndex * this.htext + 2, 2
               );
            if (this.select_Area == strAreaIndex) {
               g.setColor(15591444);
               g.drawRect(xPopUp_AreaValue + 2, this.yPopUp_Area + strAreaIndex * this.htext + 1, this.wBox - 4, this.htext - 2);
            }
         }
      }
   }

   private void UpdTouch_NewUI() {
      if (this.isPaintNewUi) {
         int cmy = 0;
         if (this.list != null) {
            this.list.moveCamera();
            if (GameCanvas.isPointer(this.xsub, 0, this.wsub, GameCanvas.h)) {
               this.list.update_Pos_UP_DOWN();
            }

            cmy = this.list.cmx;
         }

         if (GameCanvas.isPointer(this.xsub, this.ysub, this.wsub, this.hsub)) {
            int mainSelectValue = (GameCanvas.px - this.xsubpaint) / (this.wc + this.w2c)
               + (GameCanvas.py - this.ysubpaint + cmy) / (this.hc + this.w2c) * this.numw;
            int vecServerCount = this.vecServer.size();
            if (mainSelectValue >= 0 && mainSelectValue < vecServerCount) {
               this.mainSelect = mainSelectValue;

               for (int vecServerIndex = 0; vecServerIndex < this.vecServer.size(); vecServerIndex++) {
                  Command vecServerValue;
                  if ((vecServerValue = (Command)this.vecServer.elementAt(vecServerIndex)) != null) {
                     if (vecServerIndex == this.mainSelect) {
                        if (vecServerValue.isPointerPressInsideCamera(0, cmy)) {
                           vecServerValue.performAction();
                        }
                     } else {
                        vecServerValue.isFocus = false;
                     }
                  }
               }
            }
         }

         if (GameCanvas.isPointer(
               this.xinfo - 2, this.yinfo + this.hinfo, this.wCheck + 4, this.wCheck + 4
            )
            && GameCanvas.isPointerJustRelease) {
            isShowSv_HaveChar = !isShowSv_HaveChar;
            this.GetVecTypeSv(this.select_Area, this.select_typeSv);
         }

         if (this.ntypeSv != 1) {
            for (byte typeSv = 0; typeSv < this.ntypeSv; typeSv++) {
               int yValue = this.yPop + typeSv * (this.hPop + 5);
               if (GameCanvas.isPointerHoldIn(this.xPop, yValue, this.wPop, this.hPop) && GameCanvas.isPointerDown) {
                  this.GetVecTypeSv(this.select_Area, typeSv);
                  return;
               }
            }
         }
      }
   }

   private void UpdTouch_NewUI_Popup() {
      if (GameCanvas.isPointer(this.xPopUp_Area, this.yBox, this.wBox, this.hBox)
         && GameCanvas.isPointerJustRelease) {
         this.isPaint_select_area = !this.isPaint_select_area;
         GameCanvas.isPointerJustRelease = false;
      }

      if (this.isPaint_select_area) {
         for (byte select_AreaValue = 0; select_AreaValue < this.strArea.length; select_AreaValue++) {
            int yValue = this.yPopUp_Area + select_AreaValue * this.htext;
            if (GameCanvas.isPointerHoldIn(this.xPopUp_Area, yValue, this.wBox, this.htext) && GameCanvas.isPointerDown) {
               if (this.isChooseArea) {
                  this.select_Area = select_AreaValue;
               } else {
                  this.SetNewSelectMenu(select_AreaValue, this.select_typeSv);
               }

               this.isPaint_select_area = false;
               return;
            }
         }
      }
   }

   private void Load_RMS_Area() {
      byte[] storedSELECT_AREA = Rms.loadRMS(RMS_SELECT_AREA);

      try {
         this.select_Area = storedSELECT_AREA[0];
         this.select_Lang = storedSELECT_AREA[1];
      } catch (Exception ignoredException) {
         this.select_Area = this.select_Lang = 0;
      }
   }
}
