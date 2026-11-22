package nro;

import main.GameCanvas;

public final class PlayerDart {
   private Char charBelong;
   private DartInfo info;
   private MyVector darts = new MyVector("vDARTS");
   private int angle;
   private int vx;
   private int vy;
   private int va;
   private int x;
   private int y;
   private int life;
   private int dx = 0;
   private int dy = 0;
   private boolean isSpeedUp = false;
   private boolean isActive = true;
   public SkillPaint skillPaint;

   public PlayerDart(Char charBelongValue, int dartType, SkillPaint sp, int xValue, int yValue) {
      this.skillPaint = sp;
      this.charBelong = charBelongValue;
      this.info = GameScr.darts[dartType];
      this.va = this.info.va;
      this.x = xValue;
      this.y = yValue;
      IMapObject iMapObject = charBelongValue.mobFocus == null ? (nro.IMapObject) charBelongValue.charFocus : charBelongValue.mobFocus;
      dartType = Res.angle(iMapObject.getX() - xValue, iMapObject.getY() - yValue);
      PlayerDart playerDart = this;
      this.angle = dartType;
      playerDart.vx = playerDart.va * Res.cos(dartType) >> 10;
      playerDart.vy = playerDart.va * Res.sin(dartType) >> 10;
   }

   public final void update() {
      if (this.isActive) {
         if (this.charBelong.mobFocus == null && this.charBelong.charFocus == null) {
            this.endMe();
         } else {
            IMapObject iMapObject = this.charBelong.mobFocus == null
               ? (nro.IMapObject) this.charBelong.charFocus
               : this.charBelong.mobFocus;

            for (int dartIndex = 0; dartIndex < this.info.nUpdate; dartIndex++) {
               if (this.info.tail.length > 0) {
                  this.darts.addElement(new SmallDart(this.x, this.y));
               }

               int angleValue = this.charBelong.getX() > iMapObject.getX() ? 10 : -10;
               this.dx = iMapObject.getX() + angleValue - this.x;
               this.dy = iMapObject.getY() - iMapObject.getH() / 2 - this.y;
               this.life++;
               if (Res.abs(this.dx) < 20 && Res.abs(this.dy) < 20) {
                  if (this.charBelong.charFocus != null && this.charBelong.charFocus.me) {
                     this.charBelong
                        .charFocus
                        .doInjure(
                           this.charBelong.charFocus.damHP,
                           0L,
                           this.charBelong.charFocus.isCrit,
                           this.charBelong.charFocus.isMob
                        );
                  }

                  this.endMe();
                  return;
               }

               if (Math.abs((angleValue = Res.angle(this.dx, this.dy)) - this.angle) < 90
                  || this.dx * this.dx + this.dy * this.dy > 4096) {
                  if (Math.abs(angleValue - this.angle) < 15) {
                     this.angle = angleValue;
                  } else if ((angleValue - this.angle < 0 || angleValue - this.angle >= 180) && angleValue - this.angle >= -180) {
                     this.angle = Res.fixangle(this.angle - 15);
                  } else {
                     this.angle = Res.fixangle(this.angle + 15);
                  }
               }

               if (this.va < 8192) {
                  this.va += 1024;
               }

               this.vx = this.va * Res.cos(this.angle) >> 10;
               this.vy = this.va * Res.sin(this.angle) >> 10;
               this.dx = this.dx + this.vx;
               angleValue = this.dx >> 10;
               this.x += angleValue;
               this.dx &= 1023;
               this.dy = this.dy + this.vy;
               angleValue = this.dy >> 10;
               this.y += angleValue;
               this.dy &= 1023;
            }

            for (int dartIndex2 = 0; dartIndex2 < this.darts.size(); dartIndex2++) {
               SmallDart dart;
               (dart = (SmallDart)this.darts.elementAt(dartIndex2)).index++;
               if (dart.index >= this.info.tail.length) {
                  this.darts.removeElementAt(dartIndex2);
               }
            }
         }
      }
   }

   private void endMe() {
      Res.outz("END ME!");
      if (!this.charBelong.isUseSkillAfterCharge
         && this.x >= GameScr.cmx
         && this.x <= GameScr.cmx + GameCanvas.w) {
         SoundMn.gI();
         SoundMn.explode_1();
      }

      this.charBelong.setAttack();
      if (this.charBelong.me) {
         Char.saveLoadPreviousSkill();
      }

      if (this.charBelong.isUseSkillAfterCharge) {
         this.charBelong.isUseSkillAfterCharge = false;
         if (this.charBelong.isLockMove
            && this.charBelong.me
            && this.charBelong.statusMe != 14
            && this.charBelong.statusMe != 5) {
            this.charBelong.isLockMove = false;
         }

         if (this.charBelong.cgender == 2) {
            int skillId;
            if (this.charBelong.me) {
               skillId = Char.myCharz().myskill.skillId;
            } else {
               skillId = this.charBelong.skillTemplateId;
            }

            if (skillId < 77 || skillId > 83) {
               GameScr.gI().activeSuperPower(this.x, this.y);
            }
         } else {
            GameScr.gI().activeSuperPower(this.x, this.y);
         }
      }

      this.charBelong.dart = null;
      this.charBelong.isCreateDark = false;
      this.charBelong.skillPaint = null;
      this.charBelong.skillPaintRandomPaint = null;
      this.charBelong.stopUseChargeSkill();
   }

   public final void paint(mGraphics g) {
      if (this.isActive) {
         int fRAMEIndex = MonsterDart.findDirIndexFromAngle(360 - this.angle);
         byte headBorderIndex = MonsterDart.FRAME[fRAMEIndex];
         int transform = MonsterDart.TRANSFORM[fRAMEIndex];

         for (int dartIndex = this.darts.size() / 2; dartIndex < this.darts.size(); dartIndex++) {
            SmallDart dart = (SmallDart)this.darts.elementAt(dartIndex);
            SmallImage.drawSmallImage(
               g, this.info.tailBorder[dart.index], dart.x, dart.y, 0, 3
            );
         }

         int headBorderIndex2 = GameCanvas.gameTick % this.info.headBorder.length;
         SmallImage.drawSmallImage(g, this.info.headBorder[headBorderIndex2][headBorderIndex], this.x, this.y, transform, 3);

         for (int dartIndex2 = 0; dartIndex2 < this.darts.size(); dartIndex2++) {
            SmallDart dart2 = (SmallDart)this.darts.elementAt(dartIndex2);
            SmallImage.drawSmallImage(
               g, this.info.tail[dart2.index], dart2.x, dart2.y, 0, 3
            );
         }

         SmallImage.drawSmallImage(g, this.info.head[headBorderIndex2][headBorderIndex], this.x, this.y, transform, 3);

         for (int dartIndex3 = 0; dartIndex3 < this.darts.size(); dartIndex3++) {
            SmallDart dart3 = (SmallDart)this.darts.elementAt(dartIndex3);
            if (Res.abs(MonsterDart.r.nextInt(100)) < this.info.xdPercent) {
               SmallImage.drawSmallImage(
                  g,
                  GameCanvas.gameTick % 2 == 0
                     ? this.info.xd1[dart3.index]
                     : this.info.xd2[dart3.index],
                  dart3.x,
                  dart3.y,
                  0,
                  3
               );
            }
         }

         g.setColor(16711680);
      }
   }
}
