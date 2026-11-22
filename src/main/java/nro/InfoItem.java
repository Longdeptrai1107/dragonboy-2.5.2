package nro;

public final class InfoItem {
   public String s;
   public int speed = 70;
   public Char charInfo;
   public boolean isChatServer;
   public boolean isOnline;
   public int timeCount;
   public int maxTime;
   public long last;
   public long curr;

   public InfoItem(String sValue) {
      this.s = sValue;
      this.speed = 20;
   }
}
