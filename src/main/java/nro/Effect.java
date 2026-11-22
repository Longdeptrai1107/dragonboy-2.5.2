package nro;

import java.io.InputStream;

public final class Effect {
   public int effId;
   public int typeEff;
   public int indexFrom;
   public int indexTo;
   private boolean isNearPlayer;
   private Char c;
   public int t;
   private int currFrame;
   public int x;
   public int y;
   private int loop;
   private int tLoop;
   private int tLoopCount;
   private boolean isPaint = true;
   public int layer;
   public int isStand = 0;
   public static MyVector vEffData = new MyVector("vEffData");
   public int trans = 0;
   public long timeExist = 0L;
   private static MyVector vLastEff = new MyVector("vLastEff");
   public static MyVector newEff = new MyVector("vNewEff");
   public static MyVector vdowloadEff = new MyVector("vdowloadEff");
   private int[] khangia1 = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
   private int[] khangia2 = new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3, 3};
   private int[] khangia3 = new int[]{4, 4, 4, 4, 4, 5, 5, 5, 5, 5};
   private int[] khangia4 = new int[]{6, 6, 6, 6, 6, 7, 7, 7, 7, 7};
   private int[] khangia5 = new int[]{8, 8, 8, 8, 8, 9, 9, 9, 9, 9};
   private boolean isGetTime = false;
   private short[] data;

   public static void GetCharEff(Char c) {
      for (int index = 0; index < 1799; index++) {
         c.removeEffChar(0, index + 201);
      }

      if (c.bag >= 201 && c.bag < 2000) {
         Effect e;
         (e = new Effect(c.bag, c, 2, -1, 10, (byte)1)).typeEff = 5;
         c.addEffChar(e);
      }
   }

   private static void removeEffData(int id) {
      for (int effDataIndex = 0; effDataIndex < vEffData.size(); effDataIndex++) {
         EffectData effData;
         if ((effData = (EffectData)vEffData.elementAt(effDataIndex)).ID == id) {
            vEffData.removeElement(effData);
            return;
         }
      }
   }

   public static EffectData getEffDataById(int id) {
      for (int effDataIndex = 0; effDataIndex < vEffData.size(); effDataIndex++) {
         EffectData effData;
         if ((effData = (EffectData)vEffData.elementAt(effDataIndex)).ID == id) {
            return effData;
         }
      }

      return null;
   }

   public Effect() {
   }

   public Effect(int id, Char cValue, int layerValue, int loopValue, int loopCount, byte isStandValue) {
      this.c = cValue;
      this.effId = id;
      this.layer = layerValue;
      this.loop = loopValue;
      this.tLoop = loopCount;
      this.isStand = isStandValue;
      if (getEffDataById(id) == null) {
         EffectData effectData;
         (effectData = new EffectData()).ID = id;
         if (id >= 42 && id <= 46) {
            id = 106;
         }

         String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + id + "/data";
         InputStream inputStream = null;

         try {
            inputStream = SmallDart.openResourceStream(patch);
         } catch (Exception ignoredException) {
         }

         if (inputStream == null) {
            Service.gI().getEffData((short)id);
         } else {
            if (id > 100 && id < 200) {
               effectData.readData2(patch);
            } else {
               effectData.readData(((java.lang.String) (patch)));
            }

            effectData.img = mSystem.loadImage("/effectdata/" + id + "/img.png");
         }

         EffectData effData = effectData;
         vEffData.addElement(effData);
      }

      this.indexFrom = -1;
      this.indexTo = -1;
      this.trans = -1;
      this.typeEff = 4;
      if (id == 78) {
         this.typeEff = 5;
      }
   }

   public Effect(int id, int xValue, int yValue, int layerValue, int loopValue, int loopCount) {
      this.x = xValue;
      this.y = yValue;
      this.effId = id;
      this.layer = layerValue;
      this.loop = loopValue;
      this.tLoop = loopCount;
      if (getEffDataById(id) == null) {
         EffectData effectData;
         (effectData = new EffectData()).ID = id;
         if (id >= 42 && id <= 46) {
            id = 106;
         }

         String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + id + "/data";
         InputStream inputStream = null;

         try {
            inputStream = SmallDart.openResourceStream(patch);
         } catch (Exception ignoredException) {
         }

         if (inputStream == null) {
            Service.gI().getEffData((short)id);
         } else {
            if (id > 100 && id < 200) {
               effectData.readData2(patch);
            } else {
               effectData.readData(((java.lang.String) (patch)));
            }

            effectData.img = mSystem.loadImage("/effectdata/" + id + "/img.png");
         }

         EffectData effData = effectData;
         vEffData.addElement(effData);
         if (vLastEff.size() > 20) {
            removeEffData(Integer.parseInt((String)vLastEff.elementAt(0)));
            vLastEff.removeElementAt(0);
         }

         vLastEff.addElement(String.valueOf(this.effId));
      }

      this.indexFrom = -1;
      this.indexTo = -1;
      if (id == 78) {
         this.typeEff = 5;
      } else {
         this.typeEff = 1;
      }

      if (!isExistNewEff(String.valueOf(this.effId))) {
         newEff.addElement(String.valueOf(this.effId));
      }
   }

   private static boolean isExistNewEff(String id) {
      for (int newEffIndex = 0; newEffIndex < newEff.size(); newEffIndex++) {
         if (((String)newEff.elementAt(newEffIndex)).equals(id)) {
            return true;
         }
      }

      return false;
   }

   private boolean isPaintz() {
      return this.isPaint;
   }

   public final void paintUnderBackground(mGraphics g, int xLayer, int yLayer) {
      if (this.isPaintz()) {
         if (getEffDataById(this.effId).img != null) {
            getEffDataById(this.effId)
               .paintFrame(
                  g, this.currFrame, this.x + xLayer, this.y + yLayer, this.trans, this.layer
               );
         }
      }
   }

   public final void paint(mGraphics graphics) {
      if (this.isPaintz()) {
         if (getEffDataById(this.effId) != null) {
            if (getEffDataById(this.effId).img != null) {
               try {
                  getEffDataById(this.effId)
                     .paintFrame(graphics, this.currFrame, this.x, this.y, this.trans, this.layer);
                  return;
               } catch (Exception exception) {
                  Res.outz(
                     "Effect "
                        + this.effId
                        + " err at frame "
                        + this.currFrame
                        + " vitri "
                        + this.x
                        + "_"
                        + this.y
                        + "   "
                        + this.layer
                  );
                  exception.printStackTrace();
               }
            }
         }
      }
   }

   public final void update() {
      try {
         if (this.effId >= 42 && this.effId <= 46) {
            Effect effect = this;
            if (this.effId == 42) {
               effect.currFrame = effect.khangia1[effect.t];
            }

            if (effect.effId == 43) {
               effect.currFrame = effect.khangia2[effect.t];
            }

            if (effect.effId == 44) {
               effect.currFrame = effect.khangia3[effect.t];
            }

            if (effect.effId == 45) {
               effect.currFrame = effect.khangia4[effect.t];
            }

            if (effect.effId == 46) {
               effect.currFrame = effect.khangia5[effect.t];
            }

            effect.t++;
            if (effect.t > effect.khangia1.length - 1) {
               effect.t = 0;
            }

            return;
         }

         if (getEffDataById(this.effId) == null) {
            return;
         }

         if (getEffDataById(this.effId).img == null) {
            return;
         }

         if (this.typeEff == 5) {
            EffectData effDataById = getEffDataById(this.effId);
            int anim_dataIndex = this.c.statusMe;
            EffectData effDataById2 = effDataById;
            if (anim_dataIndex >= effDataById2.anim_data.length) {
               anim_dataIndex = 0;
            }

            this.data = effDataById2.anim_data[anim_dataIndex] == null ? new short[1] : effDataById2.anim_data[anim_dataIndex];
         } else {
            this.data = getEffDataById(this.effId).arrFrame;
         }

         if (this.data != null) {
            if (!this.isGetTime) {
               this.isGetTime = true;
               int maximum;
               if ((maximum = this.data.length - 1) > 0 && this.typeEff != 1) {
                  this.t = Res.random(0, maximum);
               }

               if (this.typeEff == 0) {
                  this.t = Res.random(this.indexFrom, this.indexTo);
               }
            }

            switch (this.typeEff) {
               case 0:
                  if (Res.inRect(
                        this.x - 50,
                        this.y - 50,
                        100,
                        100,
                        Char.myCharz().cx,
                        Char.myCharz().cy
                     )
                     && this.t > this.indexFrom
                     && this.t < this.indexTo) {
                     if (this.t < this.indexTo) {
                        this.t = this.indexTo;
                     }

                     this.isNearPlayer = true;
                  }

                  if (!this.isNearPlayer) {
                     this.t++;
                     if (this.t == this.indexTo) {
                        this.t = this.indexFrom;
                     }
                  } else if (this.t < this.data.length) {
                     this.t++;
                  }
                  break;
               case 1:
               case 3:
                  if (this.t < this.data.length) {
                     this.t++;
                  }
                  break;
               case 2:
                  if (this.t < this.data.length) {
                     this.t++;
                  }

                  this.tLoopCount++;
                  if (this.tLoopCount == this.tLoop) {
                     this.tLoopCount = 0;
                     this.trans = Res.random(0, 2);
                  }
                  break;
               case 4:
                  this.x = this.c.cx;
                  this.y = this.c.cy;
                  if (this.t < this.data.length) {
                     this.t++;
                  }
                  break;
               case 5:
                  this.trans = this.c.cdir == 1 ? 0 : 1;
                  if (this.c.cdir == 1) {
                     this.x = this.c.cx - 15;
                  } else {
                     this.x = this.c.cx + 15;
                  }

                  if (this.c.isMonkey == 0) {
                     this.y = this.c.cy - 25;
                  } else {
                     this.y = this.c.cy - 35;
                  }

                  if (this.t < this.data.length) {
                     this.t++;
                  }
            }

            if (this.t == this.data.length / 2
               && (this.effId == 62 || this.effId == 63 || this.effId == 64 || this.effId == 65)) {
               SoundMn.playSound();
            }

            if (this.t <= this.data.length - 1) {
               this.currFrame = this.data[this.t];
            }
         }

         if (this.t < this.data.length - 1) {
            this.isPaint = true;
            return;
         }

         if (this.typeEff == 0 || this.typeEff == 3) {
            this.isPaint = false;
         }

         if (this.tLoop == -1) {
            EffecMn.vEff.removeElement(this);
         }

         if (this.typeEff == 2) {
            this.t = 0;
            return;
         }

         if (this.typeEff == 1 && this.loop == 1) {
            this.isPaint = false;
         }

         if (this.typeEff == 4 || this.typeEff == 5) {
            if (this.loop == -1) {
               this.t = 0;
               return;
            }

            this.tLoopCount++;
            if (this.tLoopCount == this.tLoop) {
               this.tLoopCount = 0;
               this.loop--;
               this.t = 0;
               if (this.loop == 0) {
                  this.c.removeEffChar(0, this.effId);
               }
            }

            return;
         }

         this.isNearPlayer = false;
         if (this.loop == -1) {
            this.tLoopCount++;
            this.t = 0;
            if (this.tLoopCount == this.tLoop) {
               this.tLoopCount = 0;
               if (this.tLoop > 1) {
                  this.trans = Res.random(0, 2);
                  return;
               }
            }
         } else {
            this.tLoopCount++;
            this.t = 0;
            if (this.tLoopCount == this.tLoop) {
               this.tLoopCount = 0;
               this.loop--;
               if (this.loop == 0) {
                  EffecMn.vEff.removeElement(this);
                  return;
               }
            }
         }
      } catch (Exception exception) {
         System.out.println("err  Effect.update: " + this.effId + "  " + this.typeEff);
         exception.printStackTrace();
         EffecMn.vEff.removeElement(this);
      }
   }
}
