package nro;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class EffectData {
   public Image img;
   private ImageInfo[] imgInfo;
   private Frame[] frame;
   public short[] arrFrame;
   public short[][] anim_data = new short[16][];
   public int ID;
   public int typeData = 0;
   public int width;
   public int height;

   public final void readData(String patch) {
      InputStream inputStream = null;
      DataInputStream iss;

      try {
         inputStream = SmallDart.openResourceStream(patch);
         iss = new DataInputStream(inputStream);
      } catch (Exception ignoredException) {
         return;
      }

      this.readData(iss);
   }

   public final void readData2(String patch) {
      InputStream inputStream = null;
      DataInputStream msg;

      try {
         inputStream = SmallDart.openResourceStream(patch);
         msg = new DataInputStream(inputStream);
      } catch (Exception ignoredException) {
         return;
      }

      this.readEffect(msg);
   }

   private void readEffect(DataInputStream msg) {
      short dx = 0;
      short dy = 0;
      int value = 0;
      int value2 = 0;

      try {
         byte imgInfoCount = msg.readByte();
         this.imgInfo = new ImageInfo[imgInfoCount];

         for (int imgInfoIndex = 0; imgInfoIndex < imgInfoCount; imgInfoIndex++) {
            this.imgInfo[imgInfoIndex] = new ImageInfo();
            this.imgInfo[imgInfoIndex].ID = msg.readByte();
            this.imgInfo[imgInfoIndex].x0 = (short)msg.readUnsignedByte();
            this.imgInfo[imgInfoIndex].y0 = (short)msg.readUnsignedByte();
            this.imgInfo[imgInfoIndex].w = (short)msg.readUnsignedByte();
            this.imgInfo[imgInfoIndex].h = (short)msg.readUnsignedByte();
         }

         short frameCount = msg.readShort();
         this.frame = new Frame[frameCount];

         for (int frameIndex = 0; frameIndex < this.frame.length; frameIndex++) {
            this.frame[frameIndex] = new Frame();
            byte dxCount = msg.readByte();
            this.frame[frameIndex].dx = new short[dxCount];
            this.frame[frameIndex].dy = new short[dxCount];
            this.frame[frameIndex].idImg = new byte[dxCount];

            for (int dxIndex = 0; dxIndex < dxCount; dxIndex++) {
               this.frame[frameIndex].dx[dxIndex] = msg.readShort();
               this.frame[frameIndex].dy[dxIndex] = msg.readShort();
               this.frame[frameIndex].idImg[dxIndex] = msg.readByte();
               if (frameIndex == 0) {
                  if (dx > this.frame[frameIndex].dx[dxIndex]) {
                     dx = this.frame[frameIndex].dx[dxIndex];
                  }

                  if (dy > this.frame[frameIndex].dy[dxIndex]) {
                     dy = this.frame[frameIndex].dy[dxIndex];
                  }

                  if (value
                     < this.frame[frameIndex].dx[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].w) {
                     value = this.frame[frameIndex].dx[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].w;
                  }

                  if (value2
                     < this.frame[frameIndex].dy[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].h) {
                     value2 = this.frame[frameIndex].dy[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].h;
                  }

                  this.width = value - dx;
                  this.height = value2 - dy;
               }
            }
         }

         this.arrFrame = new short[msg.readShort()];

         for (int frameIndex2 = 0; frameIndex2 < this.arrFrame.length; frameIndex2++) {
            this.arrFrame[frameIndex2] = msg.readShort();
         }
      } catch (Exception ignoredException) {
      }
   }

   private void readData(DataInputStream iss) {
      short dx = 0;
      short dy = 0;
      int value = 0;
      int value2 = 0;

      try {
         short frameCount = iss.readByte();
         Res.outz("small num= " + frameCount);
         this.imgInfo = new ImageInfo[frameCount];

         for (int imgInfoIndex = 0; imgInfoIndex < frameCount; imgInfoIndex++) {
            this.imgInfo[imgInfoIndex] = new ImageInfo();
            this.imgInfo[imgInfoIndex].ID = iss.readByte();
            this.imgInfo[imgInfoIndex].x0 = (short)iss.readUnsignedByte();
            this.imgInfo[imgInfoIndex].y0 = (short)iss.readUnsignedByte();
            this.imgInfo[imgInfoIndex].w = (short)iss.readUnsignedByte();
            this.imgInfo[imgInfoIndex].h = (short)iss.readUnsignedByte();
         }

         short frameCount2 = iss.readShort();
         this.frame = new Frame[frameCount2];

         for (int frameIndex = 0; frameIndex < frameCount2; frameIndex++) {
            this.frame[frameIndex] = new Frame();
            byte dxCount = iss.readByte();
            this.frame[frameIndex].dx = new short[dxCount];
            this.frame[frameIndex].dy = new short[dxCount];
            this.frame[frameIndex].idImg = new byte[dxCount];

            for (int dxIndex = 0; dxIndex < dxCount; dxIndex++) {
               this.frame[frameIndex].dx[dxIndex] = iss.readShort();
               this.frame[frameIndex].dy[dxIndex] = iss.readShort();
               this.frame[frameIndex].idImg[dxIndex] = iss.readByte();
               if (frameIndex == 0) {
                  if (dx > this.frame[frameIndex].dx[dxIndex]) {
                     dx = this.frame[frameIndex].dx[dxIndex];
                  }

                  if (dy > this.frame[frameIndex].dy[dxIndex]) {
                     dy = this.frame[frameIndex].dy[dxIndex];
                  }

                  if (value
                     < this.frame[frameIndex].dx[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].w) {
                     value = this.frame[frameIndex].dx[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].w;
                  }

                  if (value2
                     < this.frame[frameIndex].dy[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].h) {
                     value2 = this.frame[frameIndex].dy[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].h;
                  }

                  this.width = value - dx;
                  this.height = value2 - dy;
               }
            }
         }

         frameCount = iss.readShort();
         this.arrFrame = new short[frameCount];
         if (this.ID >= 201) {
            short anim_dataIndex = 0;
            short[] shortValues = new short[frameCount];
            int shortValueCount = 0;
            String text = "";
            boolean condition = false;

            for (int frameIndex2 = 0; frameIndex2 < frameCount; frameIndex2++) {
               frameCount2 = iss.readShort();
               text = text + frameCount2 + ",";
               this.arrFrame[frameIndex2] = frameCount2;
               if (frameCount2 + 500 >= 500) {
                  shortValues[shortValueCount++] = frameCount2;
                  condition = true;
               } else {
                  anim_dataIndex = (short)Res.abs(frameCount2 + 500);
                  this.anim_data[anim_dataIndex] = new short[shortValueCount];
                  System.arraycopy(shortValues, 0, this.anim_data[anim_dataIndex], 0, shortValueCount);
                  shortValueCount = 0;
               }
            }

            if (!condition) {
               this.anim_data[0] = new short[shortValueCount];
               System.arraycopy(shortValues, 0, this.anim_data[0], 0, shortValueCount);
            } else {
               for (int anim_dataIndex2 = 0; anim_dataIndex2 < 16; anim_dataIndex2++) {
                  if (this.anim_data[anim_dataIndex2] == null) {
                     this.anim_data[anim_dataIndex2] = this.anim_data[2];
                  }
               }
            }
         } else {
            for (int frameIndex3 = 0; frameIndex3 < frameCount; frameIndex3++) {
               this.arrFrame[frameIndex3] = iss.readShort();
            }
         }
      } catch (Exception ignoredException) {
      }
   }

   public final void readDataNewBoss(byte[] data, byte typeread) {
      ByteArrayInputStream inputBytes = new ByteArrayInputStream(data);
      DataInputStream msg = new DataInputStream(inputBytes);
      this.readMobNew(msg, typeread);
   }

   public final void readData(byte[] data) {
      ByteArrayInputStream inputBytes = new ByteArrayInputStream(data);
      DataInputStream iss = new DataInputStream(inputBytes);
      this.readData(iss);
   }

   public final void paintFrame(mGraphics g, int f, int x, int y, int trans, int layer) {
      if (this.frame != null && this.frame.length != 0) {
         Frame frameValue = this.frame[f];

         for (int dxIndex = 0; dxIndex < frameValue.dx.length; dxIndex++) {
            byte idImg = frameValue.idImg[dxIndex];
            EffectData effectData = this;

            ImageInfo imgInfoValue;
            label51: {
               for (int imgInfoIndex = 0; imgInfoIndex < effectData.imgInfo.length; imgInfoIndex++) {
                  if (effectData.imgInfo[imgInfoIndex].ID == idImg) {
                     imgInfoValue = effectData.imgInfo[imgInfoIndex];
                     break label51;
                  }
               }

               imgInfoValue = null;
            }

            ImageInfo imgInfoValue2 = imgInfoValue;

            try {
               if (trans == -1) {
                  g.drawRegion(
                     this.img,
                     imgInfoValue2.x0,
                     imgInfoValue2.y0,
                     imgInfoValue2.w,
                     imgInfoValue2.h,
                     0,
                     x + frameValue.dx[dxIndex],
                     y + frameValue.dy[dxIndex],
                     0
                  );
               } else if (trans == 0) {
                  g.drawRegion(
                     this.img,
                     imgInfoValue2.x0,
                     imgInfoValue2.y0,
                     imgInfoValue2.w,
                     imgInfoValue2.h,
                     0,
                     x + frameValue.dx[dxIndex],
                     y + frameValue.dy[dxIndex] - (layer < 4 && layer > 0 ? GameCanvas.transY : 0),
                     0
                  );
               } else if (trans == 1) {
                  g.drawRegion(
                     this.img,
                     imgInfoValue2.x0,
                     imgInfoValue2.y0,
                     imgInfoValue2.w,
                     imgInfoValue2.h,
                     2,
                     x - frameValue.dx[dxIndex],
                     y + frameValue.dy[dxIndex] - (layer < 4 && layer > 0 ? GameCanvas.transY : 0),
                     StaticObj.TOP_RIGHT
                  );
               } else if (trans == 2) {
                  g.drawRegion(
                     this.img,
                     imgInfoValue2.x0,
                     imgInfoValue2.y0,
                     imgInfoValue2.w,
                     imgInfoValue2.h,
                     7,
                     x - frameValue.dx[dxIndex],
                     y + frameValue.dy[dxIndex] - (layer < 4 && layer > 0 ? GameCanvas.transY : 0),
                     StaticObj.VCENTER_HCENTER
                  );
               }
            } catch (Exception ignoredException) {
            }
         }
      }
   }

   private void readMobNew(DataInputStream msg, byte typeread) {
      short dx = 0;
      short dy = 0;
      int value = 0;
      int value2 = 0;

      try {
         byte dxCount = msg.readByte();
         this.imgInfo = new ImageInfo[dxCount];

         for (int imgInfoIndex = 0; imgInfoIndex < dxCount; imgInfoIndex++) {
            this.imgInfo[imgInfoIndex] = new ImageInfo();
            this.imgInfo[imgInfoIndex].ID = msg.readByte();
            if (typeread == 1) {
               this.imgInfo[imgInfoIndex].x0 = (short)msg.readUnsignedByte();
               this.imgInfo[imgInfoIndex].y0 = (short)msg.readUnsignedByte();
            } else {
               this.imgInfo[imgInfoIndex].x0 = msg.readShort();
               this.imgInfo[imgInfoIndex].y0 = msg.readShort();
            }

            this.imgInfo[imgInfoIndex].w = (short)msg.readUnsignedByte();
            this.imgInfo[imgInfoIndex].h = (short)msg.readUnsignedByte();
         }

         short frameCount = msg.readShort();
         this.frame = new Frame[frameCount];

         for (int frameIndex = 0; frameIndex < this.frame.length; frameIndex++) {
            this.frame[frameIndex] = new Frame();
            dxCount = msg.readByte();
            this.frame[frameIndex].dx = new short[dxCount];
            this.frame[frameIndex].dy = new short[dxCount];
            this.frame[frameIndex].idImg = new byte[dxCount];

            for (int dxIndex = 0; dxIndex < dxCount; dxIndex++) {
               this.frame[frameIndex].dx[dxIndex] = msg.readShort();
               this.frame[frameIndex].dy[dxIndex] = msg.readShort();
               this.frame[frameIndex].idImg[dxIndex] = msg.readByte();
               if (frameIndex == 0) {
                  if (dx > this.frame[frameIndex].dx[dxIndex]) {
                     dx = this.frame[frameIndex].dx[dxIndex];
                  }

                  if (dy > this.frame[frameIndex].dy[dxIndex]) {
                     dy = this.frame[frameIndex].dy[dxIndex];
                  }

                  if (value
                     < this.frame[frameIndex].dx[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].w) {
                     value = this.frame[frameIndex].dx[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].w;
                  }

                  if (value2
                     < this.frame[frameIndex].dy[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].h) {
                     value2 = this.frame[frameIndex].dy[dxIndex]
                        + this.imgInfo[this.frame[frameIndex].idImg[dxIndex]].h;
                  }

                  this.width = value - dx;
                  this.height = value2 - dy;
               }
            }
         }

         this.arrFrame = new short[msg.readShort()];

         for (int frameIndex2 = 0; frameIndex2 < this.arrFrame.length; frameIndex2++) {
            this.arrFrame[frameIndex2] = msg.readShort();
         }
      } catch (Exception ignoredException) {
      }
   }
}
