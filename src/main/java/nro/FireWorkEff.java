package nro;

import java.util.Random;
import java.util.Vector;
import main.GameCanvas;

public final class FireWorkEff {
   private static Random r = new Random();
   private static Vector mg = new Vector();
   private static int x;
   private static int y;
   private static int ag;
   private static int x0;
   private static int y0;
   private static int t;
   private static int v;
   private static float a;
   private static int[] mang_x = new int[3];
   private static int[] mang_y = new int[3];
   private static boolean st = false;
   private static long last = 0L;
   private static long delay = 150L;

   public static void paint(mGraphics graphics) {
      if (st) {
         mang_y[2] = mang_y[1];
         mang_x[2] = mang_x[1];
         mang_y[1] = mang_y[0];
         mang_x[1] = mang_x[0];
         mang_y[0] = y;
         mang_x[0] = x;
         x = (int)(Math.cos(ag * Math.PI / 180.0) * v * t) + x0;
         y = (int)(
            v * Math.sin(ag * Math.PI / 180.0) * t - 0.0F * t * t / 2.0F
         );
         if (System.currentTimeMillis() - last >= delay) {
            t++;
            last = System.currentTimeMillis();
         }
      }

      if (t > 32 && st) {
         st = false;
         mg.removeAllElements();
         mg.addElement(
            new Controller2(
               Res.random(50, GameCanvas.w - 50),
               Res.random(GameCanvas.h - 100, GameCanvas.h),
               5,
               72
            )
         );
      }

      graphics.setColor(0);
      graphics.fillRect(0, 0, 0, 0);
      graphics.setColor(16711680);

      for (int mgIndex = 0; mgIndex < mg.size(); mgIndex++) {
         Controller2 mgValue = (Controller2)mg.elementAt(mgIndex);
         mGraphics graphics2 = graphics;
         Controller2 mgValue2 = mgValue;

         for (int fwIndex = 0; fwIndex < mgValue2.fw.size(); fwIndex++) {
            Firework fw;
            if ((fw = (Firework)mgValue2.fw.elementAt(fwIndex)).y < -200) {
               mgValue2.fw.removeElementAt(fwIndex);
            }

            mGraphics graphics3 = graphics2;
            Firework fw2 = fw;
            Firework.Drawline(
               graphics3, fw2.w - fw2.x, fw2.h - fw2.y, fw2.cl
            );

            for (int arr_xIndex = 0; arr_xIndex < 2; arr_xIndex++) {
               Firework.Drawline(
                  graphics3, fw2.w - fw2.arr_x[arr_xIndex], fw2.h - fw2.arr_y[arr_xIndex], fw2.cl
               );
            }

            if (fw2.act) {
               fw = fw2;
               if (System.currentTimeMillis() - fw.last >= fw.delay) {
                  fw.t++;
                  fw.last = System.currentTimeMillis();
                  fw.arr_x[1] = fw.arr_x[0];
                  fw.arr_y[1] = fw.arr_y[0];
                  fw.arr_x[0] = fw.x;
                  fw.arr_y[0] = fw.y;
                  fw.x = (int)(Math.cos(fw.angle * Math.PI / 180.0) * fw.v * fw.t)
                     + fw.x0;
                  fw.y = (int)(
                        fw.v * Math.sin(fw.angle * Math.PI / 180.0) * fw.t
                           - fw.a * fw.t * fw.t / 2.0F
                     )
                     + fw.y0;
               }
            }
         }
      }

      if (!st) {
         int value;
         if ((value = -(Math.abs(r.nextInt() % 3) + 5)) == -5 && !st) {
            x0 = 0;
            ag = 80;
            st = true;
            add();
            return;
         }

         if (value == -7 && !st) {
            ag = 60;
            x0 = 0;
            st = true;
            add();
            return;
         }

         if (value == -6 && !st) {
            ag = 120;
            x0 = 0;
            st = true;
            add();
         }
      }
   }

   private static void add() {
      y0 = 0;
      v = 16;
      t = 0;
      a = 0.0F;

      for (int mang_xIndex = 0; mang_xIndex < 3; mang_xIndex++) {
         mang_y[mang_xIndex] = 0;
         mang_x[mang_xIndex] = x0;
      }

      st = true;
   }
}
