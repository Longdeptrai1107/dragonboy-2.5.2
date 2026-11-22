package nro;

public final class Part {
   public PartImage[] pi;

   public Part(int type) {
      if (type == 0) {
         this.pi = new PartImage[3];
      }

      if (type == 1) {
         this.pi = new PartImage[17];
      }

      if (type == 2) {
         this.pi = new PartImage[14];
      }

      if (type == 3) {
         this.pi = new PartImage[2];
      }
   }
}
