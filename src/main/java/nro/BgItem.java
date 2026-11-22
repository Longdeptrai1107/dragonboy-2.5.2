package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class BgItem {
   public int id;
   public int trans;
   public short idImage;
   public int x;
   public int y;
   public int dx;
   public int dy;
   public byte layer;
   public int[] tileX;
   public int[] tileY;
   public static MyHashTable imgNew = new MyHashTable("imgNew BgItem");
   public static MyVector vKeysNew = new MyVector("vKeyNews");
   public static MyVector vKeysLast = new MyVector("vKeyLast");
   public static byte[] newSmallVersion;
   private int transX = 0;
   private int transY = 0;
   private static int[] isMiniBgz = new int[]{79, 80, 81, 85, 86, 90, 91, 92, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108};

   public static void clearHashTable() {
      imgNew.clear();
      vKeysNew.removeAllElements();
      vKeysLast.removeAllElements();
   }

   public static boolean isExistKeyNews(String keyNew) {
      for (int keysNewIndex = 0; keysNewIndex < vKeysNew.size(); keysNewIndex++) {
         if (((String)vKeysNew.elementAt(keysNewIndex)).equals(keyNew)) {
            return true;
         }
      }

      return false;
   }

   private boolean isMiniBg() {
      for (int isMiniBgzIndex = 0; isMiniBgzIndex < isMiniBgz.length; isMiniBgzIndex++) {
         if (this.idImage == isMiniBgz[isMiniBgzIndex]) {
            return true;
         }
      }

      return false;
   }

   public final void paint(mGraphics g) {
      if (!Char.isLoadingMap) {
         if (this.idImage != 279 || GameScr.gI().tMabuEff < 110) {
            int cmx = GameScr.cmx;
            int cmy = GameScr.cmy;
            Image image;
            if ((image = (Image)imgNew.get(String.valueOf(this.idImage))) != null) {
               if (this.idImage == 96) {
                  return;
               }

               if (this.layer == 4) {
                  this.transX = -cmx / 2 + 100;
               }

               if (this.idImage == 28 && this.layer == 3) {
                  this.transX = -cmx / 3 + 200;
               }

               if ((this.idImage == 67 || this.idImage == 68 || this.idImage == 69 || this.idImage == 70)
                  && this.layer == 3) {
                  this.transX = -cmx / 3 + 200;
               }

               if (this.isMiniBg() && this.layer < 4) {
                  this.transX = -(cmx >> 4) + 50;
                  this.transY = (cmy >> 5) - 15;
               }

               int xValue = this.x + this.dx + this.transX;
               int value = this.y + this.dy + this.transY;
               if (this.x + this.dx + image.getWidth() + this.transX >= cmx
                  && this.x + this.dx + this.transX <= cmx + GameCanvas.w
                  && this.y + this.dy + this.transY + image.getHeight() >= cmy
                  && this.y + this.dy + this.transY <= cmy + GameCanvas.h) {
                  g.drawRegion(
                     image,
                     0,
                     0,
                     mGraphics.getImageWidth(image),
                     mGraphics.getImageHeight(image),
                     this.trans,
                     this.x + this.dx + this.transX,
                     this.y + this.dy + this.transY,
                     0
                  );
                  if (this.idImage == 11 && TileMap.mapID != 122) {
                     g.setClip(xValue, value + 24, 48, 14);

                     for (int index = 0; index < 2; index++) {
                        g.drawRegion(
                           TileMap.imgWaterflow, 0, (GameCanvas.gameTick % 8 >> 2) * 24, 24, 24, 0, xValue + index * 24, value + 24, 0
                        );
                     }

                     g.setClip(GameScr.cmx, GameScr.cmy, GameScr.gW, GameScr.gH);
                  }
               }

               if (TileMap.isDoubleMap()
                  && this.idImage > 137
                  && this.idImage != 156
                  && this.idImage != 159
                  && this.idImage != 157
                  && this.idImage != 165
                  && this.idImage != 167
                  && this.idImage != 168
                  && this.idImage != 169
                  && this.idImage != 170
                  && this.idImage != 238
                  && TileMap.pxw - (this.x + this.dx + this.transX) >= cmx
                  && TileMap.pxw - (this.x + this.dx + this.transX + image.getWidth())
                     <= cmx + GameCanvas.w
                  && this.y + this.dy + this.transY + image.getHeight() >= cmy
                  && this.y + this.dy + this.transY <= cmy + GameCanvas.h
                  && (this.idImage < 241 || this.idImage >= 266)) {
                  g.drawRegion(
                     image,
                     0,
                     0,
                     mGraphics.getImageWidth(image),
                     mGraphics.getImageHeight(image),
                     2,
                     TileMap.pxw - (this.x + this.dx + this.transX) - mGraphics.getImageWidth(image),
                     this.y + this.dy + this.transY,
                     0
                  );
               }
            }
         }
      }
   }

   public static void changeColor() {
   }
}
