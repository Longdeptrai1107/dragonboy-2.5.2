package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Teleport {
   public static MyVector vTeleport = new MyVector("vTeleport");
   public int x;
   public int y;
   private int headId;
   private int type;
   private boolean isMe;
   private int y2;
   public int id;
   private int dir;
   private int planet;
   private static Image[] maybay = new Image[5];
   private static Image hole;
   private boolean isUp;
   private boolean isDown;
   private boolean createShip = false;
   public boolean paintFire;
   private boolean painHead;
   private int tPrepare;
   private int vy = 1;
   private int tFire;
   private int tDelayHole;
   private boolean tHole;
   private boolean isShock;

   public Teleport(int xValue, int yValue, int headIdValue, int dirValue, int typeValue, boolean isMeValue, int planetValue) {
      this.x = xValue;
      this.y = 5;
      this.y2 = yValue;
      Res.outz("TELEPORT x= " + xValue + " y= " + yValue + " type= " + typeValue);
      this.headId = headIdValue;
      this.type = typeValue;
      this.isMe = isMeValue;
      this.dir = dirValue;
      this.planet = planetValue;
      this.tPrepare = 0;
      int index = 0;

      while (index < 100) {
         index++;
         this.y2 += 12;
         if (TileMap.tileTypeAt(xValue, this.y2, 2)) {
            if (this.y2 % 24 != 0) {
               this.y2 = this.y2 - this.y2 % 24;
            }
            break;
         }
      }

      Res.outz("Y2= " + this.y2 + "--------------------------------------------------------------");
      this.isDown = true;
      this.isUp = false;
      if (this.planet > 2) {
         this.y2 += 4;
         if (maybay[3] == null) {
            maybay[3] = mSystem.loadZoomedResourceImage("/mainImage/myTexture2dmaybay4a.png");
         }

         if (maybay[4] == null) {
            maybay[4] = mSystem.loadZoomedResourceImage("/mainImage/myTexture2dmaybay4b.png");
         }

         if (hole == null) {
            hole = mSystem.loadZoomedResourceImage("/mainImage/hole.png");
         }
      } else if (maybay[planetValue] == null) {
         maybay[planetValue] = mSystem.loadImage("/mainImage/myTexture2dmaybay" + (planetValue + 1) + ".png");
      }

      if (xValue > GameScr.cmx && xValue < GameScr.cmx + GameCanvas.w && this.y2 > 100) {
         SoundMn.gI();
         SoundMn.isPlayAirShip();
         SoundMn.gI();
         SoundMn.isPlayRain();
         this.createShip = true;
         SoundMn.gI();
         SoundMn.airShip();
      }
   }

   public static void addTeleport(Teleport p) {
      vTeleport.addElement(p);
   }

   public final void paintHole(mGraphics g) {
      if (this.planet > 2 && this.tHole) {
         g.drawImage(hole, ((int) (this.x)), ((int) (this.y2 + 20)), StaticObj.BOTTOM_HCENTER);
      }
   }

   public final void paint(mGraphics g) {
      if (!Char.isLoadingMap) {
         if (this.x >= GameScr.cmx && this.x <= GameScr.cmx + GameCanvas.w) {
            Part part = GameScr.parts[this.headId];
            byte byteValue = 0;
            byte byteValue2 = 0;
            if (this.planet == 0) {
               byteValue = 15;
               byteValue2 = 40;
            }

            if (this.planet == 1) {
               byteValue = 7;
               byteValue2 = 55;
            }

            if (this.planet == 2) {
               byteValue = 18;
               byteValue2 = 52;
            }

            if (this.painHead && this.planet < 3) {
               SmallImage.drawSmallImage(
                  g,
                  part.pi[Char.CharInfo[0][0][0]].id,
                  this.x + (this.dir == 1 ? byteValue : -byteValue),
                  this.y - byteValue2,
                  this.dir == 1 ? 0 : 2,
                  StaticObj.TOP_CENTER
               );
            }

            if (this.planet < 3) {
               g.drawRegion(
                  maybay[this.planet],
                  0,
                  0,
                  mGraphics.getImageWidth(maybay[this.planet]),
                  mGraphics.getImageHeight(maybay[this.planet]),
                  this.dir == 1 ? 2 : 0,
                  this.x,
                  this.y,
                  StaticObj.BOTTOM_HCENTER
               );
            } else if (this.isDown) {
               if (this.tPrepare > 10) {
                  g.drawRegion(
                     maybay[4],
                     0,
                     0,
                     mGraphics.getImageWidth(maybay[4]),
                     mGraphics.getImageHeight(maybay[4]),
                     this.dir == 1 ? 2 : 0,
                     this.dir == 1 ? this.x - 11 : this.x + 11,
                     this.y + 2,
                     StaticObj.BOTTOM_HCENTER
                  );
               } else {
                  g.drawRegion(
                     maybay[3],
                     0,
                     0,
                     mGraphics.getImageWidth(maybay[3]),
                     mGraphics.getImageHeight(maybay[3]),
                     this.dir == 1 ? 2 : 0,
                     this.x,
                     this.y,
                     StaticObj.BOTTOM_HCENTER
                  );
               }
            } else if (this.tPrepare < 20) {
               g.drawRegion(
                  maybay[4],
                  0,
                  0,
                  mGraphics.getImageWidth(maybay[4]),
                  mGraphics.getImageHeight(maybay[4]),
                  this.dir == 1 ? 2 : 0,
                  this.dir == 1 ? this.x - 11 : this.x + 11,
                  this.y + 2,
                  StaticObj.BOTTOM_HCENTER
               );
            } else {
               g.drawRegion(
                  maybay[3],
                  0,
                  0,
                  mGraphics.getImageWidth(maybay[3]),
                  mGraphics.getImageHeight(maybay[3]),
                  this.dir == 1 ? 2 : 0,
                  this.x,
                  this.y,
                  StaticObj.BOTTOM_HCENTER
               );
            }
         }
      }
   }

   public final void update() {
      if (this.planet > 2 && this.paintFire && this.y != -80) {
         if (this.isDown && this.tPrepare == 0) {
            if (GameCanvas.gameTick % 3 == 0) {
               ServerEffect.addServerEffect(1, this.x, this.y, 1, 0);
            }
         } else if (this.isUp && GameCanvas.gameTick % 3 == 0) {
            ServerEffect.addServerEffect(1, this.x, this.y + 16, 1, 1);
         }
      }

      this.tFire++;
      if (this.tFire > 3) {
         this.tFire = 0;
      }

      if (this.isDown) {
         this.paintFire = true;
         this.painHead = this.type != 0;
         if (this.planet < 3) {
            int yValue;
            if ((yValue = this.y2 - this.y >> 3) <= 0) {
               yValue = 1;
               this.paintFire = false;
            }

            this.y += yValue;
         } else {
            if (GameCanvas.gameTick % 2 == 0) {
               this.vy++;
            }

            if (this.y2 - this.y < this.vy) {
               this.y = this.y2;
               this.paintFire = false;
            } else {
               this.y = this.y + this.vy;
            }
         }

         if (this.isMe && this.type == 1 && Char.myCharz().isTeleport) {
            Char.myCharz().cx = this.x;
            Char.myCharz().cy = this.y - 30;
            Char.myCharz().statusMe = 4;
            GameScr.cmtoX = this.x - GameScr.gW2;
            GameScr.cmtoY = this.y - GameScr.gH23 - 1;
            GameScr.info1.isUpdate = false;
         }

         if (GameScr.findCharInMap(this.id) != null
            && !this.isMe
            && this.type == 1
            && GameScr.findCharInMap(this.id).isTeleport) {
            GameScr.findCharInMap(this.id).cx = this.x;
            GameScr.findCharInMap(this.id).cy = this.y - 30;
            GameScr.findCharInMap(this.id).statusMe = 4;
         }

         if (Res.abs(this.y - this.y2) < 50
            && TileMap.tileTypeAt(this.x, this.y, 2)) {
            this.tHole = true;
            if (this.planet < 3) {
               SoundMn.gI();
               SoundMn.pauseAirShip();
               if (this.y % 24 != 0) {
                  this.y = this.y - this.y % 24;
               }

               this.tPrepare++;
               if (this.tPrepare > 10) {
                  this.tPrepare = 0;
                  this.isDown = false;
                  this.isUp = true;
                  this.paintFire = false;
               }

               if (this.type == 1) {
                  if (this.isMe) {
                     Char.myCharz().isTeleport = false;
                  } else if (GameScr.findCharInMap(this.id) != null) {
                     GameScr.findCharInMap(this.id).isTeleport = false;
                  }

                  this.painHead = false;
               }
            } else {
               this.y = this.y2;
               if (!this.isShock) {
                  ServerEffect.addServerEffect(92, this.x + 4, this.y + 14, 1, 0);
                  GameScr.shock_scr = 10;
                  this.isShock = true;
               }

               this.tPrepare++;
               if (this.tPrepare > 30) {
                  this.tPrepare = 0;
                  this.isDown = false;
                  this.isUp = true;
                  this.paintFire = false;
               }

               if (this.type == 1) {
                  if (this.isMe) {
                     Char.myCharz().isTeleport = false;
                  } else if (GameScr.findCharInMap(this.id) != null) {
                     GameScr.findCharInMap(this.id).isTeleport = false;
                  }

                  this.painHead = false;
               }
            }
         }
      } else if (this.isUp) {
         this.tPrepare++;
         if (this.tPrepare > 30) {
            int yValue2;
            if ((yValue2 = this.y2 + 24 - this.y >> 3) > 30) {
               yValue2 = 30;
            }

            this.y -= yValue2;
            this.paintFire = true;
         } else {
            if (this.tPrepare == 14 && this.createShip) {
               SoundMn.gI();
               SoundMn.resumeAirShip();
            }

            if (this.tPrepare > 0 && this.type == 0) {
               if (this.isMe) {
                  Char.myCharz().isTeleport = false;
                  if (Char.myCharz().statusMe != 14) {
                     Char.myCharz().statusMe = 3;
                  }

                  Char.myCharz().cvy = -3;
               } else if (GameScr.findCharInMap(this.id) != null) {
                  GameScr.findCharInMap(this.id).isTeleport = false;
                  if (GameScr.findCharInMap(this.id).statusMe != 14) {
                     GameScr.findCharInMap(this.id).statusMe = 3;
                  }

                  GameScr.findCharInMap(this.id).cvy = -3;
               }

               this.painHead = false;
            }

            if (this.tPrepare > 12 && this.type == 0) {
               if (this.isMe) {
                  Char.myCharz().isTeleport = true;
               } else if (GameScr.findCharInMap(this.id) != null) {
                  GameScr.findCharInMap(this.id).cx = this.x;
                  GameScr.findCharInMap(this.id).cy = this.y;
                  GameScr.findCharInMap(this.id).isTeleport = true;
               }

               this.painHead = true;
            }
         }

         if (this.isMe) {
            if (this.type == 0) {
               GameScr.cmtoX = this.x - GameScr.gW2;
               GameScr.cmtoY = this.y - GameScr.gH23 - 1;
            }

            if (this.type == 1) {
               GameScr.info1.isUpdate = true;
            }
         }

         if (this.y <= -80) {
            if (this.isMe && this.type == 0) {
               Controller.isStopReadMessage = false;
               Char.ischangingMap = true;
            }

            if (!this.isMe && GameScr.findCharInMap(this.id) != null && this.type == 0) {
               GameScr.vCharInMap.removeElement(GameScr.findCharInMap(this.id));
            }

            if (this.planet < 3) {
               vTeleport.removeElement(this);
            } else {
               this.y = -80;
               this.tDelayHole++;
               if (this.tDelayHole > 80) {
                  this.tDelayHole = 0;
                  vTeleport.removeElement(this);
               }
            }
         }
      }

      if (this.paintFire
         && this.planet < 3
         && Res.abs(this.y - this.y2) <= 50
         && GameCanvas.gameTick % 5 == 0) {
         EffecMn.addEff(new Effect(19, this.x, this.y2 + 20, 2, 1, -1));
      }
   }
}
