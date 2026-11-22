package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class BackgroudEffect {
   public static MyVector vBgEffect = new MyVector("vBG Effect");
   private int[] x;
   private int[] y;
   private int[] vx;
   private int[] vy;
   private static int[] wP;
   private int num;
   private int xShip;
   private int yShip;
   private int way;
   private int trans;
   private int frameFire;
   private int tFire;
   private int tStart;
   private int speed;
   private boolean isFly;
   private static Image imgHatMua;
   private static Image imgMua1;
   private static Image imgMua2;
   private static Image imgSao;
   private static Image imgLacay;
   private static Image imgShip;
   private static Image imgFire1;
   private static Image imgFire2;
   private int[] type;
   private int sum;
   public int typeEff;
   private boolean[] isRainEffect;
   private int[] frame;
   private int[] t;
   private boolean[] activeEff;
   private int yWater;
   private int colorWater;
   private static int PIXEL = 16;
   private static Image water1 = mSystem.loadImage("/mainImage/myTexture2dwater1.png");
   private static Image water2 = mSystem.loadImage("/mainImage/myTexture2dwater2.png");
   private static Image imgChamTron1;
   private static Image imgChamTron2;
   public static short id_water1;
   private static Image water3 = null;
   public static boolean isFog;
   private static boolean isPaintFar;
   public static int nCloud;
   private static Image imgCloud1;
   private static Image imgFog;
   private static int cloudw;
   private static int xfog;
   public static int yfog;
   private static int fogw;
   private int[] dem = new int[]{0, 1, 2, 1, 0, 0};
   private int[] tick;

   public static void clearImage() {
      TileMap.yWater = 0;
   }

   public static boolean isHaveRain() {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect;
         if ((bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex)).typeEff == 0 || bgEffect.typeEff == 12) {
            return true;
         }
      }

      return false;
   }

   private BackgroudEffect(int typeS) {
      this.typeEff = typeS;
      switch (this.typeEff) {
         case 0:
         case 12:
            if (imgHatMua == null) {
               imgHatMua = mSystem.loadImageRMS("/bg/mua.png");
            }

            if (imgMua1 == null) {
               imgMua1 = mSystem.loadImageRMS("/bg/mua1.png");
            }

            if (imgMua2 == null) {
               imgMua2 = mSystem.loadImageRMS("/bg/mua2.png");
            }

            this.sum = Res.random(GameCanvas.w / 3, GameCanvas.w / 2);
            this.x = new int[this.sum];
            this.y = new int[this.sum];
            this.vx = new int[this.sum];
            this.vy = new int[this.sum];
            this.type = new int[this.sum];
            this.t = new int[this.sum];
            this.frame = new int[this.sum];
            this.isRainEffect = new boolean[this.sum];
            this.activeEff = new boolean[this.sum];

            for (int activeEffIndex = 0; activeEffIndex < this.sum; activeEffIndex++) {
               this.y[activeEffIndex] = Res.random(-10, GameCanvas.h + 100) + GameScr.cmy;
               this.x[activeEffIndex] = Res.random(-10, GameCanvas.w + 300) + GameScr.cmx;
               this.t[activeEffIndex] = Res.random(0, 1);
               this.vx[activeEffIndex] = -12;
               this.vy[activeEffIndex] = 12;
               this.type[activeEffIndex] = Res.random(1, 3);
               this.isRainEffect[activeEffIndex] = false;
               if (this.type[activeEffIndex] == 2 && activeEffIndex % 2 == 0) {
                  this.isRainEffect[activeEffIndex] = true;
               }

               this.activeEff[activeEffIndex] = false;
               this.frame[activeEffIndex] = Res.random(1, 2);
            }

            return;
         case 1:
         case 2:
         case 5:
         case 6:
         case 7:
         case 11:
         case 15:
            if (this.typeEff == 1) {
               imgLacay = mSystem.loadImageRMS("/bg/lacay.png");
               PIXEL = 10;
            }

            if (this.typeEff == 2) {
               imgLacay = mSystem.loadImageRMS("/bg/lacay2.png");
               PIXEL = 18;
            }

            if (this.typeEff == 5) {
               imgLacay = mSystem.loadImageRMS("/bg/lacay3.png");
               PIXEL = 14;
            }

            if (this.typeEff == 6) {
               imgLacay = mSystem.loadImageRMS("/bg/lacay4.png");
               PIXEL = 14;
            }

            if (this.typeEff == 7) {
               imgLacay = mSystem.loadImageRMS("/bg/lacay5.png");
               PIXEL = 12;
            }

            if (this.typeEff == 11) {
               imgLacay = mSystem.loadImageRMS("/bg/tuyet.png");
            }

            if (this.typeEff == 15) {
               getCachedLeafImage();
               PIXEL = 16;
            }

            this.sum = Res.random(15, 25);
            if (this.typeEff == 11) {
               this.sum = 100;
            }

            this.x = new int[this.sum];
            this.y = new int[this.sum];
            this.vx = new int[this.sum];
            this.vy = new int[this.sum];
            this.t = new int[this.sum];
            this.frame = new int[this.sum];
            this.activeEff = new boolean[this.sum];

            for (int frameIndex = 0; frameIndex < this.sum; frameIndex++) {
               this.x[frameIndex] = Res.random(-10, TileMap.pxw + 10);
               this.y[frameIndex] = Res.random(0, TileMap.pxh);
               this.frame[frameIndex] = Res.random(0, 1);
               this.t[frameIndex] = Res.random(0, 1);
               this.vx[frameIndex] = Res.random(-3, 3);
               this.vy[frameIndex] = Res.random(1, 4);
               if (this.typeEff == 11) {
                  this.frame[frameIndex] = Res.random(0, 2);
                  this.vx[frameIndex] = Res.abs(Res.random(1, 3));
                  this.vy[frameIndex] = Res.abs(Res.random(1, 3));
               }

               if (this.typeEff == 15) {
                  this.frame[frameIndex] = Res.random(0, 2);
                  this.vx[frameIndex] = Res.abs(Res.random(1, 3));
                  this.vy[frameIndex] = Res.abs(Res.random(1, 3));
               }
            }

            return;
         case 3:
            GameCanvas.isBoltEff = true;
            return;
         case 4:
            this.sum = Res.random(5, 10);
            if (imgSao == null) {
               imgSao = mSystem.loadImageRMS("/bg/sao.png");
            }

            this.x = new int[this.sum];
            this.y = new int[this.sum];
            this.frame = new int[this.sum];
            this.t = new int[this.sum];
            this.tick = new int[this.sum];

            for (int tIndex = 0; tIndex < this.sum; tIndex++) {
               this.x[tIndex] = Res.random(0, GameCanvas.w);
               this.y[tIndex] = Res.random(0, 50);
               if (tIndex % 2 == 0) {
                  this.tick[tIndex] = 0;
               } else if (tIndex % 3 == 0) {
                  this.tick[tIndex] = 1;
               } else if (tIndex % 4 == 0) {
                  this.tick[tIndex] = 2;
               } else {
                  this.tick[tIndex] = 3;
               }

               this.t[tIndex] = Res.random(0, 10);
            }

            return;
         case 8:
            this.tStart = Res.random(100, 300);
            if (imgShip == null) {
               imgShip = mSystem.loadImageRMS("/bg/ship.png");
            }

            if (imgFire1 == null) {
               imgFire1 = mSystem.loadImageRMS("/bg/fire1.png");
            }

            if (imgFire2 == null) {
               imgFire2 = mSystem.loadImageRMS("/bg/fire2.png");
            }

            this.isFly = false;
            this.reloadShip();
            return;
         case 9:
            if (imgChamTron1 == null) {
               imgChamTron1 = mSystem.loadImageRMS("/bg/cham-tron1.png");
            }

            if (imgChamTron2 == null) {
               imgChamTron2 = mSystem.loadImageRMS("/bg/cham-tron2.png");
            }

            this.num = 20;
            this.x = new int[this.num];
            this.y = new int[this.num];
            wP = new int[this.num];
            this.vx = new int[this.num];

            for (int vxIndex = 0; vxIndex < this.num; vxIndex++) {
               this.x[vxIndex] = Res.abs(Res.random(0, GameCanvas.w));
               this.y[vxIndex] = Res.abs(Res.random(10, 80));
               wP[vxIndex] = Res.abs(Res.random(1, 3));
               this.vx[vxIndex] = wP[vxIndex];
            }

            return;
         case 10:
            this.num = 30;
            this.x = new int[this.num];
            this.y = new int[this.num];
            wP = new int[this.num];
            this.vx = new int[this.num];
            typeS = 0;

            for (int vxIndex2 = 0; vxIndex2 < this.num; vxIndex2++) {
               this.x[vxIndex2] = Res.abs(Res.random(0, GameCanvas.w))
                  + GameScr.cmx;
               if (++typeS > this.num / 2) {
                  this.y[vxIndex2] = Res.abs(Res.random(20, 60));
                  wP[vxIndex2] = 10;
               } else {
                  this.y[vxIndex2] = Res.abs(Res.random(0, 20));
                  wP[vxIndex2] = 7;
               }

               this.vx[vxIndex2] = wP[vxIndex2] / 2 - 2;
            }

            return;
         case 13:
            if (Res.abs(Res.random(0, 2)) == 0) {
               if (Res.abs(Res.random(0, 2)) == 0) {
                  isPaintFar = true;
               } else {
                  isPaintFar = false;
               }

               nCloud = Res.abs(Res.random(2, 5));
               initCloud();
               return;
            }
            break;
         case 14:
            if (Res.abs(Res.random(0, 2)) == 0) {
               isFog = true;
               initCloud();
            }
      }
   }

   private static Image getCachedLeafImage() {
      if (SmallImage.imgNew != null && 11120 < SmallImage.imgNew.length) {
         Small imgNew;
         if ((imgNew = SmallImage.imgNew[11120]) == null) {
            SmallImage.createImage(11120);
            imgNew = SmallImage.imgNew[11120];
         }

         if (imgNew != null && imgNew.img != null) {
            SmallImage.touchCachedImage(11120, imgNew.img);
            return imgNew.img != SmallImage.imgEmpty
                  && mGraphics.getImageWidth(imgNew.img) > 1
                  && mGraphics.getImageHeight(imgNew.img) > 1
               ? (imgLacay = imgNew.img)
               : null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static void initCloud() {
      if (mSystem.clientType == 1) {
         imgCloud1 = null;
         imgFog = null;
      } else if (GameCanvas.lowGraphic) {
         imgCloud1 = null;
         imgFog = null;
      } else {
         if (nCloud > 0) {
            if (imgCloud1 == null) {
               cloudw = (imgCloud1 = mSystem.loadImage("/bg/fog1.png")).getWidth();
            }
         } else {
            imgCloud1 = null;
         }

         if (!isFog) {
            imgFog = null;
         } else {
            if (imgFog == null) {
               imgFog = mSystem.loadImage("/bg/fog0.png");
            }

            fogw = 287;
         }
      }
   }

   private static void paintCloud2(mGraphics g) {
      if (mSystem.clientType != 1) {
         if (!GameCanvas.lowGraphic) {
            if (nCloud != 0) {
               if (imgCloud1 != null) {
                  for (int cloudXIndex = 0; cloudXIndex < nCloud; cloudXIndex++) {
                     g.drawImage(imgCloud1, ((float) (GameCanvas.cloudX[cloudXIndex])), ((float) (GameCanvas.cloudY[cloudXIndex])), 3);
                  }
               }
            }
         }
      }
   }

   public static void paintFog(mGraphics g) {
      if (mSystem.clientType != 1) {
         if (!GameCanvas.lowGraphic) {
            if (isFog) {
               if (imgFog != null) {
                  for (int xValue = xfog; xValue < TileMap.pxw; xValue += fogw) {
                     if (xValue >= GameScr.cmx - fogw) {
                        g.drawImage(imgFog, ((float) (xValue)), ((float) (yfog)), 0);
                     }
                  }
               }
            }
         }
      }
   }

   private void reloadShip() {
      int cmx = GameScr.cmx;
      int minimum = GameScr.cmy;
      this.way = Res.random(1, 3);
      this.isFly = false;
      this.speed = Res.random(3, 5);
      BackgroudEffect backgroudEffect;
      byte transValue;
      if (this.way == 1) {
         this.xShip = -50;
         this.yShip = Res.random(minimum, GameCanvas.h - 100 + minimum);
         backgroudEffect = this;
         transValue = 0;
      } else {
         label29: {
            if (this.way == 2) {
               this.xShip = TileMap.pxw + 50;
               this.yShip = Res.random(minimum, GameCanvas.h - 100 + minimum);
               backgroudEffect = this;
            } else {
               if (this.way == 3) {
                  this.xShip = Res.random(cmx + 50, GameCanvas.w - 50 + cmx);
                  this.yShip = -50;
                  cmx = Res.random(0, 2);
                  this.trans = cmx == 0 ? 0 : 2;
                  return;
               }

               if (this.way != 4) {
                  return;
               }

               this.xShip = Res.random(cmx + 50, GameCanvas.w - 50 + cmx);
               this.yShip = TileMap.pxh + 50;
               cmx = Res.random(0, 2);
               backgroudEffect = this;
               if (cmx == 0) {
                  transValue = 0;
                  break label29;
               }
            }

            transValue = 2;
         }
      }

      backgroudEffect.trans = transValue;
   }

   public static void addEffect(int id) {
      if (!GameCanvas.lowGraphic) {
         BackgroudEffect bgEffect = new BackgroudEffect(id);
         vBgEffect.addElement(bgEffect);
      }
   }

   public static void addWater(int color, int yWater) {
      BackgroudEffect bgEffect;
      (bgEffect = new BackgroudEffect(10)).colorWater = color;
      bgEffect.yWater = yWater;
      vBgEffect.addElement(bgEffect);
   }

   public static void paintWater(mGraphics g) {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex);
         mGraphics g2 = g;
         BackgroudEffect bgEffect2 = bgEffect;
         if (bgEffect.typeEff == 10) {
            g2.setColor(bgEffect2.colorWater);

            for (int xIndex = 0; xIndex < bgEffect2.num; xIndex++) {
               g2.drawImage(
                  xIndex < bgEffect2.num / 2 ? water2 : water1,
                  ((int) (bgEffect2.x[xIndex])),
                  ((int) (bgEffect2.y[xIndex] + bgEffect2.yWater)),
                  0
               );
            }

            if (id_water1 != 0) {
               Image water3Value;
               if (id_water1 <= 0) {
                  water3Value = null;
               } else {
                  Small[] imgNew = SmallImage.imgNew;
                  label66:
                  if (SmallImage.imgNew != null && id_water1 < imgNew.length) {
                     Small imgNew2;
                     if ((imgNew2 = imgNew[id_water1]) == null) {
                        SmallImage.createImage(id_water1);
                        imgNew = SmallImage.imgNew;
                        if (SmallImage.imgNew == null || id_water1 >= imgNew.length) {
                           water3Value = null;
                           break label66;
                        }

                        imgNew2 = imgNew[id_water1];
                     }

                     if (imgNew2 == null || imgNew2.img == null) {
                        water3Value = null;
                     } else if (imgNew2.img == SmallImage.imgEmpty) {
                        SmallImage.touchCachedImage(id_water1, imgNew2.img);
                        water3Value = null;
                     } else {
                        SmallImage.touchCachedImage(id_water1, imgNew2.img);
                        water3Value = imgNew2.img;
                     }
                  } else {
                     water3Value = null;
                  }
               }

               water3 = water3Value;
            }

            if (water3 != null) {
               for (int xIndex2 = 0; xIndex2 < bgEffect2.num / 2; xIndex2++) {
                  g2.drawImage(water3, ((int) (bgEffect2.x[xIndex2])), ((int) (bgEffect2.y[xIndex2] + bgEffect2.yWater)), 0);
               }
            }
         }
      }
   }

   public static void paintBehindTile(mGraphics g) {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex);
         mGraphics g2 = g;
         BackgroudEffect bgEffect2 = bgEffect;
         switch (bgEffect.typeEff) {
            case 8:
               g2.drawRegion(
                  imgShip,
                  0,
                  0,
                  imgShip.getWidth(),
                  imgShip.getHeight(),
                  bgEffect2.trans,
                  bgEffect2.xShip,
                  bgEffect2.yShip,
                  3
               );
               if (bgEffect2.way != 1 && bgEffect2.way != 2) {
                  int value = bgEffect2.trans == 0 ? 11 : -11;
                  g2.drawRegion(
                     imgFire2, 0, bgEffect2.frameFire * 18, 8, 18, bgEffect2.trans, bgEffect2.xShip + value, bgEffect2.yShip + 22, 3
                  );
               } else {
                  int value2 = bgEffect2.trans == 0 ? -25 : 25;
                  g2.drawRegion(
                     imgFire1, 0, bgEffect2.frameFire << 3, 20, 8, bgEffect2.trans, bgEffect2.xShip + value2, bgEffect2.yShip + 5, 3
                  );
               }
               break;
            case 13:
               if (isPaintFar) {
                  paintCloud2(g2);
               }
         }
      }
   }

   public static void paintFrontAll(mGraphics graphics) {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex);
         mGraphics g = graphics;
         BackgroudEffect bgEffect2 = bgEffect;

         try {
            switch (bgEffect2.typeEff) {
               case 0:
               case 12:
                  for (int activeEffIndex = 0; activeEffIndex < bgEffect2.sum; activeEffIndex++) {
                     if (bgEffect2.type[activeEffIndex] == 2
                        && bgEffect2.x[activeEffIndex] >= GameScr.cmx
                        && bgEffect2.x[activeEffIndex] <= GameCanvas.w + GameScr.cmx
                        && bgEffect2.y[activeEffIndex] >= GameScr.cmy
                        && bgEffect2.y[activeEffIndex] <= GameCanvas.h + GameScr.cmy) {
                        if (bgEffect2.activeEff[activeEffIndex]) {
                           g.drawRegion(
                              imgHatMua,
                              0,
                              10 * bgEffect2.frame[activeEffIndex],
                              13,
                              10,
                              0,
                              bgEffect2.x[activeEffIndex],
                              bgEffect2.y[activeEffIndex] - 10,
                              0
                           );
                        } else {
                           g.drawImage(imgMua1, ((int) (bgEffect2.x[activeEffIndex])), ((int) (bgEffect2.y[activeEffIndex])), 0);
                        }
                     }
                  }
                  break;
               case 1:
               case 2:
               case 5:
               case 6:
               case 7:
               case 11:
               case 15:
                  if (bgEffect2.typeEff != 15 || (imgLacay = getCachedLeafImage()) != null) {
                     Image image = imgLacay;
                     mGraphics g2 = g;
                     BackgroudEffect backgroudEffect = bgEffect2;
                     if (backgroudEffect.typeEff == 11) {
                        PIXEL = 5;
                     }

                     try {
                        for (int frameIndex = 0; frameIndex < backgroudEffect.sum; frameIndex++) {
                           if (frameIndex % 3 == 0
                              && backgroudEffect.x[frameIndex] >= GameScr.cmx
                              && backgroudEffect.x[frameIndex] <= GameCanvas.w + GameScr.cmx
                              && backgroudEffect.y[frameIndex] >= GameScr.cmy
                              && backgroudEffect.y[frameIndex] <= GameCanvas.h + GameScr.cmy
                              && image != null) {
                              g2.drawRegion(
                                 image,
                                 0,
                                 PIXEL * backgroudEffect.frame[frameIndex],
                                 image.getWidth(),
                                 PIXEL,
                                 0,
                                 backgroudEffect.x[frameIndex],
                                 backgroudEffect.y[frameIndex],
                                 0
                              );
                           }
                        }
                     } catch (Exception ignoredException) {
                     }
                  }
               case 3:
               case 4:
               case 8:
               case 9:
               case 10:
               case 14:
               default:
                  break;
               case 13:
                  if (!isPaintFar) {
                     paintCloud2(g);
                  }
            }
         } catch (Exception ignoredException2) {
         }
      }
   }

   public static void paintFar(mGraphics g) {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex);
         mGraphics g2 = g;
         BackgroudEffect bgEffect2 = bgEffect;
         g2.translate(-g2.getTranslateX(), -g2.getTranslateY());
         if (bgEffect2.typeEff == 4) {
            for (int frameIndex = 0; frameIndex < bgEffect2.sum; frameIndex++) {
               g2.drawRegion(
                  imgSao, 0, 16 * bgEffect2.frame[frameIndex], 16, 16, 0, bgEffect2.x[frameIndex], bgEffect2.y[frameIndex], 0
               );
            }
         }

         if (bgEffect2.typeEff == 9) {
            g2.setColor(16777215);

            for (int wPIndex = 0; wPIndex < bgEffect2.num; wPIndex++) {
               g2.drawImage(
                  wP[wPIndex] == 1 ? imgChamTron1 : imgChamTron2, ((int) (bgEffect2.x[wPIndex])), ((int) (bgEffect2.y[wPIndex])), 3
               );
            }
         }
      }
   }

   public static void paintBackAll(mGraphics graphics) {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex);
         mGraphics graphics2 = graphics;
         BackgroudEffect bgEffect2 = bgEffect;
         switch (bgEffect.typeEff) {
            case 0:
               graphics2.setColor(10742731);

               for (int typeIndex = 0; typeIndex < bgEffect2.sum; typeIndex++) {
                  if (bgEffect2.type[typeIndex] != 2
                     && bgEffect2.x[typeIndex] >= GameScr.cmx
                     && bgEffect2.x[typeIndex] <= GameCanvas.w + GameScr.cmx
                     && bgEffect2.y[typeIndex] >= GameScr.cmy
                     && bgEffect2.y[typeIndex] <= GameCanvas.h + GameScr.cmy) {
                     graphics2.drawImage(imgMua2, ((int) (bgEffect2.x[typeIndex])), ((int) (bgEffect2.y[typeIndex])), 0);
                  }
               }
               break;
            case 1:
            case 2:
            case 5:
            case 6:
            case 7:
            case 11:
            case 15:
               if (bgEffect2.typeEff != 15 || (imgLacay = getCachedLeafImage()) != null) {
                  Image image = imgLacay;
                  mGraphics graphics3 = graphics2;
                  BackgroudEffect backgroudEffect = bgEffect2;
                  if (backgroudEffect.typeEff == 11) {
                     PIXEL = 5;
                  }

                  try {
                     for (int frameIndex = 0; frameIndex < backgroudEffect.sum; frameIndex++) {
                        if (frameIndex % 3 != 0
                           && backgroudEffect.x[frameIndex] >= GameScr.cmx
                           && backgroudEffect.x[frameIndex] <= GameCanvas.w + GameScr.cmx
                           && backgroudEffect.y[frameIndex] >= GameScr.cmy
                           && backgroudEffect.y[frameIndex] <= GameCanvas.h + GameScr.cmy
                           && image != null) {
                           graphics3.drawRegion(
                              image,
                              0,
                              PIXEL * backgroudEffect.frame[frameIndex],
                              image.getWidth(),
                              PIXEL,
                              0,
                              backgroudEffect.x[frameIndex],
                              backgroudEffect.y[frameIndex],
                              0
                           );
                        }
                     }
                  } catch (Exception ignoredException) {
                  }
               }
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
         }
      }
   }

   public static void update() {
      for (int bgEffectIndex = 0; bgEffectIndex < vBgEffect.size(); bgEffectIndex++) {
         BackgroudEffect bgEffect = (BackgroudEffect)vBgEffect.elementAt(bgEffectIndex);

         try {
            switch (bgEffect.typeEff) {
               case 0:
               case 12:
                  for (int activeEffIndex = 0; activeEffIndex < bgEffect.sum; activeEffIndex++) {
                     if (activeEffIndex % 3 != 0
                        && bgEffect.typeEff != 12
                        && TileMap.tileTypeAt(bgEffect.x[activeEffIndex], bgEffect.y[activeEffIndex] - GameCanvas.transY, 2)) {
                        bgEffect.activeEff[activeEffIndex] = true;
                     }

                     if (activeEffIndex % 3 == 0 && bgEffect.y[activeEffIndex] > GameCanvas.h + GameScr.cmy) {
                        bgEffect.x[activeEffIndex] = Res.random(-10, GameCanvas.w + 300) + GameScr.cmx;
                        bgEffect.y[activeEffIndex] = Res.random(-100, 0) + GameScr.cmy;
                     }

                     if (!bgEffect.activeEff[activeEffIndex]) {
                        bgEffect.y[activeEffIndex] = bgEffect.y[activeEffIndex] + bgEffect.vy[activeEffIndex];
                        bgEffect.x[activeEffIndex] = bgEffect.x[activeEffIndex] + bgEffect.vx[activeEffIndex];
                     }

                     if (bgEffect.activeEff[activeEffIndex]) {
                        bgEffect.t[activeEffIndex]++;
                        if (bgEffect.t[activeEffIndex] > 2) {
                           bgEffect.frame[activeEffIndex]++;
                           bgEffect.t[activeEffIndex] = 0;
                           if (bgEffect.frame[activeEffIndex] > 1) {
                              bgEffect.frame[activeEffIndex] = 0;
                              bgEffect.activeEff[activeEffIndex] = false;
                              bgEffect.x[activeEffIndex] = Res.random(-10, GameCanvas.w + 300) + GameScr.cmx;
                              bgEffect.y[activeEffIndex] = Res.random(-100, 0) + GameScr.cmy;
                           }
                        }
                     }
                  }
                  break;
               case 1:
               case 2:
               case 5:
               case 6:
               case 7:
               case 11:
               case 15:
                  for (int activeEffIndex2 = 0; activeEffIndex2 < bgEffect.sum; activeEffIndex2++) {
                     if (activeEffIndex2 % 3 != 0
                        && TileMap.tileTypeAt(bgEffect.x[activeEffIndex2], bgEffect.y[activeEffIndex2] + (TileMap.tileID == 15 ? 10 : 0), 2)
                        )
                      {
                        bgEffect.activeEff[activeEffIndex2] = true;
                     }

                     if (activeEffIndex2 % 3 == 0 && bgEffect.y[activeEffIndex2] > TileMap.pxh) {
                        bgEffect.x[activeEffIndex2] = Res.random(-10, TileMap.pxw + 50);
                        bgEffect.y[activeEffIndex2] = Res.random(-50, 0);
                     }

                     if (bgEffect.activeEff[activeEffIndex2]) {
                        bgEffect.t[activeEffIndex2]++;
                        if (bgEffect.t[activeEffIndex2] == 100) {
                           bgEffect.t[activeEffIndex2] = 0;
                           bgEffect.x[activeEffIndex2] = Res.random(-10, TileMap.pxw + 50);
                           bgEffect.y[activeEffIndex2] = Res.random(-50, 0);
                           bgEffect.activeEff[activeEffIndex2] = false;
                        }
                     } else {
                        for (int teleportIndex = 0; teleportIndex < Teleport.vTeleport.size(); teleportIndex++) {
                           Teleport teleport;
                           if ((teleport = (Teleport)Teleport.vTeleport.elementAt(teleportIndex)) != null
                              && teleport.paintFire
                              && bgEffect.x[activeEffIndex2] < teleport.x + 80
                              && bgEffect.x[activeEffIndex2] > teleport.x - 80
                              && bgEffect.y[activeEffIndex2] < teleport.y + 80
                              && bgEffect.y[activeEffIndex2] > teleport.y - 80) {
                              bgEffect.x[activeEffIndex2] = bgEffect.x[activeEffIndex2] + (bgEffect.x[activeEffIndex2] < teleport.x ? -10 : 10);
                           }
                        }

                        bgEffect.y[activeEffIndex2] = bgEffect.y[activeEffIndex2] + bgEffect.vy[activeEffIndex2];
                        bgEffect.x[activeEffIndex2] = bgEffect.x[activeEffIndex2] + bgEffect.vx[activeEffIndex2];
                        bgEffect.t[activeEffIndex2]++;
                        if (bgEffect.t[activeEffIndex2] > (bgEffect.typeEff != 2 ? 2 : 4)) {
                           if (bgEffect.typeEff != 11 && bgEffect.typeEff != 15) {
                              bgEffect.frame[activeEffIndex2]++;
                           }

                           bgEffect.t[activeEffIndex2] = 0;
                           if (bgEffect.frame[activeEffIndex2] > 3) {
                              bgEffect.frame[activeEffIndex2] = 0;
                           }
                        }
                     }
                  }
               case 3:
               default:
                  break;
               case 4:
                  for (int frameIndex = 0; frameIndex < bgEffect.sum; frameIndex++) {
                     bgEffect.t[frameIndex]++;
                     if (bgEffect.t[frameIndex] > 10) {
                        bgEffect.tick[frameIndex]++;
                        bgEffect.t[frameIndex] = 0;
                        if (bgEffect.tick[frameIndex] > 5) {
                           bgEffect.tick[frameIndex] = 0;
                        }

                        bgEffect.frame[frameIndex] = bgEffect.dem[bgEffect.tick[frameIndex]];
                     }
                  }
                  break;
               case 8:
                  bgEffect.tFire++;
                  if (bgEffect.tFire == 3) {
                     bgEffect.tFire = 0;
                     bgEffect.frameFire++;
                     if (bgEffect.frameFire > 1) {
                        bgEffect.frameFire = 0;
                     }
                  }

                  if (GameCanvas.gameTick % bgEffect.tStart == 0) {
                     bgEffect.isFly = true;
                  }

                  if (bgEffect.isFly) {
                     if (bgEffect.way == 1) {
                        bgEffect.xShip = bgEffect.xShip + bgEffect.speed;
                        if (bgEffect.xShip > TileMap.pxw + 50) {
                           bgEffect.reloadShip();
                        }
                     } else if (bgEffect.way == 2) {
                        bgEffect.xShip = bgEffect.xShip - bgEffect.speed;
                        if (bgEffect.xShip < -50) {
                           bgEffect.reloadShip();
                        }
                     } else if (bgEffect.way == 3) {
                        bgEffect.yShip = bgEffect.yShip + bgEffect.speed;
                        if (bgEffect.yShip > TileMap.pxh + 50) {
                           bgEffect.reloadShip();
                        }
                     } else if (bgEffect.way == 4) {
                        bgEffect.yShip = bgEffect.yShip - bgEffect.speed;
                        if (bgEffect.yShip < -50) {
                           bgEffect.reloadShip();
                        }
                     }
                  }
                  break;
               case 9:
                  for (int vxIndex = 0; vxIndex < bgEffect.num; vxIndex++) {
                     bgEffect.x[vxIndex] = bgEffect.x[vxIndex] - bgEffect.vx[vxIndex];
                     if (bgEffect.x[vxIndex] < -bgEffect.vx[vxIndex]) {
                        wP[vxIndex] = Res.abs(Res.random(1, 3));
                        bgEffect.vx[vxIndex] = wP[vxIndex];
                        bgEffect.x[vxIndex] = GameCanvas.w + bgEffect.vx[vxIndex];
                     }
                  }
                  break;
               case 10:
                  for (int vxIndex2 = 0; vxIndex2 < bgEffect.num; vxIndex2++) {
                     bgEffect.x[vxIndex2] = bgEffect.x[vxIndex2] - bgEffect.vx[vxIndex2];
                     if (bgEffect.x[vxIndex2] < -bgEffect.vx[vxIndex2] + GameScr.cmx) {
                        bgEffect.x[vxIndex2] = GameCanvas.w + bgEffect.vx[vxIndex2] + GameScr.cmx;
                     }
                  }
                  break;
               case 13:
                  if (mSystem.clientType != 1 && !GameCanvas.lowGraphic && nCloud > 0) {
                     int value = GameCanvas.currentScreen == GameScr.gI()
                        ? TileMap.pxw
                        : GameScr.cmx + GameCanvas.w;

                     for (int cloudXIndex = 0; cloudXIndex < nCloud; cloudXIndex++) {
                        int value2 = cloudXIndex + 1;
                        GameCanvas.cloudX[cloudXIndex] = GameCanvas.cloudX[cloudXIndex] - value2;
                        if (GameCanvas.cloudX[cloudXIndex] < -cloudw) {
                           GameCanvas.cloudX[cloudXIndex] = value + 100;
                        }
                     }
                  }
                  break;
               case 14:
                  if (mSystem.clientType != 1 && !GameCanvas.lowGraphic && isFog && --xfog < -fogw) {
                     xfog = 0;
                  }
            }
         } catch (Exception ignoredException) {
         }
      }
   }
}
