package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class BigBoss extends Mob implements IMapObject {
   private static Image shadowBig = mSystem.loadImage("/mainImage/shadowBig.png");
   private static EffectData data;
   public int xTo;
   public int yTo;
   public boolean haftBody;
   private int xSd;
   private int ySd;
   private boolean isOutMap;
   private int wCount;
   private boolean isShadown = true;
   private int tick;
   private int frame;
   private boolean flyUp;
   private boolean flyDown;
   private int dy;
   private int tShock;
   private Char[] charAttack;
   private long[] dameHP;
   private byte type;
   private int[] stand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
   private int[] stand_1 = new int[]{37, 37, 37, 38, 38, 38, 39, 39, 40, 40, 40, 39, 39, 39, 38, 38, 38};
   private int[] attack1;
   private int[] attack2;
   private int[] attack3;
   private int[] attack2_1;
   private int[] attack3_1;
   private int[] fly;
   private int[] hitground;
   private boolean shock;

   public final void getDataB2() {
      data = null;
      data = new EffectData();
      String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + 100 + "/data";

      try {
         data.readData2(patch);
         data.img = mSystem.loadZoomedResourceImage("/effectdata/100/img.png");
      } catch (Exception ignoredException) {
         Service.gI().requestModTemplate(super.templateId);
      }

      super.status = 2;
      super.w = data.width;
      super.h = data.height;
   }

   public final void setBody(short id) {
      super.changBody = true;
      super.smallBody = id;
   }

   public final void clearBody() {
      super.changBody = false;
   }

   public BigBoss(int id, short px, short py, int templateID, long hpValue, long maxhp, int value) {
      int[] intValues = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2};
      intValues = new int[]{1, 1, 2, 2, 3, 3, 2};
      this.attack1 = new int[]{0, 0, 34, 34, 35, 35, 36, 36, 2, 2, 1, 1};
      this.attack2 = new int[]{0, 0, 0, 4, 4, 6, 6, 9, 9, 10, 10, 13, 13, 15, 15, 17, 17, 19, 19, 21, 21, 23, 23};
      this.attack3 = new int[]{0, 0, 1, 1, 4, 4, 6, 6, 8, 8, 25, 25, 26, 26, 28, 28, 30, 30, 32, 32, 2, 2, 1, 1};
      this.attack2_1 = new int[]{37, 37, 5, 5, 7, 7, 11, 11, 14, 14, 16, 16, 18, 18, 20, 20, 22, 22, 24, 24};
      this.attack3_1 = new int[]{37, 37, 37, 38, 38, 5, 5, 7, 7, 11, 11, 27, 27, 29, 29, 31, 31, 33, 33, 38, 38};
      this.fly = new int[]{8, 8, 9, 9, 10, 10, 12, 12};
      this.hitground = new int[]{0, 0, 1, 1, 4, 4, 6, 6, 8, 8, 25, 25, 26, 26, 28, 28, 30, 30, 32, 32, 2, 2, 1, 1};
      byte[] dataValue = new byte[]{-1, 1};
      super.xFirst = super.x = px + 20;
      super.yFirst = super.y = py;
      super.mobId = id;
      super.hp = hpValue;
      super.maxHp = maxhp;
      super.templateId = 70;
      super.color = 100;
      this.updateHp_bar();
      if (value == 0) {
         BigBoss bigBoss = this;
         data = null;
         data = new EffectData();
         String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + 101 + "/data";

         try {
            data.readData2(patch);
            data.img = mSystem.loadZoomedResourceImage("/effectdata/101/img.png");
         } catch (Exception ignoredException) {
            Service.gI().requestModTemplate(bigBoss.templateId);
         }

         bigBoss.w = data.width;
         bigBoss.h = data.height;
      }

      if (value == 1) {
         this.getDataB2();
      }

      if (value == 2) {
         this.getDataB2();
         this.haftBody = true;
      }

      super.status = 2;
   }

   public final void checkFrameTick(int[] array) {
      this.tick++;
      if (this.tick > array.length - 1) {
         this.tick = 0;
      }

      this.frame = array[this.tick];
   }

   public final void updateSuperEff() {
   }

   public final void update() {
      if (this.isUpdate()) {
         BigBoss bigBoss = this;
         byte size = TileMap.size;
         bigBoss.xSd = bigBoss.x;
         bigBoss.wCount = 0;
         if (bigBoss.ySd > 0 && !TileMap.tileTypeAt(bigBoss.xSd, bigBoss.ySd, 2)) {
            if (TileMap.tileTypeAt(bigBoss.xSd / size, bigBoss.ySd / size) == 0) {
               bigBoss.isOutMap = true;
            } else if (TileMap.tileTypeAt(bigBoss.xSd / size, bigBoss.ySd / size) != 0
               && !TileMap.tileTypeAt(bigBoss.xSd, bigBoss.ySd, 2)) {
               bigBoss.xSd = bigBoss.x;
               bigBoss.ySd = bigBoss.y;
               bigBoss.isOutMap = false;
            }

            while (bigBoss.isOutMap && bigBoss.wCount < 10) {
               bigBoss.wCount++;
               bigBoss.ySd += 24;
               if (TileMap.tileTypeAt(bigBoss.xSd, bigBoss.ySd, 2)) {
                  if (bigBoss.ySd % 24 != 0) {
                     bigBoss.ySd = bigBoss.ySd - bigBoss.ySd % 24;
                  }
                  break;
               }
            }
         }

         switch (super.status) {
            case 0:
            case 1:
               bigBoss = this;
               this.checkFrameTick(bigBoss.haftBody ? bigBoss.stand_1 : bigBoss.stand);
               if (GameCanvas.gameTick % 5 == 0) {
                  ServerEffect.addServerEffect(
                     167,
                     Res.random(bigBoss.x - 60 / 2, bigBoss.x + 60 / 2),
                     Res.random(bigBoss.getY() + 40 / 2, bigBoss.getY() + 40),
                     1
                  );
               }

               if (bigBoss.x != bigBoss.xFirst || bigBoss.y != bigBoss.yFirst) {
                  bigBoss.x = bigBoss.x + (bigBoss.xFirst - bigBoss.x) / 4;
                  bigBoss.y = bigBoss.y + (bigBoss.yFirst - bigBoss.y) / 4;
               }
               break;
            case 2:
               bigBoss = this;
               this.checkFrameTick(bigBoss.haftBody ? bigBoss.stand_1 : bigBoss.stand);
               if (bigBoss.x != bigBoss.xFirst || bigBoss.y != bigBoss.yFirst) {
                  bigBoss.x = bigBoss.x + (bigBoss.xFirst - bigBoss.x) / 4;
                  bigBoss.y = bigBoss.y + (bigBoss.yFirst - bigBoss.y) / 4;
               }

               return;
            case 3:
               this.updateMobAttack();
               return;
            case 4:
               super.timeStatus = 0;
               bigBoss = this;
               if (this.flyUp) {
                  bigBoss.dy++;
                  bigBoss.y = bigBoss.y - bigBoss.dy;
                  bigBoss.checkFrameTick(bigBoss.fly);
                  if (bigBoss.y <= -500) {
                     bigBoss.flyUp = false;
                     bigBoss.flyDown = true;
                     bigBoss.dy = 0;
                  }
               }

               if (bigBoss.flyDown) {
                  bigBoss.x = bigBoss.xTo;
                  bigBoss.dy += 2;
                  bigBoss.y = bigBoss.y + bigBoss.dy;
                  bigBoss.checkFrameTick(bigBoss.hitground);
                  if (bigBoss.y > bigBoss.yFirst) {
                     bigBoss.y = bigBoss.yFirst;
                     bigBoss.flyDown = false;
                     bigBoss.dy = 0;
                     bigBoss.status = 2;
                     GameScr.shock_scr = 10;
                     bigBoss.shock = true;
                  }
               }

               return;
            case 5:
               super.timeStatus = 0;
               return;
            case 6:
               super.timeStatus = 0;
               super.p1++;
               super.y = super.y + super.p1;
               if (super.y >= super.yFirst) {
                  super.y = super.yFirst;
                  super.p1 = 0;
                  super.status = 5;
                  return;
               }
               break;
            case 7:
               return;
         }
      }
   }

   public final void setInjure() {
   }

   public final void setAttack(Char cFocus) {
      super.cFocus = cFocus;
      super.p1 = 0;
      super.p2 = 0;
      super.status = 3;
      this.tick = 0;
      super.dir = cFocus.cx > super.x ? 1 : -1;
      int cx = cFocus.cx;
      int cy = cFocus.cy;
      if (Res.abs(cx - super.x) < super.w << 1
         && Res.abs(cy - super.y) < super.h << 1) {
         if (super.x < cx) {
            super.x = cx - super.w;
         } else {
            super.x = cx + super.w;
         }

         super.p3 = 0;
      } else {
         super.p3 = 1;
      }
   }

   final boolean isSpecial() {
      return super.templateId >= 58 && super.templateId <= 65 || super.templateId == 67 || super.templateId == 68;
   }

   public final void setFly() {
      super.status = 4;
      this.flyUp = true;
   }

   public final void setAttack(Char[] cAttack, long[] dame, byte type) {
      this.charAttack = cAttack;
      this.dameHP = dame;
      this.type = type;
      this.tick = 0;
      if (type < 3) {
         super.status = 3;
      }

      if (type == 3) {
         this.flyUp = true;
         super.status = 4;
      }

      if (type == 4) {
         for (int charAttackIndex = 0; charAttackIndex < this.charAttack.length; charAttackIndex++) {
            this.charAttack[charAttackIndex].doInjure(this.dameHP[charAttackIndex], 0L, false, false);
         }
      }

      if (type == 7) {
         super.status = 3;
      }
   }

   public final void updateMobAttack() {
      if (this.type == 7) {
         if (this.tick > 8) {
            this.tick = 8;
         }

         this.checkFrameTick(this.attack1);
         if (GameCanvas.gameTick % 4 == 0) {
            ServerEffect.addServerEffect(70, super.x + (super.dir == 1 ? 15 : -15), super.y - 40, 1);
         }
      }

      if (this.type == 0) {
         if (this.tick == this.attack1.length - 1) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.attack1);
         if (this.tick == 8) {
            for (int charAttackIndex = 0; charAttackIndex < this.charAttack.length; charAttackIndex++) {
               MonsterDart.addMonsterDart(
                  super.x + (super.dir == 1 ? 45 : -45),
                  super.y - 30,
                  true,
                  this.dameHP[charAttackIndex],
                  0L,
                  this.charAttack[charAttackIndex],
                  24
               );
            }
         }
      }

      if (this.type == 1) {
         if (this.tick == (this.haftBody ? this.attack2_1.length - 1 : this.attack2.length - 1)) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.haftBody ? this.attack2_1 : this.attack2);
         super.x = super.x + (this.charAttack[0].cx - super.x) / 4;
         super.y = super.y + (this.charAttack[0].cy - super.y) / 4;
         if (this.tick == 18) {
            for (int charAttackIndex2 = 0; charAttackIndex2 < this.charAttack.length; charAttackIndex2++) {
               this.charAttack[charAttackIndex2].doInjure(this.dameHP[charAttackIndex2], 0L, false, false);
               ServerEffect.addServerEffect(102, this.charAttack[charAttackIndex2].cx, this.charAttack[charAttackIndex2].cy, 1);
            }
         }
      }

      if (this.type == 2) {
         if (this.tick == (this.haftBody ? this.attack3_1.length - 1 : this.attack3.length - 1)) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.haftBody ? this.attack3_1 : this.attack3);
         if (this.tick == 13) {
            GameScr.shock_scr = 10;
            this.shock = true;

            for (int charAttackIndex3 = 0; charAttackIndex3 < this.charAttack.length; charAttackIndex3++) {
               this.charAttack[charAttackIndex3].doInjure(this.dameHP[charAttackIndex3], 0L, false, false);
            }
         }
      }
   }

   public final void updateMobWalk() {
   }

   public final boolean isPaint() {
      if (super.x < GameScr.cmx) {
         return false;
      } else if (super.x > GameScr.cmx + GameScr.gW) {
         return false;
      } else if (super.y < GameScr.cmy) {
         return false;
      } else {
         return super.y > GameScr.cmy + GameScr.gH + 30 ? false : super.status != 0;
      }
   }

   public final boolean isUpdate() {
      return super.status != 0;
   }

   public final boolean checkIsBoss() {
      return super.isBoss || super.levelBoss > 0;
   }

   public final void paint(mGraphics g) {
      if (data != null) {
         if (!super.isHide) {
            if (super.isMafuba) {
               if (!super.changBody) {
                  data.paintFrame(
                     g, this.frame, super.xMFB, super.yMFB, super.dir == 1 ? 0 : 1, 2
                  );
               } else {
                  SmallImage.drawSmallImage(
                     g, super.smallBody, super.xMFB, super.yMFB, super.dir == 1 ? 0 : 2, 33
                  );
               }
            } else {
               if (this.isShadown && super.status != 0) {
                  mGraphics g2 = g;
                  BigBoss bigBoss = this;
                  g2.drawImage(shadowBig, ((int) (bigBoss.xSd)), ((int) (bigBoss.yFirst)), 3);
                  g2.setClip(
                     GameScr.cmx,
                     GameScr.cmy - GameCanvas.transY,
                     GameScr.gW,
                     GameScr.gH + 2 * GameCanvas.transY
                  );
               }

               g.translate(0, GameCanvas.transY);
               if (!super.changBody) {
                  data.paintFrame(
                     g, this.frame, super.x, super.y, super.dir == 1 ? 0 : 1, 2
                  );
               } else {
                  SmallImage.drawSmallImage(
                     g, super.smallBody, super.x, super.y - 9, super.dir == 1 ? 0 : 2, 33
                  );
               }

               g.translate(0, -GameCanvas.transY);
               int imageWidth = mGraphics.getImageWidth(super.imgHPtem);
               int height = mGraphics.getImageHeight(super.imgHPtem);
               int width = imageWidth;
               int xValue = super.x - imageWidth;
               int yValue = super.y - super.h - 5;
               int width2;
               if ((width2 = (imageWidth << 1) * super.len / 100) > imageWidth) {
                  if ((width2 = width2 - imageWidth) <= 0) {
                     width2 = 0;
                  }
               } else {
                  width = width2;
                  width2 = 0;
               }

               g.drawImage(GameScr.imgHP_tm_xam, ((int) (xValue)), ((int) (yValue)), 20);
               g.drawImage(GameScr.imgHP_tm_xam, ((int) (xValue + imageWidth)), ((int) (yValue)), 20);
               g.drawRegion(super.imgHPtem, 0, 0, width, height, 0, xValue, yValue, 20);
               g.drawRegion(super.imgHPtem, 0, 0, width2, height, 0, xValue + imageWidth, yValue, 20);
               if (this.shock) {
                  this.tShock++;
                  EffecMn.addEff(
                     new Effect(
                        this.type == 2 ? 19 : 22, super.x + this.tShock * 50, super.y + 25, 2, 1, -1
                     )
                  );
                  EffecMn.addEff(
                     new Effect(
                        this.type == 2 ? 19 : 22, super.x - this.tShock * 50, super.y + 25, 2, 1, -1
                     )
                  );
                  if (this.tShock == 50) {
                     this.tShock = 0;
                     this.shock = false;
                  }
               }
            }
         }
      }
   }

   public final void startDie() {
      super.hp = 0L;
      super.hp = 0L;
      super.status = 1;
      super.p1 = -3;
      super.p2 = -super.dir;
      super.p3 = 0;
   }

   public final void attackOtherMob(Mob mobToAttack) {
      super.cFocus = null;
      super.p1 = 0;
      super.p2 = 0;
      super.status = 3;
      this.tick = 0;
      super.dir = mobToAttack.x > super.x ? 1 : -1;
      int xValue = mobToAttack.x;
      int yValue = mobToAttack.y;
      if (Res.abs(xValue - super.x) < super.w << 1
         && Res.abs(yValue - super.y) < super.h << 1) {
         if (super.x < xValue) {
            super.x = xValue - super.w;
         } else {
            super.x = xValue + super.w;
         }

         super.p3 = 0;
      } else {
         super.p3 = 1;
      }
   }

   public final int getX() {
      return super.x;
   }

   public final int getY() {
      return this.haftBody ? super.y - 20 : super.y - 60;
   }

   public final int getH() {
      return 40;
   }

   public final int getW() {
      return 60;
   }

   public final void stopMoving() {
      if (super.status == 5) {
         super.status = 2;
         super.p1 = super.p2 = super.p3 = 0;
      }
   }

   public final boolean isInvisible() {
      return super.status == 0 || super.status == 1;
   }

   public final void removeHoldEff() {
      if (super.holdEffID != 0) {
         super.holdEffID = 0;
      }
   }

   public final void removeBlindEff() {
   }

   public final void removeSleepEff() {
   }
}
