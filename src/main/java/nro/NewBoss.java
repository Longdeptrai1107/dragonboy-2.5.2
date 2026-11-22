package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class NewBoss extends Mob implements IMapObject {
   private static Image shadowBig = mSystem.loadImage("/mainImage/shadowBig.png");
   public int xTo;
   public int yTo;
   private int xSd;
   private int ySd;
   private boolean isShadown = true;
   private int tick;
   private int frame;
   private Char[] charAttack;
   private long[] dameHP;
   private byte type;
   private int offset;
   private int xTempRight = -1;
   private int xTempLeft = -1;
   private int yTemp = -1;
   private int[][] frameArr;

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

   public NewBoss(int mobIdValue, short shortValue, short yFirstValue, int templateIdValue, long hpValue, long maxHpValue) {
      byte[] data = new byte[]{-1, 1};
      this.frameArr = new int[][]{
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1}
      };
      super.mobId = mobIdValue;
      super.x = super.xFirst = shortValue + 20;
      super.y = super.yFirst = yFirstValue;
      this.xTo = super.x;
      this.yTo = super.y;
      super.maxHp = maxHpValue;
      super.hp = hpValue;
      super.templateId = templateIdValue;
      super.color = 100;
      this.updateHp_bar();
      if (Mob.arrMobTemplate[super.templateId].data == null) {
         Service.gI().requestModTemplate(super.templateId);
      }

      super.status = 2;
      this.frameArr = null;
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
      if (this.frameArr == null && Mob.arrMobTemplate[super.templateId].data != null) {
         this.GetFrame();
      }

      if (this.frameArr != null) {
         if (this.isUpdate()) {
            NewBoss newBoss = this;
            int index = 0;
            newBoss.xSd = newBoss.x;
            if (TileMap.tileTypeAt(newBoss.x, newBoss.y, 2)) {
               newBoss.ySd = newBoss.y;
            } else {
               newBoss.ySd = newBoss.y;

               while (index < 30) {
                  index++;
                  newBoss.ySd += 24;
                  if (TileMap.tileTypeAt(newBoss.xSd, newBoss.ySd, 2)) {
                     if (newBoss.ySd % 24 != 0) {
                        newBoss.ySd = newBoss.ySd - newBoss.ySd % 24;
                     }
                     break;
                  }
               }
            }

            switch (super.status) {
               case 0:
               case 1:
                  newBoss = this;
                  this.tick++;
                  if (newBoss.tick > newBoss.frameArr[13].length - 1) {
                     newBoss.tick = newBoss.frameArr[13].length - 1;
                  }

                  newBoss.frame = newBoss.frameArr[13][newBoss.tick];
                  if (newBoss.x != newBoss.xTo || newBoss.y != newBoss.yTo) {
                     newBoss.x = newBoss.x + (newBoss.xTo - newBoss.x) / 4;
                     newBoss.y = newBoss.y + (newBoss.yTo - newBoss.y) / 4;
                  }
                  break;
               case 2:
                  newBoss = this;
                  this.checkFrameTick(newBoss.frameArr[0]);
                  if (newBoss.x != newBoss.xTo || newBoss.y != newBoss.yTo) {
                     newBoss.x = newBoss.x + (newBoss.xTo - newBoss.x) / 4;
                     newBoss.y = newBoss.y + (newBoss.yTo - newBoss.y) / 4;
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
                  super.update();
                  return;
            }
         }
      }
   }

   public final void setAttack(Char cFocus) {
      super.cFocus = cFocus;
      super.p1 = 0;
      super.p2 = 0;
      super.status = 3;
      this.tick = 0;
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

   public final void setFly() {
      super.status = 4;
   }

   public final void setAttack(Char[] cAttack, long[] dame, byte type, byte dir) {
      this.charAttack = cAttack;
      this.dameHP = dame;
      this.type = type;
      super.dir = dir;
      super.status = 3;
      if (super.x != this.xTo || super.y != this.yTo) {
         super.x = super.x + (this.xTo - super.x) / 4;
         super.y = super.y + (this.yTo - super.y) / 4;
      }
   }

   public final void updateMobAttack() {
      if (this.tick == this.frameArr[this.type + 1].length - 1) {
         super.status = 2;
      }

      this.checkFrameTick(this.frameArr[this.type + 1]);
      if (this.tick == this.frameArr[15][this.type - 1]) {
         for (int charAttackIndex = 0; charAttackIndex < this.charAttack.length; charAttackIndex++) {
            this.charAttack[charAttackIndex].doInjure(this.dameHP[charAttackIndex], 0L, false, false);
            ServerEffect.addServerEffect(
               this.frameArr[16][this.type - 1],
               this.charAttack[charAttackIndex].cx,
               this.charAttack[charAttackIndex].cy,
               1
            );
         }
      }
   }

   public final void updateMobWalk() {
      this.checkFrameTick(this.frameArr[1]);
      byte speed = Mob.arrMobTemplate[super.templateId].speed;
      int speed2 = Mob.arrMobTemplate[super.templateId].speed;
      if (Res.abs(super.x - this.xTo) < speed) {
         speed2 = Res.abs(super.x - this.xTo);
      }

      super.x = super.x + (super.x < this.xTo ? speed2 : -speed2);
      super.y = this.yTo;
      if (super.x < this.xTo) {
         super.dir = 1;
      } else if (super.x > this.xTo) {
         super.dir = -1;
      }

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
      if (Mob.arrMobTemplate[super.templateId].data != null) {
         if (!super.isHide) {
            if (super.isMafuba) {
               if (!super.changBody) {
                  Mob.arrMobTemplate[super.templateId]
                     .data
                     .paintFrame(g, this.frame, super.xMFB, super.yMFB, super.dir == 1 ? 0 : 1, 2);
               } else {
                  SmallImage.drawSmallImage(
                     g, super.smallBody, super.xMFB, super.yMFB, super.dir == 1 ? 0 : 2, 33
                  );
               }
            } else {
               if (this.isShadown) {
                  mGraphics g2 = g;
                  NewBoss newBoss = this;
                  byte width = TileMap.size;
                  if ((TileMap.mapID < 114 || TileMap.mapID > 120) && TileMap.mapID != 127 && TileMap.mapID != 128) {
                     if (TileMap.tileTypeAt(newBoss.xSd + width / 2, newBoss.ySd + 1, 4)) {
                        g2.setClip(newBoss.xSd / width * width, (newBoss.ySd - 30) / width * width, width, 100);
                     } else if (TileMap.tileTypeAt((newBoss.xSd - width / 2) / width, (newBoss.ySd + 1) / width) == 0) {
                        g2.setClip(newBoss.xSd / width * width, (newBoss.ySd - 30) / width * width, 100, 100);
                     } else if (TileMap.tileTypeAt((newBoss.xSd + width / 2) / width, (newBoss.ySd + 1) / width) == 0) {
                        g2.setClip(newBoss.xSd / width * width, (newBoss.ySd - 30) / width * width, width, 100);
                     } else if (TileMap.tileTypeAt(newBoss.xSd - width / 2, newBoss.ySd + 1, 8)) {
                        g2.setClip(newBoss.xSd / 24 * width, (newBoss.ySd - 30) / width * width, width, 100);
                     }
                  }

                  g2.drawImage(shadowBig, ((int) (newBoss.xSd)), ((int) (newBoss.ySd - 5)), 3);
                  g2.setClip(
                     GameScr.cmx,
                     GameScr.cmy - GameCanvas.transY,
                     GameScr.gW,
                     GameScr.gH + 2 * GameCanvas.transY
                  );
               }

               g.translate(0, GameCanvas.transY);
               if (!super.changBody) {
                  if (this.yTemp == -1) {
                     this.yTemp = super.y;
                  }

                  if (TileMap.tileTypeAt(super.x + 33, super.y, 4)) {
                     this.xTempLeft = TileMap.tileXofPixel(super.x + 33) - 33;
                     this.xTempRight = TileMap.tileXofPixel(super.x + 33);
                     if (super.x > this.xTempLeft && super.x < this.xTempRight && this.xTempRight != -1) {
                        super.x = this.xTempLeft;
                     }
                  }

                  if (super.y < this.yTemp && this.yTemp != -1) {
                     this.yTemp = super.y;
                     super.x += 33;
                  }

                  if (super.y > this.yTemp) {
                     this.yTemp = super.y;
                     super.x -= 33;
                  }

                  Mob.arrMobTemplate[super.templateId]
                     .data
                     .paintFrame(g, this.frame, super.x, super.y, super.dir == 1 ? 0 : 1, 2);
               } else {
                  SmallImage.drawSmallImage(
                     g, super.smallBody, super.x, super.y - 9, super.dir == 1 ? 0 : 2, 33
                  );
               }

               g.translate(0, -GameCanvas.transY);
               if (super.hp > 0L) {
                  int imageWidth = mGraphics.getImageWidth(super.imgHPtem);
                  int height = mGraphics.getImageHeight(super.imgHPtem);
                  int width2 = imageWidth;
                  int xValue = super.x - imageWidth;
                  int yValue = super.y - super.h - 5;
                  int width3;
                  int width4 = width3 = (imageWidth << 1) * super.len / 100;
                  if (super.w_hp_bar >= super.len) {
                     width4 = imageWidth
                        * (
                           super.w_hp_bar = super.w_hp_bar
                              - (GameCanvas.gameTick % 6 > 3 ? this.offset++ : this.offset)
                        )
                        / 100;
                     if (super.w_hp_bar <= 0) {
                        super.w_hp_bar = 0;
                     }

                     if (super.w_hp_bar < super.len) {
                        super.w_hp_bar = super.len;
                     }

                     if (this.offset >= 3) {
                        this.offset = 3;
                     }
                  }

                  if (width3 > imageWidth) {
                     if ((width3 = width3 - imageWidth) <= 0) {
                        width3 = 0;
                     }
                  } else {
                     width2 = width3;
                     width3 = 0;
                  }

                  g.drawImage(GameScr.imgHP_tm_xam, ((int) (xValue)), ((int) (yValue)), 20);
                  g.drawImage(GameScr.imgHP_tm_xam, ((int) (xValue + imageWidth)), ((int) (yValue)), 20);
                  g.setColor(16777215);
                  g.fillRect(xValue, yValue, width4, 2);
                  g.drawRegion(super.imgHPtem, 0, 0, width2, height, 0, xValue, yValue, 20);
                  g.drawRegion(super.imgHPtem, 0, 0, width3, height, 0, xValue + imageWidth, yValue, 20);
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
      return super.y;
   }

   public final int getH() {
      return super.h;
   }

   public final int getW() {
      return super.w;
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

   public final void move(short xMoveTo, short yMoveTo) {
      if (yMoveTo != -1) {
         if (Res.distance(super.x, super.y, this.xTo, this.yTo) > 100) {
            super.x = xMoveTo;
            super.y = yMoveTo;
            super.status = 2;
         } else {
            this.xTo = xMoveTo;
            this.yTo = yMoveTo;
            super.status = 5;
         }
      } else {
         this.xTo = xMoveTo;
         super.status = 5;
      }
   }

   public final void GetFrame() {
      try {
         this.frameArr = (int[][])Controller.frameHT_NEWBOSS.get(String.valueOf(super.templateId));
         super.w = Mob.arrMobTemplate[super.templateId].data.width;
         super.h = Mob.arrMobTemplate[super.templateId].data.height;
      } catch (Exception ignoredException) {
      }
   }

   public final void setDie() {
      super.status = 0;
   }
}
