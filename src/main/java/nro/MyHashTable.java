package nro;

import java.util.Hashtable;

public final class MyHashTable extends Hashtable {
   public MyHashTable(String text) {
   }

   public final synchronized void clear() {
      super.clear();
   }

   public final synchronized Object put(Object entry, Object entry2) {
      return super.put(entry, entry2);
   }

   public final synchronized Object remove(Object entry) {
      return super.remove(entry);
   }
}
