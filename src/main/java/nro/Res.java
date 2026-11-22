package nro;

import java.util.Random;

public final class Res {
   private static short[] sinz = new short[]{
      0,
      18,
      36,
      54,
      71,
      89,
      107,
      125,
      143,
      160,
      178,
      195,
      213,
      230,
      248,
      265,
      282,
      299,
      316,
      333,
      350,
      367,
      384,
      400,
      416,
      433,
      449,
      465,
      481,
      496,
      512,
      527,
      543,
      558,
      573,
      587,
      602,
      616,
      630,
      644,
      658,
      672,
      685,
      698,
      711,
      724,
      737,
      749,
      761,
      773,
      784,
      796,
      807,
      818,
      828,
      839,
      849,
      859,
      868,
      878,
      887,
      896,
      904,
      912,
      920,
      928,
      935,
      943,
      949,
      956,
      962,
      968,
      974,
      979,
      984,
      989,
      994,
      998,
      1002,
      1005,
      1008,
      1011,
      1014,
      1016,
      1018,
      1020,
      1022,
      1023,
      1023,
      1024,
      1024
   };
   private static short[] cosz;
   private static int[] tanz;
   private static String[] LOG_CAT = new String[]{
      "<color=#ff0000ff>[  LOG_CAT  ]</color>",
      "<color=#ff0000ff>[LOG_SESSION]</color>",
      "<color=#ffff00ff>[LOG_SESSION]</color>",
      "<color=#ff0000ff>[LOG_MOBILE ]</color>",
      ""
   };
   public static Random r = new Random();
   public static boolean isIcon;
   public static boolean isBig;

   static {
      new MyVector("");
   }

   public static void init() {
      cosz = new short[91];
      tanz = new int[91];

      for (int coszIndex = 0; coszIndex <= 90; coszIndex++) {
         cosz[coszIndex] = sinz[90 - coszIndex];
         if (cosz[coszIndex] == 0) {
            tanz[coszIndex] = Integer.MAX_VALUE;
         } else {
            tanz[coszIndex] = (sinz[coszIndex] << 10) / cosz[coszIndex];
         }
      }
   }

   public static final int sin(int sinzIndex) {
      if ((sinzIndex = fixangle(sinzIndex)) >= 0 && sinzIndex < 90) {
         return sinz[sinzIndex];
      } else if (sinzIndex >= 90 && sinzIndex < 180) {
         return sinz[180 - sinzIndex];
      } else {
         return sinzIndex >= 180 && sinzIndex < 270 ? -sinz[sinzIndex - 180] : -sinz[360 - sinzIndex];
      }
   }

   public static final int cos(int coszIndex) {
      if ((coszIndex = fixangle(coszIndex)) >= 0 && coszIndex < 90) {
         return cosz[coszIndex];
      } else if (coszIndex >= 90 && coszIndex < 180) {
         return -cosz[180 - coszIndex];
      } else {
         return coszIndex >= 180 && coszIndex < 270 ? -cosz[coszIndex - 180] : cosz[360 - coszIndex];
      }
   }

   public static final int angle(int dx, int dy) {
      int tanzIndex;
      if (dx != 0) {
         int value = Math.abs((dy << 10) / dx);

         label44: {
            for (int tanzIndex2 = 0; tanzIndex2 <= 90; tanzIndex2++) {
               if (tanz[tanzIndex2] >= value) {
                  tanzIndex = tanzIndex2;
                  break label44;
               }
            }

            tanzIndex = 0;
         }

         int result = tanzIndex;
         if (dy >= 0 && dx < 0) {
            result = 180 - result;
         }

         if (dy < 0 && dx < 0) {
            result += 180;
         }

         if (dy >= 0 || dx < 0) {
            return result;
         }

         tanzIndex = 360 - result;
      } else {
         tanzIndex = dy > 0 ? 90 : 270;
      }

      return tanzIndex;
   }

   public static final int fixangle(int angle) {
      if (angle >= 360) {
         angle -= 360;
      }

      if (angle < 0) {
         angle += 360;
      }

      return angle;
   }

   public static void paintOnScreenDebug(mGraphics graphics) {
      mFont.tahoma_7b_dark.drawString(graphics, "check Controller= " + Service.logController, 3, 55, 0);
      mFont.tahoma_7b_dark.drawString(graphics, "check Map= " + Service.logMap, 3, 75, 0);
   }

   public static void updateOnScreenDebug() {
      long nowMillis = mSystem.currentTimeMillis();
      if (Service.curCheckController - nowMillis > 5000L) {
         Service.gI().sendCheckController();
      }

      if (Service.curCheckMap - nowMillis > 5000L) {
         Service.gI().sendCheckMap();
      }
   }

   public static String changeString(String str) {
      if (mSystem.clientType != 7) {
         return str;
      }

      if (Char.myCharz() != null
         && Char.myCharz().taskMaint != null
         && Char.myCharz().taskMaint.taskId >= 10) {
         return str;
      }

      if (str.indexOf("Gohan") != -1) {
         str = replace(str, "Gohan", "Hango");
      }

      if (str.indexOf("Gôhan") != -1) {
         str = replace(str, "Gôhan", "Hango");
      }

      if (str.indexOf("Bunma") != -1) {
         str = replace(str, "Bunma", "Mabun");
      }

      if (str.indexOf("Bunman") != -1) {
         str = replace(str, "Bunman", "Mabun");
      }

      if (str.indexOf("Bun ma") != -1) {
         str = replace(str, "Bun ma", "Mabun");
      }

      if (str.indexOf("Đậu thần") != -1) {
         str = replace(str, "Đậu thần", "Cây thần");
      }

      if (str.indexOf("Mabu") != -1) {
         str = replace(str, "Mabu", "Buma");
      }

      if (str.indexOf("Ma bư") != -1) {
         str = replace(str, "Ma bư", "Buma");
      }

      if (str.indexOf("Xayda") != -1) {
         str = replace(str, "Xayda", "Dasay");
      }

      if (str.indexOf("Xay da") != -1) {
         str = replace(str, "Xay da", "Dasay");
      }

      if (str.indexOf("Namếc") != -1) {
         str = replace(str, "Namếc", "Mecda");
      }

      if (str.indexOf("Na mếc") != -1) {
         str = replace(str, "Na mếc", "Mecda");
      }

      if (str.indexOf("Namek") != -1) {
         str = replace(str, "Namek", "Mecda");
      }

      if (str.indexOf("Rồng thần") != -1) {
         str = replace(str, "Rồng thần", "Rồng đất");
      }

      if (str.indexOf("Kame") != -1) {
         str = replace(str, "Kame", "Meka");
      }

      if (str.indexOf("Vegeta") != -1) {
         str = replace(str, "Vegeta", "Tageve");
      }

      if (str.indexOf("Kakalot") != -1) {
         str = replace(str, "Kakalot", "Lotkaka");
      }

      if (str.indexOf("Broly") != -1) {
         str = replace(str, "Broly", "Lybro");
      }

      if (str.indexOf("Ngọc rồng") != -1) {
         str = replace(str, "Ngọc rồng", "Ngọc thần");
      }

      if (str.indexOf("ngọc rồng") != -1) {
         str = replace(str, "ngọc rồng", "Ngọc thần");
      }

      if (str.indexOf("Radic") != -1) {
         str = replace(str, "Radic", "Dicra");
      }

      if (str.indexOf("Ra dic") != -1) {
         str = replace(str, "Ra dic", "Dicra");
      }

      if (str.indexOf("Ra đíc") != -1) {
         str = replace(str, "Ra đíc", "Dicra");
      }

      if (str.indexOf("Cadic") != -1) {
         str = replace(str, "Cadic", "Dicca");
      }

      if (str.indexOf("Ca dic") != -1) {
         str = replace(str, "Ca dic", "Dicca");
      }

      if (str.indexOf("Ca đíc") != -1) {
         str = replace(str, "Ca đíc", "Dicca");
      }

      if (str.indexOf("Quy lão") != -1) {
         str = replace(str, "Quy lão", "Lão");
      }

      if (str.indexOf("quy lão") != -1) {
         str = replace(str, "quy lão", "lão");
      }

      if (str.indexOf("QuyLão") != -1) {
         str = replace(str, "Quy Lão", "Lão");
      }

      if (str.indexOf("Santa") != -1) {
         str = replace(str, "Santa", "Tasan");
      }

      if (str.indexOf("Hạt Mít") != -1) {
         str = replace(str, "Hạt Mít", "Hạt Dẻ");
      }

      if (str.indexOf("Hạt mít") != -1) {
         str = replace(str, "Hạt mít", "Hạt dẻ");
      }

      if (str.indexOf("Tàu bảy bảy") != -1) {
         str = replace(str, "Tàu bảy bảy", "Tàu tàu");
      }

      if (str.indexOf("Uron") != -1) {
         str = replace(str, "Uron", "Onru");
      }

      if (str.indexOf("U ron") != -1) {
         str = replace(str, "U ron", "Onru");
      }

      if (str.indexOf("Urôn") != -1) {
         str = replace(str, "Urôn", "Onru");
      }

      if (str.indexOf("Ngọc Rồng") != -1) {
         str = replace(str, "Ngọc Rồng", "Ngọc thần");
      }

      if (str.indexOf("Ngọc rồng") != -1) {
         str = replace(str, "Ngọc rồng", "Ngọc thần");
      }

      if (str.indexOf("Fide") != -1) {
         str = replace(str, "Fide", "Defi");
      }

      if (str.indexOf("Vegeta") != -1) {
         str = replace(str, "Vegeta", "Tageve");
      }

      if (str.indexOf("Moori") != -1) {
         str = replace(str, "Moori", "Rimoo");
      }

      if (str.indexOf("Aru") != -1) {
         str = replace(str, "Aru", "Ura");
      }

      if (str.indexOf("Kamejoko") != -1) {
         str = replace(str, "Kamejoko", "Kojomeka");
      }

      if (str.indexOf("kamejoko") != -1) {
         str = replace(str, "kamejoko", "kojomeka");
      }

      if (str.indexOf("Kame") != -1) {
         str = replace(str, "Kame", "Meka");
      }

      if (str.indexOf("kame") != -1) {
         str = replace(str, "kame", "meka");
      }

      if (str.indexOf("Masenko") != -1) {
         str = replace(str, "Masenko", "Kosenma");
      }

      if (str.indexOf("Thái Dương Hạ San") != -1) {
         str = replace(str, "Thái Dương Hạ San", "Hạ Dương");
      }

      if (str.indexOf("Solar flare") != -1) {
         str = replace(str, "Solar flare", "Solar");
      }

      if (str.indexOf("Quả cầu kênh khi") != -1) {
         str = replace(str, "Quả cầu kênh khi", "Quả cầu");
      }

      if (str.indexOf("Genki-Dama") != -1) {
         str = replace(str, "Genki-Dama", "Dama");
      }

      if (str.indexOf("Genki-Dama") != -1) {
         str = replace(str, "Genki-Dama", "Dama");
      }

      if (str.indexOf("Makankosappo") != -1) {
         str = replace(str, "Makankosappo", "Oppasoknakam");
      }

      return str;
   }

   public static String replace(String _text, String _searchStr, String _replacementStr) {
      StringBuffer stringBuffer = new StringBuffer();
      int endIndex = _text.indexOf(_searchStr);
      int startIndex = 0;
      int _searchStrCount = _searchStr.length();

      while (endIndex != -1) {
         stringBuffer.append(_text.substring(startIndex, endIndex)).append(_replacementStr);
         startIndex = endIndex + _searchStrCount;
         endIndex = _text.indexOf(_searchStr, startIndex);
      }

      stringBuffer.append(_text.substring(startIndex, _text.length()));
      return stringBuffer.toString();
   }

   public static int random(int value) {
      return r.nextInt(value);
   }

   public static int random(int value, int value2) {
      return value == value2 ? value : value + r.nextInt(value2 - value);
   }

   public static int random_Am_0(int value) {
      int result = 0;

      while (result == 0) {
         result = r.nextInt() % value;
      }

      return result;
   }

   public static int random_Am(int value, int value2) {
      int result = value + r.nextInt(value2 - value);
      byte byteValue = 2;
      if (r.nextInt(byteValue) == 0) {
         result = -result;
      }

      return result;
   }

   public static int distance(int x1, int y1, int x2, int y2) {
      return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
   }

   public static int getDistance(int x, int y) {
      return sqrt(x * x + y * y);
   }

   public static int sqrt(int value) {
      if (value <= 0) {
         return 0;
      }

      int result = (value + 1) / 2;

      int value2;
      do {
         value2 = result;
         result = result / 2 + value / (result * 2);
      } while (Math.abs(value2 - result) > 1);

      return result;
   }

   public static int square(int value, int value2) {
      int result = 1;

      for (int index = 0; index < 2; index++) {
         result *= value;
      }

      return result;
   }

   public static int abs(int value) {
      return value > 0 ? value : -value;
   }

   public static boolean inRect(int x1, int y1, int width, int height, int x2, int y2) {
      return x2 >= x1 && x2 <= x1 + width && y2 >= y1 && y2 <= y1 + height;
   }

   public static void outz(String text, int logIndex) {
   }

   public static void err(String text) {
   }

   public static void outz(String text) {
   }

   public static String[] split(String original, String separator, int count) {
      int endIndex;
      String[] parts;
      if ((endIndex = original.indexOf(separator)) >= 0) {
         parts = split(original.substring(endIndex + separator.length()), separator, count + 1);
      } else {
         parts = new String[count + 1];
         endIndex = original.length();
      }

      parts[count] = original.substring(0, endIndex);
      return parts;
   }

   public static String formatNumber(long number) {
      Object billion = null;
      String result;
      if (number >= 1000000000L) {
         billion = mResources.billion;
         long longValue = number % 1000000000L / 100000000L;
         number /= 1000000000L;
         String text = String.valueOf(number);
         if (longValue > 0L) {
            result = text + "," + longValue + billion;
         } else {
            result = text + billion;
         }
      } else if (number >= 1000000L) {
         billion = mResources.million;
         long longValue2 = number % 1000000L / 100000L;
         number /= 1000000L;
         String text2 = String.valueOf(number);
         if (longValue2 > 0L) {
            result = text2 + "," + longValue2 + billion;
         } else {
            result = text2 + billion;
         }
      } else {
         result = String.valueOf(number);
      }

      return result;
   }

   public static String formatNumber2(long number) {
      Object billion = null;
      String result;
      if (number >= 1000000000L) {
         billion = mResources.billion;
         long longValue = number % 1000000000L / 10000000L;
         number /= 1000000000L;
         String text = String.valueOf(number);
         if (longValue >= 10L) {
            if (longValue % 10L == 0L) {
               longValue /= 10L;
            }

            result = text + "," + longValue + billion;
         } else if (longValue > 0L) {
            result = text + ",0" + longValue + billion;
         } else {
            result = text + billion;
         }
      } else if (number >= 1000000L) {
         billion = mResources.million;
         long longValue2 = number % 1000000L / 10000L;
         number /= 1000000L;
         String text2 = String.valueOf(number);
         if (longValue2 >= 10L) {
            if (longValue2 % 10L == 0L) {
               longValue2 /= 10L;
            }

            result = text2 + "," + longValue2 + billion;
         } else if (longValue2 > 0L) {
            result = text2 + ",0" + longValue2 + billion;
         } else {
            result = text2 + billion;
         }
      } else if (number >= 10000L) {
         billion = "k";
         long longValue3 = number % 1000L / 10L;
         number /= 1000L;
         String text3 = String.valueOf(number);
         if (longValue3 >= 10L) {
            if (longValue3 % 10L == 0L) {
               longValue3 /= 10L;
            }

            result = text3 + "," + longValue3 + billion;
         } else if (longValue3 > 0L) {
            result = text3 + ",0" + longValue3 + billion;
         } else {
            result = text3 + billion;
         }
      } else {
         result = String.valueOf(number);
      }

      return result;
   }
}
