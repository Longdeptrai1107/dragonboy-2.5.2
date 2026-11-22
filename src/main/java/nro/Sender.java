package nro;

import java.io.IOException;
import java.util.Vector;

final class Sender implements Runnable {
   private final Vector sendingMessage;
   private final long generation;
   private Session_ME session;

   public Sender(Session_ME sessionValue, long generationValue) {
      this.session = sessionValue;
      this.sendingMessage = new Vector();
      this.generation = generationValue;
   }

   public final void AddMessage(Message sendingMessageValue) {
      this.sendingMessage.addElement(sendingMessageValue);
   }

   public final void run() {
      while (this.generation == this.session.getConnectionGeneration() && this.session.connected) {
         try {
            if (this.session.getKeyComplete) {
               while (this.sendingMessage.size() > 0) {
                  Message sendingMessageValue = (Message)this.sendingMessage.elementAt(0);
                  this.sendingMessage.removeElementAt(0);
                  Session_ME.sendForGeneration(this.session, sendingMessageValue, this.generation);
               }
            }

            try {
               Thread.sleep(10L);
            } catch (Exception ignoredException) {
            }
         } catch (IOException iOException) {
            Session_ME.failConnection(this.session, this.generation, "socket send failed: " + iOException.toString());
            return;
         } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
         }
      }
   }

   static Vector getSendingMessage(Sender sender) {
      return sender.sendingMessage;
   }
}
