package nro;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;

public final class ImageLoader {
   private static byte[] xorKey = new byte[]{-22, 2, 12, 4, 5, 2, -10};
   private int keyIndex = 0;

   public static Image loadImage(String text) {
      Image image = null;
      String text2 = "/x" + mGraphics.zoomLevel + text;

      try {
         DataInputStream input = null;
         int dataLength;
         byte[] data = new byte[dataLength = (input = new DataInputStream("".getClass().getResourceAsStream(text2))).available()];
         input.read(data, 0, dataLength);
         ImageLoader imageLoader;
         (imageLoader = new ImageLoader()).keyIndex = 0;

         for (int index = 0; index < data.length; index++) {
            byte byteValue = data[index];
            ImageLoader imageLoader2 = imageLoader;
            byte byteValue2 = (byte)(xorKey[imageLoader2.keyIndex++] & 0xFF ^ byteValue & 0xFF);
            if (imageLoader2.keyIndex >= xorKey.length) {
               imageLoader2.keyIndex = imageLoader2.keyIndex % xorKey.length;
            }

            data[index] = byteValue2;
         }

         image = Image.createImage(data, 0, dataLength);
      } catch (IOException ignoredIOException) {
      } catch (IllegalArgumentException ignoredIllegalArgumentException) {
      } catch (NullPointerException ignoredNullPointerException) {
      }

      if (image == null) {
         try {
            image = Image.createImage(text2);
         } catch (IOException ignoredIOException2) {
            return null;
         }
      }

      return image;
   }
}
