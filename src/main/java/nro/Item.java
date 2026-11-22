package nro;

import main.GameCanvas;

public final class Item {
   public ItemOption[] itemOption;
   public ItemTemplate template;
   public MyVector options;
   public int itemId;
   public int playerId;
   public boolean isSelect;
   public int indexUI;
   public int quantity;
   public int quantilyToBuy;
   public long powerRequire;
   public boolean isLock;
   public int sys;
   public int upgrade;
   public int buyCoin;
   public int buyCoinLock;
   public int buyGold;
   public int buyGoldLock;
   public int saleCoinLock;
   public int buySpec;
   public int buyRuby;
   public short iconSpec = -1;
   public byte buyType = -1;
   public int typeUI;
   public boolean isExpires;
   public boolean isBuySpec = false;
   public String reason = "";
   public int compare;
   public byte isMe;
   public boolean newItem;
   public int headTemp = -1;
   public int bodyTemp = -1;
   public int legTemp = -1;
   public int bagTemp = -1;
   public String nameNguoiKyGui = "";

   public Item() {
      int[] intValues = new int[]{
         0, 0, 0, 0, 600841, 600841, 667658, 667658, 3346944, 3346688, 4199680, 5052928, 3276851, 3932211, 4587571, 5046280, 6684682, 3359744
      };
      int[][] intValuesByRow = new int[][]{
         {18687, 16869, 15052, 13235, 11161, 9344},
         {45824, 39168, 32768, 26112, 19712, 13056},
         {16744192, 15037184, 13395456, 11753728, 10046464, 8404992},
         {13500671, 12058853, 10682572, 9371827, 7995545, 6684800},
         {16711705, 15007767, 13369364, 11730962, 10027023, 8388621}
      };
      intValues = new int[]{2, 1, 1, 1, 1, 1};
   }

   public final void getCompare() {
      this.compare = GameCanvas.panel.getCompare(this);
   }

   public final boolean isHaveOption(int id) {
      for (int itemOptionIndex = 0; itemOptionIndex < this.itemOption.length; itemOptionIndex++) {
         ItemOption itemOptionValue;
         if ((itemOptionValue = this.itemOption[itemOptionIndex]) != null && itemOptionValue.optionTemplate.id == 87) {
            return true;
         }
      }

      return false;
   }

   public final boolean isTypeBody() {
      return this.template.type >= 0 && this.template.type < 6
         || this.template.type == 32
         || this.template.type == 35
         || this.template.type == 11
         || this.template.type == 23;
   }

   public final void setPartTemp(int headTemp, int bodyTemp, int legTemp, int bagTemp) {
      this.headTemp = headTemp;
      this.bodyTemp = bodyTemp;
      this.legTemp = legTemp;
      this.bagTemp = bagTemp;
   }
}
