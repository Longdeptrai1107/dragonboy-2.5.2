package nro;

import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class CreateCharScr extends mScreen implements IActionListener {
   public static CreateCharScr instance;
   public static boolean isCreateChar = false;
   private Command cmdSelectSv;
   private static TField tAddName;
   private static int indexGender;
   private static int indexHair;
   private static int selected;
   private static int[][] hairID = new int[][]{{64, 30, 31}, {9, 29, 32}, {6, 27, 28}};
   private static int[] defaultLeg = new int[]{2, 13, 8};
   private static int[] defaultBody = new int[]{1, 12, 7};
   private int[] bgID = new int[]{0, 4, 8};
   private int cx = 168;
   private int cy = 350;
   private int dy = 45;
   private int cp1;
   private int cf;

   public static CreateCharScr gI() {
      if (instance == null) {
         instance = new CreateCharScr();
      }

      return instance;
   }

   private static void loadMapFromResource(byte[] mapID) throws java.io.IOException {
      DataInputStream input = null;
      input = null;

      for (int mapIDIndex = 0; mapIDIndex < mapID.length; mapIDIndex++) {
         InputStream inputSlot1 = SmallDart.openResourceStream("/mymap/" + mapID[mapIDIndex]);
         input = new DataInputStream(inputSlot1);
         MapTemplate.tmw[mapIDIndex] = (char)input.read();
         MapTemplate.tmh[mapIDIndex] = (char)input.read();
         MapTemplate.maps[mapIDIndex] = new int[input.available()];

         for (int mapIndex = 0; mapIndex < MapTemplate.tmw[mapIDIndex] * MapTemplate.tmh[mapIDIndex]; mapIndex++) {
            MapTemplate.maps[mapIDIndex][mapIndex] = input.read();
         }

         MapTemplate.types[mapIDIndex] = new int[MapTemplate.maps[mapIDIndex].length];
      }
   }

   public final void switchToMe() {
      LoginScr.isContinueToLogin = false;
      GameCanvas.menu.showMenu = false;
      GameCanvas.endDlg();
      super.switchToMe();
      indexGender = Res.random(0, 3);
      indexHair = Res.random(0, 3);
      this.doChangeMap();
      Char.isLoadingMap = false;
      if (tAddName != null) {
         tAddName.name = mResources.char_name;
      }

      ServerListScreen.countDieConnect = 0;
      if (GameCanvas.isTouch) {
         this.cmdSelectSv = new Command(ServerListScreen.nameServer[ServerListScreen.ipSelect], this, 10018, null);
         this.cmdSelectSv.x = 1;
         this.cmdSelectSv.y = 3;
      }
   }

   private void doChangeMap() {
      TileMap.maps = new int[MapTemplate.maps[indexGender].length];

      for (int mapIndex = 0; mapIndex < MapTemplate.maps[indexGender].length; mapIndex++) {
         TileMap.maps[mapIndex] = MapTemplate.maps[indexGender][mapIndex];
      }

      TileMap.types = MapTemplate.types[indexGender];
      TileMap.pxh = MapTemplate.pxh[indexGender];
      TileMap.pxw = MapTemplate.pxw[indexGender];
      TileMap.tileID = MapTemplate.pxw[indexGender];
      TileMap.tmw = MapTemplate.tmw[indexGender];
      TileMap.tmh = MapTemplate.tmh[indexGender];
      TileMap.tileID = this.bgID[indexGender] + 1;
      TileMap.getTile();
      GameCanvas.loadBG(this.bgID[indexGender]);
      GameScr.loadCamera(this.cx, this.cy);
   }

   public CreateCharScr() {
      if (GameCanvas.loginScr == null) {
         GameCanvas.loginScr = new LoginScr();
      }

      try {
         if (!GameCanvas.lowGraphic) {
            byte[] data = new byte[]{39, 40, 41};
            if (!GameCanvas.lowGraphic) {
               Object inputStream = null;
               inputStream = null;

               try {
                  for (int currItemIndex = 0; currItemIndex < data.length; currItemIndex++) {
                     inputStream = SmallDart.openResourceStream("/mymap/mapTable" + data[currItemIndex]);
                     DataInputStream input;
                     short packetShort = (input = new DataInputStream((InputStream)inputStream)).readShort();
                     MapTemplate.vCurrItem[currItemIndex] = new MyVector("MapTemplate.vCurrItem[a] ");

                     for (int index = 0; index < packetShort; index++) {
                        short id = input.readShort();
                        short packetShort2 = input.readShort();
                        short packetShort3 = input.readShort();
                        if (TileMap.getBIById(id) != null) {
                           BgItem bIById = TileMap.getBIById(id);
                           BgItem currItem;
                           (currItem = new BgItem()).id = id;
                           currItem.idImage = bIById.idImage;
                           currItem.dx = bIById.dx;
                           currItem.dy = bIById.dy;
                           currItem.x = packetShort2 * TileMap.size;
                           currItem.y = packetShort3 * TileMap.size;
                           currItem.layer = bIById.layer;
                           MapTemplate.vCurrItem[currItemIndex].addElement(currItem);
                           if (!BgItem.imgNew.containsKey(String.valueOf(currItem.idImage))) {
                              try {
                                 Image image = mSystem.loadImage("/mapBackGround/" + currItem.idImage + ".png");
                                 BgItem.imgNew.put(String.valueOf(currItem.idImage), image);
                              } catch (Exception ignoredException) {
                                 Image rGBImage;
                                 if ((rGBImage = mSystem.loadZoomedResourceImage("/mapBackGround/" + currItem.idImage + ".png")) == null) {
                                    rGBImage = Image.createRGBImage(new int[]{-2013265920}, 1, 1, true);
                                    Service.gI().getBgTemplate(currItem.idImage);
                                 }

                                 BgItem.imgNew.put(String.valueOf(currItem.idImage), rGBImage);
                              }

                              BgItem.vKeysLast.addElement(String.valueOf(currItem.idImage));
                           }

                           if (!BgItem.isExistKeyNews(String.valueOf(currItem.idImage))) {
                              BgItem.vKeysNew.addElement(String.valueOf(currItem.idImage));
                           }

                           BgItem.changeColor();
                        }
                     }
                  }
               } catch (Exception exception) {
                  exception.printStackTrace();
               }
            }
         }

         loadMapFromResource(new byte[]{39, 40, 41});
      } catch (Exception exception2) {
         exception2.printStackTrace();
      }

      if (GameCanvas.w <= 200) {
         GameScr.setPopupSize(128, 100);
         GameScr.popupX = (GameCanvas.w - 128) / 2;
         GameScr.popupY = 10;
         this.cy += 15;
         this.dy -= 15;
      }

      indexGender = 1;
      (tAddName = new TField()).width = GameCanvas.loginScr.tfUser.width;
      if (GameCanvas.w < 200) {
         tAddName.width = 60;
      }

      tAddName.height = 26;
      if (GameCanvas.w < 200) {
         tAddName.x = GameScr.popupX + 45;
         tAddName.y = GameScr.popupY + 12;
      } else {
         tAddName.x = GameCanvas.w / 2 - tAddName.width / 2;
         tAddName.y = 35;
      }

      if (!GameCanvas.isTouch) {
         tAddName.isFocus = true;
      }

      tAddName.setIputType(0);
      tAddName.name = mResources.char_name;
      if (tAddName.getText().equals("@")) {
         tAddName.setText(
            GameCanvas.loginScr
               .tfUser
               .getText()
               .substring(0, GameCanvas.loginScr.tfUser.getText().indexOf("@"))
         );
      }

      indexGender = 1;
      indexHair = 0;
      super.center = new Command(mResources.NEWCHAR, this, 8000, null);
      super.left = new Command(mResources.BACK, this, 8001, null);
      if (!GameCanvas.isTouch) {
         super.right = tAddName.cmdClear;
      }
   }

   public final void keyPress(int keyCode) {
      tAddName.keyPressed(keyCode);
   }

   public final void update() {
      this.cp1++;
      if (this.cp1 > 30) {
         this.cp1 = 0;
      }

      if (this.cp1 % 15 < 5) {
         this.cf = 0;
      } else {
         this.cf = 1;
      }

      tAddName.update();
      if (this.cmdSelectSv != null && this.cmdSelectSv.isPointerPressInside()) {
         this.cmdSelectSv.performAction();
      }

      if (selected != 0) {
         tAddName.isFocus = false;
      }
   }

   public final void updateKey() {
      if (GameCanvas.keyPressed[2] && --selected < 0) {
         selected = mResources.MENUNEWCHAR.length - 1;
      }

      if (GameCanvas.keyPressed[8] && ++selected >= mResources.MENUNEWCHAR.length) {
         selected = 0;
      }

      if (selected == 0) {
         if (!GameCanvas.isTouch) {
            super.right = tAddName.cmdClear;
         }

         tAddName.update();
      }

      if (selected == 1) {
         if (GameCanvas.keyPressed[4]) {
            if (--indexGender < 0) {
               indexGender = mResources.MENUGENDER.length - 1;
            }

            this.doChangeMap();
         }

         if (GameCanvas.keyPressed[6]) {
            if (++indexGender > mResources.MENUGENDER.length - 1) {
               indexGender = 0;
            }

            this.doChangeMap();
         }

         super.right = null;
      }

      if (selected == 2) {
         if (GameCanvas.keyPressed[4] && --indexHair < 0) {
            indexHair = mResources.hairStyleName[0].length - 1;
         }

         if (GameCanvas.keyPressed[6] && ++indexHair > mResources.hairStyleName[0].length - 1) {
            indexHair = 0;
         }

         super.right = null;
      }

      if (GameCanvas.isPointerJustRelease) {
         byte byteValue = 110;
         byte byteValue2 = 60;
         if (GameCanvas.w > GameCanvas.h) {
            byteValue = 100;
            byteValue2 = 40;
         }

         if (GameCanvas.isPointerHoldIn(GameCanvas.w / 2 - 117, 15, 234, 80)) {
            selected = 0;
            tAddName.isFocus = true;
         }

         if (GameCanvas.isPointerHoldIn(GameCanvas.w / 2 - 117, byteValue - 30, 234, byteValue2 + 5)) {
            selected = 1;
            int indexGenderValue = indexGender;
            if ((indexGender = (GameCanvas.px - (GameCanvas.w / 2 - 117)) / 78) < 0) {
               indexGender = 0;
            }

            if (indexGender > mResources.MENUGENDER.length - 1) {
               indexGender = mResources.MENUGENDER.length - 1;
            }

            if (indexGenderValue != indexGender) {
               this.doChangeMap();
            }
         }

         if (GameCanvas.isPointerHoldIn(GameCanvas.w / 2 - 117, byteValue - 30 + byteValue2 + 5, 234, 65)) {
            selected = 2;
            int indexHairValue = indexHair;
            if ((indexHair = (GameCanvas.px - (GameCanvas.w / 2 - 117)) / 78) < 0) {
               indexHair = 0;
            }

            if (indexHair > mResources.hairStyleName[0].length - 1) {
               indexHair = mResources.hairStyleName[0].length - 1;
            }

            if (indexHairValue != selected) {
               this.doChangeMap();
            }
         }
      }

      super.updateKey();
      GameCanvas.clearKeyHold();
      GameCanvas.clearKeyPressed();
   }

   public final void paint(mGraphics g) {
      if (!Char.isLoadingMap) {
         GameCanvas.paintBGGameScr(g);
         g.translate(-GameScr.cmx, -GameScr.cmy);
         if (!GameCanvas.lowGraphic) {
            for (int currItemIndex = 0; currItemIndex < MapTemplate.vCurrItem[indexGender].size(); currItemIndex++) {
               BgItem currItem;
               if ((currItem = (BgItem)MapTemplate.vCurrItem[indexGender].elementAt(currItemIndex)).idImage != -1 && currItem.layer == 1) {
                  currItem.paint(g);
               }
            }
         }

         TileMap.paintTilemap(g);
         byte byteValue = 30;
         if (GameCanvas.w == 128) {
            byteValue = 20;
         }

         int partIndex = hairID[indexGender][indexHair];
         int partIndex2 = defaultLeg[indexGender];
         int partIndex3 = defaultBody[indexGender];
         g.drawImage(TileMap.bong, ((int) (this.cx)), ((int) (this.cy + this.dy)), 3);
         Part part = GameScr.parts[partIndex];
         Part part2 = GameScr.parts[partIndex2];
         Part part3 = GameScr.parts[partIndex3];
         SmallImage.drawSmallImage(
            g,
            part.pi[Char.CharInfo[this.cf][0][0]].id,
            this.cx
               + Char.CharInfo[this.cf][0][1]
               + part.pi[Char.CharInfo[this.cf][0][0]].dx,
            this.cy
               - Char.CharInfo[this.cf][0][2]
               + part.pi[Char.CharInfo[this.cf][0][0]].dy
               + this.dy,
            0,
            0
         );
         SmallImage.drawSmallImage(
            g,
            part2.pi[Char.CharInfo[this.cf][1][0]].id,
            this.cx
               + Char.CharInfo[this.cf][1][1]
               + part2.pi[Char.CharInfo[this.cf][1][0]].dx,
            this.cy
               - Char.CharInfo[this.cf][1][2]
               + part2.pi[Char.CharInfo[this.cf][1][0]].dy
               + this.dy,
            0,
            0
         );
         SmallImage.drawSmallImage(
            g,
            part3.pi[Char.CharInfo[this.cf][2][0]].id,
            this.cx
               + Char.CharInfo[this.cf][2][1]
               + part3.pi[Char.CharInfo[this.cf][2][0]].dx,
            this.cy
               - Char.CharInfo[this.cf][2][2]
               + part3.pi[Char.CharInfo[this.cf][2][0]].dy
               + this.dy,
            0,
            0
         );
         if (!GameCanvas.lowGraphic) {
            for (int currItemIndex2 = 0; currItemIndex2 < MapTemplate.vCurrItem[indexGender].size(); currItemIndex2++) {
               BgItem currItem2;
               if ((currItem2 = (BgItem)MapTemplate.vCurrItem[indexGender].elementAt(currItemIndex2)).idImage != -1 && currItem2.layer == 3) {
                  currItem2.paint(g);
               }
            }
         }

         g.translate(-g.getTranslateX(), -g.getTranslateY());
         if (GameCanvas.w < 200) {
            Paint.paintFrame(GameScr.popupX, GameScr.popupY, GameScr.popupW, GameScr.popupH, g);
            SmallImage.drawSmallImage(
               g,
               part.pi[Char.CharInfo[0][0][0]].id,
               GameCanvas.w / 2 + Char.CharInfo[0][0][1] + part.pi[Char.CharInfo[0][0][0]].dx,
               GameScr.popupY
                  + 30
                  + byteValue * 3
                  - Char.CharInfo[0][0][2]
                  + part.pi[Char.CharInfo[0][0][0]].dy
                  + this.dy,
               0,
               0
            );
            SmallImage.drawSmallImage(
               g,
               part2.pi[Char.CharInfo[0][1][0]].id,
               GameCanvas.w / 2 + Char.CharInfo[0][1][1] + part2.pi[Char.CharInfo[0][1][0]].dx,
               GameScr.popupY
                  + 30
                  + byteValue * 3
                  - Char.CharInfo[0][1][2]
                  + part2.pi[Char.CharInfo[0][1][0]].dy
                  + this.dy,
               0,
               0
            );
            SmallImage.drawSmallImage(
               g,
               part3.pi[Char.CharInfo[0][2][0]].id,
               GameCanvas.w / 2 + Char.CharInfo[0][2][1] + part3.pi[Char.CharInfo[0][2][0]].dx,
               GameScr.popupY
                  + 30
                  + byteValue * 3
                  - Char.CharInfo[0][2][2]
                  + part3.pi[Char.CharInfo[0][2][0]].dy
                  + this.dy,
               0,
               0
            );

            for (int mENUNEWCHARIndex = 0; mENUNEWCHARIndex < mResources.MENUNEWCHAR.length; mENUNEWCHARIndex++) {
               if (selected == mENUNEWCHARIndex) {
                  g.drawRegion(
                     GameScr.arrow,
                     0,
                     0,
                     13,
                     16,
                     2,
                     GameScr.popupX + 10 + (GameCanvas.gameTick % 7 > 3 ? 1 : 0),
                     GameScr.popupY + 35 + mENUNEWCHARIndex * byteValue,
                     StaticObj.VCENTER_HCENTER
                  );
                  g.drawRegion(
                     GameScr.arrow,
                     0,
                     0,
                     13,
                     16,
                     0,
                     GameScr.popupX + GameScr.popupW - 10 - (GameCanvas.gameTick % 7 > 3 ? 1 : 0),
                     GameScr.popupY + 35 + mENUNEWCHARIndex * byteValue,
                     StaticObj.VCENTER_HCENTER
                  );
               }

               mFont.tahoma_7b_dark
                  .drawString(
                     g, mResources.MENUNEWCHAR[mENUNEWCHARIndex], GameScr.popupX + 20, GameScr.popupY + 30 + mENUNEWCHARIndex * byteValue, 0
                  );
            }

            mFont.tahoma_7b_dark
               .drawString(
                  g, mResources.MENUGENDER[indexGender], GameScr.popupX + 70, GameScr.popupY + 30 + byteValue * 1, 0
               );
            mFont.tahoma_7b_dark
               .drawString(
                  g,
                  mResources.hairStyleName[indexGender][indexHair],
                  GameScr.popupX + 55,
                  GameScr.popupY + 30 + byteValue * 2,
                  0
               );
         } else {
            byte y = 110;
            byte byteValue2 = 60;
            if (GameCanvas.w > GameCanvas.h) {
               y = 100;
               byteValue2 = 45;
            }

            for (int mENUGENDERIndex = 0; mENUGENDERIndex < 3; mENUGENDERIndex++) {
               if (mENUGENDERIndex != indexGender) {
                  g.drawImage(GameScr.imgLbtn, ((int) (GameCanvas.w / 2 - 78 + mENUGENDERIndex * 78)), ((int) (y)), 3);
               } else {
                  if (selected == 1) {
                     g.drawRegion(
                        GameScr.arrow,
                        0,
                        0,
                        13,
                        16,
                        4,
                        GameCanvas.w / 2 - 78 + mENUGENDERIndex * 78,
                        y - 20 + (GameCanvas.gameTick % 7 > 3 ? 1 : 0),
                        StaticObj.VCENTER_HCENTER
                     );
                  }

                  g.drawImage(GameScr.imgLbtnFocus, ((int) (GameCanvas.w / 2 - 78 + mENUGENDERIndex * 78)), ((int) (y)), 3);
               }

               mFont.tahoma_7b_dark
                  .drawString(g, mResources.MENUGENDER[mENUGENDERIndex], GameCanvas.w / 2 - 78 + mENUGENDERIndex * 78, y - 5, 2);
            }

            for (int hairStyleNameIndex = 0; hairStyleNameIndex < 3; hairStyleNameIndex++) {
               if (hairStyleNameIndex != indexHair) {
                  g.drawImage(GameScr.imgLbtn, ((int) (GameCanvas.w / 2 - 78 + hairStyleNameIndex * 78)), ((int) (y + byteValue2)), 3);
               } else {
                  if (selected == 2) {
                     g.drawRegion(
                        GameScr.arrow,
                        0,
                        0,
                        13,
                        16,
                        4,
                        GameCanvas.w / 2 - 78 + hairStyleNameIndex * 78,
                        y + byteValue2 - 20 + (GameCanvas.gameTick % 7 > 3 ? 1 : 0),
                        StaticObj.VCENTER_HCENTER
                     );
                  }

                  g.drawImage(GameScr.imgLbtnFocus, ((int) (GameCanvas.w / 2 - 78 + hairStyleNameIndex * 78)), ((int) (y + byteValue2)), 3);
               }

               mFont.tahoma_7b_dark
                  .drawString(
                     g, mResources.hairStyleName[indexGender][hairStyleNameIndex], GameCanvas.w / 2 - 78 + hairStyleNameIndex * 78, y + byteValue2 - 5, 2
                  );
            }

            if (selected == 0) {
               g.drawRegion(
                  GameScr.arrow,
                  0,
                  0,
                  13,
                  16,
                  4,
                  GameCanvas.w / 2,
                  20 + (GameCanvas.gameTick % 7 > 3 ? 1 : 0),
                  StaticObj.VCENTER_HCENTER
               );
            }
         }

         tAddName.paint(g);
         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         if (this.cmdSelectSv != null) {
            this.cmdSelectSv.paint(g);
         }

         super.paint(g);
      }
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 8000:
            if (tAddName.getText().equals("")) {
               GameCanvas.startOKDlg(mResources.char_name_blank);
               return;
            } else if (tAddName.getText().length() < 5) {
               GameCanvas.startOKDlg(mResources.char_name_short);
               return;
            } else {
               if (tAddName.getText().length() > 15) {
                  GameCanvas.startOKDlg(mResources.char_name_long);
                  return;
               }

               InfoDlg.showWait();
               Service.gI()
                  .createChar(tAddName.getText(), indexGender, hairID[indexGender][indexHair]);
               return;
            }
         case 8001:
            if (GameCanvas.loginScr.isLogin2) {
               GameCanvas.startYesNoDlg(
                  mResources.note,
                  new Command(mResources.YES, this, 10019, null),
                  new Command(mResources.NO, this, 10020, null)
               );
               return;
            }

            Session_ME.gI().close();
            ServerListScreen.isAutoLogin = false;
            GameCanvas.serverScreen.switchToMe();
            return;
         case 10018:
            ServerListScreen.SetIpSelect(-1, true);
            ServerScr.isShowSv_HaveChar = false;
            Controller.isEXTRA_LINK = false;
            GameCanvas.serverScr.switchToMe();
         default:
            return;
         case 10019:
            Session_ME.gI().close();
            GameCanvas.endDlg();
            ServerListScreen.isAutoLogin = false;
            GameCanvas.serverScreen.switchToMe();
            return;
         case 10020:
            GameCanvas.endDlg();
      }
   }
}
