package nro;

public final class EffecMn {
   public static MyVector vEff = new MyVector("vEff");

   public static void addEff(Effect me) {
      vEff.addElement(me);
   }

   public static void removeEff(int id) {
      if (getEffById(id) != null) {
         vEff.removeElement(getEffById(id));
      }
   }

   private static Effect getEffById(int id) {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         Effect eff;
         if ((eff = (Effect)vEff.elementAt(effIndex)).effId == id) {
            return eff;
         }
      }

      return null;
   }

   public static void paintBackGroundUnderLayer(mGraphics g, int x, int y, int layer) {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         if (((Effect)vEff.elementAt(effIndex)).layer == -layer) {
            ((Effect)vEff.elementAt(effIndex)).paintUnderBackground(g, x, y);
         }
      }
   }

   public static void paintLayer1(mGraphics graphics) {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         if (((Effect)vEff.elementAt(effIndex)).layer == 1) {
            ((Effect)vEff.elementAt(effIndex)).paint(graphics);
         }
      }
   }

   public static void paintLayer2(mGraphics graphics) {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         if (((Effect)vEff.elementAt(effIndex)).layer == 2) {
            ((Effect)vEff.elementAt(effIndex)).paint(graphics);
         }
      }
   }

   public static void paintLayer3(mGraphics graphics) {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         if (((Effect)vEff.elementAt(effIndex)).layer == 3) {
            ((Effect)vEff.elementAt(effIndex)).paint(graphics);
         }
      }
   }

   public static void paintLayer4(mGraphics graphics) {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         if (((Effect)vEff.elementAt(effIndex)).layer == 4) {
            ((Effect)vEff.elementAt(effIndex)).paint(graphics);
         }
      }
   }

   public static void update() {
      for (int effIndex = 0; effIndex < vEff.size(); effIndex++) {
         ((Effect)vEff.elementAt(effIndex)).update();
      }
   }
}
