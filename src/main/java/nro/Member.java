package nro;

public final class Member {
   public int ID;
   public short head;
   public short headICON = -1;
   public short leg;
   public short body;
   public String name;
   public byte role;
   public String powerPoint;
   public int donate;
   public int receive_donate;
   public int curClanPoint;
   public int clanPoint;
   public String joinTime;

   public static String getRole(int r) {
      if (r == 0) {
         return mResources.clan_leader;
      } else if (r == 1) {
         return mResources.clan_coleader;
      } else {
         return r == 2 ? mResources.member : "";
      }
   }
}
