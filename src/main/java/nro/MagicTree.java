package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class MagicTree extends Npc implements IActionListener {
   private static Image pea = mSystem.loadImage("/mainImage/myTexture2dhatdau.png");
   public int id;
   public int level;
   public int x;
   public int y;
   public int currPeas;
   public int remainPeas;
   public int maxPeas;
   public String strInfo;
   public String name;
   public int timeToRecieve;
   public boolean isUpdate;
   public int[] peaPostionX;
   public int[] peaPostionY;
   private PopUp p;
   public boolean isUpdateTree;
   public static boolean isPaint = true;
   public boolean isPeasEffect;
   public int seconds;
   public long last;
   public long cur;
   private boolean waitToUpdate;
   private int delay = 0;

   public MagicTree(int npcId, int status, int cxValue, int cyValue, int templateId, int iconId) {
      super(npcId, status, cxValue, cyValue, templateId, iconId);
      this.p = new PopUp("", 0, 0);
      this.p.command = new Command(null, this, 1, null);
      PopUp.addPopUp(this.p);
   }

   public final void paint(mGraphics g) {
      if (this.id != 0) {
         SmallImage.drawSmallImage(g, this.id, super.cx, super.cy, 0, StaticObj.BOTTOM_HCENTER);
         if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
            g.drawRegion(
               Mob.imgHP,
               0,
               0,
               9,
               6,
               0,
               super.cx,
               super.cy - SmallImage.smallImg[this.id][4] - 1,
               33
            );
            if (this.name != null) {
               mFont.tahoma_7_yellow
                  .drawString(
                     g,
                     this.name,
                     super.cx,
                     super.cy - SmallImage.smallImg[this.id][4] - 20,
                     2,
                     mFont.tahoma_7_grey
                  );
            }
         } else if (this.name != null) {
            mFont.tahoma_7_yellow
               .drawString(
                  g,
                  this.name,
                  super.cx,
                  super.cy - SmallImage.smallImg[this.id][4] - 17,
                  2,
                  mFont.tahoma_7_grey
               );
         }

         try {
            for (int peaPostionXIndex = 0; peaPostionXIndex < this.currPeas; peaPostionXIndex++) {
               g.drawImage(
                  pea,
                  ((int) (super.cx + this.peaPostionX[peaPostionXIndex] - SmallImage.smallImg[this.id][3] / 2)),
                  ((int) (super.cy + this.peaPostionY[peaPostionXIndex] - SmallImage.smallImg[this.id][4])),
                  0
               );
            }
         } catch (Exception ignoredException) {
         }

         if (super.indexEffTask >= 0 && super.effTask != null && super.cTypePk == 0) {
            SmallImage.drawSmallImage(
               g,
               super.effTask.arrEfInfo[super.indexEffTask].idImg,
               super.cx + super.effTask.arrEfInfo[super.indexEffTask].dx,
               super.cy - 15 + super.effTask.arrEfInfo[super.indexEffTask].dy,
               0,
               3
            );
            if (GameCanvas.gameTick % 2 == 0) {
               super.indexEffTask++;
               if (super.indexEffTask >= super.effTask.arrEfInfo.length) {
                  super.indexEffTask = 0;
               }
            }
         }
      }
   }

   public final void update() {
      this.p.isPaint = isPaint;
      this.cur = System.currentTimeMillis();
      if (this.cur - this.last >= 1000L) {
         this.seconds--;
         this.last = this.cur;
         if (this.seconds < 0) {
            this.seconds = 0;
         }
      }

      if (!this.isUpdate) {
         if (this.currPeas < this.maxPeas && this.seconds == 0) {
            this.waitToUpdate = true;
         }
      } else if (this.seconds == 0) {
         this.isUpdate = false;
         this.waitToUpdate = true;
      }

      if (this.waitToUpdate) {
         this.delay++;
         if (this.delay == 20) {
            this.delay = 0;
            this.waitToUpdate = false;
            Service.gI().getMagicTree((byte)2);
         }
      }

      if (this.peaPostionX != null) {
         ;
      }

      if (this.isUpdateTree) {
         this.isUpdateTree = false;
         if ((this.seconds < 0 || this.currPeas >= this.maxPeas)
            && (this.seconds < 0 || !this.isUpdate)
            && !this.isPeasEffect) {
            if (this.currPeas == this.maxPeas && !this.isUpdate) {
               this.p
                  .updateXYWH(
                     new String[]{mResources.can_harvest, this.currPeas + "/" + this.maxPeas},
                     super.cx,
                     super.cy - 20 - SmallImage.smallImg[this.id][4]
                  );
            }
         } else {
            this.p
               .updateXYWH(
                  new String[]{
                     !this.isUpdate ? this.currPeas + "/" + this.maxPeas : mResources.UPGRADING,
                     Firework.formatCountdownDuration(this.seconds)
                  },
                  super.cx,
                  super.cy - 20 - SmallImage.smallImg[this.id][4]
               );
         }
      }

      if (this.seconds >= 0 && this.currPeas < this.maxPeas || this.seconds >= 0 && this.isUpdate) {
         this.p.says[this.p.says.length - 1] = Firework.formatCountdownDuration(this.seconds);
      }

      if (this.isPeasEffect) {
         this.p.isPaint = false;
         ServerEffect.addServerEffect(
            98,
            super.cx + this.peaPostionX[this.currPeas - 1] - SmallImage.smallImg[this.id][3] / 2,
            super.cy + this.peaPostionY[this.currPeas - 1] - SmallImage.smallImg[this.id][4],
            1
         );
         this.currPeas--;
         if (GameCanvas.gameTick % 2 == 0) {
            SoundMn.gI();
            SoundMn.HP_MPup();
         }

         if (this.currPeas == this.remainPeas) {
            this.p.isPaint = true;
            this.isUpdateTree = true;
            this.isPeasEffect = false;
         }
      }

      super.update();
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 1) {
         Service.gI().magicTree((byte)1);
      }
   }
}
