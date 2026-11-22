package nro;

import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public final class SocketConnectionHandle {
   SocketConnection connection;

   public SocketConnectionHandle(String text, int value) throws java.io.IOException {
      this.connection = (SocketConnection)Connector.open("socket://" + text + ":" + value);
   }

   public final void close() throws java.io.IOException {
      if (this.connection != null) {
         this.connection.close();
      }
   }
}
