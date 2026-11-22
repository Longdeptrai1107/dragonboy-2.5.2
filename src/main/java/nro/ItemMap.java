package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class ItemMap implements IMapObject {
   public int x;
   public int y;
   private int xEnd;
   private int yEnd;
   private int vx;
   private int vy;
   public int playerId;
   public int itemMapID;
   public ItemTemplate template;
   private byte status;
   private int rO;
   private int xO;
   private int yO;
   private int angle;
   private int iAngle;
   private int iDot;
   private int[] xArg;
   private int[] yArg;
   private int[] xDot;
   private int[] yDot;
   private int count;
   private int countAura;
   public static Image imageFlare = mSystem.loadImage("/mainImage/myTexture2dflare.png");
   private static Image imageAuraItem1 = mSystem.loadImage("/mainImage/myTexture2ditemaura1.png");
   private static Image imageAuraItem2 = mSystem.loadImage("/mainImage/myTexture2ditemaura2.png");
   private static Image imageAuraItem3 = mSystem.loadImage("/mainImage/myTexture2ditemaura3.png");

   public ItemMap(short itemMapIDValue, short itemTemplateID, int xValue, int yValue, int xEndValue, int yEndValue) {
      this.itemMapID = itemMapIDValue;
      this.template = ItemTemplates.get(itemTemplateID);
      this.x = xEndValue;
      this.y = yValue;
      this.xEnd = xEndValue;
      this.yEnd = yEndValue;
      this.vx = xEndValue - xValue >> 2;
      this.vy = 5;
   }

   public ItemMap(int playerIdValue, short itemMapIDValue, short itemTemplateID, int xValue, int yValue, short rOValue) {
      this.itemMapID = itemMapIDValue;
      this.template = ItemTemplates.get(itemTemplateID);
      this.x = this.xEnd = xValue;
      this.y = this.yEnd = yValue;
      this.status = 1;
      this.playerId = playerIdValue;
      if (this.isAuraItem()) {
         this.rO = rOValue;
         ItemMap itemMap = this;
         this.xO = itemMap.x;
         itemMap.yO = itemMap.y;
         itemMap.iDot = 120;
         itemMap.angle = 0;
         if (!GameCanvas.lowGraphic) {
            itemMap.iAngle = 360 / itemMap.iDot;
            itemMap.xArg = new int[itemMap.iDot];
            itemMap.yArg = new int[itemMap.iDot];
            itemMap.xDot = new int[itemMap.iDot];
            itemMap.yDot = new int[itemMap.iDot];
            itemMap.setDotPosition();
         }
      }
   }

   public final void setPoint(int xEnd, int yEnd) {
      this.xEnd = xEnd;
      this.yEnd = yEnd;
      this.vx = xEnd - this.x >> 2;
      this.vy = yEnd - this.y >> 2;
      this.status = 2;
   }

   public final void update() {
      if (this.status == 2 && this.x == this.xEnd && this.y == this.yEnd) {
         GameScr.vItemMap.removeElement(this);
         if (Char.myCharz().itemFocus != null && Char.myCharz().itemFocus.equals(this)) {
            Char.myCharz().itemFocus = null;
         }
      } else {
         if (this.status > 0) {
            if (this.vx == 0) {
               this.x = this.xEnd;
            }

            if (this.vy == 0) {
               this.y = this.yEnd;
            }

            if (this.x != this.xEnd) {
               this.x = this.x + this.vx;
               if (this.vx > 0 && this.x > this.xEnd
                  || this.vx < 0 && this.x < this.xEnd) {
                  this.x = this.xEnd;
               }
            }

            if (this.y != this.yEnd) {
               this.y = this.y + this.vy;
               if (this.vy > 0 && this.y > this.yEnd
                  || this.vy < 0 && this.y < this.yEnd) {
                  this.y = this.yEnd;
               }
            }
         } else {
            this.status = (byte)(this.status - 4);
            if (this.status < -12) {
               this.y -= 12;
               this.status = 1;
            }
         }

         if (this.isAuraItem()) {
            ItemMap itemMap = this;
            this.count++;
            itemMap.countAura++;
            if (itemMap.countAura >= 40) {
               itemMap.countAura = 0;
            }

            if (itemMap.count >= itemMap.iDot) {
               itemMap.count = 0;
            }

            if (itemMap.count % 10 == 0 && !GameCanvas.lowGraphic) {
               ServerEffect.addServerEffect(114, itemMap.x - 5, itemMap.y - 30, 1);
            }
         }
      }
   }

   public final void paint(mGraphics g) {
      if (this.isAuraItem()) {
         g.drawImage(TileMap.bong, ((int) (this.x + 3)), ((int) (this.y)), 3);
         if (this.status <= 0) {
            if (this.countAura < 10) {
               g.drawImage(imageAuraItem1, ((int) (this.x)), ((int) (this.y + this.status + 3)), 33);
            } else {
               g.drawImage(imageAuraItem2, ((int) (this.x)), ((int) (this.y + this.status + 3)), 33);
            }
         } else if (this.countAura < 10) {
            g.drawImage(imageAuraItem1, ((int) (this.x)), ((int) (this.y + 3)), 33);
         } else {
            g.drawImage(imageAuraItem2, ((int) (this.x)), ((int) (this.y + 3)), 33);
         }

         mGraphics g2 = g;
         ItemMap itemMap = this;
         if (!GameCanvas.lowGraphic && itemMap.isAuraItem()) {
            for (int xDotIndex = 0; xDotIndex < itemMap.yArg.length; xDotIndex++) {
               if (itemMap.count == xDotIndex) {
                  if (itemMap.countAura <= 20) {
                     g2.drawImage(imageAuraItem3, ((int) (itemMap.xDot[xDotIndex])), ((int) (itemMap.yDot[xDotIndex] + 3)), 33);
                  } else {
                     SmallImage.drawSmallImage(
                        g2, itemMap.template.iconID, itemMap.xDot[xDotIndex], itemMap.yDot[xDotIndex] + 3, 0, 33
                     );
                  }
               }
            }
         }
      } else {
         if (!this.isAuraItem()) {
            if (GameCanvas.gameTick % 4 == 0) {
               g.drawImage(imageFlare, ((int) (this.x)), ((int) (this.y + this.status + 13)), 33);
            }

            if (this.status <= 0) {
               SmallImage.drawSmallImage(
                  g, this.template.iconID, this.x, this.y + this.status + 3, 0, 33
               );
            } else {
               SmallImage.drawSmallImage(g, this.template.iconID, this.x, this.y + 3, 0, 33);
            }

            if (Char.myCharz().itemFocus != null && Char.myCharz().itemFocus.equals(this) && this.status != 2) {
               g.drawRegion(Mob.imgHP, 0, 24, 9, 6, 0, this.x, this.y - 17, 3);
            }
         }
      }
   }

   private boolean isAuraItem() {
      boolean result;
      if (this.template.type == 22) {
         result = true;
      } else {
         result = false;
      }

      return result;
   }

   private void setDotPosition() {
      if (!GameCanvas.lowGraphic) {
         for (int xArgIndex = 0; xArgIndex < this.yArg.length; xArgIndex++) {
            this.yArg[xArgIndex] = Res.abs(this.rO * Res.sin(this.angle) / 1024);
            this.xArg[xArgIndex] = Res.abs(this.rO * Res.cos(this.angle) / 1024);
            if (this.angle < 90) {
               this.xDot[xArgIndex] = this.xO + this.xArg[xArgIndex];
               this.yDot[xArgIndex] = this.yO - this.yArg[xArgIndex];
            } else if (this.angle >= 90 && this.angle < 180) {
               this.xDot[xArgIndex] = this.xO - this.xArg[xArgIndex];
               this.yDot[xArgIndex] = this.yO - this.yArg[xArgIndex];
            } else if (this.angle >= 180 && this.angle < 270) {
               this.xDot[xArgIndex] = this.xO - this.xArg[xArgIndex];
               this.yDot[xArgIndex] = this.yO + this.yArg[xArgIndex];
            } else {
               this.xDot[xArgIndex] = this.xO + this.xArg[xArgIndex];
               this.yDot[xArgIndex] = this.yO + this.yArg[xArgIndex];
            }

            this.angle = this.angle + this.iAngle;
         }
      }
   }

   public final int getX() {
      return this.x;
   }

   public final int getY() {
      return this.y;
   }

   public final int getH() {
      return 20;
   }

   public final int getW() {
      return 20;
   }

   public final void stopMoving() {
   }

   public final boolean isInvisible() {
      return false;
   }
}
