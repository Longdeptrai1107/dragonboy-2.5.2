package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class BachTuoc extends Mob implements IMapObject {
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
   private Char[] charAttack;
   private long[] dameHP;
   private byte type;
   private int[] stand = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
   private int[] movee = new int[]{0, 0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4};
   private int[] attack1 = new int[]{0, 0, 0, 4, 4, 4, 5, 5, 5, 6, 6, 6};
   private int[] attack2 = new int[]{0, 0, 0, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11};

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

   public BachTuoc(int mobIdValue, short shortValue, short yValue, int value, long hpValue, long maxHpValue) {
      int[] intValues = new int[]{1, 1, 7, 7};
      byte[] dataValue = new byte[]{-1, 1};
      super.mobId = mobIdValue;
      super.x = shortValue + 20;
      super.y = yValue;
      this.xTo = super.x;
      this.yTo = super.y;
      super.maxHp = maxHpValue;
      super.hp = hpValue;
      super.templateId = 71;
      super.color = 100;
      this.updateHp_bar();
      BachTuoc bachTuoc = this;
      data = null;
      data = new EffectData();
      String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + 108 + "/data";

      try {
         data.readData2(patch);
         data.img = mSystem.loadZoomedResourceImage("/effectdata/108/img.png");
      } catch (Exception ignoredException) {
         Service.gI().requestModTemplate(bachTuoc.templateId);
      }

      bachTuoc.w = data.width;
      bachTuoc.h = data.height;
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
         BachTuoc bachTuoc = this;
         byte size = TileMap.size;
         bachTuoc.xSd = bachTuoc.x;
         bachTuoc.wCount = 0;
         if (bachTuoc.ySd > 0 && !TileMap.tileTypeAt(bachTuoc.xSd, bachTuoc.ySd, 2)) {
            if (TileMap.tileTypeAt(bachTuoc.xSd / size, bachTuoc.ySd / size) == 0) {
               bachTuoc.isOutMap = true;
            } else if (TileMap.tileTypeAt(bachTuoc.xSd / size, bachTuoc.ySd / size) != 0
               && !TileMap.tileTypeAt(bachTuoc.xSd, bachTuoc.ySd, 2)) {
               bachTuoc.xSd = bachTuoc.x;
               bachTuoc.ySd = bachTuoc.y;
               bachTuoc.isOutMap = false;
            }

            while (bachTuoc.isOutMap && bachTuoc.wCount < 10) {
               bachTuoc.wCount++;
               bachTuoc.ySd += 24;
               if (TileMap.tileTypeAt(bachTuoc.xSd, bachTuoc.ySd, 2)) {
                  if (bachTuoc.ySd % 24 != 0) {
                     bachTuoc.ySd = bachTuoc.ySd - bachTuoc.ySd % 24;
                  }
                  break;
               }
            }
         }

         switch (super.status) {
            case 0:
            case 1:
               bachTuoc = this;
               this.checkFrameTick(bachTuoc.stand);
               if (GameCanvas.gameTick % 5 == 0) {
                  ServerEffect.addServerEffect(
                     167,
                     Res.random(bachTuoc.x - 40 / 2, bachTuoc.x + 40 / 2),
                     Res.random(bachTuoc.y - 40 + 40 / 2, bachTuoc.y - 40 + 40),
                     1
                  );
               }

               if (bachTuoc.x != bachTuoc.xTo || bachTuoc.y != bachTuoc.yTo) {
                  bachTuoc.x = bachTuoc.x + (bachTuoc.xTo - bachTuoc.x) / 4;
                  bachTuoc.y = bachTuoc.y + (bachTuoc.yTo - bachTuoc.y) / 4;
               }
               break;
            case 2:
               bachTuoc = this;
               this.checkFrameTick(bachTuoc.stand);
               if (bachTuoc.x != bachTuoc.xTo || bachTuoc.y != bachTuoc.yTo) {
                  bachTuoc.x = bachTuoc.x + (bachTuoc.xTo - bachTuoc.x) / 4;
                  bachTuoc.y = bachTuoc.y + (bachTuoc.yTo - bachTuoc.y) / 4;
               }

               return;
            case 3:
               this.updateMobAttack();
               return;
            case 4:
            default:
               break;
            case 5:
               super.timeStatus = 0;
               this.updateMobWalk();
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
      this.charAttack = cAttack;
      this.dameHP = dame;
      this.type = type;
      super.status = 3;
   }

   public final void updateMobAttack() {
      if (this.type == 3) {
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

      if (this.type == 4) {
         if (this.tick == this.attack2.length - 1) {
            super.status = 2;
         }

         super.dir = super.x < this.charAttack[0].cx ? 1 : -1;
         this.checkFrameTick(this.attack2);
         if (this.tick == 8) {
            for (int charAttackIndex2 = 0; charAttackIndex2 < this.charAttack.length; charAttackIndex2++) {
               this.charAttack[charAttackIndex2].doInjure(this.dameHP[charAttackIndex2], 0L, false, false);
               ServerEffect.addServerEffect(102, this.charAttack[charAttackIndex2].cx, this.charAttack[charAttackIndex2].cy, 1);
            }
         }
      }
   }

   public final void updateMobWalk() {
      this.checkFrameTick(this.movee);
      super.x = super.x + (super.x < this.xTo ? 2 : -2);
      super.y = this.yTo;
      super.dir = super.x < this.xTo ? 1 : -1;
      if (Res.abs(super.x - this.xTo) <= 1) {
         super.x = this.xTo;
         super.status = 2;
      }
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
                  BachTuoc bachTuoc = this;
                  g2.drawImage(shadowBig, ((int) (bachTuoc.xSd)), ((int) (bachTuoc.yFirst)), 3);
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
      return super.y - 40;
   }

   public final int getH() {
      return 40;
   }

   public final int getW() {
      return 40;
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

   public final void move(short xMoveTo) {
      this.xTo = xMoveTo;
      super.status = 5;
   }
}
