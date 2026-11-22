package nro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class Message {
   public byte command;
   private ByteArrayOutputStream outputBuffer = null;
   private DataOutputStream dos = null;
   private ByteArrayInputStream inputBuffer = null;
   private DataInputStream dis = null;

   public Message() {
   }

   public Message(byte commandValue) {
      this.command = commandValue;
      this.outputBuffer = new ByteArrayOutputStream(1024);
      this.dos = new DataOutputStream(this.outputBuffer);
   }

   public Message(byte commandValue, byte[] data) {
      this.command = commandValue;
      this.inputBuffer = new ByteArrayInputStream(data);
      this.dis = new DataInputStream(this.inputBuffer);
   }

   public final byte[] getData() {
      return this.outputBuffer.toByteArray();
   }

   public final int readInt3Byte() throws java.io.IOException {
      return this.dis.readInt();
   }

   public final DataInputStream reader() {
      return this.dis;
   }

   public final DataOutputStream writer() {
      return this.dos;
   }

   public final void cleanup() {
      try {
         if (this.dis != null) {
            this.dis.close();
         }

         if (this.dos != null) {
            this.dos.close();
            return;
         }
      } catch (IOException ignoredIOException) {
      }
   }
}
