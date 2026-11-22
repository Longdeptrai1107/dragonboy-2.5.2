package nro;

public abstract class Effect2 {
   public static MyVector vEffect3 = new MyVector("vEffect3");
   public static MyVector vEffect2 = new MyVector("vEffect2");
   public static MyVector vRemoveEffect2 = new MyVector("vRemoveEff2");
   public static MyVector vEffect2Outside = new MyVector("vEffect2Out");
   public static MyVector vAnimateEffect = new MyVector("vAnimate");
   public static MyVector vEffectFeet = new MyVector("vEffectFeet");

   public abstract void update();

   public abstract void paint(mGraphics g);
}
