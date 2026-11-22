package nro;

public final class Position {
   public int x;
   public int y;
   public int anchor;
   public int v;
   public int w;

   public Position() {
      this.x = 0;
      this.y = 0;
   }

   public Position(int xValue, int yValue) {
      this.x = xValue;
      this.y = yValue;
   }
}
