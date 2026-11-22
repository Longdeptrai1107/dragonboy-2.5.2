package main;

import javax.microedition.midlet.MIDlet;
import nro.Controller;
import nro.Session_ME;
import nro.MotherCanvas;
import nro.SplashScr;

public class GameMidlet extends MIDlet {
   public static String IP = "112.213.94.23";
   public static int PORT = 14445;
   public static String IP2;
   public static int PORT2;
   public static int LANGUAGE;
   public static int intVERSION = 252;
   private static GameCanvas gameCanvas;
   public static GameMidlet instance;
   private static boolean hasStarted;
   public static boolean isConnect2;

   public GameMidlet() {
      instance = this;
   }

   protected void destroyApp(boolean condition) {
   }

   protected void pauseApp() {
   }

   public void startApp() {
      if (!hasStarted) {
         (gameCanvas = new GameCanvas()).startCanvasThread();
         Session_ME.gI().setHandler(Controller.gI());
         Session_ME.gI2().setHandler(Controller.gI());
         Session_ME.gI2().isMainSession = false;
         SplashScr.loadImg();
         SplashScr.loadSplashScr();
         GameCanvas.currentScreen = new SplashScr();
         MotherCanvas.showGameCanvas(instance);
         hasStarted = true;
      }
   }

   public final void exit() {
      MotherCanvas.isCanvasThreadRunning = false;
      System.gc();
      this.notifyDestroyed();
   }
}
