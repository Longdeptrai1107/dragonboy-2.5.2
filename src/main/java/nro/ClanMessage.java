package nro;

import main.GameCanvas;

public final class ClanMessage implements IActionListener {
   public int id;
   public int type;
   public int playerId;
   public String playerName;
   public long time;
   public String[] chat;
   public byte color;
   public byte role;
   private int timeAgo;
   public int recieve;
   public int maxCap;
   public String[] option;
   public static MyVector vMessage = new MyVector("v Message");

   public static void addMessage(ClanMessage cm, int index, boolean upToTop) {
      for (int messageIndex = 0; messageIndex < vMessage.size(); messageIndex++) {
         ClanMessage message;
         if ((message = (ClanMessage)vMessage.elementAt(messageIndex)).id == cm.id) {
            ClanMessage cm2 = cm;
            vMessage.removeElement(message);
            if (!upToTop) {
               vMessage.insertElementAt(cm2, messageIndex);
               return;
            }

            vMessage.insertElementAt(cm2, 0);
            return;
         }

         if (message.maxCap != 0 && message.recieve == message.maxCap) {
            vMessage.removeElement(message);
         }
      }

      if (index == -1) {
         vMessage.addElement(cm);
      } else {
         vMessage.insertElementAt(cm, 0);
      }

      if (vMessage.size() > 20) {
         vMessage.removeElementAt(vMessage.size() - 1);
      }
   }

   public final void paint(mGraphics g, int x, int y) {
      mFont tahoma_7b_dark = mFont.tahoma_7b_dark;
      if (this.role == 0) {
         tahoma_7b_dark = mFont.tahoma_7b_red;
      } else if (this.role == 1) {
         tahoma_7b_dark = mFont.tahoma_7b_green;
      } else if (this.role == 2) {
         tahoma_7b_dark = mFont.tahoma_7b_green2;
      }

      if (this.type == 0) {
         tahoma_7b_dark.drawString(g, this.playerName, x + 3, y + 1, 0);
         if (this.color == 0) {
            mFont.tahoma_7_grey
               .drawString(g, this.chat[0] + (this.chat.length > 1 ? "..." : ""), x + 3, y + 11, 0);
         } else {
            mFont.tahoma_7_red
               .drawString(g, this.chat[0] + (this.chat.length > 1 ? "..." : ""), x + 3, y + 11, 0);
         }

         mFont.tahoma_7_grey
            .drawString(
               g,
               Firework.formatElapsedDuration(this.timeAgo) + " " + mResources.ago,
               x + GameCanvas.panel.wScroll - 3,
               y + 1,
               1
            );
      }

      if (this.type == 1) {
         tahoma_7b_dark.drawString(g, this.playerName + " (" + this.recieve + "/" + this.maxCap + ")", x + 3, y + 1, 0);
         mFont.tahoma_7_blue
            .drawString(
               g,
               mResources.request_pea + " " + Firework.formatElapsedDuration(this.timeAgo) + " " + mResources.ago,
               x + 3,
               y + 11,
               0
            );
      }

      if (this.type == 2) {
         tahoma_7b_dark.drawString(g, this.playerName, x + 3, y + 1, 0);
         mFont.tahoma_7_blue.drawString(g, mResources.request_join_clan, x + 3, y + 11, 0);
      }
   }

   public final void perform(int idAction, Object p) {
   }

   public final void update() {
      if (this.time != 0L) {
         this.timeAgo = (int)(System.currentTimeMillis() / 1000L - this.time);
      }
   }
}
