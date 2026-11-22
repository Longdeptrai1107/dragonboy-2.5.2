package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Menu {
   public boolean showMenu;
   private MyVector menuItems;
   public int menuSelectedItem;
   public int menuX;
   public int menuY;
   private int menuW;
   private int menuH;
   private static int[] menuTemY;
   private static int cmtoX;
   private static int cmx;
   private static int cmxLim;
   private static int xc;
   private Command left = new Command(mResources.SELECT, 0);
   private Command right = new Command(
      mResources.CLOSE, 0, GameCanvas.w - 71, GameCanvas.h - mScreen.cmdH + 1
   );
   private Command center = null;
   private static Image imgMenu1 = mSystem.loadImage("/mainImage/myTexture2dbtMenu1.png");
   private static Image imgMenu2 = mSystem.loadImage("/mainImage/myTexture2dbtMenu2.png");
   private boolean disableClose;
   public int tDelay;
   private int w;
   private int pointerDownTime;
   private int pointerDownFirstX;
   private int[] pointerDownLastX = new int[3];
   private boolean pointerIsDowning;
   private boolean isDownWhenRunning;
   private int waitToPerform;
   private int cmRun;
   private boolean touch;
   private boolean close;
   private int cmvx;
   private int cmdx;
   private boolean isClose;
   private boolean[] isNotClose;

   public final void startWithoutCloseButton(MyVector menuItemsValue, int pos) {
      this.startAt(menuItemsValue);
      this.disableClose = true;
   }

   public final void startAt(MyVector menuItems, int x, int y) {
      this.startAt(menuItems);
      this.menuX = x;
      this.menuY = y;

      while (this.menuY + this.menuH > GameCanvas.h) {
         this.menuY -= 2;
      }
   }

   public final void startAt(MyVector menuItemsValue) {
      if (!this.showMenu) {
         this.isClose = false;
         this.touch = false;
         this.close = false;
         this.tDelay = 0;
         if (menuItemsValue.size() == 1) {
            this.menuSelectedItem = 0;
            Command menuItem;
            if ((menuItem = (Command)menuItemsValue.elementAt(0)) != null && menuItem.caption.equals(mResources.saying)) {
               menuItem.performAction();
               this.showMenu = false;
               InfoDlg.showWait();
               return;
            }
         }

         SoundMn.gI();
         SoundMn.openMenu();
         this.isNotClose = new boolean[menuItemsValue.size()];

         for (int isNotCloseIndex = 0; isNotCloseIndex < this.isNotClose.length; isNotCloseIndex++) {
            this.isNotClose[isNotCloseIndex] = false;
         }

         this.disableClose = false;
         ChatPopup.currChatPopup = null;
         Effect2.vEffect2.removeAllElements();
         Effect2.vEffect2Outside.removeAllElements();
         InfoDlg.hide();
         if (menuItemsValue.size() != 0) {
            this.menuItems = menuItemsValue;
            this.menuW = 60;
            this.menuH = 60;

            for (int menuItemIndex = 0; menuItemIndex < menuItemsValue.size(); menuItemIndex++) {
               Command menuItem2;
               (menuItem2 = (Command)menuItemsValue.elementAt(menuItemIndex)).isPlaySoundButton = false;
               mFont.tahoma_7_yellow.getWidth(menuItem2.caption);
               menuItem2.subCaption = mFont.tahoma_7_yellow.splitFontArray(menuItem2.caption, this.menuW - 10);
            }

            menuTemY = new int[menuItemsValue.size()];
            this.menuX = (GameCanvas.w - menuItemsValue.size() * this.menuW) / 2;
            if (this.menuX <= 0) {
               this.menuX = 1;
            }

            this.menuY = GameCanvas.h - this.menuH - (Paint.hTab + 1) - 1;
            if (GameCanvas.isTouch) {
               this.menuY -= 3;
            }

            this.menuY += 27;

            for (int menuTemYIndex = 0; menuTemYIndex < menuTemY.length; menuTemYIndex++) {
               menuTemY[menuTemYIndex] = GameCanvas.h;
            }

            this.showMenu = true;
            this.menuSelectedItem = 0;
            if ((cmxLim = this.menuItems.size() * this.menuW - GameCanvas.w) < 0) {
               cmxLim = 0;
            }

            cmtoX = 0;
            cmx = 0;
            xc = 50;
            this.w = menuItemsValue.size() * this.menuW - 1;
            if (this.w > GameCanvas.w - 2) {
               this.w = GameCanvas.w - 2;
            }

            if (GameCanvas.isTouch) {
               this.menuSelectedItem = -1;
            }
         }
      }
   }

   private boolean isScrolling() {
      return !this.isClose && menuTemY[menuTemY.length - 1] > this.menuY
         || this.isClose && menuTemY[menuTemY.length - 1] < GameCanvas.h;
   }

   public final void updateMenuKey() {
      if (!GameScr.gI().activeRongThan || !GameScr.gI().isUseFreez) {
         if (this.showMenu) {
            if (!this.isScrolling()) {
               int cmtoXValue = 0;
               if (GameCanvas.keyPressed[2] || GameCanvas.keyPressed[4]) {
                  cmtoXValue = 1;
                  this.menuSelectedItem--;
                  if (this.menuSelectedItem < 0) {
                     this.menuSelectedItem = this.menuItems.size() - 1;
                  }
               } else if (GameCanvas.keyPressed[8] || GameCanvas.keyPressed[6]) {
                  cmtoXValue = 1;
                  this.menuSelectedItem++;
                  if (this.menuSelectedItem > this.menuItems.size() - 1) {
                     this.menuSelectedItem = 0;
                  }
               } else if (GameCanvas.keyPressed[5]) {
                  this.waitToPerform = 2;
               } else if (GameCanvas.keyPressed[12] && !GameScr.gI().isRongThanMenu()) {
                  if (this.isScrolling()) {
                     return;
                  }

                  if (this.left.idAction <= 0) {
                     this.waitToPerform = 2;
                  }

                  SoundMn.gI();
                  SoundMn.buttonClose();
               } else if (!GameScr.gI().isRongThanMenu()
                  && !this.disableClose
                  && (GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(this.right))) {
                  if (this.isScrolling()) {
                     return;
                  }

                  if (!this.close) {
                     this.close = true;
                  }

                  this.isClose = true;
                  SoundMn.gI();
                  SoundMn.buttonClose();
               }

      if (cmtoXValue != 0) {
                  if ((cmtoX = this.menuSelectedItem * this.menuW + this.menuW - GameCanvas.w / 2)
                     > cmxLim) {
                     cmtoX = cmxLim;
                  }

                  if (cmtoX < 0) {
                     cmtoX = 0;
                  }

                  if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
                     cmx = cmtoX;
                  }
               }

               boolean condition = true;
               if (GameCanvas.panel.cp != null && GameCanvas.panel.cp.isClip) {
                  if (!GameCanvas.isPointerHoldIn(
                     GameCanvas.panel.cp.cx,
                     0,
                     GameCanvas.panel.cp.sayWidth + 2,
                     GameCanvas.panel.cp.ch
                  )) {
                     condition = true;
                  } else {
                     condition = false;
                     GameCanvas.panel.cp.updateKey();
                  }
               }

               if (!this.disableClose
                  && GameCanvas.isPointerJustRelease
                  && !GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH)
                  && !this.pointerIsDowning
                  && !GameScr.gI().isRongThanMenu()
                  && condition) {
                  if (!this.isScrolling()) {
                     this.pointerDownTime = this.pointerDownFirstX = 0;
                     this.pointerIsDowning = false;
                     GameCanvas.clearAllPointerEvent();
                     this.isClose = true;
                     this.close = true;
                     SoundMn.gI();
                     SoundMn.buttonClose();
                  }
               } else {
                  if (GameCanvas.isPointerDown) {
                     if (!this.pointerIsDowning
                        && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH)) {
                        for (int pointerDownLastXIndex = 0; pointerDownLastXIndex < this.pointerDownLastX.length; pointerDownLastXIndex++) {
                           this.pointerDownLastX[0] = GameCanvas.px;
                        }

                        this.pointerDownFirstX = GameCanvas.px;
                        this.pointerIsDowning = true;
                        this.isDownWhenRunning = this.cmRun != 0;
                        this.cmRun = 0;
                     } else if (this.pointerIsDowning) {
                        this.pointerDownTime++;
                        if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px && !this.isDownWhenRunning) {
                           this.pointerDownFirstX = -1000;
                           this.menuSelectedItem = (cmtoX + GameCanvas.px - this.menuX) / this.menuW;
                        }

                        if ((cmtoXValue = GameCanvas.px - this.pointerDownLastX[0]) != 0 && this.menuSelectedItem != -1) {
                           this.menuSelectedItem = -1;
                        }

                        for (int pointerDownLastXIndex2 = this.pointerDownLastX.length - 1; pointerDownLastXIndex2 > 0; pointerDownLastXIndex2--) {
                           this.pointerDownLastX[pointerDownLastXIndex2] = this.pointerDownLastX[pointerDownLastXIndex2 - 1];
                        }

                        this.pointerDownLastX[0] = GameCanvas.px;
                        if ((cmtoX -= cmtoXValue) < 0) {
                           cmtoX = 0;
                        }

                        if (cmtoX > cmxLim) {
                           cmtoX = cmxLim;
                        }

                        if (cmx < 0 || cmx > cmxLim) {
                           cmtoXValue /= 2;
                        }

                        cmx -= cmtoXValue;
                     }
                  }

                  if (GameCanvas.isPointerJustRelease && this.pointerIsDowning) {
                     cmtoXValue = GameCanvas.px - this.pointerDownLastX[0];
                     GameCanvas.isPointerJustRelease = false;
                     if (Res.abs(cmtoXValue) < 20
                        && Res.abs(GameCanvas.px - this.pointerDownFirstX) < 20
                        && !this.isDownWhenRunning) {
                        this.cmRun = 0;
                        cmtoX = cmx;
                        this.pointerDownFirstX = -1000;
                        this.menuSelectedItem = (cmtoX + GameCanvas.px - this.menuX) / this.menuW;
                        this.pointerDownTime = 0;
                        this.waitToPerform = 10;
                     } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                        this.pointerDownTime = 0;
                        this.waitToPerform = 1;
                     } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                        if (cmx < 0) {
                           cmtoX = 0;
                        } else if (cmx > cmxLim) {
                           cmtoX = cmxLim;
                        } else {
                           int value;
                           byte byteValue;
                           if ((
                                 value = GameCanvas.px
                                    - this.pointerDownLastX[0]
                                    + (this.pointerDownLastX[0] - this.pointerDownLastX[1])
                                    + (this.pointerDownLastX[1] - this.pointerDownLastX[2])
                              )
                              > 10) {
                              byteValue = 10;
                           } else if (value < -10) {
                              byteValue = -10;
                           } else {
                              byteValue = 0;
                           }

                           this.cmRun = -byteValue * 100;
                        }
                     }

                     this.pointerIsDowning = false;
                     this.pointerDownTime = 0;
                     GameCanvas.isPointerJustRelease = false;
                  }

                  GameCanvas.clearKeyPressed();
                  GameCanvas.clearKeyHold();
               }
            }
         }
      }
   }

   public final void paintMenu(mGraphics g) {
      if (!GameScr.gI().activeRongThan || !GameScr.gI().isUseFreez) {
         g.translate(-g.getTranslateX(), -g.getTranslateY());
         g.translate(-cmx, 0);

         for (int menuTemYIndex = 0; menuTemYIndex < this.menuItems.size(); menuTemYIndex++) {
            if (menuTemYIndex == this.menuSelectedItem) {
               g.drawImage(imgMenu2, ((int) (this.menuX + menuTemYIndex * this.menuW + 1)), ((int) (menuTemY[menuTemYIndex] + 1)), 0);
            } else {
               g.drawImage(imgMenu1, ((int) (this.menuX + menuTemYIndex * this.menuW + 1)), ((int) (menuTemY[menuTemYIndex] + 1)), 0);
            }

            Command menuItem;
            String[] subCaption;
            if ((subCaption = (menuItem = (Command)this.menuItems.elementAt(menuTemYIndex)).subCaption) == null) {
               subCaption = new String[]{((Command)this.menuItems.elementAt(menuTemYIndex)).caption};
            }

            int value = menuTemY[menuTemYIndex] + (this.menuH - subCaption.length * 14) / 2 + 1;

            for (int subCaptionIndex = 0; subCaptionIndex < subCaption.length; subCaptionIndex++) {
               if (menuTemYIndex == this.menuSelectedItem) {
                  mFont.tahoma_7b_green2
                     .drawString(g, subCaption[subCaptionIndex], this.menuX + menuTemYIndex * this.menuW + this.menuW / 2, value + subCaptionIndex * 14, 2);
               } else if (menuItem.isDisplay) {
                  mFont.tahoma_7b_red
                     .drawString(g, subCaption[subCaptionIndex], this.menuX + menuTemYIndex * this.menuW + this.menuW / 2, value + subCaptionIndex * 14, 2);
               } else {
                  mFont.tahoma_7b_dark
                     .drawString(g, subCaption[subCaptionIndex], this.menuX + menuTemYIndex * this.menuW + this.menuW / 2, value + subCaptionIndex * 14, 2);
               }
            }
         }

         g.translate(-g.getTranslateX(), -g.getTranslateY());
      }
   }

   public final void updateMenu() {
      Menu menu = this;
      if (this.cmRun != 0 && !menu.pointerIsDowning) {
         if ((cmtoX = cmtoX + menu.cmRun / 100) < 0) {
            cmtoX = 0;
         } else if (cmtoX > cmxLim) {
            cmtoX = cmxLim;
         } else {
            cmx = cmtoX;
         }

         menu.cmRun = menu.cmRun * 9 / 10;
         if (menu.cmRun < 100 && menu.cmRun > -100) {
            menu.cmRun = 0;
         }
      }

      if (cmx != cmtoX && !menu.pointerIsDowning) {
         menu.cmvx = cmtoX - cmx << 2;
         menu.cmdx = menu.cmdx + menu.cmvx;
         cmx = cmx + (menu.cmdx >> 4);
         menu.cmdx &= 15;
      }

      if (!this.isClose) {
         this.tDelay++;

         for (int menuTemYIndex = 0; menuTemYIndex < menuTemY.length; menuTemYIndex++) {
            if (menuTemY[menuTemYIndex] > this.menuY) {
               int value;
               if ((value = menuTemY[menuTemYIndex] - this.menuY >> 1) <= 0) {
                  value = 1;
               }

               if (this.tDelay > menuTemYIndex) {
                  menuTemY[menuTemYIndex] = menuTemY[menuTemYIndex] - value;
               }
            }
         }

         if (menuTemY[menuTemY.length - 1] <= this.menuY) {
            this.tDelay = 0;
         }
      } else {
         this.tDelay++;

         for (int menuTemYIndex2 = 0; menuTemYIndex2 < menuTemY.length; menuTemYIndex2++) {
            if (menuTemY[menuTemYIndex2] < GameCanvas.h) {
               int value2;
               if ((value2 = (GameCanvas.h - menuTemY[menuTemYIndex2] >> 1) + 2) <= 0) {
                  value2 = 1;
               }

               if (this.tDelay > menuTemYIndex2) {
                  menuTemY[menuTemYIndex2] = menuTemY[menuTemYIndex2] + value2;
               }
            }
         }

         if (menuTemY[menuTemY.length - 1] >= GameCanvas.h) {
            this.tDelay = 0;
            menu = this;
            this.isClose = false;
            menu.showMenu = false;
            InfoDlg.hide();
            if (menu.close) {
               GameCanvas.panel.cp = null;
               Char.chatPopup = null;
               if (GameCanvas.panel2 != null && GameCanvas.panel2.cp != null) {
                  GameCanvas.panel2.cp = null;
               }
            } else if (menu.touch) {
               GameCanvas.panel.cp = null;
               if (GameCanvas.panel2 != null && GameCanvas.panel2.cp != null) {
                  GameCanvas.panel2.cp = null;
               }

               Command menuItem;
               if (menu.menuSelectedItem >= 0 && (menuItem = (Command)menu.menuItems.elementAt(menu.menuSelectedItem)) != null) {
                  SoundMn.gI();
                  SoundMn.buttonClose();
                  menuItem.performAction();
               }
            }
         }
      }

      if (xc != 0 && (xc >>= 1) < 0) {
         xc = 0;
      }

      if (!this.isScrolling()) {
         if (this.waitToPerform > 0) {
            this.waitToPerform--;
            if (this.waitToPerform == 0) {
               if (this.menuSelectedItem >= 0 && !this.isNotClose[this.menuSelectedItem]) {
                  this.isClose = true;
                  this.touch = true;
                  GameCanvas.panel.cp = null;
                  return;
               }

               menu = this;
               InfoDlg.hide();
               Command menuItem2;
               if (menu.menuSelectedItem >= 0 && (menuItem2 = (Command)menu.menuItems.elementAt(menu.menuSelectedItem)) != null) {
                  menuItem2.performAction();
               }
            }
         }
      }
   }
}
