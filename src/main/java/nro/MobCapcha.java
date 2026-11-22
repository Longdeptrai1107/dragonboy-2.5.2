package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class MobCapcha {
   private static Image imgMob = mSystem.loadImage("/mainImage/myTexture2dmobCapcha.png");
   private static int cmtoY;
   private static int cmy;
   private static int cmdy;
   private static int cmvy;
   private static int cmtoX;
   private static int cmx;
   private static int cmdx;
   private static int cmvx;
   public static boolean explode;
   private static int delay = 0;
   private static int tF;
   private static int f;
   private static int dir;
   public static boolean isAttack;

   public static void paint(mGraphics g, int x, int y) {
      if (!isAttack) {
         if (GameCanvas.gameTick % 3 == 0) {
            if (Char.myCharz().cdir == 1) {
               cmtoX = x - 20 - GameScr.cmx;
            }

            if (Char.myCharz().cdir == -1) {
               cmtoX = x + 20 - GameScr.cmx;
            }
         }

         cmtoY = Char.myCharz().cy - 40 - GameScr.cmy;
      } else {
         if (++delay == 5) {
            isAttack = false;
            delay = 0;
         }

         cmtoX = x - GameScr.cmx;
         cmtoY = y - GameScr.cmy;
      }

      if (cmx > x - GameScr.cmx) {
         dir = -1;
      } else {
         dir = 1;
      }

      g.drawImage(GameScr.imgCapcha, ((int) (cmx)), ((int) (cmy - 40)), 3);
      PopUp.paintPopUp(g, cmx - 25, cmy - 70, 50, 20, 16777215, false);
      int y2 = cmy - 65;
      mFont.tahoma_7b_dark.drawString(g, GameScr.gI().keyInput, cmx, y2, 2);
      if (explode) {
         explode = false;
         EffecMn.addEff(new Effect(18, cmx + GameScr.cmx, cmy + GameScr.cmy, 2, 10, -1));
         GameScr.gI().mobCapcha = null;
         cmtoX = -GameScr.cmx;
         cmtoY = -GameScr.cmy;
      }

      g.drawRegion(
         imgMob,
         0,
         f * 40,
         40,
         40,
         dir == 1 ? 0 : 2,
         cmx,
         cmy + 3 + (GameCanvas.gameTick % 10 > 5 ? 1 : 0),
         3
      );
      if (cmy != cmtoY) {
         cmvy = cmtoY - cmy << 2;
         cmdy = cmdy + cmvy;
         cmy = cmy + (cmdy >> 4);
         cmdy &= 15;
      }

      if (cmx != cmtoX) {
         cmvx = cmtoX - cmx << 2;
         cmdx = cmdx + cmvx;
         cmx = cmx + (cmdx >> 4);
         cmdx &= 15;
      }

      if (++tF == 5) {
         tF = 0;
         if (++f > 2) {
            f = 0;
         }
      }
   }
}
