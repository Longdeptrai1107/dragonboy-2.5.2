package nro;

import java.util.Vector;

public class MyVector extends Vector {
   private String debugName;

   public MyVector(String debugNameValue) {
      this.debugName = debugNameValue;
   }

   private MyVector() {
   }

   public synchronized void removeAllElements() {
      super.removeAllElements();
   }

   public synchronized boolean removeElement(Object entry) {
      return super.removeElement(entry);
   }

   public synchronized void removeElementAt(int value) {
      super.removeElementAt(value);
   }

   public synchronized void addElement(Object entry) {
      if (this.size() > 1000) {
         System.out.println("||||||||||||||||||||||||||||||||||||||||||||" + this.debugName + " num= " + this.size());
      }

      super.addElement(entry);
   }

   public synchronized void insertElementAt(Object entry, int value) {
      if (this.size() > 1000) {
         System.out.println("||||||||||||||||||||||||||||||||||||||||||||" + this.debugName + " num= " + this.size());
      }

      super.insertElementAt(entry, value);
   }
}
