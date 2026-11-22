package nro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class ImageSource {
   private byte version;
   public String id;
   public static MyVector vSource = new MyVector("");
   private static MyVector vRms = new MyVector("");

   public ImageSource(String iD, byte versionValue) {
      this.id = iD;
      this.version = versionValue;
   }

   public static void checkRMS() {
      MyVector iD = new MyVector("");
      byte[] storedImageSource;
      if ((storedImageSource = Rms.loadRMS("ImageSource")) == null) {
         Service.gI().imageSource(iD);
      } else {
         vRms = new MyVector("");
         ByteArrayInputStream inputBytes = new ByteArrayInputStream(storedImageSource);
         DataInputStream input = new DataInputStream(inputBytes);

         try {
            short textCount;
            String[] texts = new String[textCount = input.readShort()];
            byte[] data = new byte[textCount];

            for (int index = 0; index < textCount; index++) {
               texts[index] = input.readUTF();
               data[index] = input.readByte();
               vRms.addElement(new ImageSource(texts[index], data[index]));
            }

            input.close();
         } catch (IOException iOException) {
            iOException.printStackTrace();
         }

         Res.outz("vS size= " + vSource.size() + " vRMS size= " + vRms.size());
         Service.gI().imageSource(iD);
      }
   }

   public static void saveRMS() {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      DataOutputStream output = new DataOutputStream(buffer);

      try {
         output.writeShort(vSource.size());

         for (int sourceIndex = 0; sourceIndex < vSource.size(); sourceIndex++) {
            output.writeUTF(((ImageSource)vSource.elementAt(sourceIndex)).id);
            output.writeByte(((ImageSource)vSource.elementAt(sourceIndex)).version);
         }

         Rms.saveRMS("ImageSource", buffer.toByteArray());
         output.close();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }
}
