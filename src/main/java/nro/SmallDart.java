package nro;

import java.io.InputStream;

public class SmallDart {
   public int index;
   public int x;
   public int y;

   public SmallDart(int xValue, int yValue) {
      this.x = xValue;
      this.y = yValue;
   }

   public SmallDart() {
   }

   public static InputStream openResourceStream(String text) {
      return "".getClass().getResourceAsStream(text);
   }
}
