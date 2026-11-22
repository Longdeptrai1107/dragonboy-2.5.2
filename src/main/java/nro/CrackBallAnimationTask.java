package nro;

import main.GameCanvas;

final class CrackBallAnimationTask implements Runnable {
   private int openingStage;
   private boolean completionHandled;
   private CrackBallScr screen;

   CrackBallAnimationTask(CrackBallScr screenValue) {
      this.screen = screenValue;
      this.openingStage = 0;
      this.completionHandled = false;
   }

   public final void run() {
      try {
         while (CrackBallScr.isAutoCrackBall) {
            if (GameCanvas.currentScreen != CrackBallScr.instance) {
               return;
            }

            this.screen.indexSkillSelect = 2;

            while (this.openingStage < 7 && this.screen.step != 5) {
               CrackBallScr.dispatchBallClick(this.screen, this.openingStage);
               this.openingStage++;
               Thread.sleep(300L);
            }

            if (this.openingStage == 7) {
               Thread.sleep(800L);
               CrackBallScr.useSkillCrackBall(CrackBallScr.gI());
               Thread.sleep(4000L);
               if (CrackBallScr.isCallStop) {
                  this.screen.stopAutoCrackBall();
                  Thread.yield();
                  return;
               }

               if (this.screen.step == 5) {
                  CrackBallScr.useSkillCrackBall(CrackBallScr.gI());
                  this.openingStage = 0;
               }

               Thread.sleep(1000L);
            }

            if (this.screen.step == 5 && this.openingStage == 0 && !this.completionHandled) {
               this.completionHandled = true;
               CrackBallScr.useSkillCrackBall(CrackBallScr.gI());
            }
         }
      } catch (Exception ignoredException) {
      }
   }
}
