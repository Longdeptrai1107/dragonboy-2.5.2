package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class BigBoss2 extends Mob implements IMapObject {
   private static Image shadowBig = mSystem.loadImage("/mainImage/shadowBig.png");
   private static EffectData data;
   public int xTo;
   public int yTo;
   private int xSd;
   private int ySd;
   private boolean isOutMap;
   private int wCount;
   private boolean isShadown = true;
   private int tick;
   private int frame;
   private boolean flyDown;
   private int dy;
   private int tShock;
   private Char[] charAttack;
   private long[] dameHP;
   private byte type;
   private int[] stand = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
   private int[] attack1;
   private int[] attack2;
   private int[] fly;
   private int[] hitground;
   private boolean shock;

   static {
      mSystem.loadImage("/mainImage/myTexture2dmobHP.png");
   }

   public final void setBody(short id) {
      super.changBody = true;
      super.smallBody = id;
   }

   public final void clearBody() {
      super.changBody = false;
   }

   public BigBoss2(int mobIdValue, short shortValue, short yValue, int value, long hpValue, long maxHpValue) {
      int[] intValues = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2};
      intValues = new int[]{1, 1, 2, 2, 3, 3, 2};
      this.attack1 = new int[]{0, 0, 0, 7, 7, 7, 8, 8, 8, 9, 9, 9};
      this.attack2 = new int[]{0, 0, 0, 10, 10, 10, 11, 11, 11, 12, 12, 12};
      intValues = new int[]{0, 0, 1, 1, 4, 4, 6, 6, 8, 8, 25, 25, 26, 26, 28, 28, 30, 30, 32, 32, 2, 2, 1, 1};
      this.fly = new int[]{4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6, 3, 3, 3, 2, 2, 2, 1, 1, 1};
      this.hitground = new int[]{6, 6, 6, 3, 3, 3, 2, 2, 2, 1, 1, 1};
      byte[] dataValue = new byte[]{-1, 1};
      super.mobId = mobIdValue;
      this.xTo = super.x = shortValue + 20;
      this.yTo = super.y = yValue;
      super.yFirst = yValue;
      super.hp = hpValue;
      super.maxHp = maxHpValue;
      super.templateId = 72;
      super.color = 100;
      this.updateHp_bar();
      BigBoss2 bigBoss2 = this;
      data = null;
      data = new EffectData();
      String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + 109 + "/data";

      try {
         data.readData2(patch);
         data.img = mSystem.loadZoomedResourceImage("/effectdata/109/img.png");
      } catch (Exception ignoredException) {
         Service.gI().requestModTemplate(bigBoss2.templateId);
      }

      bigBoss2.w = data.width;
      bigBoss2.h = data.height;
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
         BigBoss2 bigBoss2 = this;
         byte size = TileMap.size;
         bigBoss2.xSd = bigBoss2.x;
         bigBoss2.wCount = 0;
         if (bigBoss2.ySd > 0 && !TileMap.tileTypeAt(bigBoss2.xSd, bigBoss2.ySd, 2)) {
            if (TileMap.tileTypeAt(bigBoss2.xSd / size, bigBoss2.ySd / size) == 0) {
               bigBoss2.isOutMap = true;
            } else if (TileMap.tileTypeAt(bigBoss2.xSd / size, bigBoss2.ySd / size) != 0
               && !TileMap.tileTypeAt(bigBoss2.xSd, bigBoss2.ySd, 2)) {
               bigBoss2.xSd = bigBoss2.x;
               bigBoss2.ySd = bigBoss2.y;
               bigBoss2.isOutMap = false;
            }

            while (bigBoss2.isOutMap && bigBoss2.wCount < 10) {
               bigBoss2.wCount++;
               bigBoss2.ySd += 24;
               if (TileMap.tileTypeAt(bigBoss2.xSd, bigBoss2.ySd, 2)) {
                  if (bigBoss2.ySd % 24 != 0) {
                     bigBoss2.ySd = bigBoss2.ySd - bigBoss2.ySd % 24;
                  }
                  break;
               }
            }
         }

         switch (super.status) {
            case 0:
            case 1:
               bigBoss2 = this;
               this.checkFrameTick(bigBoss2.stand);
               if (GameCanvas.gameTick % 5 == 0) {
                  ServerEffect.addServerEffect(
                     167,
                     Res.random(bigBoss2.x - 50 / 2, bigBoss2.x + 50 / 2),
                     Res.random(bigBoss2.y - 50 + 40 / 2, bigBoss2.y - 50 + 40),
                     1
                  );
               }

               if (bigBoss2.x != bigBoss2.xTo || bigBoss2.y != bigBoss2.yTo) {
                  bigBoss2.x = bigBoss2.x + (bigBoss2.xTo - bigBoss2.x) / 4;
                  bigBoss2.y = bigBoss2.y + (bigBoss2.yTo - bigBoss2.y) / 4;
               }
               break;
            case 2:
               bigBoss2 = this;
               this.checkFrameTick(bigBoss2.stand);
               if (bigBoss2.x != bigBoss2.xTo || bigBoss2.y != bigBoss2.yTo) {
                  bigBoss2.x = bigBoss2.x + (bigBoss2.xTo - bigBoss2.x) / 4;
                  bigBoss2.y = bigBoss2.y + (bigBoss2.yTo - bigBoss2.y) / 4;
               }

               return;
            case 3:
               this.updateMobAttack();
               return;
            case 4:
               super.timeStatus = 0;
               bigBoss2 = this;
               if (this.flyDown) {
                  bigBoss2.x = bigBoss2.xTo;
                  bigBoss2.dy += 2;
                  bigBoss2.y = bigBoss2.y + bigBoss2.dy;
                  bigBoss2.checkFrameTick(bigBoss2.hitground);
                  if (bigBoss2.y > bigBoss2.yFirst) {
                     bigBoss2.y = bigBoss2.yFirst;
                     bigBoss2.flyDown = false;
                     bigBoss2.dy = 0;
                     bigBoss2.status = 2;
                     GameScr.shock_scr = 10;
                     bigBoss2.shock = true;
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

   public final void setAttack(Char[] cAttack, long[] dame, byte type) {
      super.status = 3;
      this.charAttack = cAttack;
      this.dameHP = dame;
      this.type = type;
      this.tick = 0;
   }

   public final void updateMobAttack() {
      if (this.type == 0) {
         if (this.tick == this.attack1.length - 1) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.attack1);
         super.x = super.x + (this.charAttack[0].cx - super.x) / 4;
         super.y = super.y + (this.charAttack[0].cy - super.y) / 4;
         this.xTo = super.x;
         if (this.tick == 8) {
            for (int charAttackIndex = 0; charAttackIndex < this.charAttack.length; charAttackIndex++) {
               this.charAttack[charAttackIndex].doInjure(this.dameHP[charAttackIndex], 0L, false, false);
               ServerEffect.addServerEffect(102, this.charAttack[charAttackIndex].cx, this.charAttack[charAttackIndex].cy, 1);
            }
         }
      }

      if (this.type == 1) {
         if (this.tick == this.attack2.length - 1) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.attack2);
         if (this.tick == 8) {
            for (int charAttackIndex2 = 0; charAttackIndex2 < this.charAttack.length; charAttackIndex2++) {
               MonsterDart.addMonsterDart(
                  super.x + (super.dir == 1 ? 45 : -45),
                  super.y - 25,
                  true,
                  this.dameHP[charAttackIndex2],
                  0L,
                  this.charAttack[charAttackIndex2],
                  24
               );
            }
         }
      }

      if (this.type == 2) {
         if (this.tick == this.fly.length - 1) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.fly);
         super.x = super.x + (this.charAttack[0].cx - super.x) / 4;
         this.xTo = super.x;
         this.yTo = super.y;
         if (this.tick == 12) {
            for (int charAttackIndex3 = 0; charAttackIndex3 < this.charAttack.length; charAttackIndex3++) {
               this.charAttack[charAttackIndex3].doInjure(this.dameHP[charAttackIndex3], 0L, false, false);
               ServerEffect.addServerEffect(102, this.charAttack[charAttackIndex3].cx, this.charAttack[charAttackIndex3].cy, 1);
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
                  BigBoss2 bigBoss2 = this;
                  g2.drawImage(shadowBig, ((int) (bigBoss2.xSd)), ((int) (bigBoss2.yFirst)), 3);
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
      return super.y - 50;
   }

   public final int getH() {
      return 40;
   }

   public final int getW() {
      return 50;
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
