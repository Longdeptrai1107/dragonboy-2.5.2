package nro;

public final class ItemTemplates {
   public static MyHashTable itemTemplates = new MyHashTable("item template");

   public static void add(ItemTemplate it) {
      itemTemplates.put(new Short(it.id), it);
   }

   public static ItemTemplate get(short id) {
      return (ItemTemplate)itemTemplates.get(new Short(id));
   }
}
