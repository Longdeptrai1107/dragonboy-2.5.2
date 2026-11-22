package main;

import nro.Char;
import nro.GameScr;
import nro.Res;
import nro.mGraphics;
import nro.mSystem;

public final class GamePad {
   private int xC;
   private int yC;
   private int xM;
   private int yM;
   private int xMLast;
   private int yMLast;
   private int R;
   private int d;
   private int xTemp;
   private int yTemp;
   private int deltaX;
   private int deltaY;
   private int delta;
   private int angle;
   private int xZone;
   private int yZone;
   public int wZone;
   private int hZone;
   private boolean isGamePad = false;
   public boolean isSmallGamePad;
   public boolean isMediumGamePad;
   public boolean isLargeGamePad;

   public GamePad() {
      this.R = 28;
      if (GameCanvas.w < 300) {
         this.isSmallGamePad = true;
         this.isMediumGamePad = false;
         this.isLargeGamePad = false;
      }

      if (GameCanvas.w >= 300 && GameCanvas.w <= 380) {
         this.isSmallGamePad = false;
         this.isMediumGamePad = true;
         this.isLargeGamePad = false;
      }

      if (GameCanvas.w > 380) {
         this.isSmallGamePad = false;
         this.isMediumGamePad = false;
         this.isLargeGamePad = true;
      }

      if (!this.isLargeGamePad) {
         this.xZone = 0;
         this.wZone = GameCanvas.hw;
         this.yZone = GameCanvas.hh >> 1;
         this.hZone = GameCanvas.h - 80;
      } else {
         this.xZone = 0;
         this.wZone = GameCanvas.hw / 4 * 3 - 20;
         this.yZone = GameCanvas.hh >> 1;
         this.hZone = GameCanvas.h;
         if (mSystem.clientType == 2) {
            this.xZone = 0;
            this.yZone = (GameCanvas.h >> 1) + 40;
            this.wZone = GameCanvas.hw / 4 * 3 - 40;
            this.hZone = GameCanvas.h;
         }
      }
   }

   public final void update() {
      try {
         if (GameScr.isAnalog == 0) {
            return;
         }

         if (!GameCanvas.isPointerDown || GameCanvas.isPointerJustRelease) {
            this.xM = this.xC = 45;
            if (!this.isLargeGamePad) {
               this.yM = this.yC = GameCanvas.h - 90;
            } else {
               this.yM = this.yC = GameCanvas.h - 45;
            }

            this.isGamePad = false;
            GameCanvas.clearKeyHold();
            return;
         }

         this.xTemp = GameCanvas.pxFirst;
         this.yTemp = GameCanvas.pyFirst;
         if (this.xTemp >= 0
            && this.xTemp <= this.wZone
            && this.yTemp >= this.yZone
            && this.yTemp <= this.hZone) {
            if (!this.isGamePad) {
               this.xC = this.xM = this.xTemp;
               this.yC = this.yM = this.yTemp;
            }

            this.isGamePad = true;
            this.deltaX = GameCanvas.px - this.xC;
            this.deltaY = GameCanvas.py - this.yC;
            this.delta = Res.square(this.deltaX, 2) + Res.square(this.deltaY, 2);
            this.d = Res.sqrt(this.delta);
            if (Res.abs(this.deltaX) > 4 || Res.abs(this.deltaY) > 4) {
               this.angle = Res.angle(this.deltaX, this.deltaY);
               if (!GameCanvas.isPointerHoldIn(
                  this.xC - this.R,
                  this.yC - this.R,
                  2 * this.R,
                  2 * this.R
               )) {
                  if (this.d != 0) {
                     this.yM = this.deltaY * this.R / this.d;
                     this.xM = this.deltaX * this.R / this.d;
                     this.xM = this.xM + this.xC;
                     this.yM = this.yM + this.yC;
                     if (!Res.inRect(
                        this.xC - this.R,
                        this.yC - this.R,
                        2 * this.R,
                        2 * this.R,
                        this.xM,
                        this.yM
                     )) {
                        this.xM = this.xMLast;
                        this.yM = this.yMLast;
                     } else {
                        this.xMLast = this.xM;
                        this.yMLast = this.yM;
                     }
                  } else {
                     this.xM = this.xMLast;
                     this.yM = this.yMLast;
                  }
               } else {
                  this.xM = GameCanvas.px;
                  this.yM = GameCanvas.py;
               }

               GameCanvas.clearKeyHold();
               byte byteValue = 2;
               boolean condition;
               if (GameScr.isAnalog == 0) {
                  condition = false;
               } else if (Char.myCharz().statusMe == 3) {
                  condition = true;
               } else {
                  int posIndex = 2;

                  while (true) {
                     if (posIndex <= 0) {
                        condition = true;
                        break;
                     }

                     int deltaXValue = GameCanvas.arrPos[posIndex].x - GameCanvas.arrPos[posIndex - 1].x;
                     int deltaYValue = GameCanvas.arrPos[posIndex].y - GameCanvas.arrPos[posIndex - 1].y;
                     if (Res.abs(deltaXValue) > 2 && Res.abs(deltaYValue) > 2) {
                        condition = false;
                        break;
                     }

                     posIndex--;
                  }
               }

               if (!condition) {
                  GameCanvas.clearKeyHold();
                  return;
               }

               if (this.angle <= 360 && this.angle >= 340 || this.angle >= 0 && this.angle <= 20) {
                  GameCanvas.keyHold[6] = true;
                  GameCanvas.keyPressed[6] = true;
                  return;
               }

               if (this.angle > 40 && this.angle < 70) {
                  GameCanvas.keyHold[6] = true;
                  GameCanvas.keyPressed[6] = true;
                  return;
               }

               if (this.angle >= 70 && this.angle <= 110) {
                  GameCanvas.keyHold[8] = true;
                  GameCanvas.keyPressed[8] = true;
                  return;
               }

               if (this.angle > 110 && this.angle < 120) {
                  GameCanvas.keyHold[4] = true;
                  GameCanvas.keyPressed[4] = true;
                  return;
               }

               if (this.angle >= 120 && this.angle <= 200) {
                  GameCanvas.keyHold[4] = true;
                  GameCanvas.keyPressed[4] = true;
                  return;
               }

               if (this.angle > 200 && this.angle < 250) {
                  GameCanvas.keyHold[2] = true;
                  GameCanvas.keyPressed[2] = true;
                  GameCanvas.keyHold[4] = true;
                  GameCanvas.keyPressed[4] = true;
                  return;
               }

               if (this.angle >= 250 && this.angle <= 290) {
                  GameCanvas.keyHold[2] = true;
                  GameCanvas.keyPressed[2] = true;
                  return;
               }

               if (this.angle > 290 && this.angle < 340) {
                  GameCanvas.keyHold[2] = true;
                  GameCanvas.keyPressed[2] = true;
                  GameCanvas.keyHold[6] = true;
                  GameCanvas.keyPressed[6] = true;
                  return;
               }
            }
         }
      } catch (Exception ignoredException) {
      }
   }

   public final void paint(mGraphics g) {
      if (GameScr.isAnalog != 0) {
         this.xZone = 0;
         this.yZone = (GameCanvas.h >> 1) + 40;
         this.wZone = GameCanvas.hw / 4 * 3 - 40;
         this.hZone = GameCanvas.h;
         g.drawImage(GameScr.imgAnalog1, ((int) (this.xC)), ((int) (this.yC)), 3);
         g.drawImage(GameScr.imgAnalog2, ((int) (this.xM)), ((int) (this.yM)), 3);
      }
   }

   public final boolean disableCheckDrag() {
      return GameScr.isAnalog == 0 ? false : this.isGamePad;
   }

   public final boolean disableClickMove() {
      try {
         if (GameScr.isAnalog == 0) {
            return false;
         }

         boolean result;
         if ((
               GameCanvas.px < 0
                  || GameCanvas.px > 0 + this.wZone
                  || GameCanvas.py < this.yZone
                  || GameCanvas.py > this.yZone + this.hZone
            )
            && (
               GameCanvas.px < 0
                  || GameCanvas.px > GameCanvas.w
                  || GameCanvas.py < this.yZone
                  || GameCanvas.py > this.yZone + this.hZone
            )
            && GameCanvas.px < GameCanvas.w - 50) {
            result = false;
         } else {
            result = true;
         }

         return result;
      } catch (Exception ignoredException) {
         return false;
      }
   }
}
