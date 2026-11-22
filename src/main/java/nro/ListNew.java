package nro;

import main.GameCanvas;

public final class ListNew {
   private int maxW;
   private int maxH;
   private int x;
   private int y;
   private int value;
   public int cmtoX;
   public int cmx;
   public int cmxLim;
   private int pointerDownTime;
   private int pointerDownFirstX;
   private int[] pointerDownLastX = new int[3];
   private boolean pointerIsDowning;
   private boolean isDownWhenRunning;
   private int cmRun;
   private int cmvx;
   private int cmdx;

   public ListNew() {
   }

   public ListNew(int xValue, int yValue, int maxWValue, int maxHValue, int itemH, int maxSize, int limX, boolean isLim0) {
      this.x = xValue;
      this.y = yValue;
      this.maxW = maxWValue;
      this.maxH = maxHValue;
      this.cmxLim = 0;
      if (this.cmxLim < 0) {
         this.cmxLim = 0;
      }
   }

   public final void update_Pos_UP_DOWN() {
      if (this.cmxLim > 0) {
         if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning
               && GameCanvas.isPointer(this.x, this.y, this.maxW, this.maxH)) {
               for (int pointerDownLastXIndex = 0; pointerDownLastXIndex < this.pointerDownLastX.length; pointerDownLastXIndex++) {
                  this.pointerDownLastX[pointerDownLastXIndex] = GameCanvas.py;
               }

               this.pointerDownFirstX = GameCanvas.py;
               this.pointerIsDowning = true;
               this.isDownWhenRunning = this.cmRun != 0;
               this.cmRun = 0;
            } else if (this.pointerIsDowning) {
               this.pointerDownTime++;
               if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                  this.pointerDownFirstX = -1000;
               }

               int cmtoXValue;
               if ((cmtoXValue = GameCanvas.py - this.pointerDownLastX[0]) != 0 && this.value != -1) {
                  this.value = -1;
               }

               for (int pointerDownLastXIndex2 = this.pointerDownLastX.length - 1; pointerDownLastXIndex2 > 0; pointerDownLastXIndex2--) {
                  this.pointerDownLastX[pointerDownLastXIndex2] = this.pointerDownLastX[pointerDownLastXIndex2 - 1];
               }

               this.pointerDownLastX[0] = GameCanvas.py;
               this.cmtoX -= cmtoXValue;
               if (this.cmtoX < 0) {
                  this.cmtoX = 0;
               }

               if (this.cmtoX > this.cmxLim) {
                  this.cmtoX = this.cmxLim;
               }

               if (this.cmx < 0 || this.cmx > this.cmxLim) {
                  cmtoXValue /= 2;
               }

               this.cmx -= cmtoXValue;
            }
         }

         if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int deltaY = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (Res.abs(deltaY) < 20
               && Res.abs(GameCanvas.py - this.pointerDownFirstX) < 20
               && !this.isDownWhenRunning
               && GameCanvas.isPointerSelect) {
               this.cmRun = 0;
               this.cmtoX = this.cmx;
               this.pointerDownFirstX = -1000;
               this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
               this.pointerDownTime = 0;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
               if (this.cmx < 0) {
                  this.cmtoX = 0;
               } else if (this.cmx > this.cmxLim) {
                  this.cmtoX = this.cmxLim;
               } else {
                  int valueValue;
                  byte byteValue;
                  if ((
                        valueValue = GameCanvas.py
                           - this.pointerDownLastX[0]
                           + (this.pointerDownLastX[0] - this.pointerDownLastX[1])
                           + (this.pointerDownLastX[1] - this.pointerDownLastX[2])
                     )
                     > 10) {
                     byteValue = 10;
                  } else if (valueValue < -10) {
                     byteValue = -10;
                  } else {
                     byteValue = 0;
                  }

                  this.cmRun = -byteValue * 100;
               }
            }

            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick = false;
         }

         if (this.cmx <= 0) {
            this.cmx = 0;
         }

         if (this.cmx >= this.cmxLim) {
            this.cmx = this.cmxLim;
         }
      }
   }

   public final void moveCamera() {
      if (this.cmRun != 0 && !this.pointerIsDowning) {
         this.cmtoX = this.cmtoX + this.cmRun / 100;
         if (this.cmtoX < 0) {
            this.cmtoX = 0;
         } else if (this.cmtoX > this.cmxLim) {
            this.cmtoX = this.cmxLim;
         } else {
            this.cmx = this.cmtoX;
         }

         this.cmRun = this.cmRun * 9 / 10;
         if (this.cmRun < 100 && this.cmRun > -100) {
            this.cmRun = 0;
         }
      }

      if (this.cmx != this.cmtoX && !this.pointerIsDowning) {
         this.cmvx = this.cmtoX - this.cmx << 2;
         this.cmdx = this.cmdx + this.cmvx;
         this.cmx = this.cmx + (this.cmdx >> 4);
         this.cmdx &= 15;
      }
   }
}
