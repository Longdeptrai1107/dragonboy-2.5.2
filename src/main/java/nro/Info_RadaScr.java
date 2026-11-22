package nro;

import main.GameCanvas;

public final class Info_RadaScr {
   public byte rank;
   public byte amount;
   public byte max_amount;
   public byte typeMonster;
   public int id;
   public int no;
   public int idIcon;
   public String name;
   public String info;
   public byte level;
   public byte isUse;
   public Char charInfo;
   public Mob mobInfo;
   public ItemOption[] itemOption;
   int[] f = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
   int count = 0;
   long timeRequest;
   public ChatPopup cp;
   private MyVector eff = new MyVector("");

   public final void SetInfo(int id, int no, int idIcon, byte rank, byte typeMonster, short templateId, String name, String info, Char charInfo, ItemOption[] itemOption) {
      this.id = id;
      this.no = no;
      this.idIcon = idIcon;
      this.rank = rank;
      this.typeMonster = typeMonster;
      if (templateId != -1) {
         this.mobInfo = new Mob();
         this.mobInfo.templateId = templateId;
      }

      this.name = name;
      this.info = info;
      this.charInfo = charInfo;
      this.itemOption = itemOption;
      this.addItemDetail();
   }

   public final void SetAmount(byte amountValue, byte max_amountValue) {
      this.amount = amountValue;
      this.max_amount = max_amountValue;
   }

   public final void SetLevel(byte level) {
      this.level = level;
      this.addItemDetail();
   }

   public final void SetUse(byte isUse) {
      this.isUse = isUse;
      this.addItemDetail();
   }

   public static Info_RadaScr GetInfo(MyVector vec, int id) {
      if (vec != null) {
         for (int vecIndex = 0; vecIndex < vec.size(); vecIndex++) {
            Info_RadaScr vec2;
            if ((vec2 = (Info_RadaScr)vec.elementAt(vecIndex)) != null && vec2.id == id) {
               return vec2;
            }
         }
      }

      return null;
   }

   public final void addItemDetail() {
   String stringSlot1;
   this.cp = new ChatPopup();
      ChatPopup cpValue = null;
      String src = "";
      src = src + "\n|6|" + this.info;
      String text = src + "\n--";
      if (this.itemOption != null) {
         int index = 0;

         while (true) {
            int index2 = 0;

            for (int itemOptionIndex = 0; itemOptionIndex < this.itemOption.length; itemOptionIndex++) {
               if (!this.itemOption[itemOptionIndex].getOptionString().equals("") && index == this.itemOption[itemOptionIndex].activeCard) {
                  index2++;
                  break;
               }
            }

            if (index2 == 0) {
               break;
            }

            if (index == 0) {
               text = text + "\n|6|2|--" + mResources.unlock + "--";
            } else {
               text = text + "\n|6|2|--" + mResources.equip + " Lv." + index + "--";
            }

            for (int itemOptionIndex2 = 0; itemOptionIndex2 < this.itemOption.length; itemOptionIndex2++) {
               if (!(stringSlot1 = this.itemOption[itemOptionIndex2].getOptionString()).equals("") && index == this.itemOption[itemOptionIndex2].activeCard) {
                  String text2 = "1";
                  if (this.level == 0) {
                     text2 = "2";
                  } else if (this.itemOption[itemOptionIndex2].activeCard != 0) {
                     if (this.isUse == 0) {
                        text2 = "2";
                     } else if (this.level < this.itemOption[itemOptionIndex2].activeCard) {
                        text2 = "2";
                     }
                  }

                  text = text + "\n|" + text2 + "|1|" + stringSlot1;
               }
            }

            if (index2 != 0) {
               index++;
            }
         }
      }

      src = text;
      cpValue = this.cp;
      this.cp.sayWidth = RadarScr.wText;
      cpValue.cx = RadarScr.xText;
      cpValue.says = mFont.tahoma_7.splitFontArray(src, cpValue.sayWidth - 8);
      cpValue.delay = 10000000;
      cpValue.c = null;
      cpValue.ch = cpValue.says.length * 12;
      cpValue.cy = RadarScr.yText;
      cpValue.strY = 10;
      cpValue.lim = cpValue.ch - RadarScr.hText;
      if (cpValue.lim < 0) {
         cpValue.lim = 0;
      }
   }

   public final void paintEff(mGraphics graphics, int x, int y) {
      Info_RadaScr info_RadaScr = this;
      if (this.amount == info_RadaScr.max_amount && info_RadaScr.eff.size() == 0) {
         int effCount = Res.random(1, 5);

         for (int effIndex = 0; effIndex < effCount; effIndex++) {
            Position effValue;
            (effValue = new Position()).x = Res.random(5, 25);
            effValue.y = Res.random(5, 25);
            effValue.v = effIndex * Res.random(0, 8);
            effValue.w = 0;
            effValue.anchor = -1;
            info_RadaScr.eff.addElement(effValue);
         }
      }

      for (int effIndex2 = 0; effIndex2 < this.eff.size(); effIndex2++) {
         Position effValue2;
         if ((effValue2 = (Position)this.eff.elementAt(effIndex2)) != null) {
            if (effValue2.w < effValue2.v) {
               effValue2.w++;
            }

            if (effValue2.w >= effValue2.v) {
               effValue2.anchor = GameCanvas.gameTick / 3 % (RadarScr.fraEff.nFrame + 1);
               if (effValue2.anchor >= RadarScr.fraEff.nFrame) {
                  this.eff.removeElementAt(effIndex2);
                  effIndex2--;
               } else {
                  RadarScr.fraEff.drawFrame(effValue2.anchor, x + effValue2.x, y + effValue2.y, 0, 3, graphics);
               }
            }
         }
      }
   }
}
