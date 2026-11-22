package nro;

import java.io.DataInputStream;
import main.GameMidlet;

final class MessageCollector implements Runnable {
   private final long generation;
   private final DataInputStream input;
   private Session_ME session;

   MessageCollector(Session_ME sessionValue, long generationValue, DataInputStream inputValue) {
      this.session = sessionValue;
      this.generation = generationValue;
      this.input = inputValue;
   }

   public final void run() {
      try {
         while (this.generation == this.session.getConnectionGeneration() && this.session.connected && this.input != null) {
            DataInputStream inputValue = this.input;
            MessageCollector messageCollector = this;
            byte command = inputValue.readByte();
            if (messageCollector.session.getKeyComplete) {
               command = Session_ME.readKey(messageCollector.session, command);
            }

            Message message;
            if (command != -32 && command != -66 && command != 11 && command != -67 && command != -74 && command != -87 && command != 66 && command != 12) {
               int dataCount;
               if (messageCollector.session.getKeyComplete) {
                  byte packetByte = inputValue.readByte();
                  byte packetByte2 = inputValue.readByte();
                  dataCount = (Session_ME.readKey(messageCollector.session, packetByte) & 255) << 8
                     | Session_ME.readKey(messageCollector.session, packetByte2) & 255;
               } else {
                  dataCount = inputValue.readUnsignedShort();
               }

               byte[] data = new byte[dataCount];
               int value = 0;
               int value2 = 0;

               while (value != -1 && value2 < dataCount) {
                  if ((value = inputValue.read(data, value2, dataCount - value2)) > 0) {
                     value2 += value;
                     messageCollector.session.recvByteCount += value2 + 5;
                     int value3 = Session_ME.gI().recvByteCount + Session_ME.gI().sendByteCount;
                     messageCollector.session.strRecvByteCount = value3 / 1024 + "." + value3 % 1024 / 102 + "Kb";
                  }
               }

               if (messageCollector.session.getKeyComplete) {
                  for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
                     data[dataIndex] = Session_ME.readKey(messageCollector.session, data[dataIndex]);
                  }
               }

               message = new Message(command, data);
            } else {
               DataInputStream inputValue2 = inputValue;
               byte command2 = command;
               MessageCollector messageCollector2 = messageCollector;
               int value4 = Session_ME.readKey(messageCollector.session, inputValue2.readByte()) + 128;
               int value5 = Session_ME.readKey(messageCollector2.session, inputValue2.readByte()) + 128;
               int dataLength;
               byte[] data2 = new byte[dataLength = ((Session_ME.readKey(messageCollector2.session, inputValue2.readByte()) + 128 << 8) + value5 << 8) + value4];
               int value6 = 0;
               int value7 = 0;

               while (value6 != -1 && value7 < dataLength) {
                  if ((value6 = inputValue2.read(data2, value7, dataLength - value7)) > 0) {
                     value7 += value6;
                     messageCollector2.session.recvByteCount += value7 + 5;
                     int value8 = Session_ME.gI().recvByteCount + Session_ME.gI().sendByteCount;
                     messageCollector2.session.strRecvByteCount = value8 / 1024 + "." + value8 % 1024 / 102 + "Kb";
                  }
               }

               if (messageCollector2.session.getKeyComplete) {
                  for (int dataIndex2 = 0; dataIndex2 < data2.length; dataIndex2++) {
                     data2[dataIndex2] = Session_ME.readKey(messageCollector2.session, data2[dataIndex2]);
                  }
               }

               message = new Message(command2, data2);
            }

            Message message2 = message;
            if (this.generation != this.session.getConnectionGeneration()) {
               return;
            }

            if (message2 == null) {
               break;
            }

            try {
               if (message2.command == -27) {
                  this.getKey(message2, this.generation);
               } else {
                  this.session.messageHandler.onMessage(message2);
               }
            } catch (Exception exception) {
               exception.printStackTrace();
            }
         }
      } catch (Exception ignoredException) {
      }

      if (this.generation == this.session.getConnectionGeneration()) {
         if (this.session.connected) {
            if (this.session.messageHandler != null) {
               if (System.currentTimeMillis() - this.session.connectedAt > 500L) {
                  this.session.messageHandler.onDisconnected(this.session.isMainSession);
               } else {
                  this.session.messageHandler.onConnectionFail(this.session.isMainSession);
               }
            }

            if (this.generation == this.session.getConnectionGeneration() && Session_ME.getConnection(this.session) != null) {
               Session_ME.disconnectSession(this.session);
            }
         }
      }
   }

   private void getKey(Message message, long longValue) throws java.io.IOException {
      if (longValue == this.session.getConnectionGeneration()) {
         int dataLength;
         byte[] key = new byte[dataLength = message.reader().readByte()];

         for (int keyIndex = 0; keyIndex < dataLength; keyIndex++) {
            key[keyIndex] = message.reader().readByte();
         }

         for (int keyIndex2 = 0; keyIndex2 < key.length - 1; keyIndex2++) {
            key[keyIndex2 + 1] = (byte)(key[keyIndex2 + 1] ^ key[keyIndex2]);
         }

         String packetText = message.reader().readUTF();
         dataLength = message.reader().readInt();
         boolean condition = message.reader().readByte() != 0;
         Res.outz(
            "[SECONDARY] handshake endpoint="
               + packetText
               + ":"
               + dataLength
               + " serverEnabled="
               + condition
               + " mainSession="
               + this.session.isMainSession
               + " disabledForced=true"
         );
         synchronized (this.session) {
            if (longValue != Session_ME.connectionGeneration(this.session) || Session_ME.isClosed(this.session)) {
               return;
            }

            this.session.key = key;
            if (this.session.isMainSession) {
               GameMidlet.IP2 = null;
               GameMidlet.PORT2 = 0;
               GameMidlet.isConnect2 = false;
               Res.outz("[SECONDARY] disabled reason=forced off; serverEnabled=" + condition);
            } else {
               Res.outz("[SECONDARY] handshake endpoint ignored on secondary session");
            }

            this.session.getKeyComplete = true;
            Session_ME.cancelWatchdogForGeneration(this.session, longValue);
            if (this.session.isMainSession) {
               ServerListScreen.testConnect = 2;
            }
         }

         if (this.session.isMainSession && longValue == this.session.getConnectionGeneration()) {
            Session_ME.gI2().close();
         }

         System.out.println("====> getKey " + this.session.getKeyComplete + " co nect 2 is false (forced off)");
      }
   }
}
