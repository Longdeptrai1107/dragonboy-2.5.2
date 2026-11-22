package nro;

public final class ClanImage {
   public int ID;
   public String name;
   public short[] idImage;
   public int xu;
   public int luong;
   public static MyVector vClanImage = new MyVector("vClanImage");
   public static MyHashTable idImages = new MyHashTable("h id images");

   public static void addClanImage(ClanImage cm) {
      Service.gI().clanImage((byte)cm.ID);
      vClanImage.addElement(cm);
   }

   public static ClanImage getClanImage(short ID) {
      for (int clanImageIndex = 0; clanImageIndex < vClanImage.size(); clanImageIndex++) {
         ClanImage clanImage;
         if ((clanImage = (ClanImage)vClanImage.elementAt(clanImageIndex)).ID == ID) {
            return clanImage;
         }
      }

      return null;
   }

   public static boolean isExistClanImage(int ID) {
      for (int clanImageIndex = 0; clanImageIndex < vClanImage.size(); clanImageIndex++) {
         if (((ClanImage)vClanImage.elementAt(clanImageIndex)).ID == ID) {
            return true;
         }
      }

      return false;
   }
}
