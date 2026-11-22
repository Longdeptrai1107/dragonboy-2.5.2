package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class Panel implements IActionListener, IChatable {
   public boolean isShow;
   public int X;
   public int Y;
   public int W;
   public int H;
   private int ITEM_HEIGHT;
   private int TAB_W;
   private int TAB_W_NEW;
   public int cmtoY;
   public int cmy;
   private int cmdy;
   private int cmvy;
   private int cmyLim;
   private int[] cmyLast;
   private int cmtoX;
   public int cmx;
   private int cmxMap;
   private int cmyMap;
   private int cmxMapLim;
   private int cmyMapLim;
   private int cmyQuest;
   public static Image imgBantay = mSystem.loadImage("/mainImage/myTexture2dbantay.png");
   private static Image imgMap = mSystem.loadImage("/img/map" + TileMap.planetID + ".png");
   public TabClanIcon tabIcon;
   public MyVector vItemCombine = new MyVector("");
   private int moneyGD;
   public int friendMoneyGD;
   private boolean isLock;
   public boolean isFriendLock;
   private boolean isAccept;
   public String topName;
   public ChatTextField chatTField;
   public static String specialInfo;
   public static short spearcialImage;
   public static Image imgStar = mSystem.loadImage("/mainImage/star.png");
   public static Image imgMaxStar = mSystem.loadImage("/mainImage/starE.png");
   public static Image imgStar8 = mSystem.loadImage("/mainImage/star8.png");
   public static Image imgStarCuongHoa = mSystem.loadImage("/mainImage/starCH.png");
   public static Image imgNew = mSystem.loadImage("/mainImage/new.png");
   public static Image imgXu = mSystem.loadImage("/mainImage/myTexture2dimgMoney.png");
   public static Image imgTicket = mSystem.loadImage("/mainImage/ticket12.png");
   public static Image imgLuong = mSystem.loadImage("/mainImage/myTexture2dimgDiamond.png");
   public static Image imgLuongKhoa = mSystem.loadImage("/mainImage/luongkhoa.png");
   private static Image imgUp = mSystem.loadImage("/mainImage/myTexture2dup.png");
   private static Image imgDown = mSystem.loadImage("/mainImage/myTexture2ddown.png");
   private Command left = new Command(mResources.SELECT, 0);
   public int type;
   public int currentTabIndex;
   public int startTabPos;
   private int[] lastTabIndex;
   private String[][] currentTabName;
   private int[] currClanOption;
   public String[][] mainTabName;
   public String[] mapNames;
   public String[] planetNames;
   public static String[] strTool = new String[]{
      mResources.gameInfo,
      mResources.change_flag,
      mResources.change_zone,
      mResources.chat_world,
      mResources.account,
      mResources.option,
      mResources.change_account
   };
   public static String[] strCauhinh = new String[]{
      GameCanvas.isPlaySound ? mResources.turnOffSound : mResources.turnOnSound,
      mResources.increase_vga,
      mResources.analog,
      mGraphics.zoomLevel > 1 ? mResources.x1Screen : mResources.x2Screen
   };
   private static String[] strAccount = new String[]{
      mResources.inventory_Pass, mResources.friend, mResources.enemy, mResources.msg, mResources.charger
   };
   private static String[] strAuto = new String[]{mResources.useGem};
   public static int graphics = 0;
   public String[][] shopTabName;
   public int[] maxPageShop;
   public int[] currPageShop;
   private static String[][] boxTabName = new String[][]{mResources.chestt, mResources.inventory};
   private static String[][] boxCombine = new String[][]{mResources.combine, mResources.inventory};
   private static String[][] boxZone = new String[][]{mResources.zonee};
   private static String[][] boxMap = new String[][]{mResources.mapp};
   private static String[][] boxGD = new String[][]{mResources.inventory, mResources.item_give, mResources.item_receive};
   private static String[][] boxPet = mResources.petMainTab;
   public String[][][] tabName = new String[][][]{
      null,
      null,
      boxTabName,
      boxZone,
      boxMap,
      null,
      null,
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      boxCombine,
      boxGD,
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      boxPet,
      {{""}},
      {{""}},
      {{""}},
      {{""}},
      {{""}}
   };
   private static byte BOX_BAG = 0;
   private static byte BAG_BOX = 1;
   private static byte BODY_BOX = 3;
   private static byte BAG_BODY = 4;
   private static byte BODY_BAG = 5;
   private static byte BAG_PET = 6;
   private static byte PET_BAG = 7;
   public int hasUse;
   public int currentListLength;
   private int[] lastSelect;
   private static int[] mapIdTraidat = new int[]{21, 0, 1, 2, 24, 3, 4, 5, 6, 27, 28, 29, 30, 42, 47, 46};
   private static int[] mapXTraidat = new int[]{39, 42, 105, 93, 61, 93, 142, 165, 210, 100, 165, 220, 233, 10, 125, 125};
   private static int[] mapYTraidat = new int[]{28, 60, 48, 96, 88, 131, 136, 95, 32, 200, 189, 167, 120, 110, 20, 20};
   private static int[] mapIdNamek = new int[]{22, 7, 8, 9, 25, 11, 12, 13, 10, 31, 32, 33, 34, 43};
   private static int[] mapXNamek = new int[]{55, 30, 93, 80, 24, 149, 219, 220, 233, 170, 148, 195, 148, 10};
   private static int[] mapYNamek = new int[]{136, 84, 69, 34, 25, 42, 32, 110, 192, 70, 106, 156, 210, 57};
   private static int[] mapIdSaya = new int[]{23, 14, 15, 16, 26, 17, 18, 20, 19, 35, 36, 37, 38, 44};
   private static int[] mapXSaya = new int[]{90, 95, 144, 234, 231, 122, 176, 158, 205, 54, 105, 159, 231, 27};
   private static int[] mapYSaya = new int[]{10, 43, 20, 36, 69, 87, 112, 167, 160, 151, 173, 207, 194, 29};
   private static int[][] mapId = new int[][]{mapIdTraidat, mapIdNamek, mapIdSaya};
   private static int[][] mapX = new int[][]{mapXTraidat, mapXNamek, mapXSaya};
   private static int[][] mapY = new int[][]{mapYTraidat, mapYNamek, mapYSaya};
   private Item currItem;
   private Clan currClan;
   private ClanMessage currMess;
   private Member currMem;
   public Clan[] clans;
   public MyVector member;
   public MyVector myMember;
   private MyVector logChat = new MyVector("");
   public MyVector vPlayerMenu = new MyVector("");
   public MyVector vFriend = new MyVector("");
   public MyVector vMyGD = new MyVector("");
   public MyVector vFriendGD = new MyVector("");
   public MyVector vTop = new MyVector("");
   public MyVector vEnemy = new MyVector("");
   public MyVector vFlag = new MyVector("");
   public MyVector vPlayerMenu_id = new MyVector("");
   public Command cmdClose;
   public static int WIDTH_PANEL = 240;
   private int position = 0;
   public Char charMenu;
   private boolean isThachDau;
   private int typeShop = -1;
   public static boolean CanNapTien = false;
   public int xScroll;
   public int yScroll;
   public int wScroll;
   public int hScroll;
   public ChatPopup cp;
   private int idIcon;
   private int[] partID;
   private int timeShow = 0;
   public boolean isBoxClan = false;
   public int selected;
   private int cSelected;
   private int newSelected;
   private boolean isClanOption;
   public boolean isSearchClan;
   public boolean isMessage;
   public boolean isViewMember;
   private int pointerDownTime;
   private int pointerDownFirstX;
   private int[] pointerDownLastX = new int[3];
   private boolean pointerIsDowning;
   private boolean isDownWhenRunning;
   private int waitToPerform;
   private int cmRun;
   private int pa1;
   private int pa2 = 0;
   private boolean trans = false;
   private int pX;
   private int pY;
   private int keyTouchCombine;
   private int keyTouchMapButton;
   private boolean justRelease;
   private int keyTouchTab;
   private String[][] clansOption;
   private String clanInfo;
   public String clanReport;
   private Scroll scroll;
   private int cmvx;
   private int cmdx;
   private boolean isSelectPlayerMenu;
   private String[] strStatus;
   private int shopPaintStage;
   private static long[] t_tiemnang = new long[]{
      50000000L,
      250000000L,
      1250000000L,
      5000000000L,
      15000000000L,
      30000000000L,
      45000000000L,
      60000000000L,
      75000000000L,
      90000000000L,
      110000000000L,
      130000000000L,
      150000000000L,
      170000000000L
   };
   private int[] zoneColor;
   public String[] combineInfo;
   public String[] combineTopInfo;
   private int sellectInventory;
   private Item itemInvenNew;
   public static String strWantToBuy = "";
   private int xstart;
   private int ystart;
   private int popupW;
   private int indexRow;
   private int indexRowMax;
   private int indexMenu;
   private int yPaint;
   private int xMap;
   private int yMap;
   private int xMove;
   private int yMove;
   public static boolean isPaintMap = true;
   private boolean isClose;
   private int infoSelect;
   public static MyVector vGameInfo = new MyVector("");
   private static String[] contenInfo;
   public boolean isViewChatServer;
   private int currInfoItem;
   private Char charInfo;
   private boolean isChangeZone;
   private boolean isKiguiXu;
   private boolean isKiguiLuong;
   private int delayKigui;
   public byte combineSuccess;
   public int idNPC;
   public int xS;
   public int yS;
   private int rS;
   private int angleS;
   private int angleO;
   private int iAngleS;
   private int iDotS;
   private int speed;
   private int[] xArgS;
   private int[] yArgS;
   private int[] xDotS;
   private int[] yDotS;
   private int time;
   private int typeCombine;
   private int countUpdate;
   private int countR;
   private int countWait;
   private boolean isSpeedCombine;
   private boolean isCompleteEffCombine;
   private boolean isPaintCombine;
   public boolean isDoneCombine;
   public short iconID1;
   private short iconID2;
   public short iconID3;
   private short[] iconID;
   public String[][] speacialTabName;
   private static int[] sizeUpgradeEff = new int[]{2, 1, 1};
   private static int nsize = 1;
   private static int[][] colorUpgradeEffect = new int[][]{
      {16777215, 15000805, 13487823, 11711155, 9671828, 7895160},
      {61952, 58624, 52224, 45824, 39168, 32768},
      {13500671, 12058853, 10682572, 9371827, 7995545, 6684800},
      {16744192, 15037184, 13395456, 11753728, 10046464, 8404992},
      {37119, 33509, 28108, 24499, 21145, 17536},
      {16776192, 15063040, 12635136, 11776256, 10063872, 8290304},
      {16711680, 15007744, 13369344, 11730944, 10027008, 8388608}
   };
   private Image imgo_0;
   private Image imgo_1;
   private Image imgo_2;
   private Image imgo_3;
   private byte size_tab;
   private boolean isnewInventory;

   static {
      mSystem.loadImage("/mainImage/myTexture2dbtX.png");
      mSystem.loadImage("/mainImage/star9.png");
      int[] intValues = new int[]{2327248, 8982199, 16713222};
      intValues = new int[]{4583423, 16719103, 16714764};
   }

   public Panel() {
      this.pX = GameCanvas.pxLast + this.cmxMap;
      this.pY = GameCanvas.pyLast + this.cmyMap;
      this.keyTouchCombine = -1;
      this.keyTouchMapButton = -1;
      this.keyTouchTab = -1;
      this.clansOption = new String[][]{mResources.findClan, mResources.createClan};
      this.clanInfo = "";
      this.clanReport = "";
      this.strStatus = new String[]{
         mResources.follow,
         mResources.defend,
         mResources.attack,
         mResources.gohome,
         mResources.fusion,
         mResources.fusionForever
      };
      this.shopPaintStage = 0;
      this.zoneColor = new int[]{43520, 14743570, 14155776};
      this.sellectInventory = 0;
      this.itemInvenNew = null;
      this.popupW = 140;
      this.indexRow = -1;
      this.indexMenu = 0;
      this.yPaint = 0;
      this.combineSuccess = -1;
      this.isCompleteEffCombine = true;
      this.isPaintCombine = false;
      this.isDoneCombine = true;
      this.size_tab = 0;
      this.isnewInventory = false;
      this.init();
      this.cmdClose = new Command("", this, 1003, null);
      this.cmdClose.img = mSystem.loadImage("/mainImage/myTexture2dbtX.png");
      this.cmdClose.cmdClosePanel = true;
      this.currItem = null;
   }

   public final void init() {
      this.pX = GameCanvas.pxLast + this.cmxMap;
      this.pY = GameCanvas.pyLast + this.cmyMap;
      this.lastTabIndex = new int[this.tabName.length];

      for (int lastTabIndexIndex = 0; lastTabIndexIndex < this.lastTabIndex.length; lastTabIndexIndex++) {
         this.lastTabIndex[lastTabIndexIndex] = -1;
      }
   }

   private static int getXMap() {
      for (int mapIdIndex = 0; mapIdIndex < mapId[TileMap.planetID].length; mapIdIndex++) {
         if (TileMap.mapID == mapId[TileMap.planetID][mapIdIndex]) {
            return mapX[TileMap.planetID][mapIdIndex];
         }
      }

      return -1;
   }

   private static int getYMap() {
      for (int mapIdIndex = 0; mapIdIndex < mapId[TileMap.planetID].length; mapIdIndex++) {
         if (TileMap.mapID == mapId[TileMap.planetID][mapIdIndex]) {
            return mapY[TileMap.planetID][mapIdIndex];
         }
      }

      return -1;
   }

   private static int getXMapTask() {
      if (Char.myCharz().taskMaint == null) {
         return -1;
      }

      for (int mapIdIndex = 0; mapIdIndex < mapId[TileMap.planetID].length; mapIdIndex++) {
         if (GameScr.mapTasks[Char.myCharz().taskMaint.index] == mapId[TileMap.planetID][mapIdIndex]) {
            return mapX[TileMap.planetID][mapIdIndex];
         }
      }

      return -1;
   }

   private static int getYMapTask() {
      if (Char.myCharz().taskMaint == null) {
         return -1;
      }

      for (int mapIdIndex = 0; mapIdIndex < mapId[TileMap.planetID].length; mapIdIndex++) {
         if (GameScr.mapTasks[Char.myCharz().taskMaint.index] == mapId[TileMap.planetID][mapIdIndex]) {
            return mapY[TileMap.planetID][mapIdIndex];
         }
      }

      return -1;
   }

   private void setType(int position) {
      this.typeShop = -1;
      this.W = WIDTH_PANEL;
      this.H = GameCanvas.h;
      this.X = 0;
      this.Y = 0;
      this.ITEM_HEIGHT = 24;
      this.position = position;
      if (position == 0) {
         this.xScroll = 2;
         this.yScroll = 80;
         this.wScroll = this.W - 4;
         this.hScroll = this.H - 96;
         this.cmx = this.wScroll;
         this.cmtoX = 0;
         this.X = 0;
      } else if (position == 1) {
         this.wScroll = this.W - 4;
         this.xScroll = GameCanvas.w - this.wScroll;
         this.yScroll = 80;
         this.hScroll = this.H - 96;
         this.X = this.xScroll - 2;
         this.cmx = -(GameCanvas.w + this.W);
         this.cmtoX = GameCanvas.w - this.W;
      }

      this.TAB_W = this.W / 5 - 1;
      this.currentTabIndex = 0;
      this.currentTabName = this.tabName[this.type];
      if (this.currentTabName.length < 5) {
         this.TAB_W += 5;
      }

      this.startTabPos = this.xScroll + this.wScroll / 2 - this.currentTabName.length * this.TAB_W / 2;
      this.lastSelect = new int[this.currentTabName.length];
      this.cmyLast = new int[this.currentTabName.length];

      for (int lastSelectIndex = 0; lastSelectIndex < this.currentTabName.length; lastSelectIndex++) {
         this.lastSelect[lastSelectIndex] = GameCanvas.isTouch ? -1 : 0;
      }

      if (this.lastTabIndex[this.type] != -1) {
         this.currentTabIndex = this.lastTabIndex[this.type];
      }

      if (this.currentTabIndex < 0) {
         this.currentTabIndex = 0;
      }

      if (this.currentTabIndex > this.currentTabName.length - 1) {
         this.currentTabIndex = this.currentTabName.length - 1;
      }

      this.scroll = null;
   }

   public final void setTypeMapTrans() {
      this.type = 14;
      this.setType(0);
      Panel panel = this;
      this.ITEM_HEIGHT = 24;
      panel.currentListLength = panel.mapNames.length;
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      panel.cmy = panel.cmtoY = 0;
      panel.selected = GameCanvas.isTouch ? -1 : 0;
      this.cmx = this.cmtoX = 0;
   }

   private void setTabMap() {
      GameScr.gI();
      if (!GameScr.isMapFize()) {
         if (isPaintMap) {
            if (Hint.isOnTask(2, 0)) {
               Hint.isViewMap = true;
               GameScr.info1.addInfo(mResources.go_to_quest, 0);
            }

            if (Hint.isOnTask(3, 0)) {
               Hint.isViewPotential = true;
            }

            this.type = 4;
            this.currentTabName = this.tabName[this.type];
            this.startTabPos = this.xScroll + this.wScroll / 2 - this.currentTabName.length * this.TAB_W / 2;
            this.cmx = this.cmtoX = 0;
            Panel panel = this;
            if (isPaintMap) {
               if (TileMap.lastPlanetId != TileMap.planetID) {
                  Res.outz("LOAD TAM HINH");
                  if (mGraphics.zoomLevel == 1) {
                     SmallImage.imgbig = null;
                     GameScr.isPaint = false;
                     System.gc();
                  }

                  imgMap = mSystem.loadImageRMS("/img/map" + TileMap.planetID + ".png");
                  TileMap.lastPlanetId = TileMap.planetID;
               }

               panel.cmxMap = getXMap() - panel.wScroll / 2;
               panel.cmyMap = getYMap() + panel.yScroll - (panel.yScroll + panel.hScroll / 2);
               panel.pa1 = panel.cmxMap;
               panel.pa2 = panel.cmyMap;
               panel.cmxMapLim = 250 - panel.wScroll;
               panel.cmyMapLim = 220 - panel.hScroll;
               if (panel.cmxMapLim < 0) {
                  panel.cmxMapLim = 0;
               }

               if (panel.cmyMapLim < 0) {
                  panel.cmyMapLim = 0;
               }

               for (int mapIdIndex = 0; mapIdIndex < mapId[TileMap.planetID].length; mapIdIndex++) {
                  if (TileMap.mapID == mapId[TileMap.planetID][mapIdIndex]) {
                     panel.xMove = mapX[TileMap.planetID][mapIdIndex] + panel.xScroll;
                     panel.yMove = mapY[TileMap.planetID][mapIdIndex] + panel.yScroll + 5;
                     break;
                  }
               }

               panel.xMap = getXMap() + panel.xScroll;
               panel.yMap = getYMap() + panel.yScroll;
               getXMapTask();
               getYMapTask();
            }
         }
      }
   }

   public final void setTypeArchivement() {
      this.currentListLength = Char.myCharz().arrArchive.length;
      this.setType(0);
      this.type = 9;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = 0;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   public final void setTypeKiGuiOnly() {
      this.type = 17;
      this.setType(1);
      this.setTabKiGui();
      this.typeShop = 2;
      this.currentTabIndex = 0;
   }

   public final void setTabKiGui() {
      this.ITEM_HEIGHT = 24;
      this.currentListLength = Char.myCharz().arrItemShop[4].length;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   public final void setTypeBodyOnly() {
      this.type = 7;
      this.setType(1);
      this.setTabInventory(true);
      this.currentTabIndex = 0;
   }

   public final void addChatMessage(InfoItem info) {
      this.logChat.insertElementAt(info, 0);
      if (this.logChat.size() > 20) {
         this.logChat.removeElementAt(this.logChat.size() - 1);
      }
   }

   public final void setTabPlayerMenu() {
      this.ITEM_HEIGHT = 24;
      this.currentListLength = this.vPlayerMenu.size();
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   public final void setTypeFlag() {
      this.type = 18;
      this.setType(0);
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      Panel panel = this;
      this.currentListLength = panel.vFlag.size();
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      if (panel.cmyLim < 0) {
         panel.cmyLim = 0;
      }

      panel.cmy = panel.cmtoY = panel.cmyLast[panel.currentTabIndex];
      if (panel.cmy < 0) {
         panel.cmy = panel.cmtoY = 0;
      }

      if (panel.cmy > panel.cmyLim) {
         panel.cmy = panel.cmtoY = panel.cmyLim;
      }

      if (panel.selected > panel.currentListLength - 1) {
         panel.selected = panel.currentListLength - 1;
      }

      panel.cmx = panel.cmtoX = 0;
   }

   public final void setTypePlayerMenu(Char c) {
      this.type = 10;
      this.setType(0);
      this.setTabPlayerMenu();
      this.charMenu = c;
   }

   public final void setTypeFriend() {
      this.type = 11;
      this.setType(0);
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      this.setTabFriend();
   }

   public final void setTypeEnemy() {
      this.type = 16;
      this.setType(0);
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      Panel panel = this;
      this.currentListLength = panel.vEnemy.size();
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      if (panel.cmyLim < 0) {
         panel.cmyLim = 0;
      }

      panel.cmy = panel.cmtoY = panel.cmyLast[panel.currentTabIndex];
      if (panel.cmy < 0) {
         panel.cmy = panel.cmtoY = 0;
      }

      if (panel.cmy > panel.cmyLim) {
         panel.cmy = panel.cmtoY = panel.cmyLim;
      }

      if (panel.selected > panel.currentListLength - 1) {
         panel.selected = panel.currentListLength - 1;
      }

      panel.cmx = panel.cmtoX = 0;
   }

   public final void setTypeTop(byte thachDauMode) {
      this.type = 15;
      this.setType(0);
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      Panel panel = this;
      this.currentListLength = panel.vTop.size();
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      if (panel.cmyLim < 0) {
         panel.cmyLim = 0;
      }

      panel.cmy = panel.cmtoY = panel.cmyLast[panel.currentTabIndex];
      if (panel.cmy < 0) {
         panel.cmy = panel.cmtoY = 0;
      }

      if (panel.cmy > panel.cmyLim) {
         panel.cmy = panel.cmtoY = panel.cmyLim;
      }

      if (panel.selected > panel.currentListLength - 1) {
         panel.selected = panel.currentListLength - 1;
      }

      panel.cmx = panel.cmtoX = 0;
      this.isThachDau = thachDauMode != 0;
   }

   public final void setTabFriend() {
      this.currentListLength = this.vFriend.size();
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      if (this.selected > this.currentListLength - 1) {
         this.selected = this.currentListLength - 1;
      }

      this.cmx = this.cmtoX = 0;
   }

   public final void setTypeMessage() {
      this.type = 8;
      this.setType(0);
      Panel panel = this;
      this.ITEM_HEIGHT = 24;
      panel.initLogMessage();
      panel.selected = GameCanvas.isTouch ? -1 : 0;
      this.currentTabIndex = 0;
   }

   public final void setTypeShop(int typeShop) {
      this.type = 1;
      this.setType(0);
      this.setTabShop();
      this.currentTabIndex = 0;
      this.typeShop = typeShop;
   }

   public final void setTypeBox() {
      this.type = 2;
      if (GameCanvas.w > 2 * WIDTH_PANEL) {
         boxTabName = new String[][]{mResources.chestt};
      } else {
         boxTabName = new String[][]{mResources.chestt, mResources.inventory};
      }

      this.tabName[2] = boxTabName;
      this.setType(0);
      if (this.currentTabIndex == 0) {
         this.setTabBox();
      }

      if (this.currentTabIndex == 1) {
         this.setTabInventory(true);
      }

      if (GameCanvas.w > 2 * WIDTH_PANEL) {
         (GameCanvas.panel2 = new Panel()).tabName[7] = new String[][]{{""}};
         GameCanvas.panel2.setTypeBodyOnly();
         GameCanvas.panel2.show();
      }
   }

   public final void setTypeCombine() {
      this.type = 12;
      if (GameCanvas.w > 2 * WIDTH_PANEL) {
         boxCombine = new String[][]{mResources.combine};
      } else {
         boxCombine = new String[][]{mResources.combine, mResources.inventory};
      }

      this.tabName[this.type] = boxCombine;
      this.setType(0);
      if (this.currentTabIndex == 0) {
         this.setTabCombine();
      }

      if (this.currentTabIndex == 1) {
         this.setTabInventory(true);
      }

      if (GameCanvas.w > 2 * WIDTH_PANEL) {
         (GameCanvas.panel2 = new Panel()).tabName[7] = new String[][]{{""}};
         GameCanvas.panel2.setTypeBodyOnly();
         GameCanvas.panel2.show();
      }

      this.combineSuccess = -1;
      this.isDoneCombine = true;
   }

   public final void setTabCombine() {
      this.currentListLength = this.vItemCombine.size() + 1;
      this.ITEM_HEIGHT = 24;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 9;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   private void setTabAuto() {
      this.type = 22;
      this.setType(0);
      Panel panel = this;
      this.currentListLength = strAuto.length;
      panel.ITEM_HEIGHT = 24;
      panel.selected = GameCanvas.isTouch ? -1 : 0;
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      if (panel.cmyLim < 0) {
         panel.cmyLim = 0;
      }

      panel.cmy = panel.cmtoY = panel.cmyLast[panel.currentTabIndex];
      if (panel.cmy < 0) {
         panel.cmy = panel.cmtoY = 0;
      }

      if (panel.cmy > panel.cmyLim) {
         panel.cmy = panel.cmtoY = panel.cmyLim;
      }

      this.cmx = this.cmtoX = 0;
   }

   public final void setTypePetMain() {
      this.type = 21;
      if (GameCanvas.panel2 != null) {
         boxPet = mResources.petMainTab2;
      } else {
         boxPet = mResources.petMainTab;
      }

      this.tabName[21] = boxPet;
      if (Char.myCharz().cgender == 1) {
         this.strStatus = new String[]{
            mResources.follow,
            mResources.defend,
            mResources.attack,
            mResources.gohome,
            mResources.fusion,
            mResources.fusionForever
         };
      } else {
         this.strStatus = new String[]{
            mResources.follow,
            mResources.defend,
            mResources.attack,
            mResources.gohome,
            mResources.fusion
         };
      }

      this.setType(2);
      if (this.currentTabIndex == 0) {
         this.setTabPetInventory();
      }

      if (this.currentTabIndex == 1) {
         this.setTabPetStatus();
      }

      if (this.currentTabIndex == 2) {
         this.setTabInventory(true);
      }
   }

   public final void setTypeMain() {
      this.type = 0;
      this.setType(0);
      if (this.currentTabIndex == 1) {
         this.setTabInventory(true);
      }

      if (this.currentTabIndex == 2) {
         this.setTabSkill();
      }

      if (this.currentTabIndex == 3) {
         if (this.mainTabName.length == 4) {
            this.setTabTool();
         } else {
            this.setTabClans();
         }
      }

      if (this.currentTabIndex == 4) {
         this.setTabTool();
      }
   }

   public final void setTypeZone() {
      this.type = 3;
      this.setType(0);
      this.setTabZone();
      this.cmx = this.cmtoX = 0;
   }

   private void addItemDetail(Item item) {
      this.cp = new ChatPopup();
      Object optionString = null;
      String text = "";
      if (item.template.gender != Char.myCharz().cgender) {
         if (item.template.gender == 0) {
            text = text + "\n|7|1|" + mResources.from_earth;
         } else if (item.template.gender == 1) {
            text = text + "\n|7|1|" + mResources.from_namec;
         } else if (item.template.gender == 2) {
            text = text + "\n|7|1|" + mResources.from_sayda;
         }
      }

      String text2 = "";
      if (item.itemOption != null) {
         for (int itemOptionIndex = 0; itemOptionIndex < item.itemOption.length; itemOptionIndex++) {
            if (item.itemOption[itemOptionIndex].optionTemplate.id == 72) {
               text2 = " [+" + item.itemOption[itemOptionIndex].param + "]";
            }
         }
      }

      boolean condition = false;
      if (item.itemOption != null) {
         for (int itemOptionIndex2 = 0; itemOptionIndex2 < item.itemOption.length; itemOptionIndex2++) {
            if (item.itemOption[itemOptionIndex2].optionTemplate.id == 41) {
               condition = true;
               if (item.itemOption[itemOptionIndex2].param == 1) {
                  text = text + "|0|1|" + item.template.name + text2;
               }

               if (item.itemOption[itemOptionIndex2].param == 2) {
                  text = text + "|2|1|" + item.template.name + text2;
               }

               if (item.itemOption[itemOptionIndex2].param == 3) {
                  text = text + "|8|1|" + item.template.name + text2;
               }

               if (item.itemOption[itemOptionIndex2].param == 4) {
                  text = text + "|7|1|" + item.template.name + text2;
               }
            }
         }
      }

      if (!condition) {
         text = text + "|0|1|" + item.template.name + text2;
      }

      if (item.itemOption != null) {
         for (int itemOptionIndex3 = 0; itemOptionIndex3 < item.itemOption.length; itemOptionIndex3++) {
            if (item.itemOption[itemOptionIndex3].optionTemplate.name.startsWith("$")) {
               optionString = item.itemOption[itemOptionIndex3].getOptiongColor();
               if (item.itemOption[itemOptionIndex3].param == 1) {
                  text = text + "\n|1|1|" + optionString;
               }

               if (item.itemOption[itemOptionIndex3].param == 0) {
                  text = text + "\n|0|1|" + optionString;
               }
            } else if (!(optionString = item.itemOption[itemOptionIndex3].getOptionString()).equals("")) {
               if (item.itemOption[itemOptionIndex3].optionTemplate.id == 72) {
                  continue;
               }

               if (item.itemOption[itemOptionIndex3].optionTemplate.id == 102) {
                  this.cp.starSlot = (byte)item.itemOption[itemOptionIndex3].param;
               } else if (item.itemOption[itemOptionIndex3].optionTemplate.id == 107) {
                  this.cp.maxStarSlot = (byte)item.itemOption[itemOptionIndex3].param;
               } else if (item.itemOption[itemOptionIndex3].optionTemplate.type > 0) {
                  text = text + "\n|" + item.itemOption[itemOptionIndex3].optionTemplate.type + "|1|" + optionString;
               } else {
                  text = text + "\n|1|1|" + optionString;
               }
            }

            if (item.itemOption[itemOptionIndex3].optionTemplate.id == 228 && item.itemOption[itemOptionIndex3].param > 7) {
               for (int index = 0; index < item.itemOption[itemOptionIndex3].param - 7; index++) {
                  this.cp.starCuongHoa[index + 7] = true;
               }
            }
         }
      }

      if (this.currItem.template.strRequire > 1) {
         String text3 = mResources.pow_request + ": " + this.currItem.template.strRequire;
         if (this.currItem.template.strRequire > Char.myCharz().cPower) {
            text = text + "\n|3|1|" + text3;
            text = text + "\n|3|1|" + mResources.your_pow + ": " + Char.myCharz().cPower;
         } else {
            text = text + "\n|6|1|" + text3;
         }
      } else {
         text = text + "\n|6|1|";
      }

      this.currItem.compare = this.getCompare(this.currItem);
      text = text + "\n--";
      String chat = text + "\n|6|" + item.template.description;
      if (!item.reason.equals("")) {
         if (!item.template.description.equals("")) {
            chat = chat + "\n--";
         }

         chat = chat + "\n|2|" + item.reason;
      }

      if (this.cp.maxStarSlot > 0) {
         chat = chat + "\n\n";
      }

      this.popUpDetailInit(this.cp, chat);
      this.idIcon = item.template.iconID;
      this.partID = null;
      this.charInfo = null;
   }

   private void popUpDetailInit(ChatPopup cp, String chat) {
      cp.isClip = false;
      cp.sayWidth = 180;
      cp.cx = 3
         + this.X
         - (this.X == 0 ? 0 : Res.abs(cp.sayWidth - this.W) + 8);
      cp.says = mFont.tahoma_7_red.splitFontArray(chat, cp.sayWidth - 10);
      cp.delay = 10000000;
      cp.c = null;
      cp.sayRun = 7;
      cp.ch = 15 - cp.sayRun + cp.says.length * 12 + 10;
      if (cp.ch > GameCanvas.h - 80) {
         cp.ch = GameCanvas.h - 80;
         cp.lim = cp.says.length * 12 - cp.ch + 17;
         if (cp.lim < 0) {
            cp.lim = 0;
         }

         ChatPopup.cmyText = 0;
         cp.isClip = true;
      }

      for (cp.cy = GameCanvas.menu.menuY - cp.ch;
         cp.cy < 10;
         GameCanvas.menu.menuY++
      ) {
         cp.cy++;
      }

      cp.mH = 0;
      cp.strY = 10;
   }

   private void addMessageDetail(ClanMessage cm) {
      this.cp = new ChatPopup();
      String text = "|0|" + cm.playerName;
      String text2 = text + "\n|1|" + Member.getRole(cm.role);

      for (int myMemberIndex = 0; myMemberIndex < this.myMember.size(); myMemberIndex++) {
         Member myMemberValue = (Member)this.myMember.elementAt(myMemberIndex);
         if (cm.playerId == myMemberValue.ID) {
            text = text2 + "\n|5|" + mResources.clan_capsuledonate + ": " + myMemberValue.clanPoint;
            text = text + "\n|5|" + mResources.clan_capsuleself + ": " + myMemberValue.curClanPoint;
            text = text + "\n|4|" + mResources.give_pea + ": " + myMemberValue.donate + mResources.time;
            text2 = text + "\n|4|" + mResources.receive_pea + ": " + myMemberValue.receive_donate + mResources.time;
            this.partID = new int[]{myMemberValue.head, myMemberValue.leg, myMemberValue.body};
            break;
         }
      }

      String chat = text2 + "\n--";

      for (int chatIndex = 0; chatIndex < cm.chat.length; chatIndex++) {
         chat = chat + "\n" + cm.chat[chatIndex];
      }

      if (cm.type == 1) {
         chat = chat + "\n|6|" + mResources.received + " " + cm.recieve + "/" + cm.maxCap;
      }

      this.popUpDetailInit(this.cp, chat);
      this.charInfo = null;
   }

   private void addClanMemberDetail(Member m) {
      String chat = "|0|1|" + m.name;
      String text = "\n|2|1|";
      if (m.role == 0) {
         text = "\n|7|1|";
      }

      if (m.role == 1) {
         text = "\n|1|1|";
      }

      if (m.role == 2) {
         text = "\n|0|1|";
      }

      chat = chat + text + Member.getRole(m.role);
      chat = chat + "\n|2|1|" + mResources.power + ": " + m.powerPoint;
      chat = chat + "\n--";
      chat = chat + "\n|5|" + mResources.clan_capsuledonate + ": " + m.clanPoint;
      chat = chat + "\n|5|" + mResources.clan_capsuleself + ": " + m.curClanPoint;
      chat = chat + "\n|4|" + mResources.give_pea + ": " + m.donate + mResources.time;
      chat = chat + "\n|4|" + mResources.receive_pea + ": " + m.receive_donate + mResources.time;
      chat = chat + "\n|6|" + mResources.join_date + ": " + m.joinTime;
      this.cp = new ChatPopup();
      this.popUpDetailInit(this.cp, chat);
      this.partID = new int[]{m.head, m.leg, m.body};
      this.currItem = null;
      this.charInfo = null;
   }

   private void addClanDetail(Clan cl) {
      try {
         String chat = "|0|" + cl.name;
         String[] texts = mFont.tahoma_7_green.splitFontArray(cl.slogan, this.wScroll - 60);

         for (int textIndex = 0; textIndex < texts.length; textIndex++) {
            chat = chat + "\n|2|" + texts[textIndex];
         }

         chat = chat + "\n--";
         chat = chat + "\n|7|" + mResources.clan_leader + ": " + cl.leaderName;
         chat = chat + "\n|1|" + mResources.power_point + ": " + cl.powerPoint;
         chat = chat + "\n|4|" + mResources.member + ": " + cl.currMember + "/" + cl.maxMember;
         chat = chat + "\n|4|" + mResources.level + ": " + cl.level;
         chat = chat + "\n|4|" + mResources.clan_birthday + ": " + Firework.getDate(cl.date);
         this.cp = new ChatPopup();
         this.popUpDetailInit(this.cp, chat);
         this.idIcon = ClanImage.getClanImage((short)cl.imgID).idImage[0];
         this.currItem = null;
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private void addSkillDetail(SkillTemplate tp, Skill skill, Skill nextSkill) {
      String chat = "|0|" + tp.name;

      for (int descriptionIndex = 0; descriptionIndex < tp.description.length; descriptionIndex++) {
         chat = chat + "\n|4|" + tp.description[descriptionIndex];
      }

      String text = chat + "\n--";
      if (skill != null) {
         chat = text + "\n|2|" + mResources.cap_do + ": " + skill.point;
         chat = chat + "\n|5|" + Firework.replaceAllLiteral(tp.damInfo, "#", String.valueOf(skill.damage));
         chat = chat + "\n|5|" + mResources.KI_consume + skill.manaUse + (tp.manaUseType == 1 ? "%" : "");
         chat = chat + "\n|5|" + mResources.cooldown + ": " + skill.strTimeReplay() + "s";
         String text2 = chat + "\n--";
         if (skill.point == tp.maxPoint) {
            chat = text2 + "\n|0|" + mResources.max_level_reach;
         } else {
            if (!skill.template.isSkillSpec()) {
               text2 = text2
                  + "\n|1|"
                  + mResources.next_level_require
                  + Res.formatNumber(nextSkill.powRequire)
                  + " "
                  + mResources.potential;
            }

            chat = text2 + "\n|4|" + Firework.replaceAllLiteral(tp.damInfo, "#", String.valueOf(nextSkill.damage));
         }
      } else {
         chat = text + "\n|2|" + mResources.not_learn;
         chat = chat + "\n|1|" + mResources.learn_require + Res.formatNumber(nextSkill.powRequire) + " " + mResources.potential;
         chat = chat + "\n|4|" + Firework.replaceAllLiteral(tp.damInfo, "#", String.valueOf(nextSkill.damage));
         chat = chat + "\n|4|" + mResources.KI_consume + nextSkill.manaUse + (tp.manaUseType == 1 ? "%" : "");
         chat = chat + "\n|4|" + mResources.cooldown + ": " + nextSkill.strTimeReplay() + "s";
      }

      this.currItem = null;
      this.partID = null;
      this.charInfo = null;
      this.cp = new ChatPopup();
      this.popUpDetailInit(this.cp, chat);
      this.idIcon = 0;
   }

   public final void show() {
      if (GameCanvas.isTouch) {
         this.cmdClose.x = 156;
         this.cmdClose.y = 3;
      } else {
         this.cmdClose.x = GameCanvas.w - 19;
         this.cmdClose.y = GameCanvas.h - 19;
      }

      this.cmdClose.isPlaySoundButton = false;
      ChatPopup.currChatPopup = null;
      InfoDlg.hide();
      this.timeShow = 20;
      this.isShow = true;
      this.isClose = false;
      SoundMn.gI();
      SoundMn.panelOpen();
      if (this.isTypeShop()) {
         Char.myCharz().setPartOld();
      }
   }

   public final void chatTFUpdateKey() {
      if (this.chatTField != null && this.chatTField.isShow) {
         if (this.chatTField.left != null
            && (GameCanvas.keyPressed[12] || mScreen.getCmdPointerLast(this.chatTField.left))
            && this.chatTField.left != null) {
            this.chatTField.left.performAction();
         }

         if (this.chatTField.right != null
            && (GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(this.chatTField.right))
            && this.chatTField.right != null) {
            this.chatTField.right.performAction();
         }

         if (this.chatTField.center != null
            && (GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(this.chatTField.center))
            && this.chatTField.center != null) {
            this.chatTField.center.performAction();
         }

         if (this.chatTField.isShow && GameCanvas.keyAsciiPress != 0) {
            this.chatTField.keyPressed(GameCanvas.keyAsciiPress);
            GameCanvas.keyAsciiPress = 0;
         }

         GameCanvas.clearKeyHold();
         GameCanvas.clearKeyPressed();
      }
   }

   public final void updateKey() {
      if (this.chatTField == null || !this.chatTField.isShow) {
         if (GameCanvas.panel.isDoneCombine) {
            if (!InfoDlg.isShow) {
               if (this.tabIcon != null && this.tabIcon.isShow) {
                  TabClanIcon tabIconValue = this.tabIcon;
                  if (this.tabIcon.left != null
                     && (GameCanvas.keyPressed[12] || mScreen.getCmdPointerLast(tabIconValue.left))) {
                     tabIconValue.left.performAction();
                  }

                  if (tabIconValue.right != null && (GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(tabIconValue.right))) {
                     tabIconValue.right.performAction();
                  }

                  if (!tabIconValue.isGetName) {
                     if (TabClanIcon.scrMain == null) {
                        return;
                     }

                     if (GameCanvas.isTouch) {
                        TabClanIcon.scrMain.updateKey();
                        tabIconValue.select = TabClanIcon.scrMain.selectedItem;
                     }

                     if (GameCanvas.keyPressed[2]) {
                        GameCanvas.keyPressed[2] = false;
                        tabIconValue.select--;
                        if (tabIconValue.select < 0) {
                           tabIconValue.select = tabIconValue.nItem - 1;
                        }

                        TabClanIcon.scrMain.moveTo(tabIconValue.select * TabClanIcon.scrMain.ITEM_SIZE);
                     }

                     if (GameCanvas.keyPressed[8]) {
                        GameCanvas.keyPressed[8] = false;
                        tabIconValue.select++;
                        if (tabIconValue.select > tabIconValue.nItem - 1) {
                           tabIconValue.select = 0;
                        }

                        TabClanIcon.scrMain.moveTo(tabIconValue.select * TabClanIcon.scrMain.ITEM_SIZE);
                     }

                     if (tabIconValue.select != -1) {
                        tabIconValue.lastSelect = tabIconValue.select;
                     }
                  }

                  GameCanvas.clearKeyHold();
                  GameCanvas.clearKeyPressed();
               } else if (!this.isClose) {
                  if (this.isShow) {
                     if (this.cmdClose.isPointerPressInside()) {
                        this.cmdClose.performAction();
                     } else {
                        if (GameCanvas.keyPressed[13]) {
                           if (this.type != 4) {
                              this.hide();
                              return;
                           }

                           this.setTypeMain();
                           this.cmx = this.cmtoX = 0;
                        }

                        if (GameCanvas.keyPressed[12] || GameCanvas.keyPressed[5]) {
                           if (this.left.idAction > 0) {
                              this.perform(this.left.idAction, this.left.p);
                           } else {
                              this.waitToPerform = 2;
                           }
                        }

                        if (this.equals(GameCanvas.panel)
                           && GameCanvas.panel2 == null
                           && GameCanvas.isPointerJustRelease
                           && !GameCanvas.isPointer(this.X, 0, this.W, this.H)
                           && !this.pointerIsDowning) {
                           this.hide();
                        } else {
                           if (!this.isClanOption) {
                              Panel panel = this;
                              if ((this.scroll == null || !panel.scroll.pointerIsDowning) && !panel.pointerIsDowning) {
                                 int currentTabIndexValue;
                                 boolean condition;
                                 label514: {
                                    currentTabIndexValue = panel.currentTabIndex;
                                    panel.isTabInven();
                                    Panel panel2 = panel;
                                    if (panel.size_tab > 0) {
                                       if (panel2.currentTabName.length > 1) {
                                          if (panel2.selected == 0) {
                                             condition = true;
                                             break label514;
                                          }
                                       } else if (panel2.selected >= 0) {
                                          condition = true;
                                          break label514;
                                       }
                                    }

                                    condition = false;
                                 }

                                 if (!condition) {
                                    if (GameCanvas.keyPressed[6]) {
                                       if (panel.isTabInven()) {
                                          if (panel.selected >= 0) {
                                             panel.updateKeyInvenTab();
                                          } else {
                                             panel.currentTabIndex++;
                                             if (panel.currentTabIndex >= panel.currentTabName.length) {
                                                if (GameCanvas.panel2 != null) {
                                                   panel.currentTabIndex = panel.currentTabName.length - 1;
                                                   GameCanvas.isFocusPanel2 = true;
                                                } else {
                                                   panel.currentTabIndex = 0;
                                                }
                                             }

                                             panel.selected = panel.lastSelect[panel.currentTabIndex];
                                             panel.lastTabIndex[panel.type] = panel.currentTabIndex;
                                          }
                                       } else {
                                          panel.currentTabIndex++;
                                          if (panel.currentTabIndex >= panel.currentTabName.length) {
                                             if (GameCanvas.panel2 != null) {
                                                panel.currentTabIndex = panel.currentTabName.length - 1;
                                                GameCanvas.isFocusPanel2 = true;
                                             } else {
                                                panel.currentTabIndex = 0;
                                             }
                                          }

                                          panel.selected = panel.lastSelect[panel.currentTabIndex];
                                          panel.lastTabIndex[panel.type] = panel.currentTabIndex;
                                       }
                                    }

                                    if (GameCanvas.keyPressed[4]) {
                                       panel.currentTabIndex--;
                                       if (panel.currentTabIndex < 0) {
                                          panel.currentTabIndex = panel.currentTabName.length - 1;
                                       }

                                       if (GameCanvas.isFocusPanel2) {
                                          GameCanvas.isFocusPanel2 = false;
                                       }

                                       panel.selected = panel.lastSelect[panel.currentTabIndex];
                                       panel.lastTabIndex[panel.type] = panel.currentTabIndex;
                                    }
                                 }

                                 panel.keyTouchTab = -1;

                                 for (int currentTabIndexValue2 = 0; currentTabIndexValue2 < panel.currentTabName.length; currentTabIndexValue2++) {
                                    if (GameCanvas.isPointer(panel.startTabPos + currentTabIndexValue2 * panel.TAB_W, 52, panel.TAB_W - 1, 25)
                                       )
                                     {
                                       panel.keyTouchTab = currentTabIndexValue2;
                                       if (GameCanvas.isPointerJustRelease) {
                                          panel.currentTabIndex = currentTabIndexValue2;
                                          panel.lastTabIndex[panel.type] = currentTabIndexValue2;
                                          GameCanvas.isPointerJustRelease = false;
                                          panel.selected = panel.lastSelect[panel.currentTabIndex];
                                          if (currentTabIndexValue == panel.currentTabIndex && panel.cmRun == 0) {
                                             panel.cmtoY = 0;
                                             panel.selected = GameCanvas.isTouch ? -1 : 0;
                                          }
                                          break;
                                       }
                                    }
                                 }

                                 if (currentTabIndexValue != panel.currentTabIndex) {
                                    panel.size_tab = 0;
                                    SoundMn.gI();
                                    SoundMn.panelClick();
                                    switch (panel.type) {
                                       case 0:
                                          if (panel.currentTabIndex == 0) {
                                             panel.cmyQuest = 0;
                                          }

                                          if (panel.currentTabIndex == 1) {
                                             panel.setTabInventory(true);
                                          }

                                          if (panel.currentTabIndex == 2) {
                                             panel.setTabSkill();
                                          }

                                          if (panel.currentTabIndex == 3) {
                                             if (panel.mainTabName.length > 4) {
                                                panel.setTabClans();
                                             } else {
                                                panel.setTabTool();
                                             }
                                          }

                                          if (panel.currentTabIndex == 4) {
                                             panel.setTabTool();
                                          }
                                          break;
                                       case 1:
                                          panel.setTabShop();
                                          break;
                                       case 2:
                                          if (panel.currentTabIndex == 0) {
                                             panel.setTabBox();
                                          }

                                          if (panel.currentTabIndex == 1) {
                                             panel.setTabInventory(true);
                                          }
                                          break;
                                       case 3:
                                          panel.setTabZone();
                                          break;
                                       case 12:
                                          if (panel.currentTabIndex == 0) {
                                             panel.setTabCombine();
                                          }

                                          if (panel.currentTabIndex == 1) {
                                             panel.setTabInventory(true);
                                          }
                                          break;
                                       case 13:
                                          if (panel.currentTabIndex == 0) {
                                             if (panel.equals(GameCanvas.panel)) {
                                                panel.setTabInventory(true);
                                             } else if (panel.equals(GameCanvas.panel2)) {
                                                panel.setTabGiaoDich(false);
                                             }
                                          }

                                          if (panel.currentTabIndex == 1) {
                                             panel.setTabGiaoDich(true);
                                          }

                                          if (panel.currentTabIndex == 2) {
                                             panel.setTabGiaoDich(false);
                                          }
                                          break;
                                       case 21:
                                          if (panel.currentTabIndex == 0) {
                                             panel.setTabPetInventory();
                                          }

                                          if (panel.currentTabIndex == 1) {
                                             panel.setTabPetStatus();
                                          }

                                          if (panel.currentTabIndex == 2) {
                                             panel.setTabInventory(true);
                                          }
                                          break;
                                       case 25:
                                          panel.setTabSpeacialSkill();
                                    }

                                    panel.selected = panel.lastSelect[panel.currentTabIndex];
                                 }
                              }
                           }

                           switch (this.type) {
                              case 0:
                                 if (this.currentTabIndex == 0) {
                                    Panel panel3 = this;
                                    if (GameCanvas.keyHold[2]) {
                                       panel3.cmyQuest -= 5;
                                    }

                                    if (GameCanvas.keyHold[8]) {
                                       panel3.cmyQuest += 5;
                                    }

                                    if (panel3.cmyQuest < 0) {
                                       panel3.cmyQuest = 0;
                                    }

                                    int cmyQuestValue;
                                    if ((cmyQuestValue = panel3.indexRowMax * 12 - (panel3.hScroll - 60)) < 0) {
                                       cmyQuestValue = 0;
                                    }

                                    if (panel3.cmyQuest > cmyQuestValue) {
                                       panel3.cmyQuest = cmyQuestValue;
                                    }

                                    if (panel3.scroll != null) {
                                       if (!GameCanvas.isTouch) {
                                          panel3.scroll.cmy = panel3.cmyQuest;
                                       }

                                       panel3.scroll.updateKey();
                                    }

                                    int value = panel3.xScroll + panel3.wScroll / 2 - 35;
                                    int px = GameCanvas.h > 300 ? 20 : 15;
                                    cmyQuestValue = panel3.yScroll + panel3.hScroll - px - 15;
                                    px = GameCanvas.px;
                                    int py = GameCanvas.py;
                                    panel3.keyTouchMapButton = -1;
                                    if (isPaintMap) {
                                       GameScr.gI();
                                       if (!GameScr.isMapDocNhan()
                                          && px >= value
                                          && px <= value + 70
                                          && py >= cmyQuestValue
                                          && py <= cmyQuestValue + 30
                                          && (panel3.scroll == null || !panel3.scroll.pointerIsDowning)) {
                                          panel3.keyTouchMapButton = 1;
                                          if (GameCanvas.isPointerJustRelease) {
                                             SoundMn.gI();
                                             SoundMn.buttonClick();
                                             panel3.waitToPerform = 2;
                                             GameCanvas.clearAllPointerEvent();
                                          }
                                       }
                                    }

                                    GameCanvas.clearKeyPressed();
                                    return;
                                 }

                                 if (this.currentTabIndex == 1) {
                                    this.updateKeyInventory();
                                 }

                                 if (this.currentTabIndex == 2) {
                                    this.updateKeyScrollView();
                                 }

                                 if (this.currentTabIndex == 3) {
                                    if (this.mainTabName.length == 4) {
                                       this.updateKeyScrollView();
                                    } else {
                                       Panel panel4 = this;
                                       this.updateKeyScrollView();
                                       Panel panel5 = panel4;
                                       if (panel4.currClanOption != null) {
                                          if (GameCanvas.keyPressed[4]) {
                                             panel5.currMess = panel5.getCurrMessage();
                                             panel5.cSelected--;
                                             if (panel5.selected == 0 && panel5.cSelected < 0) {
                                                panel5.cSelected = panel5.currClanOption.length - 1;
                                             }

                                             if (panel5.selected > 1
                                                && panel5.isMessage
                                                && panel5.currMess.option != null
                                                && panel5.cSelected < 0) {
                                                panel5.cSelected = panel5.currMess.option.length - 1;
                                             }
                                          } else if (GameCanvas.keyPressed[6]) {
                                             panel5.currMess = panel5.getCurrMessage();
                                             panel5.cSelected++;
                                             if (panel5.selected == 0 && panel5.cSelected > panel5.currClanOption.length - 1) {
                                                panel5.cSelected = 0;
                                             }

                                             if (panel5.selected > 1
                                                && panel5.isMessage
                                                && panel5.currMess.option != null
                                                && panel5.cSelected > panel5.currMess.option.length - 1) {
                                                panel5.cSelected = 0;
                                             }
                                          }
                                       }
                                    }
                                 }

                                 if (this.currentTabIndex != 4) {
                                    break;
                                 }
                              case 20:
                                 this.updateKeyScrollView();
                                 break;
                              case 1:
                              case 17:
                              case 25:
                                 if (this.currentTabIndex < this.currentTabName.length - (GameCanvas.panel2 != null ? 0 : 1)
                                    && this.type != 17) {
                                    this.updateKeyScrollView();
                                 } else if (this.typeShop == 0) {
                                    this.updateKeyInventory();
                                 } else {
                                    this.updateKeyScrollView();
                                 }
                                 break;
                              case 2:
                                 this.updateKeyInventory();
                                 break;
                              case 3:
                              case 26:
                                 this.updateKeyScrollView();
                                 break;
                              case 4:
                                 Panel panel6 = this;
                                 if (GameCanvas.keyHold[2]) {
                                    panel6.yMove -= 5;
                                    panel6.cmyMap = panel6.yMove - (panel6.yScroll + panel6.hScroll / 2);
                                    if (panel6.yMove < panel6.yScroll) {
                                       panel6.yMove = panel6.yScroll;
                                    }
                                 }

                                 if (GameCanvas.keyHold[8]) {
                                    panel6.yMove += 5;
                                    panel6.cmyMap = panel6.yMove - (panel6.yScroll + panel6.hScroll / 2);
                                    if (panel6.yMove > panel6.yScroll + 200) {
                                       panel6.yMove = panel6.yScroll + 200;
                                    }
                                 }

                                 if (GameCanvas.keyHold[4]) {
                                    panel6.xMove -= 5;
                                    panel6.cmxMap = panel6.xMove - panel6.wScroll / 2;
                                    if (panel6.xMove < 16) {
                                       panel6.xMove = 16;
                                    }
                                 }

                                 if (GameCanvas.keyHold[6]) {
                                    panel6.xMove += 5;
                                    panel6.cmxMap = panel6.xMove - panel6.wScroll / 2;
                                    if (panel6.xMove > 250) {
                                       panel6.xMove = 250;
                                    }
                                 }

                                 if (GameCanvas.isPointerDown) {
                                    panel6.pointerIsDowning = true;
                                    if (!panel6.trans) {
                                       panel6.pa1 = panel6.cmxMap;
                                       panel6.pa2 = panel6.cmyMap;
                                       panel6.trans = true;
                                    }

                                    panel6.cmxMap = panel6.pa1 + (GameCanvas.pxLast - GameCanvas.px);
                                    panel6.cmyMap = panel6.pa2 + (GameCanvas.pyLast - GameCanvas.py);
                                 }

                                 if (GameCanvas.isPointerJustRelease) {
                                    panel6.trans = false;
                                    GameCanvas.pxLast = GameCanvas.px;
                                    GameCanvas.pyLast = GameCanvas.py;
                                    panel6.pX = GameCanvas.pxLast + panel6.cmxMap;
                                    panel6.pY = GameCanvas.pyLast + panel6.cmyMap;
                                 }

                                 if (GameCanvas.isPointerClick) {
                                    panel6.pointerIsDowning = false;
                                 }

                                 if (panel6.cmxMap < 0) {
                                    panel6.cmxMap = 0;
                                 }

                                 if (panel6.cmxMap > panel6.cmxMapLim) {
                                    panel6.cmxMap = panel6.cmxMapLim;
                                 }

                                 if (panel6.cmyMap < 0) {
                                    panel6.cmyMap = 0;
                                 }

                                 if (panel6.cmyMap > panel6.cmyMapLim) {
                                    panel6.cmyMap = panel6.cmyMapLim;
                                 }

                                 GameCanvas.clearKeyPressed();
                                 return;
                              case 5:
                              case 6:
                              case 22:
                              default:
                                 break;
                              case 7:
                                 this.updateKeyInventory();
                                 break;
                              case 8:
                                 this.updateKeyScrollView();
                                 break;
                              case 9:
                                 this.updateKeyScrollView();
                                 break;
                              case 10:
                                 this.updateKeyScrollView();
                                 break;
                              case 11:
                              case 16:
                                 this.updateKeyScrollView();
                                 break;
                              case 12:
                                 Panel panel7 = this;
                                 if (this.currentTabIndex == 0) {
                                    panel7.updateKeyScrollView();
                                    panel7.keyTouchCombine = -1;
                                    if (panel7.selected == panel7.vItemCombine.size() && GameCanvas.isPointerClick) {
                                       GameCanvas.isPointerClick = false;
                                       panel7.keyTouchCombine = 1;
                                    }
                                 }

                                 if (panel7.currentTabIndex == 1) {
                                    panel7.updateKeyScrollView();
                                 }
                                 break;
                              case 13:
                                 Panel panel8 = this;
                                 if (this.currentTabIndex == 0) {
                                    if (panel8.equals(GameCanvas.panel)) {
                                       panel8.updateKeyInventory();
                                    }

                                    if (panel8.equals(GameCanvas.panel2)) {
                                       panel8.updateKeyScrollView();
                                    }
                                 }

                                 if (panel8.currentTabIndex == 1 || panel8.currentTabIndex == 2) {
                                    panel8.updateKeyScrollView();
                                 }
                                 break;
                              case 14:
                                 this.updateKeyScrollView();
                                 break;
                              case 15:
                                 this.updateKeyScrollView();
                                 break;
                              case 18:
                                 this.updateKeyScrollView();
                                 break;
                              case 19:
                                 this.updateKeyScrollView();
                                 break;
                              case 21:
                                 if (this.currentTabIndex == 0) {
                                    this.updateKeyScrollView();
                                 }

                                 if (this.currentTabIndex == 1) {
                                    this.updateKeyScrollView();
                                 }

                                 if (this.currentTabIndex == 2) {
                                    this.updateKeyScrollView();
                                 }
                                 break;
                              case 23:
                              case 24:
                                 this.updateKeyScrollView();
                           }

                           GameCanvas.clearKeyHold();

                           for (int keyPressedIndex = 0; keyPressedIndex < GameCanvas.keyPressed.length; keyPressedIndex++) {
                              GameCanvas.keyPressed[keyPressedIndex] = false;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public final void setTabGiaoDich(boolean isMe) {
      this.currentListLength = isMe ? this.vMyGD.size() + 3 : this.vFriendGD.size() + 3;
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }
   }

   public final void setTypeGiaoDich(Char cGD) {
      this.type = 13;
      this.tabName[this.type] = boxGD;
      this.isAccept = false;
      this.isLock = false;
      this.isFriendLock = false;
      this.vMyGD.removeAllElements();
      this.vFriendGD.removeAllElements();
      this.moneyGD = 0;
      this.friendMoneyGD = 0;
      if (GameCanvas.w > 2 * WIDTH_PANEL) {
         (GameCanvas.panel2 = new Panel()).type = 13;
         GameCanvas.panel2.tabName[this.type] = new String[][]{mResources.item_receive};
         GameCanvas.panel2.setType(1);
         GameCanvas.panel2.setTabGiaoDich(false);
         GameCanvas.panel.tabName[this.type] = new String[][]{mResources.inventory, mResources.item_give};
         GameCanvas.panel2.show();
         GameCanvas.panel2.charMenu = cGD;
      }

      if (this.equals(GameCanvas.panel)) {
         this.setType(0);
      }

      if (this.currentTabIndex == 0) {
         this.setTabInventory(true);
      }

      if (this.currentTabIndex == 1) {
         this.setTabGiaoDich(true);
      }

      if (this.currentTabIndex == 2) {
         this.setTabGiaoDich(false);
      }

      this.charMenu = cGD;
   }

   private void paintGiaoDich(mGraphics g, boolean isMe) {
      g.setColor(16711680);
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      MyVector entries = isMe ? this.vMyGD : this.vFriendGD;

      for (int index = 0; index < this.currentListLength; index++) {
         int x = this.xScroll + 36;
         int y = this.yScroll + index * this.ITEM_HEIGHT;
         int width = this.wScroll - 36;
         int height = this.ITEM_HEIGHT - 1;
         int x2 = this.xScroll;
         int y2 = this.yScroll + index * this.ITEM_HEIGHT;
         int height2 = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            if (index == this.currentListLength - 1) {
               if (isMe) {
                  g.setColor(15196114);
                  g.fillRectDelegated(x2, y, this.wScroll, height);
                  if (!this.isLock) {
                     if (!this.isFriendLock) {
                        mFont.tahoma_7_grey
                           .drawString(
                              g,
                              mResources.opponent + mResources.not_lock_trade,
                              this.xScroll + this.wScroll / 2,
                              y + height / 2 - 4,
                              2
                           );
                     } else {
                        mFont.tahoma_7_grey
                           .drawString(
                              g,
                              mResources.opponent + mResources.locked_trade,
                              this.xScroll + this.wScroll / 2,
                              y + height / 2 - 4,
                              2
                           );
                     }
                  } else if (this.isFriendLock) {
                     g.setColor(15196114);
                     g.fillRectDelegated(x2, y, this.wScroll, height);
                     g.drawImage(
                        index == this.selected ? GameScr.imgLbtnFocus2 : GameScr.imgLbtn2,
                        ((int) (this.xScroll + this.wScroll - 5)),
                        ((int) (y + 2)),
                        StaticObj.TOP_RIGHT
                     );
                     (index == this.selected ? mFont.tahoma_7b_green2 : mFont.tahoma_7b_dark)
                        .drawString(g, mResources.done, this.xScroll + this.wScroll - 22, y + 7, 2);
                     mFont.tahoma_7_grey
                        .drawString(
                           g, mResources.opponent + mResources.locked_trade, this.xScroll + 5, y + height / 2 - 4, 0
                        );
                  } else {
                     mFont.tahoma_7_grey
                        .drawString(
                           g,
                           mResources.opponent + mResources.not_lock_trade,
                           this.xScroll + this.wScroll / 2,
                           y + height / 2 - 4,
                           2
                        );
                  }
               }
            } else if (index == this.currentListLength - 2) {
               if (isMe) {
                  g.setColor(15196114);
                  g.fillRectDelegated(x2, y, this.wScroll, height);
                  if (!this.isAccept) {
                     if (!this.isLock) {
                        g.drawImage(
                           index == this.selected ? GameScr.imgLbtnFocus2 : GameScr.imgLbtn2,
                           ((int) (this.xScroll + this.wScroll - 5)),
                           ((int) (y + 2)),
                           StaticObj.TOP_RIGHT
                        );
                        (index == this.selected ? mFont.tahoma_7b_green2 : mFont.tahoma_7b_dark)
                           .drawString(g, mResources.mlock, this.xScroll + this.wScroll - 22, y + 7, 2);
                        mFont.tahoma_7_grey
                           .drawString(
                              g, mResources.you + mResources.not_lock_trade, this.xScroll + 5, y + height / 2 - 4, 0
                           );
                     } else {
                        g.drawImage(
                           index == this.selected ? GameScr.imgLbtnFocus2 : GameScr.imgLbtn2,
                           ((int) (this.xScroll + this.wScroll - 5)),
                           ((int) (y + 2)),
                           StaticObj.TOP_RIGHT
                        );
                        (index == this.selected ? mFont.tahoma_7b_green2 : mFont.tahoma_7b_dark)
                           .drawString(g, mResources.CANCEL, this.xScroll + this.wScroll - 22, y + 7, 2);
                        mFont.tahoma_7_grey
                           .drawString(
                              g, mResources.you + mResources.locked_trade, this.xScroll + 5, y + height / 2 - 4, 0
                           );
                     }
                  }
               } else if (!this.isFriendLock) {
                  mFont.tahoma_7b_dark
                     .drawString(g, mResources.not_lock_trade_upper, this.xScroll + this.wScroll / 2, y + height / 2 - 4, 2);
               } else {
                  mFont.tahoma_7b_dark
                     .drawString(g, mResources.locked_trade_upper, this.xScroll + this.wScroll / 2, y + height / 2 - 4, 2);
               }
            } else if (index == this.currentListLength - 3) {
               mGraphics g2;
               int color;
               if (this.isLock) {
                  g2 = g;
                  color = 13748667;
               } else {
                  g2 = g;
                  color = index == this.selected ? 16383818 : 15196114;
               }

               g2.setColor(color);
               g.fillRectDelegated(x, y, width, height);
               if (this.isLock) {
                  g2 = g;
                  color = 13748667;
               } else {
                  g2 = g;
                  color = index == this.selected ? 7300181 : 9993045;
               }

               g2.setColor(color);
               g.fillRectDelegated(x2, y2, 34, height2);
               g.drawImage(imgXu, ((int) (x2 + 17)), ((int) (y2 + height2 / 2)), 3);
               mFont.tahoma_7_green2
                  .drawString(
                     g,
                     Firework.getMoneys(isMe ? this.moneyGD : this.friendMoneyGD) + " " + mResources.XU,
                     x + 5,
                     y + 11,
                     0
                  );
               mFont.tahoma_7_green.drawString(g, mResources.money_trade, x + 5, y, 0);
            } else {
               if (entries.size() == 0) {
                  return;
               }

               mGraphics g3;
               int color2;
               if (this.isLock) {
                  g3 = g;
                  color2 = 13748667;
               } else {
                  g3 = g;
                  color2 = index == this.selected ? 16383818 : 15196114;
               }

               g3.setColor(color2);
               g.fillRectDelegated(x, y, width, height);
               if (this.isLock) {
                  g3 = g;
                  color2 = 13748667;
               } else {
                  g3 = g;
                  color2 = index == this.selected ? 9541120 : 9993045;
               }

               g3.setColor(color2);
               Item item;
               if ((item = (Item)entries.elementAt(index)) != null) {
                  for (int itemOptionIndex = 0; itemOptionIndex < item.itemOption.length; itemOptionIndex++) {
                     byte id;
                     if (item.itemOption[itemOptionIndex].optionTemplate.id == 72
                        && item.itemOption[itemOptionIndex].param > 0
                        && GetColor_ItemBg(id = GetColor_Item_Upgrade(item.itemOption[itemOptionIndex].param)) != -1) {
                        if (this.isLock) {
                           g3 = g;
                           color2 = 13748667;
                        } else {
                           g3 = g;
                           color2 = GetColor_ItemBg(id);
                        }

                        g3.setColor(color2);
                     }
                  }
               }

               g.fillRectDelegated(x2, y2, 34, height2);
               if (item != null) {
                  String text = "";
                  mFont tahoma_7_green2 = mFont.tahoma_7_green2;
                  if (item.itemOption != null) {
                     for (int itemOptionIndex2 = 0; itemOptionIndex2 < item.itemOption.length; itemOptionIndex2++) {
                        if (item.itemOption[itemOptionIndex2].optionTemplate.id == 72) {
                           text = " [+" + item.itemOption[itemOptionIndex2].param + "]";
                        }

                        if (item.itemOption[itemOptionIndex2].optionTemplate.id == 41) {
                           if (item.itemOption[itemOptionIndex2].param == 1) {
                              tahoma_7_green2 = GetFont(0);
                           } else if (item.itemOption[itemOptionIndex2].param == 2) {
                              tahoma_7_green2 = GetFont(2);
                           } else if (item.itemOption[itemOptionIndex2].param == 3) {
                              tahoma_7_green2 = GetFont(8);
                           } else if (item.itemOption[itemOptionIndex2].param == 4) {
                              tahoma_7_green2 = GetFont(7);
                           }
                        }
                     }
                  }

                  tahoma_7_green2.drawString(g, item.template.name + text, x + 5, y + 1, 0);
                  String text2 = "";
                  if (item.itemOption != null) {
                     if (item.itemOption.length > 0 && item.itemOption[0] != null) {
                        text2 = text2 + item.itemOption[0].getOptionString();
                     }

                     mFont tahoma_7_blue = mFont.tahoma_7_blue;
                     if (item.compare < 0 && item.template.type != 5) {
                        tahoma_7_blue = mFont.tahoma_7_red;
                     }

                     if (item.itemOption.length > 1) {
                        for (int itemOptionIndex3 = 1; itemOptionIndex3 < item.itemOption.length; itemOptionIndex3++) {
                           if (item.itemOption[itemOptionIndex3] != null
                              && item.itemOption[itemOptionIndex3].optionTemplate.id != 102
                              && item.itemOption[itemOptionIndex3].optionTemplate.id != 107) {
                              text2 = text2 + "," + item.itemOption[itemOptionIndex3].getOptionString();
                           }
                        }
                     }

                     tahoma_7_blue.drawString(g, text2, x + 5, y + 11, 0);
                  }

                  SmallImage.drawSmallImage(g, item.template.iconID, x2 + 17, y2 + height2 / 2, 0, 3);
                  if (item.itemOption != null) {
                     for (int itemOptionIndex4 = 0; itemOptionIndex4 < item.itemOption.length; itemOptionIndex4++) {
                        this.paintOptItem(g, item.itemOption[itemOptionIndex4].optionTemplate.id, x2, y2, height2);
                     }

                     for (int itemOptionIndex5 = 0; itemOptionIndex5 < item.itemOption.length; itemOptionIndex5++) {
                        paintUpgradeEffect(
                           g,
                           item.itemOption[itemOptionIndex5].optionTemplate.id,
                           item.itemOption[itemOptionIndex5].param,
                           x2,
                           y2,
                           34,
                           height2
                        );
                     }
                  }

                  if (item.quantity > 1) {
                     mFont.tahoma_7_yellow
                        .drawString(g, "" + item.quantity, x2 + 34, y2 + height2 - mFont.tahoma_7_yellow.getHeight(), 1);
                  }
               }
            }
         }
      }

      this.paintScrollArrow(g);
   }

   private void getCurrClanOtion() {
      this.isClanOption = false;
      if (this.type == 0 && this.mainTabName.length == 5 && this.currentTabIndex == 3) {
         this.isClanOption = false;
         if (this.selected == 0) {
            this.currClanOption = new int[this.clansOption.length];
            int currClanOptionIndex = 0;

            while (currClanOptionIndex < this.currClanOption.length) {
               this.currClanOption[currClanOptionIndex] = currClanOptionIndex++;
            }

            if (!this.isViewMember) {
               this.isClanOption = true;
               return;
            }
         } else if (this.selected != 1) {
            if (this.isSearchClan) {
               return;
            }

            if (this.selected > 0) {
               this.currClanOption = new int[1];
               int currClanOptionIndex2 = 0;

               while (currClanOptionIndex2 < this.currClanOption.length) {
                  this.currClanOption[currClanOptionIndex2] = currClanOptionIndex2++;
               }

               this.isClanOption = true;
            }
         }
      }
   }

   private void checkOptionSelect() {
      try {
         if (this.type == 0 && this.currentTabIndex == 3 && this.mainTabName.length == 5) {
            if (this.selected == -1) {
               return;
            }

            int value = 0;
            if (this.selected == 0) {
               value = this.xScroll + this.wScroll / 2 - this.clansOption.length * this.TAB_W / 2;
               this.cSelected = (GameCanvas.px - value) / this.TAB_W;
            } else {
               this.currMess = this.getCurrMessage();
               if (this.currMess != null && this.currMess.option != null) {
                  value = this.xScroll + this.wScroll - 2 - this.currMess.option.length * 40;
                  this.cSelected = (GameCanvas.px - value) / 40;
               }
            }

            if (GameCanvas.px < value) {
               this.cSelected = -1;
               return;
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private void updateKeyScrollView() {
      if (this.currentListLength > 0) {
         int cmtoYValue = 0;
         if (GameCanvas.keyPressed[2]) {
            cmtoYValue = 1;
            this.isTabInven();
            this.selected--;
            if (this.type == 24) {
               this.selected -= 2;
               if (this.selected < 0) {
                  this.selected = 0;
               }
            } else if (this.selected < 0) {
               if (this.equals(GameCanvas.panel)
                  && this.typeShop == 2
                  && this.currentTabIndex <= 3
                  && this.maxPageShop[this.currentTabIndex] > 1) {
                  InfoDlg.showWait();
                  if (this.currPageShop[this.currentTabIndex] <= 0) {
                     Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, this.maxPageShop[this.currentTabIndex] - 1, -1);
                     return;
                  }

                  Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, this.currPageShop[this.currentTabIndex] - 1, -1);
                  return;
               }

               this.selected = this.currentListLength - 1;
               if (this.isClanOption) {
                  this.selected = -1;
               }

               if (this.size_tab > 0) {
                  this.selected = -1;
               }
            }

            this.lastSelect[this.currentTabIndex] = this.selected;
            this.cSelected = 0;
            this.getCurrClanOtion();
         } else if (GameCanvas.keyPressed[8]) {
            cmtoYValue = 1;
            this.isTabInven();
            this.selected++;
            if (this.type == 24) {
               this.selected += 2;
               if (this.selected > this.currentListLength - 1) {
                  this.selected = this.currentListLength - 1;
               }
            } else if (this.selected > this.currentListLength - 1) {
               if (this.equals(GameCanvas.panel)
                  && this.typeShop == 2
                  && this.currentTabIndex <= 3
                  && this.maxPageShop[this.currentTabIndex] > 1) {
                  InfoDlg.showWait();
                  if (this.currPageShop[this.currentTabIndex] >= this.maxPageShop[this.currentTabIndex] - 1) {
                     Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, 0, -1);
                     return;
                  }

                  Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, this.currPageShop[this.currentTabIndex] + 1, -1);
                  return;
               }

               this.selected = 0;
            }

            this.lastSelect[this.currentTabIndex] = this.selected;
            this.cSelected = 0;
            this.getCurrClanOtion();
         }

         if (cmtoYValue != 0) {
            this.cmtoY = this.selected * this.ITEM_HEIGHT - this.hScroll / 2;
            if (this.cmtoY > this.cmyLim) {
               this.cmtoY = this.cmyLim;
            }

            if (this.cmtoY < 0) {
               this.cmtoY = 0;
            }

            this.cmy = this.cmtoY;
         }

         if (GameCanvas.isPointerDown) {
            this.justRelease = false;
            if (!this.pointerIsDowning
               && GameCanvas.isPointer(this.xScroll, this.yScroll, this.wScroll, this.hScroll)) {
               for (int pointerDownLastXIndex = 0; pointerDownLastXIndex < this.pointerDownLastX.length; pointerDownLastXIndex++) {
                  this.pointerDownLastX[0] = GameCanvas.py;
               }

               this.pointerDownFirstX = GameCanvas.py;
               this.pointerIsDowning = true;
               this.isDownWhenRunning = this.cmRun != 0;
               this.cmRun = 0;
            } else if (this.pointerIsDowning) {
               this.pointerDownTime++;
               if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                  this.pointerDownFirstX = -1000;
                  this.selected = (this.cmtoY + GameCanvas.py - this.yScroll) / this.ITEM_HEIGHT;
                  if (this.selected >= this.currentListLength) {
                     this.selected = -1;
                  }

                  this.checkOptionSelect();
               }

               if ((cmtoYValue = GameCanvas.py - this.pointerDownLastX[0]) != 0 && this.selected != -1) {
                  this.selected = -1;
                  this.cSelected = -1;
               }

               for (int pointerDownLastXIndex2 = this.pointerDownLastX.length - 1; pointerDownLastXIndex2 > 0; pointerDownLastXIndex2--) {
                  this.pointerDownLastX[pointerDownLastXIndex2] = this.pointerDownLastX[pointerDownLastXIndex2 - 1];
               }

               this.pointerDownLastX[0] = GameCanvas.py;
               this.cmtoY -= cmtoYValue;
               if (this.cmtoY < 0) {
                  this.cmtoY = 0;
               }

               if (this.cmtoY > this.cmyLim) {
                  this.cmtoY = this.cmyLim;
               }

               if (this.cmy < 0 || this.cmy > this.cmyLim) {
                  cmtoYValue /= 2;
               }

               this.cmy -= cmtoYValue;
            }
         }

         if (GameCanvas.isPointerJustRelease && this.pointerIsDowning) {
            this.justRelease = true;
            cmtoYValue = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerJustRelease = false;
            if (Res.abs(cmtoYValue) < 20
               && Res.abs(GameCanvas.py - this.pointerDownFirstX) < 20
               && !this.isDownWhenRunning) {
               this.cmRun = 0;
               this.cmtoY = this.cmy;
               this.pointerDownFirstX = -1000;
               this.selected = (this.cmtoY + GameCanvas.py - this.yScroll) / this.ITEM_HEIGHT;
               if (this.selected >= this.currentListLength) {
                  this.selected = -1;
               }

               this.checkOptionSelect();
               this.pointerDownTime = 0;
               this.waitToPerform = 10;
               SoundMn.gI();
               SoundMn.panelClick();
            } else if (this.selected != -1 && this.pointerDownTime > 5) {
               this.pointerDownTime = 0;
               this.waitToPerform = 1;
            } else if (this.selected == -1 && !this.isDownWhenRunning) {
               if (this.cmy < 0) {
                  this.cmtoY = 0;
               } else if (this.cmy > this.cmyLim) {
                  this.cmtoY = this.cmyLim;
               } else {
                  int value;
                  byte byteValue;
                  if ((
                        value = GameCanvas.py
                           - this.pointerDownLastX[0]
                           + (this.pointerDownLastX[0] - this.pointerDownLastX[1])
                           + (this.pointerDownLastX[1] - this.pointerDownLastX[2])
                     )
                     > 10) {
                     byteValue = 10;
                  } else if (value < -10) {
                     byteValue = -10;
                  } else {
                     byteValue = 0;
                  }

                  this.cmRun = -byteValue * 100;
               }
            }

            if ((this.isTabInven() || this.type == 13) && GameCanvas.py < this.yScroll + 21) {
               this.selected = 0;
               this.updateKeyInvenTab();
            }

            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerJustRelease = false;
         }
      }
   }

   private void setTabPetStatus() {
      this.currentListLength = this.strStatus.length;
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }
   }

   private void setTabTool() {
      SoundMn.gI();
      SoundMn.getSoundOption();
      this.currentListLength = strTool.length;
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }
   }

   public final void initTabClans() {
      if (this.isSearchClan) {
         this.currentListLength = this.clans == null ? 2 : this.clans.length + 2;
         this.clanInfo = mResources.clan_list;
      } else if (this.isViewMember) {
         this.clanReport = "";
         this.currentListLength = (this.member == null ? this.myMember.size() : this.member.size()) + 2;
         this.clanInfo = mResources.member
            + " "
            + (this.currClan != null ? this.currClan.name : Char.myCharz().clan.name);
      } else if (this.isMessage) {
         this.currentListLength = ClanMessage.vMessage.size() + 2;
         this.clanInfo = mResources.msg;
         this.clanReport = "";
      }

      if (Char.myCharz().clan == null) {
         this.clansOption = new String[][]{mResources.findClan, mResources.createClan};
      } else if (!this.isViewMember) {
         if (this.myMember.size() > 1) {
            this.clansOption = new String[][]{mResources.chatClan, mResources.request_pea2, mResources.memberr};
         } else {
            this.clansOption = new String[][]{mResources.memberr};
         }
      } else if (Char.myCharz().role > 0) {
         this.clansOption = new String[][]{mResources.msgg, mResources.leaveClan};
      } else if (this.myMember.size() > 1) {
         this.clansOption = new String[][]{
            mResources.msgg, mResources.leaveClan, mResources.khau_hieuu, mResources.bieu_tuongg
         };
      } else {
         this.clansOption = new String[][]{mResources.msgg, mResources.khau_hieuu, mResources.bieu_tuongg};
      }

      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }
   }

   public final void setTabClans() {
      GameScr.isNewClanMessage = false;
      this.ITEM_HEIGHT = 24;
      if (this.lastSelect != null && this.lastSelect[3] == 0) {
         this.lastSelect[3] = -1;
      }

      this.currentListLength = 2;
      if (Char.myCharz().clan != null) {
         this.isMessage = true;
         this.isViewMember = false;
         this.isSearchClan = false;
      } else {
         this.isMessage = false;
         this.isViewMember = false;
         this.isSearchClan = true;
      }

      if (Char.myCharz().clan != null) {
         this.currentListLength = ClanMessage.vMessage.size() + 2;
      }

      this.initTabClans();
      this.cSelected = -1;
      if (this.chatTField == null) {
         this.chatTField = new ChatTextField();
         this.chatTField.tfChat.y = GameCanvas.h
            - 35
            - ChatTextField.gI().tfChat.height;
         this.chatTField.initChatTextField();
         this.chatTField.parentScreen = GameCanvas.panel;
      }

      if (Char.myCharz().clan == null) {
         this.clanReport = mResources.findingClan;
         Service.gI().searchClan("");
      }

      this.selected = this.lastSelect[this.currentTabIndex];
      if (GameCanvas.isTouch) {
         this.selected = -1;
      }
   }

   public final void initLogMessage() {
      this.currentListLength = this.logChat.size() + 1;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.cmx = this.cmtoX = 0;
   }

   public final void setTabShop() {
      this.ITEM_HEIGHT = 24;
      if (this.currentTabIndex == this.currentTabName.length - 1 && GameCanvas.panel2 == null && this.typeShop != 2) {
         this.currentListLength = this.checkCurrentListLength(Char.myCharz().arrItemBody.length + Char.myCharz().arrItemBag.length);
      } else {
         this.currentListLength = Char.myCharz().arrItemShop[this.currentTabIndex].length;
      }

      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   private void setTabSkill() {
      this.ITEM_HEIGHT = 30;
      this.currentListLength = Char.myCharz().nClass.skillTemplates.length + 6;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   private void setTabZone() {
      this.ITEM_HEIGHT = 24;
      this.currentListLength = GameScr.gI().zones.length;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      this.cmy = this.cmtoY = 0;
      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   private void setTabBox() {
      this.currentListLength = this.checkCurrentListLength(Char.myCharz().arrItemBox.length);
      this.ITEM_HEIGHT = 24;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 9;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   private void setTabPetInventory() {
      this.ITEM_HEIGHT = 30;
      Item[] itemBody = Char.myPetz().arrItemBody;
      Skill[] petSkill = Char.myPetz().arrPetSkill;
      this.currentListLength = itemBody.length + petSkill.length;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = 0;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   private void setTabInventory(boolean resetSelect) {
      this.currentListLength = this.checkCurrentListLength(Char.myCharz().arrItemBody.length + Char.myCharz().arrItemBag.length);
      this.ITEM_HEIGHT = 24;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = 0;
      }

      if (resetSelect) {
         this.selected = GameCanvas.isTouch ? -1 : 0;
      }
   }

   private void moveCamera() {
      if (this.timeShow > 0) {
         this.timeShow--;
      }

      if (this.justRelease && this.equals(GameCanvas.panel) && this.typeShop == 2 && this.maxPageShop[this.currentTabIndex] > 1
         )
       {
         if (this.cmy < -50) {
            InfoDlg.showWait();
            this.justRelease = false;
            if (this.currPageShop[this.currentTabIndex] <= 0) {
               Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, this.maxPageShop[this.currentTabIndex] - 1, -1);
            } else {
               Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, this.currPageShop[this.currentTabIndex] - 1, -1);
            }
         } else if (this.cmy > this.cmyLim + 50) {
            this.justRelease = false;
            InfoDlg.showWait();
            if (this.currPageShop[this.currentTabIndex] >= this.maxPageShop[this.currentTabIndex] - 1) {
               Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, 0, -1);
            } else {
               Service.gI().kigui((byte)4, -1, (byte)this.currentTabIndex, this.currPageShop[this.currentTabIndex] + 1, -1);
            }
         }
      }

      if (this.cmx != this.cmtoX && !this.pointerIsDowning) {
         this.cmvx = this.cmtoX - this.cmx << 2;
         this.cmdx = this.cmdx + this.cmvx;
         this.cmx = this.cmx + (this.cmdx >> 3);
         this.cmdx &= 15;
      }

      if (Math.abs(this.cmtoX - this.cmx) < 10) {
         this.cmx = this.cmtoX;
      }

      if (this.isClose) {
         this.isClose = false;
         this.cmtoX = this.wScroll;
      }

      if (this.cmtoX >= this.wScroll - 10 && this.cmx >= this.wScroll - 10 && this.position == 0) {
         this.isShow = false;
         this.cleanCombine();
         if (this.isChangeZone) {
            this.isChangeZone = false;
            if (Char.myCharz().cHP > 0L && Char.myCharz().statusMe != 14) {
               InfoDlg.showWait();
               if (this.type == 3) {
                  Service.gI().requestChangeZone(this.selected);
               } else if (this.type == 14) {
                  Service.gI().requestMapSelect(this.selected);
               }
            }
         }

         if (this.isSelectPlayerMenu) {
            this.isSelectPlayerMenu = false;
            int value = this.vPlayerMenu.size() - this.vPlayerMenu_id.size();
            if (Char.myCharz().charFocus != null) {
               if (this.selected - value < 0) {
                  Char.myCharz().charFocus.menuSelect = this.selected;
               } else {
                  Char.myCharz().charFocus.menuSelect = Short.parseShort(
                     (String)this.vPlayerMenu_id.elementAt(this.selected - value)
                  );
               }
            }

            ((Command)this.vPlayerMenu.elementAt(this.selected)).performAction();
         }

         this.vPlayerMenu.removeAllElements();
         this.vPlayerMenu_id.removeAllElements();
         this.charMenu = null;
      }

      if (this.cmRun != 0 && !this.pointerIsDowning) {
         this.cmtoY = this.cmtoY + this.cmRun / 100;
         if (this.cmtoY < 0) {
            this.cmtoY = 0;
         } else if (this.cmtoY > this.cmyLim) {
            this.cmtoY = this.cmyLim;
         } else {
            this.cmy = this.cmtoY;
         }

         this.cmRun = this.cmRun * 9 / 10;
         if (this.cmRun < 100 && this.cmRun > -100) {
            this.cmRun = 0;
         }
      }

      if (this.cmy != this.cmtoY && !this.pointerIsDowning) {
         this.cmvy = this.cmtoY - this.cmy << 2;
         this.cmdy = this.cmdy + this.cmvy;
         this.cmy = this.cmy + (this.cmdy >> 4);
         this.cmdy &= 15;
      }

      this.cmyLast[this.currentTabIndex] = this.cmy;
   }

   private void paintTop(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      g.setColor(0);
      if (this.currentListLength != 0) {
         int currentListLengthValue;
         if ((currentListLengthValue = (this.cmy + this.hScroll) / 24 + 1) < this.hScroll / 24 + 1) {
            currentListLengthValue = this.hScroll / 24 + 1;
         }

         if (currentListLengthValue > this.currentListLength) {
            currentListLengthValue = this.currentListLength;
         }

         int value;
         if ((value = this.cmy / 24) >= currentListLengthValue) {
            value = currentListLengthValue - 1;
         }

         if (value < 0) {
            value = 0;
         }

         for (int topIndex = value; topIndex < currentListLengthValue; topIndex++) {
            int x = this.xScroll;
            int y = this.yScroll + topIndex * this.ITEM_HEIGHT;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll + 24;
            int y2 = this.yScroll + topIndex * this.ITEM_HEIGHT;
            int width = this.wScroll - 24;
            int height2 = this.ITEM_HEIGHT - 1;
            g.setColor(topIndex == this.selected ? 16383818 : 15196114);
            g.fillRect(x2, y2, width, height2);
            g.setColor(topIndex == this.selected ? 9541120 : 9993045);
            g.fillRect(x, y, 24, height);
            TopInfo top;
            if ((top = (TopInfo)this.vTop.elementAt(topIndex)).headICON != -1) {
               SmallImage.drawSmallImage(g, top.headICON, x, y, 0, 0);
            } else {
               Part part = GameScr.parts[top.headID];
               SmallImage.drawSmallImage(
                  g,
                  part.pi[Char.CharInfo[0][0][0]].id,
                  x + part.pi[Char.CharInfo[0][0][0]].dx,
                  y + height2 - 1,
                  0,
                  36
               );
            }

            g.setClip(this.xScroll, this.yScroll + this.cmy, this.wScroll, this.hScroll);
            if (top.pId != Char.myCharz().charID) {
               mFont.tahoma_7b_green.drawString(g, top.name, x2 + 5, y2, 0);
            } else {
               mFont.tahoma_7b_red.drawString(g, top.name, x2 + 5, y2, 0);
            }

            mFont.tahoma_7_blue.drawString(g, top.info, x2 + width - 5, y2 + 11, 1);
            mFont.tahoma_7_green2.drawString(g, mResources.rank + ": " + top.rank, x2 + 5, y2 + 11, 0);
         }

         this.paintScrollArrow(g);
      }
   }

   public final void paint(mGraphics graphicsValue) {
      graphicsValue.translate(-graphicsValue.getTranslateX(), -graphicsValue.getTranslateY());
      graphicsValue.translate(-this.cmx, 0);
      graphicsValue.translate(this.X, 0);
      if (GameCanvas.panel.combineSuccess != -1) {
         if (this.equals(GameCanvas.panel)) {
            mGraphics g = graphicsValue;
            Panel panel = this;
            GameScr.gI();
            GameScr.paintBlackSky(g);
            mGraphics g2 = g;
            Panel panel2 = panel;
            g2.translate(-GameScr.cmx, -GameScr.cmy);
            if (panel2.typeCombine < 3) {
               for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
                  Npc npc;
                  if ((npc = (Npc)GameScr.vNpc.elementAt(npcIndex)).template.npcTemplateId == panel2.idNPC) {
                     npc.paint(g2);
                     if (npc.chatInfo != null) {
                        npc.chatInfo
                           .paint(
                              g2,
                              npc.cx,
                              npc.cy - npc.ch - GameCanvas.transY,
                              npc.cdir
                           );
                     }
                  }
               }
            }

            GameCanvas.resetTrans(g2);
            if (GameCanvas.gameTick % 4 == 0) {
               g2.drawImage(ItemMap.imageFlare, ((int) (panel2.xS - 5)), ((int) (panel2.yS + 15)), 33);
               g2.drawImage(ItemMap.imageFlare, ((int) (panel2.xS + 5)), ((int) (panel2.yS + 15)), 33);
               g2.drawImage(ItemMap.imageFlare, ((int) (panel2.xS)), ((int) (panel2.yS + 15)), 33);
            }

            for (int effect3Index = 0; effect3Index < Effect2.vEffect3.size(); effect3Index++) {
               ((Effect2)Effect2.vEffect3.elementAt(effect3Index)).paint(g2);
            }

            if (GameCanvas.gameTick % 4 == 0) {
               g.drawImage(ItemMap.imageFlare, ((int) (panel.xS)), ((int) (panel.yS + 15)), 33);
            }

            if (panel.typeCombine == 0) {
               for (int xDotSIndex = 0; xDotSIndex < panel.yArgS.length; xDotSIndex++) {
                  SmallImage.drawSmallImage(g, panel.iconID1, panel.xS, panel.yS, 0, 3);
                  if (panel.isPaintCombine) {
                     SmallImage.drawSmallImage(g, panel.iconID2, panel.xDotS[xDotSIndex], panel.yDotS[xDotSIndex], 0, 3);
                  }
               }
            } else if (panel.typeCombine == 1) {
               if (!panel.isPaintCombine) {
                  SmallImage.drawSmallImage(g, panel.iconID3, panel.xS, panel.yS, 0, 3);
               } else {
                  for (int yArgSIndex = 0; yArgSIndex < panel.yArgS.length; yArgSIndex++) {
                     SmallImage.drawSmallImage(g, panel.iconID1, panel.xDotS[0], panel.yDotS[0], 0, 3);
                     SmallImage.drawSmallImage(g, panel.iconID2, panel.xDotS[1], panel.yDotS[1], 0, 3);
                  }
               }
            } else if (panel.typeCombine == 2) {
               if (!panel.isPaintCombine) {
                  SmallImage.drawSmallImage(g, panel.iconID3, panel.xS, panel.yS, 0, 3);
               } else {
                  for (int xDotSIndex2 = 0; xDotSIndex2 < panel.yArgS.length; xDotSIndex2++) {
                     SmallImage.drawSmallImage(g, panel.iconID1, panel.xDotS[xDotSIndex2], panel.yDotS[xDotSIndex2], 0, 3);
                  }
               }
            } else if (panel.typeCombine == 3) {
               if (!panel.isPaintCombine) {
                  SmallImage.drawSmallImage(g, panel.iconID3, panel.xS, panel.yS, 0, 3);
               } else {
                  SmallImage.drawSmallImage(g, panel.iconID1, panel.xS, panel.yS, 0, 3);
               }
            } else {
               if (panel.typeCombine != 4) {
                  return;
               }

               if (panel.isPaintCombine) {
                  for (int iconIDIndex = 0; iconIDIndex < panel.iconID.length; iconIDIndex++) {
                     SmallImage.drawSmallImage(g, panel.iconID[iconIDIndex], panel.xDotS[iconIDIndex], panel.yDotS[iconIDIndex], 0, 3);
                  }

                  return;
               }

               if (panel.iconID3 == -1) {
                  return;
               }

               SmallImage.drawSmallImage(g, panel.iconID3, panel.xS, panel.yS, 0, 3);
            }
         }
      } else {
         Paint.paintFrameSimple(this.X, 0, this.W, this.H, graphicsValue);

         try {
            mGraphics g3 = graphicsValue;
            Panel panel3 = this;
            g3.setClip(panel3.X + 1, 0, panel3.W - 2, panel3.yScroll - 2);
            g3.setColor(9993045);
            g3.fillRect(panel3.X, 0, panel3.W - 2, 50);
            switch (panel3.type) {
               case 0:
                  if (panel3.currentTabIndex == 0) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintMyInfo(g3);
                  }

                  if (panel3.currentTabIndex == 1) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintItemBodyBagInfo(g3);
                  }

                  if (panel3.currentTabIndex == 2) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     mGraphics graphicsValue2 = g3;
                     Panel panel4 = panel3;
                     mFont.tahoma_7_white
                        .drawString(
                           graphicsValue2, "Top " + Char.myCharz().topRankingPosition, panel4.X + 45 + (panel4.W - 50) / 2, 2, 2
                        );
                     mFont.tahoma_7_yellow
                        .drawString(graphicsValue2, mResources.potential_point, panel4.X + 45 + (panel4.W - 50) / 2, 14, 2);
                     mFont.tahoma_7_white
                        .drawString(
                           graphicsValue2,
                           Firework.getMoneys(Char.myCharz().cTiemNang),
                           panel4.X
                              + (GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0)
                              + 45
                              + (panel4.W - 50) / 2,
                           26,
                           2
                        );
                     mFont.tahoma_7_yellow
                        .drawString(
                           graphicsValue2,
                           mResources.active_point + ": " + Firework.getMoneys(Char.myCharz().cNangdong),
                           panel4.X
                              + (GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0)
                              + 45
                              + (panel4.W - 50) / 2,
                           38,
                           2
                        );
                  }

                  if (panel3.currentTabIndex == 3) {
                     if (panel3.mainTabName.length == 5) {
                        mGraphics graphicsValue3 = g3;
                        Panel panel5 = panel3;
                        if (Char.myCharz().clan == null) {
                           SmallImage.drawSmallImage(graphicsValue3, Char.myCharz().avatarz(), 25, 50, 0, 33);
                           mFont.tahoma_7b_white.drawString(graphicsValue3, mResources.not_join_clan, (panel5.wScroll - 50) / 2 + 50, 20, 2);
                        } else if (!panel5.isViewMember) {
                           Clan clan;
                           if ((clan = Char.myCharz().clan) != null) {
                              SmallImage.drawSmallImage(graphicsValue3, Char.myCharz().avatarz(), 25, 50, 0, 33);
                              mFont.tahoma_7b_white.drawString(graphicsValue3, clan.name, 60, 4, 0, mFont.tahoma_7b_dark);
                              mFont.tahoma_7_yellow
                                 .drawString(
                                    graphicsValue3, mResources.achievement_point + ": " + clan.powerPoint, 60, 16, 0, mFont.tahoma_7_grey
                                 );
                              mFont.tahoma_7_yellow
                                 .drawString(
                                    graphicsValue3, mResources.clan_point + ": " + clan.clanPoint, 60, 27, 0, mFont.tahoma_7_grey
                                 );
                              mFont.tahoma_7_yellow
                                 .drawString(
                                    graphicsValue3, mResources.level + ": " + clan.level, 60, 38, 0, mFont.tahoma_7_grey
                                 );
                              TextInfo.paint(
                                 graphicsValue3, clan.slogan, 60, 38, panel5.wScroll - 70, panel5.ITEM_HEIGHT, mFont.tahoma_7_yellow
                              );
                           }
                        } else {
                           Clan clan2 = panel5.currClan != null ? panel5.currClan : Char.myCharz().clan;
                           SmallImage.drawSmallImage(graphicsValue3, Char.myCharz().avatarz(), 25, 50, 0, 33);
                           mFont.tahoma_7b_white.drawString(graphicsValue3, clan2.name, 60, 4, 0, mFont.tahoma_7b_dark);
                           mFont.tahoma_7_yellow
                              .drawString(
                                 graphicsValue3,
                                 mResources.member + ": " + clan2.currMember + "/" + clan2.maxMember,
                                 60,
                                 16,
                                 0,
                                 mFont.tahoma_7_grey
                              );
                           mFont.tahoma_7_yellow
                              .drawString(graphicsValue3, mResources.clan_leader + ": " + clan2.leaderName, 60, 27, 0, mFont.tahoma_7_grey);
                           TextInfo.paint(
                              graphicsValue3, clan2.slogan, 60, 38, panel5.wScroll - 70, panel5.ITEM_HEIGHT, mFont.tahoma_7_yellow
                           );
                        }
                     } else {
                        SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                        paintToolInfo(g3);
                     }
                  }

                  if (panel3.currentTabIndex == 4) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     paintToolInfo(g3);
                  }
                  break;
               case 1:
                  if (panel3.currentTabIndex == panel3.currentTabName.length - 1 && GameCanvas.panel2 == null) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  } else if (Char.myCharz().npcFocus != null) {
                     int x = panel3.X + 25;
                     SmallImage.drawSmallImage(g3, Char.myCharz().npcFocus.avatar, x, 50, 0, 33);
                  }

                  mGraphics graphicsValue4 = g3;
                  Panel panel6 = panel3;
                  if (panel3.currentTabIndex == panel6.currentTabName.length - 1 && GameCanvas.panel2 == null) {
                     panel6.paintMyInfo(graphicsValue4);
                  } else if (panel6.selected < 0) {
                     if (panel6.typeShop != 2) {
                        mFont.tahoma_7_white.drawString(graphicsValue4, mResources.say_hello, panel6.X + 60, 14, 0);
                        mFont.tahoma_7_white.drawString(graphicsValue4, strWantToBuy, panel6.X + 60, 26, 0);
                     } else {
                        mFont.tahoma_7_white.drawString(graphicsValue4, mResources.say_hello, panel6.X + 60, 5, 0);
                        mFont.tahoma_7_white.drawString(graphicsValue4, strWantToBuy, panel6.X + 60, 17, 0);
                        mFont.tahoma_7_white
                           .drawString(
                              graphicsValue4,
                              mResources.page
                                 + " "
                                 + (panel6.currPageShop[panel6.currentTabIndex] + 1)
                                 + "/"
                                 + panel6.maxPageShop[panel6.currentTabIndex],
                              panel6.X + 60,
                              29,
                              0
                           );
                     }
                  } else {
                     Item itemShop;
                     if (panel6.currentTabIndex >= 0
                        && panel6.currentTabIndex <= Char.myCharz().arrItemShop.length - 1
                        && panel6.selected >= 0
                        && panel6.selected <= Char.myCharz().arrItemShop[panel6.currentTabIndex].length - 1
                        && (itemShop = Char.myCharz().arrItemShop[panel6.currentTabIndex][panel6.selected]) != null) {
                        if (panel6.equals(GameCanvas.panel) && panel6.currentTabIndex <= 3 && panel6.typeShop == 2) {
                           mFont.tahoma_7b_white
                              .drawString(
                                 graphicsValue4,
                                 mResources.page
                                    + " "
                                    + (panel6.currPageShop[panel6.currentTabIndex] + 1)
                                    + "/"
                                    + panel6.maxPageShop[panel6.currentTabIndex],
                                 panel6.X + 55,
                                 4,
                                 0
                              );
                        }

                        mFont.tahoma_7b_white.drawString(graphicsValue4, itemShop.template.name, panel6.X + 55, 24, 0);
                        String text = mResources.pow_request + " " + Res.formatNumber(itemShop.template.strRequire);
                        if (itemShop.template.strRequire > Char.myCharz().cPower) {
                           mFont.tahoma_7_yellow.drawString(graphicsValue4, text, panel6.X + 55, 35, 0);
                        } else {
                           mFont.tahoma_7_green.drawString(graphicsValue4, text, panel6.X + 55, 35, 0);
                        }
                     }
                  }
                  break;
               case 2:
                  if (panel3.currentTabIndex == 0) {
                     SmallImage.drawSmallImage(g3, 526, panel3.X + 25, 50, 0, 33);
                     mGraphics graphicsValue5 = g3;
                     Panel panel7 = panel3;
                     String text2 = mResources.used
                        + ": "
                        + panel7.hasUse
                        + "/"
                        + Char.myCharz().arrItemBox.length
                        + " "
                        + mResources.place;
                     mFont.tahoma_7b_white.drawString(graphicsValue5, mResources.chest, 60, 4, 0);
                     mFont.tahoma_7_yellow.drawString(graphicsValue5, text2, 60, 16, 0);
                  }

                  if (panel3.currentTabIndex == 1) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintItemBodyBagInfo(g3);
                  }
                  break;
               case 3:
                  SmallImage.drawSmallImage(g3, 561, panel3.X + 25, 50, 0, 33);
                  mGraphics graphicsValue6 = g3;
                  mFont.tahoma_7b_white
                     .drawString(graphicsValue6, mResources.zone + " " + TileMap.zoneID, 60, 4, 0, mFont.tahoma_7b_dark);
                  mFont.tahoma_7_yellow.drawString(graphicsValue6, TileMap.mapName, 60, 16, 0, mFont.tahoma_7_grey);
                  mFont.tahoma_7b_white.drawString(graphicsValue6, String.valueOf(TileMap.zoneID), 25, 27, 2);
                  break;
               case 4:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  paintMapInfo(g3);
               case 5:
               case 6:
               default:
                  break;
               case 7:
               case 17:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 8:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 9:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 10:
                  if (panel3.charMenu != null) {
                     SmallImage.drawSmallImage(g3, panel3.charMenu.avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintCharInfo(g3, panel3.charMenu);
                  }
                  break;
               case 11:
               case 16:
               case 23:
               case 24:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 12:
                  if (panel3.currentTabIndex == 0) {
                     int id = 1410;

                     for (int npcIndex2 = 0; npcIndex2 < GameScr.vNpc.size(); npcIndex2++) {
                        Npc npc2;
                        if ((npc2 = (Npc)GameScr.vNpc.elementAt(npcIndex2)).template.npcTemplateId == panel3.idNPC) {
                           id = npc2.avatar;
                        }
                     }

                     SmallImage.drawSmallImage(g3, id, panel3.X + 25, 50, 0, 33);
                     mGraphics graphicsValue7 = g3;
                     Panel panel8 = panel3;
                     if (panel3.combineTopInfo != null) {
                        for (int combineTopInfoIndex = 0; combineTopInfoIndex < panel8.combineTopInfo.length; combineTopInfoIndex++) {
                           mFont.tahoma_7_white
                              .drawString(
                                 graphicsValue7, panel8.combineTopInfo[combineTopInfoIndex], panel8.X + 45 + (panel8.W - 50) / 2, 5 + combineTopInfoIndex * 14, 2
                              );
                        }
                     }
                  }

                  if (panel3.currentTabIndex == 1) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintMyInfo(g3);
                  }
                  break;
               case 13:
                  if (panel3.currentTabIndex == 0 || panel3.currentTabIndex == 1) {
                     if (panel3.equals(GameCanvas.panel)) {
                        SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                        mGraphics graphicsValue8 = g3;
                        mFont.tahoma_7_yellow.drawString(graphicsValue8, mResources.select_item, 60, 4, 0, mFont.tahoma_7_grey);
                        mFont.tahoma_7_yellow.drawString(graphicsValue8, mResources.lock_trade, 60, 16, 0, mFont.tahoma_7_grey);
                        mFont.tahoma_7_yellow.drawString(graphicsValue8, mResources.wait_opp_lock_trade, 60, 27, 0, mFont.tahoma_7_grey);
                        mFont.tahoma_7_yellow.drawString(graphicsValue8, mResources.press_done, 60, 38, 0, mFont.tahoma_7_grey);
                     }

                     if (panel3.equals(GameCanvas.panel2) && panel3.charMenu != null) {
                        SmallImage.drawSmallImage(g3, panel3.charMenu.avatarz(), panel3.X + 25, 50, 0, 33);
                        panel3.paintCharInfo(g3, panel3.charMenu);
                     }
                  }

                  if (panel3.currentTabIndex == 2 && panel3.charMenu != null) {
                     SmallImage.drawSmallImage(g3, panel3.charMenu.avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintCharInfo(g3, panel3.charMenu);
                  }
                  break;
               case 14:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  paintMapInfo(g3);
                  break;
               case 15:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 18:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 19:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  paintToolInfo(g3);
                  break;
               case 20:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  paintToolInfo(g3);
                  break;
               case 21:
                  if (panel3.currentTabIndex == 0) {
                     SmallImage.drawSmallImage(g3, Char.myPetz().avatarz(), panel3.X + 25, 50, 0, 33);
                     mGraphics graphicsValue9 = g3;
                     Panel panel9 = panel3;
                     mFont.tahoma_7_yellow
                        .drawString(
                           graphicsValue9,
                           mResources.power + ": " + Firework.getMoneys(Char.myPetz().cPower),
                           panel9.X + 60,
                           4,
                           0,
                           mFont.tahoma_7_grey
                        );
                     if (Char.myPetz().cPower > 0L) {
                        mFont.tahoma_7_yellow
                           .drawString(
                              graphicsValue9,
                              Char.myPetz().me ? Char.myPetz().getStrLevel() : Char.myPetz().currStrLevel,
                              panel9.X + 60,
                              16,
                              0,
                              mFont.tahoma_7_grey
                           );
                     }

                     if (Char.myPetz().cDamFull > 0L) {
                        mFont.tahoma_7_yellow
                           .drawString(
                              graphicsValue9,
                              mResources.hit_point + " :" + Char.myPetz().cDamFull,
                              panel9.X + 60,
                              27,
                              0,
                              mFont.tahoma_7_grey
                           );
                     }

                     if (Char.myPetz().cMaxStamina > 0) {
                        mFont.tahoma_7_yellow
                           .drawString(graphicsValue9, mResources.vitality, panel9.X + 60, 38, 0, mFont.tahoma_7_grey);
                        graphicsValue9.drawImage(GameScr.imgMPLost, ((int) (panel9.X + 100)), ((int) (41)), 0);
                        int width = Char.myPetz().cStamina
                           * mGraphics.getImageWidth(GameScr.imgMP)
                           / Char.myPetz().cMaxStamina;
                        graphicsValue9.setClip(100, panel9.X + 41, width, 20);
                        graphicsValue9.drawImage(GameScr.imgMP, ((int) (panel9.X + 100)), ((int) (41)), 0);
                     }

                     graphicsValue9.setClip(0, 0, GameCanvas.w, GameCanvas.h);
                  }

                  if (panel3.currentTabIndex == 1) {
                     SmallImage.drawSmallImage(g3, Char.myPetz().avatarz(), panel3.X + 25, 50, 0, 33);
                     mGraphics graphicsValue10 = g3;
                     Panel panel10 = panel3;
                     mFont.tahoma_7b_white
                        .drawString(
                           graphicsValue10,
                           "HP: " + Char.myPetz().cHP + "/" + Char.myPetz().cHPFull,
                           panel10.X + 60,
                           4,
                           0,
                           mFont.tahoma_7b_dark
                        );
                     mFont.tahoma_7b_white
                        .drawString(
                           graphicsValue10,
                           "MP: " + Char.myPetz().cMP + "/" + Char.myPetz().cMPFull,
                           panel10.X + 60,
                           16,
                           0,
                           mFont.tahoma_7b_dark
                        );
                     mFont.tahoma_7_yellow
                        .drawString(
                           graphicsValue10,
                           mResources.critical
                              + ": "
                              + Char.myPetz().cCriticalFull
                              + ", "
                              + mResources.armor
                              + ": "
                              + Char.myPetz().cDefull,
                           panel10.X + 60,
                           27,
                           0,
                           mFont.tahoma_7_grey
                        );
                     mFont.tahoma_7_yellow
                        .drawString(
                           graphicsValue10,
                           mResources.status + ": " + panel10.strStatus[Char.myPetz().petStatus],
                           panel10.X + 60,
                           38,
                           0,
                           mFont.tahoma_7_grey
                        );
                  }

                  if (panel3.currentTabIndex == 2) {
                     SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                     panel3.paintItemBodyBagInfo(g3);
                  }
                  break;
               case 22:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  paintToolInfo(g3);
                  break;
               case 25:
                  SmallImage.drawSmallImage(g3, Char.myCharz().avatarz(), panel3.X + 25, 50, 0, 33);
                  panel3.paintMyInfo(g3);
                  break;
               case 26:
                  SmallImage.drawSmallImage(g3, 561, panel3.X + 25, 50, 0, 33);
                  mGraphics g4 = g3;
                  Panel panel11 = panel3;
                  SmallImage.drawSmallImage(g4, Char.myCharz().avatarz(), panel11.X + 25, 50, 0, 33);
                  panel11.paintMyInfo(g4);
            }
         } catch (Exception ignoredException) {
         }

         mGraphics graphicsValue11 = graphicsValue;
         Panel panel12 = this;
         if (this.type != 13 || panel12.currentTabIndex != 2 && !panel12.equals(GameCanvas.panel2)) {
            graphicsValue11.setClip(0, 0, GameCanvas.w, GameCanvas.h);
            graphicsValue11.setColor(11837316);
            graphicsValue11.fillRect(panel12.X + 1, panel12.H - 15, panel12.W - 2, 14);
            graphicsValue11.setColor(13524492);
            graphicsValue11.fillRect(panel12.X + 1, panel12.H - 15, panel12.W - 2, 1);
            graphicsValue11.drawImage(imgXu, ((int) (panel12.X + 11)), ((int) (panel12.H - 7)), 3);
            graphicsValue11.drawImage(imgLuong, ((int) (panel12.X + 75)), ((int) (panel12.H - 8)), 3);
            mFont.tahoma_7_yellow
               .drawString(
                  graphicsValue11, Char.myCharz().xuStr, panel12.X + 24, panel12.H - 13, 0, mFont.tahoma_7_grey
               );
            mFont.tahoma_7_yellow
               .drawString(
                  graphicsValue11, Char.myCharz().luongStr, panel12.X + 85, panel12.H - 13, 0, mFont.tahoma_7_grey
               );
            graphicsValue11.drawImage(imgLuongKhoa, ((int) (panel12.X + 130)), ((int) (panel12.H - 8)), 3);
            mFont.tahoma_7_yellow
               .drawString(
                  graphicsValue11, Char.myCharz().luongKhoaStr, panel12.X + 140, panel12.H - 13, 0, mFont.tahoma_7_grey
               );
         }

         this.paintTab(graphicsValue);
         switch (this.type) {
            case 0:
               if (this.currentTabIndex == 0) {
                  this.paintTask(graphicsValue);
               }

               if (this.currentTabIndex == 1) {
                  this.paintInventory(graphicsValue);
               }

               if (this.currentTabIndex == 2) {
                  this.paintSkill(graphicsValue);
               }

               if (this.currentTabIndex == 3) {
                  if (this.mainTabName.length == 4) {
                     this.paintTools(graphicsValue);
                  } else {
                     this.paintClans(graphicsValue);
                  }
               }

               if (this.currentTabIndex == 4) {
                  this.paintTools(graphicsValue);
               }
               break;
            case 1:
               this.paintShop(graphicsValue);
               break;
            case 2:
               if (this.currentTabIndex == 0) {
                  this.paintBox(graphicsValue);
               }

               if (this.currentTabIndex == 1) {
                  this.paintInventory(graphicsValue);
               }
               break;
            case 3:
               this.paintZone(graphicsValue);
               break;
            case 4:
               this.paintMap(graphicsValue);
            case 5:
            case 6:
            case 22:
            default:
               break;
            case 7:
               this.paintInventory(graphicsValue);
               break;
            case 8:
               this.paintLogChat(graphicsValue);
               break;
            case 9:
               this.paintArchivement(graphicsValue);
               break;
            case 10:
               this.paintPlayerMenu(graphicsValue);
               break;
            case 11:
               this.paintFriend(graphicsValue);
               break;
            case 12:
               if (this.currentTabIndex == 0) {
                  this.paintCombine(graphicsValue);
               }

               if (this.currentTabIndex == 1) {
                  this.paintInventory(graphicsValue);
               }
               break;
            case 13:
               if (this.currentTabIndex == 0) {
                  if (this.equals(GameCanvas.panel)) {
                     this.paintInventory(graphicsValue);
                  } else {
                     this.paintGiaoDich(graphicsValue, false);
                  }
               }

               if (this.currentTabIndex == 1) {
                  this.paintGiaoDich(graphicsValue, true);
               }

               if (this.currentTabIndex == 2) {
                  this.paintGiaoDich(graphicsValue, false);
               }
               break;
            case 14:
               this.paintMapTrans(graphicsValue);
               break;
            case 15:
               this.paintTop(graphicsValue);
               break;
            case 16:
               this.paintEnemy(graphicsValue);
               break;
            case 17:
               this.paintShop(graphicsValue);
               break;
            case 18:
               this.paintFlagChange(graphicsValue);
               break;
            case 19:
               this.paintOption(graphicsValue);
               break;
            case 20:
               this.paintAccount(graphicsValue);
               break;
            case 21:
               if (this.currentTabIndex == 0) {
                  this.paintPetInventory(graphicsValue);
               }

               if (this.currentTabIndex == 1) {
                  this.paintPetStatus(graphicsValue);
               }

               if (this.currentTabIndex == 2) {
                  this.paintInventory(graphicsValue);
               }
               break;
            case 23:
               this.paintGameInfo(graphicsValue);
               break;
            case 24:
               this.paintGameSubInfo(graphicsValue);
               break;
            case 25:
               this.paintSpeacialSkill(graphicsValue);
               break;
            case 26:
               this.paintPaymentPackages(graphicsValue);
         }

         GameScr.resetTranslate(graphicsValue);
         graphicsValue11 = graphicsValue;
         panel12 = this;
         if (this.cp != null && panel12.cp.says != null) {
            panel12.cp.paint(graphicsValue11);
            int value = panel12.cp.cx + 13;
            int value2 = panel12.cp.cy + 11;
            if (panel12.type == 15) {
               value += 5;
               value2 += 26;
            }

            if (panel12.type == 0 && panel12.currentTabIndex == 3) {
               if (panel12.isSearchClan) {
                  value -= 5;
               } else if (panel12.partID != null || panel12.charInfo != null) {
                  value = panel12.cp.cx + 21;
                  value2 = panel12.cp.cy + 40;
               }
            }

            if (panel12.partID != null) {
               Part part = GameScr.parts[panel12.partID[0]];
               Part part2 = GameScr.parts[panel12.partID[1]];
               Part part3 = GameScr.parts[panel12.partID[2]];
               SmallImage.drawSmallImage(
                  graphicsValue11,
                  part.pi[Char.CharInfo[0][0][0]].id,
                  value + Char.CharInfo[0][0][1] + part.pi[Char.CharInfo[0][0][0]].dx,
                  value2 - Char.CharInfo[0][0][2] + part.pi[Char.CharInfo[0][0][0]].dy,
                  0,
                  0
               );
               SmallImage.drawSmallImage(
                  graphicsValue11,
                  part2.pi[Char.CharInfo[0][1][0]].id,
                  value + Char.CharInfo[0][1][1] + part2.pi[Char.CharInfo[0][1][0]].dx,
                  value2 - Char.CharInfo[0][1][2] + part2.pi[Char.CharInfo[0][1][0]].dy,
                  0,
                  0
               );
               SmallImage.drawSmallImage(
                  graphicsValue11,
                  part3.pi[Char.CharInfo[0][2][0]].id,
                  value + Char.CharInfo[0][2][1] + part3.pi[Char.CharInfo[0][2][0]].dx,
                  value2 - Char.CharInfo[0][2][2] + part3.pi[Char.CharInfo[0][2][0]].dy,
                  0,
                  0
               );
            } else if (panel12.charInfo != null) {
               panel12.charInfo.paintCharBody(graphicsValue11, value + 5, value2 + 25, 1, 0, true);
            } else if (panel12.idIcon != -1) {
               SmallImage.drawSmallImage(
                  graphicsValue11, panel12.idIcon, panel12.cp.cx + 8, panel12.cp.cy + 2, 0, 20
               );
            }

            if (panel12.currItem != null && panel12.currItem.template.type != 5) {
               if (panel12.currItem.compare > 0) {
                  graphicsValue11.drawImage(imgUp, ((int) (value - 7)), ((int) (value2 + 13)), 3);
                  mFont.tahoma_7b_green
                     .drawString(graphicsValue11, String.valueOf(Res.abs(panel12.currItem.compare)), value + 1, value2 + 8, 0);
               } else if (panel12.currItem.compare < 0 && panel12.currItem.compare != -1) {
                  graphicsValue11.drawImage(imgDown, ((int) (value - 7)), ((int) (value2 + 13)), 3);
                  mFont.tahoma_7b_red
                     .drawString(graphicsValue11, String.valueOf(Res.abs(panel12.currItem.compare)), value + 1, value2 + 8, 0);
               }
            }
         }

         if (this.cmx == this.cmtoX && !GameCanvas.menu.showMenu) {
            this.cmdClose.paint(graphicsValue);
         }

         if (this.tabIcon != null && this.tabIcon.isShow) {
            graphicsValue11 = graphicsValue;
            TabClanIcon tabIconValue = this.tabIcon;
            this.tabIcon.paintIcon(graphicsValue11);
         }
      }
   }

   private void paintShop(mGraphics g) {
      try {
         if (this.type == 1
            && this.currentTabIndex == this.currentTabName.length - 1
            && GameCanvas.panel2 == null
            && this.typeShop != 2) {
            this.paintInventory(g);
         } else {
            g.setColor(16711680);
            g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
            if (this.typeShop == 2 && this.equals(GameCanvas.panel)) {
               if (this.currentTabIndex <= 3 && GameCanvas.isTouch) {
                  if (this.cmy < -50) {
                     GameCanvas.paintShukiren(this.xScroll + this.wScroll / 2, this.yScroll + 30, g);
                  } else if (this.cmy < 0) {
                     mFont.tahoma_7_grey
                        .drawString(
                           g, mResources.getDown, this.xScroll + this.wScroll / 2, this.yScroll + 15, 2
                        );
                  } else if (this.cmyLim >= 0) {
                     if (this.cmy > this.cmyLim + 50) {
                        GameCanvas.paintShukiren(
                           this.xScroll + this.wScroll / 2, this.yScroll + this.hScroll - 30, g
                        );
                     } else if (this.cmy > this.cmyLim) {
                        mFont.tahoma_7_grey
                           .drawString(
                              g,
                              mResources.getUp,
                              this.xScroll + this.wScroll / 2,
                              this.yScroll + this.hScroll - 25,
                              2
                           );
                     }
                  }
               }

               if (Char.myCharz().arrItemShop[this.currentTabIndex].length == 0 && this.type != 17) {
                  mFont.tahoma_7_grey
                     .drawString(
                        g,
                        mResources.notYetSell,
                        this.xScroll + this.wScroll / 2,
                        this.yScroll + this.hScroll / 2 - 10,
                        2
                     );
                  return;
               }
            }

            g.translate(0, -this.cmy);
            Item[] itemShop = Char.myCharz().arrItemShop[this.currentTabIndex];
            if (this.typeShop == 2
               && (this.currentTabIndex == 4 || this.type == 17)
               && (itemShop = Char.myCharz().arrItemShop[4]).length == 0) {
               mFont.tahoma_7_grey
                  .drawString(
                     g,
                     mResources.notYetSell,
                     this.xScroll + this.wScroll / 2,
                     this.yScroll + this.hScroll / 2 - 10,
                     2
                  );
            } else {
               int itemShopCount = itemShop.length;

               for (int itemShopIndex = 0; itemShopIndex < itemShopCount; itemShopIndex++) {
                  int x = this.xScroll + 26;
                  int y = this.yScroll + itemShopIndex * this.ITEM_HEIGHT;
                  int width = this.wScroll - 26;
                  int height = this.ITEM_HEIGHT - 1;
                  int x2 = this.xScroll;
                  int y2 = this.yScroll + itemShopIndex * this.ITEM_HEIGHT;
                  int height2 = this.ITEM_HEIGHT - 1;
                  if (y - this.cmy <= this.yScroll + this.hScroll
                     && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
                     g.setColor(itemShopIndex == this.selected ? 16383818 : 15196114);
                     g.fillRectDelegated(x, y, width, height);
                     g.setColor(itemShopIndex == this.selected ? 9541120 : 9993045);
                     g.fillRectDelegated(x2, y2, 24, height2);
                     Item itemShop2;
                     if ((itemShop2 = itemShop[itemShopIndex]) != null) {
                        String text = "";
                        mFont tahoma_7_green2 = mFont.tahoma_7_green2;
                        if (itemShop2.isMe != 0
                           && this.typeShop == 2
                           && this.currentTabIndex <= 3
                           && !this.equals(GameCanvas.panel2)
                           && itemShop2.template.name.length() < 20) {
                           tahoma_7_green2 = mFont.tahoma_7b_green;
                        }

                        if (itemShop2.itemOption != null) {
                           for (int itemOptionIndex = 0; itemOptionIndex < itemShop2.itemOption.length; itemOptionIndex++) {
                              if (itemShop2.itemOption[itemOptionIndex].optionTemplate.id == 72) {
                                 text = " [+" + itemShop2.itemOption[itemOptionIndex].param + "]";
                              }

                              if (itemShop2.itemOption[itemOptionIndex].optionTemplate.id == 41) {
                                 if (itemShop2.itemOption[itemOptionIndex].param == 1) {
                                    tahoma_7_green2 = GetFont(0);
                                 } else if (itemShop2.itemOption[itemOptionIndex].param == 2) {
                                    tahoma_7_green2 = GetFont(2);
                                 } else if (itemShop2.itemOption[itemOptionIndex].param == 3) {
                                    tahoma_7_green2 = GetFont(8);
                                 } else if (itemShop2.itemOption[itemOptionIndex].param == 4) {
                                    tahoma_7_green2 = GetFont(7);
                                 }
                              }
                           }
                        }

                        tahoma_7_green2.drawString(g, itemShop2.template.name + text, x + 5, y + 1, 0);
                        String text2 = "";
                        if (itemShop2.itemOption != null && itemShop2.itemOption.length > 0) {
                           if (itemShop2.itemOption[0] != null
                              && itemShop2.itemOption[0].optionTemplate.id != 102
                              && itemShop2.itemOption[0].optionTemplate.id != 107) {
                              text2 = text2 + itemShop2.itemOption[0].getOptionString();
                           }

                           mFont tahoma_7_blue = mFont.tahoma_7_blue;
                           if (itemShop2.compare < 0 && itemShop2.template.type != 5) {
                              tahoma_7_blue = mFont.tahoma_7_red;
                           }

                           if (this.typeShop == 2 && itemShop2.itemOption.length > 1 && itemShop2.buyType != -1) {
                              text2 = text2;
                           }

                           if (this.typeShop != 2 || this.typeShop == 2 && itemShop2.buyType <= 1) {
                              tahoma_7_blue.drawString(g, text2, x + 5, y + 11, 0);
                           }
                        }

                        if (itemShop2.buySpec > 0) {
                           SmallImage.drawSmallImage(g, itemShop2.iconSpec, x + width - 7, y + 9, 0, 3);
                           mFont.tahoma_7b_blue
                              .drawString(g, Res.formatNumber(itemShop2.buySpec), x + width - 15, y + 1, 1);
                        }

                        if (itemShop2.buyCoin != 0 || itemShop2.buyGold != 0) {
                           if (this.typeShop != 2 && itemShop2.powerRequire == 0L) {
                              if (itemShop2.buyCoin > 0 && itemShop2.buyGold > 0) {
                                 if (itemShop2.buyCoin > 0) {
                                    g.drawImage(imgXu, ((int) (x + width - 7)), ((int) (y + 7)), 3);
                                    mFont.tahoma_7b_yellow
                                       .drawString(g, Res.formatNumber(itemShop2.buyCoin), x + width - 15, y + 1, 1);
                                 }

                                 if (itemShop2.buyGold > 0) {
                                    g.drawImage(imgLuong, ((int) (x + width - 7)), ((int) (y + 7 + 11)), 3);
                                    mFont.tahoma_7b_green
                                       .drawString(g, Res.formatNumber(itemShop2.buyGold), x + width - 15, y + 12, 1);
                                 }
                              } else {
                                 if (itemShop2.buyCoin > 0) {
                                    g.drawImage(imgXu, ((int) (x + width - 7)), ((int) (y + 7)), 3);
                                    mFont.tahoma_7b_yellow
                                       .drawString(g, Res.formatNumber(itemShop2.buyCoin), x + width - 15, y + 1, 1);
                                 }

                                 if (itemShop2.buyGold > 0) {
                                    g.drawImage(imgLuong, ((int) (x + width - 7)), ((int) (y + 7)), 3);
                                    mFont.tahoma_7b_green
                                       .drawString(g, Res.formatNumber(itemShop2.buyGold), x + width - 15, y + 1, 1);
                                 }
                              }
                           }

                           if (this.typeShop == 2 && this.currentTabIndex <= 3 && !this.equals(GameCanvas.panel2)) {
                              if (itemShop2.buyCoin > 0 && itemShop2.buyGold > 0) {
                                 if (itemShop2.buyCoin > 0) {
                                    g.drawImage(imgXu, ((int) (x + width - 7)), ((int) (y + 7)), 3);
                                    if (Char.myCharz().xu < itemShop2.buyCoin) {
                                       tahoma_7_green2 = mFont.tahoma_7b_red;
                                    } else {
                                       tahoma_7_green2 = mFont.tahoma_7b_yellow;
                                    }

                                    tahoma_7_green2.drawString(g, Res.formatNumber2(itemShop2.buyCoin), x + width - 15, y + 1, 1);
                                 }

                                 if (itemShop2.buyGold > 0) {
                                    g.drawImage(imgLuong, ((int) (x + width - 7)), ((int) (y + 7 + 11)), 3);
                                    if (Char.myCharz().luong < itemShop2.buyGold) {
                                       tahoma_7_green2 = mFont.tahoma_7b_red;
                                    } else {
                                       tahoma_7_green2 = mFont.tahoma_7b_green;
                                    }

                                    tahoma_7_green2.drawString(g, Res.formatNumber2(itemShop2.buyGold), x + width - 15, y + 12, 1);
                                 }
                              } else {
                                 if (itemShop2.buyCoin > 0) {
                                    g.drawImage(imgXu, ((int) (x + width - 7)), ((int) (y + 7)), 3);
                                    if (Char.myCharz().xu < itemShop2.buyCoin) {
                                       tahoma_7_green2 = mFont.tahoma_7b_red;
                                    } else {
                                       tahoma_7_green2 = mFont.tahoma_7b_yellow;
                                    }

                                    tahoma_7_green2.drawString(g, Res.formatNumber2(itemShop2.buyCoin), x + width - 15, y + 1, 1);
                                 }

                                 if (itemShop2.buyGold > 0) {
                                    g.drawImage(imgLuong, ((int) (x + width - 7)), ((int) (y + 7)), 3);
                                    if (Char.myCharz().luong < itemShop2.buyGold) {
                                       tahoma_7_green2 = mFont.tahoma_7b_red;
                                    } else {
                                       tahoma_7_green2 = mFont.tahoma_7b_green;
                                    }

                                    tahoma_7_green2.drawString(g, Res.formatNumber2(itemShop2.buyGold), x + width - 15, y + 1, 1);
                                 }

                                 try {
                                    mFont tahoma_7b_green = mFont.tahoma_7b_green;
                                    if (!Char.myCharz().cName.equals(itemShop2.nameNguoiKyGui)) {
                                       tahoma_7b_green = mFont.tahoma_7b_green;
                                    }

                                    tahoma_7b_green.drawString(
                                       g, itemShop2.nameNguoiKyGui, x + width, y + 1 + mFont.tahoma_7b_red.getHeight(), 1
                                    );
                                 } catch (Exception ignoredException) {
                                 }
                              }
                           }
                        }

                        SmallImage.drawSmallImage(g, itemShop2.template.iconID, x2 + 12, y2 + height2 / 2, 0, 3);
                        if (itemShop2.quantity > 1) {
                           mFont.tahoma_7_yellow
                              .drawString(
                                 g, "" + itemShop2.quantity, x2 + 24, y2 + height2 - mFont.tahoma_7_yellow.getHeight(), 1
                              );
                        }

                        if (itemShop2.newItem && GameCanvas.gameTick % 10 > 5) {
                           g.drawImage(imgNew, ((int) (x2 + 12)), ((int) (y + 19)), 3);
                        }
                     }

                     if (this.typeShop == 2 && (this.equals(GameCanvas.panel2) || this.currentTabIndex == 4) && itemShop2.buyType != 0
                        )
                      {
                        if (itemShop2.buyType == 1) {
                           mFont.tahoma_7_green.drawString(g, mResources.dangban, x + width - 5, y + 1, 1);
                           if (itemShop2.buyCoin != -1) {
                              g.drawImage(imgXu, ((int) (x + width - 7)), ((int) (y + 19)), 3);
                              mFont.tahoma_7b_yellow
                                 .drawString(g, Res.formatNumber2(itemShop2.buyCoin), x + width - 15, y + 13, 1);
                           } else if (itemShop2.buyGold != -1) {
                              g.drawImage(imgLuongKhoa, ((int) (x + width - 7)), ((int) (y + 17)), 3);
                              mFont.tahoma_7b_red
                                 .drawString(g, Res.formatNumber2(itemShop2.buyGold), x + width - 15, y + 11, 1);
                           }
                        } else if (itemShop2.buyType == 2) {
                           mFont.tahoma_7b_blue.drawString(g, mResources.daban, x + width - 5, y + 1, 1);
                           if (itemShop2.buyCoin != -1) {
                              g.drawImage(imgXu, ((int) (x + width - 7)), ((int) (y + 17)), 3);
                              mFont.tahoma_7b_yellow
                                 .drawString(g, Res.formatNumber2(itemShop2.buyCoin), x + width - 15, y + 11, 1);
                           } else if (itemShop2.buyGold != -1) {
                              g.drawImage(imgLuongKhoa, ((int) (x + width - 7)), ((int) (y + 17)), 3);
                              mFont.tahoma_7b_red
                                 .drawString(g, Res.formatNumber2(itemShop2.buyGold), x + width - 15, y + 11, 1);
                           }
                        }
                     }

                     this.shopPaintStage = 7;
                  }
               }

               this.shopPaintStage = 8;
               this.paintScrollArrow(g);
               this.shopPaintStage = 9;
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         System.out.println("tt >>:" + this.shopPaintStage);
      }
   }

   private void paintPetStatus(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      for (int strStatuIndex = 0; strStatuIndex < this.strStatus.length; strStatuIndex++) {
         int x = this.xScroll;
         int y = this.yScroll + strStatuIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 1;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            graphicsValue.setColor(strStatuIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRect(x, y, width, height);
            mFont.tahoma_7b_dark
               .drawString(graphicsValue, this.strStatus[strStatuIndex], this.xScroll + this.wScroll / 2, y + 6, 2);
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintPetInventory(mGraphics graphicsValue) {
      graphicsValue.setColor(16711680);
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);
      Item[] itemBody = Char.myPetz().arrItemBody;
      Skill[] petSkill = Char.myPetz().arrPetSkill;

      for (int index = 0; index < itemBody.length + petSkill.length; index++) {
         boolean condition = index < itemBody.length;
         int itemBodyIndex = index;
         int petSkillIndex = index - itemBody.length;
         int x = this.xScroll + 36;
         int y = this.yScroll + index * this.ITEM_HEIGHT;
         int width = this.wScroll - 36;
         int height = this.ITEM_HEIGHT - 1;
         int x2 = this.xScroll;
         int y2 = this.yScroll + index * this.ITEM_HEIGHT;
         int height2 = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            Item item = condition ? itemBody[itemBodyIndex] : null;
            graphicsValue.setColor(index == this.selected ? 16383818 : (condition ? 15196114 : 15723751));
            graphicsValue.fillRectDelegated(x, y, width, height);
            graphicsValue.setColor(index == this.selected ? 9541120 : (condition ? 9993045 : 11837316));
            if (item != null) {
               for (int itemOptionIndex = 0; itemOptionIndex < item.itemOption.length; itemOptionIndex++) {
                  byte id;
                  if (item.itemOption[itemOptionIndex].optionTemplate.id == 72
                     && item.itemOption[itemOptionIndex].param > 0
                     && GetColor_ItemBg(id = GetColor_Item_Upgrade(item.itemOption[itemOptionIndex].param)) != -1) {
                     graphicsValue.setColor(GetColor_ItemBg(id));
                  }
               }
            }

            graphicsValue.fillRectDelegated(x2, y2, 34, height2);
            if (item != null && item.isSelect && GameCanvas.panel.type == 12) {
               graphicsValue.setColor(index == this.selected ? 7040779 : 6047789);
               graphicsValue.fillRectDelegated(x2, y2, 34, height2);
            }

            if (item == null) {
               if (!condition) {
                  Skill petSkill2 = petSkill[petSkillIndex];
                  graphicsValue.drawImage(GameScr.imgSkill, ((int) (x2 + 17)), ((int) (y2 + height2 / 2)), 3);
                  if (petSkill2.template != null) {
                     mFont.tahoma_7_blue.drawString(graphicsValue, petSkill2.template.name, x + 5, y + 1, 0);
                     mFont.tahoma_7_green2
                        .drawString(graphicsValue, mResources.level + ": " + petSkill2.point, x + 5, y + 11, 0);
                     SmallImage.drawSmallImage(graphicsValue, petSkill2.template.iconId, x2 + 17, y2 + height2 / 2, 0, 3);
                  } else {
                     mFont.tahoma_7_green2.drawString(graphicsValue, petSkill2.moreInfo, x + 5, y + 5, 0);
                     SmallImage.drawSmallImage(graphicsValue, GameScr.efs[98].arrEfInfo[0].idImg, x2 + 17, y2 + height2 / 2, 0, 3);
                  }
               }
            } else {
               String text = "";
               mFont tahoma_7_green2 = mFont.tahoma_7_green2;
               if (item.itemOption != null) {
                  for (int itemOptionIndex2 = 0; itemOptionIndex2 < item.itemOption.length; itemOptionIndex2++) {
                     if (item.itemOption[itemOptionIndex2].optionTemplate.id == 72) {
                        text = " [+" + item.itemOption[itemOptionIndex2].param + "]";
                     }

                     if (item.itemOption[itemOptionIndex2].optionTemplate.id == 41) {
                        if (item.itemOption[itemOptionIndex2].param == 1) {
                           tahoma_7_green2 = GetFont(0);
                        } else if (item.itemOption[itemOptionIndex2].param == 2) {
                           tahoma_7_green2 = GetFont(2);
                        } else if (item.itemOption[itemOptionIndex2].param == 3) {
                           tahoma_7_green2 = GetFont(8);
                        } else if (item.itemOption[itemOptionIndex2].param == 4) {
                           tahoma_7_green2 = GetFont(7);
                        }
                     }
                  }
               }

               tahoma_7_green2.drawString(graphicsValue, item.template.name + text, x + 5, y + 1, 0);
               String text2 = "";
               if (item.itemOption != null) {
                  if (item.itemOption.length > 0
                     && item.itemOption[0] != null
                     && item.itemOption[0].optionTemplate.id != 102
                     && item.itemOption[0].optionTemplate.id != 107) {
                     text2 = text2 + item.itemOption[0].getOptionString();
                  }

                  mFont tahoma_7_blue = mFont.tahoma_7_blue;
                  if (item.compare < 0 && item.template.type != 5) {
                     tahoma_7_blue = mFont.tahoma_7_red;
                  }

                  if (item.itemOption.length > 1) {
                     for (int itemOptionIndex3 = 1; itemOptionIndex3 < 2; itemOptionIndex3++) {
                        if (item.itemOption[itemOptionIndex3] != null
                           && item.itemOption[itemOptionIndex3].optionTemplate.id != 102
                           && item.itemOption[itemOptionIndex3].optionTemplate.id != 107) {
                           text2 = text2 + "," + item.itemOption[itemOptionIndex3].getOptionString();
                        }
                     }
                  }

                  tahoma_7_blue.drawString(graphicsValue, text2, x + 5, y + 11, 0);
               }

               SmallImage.drawSmallImage(graphicsValue, item.template.iconID, x2 + 17, y2 + height2 / 2, 0, 3);
               if (item.itemOption != null) {
                  for (int itemOptionIndex4 = 0; itemOptionIndex4 < item.itemOption.length; itemOptionIndex4++) {
                     this.paintOptItem(graphicsValue, item.itemOption[itemOptionIndex4].optionTemplate.id, x2, y2, height2);
                  }

                  for (int itemOptionIndex5 = 0; itemOptionIndex5 < item.itemOption.length; itemOptionIndex5++) {
                     paintUpgradeEffect(
                        graphicsValue,
                        item.itemOption[itemOptionIndex5].optionTemplate.id,
                        item.itemOption[itemOptionIndex5].param,
                        x2,
                        y2,
                        34,
                        height2
                     );
                  }
               }

               if (item.quantity > 1) {
                  mFont.tahoma_7_yellow
                     .drawString(graphicsValue, "" + item.quantity, x2 + 34, y2 + height2 - mFont.tahoma_7_yellow.getHeight(), 1);
               }
            }
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintScrollArrow(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      if (this.cmy > 24 && this.currentListLength > 0
         || this.equals(GameCanvas.panel) && this.typeShop == 2 && this.maxPageShop[this.currentTabIndex] > 1) {
         g.drawRegion(
            Mob.imgHP, 0, 0, 9, 6, 1, this.xScroll + this.wScroll - 12, this.yScroll + 3, 0
         );
      }

      if (this.cmy < this.cmyLim && this.currentListLength > 0
         || this.equals(GameCanvas.panel) && this.typeShop == 2 && this.maxPageShop[this.currentTabIndex] > 1) {
         g.drawRegion(
            Mob.imgHP,
            0,
            0,
            9,
            6,
            0,
            this.xScroll + this.wScroll - 12,
            this.yScroll + this.hScroll - 8,
            0
         );
      }
   }

   private void paintTools(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      for (int strToolIndex = 0; strToolIndex < strTool.length; strToolIndex++) {
         int x = this.xScroll;
         int y = this.yScroll + strToolIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 1;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            graphicsValue.setColor(strToolIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRect(x, y, width, height);
            mFont.tahoma_7b_dark.drawString(graphicsValue, strTool[strToolIndex], this.xScroll + this.wScroll / 2, y + 6, 2);
            if (strTool[strToolIndex].equals(mResources.gameInfo)) {
               for (int gameInfoIndex = 0; gameInfoIndex < vGameInfo.size(); gameInfoIndex++) {
                  if (!((GameInfo)vGameInfo.elementAt(gameInfoIndex)).hasRead) {
                     if (GameCanvas.gameTick % 20 > 10) {
                        graphicsValue.drawImage(imgNew, ((int) (x + 10)), ((int) (y + 10)), 3);
                     }
                     break;
                  }
               }
            }
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintGameSubInfo(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      for (int contenInfoIndex = 0; contenInfoIndex < contenInfo.length; contenInfoIndex++) {
         int value = this.yScroll + contenInfoIndex * 15;
         if (value - this.cmy <= this.yScroll + this.hScroll
            && value - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            mFont.tahoma_7b_dark.drawString(graphicsValue, contenInfo[contenInfoIndex], this.xScroll + 5, value + 6, 0);
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintGameInfo(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      for (int gameInfoIndex = 0; gameInfoIndex < vGameInfo.size(); gameInfoIndex++) {
         GameInfo gameInfo = (GameInfo)vGameInfo.elementAt(gameInfoIndex);
         int x = this.xScroll;
         int y = this.yScroll + gameInfoIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 1;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            graphicsValue.setColor(gameInfoIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRect(x, y, width, height);
            mFont.tahoma_7b_dark.drawString(graphicsValue, gameInfo.main, this.xScroll + this.wScroll / 2, y + 6, 2);
            if (!gameInfo.hasRead && GameCanvas.gameTick % 20 > 10) {
               graphicsValue.drawImage(imgNew, ((int) (x + 10)), ((int) (y + 10)), 3);
            }
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintSkill(mGraphics g) {
      g.setColor(16711680);
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      int skillTemplateCount = Char.myCharz().nClass.skillTemplates.length;

      for (int index = 0; index < skillTemplateCount + 6; index++) {
         int width = this.xScroll + 30;
         int y = this.yScroll + index * this.ITEM_HEIGHT;
         int width2 = this.wScroll - 30;
         int height = this.ITEM_HEIGHT - 1;
         int x = this.xScroll;
         int y2 = this.yScroll + index * this.ITEM_HEIGHT;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            g.setColor(index == this.selected ? 16383818 : 15196114);
            if (index == 5) {
               g.setColor(index == this.selected ? 16776068 : 16765060);
            }

            g.fillRect(width, y, width2, height);
            g.drawImage(GameScr.imgSkill, ((int) (x)), ((int) (y2)), 0);
            if (index == 0) {
               SmallImage.drawSmallImage(g, 567, x + 4, y2 + 4, 0, 0);
               String text = mResources.HP
                  + " "
                  + mResources.root
                  + ": "
                  + Firework.getMoneys(Char.myCharz().cHPGoc);
               mFont.tahoma_7b_blue.drawString(g, text, width + 5, y + 3, 0);
               mFont.tahoma_7_green2
                  .drawString(
                     g,
                     Firework.getMoneys(Char.myCharz().cHPGoc + 1000)
                        + " "
                        + mResources.potential
                        + ": "
                        + mResources.increase
                        + " "
                        + Char.myCharz().hpFrom1000TiemNang,
                     width + 5,
                     y + 15,
                     0
                  );
            }

            if (index == 1) {
               SmallImage.drawSmallImage(g, 569, x + 4, y2 + 4, 0, 0);
               String text2 = mResources.KI
                  + " "
                  + mResources.root
                  + ": "
                  + Firework.getMoneys(Char.myCharz().cMPGoc);
               mFont.tahoma_7b_blue.drawString(g, text2, width + 5, y + 3, 0);
               mFont.tahoma_7_green2
                  .drawString(
                     g,
                     Firework.getMoneys(Char.myCharz().cMPGoc + 1000)
                        + " "
                        + mResources.potential
                        + ": "
                        + mResources.increase
                        + " "
                        + Char.myCharz().mpFrom1000TiemNang,
                     width + 5,
                     y + 15,
                     0
                  );
            }

            if (index == 2) {
               SmallImage.drawSmallImage(g, 568, x + 4, y2 + 4, 0, 0);
               String text3 = mResources.hit_point
                  + " "
                  + mResources.root
                  + ": "
                  + Firework.getMoneys(Char.myCharz().cDamGoc);
               mFont.tahoma_7b_blue.drawString(g, text3, width + 5, y + 3, 0);
               mFont.tahoma_7_green2
                  .drawString(
                     g,
                     Firework.getMoneys(Char.myCharz().cDamGoc * 100)
                        + " "
                        + mResources.potential
                        + ": "
                        + mResources.increase
                        + " "
                        + Char.myCharz().damFrom1000TiemNang,
                     width + 5,
                     y + 15,
                     0
                  );
            }

            if (index == 3) {
               SmallImage.drawSmallImage(g, 721, x + 4, y2 + 4, 0, 0);
               String text4 = mResources.armor
                  + " "
                  + mResources.root
                  + ": "
                  + Firework.getMoneys(Char.myCharz().cDefGoc);
               mFont.tahoma_7b_blue.drawString(g, text4, width + 5, y + 3, 0);
               mFont.tahoma_7_green2
                  .drawString(
                     g,
                     Firework.getMoneys(500000 + Char.myCharz().cDefGoc * 100000)
                        + " "
                        + mResources.potential
                        + ": "
                        + mResources.increase
                        + " "
                        + Char.myCharz().defFrom1000TiemNang,
                     width + 5,
                     y + 15,
                     0
                  );
            }

            if (index == 4) {
               SmallImage.drawSmallImage(g, 719, x + 4, y2 + 4, 0, 0);
               String text5 = mResources.critical + " " + mResources.root + ": " + Char.myCharz().cCriticalGoc + "%";
               int t_tiemnangIndex;
               if ((t_tiemnangIndex = Char.myCharz().cCriticalGoc) > t_tiemnang.length - 1) {
                  t_tiemnangIndex = t_tiemnang.length - 1;
               }

               long t_tiemnangValue = t_tiemnang[t_tiemnangIndex];
               mFont.tahoma_7b_blue.drawString(g, text5, width + 5, y + 3, 0);
               long number = t_tiemnangValue;
               mFont.tahoma_7_green2
                  .drawString(
                     g,
                     Res.formatNumber2(number)
                        + " "
                        + mResources.potential
                        + ": "
                        + mResources.increase
                        + " "
                        + Char.myCharz().criticalFrom1000Tiemnang,
                     width + 5,
                     y + 15,
                     0
                  );
            }

            if (index == 5) {
               if (specialInfo != null) {
                  SmallImage.drawSmallImage(g, spearcialImage, x + 4, y2 + 4, 0, 0);
                  String[] texts = mFont.tahoma_7.splitFontArray(specialInfo, 120);

                  for (int textIndex = 0; textIndex < texts.length; textIndex++) {
                     mFont.tahoma_7_green2.drawString(g, texts[textIndex], width + 5, y + 3 + textIndex * 12, 0);
                  }
               } else {
                  mFont.tahoma_7_green2.drawString(g, "", width + 5, y + 9, 0);
               }
            }

            if (index >= 6) {
               height = index - 6;
               SkillTemplate skillTemplate = Char.myCharz().nClass.skillTemplates[height];
               SmallImage.drawSmallImage(g, skillTemplate.iconId, x + 4, y2 + 4, 0, 0);
               Skill skill;
               if ((skill = Char.myCharz().getSkill(skillTemplate)) != null) {
                  mFont.tahoma_7b_blue.drawString(g, skillTemplate.name, width + 5, y + 3, 0);
                  mFont.tahoma_7_blue
                     .drawString(g, mResources.level + ": " + skill.point, width + width2 - 5, y + 3, 1);
                  if (skill.point == skillTemplate.maxPoint) {
                     mFont.tahoma_7_green2.drawString(g, mResources.max_level_reach, width + 5, y + 15, 0);
                  } else if (skill.template.isSkillSpec()) {
                     String text6 = mResources.proficiency + ": ";
                     int x2 = mFont.tahoma_7_green2.getWidth(text6) + width + 5;
                     int y3 = y + 15;
                     mFont.tahoma_7_green2.drawString(g, text6, width + 5, y3, 0);
                     mFont tahoma_7_green2 = mFont.tahoma_7_green2;
                     StringBuffer stringBuffer = new StringBuffer("(");
                     Skill skill2 = skill;
                     String text7;
                     if (skill.curExp / 10 >= 100) {
                        text7 = "MAX";
                     } else if (skill2.curExp % 10 == 0) {
                        text7 = skill2.curExp / 10 + "%";
                     } else {
                        height = skill2.curExp % 10;
                        text7 = skill2.curExp / 10 + "." + height % 10 + "%";
                     }

                     tahoma_7_green2.drawString(g, stringBuffer.append(text7).append(")").toString(), width + width2 - 5, y3, 1);
                     y3 += 4;
                     g.setColor(7169134);
                     g.fillRect(x2, y3, 50, 5);
                     width = skill.curExp * 50 / 1000;
                     g.setColor(11992374);
                     g.fillRect(x2, y3, width, 5);
                  } else {
                     Skill skill3 = skillTemplate.skills[skill.point];
                     mFont.tahoma_7_green2
                        .drawString(
                           g,
                           mResources.level
                              + " "
                              + (skill.point + 1)
                              + " "
                              + mResources.need
                              + " "
                              + Res.formatNumber2(skill3.powRequire)
                              + " "
                              + mResources.potential,
                           width + 5,
                           y + 15,
                           0
                        );
                  }
               } else {
                  Skill skill4 = skillTemplate.skills[0];
                  String text8 = mResources.need_upper
                     + " "
                     + Res.formatNumber2(skill4.powRequire)
                     + " "
                     + mResources.potential_to_learn;
                  if (skill4.template.id == 24
                     || skill4.template.id == 25
                     || skill4.template.id == 26) {
                     text8 = mResources.need_upper + " " + Res.formatNumber2(skill4.powRequire) + " " + mResources.potential_to_learn_tuyetKi;
                  }

                  mFont.tahoma_7b_green.drawString(g, skillTemplate.name, width + 5, y + 3, 0);
                  mFont.tahoma_7_green2.drawString(g, text8, width + 5, y + 15, 0);
               }
            }
         }
      }

      this.paintScrollArrow(g);
   }

   private void paintMapTrans(mGraphics g) {
      g.setColor(16711680);
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);

      for (int mapNameIndex = 0; mapNameIndex < this.mapNames.length; mapNameIndex++) {
         int y = this.yScroll + mapNameIndex * this.ITEM_HEIGHT;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            g.setColor(mapNameIndex == this.selected ? 16383818 : 15196114);
            g.fillRect(this.xScroll, y, this.wScroll, height);
            mFont.tahoma_7b_blue.drawString(g, this.mapNames[mapNameIndex], 5, y + 1, 0);
            mFont.tahoma_7_grey.drawString(g, this.planetNames[mapNameIndex], 5, y + 11, 0);
         }
      }

      this.paintScrollArrow(g);
   }

   private void paintPaymentPackages(mGraphics graphicsValue) {
      graphicsValue.setColor(16711680);
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);
      String[] packageLabels = PaymentCatalog.packageLabels;
      String[] priceLabels = PaymentCatalog.priceLabels;

      for (int packageLabelIndex = 0; packageLabelIndex < priceLabels.length; packageLabelIndex++) {
         int x = this.xScroll;
         int y = this.yScroll + packageLabelIndex * this.ITEM_HEIGHT;
         int width = this.wScroll;
         int height = this.ITEM_HEIGHT - 1;
         int xScrollValue = this.xScroll;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            graphicsValue.setColor(packageLabelIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRectDelegated(x, y, width, height);
            mFont.tahoma_7_grey.drawString(graphicsValue, packageLabels[packageLabelIndex], xScrollValue + 5, y + 6, 0);
            mFont.tahoma_7_grey.drawString(graphicsValue, priceLabels[packageLabelIndex], x + width - 2, y + 6, 1);
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintZone(mGraphics g) {
      g.setColor(16711680);
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      int[] zones = GameScr.gI().zones;
      int[] pts = GameScr.gI().pts;

      for (int maxPlayerIndex = 0; maxPlayerIndex < pts.length; maxPlayerIndex++) {
         int x = this.xScroll + 36;
         int y = this.yScroll + maxPlayerIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 36;
         int height = this.ITEM_HEIGHT - 1;
         int x2 = this.xScroll;
         int y2 = this.yScroll + maxPlayerIndex * this.ITEM_HEIGHT;
         int height2 = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            g.setColor(maxPlayerIndex == this.selected ? 16383818 : 15196114);
            g.fillRectDelegated(x, y, width, height);
            g.setColor(this.zoneColor[pts[maxPlayerIndex]]);
            g.fillRectDelegated(x2, y2, 34, height2);
            if (zones[maxPlayerIndex] != -1) {
               if (pts[maxPlayerIndex] != 1) {
                  mFont.tahoma_7_yellow.drawString(g, String.valueOf(zones[maxPlayerIndex]), x2 + 17, y + 6, 2);
               } else {
                  mFont.tahoma_7_grey.drawString(g, String.valueOf(zones[maxPlayerIndex]), x2 + 17, y + 6, 2);
               }

               mFont.tahoma_7_green2
                  .drawString(
                     g, GameScr.gI().numPlayer[maxPlayerIndex] + "/" + GameScr.gI().maxPlayer[maxPlayerIndex], x + 5, y + 6, 0
                  );
            }

            if (GameScr.gI().rankName1[maxPlayerIndex] != null) {
               mFont.tahoma_7_grey
                  .drawString(
                     g,
                     GameScr.gI().rankName1[maxPlayerIndex] + "(Top " + GameScr.gI().rank1[maxPlayerIndex] + ")",
                     x + width - 2,
                     y + 1,
                     1
                  );
               mFont.tahoma_7_grey
                  .drawString(
                     g,
                     GameScr.gI().rankName2[maxPlayerIndex] + "(Top " + GameScr.gI().rank2[maxPlayerIndex] + ")",
                     x + width - 2,
                     y + 11,
                     1
                  );
            }
         }
      }

      this.paintScrollArrow(g);
   }

   private void paintSpeacialSkill(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      g.setColor(0);
      if (this.currentListLength != 0) {
         int currentListLengthValue;
         if ((currentListLengthValue = (this.cmy + this.hScroll) / 24 + 1) < this.hScroll / 24 + 1) {
            currentListLengthValue = this.hScroll / 24 + 1;
         }

         if (currentListLengthValue > this.currentListLength) {
            currentListLengthValue = this.currentListLength;
         }

         int value;
         if ((value = this.cmy / 24) >= currentListLengthValue) {
            value = currentListLengthValue - 1;
         }

         if (value < 0) {
            value = 0;
         }

         for (int imgSpeacialSkillIndex = value; imgSpeacialSkillIndex < currentListLengthValue; imgSpeacialSkillIndex++) {
            int x = this.xScroll;
            int y = this.yScroll + imgSpeacialSkillIndex * this.ITEM_HEIGHT;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll + 24;
            int y2 = this.yScroll + imgSpeacialSkillIndex * this.ITEM_HEIGHT;
            int width = this.wScroll - 24;
            int height2 = this.ITEM_HEIGHT - 1;
            g.setColor(imgSpeacialSkillIndex == this.selected ? 16383818 : 15196114);
            g.fillRectDelegated(x2, y2, width, height2);
            g.setColor(imgSpeacialSkillIndex == this.selected ? 9541120 : 9993045);
            g.fillRectDelegated(x, y, 24, height);
            SmallImage.drawSmallImage(g, Char.myCharz().imgSpeacialSkill[this.currentTabIndex][imgSpeacialSkillIndex], x + 12, y + height / 2, 0, 3);
            String[] texts = mFont.tahoma_7_grey.splitFontArray(Char.myCharz().infoSpeacialSkill[this.currentTabIndex][imgSpeacialSkillIndex], 140);

            for (int textIndex = 0; textIndex < texts.length; textIndex++) {
               mFont.tahoma_7_grey.drawString(g, texts[textIndex], x2 + 5, y2 + 1 + textIndex * 11, 0);
            }
         }

         this.paintScrollArrow(g);
      }
   }

   private void paintBox(mGraphics graphicsValue) {
      graphicsValue.setColor(16711680);
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      try {
         Item[] itemBox = Char.myCharz().arrItemBox;
         this.currentListLength = this.checkCurrentListLength(itemBox.length);
         int value = itemBox.length / 20 + (itemBox.length % 20 > 0 ? 1 : 0);
         this.TAB_W_NEW = this.wScroll / value;

         for (int select = 0; select < this.currentListLength; select++) {
            int x = this.xScroll + 36;
            int y = this.yScroll + select * this.ITEM_HEIGHT;
            int width = this.wScroll - 36;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll;
            int y2 = this.yScroll + select * this.ITEM_HEIGHT;
            int height2 = this.ITEM_HEIGHT - 1;
            if (y - this.cmy <= this.yScroll + this.hScroll
               && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
               if (select == 0) {
                  for (int index = 0; index < value; index++) {
                     height = index == this.newSelected && this.selected == 0 ? (GameCanvas.gameTick % 10 < 7 ? -1 : 0) : 0;
                     graphicsValue.setColor(index == this.newSelected ? 16383818 : 15723751);
                     graphicsValue.fillRect(this.xScroll + index * this.TAB_W_NEW, y + 9 + height, this.TAB_W_NEW - 1, 14);
                     mFont.tahoma_7_grey
                        .drawString(
                           graphicsValue,
                           "" + index,
                           this.xScroll + index * this.TAB_W_NEW + this.TAB_W_NEW / 2,
                           this.yScroll + 11 + height,
                           2
                        );
                  }
               } else {
                  graphicsValue.setColor(select == this.selected ? 16383818 : 15196114);
                  graphicsValue.fillRect(x, y, width, height);
                  graphicsValue.setColor(select == this.selected ? 9541120 : 9993045);
                  width = GetInventorySelect_body(select, this.newSelected);
                  Item itemBox2;
                  if ((itemBox2 = itemBox[width]) != null) {
                     for (int itemOptionIndex = 0; itemOptionIndex < itemBox2.itemOption.length; itemOptionIndex++) {
                        byte id;
                        if (itemBox2.itemOption[itemOptionIndex].optionTemplate.id == 72
                           && itemBox2.itemOption[itemOptionIndex].param > 0
                           && GetColor_ItemBg(id = GetColor_Item_Upgrade(itemBox2.itemOption[itemOptionIndex].param)) != -1) {
                           graphicsValue.setColor(GetColor_ItemBg(id));
                        }
                     }
                  }

                  graphicsValue.fillRectDelegated(x2, y2, 34, height2);
                  if (itemBox2 != null) {
                     String text = "";
                     mFont tahoma_7_green2 = mFont.tahoma_7_green2;
                     if (itemBox2.itemOption != null) {
                        for (int itemOptionIndex2 = 0; itemOptionIndex2 < itemBox2.itemOption.length; itemOptionIndex2++) {
                           if (itemBox2.itemOption[itemOptionIndex2].optionTemplate.id == 72) {
                              text = " [+" + itemBox2.itemOption[itemOptionIndex2].getOptionString() + "]";
                           }

                           if (itemBox2.itemOption[itemOptionIndex2].optionTemplate.id == 41) {
                              if (itemBox2.itemOption[itemOptionIndex2].param == 1) {
                                 tahoma_7_green2 = GetFont(0);
                              } else if (itemBox2.itemOption[itemOptionIndex2].param == 2) {
                                 tahoma_7_green2 = GetFont(2);
                              } else if (itemBox2.itemOption[itemOptionIndex2].param == 3) {
                                 tahoma_7_green2 = GetFont(8);
                              } else if (itemBox2.itemOption[itemOptionIndex2].param == 4) {
                                 tahoma_7_green2 = GetFont(7);
                              }
                           }
                        }
                     }

                     tahoma_7_green2.drawString(graphicsValue, itemBox2.template.name + text, x + 5, y + 1, 0);
                     String text2 = "";
                     if (itemBox2.itemOption != null) {
                        if (itemBox2.itemOption.length > 0 && itemBox2.itemOption[0] != null) {
                           text2 = text2 + itemBox2.itemOption[0].getOptionString();
                        }

                        mFont tahoma_7_blue = mFont.tahoma_7_blue;
                        if (itemBox2.compare < 0 && itemBox2.template.type != 5) {
                           tahoma_7_blue = mFont.tahoma_7_red;
                        }

                        if (itemBox2.itemOption.length > 1) {
                           for (int itemOptionIndex3 = 1; itemOptionIndex3 < itemBox2.itemOption.length; itemOptionIndex3++) {
                              if (itemBox2.itemOption[itemOptionIndex3] != null
                                 && itemBox2.itemOption[itemOptionIndex3].optionTemplate.id != 102
                                 && itemBox2.itemOption[itemOptionIndex3].optionTemplate.id != 107) {
                                 text2 = text2 + "," + itemBox2.itemOption[itemOptionIndex3].getOptionString();
                              }
                           }
                        }

                        tahoma_7_blue.drawString(graphicsValue, text2, x + 5, y + 11, 0);
                     }

                     SmallImage.drawSmallImage(graphicsValue, itemBox2.template.iconID, x2 + 17, y2 + height2 / 2, 0, 3);
                     if (itemBox2.itemOption != null) {
                        for (int itemOptionIndex4 = 0; itemOptionIndex4 < itemBox2.itemOption.length; itemOptionIndex4++) {
                           this.paintOptItem(graphicsValue, itemBox2.itemOption[itemOptionIndex4].optionTemplate.id, x2, y2, height2);
                        }

                        for (int itemOptionIndex5 = 0; itemOptionIndex5 < itemBox2.itemOption.length; itemOptionIndex5++) {
                           paintUpgradeEffect(
                              graphicsValue,
                              itemBox2.itemOption[itemOptionIndex5].optionTemplate.id,
                              itemBox2.itemOption[itemOptionIndex5].param,
                              x2,
                              y2,
                              34,
                              height2
                           );
                        }
                     }

                     if (itemBox2.quantity > 1) {
                        mFont.tahoma_7_yellow
                           .drawString(graphicsValue, "" + itemBox2.quantity, x2 + 34, y2 + height2 - mFont.tahoma_7_yellow.getHeight(), 1);
                     }
                  }
               }
            }
         }
      } catch (Exception ignoredException) {
      }

      this.paintScrollArrow(graphicsValue);
   }

   private Member getCurrMember() {
      if (this.selected < 2) {
         return null;
      } else if (this.selected > (this.member != null ? this.member.size() : this.myMember.size()) + 1) {
         return null;
      } else {
         return this.member != null
            ? (Member)this.member.elementAt(this.selected - 2)
            : (Member)this.myMember.elementAt(this.selected - 2);
      }
   }

   private ClanMessage getCurrMessage() {
      if (this.selected < 2) {
         return null;
      } else {
         return this.selected > ClanMessage.vMessage.size() + 1
            ? null
            : (ClanMessage)ClanMessage.vMessage.elementAt(this.selected - 2);
      }
   }

   private Clan getCurrClan() {
      if (this.selected < 2) {
         return null;
      } else {
         return this.selected > this.clans.length + 1 ? null : this.clans[this.selected - 2];
      }
   }

   private void paintLogChat(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      g.setColor(0);
      if (this.logChat.size() == 0) {
         mFont.tahoma_7_green2
            .drawString(
               g,
               mResources.no_msg,
               this.xScroll + this.wScroll / 2,
               this.yScroll + this.hScroll / 2 - mFont.tahoma_7.getHeight() / 2 + 24,
               2
            );
      }

      for (int index = 0; index < this.currentListLength; index++) {
         int x = this.xScroll;
         int y = this.yScroll + index * this.ITEM_HEIGHT;
         int height = this.ITEM_HEIGHT - 1;
         int x2 = this.xScroll + 24;
         int y2 = this.yScroll + index * this.ITEM_HEIGHT;
         int width = this.wScroll - 24;
         int height2 = this.ITEM_HEIGHT - 1;
         if (index == 0) {
            g.setColor(15196114);
            g.fillRectDelegated(x, y2, this.wScroll, height2);
            g.drawImage(
               index == this.selected ? GameScr.imgLbtnFocus2 : GameScr.imgLbtn2,
               ((int) (this.xScroll + this.wScroll - 5)),
               ((int) (y2 + 2)),
               StaticObj.TOP_RIGHT
            );
            (index == this.selected ? mFont.tahoma_7b_green2 : mFont.tahoma_7b_dark)
               .drawString(
                  g,
                  this.isViewChatServer ? mResources.off : mResources.on,
                  this.xScroll + this.wScroll - 22,
                  y2 + 7,
                  2
               );
            mFont.tahoma_7_grey
               .drawString(
                  g,
                  this.isViewChatServer ? mResources.offPlease : mResources.onPlease,
                  this.xScroll + 5,
                  y2 + height2 / 2 - 4,
                  0
               );
         } else {
            g.setColor(index == this.selected ? 16383818 : 15196114);
            g.fillRectDelegated(x2, y2, width, height2);
            g.setColor(index == this.selected ? 9541120 : 9993045);
            g.fillRectDelegated(x, y, 24, height);
            InfoItem logChatValue;
            if ((logChatValue = (InfoItem)this.logChat.elementAt(index - 1)).charInfo.headICON != -1) {
               SmallImage.drawSmallImage(g, logChatValue.charInfo.headICON, x, y, 0, 0);
            } else {
               Part part = GameScr.parts[logChatValue.charInfo.head];
               SmallImage.drawSmallImage(
                  g,
                  part.pi[Char.CharInfo[0][0][0]].id,
                  x + part.pi[Char.CharInfo[0][0][0]].dx,
                  y + part.pi[Char.CharInfo[0][0][0]].dy,
                  0,
                  0
               );
            }

            g.setClip(this.xScroll, this.yScroll + this.cmy, this.wScroll, this.hScroll);
            mFont tahoma_7b_green2 = mFont.tahoma_7b_green2;
            mFont.tahoma_7b_green2.drawString(g, logChatValue.charInfo.cName, x2 + 5, y2, 0);
            if (!logChatValue.isChatServer) {
               mFont.tahoma_7_blue.drawString(g, Res.split(logChatValue.s, "|", 0)[2], x2 + 5, y2 + 11, 0);
            } else {
               mFont.tahoma_7_red.drawString(g, Res.split(logChatValue.s, "|", 0)[2], x2 + 5, y2 + 11, 0);
            }
         }
      }

      this.paintScrollArrow(g);
   }

   private void paintFlagChange(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      g.setColor(0);

      for (int flagIndex = 0; flagIndex < this.currentListLength; flagIndex++) {
         int x = this.xScroll + 26;
         int y = this.yScroll + flagIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 26;
         int height = this.ITEM_HEIGHT - 1;
         int x2 = this.xScroll;
         int y2 = this.yScroll + flagIndex * this.ITEM_HEIGHT;
         int height2 = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            g.setColor(flagIndex == this.selected ? 16383818 : 15196114);
            g.fillRectDelegated(x, y, width, height);
            g.setColor(flagIndex == this.selected ? 9541120 : 9993045);
            g.fillRectDelegated(x2, y2, 24, height2);
            Item flag;
            if ((flag = (Item)this.vFlag.elementAt(flagIndex)) != null) {
               mFont.tahoma_7_green2.drawString(g, flag.template.name, x + 5, y + 1, 0);
               String text = "";
               if (flag.itemOption != null && flag.itemOption.length > 0) {
                  if (flag.itemOption[0] != null
                     && flag.itemOption[0].optionTemplate.id != 102
                     && flag.itemOption[0].optionTemplate.id != 107) {
                     text = text + flag.itemOption[0].getOptionString();
                  }

                  mFont tahoma_7_blue = mFont.tahoma_7_blue;
                  mFont.tahoma_7_blue.drawString(g, text, x + 5, y + 11, 0);
                  SmallImage.drawSmallImage(g, flag.template.iconID, x2 + 12, y2 + height2 / 2, 0, 3);
               }
            }
         }
      }

      this.paintScrollArrow(g);
   }

   private void paintEnemy(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);
      graphicsValue.setColor(0);
      if (this.currentListLength == 0) {
         mFont.tahoma_7_green2
            .drawString(
               graphicsValue,
               mResources.no_enemy,
               this.xScroll + this.wScroll / 2,
               this.yScroll + this.hScroll / 2 - mFont.tahoma_7.getHeight() / 2,
               2
            );
      } else {
         for (int enemyIndex = 0; enemyIndex < this.currentListLength; enemyIndex++) {
            int x = this.xScroll;
            int y = this.yScroll + enemyIndex * this.ITEM_HEIGHT;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll + 24;
            int y2 = this.yScroll + enemyIndex * this.ITEM_HEIGHT;
            int width = this.wScroll - 24;
            int height2 = this.ITEM_HEIGHT - 1;
            graphicsValue.setColor(enemyIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRectDelegated(x2, y2, width, height2);
            graphicsValue.setColor(enemyIndex == this.selected ? 9541120 : 9993045);
            graphicsValue.fillRectDelegated(x, y, 24, height);
            InfoItem enemy;
            if ((enemy = (InfoItem)this.vEnemy.elementAt(enemyIndex)).charInfo.headICON != -1) {
               SmallImage.drawSmallImage(graphicsValue, enemy.charInfo.headICON, x, y, 0, 0);
            } else {
               Part part = GameScr.parts[enemy.charInfo.head];
               SmallImage.drawSmallImage(
                  graphicsValue,
                  part.pi[Char.CharInfo[0][0][0]].id,
                  x + part.pi[Char.CharInfo[0][0][0]].dx,
                  y + 3 + part.pi[Char.CharInfo[0][0][0]].dy,
                  0,
                  0
               );
            }

            graphicsValue.setClip(this.xScroll, this.yScroll + this.cmy, this.wScroll, this.hScroll);
            if (enemy.isOnline) {
               mFont.tahoma_7b_green.drawString(graphicsValue, enemy.charInfo.cName, x2 + 5, y2, 0);
               mFont.tahoma_7_blue.drawString(graphicsValue, enemy.s, x2 + 5, y2 + 11, 0);
            } else {
               mFont.tahoma_7_grey.drawString(graphicsValue, enemy.charInfo.cName, x2 + 5, y2, 0);
               mFont.tahoma_7_grey.drawString(graphicsValue, enemy.s, x2 + 5, y2 + 11, 0);
            }
         }

         this.paintScrollArrow(graphicsValue);
      }
   }

   private void paintFriend(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);
      graphicsValue.setColor(0);
      if (this.currentListLength == 0) {
         mFont.tahoma_7_green2
            .drawString(
               graphicsValue,
               mResources.no_friend,
               this.xScroll + this.wScroll / 2,
               this.yScroll + this.hScroll / 2 - mFont.tahoma_7.getHeight() / 2,
               2
            );
      } else {
         for (int friendIndex = 0; friendIndex < this.currentListLength; friendIndex++) {
            int x = this.xScroll;
            int y = this.yScroll + friendIndex * this.ITEM_HEIGHT;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll + 24;
            int y2 = this.yScroll + friendIndex * this.ITEM_HEIGHT;
            int width = this.wScroll - 24;
            int height2 = this.ITEM_HEIGHT - 1;
            graphicsValue.setColor(friendIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRectDelegated(x2, y2, width, height2);
            graphicsValue.setColor(friendIndex == this.selected ? 9541120 : 9993045);
            graphicsValue.fillRectDelegated(x, y, 24, height);
            InfoItem friend;
            if ((friend = (InfoItem)this.vFriend.elementAt(friendIndex)).charInfo.headICON != -1) {
               SmallImage.drawSmallImage(graphicsValue, friend.charInfo.headICON, x, y, 0, 0);
            } else {
               Part part = GameScr.parts[friend.charInfo.head];
               SmallImage.drawSmallImage(
                  graphicsValue,
                  part.pi[Char.CharInfo[0][0][0]].id,
                  x + part.pi[Char.CharInfo[0][0][0]].dx,
                  y + 3 + part.pi[Char.CharInfo[0][0][0]].dy,
                  0,
                  0
               );
            }

            graphicsValue.setClip(this.xScroll, this.yScroll + this.cmy, this.wScroll, this.hScroll);
            if (friend.isOnline) {
               mFont.tahoma_7b_green.drawString(graphicsValue, friend.charInfo.cName, x2 + 5, y2, 0);
               mFont.tahoma_7_blue.drawString(graphicsValue, friend.s, x2 + 5, y2 + 11, 0);
            } else {
               mFont.tahoma_7_grey.drawString(graphicsValue, friend.charInfo.cName, x2 + 5, y2, 0);
               mFont.tahoma_7_grey.drawString(graphicsValue, friend.s, x2 + 5, y2 + 11, 0);
            }
         }

         this.paintScrollArrow(graphicsValue);
      }
   }

   private void paintPlayerMenu(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);

      for (int playerMenuIndex = 0; playerMenuIndex < this.vPlayerMenu.size(); playerMenuIndex++) {
         int x = this.xScroll;
         int y = this.yScroll + playerMenuIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 1;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            Command playerMenu = (Command)this.vPlayerMenu.elementAt(playerMenuIndex);
            g.setColor(playerMenuIndex == this.selected ? 16383818 : 15196114);
            g.fillRectDelegated(x, y, width, height);
            if (playerMenu.caption2.equals("")) {
               mFont.tahoma_7b_dark.drawString(g, playerMenu.caption, this.xScroll + this.wScroll / 2, y + 6, 2);
            } else {
               mFont.tahoma_7b_dark.drawString(g, playerMenu.caption, this.xScroll + this.wScroll / 2, y + 1, 2);
               mFont.tahoma_7b_dark.drawString(g, playerMenu.caption2, this.xScroll + this.wScroll / 2, y + 11, 2);
            }
         }
      }

      this.paintScrollArrow(g);
   }

   private void paintClans(mGraphics g) {
      try {
         g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
         g.translate(-this.cmx, -this.cmy);
         g.setColor(0);
         int value = this.xScroll + this.wScroll / 2 - this.clansOption.length * this.TAB_W / 2;
         if (this.currentListLength == 2) {
            mFont.tahoma_7_green2
               .drawString(
                  g,
                  this.clanReport,
                  this.xScroll + this.wScroll / 2,
                  this.yScroll + 24 + this.hScroll / 2 - mFont.tahoma_7.getHeight() / 2,
                  2
               );
            if (this.isMessage && this.myMember.size() == 1) {
               for (int clanEmptyIndex = 0; clanEmptyIndex < mResources.clanEmpty.length; clanEmptyIndex++) {
                  mFont.tahoma_7b_dark
                     .drawString(
                        g,
                        mResources.clanEmpty[clanEmptyIndex],
                        this.xScroll + this.wScroll / 2,
                        this.yScroll + 24 + this.hScroll / 2 - mResources.clanEmpty.length * 12 / 2 + clanEmptyIndex * 12,
                        2
                     );
               }
            }
         }

         if (this.isMessage) {
            this.currentListLength = ClanMessage.vMessage.size() + 2;
         }

         for (int index = 0; index < this.currentListLength; index++) {
            int x = this.xScroll;
            int y = this.yScroll + index * this.ITEM_HEIGHT;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll + 24;
            int y2 = this.yScroll + index * this.ITEM_HEIGHT;
            int width = this.wScroll - 24;
            int height2 = this.ITEM_HEIGHT - 1;
            if (y2 - this.cmy <= this.yScroll + this.hScroll
               && y2 - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
               if (index == 0) {
                  for (int clansOptionIndex = 0; clansOptionIndex < this.clansOption.length; clansOptionIndex++) {
                     g.setColor(clansOptionIndex == this.cSelected && index == this.selected ? 16383818 : 15723751);
                     g.fillRectDelegated(value + clansOptionIndex * this.TAB_W, y2, this.TAB_W - 1, 23);

                     for (int clansOptionIndex2 = 0; clansOptionIndex2 < this.clansOption[clansOptionIndex].length; clansOptionIndex2++) {
                        mFont.tahoma_7_grey
                           .drawString(
                              g,
                              this.clansOption[clansOptionIndex][clansOptionIndex2],
                              value + clansOptionIndex * this.TAB_W + this.TAB_W / 2,
                              this.yScroll + clansOptionIndex2 * 11,
                              2
                           );
                     }
                  }
               } else if (index == 1) {
                  g.setColor(index == this.selected ? 16383818 : 15196114);
                  g.fillRectDelegated(this.xScroll, y2, this.wScroll, height2);
                  if (this.clanInfo != null) {
                     mFont.tahoma_7b_dark
                        .drawString(g, this.clanInfo, this.xScroll + this.wScroll / 2, y2 + 6, 2);
                  }
               } else if (this.isSearchClan) {
                  if (this.clans != null && this.clans.length != 0) {
                     g.setColor(index == this.selected ? 16383818 : 15196114);
                     g.fillRectDelegated(x2, y2, width, height2);
                     g.setColor(index == this.selected ? 9541120 : 9993045);
                     g.fillRectDelegated(x, y, 24, height);
                     if (ClanImage.isExistClanImage(this.clans[index - 2].imgID)) {
                        if (ClanImage.getClanImage((short)this.clans[index - 2].imgID).idImage != null) {
                           SmallImage.drawSmallImageUntransformed(
                              g,
                              ClanImage.getClanImage((short)this.clans[index - 2].imgID).idImage[0],
                              x + 12,
                              y + height / 2,
                              0,
                              StaticObj.VCENTER_HCENTER
                           );
                        }
                     } else {
                        ClanImage cm;
                        (cm = new ClanImage()).ID = this.clans[index - 2].imgID;
                        if (!ClanImage.isExistClanImage(cm.ID)) {
                           ClanImage.addClanImage(cm);
                        }
                     }

                     String text = this.clans[index - 2].name.length() > 17
                        ? this.clans[index - 2].name.substring(0, 17) + "..."
                        : this.clans[index - 2].name;
                     mFont.tahoma_7b_green2.drawString(g, text, x2 + 5, y2, 0);
                     mFont.tahoma_7_blue.drawString(g, this.clans[index - 2].slogan, x2 + 5, y2 + 11, 0);
                     mFont.tahoma_7_green2
                        .drawString(
                           g,
                           this.clans[index - 2].currMember + "/" + this.clans[index - 2].maxMember,
                           x2 + width - 5,
                           y2,
                           1
                        );
                  }
               } else if (this.isViewMember) {
                  g.setColor(index == this.selected ? 16383818 : 15196114);
                  g.fillRectDelegated(x2, y2, width, height2);
                  g.setColor(index == this.selected ? 9541120 : 9993045);
                  g.fillRectDelegated(x, y, 24, height);
                  Member memberValue = this.member != null
                            ? (Member)this.member.elementAt(index - 2)
                            : (Member)this.myMember.elementAt(index - 2);
                  if (memberValue.headICON != -1) {
                     SmallImage.drawSmallImage(g, memberValue.headICON, x, y, 0, 0);
                  } else {
                     Part part = GameScr.parts[memberValue.head];
                     SmallImage.drawSmallImage(
                        g,
                        part.pi[Char.CharInfo[0][0][0]].id,
                        x + part.pi[Char.CharInfo[0][0][0]].dx,
                        y + 3 + part.pi[Char.CharInfo[0][0][0]].dy,
                        0,
                        0
                     );
                  }

                  g.setClip(this.xScroll, this.yScroll + this.cmy, this.wScroll, this.hScroll);
                  mFont tahoma_7b_dark = mFont.tahoma_7b_dark;
                  if (memberValue.role == 0) {
                     tahoma_7b_dark = mFont.tahoma_7b_red;
                  } else if (memberValue.role == 1) {
                     tahoma_7b_dark = mFont.tahoma_7b_green;
                  } else if (memberValue.role == 2) {
                     tahoma_7b_dark = mFont.tahoma_7b_green2;
                  }

                  tahoma_7b_dark.drawString(g, memberValue.name, x2 + 5, y2, 0);
                  mFont.tahoma_7_blue.drawString(g, mResources.power + ": " + memberValue.powerPoint, x2 + 5, y2 + 11, 0);
                  SmallImage.drawSmallImage(g, 7223, x2 + width - 7, y2 + 12, 0, 3);
                  mFont.tahoma_7_green2.drawString(g, "" + memberValue.clanPoint, x2 + width - 15, y2 + 6, 1);
               } else if (this.isMessage && ClanMessage.vMessage.size() != 0) {
                  ClanMessage message = (ClanMessage)ClanMessage.vMessage.elementAt(index - 2);
                  g.setColor(index == this.selected && message.option == null ? 16383818 : 15196114);
                  g.fillRectDelegated(x, y, width + 24, height2);
                  message.paint(g, x, y);
                  if (message.option != null) {
                     int value2 = this.xScroll + this.wScroll - 2 - message.option.length * 40;

                     for (int optionIndex = 0; optionIndex < message.option.length; optionIndex++) {
                        if (optionIndex == this.cSelected && index == this.selected) {
                           g.drawImage(GameScr.imgLbtnFocus2, ((int) (value2 + optionIndex * 40 + 20)), ((int) (y2 + height2 / 2)), StaticObj.VCENTER_HCENTER);
                           mFont.tahoma_7b_green2.drawString(g, message.option[optionIndex], value2 + optionIndex * 40 + 20, y2 + 6, 2);
                        } else {
                           g.drawImage(GameScr.imgLbtn2, ((int) (value2 + optionIndex * 40 + 20)), ((int) (y2 + height2 / 2)), StaticObj.VCENTER_HCENTER);
                           mFont.tahoma_7b_dark.drawString(g, message.option[optionIndex], value2 + optionIndex * 40 + 20, y2 + 6, 2);
                        }
                     }
                  }
               }
            }
         }

         this.paintScrollArrow(g);
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private void paintArchivement(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(0, -this.cmy);
      g.setColor(0);
      if (this.currentListLength == 0) {
         mFont.tahoma_7_green2
            .drawString(
               g,
               mResources.no_mission,
               this.xScroll + this.wScroll / 2,
               this.yScroll + this.hScroll / 2 - mFont.tahoma_7.getHeight() / 2,
               2
            );
      } else if (Char.myCharz().arrArchive != null) {
         if (Char.myCharz().arrArchive.length == this.currentListLength) {
            for (int archiveIndex = 0; archiveIndex < this.currentListLength; archiveIndex++) {
               int x = this.xScroll;
               int y = this.yScroll + archiveIndex * this.ITEM_HEIGHT;
               int width = this.wScroll;
               int height = this.ITEM_HEIGHT - 1;
               Archivement archive = Char.myCharz().arrArchive[archiveIndex];
               g.setColor(
                  archiveIndex != this.selected || (archive.isRecieve || archive.isFinish) && (!archive.isRecieve || !archive.isFinish)
                     ? 15196114
                     : 16383818
               );
               g.fillRectDelegated(x, y, width, height);
               if (archive != null) {
                  if (!archive.isFinish) {
                     mFont.tahoma_7.drawString(g, archive.info1, x + 5, y, 0);
                     mFont.tahoma_7_green
                        .drawString(g, archive.money + " " + mResources.RUBY, x + width - 5, y, 1);
                     mFont.tahoma_7_red.drawString(g, archive.info2, x + 5, y + 11, 0);
                  } else if (archive.isFinish && !archive.isRecieve) {
                     mFont.tahoma_7.drawString(g, archive.info1, x + 5, y, 0);
                     mFont.tahoma_7_blue
                        .drawString(
                           g, mResources.reward_mission + archive.money + " " + mResources.RUBY, x + 5, y + 11, 0
                        );
                     if (archiveIndex == this.selected) {
                        mFont.tahoma_7b_green2.drawString(g, mResources.receive_upper, x + width - 20, y + 6, 2);
                        mFont.tahoma_7b_dark.drawString(g, mResources.receive_upper, x + width - 20, y + 6, 2);
                     } else {
                        g.drawImage(GameScr.imgLbtn2, ((int) (x + width - 20)), ((int) (y + height / 2)), StaticObj.VCENTER_HCENTER);
                        mFont.tahoma_7b_dark.drawString(g, mResources.receive_upper, x + width - 20, y + 6, 2);
                     }
                  } else if (archive.isFinish && archive.isRecieve) {
                     mFont.tahoma_7_green.drawString(g, archive.info1, x + 5, y, 0);
                     mFont.tahoma_7_green.drawString(g, archive.info2, x + 5, y + 11, 0);
                  }
               }
            }

            this.paintScrollArrow(g);
         }
      }
   }

   private void paintCombine(mGraphics graphicsValue) {
      graphicsValue.setColor(16711680);
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);
      if (this.vItemCombine.size() == 0) {
         if (this.combineInfo != null) {
            for (int combineInfoIndex = 0; combineInfoIndex < this.combineInfo.length; combineInfoIndex++) {
               mFont.tahoma_7b_dark
                  .drawString(
                     graphicsValue,
                     this.combineInfo[combineInfoIndex],
                     this.xScroll + this.wScroll / 2,
                     this.yScroll + this.hScroll / 2 - this.combineInfo.length * 14 / 2 + combineInfoIndex * 14 + 5,
                     2
                  );
            }
         }
      } else {
         for (int itemCombineIndex = 0; itemCombineIndex < this.vItemCombine.size() + 1; itemCombineIndex++) {
            int x = this.xScroll + 36;
            int y = this.yScroll + itemCombineIndex * this.ITEM_HEIGHT;
            int width = this.wScroll - 36;
            int height = this.ITEM_HEIGHT - 1;
            int x2 = this.xScroll;
            int y2 = this.yScroll + itemCombineIndex * this.ITEM_HEIGHT;
            int height2 = this.ITEM_HEIGHT - 1;
            if (y - this.cmy <= this.yScroll + this.hScroll
               && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
               if (itemCombineIndex == this.vItemCombine.size()) {
                  if (this.vItemCombine.size() > 0) {
                     if (!GameCanvas.isTouch && itemCombineIndex == this.selected) {
                        graphicsValue.setColor(16383818);
                        graphicsValue.fillRectDelegated(x2, y, this.wScroll, height + 2);
                     }

                     if ((itemCombineIndex != this.selected || this.keyTouchCombine != 1) && (GameCanvas.isTouch || itemCombineIndex != this.selected)) {
                        graphicsValue.drawImage(
                           GameScr.imgLbtn, ((int) (this.xScroll + this.wScroll / 2)), ((int) (y + height / 2 + 1)), StaticObj.VCENTER_HCENTER
                        );
                        mFont.tahoma_7b_dark
                           .drawString(graphicsValue, mResources.UPGRADE, this.xScroll + this.wScroll / 2, y + height / 2 - 4, 2);
                     } else {
                        graphicsValue.drawImage(
                           GameScr.imgLbtnFocus, ((int) (this.xScroll + this.wScroll / 2)), ((int) (y + height / 2 + 1)), StaticObj.VCENTER_HCENTER
                        );
                        mFont.tahoma_7b_green2
                           .drawString(graphicsValue, mResources.UPGRADE, this.xScroll + this.wScroll / 2, y + height / 2 - 4, 2);
                     }
                  }
               } else {
                  graphicsValue.setColor(itemCombineIndex == this.selected ? 16383818 : 15196114);
                  graphicsValue.fillRectDelegated(x, y, width, height);
                  graphicsValue.setColor(itemCombineIndex == this.selected ? 9541120 : 9993045);
                  Item itemCombine;
                  if ((itemCombine = (Item)this.vItemCombine.elementAt(itemCombineIndex)) != null) {
                     for (int itemOptionIndex = 0; itemOptionIndex < itemCombine.itemOption.length; itemOptionIndex++) {
                        byte id;
                        if (itemCombine.itemOption[itemOptionIndex].optionTemplate.id == 72
                           && itemCombine.itemOption[itemOptionIndex].param > 0
                           && GetColor_ItemBg(id = GetColor_Item_Upgrade(itemCombine.itemOption[itemOptionIndex].param)) != -1) {
                           graphicsValue.setColor(GetColor_ItemBg(id));
                        }
                     }
                  }

                  graphicsValue.fillRectDelegated(x2, y2, 34, height2);
                  if (itemCombine != null) {
                     String text = "";
                     mFont tahoma_7_green2 = mFont.tahoma_7_green2;
                     if (itemCombine.itemOption != null) {
                        for (int itemOptionIndex2 = 0; itemOptionIndex2 < itemCombine.itemOption.length; itemOptionIndex2++) {
                           if (itemCombine.itemOption[itemOptionIndex2].optionTemplate.id == 72) {
                              text = " [+" + itemCombine.itemOption[itemOptionIndex2].param + "]";
                           }

                           if (itemCombine.itemOption[itemOptionIndex2].optionTemplate.id == 41) {
                              if (itemCombine.itemOption[itemOptionIndex2].param == 1) {
                                 tahoma_7_green2 = GetFont(0);
                              } else if (itemCombine.itemOption[itemOptionIndex2].param == 2) {
                                 tahoma_7_green2 = GetFont(2);
                              } else if (itemCombine.itemOption[itemOptionIndex2].param == 3) {
                                 tahoma_7_green2 = GetFont(8);
                              } else if (itemCombine.itemOption[itemOptionIndex2].param == 4) {
                                 tahoma_7_green2 = GetFont(7);
                              }
                           }
                        }
                     }

                     tahoma_7_green2.drawString(graphicsValue, itemCombine.template.name + text, x + 5, y + 1, 0);
                     String text2 = "";
                     if (itemCombine.itemOption != null) {
                        if (itemCombine.itemOption.length > 0
                           && itemCombine.itemOption[0] != null
                           && itemCombine.itemOption[0].optionTemplate.id != 102
                           && itemCombine.itemOption[0].optionTemplate.id != 107) {
                           text2 = text2 + itemCombine.itemOption[0].getOptionString();
                        }

                        mFont tahoma_7_blue = mFont.tahoma_7_blue;
                        if (itemCombine.compare < 0 && itemCombine.template.type != 5) {
                           tahoma_7_blue = mFont.tahoma_7_red;
                        }

                        if (itemCombine.itemOption.length > 1) {
                           for (int itemOptionIndex3 = 1; itemOptionIndex3 < itemCombine.itemOption.length; itemOptionIndex3++) {
                              if (itemCombine.itemOption[itemOptionIndex3] != null
                                 && itemCombine.itemOption[itemOptionIndex3].optionTemplate.id != 102
                                 && itemCombine.itemOption[itemOptionIndex3].optionTemplate.id != 107) {
                                 text2 = text2 + "," + itemCombine.itemOption[itemOptionIndex3].getOptionString();
                              }
                           }
                        }

                        tahoma_7_blue.drawString(graphicsValue, text2, x + 5, y + 11, 0);
                     }

                     SmallImage.drawSmallImage(graphicsValue, itemCombine.template.iconID, x2 + 17, y2 + height2 / 2, 0, 3);
                     if (itemCombine.itemOption != null) {
                        for (int itemOptionIndex4 = 0; itemOptionIndex4 < itemCombine.itemOption.length; itemOptionIndex4++) {
                           this.paintOptItem(graphicsValue, itemCombine.itemOption[itemOptionIndex4].optionTemplate.id, x2, y2, height2);
                        }

                        for (int itemOptionIndex5 = 0; itemOptionIndex5 < itemCombine.itemOption.length; itemOptionIndex5++) {
                           paintUpgradeEffect(
                              graphicsValue,
                              itemCombine.itemOption[itemOptionIndex5].optionTemplate.id,
                              itemCombine.itemOption[itemOptionIndex5].param,
                              x2,
                              y2,
                              34,
                              height2
                           );
                        }
                     }

                     if (itemCombine.quantity > 1) {
                        mFont.tahoma_7_yellow
                           .drawString(graphicsValue, "" + itemCombine.quantity, x2 + 34, y2 + height2 - mFont.tahoma_7_yellow.getHeight(), 1);
                     }
                  }
               }
            }
         }

         this.paintScrollArrow(graphicsValue);
      }
   }

   private void paintInventory(mGraphics graphicsValue) {
      graphicsValue.setColor(16711680);
      Item[] itemBody = Char.myCharz().arrItemBody;
      Item[] itemBag = Char.myCharz().arrItemBag;
      this.currentListLength = this.checkCurrentListLength(itemBody.length + itemBag.length);
      int height = (itemBody.length + itemBag.length) / 20 + ((itemBody.length + itemBag.length) % 20 > 0 ? 1 : 0);
      this.TAB_W_NEW = this.wScroll / height;

      for (int index = 0; index < height; index++) {
         int value = index == this.newSelected && this.selected == 0 ? (GameCanvas.gameTick % 10 < 7 ? -1 : 0) : 0;
         graphicsValue.setColor(index == this.newSelected ? 16383818 : 15723751);
         graphicsValue.fillRect(this.xScroll + index * this.TAB_W_NEW, value + 89 - 10, this.TAB_W_NEW - 1, 21);
         if (index == this.newSelected) {
            graphicsValue.setColor(13524492);
            int x = this.xScroll + index * this.TAB_W_NEW;
            int value2 = value + 89 - 10 + 21;
            graphicsValue.fillRect(x, value2 - 3, this.TAB_W_NEW - 1, 3);
         }

         mFont.tahoma_7_grey
            .drawString(graphicsValue, "" + (index + 1), this.xScroll + index * this.TAB_W_NEW + this.TAB_W_NEW / 2, value + 91 - 10, 2);
      }

      graphicsValue.setClip(this.xScroll, this.yScroll + 21, this.wScroll, this.hScroll - 21);
      graphicsValue.translate(0, -this.cmy);

      try {
         for (int select = 1; select < this.currentListLength; select++) {
            int x2 = this.xScroll + 36;
            int y = this.yScroll + select * this.ITEM_HEIGHT;
            int width = this.wScroll - 36;
            height = this.ITEM_HEIGHT - 1;
            int x3 = this.xScroll;
            int y2 = this.yScroll + select * this.ITEM_HEIGHT;
            int height2 = this.ITEM_HEIGHT - 1;
            if (y - this.cmy <= this.yScroll + this.hScroll
               && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
               boolean condition = GetInventorySelect_isbody(select, this.newSelected, Char.myCharz().arrItemBody);
               int itemBodyIndex = GetInventorySelect_body(select, this.newSelected);
               int itemBagIndex = GetInventorySelect_bag(select, this.newSelected, Char.myCharz().arrItemBody);
               graphicsValue.setColor(select == this.selected ? 16383818 : (condition ? 15196114 : 15723751));
               graphicsValue.fillRectDelegated(x2, y, width, height);
               graphicsValue.setColor(select == this.selected ? 9541120 : (condition ? 9993045 : 11837316));
               Item item;
               if ((item = condition ? itemBody[itemBodyIndex] : itemBag[itemBagIndex]) != null) {
                  for (int itemOptionIndex = 0; itemOptionIndex < item.itemOption.length; itemOptionIndex++) {
                     int intSlot12;
                     if (item.itemOption[itemOptionIndex].optionTemplate.id == 72
                         && item.itemOption[itemOptionIndex].param > 0
                         && GetColor_ItemBg(intSlot12 = GetColor_Item_Upgrade(item.itemOption[itemOptionIndex].param)) != -1) {
                         graphicsValue.setColor(GetColor_ItemBg(intSlot12));
                     }
                  }
               }

               graphicsValue.fillRectDelegated(x3, y2, 34, height2);
               if (item != null && item.isSelect && GameCanvas.panel.type == 12) {
                  graphicsValue.setColor(select == this.selected ? 7040779 : 6047789);
                  graphicsValue.fillRectDelegated(x3, y2, 34, height2);
               }

               if (item != null) {
                  String text = "";
                  mFont tahoma_7_green2 = mFont.tahoma_7_green2;
                  if (item.itemOption != null) {
                     for (int itemOptionIndex2 = 0; itemOptionIndex2 < item.itemOption.length; itemOptionIndex2++) {
                        if (item.itemOption[itemOptionIndex2].optionTemplate.id == 72) {
                           text = " [+" + item.itemOption[itemOptionIndex2].param + "]";
                        }

                        if (item.itemOption[itemOptionIndex2].optionTemplate.id == 41) {
                           if (item.itemOption[itemOptionIndex2].param == 1) {
                              tahoma_7_green2 = GetFont(0);
                           } else if (item.itemOption[itemOptionIndex2].param == 2) {
                              tahoma_7_green2 = GetFont(2);
                           } else if (item.itemOption[itemOptionIndex2].param == 3) {
                              tahoma_7_green2 = GetFont(8);
                           } else if (item.itemOption[itemOptionIndex2].param == 4) {
                              tahoma_7_green2 = GetFont(7);
                           }
                        }
                     }
                  }

                  tahoma_7_green2.drawString(graphicsValue, item.template.name + text, x2 + 5, y + 1, 0);
                  String text2 = "";
                  if (item.itemOption != null) {
                     if (item.itemOption.length > 0
                        && item.itemOption[0] != null
                        && item.itemOption[0].optionTemplate.id != 102
                        && item.itemOption[0].optionTemplate.id != 107) {
                        text2 = text2 + item.itemOption[0].getOptionString();
                     }

                     mFont tahoma_7_blue = mFont.tahoma_7_blue;
                     if (item.compare < 0 && item.template.type != 5) {
                        tahoma_7_blue = mFont.tahoma_7_red;
                     }

                     if (item.itemOption.length > 1) {
                        for (int itemOptionIndex3 = 1; itemOptionIndex3 < 2; itemOptionIndex3++) {
                           if (item.itemOption[itemOptionIndex3] != null
                              && item.itemOption[itemOptionIndex3].optionTemplate.id != 102
                              && item.itemOption[itemOptionIndex3].optionTemplate.id != 107) {
                              text2 = text2 + "," + item.itemOption[itemOptionIndex3].getOptionString();
                           }
                        }
                     }

                     tahoma_7_blue.drawString(graphicsValue, text2, x2 + 5, y + 11, 0);
                  }

                  SmallImage.drawSmallImage(graphicsValue, item.template.iconID, x3 + 17, y2 + height2 / 2, 0, 3);
                  if (item.itemOption != null) {
                     for (int itemOptionIndex4 = 0; itemOptionIndex4 < item.itemOption.length; itemOptionIndex4++) {
                        this.paintOptItem(graphicsValue, item.itemOption[itemOptionIndex4].optionTemplate.id, x3, y2, height2);
                     }

                     for (int itemOptionIndex5 = 0; itemOptionIndex5 < item.itemOption.length; itemOptionIndex5++) {
                        paintUpgradeEffect(
                           graphicsValue,
                           item.itemOption[itemOptionIndex5].optionTemplate.id,
                           item.itemOption[itemOptionIndex5].param,
                           x3,
                           y2,
                           34,
                           height2
                        );
                     }
                  }

                  if (item.quantity > 1) {
                     mFont.tahoma_7_yellow
                        .drawString(graphicsValue, "" + item.quantity, x3 + 34, y2 + height2 - mFont.tahoma_7_yellow.getHeight(), 1);
                  }
               }
            }
         }
      } catch (Exception ignoredException) {
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void paintTab(mGraphics g) {
      if (this.type != 23 && this.type != 24) {
         if (this.type == 20) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.account, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 22) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.autoFunction, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 19) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.option, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 18) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.change_flag, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 13 && this.equals(GameCanvas.panel2)) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.item_receive2, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 12 && GameCanvas.panel2 != null) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.UPGRADE, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 11) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.friend, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 16) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.enemy, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 15) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, this.topName, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 2 && GameCanvas.panel2 != null) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.chest, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 9) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.achievement_mission, this.xScroll + this.wScroll / 2, 59, 2);
         } else if (this.type == 3) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.select_zone, this.startTabPos + this.TAB_W / 2, 59, 2);
         } else if (this.type == 26) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.napngoc, this.startTabPos + this.TAB_W / 2, 59, 2);
         } else if (this.type == 14) {
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
            mFont.tahoma_7b_dark.drawString(g, mResources.select_map, this.startTabPos + this.TAB_W / 2, 59, 2);
         } else if (this.type == 4) {
            mFont.tahoma_7b_dark.drawString(g, mResources.map, this.startTabPos + this.TAB_W / 2, 59, 2);
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
         } else if (this.type == 7) {
            mFont.tahoma_7b_dark.drawString(g, mResources.trangbi, this.startTabPos + this.TAB_W / 2, 59, 2);
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
         } else if (this.type == 17) {
            mFont.tahoma_7b_dark.drawString(g, mResources.kigui, this.startTabPos + this.TAB_W / 2, 59, 2);
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
         } else if (this.type == 8) {
            mFont.tahoma_7b_dark.drawString(g, mResources.msg, this.startTabPos + this.TAB_W / 2, 59, 2);
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
         } else if (this.type == 10) {
            mFont.tahoma_7b_dark.drawString(g, mResources.wat_do_u_want, this.startTabPos + this.TAB_W / 2, 59, 2);
            g.setColor(13524492);
            g.fillRect(this.X + 1, 78, this.W - 2, 1);
         } else {
            if (this.currentTabIndex == 3 && this.mainTabName.length != 4) {
               g.translate(-this.cmx, 0);
            }

            for (int currentTabNameIndex = 0; currentTabNameIndex < this.currentTabName.length; currentTabNameIndex++) {
               g.setColor(currentTabNameIndex == this.currentTabIndex ? 6805896 : 16773296);
               PopUp.paintPopUp(
                  g, this.startTabPos + currentTabNameIndex * this.TAB_W, 52, this.TAB_W - 1, 25, currentTabNameIndex == this.currentTabIndex ? 1 : 0, true
               );
               if (currentTabNameIndex == this.keyTouchTab) {
                  g.drawImage(
                     ItemMap.imageFlare, ((int) (this.startTabPos + currentTabNameIndex * this.TAB_W + this.TAB_W / 2)), ((int) (62)), 3
                  );
               }

               mFont font = currentTabNameIndex == this.currentTabIndex ? mFont.tahoma_7_green2 : mFont.tahoma_7_grey;
               if (!this.currentTabName[currentTabNameIndex][1].equals("")) {
                  font.drawString(
                     g, this.currentTabName[currentTabNameIndex][0], this.startTabPos + currentTabNameIndex * this.TAB_W + this.TAB_W / 2, 53, 2
                  );
                  font.drawString(
                     g, this.currentTabName[currentTabNameIndex][1], this.startTabPos + currentTabNameIndex * this.TAB_W + this.TAB_W / 2, 64, 2
                  );
               } else {
                  font.drawString(
                     g, this.currentTabName[currentTabNameIndex][0], this.startTabPos + currentTabNameIndex * this.TAB_W + this.TAB_W / 2, 59, 2
                  );
               }

               if (this.type == 0 && this.currentTabName.length == 5 && GameScr.isNewClanMessage && GameCanvas.gameTick % 4 == 0) {
                  g.drawImage(ItemMap.imageFlare, ((int) (this.startTabPos + 3 * this.TAB_W + this.TAB_W / 2)), ((int) (77)), 33);
               }
            }

            g.setColor(13524492);
            g.fillRect(1, 78, this.W - 2, 1);
         }
      } else {
         g.setColor(13524492);
         g.fillRect(this.X + 1, 78, this.W - 2, 1);
         mFont.tahoma_7b_dark.drawString(g, mResources.gameInfo, this.xScroll + this.wScroll / 2, 59, 2);
      }
   }

   private static void paintToolInfo(mGraphics graphicsValue) {
      mFont.tahoma_7b_white.drawString(graphicsValue, mResources.dragon_ball + " " + "2.5.2", 60, 4, 0, mFont.tahoma_7b_dark);
      mFont.tahoma_7_yellow
         .drawString(graphicsValue, mResources.character + ": " + Char.myCharz().cName, 60, 16, 0, mFont.tahoma_7_grey);
      String text = GameCanvas.loginScr.tfUser.getText().equals("")
         ? mResources.not_register_yet
         : GameCanvas.loginScr.tfUser.getText();
      mFont.tahoma_7_yellow
         .drawString(
            graphicsValue,
            mResources.account_server + " " + ServerListScreen.nameServer[ServerListScreen.ipSelect] + ": " + text,
            60,
            27,
            0,
            mFont.tahoma_7_grey
         );
   }

   private void paintMyInfo(mGraphics graphicsValue) {
      this.paintCharInfo(graphicsValue, Char.myCharz());
   }

   private void paintCharInfo(mGraphics g, Char c) {
      mFont.tahoma_7b_white
         .drawString(
            g, (GameScr.isNewMember != 1 ? "" : "       ") + c.cName, this.X + 60, 4, 0, mFont.tahoma_7b_dark
         );
      if (GameScr.isNewMember == 1) {
         SmallImage.drawSmallImage(g, 5427, this.X + 55, 4, 0, 0);
      }

      if (c.cMaxStamina > 0) {
         mFont.tahoma_7_yellow.drawString(g, mResources.vitality, this.X + 60, 16, 0, mFont.tahoma_7_grey);
         g.drawImage(GameScr.imgMPLost, ((int) (this.X + 95)), ((int) (19)), 0);
         int width = c.cStamina * mGraphics.getImageWidth(GameScr.imgMP) / c.cMaxStamina;
         g.setClip(95, this.X + 19, width, 20);
         g.drawImage(GameScr.imgMP, ((int) (this.X + 95)), ((int) (19)), 0);
      }

      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      if (c.cPower > 0L) {
         mFont.tahoma_7_yellow
            .drawString(
               g, c.me ? c.getStrLevel() : c.currStrLevel, this.X + 60, 27, 0, mFont.tahoma_7_grey
            );
      }

      mFont.tahoma_7_yellow
         .drawString(
            g,
            mResources.power + ": " + Firework.getMoneys(c.cPower),
            this.X + 60,
            38,
            0,
            mFont.tahoma_7_grey
         );
   }

   public final int getCompare(Item item) {
      if (item == null) {
         return -1;
      }

      if (!item.isTypeBody()) {
         return 0;
      }

      if (item.itemOption == null) {
         return -1;
      }

      ItemOption itemOption;
      if ((itemOption = item.itemOption[0]).optionTemplate.id == 22) {
         itemOption.optionTemplate = GameScr.gI().iOptionTemplates[6];
         itemOption.param *= 1000;
      }

      if (itemOption.optionTemplate.id == 23) {
         itemOption.optionTemplate = GameScr.gI().iOptionTemplates[7];
         itemOption.param *= 1000;
      }

      Item itemBody = null;

      for (int itemBodyIndex = 0; itemBodyIndex < Char.myCharz().arrItemBody.length; itemBodyIndex++) {
         Item itemBody2 = Char.myCharz().arrItemBody[itemBodyIndex];
         if (itemOption.optionTemplate.id == 22) {
            itemOption.optionTemplate = GameScr.gI().iOptionTemplates[6];
            itemOption.param *= 1000;
         }

         if (itemOption.optionTemplate.id == 23) {
            itemOption.optionTemplate = GameScr.gI().iOptionTemplates[7];
            itemOption.param *= 1000;
         }

         if (itemBody2 != null && itemBody2.itemOption != null && itemBody2.template.type == item.template.type) {
            itemBody = itemBody2;
            break;
         }
      }

      if (itemBody == null) {
         Res.outz("5");
         return itemOption.param;
      }

      int param;
      if (itemBody != null && itemBody.itemOption != null) {
         param = itemOption.param - itemBody.itemOption[0].param;
      } else {
         param = itemOption.param;
      }

      return param;
   }

   private static void paintMapInfo(mGraphics g) {
      mFont.tahoma_7b_white.drawString(g, mResources.MENUGENDER[TileMap.planetID], 60, 4, 0);
      String text = "";
      if (TileMap.mapID >= 135 && TileMap.mapID <= 138) {
         text = " " + mResources.tang + TileMap.zoneID;
      }

      mFont.tahoma_7_yellow.drawString(g, TileMap.mapName + text, 60, 16, 0);
      mFont.tahoma_7b_white.drawString(g, mResources.quest_place + ": ", 60, 27, 0);
      if (GameScr.getTaskMapId() >= 0 && GameScr.getTaskMapId() <= TileMap.mapNames.length - 1) {
         mFont.tahoma_7_yellow.drawString(g, TileMap.mapNames[GameScr.getTaskMapId()], 60, 38, 0);
      } else {
         mFont.tahoma_7_yellow.drawString(g, mResources.random, 60, 38, 0);
      }
   }

   private void paintItemBodyBagInfo(mGraphics graphicsValue) {
      mFont.tahoma_7_yellow
         .drawString(
            graphicsValue,
            mResources.HP + ": " + Char.myCharz().cHP + " / " + Char.myCharz().cHPFull,
            this.X + 60,
            2,
            0,
            mFont.tahoma_7_grey
         );
      mFont.tahoma_7_yellow
         .drawString(
            graphicsValue,
            mResources.KI + ": " + Char.myCharz().cMP + " / " + Char.myCharz().cMPFull,
            this.X + 60,
            14,
            0,
            mFont.tahoma_7_grey
         );
      mFont.tahoma_7_yellow
         .drawString(
            graphicsValue,
            mResources.hit_point
               + ": "
               + Char.myCharz().cDamFull
               + ", "
               + mResources.critical
               + ": "
               + Char.myCharz().cCriticalFull
               + "%",
            this.X + 60,
            26,
            0,
            mFont.tahoma_7_grey
         );
      mFont.tahoma_7_yellow
         .drawString(
            graphicsValue,
            mResources.giamsatthuong
               + ": "
               + Char.myCharz().cGiamST
               + "%, "
               + mResources.critdame
               + ": "
               + Char.myCharz().cCritDameFull
               + "%",
            this.X + 60,
            38,
            0,
            mFont.tahoma_7_grey
         );
   }

   private void paintMap(mGraphics g) {
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      g.translate(-this.cmxMap, -this.cmyMap);
      g.drawImage(imgMap, ((int) (this.xScroll)), ((int) (this.yScroll)), 0);
      int partIndex = Char.myCharz().head;
      Part part = GameScr.parts[partIndex];
      SmallImage.drawSmallImage(
         g, part.pi[Char.CharInfo[0][0][0]].id, this.xMap, this.yMap + 5, 0, 3
      );
      byte align = 2;
      if (this.xMap <= 40) {
         align = 0;
      }

      if (this.xMap >= 220) {
         align = 1;
      }

      mFont.tahoma_7b_yellow
         .drawString(g, TileMap.mapName, this.xMap, this.yMap - 12, align, mFont.tahoma_7_grey);
      int mapXIndex = -1;
      if (GameScr.getTaskMapId() != -1) {
         for (int mapIdIndex = 0; mapIdIndex < mapId[TileMap.planetID].length; mapIdIndex++) {
            if (mapId[TileMap.planetID][mapIdIndex] == GameScr.getTaskMapId()) {
               mapXIndex = mapIdIndex;
               break;
            }

            mapXIndex = 4;
         }

         if (GameCanvas.gameTick % 4 > 0) {
            g.drawImage(
               ItemMap.imageFlare,
               ((int) (this.xScroll + mapX[TileMap.planetID][mapXIndex])),
               ((int) (this.yScroll + mapY[TileMap.planetID][mapXIndex])),
               3
            );
         }
      }

      if (!GameCanvas.isTouch) {
         g.drawImage(imgBantay, ((int) (this.xMove)), ((int) (this.yMove)), StaticObj.TOP_RIGHT);

         for (int mapIdIndex2 = 0; mapIdIndex2 < mapX[TileMap.planetID].length; mapIdIndex2++) {
            int x = mapX[TileMap.planetID][mapIdIndex2] + this.xScroll;
            int value = mapY[TileMap.planetID][mapIdIndex2] + this.yScroll;
            if (Res.inRect(x - 15, value - 15, 30, 30, this.xMove, this.yMove)) {
               byte align2 = 2;
               if (x <= 20) {
                  align2 = 0;
               }

               if (x >= 220) {
                  align2 = 1;
               }

               mFont.tahoma_7b_yellow
                  .drawString(
                     g, TileMap.mapNames[mapId[TileMap.planetID][mapIdIndex2]], x, value - 12, align2, mFont.tahoma_7_grey
                  );
               break;
            }
         }
      } else if (!this.trans) {
         for (int mapIdIndex3 = 0; mapIdIndex3 < mapX[TileMap.planetID].length; mapIdIndex3++) {
            int x2 = mapX[TileMap.planetID][mapIdIndex3] + this.xScroll;
            int y = mapY[TileMap.planetID][mapIdIndex3] + this.yScroll;
            if (Res.inRect(x2 - 15, y - 15, 30, 30, this.pX, this.pY)) {
               byte align3 = 2;
               if (x2 <= 30) {
                  align3 = 0;
               }

               if (x2 >= 220) {
                  align3 = 1;
               }

               g.drawImage(imgBantay, ((int) (x2)), ((int) (y)), StaticObj.TOP_RIGHT);
               mFont.tahoma_7b_yellow
                  .drawString(
                     g, TileMap.mapNames[mapId[TileMap.planetID][mapIdIndex3]], x2, y - 12, align3, mFont.tahoma_7_grey
                  );
               break;
            }
         }
      }

      g.translate(-g.getTranslateX(), -g.getTranslateY());
      if (mapXIndex != -1) {
         if (mapX[TileMap.planetID][mapXIndex] + this.xScroll < this.cmxMap) {
            g.drawRegion(
               Mob.imgHP, 0, 0, 9, 6, 5, this.xScroll + 5, this.yScroll + this.hScroll / 2 - 4, 0
            );
         }

         if (this.cmxMap + this.wScroll < mapX[TileMap.planetID][mapXIndex] + this.xScroll) {
            g.drawRegion(
               Mob.imgHP,
               0,
               0,
               9,
               6,
               6,
               this.xScroll + this.wScroll - 5,
               this.yScroll + this.hScroll / 2 - 4,
               StaticObj.TOP_RIGHT
            );
         }

         if (mapY[TileMap.planetID][mapXIndex] < this.cmyMap) {
            g.drawRegion(
               Mob.imgHP,
               0,
               0,
               9,
               6,
               1,
               this.xScroll + this.wScroll / 2,
               this.yScroll + 5,
               StaticObj.TOP_CENTER
            );
         }

         if (mapY[TileMap.planetID][mapXIndex] > this.cmyMap + this.hScroll) {
            g.drawRegion(
               Mob.imgHP,
               0,
               0,
               9,
               6,
               0,
               this.xScroll + this.wScroll / 2,
               this.yScroll + this.hScroll - 5,
               StaticObj.BOTTOM_HCENTER
            );
         }
      }
   }

   private void paintTask(mGraphics g) {
      int value = GameCanvas.h > 300 ? 20 : 15;
      if (isPaintMap) {
         GameScr.gI();
         if (!GameScr.isMapDocNhan()) {
            GameScr.gI();
            if (!GameScr.isMapFize()) {
               g.drawImage(
                  this.keyTouchMapButton == 1 ? GameScr.imgLbtnFocus : GameScr.imgLbtn,
                  ((int) (this.xScroll + this.wScroll / 2)),
                  ((int) (this.yScroll + this.hScroll - value)),
                  3
               );
               mFont.tahoma_7b_dark
                  .drawString(
                     g,
                     mResources.map,
                     this.xScroll + this.wScroll / 2,
                     this.yScroll + this.hScroll - (value + 5),
                     2
                  );
            }
         }
      }

      this.xstart = this.xScroll + 5;
      this.ystart = this.yScroll + 14;
      this.yPaint = this.ystart;
      g.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll - 35);
      if (this.scroll != null) {
         if (this.scroll.cmy > 0) {
            g.drawRegion(
               Mob.imgHP, 0, 0, 9, 6, 1, this.xScroll + this.wScroll - 12, this.yScroll + 3, 0
            );
         }

         if (this.scroll.cmy < this.scroll.cmyLim) {
            g.drawRegion(
               Mob.imgHP,
               0,
               0,
               9,
               6,
               0,
               this.xScroll + this.wScroll - 12,
               this.yScroll + this.hScroll - 45,
               0
            );
         }

         g.translate(0, -this.scroll.cmy);
      }

      this.indexRowMax = 0;
      boolean condition = false;
      if (Char.myCharz().taskMaint != null) {
         for (int nameIndex = 0; nameIndex < Char.myCharz().taskMaint.names.length; nameIndex++) {
            mFont.tahoma_7_grey
               .drawString(
                  g,
                  Char.myCharz().taskMaint.names[nameIndex],
                  this.xScroll + this.wScroll / 2,
                  this.yPaint - 5 + nameIndex * 12,
                  2
               );
            this.indexRowMax++;
         }

         this.yPaint = this.yPaint + (Char.myCharz().taskMaint.names.length - 1) * 12;
         int subNameIndex = 0;
         String text = null;

         for (int countIndex = 0; countIndex < Char.myCharz().taskMaint.subNames.length; countIndex++) {
            if (Char.myCharz().taskMaint.subNames[countIndex] != null) {
               label190: {
                  subNameIndex = countIndex;
                  String text2 = "- " + Char.myCharz().taskMaint.subNames[countIndex];
                  mFont tahoma_7;
                  mGraphics graphicsValue;
                  String text3;
                  int xstartValue;
                  byte byteValue;
                  if (Char.myCharz().taskMaint.counts[countIndex] != -1) {
                     if (Char.myCharz().taskMaint.index == countIndex) {
                        if (Char.myCharz().taskMaint.counts[countIndex] != 1) {
                           text2 = text2
                              + " ("
                              + Char.myCharz().taskMaint.count
                              + "/"
                              + Char.myCharz().taskMaint.counts[countIndex]
                              + ")";
                        }

                        if (Char.myCharz().taskMaint.count != Char.myCharz().taskMaint.counts[countIndex]) {
                           mFont tahoma_7_blue = mFont.tahoma_7_grey;
                           if (!condition) {
                              condition = true;
                              (tahoma_7_blue = mFont.tahoma_7_blue)
                                 .drawString(
                                    g,
                                    text2,
                                    this.xstart
                                       + 5
                                       + (
                                          tahoma_7_blue == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10
                                             ? GameCanvas.gameTick % 4 / 2
                                             : 0
                                       ),
                                    this.yPaint += 12,
                                    0
                                 );
                              break label190;
                           }

                           tahoma_7_blue.drawString(
                              g,
                              "- ...",
                              this.xstart
                                 + 5
                                 + (tahoma_7_blue == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0),
                              this.yPaint += 12,
                              0
                           );
                           break label190;
                        }

                        tahoma_7 = mFont.tahoma_7;
                        graphicsValue = g;
                        text3 = text2;
                        xstartValue = this.xstart;
                        byteValue = 5;
                     } else {
                        if (Char.myCharz().taskMaint.index <= countIndex) {
                           if (Char.myCharz().taskMaint.counts[countIndex] != 1) {
                              text2 = text2 + " 0/" + Char.myCharz().taskMaint.counts[countIndex];
                           }

                           mFont tahoma_7_blue2 = mFont.tahoma_7_grey;
                           if (!condition) {
                              condition = true;
                              (tahoma_7_blue2 = mFont.tahoma_7_blue)
                                 .drawString(
                                    g,
                                    text2,
                                    this.xstart
                                       + 5
                                       + (
                                          tahoma_7_blue2 == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10
                                             ? GameCanvas.gameTick % 4 / 2
                                             : 0
                                       ),
                                    this.yPaint += 12,
                                    0
                                 );
                              break label190;
                           }

                           tahoma_7_blue2.drawString(
                              g,
                              "- ...",
                              this.xstart
                                 + 5
                                 + (tahoma_7_blue2 == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0),
                              this.yPaint += 12,
                              0
                           );
                           break label190;
                        }

                        if (Char.myCharz().taskMaint.counts[countIndex] != 1) {
                           text2 = text2
                              + " ("
                              + Char.myCharz().taskMaint.counts[countIndex]
                              + "/"
                              + Char.myCharz().taskMaint.counts[countIndex]
                              + ")";
                        }

                        tahoma_7 = mFont.tahoma_7_white;
                        graphicsValue = g;
                        text3 = text2;
                        xstartValue = this.xstart;
                        byteValue = 5;
                     }
                  } else if (Char.myCharz().taskMaint.index > countIndex) {
                     tahoma_7 = mFont.tahoma_7_white;
                     graphicsValue = g;
                     text3 = text2;
                     xstartValue = this.xstart;
                     byteValue = 5;
                  } else {
                     mFont tahoma_7_blue3 = mFont.tahoma_7_grey;
                     if (!condition) {
                        condition = true;
                        (tahoma_7_blue3 = mFont.tahoma_7_blue)
                           .drawString(
                              g,
                              text2,
                              this.xstart
                                 + 5
                                 + (tahoma_7_blue3 == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0),
                              this.yPaint += 12,
                              0
                           );
                        break label190;
                     }

                     tahoma_7 = tahoma_7_blue3;
                     graphicsValue = g;
                     text3 = "- ...";
                     xstartValue = this.xstart + 5;
                     byteValue = (byte)(tahoma_7_blue3 == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0);
                  }

                  tahoma_7.drawString(graphicsValue, text3, xstartValue + byteValue, this.yPaint += 12, 0);
               }

               this.indexRowMax++;
            } else if (Char.myCharz().taskMaint.index <= countIndex) {
               text = "- " + Char.myCharz().taskMaint.subNames[subNameIndex];
               mFont tahoma_7_blue4 = mFont.tahoma_7_grey;
               if (!condition) {
                  condition = true;
                  tahoma_7_blue4 = mFont.tahoma_7_blue;
               }

               tahoma_7_blue4.drawString(
                  g,
                  text,
                  this.xstart
                     + 5
                     + (tahoma_7_blue4 == mFont.tahoma_7_blue && GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0),
                  this.yPaint += 12,
                  0
               );
            }
         }

         this.yPaint += 5;

         for (int detailIndex = 0; detailIndex < Char.myCharz().taskMaint.details.length; detailIndex++) {
            mFont.tahoma_7_green2
               .drawString(
                  g, Char.myCharz().taskMaint.details[detailIndex], this.xstart + 5, this.yPaint += 12, 0
               );
            this.indexRowMax++;
         }
      } else {
         int mapNameIndex = GameScr.getTaskMapId();
         byte npcTemplateIndex = GameScr.getTaskNpcId();
         String src;
         if (mapNameIndex == -3 || npcTemplateIndex == -3) {
            src = mResources.DES_TASK[3];
         } else if (Char.myCharz().taskMaint == null
            && Char.myCharz().ctaskId == 9
            && Char.myCharz().nClass.classId == 0) {
            src = mResources.TASK_INPUT_CLASS;
         } else {
            if (npcTemplateIndex < 0 || mapNameIndex < 0) {
               return;
            }

            src = mResources.DES_TASK[0]
               + Npc.arrNpcTemplate[npcTemplateIndex].name
               + mResources.DES_TASK[1]
               + TileMap.mapNames[mapNameIndex]
               + mResources.DES_TASK[2];
         }

         String[] texts = mFont.tahoma_7_white.splitFontArray(src, 150);

         for (int textIndex = 0; textIndex < texts.length; textIndex++) {
            if (textIndex == 0) {
               mFont.tahoma_7_white.drawString(g, texts[textIndex], this.xstart + 5, this.yPaint = this.ystart, 0);
            } else {
               mFont.tahoma_7_white.drawString(g, texts[textIndex], this.xstart + 5, this.yPaint += 12, 0);
            }
         }
      }

      if (this.scroll == null) {
         this.scroll = new Scroll();
         this.scroll
            .setStyle(
               this.indexRowMax, 12, this.xScroll, this.yScroll, this.wScroll, this.hScroll - value - 40, true, 1
            );
      }
   }

   private void cleanCombine() {
      for (int itemCombineIndex = 0; itemCombineIndex < this.vItemCombine.size(); itemCombineIndex++) {
         ((Item)this.vItemCombine.elementAt(itemCombineIndex)).isSelect = false;
      }

      this.vItemCombine.removeAllElements();
   }

   public final void hideNow() {
      if (this.timeShow > 0) {
         this.isClose = false;
      } else {
         this.cp = null;
         if (this.isTypeShop() || TileMap.mapID == 45) {
            Char.myCharz().resetPartTemp();
         }

         if (this.chatTField != null && this.type == 13 && this.chatTField.isShow) {
            this.chatTField = null;
         }

         if (this.type == 13 && !this.isAccept) {
            Service.gI().giaodich((byte)3, -1, (byte)-1, -1);
         }

         SoundMn.gI();
         SoundMn.buttonClose();
         GameScr.isPaint = true;
         TileMap.lastPlanetId = -1;
         imgMap = null;
         System.gc();
         this.isClanOption = false;
         this.isClose = true;
         this.cleanCombine();
         Hint.clickNpc();
         GameCanvas.panel2 = null;
         GameCanvas.clearAllPointerEvent();
         GameCanvas.clearKeyPressed();
         this.pointerDownTime = this.pointerDownFirstX = 0;
         this.pointerIsDowning = false;
         this.isShow = false;
         if ((
               Char.myCharz().cHP <= 0L
                  || Char.myCharz().statusMe == 14
                  || Char.myCharz().statusMe == 5
            )
            && Char.myCharz().meDead) {
            Command center = new Command(mResources.DIES[0], 11038, GameScr.gI());
            GameScr.gI().center = center;
            Char.myCharz().cHP = 0L;
         }
      }
   }

   public final void hide() {
      if (this.timeShow > 0) {
         this.isClose = false;
      } else {
         this.cp = null;
         if (this.isTypeShop() || TileMap.mapID == 45) {
            Char.myCharz().resetPartTemp();
         }

         if (this.chatTField != null && this.type == 13 && this.chatTField.isShow) {
            this.chatTField = null;
         }

         if (this.type == 13 && !this.isAccept) {
            Service.gI().giaodich((byte)3, -1, (byte)-1, -1);
         }

         if (this.type == 15) {
            Service.gI().sendThachDau(-1);
         }

         SoundMn.gI();
         SoundMn.buttonClose();
         GameScr.isPaint = true;
         TileMap.lastPlanetId = -1;
         if (imgMap != null) {
            imgMap = null;
         }

         System.gc();
         this.isClanOption = false;
         if (this.type != 4) {
            if (this.type == 24) {
               this.setTypeGameInfo();
            } else if (this.type == 23) {
               this.setTypeMain();
            } else if (this.type != 3 && this.type != 14) {
               if (this.type == 18 || this.type == 19 || this.type == 20 || this.type == 21) {
                  this.setTypeMain();
                  this.cmx = this.cmtoX = 0;
               } else if (this.type != 8 && this.type != 11 && this.type != 16) {
                  this.isClose = true;
               } else {
                  this.setTypeAccount();
                  this.cmx = this.cmtoX = 0;
               }
            } else if (this.isChangeZone) {
               this.isClose = true;
            } else {
               this.setTypeMain();
               this.cmx = this.cmtoX = 0;
            }
         } else {
            this.setTypeMain();
            this.cmx = this.cmtoX = 0;
         }

         Hint.clickNpc();
         GameCanvas.panel2 = null;
         GameCanvas.clearAllPointerEvent();
         GameCanvas.clearKeyPressed();
         GameCanvas.isFocusPanel2 = false;
         this.pointerDownTime = this.pointerDownFirstX = 0;
         this.pointerIsDowning = false;
         if ((
               Char.myCharz().cHP <= 0L
                  || Char.myCharz().statusMe == 14
                  || Char.myCharz().statusMe == 5
            )
            && Char.myCharz().meDead) {
            Command center = new Command(mResources.DIES[0], 11038, GameScr.gI());
            GameScr.gI().center = center;
            Char.myCharz().cHP = 0L;
         }
      }
   }

   public final void doFireClanOption() {
      if (this.chatTField != null && this.chatTField.isShow) {
         this.chatTField.update();
      } else if (this.isKiguiXu) {
         this.delayKigui++;
         if (this.delayKigui == 10) {
            this.delayKigui = 0;
            this.isKiguiXu = false;
            this.chatTField.tfChat.setText("");
            this.chatTField.strChat = mResources.kiguiXuchat + " ";
            this.chatTField.tfChat.name = mResources.input_money;
            this.chatTField.to = "";
            this.chatTField.isShow = true;
            this.chatTField.tfChat.setIputType(1);
            this.chatTField.tfChat.setMaxTextLenght(10);
            if (GameCanvas.isTouch) {
               this.chatTField.tfChat.doChangeToTextBox();
            }
         }
      } else if (this.isKiguiLuong) {
         this.delayKigui++;
         if (this.delayKigui == 10) {
            this.delayKigui = 0;
            this.isKiguiLuong = false;
            this.chatTField.tfChat.setText("");
            this.chatTField.strChat = mResources.kiguiLuongchat + "  ";
            this.chatTField.tfChat.name = mResources.input_money;
            this.chatTField.to = "";
            this.chatTField.isShow = true;
            this.chatTField.tfChat.setIputType(1);
            this.chatTField.tfChat.setMaxTextLenght(10);
            if (GameCanvas.isTouch) {
               this.chatTField.tfChat.doChangeToTextBox();
            }
         }
      } else {
         if (this.scroll != null) {
            this.scroll.updatecm();
         }

         if (this.tabIcon != null && this.tabIcon.isShow) {
            TabClanIcon tabIconValue = this.tabIcon;
            if (TabClanIcon.scrMain != null) {
               TabClanIcon.scrMain.updatecm();
            }

            if (tabIconValue.cmx != tabIconValue.cmtoX) {
               tabIconValue.cmvx = tabIconValue.cmtoX - tabIconValue.cmx << 2;
               tabIconValue.cmdx = tabIconValue.cmdx + tabIconValue.cmvx;
               tabIconValue.cmx = tabIconValue.cmx + (tabIconValue.cmdx >> 3);
               tabIconValue.cmdx &= 15;
            }

            if (Math.abs(tabIconValue.cmtoX - tabIconValue.cmx) < 10) {
               tabIconValue.cmx = tabIconValue.cmtoX;
            }

            if (tabIconValue.cmx >= tabIconValue.x + tabIconValue.w - 10
               && tabIconValue.cmtoX >= tabIconValue.x + tabIconValue.w - 10) {
               tabIconValue.isShow = false;
            }
         } else {
            this.moveCamera();
            this.isTabInven();
            if (this.waitToPerform > 0) {
               this.waitToPerform--;
               if (this.waitToPerform == 0) {
                  this.lastSelect[this.currentTabIndex] = this.selected;
                  switch (this.type) {
                     case 0:
                        Panel panel = this;

                        try {
                           if (panel.currentTabIndex == 0) {
                              panel.setTabMap();
                           }

                           if (panel.currentTabIndex == 1) {
                              panel.doFireInventory();
                           }

                           if (panel.currentTabIndex == 2) {
                              Panel panel2 = panel;
                              if (panel.selected >= 0) {
                                 if (Char.myCharz().statusMe == 14) {
                                    GameCanvas.startOKDlg(mResources.can_not_do_when_die);
                                 } else if (panel2.selected != 0
                                    && panel2.selected != 1
                                    && panel2.selected != 2
                                    && panel2.selected != 3
                                    && panel2.selected != 4
                                    && panel2.selected != 5) {
                                    int skillTemplateIndex = panel2.selected - 6;
                                    SkillTemplate skillTemplate = Char.myCharz().nClass.skillTemplates[skillTemplateIndex];
                                    Skill skill = Char.myCharz().getSkill(skillTemplate);
                                    Skill nextSkill = null;
                                    MyVector menuItems = new MyVector("");
                                    if (skill != null) {
                                       if (skill.point == skillTemplate.maxPoint) {
                                          menuItems.addElement(new Command(mResources.make_shortcut, panel2, 9003, skill.template));
                                          menuItems.addElement(new Command(mResources.CLOSE, 2));
                                       } else {
                                          nextSkill = skillTemplate.skills[skill.point];
                                          menuItems.addElement(new Command(mResources.UPGRADE, panel2, 9002, nextSkill));
                                          menuItems.addElement(new Command(mResources.make_shortcut, panel2, 9003, skill.template));
                                       }
                                    } else {
                                       nextSkill = skillTemplate.skills[0];
                                       menuItems.addElement(new Command(mResources.learn, panel2, 9004, nextSkill));
                                    }

                                    GameCanvas.menu
                                       .startAt(
                                          menuItems,
                                          panel2.X,
                                          (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                       );
                                    panel2.addSkillDetail(skillTemplate, skill, nextSkill);
                                 } else {
                                    label534: {
                                       long cTiemNang = Char.myCharz().cTiemNang;
                                       int cHPGoc = Char.myCharz().cHPGoc;
                                       int cMPGoc = Char.myCharz().cMPGoc;
                                       int cDamGoc = Char.myCharz().cDamGoc;
                                       int cDefGoc = Char.myCharz().cDefGoc;
                                       if (panel2.selected == 0) {
                                          if (cTiemNang < Char.myCharz().cHPGoc + 1000) {
                                             GameCanvas.showActionWaitDialog(
                                                mResources.not_enough_potential_point1
                                                   + Char.myCharz().cTiemNang
                                                   + mResources.not_enough_potential_point2
                                                   + (Char.myCharz().cHPGoc + 1000)
                                             );
                                             break label534;
                                          }

                                          if (cTiemNang > cHPGoc && cTiemNang < 10 * (2 * (cHPGoc + 1000) + 180) / 2) {
                                             GameCanvas.startYesNoDlg(
                                                mResources.use_potential_point_for1
                                                   + (cHPGoc + 1000)
                                                   + mResources.use_potential_point_for2
                                                   + Char.myCharz().hpFrom1000TiemNang
                                                   + mResources.for_HP,
                                                new Command(mResources.increase_upper, panel2, 9000, null),
                                                new Command(mResources.CANCEL, panel2, 4007, null)
                                             );
                                             break label534;
                                          }

                                          if (cTiemNang >= 10 * (2 * (cHPGoc + 1000) + 180) / 2 && cTiemNang < 100 * (2 * (cHPGoc + 1000) + 1980) / 2) {
                                             MyVector menuItems2;
                                             (menuItems2 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.increase_upper
                                                         + "\n"
                                                         + Char.myCharz().hpFrom1000TiemNang
                                                         + mResources.HP
                                                         + "\n"
                                                         + "-"
                                                         + Res.formatNumber2(cHPGoc + 1000),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems2.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 10 * Char.myCharz().hpFrom1000TiemNang
                                                      + mResources.HP
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(10 * (2 * (cHPGoc + 1000) + 180) / 2),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems2,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }

                                          if (cTiemNang >= 100 * (2 * (cHPGoc + 1000) + 1980) / 2) {
                                             MyVector menuItems3;
                                             (menuItems3 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.increase_upper
                                                         + "\n"
                                                         + Char.myCharz().hpFrom1000TiemNang
                                                         + mResources.HP
                                                         + "\n"
                                                         + "-"
                                                         + Res.formatNumber2(cHPGoc + 1000),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems3.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 10 * Char.myCharz().hpFrom1000TiemNang
                                                      + mResources.HP
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(10 * (2 * (cHPGoc + 1000) + 180) / 2),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             menuItems3.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 100 * Char.myCharz().hpFrom1000TiemNang
                                                      + mResources.HP
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(100 * (2 * (cHPGoc + 1000) + 1980) / 2),
                                                   panel2,
                                                   9007,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems3,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }
                                       }

                                       if (panel2.selected == 1) {
                                          if (Char.myCharz().cTiemNang < Char.myCharz().cMPGoc + 1000) {
                                             GameCanvas.startOKDlg(
                                                mResources.not_enough_potential_point1
                                                   + Char.myCharz().cTiemNang
                                                   + mResources.not_enough_potential_point2
                                                   + (Char.myCharz().cMPGoc + 1000)
                                             );
                                             break label534;
                                          }

                                          if (cTiemNang > cMPGoc && cTiemNang < 10 * (2 * (cMPGoc + 1000) + 180) / 2) {
                                             GameCanvas.startYesNoDlg(
                                                mResources.use_potential_point_for1
                                                   + (cMPGoc + 1000)
                                                   + mResources.use_potential_point_for2
                                                   + Char.myCharz().mpFrom1000TiemNang
                                                   + mResources.for_KI,
                                                new Command(mResources.increase_upper, panel2, 9000, null),
                                                new Command(mResources.CANCEL, panel2, 4007, null)
                                             );
                                             break label534;
                                          }

                                          if (cTiemNang >= 10 * (2 * (cMPGoc + 1000) + 180) / 2 && cTiemNang < 100 * (2 * (cMPGoc + 1000) + 1980) / 2) {
                                             MyVector menuItems4;
                                             (menuItems4 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.increase_upper
                                                         + "\n"
                                                         + Char.myCharz().mpFrom1000TiemNang
                                                         + mResources.KI
                                                         + "\n"
                                                         + "-"
                                                         + Res.formatNumber2(cHPGoc + 1000),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems4.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 10 * Char.myCharz().mpFrom1000TiemNang
                                                      + mResources.KI
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(10 * (2 * (cHPGoc + 1000) + 180) / 2),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems4,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }

                                          if (cTiemNang >= 100 * (2 * (cMPGoc + 1000) + 1980) / 2) {
                                             MyVector menuItems5;
                                             (menuItems5 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.increase_upper
                                                         + "\n"
                                                         + Char.myCharz().mpFrom1000TiemNang
                                                         + mResources.KI
                                                         + "\n"
                                                         + "-"
                                                         + Res.formatNumber2(cMPGoc + 1000),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems5.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 10 * Char.myCharz().mpFrom1000TiemNang
                                                      + mResources.KI
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(10 * (2 * (cMPGoc + 1000) + 180) / 2),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             menuItems5.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 100 * Char.myCharz().mpFrom1000TiemNang
                                                      + mResources.KI
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(100 * (2 * (cMPGoc + 1000) + 1980) / 2),
                                                   panel2,
                                                   9007,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems5,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }
                                       }

                                       if (panel2.selected == 2) {
                                          if (Char.myCharz().cTiemNang
                                             < Char.myCharz().cDamGoc * Char.myCharz().expForOneAdd) {
                                             GameCanvas.startOKDlg(
                                                mResources.not_enough_potential_point1
                                                   + Char.myCharz().cTiemNang
                                                   + mResources.not_enough_potential_point2
                                                   + cDamGoc * 100
                                             );
                                             break label534;
                                          }

                                          if (cTiemNang > cDamGoc && cTiemNang < 10 * (cDamGoc * 2 + 9) / 2 * Char.myCharz().expForOneAdd) {
                                             GameCanvas.startYesNoDlg(
                                                mResources.use_potential_point_for1
                                                   + cDamGoc * 100
                                                   + mResources.use_potential_point_for2
                                                   + Char.myCharz().damFrom1000TiemNang
                                                   + mResources.for_hit_point,
                                                new Command(mResources.increase_upper, panel2, 9000, null),
                                                new Command(mResources.CANCEL, panel2, 4007, null)
                                             );
                                             break label534;
                                          }

                                          if (cTiemNang >= 10 * (cDamGoc * 2 + 9) / 2 * Char.myCharz().expForOneAdd
                                             && cTiemNang < 100 * (cDamGoc * 2 + 99) / 2 * Char.myCharz().expForOneAdd) {
                                             MyVector menuItems6;
                                             (menuItems6 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.increase_upper
                                                         + "\n"
                                                         + Char.myCharz().damFrom1000TiemNang
                                                         + "\n"
                                                         + mResources.hit_point
                                                         + "\n"
                                                         + "-"
                                                         + Res.formatNumber2(cDamGoc * 100),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems6.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 10 * Char.myCharz().damFrom1000TiemNang
                                                      + "\n"
                                                      + mResources.hit_point
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(10 * (cDamGoc * 2 + 9) / 2 * Char.myCharz().expForOneAdd),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems6,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }

                                          if (cTiemNang >= 100 * (cDamGoc * 2 + 99) / 2 * Char.myCharz().expForOneAdd) {
                                             MyVector menuItems7;
                                             (menuItems7 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.increase_upper
                                                         + "\n"
                                                         + Char.myCharz().damFrom1000TiemNang
                                                         + "\n"
                                                         + mResources.hit_point
                                                         + "\n"
                                                         + "-"
                                                         + Res.formatNumber2(cDamGoc * 100),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems7.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 10 * Char.myCharz().damFrom1000TiemNang
                                                      + "\n"
                                                      + mResources.hit_point
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(10 * (cDamGoc * 2 + 9) / 2 * Char.myCharz().expForOneAdd),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             menuItems7.addElement(
                                                new Command(
                                                   mResources.increase_upper
                                                      + "\n"
                                                      + 100 * Char.myCharz().damFrom1000TiemNang
                                                      + "\n"
                                                      + mResources.hit_point
                                                      + "\n"
                                                      + "-"
                                                      + Res.formatNumber2(100 * (cDamGoc * 2 + 99) / 2 * Char.myCharz().expForOneAdd),
                                                   panel2,
                                                   9007,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems7,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }
                                       }

                                       if (panel2.selected == 3) {
                                          if (Char.myCharz().cTiemNang < 50000 + Char.myCharz().cDefGoc * 1000) {
                                             GameCanvas.startOKDlg(
                                                mResources.not_enough_potential_point1
                                                   + Firework.getMoneys(Char.myCharz().cTiemNang)
                                                   + mResources.not_enough_potential_point2
                                                   + Firework.getMoneys(50000 + Char.myCharz().cDefGoc * 1000)
                                             );
                                          } else {
                                             long number = 1L * (2 * (cDefGoc + 5)) / 2L * 100000L;
                                             long number2 = 10L * (2 * (cDefGoc + 5) + 9) / 2L * 100000L;
                                             long number3 = 100L * (2 * (cDefGoc + 5) + 99) / 2L * 100000L;
                                             mResources.use_potential_point_for1 = mResources.increase_upper;
                                             MyVector menuItems8;
                                             (menuItems8 = new MyVector(""))
                                                .addElement(
                                                   new Command(
                                                      mResources.use_potential_point_for1
                                                         + "\n"
                                                         + "1 "
                                                         + mResources.armor
                                                         + "\n"
                                                         + Res.formatNumber2(number),
                                                      panel2,
                                                      9000,
                                                      null
                                                   )
                                                );
                                             menuItems8.addElement(
                                                new Command(
                                                   mResources.use_potential_point_for1
                                                      + "\n"
                                                      + "10 "
                                                      + mResources.armor
                                                      + "\n"
                                                      + Res.formatNumber2(number2),
                                                   panel2,
                                                   9006,
                                                   null
                                                )
                                             );
                                             menuItems8.addElement(
                                                new Command(
                                                   mResources.use_potential_point_for1
                                                      + "\n"
                                                      + "100 "
                                                      + mResources.armor
                                                      + "\n"
                                                      + Res.formatNumber2(number3),
                                                   panel2,
                                                   9007,
                                                   null
                                                )
                                             );
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems8,
                                                   panel2.X,
                                                   (panel2.selected + 1) * panel2.ITEM_HEIGHT - panel2.cmy + panel2.yScroll
                                                );
                                             panel2.addSkillDetail2(panel2.selected);
                                          }
                                       } else if (panel2.selected == 4) {
                                          int t_tiemnangIndex;
                                          if ((t_tiemnangIndex = Char.myCharz().cCriticalGoc) > t_tiemnang.length - 1) {
                                             t_tiemnangIndex = t_tiemnang.length - 1;
                                          }

                                          long number4 = t_tiemnang[t_tiemnangIndex];
                                          if (Char.myCharz().cTiemNang < number4) {
                                             GameCanvas.startOKDlg(
                                                mResources.not_enough_potential_point1
                                                   + Res.formatNumber2(Char.myCharz().cTiemNang)
                                                   + mResources.not_enough_potential_point2
                                                   + Res.formatNumber2(number4)
                                             );
                                          } else {
                                             GameCanvas.startYesNoDlg(
                                                mResources.use_potential_point_for1
                                                   + Res.formatNumber(number4)
                                                   + mResources.use_potential_point_for2
                                                   + Char.myCharz().criticalFrom1000Tiemnang
                                                   + mResources.for_crit,
                                                new Command(mResources.increase_upper, panel2, 9000, null),
                                                new Command(mResources.CANCEL, panel2, 4007, null)
                                             );
                                          }
                                       } else if (panel2.selected == 5) {
                                          Service.gI().speacialSkill((byte)0);
                                       }
                                    }
                                 }
                              }
                           }

                           if (panel.currentTabIndex == 3) {
                              if (panel.mainTabName.length == 4) {
                                 panel.doFireTool();
                              } else {
                                 Panel panel3 = panel;
                                 panel.partID = null;
                                 panel3.charInfo = null;
                                 if (panel3.selected < 0) {
                                    panel3.cSelected = -1;
                                 } else {
                                    if (Char.myCharz().clan == null) {
                                       if (panel3.selected == 0) {
                                          if (panel3.cSelected == 0) {
                                             Panel panel4 = panel3;
                                             panel3.chatTField.strChat = mResources.input_clan_name;
                                             panel4.chatTField.tfChat.name = mResources.clan_name;
                                             panel4.chatTField.to = "";
                                             panel4.chatTField.isShow = true;
                                             panel4.chatTField.tfChat.isFocus = true;
                                             panel4.chatTField.tfChat.setIputType(0);
                                             if (GameCanvas.isTouch) {
                                                panel4.chatTField.tfChat.doChangeToTextBox();
                                             }
                                          } else if (panel3.cSelected == 1) {
                                             InfoDlg.showWait();
                                             Panel panel5 = panel3;
                                             panel3.chatTField.strChat = mResources.input_clan_name_to_create;
                                             panel5.chatTField.tfChat.name = mResources.input_clan_name;
                                             panel5.chatTField.to = "";
                                             panel5.chatTField.isShow = true;
                                             panel5.chatTField.tfChat.setIputType(0);
                                             if (GameCanvas.isTouch) {
                                                panel5.chatTField.tfChat.doChangeToTextBox();
                                             }

                                             Service.gI().getClan((byte)1, -1, null);
                                          }
                                       } else if (panel3.selected != -1) {
                                          if (panel3.selected == 1) {
                                             if (panel3.isSearchClan) {
                                                Service.gI().searchClan("");
                                             } else if (panel3.isViewMember && panel3.currClan != null) {
                                                GameCanvas.startYesNoDlg(
                                                   mResources.do_u_want_join_clan + panel3.currClan.name,
                                                   new Command(mResources.YES, panel3, 4000, panel3.currClan),
                                                   new Command(mResources.NO, panel3, 4005, panel3.currClan)
                                                );
                                             }
                                          } else if (panel3.isSearchClan) {
                                             panel3.currClan = panel3.getCurrClan();
                                             if (panel3.currClan != null) {
                                                MyVector menuItems9;
                                                (menuItems9 = new MyVector(""))
                                                   .addElement(new Command(mResources.request_join_clan, panel3, 4000, panel3.currClan));
                                                menuItems9.addElement(new Command(mResources.view_clan_member, panel3, 4001, panel3.currClan));
                                                GameCanvas.menu
                                                   .startAt(
                                                      menuItems9,
                                                      panel3.X,
                                                      (panel3.selected + 1) * panel3.ITEM_HEIGHT
                                                         - panel3.cmy
                                                         + panel3.yScroll
                                                   );
                                                panel3.addClanDetail(panel3.getCurrClan());
                                             }
                                          } else if (panel3.isViewMember) {
                                             panel3.currMem = panel3.getCurrMember();
                                             if (panel3.currMem != null) {
                                                MyVector menuItems10;
                                                (menuItems10 = new MyVector(""))
                                                   .addElement(new Command(mResources.CLOSE, panel3, 8000, panel3.currClan));
                                                GameCanvas.menu
                                                   .startAt(
                                                      menuItems10,
                                                      panel3.X,
                                                      (panel3.selected + 1) * panel3.ITEM_HEIGHT
                                                         - panel3.cmy
                                                         + panel3.yScroll
                                                   );
                                                GameCanvas.menu
                                                   .startAt(
                                                      menuItems10,
                                                      0,
                                                      (panel3.selected + 1) * panel3.ITEM_HEIGHT
                                                         - panel3.cmy
                                                         + panel3.yScroll
                                                   );
                                                panel3.addClanMemberDetail(panel3.currMem);
                                             }
                                          }
                                       }
                                    } else if (panel3.selected == 0) {
                                       if (panel3.isMessage) {
                                          if (panel3.cSelected == 0) {
                                             if (panel3.myMember.size() > 1) {
                                                Panel panel6 = panel3;
                                                panel3.chatTField.strChat = mResources.chat_clan;
                                                panel6.chatTField.tfChat.name = mResources.CHAT;
                                                panel6.chatTField.to = "";
                                                panel6.chatTField.isShow = true;
                                                panel6.chatTField.tfChat.isFocus = true;
                                                panel6.chatTField.tfChat.setIputType(0);
                                                if (GameCanvas.isTouch) {
                                                   panel6.chatTField.tfChat.doChangeToTextBox();
                                                }
                                             } else {
                                                panel3.member = null;
                                                panel3.isSearchClan = false;
                                                panel3.isViewMember = true;
                                                panel3.isMessage = false;
                                                panel3.currentListLength = panel3.myMember.size() + 2;
                                                panel3.initTabClans();
                                             }
                                          }

                                          if (panel3.cSelected == 1) {
                                             Service.gI().clanMessage(1, null, -1);
                                          }

                                          if (panel3.cSelected == 2) {
                                             panel3.member = null;
                                             panel3.isSearchClan = false;
                                             panel3.isViewMember = true;
                                             panel3.isMessage = false;
                                             panel3.currentListLength = panel3.myMember.size() + 2;
                                             panel3.initTabClans();
                                             panel3.getCurrClanOtion();
                                          }
                                       } else if (panel3.isViewMember) {
                                          if (panel3.cSelected == 0) {
                                             panel3.isSearchClan = false;
                                             panel3.isViewMember = false;
                                             panel3.isMessage = true;
                                             panel3.currentListLength = ClanMessage.vMessage.size() + 2;
                                             panel3.initTabClans();
                                          }

                                          if (panel3.cSelected == 1) {
                                             if (panel3.myMember.size() > 1) {
                                                Service.gI().leaveClan();
                                             } else {
                                                panel3.chagenSlogan();
                                             }
                                          }

                                          if (panel3.cSelected == 2) {
                                             if (panel3.myMember.size() > 1) {
                                                panel3.chagenSlogan();
                                             } else {
                                                Service.gI().getClan((byte)3, -1, null);
                                             }
                                          }

                                          if (panel3.cSelected == 3) {
                                             Service.gI().getClan((byte)3, -1, null);
                                          }
                                       }
                                    } else if (panel3.selected == 1) {
                                       if (panel3.isSearchClan) {
                                          Service.gI().searchClan("");
                                       }
                                    } else if (panel3.isSearchClan) {
                                       panel3.currClan = panel3.getCurrClan();
                                       if (panel3.currClan != null) {
                                          MyVector menuItems11;
                                          (menuItems11 = new MyVector(""))
                                             .addElement(new Command(mResources.view_clan_member, panel3, 4001, panel3.currClan));
                                          GameCanvas.menu
                                             .startAt(
                                                menuItems11,
                                                panel3.X,
                                                (panel3.selected + 1) * panel3.ITEM_HEIGHT - panel3.cmy + panel3.yScroll
                                             );
                                          panel3.addClanDetail(panel3.getCurrClan());
                                       }
                                    } else if (panel3.isViewMember) {
                                       Res.outz("TOI DAY 1");
                                       panel3.currMem = panel3.getCurrMember();
                                       if (panel3.currMem != null) {
                                          MyVector menuItems12 = new MyVector("");
                                          Res.outz("TOI DAY 2");
                                          if (panel3.member != null) {
                                             menuItems12.addElement(new Command(mResources.CLOSE, panel3, 8000, null));
                                             Res.outz("TOI DAY 3");
                                          } else if (panel3.myMember != null) {
                                             Res.outz("TOI DAY 4");
                                             Res.outz("my role= " + Char.myCharz().role);
                                             if (Char.myCharz().charID == panel3.currMem.ID
                                                || Char.myCharz().role == 2) {
                                                menuItems12.addElement(new Command(mResources.CLOSE, panel3, 8000, panel3.currMem));
                                             }

                                             if (Char.myCharz().role < 2
                                                && Char.myCharz().charID != panel3.currMem.ID) {
                                                if (panel3.currMem.role == 0 || panel3.currMem.role == 1) {
                                                   menuItems12.addElement(new Command(mResources.CLOSE, panel3, 8000, panel3.currMem));
                                                }

                                                if (panel3.currMem.role == 2) {
                                                   menuItems12.addElement(new Command(mResources.create_clan_co_leader, panel3, 5002, panel3.currMem));
                                                }

                                                if (Char.myCharz().role == 0) {
                                                   menuItems12.addElement(new Command(mResources.create_clan_leader, panel3, 5001, panel3.currMem));
                                                   if (panel3.currMem.role == 1) {
                                                      menuItems12.addElement(new Command(mResources.disable_clan_mastership, panel3, 5003, panel3.currMem));
                                                   }
                                                }
                                             }

                                             if (Char.myCharz().role < panel3.currMem.role) {
                                                menuItems12.addElement(new Command(mResources.kick_clan_mem, panel3, 5004, panel3.currMem));
                                             }
                                          }

                                          GameCanvas.menu
                                             .startAt(
                                                menuItems12,
                                                panel3.X,
                                                (panel3.selected + 1) * panel3.ITEM_HEIGHT - panel3.cmy + panel3.yScroll
                                             );
                                          panel3.addClanMemberDetail(panel3.currMem);
                                       }
                                    } else if (panel3.isMessage) {
                                       panel3.currMess = panel3.getCurrMessage();
                                       if (panel3.currMess != null) {
                                          if (panel3.currMess.type == 0) {
                                             MyVector menuItems13;
                                             (menuItems13 = new MyVector(""))
                                                .addElement(new Command(mResources.CLOSE, panel3, 8000, panel3.currMess));
                                             GameCanvas.menu
                                                .startAt(
                                                   menuItems13,
                                                   panel3.X,
                                                   (panel3.selected + 1) * panel3.ITEM_HEIGHT - panel3.cmy + panel3.yScroll
                                                );
                                             panel3.addMessageDetail(panel3.currMess);
                                          } else if (panel3.currMess.type == 1) {
                                             if (panel3.currMess.playerId != Char.myCharz().charID
                                                && panel3.cSelected != -1) {
                                                Service.gI().clanDonate(panel3.currMess.id);
                                             }
                                          } else if (panel3.currMess.type == 2 && panel3.currMess.option != null) {
                                             if (panel3.cSelected == 0) {
                                                Service.gI().joinClan(panel3.currMess.id, (byte)1);
                                             } else if (panel3.cSelected == 1) {
                                                Service.gI().joinClan(panel3.currMess.id, (byte)0);
                                             }
                                          }
                                       }
                                    }

                                    if (GameCanvas.isTouch) {
                                       panel3.cSelected = -1;
                                       panel3.selected = -1;
                                    }
                                 }
                              }
                           }

                           if (panel.currentTabIndex == 4) {
                              panel.doFireTool();
                           }
                        } catch (Exception exception) {
                           exception.printStackTrace();
                        }
                        break;
                     case 1:
                     case 17:
                        this.doFireShop();
                        break;
                     case 2:
                        this.doFireBox();
                        break;
                     case 3:
                        this.doFireZone();
                        break;
                     case 4:
                        Panel panel7 = this;
                        if (imgMap != null) {
                           imgMap = null;
                        }

                        TileMap.lastPlanetId = -1;
                        System.gc();
                        SmallImage.loadBigRMS();
                        panel7.setTypeMain();
                        panel7.cmx = panel7.cmtoX = 0;
                     case 5:
                     case 6:
                     case 22:
                     case 24:
                     case 25:
                     default:
                        break;
                     case 7:
                        if (this.equals(GameCanvas.panel2) && GameCanvas.panel.type == 2) {
                           this.doFireBox();
                           return;
                        }

                        this.doFireInventory();
                        break;
                     case 8:
                        Panel panel8 = this;
                        if (this.selected == 0) {
                           panel8.isViewChatServer = !panel8.isViewChatServer;
                           Rms.saveRMSInt("viewchat", panel8.isViewChatServer ? 1 : 0);
                           if (GameCanvas.isTouch) {
                              panel8.selected = -1;
                           }
                        } else if (panel8.selected >= 0 && panel8.logChat.size() != 0) {
                           MyVector menuItems14 = new MyVector("");
                           panel8.currInfoItem = panel8.selected - 1;
                           menuItems14.addElement(
                              new Command(
                                 mResources.CHAT, panel8, 8001, (InfoItem)panel8.logChat.elementAt(panel8.currInfoItem)
                              )
                           );
                           menuItems14.addElement(
                              new Command(
                                 mResources.make_friend, panel8, 8003, (InfoItem)panel8.logChat.elementAt(panel8.currInfoItem)
                              )
                           );
                           GameCanvas.menu
                              .startAt(
                                 menuItems14,
                                 panel8.X,
                                 (panel8.selected + 1) * panel8.ITEM_HEIGHT - panel8.cmy + panel8.yScroll
                              );
                           InfoItem logChatValue = (InfoItem)panel8.logChat.elementAt(panel8.selected - 1);
                           Panel panel9 = panel8;
                           String chat = "|0|1|" + logChatValue.charInfo.cName;
                           chat = chat + "\n";
                           chat = chat + "\n--";
                           chat = chat + "\n|5|" + Res.split(logChatValue.s, "|", 0)[2];
                           panel9.cp = new ChatPopup();
                           panel9.popUpDetailInit(panel9.cp, chat);
                           panel9.charInfo = logChatValue.charInfo;
                           panel9.currItem = null;
                        }
                        break;
                     case 9:
                        this.doFireArchivement();
                        break;
                     case 10:
                        Panel panel10 = this;
                        if (this.selected != -1) {
                           panel10.isSelectPlayerMenu = true;
                           panel10.hide();
                        }
                        break;
                     case 11:
                        this.doFireFriend();
                        break;
                     case 12:
                        this.doFireCombine();
                        break;
                     case 13:
                        this.doFireGiaoDich();
                        break;
                     case 14:
                        this.doFireZone();
                        break;
                     case 15:
                        this.doFireTop();
                        break;
                     case 16:
                        this.doFireEnemy();
                        break;
                     case 18:
                        this.doFireChangeFlag();
                        break;
                     case 19:
                        this.doFireOption();
                        break;
                     case 20:
                        this.doFireAccount();
                        break;
                     case 21:
                        Panel panel11 = this;
                        if (this.currentTabIndex == 0) {
                           if (panel11.selected == -1 || panel11.selected > Char.myPetz().arrItemBody.length - 1) {
                              break;
                           }

                           MyVector menuItems15 = new MyVector("");
                           Item currItemValue = Char.myPetz().arrItemBody[panel11.selected];
                           panel11.currItem = currItemValue;
                           if (panel11.currItem != null) {
                              menuItems15.addElement(new Command(mResources.MOVEOUT, panel11, 2006, panel11.currItem));
                              GameCanvas.menu
                                 .startAt(
                                    menuItems15,
                                    panel11.X,
                                    (panel11.selected + 1) * panel11.ITEM_HEIGHT - panel11.cmy + panel11.yScroll
                                 );
                              panel11.addItemDetail(panel11.currItem);
                           } else {
                              panel11.cp = null;
                           }
                        }

                        if (panel11.currentTabIndex == 1) {
                           Panel panel12 = panel11;
                           if (panel11.selected != -1) {
                              if (panel12.selected == 5) {
                                 GameCanvas.startYesNoDlg(
                                    mResources.sure_fusion,
                                    new Command(mResources.YES, 888351),
                                    new Command(mResources.NO, 2001)
                                 );
                              } else {
                                 Service.gI().petStatus((byte)panel12.selected);
                                 if (panel12.selected < 4) {
                                    Char.myPetz().petStatus = (byte)panel12.selected;
                                 }
                              }
                           }
                        }

                        if (panel11.currentTabIndex == 2) {
                           panel11.doFireInventory();
                        }
                        break;
                     case 23:
                        Panel panel13 = this;
                        if (this.selected != -1) {
                           panel13.infoSelect = panel13.selected;
                           ((GameInfo)vGameInfo.elementAt(panel13.infoSelect)).hasRead = true;
                           Rms.saveRMSInt(String.valueOf(((GameInfo)vGameInfo.elementAt(panel13.infoSelect)).id), 1);
                           Panel panel14 = panel13;
                           String src = ((GameInfo)vGameInfo.elementAt(panel14.infoSelect)).content;
                           contenInfo = mFont.tahoma_7_grey.splitFontArray(src, panel14.wScroll - 40);
                           panel14.currentListLength = contenInfo.length;
                           panel14.ITEM_HEIGHT = 16;
                           panel14.selected = GameCanvas.isTouch ? -1 : 0;
                           panel14.cmyLim = panel14.currentListLength * panel14.ITEM_HEIGHT - panel14.hScroll;
                           if (panel14.cmyLim < 0) {
                              panel14.cmyLim = 0;
                           }

                           if (panel14.cmy < 0) {
                              panel14.cmy = panel14.cmtoY = 0;
                           }

                           if (panel14.cmy > panel14.cmyLim) {
                              panel14.cmy = panel14.cmtoY = panel14.cmyLim;
                           }

                           panel14.type = 24;
                           panel14.setType(0);
                        }
                        break;
                     case 26:
                        if (this.selected != -1) {
                           PaymentCatalog.onPurchaseSelection();
                           GameCanvas.panel.hide();
                        }
                  }
               }
            }

            for (int messageIndex = 0; messageIndex < ClanMessage.vMessage.size(); messageIndex++) {
               ((ClanMessage)ClanMessage.vMessage.elementAt(messageIndex)).update();
            }

            this.updateCombineEff();
         }
      }
   }

   private void doFireTop() {
      if (this.selected >= -1) {
         if (this.isThachDau) {
            Service.gI().sendTop(this.topName, (byte)this.selected);
         } else {
            MyVector menuItems;
            (menuItems = new MyVector(""))
               .addElement(new Command(mResources.CHAR_ORDER[0], this, 9999, (TopInfo)this.vTop.elementAt(this.selected)));
            GameCanvas.menu
               .startAt(
                  menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
               );
            TopInfo top = (TopInfo)this.vTop.elementAt(this.selected);
            Panel panel = this;
            String chat = "|0|1|" + top.name;
            chat = chat + "\n|1|Top " + top.rank;
            chat = chat + "\n|1|" + top.info;
            chat = chat + "\n|2|" + top.info2;
            panel.cp = new ChatPopup();
            panel.popUpDetailInit(panel.cp, chat);
            panel.partID = new int[]{top.headID, top.leg, top.body};
            panel.currItem = null;
            panel.charInfo = null;
         }
      }
   }

   private void doFireGiaoDich() {
      if (this.currentTabIndex == 0 && this.equals(GameCanvas.panel)) {
         this.doFireInventory();
      } else {
         if (this.currentTabIndex == 0 && this.equals(GameCanvas.panel2) || this.currentTabIndex == 2) {
            if (this.equals(GameCanvas.panel2)) {
               this.currItem = (Item)GameCanvas.panel2.vFriendGD.elementAt(this.selected);
            } else {
               this.currItem = (Item)GameCanvas.panel.vFriendGD.elementAt(this.selected);
            }

            Res.outz("toi day select= " + this.selected);
            MyVector menuItems;
            (menuItems = new MyVector("")).addElement(new Command(mResources.CLOSE, this, 8000, this.currItem));
            if (this.currItem != null) {
               GameCanvas.menu
                  .startAt(
                     menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
                  );
               this.addItemDetail(this.currItem);
            } else {
               this.cp = null;
            }
         }

         if (this.currentTabIndex == 1) {
            if (this.selected == this.currentListLength - 3) {
               if (this.isLock) {
                  return;
               }

               Panel panel = this;
               if (this.chatTField == null) {
                  panel.chatTField = new ChatTextField();
                  panel.chatTField.tfChat.y = GameCanvas.h
                     - 35
                     - ChatTextField.gI().tfChat.height;
                  panel.chatTField.initChatTextField();
                  panel.chatTField.parentScreen = GameCanvas.panel;
               }

               panel.chatTField.strChat = mResources.input_money_to_trade;
               panel.chatTField.tfChat.name = mResources.input_money;
               panel.chatTField.to = "";
               panel.chatTField.isShow = true;
               panel.chatTField.tfChat.setIputType(1);
               panel.chatTField.tfChat.setMaxTextLenght(10);
               if (GameCanvas.isTouch) {
                  panel.chatTField.tfChat.doChangeToTextBox();
               }
            } else if (this.selected == this.currentListLength - 2) {
               if (!this.isAccept) {
                  this.isLock = !this.isLock;
                  if (this.isLock) {
                     Service.gI().giaodich((byte)5, -1, (byte)-1, -1);
                  } else {
                     this.hide();
                     InfoDlg.showWait();
                     Service.gI().giaodich((byte)3, -1, (byte)-1, -1);
                  }
               } else {
                  this.isAccept = false;
               }
            } else if (this.selected == this.currentListLength - 1) {
               if (this.isLock && !this.isAccept && this.isFriendLock) {
                  GameCanvas.startYesNoDlg(
                     mResources.do_u_sure_to_trade,
                     new Command(mResources.YES, this, 7002, null),
                     new Command(mResources.NO, this, 4005, null)
                  );
               }
            } else {
               if (this.isLock) {
                  return;
               }

               this.currItem = (Item)GameCanvas.panel.vMyGD.elementAt(this.selected);
               MyVector menuItems2;
               (menuItems2 = new MyVector("")).addElement(new Command(mResources.CLOSE, this, 8000, this.currItem));
               if (this.currItem != null) {
                  GameCanvas.menu
                     .startAt(
                        menuItems2, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
                     );
                  this.addItemDetail(this.currItem);
               } else {
                  this.cp = null;
               }
            }
         }

         if (GameCanvas.isTouch) {
            this.selected = -1;
         }
      }
   }

   private void doFireCombine() {
      if (this.currentTabIndex == 0) {
         if (this.selected == -1) {
            return;
         }

         if (this.vItemCombine.size() == 0) {
            return;
         }

         if (this.selected == this.vItemCombine.size()) {
            this.keyTouchCombine = -1;
            this.selected = GameCanvas.isTouch ? -1 : 0;
            InfoDlg.showWait();
            Service.gI().combine((byte)1, this.vItemCombine);
            return;
         }

         if (this.selected > this.vItemCombine.size() - 1) {
            return;
         }

         this.currItem = (Item)GameCanvas.panel.vItemCombine.elementAt(this.selected);
         MyVector menuItems;
         (menuItems = new MyVector("")).addElement(new Command(mResources.GETOUT, this, 6001, this.currItem));
         if (this.currItem != null) {
            GameCanvas.menu
               .startAt(
                  menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
               );
            this.addItemDetail(this.currItem);
         } else {
            this.cp = null;
         }
      }

      if (this.currentTabIndex == 1) {
         this.doFireInventory();
      }
   }

   private void doFireShop() {
      this.currItem = null;
      if (this.selected >= 0) {
         MyVector menuItems = new MyVector("");
         if (this.currentTabIndex < this.currentTabName.length - (GameCanvas.panel2 != null ? 0 : 1) && this.type != 17) {
            this.currItem = Char.myCharz().arrItemShop[this.currentTabIndex][this.selected];
            if (this.currItem != null) {
               if (this.currItem.isBuySpec) {
                  if (this.currItem.buySpec > 0) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with + "\n" + Res.formatNumber2(this.currItem.buySpec),
                           this,
                           3005,
                           this.currItem
                        )
                     );
                  }
               } else if (this.typeShop == 4) {
                  menuItems.addElement(new Command(mResources.receive_upper, this, 30001, this.currItem));
                  menuItems.addElement(new Command(mResources.DELETE, this, 30002, this.currItem));
                  menuItems.addElement(new Command(mResources.receive_all, this, 30003, this.currItem));
               } else if (this.currItem.buyCoin == 0 && this.currItem.buyGold == 0) {
                  if (this.currItem.powerRequire != 0L) {
                     menuItems.addElement(
                        new Command(
                           mResources.learn_with
                              + "\n"
                              + Res.formatNumber(this.currItem.powerRequire)
                              + " \n"
                              + mResources.potential,
                           this,
                           3004,
                           this.currItem
                        )
                     );
                  } else {
                     menuItems.addElement(new Command(mResources.receive_upper + "\n" + mResources.free, this, 3000, this.currItem));
                  }
               } else if (this.typeShop == 8) {
                  if (this.currItem.buyCoin > 0) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with
                              + "\n"
                              + Res.formatNumber2(this.currItem.buyCoin)
                              + "\n"
                              + mResources.XU,
                           this,
                           30001,
                           this.currItem
                        )
                     );
                  }

                  if (this.currItem.buyGold > 0) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with
                              + "\n"
                              + Res.formatNumber2(this.currItem.buyGold)
                              + "\n"
                              + mResources.LUONG,
                           this,
                           30002,
                           this.currItem
                        )
                     );
                  }
               } else if (this.typeShop != 2) {
                  if (this.currItem.buyCoin > 0) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with
                              + "\n"
                              + Res.formatNumber2(this.currItem.buyCoin)
                              + "\n"
                              + mResources.XU,
                           this,
                           3000,
                           this.currItem
                        )
                     );
                  }

                  if (this.currItem.buyGold > 0) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with
                              + "\n"
                              + Res.formatNumber2(this.currItem.buyGold)
                              + "\n"
                              + mResources.LUONG,
                           this,
                           3001,
                           this.currItem
                        )
                     );
                  }
               } else {
                  if (this.currItem.buyCoin != -1) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with
                              + "\n"
                              + Res.formatNumber2(this.currItem.buyCoin)
                              + "\n"
                              + mResources.XU,
                           this,
                           10016,
                           this.currItem
                        )
                     );
                  }

                  if (this.currItem.buyGold != -1) {
                     menuItems.addElement(
                        new Command(
                           mResources.buy_with
                              + "\n"
                              + Res.formatNumber2(this.currItem.buyGold)
                              + "\n"
                              + mResources.LUONG,
                           this,
                           10017,
                           this.currItem
                        )
                     );
                  }
               }
            }
         } else if (this.typeShop == 0) {
            if (this.selected == 0) {
               this.setNewSelected(Char.myCharz().arrItemBody.length + Char.myCharz().arrItemBag.length, false, false);
            } else {
               this.currItem = null;
               if (!GetInventorySelect_isbody(this.selected, this.newSelected, Char.myCharz().arrItemBody)) {
                  Item currItemValue;
                  if ((
                        currItemValue = Char.myCharz().arrItemBag[GetInventorySelect_bag(
                           this.selected, this.newSelected, Char.myCharz().arrItemBody
                        )]
                     )
                     != null) {
                     this.currItem = currItemValue;
                  }
               } else {
                  Item currItemValue2;
                  if ((currItemValue2 = Char.myCharz().arrItemBody[GetInventorySelect_body(this.selected, this.newSelected)]) != null) {
                     this.currItem = currItemValue2;
                  }
               }

               if (this.currItem != null) {
                  menuItems.addElement(new Command(mResources.SALE, this, 3002, this.currItem));
               }
            }
         } else {
            if (this.type == 17) {
               this.currItem = Char.myCharz().arrItemShop[4][this.selected];
            } else {
               this.currItem = Char.myCharz().arrItemShop[this.currentTabIndex][this.selected];
            }

            if (this.currItem.buyType == 0) {
               if (this.currItem.isHaveOption(87)) {
                  menuItems.addElement(new Command(mResources.kiguiLuong, this, 10013, this.currItem));
               } else {
                  menuItems.addElement(new Command(mResources.kiguiXu, this, 10012, this.currItem));
               }
            } else if (this.currItem.buyType == 1) {
               menuItems.addElement(new Command(mResources.huykigui, this, 10014, this.currItem));
               menuItems.addElement(new Command(mResources.upTop, this, 10018, this.currItem));
            } else if (this.currItem.buyType == 2) {
               menuItems.addElement(new Command(mResources.nhantien, this, 10015, this.currItem));
            }
         }

         if (this.currItem != null) {
            Char.myCharz()
               .setPartTemp(
                  this.currItem.headTemp,
                  this.currItem.bodyTemp,
                  this.currItem.legTemp,
                  this.currItem.bagTemp
               );
            GameCanvas.menu
               .startAt(
                  menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
               );
            this.addItemDetail(this.currItem);
         } else {
            this.cp = null;
         }
      }
   }

   private void doFireArchivement() {
      if (this.selected >= 0) {
         if (Char.myCharz().arrArchive[this.selected].isFinish
            && !Char.myCharz().arrArchive[this.selected].isRecieve) {
            if (!GameCanvas.isTouch) {
               Service.gI().getArchivemnt(this.selected);
               return;
            }

            if (GameCanvas.px > this.xScroll + this.wScroll - 40) {
               Service.gI().getArchivemnt(this.selected);
            }
         }
      }
   }

   private void doFireInventory() {
      Res.outz("fire inventory");
      if (Char.myCharz().statusMe == 14) {
         GameCanvas.startOKDlg(mResources.can_not_do_when_die);
      } else if (this.selected != -1) {
         if (this.selected == 0) {
            this.setNewSelected(Char.myCharz().arrItemBody.length + Char.myCharz().arrItemBag.length, false, false);
         } else {
            this.currItem = null;
            MyVector menuItems = new MyVector("");
            if (!GetInventorySelect_isbody(this.selected, this.newSelected, Char.myCharz().arrItemBody)) {
               Item currItemValue;
               if ((
                     currItemValue = Char.myCharz().arrItemBag[GetInventorySelect_bag(
                        this.selected, this.newSelected, Char.myCharz().arrItemBody
                     )]
                  )
                  != null) {
                  this.currItem = currItemValue;
                  if (GameCanvas.panel.type == 12) {
                     menuItems.addElement(new Command(mResources.use_for_combine, this, 6000, this.currItem));
                  } else if (GameCanvas.panel.type == 13) {
                     menuItems.addElement(new Command(mResources.use_for_trade, this, 7000, this.currItem));
                  } else if (currItemValue.isTypeBody()) {
                     menuItems.addElement(new Command(mResources.USE, this, 2000, this.currItem));
                     if (Char.myCharz().havePet) {
                        menuItems.addElement(new Command(mResources.MOVEFORPET, this, 2005, this.currItem));
                     }
                  } else {
                     menuItems.addElement(new Command(mResources.USE, this, 2001, this.currItem));
                  }
               }
            } else {
               Item currItemValue2;
               if ((currItemValue2 = Char.myCharz().arrItemBody[GetInventorySelect_body(this.selected, this.newSelected)]) != null) {
                  this.currItem = currItemValue2;
                  menuItems.addElement(new Command(mResources.GETOUT, this, 2002, this.currItem));
               }
            }

            if (this.currItem != null) {
               Char.myCharz()
                  .setPartTemp(
                     this.currItem.headTemp,
                     this.currItem.bodyTemp,
                     this.currItem.legTemp,
                     this.currItem.bagTemp
                  );
               if (GameCanvas.panel.type != 12 && GameCanvas.panel.type != 13) {
                  if (this.position == 0) {
                     menuItems.addElement(new Command(mResources.MOVEOUT, this, 2003, this.currItem));
                  }

                  if (this.position == 1) {
                     menuItems.addElement(new Command(mResources.SALE, this, 3002, this.currItem));
                  }
               }

               GameCanvas.menu
                  .startAt(
                     menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
                  );
               this.addItemDetail(this.currItem);
            } else {
               this.cp = null;
            }
         }
      }
   }

   private void doRada() {
      this.hide();
      if (RadarScr.list == null || RadarScr.list.size() == 0) {
         Service.gI().SendRada(0, -1);
      }

      RadarScr.gI().switchToMe();
   }

   private void doFireTool() {
      if (this.selected >= 0) {
         if (SoundMn.IsDelAcc && this.selected == strTool.length - 1) {
            Service.gI().sendDelAcc();
         } else {
            if (!Char.myCharz().havePet) {
               switch (this.selected) {
                  case 0:
                     this.doRada();
                     return;
                  case 1:
                     Service.gI().openMenu(54);
                     return;
                  case 2:
                     this.setTypeGameInfo();
                     return;
                  case 3:
                     Service.gI().getFlag((byte)0, (byte)-1);
                     InfoDlg.showWait();
                     return;
                  case 4:
                     if (Char.myCharz().statusMe == 14) {
                        GameCanvas.startOKDlg(mResources.can_not_do_when_die);
                        return;
                     }

                     Service.gI().openUIZone();
                     return;
                  case 5:
                     GameCanvas.endDlg();
                     if (Char.myCharz().checkLuong() < 5) {
                        GameCanvas.startOKDlg(mResources.not_enough_luong_world_channel);
                        return;
                     }

                     if (this.chatTField == null) {
                        this.chatTField = new ChatTextField();
                        this.chatTField.tfChat.y = GameCanvas.h
                           - 35
                           - ChatTextField.gI().tfChat.height;
                        this.chatTField.initChatTextField();
                        this.chatTField.parentScreen = GameCanvas.panel;
                     }

                     this.chatTField.strChat = mResources.world_channel_5_luong;
                     this.chatTField.tfChat.name = mResources.CHAT;
                     this.chatTField.to = "";
                     this.chatTField.isShow = true;
                     this.chatTField.tfChat.setIputType(0);
                     if (GameCanvas.isTouch) {
                        this.chatTField.tfChat.doChangeToTextBox();
                        return;
                     }
                     break;
                  case 6:
                     this.setTypeAccount();
                     return;
                  case 7:
                     this.setTabOption();
                     return;
                  case 8:
                     LoginScr.backToRegister();
                     return;
                  case 9:
                     if (GameCanvas.loginScr.isLogin2) {
                        SoundMn.gI();
                        SoundMn.backToRegister();
                        return;
                     }
                     break;
                  default:
                     return;
               }
            } else {
               switch (this.selected) {
                  case 0:
                     this.doRada();
                     return;
                  case 1:
                     Service.gI().openMenu(54);
                     return;
                  case 2:
                     this.setTypeGameInfo();
                     return;
                  case 3:
                     Panel panel = this;
                     InfoDlg.showWait();
                     Service.gI().petInfo();
                     panel.timeShow = 20;
                     return;
                  case 4:
                     Service.gI().getFlag((byte)0, (byte)-1);
                     InfoDlg.showWait();
                     return;
                  case 5:
                     if (Char.myCharz().statusMe == 14) {
                        GameCanvas.startOKDlg(mResources.can_not_do_when_die);
                        return;
                     }

                     Service.gI().openUIZone();
                     return;
                  case 6:
                     GameCanvas.endDlg();
                     if (Char.myCharz().checkLuong() < 5) {
                        GameCanvas.startOKDlg(mResources.not_enough_luong_world_channel);
                        return;
                     }

                     if (this.chatTField == null) {
                        this.chatTField = new ChatTextField();
                        this.chatTField.tfChat.y = GameCanvas.h
                           - 35
                           - ChatTextField.gI().tfChat.height;
                        this.chatTField.initChatTextField();
                        this.chatTField.parentScreen = GameCanvas.panel;
                     }

                     this.chatTField.strChat = mResources.world_channel_5_luong;
                     this.chatTField.tfChat.name = mResources.CHAT;
                     this.chatTField.to = "";
                     this.chatTField.isShow = true;
                     this.chatTField.tfChat.setIputType(0);
                     if (GameCanvas.isTouch) {
                        this.chatTField.tfChat.doChangeToTextBox();
                        return;
                     }
                     break;
                  case 7:
                     this.setTypeAccount();
                     return;
                  case 8:
                     this.setTabOption();
                     return;
                  case 9:
                     LoginScr.backToRegister();
                     return;
                  case 10:
                     if (GameCanvas.loginScr.isLogin2) {
                        SoundMn.gI();
                        SoundMn.backToRegister();
                     }
               }
            }
         }
      }
   }

   private void setTypeGameInfo() {
      this.currentListLength = vGameInfo.size();
      this.ITEM_HEIGHT = 24;
      this.selected = GameCanvas.isTouch ? -1 : 0;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.type = 23;
      this.setType(0);
   }

   private void chagenSlogan() {
      this.chatTField.strChat = mResources.input_clan_slogan;
      this.chatTField.tfChat.name = mResources.input_clan_slogan;
      this.chatTField.to = "";
      this.chatTField.isShow = true;
      this.chatTField.tfChat.isFocus = true;
      this.chatTField.tfChat.setIputType(0);
      if (GameCanvas.isTouch) {
         this.chatTField.tfChat.doChangeToTextBox();
      }
   }

   public final void changeIcon() {
      if (this.tabIcon == null) {
         this.tabIcon = new TabClanIcon();
      }

      this.tabIcon.text = this.chatTField.tfChat.getText();
      this.tabIcon.show(false);
      this.chatTField.isShow = false;
   }

   private void addFriend(InfoItem info) {
      String chat = "|0|1|" + info.charInfo.cName;
      String text = chat + "\n";
      if (info.isOnline) {
         chat = text + "|4|1|" + mResources.is_online;
      } else {
         chat = text + "|3|1|" + mResources.is_offline;
      }

      chat = chat + "\n--";
      chat = chat + "\n|5|" + mResources.power + ": " + info.s;
      this.cp = new ChatPopup();
      this.popUpDetailInit(this.cp, chat);
      this.charInfo = info.charInfo;
      this.currItem = null;
   }

   private void doFireEnemy() {
      if (this.selected >= 0) {
         if (this.vEnemy.size() != 0) {
            MyVector menuItems = new MyVector("");
            this.currInfoItem = this.selected;
            menuItems.addElement(new Command(mResources.REVENGE, this, 10000, (InfoItem)this.vEnemy.elementAt(this.currInfoItem)));
            menuItems.addElement(new Command(mResources.DELETE, this, 10001, (InfoItem)this.vEnemy.elementAt(this.currInfoItem)));
            GameCanvas.menu
               .startAt(
                  menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
               );
            this.addFriend((InfoItem)this.vEnemy.elementAt(this.selected));
         }
      }
   }

   private void doFireFriend() {
      if (this.selected >= 0) {
         if (this.vFriend.size() != 0) {
            MyVector menuItems = new MyVector("");
            this.currInfoItem = this.selected;
            menuItems.addElement(new Command(mResources.CHAT, this, 8001, (InfoItem)this.vFriend.elementAt(this.currInfoItem)));
            menuItems.addElement(new Command(mResources.DELETE, this, 8002, (InfoItem)this.vFriend.elementAt(this.currInfoItem)));
            menuItems.addElement(new Command(mResources.den, this, 8004, (InfoItem)this.vFriend.elementAt(this.currInfoItem)));
            GameCanvas.menu
               .startAt(
                  menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
               );
            this.addFriend((InfoItem)this.vFriend.elementAt(this.selected));
         }
      }
   }

   private void doFireChangeFlag() {
      if (this.selected >= 0) {
         MyVector menuItems = new MyVector("");
         this.currInfoItem = this.selected;
         menuItems.addElement(new Command(mResources.change_flag, this, 10030, null));
         menuItems.addElement(new Command(mResources.BACK, this, 10031, null));
         GameCanvas.menu
            .startAt(
               menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
            );
      }
   }

   private void addSkillDetail2(int type) {
      String text = "";
      int value = 0;
      if (this.selected == 0) {
         value = Char.myCharz().cHPGoc + 1000;
      }

      if (this.selected == 1) {
         value = Char.myCharz().cMPGoc + 1000;
      }

      if (this.selected == 2) {
         value = Char.myCharz().cDamGoc * Char.myCharz().expForOneAdd;
      }

      if (this.selected == 3) {
         value = 500000 + Char.myCharz().cDefGoc * 100000;
      }

      String chat = text + "|5|2|" + mResources.USE + " " + value + " " + mResources.potential;
      if (type == 0) {
         chat = chat + "\n|5|2|" + mResources.to_gain_20hp;
      }

      if (type == 1) {
         chat = chat + "\n|5|2|" + mResources.to_gain_20mp;
      }

      if (type == 2) {
         chat = chat + "\n|5|2|" + mResources.to_gain_1pow;
      }

      if (type == 3) {
         chat = chat + "\n|5|2|" + mResources.to_gain_1pow;
      }

      this.currItem = null;
      this.partID = null;
      this.charInfo = null;
      this.idIcon = -1;
      this.cp = new ChatPopup();
      this.popUpDetailInit(this.cp, chat);
   }

   private void doFireZone() {
      if (this.selected != -1) {
         Res.outz("FIRE ZONE");
         this.isChangeZone = true;
         GameCanvas.panel.hide();
      }
   }

   public final void updateRequest(int recieve, int maxCap) {
      this.cp.says[this.cp.says.length - 1] = mResources.received + " " + recieve + "/" + maxCap;
   }

   private void doFireBox() {
      if (this.selected >= 0) {
         this.currItem = null;
         MyVector menuItems = new MyVector("");
         if (this.currentTabIndex == 0 && !this.equals(GameCanvas.panel2)) {
            if (this.selected == 0) {
               this.setNewSelected(Char.myCharz().arrItemBox.length, false, true);
            } else {
               byte itemBoxIndex = (byte)GetInventorySelect_body(this.selected, this.newSelected);
               Item currItemValue;
               if ((currItemValue = Char.myCharz().arrItemBox[itemBoxIndex]) != null) {
                  if (this.isBoxClan) {
                     menuItems.addElement(new Command(mResources.GETOUT, this, 1000, currItemValue));
                     menuItems.addElement(new Command(mResources.USE, this, 2010, currItemValue));
                  } else if (currItemValue.isTypeBody()) {
                     menuItems.addElement(new Command(mResources.GETOUT, this, 1000, currItemValue));
                  } else {
                     menuItems.addElement(new Command(mResources.GETOUT, this, 1000, currItemValue));
                  }

                  this.currItem = currItemValue;
               }
            }
         }

         if (this.currentTabIndex == 1 || this.equals(GameCanvas.panel2)) {
            if (this.selected == 0) {
               this.setNewSelected(Char.myCharz().arrItemBody.length + Char.myCharz().arrItemBag.length, true, false);
            } else {
               Item[] item = Char.myCharz().arrItemBody;
               if (!GetInventorySelect_isbody(this.selected, this.newSelected, item)) {
                  byte itemBagIndex = (byte)GetInventorySelect_bag(this.selected, this.newSelected, item);
                  Item currItemValue2;
                  if ((currItemValue2 = Char.myCharz().arrItemBag[itemBagIndex]) != null) {
                     menuItems.addElement(new Command(mResources.move_to_chest, this, 1001, currItemValue2));
                     if (currItemValue2.isTypeBody()) {
                        menuItems.addElement(new Command(mResources.USE, this, 2000, currItemValue2));
                     } else {
                        menuItems.addElement(new Command(mResources.USE, this, 2001, currItemValue2));
                     }

                     this.currItem = currItemValue2;
                  }
               } else {
                  Item currItemValue3;
                  if ((currItemValue3 = Char.myCharz().arrItemBody[GetInventorySelect_body(this.selected, this.newSelected)]) != null) {
                     menuItems.addElement(new Command(mResources.move_to_chest2, this, 1002, currItemValue3));
                     this.currItem = currItemValue3;
                  }
               }
            }
         }

         if (this.currItem != null) {
            Char.myCharz()
               .setPartTemp(
                  this.currItem.headTemp,
                  this.currItem.bodyTemp,
                  this.currItem.legTemp,
                  this.currItem.bagTemp
               );
            if (this.isBoxClan) {
               menuItems.addElement(new Command(mResources.MOVEOUT, this, 2011, this.currItem));
            }

            GameCanvas.menu
               .startAt(
                  menuItems, this.X, (this.selected + 1) * this.ITEM_HEIGHT - this.cmy + this.yScroll
               );
            this.addItemDetail(this.currItem);
         } else {
            this.cp = null;
         }

         this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      }
   }

   public final void itemRequest(byte itemAction, String info, byte where, byte index) {
      GameCanvas.endDlg();
      ItemObject itemObject;
      (itemObject = new ItemObject()).type = itemAction;
      itemObject.id = index;
      itemObject.where = where;
      GameCanvas.startYesNoDlg(
         info, new Command(mResources.YES, this, 2004, itemObject), new Command(mResources.NO, this, 4005, null)
      );
   }

   public final void saleRequest(byte type, String info, short id) {
      ItemObject itemObject;
      (itemObject = new ItemObject()).type = type;
      itemObject.id = id;
      GameCanvas.startYesNoDlg(
         info, new Command(mResources.YES, this, 3003, itemObject), new Command(mResources.NO, this, 4005, null)
      );
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 9999) {
         TopInfo p2 = (TopInfo)p;
         Service.gI().sendThachDau(p2.pId);
      }

      if (idAction == 170391) {
         Rms.clearAll();
         if (mGraphics.zoomLevel > 1) {
            Rms.saveRMSInt("levelScreenKN", 1);
         } else {
            Rms.saveRMSInt("levelScreenKN", 0);
         }

         GameMidlet.instance.exit();
      }

      if (idAction == 6001) {
         Item p3;
         (p3 = (Item)p).isSelect = false;
         GameCanvas.panel.vItemCombine.removeElement(p3);
         if (GameCanvas.panel.currentTabIndex == 0) {
            GameCanvas.panel.setTabCombine();
         }
      }

      if (idAction == 6000) {
         Item entry = (Item)p;

         for (int itemCombineIndex = 0; itemCombineIndex < GameCanvas.panel.vItemCombine.size(); itemCombineIndex++) {
            if (((Item)GameCanvas.panel.vItemCombine.elementAt(itemCombineIndex)).template.id
               == entry.template.id) {
               GameCanvas.startOKDlg(mResources.already_has_item);
               return;
            }
         }

         entry.isSelect = true;
         GameCanvas.panel.vItemCombine.addElement(entry);
         if (GameCanvas.panel.currentTabIndex == 0) {
            GameCanvas.panel.setTabCombine();
         }
      }

      if (idAction == 7000) {
         if (this.isLock) {
            GameCanvas.startOKDlg(mResources.unlock_item_to_trade);
            return;
         }

         Item p4 = (Item)p;

         for (int myGDIndex = 0; myGDIndex < GameCanvas.panel.vMyGD.size(); myGDIndex++) {
            if (((Item)GameCanvas.panel.vMyGD.elementAt(myGDIndex)).indexUI == p4.indexUI) {
               GameCanvas.startOKDlg(mResources.already_has_item);
               return;
            }
         }

         if (p4.quantity > 1) {
            Panel panel = this;
            if (this.chatTField == null) {
               panel.chatTField = new ChatTextField();
               panel.chatTField.tfChat.y = GameCanvas.h
                  - 35
                  - ChatTextField.gI().tfChat.height;
               panel.chatTField.initChatTextField();
               panel.chatTField.parentScreen = GameCanvas.panel;
            }

            panel.chatTField.strChat = mResources.input_quantity_to_trade;
            panel.chatTField.tfChat.name = mResources.input_quantity;
            panel.chatTField.to = "";
            panel.chatTField.isShow = true;
            panel.chatTField.tfChat.setIputType(1);
            if (GameCanvas.isTouch) {
               panel.chatTField.tfChat.doChangeToTextBox();
            }

            return;
         }

         p4.isSelect = true;
         Item entry2;
         (entry2 = new Item()).template = p4.template;
         entry2.itemOption = p4.itemOption;
         entry2.indexUI = p4.indexUI;
         GameCanvas.panel.vMyGD.addElement(entry2);
         Service.gI().giaodich((byte)2, -1, (byte)entry2.indexUI, entry2.quantity);
      }

      if (idAction == 7001) {
         Item p5;
         (p5 = (Item)p).isSelect = false;
         GameCanvas.panel.vMyGD.removeElement(p5);
         if (GameCanvas.panel.currentTabIndex == 1) {
            GameCanvas.panel.setTabGiaoDich(true);
         }

         Service.gI().giaodich((byte)4, -1, (byte)p5.indexUI, -1);
      }

      if (idAction == 7002) {
         this.isAccept = true;
         GameCanvas.endDlg();
         Service.gI().giaodich((byte)7, -1, (byte)-1, -1);
         this.hide();
      }

      if (idAction == 8003) {
         InfoItem p6 = (InfoItem)p;
         Service.gI().friend((byte)1, p6.charInfo.charID);
      }

      if (idAction == 8002) {
         InfoItem p7 = (InfoItem)p;
         Service.gI().friend((byte)2, p7.charInfo.charID);
      }

      if (idAction == 8004) {
         InfoItem p8 = (InfoItem)p;
         Service.gI().gotoPlayer(p8.charInfo.charID);
      }

      if (idAction == 8001) {
         Res.outz("chat player");
         InfoItem p9 = (InfoItem)p;
         if (this.chatTField == null) {
            this.chatTField = new ChatTextField();
            this.chatTField.tfChat.y = GameCanvas.h
               - 35
               - ChatTextField.gI().tfChat.height;
            this.chatTField.initChatTextField();
            this.chatTField.parentScreen = GameCanvas.panel;
         }

         this.chatTField.strChat = mResources.chat_player;
         this.chatTField.tfChat.name = mResources.chat_with + " " + p9.charInfo.cName;
         this.chatTField.to = "";
         this.chatTField.isShow = true;
         this.chatTField.tfChat.isFocus = true;
         this.chatTField.tfChat.setIputType(0);
         if (GameCanvas.isTouch) {
            this.chatTField.tfChat.doChangeToTextBox();
         }
      }

      if (idAction == 1000) {
         Service.gI().getItem((byte)0, (byte)GetInventorySelect_body(this.selected, this.newSelected));
      }

      if (idAction == 1001) {
         byte id = (byte)GetInventorySelect_bag(this.selected, this.newSelected, Char.myCharz().arrItemBody);
         Service.gI().getItem(BAG_BOX, id);
      }

      if (idAction == 1003) {
         this.hide();
      }

      if (idAction == 1002) {
         Service.gI().getItem(BODY_BOX, (byte)GetInventorySelect_body(this.selected, this.newSelected));
      }

      if (idAction == 2011) {
         Service.gI().useItem((byte)1, (byte)2, (byte)GetInventorySelect_body(this.selected, this.newSelected), (short)-1);
      }

      if (idAction == 2010) {
         Service.gI().useItem((byte)0, (byte)2, (byte)GetInventorySelect_body(this.selected, this.newSelected), (short)-1);
         Item p10;
         if ((p10 = (Item)p) != null && (p10.template.id == 193 || p10.template.id == 194)) {
            GameCanvas.panel.hide();
         }
      }

      if (idAction == 2000) {
         Item[] item = Char.myCharz().arrItemBody;
         byte id2 = (byte)GetInventorySelect_bag(this.selected, this.newSelected, item);
         Service.gI().getItem(BAG_BODY, id2);
      }

      if (idAction == 2001) {
         Res.outz("use item");
         Item p11 = (Item)p;
         byte index;
         boolean condition;
         if (!(condition = GetInventorySelect_isbody(this.selected, this.newSelected, Char.myCharz().arrItemBody))) {
            index = (byte)GetInventorySelect_bag(this.selected, this.newSelected, Char.myCharz().arrItemBody);
         } else {
            index = (byte)GetInventorySelect_body(this.selected, this.newSelected);
         }

         Service.gI().useItem((byte)0, (byte)(condition ? 0 : 1), index, (short)-1);
         if (p11.template.id == 193 || p11.template.id == 194) {
            GameCanvas.panel.hide();
         }
      }

      if (idAction == 2002) {
         Service.gI().getItem(BODY_BAG, (byte)GetInventorySelect_body(this.selected, this.newSelected));
      }

      if (idAction == 2003) {
         Res.outz("remove item");
         boolean condition2;
         byte index2;
         if (!(condition2 = GetInventorySelect_isbody(this.selected, this.newSelected, Char.myCharz().arrItemBody))) {
            index2 = (byte)GetInventorySelect_bag(this.selected, this.newSelected, Char.myCharz().arrItemBody);
         } else {
            index2 = (byte)GetInventorySelect_body(this.selected, this.newSelected);
         }

         Service.gI().useItem((byte)1, (byte)(condition2 ? 0 : 1), index2, (short)-1);
      }

      if (idAction == 2004) {
         GameCanvas.endDlg();
         ItemObject p12;
         byte where = (byte)(p12 = (ItemObject)p).where;
         byte index3 = (byte)p12.id;
         Service.gI().useItem((byte)(p12.type == 0 ? 3 : 2), where, index3, (short)-1);
      }

      if (idAction == 2005) {
         byte id3 = (byte)GetInventorySelect_bag(this.selected, this.newSelected, Char.myCharz().arrItemBody);
         Service.gI().getItem(BAG_PET, id3);
      }

      if (idAction == 2006) {
         byte id4 = (byte)this.selected;
         Service.gI().getItem(PET_BAG, id4);
      }

      if (idAction == 30001) {
         Res.outz("nhan do");
         Service.gI().buyItem((byte)0, this.selected, 0);
      }

      if (idAction == 30002) {
         Res.outz("xoa do");
         Service.gI().buyItem((byte)1, this.selected, 0);
      }

      if (idAction == 30003) {
         Res.outz("nhan tat");
         Service.gI().buyItem((byte)2, this.selected, 0);
      }

      if (idAction == 3000) {
         Res.outz("mua do");
         Item p13 = (Item)p;
         Service.gI().buyItem((byte)0, p13.template.id, 0);
      }

      if (idAction == 3001) {
         Item p14 = (Item)p;
         GameCanvas.msgdlg.pleasewait();
         Service.gI().buyItem((byte)1, p14.template.id, 0);
      }

      if (idAction == 3002) {
         GameCanvas.endDlg();
         boolean condition3;
         byte id5;
         if (!(condition3 = GetInventorySelect_isbody(this.selected, this.newSelected, Char.myCharz().arrItemBody))) {
            id5 = (byte)GetInventorySelect_bag(this.selected, this.newSelected, Char.myCharz().arrItemBody);
         } else {
            id5 = (byte)GetInventorySelect_body(this.selected, this.newSelected);
         }

         Service.gI().saleItem((byte)0, (byte)(condition3 ? 0 : 1), id5);
      }

      if (idAction == 3003) {
         GameCanvas.endDlg();
         ItemObject p15 = (ItemObject)p;
         Service.gI().saleItem((byte)1, (byte)p15.type, (short)p15.id);
      }

      if (idAction == 3004) {
         Item p16 = (Item)p;
         Service.gI().buyItem((byte)3, p16.template.id, 0);
      }

      if (idAction == 3005) {
         Res.outz("mua do");
         Item p17 = (Item)p;
         Service.gI().buyItem((byte)3, p17.template.id, 0);
      }

      Clan p18;
      if (idAction == 4000 && (p18 = (Clan)p) != null) {
         GameCanvas.endDlg();
         Service.gI().clanMessage(2, null, p18.ID);
      }

      if (idAction == 4001 && (p18 = (Clan)p) != null) {
         InfoDlg.showWait();
         this.clanReport = mResources.PLEASEWAIT;
         Service.gI().clanMember(p18.ID);
      }

      if (idAction == 4005) {
         GameCanvas.endDlg();
      }

      if (idAction == 4007) {
         GameCanvas.endDlg();
      }

      if (idAction == 4006) {
         ClanMessage p19 = (ClanMessage)p;
         Service.gI().clanDonate(p19.id);
      }

      if (idAction == 5001) {
         Member p20 = (Member)p;
         Service.gI().clanRemote(p20.ID, (byte)0);
      }

      if (idAction == 5002) {
         Member p21 = (Member)p;
         Service.gI().clanRemote(p21.ID, (byte)1);
      }

      if (idAction == 5003) {
         Member p22 = (Member)p;
         Service.gI().clanRemote(p22.ID, (byte)2);
      }

      if (idAction == 5004) {
         Member p23 = (Member)p;
         Service.gI().clanRemote(p23.ID, (byte)-1);
      }

      if (idAction == 9000) {
         Service.gI().upPotential(this.selected, 1);
         GameCanvas.endDlg();
         InfoDlg.showWait();
      }

      if (idAction == 9006) {
         Service.gI().upPotential(this.selected, 10);
         GameCanvas.endDlg();
         InfoDlg.showWait();
      }

      if (idAction == 9007) {
         Service.gI().upPotential(this.selected, 100);
         GameCanvas.endDlg();
         InfoDlg.showWait();
      }

      if (idAction == 9002) {
         Skill p24;
         if ((p24 = (Skill)p).template.isSkillSpec()) {
            GameCanvas.startOKDlg(mResources.updSkill);
         } else {
            GameCanvas.startOKDlg(
               mResources.can_buy_from_Uron1 + p24.powRequire + mResources.can_buy_from_Uron2 + p24.moreInfo + mResources.can_buy_from_Uron3
            );
         }
      }

      if (idAction == 9003) {
         if (GameCanvas.isTouch) {
            GameScr.gI();
            GameScr.doSetOnScreenSkill((SkillTemplate)p);
         } else {
            GameScr.gI();
            GameScr.doSetKeySkill((SkillTemplate)p);
         }
      }

      if (idAction == 9004) {
         Skill p25;
         if ((p25 = (Skill)p).template.isSkillSpec()) {
            GameCanvas.startOKDlg(mResources.learnSkill);
         } else {
            GameCanvas.startOKDlg(
               mResources.can_buy_from_Uron1 + p25.powRequire + mResources.can_buy_from_Uron2 + p25.moreInfo + mResources.can_buy_from_Uron3
            );
         }
      }

      if (idAction == 10000) {
         InfoItem p26 = (InfoItem)p;
         Service.gI().enemy((byte)1, p26.charInfo.charID);
         GameCanvas.panel.hideNow();
      }

      if (idAction == 10001) {
         InfoItem p27 = (InfoItem)p;
         Service.gI().enemy((byte)2, p27.charInfo.charID);
         InfoDlg.showWait();
      }

      if (idAction == 10012) {
         if (this.chatTField == null) {
            this.chatTField = new ChatTextField();
            this.chatTField.tfChat.y = GameCanvas.h
               - 35
               - ChatTextField.gI().tfChat.height;
            this.chatTField.initChatTextField();
            this.chatTField.parentScreen = GameCanvas.panel2 == null ? GameCanvas.panel : GameCanvas.panel2;
         }

         this.chatTField.tfChat.setIputType(1);
         this.chatTField.tfChat.setText("");
         if (this.currItem.quantity == 1) {
            this.chatTField.strChat = mResources.kiguiXuchat;
            this.chatTField.tfChat.name = mResources.input_money;
         } else {
            this.chatTField.strChat = mResources.input_quantity + " ";
            this.chatTField.tfChat.name = mResources.input_quantity;
         }

         this.chatTField.tfChat.setMaxTextLenght(10);
         this.chatTField.to = "";
         this.chatTField.isShow = true;
         this.chatTField.tfChat.setIputType(1);
         if (GameCanvas.isTouch) {
            this.chatTField.tfChat.doChangeToTextBox();
         }
      }

      if (idAction == 10013) {
         if (this.chatTField == null) {
            this.chatTField = new ChatTextField();
            this.chatTField.tfChat.y = GameCanvas.h
               - 35
               - ChatTextField.gI().tfChat.height;
            this.chatTField.initChatTextField();
            this.chatTField.parentScreen = GameCanvas.panel2 == null ? GameCanvas.panel : GameCanvas.panel2;
         }

         this.chatTField.tfChat.setIputType(1);
         this.chatTField.tfChat.setText("");
         if (this.currItem.quantity == 1) {
            this.chatTField.strChat = mResources.kiguiLuongchat;
            this.chatTField.tfChat.name = mResources.input_money;
         } else {
            this.chatTField.strChat = mResources.input_quantity + "  ";
            this.chatTField.tfChat.name = mResources.input_quantity;
         }

         this.chatTField.to = "";
         this.chatTField.isShow = true;
         this.chatTField.tfChat.setIputType(1);
         if (GameCanvas.isTouch) {
            this.chatTField.tfChat.doChangeToTextBox();
         }
      }

      if (idAction == 10014) {
         Item p28 = (Item)p;
         Service.gI().kigui((byte)1, p28.itemId, (byte)-1, -1, -1);
         InfoDlg.showWait();
      }

      if (idAction == 10015) {
         Item p29 = (Item)p;
         Service.gI().kigui((byte)2, p29.itemId, (byte)-1, -1, -1);
         InfoDlg.showWait();
      }

      if (idAction == 10016) {
         Item p30 = (Item)p;
         Service.gI().kigui((byte)3, p30.itemId, (byte)0, p30.buyCoin, -1);
         InfoDlg.showWait();
      }

      if (idAction == 10017) {
         Item p31 = (Item)p;
         Service.gI().kigui((byte)3, p31.itemId, (byte)1, p31.buyGold, -1);
         InfoDlg.showWait();
      }

      if (idAction == 10018) {
         Item p32 = (Item)p;
         Service.gI().kigui((byte)5, p32.itemId, (byte)-1, -1, -1);
         InfoDlg.showWait();
      }

      if (idAction == 10019) {
         Session_ME.gI().close();
         Rms.saveRMSString(Rms.RMS_acc, "");
         Rms.saveRMSString(Rms.RMS_pass, "");
         GameCanvas.loginScr.tfPass.setText("");
         GameCanvas.loginScr.tfUser.setText("");
         GameCanvas.loginScr.isLogin2 = false;
         GameCanvas.serverScreen.switchToMe();
         GameCanvas.endDlg();
         this.hide();
      }

      if (idAction == 10020) {
         GameCanvas.endDlg();
      }

      if (idAction == 10030) {
         Service.gI().getFlag((byte)1, (byte)this.selected);
         GameCanvas.panel.hideNow();
      }

      if (idAction == 10031) {
         Session_ME.gI().close();
      }

      if (idAction == 11000) {
         Service.gI().kigui((byte)0, this.currItem.itemId, (byte)1, this.currItem.buyRuby, 1);
         GameCanvas.endDlg();
      }

      if (idAction == 11001) {
         Service.gI()
            .kigui((byte)0, this.currItem.itemId, (byte)1, this.currItem.buyRuby, this.currItem.quantilyToBuy);
         GameCanvas.endDlg();
      }

      if (idAction == 11002) {
         this.chatTField.isShow = false;
         GameCanvas.endDlg();
      }
   }

   public final void onChatFromMe(String text, String to) {
      if (this.chatTField.tfChat.getText() == null
         || this.chatTField.tfChat.getText().equals("")
         || text.equals("")
         || text == null) {
         this.chatTField.isShow = false;
      } else if (this.chatTField.strChat.equals(mResources.input_clan_name)) {
         InfoDlg.showWait();
         this.chatTField.isShow = false;
         Service.gI().searchClan(text);
      } else if (this.chatTField.strChat.equals(mResources.chat_clan)) {
         InfoDlg.showWait();
         this.chatTField.isShow = false;
         Service.gI().clanMessage(0, text, -1);
      } else if (this.chatTField.strChat.equals(mResources.input_clan_name_to_create)) {
         if (this.chatTField.tfChat.getText() == "") {
            GameScr.info1.addInfo(mResources.clan_name_blank, 0);
         } else {
            if (this.tabIcon == null) {
               this.tabIcon = new TabClanIcon();
            }

            this.tabIcon.text = this.chatTField.tfChat.getText();
            this.tabIcon.show(false);
            this.chatTField.isShow = false;
         }
      } else if (this.chatTField.strChat.equals(mResources.input_clan_slogan)) {
         if (this.chatTField.tfChat.getText() == "") {
            GameScr.info1.addInfo(mResources.clan_slogan_blank, 0);
         } else {
            Service.gI()
               .getClan(
                  (byte)4, Char.myCharz().clan.imgID, this.chatTField.tfChat.getText()
               );
            this.chatTField.isShow = false;
         }
      } else if (this.chatTField.strChat.equals(mResources.input_Inventory_Pass)) {
         try {
            int pass = Integer.parseInt(this.chatTField.tfChat.getText());
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
            this.hide();
            if (this.chatTField.tfChat.getText().length() == 6
               && !this.chatTField.tfChat.getText().equals("")) {
               Service.gI().setLockInventory(pass);
               this.chatTField.isShow = false;
               this.chatTField.tfChat.setIputType(0);
               this.hide();
            } else {
               GameCanvas.startOKDlg(mResources.input_Inventory_Pass_wrong);
            }
         } catch (Exception ignoredException) {
            GameCanvas.startOKDlg(mResources.ALERT_PRIVATE_PASS_2);
         }
      } else if (this.chatTField.strChat.equals(mResources.world_channel_5_luong)) {
         if (!this.chatTField.tfChat.getText().equals("")) {
            Service.gI().chatGlobal(this.chatTField.tfChat.getText());
            this.chatTField.isShow = false;
            this.hide();
         }
      } else if (this.chatTField.strChat.equals(mResources.chat_player)) {
         this.chatTField.isShow = false;
         InfoItem friend = null;
         if (this.type == 8) {
            friend = (InfoItem)this.logChat.elementAt(this.currInfoItem);
         } else if (this.type == 11) {
            friend = (InfoItem)this.vFriend.elementAt(this.currInfoItem);
         }

         if (friend.charInfo.charID != Char.myCharz().charID) {
            Service.gI().chatPlayer(text, friend.charInfo.charID);
         }
      } else if (this.chatTField.strChat.equals(mResources.input_quantity_to_trade)) {
         int quantity;
         try {
            quantity = Integer.parseInt(this.chatTField.tfChat.getText());
         } catch (Exception ignoredException2) {
            GameCanvas.startOKDlg(mResources.input_quantity_wrong);
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
            return;
         }

         if (quantity > 0 && quantity <= this.currItem.quantity) {
            this.currItem.isSelect = true;
            Item entry;
            (entry = new Item()).template = this.currItem.template;
            entry.quantity = quantity;
            entry.indexUI = this.currItem.indexUI;
            entry.itemOption = this.currItem.itemOption;
            GameCanvas.panel.vMyGD.addElement(entry);
            Service.gI().giaodich((byte)2, -1, (byte)entry.indexUI, entry.quantity);
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
         } else {
            GameCanvas.startOKDlg(mResources.input_quantity_wrong);
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
         }
      } else if (this.chatTField.strChat == mResources.input_money_to_trade) {
         int moneyGDValue;
         try {
            moneyGDValue = Integer.parseInt(this.chatTField.tfChat.getText());
         } catch (Exception ignoredException3) {
            GameCanvas.startOKDlg(mResources.input_money_wrong);
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
            return;
         }

         if (moneyGDValue > Char.myCharz().xu) {
            GameCanvas.startOKDlg(mResources.not_enough_money);
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
         } else {
            this.moneyGD = moneyGDValue;
            Service.gI().giaodich((byte)2, -1, (byte)-1, moneyGDValue);
            this.chatTField.isShow = false;
            this.chatTField.tfChat.setIputType(0);
         }
      } else if (this.chatTField.strChat.equals(mResources.kiguiXuchat)) {
         try {
            Service.gI()
               .kigui(
                  (byte)0, this.currItem.itemId, (byte)0, Integer.parseInt(this.chatTField.tfChat.getText()), 1
               );
         } catch (Exception ignoredException4) {
            GameCanvas.startOKDlg(mResources.input_money_wrong);
         }

         this.chatTField.isShow = false;
      } else if (this.chatTField.strChat.equals(mResources.kiguiXuchat + " ")) {
         try {
            Service.gI()
               .kigui(
                  (byte)0,
                  this.currItem.itemId,
                  (byte)0,
                  Integer.parseInt(this.chatTField.tfChat.getText()),
                  this.currItem.quantilyToBuy
               );
         } catch (Exception ignoredException5) {
            GameCanvas.startOKDlg(mResources.input_money_wrong);
         }

         this.chatTField.isShow = false;
      } else if (this.chatTField.strChat.equals(mResources.kiguiLuongchat)) {
         this.doNotiRuby(0);
         this.chatTField.isShow = false;
      } else if (this.chatTField.strChat.equals(mResources.kiguiLuongchat + "  ")) {
         this.doNotiRuby(1);
         this.chatTField.isShow = false;
      } else if (this.chatTField.strChat.equals(mResources.input_quantity + " ")) {
         this.currItem.quantilyToBuy = Integer.parseInt(this.chatTField.tfChat.getText());
         if (this.currItem.quantilyToBuy > this.currItem.quantity) {
            GameCanvas.startOKDlg(mResources.input_quantity_wrong);
         } else {
            this.isKiguiXu = true;
            this.chatTField.isShow = false;
         }
      } else {
         if (this.chatTField.strChat.equals(mResources.input_quantity + "  ")) {
            this.currItem.quantilyToBuy = Integer.parseInt(this.chatTField.tfChat.getText());
            if (this.currItem.quantilyToBuy > this.currItem.quantity) {
               GameCanvas.startOKDlg(mResources.input_quantity_wrong);
               return;
            }

            this.isKiguiLuong = true;
            this.chatTField.isShow = false;
         }
      }
   }

   public final void onCancelChat() {
      this.chatTField.tfChat.setIputType(0);
   }

   public final void setCombineEff(int type) {
      this.typeCombine = type;
      this.rS = 90;
      if (this.typeCombine == 0) {
         this.iDotS = 5;
         this.angleS = this.angleO = 90;
         this.time = 2;

         for (int itemCombineIndex = 0; itemCombineIndex < this.vItemCombine.size(); itemCombineIndex++) {
            Item itemCombine;
            if ((itemCombine = (Item)this.vItemCombine.elementAt(itemCombineIndex)) != null) {
               if (itemCombine.template.type == 14) {
                  this.iconID2 = itemCombine.template.iconID;
               } else {
                  this.iconID1 = itemCombine.template.iconID;
               }
            }
         }
      } else if (this.typeCombine == 1) {
         this.iDotS = 2;
         this.angleS = this.angleO = 0;
         this.time = 1;

         for (int itemCombineIndex2 = 0; itemCombineIndex2 < this.vItemCombine.size(); itemCombineIndex2++) {
            Item itemCombine2;
            if ((itemCombine2 = (Item)this.vItemCombine.elementAt(itemCombineIndex2)) != null) {
               if (itemCombineIndex2 == 0) {
                  this.iconID1 = itemCombine2.template.iconID;
               } else {
                  this.iconID2 = itemCombine2.template.iconID;
               }
            }
         }
      } else if (this.typeCombine == 2) {
         this.iDotS = 7;
         this.angleS = this.angleO = 25;
         this.time = 1;

         for (int itemCombineIndex3 = 0; itemCombineIndex3 < this.vItemCombine.size(); itemCombineIndex3++) {
            Item itemCombine3;
            if ((itemCombine3 = (Item)this.vItemCombine.elementAt(itemCombineIndex3)) != null) {
               this.iconID1 = itemCombine3.template.iconID;
            }
         }
      } else if (this.typeCombine == 3) {
         this.xS = GameCanvas.hw;
         this.yS = GameCanvas.hh;
         this.iDotS = 1;
         this.angleS = this.angleO = 1;
         this.time = 4;

         for (int itemCombineIndex4 = 0; itemCombineIndex4 < this.vItemCombine.size(); itemCombineIndex4++) {
            Item itemCombine4;
            if ((itemCombine4 = (Item)this.vItemCombine.elementAt(itemCombineIndex4)) != null) {
               this.iconID1 = itemCombine4.template.iconID;
            }
         }
      } else if (this.typeCombine == 4) {
         this.iDotS = this.vItemCombine.size();
         this.iconID = new short[this.iDotS];
         this.angleS = this.angleO = 25;
         this.time = 1;

         for (int iconIDIndex = 0; iconIDIndex < this.vItemCombine.size(); iconIDIndex++) {
            Item itemCombine5;
            if ((itemCombine5 = (Item)this.vItemCombine.elementAt(iconIDIndex)) != null) {
               this.iconID[iconIDIndex] = itemCombine5.template.iconID;
            }
         }
      }

      this.speed = 1;
      this.isSpeedCombine = true;
      this.isDoneCombine = false;
      this.isCompleteEffCombine = false;
      this.iAngleS = 360 / this.iDotS;
      this.xArgS = new int[this.iDotS];
      this.yArgS = new int[this.iDotS];
      this.xDotS = new int[this.iDotS];
      this.yDotS = new int[this.iDotS];
      this.setDotStar();
      this.isPaintCombine = true;
      this.countUpdate = 10;
      this.countR = 30;
      this.countWait = 10;
      this.addTextCombineNPC(this.idNPC, mResources.combineSpell);
   }

   private void updateCombineEff() {
      this.countUpdate--;
      if (this.countUpdate < 0) {
         this.countUpdate = 0;
      }

      this.countR--;
      if (this.countR < 0) {
         this.countR = 0;
      }

      if (this.countUpdate == 0) {
         if (!this.isCompleteEffCombine) {
            if (this.time > 0) {
               if (this.combineSuccess != -1) {
                  if (this.typeCombine == 3) {
                     if (GameCanvas.gameTick % 10 == 0) {
                        EffecMn.addEff(new Effect(21, this.xS - 10, this.yS + 25, 4, 1, 1));
                        this.time--;
                     }
                  } else {
                     if (GameCanvas.gameTick % 2 == 0) {
                        if (this.isSpeedCombine) {
                           if (this.speed < 40) {
                              this.speed += 2;
                           }
                        } else if (this.speed > 10) {
                           this.speed -= 2;
                        }
                     }

                     if (this.countR == 0) {
                        if (this.isSpeedCombine) {
                           if (this.rS > 0) {
                              this.rS -= 5;
                           } else if (GameCanvas.gameTick % 10 == 0) {
                              this.isSpeedCombine = false;
                              this.time--;
                              this.countR = 5;
                              this.countWait = 10;
                           }
                        } else if (this.rS < 90) {
                           this.rS += 5;
                        } else if (GameCanvas.gameTick % 10 == 0) {
                           this.isSpeedCombine = true;
                           this.countR = 10;
                        }
                     }

                     this.angleS = this.angleO;
                     this.angleS = this.angleS - this.speed;
                     if (this.angleS >= 360) {
                        this.angleS -= 360;
                     }

                     if (this.angleS < 0) {
                        this.angleS += 360;
                     }

                     this.angleO = this.angleS;
                     this.setDotStar();
                  }
               }
            } else if (GameCanvas.gameTick % 20 == 0) {
               this.isCompleteEffCombine = true;
            }

            if (GameCanvas.gameTick % 20 == 0) {
               if (this.typeCombine != 3) {
                  EffectPanel.addServerEffect(132, this.xS, this.yS, 2);
               }

               EffectPanel.addServerEffect(114, this.xS, this.yS + 20, 2);
               return;
            }
         } else if (this.isCompleteEffCombine) {
            if (this.combineSuccess == 1) {
               if (this.countWait == 10) {
                  EffecMn.addEff(new Effect(22, this.xS - 3, this.yS + 25, 4, 1, 1));
               }

               this.countWait--;
               if (this.countWait < 0) {
                  this.countWait = 0;
               }

               if (this.rS < 300) {
                  this.rS = Res.abs(this.rS + 10);
                  if (this.rS == 20) {
                     this.addTextCombineNPC(this.idNPC, mResources.combineFail);
                  }
               } else if (GameCanvas.gameTick % 20 == 0) {
                  if (GameCanvas.w > 2 * WIDTH_PANEL) {
                     (GameCanvas.panel2 = new Panel()).tabName[7] = new String[][]{{""}};
                     GameCanvas.panel2.setTypeBodyOnly();
                     GameCanvas.panel2.show();
                  }

                  this.combineSuccess = -1;
                  this.isDoneCombine = true;
                  if (this.typeCombine == 4) {
                     GameCanvas.panel.hideNow();
                  }
               }

               this.setDotStar();
               return;
            }

            if (this.combineSuccess == 0) {
               if (this.countWait == 10) {
                  if (this.typeCombine == 2) {
                     EffecMn.addEff(new Effect(20, this.xS - 3, this.yS + 15, 4, 2, 1));
                  } else {
                     EffecMn.addEff(new Effect(21, this.xS - 10, this.yS + 25, 4, 1, 1));
                  }

                  this.addTextCombineNPC(this.idNPC, mResources.combineSuccess);
                  this.isPaintCombine = false;
               }

               if (!this.isPaintCombine) {
                  this.countWait--;
                  if (this.countWait < -50) {
                     this.countWait = -50;
                     if (this.typeCombine < 3 && GameCanvas.w > 2 * WIDTH_PANEL) {
                        (GameCanvas.panel2 = new Panel()).tabName[7] = new String[][]{{""}};
                        GameCanvas.panel2.setTypeBodyOnly();
                        GameCanvas.panel2.show();
                     }

                     this.combineSuccess = -1;
                     this.isDoneCombine = true;
                     if (this.typeCombine == 4) {
                        GameCanvas.panel.hideNow();
                     }
                  }
               }
            }
         }
      }
   }

   private void setDotStar() {
      for (int xArgSIndex = 0; xArgSIndex < this.yArgS.length; xArgSIndex++) {
         if (this.angleS >= 360) {
            this.angleS -= 360;
         }

         if (this.angleS < 0) {
            this.angleS += 360;
         }

         this.yArgS[xArgSIndex] = Res.abs(this.rS * Res.sin(this.angleS) / 1024);
         this.xArgS[xArgSIndex] = Res.abs(this.rS * Res.cos(this.angleS) / 1024);
         if (this.angleS < 90) {
            this.xDotS[xArgSIndex] = this.xS + this.xArgS[xArgSIndex];
            this.yDotS[xArgSIndex] = this.yS - this.yArgS[xArgSIndex];
         } else if (this.angleS >= 90 && this.angleS < 180) {
            this.xDotS[xArgSIndex] = this.xS - this.xArgS[xArgSIndex];
            this.yDotS[xArgSIndex] = this.yS - this.yArgS[xArgSIndex];
         } else if (this.angleS >= 180 && this.angleS < 270) {
            this.xDotS[xArgSIndex] = this.xS - this.xArgS[xArgSIndex];
            this.yDotS[xArgSIndex] = this.yS + this.yArgS[xArgSIndex];
         } else {
            this.xDotS[xArgSIndex] = this.xS + this.xArgS[xArgSIndex];
            this.yDotS[xArgSIndex] = this.yS + this.yArgS[xArgSIndex];
         }

         this.angleS = this.angleS - this.iAngleS;
      }
   }

   private void addTextCombineNPC(int idNPC, String text) {
      if (this.typeCombine < 3) {
         for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
            Npc npc;
            if ((npc = (Npc)GameScr.vNpc.elementAt(npcIndex)).template.npcTemplateId == idNPC) {
               npc.addInfo(text);
            }
         }
      }
   }

   private void setTabOption() {
      this.type = 19;
      this.setType(0);
      Panel panel = this;
      SoundMn.gI();
      SoundMn.getStrOption();
      panel.currentListLength = strCauhinh.length;
      panel.ITEM_HEIGHT = 24;
      panel.selected = GameCanvas.isTouch ? -1 : 0;
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      if (panel.cmyLim < 0) {
         panel.cmyLim = 0;
      }

      panel.cmy = panel.cmtoY = panel.cmyLast[panel.currentTabIndex];
      if (panel.cmy < 0) {
         panel.cmy = panel.cmtoY = 0;
      }

      if (panel.cmy > panel.cmyLim) {
         panel.cmy = panel.cmtoY = panel.cmyLim;
      }

      this.cmx = this.cmtoX = 0;
   }

   private void paintOption(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      for (int strCauhinhIndex = 0; strCauhinhIndex < strCauhinh.length; strCauhinhIndex++) {
         int x = this.xScroll;
         int y = this.yScroll + strCauhinhIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 1;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            graphicsValue.setColor(strCauhinhIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRect(x, y, width, height);
            if (strCauhinh[strCauhinhIndex] != null
               && (strCauhinh[strCauhinhIndex].equals(mResources.graphicSpeed) || strCauhinh[strCauhinhIndex].startsWith(mResources.graphicSpeed))) {
               if (SoundMn.doesGameSpeedOptionFit()) {
                  mFont.tahoma_7b_dark.drawString(graphicsValue, SoundMn.formatGameSpeedOption(), this.xScroll + 25, y + 6, 0);
               } else {
                  mFont.tahoma_7b_dark.drawString(graphicsValue, mResources.graphicSpeed, this.xScroll + 25, y + 2, 0);
                  mFont.tahoma_7b_dark.drawString(graphicsValue, SoundMn.formatGameSpeedChoices(), this.xScroll + 25, y + 12, 0);
               }
            } else {
               mFont.tahoma_7b_dark.drawString(graphicsValue, strCauhinh[strCauhinhIndex], this.xScroll + 25, y + 6, 0);
            }
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void doFireOption() {
      if (this.selected >= 0) {
         if (this.selected >= strCauhinh.length
            || strCauhinh[this.selected] == null
            || !strCauhinh[this.selected].equals(mResources.graphicSpeed)
               && !strCauhinh[this.selected].startsWith(mResources.graphicSpeed)) {
            switch (this.selected) {
               case 0:
                  SoundMn.gI().AuraToolOption();
                  return;
               case 1:
                  SoundMn.gI().AuraToolOption2();
                  return;
               case 2:
                  return;
               case 3:
                  SoundMn.gI().CaseSizeScr();
                  return;
               case 4:
                  SoundMn.gI().analogToolOption();
               case 5:
            }
         } else {
            SoundMn.gI().selectGameSpeed();
         }
      }
   }

   private void setTypeAccount() {
      this.type = 20;
      this.setType(0);
      Panel panel = this;
      if (mSystem.clientType == 5) {
         strAccount = new String[]{
            mResources.inventory_Pass, mResources.friend, mResources.enemy, mResources.msg
         };
         if (GameScr.canAutoPlay) {
            strAccount = new String[]{
               mResources.inventory_Pass,
               mResources.friend,
               mResources.enemy,
               mResources.msg,
               mResources.autoFunction
            };
         }
      } else {
         strAccount = new String[]{
            mResources.inventory_Pass,
            mResources.friend,
            mResources.enemy,
            mResources.msg,
            mResources.charger
         };
         if (GameScr.canAutoPlay) {
            strAccount = new String[]{
               mResources.inventory_Pass,
               mResources.friend,
               mResources.enemy,
               mResources.msg,
               mResources.charger,
               mResources.autoFunction
            };
         }

         if ((mSystem.clientType == 2 || mSystem.clientType == 7) && mResources.language != 2) {
            strAccount = new String[]{
               mResources.inventory_Pass,
               mResources.friend,
               mResources.enemy,
               mResources.msg,
               mResources.charger
            };
            if (GameScr.canAutoPlay) {
               strAccount = new String[]{
                  mResources.inventory_Pass,
                  mResources.friend,
                  mResources.enemy,
                  mResources.msg,
                  mResources.charger,
                  mResources.autoFunction
               };
            }
         }
      }

      panel.currentListLength = strAccount.length;
      panel.ITEM_HEIGHT = 24;
      panel.selected = GameCanvas.isTouch ? -1 : 0;
      panel.cmyLim = panel.currentListLength * panel.ITEM_HEIGHT - panel.hScroll;
      if (panel.cmyLim < 0) {
         panel.cmyLim = 0;
      }

      panel.cmy = panel.cmtoY = panel.cmyLast[panel.currentTabIndex];
      if (panel.cmy < 0) {
         panel.cmy = panel.cmtoY = 0;
      }

      if (panel.cmy > panel.cmyLim) {
         panel.cmy = panel.cmtoY = panel.cmyLim;
      }

      this.cmx = this.cmtoX = 0;
   }

   private void paintAccount(mGraphics graphicsValue) {
      graphicsValue.setClip(this.xScroll, this.yScroll, this.wScroll, this.hScroll);
      graphicsValue.translate(0, -this.cmy);

      for (int strAccountIndex = 0; strAccountIndex < strAccount.length; strAccountIndex++) {
         int x = this.xScroll;
         int y = this.yScroll + strAccountIndex * this.ITEM_HEIGHT;
         int width = this.wScroll - 1;
         int height = this.ITEM_HEIGHT - 1;
         if (y - this.cmy <= this.yScroll + this.hScroll
            && y - this.cmy >= this.yScroll - this.ITEM_HEIGHT) {
            graphicsValue.setColor(strAccountIndex == this.selected ? 16383818 : 15196114);
            graphicsValue.fillRect(x, y, width, height);
            mFont.tahoma_7b_dark.drawString(graphicsValue, strAccount[strAccountIndex], this.xScroll + this.wScroll / 2, y + 6, 2);
         }
      }

      this.paintScrollArrow(graphicsValue);
   }

   private void doFireAccount() {
      if (this.selected >= 0) {
         switch (this.selected) {
            case 0:
               GameCanvas.endDlg();
               if (this.chatTField == null) {
                  this.chatTField = new ChatTextField();
                  this.chatTField.tfChat.y = GameCanvas.h
                     - 35
                     - ChatTextField.gI().tfChat.height;
                  this.chatTField.initChatTextField();
                  this.chatTField.parentScreen = GameCanvas.panel;
               }

               this.chatTField.tfChat.setText("");
               this.chatTField.strChat = mResources.input_Inventory_Pass;
               this.chatTField.tfChat.name = mResources.input_Inventory_Pass;
               this.chatTField.to = "";
               this.chatTField.isShow = true;
               this.chatTField.tfChat.isFocus = true;
               this.chatTField.tfChat.setIputType(1);
               if (GameCanvas.isTouch) {
                  this.chatTField.tfChat.doChangeToTextBox();
                  return;
               }
               break;
            case 1:
               Service.gI().friend((byte)0, -1);
               InfoDlg.showWait();
               return;
            case 2:
               Service.gI().enemy((byte)0, -1);
               InfoDlg.showWait();
               return;
            case 3:
               this.setTypeMessage();
               if (this.chatTField == null) {
                  this.chatTField = new ChatTextField();
                  this.chatTField.tfChat.y = GameCanvas.h
                     - 35
                     - ChatTextField.gI().tfChat.height;
                  this.chatTField.initChatTextField();
                  this.chatTField.parentScreen = GameCanvas.panel;
                  return;
               }
               break;
            case 4:
               if (mResources.language == 2) {
                  String text = "http://dragonball.indonaga.com/coda/?username=" + GameCanvas.loginScr.tfUser.getText();
                  this.hideNow();

                  try {
                     GameMidlet.instance.platformRequest(text);
                     return;
                  } catch (Exception exception) {
                     exception.toString();
                     return;
                  }
               }

               this.hideNow();
               if (Char.myCharz().taskMaint.taskId <= 10) {
                  GameCanvas.startOKDlg(mResources.finishBomong);
                  return;
               }

               MoneyCharge.gI().switchToMe();
               return;
            case 5:
               this.setTabAuto();
         }
      }
   }

   public final void setTypeSpeacialSkill() {
      this.type = 25;
      this.setType(0);
      this.setTabSpeacialSkill();
      this.currentTabIndex = 0;
   }

   private void setTabSpeacialSkill() {
      this.ITEM_HEIGHT = 24;
      this.currentListLength = Char.myCharz().infoSpeacialSkill[this.currentTabIndex].length;
      this.cmyLim = this.currentListLength * this.ITEM_HEIGHT - this.hScroll;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = this.cmyLast[this.currentTabIndex];
      if (this.cmy < 0) {
         this.cmy = this.cmtoY = 0;
      }

      if (this.cmy > this.cmyLim) {
         this.cmy = this.cmtoY = this.cmyLim;
      }

      this.selected = GameCanvas.isTouch ? -1 : 0;
   }

   public final boolean isTypeShop() {
      return this.type == 1;
   }

   private void doNotiRuby(int type) {
      try {
         this.currItem.buyRuby = Integer.parseInt(this.chatTField.tfChat.getText());
      } catch (Exception ignoredException) {
         GameCanvas.startOKDlg(mResources.input_money_wrong);
         this.chatTField.isShow = false;
         return;
      }

      Command cmdYes = new Command(mResources.YES, this, type == 0 ? 11000 : 11001, null);
      Command cmdNo = new Command(mResources.NO, this, 11002, null);
      GameCanvas.startYesNoDlg(mResources.notiRuby, cmdYes, cmdNo);
   }

   private static int GetColor_ItemBg(int id) {
      switch (id) {
         case 1:
            return 2786816;
         case 2:
            return 7078041;
         case 3:
            return 12537346;
         case 4:
            return 1269146;
         case 5:
            return 13279744;
         case 6:
            return 11599872;
         default:
            return -1;
      }
   }

   private static byte GetColor_Item_Upgrade(int lv) {
      if (lv < 0) {
         return 0;
      }

      switch (lv) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
            return 0;
         case 9:
            return 4;
         case 10:
            return 1;
         case 11:
            return 5;
         case 12:
            return 3;
         case 13:
            return 2;
         default:
            return 6;
      }
   }

   private static mFont GetFont(int color) {
      mFont tahoma_7 = mFont.tahoma_7;
      switch (color) {
         case -1:
            tahoma_7 = mFont.tahoma_7;
            break;
         case 0:
            tahoma_7 = mFont.tahoma_7b_dark;
            break;
         case 1:
            tahoma_7 = mFont.tahoma_7b_green;
            break;
         case 2:
            tahoma_7 = mFont.tahoma_7b_blue;
            break;
         case 3:
            tahoma_7 = mFont.tahoma_7_red;
            break;
         case 4:
            tahoma_7 = mFont.tahoma_7_green;
            break;
         case 5:
            tahoma_7 = mFont.tahoma_7_blue;
         case 6:
         default:
            break;
         case 7:
            tahoma_7 = mFont.tahoma_7b_red;
            break;
         case 8:
            tahoma_7 = mFont.tahoma_7b_yellow;
      }

      return tahoma_7;
   }

   private void paintOptItem(mGraphics graphicsValue, int value, int x, int value2, int value3) {
      if (value == 34) {
         if (this.imgo_0 != null) {
            graphicsValue.drawImage(this.imgo_0, ((int) (x)), ((int) (value2 + value3 - this.imgo_0.getHeight())), 0);
         } else {
            this.imgo_0 = mSystem.loadImage("/mainImage/o_0.png");
         }

         if (this.imgo_1 != null) {
            graphicsValue.drawImage(this.imgo_1, ((int) (x)), ((int) (value2 + value3 - this.imgo_1.getHeight())), 0);
         } else {
            this.imgo_1 = mSystem.loadImage("/mainImage/o_1.png");
         }
      } else if (value == 35) {
         if (this.imgo_0 != null) {
            graphicsValue.drawImage(this.imgo_0, ((int) (x)), ((int) (value2 + value3 - this.imgo_0.getHeight())), 0);
         } else {
            this.imgo_0 = mSystem.loadImage("/mainImage/o_0.png");
         }

         if (this.imgo_2 != null) {
            graphicsValue.drawImage(this.imgo_2, ((int) (x)), ((int) (value2 + value3 - this.imgo_2.getHeight())), 0);
         } else {
            this.imgo_2 = mSystem.loadImage("/mainImage/o_2.png");
         }
      } else {
         if (value == 36) {
            if (this.imgo_0 != null) {
               graphicsValue.drawImage(this.imgo_0, ((int) (x)), ((int) (value2 + value3 - this.imgo_0.getHeight())), 0);
            } else {
               this.imgo_0 = mSystem.loadImage("/mainImage/o_0.png");
            }

            if (this.imgo_3 != null) {
               graphicsValue.drawImage(this.imgo_3, ((int) (x)), ((int) (value2 + value3 - this.imgo_3.getHeight())), 0);
               return;
            }

            this.imgo_3 = mSystem.loadImage("/mainImage/o_3.png");
         }
      }
   }

   private static void paintUpgradeEffect(mGraphics graphicsValue, int value, int lv, int value2, int value3, int value4, int value5) {
      if (value == 102 && lv > ChatPopup.numSlot) {
         int byteValue = GetColor_Item_Upgrade(lv);
         lv -= ChatPopup.numSlot;
         mGraphics graphicsValue2 = graphicsValue;
         byte colorUpgradeEffectIndex = (byte)byteValue;
         int lv2 = lv;
         int value6 = value5;
         byte byteValue2 = 34;
         byteValue = value3;
         int value7 = value2;

         try {
            int value8 = ((byteValue2 << 1) + (value6 << 1)) / lv2;
            nsize = sizeUpgradeEff.length;
            if (lv2 > 4) {
               nsize = 2;
            }

            for (int index = 0; index < lv2; index++) {
               for (int colorUpgradeEffectIndex2 = 0; colorUpgradeEffectIndex2 < nsize; colorUpgradeEffectIndex2++) {
                  int y = sizeUpgradeEff[colorUpgradeEffectIndex2] > 1 ? (sizeUpgradeEff[colorUpgradeEffectIndex2] >> 1) + 1 : 1;
                  int value9 = value8 * index;
                  int value10 = GameCanvas.gameTick - (colorUpgradeEffectIndex2 << 2);
                  int y2 = y;
                  int value11 = value6;
                  byte byteValue3 = byteValue2;
                  int value12 = value10;
                  int value13 = value9;
                  int value14;
                  int x = value7
                     + (
                        (value14 = (value12 + value13) % ((byteValue3 << 1) + (value11 << 1))) >= 0 && value14 < byteValue3
                           ? value14 % byteValue3
                           : (
                              byteValue3 <= value14 && value14 < byteValue3 + value11
                                 ? byteValue3 - y2
                                 : (byteValue3 + value11 <= value14 && value14 < (byteValue3 << 1) + value11 ? byteValue3 - (value14 - value11) % byteValue3 - y2 : 0)
                           )
                     );
                  value9 = value8 * index;
                  value10 = GameCanvas.gameTick - (colorUpgradeEffectIndex2 << 2);
                  y2 = y;
                  value11 = value6;
                  byteValue3 = byteValue2;
                  value12 = value10;
                  value13 = value9;
                  int value15;
                  y = byteValue
                     + (
                        (value15 = (value12 + value13) % ((byteValue3 << 1) + (value11 << 1))) >= 0 && value15 < byteValue3
                           ? 0
                           : (
                              byteValue3 <= value15 && value15 < byteValue3 + value11
                                 ? value15 % byteValue3
                                 : (byteValue3 + value11 <= value15 && value15 < (byteValue3 << 1) + value11 ? value11 - y2 : value11 - (value15 - (byteValue3 << 1)) % value11 - y2)
                           )
                     );
                  graphicsValue2.setColor(colorUpgradeEffect[colorUpgradeEffectIndex][colorUpgradeEffectIndex2]);
                  graphicsValue2.fillRect(x, y, sizeUpgradeEff[colorUpgradeEffectIndex2], sizeUpgradeEff[colorUpgradeEffectIndex2]);
               }
            }

            return;
         } catch (Exception ignoredException) {
         }
      }
   }

   public static mFont setTextColor(int id, int typeValue) {
      switch (id) {
         case 0:
            return mFont.tahoma_7b_white;
         case 1:
            return mFont.tahoma_7b_green;
         case 2:
         default:
            return mFont.tahoma_7b_white;
         case 3:
            return mFont.tahoma_7b_yellowSmall2;
         case 4:
            return mFont.tahoma_7b_blue;
         case 5:
            return mFont.tahoma_7b_yellow;
         case 6:
            return mFont.tahoma_7b_red;
         case 7:
            return mFont.tahoma_7b_dark;
      }
   }

   private static boolean GetInventorySelect_isbody(int select, int subSelect, Item[] arrItem) {
      select = select - 1 + subSelect * 20;
      return subSelect == 0 ? select < arrItem.length : false;
   }

   private static int GetInventorySelect_body(int select, int subSelect) {
      return select - 1 + subSelect * 20;
   }

   private static int GetInventorySelect_bag(int select, int subSelect, Item[] arrItem) {
      return select - 1 + subSelect * 20 - arrItem.length;
   }

   private boolean isTabInven() {
      return this.type == 0 && this.currentTabIndex == 1 || this.type == 7 && this.currentTabIndex == 0;
   }

   private void updateKeyInvenTab() {
      if (this.selected >= 0) {
         if (GameCanvas.keyPressed[4]) {
            this.newSelected--;
            if (this.newSelected < 0) {
               this.newSelected = 0;
               if (GameCanvas.isFocusPanel2) {
                  GameCanvas.isFocusPanel2 = false;
                  GameCanvas.panel.selected = 0;
                  return;
               }
            }
         } else if (GameCanvas.keyPressed[6]) {
            this.newSelected++;
            if (this.newSelected > this.size_tab - 1) {
               this.newSelected = this.size_tab - 1;
               if (GameCanvas.panel2 != null) {
                  GameCanvas.isFocusPanel2 = true;
                  GameCanvas.panel2.selected = 0;
               }
            }
         }
      }
   }

   private void updateKeyInventory() {
      this.updateKeyScrollView();
      if (this.selected == 0) {
         this.updateKeyInvenTab();
      }
   }

   private int checkCurrentListLength(int arrLength) {
      int value = 20;
      int size_tabValue = arrLength / 20 + (arrLength % 20 > 0 ? 1 : 0);
      this.size_tab = (byte)size_tabValue;
      if (this.newSelected > size_tabValue - 1) {
         this.newSelected = size_tabValue - 1;
      }

      if (arrLength % 20 > 0 && this.newSelected == size_tabValue - 1) {
         value = arrLength % 20;
      }

      return value + 1;
   }

   private void setNewSelected(int arrLength, boolean resetSelect, boolean isTabBox) {
      int value = arrLength / 20 + (arrLength % 20 > 0 ? 1 : 0);
      int xScrollValue = this.xScroll;
      this.newSelected = (GameCanvas.px - xScrollValue) / this.TAB_W_NEW;
      if (this.newSelected > value - 1) {
         this.newSelected = value - 1;
      }

      if (GameCanvas.px < xScrollValue) {
         this.newSelected = 0;
      }

      if (!isTabBox) {
         this.setTabInventory(resetSelect);
      } else {
         this.setTabBox();
      }
   }
}
