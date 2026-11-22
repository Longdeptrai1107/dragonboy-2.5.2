package nro;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import main.GameCanvas;
import main.GameMidlet;

public final class TField implements IActionListener {
   public int x;
   public int y;
   public int width;
   public int height;
   public boolean isFocus;
   private boolean field_l_94ba76e4 = false;
   private static int typeXpeed = 2;
   private static final int[] MAX_TIME_TO_CONFIRM_KEY = new int[]{18, 14, 11, 9, 6, 4, 2};
   private static int CARET_HEIGHT = 0;
   private static String[] print = new String[]{
      " 0",
      ".,@?!_1\"/$-():*+<=>;%&~#%^&*{}[];'/1",
      "abc2áàảãạâấầẩẫậăắằẳẵặ2",
      "def3đéèẻẽẹêếềểễệ3",
      "ghi4íìỉĩị4",
      "jkl5",
      "mno6óòỏõọôốồổỗộơớờởỡợ6",
      "pqrs7",
      "tuv8úùủũụưứừửữự8",
      "wxyz9ýỳỷỹỵ9",
      "*",
      "#"
   };
   private static String[] printA = new String[]{"0", "1", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9", "0", "0"};
   public String title = "";
   private String text = "";
   private String passwordText = "";
   private String paintedText = "";
   private int caretPos = 0;
   private int counter = 0;
   private int maxTextLenght = 500;
   private int offsetX = 0;
   private int lastKey = -1984;
   private int keyInActiveState = 0;
   private int indexOfActiveChar = 0;
   private int MAX_SHOW_CARET_COUNER = 10;
   private int inputType = 0;
   public static boolean isQwerty;
   private static int typingModeAreaWidth;
   private int mode = 0;
   private static int changeModeKey = 11;
   public boolean justReturnFromTextBox;
   public String name = "";
   public nro.Command cmdClear;
   private static Image eraseIcon = mSystem.loadImage("/mainImage/myTexture2der.png");
   private boolean resetClipBeforePaint = true;
   public boolean isPaintCarret = true;
   private static Image imgTf = mSystem.loadImage("/mainImage/myTexture2dtf.png");

   static {
      String[] texts = new String[]{"abc", "Abc", "ABC", "123"};
      int[][] intValuesByRow = new int[][]{
         {32, 48},
         {49, 69},
         {50, 84},
         {51, 85},
         {52, 68},
         {53, 71},
         {54, 74},
         {55, 67},
         {56, 66},
         {57, 77},
         {42, 128},
         {35, 137},
         {33, 113},
         {63, 97},
         {64, 121, 122},
         {46, 111},
         {44, 108}
      };
   }

   public static void openTouchKeyboard() {
   }

   public final void doChangeToTextBox() {
      TextBox textBox;
      (textBox = new TextBox(this.name, "", this.maxTextLenght, 0)).addCommand(new Command(mResources.OK, 4, 0));
      textBox.addCommand(new Command(mResources.CANCEL, 3, 0));
      textBox.setCommandListener(new TextBoxCommandListener(this, textBox));

      try {
         if (this.inputType == 2) {
            textBox.setConstraints(65536);
         } else if (this.inputType == 1) {
            textBox.setConstraints(2);
         } else {
            textBox.setConstraints(0);
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      textBox.setString(this.text);
      textBox.setMaxSize(this.maxTextLenght);
      Display.getDisplay(GameMidlet.instance).setCurrent(textBox);
   }

   public TField() {
      this.text = "";
      TField tField = this;
      CARET_HEIGHT = mFont.tahoma_8b.getHeight() + 1;
      tField.cmdClear = new nro.Command(mResources.DELETE, tField, 1000, null);
      typingModeAreaWidth = 0;
      if (Rms.loadRMSInt("qwerty") == 1) {
         isQwerty = true;
      }
   }

   public final void clear() {
      if (this.caretPos > 0 && this.text.length() > 0) {
         this.text = this.text.substring(0, this.caretPos - 1)
            + this.text.substring(this.caretPos, this.text.length());
         this.caretPos--;
         this.setOffset();
         this.setPasswordTest();
      }
   }

   private void setOffset() {
      if (this.inputType == 2) {
         this.paintedText = this.passwordText;
      } else {
         this.paintedText = this.text;
      }

      if (this.offsetX < 0
         && mFont.tahoma_8b.getWidth(this.paintedText) + this.offsetX < this.width - 6 - 13) {
         this.offsetX = this.width - 10 - mFont.tahoma_8b.getWidth(this.paintedText);
      }

      if (this.offsetX + mFont.tahoma_8b.getWidth(this.paintedText.substring(0, this.caretPos)) <= 0) {
         this.offsetX = -mFont.tahoma_8b.getWidth(this.paintedText.substring(0, this.caretPos));
         this.offsetX += 40;
      } else if (this.offsetX + mFont.tahoma_8b.getWidth(this.paintedText.substring(0, this.caretPos))
         >= this.width - 22) {
         this.offsetX = this.width
            - 10
            - mFont.tahoma_8b.getWidth(this.paintedText.substring(0, this.caretPos))
            - 12;
      }

      if (this.offsetX > 0) {
         this.offsetX = 0;
      }
   }

   private void keyPressedAscii(int keyCode) {
      if (keyCode == 432) {
         keyCode = 119;
      }

      if (this.inputType != 2 && this.inputType != 3 || keyCode >= 48 && keyCode <= 57 || keyCode >= 65 && keyCode <= 90 || keyCode >= 97 && keyCode <= 122) {
         if (this.text.length() < this.maxTextLenght) {
            String textValue = this.text.substring(0, this.caretPos) + (char)keyCode;
            if (this.caretPos < this.text.length()) {
               textValue = textValue + this.text.substring(this.caretPos, this.text.length());
            }

            this.text = textValue;
            this.caretPos++;
            this.setPasswordTest();
            this.setOffset();
         }
      }
   }

   public final boolean keyPressed(int keyCode) {
      if (keyCode != 8 && keyCode != -8 && keyCode != 204) {
         if (keyCode >= 65 && keyCode <= 122 && !isQwerty) {
            isQwerty = true;
            typingModeAreaWidth = 0;
            Rms.saveRMSInt("qwerty", 1);
         }

         if (isQwerty) {
            if (keyCode == 45) {
               if (keyCode == this.lastKey && this.keyInActiveState < MAX_TIME_TO_CONFIRM_KEY[typeXpeed]) {
                  this.text = this.text.substring(0, this.caretPos - 1) + '_';
                  this.paintedText = this.text;
                  this.setPasswordTest();
                  this.setOffset();
                  this.lastKey = -1984;
                  return false;
               }

               this.lastKey = 45;
            }

            if (keyCode >= 32) {
               this.keyPressedAscii(keyCode);
               return false;
            }
         }

         if (keyCode == changeModeKey) {
            this.mode++;
            if (this.mode > 3) {
               this.mode = 0;
            }

            this.keyInActiveState = 1;
            this.lastKey = keyCode;
            return false;
         } else {
            if (keyCode == 42) {
               keyCode = 58;
            }

            if (keyCode == 35) {
               keyCode = 59;
            }

            if (keyCode < 48 || keyCode > 59) {
               this.indexOfActiveChar = 0;
               this.lastKey = -1984;
               if (keyCode == 14) {
                  if (this.caretPos > 0) {
                     this.caretPos--;
                     this.setOffset();
                     this.MAX_SHOW_CARET_COUNER = 10;
                     return false;
                  }
               } else if (keyCode == 15) {
                  if (this.caretPos < this.text.length()) {
                     this.caretPos++;
                     this.setOffset();
                     this.MAX_SHOW_CARET_COUNER = 10;
                     return false;
                  }
               } else {
                  if (keyCode == 19) {
                     this.clear();
                     return false;
                  }

                  this.lastKey = keyCode;
               }
            } else if (this.inputType == 0 || this.inputType == 2 || this.inputType == 3) {
               int lastKeyValue = keyCode;
               TField tField = this;
               String[] printValue;
               if (this.inputType != 2 && tField.inputType != 3) {
                  printValue = print;
               } else {
                  printValue = printA;
               }

               if (lastKeyValue == tField.lastKey) {
                  tField.indexOfActiveChar = (tField.indexOfActiveChar + 1) % printValue[lastKeyValue - 48].length();
                  char characterValue = printValue[lastKeyValue - 48].charAt(tField.indexOfActiveChar);
                  if (tField.mode == 0) {
                     characterValue = Character.toLowerCase(characterValue);
                  } else if (tField.mode == 1) {
                     characterValue = Character.toUpperCase(characterValue);
                  } else if (tField.mode == 2) {
                     characterValue = Character.toUpperCase(characterValue);
                  } else {
                     characterValue = printValue[lastKeyValue - 48].charAt(printValue[lastKeyValue - 48].length() - 1);
                  }

                  String textValue = tField.text.substring(0, tField.caretPos - 1) + characterValue;
                  if (tField.caretPos < tField.text.length()) {
                     textValue = textValue + tField.text.substring(tField.caretPos, tField.text.length());
                  }

                  tField.text = textValue;
                  tField.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
                  tField.setPasswordTest();
               } else if (tField.text.length() < tField.maxTextLenght) {
                  if (tField.mode == 1 && tField.lastKey != -1984) {
                     tField.mode = 0;
                  }

                  tField.indexOfActiveChar = 0;
                  char characterValue2 = printValue[lastKeyValue - 48].charAt(tField.indexOfActiveChar);
                  if (tField.mode == 0) {
                     characterValue2 = Character.toLowerCase(characterValue2);
                  } else if (tField.mode == 1) {
                     characterValue2 = Character.toUpperCase(characterValue2);
                  } else if (tField.mode == 2) {
                     characterValue2 = Character.toUpperCase(characterValue2);
                  } else {
                     characterValue2 = printValue[lastKeyValue - 48].charAt(printValue[lastKeyValue - 48].length() - 1);
                  }

                  String textValue2 = tField.text.substring(0, tField.caretPos) + characterValue2;
                  if (tField.caretPos < tField.text.length()) {
                     textValue2 = textValue2 + tField.text.substring(tField.caretPos, tField.text.length());
                  }

                  tField.text = textValue2;
                  tField.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
                  tField.caretPos++;
                  tField.setPasswordTest();
                  tField.setOffset();
               }

               tField.lastKey = lastKeyValue;
            } else if (this.inputType == 1) {
               this.keyPressedAscii(keyCode);
               this.keyInActiveState = 1;
            }

            return true;
         }
      } else {
         this.clear();
         return true;
      }
   }

   public final void paint(mGraphics g) {
      if (this.resetClipBeforePaint) {
         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      }

      boolean isFocusValue = this.isFocus;
      if (this.inputType == 2) {
         this.paintedText = this.passwordText;
      } else {
         this.paintedText = this.text;
      }

      int xValue = this.x;
      int deltaY = this.y - 1;
      int widthValue = this.width;
      int heightValue = this.height;
      int value = 6 + this.offsetX + this.x;
      int value2 = this.y + (this.height - mFont.tahoma_8b.getHeight()) / 2;
      String textValue = this.name;
      String textValue2 = this.paintedText;
      int yValue = value2;
      int xValue2 = value;
      int heightValue2 = heightValue;
      int widthValue2 = widthValue;
      int yValue2 = deltaY;
      int xValue3 = xValue;
      boolean isFocusValue2 = isFocusValue;
      mGraphics graphics = g;
      g.setColor(0);
      if (isFocusValue2) {
         graphics.drawRegion(imgTf, 0, 81, 29, 27, 0, xValue3, yValue2, 0);
         graphics.drawRegion(imgTf, 0, 135, 29, 27, 0, xValue3 + widthValue2 - 29, yValue2, 0);
         graphics.drawRegion(imgTf, 0, 108, 29, 27, 0, xValue3 + widthValue2 - 58, yValue2, 0);

         for (int index = 0; index < (widthValue2 - 58) / 29; index++) {
            graphics.drawRegion(imgTf, 0, 108, 29, 27, 0, xValue3 + 29 + index * 29, yValue2, 0);
         }
      } else {
         graphics.drawRegion(imgTf, 0, 0, 29, 27, 0, xValue3, yValue2, 0);
         graphics.drawRegion(imgTf, 0, 54, 29, 27, 0, xValue3 + widthValue2 - 29, yValue2, 0);
         graphics.drawRegion(imgTf, 0, 27, 29, 27, 0, xValue3 + widthValue2 - 58, yValue2, 0);

         for (int index2 = 0; index2 < (widthValue2 - 58) / 29; index2++) {
            graphics.drawRegion(imgTf, 0, 27, 29, 27, 0, xValue3 + 29 + index2 * 29, yValue2, 0);
         }
      }

      graphics.setClip(xValue3 + 3, yValue2 + 1, widthValue2 - 4, heightValue2);
      if (textValue2 != null && !textValue2.equals("")) {
         mFont.tahoma_8b.drawString(graphics, textValue2, xValue2, yValue, 0);
      } else if (textValue != null) {
         if (isFocusValue2) {
            mFont.tahoma_7b_focus.drawString(graphics, textValue, xValue2, yValue + 2, 0);
         } else {
            mFont.tahoma_7b_unfocus.drawString(graphics, textValue, xValue2, yValue + 2, 0);
         }
      }

      g.setColor(0);
      if (this.isFocus && this.isPaintCarret) {
         if (this.keyInActiveState == 0 && (this.MAX_SHOW_CARET_COUNER > 0 || this.counter / 5 % 2 == 0)) {
            g.setColor(7999781);
            g.fillRect(
               7
                  + this.offsetX
                  + this.x
                  + mFont.tahoma_8b.getWidth(this.paintedText.substring(0, this.caretPos))
                  - 1,
               this.y + (this.height - CARET_HEIGHT) / 2 + 1,
               1,
               CARET_HEIGHT
            );
         }

         GameCanvas.resetTrans(g);
         if (this.text != null && this.text.length() > 0 && GameCanvas.isTouch && isFocusValue) {
            g.drawImage(
               eraseIcon, ((int) (this.x + this.width - 13)), ((int) (this.y + this.height / 2 + 1)), 3
            );
         }
      }
   }

   private void setPasswordTest() {
      if (this.inputType == 2) {
         this.passwordText = "";

         for (int textIndex = 0; textIndex < this.text.length(); textIndex++) {
            this.passwordText = this.passwordText + "*";
         }

         if (this.keyInActiveState > 0 && this.caretPos > 0) {
            this.passwordText = this.passwordText.substring(0, this.caretPos - 1)
               + this.text.charAt(this.caretPos - 1)
               + this.passwordText.substring(this.caretPos, this.passwordText.length());
         }
      }
   }

   public final void update() {
      this.isPaintCarret = true;
      this.counter++;
      if (this.keyInActiveState > 0) {
         this.keyInActiveState--;
         if (this.keyInActiveState == 0) {
            this.indexOfActiveChar = 0;
            if (this.mode == 1 && this.lastKey != changeModeKey) {
               this.mode = 0;
            }

            this.lastKey = -1984;
            this.setPasswordTest();
         }
      }

      if (this.MAX_SHOW_CARET_COUNER > 0) {
         this.MAX_SHOW_CARET_COUNER--;
      }

      if (GameCanvas.isPointerJustRelease) {
         TField tField = this;
         if (GameCanvas.isPointerHoldIn(this.x + tField.width - 20, tField.y, 40, tField.height)) {
            TField tField2 = tField;
            tField.text = "";
            tField2.caretPos = 0;
            tField2.setOffset();
            tField2.setPasswordTest();
            tField.isFocus = true;
            return;
         }

         if (GameCanvas.isPointerHoldIn(tField.x, tField.y, tField.width, tField.height)) {
            tField.doChangeToTextBox();
            return;
         }

         tField.isFocus = false;
      }
   }

   public final String getText() {
      return this.text;
   }

   public final void setText(String text) {
      if (text != null) {
         this.lastKey = -1984;
         this.keyInActiveState = 0;
         this.indexOfActiveChar = 0;
         this.text = text;
         this.paintedText = text;
         this.setPasswordTest();
         this.caretPos = text.length();
         this.setOffset();
      }
   }

   public final void setMaxTextLenght(int maxTextLenght) {
      this.maxTextLenght = maxTextLenght;
   }

   public final void setIputType(int iputType) {
      this.inputType = iputType;
      short maxTextLenghtValue = 500;
      this.maxTextLenght = maxTextLenghtValue;
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 1000:
            this.clear();
      }
   }
}
