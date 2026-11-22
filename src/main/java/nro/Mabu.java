package nro;

import main.GameCanvas;

public final class Mabu extends Char {
   private static EffectData data1;
   private static EffectData data2;
   private int tick = 0;
   private int lastDir;
   private boolean addFoot;
   private Effect effEat;
   private Char focus;
   private int xTo;
   private int yTo;
   private Char[] charAttack;
   private long[] damageAttack;
   private int dx;
   private int[] skill1 = new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
   private int[] skill2 = new int[]{0, 0, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9, 10, 10};
   private int[] skill3 = new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12};
   private int[] skill4 = new int[]{13, 13, 14, 14, 15, 15, 16, 16};
   private int[][] skills = new int[][]{this.skill1, this.skill2, this.skill3, this.skill4};
   private byte skillID = -1;
   private int frame;
   private int pIndex;

   public final void eat(int id) {
      this.effEat = new Effect(105, super.cx, super.cy + 20, 2, 1, -1);
      EffecMn.addEff(this.effEat);
      if (id == Char.myCharz().charID) {
         this.focus = Char.myCharz();
      } else {
         this.focus = GameScr.findCharInMap(id);
      }
   }

   public final void checkFrameTick(int[] array) {
      if (this.skillID == 0) {
         if (this.tick == 11) {
            this.addFoot = true;
            EffecMn.addEff(new Effect(19, super.cx, super.cy + 20, 2, 1, -1));
         }

         if (this.tick >= array.length - 1) {
            this.skillID = 2;
            return;
         }
      }

      if (this.skillID == 1 && this.tick == array.length - 1) {
         this.skillID = 3;
         super.cy -= 15;
      } else {
         this.tick++;
         if (this.tick > array.length - 1) {
            this.tick = 0;
         }

         this.frame = array[this.tick];
      }
   }

   public Mabu() {
      data1 = null;
      data1 = new EffectData();
      String patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + 102 + "/data";

      try {
         data1.readData2(patch);
         data1.img = mSystem.loadZoomedResourceImage("/effectdata/102/img.png");
      } catch (Exception ignoredException) {
      }

      data2 = null;
      data2 = new EffectData();
      patch = "/x" + mGraphics.zoomLevel + "/effectdata/" + 103 + "/data";

      try {
         data2.readData2(patch);
         data2.img = mSystem.loadZoomedResourceImage("/effectdata/103/img.png");
      } catch (Exception ignoredException2) {
      }
   }

   public final void setSkill(byte id, short x, short y, Char[] charHit, long[] damageHit) {
      this.skillID = id;
      this.xTo = x;
      this.yTo = y;
      this.lastDir = super.cdir;
      super.cdir = this.xTo > super.cx ? 1 : -1;
      this.charAttack = charHit;
      this.damageAttack = damageHit;
   }

   public final void update() {
      if (this.focus != null) {
         if (this.effEat.t >= 30) {
            this.effEat.x = this.effEat.x
               + (super.cx - this.effEat.x) / 4;
            this.effEat.y = this.effEat.y
               + (super.cy - this.effEat.y) / 4;
            this.focus.cx = this.effEat.x;
            this.focus.cy = this.effEat.y;
            this.focus.isMabuHold = true;
         } else {
            this.effEat.trans = this.effEat.x > this.focus.cx ? 1 : 0;
            this.effEat.x = this.effEat.x
               + (this.focus.cx - this.effEat.x) / 3;
            this.effEat.y = this.effEat.y
               + (this.focus.cy - this.effEat.y) / 3;
         }
      }

      if (this.skillID == -1) {
         super.update();
      } else {
         if (this.skillID == 0 && this.addFoot && GameCanvas.gameTick % 2 == 0) {
            this.dx = this.dx + (this.xTo > super.cx ? 30 : -30);
            Effect meValue;
            (meValue = new Effect(103, super.cx + this.dx, super.cy + 20, 2, 1, -1)).trans = this.xTo
                  > super.cx
               ? 0
               : 1;
            EffecMn.addEff(meValue);
            if (super.cdir == 1 && super.cx + this.dx >= this.xTo
               || super.cdir == -1 && super.cx + this.dx <= this.xTo) {
               this.addFoot = false;
               this.skillID = -1;
               this.dx = 0;
               this.tick = 0;
               super.cdir = this.lastDir;

               for (int charAttackIndex = 0; charAttackIndex < this.charAttack.length; charAttackIndex++) {
                  this.charAttack[charAttackIndex].doInjure(this.damageAttack[charAttackIndex], 0L, false, false);
               }
            }
         }

         if (this.skillID == 3) {
            this.xTo = this.charAttack[this.pIndex].cx;
            this.yTo = this.charAttack[this.pIndex].cy;
            super.cx = super.cx + (this.xTo - super.cx) / 3;
            super.cy = super.cy + (this.yTo - super.cy) / 3;
            if (GameCanvas.gameTick % 5 == 0) {
               EffecMn.addEff(new Effect(19, super.cx, super.cy, 2, 1, -1));
            }

            if (Res.abs(super.cx - this.xTo) <= 20
               && Res.abs(super.cy - this.yTo) <= 20) {
               super.cx = this.xTo;
               super.cy = this.yTo;
               this.charAttack[this.pIndex].doInjure(this.damageAttack[this.pIndex], 0L, false, false);
               this.pIndex++;
               if (this.pIndex == this.charAttack.length) {
                  this.skillID = -1;
                  this.pIndex = 0;
               }
            }
         }
      }
   }

   public final void paint(mGraphics g) {
      if (this.skillID == -1) {
         super.paint(g);
      } else {
         this.paintShadow(g);
         g.translate(0, GameCanvas.transY);
         this.checkFrameTick(this.skills[this.skillID]);
         if (this.skillID != 0 && this.skillID != 1) {
            data2.paintFrame(
               g, this.frame, super.cx, super.cy + super.fy, super.cdir == 1 ? 0 : 1, 2
            );
         } else {
            data1.paintFrame(
               g, this.frame, super.cx, super.cy + super.fy, super.cdir == 1 ? 0 : 1, 2
            );
         }

         g.translate(0, -GameCanvas.transY);
      }
   }
}
