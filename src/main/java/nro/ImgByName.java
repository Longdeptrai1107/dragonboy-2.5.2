package nro;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Enumeration;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class ImgByName {
   public static MyHashTable hashImagePath = new MyHashTable("h ImgByName");

   public static void SetImage(String name, Image img, byte nFrame) {
      hashImagePath.put(name, new MainImage(img, nFrame));
   }

   public static MainImage getImagePath(String nameImg, MyHashTable hash) {
      MainImage mainImage;
      if ((mainImage = (MainImage)hash.get(nameImg)) == null) {
         mainImage = new MainImage();
         MainImage fromRms;
         if ((fromRms = getFromRms(nameImg)) != null) {
            mainImage.img = fromRms.img;
            mainImage.nFrame = fromRms.nFrame;
         }

         hash.put(nameImg, mainImage);
      }

      mainImage.count = GameCanvas.timeNow / 1000L;
      if (mainImage.img == null) {
         mainImage.timeImageNull--;
         if (mainImage.timeImageNull <= 0) {
            Service.gI().getImgByName(nameImg);
            mainImage.timeImageNull = 200;
         }
      }

      return mainImage;
   }

   private static MainImage getFromRms(String nameImg) {
      nameImg = mGraphics.zoomLevel + "ImgByName_" + nameImg;
      MainImage mainImage = null;
      byte[] storedNameImg;
      if ((storedNameImg = Rms.loadRMS(nameImg)) == null) {
         return null;
      }

      try {
         (mainImage = new MainImage()).nFrame = storedNameImg[0];
         mainImage.img = Image.createImage(storedNameImg, 1, storedNameImg.length - 1);
         return mainImage;
      } catch (Exception ignoredException) {
         return null;
      }
   }

   public static void saveRMS(String nameImg, byte nFrame, byte[] data) {
      String filename = mGraphics.zoomLevel + "ImgByName_" + nameImg;
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      DataOutputStream output = new DataOutputStream(buffer);

      try {
         output.writeByte(nFrame);

         for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
            output.writeByte(data[dataIndex]);
         }

         Rms.saveRMS(filename, buffer.toByteArray());
         output.close();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static void checkDelHash(MyHashTable lookup, int value) {
      MyVector entries = new MyVector("checkDelHash");
      Enumeration enumeration = lookup.keys();

      while (enumeration.hasMoreElements()) {
         String entry = (String)enumeration.nextElement();
         MainImage mainImage = (MainImage)lookup.get(entry);
         if (GameCanvas.timeNow / 1000L - mainImage.count > 600L) {
            entries.addElement(entry);
         }
      }

      for (int entryIndex = 0; entryIndex < entries.size(); entryIndex++) {
         lookup.remove((String)entries.elementAt(entryIndex));
      }
   }
}
