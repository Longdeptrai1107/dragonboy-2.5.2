package nro;

public final class PostLoginRequestHook {
   public static PostLoginRequestHook instance = new PostLoginRequestHook();

   public static void afterLoginRequestQueued() {
   }
}
