package nro;

public final class MovePoint {
   public int xEnd;
   public int yEnd;
   public int status;

   public MovePoint(int xEndValue, int yEndValue, int act, int dir) {
      this.xEnd = xEndValue;
      this.yEnd = yEndValue;
      this.status = act;
   }

   public MovePoint(int xEndValue, int yEndValue) {
      this.xEnd = xEndValue;
      this.yEnd = yEndValue;
   }
}
