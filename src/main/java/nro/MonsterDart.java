package nro;

import java.util.Random;
import main.GameCanvas;

public final class MonsterDart extends Effect2 {
   private int va;
   private DartInfo info;
   public static Random r = new Random(System.currentTimeMillis());
   private int angle;
   private int vx;
   private int vy;
   private int x;
   private int y;
   private int life;
   private boolean field_l_88d6ef8c = false;
   private long dame;
   private long dameMp;
   private Char c;
   private MyVector darts = new MyVector("vDarts");
   private int dx = 0;
   private int dy = 0;
   private static int[] ARROWINDEX = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
   public static final int[] TRANSFORM = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
   public static final byte[] FRAME = new byte[]{0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};

   public static void addMonsterDart(int x, int y, boolean isBoss, long dame, long dameMp, Char c, int dartType) {
      Effect2.vEffect2.addElement(new MonsterDart(x, y, isBoss, dame, dameMp, c, dartType));
   }

   private MonsterDart(int xValue, int yValue, boolean isBoss, long dameValue, long dameMpValue, Char cValue, int dartType) {
      this.info = GameScr.darts[dartType];
      this.x = xValue;
      this.y = yValue;
      this.dame = dameValue;
      this.dameMp = dameMpValue;
      this.c = cValue;
      this.va = this.info.va;
      int intSlot3 = Res.angle(cValue.cx - xValue, cValue.cy - yValue);
      MonsterDart monsterDart = this;
      this.angle = intSlot3;
      monsterDart.vx = monsterDart.va * Res.cos(intSlot3) >> 10;
      monsterDart.vy = monsterDart.va * Res.sin(intSlot3) >> 10;
      if (xValue >= GameScr.cmx && xValue <= GameScr.cmx + GameCanvas.w) {
         SoundMn.gI();
         SoundMn.mobKame();
      }
   }

   public final void update() {
      for (int dartIndex = 0; dartIndex < this.info.nUpdate; dartIndex++) {
         if (this.info.tail.length > 0) {
            this.darts.addElement(new SmallDart(this.x, this.y));
         }

         this.dx = (this.c != null ? this.c.cx : 0) - this.x;
         this.dy = (this.c != null ? this.c.cy : 0) - 10 - this.y;
         int angleValue = 60;
         if (TileMap.mapID == 0) {
            angleValue = 600;
         }

         this.life++;
         if (this.c != null && (this.c.statusMe == 5 || this.c.statusMe == 14)
            || this.c == null) {
            this.x = this.x
               + ((this.c != null ? this.c.cx : 0) - this.x) / 2;
            this.y = this.y
               + ((this.c != null ? this.c.cy : 0) - this.y) / 2;
         }

         if (Res.abs(this.dx) < 16 && Res.abs(this.dy) < 16
            || this.life > angleValue) {
            if (this.c != null && this.c.charID >= 0 && this.dameMp != -1L) {
               if (this.dameMp != -100L) {
                  this.c.doInjure(this.dame, this.dameMp, false, true);
               } else {
                  ServerEffect.addServerEffect(80, ((nro.Char) (this.c)), 1);
               }
            }

            Effect2.vEffect2.removeElement(this);
            if (this.dameMp != -100L) {
               ServerEffect.addServerEffect(81, ((nro.Char) (this.c)), 1);
               if (this.x >= GameScr.cmx && this.x <= GameScr.cmx + GameCanvas.w) {
                  SoundMn.gI();
                  SoundMn.explode_2();
               }
            }
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

   public static int findDirIndexFromAngle(int angle) {
      for (int aRROWINDEXIndex = 0; aRROWINDEXIndex < ARROWINDEX.length - 1; aRROWINDEXIndex++) {
         if (angle >= ARROWINDEX[aRROWINDEXIndex] && angle <= ARROWINDEX[aRROWINDEXIndex + 1]) {
            if (aRROWINDEXIndex >= 16) {
               return 0;
            }

            return aRROWINDEXIndex;
         }
      }

      return 0;
   }

   public final void paint(mGraphics g) {
      int fRAMEIndex = findDirIndexFromAngle(360 - this.angle);
      byte headBorderIndex = FRAME[fRAMEIndex];
      int transform = TRANSFORM[fRAMEIndex];

      for (int dartIndex = this.darts.size() / 2; dartIndex < this.darts.size(); dartIndex++) {
         SmallDart dart = (SmallDart)this.darts.elementAt(dartIndex);
         SmallImage.drawSmallImage(g, this.info.tailBorder[dart.index], dart.x, dart.y, 0, 3);
      }

      int headBorderIndex2 = GameCanvas.gameTick % this.info.headBorder.length;
      SmallImage.drawSmallImage(g, this.info.headBorder[headBorderIndex2][headBorderIndex], this.x, this.y, transform, 3);

      for (int dartIndex2 = 0; dartIndex2 < this.darts.size(); dartIndex2++) {
         SmallDart dart2 = (SmallDart)this.darts.elementAt(dartIndex2);
         SmallImage.drawSmallImage(g, this.info.tail[dart2.index], dart2.x, dart2.y, 0, 3);
      }

      SmallImage.drawSmallImage(g, this.info.head[headBorderIndex2][headBorderIndex], this.x, this.y, transform, 3);

      for (int dartIndex3 = 0; dartIndex3 < this.darts.size(); dartIndex3++) {
         SmallDart dart3 = (SmallDart)this.darts.elementAt(dartIndex3);
         if (Res.abs(r.nextInt(100)) < this.info.xdPercent) {
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
   }
}
