package nro;

public final class PlayerData {
   public int playerID;
   public String name;
   public short head;
   public short body;
   public short leg;
   public long powpoint;

   public PlayerData(int playerIDValue, String nameValue, short headValue, short bodyValue, short legValue, long ppoint) {
      this.playerID = playerIDValue;
      this.name = nameValue;
      this.head = headValue;
      this.body = bodyValue;
      this.leg = legValue;
      this.powpoint = ppoint;
   }
}
