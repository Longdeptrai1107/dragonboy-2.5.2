package nro;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class SmallImage {
   public static int[][] smallImg;
   public static Image[] imgbig;
   public static Small[] imgNew;
   public static MyVector vKeys = new MyVector("vKeys");
   public static Image imgEmpty = null;
   public static byte[] newSmallVersion;
   public static MyVector vt_images_watingDowload = new MyVector("");
   private static int smallCount;
   public static short maxSmall;

   public static void loadBigRMS() {
      if (imgbig == null) {
         imgbig = new Image[]{
            mSystem.loadImageRMS("/img/Big0.png"),
            mSystem.loadImageRMS("/img/Big1.png"),
            mSystem.loadImageRMS("/img/Big2.png"),
            mSystem.loadImageRMS("/img/Big3.png")
         };
         System.gc();
      }
   }

   public static void loadBigImage() {
      System.gc();
      imgEmpty = Image.createRGBImage(new int[]{-2013265920}, 1, 1, true);
   }

   public SmallImage() {
      readImage();
   }

   public static void init() {
      new SmallImage();
   }

   private static void readImage() {
      try {
         DataInputStream input;
         short valueCount;
         smallImg = new int[valueCount = (input = new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NR_image")))).readShort()][5];

         for (int smallImgIndex = 0; smallImgIndex < valueCount; smallImgIndex++) {
            smallImg[smallImgIndex][0] = input.readUnsignedByte();
            smallImg[smallImgIndex][1] = input.readShort();
            smallImg[smallImgIndex][2] = input.readShort();
            smallImg[smallImgIndex][3] = input.readShort();
            smallImg[smallImgIndex][4] = input.readShort();
            if ((smallImgIndex < 84 || smallImgIndex > 90)
               && (smallImgIndex < 372 || smallImgIndex > 384)
               && (smallImgIndex < 371 || smallImgIndex > 377)
               && (smallImg[smallImgIndex][1] >= 256 || smallImg[smallImgIndex][3] >= 256 || smallImg[smallImgIndex][2] >= 256 || smallImg[smallImgIndex][4] >= 256)
               )
             {
               smallImg[smallImgIndex] = null;
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static void clearHastable() {
   }

   private static boolean hasAtlasImage(int index) {
      Image[] imgbigValue = imgbig;
      return imgbig != null && index >= 0 && index < imgbigValue.length && imgbigValue[index] != null;
   }

   private static Small getValidatedCachedImage(int index) {
      Small[] imgNewValue = imgNew;
      if (imgNew != null && index >= 0 && index < imgNewValue.length) {
         Small validatedCachedImage;
         if ((validatedCachedImage = imgNewValue[index]) == null) {
            return null;
         }

         boolean condition;
         label35: {
            Image image = validatedCachedImage.img;
            if (validatedCachedImage.img != null) {
               if (image == imgEmpty) {
                  condition = true;
                  break label35;
               }

               if (mGraphics.getImageWidth(image) > 0 && mGraphics.getImageHeight(image) > 0) {
                  condition = true;
                  break label35;
               }
            }

            condition = false;
         }

         if (!condition) {
            if (imgNew == imgNewValue && imgNewValue[index] == validatedCachedImage) {
               imgNewValue[index] = null;
            }

            return null;
         } else {
            return validatedCachedImage;
         }
      } else {
         return null;
      }
   }

   public static void createImage(int id) {
      Small[] imgNewValue = imgNew;
      if (imgNew != null && id >= 0 && id < imgNewValue.length) {
         if (getValidatedCachedImage(id) == null) {
            if (mGraphics.zoomLevel == 1) {
               Image img;
               if ((img = mSystem.loadImage("/SmallImage/Small" + id + ".png")) != null) {
                  imgNewValue[id] = new Small(img, id);
                  return;
               }

               imgNewValue[id] = new Small(imgEmpty, id);
               if (GameCanvas.currentScreen == GameCanvas._SelectCharScr) {
                  Service service;
                  if ((service = Service.gI()) != null) {
                     service.requestIcon(id);
                     return;
                  }
               } else if (vt_images_watingDowload != null) {
                  vt_images_watingDowload.addElement(imgNewValue[id]);
                  return;
               }
            } else {
               Image img2;
               if ((img2 = mSystem.loadImage("/SmallImage/Small" + id + ".png")) != null) {
                  imgNewValue[id] = new Small(img2, id);
                  return;
               }

               boolean condition = false;
               byte[] rMS;
               if ((rMS = Rms.loadRMS(mGraphics.zoomLevel + "Small" + id)) == null) {
                  condition = true;
               } else {
                  if (newSmallVersion != null && (id >= newSmallVersion.length || rMS.length % 127 != newSmallVersion[id])) {
                     condition = true;
                  }

                  if (!condition) {
                     Image img3;
                     if ((img3 = Image.createImage(rMS, 0, rMS.length)) != null) {
                        imgNewValue[id] = new Small(img3, id);
                     } else {
                        condition = true;
                     }
                  }
               }

               if (condition) {
                  imgNewValue[id] = new Small(imgEmpty, id);
                  if (GameCanvas.currentScreen == GameCanvas._SelectCharScr) {
                     Service service2;
                     if ((service2 = Service.gI()) != null) {
                        service2.requestIcon(id);
                        return;
                     }
                  } else if (vt_images_watingDowload != null) {
                     vt_images_watingDowload.addElement(imgNewValue[id]);
                  }
               }
            }
         }
      }
   }

   public static void touchCachedImage(int imgNewIndex, Image image) {
      if (image != null && imgNew != null && imgNewIndex >= 0 && imgNewIndex < imgNew.length) {
         Small imgNewValue;
         if ((imgNewValue = imgNew[imgNewIndex]) != null && imgNewValue.img == image) {
            Small imgNewValue2;
            (imgNewValue2 = imgNewValue).timePaint = imgNewValue2.timeUpdate;
         }
      }
   }

   public static void putCachedSmallImage(int index, Image img) {
      if (img != null) {
         Small[] imgNewValue = imgNew;
         if (imgNew != null && index >= 0 && index < imgNewValue.length) {
            Small small;
            if ((small = imgNewValue[index]) == null) {
               small = new Small(img, index);
               if (imgNew == imgNewValue && imgNewValue[index] == null) {
                  imgNewValue[index] = small;
               }
            } else {
               if (imgNew == imgNewValue && imgNewValue[index] == small) {
                  small.img = img;
               }
            }
         }
      }
   }

   public static void drawSmallImageUntransformed(mGraphics g, int id, int x, int y, int value, int anchor) {
      if (g != null && id >= 0) {
         drawSmallImage(g, id, x, y, 0, anchor);
      }
   }

   public static void drawSmallImage(mGraphics g, int id, int x, int y, int transform, int anchor) {
      if (g != null && id >= 0) {
         try {
            if (imgbig == null) {
               Small validatedCachedImage;
               if ((validatedCachedImage = getValidatedCachedImage(id)) == null) {
                  createImage(id);
                  return;
               }

               validatedCachedImage.paint(g, transform, x, y, anchor);
               return;
            }

            if (smallImg != null) {
               if (id >= 0
                  && id < smallImg.length
                  && smallImg[id] != null
                  && smallImg[id].length >= 5
                  && smallImg[id][1] < 256
                  && smallImg[id][3] < 256
                  && smallImg[id][2] < 256
                  && smallImg[id][4] < 256) {
                  if (smallImg[id][0] != 4 && hasAtlasImage(smallImg[id][0])) {
                     g.drawRegion(
                        imgbig[smallImg[id][0]],
                        smallImg[id][1],
                        smallImg[id][2],
                        smallImg[id][3],
                        smallImg[id][4],
                        transform,
                        x,
                        y,
                        anchor
                     );
                     return;
                  }

                  Small validatedCachedImage2;
                  if ((validatedCachedImage2 = getValidatedCachedImage(id)) == null) {
                     createImage(id);
                     return;
                  }

                  validatedCachedImage2.paint(g, transform, x, y, anchor);
                  return;
               }

               Small validatedCachedImage3;
               if ((validatedCachedImage3 = getValidatedCachedImage(id)) == null) {
                  createImage(id);
                  return;
               }

               validatedCachedImage3.paint(g, transform, x, y, anchor);
               return;
            }

            if (GameCanvas.currentScreen != GameScr.gI()) {
               Small validatedCachedImage4;
               if ((validatedCachedImage4 = getValidatedCachedImage(id)) == null) {
                  createImage(id);
                  return;
               }

               validatedCachedImage4.paint(g, transform, x, y, anchor);
               return;
            }
         } catch (Exception ignoredException) {
         }
      }
   }

   public static void drawSmallImage(mGraphics graphics, int id, int transform, int x, int y, int width, int height, int transform2, int anchor) {
      if (graphics != null && id >= 0) {
         try {
            if (imgbig == null) {
               Small validatedCachedImage;
               if ((validatedCachedImage = getValidatedCachedImage(id)) == null) {
                  createImage(id);
                  return;
               }

               validatedCachedImage.paint(graphics, transform2, transform, x, y, width, height, anchor);
               return;
            }

            if (smallImg != null) {
               if (id >= 0
                  && id < smallImg.length
                  && smallImg[id] != null
                  && smallImg[id].length >= 5
                  && smallImg[id][1] < 256
                  && smallImg[id][3] < 256
                  && smallImg[id][2] < 256
                  && smallImg[id][4] < 256) {
                  if (smallImg[id][0] != 4 && hasAtlasImage(smallImg[id][0])) {
                     graphics.drawRegion(imgbig[smallImg[id][0]], 0, transform * width, width, height, transform2, x, y, anchor);
                     return;
                  }

                  Small validatedCachedImage2;
                  if ((validatedCachedImage2 = getValidatedCachedImage(id)) == null) {
                     createImage(id);
                     return;
                  }

                  validatedCachedImage2.paint(graphics, transform2, transform, x, y, width, height, anchor);
                  return;
               }

               Small validatedCachedImage3;
               if ((validatedCachedImage3 = getValidatedCachedImage(id)) == null) {
                  createImage(id);
                  return;
               }

               validatedCachedImage3.paint(graphics, transform2, transform, x, y, width, height, anchor);
               return;
            }

            if (GameCanvas.currentScreen != GameScr.gI()) {
               Small validatedCachedImage4;
               if ((validatedCachedImage4 = getValidatedCachedImage(id)) == null) {
                  createImage(id);
                  return;
               }

               validatedCachedImage4.paint(graphics, transform2, transform, x, y, width, height, anchor);
               return;
            }
         } catch (Exception ignoredException) {
         }
      }
   }

   public static void update() {
      Small[] imgNewValue = imgNew;
      if (GameCanvas.gameTick % 1000 == 0 && imgNewValue != null) {
         for (int index = 0; index < imgNewValue.length; index++) {
            Small small;
            if ((small = imgNewValue[index]) != null) {
               Small small2;
               (small2 = small).timeUpdate++;
               Char player;
               boolean condition = (player = Char.myCharz()) != null && player.isCharBodyImageID(small2.id);
               if (small2.timeUpdate - small2.timePaint > 1
                  && !condition
                  && imgNew != null
                  && small2.id >= 0
                  && small2.id < imgNew.length
                  && imgNew[small2.id] == small2) {
                  imgNew[small2.id] = null;
               }

               smallCount++;
            }
         }
      }
   }
}
