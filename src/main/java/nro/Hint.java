package nro;

import main.GameCanvas;

public final class Hint {
   public static int x;
   public static int y;
   public static int type;
   public static int t;
   public static boolean activeClick;
   public static boolean isViewMap;
   public static boolean isCloseMap;
   public static boolean isViewPotential;
   public static boolean isPaint;
   public static boolean isCamera;
   public static int trans;
   public static boolean paintFlare;
   public static boolean isPaintArrow;

   public static boolean isOnTask(int tastId, int index) {
      return Char.myCharz().taskMaint != null
         && Char.myCharz().taskMaint.taskId == tastId
         && Char.myCharz().taskMaint.index == 0;
   }

   public static void clickNpc() {
      if (GameCanvas.panel.isShow) {
         isPaint = false;
      }

      if (GameScr.getNpcTask() != null) {
         x = GameScr.getNpcTask().cx;
         y = GameScr.getNpcTask().cy;
         trans = 0;
         isCamera = true;
         type = GameCanvas.isTouch ? 1 : 0;
      }
   }

   public static void nextMap(int index) {
      if (!GameCanvas.panel.isShow) {
         if (PopUp.vPopups.size() - 1 >= index) {
            PopUp popup;
            x = (popup = (PopUp)PopUp.vPopups.elementAt(index)).cx + popup.sayWidth / 2;
            y = popup.cy + 30;
            if (!popup.isHide && popup.isPaint) {
               isPaint = true;
            } else {
               isPaint = false;
            }

            type = 0;
            isCamera = true;
            trans = 0;
            if (!GameCanvas.isTouch) {
               isPaint = false;
            }
         }
      }
   }

   public static void clickMob() {
      type = 1;
      if (GameCanvas.panel.isShow) {
         isPaint = false;
      }

      boolean condition = false;

      for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
         if (((Mob)GameScr.vMob.elementAt(mobIndex)).isHintFocus) {
            condition = true;
            break;
         }
      }

      for (int mobIndex2 = 0; mobIndex2 < GameScr.vMob.size(); mobIndex2++) {
         Mob mob;
         if ((mob = (Mob)GameScr.vMob.elementAt(mobIndex2)).isHintFocus) {
            x = mob.x;
            y = mob.y + 5;
            isCamera = true;
            if (mob.status == 0) {
               mob.isHintFocus = false;
               return;
            }
            break;
         }

         if (!condition) {
            if (mob.status != 0) {
               mob.isHintFocus = true;
               return;
            }

            mob.isHintFocus = false;
         }
      }
   }

   public static boolean isHaveItem() {
      if (GameCanvas.panel.isShow) {
         isPaint = false;
      }

      for (int itemMapIndex = 0; itemMapIndex < GameScr.vItemMap.size(); itemMapIndex++) {
         ItemMap itemMap;
         if ((itemMap = (ItemMap)GameScr.vItemMap.elementAt(itemMapIndex)).playerId == Char.myCharz().charID
            && itemMap.template.id == 73) {
            type = 1;
            x = itemMap.x;
            y = itemMap.y + 5;
            isCamera = true;
            return true;
         }
      }

      return false;
   }
}
