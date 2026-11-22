package nro;

import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public class Char implements IMapObject {
   public String xuStr;
   public String luongStr;
   public String luongKhoaStr;
   public long lastUpdateTime;
   public boolean isMask;
   public boolean isTeleport;
   public boolean isUsePlane;
   private int shadowX;
   private int shadowY;
   private int shadowLife;
   public boolean isNhapThe;
   public PetFollow petFollow;
   public int topRankingPosition = 0;
   public static ChatPopup chatPopup;
   public long cPower;
   public Info chatInfo;
   public byte petStatus;
   public int cx = 24;
   public int cy = 24;
   public int cvx;
   public int cvy;
   public int cp1;
   private int cp2;
   public int cp3;
   public int statusMe = 5;
   public int cdir = 1;
   public int charID;
   public int cgender;
   public int ctaskId;
   public int menuSelect;
   public int cBonusSpeed;
   public int cspeed = 4;
   public int cCriticalFull;
   public int clevel;
   private int cyStartFall;
   public int eff5BuffHp;
   public int eff5BuffMp;
   public long cMP;
   public long cHP;
   public long cHPNew;
   public long cHPShow;
   public long cHPFull;
   public long cMPFull;
   public long cDamFull;
   public long cDefull;
   public long cLevelPercent;
   public long cTiemNang;
   public long cNangdong;
   public long damHP;
   public boolean isMob;
   public boolean isCrit;
   public boolean isDie;
   public String cName = "";
   public int clanID;
   public Clan clan;
   public byte role;
   public int cw = 22;
   public int ch = 32;
   public int chw = 11;
   private int chh = 16;
   public Command cmdMenu;
   public boolean canFly = true;
   public boolean cmtoChar;
   public boolean me;
   public boolean isAttack;
   private boolean isSoundJump;
   public int cf;
   private int tick;
   public long xu;
   public int yen;
   public int luong;
   public int luongKhoa;
   public NClass nClass;
   public Command endMovePointCommand;
   public MyVector vSkill = new MyVector("vSkill");
   public MyVector vSkillFight = new MyVector("vSkillFight");
   public MyVector vEff = new MyVector("vEff char");
   public Skill myskill;
   public Task taskMaint;
   private boolean paintName = true;
   public Archivement[] arrArchive;
   public Item[] arrItemBag;
   public Item[] arrItemBox;
   public Item[] arrItemBody;
   public Skill[] arrPetSkill;
   public Item[][] arrItemShop;
   public String[][] infoSpeacialSkill;
   public short[][] imgSpeacialSkill;
   public byte cPk;
   public byte cTypePk;
   public int avatar;
   public int skillTemplateId;
   public Mob mobFocus;
   public Mob mobMe;
   public int tMobMeBorn;
   public Npc npcFocus;
   public Char charFocus;
   public ItemMap itemFocus;
   public MyVector focus = new MyVector("focus");
   public Mob[] attMobs;
   public Char[] attChars;
   public short[] moveFast;
   public int testCharId = -9999;
   public int killCharId = -9999;
   private byte resultTest;
   public int countKill;
   public int countKillMax;
   public boolean isInvisiblez;
   public boolean isShadown = true;
   public MyVector taskOrders = new MyVector("taskOrders");
   public int cStamina;
   public static short[] idHead;
   public static short[] idAvatar;
   public String[] strLevel;
   public String currStrLevel;
   private static Image eyeTraiDat = mSystem.loadImage("/mainImage/myTexture2dat-trai-dat.png");
   private static Image eyeNamek = mSystem.loadImage("/mainImage/myTexture2dmat-namek.png");
   public boolean isFreez = false;
   public boolean isCharge;
   public int seconds;
   public int freezSeconds;
   public long last;
   private long cur;
   public long lastFreez;
   public long currFreez;
   public boolean isFlyUp;
   public static Vector vItemTime = new Vector();
   public static short ID_NEW_MOUNT = 30000;
   public short idMount = 0;
   public boolean isHaveMount;
   public boolean isMountVip;
   public boolean isEventMount;
   public boolean isSpeacialMount;
   private static Image imgMount_TD = mSystem.loadImage("/mainImage/myTexture2dthucuoi10.png");
   private static Image imgMount_NM = mSystem.loadImage("/mainImage/myTexture2dthucuoi20.png");
   private static Image imgMount_NM_1 = mSystem.loadImage("/mainImage/myTexture2dthucuoi21.png");
   private static Image imgMount_XD = mSystem.loadImage("/mainImage/myTexture2dthucuoi30.png");
   private static Image imgMount_TD_VIP = mSystem.loadImage("/mainImage/myTexture2dthucuoi11.png");
   private static Image imgMount_NM_VIP = mSystem.loadImage("/mainImage/myTexture2dthucuoi22.png");
   private static Image imgMount_NM_1_VIP = mSystem.loadImage("/mainImage/myTexture2dthucuoi23.png");
   private static Image imgMount_XD_VIP = mSystem.loadImage("/mainImage/myTexture2dthucuoi31.png");
   private static Image imgEventMount = mSystem.loadImage("/mainImage/myTexture2drong.png");
   private static Image imgEventMountWing = mSystem.loadImage("/mainImage/myTexture2dcanhrong.png");
   private byte[] FrameMount = new byte[]{0, 0, 1, 1, 2, 2, 1, 1};
   private int frameMount;
   private int frameNewMount;
   private int transMount;
   private int genderMount;
   private int xMount;
   private int yMount;
   private int dxMount;
   private int dyMount;
   private int xChar;
   private int xdis;
   private int speedMount;
   private boolean isStartMount;
   private boolean isMount;
   private boolean isEndMount;
   public byte cFlag;
   public int flagImage;
   public short x_hint;
   public short y_hint;
   public static final int[][][] CharInfo = new int[][][]{
      {{0, -13, 34}, {1, -8, 10}, {1, -9, 16}, {1, -9, 45}},
      {{0, -13, 35}, {1, -8, 10}, {1, -9, 17}, {1, -9, 46}},
      {{1, -10, 33}, {2, -10, 11}, {2, -8, 16}, {1, -12, 49}},
      {{1, -10, 32}, {3, -12, 10}, {3, -11, 15}, {1, -13, 47}},
      {{1, -10, 34}, {4, -8, 11}, {4, -7, 17}, {1, -12, 47}},
      {{1, -10, 34}, {5, -12, 11}, {5, -9, 17}, {1, -13, 49}},
      {{1, -10, 33}, {6, -10, 10}, {6, -8, 16}, {1, -12, 47}},
      {{0, -9, 36}, {7, -5, 17}, {7, -11, 25}, {1, -8, 49}},
      {{0, -7, 35}, {0, -18, 22}, {7, -10, 25}, {1, -7, 48}},
      {{1, -11, 35}, {10, -3, 25}, {12, -10, 26}, new int[3]},
      {{1, -11, 37}, {11, -3, 25}, {12, -11, 27}, new int[3]},
      {{0, -14, 34}, {12, -8, 21}, {9, -7, 31}, new int[3]},
      {{0, -12, 35}, {8, -5, 14}, {8, -15, 29}, {1, -9, 49}},
      {{1, -9, 34}, {9, -12, 9}, {10, -7, 19}, new int[3]},
      {{1, -13, 34}, {9, -12, 9}, {11, -10, 19}, new int[3]},
      {{1, -8, 32}, {9, -12, 9}, {2, -6, 15}, new int[3]},
      {{1, -8, 32}, {9, -12, 9}, {13, -12, 16}, new int[3]},
      {{0, -10, 31}, {9, -12, 9}, {7, -13, 20}, new int[3]},
      {{0, -11, 32}, {9, -12, 9}, {8, -15, 26}, new int[3]},
      {{0, -9, 33}, {9, -12, 9}, {14, -8, 18}, new int[3]},
      {{0, -11, 33}, {9, -12, 9}, {15, -6, 19}, new int[3]},
      {{0, -16, 31}, {9, -12, 9}, {9, -8, 28}, new int[3]},
      {{0, -14, 34}, {1, -8, 10}, {8, -16, 28}, new int[3]},
      {{0, -8, 36}, {7, -5, 17}, {0, -5, 25}, new int[3]},
      {{0, -9, 31}, {9, -12, 9}, {0, -6, 20}, new int[3]},
      {{2, -9, 36}, {13, -5, 17}, {16, -11, 25}, new int[3]},
      {{1, -9, 34}, {8, -5, 13}, {10, -7, 19}, new int[3]},
      {{1, -13, 34}, {8, -5, 13}, {11, -10, 19}, new int[3]},
      {{1, -8, 32}, {8, -5, 13}, {2, -6, 15}, new int[3]},
      {{1, -8, 32}, {8, -5, 13}, {13, -12, 16}, new int[3]},
      {{0, -9, 33}, {8, -5, 13}, {14, -8, 18}, new int[3]},
      {{0, -11, 33}, {8, -5, 13}, {15, -6, 19}, new int[3]},
      {{0, -16, 32}, {8, -5, 13}, {9, -8, 29}, new int[3]}
   };
   private static Char myChar;
   private static Char myPet;
   private int cvyJump;
   public int cxSend;
   public int cySend;
   public int lastSentMoveDirection;
   public int cxFocus;
   public int cyFocus;
   public int lastSentMoveStatus;
   public MyVector vMovePoints = new MyVector("vMovePoints");
   public static boolean ischangingMap;
   public static boolean isLockKey;
   public static boolean isLoadingMap;
   public boolean isLockMove;
   String strInfo;
   short powerPoint;
   short maxPowerPoint;
   short secondPower;
   private long lastS;
   private long currS;
   public boolean havePet = true;
   public MovePoint currentMovePoint;
   public int delayFall;
   private Effect eProtect = null;
   private Effect eDanhHieu = null;
   private int twHp;
   private boolean isInjureHp;
   private boolean changePos;
   public boolean isHide;
   private boolean wy;
   private int wt;
   int fy;
   private int ty;
   private int t;
   private int fM;
   private int[] move = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2};
   private String strMount = "mount_";
   public int headICON = -1;
   public int head;
   public int leg;
   public int body;
   public int bag;
   public int wp;
   private int indexEff = -1;
   public int indexEffTask = -1;
   private EffectCharPaint eff;
   public EffectCharPaint effTask;
   public int indexSkill;
   public int i0;
   public int i1;
   public int i2;
   private int dx0;
   private int dx1;
   private int dx2;
   private int dy0;
   private int dy1;
   private int dy2;
   public EffectCharPaint eff0 = null;
   public EffectCharPaint eff1 = null;
   public EffectCharPaint eff2 = null;
   public Arrow arr = null;
   public PlayerDart dart = null;
   public boolean isCreateDark = false;
   public SkillPaint skillPaint;
   public SkillPaint skillPaintRandomPaint;
   private EffectPaint[] effPaints;
   public int sType;
   private byte isInjure;
   public boolean isUseSkillAfterCharge = false;
   public boolean isFlyAndCharge = false;
   public boolean isStandAndCharge = false;
   private int posDisY;
   private int chargeCount = 0;
   private boolean hasSendAttack;
   boolean isMabuHold;
   private long timeBlue = 0L;
   private int tBlue;
   private boolean IsAddDust1;
   private boolean IsAddDust2;
   private int len = 24;
   private int w_hp_bar = 24;
   private Image imgHPtem;
   private boolean isPet;
   private boolean isMiniPet;
   public int xSd;
   public int ySd;
   private int fBag = 0;
   private Part ph;
   private Part pl;
   private Part pb;
   private int cH_new = 32;
   private int statusBeforeNothing;
   private int timeFocusToMob = 0;
   public static boolean isManualFocus = false;
   private Char charHold;
   private Mob mobHold;
   public short wdx;
   public short wdy;
   public boolean currentFireByShortcut;
   public int cDamGoc;
   public int cHPGoc;
   public int cMPGoc;
   public int cDefGoc;
   public int cCriticalGoc;
   public int cGiamST;
   public int cCritDameFull;
   public byte hpFrom1000TiemNang;
   public byte mpFrom1000TiemNang;
   public byte damFrom1000TiemNang;
   public byte defFrom1000TiemNang = 1;
   public byte criticalFrom1000Tiemnang = 1;
   public short cMaxStamina;
   public short expForOneAdd;
   public byte isMonkey;
   public boolean isCopy;
   public boolean isWaitMonkey = false;
   public boolean meDead;
   public int holdEffID;
   public boolean holder;
   public boolean protectEff;
   private boolean danhHieuEff = true;
   private boolean isSetPos = false;
   private int tpos = 0;
   private short xPos;
   private short yPos;
   private byte typePos;
   boolean isFusion;
   int tFusion;
   public boolean huytSao;
   public boolean blindEff;
   public boolean telePortSkill;
   public boolean sleepEff;
   public boolean stone;
   public int perCentMp = 100;
   public long dHP;
   private int headTemp = -1;
   private int bodyTemp = -1;
   private int legTemp = -1;
   private int bagTemp = -1;
   private MyVector vEffChar = new MyVector("vEff");
   private static FrameImage fraRedEye;
   private int fChopmat = 0;
   private long timeAddChopmat;
   private int[] frChopNhanh = new int[]{-1, -1, -1, -1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, -1, -1, -1, -1};
   private int[] frChopCham = new int[]{-1, -1, -1, -1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, -1, -1, -1, -1};
   private int[] frEye = new int[]{-1, -1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, -1, -1};
   public static int[][] Arr_Head_2Fr = new int[][]{{542, 543}};
   private int fHead = 0;
   private String strEffAura = "aura_";
   public short idAuraEff = -1;
   public static boolean isPaintAura = true;
   public static boolean isPaintAura2 = true;
   private FrameImage fraEff;
   private FrameImage fraEffSub;
   private String strEff_Set_Item = "set_eff_";
   public short idEff_Set_Item = -1;
   private FrameImage fraHat_behind;
   private FrameImage fraHat_font;
   private FrameImage fraHat_behind_2;
   private FrameImage fraHat_font_2;
   private String strHat_behind = "hat_sau_";
   private String strHat_font = "hat_truoc_";
   private String strNgang = "ngang_";
   public short idHat = -1;
   private static int[][] hatInfo = new int[][]{
      {5, -7},
      {5, -7},
      {5, -8},
      {5, -7},
      {5, -6},
      {5, -8},
      {5, -7},
      {9, 0},
      {11, 1},
      {4, 0},
      {4, -1},
      {4, 8},
      {6, 5},
      {6, -6},
      {2, -5},
      {7, -8},
      {7, -6},
      {8, 0},
      {7, 5},
      {9, -7},
      {7, -3},
      {2, 8},
      {4, 5},
      {10, -5},
      {9, -5},
      {9, -5},
      {6, -6},
      {2, -5},
      {7, -8},
      {7, -6},
      {9, -7},
      {7, -3}
   };
   public static short[] Arr_Head_FlyMove = new short[0];
   public boolean isPaintNewSkill;
   private boolean isFly;
   private long timeReset_newSkill;
   private byte typeFrame = 0;
   private short idskillPaint;
   private byte[] fr_start;
   private byte[] fr_atk;
   private byte[] fr_end;
   private int count_NEW = 0;
   private int stt = 0;
   private Point targetDame;
   private long timeDame;
   public boolean isMafuba = false;
   private short countMafuba;
   public int xMFB;
   public int yMFB;
   private FrameImage fraDanhHieu = null;

   static {
      mSystem.loadZoomedResourceImage("/mainImage/glass0.png");
      mSystem.loadZoomedResourceImage("/mainImage/glass1.png");
      int[] intValues = new int[]{-2, -6, 22, 21, 19, 22, 10, -2, -2, 5, 19};
      intValues = new int[]{9, 22, 25, 17, 26, 37, 36, 49, 50, 52, 36};
      String[][] textsByRow = new String[][]{{"1", "1", "chiêu 1", "0"}, {"2", "2", "chiêu 2", "5"}};
      int[][] intValuesByRow = new int[][]{
         {1, 0, 1, 1000, 40, 1, 0, 20, 0, 0, 0, 0},
         {2, 1, 10, 1000, 100, 1, 0, 40, 0, 0, 0, 0},
         {2, 2, 11, 800, 100, 1, 0, 45, 0, 0, 0, 0},
         {2, 3, 12, 600, 100, 1, 0, 50, 0, 0, 0, 0},
         {2, 4, 13, 500, 100, 1, 0, 55, 0, 0, 0, 0},
         {3, 1, 14, 500, 100, 1, 0, 60, 0, 0, 0, 0},
         {3, 2, 14, 500, 100, 1, 0, 60, 0, 0, 0, 0},
         {3, 3, 14, 500, 100, 1, 0, 60, 0, 0, 0, 0},
         {3, 4, 14, 500, 100, 1, 0, 60, 0, 0, 0, 0},
         {3, 5, 14, 500, 100, 1, 0, 60, 0, 0, 0, 0}
      };
   }

   public final void applyCharLevelPercent() {
      try {
         long longValue = 1L;
         long longValue2 = 0L;
         int clevelValue = 0;

         for (int expIndex = GameScr.exps.length - 1; expIndex >= 0; expIndex--) {
            if (this.cPower >= GameScr.exps[expIndex]) {
               if (expIndex == GameScr.exps.length - 1) {
                  longValue = 1L;
               } else {
                  longValue = GameScr.exps[expIndex + 1] - GameScr.exps[expIndex];
               }

               longValue2 = this.cPower - GameScr.exps[expIndex];
               clevelValue = expIndex;
               break;
            }
         }

         this.clevel = clevelValue;
         this.cLevelPercent = (long)((int)(longValue2 * 10000L / longValue));
      } catch (Exception ignoredException) {
      }
   }

   private int getdxSkill() {
      return this.myskill != null ? this.myskill.dx : 0;
   }

   private int getdySkill() {
      return this.myskill != null ? this.myskill.dy : 0;
   }

   public static void taskAction(boolean isNextStep) {
      String s;
      Task taskMaintValue;
      if ((s = (taskMaintValue = myCharz().taskMaint).contentInfo[taskMaintValue.index]) != null && !s.equals("")) {
         if (s.startsWith("#")) {
            String strNext = Firework.replaceAllLiteral(s, "#", "");
            Npc next;
            (next = new Npc(5, 0, -100, -100, 5, GameScr.info1.charId[myCharz().cgender][2])).cx = next.cy = -100;
            next.avatar = GameScr.info1.charId[myCharz().cgender][2];
            next.charID = 5;
            if (GameCanvas.currentScreen == GameScr.instance) {
               ChatPopup.addNextPopUpMultiLine(strNext, next);
            }
         } else {
            GameScr.info1.addInfo(s, 0);
         }
      }

      GameScr.isHaveSelectSkill = true;
      Res.outz("TASKx " + myCharz().taskMaint.taskId);
      if (myCharz().taskMaint.taskId <= 2) {
         myCharz().canFly = false;
      } else {
         myCharz().canFly = true;
      }

      GameScr.gI().left = null;
      if (taskMaintValue.taskId == 0) {
         Hint.isViewMap = false;
         Hint.isViewPotential = false;
         GameScr.gI().right = null;
         GameScr.isHaveSelectSkill = false;
         GameScr.gI().left = null;
         if (taskMaintValue.index < 4) {
            MagicTree.isPaint = false;
            GameScr.isPaintRada = -1;
         }

         if (taskMaintValue.index == 4) {
            GameScr.isPaintRada = 1;
            MagicTree.isPaint = true;
         }

         if (taskMaintValue.index >= 5) {
            GameScr.gI().right = GameScr.gI().cmdFocus;
         }
      }

      if (taskMaintValue.taskId == 1) {
         GameScr.isHaveSelectSkill = true;
      }

      if (taskMaintValue.taskId > 0) {
         GameScr.gI().right = GameScr.gI().cmdFocus;
         GameScr.gI().left = GameScr.gI().cmdMenu;
      }

      if (taskMaintValue.taskId >= 0) {
         Panel.isPaintMap = true;
      } else {
         Panel.isPaintMap = false;
      }

      if (taskMaintValue.taskId < 12) {
         GameCanvas.panel.mainTabName = mResources.mainTab1;
      } else {
         GameCanvas.panel.mainTabName = mResources.mainTab2;
      }

      GameCanvas.panel.tabName[0] = GameCanvas.panel.mainTabName;
      if (myChar.taskMaint.taskId > 10) {
         Rms.saveRMSString("fake", "aa");
      }
   }

   public final String getStrLevel() {
      String _text;
      if ((_text = this.strLevel[this.clevel] + "+" + this.cLevelPercent / 100L + "." + this.cLevelPercent % 100L + "%").length()
            > 23
         && _text.indexOf("cấp ") >= 0) {
         _text = Res.replace(_text, "cấp ", "c");
      }

      return _text;
   }

   public final int avatarz() {
      int headValue = this.head;

      for (int idAvatarIndex = 0; idAvatarIndex < idHead.length; idAvatarIndex++) {
         if (headValue == idHead[idAvatarIndex]) {
            return idAvatar[idAvatarIndex];
         }
      }

      return -1;
   }

   public final void setPowerInfo(String info, short p, short maxP, short sc) {
      this.powerPoint = p;
      this.strInfo = info;
      this.maxPowerPoint = maxP;
      this.secondPower = sc;
      Res.outz("sc= " + sc + " max= " + maxP);
      this.lastS = this.currS = System.currentTimeMillis();
   }

   public final void addInfo(String info) {
      if (this.chatInfo == null) {
         this.chatInfo = new Info();
      }

      this.chatInfo.addInfo(info, 0, null, false);
   }

   public Char() {
      this.statusMe = 6;
   }

   public static Char myCharz() {
      if (myChar == null) {
         (myChar = new Char()).me = true;
         myChar.cmtoChar = true;
      }

      return myChar;
   }

   public static Char myPetz() {
      if (myPet == null) {
         (myPet = new Char()).me = false;
      }

      return myPet;
   }

   public static void clearMyChar() {
      myChar = null;
   }

   public final void boxSort() {
      try {
         MyVector entries = new MyVector("items");

         for (int itemBoxIndex = 0; itemBoxIndex < this.arrItemBox.length; itemBoxIndex++) {
            Item entry;
            if ((entry = this.arrItemBox[itemBoxIndex]) != null && entry.template.isUpToUp && !entry.isExpires) {
               entries.addElement(entry);
            }
         }

         for (int entryIndex = 0; entryIndex < entries.size(); entryIndex++) {
            Item entry2;
            if ((entry2 = (Item)entries.elementAt(entryIndex)) != null) {
               for (int entryIndex2 = entryIndex + 1; entryIndex2 < entries.size(); entryIndex2++) {
                  Item entry3;
                  if ((entry3 = (Item)entries.elementAt(entryIndex2)) != null
                     && entry2.template.equals(entry3.template)
                     && entry2.isLock == entry3.isLock) {
                     entry2.quantity = entry2.quantity + entry3.quantity;
                     this.arrItemBox[entry3.indexUI] = null;
                     entries.setElementAt(null, entryIndex2);
                  }
               }
            }
         }

         for (int itemBoxIndex2 = 0; itemBoxIndex2 < this.arrItemBox.length; itemBoxIndex2++) {
            if (this.arrItemBox[itemBoxIndex2] != null) {
               for (int indexUI = 0; indexUI <= itemBoxIndex2; indexUI++) {
                  if (this.arrItemBox[indexUI] == null) {
                     this.arrItemBox[indexUI] = this.arrItemBox[itemBoxIndex2];
                     this.arrItemBox[indexUI].indexUI = indexUI;
                     this.arrItemBox[itemBoxIndex2] = null;
                     break;
                  }
               }
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
         Res.outz("Char.boxSort()");
      }
   }

   public final Skill getSkill(SkillTemplate skillTemplate) {
      for (int skillIndex = 0; skillIndex < this.vSkill.size(); skillIndex++) {
         if (((Skill)this.vSkill.elementAt(skillIndex)).template.id == skillTemplate.id) {
            return (Skill)this.vSkill.elementAt(skillIndex);
         }
      }

      return null;
   }

   public final Waypoint isInEnterOfflinePoint() {
      Task taskMaintValue = myChar.taskMaint;
      if (myChar.taskMaint != null && taskMaintValue.taskId == 0 && taskMaintValue.index < 6) {
         return null;
      } else {
         int goCount = TileMap.vGo.size();

         for (byte goIndex = 0; goIndex < goCount; goIndex++) {
            Waypoint go = (Waypoint)TileMap.vGo.elementAt(goIndex);
            if (PopUp.vPopups.size() >= goCount && !((PopUp)PopUp.vPopups.elementAt(goIndex)).isPaint) {
               return null;
            }

            if (this.cx >= go.minX
               && this.cx <= go.maxX
               && this.cy >= go.minY
               && this.cy <= go.maxY
               && go.isEnter
               && go.isOffline) {
               return go;
            }
         }

         return null;
      }
   }

   public final Waypoint isInEnterOnlinePoint() {
      Task taskMaintValue = myChar.taskMaint;
      if (myChar.taskMaint != null && taskMaintValue.taskId == 0 && taskMaintValue.index < 6) {
         return null;
      } else {
         int goCount = TileMap.vGo.size();

         for (byte goIndex = 0; goIndex < goCount; goIndex++) {
            Waypoint go = (Waypoint)TileMap.vGo.elementAt(goIndex);
            if (PopUp.vPopups.size() >= goCount && !((PopUp)PopUp.vPopups.elementAt(goIndex)).isPaint) {
               return null;
            }

            if (this.cx >= go.minX
               && this.cx <= go.maxX
               && this.cy >= go.minY
               && this.cy <= go.maxY
               && go.isEnter
               && !go.isOffline) {
               return go;
            }
         }

         return null;
      }
   }

   private boolean isInWaypoint() {
      if (TileMap.isInAirMap() && this.cy >= TileMap.pxh - 48) {
         return true;
      } else if (!this.isTeleport && !this.isUsePlane) {
         int goCount = TileMap.vGo.size();

         for (byte goIndex = 0; goIndex < goCount; goIndex++) {
            Waypoint go = (Waypoint)TileMap.vGo.elementAt(goIndex);
            if ((TileMap.mapID == 47 || TileMap.isInAirMap())
               && this.cy <= go.minY + go.maxY
               && this.cx > go.minX
               && this.cx < go.maxX) {
               if (TileMap.isInAirMap() && this.cTypePk != 0) {
                  return false;
               }

               return true;
            }

            if (this.cx >= go.minX
               && this.cx <= go.maxX
               && this.cy >= go.minY
               && this.cy <= go.maxY
               && !go.isEnter) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void soundUpdate() {
      if (this.me
         && this.statusMe == 10
         && this.cf == 8
         && this.ty > 20
         && GameCanvas.gameTick % 20 == 0) {
         SoundMn.gI();
         SoundMn.charFly();
      }

      if (this.skillPaint != null && this.skillInfoPaint() != null && this.indexSkill < this.skillInfoPaint().length) {
         boolean condition;
         label82: {
            if (this.skillPaint != null) {
               if (this.skillPaint.id >= 0 && this.skillPaint.id <= 6) {
                  condition = true;
                  break label82;
               }

               if (this.skillPaint.id >= 14 && this.skillPaint.id <= 20) {
                  condition = true;
                  break label82;
               }

               if (this.skillPaint.id >= 28 && this.skillPaint.id <= 34) {
                  condition = true;
                  break label82;
               }

               if (this.skillPaint.id >= 63 && this.skillPaint.id <= 69) {
                  condition = true;
                  break label82;
               }
            }

            condition = false;
         }

         if (condition
            && (
               this.me
                  || !this.me
                     && this.cx >= GameScr.cmx
                     && this.cx <= GameScr.cmx + GameCanvas.w
            )
            && GameCanvas.gameTick % 5 == 0) {
            if (this.cf == 9 || this.cf == 10 || this.cf == 11) {
               SoundMn.gI();
               SoundMn.charPunch();
               return;
            }

            SoundMn.gI();
            SoundMn.charPunch();
         }
      }
   }

   private boolean getMe() {
      return false;
   }

   public void update() {
      if (!this.getMe()) {
         if (this.isMafuba) {
            this.cf = 23;
            this.countMafuba++;
            if (this.countMafuba > 150) {
               this.isMafuba = false;
            }
         } else {
            this.countMafuba = 0;
            if (!this.isHide) {
               if (!this.isMabuHold) {
                  if (this.petFollow != null) {
                     if (GameCanvas.gameTick % 3 == 0) {
                        if (myCharz().cdir == 1) {
                           this.petFollow.cmtoX = this.cx - 20;
                        }

                        if (myCharz().cdir == -1) {
                           this.petFollow.cmtoX = this.cx + 20;
                        }

                        this.petFollow.cmtoY = this.cy - 40;
                        if (this.petFollow.cmx > this.cx) {
                           this.petFollow.dir = -1;
                        } else {
                           this.petFollow.dir = 1;
                        }

                        if (this.petFollow.cmtoX < 100) {
                           this.petFollow.cmtoX = 100;
                        }

                        if (this.petFollow.cmtoX > TileMap.pxw - 100) {
                           this.petFollow.cmtoX = TileMap.pxw - 100;
                        }
                     }

                     this.petFollow.update();
                  }

                  if (!this.me
                     && this.cHP <= 0L
                     && this.clanID != -100
                     && this.statusMe != 14
                     && this.statusMe != 5) {
                     this.startDie((short)this.cx, (short)this.cy);
                  }

                  if (this.isInjureHp) {
                     this.twHp++;
                     if (this.twHp == 20) {
                        this.twHp = 0;
                        this.isInjureHp = false;
                     }
                  } else if (this.dHP > this.cHP) {
                     long dHPValue;
                     if ((dHPValue = this.dHP - this.cHP >> 1) < 1L) {
                        dHPValue = 1L;
                     }

                     this.dHP -= dHPValue;
                  } else {
                     this.dHP = this.cHP;
                  }

                  if (this.secondPower != 0) {
                     this.currS = System.currentTimeMillis();
                     if (this.currS - this.lastS >= 1000L) {
                        this.lastS = System.currentTimeMillis();
                        this.secondPower--;
                     }
                  }

                  if (this.isPaintNewSkill) {
                     if (GameCanvas.timeNow > this.timeReset_newSkill || this.statusMe == 14 || this.statusMe == 5) {
                        this.timeReset_newSkill = 0L;
                        this.isPaintNewSkill = false;
                     }

                     this.UpdSkillPaint_NEW();
                     if (this.isShadown) {
                        this.updateShadown();
                     }
                  } else {
                     if (this.sleepEff && GameCanvas.gameTick % 10 == 0) {
                        EffecMn.addEff(new Effect(41, this.cx, this.cy, 3, 1, 1));
                     }

                     if (this.huytSao) {
                        this.huytSao = false;
                        EffecMn.addEff(new Effect(39, this.cx, this.cy, 3, 3, 1));
                     }

                     if (this.blindEff && GameCanvas.gameTick % 5 == 0) {
                        ServerEffect.addServerEffect(113, ((nro.Char) (this)), 1);
                     }

                     if (this.protectEff) {
                        int y = this.cH_new + 73;
                        if (GameCanvas.gameTick % 5 == 0) {
                           this.eProtect = new Effect(33, this.cx, y, 3, 3, 1);
                        }

                        if (this.eProtect != null) {
                           this.eProtect.update();
                           this.eProtect.x = this.cx;
                           this.eProtect.y = y;
                        }
                     }

                     if (this.danhHieuEff) {
                        String text;
                        if (this.eDanhHieu == null && (text = (String)GameCanvas.danhHieu.get(String.valueOf(this.charID))) != null) {
                           String[] parts;
                           short id = Short.parseShort((parts = Res.split(text.trim(), ",", 0))[0]);
                           short shortValue = Short.parseShort(parts[1]);
                           this.eDanhHieu = new Effect(id, this.cx, this.cH_new + 73, 1, -1, -1);
                           this.eDanhHieu.timeExist = (long)(shortValue * 1000) + mSystem.currentTimeMillis();
                        }

                        if (this.eDanhHieu != null) {
                           this.eDanhHieu.update();
                           this.eDanhHieu.x = this.cx;
                           this.eDanhHieu.y = this.cH_new;
                           if (this.eDanhHieu.timeExist <= mSystem.currentTimeMillis()) {
                              this.eDanhHieu = null;
                              GameCanvas.danhHieu.remove(String.valueOf(this.charID));
                           }
                        }
                     }

                     if (this.charFocus != null && this.charFocus.cy < 0) {
                        this.charFocus = null;
                     }

                     if (this.isFusion) {
                        this.tFusion++;
                     }

                     if (this.isNhapThe && GameCanvas.gameTick % 25 == 0) {
                        ServerEffect.addServerEffect(114, ((nro.Char) (this)), 1);
                     }

                     if (this.isSetPos) {
                        this.tpos++;
                        if (this.tpos == 1) {
                           this.tpos = 0;
                           this.isSetPos = false;
                           this.cx = this.xPos;
                           this.cy = this.yPos;
                           this.cp1 = this.cp2 = this.cp3 = 0;
                           Res.outz("set pox x= " + this.xPos + " y= " + this.yPos);
                           if (this.typePos == 1) {
                              if (this.me) {
                                 this.cxSend = this.cx;
                                 this.cySend = this.cy;
                              }

                              this.currentMovePoint = null;
                              this.telePortSkill = false;
                              ServerEffect.addServerEffect(173, this.cx, this.cy, 1);
                           } else {
                              ServerEffect.addServerEffect(60, this.cx, this.cy, 1);
                           }

                           if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
                              this.statusMe = 1;
                              return;
                           }

                           this.statusMe = 4;
                        }
                     } else {
                        this.soundUpdate();
                        if (!this.stone) {
                           if (this.isFreez) {
                              if (GameCanvas.gameTick % 5 == 0) {
                                 ServerEffect.addServerEffect(113, this.cx, this.cy, 1);
                              }

                              this.cf = 23;
                              long lastFreezValue;
                              if ((lastFreezValue = System.currentTimeMillis()) - this.lastFreez >= 1000L) {
                                 this.freezSeconds--;
                                 this.lastFreez = lastFreezValue;
                                 if (this.freezSeconds < 0) {
                                    this.isFreez = false;
                                    this.seconds = 0;
                                    if (this.me) {
                                       myCharz().isLockMove = false;
                                       GameScr.gI().dem = 0;
                                       GameScr.gI().isFreez = false;
                                    }
                                 }
                              }

                              if (TileMap.tileTypeAt(this.cx / TileMap.size, this.cy / TileMap.size)
                                 == 0) {
                                 this.ty++;
                                 this.wt++;
                                 this.fy = this.fy + (!this.wy ? 1 : -1);
                                 if (this.wt == 10) {
                                    this.wt = 0;
                                    this.wy = !this.wy;
                                 }
                              }
                           } else if (this.isWaitMonkey) {
                              this.isLockMove = true;
                              this.cf = 17;
                              if (GameCanvas.gameTick % 5 == 0) {
                                 ServerEffect.addServerEffect(154, this.cx, this.cy - 10, 2);
                              }

                              if (GameCanvas.gameTick % 5 == 0) {
                                 ServerEffect.addServerEffect(1, this.cx, this.cy + 10, 1);
                              }

                              this.chargeCount++;
                              if (this.chargeCount == 500) {
                                 this.isWaitMonkey = false;
                                 this.isLockMove = false;
                              }
                           } else if (this.isStandAndCharge) {
                              this.chargeCount++;
                              boolean condition = !TileMap.tileTypeAt(myCharz().cx, myCharz().cy, 2);
                              this.updateEffect();
                              this.updateSkillPaint();
                              this.moveFast = null;
                              this.currentMovePoint = null;
                              this.cf = 17;
                              if (condition && this.cgender != 2) {
                                 this.cf = 12;
                              }

                              if (this.cgender == 2) {
                                 if (TileMap.mapID == 170) {
                                    if (GameCanvas.gameTick % 2 == 0) {
                                       if (this.cdir == 1) {
                                          ServerEffect.addServerEffect(
                                             70, this.cx - 18, this.cy - this.ch / 2 + 8, 1
                                          );
                                          ServerEffect.addServerEffect(
                                             70, this.cx + 23, this.cy - this.ch / 2 + 15, 1
                                          );
                                       } else {
                                          ServerEffect.addServerEffect(
                                             70, this.cx + 18, this.cy - this.ch / 2 + 8, 1
                                          );
                                          ServerEffect.addServerEffect(
                                             70, this.cx - 23, this.cy - this.ch / 2 + 15, 1
                                          );
                                       }
                                    }
                                 } else {
                                    if (GameCanvas.gameTick % 3 == 0) {
                                       ServerEffect.addServerEffect(154, this.cx, this.cy - this.ch / 2 + 10, 1);
                                    }

                                    if (GameCanvas.gameTick % 5 == 0) {
                                       ServerEffect.addServerEffect(
                                          114,
                                          this.cx + Res.random(-20, 20),
                                          this.cy + Res.random(-20, 20),
                                          1
                                       );
                                    }
                                 }
                              }

                              if (this.cgender == 1 && GameCanvas.gameTick % 2 == 0) {
                                 if (this.cdir == 1) {
                                    ServerEffect.addServerEffect(70, this.cx - 18, this.cy - this.ch / 2 + 8, 1);
                                    ServerEffect.addServerEffect(70, this.cx + 23, this.cy - this.ch / 2 + 15, 1);
                                 } else {
                                    ServerEffect.addServerEffect(70, this.cx + 18, this.cy - this.ch / 2 + 8, 1);
                                    ServerEffect.addServerEffect(70, this.cx - 23, this.cy - this.ch / 2 + 15, 1);
                                 }
                              }

                              if (this.cgender == 0 && GameCanvas.gameTick % 2 == 0) {
                                 if (this.cdir == 1) {
                                    ServerEffect.addServerEffect(70, this.cx - 18, this.cy - this.ch / 2 + 8, 1);
                                    ServerEffect.addServerEffect(70, this.cx + 23, this.cy - this.ch / 2 + 15, 1);
                                 } else {
                                    ServerEffect.addServerEffect(70, this.cx + 18, this.cy - this.ch / 2 + 8, 1);
                                    ServerEffect.addServerEffect(70, this.cx - 23, this.cy - this.ch / 2 + 15, 1);
                                 }
                              }

                              this.cur = System.currentTimeMillis();
                              Res.outz("  7.5 gong namekLazer " + this.cName + "_" + this.cgender);
                              if (this.cur - this.last > (long)this.seconds
                                 || this.cur - this.last > 10000L) {
                                 Res.outz("<*> 8  namekLazer gong xong " + this.cName);
                                 this.stopUseChargeSkill();
                                 this.isStandAndCharge = false;
                                 int skillId;
                                 if (this.me) {
                                    if (this.cgender == 2) {
                                       Res.outz("<*> 9 [me] xay da xong  " + myCharz().myskill.skillId);
                                       myCharz()
                                          .setSkillPaint(GameScr.sks[myCharz().myskill.skillId], !condition ? 0 : 1);
                                    }

                                    if (this.cgender == 1) {
                                       Res.outz("<*> 9 [me] namec xong " + myCharz().myskill.skillId);
                                       this.isCreateDark = true;
                                       myCharz()
                                          .setSkillPaint(GameScr.sks[myCharz().myskill.skillId], !condition ? 0 : 1);
                                    }

                                    if (this.cgender == 0) {
                                       Res.outz("<*> 9 [me] namec xong " + myCharz().myskill.skillId);
                                       myCharz()
                                          .setSkillPaint(GameScr.sks[myCharz().myskill.skillId], !condition ? 0 : 1);
                                    }

                                    if (myCharz().myskill.skillId >= 77
                                       && myCharz().myskill.skillId <= 83) {
                                       Service.gI().skill_not_focus((byte)4);
                                    }

                                    skillId = myCharz().myskill.skillId;
                                 } else {
                                    if (this.cgender == 2) {
                                       this.setSkillPaint(GameScr.sks[this.skillTemplateId], !condition ? 0 : 1);
                                       Res.outz("<*> 10 xay da xong 111   " + this.skillTemplateId);
                                    }

                                    if (this.cgender == 1) {
                                       this.setSkillPaint(GameScr.sks[this.skillTemplateId], !condition ? 0 : 1);
                                       Res.outz("<*> 10 C_NAMEC xong 222   " + this.skillTemplateId);
                                    }

                                    if (this.cgender == 0) {
                                       this.setSkillPaint(GameScr.sks[this.skillTemplateId], !condition ? 0 : 1);
                                       Res.outz("<*> 10  C_TRAIDAT xong 333   " + this.skillTemplateId);
                                    }

                                    skillId = this.skillTemplateId;
                                 }

                                 if (this.cgender == 2 && this.statusMe != 14 && this.statusMe != 5 && (skillId < 77 || skillId > 83)) {
                                    GameScr.gI().activeSuperPower(this.cx, this.cy);
                                 }

                                 Res.outz("<*> 11 Hoàn thành skill not focus -  STAND");
                              }

                              this.chargeCount++;
                              if (this.chargeCount == 500) {
                                 this.stopUseChargeSkill();
                              }
                           } else if (this.isFlyAndCharge) {
                              this.updateEffect();
                              this.updateSkillPaint();
                              this.moveFast = null;
                              this.currentMovePoint = null;
                              this.posDisY++;
                              if (TileMap.tileTypeAt(this.cx, this.cy - this.ch, 8192)) {
                                 this.stopUseChargeSkill();
                              } else {
                                 if (this.posDisY == 20) {
                                    this.last = System.currentTimeMillis();
                                 }

                                 Res.outz("<*> 11 gồng qua cau kinh khi  " + this.cName);
                                 if (this.posDisY <= 20) {
                                    if (this.statusMe != 14) {
                                       this.statusMe = 3;
                                    }

                                    this.cvy = -3;
                                    this.cy = this.cy + this.cvy;
                                    this.cf = 7;
                                 } else {
                                    this.cur = System.currentTimeMillis();
                                    if (this.cur - this.last <= (long)this.seconds
                                       && this.cur - this.last <= 10000L) {
                                       this.cf = 32;
                                       if (this.cgender == 0 && GameCanvas.gameTick % 3 == 0) {
                                          ServerEffect.addServerEffect(153, this.cx, this.cy - this.ch, 2);
                                       }

                                       if (TileMap.mapID == 170
                                          && (this.cgender == 2 || this.cgender == 1)
                                          && GameCanvas.gameTick % 3 == 0) {
                                          ServerEffect.addServerEffect(153, this.cx, this.cy - this.ch, 2);
                                       }

                                       this.chargeCount++;
                                       if (this.chargeCount == 500) {
                                          this.stopUseChargeSkill();
                                       }
                                    } else {
                                       Res.outz("<*> 12 kết thúc skill  qua cau kinh khi \tFLY " + this.cName);
                                       this.isFlyAndCharge = false;
                                       if (this.me) {
                                          this.isCreateDark = true;
                                          boolean condition2 = TileMap.tileTypeAt(
                                             myCharz().cx, myCharz().cy, 2
                                          );
                                          this.isUseSkillAfterCharge = true;
                                          this.setSkillPaint(
                                             GameScr.sks[myCharz().myskill.skillId], condition2 ? 0 : 1
                                          );
                                       } else {
                                          if (TileMap.mapID == 170) {
                                             this.isCreateDark = true;
                                             this.isUseSkillAfterCharge = true;
                                             boolean condition3 = TileMap.tileTypeAt(this.cx, this.cy, 2);
                                             this.setSkillPaint(GameScr.sks[this.skillTemplateId], condition3 ? 0 : 1);
                                          }
                                       }
                                    }
                                 }
                              }
                           } else {
                              if (this.me && GameCanvas.isTouch) {
                                 if (this.charFocus != null
                                    && this.charFocus.charID >= 0
                                    && this.charFocus.cx > 100
                                    && this.charFocus.cx < TileMap.pxw - 100
                                    && this.isInEnterOnlinePoint() == null
                                    && this.isInEnterOfflinePoint() == null
                                    && !this.isAttacPlayerStatus()
                                    && TileMap.mapID != 51
                                    && TileMap.mapID != 52
                                    && GameCanvas.panel.vPlayerMenu.size() > 0
                                    && GameScr.gI().popUpYesNo == null) {
                                    int value = Math.abs(this.cx - this.charFocus.cx);
                                    int value2 = Math.abs(this.cy - this.charFocus.cy);
                                    if (value < 60 && value2 < 40) {
                                       if (this.cmdMenu == null) {
                                          this.cmdMenu = new Command(mResources.MENU, 11111);
                                          this.cmdMenu.isPlaySoundButton = false;
                                       }

                                       this.cmdMenu.x = this.charFocus.cx - GameScr.cmx;
                                       this.cmdMenu.y = this.charFocus.cy
                                          - this.charFocus.ch
                                          - 30
                                          - GameScr.cmy;
                                    } else {
                                       this.cmdMenu = null;
                                    }
                                 } else {
                                    this.cmdMenu = null;
                                 }
                              }

                              if (this.isShadown) {
                                 this.updateShadown();
                              }

                              if (!this.isTeleport) {
                                 if (this.chatInfo != null) {
                                    this.chatInfo.update();
                                 }

                                 if (this.shadowLife > 0) {
                                    this.shadowLife--;
                                 }

                                 if (this.resultTest > 0 && GameCanvas.gameTick % 2 == 0) {
                                    this.resultTest--;
                                    if (this.resultTest == 30 || this.resultTest == 60) {
                                       this.resultTest = 0;
                                    }
                                 }

                                 this.updateSkillPaint();
                                 if (this.mobMe != null) {
                                    this.updateMobMe();
                                 }

                                 if (this.arr != null) {
                                    this.arr.update();
                                 }

                                 if (this.dart != null) {
                                    this.dart.update();
                                 }

                                 this.updateEffect();
                                 if (this.holdEffID != 0) {
                                    if (GameCanvas.gameTick % 5 == 0) {
                                       EffecMn.addEff(new Effect(32, this.cx, this.cy + 24, 3, 5, 1));
                                    }
                                 } else if (!this.blindEff) {
                                    if (!this.sleepEff) {
                                       if (this.holder) {
                                          if (this.charHold != null
                                             && (this.charHold.statusMe == 14 || this.charHold.statusMe == 5)) {
                                             this.removeHoleEff();
                                          }

                                          if (this.mobHold != null && this.mobHold.status == 1) {
                                             this.removeHoleEff();
                                          }

                                          if (this.me && this.statusMe == 2 && this.currentMovePoint != null) {
                                             this.holder = false;
                                             this.charHold = null;
                                             this.mobHold = null;
                                          }

                                          if (TileMap.tileTypeAt(this.cx, this.cy, 2)) {
                                             this.cf = 16;
                                          } else {
                                             this.cf = 31;
                                          }
                                       } else {
                                          if (this.cHP > 0L) {
                                             for (int effIndex = 0; effIndex < this.vEff.size(); effIndex++) {
                                                EffectChar effValue = (EffectChar)this.vEff.elementAt(effIndex);
                                                if (GameCanvas.isEff1) {
                                                   this.cHP = this.cHP + (long)effValue.param;
                                                   this.cMP = this.cMP + (long)effValue.param;
                                                }
                                             }

                                             if (this.eff5BuffHp > 0 && GameCanvas.isEff2) {
                                                this.cHP = this.cHP + (long)this.eff5BuffHp;
                                             }

                                             if (this.eff5BuffMp > 0 && GameCanvas.isEff2) {
                                                this.cMP = this.cMP + (long)this.eff5BuffMp;
                                             }

                                             if (this.cHP > this.cHPFull) {
                                                this.cHP = this.cHPFull;
                                             }

                                             if (this.cMP > this.cMPFull) {
                                                this.cMP = this.cMPFull;
                                             }
                                          }

                                          if (this.cmtoChar) {
                                             GameScr.cmtoX = this.cx - GameScr.gW2;
                                             GameScr.cmtoY = this.cy - GameScr.gH23 - 1;
                                             if (!GameCanvas.isTouchControl) {
                                                GameScr.cmtoX = GameScr.cmtoX + GameScr.gW6 * this.cdir;
                                             }
                                          }

                                          this.tick = (this.tick + 1) % 100;
                                          if (this.me) {
                                             if (this.charFocus != null && !GameScr.vCharInMap.contains(this.charFocus)) {
                                                this.charFocus = null;
                                             }

                                             if (this.cx < 10) {
                                                this.cvx = 0;
                                                this.cx = 10;
                                             } else if (this.cx > TileMap.pxw - 10) {
                                                this.cx = TileMap.pxw - 10;
                                                this.cvx = 0;
                                             }

                                             if (!ischangingMap && this.isInWaypoint()) {
                                                Service.gI().charMove();
                                                if (TileMap.isTrainingMap()) {
                                                   Service.gI().getMapOffline();
                                                   ischangingMap = true;
                                                } else {
                                                   Service.gI().requestChangeMap();
                                                }

                                                isLockKey = true;
                                                ischangingMap = true;
                                                GameCanvas.clearKeyHold();
                                                GameCanvas.clearKeyPressed();
                                                InfoDlg.showWait();
                                                return;
                                             }

                                             if (this.statusMe != 4
                                                && Res.abs(this.cx - this.cxSend)
                                                      + Res.abs(this.cy - this.cySend)
                                                   >= 70
                                                && this.cy - this.cySend <= 0
                                                && this.me) {
                                                Service.gI().charMove();
                                             }

                                             if (this.isLockMove) {
                                                this.currentMovePoint = null;
                                             }

                                             if (this.currentMovePoint != null) {
                                                if (abs(this.cx - this.currentMovePoint.xEnd) <= 16
                                                   && abs(this.cy - this.currentMovePoint.yEnd) <= 16) {
                                                   this.cx = (this.currentMovePoint.xEnd + this.cx) / 2;
                                                   this.cy = this.currentMovePoint.yEnd;
                                                   this.currentMovePoint = null;
                                                   GameScr.instance.clickMoving = false;
                                                   this.checkPerformEndMovePointAction();
                                                   this.cvx = this.cvy = 0;
                                                   if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
                                                      this.statusMe = 1;
                                                   } else {
                                                      this.setCharFallFromJump();
                                                      this.checkDelayFallIfTooHigh();
                                                   }

                                                   Service.gI().charMove();
                                                } else {
                                                   this.cdir = this.currentMovePoint.xEnd > this.cx ? 1 : -1;
                                                   if (TileMap.tileTypeAt(this.cx, this.cy, 2)) {
                                                      this.statusMe = 2;
                                                      if (this.currentMovePoint != null) {
                                                         this.cvx = this.cspeed * this.cdir;
                                                         this.cvy = 0;
                                                      }

                                                      if (abs(this.cx - this.currentMovePoint.xEnd) <= 10) {
                                                         if (this.currentMovePoint.yEnd > this.cy) {
                                                            if (this.currentMovePoint.yEnd > this.cy) {
                                                               boolean condition4 = false;
                                                               byte byteValue;
                                                               if (this.cdir == 1) {
                                                                  byteValue = 1;
                                                               } else {
                                                                  byteValue = -1;
                                                               }

                                                               for (int index = 0; index < 2; index++) {
                                                                  if (TileMap.tileTypeAt(
                                                                     this.currentMovePoint.xEnd + this.chw * byteValue,
                                                                     this.cy + this.chh * index,
                                                                     2
                                                                  )) {
                                                                     condition4 = true;
                                                                     break;
                                                                  }
                                                               }

                                                               if (condition4) {
                                                                  this.currentMovePoint = null;
                                                                  GameScr.instance.clickMoving = false;
                                                                  this.statusMe = 1;
                                                                  this.cvx = this.cvy = 0;
                                                                  this.checkPerformEndMovePointAction();
                                                               } else {
                                                                  SoundMn.gI();
                                                                  SoundMn.charJump();
                                                                  this.cx = this.currentMovePoint.xEnd;
                                                                  this.statusMe = 10;
                                                                  this.cvy = -5;
                                                                  this.cvx = 0;
                                                                  Res.outz("Jum lun");
                                                               }
                                                            }
                                                         } else {
                                                            SoundMn.gI();
                                                            SoundMn.charJump();
                                                            this.cx = this.currentMovePoint.xEnd;
                                                            this.statusMe = 10;
                                                            this.cvy = -5;
                                                            this.cvx = 0;
                                                         }
                                                      }

                                                      if (this.cdir == 1) {
                                                         if (TileMap.tileTypeAt(
                                                            this.cx + this.chw, this.cy - this.chh, 4
                                                         )) {
                                                            this.cvx = this.cspeed * this.cdir;
                                                            this.statusMe = 10;
                                                            this.cvy = -5;
                                                         }
                                                      } else if (TileMap.tileTypeAt(
                                                         this.cx - this.chw - 1, this.cy - this.chh, 8
                                                      )) {
                                                         this.cvx = this.cspeed * this.cdir;
                                                         this.statusMe = 10;
                                                         this.cvy = -5;
                                                      }
                                                   } else {
                                                      if (this.currentMovePoint.yEnd < this.cy + 10) {
                                                         this.statusMe = 10;
                                                         this.cvy = -5;
                                                         if (abs(this.cy - this.currentMovePoint.yEnd) <= 10) {
                                                            this.cy = this.currentMovePoint.yEnd;
                                                            this.cvy = 0;
                                                         }

                                                         if (abs(this.cx - this.currentMovePoint.xEnd) <= 10) {
                                                            this.cvx = 0;
                                                         } else {
                                                            this.cvx = this.cspeed * this.cdir;
                                                         }
                                                      } else if (TileMap.tileTypeAt(this.cx, this.cy, 2)) {
                                                         this.currentMovePoint = null;
                                                         GameScr.instance.clickMoving = false;
                                                         this.statusMe = 1;
                                                         this.cvx = this.cvy = 0;
                                                         this.checkPerformEndMovePointAction();
                                                      } else {
                                                         if (this.statusMe == 10 || this.statusMe == 2) {
                                                            this.cvy = 0;
                                                         }

                                                         this.statusMe = 4;
                                                      }

                                                      if (this.currentMovePoint.yEnd > this.cy) {
                                                         if (this.cdir == 1) {
                                                            if (TileMap.tileTypeAt(
                                                               this.cx + this.chw, this.cy - this.chh, 4
                                                            )) {
                                                               this.cvx = this.cvy = 0;
                                                               this.statusMe = 4;
                                                               this.currentMovePoint = null;
                                                               GameScr.instance.clickMoving = false;
                                                               this.checkPerformEndMovePointAction();
                                                            }
                                                         } else if (TileMap.tileTypeAt(
                                                            this.cx - this.chw - 1,
                                                            this.cy - this.chh,
                                                            8
                                                         )) {
                                                            this.cvx = this.cvy = 0;
                                                            this.statusMe = 4;
                                                            this.currentMovePoint = null;
                                                            GameScr.instance.clickMoving = false;
                                                            this.checkPerformEndMovePointAction();
                                                         }
                                                      }
                                                   }
                                                }
                                             }

                                             this.searchFocus();
                                          } else {
                                             this.checkHideCharName();
                                             if (this.statusMe == 1 || this.statusMe == 6) {
                                                boolean condition5;
                                                label1044: {
                                                   condition5 = false;
                                                   if (this.currentMovePoint != null) {
                                                      if (abs(this.currentMovePoint.xEnd - this.cx) < 17
                                                         && abs(this.currentMovePoint.yEnd - this.cy) < 25) {
                                                         this.cx = this.currentMovePoint.xEnd;
                                                         this.cy = this.currentMovePoint.yEnd;
                                                         this.currentMovePoint = null;
                                                         if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
                                                            this.statusMe = 1;
                                                            this.cp3 = 0;
                                                            GameCanvas.gI()
                                                               .startDust(-1, this.cx - -8, this.cy);
                                                            GameCanvas.gI()
                                                               .startDust(1, this.cx - 8, this.cy);
                                                         } else {
                                                            this.statusMe = 4;
                                                            this.cvy = 0;
                                                            this.cp1 = 0;
                                                         }

                                                         condition5 = true;
                                                         break label1044;
                                                      }

                                                      if (this.statusBeforeNothing != 10 && this.cf != 8 || this.vMovePoints.size() <= 0) {
                                                         if (this.cy == this.currentMovePoint.yEnd) {
                                                            if (this.cx != this.currentMovePoint.xEnd) {
                                                               this.cx = (this.cx + this.currentMovePoint.xEnd) / 2;
                                                               this.cf = GameCanvas.gameTick % 5 + 2;
                                                            }
                                                         } else if (this.cy < this.currentMovePoint.yEnd) {
                                                            this.cf = 12;
                                                            this.cx = (this.cx + this.currentMovePoint.xEnd) / 2;
                                                            if (this.cvy < 0) {
                                                               this.cvy = 0;
                                                            }

                                                            this.cy = this.cy + this.cvy;
                                                            if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
                                                               GameCanvas.gI()
                                                                  .startDust(-1, this.cx - -8, this.cy);
                                                               GameCanvas.gI()
                                                                  .startDust(1, this.cx - 8, this.cy);
                                                            }

                                                            this.cvy++;
                                                            if (this.cvy > 16) {
                                                               this.cy = (this.cy + this.currentMovePoint.yEnd) / 2;
                                                            }
                                                         } else {
                                                            this.cf = 7;
                                                            this.cx = (this.cx + this.currentMovePoint.xEnd) / 2;
                                                            this.cy = (this.cy + this.currentMovePoint.yEnd) / 2;
                                                         }
                                                         break label1044;
                                                      }
                                                   }

                                                   condition5 = true;
                                                }

                                                if (condition5 && this.vMovePoints.size() > 0) {
                                                   this.currentMovePoint = (MovePoint)this.vMovePoints.firstElement();
                                                   this.vMovePoints.removeElementAt(0);
                                                   if (this.currentMovePoint.status == 2) {
                                                      if ((TileMap.tileTypeAtPixel(this.cx, this.cy + 12) & 2) != 2) {
                                                         this.statusMe = 10;
                                                         this.cp1 = 0;
                                                         this.cp2 = 0;
                                                         this.cvx = -(this.cx - this.currentMovePoint.xEnd) / 10;
                                                         this.cvy = -(this.cy - this.currentMovePoint.yEnd) / 10;
                                                         if (this.cx - this.currentMovePoint.xEnd > 0) {
                                                            this.cdir = -1;
                                                         } else if (this.cx - this.currentMovePoint.xEnd < 0) {
                                                            this.cdir = 1;
                                                         }
                                                      } else {
                                                         this.statusMe = 2;
                                                         if (this.cx - this.currentMovePoint.xEnd > 0) {
                                                            this.cdir = -1;
                                                         } else if (this.cx - this.currentMovePoint.xEnd < 0) {
                                                            this.cdir = 1;
                                                         }

                                                         this.cvx = this.cspeed * this.cdir;
                                                         this.cvy = 0;
                                                      }
                                                   } else if (this.currentMovePoint.status == 3) {
                                                      if ((TileMap.tileTypeAtPixel(this.cx, this.cy + 23) & 2) != 2) {
                                                         this.statusMe = 10;
                                                         this.cp1 = 0;
                                                         this.cp2 = 0;
                                                         this.cvx = -(this.cx - this.currentMovePoint.xEnd) / 10;
                                                         this.cvy = -(this.cy - this.currentMovePoint.yEnd) / 10;
                                                         if (this.cx - this.currentMovePoint.xEnd > 0) {
                                                            this.cdir = -1;
                                                         } else if (this.cx - this.currentMovePoint.xEnd < 0) {
                                                            this.cdir = 1;
                                                         }
                                                      } else {
                                                         this.statusMe = 3;
                                                         GameCanvas.gI()
                                                            .startDust(-1, this.cx - -8, this.cy);
                                                         GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
                                                         if (this.cx - this.currentMovePoint.xEnd > 0) {
                                                            this.cdir = -1;
                                                         } else if (this.cx - this.currentMovePoint.xEnd < 0) {
                                                            this.cdir = 1;
                                                         }

                                                         this.cvx = abs(
                                                               this.cx - this.currentMovePoint.xEnd
                                                            )
                                                            / 10
                                                            * this.cdir;
                                                         this.cvy = -10;
                                                      }
                                                   } else if (this.currentMovePoint.status == 4) {
                                                      this.statusMe = 4;
                                                      if (this.cx - this.currentMovePoint.xEnd > 0) {
                                                         this.cdir = -1;
                                                      } else if (this.cx - this.currentMovePoint.xEnd < 0) {
                                                         this.cdir = 1;
                                                      }

                                                      this.cvx = abs(this.cx - this.currentMovePoint.xEnd)
                                                         / 9
                                                         * this.cdir;
                                                      this.cvy = 0;
                                                   } else {
                                                      this.cx = this.currentMovePoint.xEnd;
                                                      this.cy = this.currentMovePoint.yEnd;
                                                      this.currentMovePoint = null;
                                                   }
                                                }
                                             }
                                          }

                                          switch (this.statusMe) {
                                             case 1:
                                                this.updateCharStand();
                                                break;
                                             case 2:
                                                this.updateCharRun();
                                                break;
                                             case 3:
                                                this.updateCharJump();
                                                break;
                                             case 4:
                                                this.updateCharFall();
                                                break;
                                             case 5:
                                                this.updateCharDeadFly();
                                                break;
                                             case 6:
                                                if (this.isInjure <= 0) {
                                                   this.cf = 0;
                                                } else if (this.statusBeforeNothing == 10) {
                                                   this.cx = this.cx + this.cvx;
                                                } else if (this.cf <= 1) {
                                                   this.cp1++;
                                                   if (this.cp1 > 6) {
                                                      this.cf = 0;
                                                   } else {
                                                      this.cf = 1;
                                                   }

                                                   if (this.cp1 > 10) {
                                                      this.cp1 = 0;
                                                   }
                                                }

                                                if (this.cf != 7
                                                   && this.cf != 12
                                                   && (TileMap.tileTypeAtPixel(this.cx, this.cy + 1) & 2) != 2) {
                                                   this.cvx = 0;
                                                   this.cvy = 0;
                                                   this.statusMe = 4;
                                                   this.cf = 7;
                                                }

                                                if (!this.me) {
                                                   this.cp3++;
                                                   if (this.cp3 > 10) {
                                                      if ((TileMap.tileTypeAtPixel(this.cx, this.cy + 1) & 2) != 2) {
                                                         this.cy += 5;
                                                      } else {
                                                         this.cf = 0;
                                                      }
                                                   }

                                                   if (this.cp3 > 50) {
                                                      this.cp3 = 0;
                                                      this.currentMovePoint = null;
                                                   }
                                                }
                                             case 7:
                                             case 8:
                                             case 11:
                                             case 13:
                                             case 15:
                                             default:
                                                break;
                                             case 9:
                                                this.updateCharAutoJump();
                                                break;
                                             case 10:
                                                this.updateCharFly();
                                                break;
                                             case 12:
                                                this.updateSkillStand();
                                                break;
                                             case 14:
                                                this.cp1++;
                                                if (this.cp1 > 30) {
                                                   this.cp1 = 0;
                                                }

                                                if (this.cp1 % 15 < 5) {
                                                   this.cf = 0;
                                                } else {
                                                   this.cf = 1;
                                                }
                                                break;
                                             case 16:
                                                this.updateResetPoint();
                                          }

                                          if (this.isInjure > 0) {
                                             this.cf = 23;
                                             this.isInjure--;
                                          }

                                          if (this.wdx != 0 || this.wdy != 0) {
                                             this.startDie(this.wdx, this.wdy);
                                             this.wdx = 0;
                                             this.wdy = 0;
                                          }

                                          if (this.moveFast != null) {
                                             if (this.moveFast[0] == 0) {
                                                this.moveFast[0]++;
                                                ServerEffect.addServerEffect(60, ((nro.Char) (this)), 1);
                                             } else if (this.moveFast[0] < 10) {
                                                this.moveFast[0]++;
                                             } else {
                                                this.cx = this.moveFast[1];
                                                this.cy = this.moveFast[2];
                                                this.moveFast = null;
                                                ServerEffect.addServerEffect(60, ((nro.Char) (this)), 1);
                                                if (this.me) {
                                                   if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) != 2) {
                                                      this.statusMe = 4;
                                                      myCharz().setAutoSkillPaint(GameScr.sks[38], 1);
                                                   } else {
                                                      Service.gI().charMove();
                                                      myCharz().setAutoSkillPaint(GameScr.sks[38], 0);
                                                   }
                                                }
                                             }
                                          }

                                          if (this.statusMe != 10) {
                                             this.fy = 0;
                                          }

                                          if (this.isCharge) {
                                             this.cf = 17;
                                             if (GameCanvas.gameTick % 4 == 0) {
                                                ServerEffect.addServerEffect(1, this.cx, this.cy + GameCanvas.transY, 1);
                                             }

                                             long lastValue;
                                             if (this.me && (lastValue = System.currentTimeMillis()) - this.last >= 1000L) {
                                                Res.outz("%= " + this.myskill.damage);
                                                this.last = lastValue;
                                                this.cHP = this.cHP
                                                   + this.cHPFull * (long)this.myskill.damage / 100L;
                                                this.cMP = this.cMP
                                                   + this.cMPFull * (long)this.myskill.damage / 100L;
                                                if (this.cHP < this.cHPFull) {
                                                   GameScr.startFlyText(
                                                      "+"
                                                         + this.cHPFull * (long)this.myskill.damage / 100L
                                                         + " "
                                                         + mResources.HP,
                                                      this.cx,
                                                      this.cy - this.ch - 20,
                                                      0,
                                                      -1,
                                                      9
                                                   );
                                                }

                                                if (this.cMP < this.cMPFull) {
                                                   GameScr.startFlyText(
                                                      "+"
                                                         + this.cMPFull * (long)this.myskill.damage / 100L
                                                         + " "
                                                         + mResources.KI,
                                                      this.cx,
                                                      this.cy - this.ch - 20,
                                                      0,
                                                      -2,
                                                      10
                                                   );
                                                }

                                                Service.gI().skill_not_focus((byte)2);
                                             }
                                          }

                                          if (this.isFlyUp) {
                                             if (this.me) {
                                                isLockKey = true;
                                                this.statusMe = 3;
                                                this.cvy = -8;
                                                if (this.cy <= TileMap.pxh - 240) {
                                                   this.isFlyUp = false;
                                                   isLockKey = false;
                                                   this.statusMe = 4;
                                                }
                                             } else {
                                                this.statusMe = 3;
                                                this.cvy = -8;
                                                if (this.cy <= TileMap.pxh - 240) {
                                                   this.cvy = 0;
                                                   this.isFlyUp = false;
                                                   this.cvy = 0;
                                                   this.statusMe = 1;
                                                }
                                             }
                                          }

                                          this.updateMount();
                                          this.updEffChar();
                                          this.updateEye();
                                          this.updateFHead();
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void updateEffect() {
      if (this.effPaints != null) {
         for (int effPaintIndex = 0; effPaintIndex < this.effPaints.length; effPaintIndex++) {
            if (this.effPaints[effPaintIndex] != null) {
               if (this.effPaints[effPaintIndex].eMob != null) {
                  if (!this.effPaints[effPaintIndex].isFly) {
                     this.effPaints[effPaintIndex].eMob.setInjure();
                     this.effPaints[effPaintIndex].eMob.injureBy = this;
                     if (this.me) {
                        Mob eMob = this.effPaints[effPaintIndex].eMob;
                        long longValue = myCharz().cDamFull / 2L;
                        byte byteValue = 11;
                        eMob.hpInjure = longValue - myCharz().cDamFull * (long)new Random().nextInt(11) / 100L;
                     }

                     int value = this.effPaints[effPaintIndex].eMob.h >> 1;
                     if (this.effPaints[effPaintIndex].eMob.isBigBoss()) {
                        value = this.effPaints[effPaintIndex].eMob.getY() + 20;
                     }

                     GameScr.startSplash(
                        this.effPaints[effPaintIndex].eMob.x,
                        this.effPaints[effPaintIndex].eMob.y - value,
                        this.cdir
                     );
                     this.effPaints[effPaintIndex].isFly = true;
                  }
               } else if (this.effPaints[effPaintIndex].eChar != null && !this.effPaints[effPaintIndex].isFly) {
                  if (this.effPaints[effPaintIndex].eChar.charID >= 0) {
                     this.effPaints[effPaintIndex].eChar.doInjure();
                  }

                  GameScr.startSplash(
                     this.effPaints[effPaintIndex].eChar.cx,
                     this.effPaints[effPaintIndex].eChar.cy - (this.effPaints[effPaintIndex].eChar.ch >> 1),
                     this.cdir
                  );
                  this.effPaints[effPaintIndex].isFly = true;
               }

               this.effPaints[effPaintIndex].index++;
               if (this.effPaints[effPaintIndex].index >= this.effPaints[effPaintIndex].effCharPaint.arrEfInfo.length) {
                  this.effPaints[effPaintIndex] = null;
               }
            }
         }
      }

      if (this.indexEffTask >= 0 && this.effTask != null && GameCanvas.gameTick % 2 == 0) {
         this.indexEffTask++;
         if (this.indexEffTask >= this.effTask.arrEfInfo.length) {
            this.indexEffTask = -1;
            this.effTask = null;
         }
      }
   }

   private void checkPerformEndMovePointAction() {
      if (this.endMovePointCommand != null) {
         Command endMovePointCommandValue = this.endMovePointCommand;
         this.endMovePointCommand = null;
         endMovePointCommandValue.performAction();
      }
   }

   private void checkHideCharName() {
      if (GameCanvas.gameTick % 20 == 0 && this.charID >= 0) {
         this.paintName = true;

         for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
            Char charInMap = null;

            try {
               charInMap = (Char)GameScr.vCharInMap.elementAt(charInMapIndex);
            } catch (Exception ignoredException) {
            }

            if (charInMap != null
               && !charInMap.equals(this)
               && (
                  charInMap.cy == this.cy && Res.abs(charInMap.cx - this.cx) < 35
                     || this.cy - charInMap.cy < 32
                        && this.cy - charInMap.cy > 0
                        && Res.abs(charInMap.cx - this.cx) < 24
               )) {
               this.paintName = false;
            }
         }

         for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
            Npc npc = null;

            try {
               npc = (Npc)GameScr.vNpc.elementAt(npcIndex);
            } catch (Exception ignoredException2) {
            }

            if (npc != null
               && npc.cy == this.cy
               && Res.abs(npc.cx - this.cx) < 24) {
               this.paintName = false;
            }
         }
      }
   }

   private void updateMobMe() {
      if (this.tMobMeBorn != 0) {
         this.tMobMeBorn--;
      }

      if (this.tMobMeBorn == 0) {
         this.mobMe.xFirst = this.cdir == 1 ? this.cx - 30 : this.cx + 30;
         this.mobMe.yFirst = this.cy - 60;
         int value = this.mobMe.xFirst - this.mobMe.x;
         int value2 = this.mobMe.yFirst - this.mobMe.y;
         this.mobMe.x += value / 4;
         this.mobMe.y += value2 / 4;
         this.mobMe.dir = this.cdir;
      }
   }

   private void updateSkillPaint() {
      if (this.statusMe != 14 && this.statusMe != 5) {
         if (this.skillPaint != null
            && (
               this.charFocus != null && this.isMeCanAttackOtherPlayer(this.charFocus) && this.charFocus.statusMe == 14
                  || this.mobFocus != null && this.mobFocus.status == 0
            )) {
            if (!this.me) {
               if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
                  this.statusMe = 1;
               } else {
                  this.statusMe = 6;
               }

               this.cp3 = 0;
            }

            this.indexSkill = 0;
            this.skillPaint = null;
            this.skillPaintRandomPaint = null;
            this.eff0 = this.eff1 = this.eff2 = null;
            this.i0 = this.i1 = this.i2 = 0;
            this.mobFocus = null;
            this.charFocus = null;
            this.effPaints = null;
            this.currentMovePoint = null;
            this.arr = null;
            if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) != 2) {
               this.delayFall = 5;
            }
         }

         if (this.skillPaint != null
            && this.arr == null
            && this.skillInfoPaint() != null
            && this.indexSkill >= this.skillInfoPaint().length) {
            if (!this.me) {
               if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
                  this.statusMe = 1;
               } else {
                  this.statusMe = 6;
               }

               this.cp3 = 0;
            }

            this.indexSkill = 0;
            this.skillPaint = null;
            this.skillPaintRandomPaint = null;
            this.eff0 = this.eff1 = this.eff2 = null;
            this.i0 = this.i1 = this.i2 = 0;
            this.arr = null;
            if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) != 2) {
               this.delayFall = 5;
            }
         }

         SkillInfoPaint[] skillInfoPaints;
         if ((skillInfoPaints = this.skillInfoPaint()) != null && this.indexSkill >= 0 && this.indexSkill <= skillInfoPaints.length - 1) {
            if (skillInfoPaints[this.indexSkill].effS0Id != 0) {
               this.eff0 = GameScr.efs[skillInfoPaints[this.indexSkill].effS0Id - 1];
               this.i0 = this.dx0 = this.dy0 = 0;
            }

            if (skillInfoPaints[this.indexSkill].effS1Id != 0) {
               this.eff1 = GameScr.efs[skillInfoPaints[this.indexSkill].effS1Id - 1];
               this.i1 = this.dx1 = this.dy1 = 0;
            }

            if (skillInfoPaints[this.indexSkill].effS2Id != 0) {
               this.eff2 = GameScr.efs[skillInfoPaints[this.indexSkill].effS2Id - 1];
               this.i2 = this.dx2 = this.dy2 = 0;
            }

            int skillInfoPaintIndex = this.indexSkill;
            if (skillInfoPaints != null && skillInfoPaints[skillInfoPaintIndex] != null && skillInfoPaintIndex >= 0 && skillInfoPaintIndex <= skillInfoPaints.length - 1 && skillInfoPaints[skillInfoPaintIndex].arrowId != 0) {
               int arrowId = skillInfoPaints[skillInfoPaintIndex].arrowId;
               if (skillInfoPaints[skillInfoPaintIndex].arrowId >= 100) {
                  Object entry;
                  if ((entry = this.mobFocus == null ? (nro.IMapObject) this.charFocus : this.mobFocus) != null) {
                     int value = Res.abs(((IMapObject)entry).getX() - this.cx);
                     int value2 = Res.abs(((IMapObject)entry).getY() - this.cy);
                     byte byteValue;
                     if (value > value2 * 4) {
                        byteValue = 0;
                     } else {
                        if (((IMapObject)entry).getY() < this.cy) {
                           byteValue = -3;
                        } else {
                           byteValue = 3;
                        }

                        if (entry instanceof BigBoss && ((BigBoss)(entry = (BigBoss)entry)).haftBody) {
                           byteValue = -20;
                        }
                     }

                     this.dart = new PlayerDart(
                        this,
                        arrowId - 100,
                        this.skillPaintRandomPaint,
                        this.cx + (skillInfoPaints[skillInfoPaintIndex].adx - 10) * this.cdir,
                        this.cy + skillInfoPaints[skillInfoPaintIndex].ady + byteValue
                     );
                     if (this.myskill != null) {
                        if (this.myskill.template.id == 1) {
                           SoundMn.gI();
                           SoundMn.traidatKame();
                        } else if (this.myskill.template.id == 3) {
                           SoundMn.gI();
                           SoundMn.namekKame();
                        } else if (this.myskill.template.id == 5) {
                           SoundMn.gI();
                           SoundMn.xaydaKame();
                        } else if (this.myskill.template.id == 11) {
                           SoundMn.gI();
                           SoundMn.nameLazer();
                        }
                     }
                  } else if (this.isFlyAndCharge || this.isUseSkillAfterCharge) {
                     this.stopUseChargeSkill();
                  }
               } else {
                  this.arr = new Arrow(this, GameScr.arrs[arrowId - 1]);
                  this.arr.life = 10;
                  this.arr.ax = this.cx + skillInfoPaints[skillInfoPaintIndex].adx;
                  this.arr.ay = this.cy + skillInfoPaints[skillInfoPaintIndex].ady;
               }
            }

            if ((
                  this.mobFocus != null
                     || !this.me && this.charFocus != null
                     || this.me
                        && this.charFocus != null
                        && (this.isMeCanAttackOtherPlayer(this.charFocus) || this.isSelectingSkillBuffToPlayer())
                        && this.arr == null
                        && this.dart == null
               )
               && this.indexSkill == skillInfoPaints.length - 1) {
               this.setAttack();
               if (this.me) {
                  SkillTemplate template = this.myskill.template;
                  if (this.myskill.template.type == 1) {
                     boolean condition = true;
                  } else {
                     boolean condition2 = false;
                  }
               }
            }

            if (!this.me) {
               Object charFocusValue = null;
               if (this.mobFocus != null) {
                  charFocusValue = this.mobFocus;
               } else if (this.charFocus != null) {
                  charFocusValue = this.charFocus;
               }

               if (charFocusValue != null) {
                  if (Res.abs(((IMapObject)charFocusValue).getX() - this.cx) < 10) {
                     if (((IMapObject)charFocusValue).getX() > this.cx) {
                        this.cx -= 10;
                     } else {
                        this.cx += 10;
                     }
                  }

                  if (((IMapObject)charFocusValue).getX() > this.cx) {
                     this.cdir = 1;
                     return;
                  }

                  this.cdir = -1;
               }
            }
         }
      }
   }

   public static void saveLoadPreviousSkill() {
   }

   public final void setResetPoint(int x, int y) {
      InfoDlg.hide();
      Res.outz("REST POINT x= " + x + " y= " + y);
      this.currentMovePoint = null;
      int deltaY;
      if ((deltaY = this.cy - y) == 0) {
         this.cx = x;
         ischangingMap = false;
         isLockKey = false;
      } else {
         this.statusMe = 16;
         this.cp2 = x;
         this.cp3 = y;
         this.cp1 = 0;
         myCharz().cxSend = x;
         myCharz().cySend = y;
      }
   }

   private void updateCharDeadFly() {
      this.isFreez = false;
      if (this.isCharge) {
         this.isCharge = false;
         SoundMn.gI();
         SoundMn.taitaoPause();
         Service.gI().skill_not_focus((byte)3);
      }

      this.cp1++;
      this.cx = this.cx + (this.cp2 - this.cx) / 4;
      if (this.cp1 > 7) {
         this.cy = this.cy + (this.cp3 - this.cy) / 4;
      } else {
         this.cy = this.cy + (this.cp1 - 10);
      }

      if (Res.abs(this.cp2 - this.cx) < 4
         && Res.abs(this.cp3 - this.cy) < 10) {
         this.cx = this.cp2;
         this.cy = this.cp3;
         this.statusMe = 14;
         if (this.me) {
            GameScr.gI().resetButton();
            Service.gI().charMove();
         }
      }

      this.cf = 23;
   }

   private void updateResetPoint() {
      InfoDlg.hide();
      GameCanvas.clearAllPointerEvent();
      this.currentMovePoint = null;
      this.cp1++;
      this.cx = this.cx + (this.cp2 - this.cx) / 4;
      if (this.cp1 > 7) {
         this.cy = this.cy + (this.cp3 - this.cy) / 4;
      } else {
         this.cy = this.cy + (this.cp1 - 10);
      }

      if (Res.abs(this.cp2 - this.cx) < 4
         && Res.abs(this.cp3 - this.cy) < 10) {
         this.cx = this.cp2;
         this.cy = this.cp3;
         this.statusMe = 1;
         this.cp3 = 0;
         ischangingMap = false;
         Service.gI().charMove();
      }

      this.cf = 23;
   }

   private void updateSkillStand() {
      this.ty = 0;
      this.cp1++;
      if (this.cdir == 1) {
         if ((TileMap.tileTypeAtPixel(this.cx + this.chw, this.cy - this.chh) & 4) == 4) {
            this.cvx = 0;
         }
      } else if ((TileMap.tileTypeAtPixel(this.cx - this.chw, this.cy - this.chh) & 8) == 8) {
         this.cvx = 0;
      }

      if (this.cy > this.ch
         && TileMap.tileTypeAt(this.cx, this.cy - this.ch + 24, 8192)) {
         if (!TileMap.tileTypeAt(this.cx, this.cy, 2)) {
            this.statusMe = 4;
            this.cp1 = 0;
            this.cp2 = 0;
            this.cvy = 1;
         } else {
            this.cy = TileMap.tileYofPixel(this.cy);
         }
      }

      this.cx = this.cx + this.cvx;
      this.cy = this.cy + this.cvy;
      if (this.cy < 0) {
         this.cy = this.cvy = 0;
      }

      if (this.cvy == 0) {
         if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) != 2) {
            this.statusMe = 4;
            this.cvx = (this.cspeed >> 1) * this.cdir;
            this.cp1 = this.cp2 = 0;
         }
      } else if (this.cvy < 0) {
         this.cvy++;
         if (this.cvy == 0) {
            this.cvy = 1;
         }
      } else {
         if (this.cvy < 20 && this.cp1 % 5 == 0) {
            this.cvy++;
         }

         if (this.cvy > 3) {
            this.cvy = 3;
         }

         if ((TileMap.tileTypeAtPixel(this.cx, this.cy + 3) & 2) == 2
            && this.cy <= TileMap.tileXofPixel(this.cy + 3)) {
            this.cvx = this.cvy = 0;
            this.cy = TileMap.tileXofPixel(this.cy + 3);
         }
      }

      if (this.cvx > 0) {
         this.cvx--;
      } else {
         if (this.cvx < 0) {
            this.cvx++;
         }
      }
   }

   private void updateCharAutoJump() {
      this.isFreez = false;
      if (this.isCharge) {
         this.isCharge = false;
         SoundMn.gI();
         SoundMn.taitaoPause();
         Service.gI().skill_not_focus((byte)3);
      }

      this.cx = this.cx + this.cvx * this.cdir;
      this.cy = this.cy + this.cvyJump;
      this.cvyJump++;
      if (this.cp1 == 0) {
         this.cf = 7;
      } else {
         this.cf = 23;
      }

      if (this.cvyJump == -3) {
         this.cf = 8;
      } else if (this.cvyJump == -2) {
         this.cf = 9;
      } else if (this.cvyJump == -1) {
         this.cf = 10;
      } else if (this.cvyJump == 0) {
         this.cf = 11;
      }

      if (this.cvyJump == 0) {
         this.statusMe = 6;
         this.cp3 = 0;
         ((MovePoint)this.vMovePoints.firstElement()).status = 4;
         this.cp1 = 0;
         this.cvy = 1;
      }
   }

   public void hide() {
      this.isHide = true;
      EffecMn.addEff(new Effect(107, this.cx, this.cy + 25, 3, 15, 1));
   }

   public final void show() {
      this.isHide = false;
      EffecMn.addEff(new Effect(107, this.cx, this.cy + 25, 3, 10, 1));
   }

   private void updateCharStand() {
      this.isAttack = false;
      this.isSoundJump = false;
      this.cvx = 0;
      this.cvy = 0;
      this.cp1++;
      if (this.cp1 > 30) {
         this.cp1 = 0;
      }

      if (this.cp1 % 15 < 5) {
         this.cf = 0;
      } else {
         this.cf = 1;
      }

      this.updateCharInBridge();
      if (!this.me) {
         this.cp3++;
         if (this.cp3 > 50) {
            this.cp3 = 0;
            this.currentMovePoint = null;
         }
      }

      this.updateSuperEff();
      if (this.me && GameScr.vCharInMap.size() != 0 && TileMap.mapID == 50) {
         Char charInMap;
         if (!(charInMap = (Char)GameScr.vCharInMap.elementAt(0)).changePos) {
            if (charInMap.statusMe != 2) {
               charInMap.moveTo(this.cx - 45, this.cy, 0);
            }

            System.currentTimeMillis();
            if (Res.abs(this.cx - 45 - charInMap.cx) <= 10) {
               charInMap.changePos = true;
            }
         } else {
            if (charInMap.statusMe != 2) {
               charInMap.moveTo(this.cx + 45, this.cy, 0);
            }

            System.currentTimeMillis();
            if (Res.abs(this.cx + 45 - charInMap.cx) <= 10) {
               charInMap.changePos = false;
            }
         }

         if (GameCanvas.gameTick % 100 == 0) {
            charInMap.addInfo("Cắc cùm cum");
         }
      }
   }

   private void updateSuperEff() {
      if (!this.isCopy
         && !this.isFusion
         && !this.isSetPos
         && !this.isPet
         && !this.isMiniPet
         && this.isMonkey != 1) {
         if ((!this.me || isPaintAura2 || this.idAuraEff < 0) && (this.me || this.idAuraEff < 0)) {
            this.ty++;
            if (this.clevel >= 9 && this.clevel < 14) {
               if ((this.ty == 40 || this.ty == 50) && !GameCanvas.lowGraphic) {
                  GameCanvas.gI().startDust(-1, this.cx + 8, this.cy);
                  GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
                  this.addDustEff(1);
               }

               if (this.ty > 50) {
                  switch (this.cgender) {
                     case 0:
                        if (GameCanvas.gameTick % 25 == 0) {
                           ServerEffect.addServerEffect(114, ((nro.Char) (this)), 1);
                        }

                        if (this.clevel >= 13 && GameCanvas.gameTick % 4 == 0) {
                           ServerEffect.addServerEffect(132, ((nro.Char) (this)), 1);
                           return;
                        }
                        break;
                     case 1:
                        if (GameCanvas.gameTick % 4 == 0) {
                           ServerEffect.addServerEffect(132, ((nro.Char) (this)), 1);
                        }

                        if (this.clevel >= 13 && GameCanvas.gameTick % 12 == 0) {
                           ServerEffect.addServerEffect(114, ((nro.Char) (this)), 1);
                        }

                        if (this.clevel >= 13 && GameCanvas.gameTick % 25 == 0) {
                           ServerEffect.addServerEffect(131, ((nro.Char) (this)), 1);
                           return;
                        }
                        break;
                     case 2:
                        if (GameCanvas.gameTick % 4 == 0) {
                           ServerEffect.addServerEffect(131, ((nro.Char) (this)), 1);
                        }

                        if (this.clevel >= 13 && GameCanvas.gameTick % 25 == 0) {
                           ServerEffect.addServerEffect(114, ((nro.Char) (this)), 1);
                        }
                  }
               }
            }
         }
      }
   }

   private float getSoundVolumn() {
      if (this.me) {
         return 0.1F;
      } else {
         int value;
         return (value = Res.abs(myChar.cx - this.cx)) >= 0 && value <= 50 ? 0.1F : 0.05F;
      }
   }

   private void updateCharRun() {
      if (this.isMonkey == 1) {
         ;
      }

      if (this.cx >= GameScr.cmx && this.cx <= GameScr.cmx + GameCanvas.w) {
         if (this.isMonkey == 0) {
            SoundMn.gI();
            this.getSoundVolumn();
            SoundMn.charRun();
         } else {
            SoundMn.gI();
            this.getSoundVolumn();
            SoundMn.monkeyRun();
         }
      }

      this.ty = 0;
      this.isFreez = false;
      if (this.isCharge) {
         this.isCharge = false;
         SoundMn.gI();
         SoundMn.taitaoPause();
         Service.gI().skill_not_focus((byte)3);
      }

      int value = 0;
      if (!this.me && this.currentMovePoint != null) {
         value = abs(this.cx - this.currentMovePoint.xEnd);
      }

      this.cp1++;
      if (this.cp1 >= 10) {
         this.cp1 = 0;
         this.cBonusSpeed = 0;
      }

      this.cf = (this.cp1 >> 1) + 2;
      if ((TileMap.tileTypeAtPixel(this.cx, this.cy - 1) & 64) == 64) {
         this.cx = this.cx + (this.cvx >> 1);
      } else {
         this.cx = this.cx + this.cvx;
      }

      if (this.cdir == 1) {
         if (TileMap.tileTypeAt(this.cx + this.chw, this.cy - this.chh, 4)) {
            if (this.me) {
               this.cvx = 0;
               this.cx = TileMap.tileXofPixel(this.cx + this.chw) - this.chw;
            } else {
               this.stop();
            }
         }
      } else if (TileMap.tileTypeAt(this.cx - this.chw - 1, this.cy - this.chh, 8)) {
         if (this.me) {
            this.cvx = 0;
            this.cx = TileMap.tileXofPixel(this.cx - this.chw - 1)
               + TileMap.size
               + this.chw;
         } else {
            this.stop();
         }
      }

      if (this.me) {
         if (this.cvx > 0) {
            this.cvx--;
         } else if (this.cvx < 0) {
            this.cvx++;
         } else {
            if (this.cx - this.cxSend != 0 && this.me) {
               Service.gI().charMove();
            }

            this.statusMe = 1;
            this.cBonusSpeed = 0;
         }
      }

      if ((TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) != 2) {
         if (this.me) {
            if (this.cx - this.cxSend != 0 || this.cy - this.cySend != 0) {
               Service.gI().charMove();
            }

            this.cf = 7;
            this.statusMe = 4;
            this.delayFall = 0;
            this.cvx = 3 * this.cdir;
            this.cp2 = 0;
         } else {
            this.stop();
         }
      }

      if (!this.me
         && this.currentMovePoint != null
         && abs(this.cx - this.currentMovePoint.xEnd) > value) {
         this.stop();
      }

      GameCanvas.gI().startDust(this.cdir, this.cx - (this.cdir << 3), this.cy);
      this.updateCharInBridge();
      this.addDustEff(2);
   }

   private void stop() {
      this.statusMe = 6;
      this.cp3 = 0;
      this.cvx = 0;
      this.cvy = 0;
      this.cp1 = this.cp2 = 0;
   }

   private static int abs(int i) {
      return i > 0 ? i : -i;
   }

   private void updateCharJump() {
      this.setMountIsStart();
      this.addDustEff(3);
      this.ty = 0;
      this.isFreez = false;
      if (this.isCharge) {
         this.isCharge = false;
         SoundMn.gI();
         SoundMn.taitaoPause();
         Service.gI().skill_not_focus((byte)3);
      }

      this.cx = this.cx + this.cvx;
      this.cy = this.cy + this.cvy;
      if (this.cy < 0) {
         this.cy = 0;
         this.cvy = -1;
      }

      this.cvy++;
      if (this.cvy > 0) {
         this.cvy = 0;
      }

      label95:
      if (!this.me && this.currentMovePoint != null) {
         int cvxValue;
         if ((cvxValue = this.currentMovePoint.xEnd - this.cx) > 0) {
            if (this.cvx > cvxValue) {
               this.cvx = cvxValue;
            }

            if (this.cvx >= 0) {
               break label95;
            }
         } else if (cvxValue < 0) {
            if (this.cvx < cvxValue) {
               this.cvx = cvxValue;
            }

            if (this.cvx <= 0) {
               break label95;
            }
         }

         this.cvx = cvxValue;
      }

      if (this.cdir == 1) {
         if ((TileMap.tileTypeAtPixel(this.cx + this.chw, this.cy - 1) & 4) == 4
            && this.cx <= TileMap.tileXofPixel(this.cx + this.chw) + 12) {
            this.cx = TileMap.tileXofPixel(this.cx + this.chw) - this.chw;
            this.cvx = 0;
         }
      } else if ((TileMap.tileTypeAtPixel(this.cx - this.chw, this.cy - 1) & 8) == 8
         && this.cx >= TileMap.tileXofPixel(this.cx - this.chw) + 12) {
         this.cx = TileMap.tileXofPixel(this.cx + 24 - this.chw) + this.chw;
         this.cvx = 0;
      }

      if (this.cvy == 0) {
         if (this.me) {
            this.setCharFallFromJump();
         } else {
            this.stop();
         }
      }

      if (this.me && !ischangingMap && this.isInWaypoint()) {
         Service.gI().charMove();
         if (TileMap.isTrainingMap()) {
            ischangingMap = true;
            Service.gI().getMapOffline();
         } else {
            Service.gI().requestChangeMap();
         }

         isLockKey = true;
         ischangingMap = true;
         GameCanvas.clearKeyHold();
         GameCanvas.clearKeyPressed();
         InfoDlg.showWait();
      } else {
         if (this.statusMe != 16
            && (TileMap.tileTypeAt(this.cx, this.cy - this.ch + 24, 8192) || this.cy < 0)) {
            this.statusMe = 4;
            this.cp1 = 0;
            this.cp2 = 0;
            this.cvy = 1;
            this.delayFall = 0;
            if (this.cy < 0) {
               this.cy = 0;
            }

            this.cy = TileMap.tileYofPixel(this.cy + 25);
            GameCanvas.clearKeyHold();
         }

         if (this.cp3 < 0) {
            this.cp3++;
         }

         this.cf = 7;
         if (!this.me && this.currentMovePoint != null && this.cy < this.currentMovePoint.yEnd) {
            this.stop();
         }
      }
   }

   private void setCharFallFromJump() {
      this.cyStartFall = this.cy;
      this.cp1 = 0;
      this.cp2 = 0;
      this.statusMe = 10;
      this.cvx = this.cdir << 2;
      this.cvy = 0;
      this.cy = TileMap.tileYofPixel(this.cy) + 12;
      if (this.me
         && (this.cx - this.cxSend != 0 || this.cy - this.cySend != 0)
         && (
            Res.abs(myCharz().cx - myCharz().cxSend) > 96
               || Res.abs(myCharz().cy - myCharz().cySend) > 24
         )) {
         Service.gI().charMove();
      }
   }

   private void updateCharFall() {
      if (!this.holder) {
         this.ty = 0;
         if (this.cy + 4 >= TileMap.pxh) {
            this.statusMe = 1;
            if (this.me) {
               SoundMn.gI();
               SoundMn.charFall();
            }

            this.cvx = this.cvy = 0;
            this.cp3 = 0;
         } else {
            if (this.cy % 24 == 0 && (TileMap.tileTypeAtPixel(this.cx, this.cy) & 2) == 2) {
               this.delayFall = 0;
               if (this.me) {
                  if (this.cy - this.cySend > 0
                     || this.cx - this.cxSend != 0
                     || this.cy - this.cySend < 0) {
                     Service.gI().charMove();
                  }

                  this.cvx = this.cvy = 0;
                  this.cp1 = this.cp2 = 0;
                  this.statusMe = 1;
                  this.cp3 = 0;
                  return;
               }

               this.stop();
               this.cf = 0;
               GameCanvas.gI().startDust(-1, this.cx - -8, this.cy);
               GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
               this.addDustEff(1);
            }

            if (this.delayFall > 0) {
               this.delayFall--;
               if (this.delayFall % 10 > 5) {
                  this.cy++;
               } else {
                  this.cy--;
               }
            } else {
               if (this.cvy < -4) {
                  this.cf = 7;
               } else {
                  this.cf = 12;
               }

               this.cx = this.cx + this.cvx;
               label134:
               if (!this.me && this.currentMovePoint != null) {
                  int cvxValue;
                  if ((cvxValue = this.currentMovePoint.xEnd - this.cx) > 0) {
                     if (this.cvx > cvxValue) {
                        this.cvx = cvxValue;
                     }

                     if (this.cvx >= 0) {
                        break label134;
                     }
                  } else if (cvxValue < 0) {
                     if (this.cvx < cvxValue) {
                        this.cvx = cvxValue;
                     }

                     if (this.cvx <= 0) {
                        break label134;
                     }
                  }

                  this.cvx = cvxValue;
               }

               this.cvy++;
               if (this.cvy > 8) {
                  this.cvy = 8;
               }

               if (this.skillPaintRandomPaint == null) {
                  this.cy = this.cy + this.cvy;
               }

               if (this.cdir == 1) {
                  if ((TileMap.tileTypeAtPixel(this.cx + this.chw, this.cy - 1) & 4) == 4
                     && this.cx <= TileMap.tileXofPixel(this.cx + this.chw) + 12) {
                     this.cx = TileMap.tileXofPixel(this.cx + this.chw) - this.chw;
                     this.cvx = 0;
                  }
               } else if ((TileMap.tileTypeAtPixel(this.cx - this.chw, this.cy - 1) & 8) == 8
                  && this.cx >= TileMap.tileXofPixel(this.cx - this.chw) + 12) {
                  this.cx = TileMap.tileXofPixel(this.cx + 24 - this.chw) + this.chw;
                  this.cvx = 0;
               }

               if (this.cvy > 3
                  && (this.cyStartFall == 0 || this.cyStartFall <= TileMap.tileYofPixel(this.cy + 3))
                  && (TileMap.tileTypeAtPixel(this.cx, this.cy + 3) & 2) == 2) {
                  if (this.me) {
                     this.cyStartFall = 0;
                     this.cvx = this.cvy = 0;
                     this.cp1 = this.cp2 = 0;
                     this.cy = TileMap.tileXofPixel(this.cy + 3);
                     this.statusMe = 1;
                     if (this.me) {
                        SoundMn.gI();
                        SoundMn.charFall();
                     }

                     this.cp3 = 0;
                     GameCanvas.gI().startDust(-1, this.cx - -8, this.cy);
                     GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
                     this.addDustEff(1);
                     if (this.cy - this.cySend > 0) {
                        if (this.me) {
                           Service.gI().charMove();
                           return;
                        }
                     } else if ((this.cx - this.cxSend != 0 || this.cy - this.cySend < 0)
                        && this.me) {
                        Service.gI().charMove();
                        return;
                     }
                  } else {
                     this.stop();
                     this.cy = TileMap.tileXofPixel(this.cy + 3);
                     this.cf = 0;
                     GameCanvas.gI().startDust(-1, this.cx - -8, this.cy);
                     GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
                     this.addDustEff(1);
                     this.currentMovePoint = null;
                  }
               } else {
                  this.cf = 12;
                  if (!this.me) {
                     if ((TileMap.tileTypeAtPixel(this.cx, this.cy + 1) & 2) == 2) {
                        this.cf = 0;
                     }

                     if (this.currentMovePoint != null && this.cy > this.currentMovePoint.yEnd) {
                        this.stop();
                        this.cy = TileMap.tileXofPixel(this.cy + 3);
                        this.currentMovePoint = null;
                     }
                  }
               }
            }
         }
      }
   }

   private void updateCharFly() {
      int value = this.isMonkey == 1 && !this.me ? 2 : 1;
      this.setMountIsStart();
      if (this.statusMe == 16
         || !TileMap.tileTypeAt(this.cx, this.cy - this.ch + 24, 8192) && this.cy >= 0) {
         int cyValue;
         boolean condition;
         label180: {
            cyValue = this.cy;
            int headValue = this.head;
            if (Arr_Head_FlyMove.length > 0) {
               for (int arr_Head_FlyMoveIndex = 0; arr_Head_FlyMoveIndex < Arr_Head_FlyMove.length; arr_Head_FlyMoveIndex++) {
                  if (Arr_Head_FlyMove[arr_Head_FlyMoveIndex] == headValue) {
                     condition = true;
                     break label180;
                  }
               }
            }

            condition = false;
         }

         if (condition) {
            if (GameCanvas.gameTick % 3 == 0) {
               this.cp1++;
            }

            if (this.cp1 > 4) {
               this.cp1 = 0;
            }

            this.cf = this.cp1 + 2;
         } else {
            this.cp1++;
            if (this.cp1 >= 9) {
               this.cp1 = 0;
               if (!this.me) {
                  this.cvx = this.cvy = 0;
               }

               this.cBonusSpeed = 0;
            }

            this.cf = 8;
            if (Res.abs(this.cvx) <= 4 && this.me) {
               if (this.currentMovePoint != null) {
                  int value2 = abs(this.cx - this.currentMovePoint.xEnd);
                  int value3 = abs(this.cy - this.currentMovePoint.yEnd);
                  if (value2 > value3 * 10) {
                     this.cf = 8;
                  } else if (value2 > value3 && value2 > 48 && value3 > 32) {
                     this.cf = 8;
                  } else {
                     this.cf = 7;
                  }
               } else {
                  if (this.cvy < 0) {
                     this.cvy = 0;
                  }

                  if (this.cvy > 16) {
                     this.cvy = 16;
                  }

                  this.cf = 7;
               }
            }

            if (!this.me) {
               if (abs(this.cvx) < 2) {
                  this.cvx = (this.cdir << 1) * value;
               }

               if (this.cvy != 0) {
                  this.cf = 7;
               }

               if (abs(this.cvx) <= 2) {
                  this.cp2++;
                  if (this.cp2 > 32) {
                     this.statusMe = 4;
                     this.cvx = 0;
                     this.cvy = 0;
                  }
               }
            }
         }

         if (this.cdir == 1) {
            if (TileMap.tileTypeAt(this.cx + this.chw, this.cy - 1, 4)) {
               this.cvx = 0;
               this.cx = TileMap.tileXofPixel(this.cx + this.chw) - this.chw;
               if (this.cvy == 0) {
                  this.currentMovePoint = null;
               }
            }
         } else if (TileMap.tileTypeAt(this.cx - this.chw - 1, this.cy - 1, 8)) {
            this.cvx = 0;
            this.cx = TileMap.tileXofPixel(this.cx - this.chw - 1)
               + TileMap.size
               + this.chw;
            if (this.cvy == 0) {
               this.currentMovePoint = null;
            }
         }

         this.cx = this.cx + this.cvx * value;
         this.cy = this.cy + this.cvy * value;
         int deltaY;
         if (!this.isMount && (deltaY = cyValue - this.cy) == 0) {
            this.ty++;
            this.wt++;
            this.fy = this.fy + (!this.wy ? 1 : -1);
            if (this.wt == 10) {
               this.wt = 0;
               this.wy = !this.wy;
            }

            if (this.ty > 20) {
               this.delayFall = 10;
               if (GameCanvas.gameTick % 3 == 0) {
                  ServerEffect.addServerEffect(
                     111,
                     this.cx + (this.cdir == 1 ? -17 : 27),
                     this.cy + this.fy + 13,
                     1,
                     this.cdir != 1 ? 2 : 0
                  );
               }
            }
         }

         if (this.me) {
            if (this.cvx > 0) {
               this.cvx--;
            } else if (this.cvx < 0) {
               this.cvx++;
            } else if (this.cvy == 0) {
               this.statusMe = 4;
               this.checkDelayFallIfTooHigh();
               Service.gI().charMove();
            }

            if ((TileMap.tileTypeAtPixel(this.cx, this.cy + 20) & 2) == 2
               || (TileMap.tileTypeAtPixel(this.cx, this.cy + 40) & 2) == 2) {
               if (this.cvy == 0) {
                  this.delayFall = 0;
               }

               this.cyStartFall = 0;
               this.cvx = this.cvy = 0;
               this.cp1 = this.cp2 = 0;
               this.statusMe = 4;
               this.addDustEff(3);
            }

            if (abs(this.cx - this.cxSend) > 96
               || abs(this.cy - this.cySend) > 24) {
               Service.gI().charMove();
            }
         }
      } else {
         if (this.cy - this.ch < 0) {
            this.cy = this.ch;
         }

         this.cf = 7;
         this.statusMe = 4;
         this.cvx = 0;
         this.cp2 = 0;
         this.currentMovePoint = null;
      }
   }

   private void updateMount() {
      this.frameMount++;
      if (this.frameMount > this.FrameMount.length - 1) {
         this.frameMount = 0;
      }

      this.frameNewMount++;
      if (this.frameNewMount > 1000) {
         this.frameNewMount = 0;
      }

      if (this.isStartMount && !this.isMount) {
         this.yMount = this.cy;
         if (this.transMount == 0) {
            if (this.xMount - this.cx >= this.speedMount) {
               this.xMount = this.xMount - this.speedMount;
               return;
            }

            this.xMount = this.cx;
            this.isMount = true;
            this.isEndMount = false;
            return;
         }

         if (this.transMount == 2) {
            if (this.cx - this.xMount >= this.speedMount) {
               this.xMount = this.xMount + this.speedMount;
               return;
            }

            this.xMount = this.cx;
            this.isMount = true;
            this.isEndMount = false;
            return;
         }
      } else {
         if (this.isMount) {
            if ((this.statusMe == 14 || this.ySd - this.cy < 24)
               && this.ySd - this.cy < 24
               && !this.isEndMount) {
               this.isStartMount = false;
               this.isMount = false;
               this.isEndMount = true;
               this.xdis = 0;
            }

            if (this.cp1 % 15 < 5) {
               this.cf = 0;
            } else {
               this.cf = 1;
            }

            this.transMount = this.cdir;
            this.updateSuperEff();
            if (this.transMount < 0) {
               this.transMount = 0;
               this.dxMount = -19;
            } else if (this.transMount == 1) {
               this.transMount = 2;
               this.dxMount = -31;
               if (this.isEventMount) {
                  this.dxMount = -38;
               }
            }

            if (this.skillInfoPaint() != null) {
               this.dyMount = -15;
            } else {
               this.dyMount = -17;
            }

            this.yMount = this.cy;
            this.xMount = this.cx;
            return;
         }

         if (this.isEndMount) {
            if (this.transMount == 0) {
               if (this.xMount > GameScr.cmx - 100) {
                  this.xMount -= 20;
                  return;
               }

               this.isStartMount = false;
               this.isMount = false;
               this.isEndMount = false;
               return;
            }

            if (this.transMount == 2) {
               if (this.xMount < GameScr.cmx + GameCanvas.w + 50) {
                  this.xMount += 20;
                  return;
               }

               this.isStartMount = false;
               this.isMount = false;
               this.isEndMount = false;
               return;
            }
         } else if (!this.isStartMount || !this.isMount || !this.isEndMount) {
            this.xMount = GameScr.cmx - 100;
            this.yMount = GameScr.cmy - 100;
         }
      }
   }

   private static void getMountData() {
      if (Mob.arrMobTemplate[50].data == null) {
         Mob.arrMobTemplate[50].data = new EffectData();
         String text = "/Mob/50";
         if ("".getClass().getResourceAsStream(text) != null) {
            Mob.arrMobTemplate[50].data.readData(((java.lang.String) (text + "/data")));
            Mob.arrMobTemplate[50].data.img = mSystem.loadImage(text + "/img.png");
         } else {
            Service.gI().requestModTemplate(50);
         }

         Mob.lastMob.addElement("50");
      }
   }

   public void checkFrameTick(int[] array) {
      this.t++;
      if (this.t > array.length - 1) {
         this.t = 0;
      }

      this.fM = array[this.t];
   }

   public final void paintMount2(mGraphics g) {
      if (this.xMount > GameScr.cmx && this.xMount < GameScr.cmx + GameCanvas.w) {
         if (this.me) {
            if (this.isEndMount || this.isStartMount || this.isMount) {
               if (this.idMount >= ID_NEW_MOUNT) {
                  FrameImage fraImage;
                  if ((fraImage = mSystem.getFraImage(this.strMount + (this.idMount - ID_NEW_MOUNT) + "_1")) != null) {
                     fraImage.drawFrame(
                        this.frameNewMount / 2 % fraImage.nFrame,
                        this.xMount,
                        this.yMount + this.fy,
                        this.transMount,
                        3,
                        g
                     );
                  }

                  return;
               }

               if (this.isSpeacialMount) {
                  this.checkFrameTick(this.move);
                  if (Mob.arrMobTemplate[50] != null && Mob.arrMobTemplate[50].data != null) {
                     Mob.arrMobTemplate[50]
                        .data
                        .paintFrame(
                           g,
                           this.fM,
                           this.xMount + (this.cdir == 1 ? -8 : 8),
                           this.yMount + 35,
                           this.cdir == 1 ? 0 : 1,
                           0
                        );
                     return;
                  }

                  getMountData();
                  return;
               }

               if (this.isEventMount) {
                  g.drawRegion(
                     imgEventMount,
                     0,
                     this.FrameMount[this.frameMount] * 60,
                     60,
                     60,
                     this.transMount,
                     this.xMount + this.dxMount,
                     this.yMount + this.dyMount + this.fy,
                     0
                  );
                  return;
               }

               if (this.genderMount == 0) {
                  if (!this.isMountVip) {
                     g.drawRegion(
                        imgMount_TD,
                        0,
                        this.FrameMount[this.frameMount] * 40,
                        50,
                        40,
                        this.transMount,
                        this.xMount + this.dxMount,
                        this.yMount + this.dyMount + this.fy,
                        0
                     );
                     return;
                  }

                  g.drawRegion(
                     imgMount_TD_VIP,
                     0,
                     this.FrameMount[this.frameMount] * 40,
                     50,
                     40,
                     this.transMount,
                     this.xMount + this.dxMount,
                     this.yMount + this.dyMount + this.fy,
                     0
                  );
                  return;
               }

               if (this.genderMount == 1) {
                  if (!this.isMountVip) {
                     g.drawRegion(
                        imgMount_NM_1,
                        0,
                        this.FrameMount[this.frameMount] * 40,
                        50,
                        40,
                        this.transMount,
                        this.xMount + this.dxMount,
                        this.yMount + this.dyMount + this.fy,
                        0
                     );
                     return;
                  }

                  g.drawRegion(
                     imgMount_NM_1_VIP,
                     0,
                     this.FrameMount[this.frameMount] * 40,
                     50,
                     40,
                     this.transMount,
                     this.xMount + this.dxMount,
                     this.yMount + this.dyMount + this.fy,
                     0
                  );
                  return;
               }
            }
         } else if (!this.me) {
            if (this.idMount >= ID_NEW_MOUNT) {
               FrameImage fraImage2;
               if ((fraImage2 = mSystem.getFraImage(this.strMount + (this.idMount - ID_NEW_MOUNT) + "_1")) != null) {
                  fraImage2.drawFrame(
                     this.frameNewMount / 2 % fraImage2.nFrame,
                     this.xMount,
                     this.yMount + this.fy,
                     this.transMount,
                     3,
                     g
                  );
               }

               return;
            }

            if (this.isSpeacialMount) {
               this.checkFrameTick(this.move);
               if (Mob.arrMobTemplate[50] != null && Mob.arrMobTemplate[50].data != null) {
                  Mob.arrMobTemplate[50]
                     .data
                     .paintFrame(
                        g,
                        this.fM,
                        this.xMount + (this.cdir == 1 ? -8 : 8),
                        this.yMount + 35,
                        this.cdir == 1 ? 0 : 1,
                        0
                     );
                  return;
               }

               getMountData();
               return;
            }

            if (this.isEventMount) {
               g.drawRegion(
                  imgEventMount,
                  0,
                  this.FrameMount[this.frameMount] * 60,
                  60,
                  60,
                  this.transMount,
                  this.xMount + this.dxMount,
                  this.yMount + this.dyMount + this.fy,
                  0
               );
               return;
            }

            if (this.isMount) {
               if (this.genderMount == 0) {
                  if (!this.isMountVip) {
                     g.drawRegion(
                        imgMount_TD,
                        0,
                        this.FrameMount[this.frameMount] * 40,
                        50,
                        40,
                        this.transMount,
                        this.xMount + this.dxMount,
                        this.yMount + this.dyMount + this.fy,
                        0
                     );
                     return;
                  }

                  g.drawRegion(
                     imgMount_TD_VIP,
                     0,
                     this.FrameMount[this.frameMount] * 40,
                     50,
                     40,
                     this.transMount,
                     this.xMount + this.dxMount,
                     this.yMount + this.dyMount + this.fy,
                     0
                  );
                  return;
               }

               if (this.genderMount == 1) {
                  if (!this.isMountVip) {
                     g.drawRegion(
                        imgMount_NM_1,
                        0,
                        this.FrameMount[this.frameMount] * 40,
                        50,
                        40,
                        this.transMount,
                        this.xMount + this.dxMount,
                        this.yMount + this.dyMount + this.fy,
                        0
                     );
                     return;
                  }

                  g.drawRegion(
                     imgMount_NM_1_VIP,
                     0,
                     this.FrameMount[this.frameMount] * 40,
                     50,
                     40,
                     this.transMount,
                     this.xMount + this.dxMount,
                     this.yMount + this.dyMount + this.fy,
                     0
                  );
               }
            }
         }
      }
   }

   private void setMountIsStart() {
      if (this.me) {
         boolean isHaveMountValue = false;
         short idMountValue = -1;
         Item[] itemBody = this.arrItemBody;

         for (int itemBodyIndex = 0; itemBodyIndex < itemBody.length; itemBodyIndex++) {
            if (itemBody[itemBodyIndex] != null && (itemBody[itemBodyIndex].template.type == 24 || itemBody[itemBodyIndex].template.type == 23)) {
               if (itemBody[itemBodyIndex].template.part >= 0) {
                  idMountValue = (short)(ID_NEW_MOUNT + itemBody[itemBodyIndex].template.part);
               } else {
                  idMountValue = itemBody[itemBodyIndex].template.id;
               }

               isHaveMountValue = true;
               break;
            }
         }

         this.isMountVip = false;
         this.isSpeacialMount = false;
         this.isEventMount = false;
         this.idMount = -1;
         if (idMountValue == 349 || idMountValue == 350 || idMountValue == 351) {
            this.isMountVip = true;
         } else if (idMountValue == 396) {
            this.isEventMount = true;
         } else if (idMountValue == 532) {
            this.isSpeacialMount = true;
         } else if (idMountValue >= ID_NEW_MOUNT) {
            this.idMount = idMountValue;
         }

         this.isHaveMount = isHaveMountValue;
         if (TileMap.isVoDaiMap()) {
            this.isHaveMount = false;
         }
      }

      if (this.isHaveMount) {
         if (this.ySd - this.cy <= 20) {
            this.xChar = this.cx;
         }

         if (this.xdis < 100) {
            this.xdis = Res.abs(this.xChar - this.cx);
         }

         if (this.xdis >= 70 && this.ySd - this.cy > 30 && !this.isStartMount && !this.isEndMount) {
            int genderMountValue = this.cgender;
            int transMountValue = this.cdir;
            int charIDValue = this.charID;
            this.transMount = transMountValue;
            this.genderMount = genderMountValue;
            this.speedMount = 30;
            if (this.transMount < 0) {
               this.transMount = 0;
               this.xMount = GameScr.cmx + GameCanvas.w + 50;
               this.dxMount = -19;
            } else if (this.transMount == 1) {
               this.transMount = 2;
               this.xMount = GameScr.cmx - 100;
               this.dxMount = -33;
            }

            this.dyMount = -17;
            this.yMount = this.cy;
            this.frameMount = 0;
            this.frameNewMount = 0;
            this.isMount = false;
            this.isEndMount = false;
            this.isStartMount = true;
         }
      }
   }

   private void checkDelayFallIfTooHigh() {
      boolean condition = true;

      for (int value = 0; value < 150; value += 24) {
         if ((TileMap.tileTypeAtPixel(this.cx, this.cy + value) & 2) == 2
            || this.cy + value > TileMap.tmh * TileMap.size - 24) {
            condition = false;
            break;
         }
      }

      if (condition) {
         this.delayFall = 40;
      }
   }

   public final void setDefaultPart() {
      this.setDefaultWeapon();
      this.setDefaultBody();
      this.setDefaultLeg();
   }

   public final void setDefaultWeapon() {
      if (this.cgender == 0) {
         this.wp = 0;
      }
   }

   public final void setDefaultBody() {
      if (this.cgender == 0) {
         this.body = 57;
      } else if (this.cgender == 1) {
         this.body = 59;
      } else {
         if (this.cgender == 2) {
            this.body = 57;
         }
      }
   }

   public final void setDefaultLeg() {
      if (this.cgender == 0) {
         this.leg = 58;
      } else if (this.cgender == 1) {
         this.leg = 60;
      } else {
         if (this.cgender == 2) {
            this.leg = 58;
         }
      }
   }

   public final boolean isSelectingSkillUseAlone() {
      return this.myskill != null && this.myskill.template.isUseAlone();
   }

   public final boolean isUseSkillSpec() {
      return this.myskill != null && this.myskill.template.isSkillSpec();
   }

   public final boolean isSelectingSkillBuffToPlayer() {
      return this.myskill != null && this.myskill.template.isBuffToPlayer();
   }

   public final boolean isUseChargeSkill() {
      return !this.isUseSkillAfterCharge
         && this.myskill != null
         && (this.myskill.template.id == 10 || this.myskill.template.id == 11);
   }

   public final void setSkillPaint(SkillPaint skillPaint, int sType) {
      this.hasSendAttack = false;
      if (!this.stone) {
         if (!this.me || this.myskill.template.id != 9 || this.cHP > this.cHPFull / 10L) {
            if (this.me) {
               if (this.mobFocus == null && this.charFocus == null) {
                  this.stopUseChargeSkill();
               }

               if (this.mobFocus != null && (this.mobFocus.status == 1 || this.mobFocus.status == 0)) {
                  this.stopUseChargeSkill();
               }

               if (this.charFocus != null && (this.charFocus.statusMe == 14 || this.charFocus.statusMe == 5)) {
                  this.stopUseChargeSkill();
               }

               if (this.myskill.template.id == 23) {
                  if (this.charFocus != null && this.charFocus.holdEffID != 0) {
                     return;
                  }

                  if (this.mobFocus != null && this.mobFocus.holdEffID != 0) {
                     return;
                  }

                  if (this.holdEffID != 0) {
                     return;
                  }
               }

               if (this.sleepEff || this.blindEff) {
                  return;
               }
            }

            if (!this.me || this.dart == null) {
               if (!TileMap.isOfflineMap()) {
                  long lastTimeUseThisSkill = System.currentTimeMillis();
                  if (this.me) {
                     if (this.isSelectingSkillBuffToPlayer() && this.charFocus == null) {
                        return;
                     }

                     if (lastTimeUseThisSkill - this.myskill.lastTimeUseThisSkill < (long)this.myskill.coolDown) {
                        this.myskill.paintCanNotUseSkill = true;
                        return;
                     }

                     this.myskill.lastTimeUseThisSkill = lastTimeUseThisSkill;
                     if (this.myskill.template.manaUseType == 2) {
                        this.cMP = 1L;
                     } else if (this.myskill.template.manaUseType != 1) {
                        this.cMP = this.cMP - (long)this.myskill.manaUse;
                     } else {
                        this.cMP = this.cMP - (long)this.myskill.manaUse * this.cMPFull / 100L;
                     }

                     myCharz().cStamina--;
                     GameScr.gI().isInjureMp = true;
                     GameScr.gI().twMp = 0L;
                     if (this.cMP < 0L) {
                        this.cMP = 0L;
                     }
                  }

                  if (this.me) {
                     if (this.myskill.template.id == 10) {
                        Service.gI().skill_not_focus((byte)4);
                     }

                     if (this.myskill.template.id == 11) {
                        Service.gI().skill_not_focus((byte)4);
                     }

                     if (this.myskill.template.id == 7) {
                        SoundMn.gI();
                        SoundMn.hoisinh();
                     }

                     if (this.myskill.template.id == 6) {
                        Service.gI().skill_not_focus((byte)0);
                        GameScr.gI().isUseFreez = true;
                        SoundMn.gI();
                        SoundMn.thaiduonghasan();
                     }

                     if (this.myskill.template.id == 8) {
                        if (!this.isCharge) {
                           SoundMn.gI();
                           SoundMn.taitaoPause();
                           Service.gI().skill_not_focus((byte)1);
                           this.isCharge = true;
                           this.last = this.cur = System.currentTimeMillis();
                        } else {
                           Service.gI().skill_not_focus((byte)3);
                           this.isCharge = false;
                           SoundMn.gI();
                           SoundMn.taitaoPause();
                        }
                     }

                     if (this.myskill.template.id == 13) {
                        if (this.isMonkey != 0) {
                           GameScr.gI().auto = 0;
                           return;
                        }

                        if (this.isCreateDark) {
                           return;
                        }

                        SoundMn.gI();
                        SoundMn.gong();
                        Service.gI().skill_not_focus((byte)6);
                        this.chargeCount = 0;
                        this.isWaitMonkey = true;
                        return;
                     }

                     if (this.myskill.template.id == 14) {
                        SoundMn.gI();
                        SoundMn.gong();
                        Service.gI().skill_not_focus((byte)7);
                        this.useChargeSkill(true);
                     }

                     if (this.myskill.template.id == 21) {
                        Service.gI().skill_not_focus((byte)10);
                        return;
                     }

                     if (this.myskill.template.id == 12) {
                        Service.gI().skill_not_focus((byte)8);
                     }

                     if (this.myskill.template.id == 19) {
                        Service.gI().skill_not_focus((byte)9);
                        return;
                     }
                  }

                  if (this.isMonkey == 1 && skillPaint.id >= 35 && skillPaint.id <= 41) {
                     skillPaint = GameScr.sks[106];
                  }

                  if (skillPaint.id >= 128 && skillPaint.id <= 134) {
                     skillPaint = GameScr.sks[skillPaint.id - 65];
                     if (this.charFocus != null) {
                        this.cx = this.charFocus.cx;
                        this.cy = this.charFocus.cy;
                        this.currentMovePoint = null;
                     }

                     if (this.mobFocus != null) {
                        this.cx = this.mobFocus.x;
                        this.cy = this.mobFocus.y;
                        this.currentMovePoint = null;
                     }

                     ServerEffect.addServerEffect(60, this.cx, this.cy, 1);
                     this.telePortSkill = true;
                  }

                  if (skillPaint.id >= 107 && skillPaint.id <= 113) {
                     skillPaint = GameScr.sks[skillPaint.id - 44];
                     EffecMn.addEff(new Effect(23, this.cx, this.cy + this.ch / 2, 3, 2, 1));
                  }

                  this.setAutoSkillPaint(skillPaint, sType);
               }
            }
         }
      }
   }

   public static void useSkillNotFocus() {
      GameScr.gI().auto = 0;
      myCharz()
         .setSkillPaint(
            GameScr.sks[myCharz().myskill.skillId],
            TileMap.tileTypeAt(myCharz().cx, myCharz().cy, 2) ? 0 : 1
         );
   }

   public final void sendUseChargeSkill() {
      if (!this.me || !this.isFreez && !this.isUsePlane) {
         long nowMillis = System.currentTimeMillis();
         if (this.me && nowMillis - this.myskill.lastTimeUseThisSkill < (long)this.myskill.coolDown) {
            this.myskill.paintCanNotUseSkill = true;
         } else {
            if (this.myskill.template.id == 10) {
               this.useChargeSkill(false);
            }

            if (this.myskill.template.id == 11) {
               this.useChargeSkill(true);
            }
         }
      } else {
         GameScr.gI().auto = 0;
      }
   }

   public final void stopUseChargeSkill() {
      this.isFlyAndCharge = false;
      this.isStandAndCharge = false;
      this.isUseSkillAfterCharge = false;
      this.isCreateDark = false;
      if (this.me && this.statusMe != 14 && this.statusMe != 5) {
         this.isLockMove = false;
      }

      GameScr.gI().auto = 0;
   }

   public final void useChargeSkill(boolean isGround) {
      if (!this.isCreateDark) {
         GameScr.gI().auto = 0;
         if (isGround) {
            if (!this.isStandAndCharge) {
               this.chargeCount = 0;
               this.seconds = 50000;
               this.posDisY = 0;
               this.last = System.currentTimeMillis();
               if (this.me) {
                  this.isLockMove = true;
                  if (this.cgender == 1) {
                     Service.gI().skill_not_focus((byte)4);
                  }

                  if (TileMap.mapID == 170 && this.cgender != 1) {
                     Service.gI().skill_not_focus((byte)4);
                  }
               }

               if (this.cgender == 1) {
                  SoundMn.gI();
                  SoundMn.gongName();
               }

               if (TileMap.mapID == 170 && this.cgender != 1) {
                  SoundMn.gI();
                  SoundMn.gongName();
               }

               this.isStandAndCharge = true;
               return;
            }
         } else if (!this.isFlyAndCharge) {
            if (this.me) {
               GameScr.gI().auto = 0;
               this.isLockMove = true;
               Service.gI().skill_not_focus((byte)4);
            }

            this.isUseSkillAfterCharge = false;
            this.chargeCount = 0;
            this.isFlyAndCharge = true;
            this.posDisY = 0;
            this.seconds = 50000;
            TileMap.tileTypeAt(this.cx, this.cy, 2);
         }
      }
   }

   public final void setAutoSkillPaint(SkillPaint skillPaint, int sType) {
      this.skillPaint = skillPaint;
      if (skillPaint.id >= 0 && skillPaint.id <= 6) {
         int sksIndex;
         if ((sksIndex = Res.random(0, skillPaint.id + 4) - 1) < 0) {
            sksIndex = 0;
         }

         if (sksIndex > 6) {
            sksIndex = 6;
         }

         this.skillPaintRandomPaint = GameScr.sks[sksIndex];
      } else if (skillPaint.id >= 14 && skillPaint.id <= 20) {
         int value;
         if ((value = Res.random(0, skillPaint.id - 14 + 4) - 1) < 0) {
            value = 0;
         }

         if (value > 6) {
            value = 6;
         }

         this.skillPaintRandomPaint = GameScr.sks[value + 14];
      } else if (skillPaint.id >= 28 && skillPaint.id <= 34) {
         int value2;
         if ((
               value2 = Res.random(
                     0, (this.isMonkey == 1 ? 105 : skillPaint.id) - (this.isMonkey == 1 ? 105 : 28) + 4
                  )
                  - 1
            )
            < 0) {
            value2 = 0;
         }

         if (value2 > 6) {
            value2 = 6;
         }

         if (this.isMonkey == 1) {
            value2 = 0;
         }

         this.skillPaintRandomPaint = GameScr.sks[value2 + (this.isMonkey == 1 ? 105 : 28)];
      } else if (skillPaint.id >= 63 && skillPaint.id <= 69) {
         int value3;
         if ((value3 = Res.random(0, skillPaint.id - 63 + 4) - 1) < 0) {
            value3 = 0;
         }

         if (value3 > 6) {
            value3 = 6;
         }

         this.skillPaintRandomPaint = GameScr.sks[value3 + 63];
      } else if (skillPaint.id >= 107 && skillPaint.id <= 109) {
         int value4;
         if ((value4 = Res.random(0, skillPaint.id - 107 + 4) - 1) < 0) {
            value4 = 0;
         }

         if (value4 > 6) {
            value4 = 6;
         }

         this.skillPaintRandomPaint = GameScr.sks[value4 + 107];
      } else {
         this.skillPaintRandomPaint = skillPaint;
      }

      this.sType = sType;
      this.indexSkill = 0;
      this.i0 = this.i1 = this.i2 = this.dx0 = this.dx1 = this.dx2 = this.dy0 = this.dy1 = this.dy2 = 0;
      this.eff0 = null;
      this.eff1 = null;
      this.eff2 = null;
      this.cvy = 0;
   }

   public final SkillInfoPaint[] skillInfoPaint() {
      if (this.skillPaint == null) {
         return null;
      } else if (this.skillPaintRandomPaint == null) {
         return null;
      } else {
         return this.sType == 0 ? this.skillPaintRandomPaint.skillStand : this.skillPaintRandomPaint.skillfly;
      }
   }

   public final void setAttack() {
      if (this.me) {
         SkillPaint skillPaintValue = this.skillPaintRandomPaint;
         if (this.dart != null) {
            skillPaintValue = this.dart.skillPaint;
         }

         if (skillPaintValue != null) {
            MyVector mob = new MyVector("vMob");
            MyVector entries = new MyVector("vChar");
            if (this.charFocus != null) {
               entries.addElement(this.charFocus);
            } else if (this.mobFocus != null) {
               mob.addElement(this.mobFocus);
            }

            this.effPaints = new EffectPaint[mob.size() + entries.size()];

            for (int effPaintIndex = 0; effPaintIndex < mob.size(); effPaintIndex++) {
               this.effPaints[effPaintIndex] = new EffectPaint();
               this.effPaints[effPaintIndex].effCharPaint = GameScr.efs[skillPaintValue.effectHappenOnMob - 1];
               if (!this.isSelectingSkillUseAlone()) {
                  this.effPaints[effPaintIndex].eMob = (Mob)mob.elementAt(effPaintIndex);
               }
            }

            for (int entryIndex = 0; entryIndex < entries.size(); entryIndex++) {
               this.effPaints[entryIndex + mob.size()] = new EffectPaint();
               this.effPaints[entryIndex + mob.size()].effCharPaint = GameScr.efs[skillPaintValue.effectHappenOnMob - 1];
               this.effPaints[entryIndex + mob.size()].eChar = (Char)entries.elementAt(entryIndex);
            }

            byte type = 0;
            if (this.mobFocus != null) {
               type = 1;
            } else if (this.charFocus != null) {
               type = 2;
            }

            if (mob.size() == 0 && entries.size() == 0) {
               this.stopUseChargeSkill();
            }

            if (this.me && !this.isSelectingSkillUseAlone() && !this.hasSendAttack) {
               Service.gI().sendPlayerAttack(mob, entries, type);
               this.hasSendAttack = true;
               return;
            }
         }
      } else {
         SkillPaint skillPaintValue2 = this.skillPaintRandomPaint;
         if (this.dart != null) {
            skillPaintValue2 = this.dart.skillPaint;
         }

         if (skillPaintValue2 != null) {
            if (this.attMobs != null) {
               this.effPaints = new EffectPaint[this.attMobs.length];

               for (int attMobIndex = 0; attMobIndex < this.attMobs.length; attMobIndex++) {
                  this.effPaints[attMobIndex] = new EffectPaint();
                  this.effPaints[attMobIndex].effCharPaint = GameScr.efs[skillPaintValue2.effectHappenOnMob - 1];
                  this.effPaints[attMobIndex].eMob = this.attMobs[attMobIndex];
               }

               this.attMobs = null;
               return;
            }

            if (this.attChars != null) {
               this.effPaints = new EffectPaint[this.attChars.length];

               for (int attCharIndex = 0; attCharIndex < this.attChars.length; attCharIndex++) {
                  this.effPaints[attCharIndex] = new EffectPaint();
                  this.effPaints[attCharIndex].effCharPaint = GameScr.efs[skillPaintValue2.effectHappenOnMob - 1];
                  this.effPaints[attCharIndex].eChar = this.attChars[attCharIndex];
               }

               this.attChars = null;
            }
         }
      }
   }

   public final boolean isOutX() {
      return this.cx < GameScr.cmx ? true : this.cx > GameScr.cmx + GameScr.gW;
   }

   public final boolean isPaint() {
      if (this.cy < GameScr.cmy) {
         return false;
      } else if (this.cy > GameScr.cmy + GameScr.gH + 30) {
         return false;
      } else if (this.isOutX()) {
         return false;
      } else {
         return this.isSetPos ? false : !this.isFusion;
      }
   }

   public final void createShadow(int x, int y, int life) {
      this.shadowX = x;
      this.shadowY = y;
      this.shadowLife = 10;
   }

   public final void setMabuHold(boolean m) {
      this.isMabuHold = m;
   }

   public void paint(mGraphics g) {
      if (!this.isHide) {
         if (this.isMafuba) {
            this.paintCharWithoutSkill(g);
         } else if (this.isMabuHold) {
            if (this.cmtoChar) {
               GameScr.cmtoX = this.cx - GameScr.gW2;
               GameScr.cmtoY = this.cy - GameScr.gH23 - 1;
               if (!GameCanvas.isTouchControl) {
                  GameScr.cmtoX = GameScr.cmtoX + GameScr.gW6 * this.cdir;
               }
            }
         } else if (this.isPaint()) {
            if (this.petFollow != null) {
               PetFollow petFollowValue = this.petFollow;
               int width = 32;
               int height = 32;
               int value = GameCanvas.gameTick % 10 > 5 ? 1 : 0;
               if (petFollowValue.fimg > 0) {
                  width = petFollowValue.wimg;
                  height = petFollowValue.himg;
                  value = 0;
               }

               SmallImage.drawSmallImage(
                  g,
                  petFollowValue.smallID,
                  petFollowValue.f,
                  petFollowValue.cmx,
                  petFollowValue.cmy + 3 + value,
                  width,
                  height,
                  petFollowValue.dir == 1 ? 0 : 2,
                  StaticObj.VCENTER_HCENTER
               );
            }

            if (this.xMount > GameScr.cmx && this.xMount < GameScr.cmx + GameCanvas.w) {
               if (this.me) {
                  if (this.isEndMount || this.isStartMount || this.isMount) {
                     if (this.idMount >= ID_NEW_MOUNT) {
                        FrameImage fraImage;
                        if ((fraImage = mSystem.getFraImage(this.strMount + (this.idMount - ID_NEW_MOUNT) + "_0")) != null) {
                           fraImage.drawFrame(
                              this.frameNewMount / 2 % fraImage.nFrame,
                              this.xMount,
                              this.yMount + this.fy,
                              this.transMount,
                              3,
                              g
                           );
                        }
                     } else if (!this.isSpeacialMount) {
                        if (this.isEventMount) {
                           g.drawRegion(
                              imgEventMountWing,
                              0,
                              this.FrameMount[this.frameMount] * 60,
                              60,
                              60,
                              this.transMount,
                              this.xMount + this.dxMount,
                              this.yMount + this.dyMount + this.fy,
                              0
                           );
                        } else if (this.genderMount == 2) {
                           if (!this.isMountVip) {
                              g.drawRegion(
                                 imgMount_XD,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           } else {
                              g.drawRegion(
                                 imgMount_XD_VIP,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           }
                        } else if (this.genderMount == 1) {
                           if (!this.isMountVip) {
                              g.drawRegion(
                                 imgMount_NM,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           } else {
                              g.drawRegion(
                                 imgMount_NM_VIP,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           }
                        }
                     }
                  }
               } else if (!this.me) {
                  if (this.idMount >= ID_NEW_MOUNT) {
                     FrameImage fraImage2;
                     if ((fraImage2 = mSystem.getFraImage(this.strMount + (this.idMount - ID_NEW_MOUNT) + "_0")) != null) {
                        fraImage2.drawFrame(
                           this.frameNewMount / 2 % fraImage2.nFrame,
                           this.xMount,
                           this.yMount + this.fy,
                           this.transMount,
                           3,
                           g
                        );
                     }
                  } else if (!this.isSpeacialMount) {
                     if (this.isEventMount) {
                        g.drawRegion(
                           imgEventMountWing,
                           0,
                           this.FrameMount[this.frameMount] * 60,
                           60,
                           60,
                           this.transMount,
                           this.xMount + this.dxMount,
                           this.yMount + this.dyMount + this.fy,
                           0
                        );
                     } else if (this.isMount) {
                        if (this.genderMount == 2) {
                           if (!this.isMountVip) {
                              g.drawRegion(
                                 imgMount_XD,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           } else {
                              g.drawRegion(
                                 imgMount_XD_VIP,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           }
                        } else if (this.genderMount == 1) {
                           if (!this.isMountVip) {
                              g.drawRegion(
                                 imgMount_NM,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           } else {
                              g.drawRegion(
                                 imgMount_NM_VIP,
                                 0,
                                 this.FrameMount[this.frameMount] * 40,
                                 50,
                                 40,
                                 this.transMount,
                                 this.xMount + this.dxMount,
                                 this.yMount + this.dyMount + this.fy,
                                 0
                              );
                           }
                        }
                     }
                  }
               }
            }

            if (!TileMap.isInAirMap() || this.cy < TileMap.pxh - 48) {
               if (!this.isTeleport) {
                  if (this.holder && GameCanvas.gameTick % 2 == 0) {
                     g.setColor(16185600);
                     if (this.charHold != null) {
                        g.drawLine(
                           this.cx,
                           this.cy - this.ch / 2,
                           this.charHold.cx,
                           this.charHold.cy - this.charHold.ch / 2
                        );
                     }

                     if (this.mobHold != null) {
                        g.drawLine(
                           this.cx,
                           this.cy - this.ch / 2,
                           this.mobHold.x,
                           this.mobHold.y - this.mobHold.h / 2
                        );
                     }
                  }

                  if ((!this.me || isPaintAura2)
                     && this.idAuraEff < 0
                     && (this.statusMe == 1 || this.statusMe == 6)
                     && mSystem.currentTimeMillis() - this.timeBlue > 0L
                     && !this.isCopy
                     && this.clevel >= 16) {
                     int sourceY = 7598;
                     if (this.clevel >= 19) {
                        sourceY = 7676;
                     }

                     if (this.clevel >= 22) {
                        sourceY = 7677;
                     }

                     if (this.clevel >= 25) {
                        sourceY = 7678;
                     }

                     if (sourceY != -1) {
                        Small imgNew;
                        if ((imgNew = SmallImage.imgNew[sourceY]) == null) {
                           SmallImage.createImage(sourceY);
                        } else {
                           sourceY = GameCanvas.gameTick / 4 % 4 * (mGraphics.getImageHeight(imgNew.img) / 4);
                           g.drawRegion(
                              imgNew.img,
                              0,
                              sourceY,
                              mGraphics.getImageWidth(imgNew.img),
                              mGraphics.getImageHeight(imgNew.img) / 4,
                              0,
                              this.cx,
                              this.cy + 2,
                              33
                           );
                        }
                     }
                  }

                  FrameImage fraImage3;
                  if ((!this.me || isPaintAura)
                     && this.idAuraEff >= 0
                     && (this.statusMe == 1 || this.statusMe == 6)
                     && !GameCanvas.panel.isShow
                     && mSystem.currentTimeMillis() - this.timeBlue > 0L
                     && (fraImage3 = mSystem.getFraImage(this.strEffAura + this.idAuraEff + "_0")) != null) {
                     fraImage3.drawFrame(
                        GameCanvas.gameTick / 4 % fraImage3.nFrame,
                        this.cx,
                        this.cy,
                        this.cdir == 1 ? 0 : 2,
                        33,
                        g
                     );
                  }

                  this.paintEffBehind(g);
                  if (this.idEff_Set_Item != -1) {
                     if (this.fraEff != null) {
                        this.fraEff
                           .drawFrame(
                              GameCanvas.gameTick / 4 % this.fraEff.nFrame,
                              this.cx,
                              this.cy + 3,
                              this.cdir == 1 ? 0 : 2,
                              33,
                              g
                           );
                     } else {
                        this.fraEff = mSystem.getFraImage(this.strEff_Set_Item + this.idEff_Set_Item + "_0");
                     }
                  }

                  this.paintEff_Pet(g);
                  if (this.shadowLife > 0) {
                     if (GameCanvas.gameTick % 2 == 0) {
                        this.paintCharBody(g, this.shadowX, this.shadowY, this.cdir, 25, true);
                     } else if (this.shadowLife > 5) {
                        this.paintCharBody(g, this.shadowX, this.shadowY, this.cdir, 7, true);
                     }
                  }

                  if (this.isPaint()
                     || this.skillPaint == null
                     || this.skillPaint.id >= 70 && this.skillPaint.id <= 76
                     || this.skillPaint.id >= 77 && this.skillPaint.id <= 83) {
                     if (this.statusMe != 15 && (this.moveFast == null || this.moveFast[0] <= 0)) {
                        Part part = GameScr.parts[this.getFHead(this.head)];
                        int value2 = CharInfo[this.cf][0][2]
                           - part.pi[CharInfo[this.cf][0][0]].dy
                           + 5;
                        if ((!this.isInvisiblez || this.me)
                           && (this.me || TileMap.mapID != 113 || this.cy < 360)) {
                           if (this.me) {
                              int value3 = value2 + 5;
                              this.paintHp(g, this.cx, this.cy - value3 + 3);
                           } else {
                              boolean condition = myChar.clan != null
                                 && this.clanID == myChar.clan.ID;
                              boolean condition2 = this.cTypePk == 3 || this.cTypePk == 5;
                              boolean condition3 = this.cTypePk == 4;
                              if (this.cName.startsWith("$")) {
                                 this.cName = this.cName.substring(1);
                                 this.isPet = true;
                              }

                              if (this.cName.startsWith("#")) {
                                 this.cName = this.cName.substring(1);
                                 this.isMiniPet = true;
                              }

                              if (myCharz().charFocus != null && myCharz().charFocus.equals(this)) {
                                 value2 += 5;
                                 this.paintHp(g, this.cx, this.cy - value2 + 3);
                              }

                              int value4 = value2 + mFont.tahoma_7.getHeight();
                              mFont nameFontGreen = mFont.tahoma_7_whiteSmall;
                              if (this.isPet || this.isMiniPet) {
                                 nameFontGreen = mFont.tahoma_7_blue1Small;
                              } else if (condition2) {
                                 nameFontGreen = mFont.nameFontRed;
                              } else if (condition3) {
                                 nameFontGreen = mFont.nameFontYellow;
                              } else if (condition) {
                                 nameFontGreen = mFont.nameFontGreen;
                              }

                              if (TileMap.mapID == 170) {
                                 if (this.flagImage == 2325) {
                                    nameFontGreen = mFont.tahoma_7_blue;
                                 } else if (this.flagImage == 2323) {
                                    nameFontGreen = mFont.tahoma_7_red;
                                 }
                              }

                              if ((this.paintName || condition2 || condition3) && !condition) {
                                 if (mSystem.clientType == 1) {
                                    nameFontGreen.drawString(
                                       g, this.cName, this.cx, this.cy - value4, 2, mFont.tahoma_7_greySmall
                                    );
                                 } else {
                                    nameFontGreen.drawString(g, this.cName, this.cx, this.cy - value4, 2);
                                 }

                                 value4 += mFont.tahoma_7.getHeight();
                              }

                              if (condition) {
                                 if (myCharz().charFocus != null && myCharz().charFocus.equals(this)) {
                                    nameFontGreen.drawString(
                                       g, this.cName, this.cx, this.cy - value4, 2, mFont.tahoma_7_greySmall
                                    );
                                 } else if (this.charFocus == null) {
                                    nameFontGreen.drawString(
                                       g,
                                       this.cName,
                                       this.cx - 10,
                                       this.cy - value4 + 3,
                                       0,
                                       mFont.tahoma_7_grey
                                    );
                                    this.paintHp(g, this.cx - 16, this.cy - value4 + 10);
                                 }
                              }
                           }
                        }

                        if (this.skillPaint == null || this.skillInfoPaint() == null || this.indexSkill >= this.skillInfoPaint().length) {
                           this.paintCharWithoutSkill(g);
                        }

                        if (this.arr != null) {
                           this.arr.paint(g);
                        }

                        if (this.dart != null) {
                           this.dart.paint(g);
                        }

                        this.paintEffect(g);
                        this.paintMount2(g);
                        if (this.idEff_Set_Item != -1) {
                           if (this.fraEffSub != null) {
                              this.fraEffSub
                                 .drawFrame(
                                    GameCanvas.gameTick / 4 % this.fraEffSub.nFrame,
                                    this.cx,
                                    this.cy + 8,
                                    this.cdir == 1 ? 0 : 2,
                                    33,
                                    g
                                 );
                           } else {
                              this.fraEffSub = mSystem.getFraImage(this.strEff_Set_Item + this.idEff_Set_Item + "_1");
                           }
                        }

                        if (isPaintAura2) {
                           if (this.statusMe != 1 && this.statusMe != 6) {
                              this.timeBlue = mSystem.currentTimeMillis() + 1500L;
                              this.IsAddDust1 = true;
                              this.IsAddDust2 = true;
                           } else if (mSystem.currentTimeMillis() - this.timeBlue > 0L) {
                              if (this.isCopy) {
                                 if (GameCanvas.gameTick % 2 == 0) {
                                    this.tBlue++;
                                 }

                                 if (this.tBlue > 6) {
                                    this.tBlue = 0;
                                 }

                                 g.drawImage(
                                    GameCanvas.imgViolet[this.tBlue], ((int) (this.cx)), ((int) (this.cy + 9)), 33
                                 );
                              } else {
                                 if (this.clevel >= 14 && !GameCanvas.lowGraphic) {
                                    boolean condition4 = false;
                                    if (mSystem.currentTimeMillis() - this.timeBlue > -1000L && this.IsAddDust1) {
                                       condition4 = true;
                                       this.IsAddDust1 = false;
                                    }

                                    if (mSystem.currentTimeMillis() - this.timeBlue > -500L && this.IsAddDust2) {
                                       condition4 = true;
                                       this.IsAddDust2 = false;
                                    }

                                    if (condition4) {
                                       GameCanvas.gI().startDust(-1, this.cx - -8, this.cy);
                                       GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
                                       this.addDustEff(1);
                                    }
                                 }

                                 if (this.clevel == 14) {
                                    if (GameCanvas.gameTick % 2 == 0) {
                                       this.tBlue++;
                                    }

                                    if (this.tBlue > 6) {
                                       this.tBlue = 0;
                                    }

                                    g.drawImage(
                                       GameCanvas.imgBlue[this.tBlue], ((int) (this.cx)), ((int) (this.cy + 9)), 33
                                    );
                                 } else if (this.clevel == 15) {
                                    if (GameCanvas.gameTick % 2 == 0) {
                                       this.tBlue++;
                                    }

                                    if (this.tBlue > 6) {
                                       this.tBlue = 0;
                                    }

                                    g.drawImage(
                                       GameCanvas.imgViolet[this.tBlue], ((int) (this.cx)), ((int) (this.cy + 9)), 33
                                    );
                                 } else if (this.clevel >= 16) {
                                    short shortValue = -1;
                                    if (this.clevel >= 16 && this.clevel < 22) {
                                       shortValue = 7599;
                                    }

                                    if (shortValue != -1) {
                                       Small imgNew2;
                                       if ((imgNew2 = SmallImage.imgNew[7599]) == null) {
                                          SmallImage.createImage(7599);
                                       } else {
                                          int sourceY2 = GameCanvas.gameTick / 4 % 4 * (mGraphics.getImageHeight(imgNew2.img) / 4);
                                          g.drawRegion(
                                             imgNew2.img,
                                             0,
                                             sourceY2,
                                             mGraphics.getImageWidth(imgNew2.img),
                                             mGraphics.getImageHeight(imgNew2.img) / 4,
                                             0,
                                             this.cx,
                                             this.cy + 2,
                                             33
                                          );
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        if ((!this.me || isPaintAura) && this.idAuraEff >= 0) {
                           if (this.statusMe != 1 && this.statusMe != 6) {
                              this.timeBlue = mSystem.currentTimeMillis() + 1500L;
                              this.IsAddDust1 = true;
                              this.IsAddDust2 = true;
                           } else if (!GameCanvas.panel.isShow && !GameCanvas.lowGraphic) {
                              boolean condition5 = false;
                              if (mSystem.currentTimeMillis() - this.timeBlue > -1000L && this.IsAddDust1) {
                                 condition5 = true;
                                 this.IsAddDust1 = false;
                              }

                              if (mSystem.currentTimeMillis() - this.timeBlue > -500L && this.IsAddDust2) {
                                 condition5 = true;
                                 this.IsAddDust2 = false;
                              }

                              if (condition5) {
                                 GameCanvas.gI().startDust(-1, this.cx - -8, this.cy);
                                 GameCanvas.gI().startDust(1, this.cx - 8, this.cy);
                                 this.addDustEff(1);
                              }

                              FrameImage fraImage4;
                              if (mSystem.currentTimeMillis() - this.timeBlue > 0L
                                 && (fraImage4 = mSystem.getFraImage(this.strEffAura + this.idAuraEff + "_1")) != null) {
                                 fraImage4.drawFrame(
                                    GameCanvas.gameTick / 4 % fraImage4.nFrame,
                                    this.cx,
                                    this.cy + 2,
                                    this.cdir == 1 ? 0 : 2,
                                    33,
                                    g
                                 );
                              }
                           }
                        }

                        this.paintEffFront(g);
                        if (!this.isPaintNewSkill && this.x_hint != 0 && this.y_hint != 0 && this.statusMe != 14) {
                           byte transform = 0;
                           int x = this.cx - 30;
                           int y = this.cy - 15;
                           if (Res.abs(this.cy - this.y_hint) > 150) {
                              if (this.cy > this.y_hint) {
                                 transform = 7;
                                 x = this.cx;
                                 y = this.cy - 15 - 60;
                              } else {
                                 transform = 5;
                                 x = this.cx;
                                 y = this.cy - 15 + 60;
                              }
                           } else if (this.cx > this.x_hint) {
                              transform = 2;
                           } else if (this.cx <= this.x_hint) {
                              x = this.cx + 30;
                           }

                           if (GameCanvas.gameTick % 10 >= 5) {
                              if (Res.abs(this.cx - this.x_hint) > 100) {
                                 g.drawRegion(GameScr.arrow, 0, 0, 13, 16, transform, x, y, StaticObj.VCENTER_HCENTER);
                                 return;
                              }

                              if (Res.abs(this.cx - this.x_hint) < 50) {
                                 g.drawImage(Panel.imgBantay, ((int) (this.x_hint + -30)), ((int) (this.y_hint - 60 + 5)), 0);
                              }
                           }
                        }
                     }
                  } else {
                     if (this.skillPaint != null) {
                        this.indexSkill = this.skillInfoPaint().length;
                        this.skillPaint = null;
                     }

                     this.effPaints = null;
                     this.eff = null;
                     this.effTask = null;
                     this.indexEff = -1;
                     this.indexEffTask = -1;
                  }
               }
            }
         }
      }
   }

   private void paintEff_Pet(mGraphics g) {
      for (int effCharIndex = 0; effCharIndex < this.vEffChar.size(); effCharIndex++) {
         Effect effChar;
         if ((effChar = (Effect)this.vEffChar.elementAt(effCharIndex)).effId >= 201) {
            effChar.paint(g);
         }
      }
   }

   private void paintEffect(mGraphics g) {
      if (this.effPaints != null) {
         for (int effPaintIndex = 0; effPaintIndex < this.effPaints.length; effPaintIndex++) {
            if (this.effPaints[effPaintIndex] != null) {
               if (this.effPaints[effPaintIndex].eMob != null) {
                  int y = this.effPaints[effPaintIndex].eMob.y;
                  if (this.effPaints[effPaintIndex].eMob instanceof BigBoss) {
                     y = this.effPaints[effPaintIndex].eMob.y - 60;
                  }

                  if (this.effPaints[effPaintIndex].eMob instanceof BigBoss2) {
                     y = this.effPaints[effPaintIndex].eMob.y - 50;
                  }

                  if (this.effPaints[effPaintIndex].eMob instanceof BachTuoc) {
                     y = this.effPaints[effPaintIndex].eMob.y - 40;
                  }

                  SmallImage.drawSmallImage(
                     g, this.effPaints[effPaintIndex].getImgId(), this.effPaints[effPaintIndex].eMob.x, y, 0, 33
                  );
               } else if (this.effPaints[effPaintIndex].eChar != null) {
                  SmallImage.drawSmallImage(
                     g,
                     this.effPaints[effPaintIndex].getImgId(),
                     this.effPaints[effPaintIndex].eChar.cx,
                     this.effPaints[effPaintIndex].eChar.cy,
                     0,
                     33
                  );
               }
            }
         }
      }

      if (this.indexEffTask >= 0 && this.effTask != null) {
         SmallImage.drawSmallImage(
            g,
            this.effTask.arrEfInfo[this.indexEffTask].idImg,
            this.cx + this.effTask.arrEfInfo[this.indexEffTask].dx,
            this.cy + this.effTask.arrEfInfo[this.indexEffTask].dy,
            0,
            3
         );
      }
   }

   public final void paintHp(mGraphics g, int x, int y) {
      int value;
      if ((value = (int)((long)((int)this.cHP * 100) / this.cHPFull) / 10 - 1) < 0) {
         value = 0;
      }

      if (value > 9) {
         value = 9;
      }

      if (!this.me) {
         g.drawRegion(Mob.imgHP, 0, 6 * (9 - value), 9, 6, 0, x, y - mFont.tahoma_7.getHeight() - 6, 3);
      }

      if (this.cTypePk != 0
         || myCharz().cFlag != 0
            && this.cFlag != 0
            && (this.cFlag == 8 || myCharz().cFlag == 8 || this.cFlag != myCharz().cFlag)) {
         this.len = (int)(this.cHP * 100L / this.cHPFull * (long)this.w_hp_bar) / 100;
         if ((value = (int)(this.cHP * 100L / this.cHPFull)) < 30) {
            this.imgHPtem = GameScr.imgHP_tm_do;
         } else if (value < 60) {
            this.imgHPtem = GameScr.imgHP_tm_vang;
         } else {
            this.imgHPtem = GameScr.imgHP_tm_xanh;
         }

         int imageWidth = mGraphics.getImageWidth(GameScr.imgHP_tm_xam);
         int height = mGraphics.getImageHeight(GameScr.imgHP_tm_xam);
         int width = imageWidth * value / 100;
         g.drawImage(GameScr.imgHP_tm_xam, ((int) (x - (imageWidth >> 1))), ((int) (y - 1)), 20);
         if (this.len >= 5 || GameCanvas.gameTick % 6 < 3) {
            g.drawRegion(this.imgHPtem, 0, 0, width, height, 0, x - (imageWidth >> 1), y - 1, 20);
         }
      }
   }

   public final void paintShadow(mGraphics g) {
      if (!this.isMabuHold) {
         if (this.head != 377) {
            if (this.leg != 471) {
               if (!this.isTeleport) {
                  if (!this.isFlyUp) {
                     byte width = TileMap.size;
                     if ((TileMap.mapID < 114 || TileMap.mapID > 120)
                        && TileMap.mapID != 127
                        && TileMap.mapID != 128
                        && !TileMap.tileTypeAt(this.xSd + width / 2, this.ySd + 1, 4)) {
                        if (TileMap.tileTypeAt((this.xSd - width / 2) / width, (this.ySd + 1) / width) == 0) {
                           g.setClip(this.xSd / width * width, (this.ySd - 30) / width * width, 100, 100);
                        } else if (TileMap.tileTypeAt((this.xSd + width / 2) / width, (this.ySd + 1) / width) == 0) {
                           g.setClip(this.xSd / width * width, (this.ySd - 30) / width * width, width, 100);
                        } else if (TileMap.tileTypeAt(this.xSd - width / 2, this.ySd + 1, 8)) {
                           g.setClip(this.xSd / 24 * width, (this.ySd - 30) / width * width, width, 100);
                        }
                     }

                     g.drawImage(TileMap.bong, ((int) (this.xSd)), ((int) (this.ySd)), 3);
                     g.setClip(
                        GameScr.cmx,
                        GameScr.cmy - GameCanvas.transY,
                        GameScr.gW,
                        GameScr.gH + 2 * GameCanvas.transY
                     );
                  }
               }
            }
         }
      }
   }

   public final void updateShadown() {
      int index = 0;
      this.xSd = this.cx;
      if (TileMap.tileTypeAt(this.cx, this.cy, 2)) {
         this.ySd = this.cy;
      } else {
         this.ySd = this.cy;

         while (index < 30) {
            index++;
            this.ySd += 24;
            if (TileMap.tileTypeAt(this.xSd, this.ySd, 2)) {
               if (this.ySd % 24 != 0) {
                  this.ySd = this.ySd - this.ySd % 24;
                  return;
               }
               break;
            }
         }
      }
   }

   private void paintCharWithoutSkill(mGraphics g) {
      try {
         if (this.isMafuba) {
            this.paintCharBody(g, this.xMFB, this.yMFB, this.cdir, this.cf, false);
         } else {
            if (this.isInvisiblez) {
               if (this.me) {
                  if (GameCanvas.gameTick % 50 != 48 && GameCanvas.gameTick % 50 != 90) {
                     SmallImage.drawSmallImage(g, 1195, this.cx, this.cy - 18, 0, 3);
                  } else {
                     SmallImage.drawSmallImage(g, 1196, this.cx, this.cy - 18, 0, 3);
                  }

                  return;
               }
            } else {
               this.paintCharBody(
                  g, this.cx, this.cy + this.fy, this.cdir, this.cf, true
               );
            }
         }
      } catch (Exception ignoredException) {
      }
   }

   public final void paintBag(mGraphics g, short[] id, int x, int y, int dir, boolean isPaintChar) {
      byte byteValue = 0;
      byte byteValue2 = 0;
      if (this.statusMe == 6) {
         byteValue = 8;
         byteValue2 = 17;
      }

      if (this.statusMe == 1) {
         if (this.cp1 % 15 < 5) {
            byteValue = 8;
            byteValue2 = 17;
         } else {
            byteValue = 8;
            byteValue2 = 18;
         }
      }

      if (this.statusMe == 2) {
         if (this.cf <= 3) {
            byteValue = 7;
            byteValue2 = 17;
         } else {
            byteValue = 7;
            byteValue2 = 18;
         }
      }

      if (this.statusMe == 3 || this.statusMe == 9) {
         byteValue = 5;
         byteValue2 = 20;
      }

      if (this.statusMe == 4) {
         if (this.cf == 8) {
            byteValue = 5;
            byteValue2 = 16;
         } else {
            byteValue = 5;
            byteValue2 = 20;
         }
      }

      if (this.statusMe == 10) {
         if (this.cf == 8) {
            byteValue = 0;
            byteValue2 = 23;
         } else {
            byteValue = 5;
            byteValue2 = 22;
         }
      }

      if (this.isInjure > 0) {
         byteValue = 5;
         byteValue2 = 18;
      }

      if (this.skillPaint != null && this.skillInfoPaint() != null && this.indexSkill < this.skillInfoPaint().length) {
         byteValue = -1;
         byteValue2 = 17;
      }

      this.fBag++;
      if (this.fBag > 10000) {
         this.fBag = 0;
      }

      byte idIndex = (byte)(this.fBag / 4 % id.length);
      if (!isPaintChar) {
         if (id.length == 2) {
            idIndex = 1;
         }

         if (id.length == 3) {
            if (id[2] >= 0) {
               idIndex = 2;
               if (GameCanvas.gameTick % 10 > 5) {
                  idIndex = 1;
               }
            } else {
               idIndex = 1;
            }
         }
      } else if (id.length > 1 && (idIndex == 0 || idIndex == 1) && this.statusMe != 1 && this.statusMe != 6) {
         this.fBag = 0;
         idIndex = 0;
         if (GameCanvas.gameTick % 10 > 5) {
            idIndex = 1;
         }
      }

      SmallImage.drawSmallImage(g, id[idIndex], x + (dir == 1 ? -byteValue : byteValue), y - byteValue2, dir == 1 ? 0 : 2, StaticObj.VCENTER_HCENTER);
   }

   public final boolean isCharBodyImageID(int id) {
      Part part = GameScr.parts[this.head];
      Part part2 = GameScr.parts[this.leg];
      Part part3 = GameScr.parts[this.body];

      for (int charInfoIndex = 0; charInfoIndex < CharInfo.length; charInfoIndex++) {
         if (id == part.pi[CharInfo[charInfoIndex][0][0]].id) {
            return true;
         }

         if (id == part2.pi[CharInfo[charInfoIndex][1][0]].id) {
            return true;
         }

         if (id == part3.pi[CharInfo[charInfoIndex][2][0]].id) {
            return true;
         }
      }

      return false;
   }

   public final void paintHead(mGraphics g, int cx, int cy, int look) {
      Part part = GameScr.parts[this.head];
      SmallImage.drawSmallImage(g, part.pi[CharInfo[0][0][0]].id, cx, cy, 0, 10);
   }

   public final void paintHeadWithXY(mGraphics g, int x, int y, int look) {
      Part part = GameScr.parts[this.head];
      SmallImage.drawSmallImage(
         g,
         part.pi[CharInfo[0][0][0]].id,
         x + CharInfo[0][0][1] + part.pi[CharInfo[0][0][0]].dx - 3,
         y + 3,
         look,
         36
      );
   }

   public final void paintCharBody(mGraphics g, int cx, int cy, int cdir, int cf, boolean isPaintBag) {
      this.ph = GameScr.parts[this.head];
      this.pl = GameScr.parts[this.leg];
      this.pb = GameScr.parts[this.body];
      if (this.bag >= 0 && this.statusMe != 14) {
         if (!ClanImage.idImages.containsKey(String.valueOf(this.bag))) {
            ClanImage.idImages.put(String.valueOf(this.bag), new ClanImage());
            Service.gI().requestBagImage(this.bag);
         } else {
            ClanImage clanImage;
            if ((clanImage = (ClanImage)ClanImage.idImages.get(String.valueOf(this.bag))).idImage != null && isPaintBag) {
               this.paintBag(g, clanImage.idImage, cx, cy, cdir, true);
            }
         }
      }

      byte trans = 2;
      byte anchor = 24;
      int anchor2 = StaticObj.TOP_RIGHT;
      byte byteValue = -1;
      if (cdir == 1) {
         trans = 0;
         anchor = 0;
         anchor2 = 0;
         byteValue = 1;
      }

      if (this.statusMe == 14) {
         if (GameCanvas.gameTick % 4 > 0) {
            g.drawImage(ItemMap.imageFlare, ((int) (cx)), ((int) (cy - this.ch - 11)), 3);
         }

         byte byteValue2 = 0;
         if (this.head == 89
            || this.head == 457
            || this.head == 460
            || this.head == 461
            || this.head == 462
            || this.head == 463
            || this.head == 464
            || this.head == 465
            || this.head == 466) {
            byteValue2 = 15;
         }

         if (this.head == 1291) {
            byteValue2 = 23;
         }

         SmallImage.drawSmallImage(
            g,
            834,
            cx,
            cy - CharInfo[cf][2][2] + this.pb.pi[CharInfo[cf][2][0]].dy - 2 + byteValue2,
            trans,
            StaticObj.TOP_CENTER
         );
         SmallImage.drawSmallImage(g, 79, cx, cy - this.ch - 8, 0, 33);
         SmallImage.drawSmallImage(
            g,
            this.ph.pi[CharInfo[cf][0][0]].id,
            cx + (CharInfo[cf][0][1] + this.ph.pi[CharInfo[cf][0][0]].dx) * byteValue,
            cy - CharInfo[cf][0][2] + this.ph.pi[CharInfo[cf][0][0]].dy,
            trans,
            anchor
         );
         this.paintHat_behind(
            g, cf, cy - CharInfo[cf][2][2] + this.pb.pi[CharInfo[cf][2][0]].dy
         );
         if (isHead_2Fr(this.head)) {
            Part part = GameScr.parts[this.getFHead(this.head)];
            SmallImage.drawSmallImage(
               g,
               part.pi[CharInfo[cf][0][0]].id,
               cx + (CharInfo[cf][0][1] + part.pi[CharInfo[cf][0][0]].dx) * byteValue,
               cy - CharInfo[cf][0][2] + part.pi[CharInfo[cf][0][0]].dy,
               trans,
               anchor
            );
         } else {
            SmallImage.drawSmallImage(
               g,
               this.ph.pi[CharInfo[cf][0][0]].id,
               cx + (CharInfo[cf][0][1] + this.ph.pi[CharInfo[cf][0][0]].dx) * byteValue,
               cy - CharInfo[cf][0][2] + this.ph.pi[CharInfo[cf][0][0]].dy,
               trans,
               anchor
            );
         }

         int value = cy - CharInfo[cf][2][2] + this.pb.pi[CharInfo[cf][2][0]].dy;
         int hatInfoIndex = cf;
         mGraphics g2 = g;
         Char character = this;

         try {
            if (character.idHat != -1) {
               if (usesSideHatFrame(hatInfoIndex)) {
                  if (character.fraHat_font_2 != null) {
                     character.fraHat_font_2
                        .drawFrame(
                           GameCanvas.gameTick / 4 % character.fraHat_font_2.nFrame,
                           character.cx + hatInfo[hatInfoIndex][0] * (character.cdir == 1 ? 1 : -1),
                           value + hatInfo[hatInfoIndex][1],
                           character.cdir == 1 ? 0 : 2,
                           33,
                           g2
                        );
                  } else {
                     character.fraHat_font_2 = mSystem.getFraImage(character.strHat_font + character.strNgang + character.idHat);
                  }
               } else if (character.fraHat_font != null) {
                  character.fraHat_font
                     .drawFrame(
                        GameCanvas.gameTick / 4 % character.fraHat_font.nFrame,
                        character.cx + hatInfo[hatInfoIndex][0] * (character.cdir == 1 ? 1 : -1),
                        value + hatInfo[hatInfoIndex][1],
                        character.cdir == 1 ? 0 : 2,
                        33,
                        g2
                     );
               } else {
                  character.fraHat_font = mSystem.getFraImage(character.strHat_font + character.idHat);
               }
            }
         } catch (Exception ignoredException) {
         }

         this.paintRedEye(
            g,
            cx + (CharInfo[cf][0][1] + this.ph.pi[CharInfo[cf][0][0]].dx) * byteValue,
            cy - CharInfo[cf][0][2] + this.ph.pi[CharInfo[cf][0][0]].dy,
            trans,
            anchor
         );
      } else {
         this.paintHat_behind(
            g, cf, cy - CharInfo[cf][2][2] + this.pb.pi[CharInfo[cf][2][0]].dy
         );

         try {
            if (isHead_2Fr(this.head)) {
               Part part2 = GameScr.parts[this.getFHead(this.head)];
               SmallImage.drawSmallImage(
                  g,
                  part2.pi[CharInfo[cf][0][0]].id,
                  cx + (CharInfo[cf][0][1] + part2.pi[CharInfo[cf][0][0]].dx) * byteValue,
                  cy - CharInfo[cf][0][2] + part2.pi[CharInfo[cf][0][0]].dy,
                  trans,
                  anchor
               );
            } else {
               SmallImage.drawSmallImage(
                  g,
                  this.ph.pi[CharInfo[cf][0][0]].id,
                  cx + (CharInfo[cf][0][1] + this.ph.pi[CharInfo[cf][0][0]].dx) * byteValue,
                  cy - CharInfo[cf][0][2] + this.ph.pi[CharInfo[cf][0][0]].dy,
                  trans,
                  anchor
               );
            }

            SmallImage.drawSmallImage(
               g,
               this.pl.pi[CharInfo[cf][1][0]].id,
               cx + (CharInfo[cf][1][1] + this.pl.pi[CharInfo[cf][1][0]].dx) * byteValue,
               cy - CharInfo[cf][1][2] + this.pl.pi[CharInfo[cf][1][0]].dy,
               trans,
               anchor
            );
            SmallImage.drawSmallImage(
               g,
               this.pb.pi[CharInfo[cf][2][0]].id,
               cx + (CharInfo[cf][2][1] + this.pb.pi[CharInfo[cf][2][0]].dx) * byteValue,
               cy - CharInfo[cf][2][2] + this.pb.pi[CharInfo[cf][2][0]].dy,
               trans,
               anchor
            );
            this.paintRedEye(
               g,
               cx + (CharInfo[cf][0][1] + this.ph.pi[CharInfo[cf][0][0]].dx) * byteValue,
               cy - CharInfo[cf][0][2] + this.ph.pi[CharInfo[cf][0][0]].dy,
               trans,
               anchor
            );
         } catch (Exception ignoredException2) {
         }
      }

      this.ch = this.isMonkey != 1 && !this.isFusion
         ? CharInfo[0][0][2] + this.ph.pi[CharInfo[0][0][0]].dy + 10
         : 60;
      cdir = Res.abs(this.ph.pi[CharInfo[cf][0][0]].dy) >= 22
         ? (
            this.ph.pi[CharInfo[cf][0][0]].dy < 0
               ? this.ph.pi[CharInfo[cf][0][0]].dy + 5
               : this.ph.pi[CharInfo[cf][0][0]].dy - 5
         )
         : this.ph.pi[CharInfo[cf][0][0]].dy;
      this.cH_new = cy - CharInfo[cf][0][2] + cdir;
      if (this.statusMe == 1
         && this.charID > 0
         && !this.isMask
         && !this.isUseChargeSkill()
         && !this.isWaitMonkey
         && this.skillPaint == null
         && cf != 23
         && this.bag < 0
         && ((GameCanvas.gameTick + this.charID) % 30 == 0 || this.isFreez)) {
         g.drawImage(
            this.cgender == 1 ? eyeNamek : eyeTraiDat, ((int) (cx + byteValue * -2)), ((int) (cy - 32 + (this.cgender == 1 ? 10 : 11) - cf)), anchor2
         );
      }

      if (this.eProtect != null) {
         this.eProtect.paint(g);
      }

      if (this.eDanhHieu != null) {
         this.eDanhHieu.paint(g);
      }

      if (this.cdir == 1) {
         if (this.cFlag != 0 && this.cFlag != -1) {
            SmallImage.drawSmallImage(
               g,
               this.flagImage,
               this.cx - 10,
               this.cy - this.ch - 30 + (GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0),
               2,
               0
            );
            return;
         }
      } else if (this.cFlag != 0 && this.cFlag != -1) {
         SmallImage.drawSmallImage(
            g,
            this.flagImage,
            this.cx,
            this.cy - this.ch - 30 + (GameCanvas.gameTick % 20 > 10 ? GameCanvas.gameTick % 4 / 2 : 0),
            0,
            0
         );
      }
   }

   public final void paintCharWithSkill(mGraphics g) {
      this.ty = 0;
      SkillInfoPaint[] skillInfoPaints = this.skillInfoPaint();
      this.cf = skillInfoPaints[this.indexSkill].status;
      this.paintCharWithoutSkill(g);
      if (this.cdir == 1) {
         if (this.eff0 != null) {
            if (this.dx0 == 0) {
               this.dx0 = skillInfoPaints[this.indexSkill].e0dx;
            }

            if (this.dy0 == 0) {
               this.dy0 = skillInfoPaints[this.indexSkill].e0dy;
            }

            SmallImage.drawSmallImage(
               g,
               this.eff0.arrEfInfo[this.i0].idImg,
               this.cx + this.dx0 + this.eff0.arrEfInfo[this.i0].dx,
               this.cy + this.dy0 + this.eff0.arrEfInfo[this.i0].dy,
               0,
               3
            );
            this.i0++;
            if (this.i0 >= this.eff0.arrEfInfo.length) {
               this.eff0 = null;
               this.i0 = this.dx0 = this.dy0 = 0;
            }
         }

         if (this.eff1 != null) {
            if (this.dx1 == 0) {
               this.dx1 = skillInfoPaints[this.indexSkill].e1dx;
            }

            if (this.dy1 == 0) {
               this.dy1 = skillInfoPaints[this.indexSkill].e1dy;
            }

            SmallImage.drawSmallImage(
               g,
               this.eff1.arrEfInfo[this.i1].idImg,
               this.cx + this.dx1 + this.eff1.arrEfInfo[this.i1].dx,
               this.cy + this.dy1 + this.eff1.arrEfInfo[this.i1].dy,
               0,
               3
            );
            this.i1++;
            if (this.i1 >= this.eff1.arrEfInfo.length) {
               this.eff1 = null;
               this.i1 = this.dx1 = this.dy1 = 0;
            }
         }

         if (this.eff2 != null) {
            if (this.dx2 == 0) {
               this.dx2 = skillInfoPaints[this.indexSkill].e2dx;
            }

            if (this.dy2 == 0) {
               this.dy2 = skillInfoPaints[this.indexSkill].e2dy;
            }

            SmallImage.drawSmallImage(
               g,
               this.eff2.arrEfInfo[this.i2].idImg,
               this.cx + this.dx2 + this.eff2.arrEfInfo[this.i2].dx,
               this.cy + this.dy2 + this.eff2.arrEfInfo[this.i2].dy,
               0,
               3
            );
            this.i2++;
            if (this.i2 >= this.eff2.arrEfInfo.length) {
               this.eff2 = null;
               this.i2 = this.dx2 = this.dy2 = 0;
            }
         }
      } else {
         if (this.eff0 != null) {
            if (this.dx0 == 0) {
               this.dx0 = skillInfoPaints[this.indexSkill].e0dx;
            }

            if (this.dy0 == 0) {
               this.dy0 = skillInfoPaints[this.indexSkill].e0dy;
            }

            SmallImage.drawSmallImage(
               g,
               this.eff0.arrEfInfo[this.i0].idImg,
               this.cx - this.dx0 - this.eff0.arrEfInfo[this.i0].dx,
               this.cy + this.dy0 + this.eff0.arrEfInfo[this.i0].dy,
               2,
               3
            );
            this.i0++;
            if (this.i0 >= this.eff0.arrEfInfo.length) {
               this.eff0 = null;
               this.i0 = 0;
               this.dx0 = 0;
               this.dy0 = 0;
            }
         }

         if (this.eff1 != null) {
            if (this.dx1 == 0) {
               this.dx1 = skillInfoPaints[this.indexSkill].e1dx;
            }

            if (this.dy1 == 0) {
               this.dy1 = skillInfoPaints[this.indexSkill].e1dy;
            }

            SmallImage.drawSmallImage(
               g,
               this.eff1.arrEfInfo[this.i1].idImg,
               this.cx - this.dx1 - this.eff1.arrEfInfo[this.i1].dx,
               this.cy + this.dy1 + this.eff1.arrEfInfo[this.i1].dy,
               2,
               3
            );
            this.i1++;
            if (this.i1 >= this.eff1.arrEfInfo.length) {
               this.eff1 = null;
               this.i1 = 0;
               this.dx1 = 0;
               this.dy1 = 0;
            }
         }

         if (this.eff2 != null) {
            if (this.dx2 == 0) {
               this.dx2 = skillInfoPaints[this.indexSkill].e2dx;
            }

            if (this.dy2 == 0) {
               this.dy2 = skillInfoPaints[this.indexSkill].e2dy;
            }

            SmallImage.drawSmallImage(
               g,
               this.eff2.arrEfInfo[this.i2].idImg,
               this.cx - this.dx2 - this.eff2.arrEfInfo[this.i2].dx,
               this.cy + this.dy2 + this.eff2.arrEfInfo[this.i2].dy,
               2,
               3
            );
            this.i2++;
            if (this.i2 >= this.eff2.arrEfInfo.length) {
               this.eff2 = null;
               this.i2 = 0;
               this.dx2 = 0;
               this.dy2 = 0;
            }
         }
      }

      this.indexSkill++;
   }

   public final void moveTo(int toX, int toY, int type) {
      if (type != 1 && Res.abs(toX - this.cx) <= 100 && Res.abs(toY - this.cy) <= 300
         )
       {
         byte dir = 0;
         byte act = 0;
         int deltaX = toX - this.cx;
         int deltaY = toY - this.cy;
         if (deltaX == 0 && deltaY == 0) {
            act = 1;
            this.cp3 = 0;
         } else if (deltaY == 0) {
            act = 2;
            if (deltaX > 0) {
               dir = 1;
            }

            if (deltaX < 0) {
               dir = -1;
            }
         } else if (deltaY != 0) {
            if (deltaY < 0) {
               act = 3;
            }

            if (deltaY > 0) {
               act = 4;
            }

            if (deltaX < 0) {
               dir = -1;
            }

            if (deltaX > 0) {
               dir = 1;
            }
         }

         this.vMovePoints.addElement(new MovePoint(toX, toY, act, dir));
         if (this.statusMe != 6) {
            this.statusBeforeNothing = this.statusMe;
         }

         this.statusMe = 6;
         this.cp3 = 0;
      } else {
         this.createShadow(this.cx, this.cy, 10);
         this.cx = toX;
         this.cy = toY;
         this.vMovePoints.removeAllElements();
         this.statusMe = 6;
         this.cp3 = 0;
         this.currentMovePoint = null;
         this.cf = 25;
      }
   }

   private void searchFocus() {
      if (myCharz().skillPaint != null || myCharz().arr != null || myCharz().dart != null) {
         this.timeFocusToMob = 200;
      } else if (this.timeFocusToMob > 0) {
         this.timeFocusToMob--;
      } else {
         if (isManualFocus && this.charFocus != null && (this.charFocus.statusMe == 15 || this.charFocus.isInvisiblez)
            )
          {
            this.charFocus = null;
         }

         if (GameCanvas.gameTick % 2 != 0) {
            if (!this.isMeCanAttackOtherPlayer(this.charFocus)) {
               byte byteValue = 0;
               if (this.nClass != null
                  && (
                     this.nClass.classId == 0
                        || this.nClass.classId == 1
                        || this.nClass.classId == 3
                        || this.nClass.classId == 5
                  )) {
                  byteValue = 40;
               }

               int[] intValues = new int[]{-1, -1, -1, -1};
               int deltaX = GameScr.cmx - 10;
               int value = GameScr.cmx + GameCanvas.w + 10;
               int cmy = GameScr.cmy;
               int value2 = GameScr.cmy + GameCanvas.h - GameScr.cmdBarH + 10;
               if (isManualFocus) {
                  if (this.mobFocus != null
                        && this.mobFocus.status != 1
                        && this.mobFocus.status != 0
                        && deltaX <= this.mobFocus.x
                        && this.mobFocus.x <= value
                        && cmy <= this.mobFocus.y
                        && this.mobFocus.y <= value2
                     || this.npcFocus != null
                        && deltaX <= this.npcFocus.cx
                        && this.npcFocus.cx <= value
                        && cmy <= this.npcFocus.cy
                        && this.npcFocus.cy <= value2
                     || this.charFocus != null
                        && deltaX <= this.charFocus.cx
                        && this.charFocus.cx <= value
                        && cmy <= this.charFocus.cy
                        && this.charFocus.cy <= value2
                     || this.itemFocus != null
                        && deltaX <= this.itemFocus.x
                        && this.itemFocus.x <= value
                        && cmy <= this.itemFocus.y
                        && this.itemFocus.y <= value2) {
                     return;
                  }

                  isManualFocus = false;
               }

               int deltaX2 = myCharz().cx - 80;
               int value3 = myCharz().cx + 80;
               int deltaY = myCharz().cy - 30;
               int value4 = myCharz().cy + 30;
               if (this.npcFocus != null && this.npcFocus.template.npcTemplateId == 6) {
                  deltaX2 = myCharz().cx - 20;
                  value3 = myCharz().cx + 20;
                  deltaY = myCharz().cy - 10;
                  value4 = myCharz().cy + 10;
               }

               if (this.npcFocus == null) {
                  for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
                     Npc npcFocusValue;
                     if ((npcFocusValue = (Npc)GameScr.vNpc.elementAt(npcIndex)).statusMe != 15) {
                        int value5 = Math.abs(myCharz().cx - npcFocusValue.cx);
                        int value6 = Math.abs(myCharz().cy - npcFocusValue.cy);
                        int value7 = value5 > value6 ? value5 : value6;
                        deltaX2 = myCharz().cx - 80;
                        value3 = myCharz().cx + 80;
                        deltaY = myCharz().cy - 30;
                        value4 = myCharz().cy + 30;
                        if (npcFocusValue.template.npcTemplateId == 6) {
                           deltaX2 = myCharz().cx - 20;
                           value3 = myCharz().cx + 20;
                           deltaY = myCharz().cy - 10;
                           value4 = myCharz().cy + 10;
                        }

                        if (deltaX2 <= npcFocusValue.cx
                           && npcFocusValue.cx <= value3
                           && deltaY <= npcFocusValue.cy
                           && npcFocusValue.cy <= value4
                           && (this.npcFocus == null || value7 < intValues[1])) {
                           this.npcFocus = npcFocusValue;
                           intValues[1] = value7;
                        }
                     }
                  }
               } else {
                  if (deltaX2 <= this.npcFocus.cx
                     && this.npcFocus.cx <= value3
                     && deltaY <= this.npcFocus.cy
                     && this.npcFocus.cy <= value4) {
                     this.clearFocus(1);
                     return;
                  }

                  this.deFocusNPC();

                  for (int npcIndex2 = 0; npcIndex2 < GameScr.vNpc.size(); npcIndex2++) {
                     Npc npcFocusValue2;
                     if ((npcFocusValue2 = (Npc)GameScr.vNpc.elementAt(npcIndex2)).statusMe != 15) {
                        int value8 = Math.abs(myCharz().cx - npcFocusValue2.cx);
                        int value9 = Math.abs(myCharz().cy - npcFocusValue2.cy);
                        int value10 = value8 > value9 ? value8 : value9;
                        deltaX2 = myCharz().cx - 80;
                        value3 = myCharz().cx + 80;
                        deltaY = myCharz().cy - 30;
                        value4 = myCharz().cy + 30;
                        if (npcFocusValue2.template.npcTemplateId == 6) {
                           deltaX2 = myCharz().cx - 20;
                           value3 = myCharz().cx + 20;
                           deltaY = myCharz().cy - 10;
                           value4 = myCharz().cy + 10;
                        }

                        if (deltaX2 <= npcFocusValue2.cx
                           && npcFocusValue2.cx <= value3
                           && deltaY <= npcFocusValue2.cy
                           && npcFocusValue2.cy <= value4
                           && (this.npcFocus == null || value10 < intValues[1])) {
                           this.npcFocus = npcFocusValue2;
                           intValues[1] = value10;
                        }
                     }
                  }
               }

               if (this.itemFocus == null) {
                  for (int itemMapIndex = 0; itemMapIndex < GameScr.vItemMap.size(); itemMapIndex++) {
                     ItemMap itemFocusValue = (ItemMap)GameScr.vItemMap.elementAt(itemMapIndex);
                     int value11 = Math.abs(myCharz().cx - itemFocusValue.x);
                     int value12 = Math.abs(myCharz().cy - itemFocusValue.y);
                     int value13 = value11 > value12 ? value11 : value12;
                     if (value11 <= 48 && value12 <= 48 && (this.itemFocus == null || value13 < intValues[3])) {
                        if (GameScr.gI().auto != 0) {
                           GameScr.gI();
                           if (GameScr.isBagFull() && itemFocusValue.template.type != 9) {
                              continue;
                           }
                        }

                        this.itemFocus = itemFocusValue;
                        intValues[3] = value13;
                     }
                  }
               } else {
                  if (deltaX2 <= this.itemFocus.x
                     && this.itemFocus.x <= value3
                     && deltaY <= this.itemFocus.y
                     && this.itemFocus.y <= value4) {
                     this.clearFocus(3);
                     return;
                  }

                  this.itemFocus = null;

                  for (int itemMapIndex2 = 0; itemMapIndex2 < GameScr.vItemMap.size(); itemMapIndex2++) {
                     ItemMap itemFocusValue2 = (ItemMap)GameScr.vItemMap.elementAt(itemMapIndex2);
                     int value14 = Math.abs(myCharz().cx - itemFocusValue2.x);
                     int value15 = Math.abs(myCharz().cy - itemFocusValue2.y);
                     int value16 = value14 > value15 ? value14 : value15;
                     if (deltaX2 <= itemFocusValue2.x
                        && itemFocusValue2.x <= value3
                        && deltaY <= itemFocusValue2.y
                        && itemFocusValue2.y <= value4
                        && (this.itemFocus == null || value16 < intValues[3])) {
                        if (GameScr.gI().auto != 0) {
                           GameScr.gI();
                           if (GameScr.isBagFull() && itemFocusValue2.template.type != 9) {
                              continue;
                           }
                        }

                        this.itemFocus = itemFocusValue2;
                        intValues[3] = value16;
                     }
                  }
               }

               deltaX = myCharz().cx - myCharz().getdxSkill() - 10;
               value = myCharz().cx + myCharz().getdxSkill() + 10;
               cmy = myCharz().cy - myCharz().getdySkill() - byteValue - 20;
               if ((value2 = myCharz().cy + myCharz().getdySkill() + 20) > myCharz().cy + 30) {
                  value2 = myCharz().cy + 30;
               }

               if (this.mobFocus == null) {
                  for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
                     Mob mobFocusValue = (Mob)GameScr.vMob.elementAt(mobIndex);
                     int value17 = Math.abs(myCharz().cx - mobFocusValue.x);
                     int value18 = Math.abs(myCharz().cy - mobFocusValue.y);
                     int value19 = value17 > value18 ? value17 : value18;
                     if (deltaX <= mobFocusValue.x
                        && mobFocusValue.x <= value
                        && cmy <= mobFocusValue.y
                        && mobFocusValue.y <= value2
                        && (this.mobFocus == null || value19 < intValues[0])) {
                        this.mobFocus = mobFocusValue;
                        intValues[0] = value19;
                     }
                  }
               } else {
                  if (this.mobFocus.status != 1
                     && this.mobFocus.status != 0
                     && deltaX <= this.mobFocus.x
                     && this.mobFocus.x <= value
                     && cmy <= this.mobFocus.y
                     && this.mobFocus.y <= value2) {
                     this.clearFocus(0);
                     return;
                  }

                  this.mobFocus = null;

                  for (int mobIndex2 = 0; mobIndex2 < GameScr.vMob.size(); mobIndex2++) {
                     Mob mobFocusValue2 = (Mob)GameScr.vMob.elementAt(mobIndex2);
                     int value20 = Math.abs(myCharz().cx - mobFocusValue2.x);
                     int value21 = Math.abs(myCharz().cy - mobFocusValue2.y);
                     int value22 = value20 > value21 ? value20 : value21;
                     if (deltaX <= mobFocusValue2.x
                        && mobFocusValue2.x <= value
                        && cmy <= mobFocusValue2.y
                        && mobFocusValue2.y <= value2
                        && (this.mobFocus == null || value22 < intValues[0])) {
                        this.mobFocus = mobFocusValue2;
                        intValues[0] = value22;
                     }
                  }
               }

               if (this.charFocus == null) {
                  for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
                     Char charFocusValue;
                     if ((charFocusValue = (Char)GameScr.vCharInMap.elementAt(charInMapIndex)).statusMe != 15
                        && !charFocusValue.isInvisiblez
                        && this.wdx == 0
                        && this.wdy == 0) {
                        int value23 = Math.abs(myCharz().cx - charFocusValue.cx);
                        int value24 = Math.abs(myCharz().cy - charFocusValue.cy);
                        int value25 = value23 > value24 ? value23 : value24;
                        if (deltaX <= charFocusValue.cx
                           && charFocusValue.cx <= value
                           && cmy <= charFocusValue.cy
                           && charFocusValue.cy <= value2
                           && (this.charFocus == null || value25 < intValues[2])) {
                           this.charFocus = charFocusValue;
                           intValues[2] = value25;
                        }
                     }
                  }
               } else {
                  if (deltaX <= this.charFocus.cx
                     && this.charFocus.cx <= value
                     && cmy <= this.charFocus.cy
                     && this.charFocus.cy <= value2
                     && this.charFocus.statusMe != 15
                     && !this.charFocus.isInvisiblez) {
                     this.clearFocus(2);
                     return;
                  }

                  this.charFocus = null;

                  for (int charInMapIndex2 = 0; charInMapIndex2 < GameScr.vCharInMap.size(); charInMapIndex2++) {
                     Char charFocusValue2;
                     if ((charFocusValue2 = (Char)GameScr.vCharInMap.elementAt(charInMapIndex2)).statusMe != 15
                        && !charFocusValue2.isInvisiblez
                        && this.wdx == 0
                        && this.wdy == 0) {
                        int value26 = Math.abs(myCharz().cx - charFocusValue2.cx);
                        int value27 = Math.abs(myCharz().cy - charFocusValue2.cy);
                        int value28 = value26 > value27 ? value26 : value27;
                        if (deltaX <= charFocusValue2.cx
                           && charFocusValue2.cx <= value
                           && cmy <= charFocusValue2.cy
                           && charFocusValue2.cy <= value2
                           && (this.charFocus == null || value28 < intValues[2])) {
                           this.charFocus = charFocusValue2;
                           intValues[2] = value28;
                        }
                     }
                  }
               }

               int index = -1;

               for (int index2 = 0; index2 < intValues.length; index2++) {
                  if (index == -1) {
                     if (intValues[index2] != -1) {
                        index = index2;
                     }
                  } else if (intValues[index2] < intValues[index] && intValues[index2] != -1) {
                     index = index2;
                  }
               }

               this.clearFocus(index);
               if (this.me && this.isAttacPlayerStatus()) {
                  if (this.mobFocus != null && !this.mobFocus.isMobMe) {
                     this.mobFocus = null;
                  }

                  this.npcFocus = null;
                  this.itemFocus = null;
               }
            }
         }
      }
   }

   private void clearFocus(int index) {
      if (index == 0) {
         this.deFocusNPC();
         this.charFocus = null;
         this.itemFocus = null;
      } else if (index == 1) {
         this.mobFocus = null;
         this.charFocus = null;
         this.itemFocus = null;
      } else if (index == 2) {
         this.mobFocus = null;
         this.deFocusNPC();
         this.itemFocus = null;
      } else {
         if (index == 3) {
            this.mobFocus = null;
            this.deFocusNPC();
            this.charFocus = null;
         }
      }
   }

   public static boolean isCharInScreen(Char c) {
      int cmx = GameScr.cmx;
      int value = GameScr.cmx + GameCanvas.w;
      int value2 = GameScr.cmy + 10;
      int value3 = GameScr.cmy + GameScr.gH;
      return c.statusMe != 15
         && !c.isInvisiblez
         && cmx <= c.cx
         && c.cx <= value
         && value2 <= c.cy
         && c.cy <= value3;
   }

   public final boolean isAttacPlayerStatus() {
      return this.cTypePk == 4 || this.cTypePk == 3;
   }

   public final void setHoldChar(Char r) {
      if (this.cx < r.cx) {
         this.cdir = 1;
      } else {
         this.cdir = -1;
      }

      this.charHold = r;
      this.holder = true;
   }

   public final void setHoldMob(Mob r) {
      if (this.cx < r.x) {
         this.cdir = 1;
      } else {
         this.cdir = -1;
      }

      this.mobHold = r;
      this.holder = true;
   }

   public final void findNextFocusByKey() {
      if (myCharz().skillPaint == null
            && myCharz().arr == null
            && myCharz().dart == null
            && myCharz().skillInfoPaint() == null
         || this.focus.size() != 0) {
         this.focus.removeAllElements();
         int focuIndex = 0;
         int value = GameScr.cmx + 10;
         int value2 = GameScr.cmx + GameCanvas.w - 10;
         int value3 = GameScr.cmy + 10;
         int value4 = GameScr.cmy + GameScr.gH;

         for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
            Char entry;
            if ((entry = (Char)GameScr.vCharInMap.elementAt(charInMapIndex)).statusMe != 15
               && !entry.isInvisiblez
               && value <= entry.cx
               && entry.cx <= value2
               && value3 <= entry.cy
               && entry.cy <= value4
               && entry.charID != -114
               && (TileMap.mapID != 129 || TileMap.mapID == 129 && myCharz().cy > 264)) {
               this.focus.addElement(entry);
               if (this.charFocus != null && entry.equals(this.charFocus)) {
                  focuIndex = this.focus.size();
               }
            }
         }

         if (this.me && this.isAttacPlayerStatus()) {
            Res.outz("co the tan cong nguoi");

            for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
               Mob entry2 = (Mob)GameScr.vMob.elementAt(mobIndex);
               GameScr.gI();
               if (!GameScr.isMeCanAttackMob(entry2)) {
                  Res.outz("khong the tan cong quai");
                  this.mobFocus = null;
               } else {
                  Res.outz("co the tan ong quai");
                  this.focus.addElement(entry2);
                  if (this.mobFocus != null) {
                     focuIndex = this.focus.size();
                  }
               }
            }

            this.npcFocus = null;
            this.itemFocus = null;
            if (this.focus.size() > 0) {
               if (focuIndex >= this.focus.size()) {
                  focuIndex = 0;
               }

               this.focusManualTo(this.focus.elementAt(focuIndex));
            } else {
               this.mobFocus = null;
               this.deFocusNPC();
               this.charFocus = null;
               this.itemFocus = null;
               isManualFocus = false;
            }
         } else {
            for (int itemMapIndex = 0; itemMapIndex < GameScr.vItemMap.size(); itemMapIndex++) {
               ItemMap entry3 = (ItemMap)GameScr.vItemMap.elementAt(itemMapIndex);
               if (value <= entry3.x && entry3.x <= value2 && value3 <= entry3.y && entry3.y <= value4) {
                  this.focus.addElement(entry3);
                  if (this.itemFocus != null && entry3.equals(this.itemFocus)) {
                     focuIndex = this.focus.size();
                  }
               }
            }

            for (int mobIndex2 = 0; mobIndex2 < GameScr.vMob.size(); mobIndex2++) {
               Mob entry4;
               if ((entry4 = (Mob)GameScr.vMob.elementAt(mobIndex2)).status != 1
                  && entry4.status != 0
                  && value <= entry4.x
                  && entry4.x <= value2
                  && value3 <= entry4.y
                  && entry4.y <= value4) {
                  this.focus.addElement(entry4);
                  if (this.mobFocus != null && entry4.equals(this.mobFocus)) {
                     focuIndex = this.focus.size();
                  }
               }
            }

            for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
               Npc entry5;
               if ((entry5 = (Npc)GameScr.vNpc.elementAt(npcIndex)).statusMe != 15
                  && value <= entry5.cx
                  && entry5.cx <= value2
                  && value3 <= entry5.cy
                  && entry5.cy <= value4) {
                  this.focus.addElement(entry5);
                  if (this.npcFocus != null && entry5.equals(this.npcFocus)) {
                     focuIndex = this.focus.size();
                  }
               }
            }

            if (this.focus.size() > 0) {
               if (focuIndex >= this.focus.size()) {
                  focuIndex = 0;
               }

               this.focusManualTo(this.focus.elementAt(focuIndex));
            } else {
               this.mobFocus = null;
               this.deFocusNPC();
               this.charFocus = null;
               this.itemFocus = null;
               isManualFocus = false;
            }
         }
      }
   }

   private void deFocusNPC() {
      if (this.me && this.npcFocus != null) {
         if (!GameCanvas.menu.showMenu) {
            chatPopup = null;
         }

         this.npcFocus = null;
      }
   }

   private void updateCharInBridge() {
      if (!GameCanvas.lowGraphic) {
         if (TileMap.tileTypeAt(this.cx, this.cy + 1, 1024)) {
            TileMap.setTileTypeAtPixel(this.cx, this.cy + 1, 512);
            TileMap.setTileTypeAtPixel(this.cx, this.cy - 2, 512);
         }

         if (TileMap.tileTypeAt(this.cx - TileMap.size, this.cy + 1, 512)) {
            TileMap.killTileTypeAt(this.cx - TileMap.size, this.cy + 1, 512);
            TileMap.killTileTypeAt(this.cx - TileMap.size, this.cy - 2, 512);
         }

         if (TileMap.tileTypeAt(this.cx + TileMap.size, this.cy + 1, 512)) {
            TileMap.killTileTypeAt(this.cx + TileMap.size, this.cy + 1, 512);
            TileMap.killTileTypeAt(this.cx + TileMap.size, this.cy - 2, 512);
         }
      }
   }

   public final void doInjure(long HPShow, long MPShow, boolean isCrit, boolean isMob) {
      this.isCrit = isCrit;
      this.isMob = isMob;
      this.cHP -= HPShow;
      this.cMP -= MPShow;
      GameScr.gI().isInjureHp = true;
      GameScr.gI().twHp = 0L;
      GameScr.gI().isInjureMp = true;
      GameScr.gI().twMp = 0L;
      if (this.cHP < 0L) {
         this.cHP = 0L;
      }

      if (this.cMP < 0L) {
         this.cMP = 0L;
      }

      if (isMob || !isMob && this.cTypePk != 4 && 0L != -100L) {
         String flyString;
         int x;
         int y;
         byte dx;
         byte dy;
         int color;
         if (HPShow <= 0L) {
            if (this.me) {
               flyString = mResources.miss;
               x = this.cx;
               y = this.cy - this.ch;
               dx = 0;
               dy = -2;
               color = 7;
            } else {
               flyString = mResources.miss;
               x = this.cx;
               y = this.cy - this.ch;
               dx = 0;
               dy = -2;
               color = 4;
            }
         } else {
            flyString = "-" + HPShow;
            x = this.cx;
            y = this.cy - this.ch;
            dx = 0;
            dy = -2;
            color = !isCrit ? 0 : 3;
         }

         GameScr.startFlyText(flyString, x, y, dx, dy, color);
      }

      if (HPShow > 0L) {
         this.isInjure = 6;
      }

      ServerEffect.addServerEffect(80, ((nro.Char) (this)), 1);
      if (this.isDie) {
         this.isDie = false;
         isLockKey = false;
         this.startDie((short)this.xSd, (short)this.ySd);
      }
   }

   public final void doInjure() {
      GameScr.gI().isInjureHp = true;
      GameScr.gI().twHp = 0L;
      GameScr.gI().isInjureMp = true;
      GameScr.gI().twMp = 0L;
      this.isInjure = 6;
      ServerEffect.addServerEffect(8, ((nro.Char) (this)), 1);
      this.isInjureHp = true;
      this.twHp = 0;
   }

   public final void startDie(short toX, short toY) {
      this.isMonkey = 0;
      this.isWaitMonkey = false;
      if (!this.me || !this.isDie) {
         if (this.me) {
            this.isLockMove = true;

            for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
               ((Char)GameScr.vCharInMap.elementAt(charInMapIndex)).killCharId = -9999;
            }

            if (GameCanvas.panel != null && GameCanvas.panel.cp != null) {
               GameCanvas.panel.cp = null;
            }

            if (GameCanvas.panel2 != null && GameCanvas.panel2.cp != null) {
               GameCanvas.panel2.cp = null;
            }
         }

         this.statusMe = 5;
         this.cp2 = toX;
         this.cp3 = toY;
         this.cp1 = 0;
         this.cHP = 0L;
         this.testCharId = -9999;
         this.killCharId = -9999;
         if (this.me && this.myskill != null && this.myskill.template.id != 14) {
            this.stopUseChargeSkill();
         }

         this.cTypePk = 0;
      }
   }

   public final void waitToDie(short toX, short toY) {
      this.wdx = toX;
      this.wdy = toY;
   }

   public final void liveFromDead() {
      this.cHP = this.cHPFull;
      this.cMP = this.cMPFull;
      this.statusMe = 1;
      this.cp1 = this.cp2 = this.cp3 = 0;
      ServerEffect.addServerEffect(109, ((nro.Char) (this)), 2);
      GameScr.gI().center = null;
      GameScr.isHaveSelectSkill = true;
   }

   public final boolean doUsePotion() {
      if (this.arrItemBag == null) {
         return false;
      } else {
         for (int itemBagIndex = 0; itemBagIndex < this.arrItemBag.length; itemBagIndex++) {
            if (this.arrItemBag[itemBagIndex] != null && this.arrItemBag[itemBagIndex].template.type == 6) {
               Service.gI().useItem((byte)0, (byte)1, (byte)-1, this.arrItemBag[itemBagIndex].template.id);
               return true;
            }
         }

         return false;
      }
   }

   private static boolean isLang() {
      return TileMap.mapID == 1
         || TileMap.mapID == 27
         || TileMap.mapID == 72
         || TileMap.mapID == 10
         || TileMap.mapID == 17
         || TileMap.mapID == 22
         || TileMap.mapID == 32
         || TileMap.mapID == 38
         || TileMap.mapID == 43
         || TileMap.mapID == 48;
   }

   public final boolean isMeCanAttackOtherPlayer(Char cAtt) {
      return cAtt != null
            && myCharz().myskill != null
            && myCharz().myskill.template.type != 2
            && (myCharz().myskill.template.type != 4 || cAtt.statusMe == 14 || cAtt.statusMe == 5)
         ? (
               cAtt.cTypePk == 3 && myCharz().cTypePk == 3
                  || myCharz().cTypePk == 5
                  || cAtt.cTypePk == 5
                  || myCharz().cTypePk == 1 && cAtt.cTypePk == 1
                  || myCharz().cTypePk == 4 && cAtt.cTypePk == 4
                  || myCharz().testCharId >= 0 && myCharz().testCharId == cAtt.charID
                  || myCharz().killCharId >= 0 && myCharz().killCharId == cAtt.charID && !isLang()
                  || cAtt.killCharId >= 0 && cAtt.killCharId == myCharz().charID && !isLang()
                  || myCharz().cFlag == 8 && cAtt.cFlag != 0
                  || myCharz().cFlag != 0 && cAtt.cFlag == 8
                  || myCharz().cFlag != cAtt.cFlag
                     && myCharz().cFlag != 0
                     && cAtt.cFlag != 0
            )
            && cAtt.statusMe != 14
            && cAtt.statusMe != 5
         : false;
   }

   public static void clearTask() {
      myCharz().taskMaint = null;

      for (int itemBagIndex = 0; itemBagIndex < myCharz().arrItemBag.length; itemBagIndex++) {
         if (myCharz().arrItemBag[itemBagIndex] != null && myCharz().arrItemBag[itemBagIndex].template.type == 8) {
            myCharz().arrItemBag[itemBagIndex] = null;
         }
      }

      Npc.clearEffTask();
   }

   public final int getX() {
      return this.cx;
   }

   public final int getY() {
      return this.cy;
   }

   public final int getH() {
      return 32;
   }

   public final int getW() {
      return 24;
   }

   public final void focusManualTo(Object objectz) {
      if (objectz instanceof Mob) {
         this.mobFocus = (Mob)objectz;
         this.deFocusNPC();
         this.charFocus = null;
         this.itemFocus = null;
      } else if (objectz instanceof Npc) {
         myCharz().mobFocus = null;
         myCharz().deFocusNPC();
         myCharz().npcFocus = (Npc)objectz;
         myCharz().charFocus = null;
         myCharz().itemFocus = null;
      } else if (objectz instanceof Char) {
         myCharz().mobFocus = null;
         myCharz().deFocusNPC();
         myCharz().charFocus = (Char)objectz;
         myCharz().itemFocus = null;
      } else if (objectz instanceof ItemMap) {
         myCharz().mobFocus = null;
         myCharz().deFocusNPC();
         myCharz().charFocus = null;
         myCharz().itemFocus = (ItemMap)objectz;
      }

      isManualFocus = true;
   }

   public final void stopMoving() {
   }

   public static void cancelAttack() {
   }

   public final boolean isInvisible() {
      return false;
   }

   public final boolean focusToAttack() {
      return this.mobFocus != null || this.charFocus != null && this.isMeCanAttackOtherPlayer(this.charFocus);
   }

   private void addDustEff(int type) {
      if (!GameCanvas.lowGraphic) {
         if (type == 1) {
            EffecMn.addEff(new Effect(19, this.cx - 5, this.cy + 20, 2, 1, -1));
            return;
         }

         if (type == 2) {
            if (this.me && this.isMonkey == 1) {
               return;
            }

            if (this.isNhapThe && GameCanvas.gameTick % 5 == 0) {
               EffecMn.addEff(new Effect(22, this.cx - 5, this.cy + 35, 2, 1, -1));
               return;
            }
         } else if (type == 3 && this.ySd - this.cy <= 5) {
            EffecMn.addEff(new Effect(19, this.cx - 5, this.ySd + 20, 2, 1, -1));
         }
      }
   }

   public static boolean isGetFlagImage(byte getFlag) {
      boolean result = true;

      for (int flagIndex = 0; flagIndex < GameScr.vFlag.size(); flagIndex++) {
         PKFlag flag;
         if ((flag = (PKFlag)GameScr.vFlag.elementAt(flagIndex)) != null) {
            if (flag.cflag == getFlag) {
               return true;
            }

            result = false;
         }
      }

      return result;
   }

   public final void removeHoleEff() {
      if (this.holder) {
         this.holder = false;
         this.charHold = null;
         this.mobHold = null;
      } else {
         this.holdEffID = 0;
         this.charHold = null;
         this.mobHold = null;
      }
   }

   public final void removeProtectEff() {
      this.protectEff = false;
      this.eProtect = null;
   }

   public final void removeBlindEff() {
      this.blindEff = false;
   }

   public final void removeEffect() {
      if (this.holdEffID != 0) {
         this.holdEffID = 0;
      }

      if (this.holder) {
         this.holder = false;
      }

      if (this.protectEff) {
         this.protectEff = false;
      }

      this.eProtect = null;
      this.charHold = null;
      this.mobHold = null;
      this.blindEff = false;
      this.sleepEff = false;
   }

   public final void setPos(short xPos, short yPos, byte typePos) {
      this.isSetPos = true;
      this.xPos = xPos;
      this.yPos = yPos;
      this.typePos = typePos;
      this.tpos = 0;
      if (this.me) {
         if (GameCanvas.panel != null) {
            GameCanvas.panel.hide();
         }

         if (GameCanvas.panel2 != null) {
            GameCanvas.panel2.hide();
         }
      }
   }

   public final void removeHuytSao() {
      this.huytSao = false;
   }

   public final void fusionComplete() {
      this.isFusion = false;
      isLockKey = false;
      this.tFusion = 0;
   }

   public final void setFusion(byte fusion) {
      this.tFusion = 0;
      if (fusion == 4 || fusion == 5) {
         if (this.me) {
            Service.gI().funsion(fusion);
         }

         EffecMn.addEff(new Effect(34, this.cx, this.cy + 12, 2, 1, -1));
      }

      if (fusion == 6) {
         EffecMn.addEff(new Effect(38, this.cx, this.cy + 12, 2, 1, -1));
      }

      if (this.me) {
         GameCanvas.panel.hideNow();
         isLockKey = true;
      }

      this.isFusion = true;
      if (fusion == 1) {
         this.isNhapThe = false;
      } else {
         this.isNhapThe = true;
      }
   }

   public final void removeSleepEff() {
      this.sleepEff = false;
   }

   public final void setPartOld() {
      this.headTemp = this.head;
      this.bodyTemp = this.body;
      this.legTemp = this.leg;
      this.bagTemp = this.bag;
   }

   public final void setPartTemp(int head, int body, int leg, int bag) {
      if (head != -1) {
         this.head = head;
      }

      if (body != -1) {
         this.body = body;
      }

      if (leg != -1) {
         this.leg = leg;
      }

      if (bag != -1) {
         this.bag = bag;
      }
   }

   public final void resetPartTemp() {
      if (this.headTemp != -1) {
         this.head = this.headTemp;
         this.headTemp = -1;
      }

      if (this.bodyTemp != -1) {
         this.body = this.bodyTemp;
         this.bodyTemp = -1;
      }

      if (this.legTemp != -1) {
         this.leg = this.legTemp;
         this.legTemp = -1;
      }

      if (this.bagTemp != -1) {
         this.bag = this.bagTemp;
         this.bagTemp = -1;
      }
   }

   private Effect getEffById(int id) {
      for (int effCharIndex = 0; effCharIndex < this.vEffChar.size(); effCharIndex++) {
         Effect effChar;
         if ((effChar = (Effect)this.vEffChar.elementAt(effCharIndex)).effId == id) {
            return effChar;
         }
      }

      return null;
   }

   public final void addEffChar(Effect e) {
      this.removeEffChar(0, e.effId);
      this.vEffChar.addElement(e);
   }

   public final void removeEffChar(int type, int id) {
      if (type == -1) {
         this.vEffChar.removeAllElements();
      } else {
         if (this.getEffById(id) != null) {
            this.vEffChar.removeElement(this.getEffById(id));
         }
      }
   }

   private void paintEffBehind(mGraphics graphics) {
      for (int effCharIndex = 0; effCharIndex < this.vEffChar.size(); effCharIndex++) {
         Effect effChar;
         if ((effChar = (Effect)this.vEffChar.elementAt(effCharIndex)).layer == 0) {
            boolean condition = true;
            if (effChar.isStand == 0) {
               if (this.statusMe != 1 && this.statusMe != 6) {
                  condition = false;
               } else {
                  condition = true;
               }
            }

            if (condition) {
               effChar.paint(graphics);
            }
         }
      }
   }

   private void paintEffFront(mGraphics graphics) {
      for (int effCharIndex = 0; effCharIndex < this.vEffChar.size(); effCharIndex++) {
         Effect effChar;
         if ((effChar = (Effect)this.vEffChar.elementAt(effCharIndex)).layer == 1) {
            boolean condition = true;
            if (effChar.isStand == 0) {
               if (this.statusMe != 1 && this.statusMe != 6) {
                  condition = false;
               } else {
                  condition = true;
               }
            }

            if (condition) {
               effChar.paint(graphics);
            }
         }
      }
   }

   private void updEffChar() {
      for (int effCharIndex = 0; effCharIndex < this.vEffChar.size(); effCharIndex++) {
         ((Effect)this.vEffChar.elementAt(effCharIndex)).update();
      }
   }

   public final int checkLuong() {
      return this.luong + this.luongKhoa;
   }

   private void updateEye() {
      if (this.head == 934) {
         if (GameCanvas.timeNow - this.timeAddChopmat > 0L) {
            this.fChopmat++;
            if (this.fChopmat > this.frEye.length - 1) {
               this.fChopmat = 0;
               this.timeAddChopmat = GameCanvas.timeNow + (long)Res.random(2000, 3500);
               this.frEye = this.frChopCham;
               if (Res.random(2) == 0) {
                  this.frEye = this.frChopNhanh;
                  return;
               }
            }
         } else {
            this.fChopmat = 0;
         }
      }
   }

   private void paintRedEye(mGraphics g, int xx, int yy, int trans, int anchor) {
      if (this.head == 934 && (this.statusMe == 1 || this.statusMe == 6)) {
         if (fraRedEye == null || fraRedEye.imgFrame == null) {
            Image img = mSystem.loadImage("/redeye.png");
            fraRedEye = new FrameImage(img, 14, 10);
            return;
         }

         if (this.frEye[this.fChopmat] != -1) {
            byte byteValue = 8;
            if (trans == 2) {
               byteValue = -8;
            }

            fraRedEye.drawFrame(this.frEye[this.fChopmat], xx + byteValue, yy + 15, trans, anchor, g);
         }
      }
   }

   private static boolean isHead_2Fr(int idHead) {
      for (int arr_Head_2FrIndex = 0; arr_Head_2FrIndex < Arr_Head_2Fr.length; arr_Head_2FrIndex++) {
         if (Arr_Head_2Fr[arr_Head_2FrIndex][0] == idHead) {
            return true;
         }
      }

      return false;
   }

   private void updateFHead() {
      if (isHead_2Fr(this.head)) {
         this.fHead++;
         if (this.fHead <= 10000) {
            return;
         }
      }

      this.fHead = 0;
   }

   private int getFHead(int idHead) {
      for (int arr_Head_2FrIndex = 0; arr_Head_2FrIndex < Arr_Head_2Fr.length; arr_Head_2FrIndex++) {
         if (Arr_Head_2Fr[arr_Head_2FrIndex][0] == idHead) {
            return Arr_Head_2Fr[arr_Head_2FrIndex][this.fHead / 4 % Arr_Head_2Fr[arr_Head_2FrIndex].length];
         }
      }

      return idHead;
   }

   private void paintHat_behind(mGraphics graphics, int cfValue, int yh) {
      try {
         if (this.idHat != -1) {
            if (usesSideHatFrame(cfValue)) {
               if (this.fraHat_behind_2 != null) {
                  this.fraHat_behind_2
                     .drawFrame(
                        GameCanvas.gameTick / 4 % this.fraHat_behind_2.nFrame,
                        this.cx + hatInfo[cfValue][0] * (this.cdir == 1 ? 1 : -1),
                        yh + hatInfo[cfValue][1],
                        this.cdir == 1 ? 0 : 2,
                        33,
                        graphics
                     );
               } else {
                  this.fraHat_behind_2 = mSystem.getFraImage(this.strHat_behind + this.strNgang + this.idHat);
               }
            } else if (this.fraHat_behind != null) {
               this.fraHat_behind
                  .drawFrame(
                     GameCanvas.gameTick / 4 % this.fraHat_behind.nFrame,
                     this.cx + hatInfo[cfValue][0] * (this.cdir == 1 ? 1 : -1),
                     yh + hatInfo[cfValue][1],
                     this.cdir == 1 ? 0 : 2,
                     33,
                     graphics
                  );
            } else {
               this.fraHat_behind = mSystem.getFraImage(this.strHat_behind + this.idHat);
            }
         }
      } catch (Exception ignoredException) {
      }
   }

   private static boolean usesSideHatFrame(int value) {
      return value == 2
         || value == 3
         || value == 4
         || value == 5
         || value == 6
         || value == 9
         || value == 10
         || value == 13
         || value == 14
         || value == 15
         || value == 16
         || value == 26
         || value == 27
         || value == 28
         || value == 29;
   }

   public final void sendNewAttack(short idTemplateSkill) {
      short x = -1;
      short y = -1;
      if (this.mobFocus != null) {
         x = (short)this.mobFocus.x;
         y = (short)this.mobFocus.y;
      }

      if (this.charFocus != null && !this.charFocus.isPet && !this.charFocus.isMiniPet) {
         x = (short)this.charFocus.cx;
         y = (short)this.charFocus.cy;
      }

      Service.gI().new_skill_not_focus((byte)idTemplateSkill, (byte)this.cdir, x, y);
   }

   public final void SetSkillPaint_NEW(short idskillPaint, boolean isFly, byte typeFrame, byte typePaint, byte dir, short timeGong, byte typeItem) {
      this.isPaintNewSkill = true;
      this.timeReset_newSkill = GameCanvas.timeNow + 10000L;
      this.idskillPaint = idskillPaint;
      this.isFly = isFly;
      this.typeFrame = typeFrame;
      this.cdir = dir;
      this.count_NEW = 0;
      this.stt = 0;
      long lastTimeUseThisSkill = mSystem.currentTimeMillis();
      if (this.me) {
         this.myskill.lastTimeUseThisSkill = lastTimeUseThisSkill;
         if (this.myskill.template.manaUseType == 2) {
            this.cMP = 1L;
         } else if (this.myskill.template.manaUseType != 1) {
            this.cMP = this.cMP - (long)this.myskill.manaUse;
         } else {
            this.cMP = this.cMP - (long)this.myskill.manaUse * this.cMPFull / 100L;
         }

         myCharz().cStamina--;
         GameScr.gI().isInjureMp = true;
         GameScr.gI().twMp = 0L;
         if (this.cMP < 0L) {
            this.cMP = 0L;
         }
      }

      if (idskillPaint == 24) {
         GameScr.addEffectEnd_Target(18, 0, typePaint, this.clone(), null, 3, timeGong, (short)0);
         GameScr.addEffectEnd_Target(21, 0, typePaint, this.clone(), null, 1, timeGong, (short)0);
      } else if (idskillPaint == 25) {
         GameScr.addEffectEnd_Target(19, 0, typePaint, this.clone(), null, 3, timeGong, (short)0);
         GameScr.addEffectEnd_Target(22, 0, typePaint, this.clone(), null, 1, timeGong, (short)0);
      } else if (idskillPaint == 26) {
         GameScr.addEffectEnd_Target(20, 0, typePaint, this.clone(), null, 3, timeGong, (short)0);
         GameScr.addEffectEnd_Target(23, 0, typePaint, this.clone(), null, 1, timeGong, (short)0);
      }

      if (this.typeFrame == 1) {
         if (!this.isFly) {
            this.fr_start = new byte[]{20, 20, 20, 20, 20, 20, 19};
            this.fr_atk = new byte[]{20};
            this.fr_end = new byte[1];
         } else {
            this.fr_start = new byte[]{31, 31, 31, 31, 31, 31, 30};
            this.fr_atk = new byte[]{31};
            this.fr_end = new byte[]{12};
         }
      }

      if (this.typeFrame == 2) {
         if (!this.isFly) {
            this.fr_start = new byte[]{20};
            this.fr_atk = new byte[]{13, 13, 13, 14, 14, 14};
            this.fr_end = new byte[1];
         } else {
            this.fr_start = new byte[]{31};
            this.fr_atk = new byte[]{26, 26, 26, 27, 27, 27};
            this.fr_end = new byte[]{12};
         }
      }

      if (this.typeFrame == 4) {
         if (!this.isFly) {
            this.fr_start = new byte[]{17, 17, 17, 18, 18, 18};
            this.fr_atk = new byte[]{18};
            this.fr_end = new byte[1];
         } else {
            this.fr_start = new byte[]{7, 7, 7, 12, 12, 12, 12};
            this.fr_atk = new byte[]{12};
            this.fr_end = new byte[]{12};
         }
      }

      if (this.typeFrame == 3) {
         if (!this.isFly) {
            this.fr_start = new byte[]{24, 24, 24, 17, 17, 17, 18, 18, 18};
            this.fr_atk = new byte[]{20};
            this.fr_end = new byte[1];
            return;
         }

         this.fr_start = new byte[]{23, 23, 23, 7, 7, 7, 12, 12, 12, 12};
         this.fr_atk = new byte[]{31};
         this.fr_end = new byte[]{12};
      }
   }

   public final void SetSkillPaint_STT(int stt, short idskillPaint, Point targetDame, short timeDame, short rangeDame, byte typePaint, Point[] listObj, byte typeItem) {
      this.stt = stt;
      this.idskillPaint = idskillPaint;
      this.count_NEW = 0;
      this.targetDame = targetDame;
      this.timeDame = mSystem.currentTimeMillis() + (long)timeDame;
      if (this.stt == 1) {
         if (this.idskillPaint == 24) {
            GameScr.addEffectEnd_Target(18, 1, typePaint, this, null, 3, timeDame, (short)0);
            GameScr.addEffectEnd_Target(24, 0, typePaint, this, this.targetDame, 1, timeDame, rangeDame);
         }

         if (this.idskillPaint == 25) {
            GameScr.addEffectEnd_Target(19, 0, typePaint, this, null, 3, timeDame, (short)0);
            GameScr.addEffectEnd_Target(25, 0, typePaint, this, this.targetDame, 1, timeDame, rangeDame);
         }

         if (this.idskillPaint == 26) {
            GameScr.addEffectEnd_Target(20, 0, typePaint, this, null, 3, timeDame, (short)0);
            GameScr.addEffectEnd(26, typeItem, typePaint, targetDame.x, targetDame.y, 1, 0, timeDame, listObj);
         }
      }
   }

   private void UpdSkillPaint_NEW() {
      if (this.stt == 0) {
         if (this.isFly && this.count_NEW < 20) {
            this.cvy = -3;
            this.cy = this.cy + this.cvy;
         }

         if (this.fr_start.length == 1) {
            this.cf = this.fr_start[0];
         } else if (this.count_NEW > this.fr_start.length - 1) {
            this.cf = this.fr_start[this.fr_start.length - 1];
         } else {
            this.cf = this.fr_start[this.count_NEW];
         }
      } else if (this.stt == 1) {
         this.cf = this.fr_atk[this.count_NEW % this.fr_atk.length];
         if (mSystem.currentTimeMillis() - this.timeDame > 0L) {
            this.SetSkillPaint_STT(2, (short)0, null, (short)0, (short)0, (byte)0, null, (byte)0);
         }

         if (this.count_NEW % 5 == 0) {
            GameScr.shock_scr = 5;
         }

         if (this.typeFrame == 1
            && this.count_NEW < 10
            && !TileMap.tileTypeAt(
               this.cx - (this.chw + 1) * this.cdir, this.cy, this.cdir == 1 ? 8 : 4
            )) {
            this.cx = this.cx - this.cdir;
         }
      } else if (this.stt == 2) {
         if (this.fr_end.length == 1) {
            this.cf = this.fr_end[0];
         } else if (this.count_NEW > this.fr_end.length - 1) {
            this.cf = this.fr_end[this.fr_end.length - 1];
         } else {
            this.cf = this.fr_end[this.count_NEW];
         }

         if (this.isFly) {
            this.cvx = this.cvy = 0;
            this.statusMe = 4;
         }

         this.isPaintNewSkill = false;
      }

      this.count_NEW++;
   }

   public final Char clone() {
      Char character;
      (character = new Char()).charID = this.charID;
      character.cx = this.cx;
      character.cy = this.cy;
      character.cdir = this.cdir;
      if (this.arrItemBody != null) {
         character.arrItemBody = new Item[this.arrItemBody.length];

         for (int itemBodyIndex = 0; itemBodyIndex < this.arrItemBody.length; itemBodyIndex++) {
            if (this.arrItemBody[itemBodyIndex] == null) {
               character.arrItemBody[itemBodyIndex] = null;
            } else {
               Item[] itemBody = character.arrItemBody;
               Item itemBody2 = this.arrItemBody[itemBodyIndex];
               Item item;
               (item = new Item()).template = itemBody2.template;
               if (itemBody2.options != null) {
                  item.options = new MyVector("item.options");

                  for (int optionIndex = 0; optionIndex < itemBody2.options.size(); optionIndex++) {
                     ItemOption entry;
                     (entry = new ItemOption()).optionTemplate = ((ItemOption)itemBody2.options.elementAt(optionIndex)).optionTemplate;
                     entry.param = ((ItemOption)itemBody2.options.elementAt(optionIndex)).param;
                     item.options.addElement(entry);
                  }
               }

               item.itemId = itemBody2.itemId;
               item.playerId = itemBody2.playerId;
               item.indexUI = itemBody2.indexUI;
               item.quantity = itemBody2.quantity;
               item.isLock = itemBody2.isLock;
               item.sys = itemBody2.sys;
               item.upgrade = itemBody2.upgrade;
               item.buyCoin = itemBody2.buyCoin;
               item.buyCoinLock = itemBody2.buyCoinLock;
               item.buyGold = itemBody2.buyGold;
               item.buyGoldLock = itemBody2.buyGoldLock;
               item.saleCoinLock = itemBody2.saleCoinLock;
               item.typeUI = itemBody2.typeUI;
               item.isExpires = itemBody2.isExpires;
               itemBody[itemBodyIndex] = item;
            }
         }
      }

      return character;
   }

   public final boolean containsCaiTrang(int v) {
      if (this.arrItemBody != null) {
         for (int itemBodyIndex = 0; itemBodyIndex < this.arrItemBody.length; itemBodyIndex++) {
            if (this.arrItemBody[itemBodyIndex] != null
               && this.arrItemBody[itemBodyIndex].template != null
               && this.arrItemBody[itemBodyIndex].template.id == 1265) {
               return true;
            }
         }
      }

      Res.err("tim kiem id cai trang " + 1265 + " ko tim thay");
      return false;
   }
}
