package nro;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class mFont {
   private int space;
   private int height;
   private Image imgFont;
   private String strFont;
   private int[][] fImages;
   private static String str = " 0123456789+-*='_?.,<>/[]{}!@#$%^&*():aáàảãạâấầẩẫậăắằẳẵặbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvxyýỳỷỹỵzwAÁÀẢÃẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÉÈẺẼẸÊẾỀỂỄỆFGHIÍÌỈĨỊJKLMNOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢPQRSTUÚÙỦŨỤƯỨỪỬỮỰVXYÝỲỶỸỴZW";
   public static mFont tahoma_7b_red = new mFont(str, "/myfont/tahoma_7b_red.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_blue = new mFont(str, "/myfont/tahoma_7b_blue.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_white = new mFont(str, "/myfont/tahoma_7b_white.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_yellow = new mFont(str, "/myfont/tahoma_7b_yellow.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_yellowSmall = new mFont(str, "/myfont/tahoma_7b_yellow.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_dark = new mFont(str, "/myfont/tahoma_7b_brown.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_green2 = new mFont(str, "/myfont/tahoma_7b_green2.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_green = new mFont(str, "/myfont/tahoma_7b_green.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_focus = new mFont(str, "/myfont/tahoma_7b_focus.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7b_unfocus = new mFont(str, "/myfont/tahoma_7b_unfocus.png", "/myfont/tahoma_7b", 0);
   public static mFont tahoma_7 = new mFont(str, "/myfont/tahoma_7.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_blue1 = new mFont(str, "/myfont/tahoma_7_blue1.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_green2 = new mFont(str, "/myfont/tahoma_7_green2.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_yellow = new mFont(str, "/myfont/tahoma_7_yellow.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_grey = new mFont(str, "/myfont/tahoma_7_grey.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_red = new mFont(str, "/myfont/tahoma_7_red.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_blue = new mFont(str, "/myfont/tahoma_7_blue.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_green = new mFont(str, "/myfont/tahoma_7_green.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_7_white = new mFont(str, "/myfont/tahoma_7_white.png", "/myfont/tahoma_7", 0);
   public static mFont tahoma_8b = new mFont(str, "/myfont/tahoma_8b.png", "/myfont/tahoma_8b", -1);
   private static mFont number_yellow = new mFont(" 0123456789+-", "/myfont/number_yellow.png", "/myfont/number", 0);
   private static mFont number_red = new mFont(" 0123456789+-", "/myfont/number_red.png", "/myfont/number", 0);
   private static mFont number_green = new mFont(" 0123456789+-", "/myfont/number_green.png", "/myfont/number", 0);
   public static mFont number_gray = new mFont(" 0123456789+-", "/myfont/number_gray.png", "/myfont/number", 0);
   private static mFont number_orange = new mFont(" 0123456789+-", "/myfont/number_orange.png", "/myfont/number", 0);
   public static mFont bigNumber_red = number_red;
   public static mFont bigNumber_While = tahoma_7b_white;
   public static mFont bigNumber_yellow = number_yellow;
   public static mFont bigNumber_green = number_green;
   public static mFont bigNumber_orange = number_orange;
   public static mFont bigNumber_blue = tahoma_7_blue1;
   public static mFont nameFontRed = tahoma_7_red;
   public static mFont nameFontYellow = tahoma_7_yellow;
   public static mFont nameFontGreen = tahoma_7_green;
   public static mFont tahoma_7_greySmall = tahoma_7_grey;
   public static mFont tahoma_7b_yellowSmall2 = tahoma_7_yellow;
   public static mFont tahoma_7b_green2Small = tahoma_7b_green2;
   public static mFont tahoma_7_whiteSmall = tahoma_7_white;
   public static mFont tahoma_7b_greenSmall = tahoma_7b_green;
   public static mFont tahoma_7_blue1Small = tahoma_7_blue1;
   private String pathImage;

   private mFont(String strFontValue, String pathImageValue, String pathData, int spaceValue) {
      try {
         this.strFont = strFontValue;
         this.space = spaceValue;
         this.pathImage = pathImageValue;
         DataInputStream input = null;
         mFont font = this;
         this.imgFont = mSystem.loadImage(font.pathImage);

         try {
            input = new DataInputStream(SmallDart.openResourceStream(pathData));
            this.fImages = new int[input.readShort()][];

            for (int fImageIndex = 0; fImageIndex < this.fImages.length; fImageIndex++) {
               this.fImages[fImageIndex] = new int[4];
               this.fImages[fImageIndex][0] = input.readShort();
               this.fImages[fImageIndex][1] = input.readShort();
               this.fImages[fImageIndex][2] = input.readShort();
               this.fImages[fImageIndex][3] = input.readShort();
               int heightValue = this.fImages[fImageIndex][3];
               this.height = heightValue;
            }
         } catch (Exception ignoredException) {
            try {
               input.close();
            } catch (IOException iOException) {
               iOException.printStackTrace();
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         System.out.println("paht data:" + pathImageValue);
      }
   }

   public final int getHeight() {
      return this.height;
   }

   public final int getWidth(String text) {
      int width = 0;

      for (int characterIndex = 0; characterIndex < text.length(); characterIndex++) {
         int fImageIndex;
         if ((fImageIndex = this.strFont.indexOf(text.charAt(characterIndex))) == -1) {
            fImageIndex = 0;
         }

         width += this.fImages[fImageIndex][2] + this.space;
      }

      return width;
   }

   public final void drawString(mGraphics graphics, String st, int x, int y, int align) {
      int characterCount = st.length();
      if (align == 0) {
         align = x;
      } else if (align == 1) {
         align = x - this.getWidth(st);
      } else {
         align = x - (this.getWidth(st) >> 1);
      }

      for (int characterIndex = 0; characterIndex < characterCount; characterIndex++) {
         if ((x = this.strFont.indexOf(st.charAt(characterIndex))) == -1) {
            x = 0;
         }

         if (x >= 0) {
            graphics.drawRegion(
               this.imgFont,
               this.fImages[x][0],
               this.fImages[x][1],
               this.fImages[x][2],
               this.fImages[x][3],
               0,
               align,
               y,
               20
            );
         }

         align += this.fImages[x][2] + this.space;
      }
   }

   public final void drawString(mGraphics graphics, String st, int x, int y, int align, mFont font) {
      int characterCount = st.length();
      if (align == 0) {
         align = x;
      } else if (align == 1) {
         align = x - this.getWidth(st);
      } else {
         align = x - (this.getWidth(st) >> 1);
      }

      for (int characterIndex = 0; characterIndex < characterCount; characterIndex++) {
         if ((x = this.strFont.indexOf(st.charAt(characterIndex))) == -1) {
            x = 0;
         }

         if (x >= 0) {
            if (!GameCanvas.lowGraphic) {
               graphics.drawRegion(
                  font.imgFont,
                  this.fImages[x][0],
                  this.fImages[x][1],
                  this.fImages[x][2],
                  this.fImages[x][3],
                  0,
                  align + 1,
                  y,
                  20
               );
               graphics.drawRegion(
                  font.imgFont,
                  this.fImages[x][0],
                  this.fImages[x][1],
                  this.fImages[x][2],
                  this.fImages[x][3],
                  0,
                  align,
                  y + 1,
                  20
               );
            }

            graphics.drawRegion(
               this.imgFont,
               this.fImages[x][0],
               this.fImages[x][1],
               this.fImages[x][2],
               this.fImages[x][3],
               0,
               align,
               y,
               20
            );
         }

         align += this.fImages[x][2] + this.space;
      }
   }

   public final String[] splitFontArray(String src, int lineWidth) {
      int lineWidth2 = lineWidth;
      String src2 = src;
      mFont font = this;
      MyVector entries = new MyVector("vLine");
      String text = "";

      for (int characterIndex = 0; characterIndex < src2.length(); characterIndex++) {
         if (src2.charAt(characterIndex) != '\n' && src2.charAt(characterIndex) != '\b') {
            text = text + src2.charAt(characterIndex);
            if (font.getWidth(text) > lineWidth2) {
               int characterIndex2 = text.length() - 1;

               while (characterIndex2 >= 0 && text.charAt(characterIndex2) != ' ') {
                  characterIndex2--;
               }

               if (characterIndex2 < 0) {
                  characterIndex2 = text.length() - 1;
               }

               entries.addElement(text.substring(0, characterIndex2));
               characterIndex = characterIndex - (text.length() - characterIndex2) + 1;
               text = "";
            }

            if (characterIndex == src2.length() - 1 && !text.trim().equals("")) {
               entries.addElement(text);
            }
         } else {
            entries.addElement(text);
            text = "";
         }
      }

      MyVector entries2 = entries;
      String[] texts = new String[entries.size()];

      for (int index = 0; index < entries2.size(); index++) {
         texts[index] = entries2.elementAt(index).toString();
      }

      return texts;
   }

   public final void drawStringBorder(mGraphics graphics, String st, int x, int y, int align) {
      this.drawString(graphics, st, x, y, 2);
   }

   public final void drawStringBorder(mGraphics graphics, String st, int x, int y, int align, mFont font2) {
      this.drawString(graphics, st, x, y, align, font2);
   }
}
