package nro;

import main.GameCanvas;

public final class ChooseCharScr extends mScreen implements IActionListener {
   private Command[] vc_players;
   public static PlayerData[] playerData;
   private int cf;
   private int[] cx = new int[]{GameCanvas.w / 2 - 100, GameCanvas.w / 2 - 100};
   private int focus = 0;
   private int[] cy = new int[2];
   private int[] rectPanel = new int[]{GameCanvas.w / 2 - 150, GameCanvas.h / 2 - 100, 300, 200};
   private int offsetY = -35;
   private int offsetX = -35;

   public final void switchToMe() {
      ServerListScreen.isWait = false;
      Char.isLoadingMap = false;
      LoginScr.isContinueToLogin = false;
      ServerListScreen.waitToLogin = false;
      GameScr.gI().readPart();
      super.switchToMe();
   }

   public final void update() {
      if (GameCanvas.gameTick % 10 > 2) {
         this.cf = 1;
      } else {
         this.cf = 0;
      }

      for (int vc_playerIndex = 0; vc_playerIndex < this.vc_players.length; vc_playerIndex++) {
         if (this.vc_players[vc_playerIndex].isPointerPressInside()) {
            this.vc_players[vc_playerIndex].performAction();
         }
      }

      for (int focusValue = 0; focusValue < this.cx.length; focusValue++) {
         if (GameCanvas.isPointerHoldIn(
            this.cx[focusValue] + this.offsetX, this.cy[focusValue] + this.offsetY, this.rectPanel[2], 60
         )) {
            if (GameCanvas.isPointerDown) {
               this.focus = focusValue;
               break;
            }

            if (GameCanvas.isPointerJustRelease) {
               ;
            }
         }
      }

      super.update();
   }

   public final void paint(mGraphics g) {
      GameCanvas.paintBGGameScr(g);

      try {
         PopUp.paintPopUp(
            g, this.rectPanel[0] - 10, this.rectPanel[1], this.rectPanel[2] + 20, this.rectPanel[3], 16777215, true
         );
         if (this.vc_players != null) {
            for (int vc_playerIndex = 0; vc_playerIndex < this.vc_players.length; vc_playerIndex++) {
               this.vc_players[vc_playerIndex].paint(g);
            }
         }

         if (playerData != null) {
            for (int cxIndex = 0; cxIndex < playerData.length; cxIndex++) {
               PopUp.paintPopUp(
                  g, this.cx[cxIndex] - 20, this.cy[cxIndex] + this.offsetY, this.rectPanel[2], 60, 16777215, false
               );
               Part part = GameScr.parts[playerData[cxIndex].head];
               Part part2 = GameScr.parts[playerData[cxIndex].leg];
               Part part3 = GameScr.parts[playerData[cxIndex].body];
               SmallImage.drawSmallImage(
                  g,
                  part.pi[Char.CharInfo[this.cf][0][0]].id,
                  this.cx[cxIndex]
                     + Char.CharInfo[this.cf][0][1]
                     + part.pi[Char.CharInfo[this.cf][0][0]].dx,
                  this.cy[cxIndex]
                     - Char.CharInfo[this.cf][0][2]
                     + part.pi[Char.CharInfo[this.cf][0][0]].dy,
                  0,
                  0
               );
               SmallImage.drawSmallImage(
                  g,
                  part2.pi[Char.CharInfo[this.cf][1][0]].id,
                  this.cx[cxIndex]
                     + Char.CharInfo[this.cf][1][1]
                     + part2.pi[Char.CharInfo[this.cf][1][0]].dx,
                  this.cy[cxIndex]
                     - Char.CharInfo[this.cf][1][2]
                     + part2.pi[Char.CharInfo[this.cf][1][0]].dy,
                  0,
                  0
               );
               SmallImage.drawSmallImage(
                  g,
                  part3.pi[Char.CharInfo[this.cf][2][0]].id,
                  this.cx[cxIndex]
                     + Char.CharInfo[this.cf][2][1]
                     + part3.pi[Char.CharInfo[this.cf][2][0]].dx,
                  this.cy[cxIndex]
                     - Char.CharInfo[this.cf][2][2]
                     + part3.pi[Char.CharInfo[this.cf][2][0]].dy,
                  0,
                  0
               );
               if (this.focus == cxIndex) {
                  mFont.tahoma_7b_yellow
                     .drawString(
                        g,
                        playerData[cxIndex].name,
                        this.cx[cxIndex] + this.rectPanel[2] - 25,
                        this.cy[cxIndex] + this.offsetY,
                        1
                     );
                  mFont.tahoma_7b_yellow
                     .drawString(
                        g,
                        mResources.power_point + " " + Res.formatNumber2(playerData[cxIndex].powpoint),
                        this.cx[cxIndex] + this.rectPanel[2] - 25,
                        this.cy[cxIndex] + this.offsetY + mFont.tahoma_7b_yellow.getHeight(),
                        1
                     );
               } else {
                  mFont.tahoma_7b_dark
                     .drawString(
                        g,
                        playerData[cxIndex].name,
                        this.cx[cxIndex] + this.rectPanel[2] - 25,
                        this.cy[cxIndex] + this.offsetY,
                        1
                     );
                  mFont.tahoma_7b_dark
                     .drawString(
                        g,
                        mResources.power_point + " " + Res.formatNumber2(playerData[cxIndex].powpoint),
                        this.cx[cxIndex] + this.rectPanel[2] - 25,
                        this.cy[cxIndex] + this.offsetY + mFont.tahoma_7b_dark.getHeight(),
                        1
                     );
               }
            }
         }
      } catch (Exception ignoredException) {
      }

      super.paint(g);
   }

   public final void updateChooseCharacter(byte len) {
      this.cx = new int[len];
      this.cy = new int[len];

      for (int cxIndex = 0; cxIndex < len; cxIndex++) {
         this.cx[cxIndex] = this.rectPanel[0] + 20;
         this.cy[cxIndex] = cxIndex * 70 + this.rectPanel[1] + 50;
      }

      this.vc_players = new Command[2];
      this.vc_players[1] = new Command(
         "Vào game", this, 1, null, this.rectPanel[0] + this.rectPanel[2] - 80 - 80, this.rectPanel[1] + this.rectPanel[3] - 30
      );
      this.vc_players[0] = new Command(
         "Trờ ra", this, 2, null, this.rectPanel[0] + this.rectPanel[2] - 80, this.rectPanel[1] + this.rectPanel[3] - 30
      );
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 1:
            if (this.focus != -1) {
               GameCanvas.startWaitDlg();
               Service.gI().finishUpdate(playerData[this.focus].playerID);
               return;
            }
            break;
         case 2:
            GameCanvas.doResetToLoginScr();
      }
   }
}
