package nro;

public final class EffectManager extends MyVector {
   public static EffectManager lowEffects = new EffectManager("low");
   public static EffectManager midEffects = new EffectManager("mid");
   public static EffectManager mid_2Effects = new EffectManager("mid2");
   public static EffectManager hiEffects = new EffectManager("hi");

   private void updateAll() {
      for (int effectManagerIndex = this.size() - 1; effectManagerIndex >= 0; effectManagerIndex--) {
         Effect_End effectManager;
         if ((effectManager = (Effect_End)this.elementAt(effectManagerIndex)) != null) {
            effectManager.update();
            if (effectManager.isRemove) {
               this.removeElementAt(effectManagerIndex);
            }
         }
      }
   }

   public static void update() {
      hiEffects.updateAll();
      midEffects.updateAll();
      mid_2Effects.updateAll();
      lowEffects.updateAll();
   }

   public final void paintAll(mGraphics g) {
      for (int effectManagerIndex = 0; effectManagerIndex < this.size(); effectManagerIndex++) {
         Effect_End effectManager;
         if ((effectManager = (Effect_End)this.elementAt(effectManagerIndex)) != null && !effectManager.isRemove) {
            ((Effect_End)this.elementAt(effectManagerIndex)).paint(g);
         }
      }
   }

   private void removeAll() {
      for (int effectManagerIndex = this.size() - 1; effectManagerIndex >= 0; effectManagerIndex--) {
         Effect_End effectManager;
         if ((effectManager = (Effect_End)this.elementAt(effectManagerIndex)) != null) {
            effectManager.isRemove = true;
            this.removeElementAt(effectManagerIndex);
         }
      }
   }

   public static void remove() {
      hiEffects.removeAll();
      lowEffects.removeAll();
      midEffects.removeAll();
      mid_2Effects.removeAll();
   }

   public static void addHiEffect(Effect_End eff) {
      hiEffects.addElement(eff);
   }

   public static void addMidEffects(Effect_End eff) {
      midEffects.addElement(eff);
   }

   public static void addMid_2Effects(Effect_End eff) {
      mid_2Effects.addElement(eff);
   }

   public static void addLowEffect(Effect_End eff) {
      lowEffects.addElement(eff);
   }

   private EffectManager(String text) {
      super(text);
   }
}
