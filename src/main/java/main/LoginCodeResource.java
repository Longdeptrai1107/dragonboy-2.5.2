package main;

import java.io.IOException;
import java.io.InputStream;
import nro.Res;

public final class LoginCodeResource {
   private static String cachedCode = "";
   private static boolean loaded;

   private LoginCodeResource() {
   }

   public static synchronized String getCode() {
      if (!loaded) {
         InputStream resourceAsStream = null;

         try {
            if ((resourceAsStream = "".getClass().getResourceAsStream("/login_codes_generated.txt")) != null) {
               StringBuffer stringBuffer = new StringBuffer(96);

               int value;
               while ((value = resourceAsStream.read()) != -1) {
                  if (value != 13) {
                     if (value == 10) {
                        parseCodeLine(stringBuffer.toString());
                        stringBuffer.setLength(0);
                     } else if (value < 128) {
                        stringBuffer.append((char)value);
                     }
                  }
               }

               if (stringBuffer.length() > 0) {
                  parseCodeLine(stringBuffer.toString());
               }
            }
         } catch (IOException iOException) {
            Res.err("Unable to read login_codes_generated.txt: " + iOException.toString());
         } finally {
            if (resourceAsStream != null) {
               try {
                  resourceAsStream.close();
               } catch (IOException ignoredIOException) {
               }
            }
         }

         loaded = true;
      }

      return cachedCode;
   }

   private static void parseCodeLine(String cachedCodeValue) {
      int value;
      if ((value = cachedCodeValue.indexOf(58)) >= 0) {
         String text;
         boolean condition;
         if ((text = cachedCodeValue = cachedCodeValue.substring(value + 1).trim()) != null && text.length() == 32) {
            int characterIndex = 0;

            while (true) {
               if (characterIndex >= text.length()) {
                  condition = true;
                  break;
               }

               char characterValue;
               if (((characterValue = text.charAt(characterIndex)) < '0' || characterValue > '9') && (characterValue < 'a' || characterValue > 'f') && (characterValue < 'A' || characterValue > 'F')) {
                  condition = false;
                  break;
               }

               characterIndex++;
            }
         } else {
            condition = false;
         }

         if (condition) {
            cachedCode = cachedCodeValue;
         }
      }
   }
}
