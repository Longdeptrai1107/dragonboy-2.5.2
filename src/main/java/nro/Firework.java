package nro;

import java.io.DataInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import main.GameCanvas;

public class Firework {
   int w;
   int h;
   int v;
   int x0;
   int x;
   int y;
   int y0;
   int angle;
   int t;
   int cl;
   float a;
   long last;
   long delay;
   boolean act;
   int[] arr_x;
   int[] arr_y;

   public Firework(int x0Value, int y0Value, int vValue, int angleValue, int clValue) {
      this.t = 0;
      this.cl = 16711680;
      this.last = 0L;
      this.delay = 150L;
      this.act = true;
      this.arr_x = new int[2];
      this.arr_y = new int[2];
      this.y0 = y0Value;
      this.x0 = x0Value;
      this.a = 1.0F;
      this.v = vValue;
      this.angle = angleValue;
      this.w = GameCanvas.w;
      this.h = GameCanvas.h;
      this.last = System.currentTimeMillis();

      for (int arr_xIndex = 0; arr_xIndex < 2; arr_xIndex++) {
         this.arr_x[arr_xIndex] = x0Value;
         this.arr_y[arr_xIndex] = y0Value;
      }

      this.cl = clValue;
   }

   public static void Drawline(mGraphics graphics, int xValue, int yValue, int color) {
      graphics.setColor(color);
      graphics.fillRect(xValue, yValue, 1, 2);
   }

   public Firework() {
   }

   public static byte[] readMessageBytes(Message message) {
      try {
         int dataCount;
         if ((dataCount = message.reader().readInt()) > 1) {
            byte[] data = new byte[dataCount];
            message.reader().read(data);
            return data;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      return null;
   }

   public static byte[] readLengthPrefixedBytes(DataInputStream input) {
      try {
         byte[] data = new byte[input.readInt()];
         input.read(data);
         return data;
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }
   }

   public static String replaceAllLiteral(String textPart, String text, String text2) {
      StringBuffer stringBuffer = new StringBuffer();
      int endIndex = 0;

      while ((endIndex = textPart.indexOf(text)) != -1) {
         stringBuffer.append(textPart.substring(0, endIndex) + text2);
         textPart = textPart.substring(endIndex + text.length());
      }

      stringBuffer.append(textPart);
      return stringBuffer.toString();
   }

   public static String getDate(int second) {
      long longValue = second * 1000L;
      Calendar instance;
      (instance = Calendar.getInstance()).setTimeZone(TimeZone.getTimeZone("GMT+7"));
      instance.setTime(new Date(longValue));
      int value = instance.get(11);
      instance.get(12);
      int value2 = instance.get(5);
      int value3 = instance.get(2) + 1;
      second = instance.get(1);
      return value2 + "/" + value3 + "/" + second + " " + value + "h";
   }

   public static String formatCountdownDuration(int value) {
      int value2 = 0;
      if (value > 60) {
         value2 = value / 60;
         value %= 60;
      }

      int value3 = 0;
      if (value2 > 60) {
         value3 = value2 / 60;
         value2 %= 60;
      }

      int value4 = 0;
      if (value3 > 24) {
         value4 = value3 / 24;
         value3 %= 24;
      }

      String result = "";
      if (value4 > 0) {
         result = result + value4;
         result = result + "d";
         result = result + value3 + "h";
      } else if (value3 > 0) {
         result = result + value3;
         result = result + "h";
         result = result + value2 + "'";
      } else {
         if (value2 > 9) {
            result = result + value2;
         } else {
            result = result + "0" + value2;
         }

         String text = result + ":";
         if (value > 9) {
            result = text + value;
         } else {
            result = text + "0" + value;
         }
      }

      return result;
   }

   public static String getMoneys(long longValue) {
      String moneys = "";
      long longValue2 = longValue / 1000L + 1L;

      for (int index = 0; index < longValue2; index++) {
         if (longValue < 1000L) {
            moneys = longValue + moneys;
            break;
         }

         long longValue3;
         if ((longValue3 = longValue % 1000L) == 0L) {
            moneys = ".000" + moneys;
         } else if (longValue3 < 10L) {
            moneys = ".00" + longValue3 + moneys;
         } else if (longValue3 < 100L) {
            moneys = ".0" + longValue3 + moneys;
         } else {
            moneys = "." + longValue3 + moneys;
         }

         longValue /= 1000L;
      }

      return moneys;
   }

   public static String formatElapsedDuration(int value) {
      int value2 = 0;
      if (value > 60) {
         value2 = value / 60;
      }

      int value3 = 0;
      if (value2 > 60) {
         value3 = value2 / 60;
         value2 %= 60;
      }

      int value4 = 0;
      if (value3 > 24) {
         value4 = value3 / 24;
         value3 %= 24;
      }

      String result = "";
      if (value4 > 0) {
         result = result + value4;
         result = result + "d";
         result = result + value3 + "h";
      } else if (value3 > 0) {
         result = result + value3;
         result = result + "h";
         result = result + value2 + "'";
      } else {
         if (value2 == 0) {
            value2 = 1;
         }

         result = result + value2;
         result = result + "ph";
      }

      return result;
   }
}
