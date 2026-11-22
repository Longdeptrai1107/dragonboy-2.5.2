package nro;

import main.GameCanvas;

public final class ItemTime {
   public short idIcon;
   public int second = 0;
   public int minute;
   private long curr;
   private long last;
   private boolean isPaint_coolDownBar;
   private int time;
   private int coutTime;
   private boolean isText;
   private boolean dontClear;
   private String text;
   private int per = 100;

   public ItemTime(short idIconValue, int coutTimeValue) {
      this.idIcon = idIconValue;
      this.minute = coutTimeValue / 60;
      this.second = coutTimeValue % 60;
      this.time = coutTimeValue;
      this.coutTime = coutTimeValue;
      this.curr = this.last = System.currentTimeMillis();
      this.isPaint_coolDownBar = idIconValue == 14;
   }

   public ItemTime() {
   }

   public static boolean isExistItem(int id) {
      for (int itemTimeIndex = 0; itemTimeIndex < Char.vItemTime.size(); itemTimeIndex++) {
         if (((ItemTime)Char.vItemTime.elementAt(itemTimeIndex)).idIcon == id) {
            return true;
         }
      }

      return false;
   }

   public static boolean isExistMessage(int id) {
      for (int textTimeIndex = 0; textTimeIndex < GameScr.textTime.size(); textTimeIndex++) {
         if (((ItemTime)GameScr.textTime.elementAt(textTimeIndex)).idIcon == id) {
            return true;
         }
      }

      return false;
   }

   public static ItemTime getItemById(int id) {
      for (int itemTimeIndex = 0; itemTimeIndex < Char.vItemTime.size(); itemTimeIndex++) {
         ItemTime itemTime;
         if ((itemTime = (ItemTime)Char.vItemTime.elementAt(itemTimeIndex)).idIcon == id) {
            return itemTime;
         }
      }

      return null;
   }

   public static ItemTime getMessageById(int id) {
      for (int textTimeIndex = 0; textTimeIndex < GameScr.textTime.size(); textTimeIndex++) {
         ItemTime textTime;
         if ((textTime = (ItemTime)GameScr.textTime.elementAt(textTimeIndex)).idIcon == id) {
            return textTime;
         }
      }

      return null;
   }

   public final void initTimeText(byte id, String text, int time) {
      if (time == -1) {
         this.dontClear = true;
      } else {
         this.dontClear = false;
      }

      this.isText = true;
      this.minute = time / 60;
      this.second = time % 60;
      this.idIcon = id;
      this.time = time;
      this.coutTime = time;
      this.text = text;
      this.curr = this.last = System.currentTimeMillis();
      this.isPaint_coolDownBar = this.idIcon == 14;
   }

   public final void initTime(int time, boolean isText) {
      this.minute = time / 60;
      this.second = time % 60;
      this.time = time;
      this.coutTime = time;
      this.isText = false;
      this.curr = this.last = System.currentTimeMillis();
   }

   public final void paintText(mGraphics g, int x, int y) {
      if (this.isPaint_coolDownBar) {
         if (Char.myCharz() != null) {
            x = GameCanvas.w / 2 - 40;
            y = GameCanvas.h - 80;
            g.setColor(8421504);
            g.fillRect(x, y, 80, 2);
            g.setColor(16777215);
            if (this.per > 0) {
               g.fillRect(x, y, 80 * this.per / 100, 2);
               return;
            }
         }
      } else {
         String textValue = this.minute + "'";
         if (this.minute <= 0) {
            textValue = this.second + "s";
         }

         if (this.minute < 0) {
            textValue = "";
         }

         if (this.dontClear) {
            textValue = "";
         }

         mFont.tahoma_7b_white.drawString(g, this.text + " " + textValue, x, y, 0, mFont.tahoma_7b_dark);
      }
   }

   public final void update() {
      this.curr = System.currentTimeMillis();
      if (this.curr - this.last >= 1000L) {
         this.last = System.currentTimeMillis();
         this.second--;
         this.coutTime--;
         if (this.second <= 0) {
            this.second = 60;
            this.minute--;
         }

         if (this.time > 0) {
            this.per = this.coutTime * 100 / this.time;
         }
      }

      if (this.minute < 0 && !this.isText) {
         Char.vItemTime.removeElement(this);
      }

      if (this.minute < 0 && this.isText && !this.dontClear) {
         GameScr.textTime.removeElement(this);
      }
   }
}
