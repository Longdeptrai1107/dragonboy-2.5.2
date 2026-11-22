package nro;

import main.GameCanvas;

public final class InfoMe {
   public int[][] charId = new int[3][];
   public Info info = new Info();
   private int dir;
   private int f;
   private int tF;
   private int cmtoY;
   public int cmy;
   private int cmdy;
   private int cmvy;
   private int cmtoX;
   public int cmx;
   private int cmdx;
   private int cmvx;
   public boolean isDone;
   public boolean isUpdate = true;
   public int timeDelay;
   public int playerID;
   public Command cmdChat;

   public InfoMe() {
      for (int charIdIndex = 0; charIdIndex < this.charId.length; charIdIndex++) {
         this.charId[charIdIndex] = new int[3];
      }
   }

   public final void paint(mGraphics g) {
      if (!this.equals(GameScr.info2) || !GameScr.gI().isVS()) {
         if (!this.equals(GameScr.info2) || GameScr.gI().popUpYesNo == null) {
            if (GameScr.isPaint) {
               if (GameCanvas.currentScreen == GameScr.gI() || GameCanvas.currentScreen == CrackBallScr.gI()) {
                  if (ChatPopup.serverChatPopUp == null) {
                     if (this.isUpdate) {
                        if (!Char.ischangingMap) {
                           if (!GameCanvas.panel.isShow || !this.equals(GameScr.info2)) {
                              g.translate(-g.getTranslateX(), -g.getTranslateY());
                              g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
                              if (this.info != null) {
                                 this.info.paint(g, this.cmx, this.cmy, this.dir);
                                 if (this.info.info != null
                                    && this.info.info.charInfo != null
                                    && this.cmdChat == null) {
                                    ;
                                 }

                                 if (this.info.info != null) {
                                    ;
                                 }
                              }

                              if (this.info.info != null
                                 && this.info.info.charInfo == null
                                 && this.charId != null) {
                                 SmallImage.drawSmallImage(
                                    g,
                                    this.charId[Char.myCharz().cgender][this.f],
                                    this.cmx,
                                    this.cmy + 3 + (GameCanvas.gameTick % 10 > 5 ? 1 : 0),
                                    this.dir == 1 ? 0 : 2,
                                    StaticObj.VCENTER_HCENTER
                                 );
                              }

                              g.translate(-g.getTranslateX(), -g.getTranslateY());
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public final void update() {
      if (this.info != null
         && this.info.infoWaitToShow != null
         && this.info.infoWaitToShow.size() == 0
         && this.cmy != -40) {
         this.info.timeW--;
         if (this.info.timeW <= 0) {
            this.cmy = -40;
            this.info.time = 0;
            this.info.infoWaitToShow.removeAllElements();
            this.info.says = null;
            this.info.timeW = 200;
         }
      }

      if (!this.equals(GameScr.info2) || GameScr.gI().popUpYesNo == null) {
         if (this.isUpdate) {
            InfoMe infoMe = this;
            if (this.cmy != infoMe.cmtoY) {
               infoMe.cmvy = infoMe.cmtoY - infoMe.cmy << 2;
               infoMe.cmdy = infoMe.cmdy + infoMe.cmvy;
               infoMe.cmy = infoMe.cmy + (infoMe.cmdy >> 4);
               infoMe.cmdy &= 15;
            }

            if (infoMe.cmx != infoMe.cmtoX) {
               infoMe.cmvx = infoMe.cmtoX - infoMe.cmx << 2;
               infoMe.cmdx = infoMe.cmdx + infoMe.cmvx;
               infoMe.cmx = infoMe.cmx + (infoMe.cmdx >> 4);
               infoMe.cmdx &= 15;
            }

            infoMe.tF++;
            if (infoMe.tF == 5) {
               infoMe.tF = 0;
               if (infoMe.f == 0) {
                  infoMe.f = 1;
               } else {
                  infoMe.f = 0;
               }
            }

            if (this.info != null) {
               if (this.info == null || this.info.info != null) {
                  if (!this.isDone) {
                     if (this.timeDelay > 0) {
                        this.timeDelay--;
                        if (this.timeDelay == 0) {
                           GameCanvas.panel.setTypeMessage();
                           GameCanvas.panel.show();
                        }
                     }

                     if (GameCanvas.gameTick % 3 == 0) {
                        if (Char.myCharz().cdir == 1) {
                           this.cmtoX = Char.myCharz().cx - 20 - GameScr.cmx;
                        }

                        if (Char.myCharz().cdir == -1) {
                           this.cmtoX = Char.myCharz().cx + 20 - GameScr.cmx;
                        }

                        if (this.cmtoX <= 24) {
                           this.cmtoX = this.cmtoX + this.info.sayWidth / 2;
                        }

                        if (this.cmtoX >= GameCanvas.w - 24) {
                           this.cmtoX = this.cmtoX - this.info.sayWidth / 2;
                        }

                        this.cmtoY = Char.myCharz().cy - 40 - GameScr.cmy;
                        if (this.info.says != null
                           && this.cmtoY < (this.info.says.length + 1) * 12 + 10) {
                           this.cmtoY = (this.info.says.length + 1) * 12 + 10;
                        }

                        if (this.info.info.charInfo != null) {
                           if (GameCanvas.w - 50 > 155 + this.info.W) {
                              this.cmtoX = GameCanvas.w - 60 - this.info.W / 2;
                              this.cmtoY = this.info.H + 10;
                           } else {
                              this.cmtoX = GameCanvas.w - 20 - this.info.W / 2;
                              this.cmtoY = 45 + this.info.H;
                              if (GameCanvas.w > GameCanvas.h || GameCanvas.w < 220) {
                                 this.cmtoX = GameCanvas.w - 20 - this.info.W / 2;
                                 this.cmtoY = this.info.H + 10;
                              }
                           }
                        }
                     }

                     if (this.cmx > Char.myCharz().cx - GameScr.cmx) {
                        this.dir = -1;
                     } else {
                        this.dir = 1;
                     }
                  }

                  if (this.info.info != null) {
                     if (this.info.infoWaitToShow.size() > 1) {
                        if (this.info.info.timeCount == 0) {
                           this.info.time++;
                           if (this.info.time >= this.info.info.speed) {
                              this.info.time = 0;
                              this.info.infoWaitToShow.removeElementAt(0);
                              InfoItem infoValue = (InfoItem)this.info.infoWaitToShow.firstElement();
                              this.info.info = infoValue;
                              this.info.getInfo();
                              return;
                           }
                        } else {
                           this.info.info.curr = System.currentTimeMillis();
                           if (this.info.info.curr - this.info.info.last >= 1000L) {
                              this.info.info.last = System.currentTimeMillis();
                              this.info.info.timeCount--;
                           }

                           if (this.info.info.timeCount == 0) {
                              this.info.infoWaitToShow.removeElementAt(0);
                              if (this.info.infoWaitToShow.size() == 0) {
                                 return;
                              }

                              InfoItem infoValue2 = (InfoItem)this.info.infoWaitToShow.firstElement();
                              this.info.info = infoValue2;
                              this.info.getInfo();
                              return;
                           }
                        }
                     } else if (this.info.infoWaitToShow.size() == 1) {
                        if (this.info.info.timeCount == 0) {
                           this.info.time++;
                           if (this.info.time >= this.info.info.speed) {
                              this.isDone = true;
                           }

                           if (this.info.time == this.info.info.speed) {
                              this.cmtoY = -40;
                              this.cmtoX = Char.myCharz().cx
                                 - GameScr.cmx
                                 + (Char.myCharz().cdir == 1 ? -20 : 20);
                           }

                           if (this.info.time >= this.info.info.speed + 20) {
                              this.info.time = 0;
                              this.info.infoWaitToShow.removeAllElements();
                              this.info.says = null;
                              this.info.timeW = 200;
                              return;
                           }
                        } else {
                           this.info.info.curr = System.currentTimeMillis();
                           if (this.info.info.curr - this.info.info.last >= 1000L) {
                              this.info.info.last = System.currentTimeMillis();
                              this.info.info.timeCount--;
                           }

                           if (this.info.info.timeCount == 0) {
                              this.isDone = true;
                              this.cmtoY = -40;
                              this.cmtoX = Char.myCharz().cx
                                 - GameScr.cmx
                                 + (Char.myCharz().cdir == 1 ? -20 : 20);
                              this.info.time = 0;
                              this.info.infoWaitToShow.removeAllElements();
                              this.info.says = null;
                              this.cmdChat = null;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public final void addInfoWithChar(String s, Char c, boolean isChatServer) {
      this.playerID = c.charID;
      this.info.addInfo(s, 3, c, isChatServer);
      this.isDone = false;
   }

   public final void addInfo(String s, int Type) {
      String s2 = Res.changeString(s);
      if (this.info.infoWaitToShow.size() <= 0
         || !s2.equals(((InfoItem)this.info.infoWaitToShow.lastElement()).s)) {
         if (this.info.infoWaitToShow.size() > 10) {
            for (int index = 0; index < 5; index++) {
               this.info.infoWaitToShow.removeElementAt(0);
            }
         }

         this.info.addInfo(s2, Type, null, false);
         if (this.info.infoWaitToShow.size() == 1) {
            this.cmy = 0;
            this.cmx = Char.myCharz().cx
               - GameScr.cmx
               + (Char.myCharz().cdir == 1 ? -20 : 20);
         }

         this.isDone = false;
      }
   }
}
