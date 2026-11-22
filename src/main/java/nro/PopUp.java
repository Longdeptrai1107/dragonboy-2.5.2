package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class PopUp {
   public static MyVector vPopups = new MyVector("vPopups");
   public int sayWidth;
   private int sayRun;
   public String[] says;
   public int cx;
   public int cy;
   public int cw;
   public int ch;
   public boolean isWayPoint = false;
   private int tDelay = 0;
   private int timeDelay;
   public Command command;
   public boolean isPaint = true;
   public boolean isHide = false;
   private static Image goc;
   private static Image portalPopupBackground;
   private static Image imgPopUp;
   private static Image imgPopUp2;

   public static void loadBg() {
      if (goc == null) {
         goc = mSystem.loadImage("/mainImage/myTexture2dbd3.png");
      }

      if (imgPopUp == null) {
         imgPopUp = mSystem.loadImage("/mainImage/myTexture2dimgPopup.png");
      }

      if (imgPopUp2 == null) {
         imgPopUp2 = mSystem.loadImage("/mainImage/myTexture2dimgPopup2.png");
      }

      if (portalPopupBackground == null && mSystem.clientType == 1) {
         portalPopupBackground = mSystem.loadImage("/mainImage/portal.png");
      }
   }

   public PopUp(String info, int x, int y) {
      this.sayWidth = 100;
      if (info.length() < 10) {
         this.sayWidth = 60;
      }

      if (GameCanvas.w == 128) {
         this.sayWidth = 128;
      }

      this.says = mFont.tahoma_7b_dark.splitFontArray(info, this.sayWidth - 10);
      this.sayRun = 7;
      this.cx = x - this.sayWidth / 2 - 1;
      this.cy = y - 15 + this.sayRun - this.says.length * 12 - 10;
      this.cw = this.sayWidth + 2;
      this.ch = (this.says.length + 1) * 12 + 1;

      while (this.cw % 10 != 0) {
         this.cw++;
      }

      while (this.ch % 10 != 0) {
         this.ch++;
      }

      if (x >= 0 && x <= 24) {
         this.cx = this.cx + this.cw / 2 + 30;
      }

      if (x <= TileMap.tmw * 24 && x >= TileMap.tmw * 24 - 24) {
         this.cx = this.cx - (this.cw / 2 + 6);
      }

      while (this.cx <= 30) {
         this.cx += 2;
      }

      while (this.cx + this.cw >= TileMap.tmw * 24 - 30) {
         this.cx -= 2;
      }
   }

   public final void updateXYWH(String[] info, int x, int y) {
      this.sayWidth = 0;

      for (int infoIndex = 0; infoIndex < info.length; infoIndex++) {
         if (this.sayWidth < mFont.tahoma_7b_dark.getWidth(info[infoIndex])) {
            this.sayWidth = mFont.tahoma_7b_dark.getWidth(info[infoIndex]);
         }
      }

      this.sayWidth += 20;
      this.says = info;
      this.sayRun = 7;
      this.cx = x - this.sayWidth / 2 - 1;
      this.cy = y - 15 + this.sayRun - this.says.length * 12 - 10;
      this.cw = this.sayWidth + 2;
      this.ch = (this.says.length + 1) * 12 + 1;

      while (this.cw % 10 != 0) {
         this.cw++;
      }

      while (this.ch % 10 != 0) {
         this.ch++;
      }

      if (x >= 0 && x <= 24) {
         this.cx = this.cx + this.cw / 2 + 30;
      }

      if (x <= TileMap.tmw * 24 && x >= TileMap.tmw * 24 - 24) {
         this.cx = this.cx - (this.cw / 2 + 6);
      }

      while (this.cx <= 30) {
         this.cx += 2;
      }

      while (this.cx + this.cw >= TileMap.tmw * 24 - 30) {
         this.cx -= 2;
      }
   }

   public static void addPopUp(PopUp p) {
      vPopups.addElement(p);
   }

   public static void paintPopUp(mGraphics g, int x, int y, int w, int h, int color, boolean isButton) {
      if (!isButton) {
         g.setColor(0);
         g.fillRect(x + 6, y, w - 14 + 1, h);
         g.fillRect(x, y + 6, w, h - 12 + 1);
         g.setColor(color);
         g.fillRect(x + 6, y + 1, w - 12, h - 2);
         g.fillRect(x + 1, y + 6, w - 2, h - 12);
         g.drawRegion(goc, 0, 0, 7, 6, 0, x, y, 0);
         g.drawRegion(goc, 0, 0, 7, 6, 2, x + w - 7, y, 0);
         g.drawRegion(goc, 0, 0, 7, 6, 6, x, y + h - 7, 0);
         g.drawRegion(goc, 0, 0, 7, 6, 3, x + w - 7, y + h - 6, 0);
      } else {
         Image image = color == 1 ? imgPopUp2 : imgPopUp;
         g.drawRegion(image, 0, 0, 10, 10, 0, x, y, 0);
         g.drawRegion(image, 0, 20, 10, 10, 0, x + w - 10, y, 0);
         g.drawRegion(image, 0, 50, 10, 10, 0, x, y + h - 10, 0);
         g.drawRegion(image, 0, 70, 10, 10, 0, x + w - 10, y + h - 10, 0);
         int value = (w - 20) % 10 == 0 ? (w - 20) / 10 : (w - 20) / 10 + 1;
         int value2 = (h - 20) % 10 == 0 ? (h - 20) / 10 : (h - 20) / 10 + 1;

         for (int index = 0; index < value; index++) {
            g.drawRegion(image, 0, 10, 10, 10, 0, x + 10 + index * 10, y, 0);
         }

         for (int index2 = 0; index2 < value2; index2++) {
            g.drawRegion(image, 0, 30, 10, 10, 0, x, y + 10 + index2 * 10, 0);
         }

         for (int index3 = 0; index3 < value; index3++) {
            g.drawRegion(image, 0, 60, 10, 10, 0, x + 10 + index3 * 10, y + h - 10, 0);
         }

         for (int index4 = 0; index4 < value2; index4++) {
            g.drawRegion(image, 0, 40, 10, 10, 0, x + w - 10, y + 10 + index4 * 10, 0);
         }

         g.setColor(color == 1 ? 12052656 : 16770503);
         g.fillRect(x + 10, y + 10, w - 20, h - 20);
      }
   }

   public final void doClick(int timeDelayValue) {
      this.timeDelay = 10;
   }

   public static void paintAll(mGraphics graphics) {
      for (int popupIndex = 0; popupIndex < vPopups.size(); popupIndex++) {
         PopUp popup = (PopUp)vPopups.elementAt(popupIndex);
         mGraphics graphics2 = graphics;
         PopUp popup2 = popup;
         if (popup.isPaint && popup2.says != null && ChatPopup.currChatPopup == null && !popup2.isHide) {
            if (mSystem.clientType == 1) {
               int x = popup2.cx;
               int y = popup2.cy - GameCanvas.transY;
               graphics2.setClip(x, y, popup2.cw, popup2.ch);
               graphics2.drawImage(portalPopupBackground, ((int) (x)), ((int) (y)), 0);
               if (popup2.cw > 70) {
                  graphics2.drawImage(portalPopupBackground, ((int) (x + 70)), ((int) (y)), 0);
               }

               if (popup2.ch > 30) {
                  graphics2.drawImage(portalPopupBackground, ((int) (x)), ((int) (y + 30)), 0);
                  if (popup2.cw > 70) {
                     graphics2.drawImage(portalPopupBackground, ((int) (x + 70)), ((int) (y + 30)), 0);
                  }
               }

               graphics2.setClip(GameScr.cmx, GameScr.cmy, GameCanvas.w, GameCanvas.h);
            } else {
               int deltaY = popup2.cy - GameCanvas.transY;
               boolean condition = popup2.timeDelay != 0;
               int height = popup2.ch;
               int width = popup2.cw;
               int y2 = deltaY;
               int x2 = popup2.cx;
               mGraphics graphics3 = graphics2;
               if (condition) {
                  graphics3.fillRect(x2, y2, width, height, 16777215);
               } else {
                  graphics3.fillRect(x2, y2, width, height, 0);
               }
            }

            for (int sayIndex = 0; sayIndex < popup2.says.length; sayIndex++) {
               (popup2.timeDelay == 0 ? mFont.tahoma_7b_white : mFont.tahoma_7b_green2)
                  .drawString(
                     graphics2,
                     popup2.says[sayIndex],
                     popup2.cx + popup2.cw / 2,
                     popup2.cy + (popup2.ch / 2 - popup2.says.length * 12 / 2) + sayIndex * 12 - GameCanvas.transY,
                     2
                  );
            }
         }
      }
   }

   public static void update() {
      for (int popupIndex = 0; popupIndex < vPopups.size(); popupIndex++) {
         PopUp popup = (PopUp)vPopups.elementAt(popupIndex);
         if (Char.myCharz().taskMaint != null && Char.myCharz().taskMaint.taskId == 0) {
            if (popup.cx + popup.cw >= GameScr.cmx
               && popup.cx <= GameCanvas.w + GameScr.cmx
               && popup.cy + popup.ch >= GameScr.cmy
               && popup.cy <= GameCanvas.h + GameScr.cmy) {
               popup.isHide = false;
            } else {
               popup.isHide = true;
            }
         }

         if (Char.myCharz().taskMaint == null
            || Char.myCharz().taskMaint != null && Char.myCharz().taskMaint.taskId != 0) {
            if (popup.cx + popup.cw / 2 >= Char.myCharz().cx - 100
               && popup.cx + popup.cw / 2 <= Char.myCharz().cx + 100
               && popup.cy + popup.ch >= GameScr.cmy
               && popup.cy <= GameCanvas.h + GameScr.cmy) {
               popup.isHide = false;
            } else {
               popup.isHide = true;
            }
         }

         if (popup.timeDelay > 0) {
            popup.timeDelay--;
            if (popup.timeDelay == 0 && popup.command != null) {
               popup.command.performAction();
            }
         }

         if (popup.isWayPoint) {
            if (Char.myCharz().taskMaint != null) {
               if (Char.myCharz().taskMaint.taskId == 0) {
                  if (Char.myCharz().taskMaint.index == 0) {
                     popup.isPaint = false;
                  }

                  if (Char.myCharz().taskMaint.index == 1) {
                     popup.isPaint = true;
                  }

                  if (Char.myCharz().taskMaint.index > 1 && Char.myCharz().taskMaint.index < 6) {
                     popup.isPaint = false;
                  }
               } else if (!popup.isPaint) {
                  popup.tDelay++;
                  if (popup.tDelay == 50) {
                     popup.isPaint = true;
                  }
               }
            } else if (!popup.isPaint) {
               Hint.isPaint = false;
               popup.tDelay++;
               if (popup.tDelay == 50) {
                  popup.isPaint = true;
                  Hint.isPaint = true;
               }
            }
         }
      }
   }
}
