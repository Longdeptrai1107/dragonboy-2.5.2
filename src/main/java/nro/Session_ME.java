package nro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class Session_ME implements ISession {
   private static Session_ME instance = new Session_ME();
   private static Session_ME secondaryInstance = new Session_ME();
   private volatile DataOutputStream dos;
   public volatile DataInputStream dis;
   public IMessageHandler messageHandler;
   public boolean isMainSession = true;
   private volatile SocketConnectionHandle connection;
   public volatile boolean connected;
   public volatile boolean connecting;
   private Sender sender = new Sender(this, Long.MIN_VALUE);
   private volatile long connectionGeneration;
   private volatile boolean closed;
   private Thread handshakeWatchdog;
   public Thread collectorThread;
   public Thread senderThread;
   public int sendByteCount;
   public int recvByteCount;
   volatile boolean getKeyComplete;
   public byte[] key = null;
   private byte curR;
   private byte curW;
   long connectedAt;
   public String strRecvByteCount = "";
   private long nextConnectAllowedAt = 0L;
   private static int sendCount;

   public static Session_ME gI() {
      return instance;
   }

   public static Session_ME gI2() {
      return secondaryInstance;
   }

   public final synchronized void clearSendingMessage() {
      Sender.getSendingMessage(this.sender).removeAllElements();
   }

   public final boolean isConnected() {
      return this.connected && this.connection != null && this.connection.connection != null && this.dis != null;
   }

   public final synchronized long getConnectionGeneration() {
      return this.connectionGeneration;
   }

   public final void setHandler(IMessageHandler msgHandler) {
      this.messageHandler = msgHandler;
   }

   public final void connect(String host, int port) {
      System.out.println(">>connect: " + host + ":" + port);
      long generation;
      Thread thread;
      synchronized (this) {
         if (this.connected || this.connecting) {
            return;
         }

         long nowMillis;
         if ((nowMillis = mSystem.currentTimeMillis()) < this.nextConnectAllowedAt) {
            return;
         }

         this.nextConnectAllowedAt = nowMillis + 50L;
         this.closeCurrentGeneration();
         this.closed = false;
         this.getKeyComplete = false;
         this.connecting = true;
         if (this.isMainSession) {
            ServerListScreen.testConnect = -1;
         }

         generation = this.connectionGeneration;
         thread = new Thread(new SessionConnector(this, host, port, generation));
      }

      Service.gI().resetClientInfoStateForGeneration(this.isMainSession, generation);
      thread.start();
   }

   public final synchronized void sendMessage(Message message) {
      sendCount++;
      this.sender.AddMessage(message);
      System.out.println("SEND MSG: " + message.command);
   }

   private synchronized void doSendMessage(Message message, long longValue) throws java.io.IOException {
      if (longValue == this.connectionGeneration && this.dos != null) {
         System.out.println("DO SEND MSG: " + message.command);
         byte[] data = message.getData();
         if (this.getKeyComplete) {
            byte byteValue = this.writeKey(message.command);
            this.dos.writeByte(byteValue);
         } else {
            this.dos.writeByte(message.command);
         }

         if (data != null) {
            int dataCount = data.length;
            if (this.getKeyComplete) {
               byte byteValue2 = this.writeKey((byte)(dataCount >> 8));
               this.dos.writeByte(byteValue2);
               byte byteValue3 = this.writeKey((byte)dataCount);
               this.dos.writeByte(byteValue3);
            } else {
               this.dos.writeShort(dataCount);
            }

            if (this.getKeyComplete) {
               for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
                  data[dataIndex] = this.writeKey(data[dataIndex]);
               }
            }

            this.dos.write(data);
            this.sendByteCount += 5 + data.length;
         } else {
            this.dos.writeShort(0);
            this.sendByteCount += 5;
         }

         this.dos.flush();
      }
   }

   private byte writeKey(byte byteValue) {
      byte result = (byte)(this.key[this.curW++] & 0xFF ^ byteValue & 0xFF);
      if (this.curW >= this.key.length) {
         this.curW = (byte)(this.curW % this.key.length);
      }

      return result;
   }

   public final void close() {
      this.disconnectAndResetClientInfo();
   }

   private void disconnectAndResetClientInfo() {
      long connectionGenerationValue;
      synchronized (this) {
         this.closeCurrentGeneration();
         connectionGenerationValue = this.connectionGeneration;
      }

      Service.gI().resetClientInfoStateForGeneration(this.isMainSession, connectionGenerationValue);
   }

   private void closeCurrentGeneration() {
      long connectionGenerationValue = this.connectionGeneration++;
      this.cancelHandshakeWatchdog(connectionGenerationValue);
      this.closed = true;
      this.key = null;
      this.curR = 0;
      this.curW = 0;
      this.connected = false;
      this.connecting = false;
      this.getKeyComplete = false;
      if (this.sender != null) {
         Sender.getSendingMessage(this.sender).removeAllElements();
      }

      SocketConnectionHandle connectionValue = this.connection;
      DataOutputStream dosValue = this.dos;
      DataInputStream disValue = this.dis;
      this.connection = null;
      this.dos = null;
      this.dis = null;
      if (this.senderThread != null) {
         this.senderThread.interrupt();
      }

      this.senderThread = null;
      if (this.collectorThread != null) {
         this.collectorThread.interrupt();
      }

      this.collectorThread = null;

      try {
         if (connectionValue != null && connectionValue.connection != null) {
            connectionValue.close();
         }
      } catch (IOException iOException) {
         Res.err("socket close failed: " + iOException.toString());
      }

      try {
         if (dosValue != null) {
            dosValue.close();
         }
      } catch (IOException iOException2) {
         Res.err("socket output close failed: " + iOException2.toString());
      }

      try {
         if (disValue != null) {
            disValue.close();
         }
      } catch (IOException iOException3) {
         Res.err("socket input close failed: " + iOException3.toString());
      }

      if (this.isMainSession) {
         ServerListScreen.testConnect = 0;
      }

      Controller.isGet_CLIENT_INFO = false;
   }

   private synchronized void cancelHandshakeWatchdog(long longValue) {
      if (longValue == this.connectionGeneration || longValue + 1L == this.connectionGeneration) {
         Thread handshakeWatchdogValue = this.handshakeWatchdog;
         this.handshakeWatchdog = null;
         if (handshakeWatchdogValue != null && handshakeWatchdogValue != Thread.currentThread()) {
            handshakeWatchdogValue.interrupt();
         }
      }
   }

   public static boolean isSecondarySessionForcedOff() {
      return true;
   }

   static void startHandshakeWatchdog(Session_ME session, long longValue) {
      long generation = longValue;
      Session_ME session2 = session;
      Thread handshakeWatchdogValue = new Thread(new SessionHandshakeWatchdog(session2, generation));
      synchronized (session2) {
         if (generation != session2.connectionGeneration || session2.closed) {
            return;
         }

         session2.handshakeWatchdog = handshakeWatchdogValue;
      }

      handshakeWatchdogValue.start();
   }

   static boolean isClosed(Session_ME session) {
      return session.closed;
   }

   static void failConnection(Session_ME session, long longValue, String text) {
      String text2 = text;
      long longValue2 = longValue;
      Session_ME session2 = session;
      IMessageHandler messageHandlerValue;
      long connectionGenerationValue;
      synchronized (session) {
         if (longValue2 != session2.connectionGeneration || session2.closed) {
            return;
         }

         session2.closeCurrentGeneration();
         connectionGenerationValue = session2.connectionGeneration;
         messageHandlerValue = session2.messageHandler;
      }

      Service.gI().resetClientInfoStateForGeneration(session2.isMainSession, connectionGenerationValue);
      Res.err(text2);
      if (messageHandlerValue != null) {
         messageHandlerValue.onConnectionFail(session2.isMainSession);
      }
   }

   static void closeSocketResources(Session_ME session, SocketConnectionHandle socketConnectionHandle, DataOutputStream output, DataInputStream input) {
      DataInputStream input2 = input;
      DataOutputStream output2 = output;
      SocketConnectionHandle socketConnectionHandle2 = socketConnectionHandle;

      try {
         if (input2 != null) {
            input2.close();
         }
      } catch (IOException ignoredIOException) {
      }

      try {
         if (output2 != null) {
            output2.close();
         }
      } catch (IOException ignoredIOException2) {
      }

      try {
         if (socketConnectionHandle2 != null) {
            socketConnectionHandle2.close();
            return;
         }
      } catch (IOException ignoredIOException3) {
      }
   }

   static long connectionGeneration(Session_ME session) {
      return session.connectionGeneration;
   }

   static void setConnection(Session_ME session, SocketConnectionHandle connectionValue) {
      session.connection = connectionValue;
   }

   static void setOutput(Session_ME session, DataOutputStream dosValue) {
      session.dos = dosValue;
   }

   static void setSender(Session_ME session, Sender senderValue) {
      session.sender = senderValue;
   }

   static void sendForGeneration(Session_ME session, Message message, long longValue) throws java.io.IOException {
      session.doSendMessage(message, longValue);
   }

   static SocketConnectionHandle getConnection(Session_ME session) {
      return session.connection;
   }

   static void disconnectSession(Session_ME session) {
      session.disconnectAndResetClientInfo();
   }

   static void cancelWatchdogForGeneration(Session_ME session, long longValue) {
      session.cancelHandshakeWatchdog(longValue);
   }

   static byte readKey(Session_ME session, byte byteValue) {
      byteValue = byteValue;
      Session_ME session2 = session;
      byte result = (byte)(session.key[session2.curR++] & 0xFF ^ byteValue & 0xFF);
      if (session2.curR >= session2.key.length) {
         session2.curR = (byte)(session2.curR % session2.key.length);
      }

      return result;
   }
}
