package nro;

import main.GameCanvas;

public final class Scroll {
   public int cmtoX;
   public int cmtoY;
   public int cmx;
   public int cmy;
   public int cmvx;
   public int cmvy;
   public int cmdx;
   public int cmdy;
   private int xPos;
   private int yPos;
   public int width;
   public int height;
   public int cmxLim;
   public int cmyLim;
   private int pointerDownTime;
   private int pointerDownFirstX;
   private int[] pointerDownLastX = new int[3];
   public boolean pointerIsDowning;
   private boolean isDownWhenRunning;
   private int cmRun;
   public int selectedItem;
   public int ITEM_SIZE = 1;
   private int ITEM_PER_LINE = 1;
   private boolean styleUPDOWN = true;

   public final ScrollResult updateKey() {
      if (this.styleUPDOWN) {
         int deltaY = 0;
         Scroll scroll = this;
         int x = this.xPos;
         int y = scroll.yPos;
         int cmtoYValue = scroll.width;
         int h = scroll.height;
         if (GameCanvas.isPointerDown) {
            if (!scroll.pointerIsDowning && GameCanvas.isPointer(x, y, cmtoYValue, h)) {
               for (int pointerDownLastXIndex = 0; pointerDownLastXIndex < scroll.pointerDownLastX.length; pointerDownLastXIndex++) {
                  scroll.pointerDownLastX[0] = GameCanvas.py;
               }

               scroll.pointerDownFirstX = GameCanvas.py;
               scroll.pointerIsDowning = true;
               scroll.selectedItem = -1;
               scroll.isDownWhenRunning = scroll.cmRun != 0;
               scroll.cmRun = 0;
            } else if (scroll.pointerIsDowning) {
               scroll.pointerDownTime++;
               if (scroll.pointerDownTime > 5 && scroll.pointerDownFirstX == GameCanvas.py && !scroll.isDownWhenRunning) {
                  scroll.pointerDownFirstX = -1000;
                  if (scroll.ITEM_SIZE > 0) {
                     if (scroll.ITEM_PER_LINE > 1) {
                        cmtoYValue = (scroll.cmtoY + GameCanvas.py - y) / scroll.ITEM_SIZE;
                        deltaY = (scroll.cmtoX + GameCanvas.px - x) / scroll.ITEM_SIZE;
                        scroll.selectedItem = cmtoYValue * scroll.ITEM_PER_LINE + deltaY;
                     } else {
                        scroll.selectedItem = (scroll.cmtoY + GameCanvas.py - y) / scroll.ITEM_SIZE;
                     }
                  } else {
                     scroll.selectedItem = -1;
                  }
               }

               if ((cmtoYValue = GameCanvas.py - scroll.pointerDownLastX[0]) != 0 && scroll.selectedItem != -1) {
                  scroll.selectedItem = -1;
               }

               for (int pointerDownLastXIndex2 = scroll.pointerDownLastX.length - 1; pointerDownLastXIndex2 > 0; pointerDownLastXIndex2--) {
                  scroll.pointerDownLastX[pointerDownLastXIndex2] = scroll.pointerDownLastX[pointerDownLastXIndex2 - 1];
               }

               scroll.pointerDownLastX[0] = GameCanvas.py;
               scroll.cmtoY -= cmtoYValue;
               if (scroll.cmtoY < 0) {
                  scroll.cmtoY = 0;
               }

               if (scroll.cmtoY > scroll.cmyLim) {
                  scroll.cmtoY = scroll.cmyLim;
               }

               if (scroll.cmy < 0 || scroll.cmy > scroll.cmyLim) {
                  cmtoYValue /= 2;
               }

               scroll.cmy -= cmtoYValue;
            }
         }

         boolean isFinish = false;
         if (GameCanvas.isPointerJustRelease && scroll.pointerIsDowning) {
            deltaY = GameCanvas.py - scroll.pointerDownLastX[0];
            GameCanvas.isPointerJustRelease = false;
            if (Res.abs(deltaY) < 20
               && Res.abs(GameCanvas.py - scroll.pointerDownFirstX) < 20
               && !scroll.isDownWhenRunning) {
               scroll.cmRun = 0;
               scroll.cmtoY = scroll.cmy;
               scroll.pointerDownFirstX = -1000;
               if (scroll.ITEM_SIZE > 0) {
                  if (scroll.ITEM_PER_LINE > 1) {
                     deltaY = (scroll.cmtoY + GameCanvas.py - y) / scroll.ITEM_SIZE;
                     x = (scroll.cmtoX + GameCanvas.px - x) / scroll.ITEM_SIZE;
                     scroll.selectedItem = deltaY * scroll.ITEM_PER_LINE + x;
                  } else {
                     scroll.selectedItem = (scroll.cmtoY + GameCanvas.py - y) / scroll.ITEM_SIZE;
                  }
               } else {
                  scroll.selectedItem = -1;
               }

               scroll.pointerDownTime = 0;
               isFinish = true;
            } else if (scroll.selectedItem != -1 && scroll.pointerDownTime > 5) {
               scroll.pointerDownTime = 0;
               isFinish = true;
            } else if (scroll.selectedItem == -1 && !scroll.isDownWhenRunning) {
               if (scroll.cmy < 0) {
                  scroll.cmtoY = 0;
               } else if (scroll.cmy > scroll.cmyLim) {
                  scroll.cmtoY = scroll.cmyLim;
               } else {
                  byte byteValue;
                  if ((
                        deltaY = GameCanvas.py
                           - scroll.pointerDownLastX[0]
                           + (scroll.pointerDownLastX[0] - scroll.pointerDownLastX[1])
                           + (scroll.pointerDownLastX[1] - scroll.pointerDownLastX[2])
                     )
                     > 10) {
                     byteValue = 10;
                  } else if (deltaY < -10) {
                     byteValue = -10;
                  } else {
                     byteValue = 0;
                  }

                  scroll.cmRun = -byteValue * 100;
               }
            }

            scroll.pointerIsDowning = false;
            scroll.pointerDownTime = 0;
            GameCanvas.isPointerJustRelease = false;
         }

         ScrollResult scrollResult;
         (scrollResult = new ScrollResult()).selected = scroll.selectedItem;
         scrollResult.isFinish = isFinish;
         scrollResult.isDowning = scroll.pointerIsDowning;
         return scrollResult;
      } else {
         Scroll scroll2 = this;
         int x2 = this.xPos;
         int y2 = scroll2.yPos;
         int w = scroll2.width;
         int h2 = scroll2.height;
         if (GameCanvas.isPointerDown) {
            if (!scroll2.pointerIsDowning && GameCanvas.isPointer(x2, y2, w, h2)) {
               for (int pointerDownLastXIndex3 = 0; pointerDownLastXIndex3 < scroll2.pointerDownLastX.length; pointerDownLastXIndex3++) {
                  scroll2.pointerDownLastX[0] = GameCanvas.px;
               }

               scroll2.pointerDownFirstX = GameCanvas.px;
               scroll2.pointerIsDowning = true;
               scroll2.selectedItem = -1;
               scroll2.isDownWhenRunning = scroll2.cmRun != 0;
               scroll2.cmRun = 0;
            } else if (scroll2.pointerIsDowning) {
               scroll2.pointerDownTime++;
               if (scroll2.pointerDownTime > 5 && scroll2.pointerDownFirstX == GameCanvas.px && !scroll2.isDownWhenRunning) {
                  scroll2.pointerDownFirstX = -1000;
                  if (scroll2.ITEM_SIZE > 0) {
                     scroll2.selectedItem = (scroll2.cmtoX + GameCanvas.px - x2) / scroll2.ITEM_SIZE;
                  } else {
                     scroll2.selectedItem = -1;
                  }
               }

               int cmtoXValue;
               if ((cmtoXValue = GameCanvas.px - scroll2.pointerDownLastX[0]) != 0 && scroll2.selectedItem != -1) {
                  scroll2.selectedItem = -1;
               }

               for (int pointerDownLastXIndex4 = scroll2.pointerDownLastX.length - 1; pointerDownLastXIndex4 > 0; pointerDownLastXIndex4--) {
                  scroll2.pointerDownLastX[pointerDownLastXIndex4] = scroll2.pointerDownLastX[pointerDownLastXIndex4 - 1];
               }

               scroll2.pointerDownLastX[0] = GameCanvas.px;
               scroll2.cmtoX -= cmtoXValue;
               if (scroll2.cmtoX < 0) {
                  scroll2.cmtoX = 0;
               }

               if (scroll2.cmtoX > scroll2.cmxLim) {
                  scroll2.cmtoX = scroll2.cmxLim;
               }

               if (scroll2.cmx < 0 || scroll2.cmx > scroll2.cmxLim) {
                  cmtoXValue /= 2;
               }

               scroll2.cmx -= cmtoXValue;
            }
         }

         boolean isFinish2 = false;
         if (GameCanvas.isPointerJustRelease && scroll2.pointerIsDowning) {
            h2 = GameCanvas.px - scroll2.pointerDownLastX[0];
            GameCanvas.isPointerJustRelease = false;
            if (Res.abs(h2) < 20
               && Res.abs(GameCanvas.px - scroll2.pointerDownFirstX) < 20
               && !scroll2.isDownWhenRunning) {
               scroll2.cmRun = 0;
               scroll2.cmtoX = scroll2.cmx;
               scroll2.pointerDownFirstX = -1000;
               if (scroll2.ITEM_SIZE > 0) {
                  scroll2.selectedItem = (scroll2.cmtoX + GameCanvas.px - x2) / scroll2.ITEM_SIZE;
               } else {
                  scroll2.selectedItem = -1;
               }

               scroll2.pointerDownTime = 0;
               isFinish2 = true;
            } else if (scroll2.selectedItem != -1 && scroll2.pointerDownTime > 5) {
               scroll2.pointerDownTime = 0;
               isFinish2 = true;
            } else if (scroll2.selectedItem == -1 && !scroll2.isDownWhenRunning) {
               if (scroll2.cmx < 0) {
                  scroll2.cmtoX = 0;
               } else if (scroll2.cmx > scroll2.cmxLim) {
                  scroll2.cmtoX = scroll2.cmxLim;
               } else {
                  byte byteValue2;
                  if ((
                        x2 = GameCanvas.px
                           - scroll2.pointerDownLastX[0]
                           + (scroll2.pointerDownLastX[0] - scroll2.pointerDownLastX[1])
                           + (scroll2.pointerDownLastX[1] - scroll2.pointerDownLastX[2])
                     )
                     > 10) {
                     byteValue2 = 10;
                  } else if (x2 < -10) {
                     byteValue2 = -10;
                  } else {
                     byteValue2 = 0;
                  }

                  scroll2.cmRun = -byteValue2 * 100;
               }
            }

            scroll2.pointerIsDowning = false;
            scroll2.pointerDownTime = 0;
            GameCanvas.isPointerJustRelease = false;
         }

         ScrollResult scrollResult2;
         (scrollResult2 = new ScrollResult()).selected = scroll2.selectedItem;
         scrollResult2.isFinish = isFinish2;
         scrollResult2.isDowning = scroll2.pointerIsDowning;
         return scrollResult2;
      }
   }

   public final void updatecm() {
      if (this.cmRun != 0 && !this.pointerIsDowning) {
         if (this.styleUPDOWN) {
            this.cmtoY = this.cmtoY + this.cmRun / 100;
            if (this.cmtoY < 0) {
               this.cmtoY = 0;
            } else if (this.cmtoY > this.cmyLim) {
               this.cmtoY = this.cmyLim;
            } else {
               this.cmy = this.cmtoY;
            }
         } else {
            this.cmtoX = this.cmtoX + this.cmRun / 100;
            if (this.cmtoX < 0) {
               this.cmtoX = 0;
            } else if (this.cmtoX > this.cmxLim) {
               this.cmtoX = this.cmxLim;
            } else {
               this.cmx = this.cmtoX;
            }
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

      if (this.cmy != this.cmtoY && !this.pointerIsDowning) {
         this.cmvy = this.cmtoY - this.cmy << 2;
         this.cmdy = this.cmdy + this.cmvy;
         this.cmy = this.cmy + (this.cmdy >> 4);
         this.cmdy &= 15;
      }
   }

   public final void setStyle(int nItem, int ITEM_SIZE, int xPos, int yPos, int width, int height, boolean styleUPDOWN, int ITEM_PER_LINE) {
      this.xPos = xPos;
      this.yPos = yPos;
      if (ITEM_SIZE <= 0) {
         ITEM_SIZE = 1;
      }

      this.ITEM_PER_LINE = 1;
      this.ITEM_SIZE = ITEM_SIZE;
      this.width = width;
      this.height = height;
      this.styleUPDOWN = true;
      int index = nItem;
      if (false) {
         index++;
      }

      this.cmyLim = index * ITEM_SIZE - height;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      if (this.cmxLim < 0) {
         this.cmxLim = 0;
      }
   }

   public final void moveTo(int to) {
      if (this.styleUPDOWN) {
         to -= (this.height - this.ITEM_SIZE) / 2;
         this.cmtoY = to;
         if (this.cmtoY < 0) {
            this.cmtoY = 0;
         }

         if (this.cmtoY > this.cmyLim) {
            this.cmtoY = this.cmyLim;
            return;
         }
      } else {
         to -= (this.width - this.ITEM_SIZE) / 2;
         this.cmtoX = to;
         if (this.cmtoX < 0) {
            this.cmtoX = 0;
         }

         if (this.cmtoX > this.cmxLim) {
            this.cmtoX = this.cmxLim;
         }
      }
   }
}
