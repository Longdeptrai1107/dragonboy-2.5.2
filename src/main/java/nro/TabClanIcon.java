package nro;

import main.GameCanvas;

public final class TabClanIcon implements IActionListener {
   int x;
   private int y;
   int w;
   private int h;
   Command left;
   Command right;
   private int WIDTH = 24;
   public int nItem = 0;
   private int disStart = 50;
   public static Scroll scrMain;
   public int cmtoX;
   public int cmx;
   public int cmvx;
   public int cmdx;
   public boolean isShow;
   public boolean isGetName;
   public String text;
   private MyVector vItems = new MyVector("vItems");
   int select;
   int lastSelect;

   public final void show(boolean isGetName) {
      this.isShow = true;
      this.isGetName = false;
      TabClanIcon tabClanIcon = this;
      if (this.isGetName) {
         tabClanIcon.w = 170;
         tabClanIcon.h = 118;
         tabClanIcon.x = GameCanvas.w / 2 - tabClanIcon.w / 2;
         tabClanIcon.y = GameCanvas.h / 2 - tabClanIcon.h / 2;
      } else {
         tabClanIcon.w = 170;
         tabClanIcon.h = 170;
         tabClanIcon.x = GameCanvas.w / 2 - tabClanIcon.w / 2;
         tabClanIcon.y = GameCanvas.h / 2 - tabClanIcon.h / 2;
         if (GameCanvas.h < 240) {
            tabClanIcon.y -= 10;
         }
      }

      tabClanIcon.cmx = tabClanIcon.x;
      tabClanIcon.cmtoX = 0;
      tabClanIcon.nItem = ClanImage.vClanImage.size();
      if (GameCanvas.isTouch) {
         tabClanIcon.left.x = tabClanIcon.x;
         tabClanIcon.left.y = tabClanIcon.y + tabClanIcon.h + 5;
         tabClanIcon.right.x = tabClanIcon.x + tabClanIcon.w - 68;
         tabClanIcon.right.y = tabClanIcon.y + tabClanIcon.h + 5;
      }

      (scrMain = new Scroll())
         .setStyle(
            tabClanIcon.nItem,
            tabClanIcon.WIDTH,
            tabClanIcon.x,
            tabClanIcon.y + tabClanIcon.disStart,
            tabClanIcon.w,
            tabClanIcon.h - tabClanIcon.disStart,
            true,
            1
         );
   }

   private void hide() {
      this.cmtoX = this.x + this.w;
      SmallImage.clearHastable();
   }

   public TabClanIcon() {
      this.left = new Command(mResources.SELECT, this, 1, null);
      this.right = new Command(mResources.CLOSE, this, 2, null);
   }

   public final void paintIcon(mGraphics g) {
      g.translate(-this.cmx, 0);
      PopUp.paintPopUp(g, this.x, this.y - 17, this.w, this.h + 17, -1, true);
      mFont.tahoma_7b_dark
         .drawString(g, mResources.select_clan_icon, this.x + this.w / 2, this.y - 7, 2);
      ClanImage clanImage;
      if (this.lastSelect >= 0
         && this.lastSelect <= ClanImage.vClanImage.size() - 1
         && (clanImage = (ClanImage)ClanImage.vClanImage.elementAt(this.lastSelect)).idImage != null) {
         Char.myCharz().paintBag(g, clanImage.idImage, GameCanvas.w / 2, this.y + 45, 1, false);
      }

      Char.myCharz()
         .paintCharBody(g, GameCanvas.w / 2, this.y + 45, 1, Char.myCharz().cf, false);
      g.setClip(
         this.x, this.y + this.disStart, this.w, this.h - this.disStart - 10
      );
      if (scrMain != null) {
         g.translate(0, -scrMain.cmy);
      }

      for (int clanImageIndex = 0; clanImageIndex < this.nItem; clanImageIndex++) {
         int xValue = this.x + 10;
         int yValue;
         if ((yValue = this.y + clanImageIndex * this.WIDTH + this.disStart)
                  + this.WIDTH
                  - (scrMain != null ? scrMain.cmy : 0)
               >= this.y + this.disStart
            && yValue - (scrMain != null ? scrMain.cmy : 0)
               <= this.y + this.disStart + this.h) {
            ClanImage clanImage2 = (ClanImage)ClanImage.vClanImage.elementAt(clanImageIndex);
            mFont tahoma_7_blue = mFont.tahoma_7_grey;
            if (clanImageIndex == this.lastSelect) {
               tahoma_7_blue = mFont.tahoma_7_blue;
            }

            if (clanImage2.name != null) {
               tahoma_7_blue.drawString(g, clanImage2.name, xValue + 20, yValue, 0);
            }

            if (clanImage2.xu > 0) {
               tahoma_7_blue.drawString(g, clanImage2.xu + " " + mResources.XU, xValue + this.w - 20, yValue, 1);
            } else if (clanImage2.luong > 0) {
               tahoma_7_blue.drawString(g, clanImage2.luong + " " + mResources.LUONG, xValue + this.w - 20, yValue, 1);
            }

            if (clanImage2.idImage != null) {
               SmallImage.drawSmallImage(g, clanImage2.idImage[0], xValue, yValue, 0, 0);
            }
         }
      }

      g.translate(0, -g.getTranslateY());
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      Paint.paintCmdBar(g, this.left, null, this.right);
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 2) {
         this.hide();
      }

      if (idAction == 1 && !this.isGetName && this.lastSelect >= 0) {
         this.hide();
         if (Char.myCharz().clan == null) {
            Service.gI()
               .getClan((byte)2, ((ClanImage)ClanImage.vClanImage.elementAt(this.lastSelect)).ID, this.text);
            return;
         }

         Service.gI()
            .getClan((byte)4, ((ClanImage)ClanImage.vClanImage.elementAt(this.lastSelect)).ID, "");
      }
   }
}
