package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class RadarScr extends mScreen {
   private static RadarScr instance;
   private static boolean TYPE_UI;
   private static FrameImage fraImgFocus;
   private static FrameImage fraImgFocusNone;
   public static FrameImage fraEff;
   private static Image imgUI;
   private static Image imgUIText;
   private static Image imgArrow_Left;
   private static Image imgArrow_Right;
   private static Image imgArrow_Down;
   private static Image imgLock;
   private static Image imgUse_0;
   private static Image imgUse;
   private static Image imgBack;
   private static Image imgChange;
   private static Image imgBar_0;
   private static Image imgBar_1;
   private static Image imgPro_0;
   private static Image imgPro_1;
   private static Image[] imgRank;
   private static int xUi;
   private static int yUi;
   private static int wUi;
   private static int hUi;
   private static int xMon;
   private static int yMon;
   public static int xText;
   public static int yText;
   public static int wText;
   private static int cmyText;
   public static int hText;
   private static int yCmd;
   private static int[] xCmd = new int[0];
   private static int[] dxCmd = new int[0];
   private static int[][] xyArrow;
   private static int[][] xyItem;
   private static int[] index = new int[]{-2, -1, 0, 1, 2};
   private int dyArrow;
   private int[] dxArrow;
   private int page;
   private int maxpage;
   private int indexFocus = 0;
   public static MyVector list;
   public static MyVector listUse;
   private static int num;
   private Info_RadaScr focus_card;
   private int pxx = 0;
   private int pyy = 0;
   private int wClip;
   private int yClip;
   private int hClip;

   public static RadarScr gI() {
      if (instance == null) {
         instance = new RadarScr();
      }

      return instance;
   }

   public RadarScr() {
      TYPE_UI = true;
      Image img = mSystem.loadImage("/radar/17.png");
      Image img2 = mSystem.loadImage("/radar/3.png");
      Image img3 = mSystem.loadImage("/radar/23.png");
      fraImgFocus = new FrameImage(img, 28, 28);
      fraImgFocusNone = new FrameImage(img2, 30, 30);
      fraEff = new FrameImage(img3, 11, 11);
      imgUI = mSystem.loadImage("/radar/0.png");
      imgArrow_Left = mSystem.loadImage("/radar/1.png");
      imgArrow_Right = mSystem.loadImage("/radar/2.png");
      imgUIText = mSystem.loadImage("/radar/17.png");
      imgArrow_Down = mSystem.loadImage("/radar/4.png");
      imgLock = mSystem.loadImage("/radar/5.png");
      imgUse_0 = mSystem.loadImage("/radar/6.png");
      imgRank = new Image[7];

      for (int imgRankIndex = 0; imgRankIndex < 7; imgRankIndex++) {
         imgRank[imgRankIndex] = mSystem.loadImage("/radar/" + (imgRankIndex + 7) + ".png");
      }

      imgUse = mSystem.loadImage("/radar/14.png");
      imgBack = mSystem.loadImage("/radar/15.png");
      imgChange = mSystem.loadImage("/radar/16.png");
      imgUIText = mSystem.loadImage("/radar/18.png");
      imgBar_1 = mSystem.loadImage("/radar/19.png");
      imgPro_0 = mSystem.loadImage("/radar/20.png");
      imgPro_1 = mSystem.loadImage("/radar/21.png");
      imgBar_0 = mSystem.loadImage("/radar/22.png");
      wUi = 200;
      hUi = 219;
      xUi = GameCanvas.hw - (wUi + 40) / 2;
      yUi = GameCanvas.hh - hUi / 2;
      xText = xUi + wUi - 81;
      yText = yUi + 29;
      wText = 120;
      hText = 80;
      xyArrow = new int[][]{
         {xUi + 34, yUi + hUi - 42},
         {xUi + wUi / 2 - imgArrow_Down.getWidth() / 2, yUi + hUi / 2 + 33},
         {xUi + wUi - 41, yUi + hUi - 42}
      };
      xyItem = new int[][]{
         {xUi + 25, yUi + hUi - 82},
         {xUi + 57, yUi + hUi - 62},
         {xUi + wUi / 2 - 14, yUi + hUi - 102},
         {xUi + wUi - 57 - 28, yUi + hUi - 62},
         {xUi + wUi - 25 - 28, yUi + hUi - 82}
      };
      this.dxArrow = new int[2];
      this.dyArrow = 0;
      xMon = xUi + 73;
      yMon = yUi + hUi / 2 + 5;
      yCmd = yUi + hUi - 22;
      xCmd = new int[]{
         xUi + wUi / 2 - 8 - 80, xUi + wUi / 2 - 8, xUi + wUi / 2 - 8 + 80
      };
      dxCmd = new int[3];
      this.yClip = yText + 10 + 70;
      this.hClip = 0;
      list = new MyVector("");
      listUse = new MyVector("");
      this.page = 1;
      this.maxpage = 2;
   }

   public final void SetRadarScr(MyVector listValue, int num, int numMax) {
      list = listValue;
      nro.RadarScr.num = num;
      this.page = 1;
      this.indexFocus = 2;
      this.listIndex();
      TYPE_UI = true;
      SetListUse();
      if (TYPE_UI) {
         this.maxpage = listValue.size() / 5 + (listValue.size() % 5 > 0 ? 1 : 0);
      } else {
         this.maxpage = listUse.size() / 5 + (listUse.size() % 5 > 0 ? 1 : 0);
      }
   }

   public static void SetNum(int numValue, int numMax) {
      num = numValue;
   }

   public static void SetListUse() {
      listUse = new MyVector("");

      for (int listIndex = 0; listIndex < list.size(); listIndex++) {
         Info_RadaScr entry;
         if ((entry = (Info_RadaScr)list.elementAt(listIndex)) != null && entry.isUse == 1) {
            listUse.addElement(entry);
         }
      }
   }

   private void listIndex() {
      MyVector listValue = listUse;
      if (TYPE_UI) {
         listValue = list;
      }

      int value;
      int value2 = (value = (this.page - 1) * 5) + 5;

      for (int indexValue = value; indexValue < value2; indexValue++) {
         if (indexValue >= listValue.size()) {
            index[indexValue - value] = -1;
         } else {
            Info_RadaScr info_RadaScr;
            if ((info_RadaScr = (Info_RadaScr)listValue.elementAt(indexValue)) != null) {
               index[indexValue - value] = info_RadaScr.id;
            }
         }
      }

      SoundMn.gI();
      SoundMn.radarItem();
   }

   public final void update() {
      try {
         if (hText < 80 && (hText += 4) > 80) {
            hText = 80;
         }

         this.focus_card = Info_RadaScr.GetInfo(listUse, index[this.indexFocus]);
         if (TYPE_UI) {
            this.focus_card = Info_RadaScr.GetInfo(list, index[this.indexFocus]);
         }

         GameScr.gI().update();
         if (GameCanvas.gameTick % 10 < 6) {
            if (GameCanvas.gameTick % 2 == 0) {
               this.dyArrow--;
            }
         } else {
            this.dyArrow = 0;
         }

         if (this.focus_card != null) {
            int value = this.focus_card.amount * 100 / this.focus_card.max_amount;
            this.hClip = value * imgBar_1.getHeight() / 100;
            value = num * 100 / list.size();
            this.wClip = value * imgPro_1.getWidth() / 100;
            return;
         }
      } catch (Exception exception) {
         System.out.println("-upd-radaScr-null: " + exception.toString());
      }
   }

   public final void updateKey() {
      if (!InfoDlg.isLock) {
         if (GameCanvas.isTouch && !ChatTextField.gI().isShow && !GameCanvas.menu.showMenu) {
            RadarScr radarScr = this;
            if (!GameCanvas.isPointerClick) {
               dxCmd[0] = 0;
               dxCmd[1] = 0;
               dxCmd[2] = 0;
               radarScr.dxArrow[0] = 0;
               radarScr.dxArrow[1] = 0;
            } else {
               for (int xyItemIndex = 0; xyItemIndex < 5; xyItemIndex++) {
                  if (GameCanvas.isPointerHoldIn(xyItem[xyItemIndex][0], xyItem[xyItemIndex][1], 30, 30)
                     && GameCanvas.isPointerClick
                     && GameCanvas.isPointerJustRelease
                     && xyItemIndex != radarScr.indexFocus) {
                     int indexFocusValue = xyItemIndex;
                     RadarScr radarScr2 = radarScr;
                     radarScr.indexFocus = indexFocusValue;
                     radarScr2.listIndex();
                  }
               }

               if (GameCanvas.isPointerHoldIn(xyArrow[0][0] - 5, xyArrow[0][1] - 5, 20, 20)) {
                  if (GameCanvas.isPointerDown) {
                     radarScr.dxArrow[0] = 1;
                  }

                  if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                     radarScr.doClickArrow(0);
                     radarScr.dxArrow[0] = 0;
                  }
               }

               if (GameCanvas.isPointerHoldIn(xyArrow[2][0] - 5, xyArrow[2][1] - 5, 20, 20)) {
                  if (GameCanvas.isPointerDown) {
                     radarScr.dxArrow[1] = 1;
                  }

                  if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                     radarScr.doClickArrow(1);
                     radarScr.dxArrow[1] = 0;
                  }
               }

               for (int dxCmdIndex = 0; dxCmdIndex < xCmd.length; dxCmdIndex++) {
                  if (GameCanvas.isPointerHoldIn(xCmd[dxCmdIndex] - 5, yCmd - 5, 20, 20)) {
                     if (GameCanvas.isPointerDown) {
                        dxCmd[dxCmdIndex] = 1;
                     }

                     if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                        radarScr.doClickUse(dxCmdIndex);
                        dxCmd[dxCmdIndex] = 0;
                     }
                  }
               }
            }

            if (GameCanvas.isPointerHoldIn(xText, 0, wText, yText + hText)) {
               if (GameCanvas.isPointerMove) {
                  if (radarScr.pyy == 0) {
                     radarScr.pyy = GameCanvas.py;
                  }

                  radarScr.pxx = radarScr.pyy - GameCanvas.py;
                  if (radarScr.pxx != 0) {
                     cmyText = cmyText + radarScr.pxx;
                     radarScr.pyy = GameCanvas.py;
                  }

                  if (cmyText < 0) {
                     cmyText = 0;
                  }

                  if (cmyText > radarScr.focus_card.cp.lim) {
                     cmyText = radarScr.focus_card.cp.lim;
                  }
               } else {
                  radarScr.pyy = 0;
                  radarScr.pyy = 0;
               }
            }
         }

         if (GameCanvas.keyPressed[8]) {
            GameCanvas.keyPressed[8] = false;
            this.doKeyText(1);
         }

         if (GameCanvas.keyPressed[2]) {
            GameCanvas.keyPressed[2] = false;
            this.doKeyText(-1);
         }

         if (GameCanvas.keyPressed[4]) {
            GameCanvas.keyPressed[4] = false;
            this.doKeyItem(1);
         }

         if (GameCanvas.keyPressed[6]) {
            GameCanvas.keyPressed[6] = false;
            this.doKeyItem(0);
         }

         if (GameCanvas.keyPressed[12]) {
            GameCanvas.keyPressed[12] = false;
            this.doClickUse(0);
         }

         if (GameCanvas.keyPressed[5]) {
            GameCanvas.keyPressed[5] = false;
            this.doClickUse(1);
         }

         if (GameCanvas.keyPressed[13]) {
            this.doClickUse(2);
         }

         GameCanvas.clearKeyPressed();
      }
   }

   private void doClickUse(int value) {
      if (value == 0) {
         RadarScr radarScr = this;
         TYPE_UI = !TYPE_UI;
         radarScr.page = 1;
         radarScr.indexFocus = 0;
         if (TYPE_UI) {
            radarScr.maxpage = list.size() / 5 + (list.size() % 5 > 0 ? 1 : 0);
         } else {
            radarScr.maxpage = listUse.size() / 5 + (listUse.size() % 5 > 0 ? 1 : 0);
         }

         radarScr.listIndex();
         hText = 0;
      } else if (value == 1) {
         if (this.focus_card != null) {
            Service.gI().SendRada(1, this.focus_card.id);
         }
      } else if (value == 2) {
         GameScr.gI().switchToMe();
      }

      SoundMn.gI();
      SoundMn.radarClick();
   }

   private void doClickArrow(int dir) {
      if (TYPE_UI) {
         this.maxpage = list.size() / 5 + (list.size() % 5 > 0 ? 1 : 0);
      } else {
         this.maxpage = listUse.size() / 5 + (listUse.size() % 5 > 0 ? 1 : 0);
      }

      int pageValue = this.page;
      if (dir == 0) {
         if (this.page == 1) {
            return;
         }

         if (--pageValue <= 0) {
            pageValue = 1;
         }
      } else {
         if (this.page == this.maxpage) {
            return;
         }

         if (++pageValue > this.maxpage) {
            pageValue = this.maxpage;
         }
      }

      if (pageValue != this.page) {
         this.page = pageValue;
         this.listIndex();
      }
   }

   private void doKeyText(int type) {
      if ((cmyText += type * 12) < 0) {
         cmyText = 0;
      }

      if (cmyText > this.focus_card.cp.lim) {
         cmyText = this.focus_card.cp.lim;
      }
   }

   private void doKeyItem(int type) {
      int indexFocusValue = this.indexFocus;
      int pageValue = this.page;
      if (type == 0) {
         indexFocusValue++;
      } else {
         indexFocusValue--;
      }

      if (indexFocusValue >= index.length) {
         if (this.page < this.maxpage) {
            indexFocusValue = 0;
            pageValue++;
         } else {
            indexFocusValue = index.length - 1;
         }
      }

      if (indexFocusValue < 0) {
         if (this.page > 1) {
            indexFocusValue = index.length - 1;
            pageValue--;
         } else {
            indexFocusValue = 0;
         }
      }

      if (indexFocusValue != this.indexFocus) {
         this.indexFocus = indexFocusValue;
         cmyText = 0;
         hText = 0;
      }

      if (pageValue != this.page) {
         this.page = pageValue;
         this.listIndex();
      }
   }

   public final void paint(mGraphics g) {
      try {
         GameScr.gI().paint(g);
         g.translate(-GameScr.cmx, -GameScr.cmy);
         g.translate(0, GameCanvas.transY);
         GameScr.resetTranslate(g);
         g.drawImage(imgUI, ((int) (xUi)), ((int) (yUi)), 0);
         g.drawImage(
            imgPro_0,
            ((int) (xUi + wUi / 2 - mSystem.getWidth(imgPro_0) / 2)),
            ((int) (yUi - mSystem.getHeight(imgPro_0) / 2 - 2)),
            0
         );
         g.setClip(
            xUi + wUi / 2 - mSystem.getWidth(imgPro_0) / 2 + 13,
            yUi - mSystem.getHeight(imgPro_0) / 2 + 3,
            this.wClip,
            mSystem.getHeight(imgPro_0)
         );
         g.drawImage(
            imgPro_1,
            ((int) (xUi + wUi / 2 - mSystem.getWidth(imgPro_0) / 2 + 13)),
            ((int) (yUi - mSystem.getHeight(imgPro_0) / 2 + 3)),
            0
         );
         GameScr.resetTranslate(g);
         g.drawImage(imgChange, ((int) (xCmd[0])), ((int) (yCmd + dxCmd[0])), 0);
         g.drawImage(imgUse_0, ((int) (xCmd[1])), ((int) (yCmd + dxCmd[1])), 0);
         g.drawImage(imgBack, ((int) (xCmd[2])), ((int) (yCmd + dxCmd[2])), 0);
         if (TYPE_UI) {
            g.drawRegion(imgUse, 0, 0, 17, 17, 0, xCmd[1], yCmd + dxCmd[1], 0);
         } else {
            g.drawRegion(imgUse, 0, 0, 17, 17, 1, xCmd[1], yCmd + dxCmd[1], 0);
         }

         if (this.focus_card != null) {
            g.setClip(xUi + 30, yUi + 13, wUi - 60, hUi / 2);
            int cy = yMon;
            int cx = xMon;
            mGraphics g2 = g;
            Info_RadaScr focus_cardValue = this.focus_card;
            this.focus_card.count++;
            if (focus_cardValue.count > focus_cardValue.f.length - 1) {
               focus_cardValue.count = 0;
            }

            if (focus_cardValue.typeMonster == 0) {
               if (Mob.arrMobTemplate[focus_cardValue.mobInfo.templateId] != null) {
                  if (Mob.arrMobTemplate[focus_cardValue.mobInfo.templateId].data != null) {
                     Mob.arrMobTemplate[focus_cardValue.mobInfo.templateId]
                        .data
                        .paintFrame(g2, focus_cardValue.f[focus_cardValue.count], cx, cy, 0, 0);
                  } else if (focus_cardValue.timeRequest - GameCanvas.timeNow < 0L) {
                     focus_cardValue.timeRequest = GameCanvas.timeNow + 1500L;
                     focus_cardValue.mobInfo.getData();
                  }
               }
            } else if (focus_cardValue.charInfo != null) {
               focus_cardValue.charInfo.paintCharBody(g2, cx, cy, 1, focus_cardValue.f[focus_cardValue.count], true);
            }

            GameScr.resetTranslate(g);
            mFont.tahoma_7b_yellow
               .drawString(
                  g,
                  (this.focus_card.level > 0 ? "Lv." + this.focus_card.level + " " : "")
                     + this.focus_card.name,
                  xUi + wUi / 2,
                  yUi + 15,
                  2
               );
            mFont.tahoma_7_white
               .drawString(g, "no." + this.focus_card.no, xUi + 30, yText - 2, 0);
            g.drawImage(imgBar_0, ((int) (xUi + 36)), ((int) (yText + 10)), 0);
            g.setClip(xUi + 36, this.yClip - this.hClip, 7, this.hClip);
            g.drawImage(imgBar_1, ((int) (xUi + 36)), ((int) (yText + 10)), 0);
            GameScr.resetTranslate(g);
            g.drawImage(imgRank[this.focus_card.rank], ((int) (xUi + 39 - 5 + 14)), ((int) (yText + 12)), 0);
         }

         g.setClip(xText, yText, wText + 5, hText + 8);
         if (this.focus_card != null) {
            g.drawImage(imgUIText, ((int) (xText)), ((int) (yText)), 0);
         }

         GameScr.resetTranslate(g);
         g.setClip(xText, yText + 1, wText, hText + 5);
         if (this.focus_card != null && this.focus_card.cp != null && this.focus_card.cp.says != null
            )
          {
            this.focus_card.cp.paintRada(g, cmyText);
         }

         GameScr.resetTranslate(g);
         if (!TYPE_UI && listUse.size() > 5 || TYPE_UI) {
            if (this.page > 1) {
               g.drawImage(imgArrow_Left, ((int) (xyArrow[0][0])), ((int) (xyArrow[0][1] + this.dxArrow[0])), 0);
            }

            if (this.page < this.maxpage) {
               g.drawImage(imgArrow_Right, ((int) (xyArrow[2][0])), ((int) (xyArrow[2][1] + this.dxArrow[1])), 0);
            }
         }

         for (int indexIndex = 0; indexIndex < index.length; indexIndex++) {
            int dyArrowValue = 0;
            byte byteValue = 0;
            byte idx = 0;
            if (indexIndex == this.indexFocus) {
               dyArrowValue = this.dyArrow;
               byteValue = -10;
               idx = 1;
               g.drawImage(imgArrow_Down, ((int) (xyItem[indexIndex][0] + 10)), ((int) (xyItem[indexIndex][1] + this.dyArrow + 29 + -10)), 0);
            }

            Info_RadaScr info_RadaScr = Info_RadaScr.GetInfo(listUse, index[indexIndex]);
            if (TYPE_UI) {
               info_RadaScr = Info_RadaScr.GetInfo(list, index[indexIndex]);
            }

            if (info_RadaScr != null) {
               fraImgFocus.drawFrame(info_RadaScr.rank, xyItem[indexIndex][0], xyItem[indexIndex][1] + dyArrowValue + byteValue, 0, 0, g);
               SmallImage.drawSmallImage(
                  g, info_RadaScr.idIcon, xyItem[indexIndex][0] + 14, xyItem[indexIndex][1] + 14 + dyArrowValue + byteValue, 0, StaticObj.VCENTER_HCENTER
               );
               info_RadaScr.paintEff(g, xyItem[indexIndex][0], xyItem[indexIndex][1] + dyArrowValue + byteValue);
               if (info_RadaScr.level == 0) {
                  g.drawImage(imgLock, ((int) (xyItem[indexIndex][0])), ((int) (xyItem[indexIndex][1] + dyArrowValue + byteValue)), 0);
               }

               if (indexIndex == this.indexFocus) {
                  fraImgFocus.drawFrame(7, xyItem[indexIndex][0], xyItem[indexIndex][1] + dyArrowValue + byteValue, 0, 0, g);
               }

               if (info_RadaScr.isUse == 1) {
                  fraImgFocus.drawFrame(8, xyItem[indexIndex][0], xyItem[indexIndex][1] + dyArrowValue + byteValue, 0, 0, g);
               }
            } else {
               fraImgFocusNone.drawFrame(idx, xyItem[indexIndex][0] - 1, xyItem[indexIndex][1] - 1 + dyArrowValue + byteValue, 0, 0, g);
            }
         }
      } catch (Exception exception) {
         System.out.println("-paint-radaScr-null: " + exception.toString());
      }
   }

   public final void switchToMe() {
      GameScr.isPaintOther = true;
      super.switchToMe();
   }
}
