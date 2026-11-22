package nro;

import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class TileMap {
   public static int tmw;
   public static int tmh;
   public static int pxw;
   public static int pxh;
   public static int tileID;
   private static int lastTileID = -1;
   public static int[] maps;
   public static int[] types;
   private static Image[] imgTile;
   private static Image imgWaterfall;
   private static Image imgTopWaterfall;
   public static Image imgWaterflow;
   private static Image imgWaterlowN;
   private static Image imgWaterlowN2;
   public static byte size = 24;
   private static int bx;
   private static int dbx;
   private static int fx;
   private static int dfx;
   public static boolean isMapDouble = false;
   public static String mapName = "";
   public static int mapID;
   public static int lastBgID = -1;
   public static int zoneID;
   public static int bgID;
   public static int bgType;
   public static int lastType = -1;
   public static int typeMap;
   public static byte planetID;
   public static byte lastPlanetId = -1;
   public static MyVector vGo = new MyVector("vGO");
   public static MyVector vItemBg = new MyVector("vItemBg");
   public static MyVector vCurrItem = new MyVector("vCurrItem");
   public static String[] mapNames;
   public static Image bong = mSystem.loadImage("/mainImage/myTexture2dbong.png");
   private static int[] offlineId = new int[]{21, 22, 23, 39, 40, 41};
   public static int[][] tileType;
   public static int[][][] tileIndex;
   public static Image imgLight = mSystem.loadImage("/bg/light.png");
   public static int gssx;
   public static int gssxe;
   public static int gssy;
   public static int gssye;
   public static int countx;
   public static int county;
   public static int yWater = 0;

   static {
      new MyVector("vObject");
      int[] intValues = new int[]{21, 22, 23, 24, 25, 26};
      intValues = new int[]{0, 7, 14};
      intValues = new int[]{5257738, 8807192};
   }

   public static boolean isVoDaiMap() {
      return mapID == 51
         || mapID == 103
         || mapID == 112
         || mapID == 113
         || mapID == 129
         || mapID == 130;
   }

   public static boolean isTrainingMap() {
      return mapID == 39 || mapID == 40 || mapID == 41;
   }

   public static boolean mapPhuBang() {
      return GameScr.phuban_Info != null && mapID == GameScr.phuban_Info.idmapPaint;
   }

   public static BgItem getBIById(int id) {
      for (int itemBgIndex = 0; itemBgIndex < vItemBg.size(); itemBgIndex++) {
         BgItem itemBg;
         if ((itemBg = (BgItem)vItemBg.elementAt(itemBgIndex)).id == id) {
            return itemBg;
         }
      }

      return null;
   }

   public static boolean isOfflineMap() {
      for (int offlineIdIndex = 0; offlineIdIndex < offlineId.length; offlineIdIndex++) {
         if (mapID == offlineId[offlineIdIndex]) {
            return true;
         }
      }

      return false;
   }

   public static boolean isExistMoreOne(int id) {
      if (id != 156 && id != 330 && id != 345 && id != 334) {
         if (mapID != 54
            && mapID != 55
            && mapID != 56
            && mapID != 57
            && mapID != 58
            && mapID != 59
            && mapID != 103) {
            int index = 0;

            for (int currItemIndex = 0; currItemIndex < vCurrItem.size(); currItemIndex++) {
               if (((BgItem)vCurrItem.elementAt(currItemIndex)).id == id) {
                  index++;
               }
            }

            return index > 2;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static final void loadTileImage() {
      if (imgWaterfall == null) {
         imgWaterfall = mSystem.loadImageRMS("/tWater/wtf.png");
      }

      if (imgTopWaterfall == null) {
         imgTopWaterfall = mSystem.loadImageRMS("/tWater/twtf.png");
      }

      if (imgWaterflow == null) {
         imgWaterflow = mSystem.loadImageRMS("/tWater/wts.png");
      }

      if (imgWaterlowN == null) {
         imgWaterlowN = mSystem.loadImageRMS("/tWater/wtsN.png");
      }

      if (imgWaterlowN2 == null) {
         imgWaterlowN2 = mSystem.loadImageRMS("/tWater/wtsN2.png");
      }

      System.gc();
   }

   public static void loadMap(int tileId) {
      pxh = tmh * size;
      pxw = tmw * size;
      tileId--;

      try {
         for (int index = 0; index < tmw * tmh; index++) {
            for (int tileIndexIndex = 0; tileIndexIndex < tileType[tileId].length; tileIndexIndex++) {
               int[] tileIndexValue = tileIndex[tileId][tileIndexIndex];
               int tileTypeValue = tileType[tileId][tileIndexIndex];
               int[] tileIndexValue2 = tileIndexValue;
               int mapIndex = index;

               for (int index2 = 0; index2 < tileIndexValue2.length; index2++) {
                  if (maps[mapIndex] == tileIndexValue2[index2]) {
                     types[mapIndex] = types[mapIndex] | tileTypeValue;
                     break;
                  }
               }
            }
         }
      } catch (Exception exception) {
         System.out.println("Error Load Map");
         exception.printStackTrace();
         GameMidlet.instance.exit();
      }
   }

   public static boolean isInAirMap() {
      return mapID == 45 || mapID == 46 || mapID == 48;
   }

   public static boolean isDoubleMap() {
      return isMapDouble
         || mapID == 45
         || mapID == 46
         || mapID == 48
         || mapID == 51
         || mapID == 52
         || mapID == 103
         || mapID == 112
         || mapID == 113
         || mapID == 115
         || mapID == 117
         || mapID == 118
         || mapID == 119
         || mapID == 120
         || mapID == 121
         || mapID == 125
         || mapID == 129
         || mapID == 130;
   }

   private static void paintTile(mGraphics g, int frame, int indexX, int indexY) {
      try {
         if (imgTile != null) {
            if (imgTile.length == 1) {
               if (imgTile[0] != null) {
                  g.drawRegion(
                     imgTile[0],
                     0,
                     frame * size,
                     size,
                     size,
                     0,
                     indexX * size,
                     indexY * size,
                     0
                  );
                  return;
               }
            } else if (frame >= 0 && frame < imgTile.length && imgTile[frame] != null) {
               g.drawImage(imgTile[frame], ((int) (indexX * size)), ((int) (indexY * size)), 0);
            }
         }
      } catch (Exception ignoredException) {
      }
   }

   private static void paintTile(mGraphics graphics, int imgTileIndex, int x, int y, int value) {
      try {
         if (imgTile == null) {
            return;
         }

         if (imgTile.length == 1) {
            if (imgTile[0] != null) {
               graphics.drawRegion(imgTile[0], 0, imgTileIndex * 24, 24, 24, 0, x, y, 0);
               return;
            }
         } else if (imgTileIndex >= 0 && imgTileIndex < imgTile.length && imgTile[imgTileIndex] != null) {
            graphics.drawImage(imgTile[imgTileIndex], ((int) (x)), ((int) (y)), 0);
            return;
         }
      } catch (Exception ignoredException) {
      }
   }

   public static final void paintTilemap(mGraphics g) {
      try {
         if (Char.isLoadingMap) {
            return;
         }

         GameScr.gI();
         GameScr.paintBgItem(g, 1);

         for (int indexX = GameScr.gssx; indexX < GameScr.gssxe; indexX++) {
            for (int indexY = GameScr.gssy; indexY < GameScr.gssye; indexY++) {
               if (indexX != 0 && indexX != tmw - 1) {
                  int frame = maps[indexY * tmw + indexX] - 1;
                  if ((tileTypeAt(indexX, indexY) & 256) != 256) {
                     if ((tileTypeAt(indexX, indexY) & 32) == 32) {
                        g.drawRegion(
                           imgWaterfall, 0, 24 * (GameCanvas.gameTick % 8 >> 1), 24, 24, 0, indexX * size, indexY * size, 0
                        );
                     } else if ((tileTypeAt(indexX, indexY) & 128) == 128) {
                        g.drawRegion(
                           imgTopWaterfall, 0, 24 * (GameCanvas.gameTick % 8 >> 1), 24, 24, 0, indexX * size, indexY * size, 0
                        );
                     } else if (tileID != 13 || frame == -1) {
                        if (tileID == 2 && (tileTypeAt(indexX, indexY) & 512) == 512 && frame != -1) {
                           paintTile(g, frame, indexX * size, indexY * size, 24);
                           paintTile(g, frame, indexX * size, indexY * size + 1, 24);
                        }

                        if ((tileTypeAt(indexX, indexY) & 16) == 16) {
                           dbx = (bx = indexX * size - GameScr.cmx) - GameScr.gW2;
                           fx = (dfx = (size - 2) * dbx / size) + GameScr.gW2;
                           paintTile(g, frame, fx + GameScr.cmx, indexY * size, 24);
                        } else if ((tileTypeAt(indexX, indexY) & 512) == 512) {
                           if (frame != -1) {
                              paintTile(g, frame, indexX * size, indexY * size, 24);
                              paintTile(g, frame, indexX * size, indexY * size + 1, 24);
                           }
                        } else if (frame != -1) {
                           paintTile(g, frame, indexX, indexY);
                        }
                     }
                  }
               }
            }
         }

         if (GameScr.cmx < 24) {
            for (int indexY2 = GameScr.gssy; indexY2 < GameScr.gssye; indexY2++) {
               int frame2;
               if ((frame2 = maps[indexY2 * tmw + 1] - 1) != -1) {
                  paintTile(g, frame2, 0, indexY2);
               }
            }
         }

         if (GameScr.cmx > GameScr.cmxLim) {
            int value = tmw - 2;

            for (int indexY3 = GameScr.gssy; indexY3 < GameScr.gssye; indexY3++) {
               int frame3;
               if ((frame3 = maps[indexY3 * tmw + value] - 1) != -1) {
                  paintTile(g, frame3, value + 1, indexY3);
               }
            }

            return;
         }
      } catch (Exception ignoredException) {
      }
   }

   private static boolean isWaterEff() {
      return mapID != 54
         && mapID != 55
         && mapID != 56
         && mapID != 57
         && mapID != 138
         && mapID != 167;
   }

   public static final void paintOutTilemap(mGraphics g) {
      if (!GameCanvas.lowGraphic) {
         try {
            for (int x = GameScr.gssx; x < GameScr.gssxe; x++) {
               for (int y = GameScr.gssy; y < GameScr.gssye; y++) {
                  if ((tileTypeAt(x, y) & 64) == 64) {
                     Image image;
                     if (tileID == 5) {
                        image = imgWaterlowN;
                     } else if (tileID == 8) {
                        image = imgWaterlowN2;
                     } else {
                        image = imgWaterflow;
                     }

                     if (!isWaterEff()) {
                        g.drawRegion(image, 0, 0, 24, 24, 0, x * size, y * size - 1, 0);
                        g.drawRegion(image, 0, 0, 24, 24, 0, x * size, y * size - 3, 0);
                     }

                     g.drawRegion(
                        image, 0, (GameCanvas.gameTick % 8 >> 2) * 24, 24, 24, 0, x * size, y * size - 12, 0
                     );
                     if (yWater == 0 && isWaterEff()) {
                        yWater = y * size - 12;
                        int color = 16777215;
                        if (GameCanvas.typeBg == 2) {
                           color = 10871287;
                        } else if (GameCanvas.typeBg == 4) {
                           color = 8111470;
                        } else if (GameCanvas.typeBg == 7) {
                           color = 5693125;
                        } else if (GameCanvas.typeBg == 19) {
                           color = 16711680;
                        }

                        BackgroudEffect.addWater(color, yWater + 15);
                     }
                  }
               }
            }

            BackgroudEffect.paintWater(g);
         } catch (Exception ignoredException) {
         }
      }
   }

   public static void loadMapFromResource(int mapID) throws java.lang.Exception {
      InputStream inputStream;
      if ((inputStream = SmallDart.openResourceStream("/mymap/" + mapID)) == null) {
         throw new Exception("Map " + mapID + " not found in assets, requesting from server");
      }

      DataInputStream input;
      tmw = (char)(input = new DataInputStream(inputStream)).read();
      tmh = (char)input.read();
      maps = new int[input.available()];

      for (int mapIndex = 0; mapIndex < tmw * tmh; mapIndex++) {
         maps[mapIndex] = (char)input.read();
      }

      types = new int[maps.length];
   }

   public static final int tileTypeAt(int x, int y) {
      try {
         return types[y * tmw + x];
      } catch (Exception ignoredException) {
         return 1000;
      }
   }

   public static final int tileTypeAtPixel(int px, int py) {
      try {
         return types[py / size * tmw + px / size];
      } catch (Exception ignoredException) {
         return 1000;
      }
   }

   public static final boolean tileTypeAt(int px, int py, int t) {
      try {
         return (types[py / size * tmw + px / size] & t) == t;
      } catch (Exception ignoredException) {
         return false;
      }
   }

   public static final void setTileTypeAtPixel(int px, int py, int value) {
      types[py / size * tmw + px / size] = types[py / size * tmw
            + px / size]
         | 512;
   }

   public static final void killTileTypeAt(int px, int py, int value) {
      types[py / size * tmw + px / size] = types[py / size * tmw
            + px / size]
         & -513;
   }

   public static final int tileYofPixel(int py) {
      return py / size * size;
   }

   public static final int tileXofPixel(int px) {
      return px / size * size;
   }

   public static void getTile() {
      System.gc();
      if (lastTileID != tileID) {
         if (mSystem.clientType != 5 && mSystem.clientType != 3 && mSystem.clientType != 8) {
            if (mSystem.loadImageRMS("/t/" + tileID + "$1.png") != null) {
               Rms.deleteRecord("x" + mGraphics.zoomLevel + "t" + tileID);
               imgTile = new Image[100];

               for (int imgTileIndex = 0; imgTileIndex < imgTile.length; imgTileIndex++) {
                  imgTile[imgTileIndex] = mSystem.loadImageRMS("/t/" + tileID + "$" + (imgTileIndex + 1) + ".png");
               }
            } else {
               Image imageRMS;
               if ((imageRMS = mSystem.loadImageRMS("/t/" + tileID + ".png")) != null) {
                  (imgTile = new Image[1])[0] = imageRMS;
               }
            }
         } else if (mGraphics.zoomLevel == 1) {
            (imgTile = new Image[1])[0] = mSystem.loadZoomedResourceImage("/t/" + tileID + ".png");
         } else {
            imgTile = new Image[100];

            for (int imgTileIndex2 = 0; imgTileIndex2 < imgTile.length; imgTileIndex2++) {
               imgTile[imgTileIndex2] = mSystem.loadZoomedResourceImage("/t/" + tileID + "/" + (imgTileIndex2 + 1) + ".png");
            }
         }

         lastTileID = tileID;
      }
   }
}
