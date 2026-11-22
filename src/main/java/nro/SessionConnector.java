package nro;

import java.io.DataOutputStream;
import java.io.IOException;

final class SessionConnector implements Runnable {
   private final String host;
   private final long generation;
   private int port;
   private Session_ME session;

   SessionConnector(Session_ME sessionValue, String hostValue, int portValue, long generationValue) {
      this.session = sessionValue;
      this.host = hostValue;
      this.port = portValue;
      this.generation = generationValue;
   }

   public final void run() {
      Session_ME.startHandshakeWatchdog(this.session, this.generation);
      if (this.generation == this.session.getConnectionGeneration()) {
         try {
            long generationValue = this.generation;
            int portValue = this.port;
            String hostValue = this.host;
            SessionConnector sessionConnector = this;
            SocketConnectionHandle connection = null;
            DataOutputStream dos = null;
            java.io.DataInputStream dis;

            try {
               dos = (connection = new SocketConnectionHandle(hostValue, portValue)).connection.openDataOutputStream();
               dis = connection.connection.openDataInputStream();
            } catch (IOException iOException) {
               Session_ME.closeSocketResources(sessionConnector.session, connection, dos, null);
               throw iOException;
            } catch (RuntimeException runtimeException) {
               Session_ME.closeSocketResources(sessionConnector.session, connection, dos, null);
               throw runtimeException;
            }

            if (dos == null || dis == null) {
               Session_ME.closeSocketResources(sessionConnector.session, connection, dos, dis);
               throw new IOException("socket streams are unavailable");
            }

            label55: {
               Thread collectorThread;
               Thread senderThread;
               synchronized (sessionConnector.session) {
                  if (generationValue != Session_ME.connectionGeneration(sessionConnector.session) || Session_ME.isClosed(sessionConnector.session)) {
                     Session_ME.closeSocketResources(sessionConnector.session, connection, dos, dis);
                     break label55;
                  }

                  Session_ME.setConnection(sessionConnector.session, connection);
                  Session_ME.setOutput(sessionConnector.session, dos);
                  sessionConnector.session.dis = dis;
                  sessionConnector.session.connected = true;
                  Sender sender = new Sender(sessionConnector.session, generationValue);
                  Session_ME.setSender(sessionConnector.session, sender);
                  senderThread = new Thread(sender);
                  sessionConnector.session.senderThread = senderThread;
                  collectorThread = new Thread(new MessageCollector(sessionConnector.session, generationValue, dis));
                  sessionConnector.session.collectorThread = collectorThread;
               }

               if (generationValue == sessionConnector.session.getConnectionGeneration() && !Session_ME.isClosed(sessionConnector.session)) {
                  senderThread.start();
                  collectorThread.start();
                  sessionConnector.session.connectedAt = System.currentTimeMillis();
                  Session_ME.sendForGeneration(sessionConnector.session, new Message((byte)-27), generationValue);
                  Res.outz("=======================> gui message cmd = -27 to server");
                  sessionConnector.session.connecting = false;
               }
            }

            if (!Session_ME.isClosed(this.session)
               && this.generation == this.session.getConnectionGeneration()
               && this.session.messageHandler != null) {
               this.session.messageHandler.onConnectOK(this.session.isMainSession);
               return;
            }
         } catch (IOException iOException2) {
            Session_ME.failConnection(this.session, this.generation, "socket connect failed: " + iOException2.toString());
            return;
         } catch (RuntimeException runtimeException2) {
            Session_ME.failConnection(this.session, this.generation, "socket setup failed: " + runtimeException2.toString());
         }
      }
   }
}
