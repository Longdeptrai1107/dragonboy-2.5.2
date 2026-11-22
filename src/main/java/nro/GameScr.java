package nro;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class GameScr extends mScreen implements IChatable {
   private boolean isWaitingDoubleClick;
   private long timeStartDblClick;
   private long timeEndDblClick;
   public static boolean isPaintOther = false;
   public static boolean isLoadAllData = false;
   public static GameScr instance;
   public static int gW;
   public static int gH;
   public static int gW2;
   private static int gssw;
   private static int gssh;
   public static int gH23;
   private static int gW23;
   private static int gH2;
   private static int csPadMaxH;
   public static int cmdBarH;
   public static int gW6;
   public static int cmx;
   public static int cmy;
   private static int cmdx;
   private static int cmdy;
   private static int cmvx;
   private static int cmvy;
   public static int cmtoX;
   public static int cmtoY;
   public static int cmxLim;
   private static int cmyLim;
   public static int gssx;
   public static int gssy;
   public static int gssxe;
   public static int gssye;
   public Command cmdFocus;
   public static int maxScreenDimensionWithMargin;
   public static int hpPotion;
   public static SkillPaint[] sks;
   public static Arrowpaint[] arrs;
   public static DartInfo[] darts;
   public static Part[] parts;
   public static EffectCharPaint[] efs;
   public static int lockTick;
   public static MyVector vClan = new MyVector("vClan");
   public static MyVector vFriend = new MyVector("vFriend");
   public static MyVector vEnemies = new MyVector("vEnemies");
   public static MyVector vCharInMap = new MyVector("vCharInMap");
   public static MyVector vItemMap = new MyVector("vItemMap");
   private static MyVector vMobAttack = new MyVector("vMobAttack");
   public static MyVector vMob = new MyVector("vMob");
   public static MyVector vNpc = new MyVector("vNpc");
   public static MyVector vFlag = new MyVector("vFlag");
   public static NClass[] nClasss;
   private static int indexSelect = 0;
   public static int indexRow = -1;
   private static int indexMenu = 0;
   public ItemOptionTemplate[] iOptionTemplates;
   public SkillOptionTemplate[] sOptionTemplates;
   private static Scroll scrInfo = new Scroll();
   private static Scroll scrMain = new Scroll();
   public static boolean isViewClanInvite;
   public static int tickMove;
   public static boolean field_P_fb95a7dc = false;
   private static boolean field_cY_7e7a3cc7 = false;
   private static boolean field_cZ_883dc335 = false;
   private static boolean field_da_7758b532 = false;
   private static boolean field_db_021a9986 = false;
   private static boolean field_dc_af1b5cf8 = false;
   private static boolean field_dd_9a6d0a41 = false;
   public static boolean isHaveSelectSkill = false;
   public static boolean field_R_b8265d0f = false;
   private static boolean field_de_edc335d1 = false;
   private static boolean field_df_07c20829 = false;
   private static boolean field_dg_1bf4465a = false;
   private static boolean field_dh_6f257417 = false;
   private static boolean field_di_19d8edeb = false;
   private static boolean field_dj_31e0cfc8 = false;
   private static boolean field_dk_f328af30 = false;
   private static boolean field_dl_8654caa0 = false;
   private static boolean field_dm_1c94f4a6 = false;
   private static boolean field_dn_9a937c70 = false;
   private static boolean field_do_ee739656 = false;
   private static boolean field_dp_4b117540 = false;
   private static boolean field_dq_e9e07188 = false;
   private static boolean field_dr_95729d0b = false;
   private static boolean field_ds_74f14ced = false;
   private static boolean field_dt_3a0cee59 = false;
   private static boolean field_du_4d3a414a = false;
   private static boolean field_dv_09bbeaf3 = false;
   private static boolean field_dw_18f4ebe0 = false;
   private static boolean field_dx_b113a494 = false;
   private static boolean field_dy_e66ed70a = false;
   private static boolean field_dz_f85c98b0 = false;
   private static boolean field_dA_b290056c = false;
   private static boolean field_dB_6277a657 = false;
   private static boolean field_dC_9ddaca07 = false;
   private static boolean field_dD_2fd7f050 = false;
   private static boolean field_dE_1b70b56c = false;
   private static boolean field_dF_d1a2242b = false;
   private static boolean field_dG_1e25e0f4 = false;
   private static boolean field_dH_7c97b273 = false;
   public static Char currentCharViewInfo;
   public static long[] exps;
   private int tMenuDelay = 0;
   public int[] zones;
   public int[] pts;
   public int[] numPlayer;
   public int[] maxPlayer;
   public int[] rank1;
   public int[] rank2;
   public String[] rankName1;
   public String[] rankName2;
   public int typeTrade = 0;
   public int typeTradeOrder = 0;
   private int indexItemUse = -1;
   private boolean isLockKey;
   public static int[] tasks;
   public static int[] mapTasks;
   public static Image imgRoomStat = mSystem.loadImage("/mainImage/myTexture2dstat.png");
   public static Image frBarPow0 = mSystem.loadImage("/mainImage/myTexture2dlineColor20.png");
   public static Image frBarPow1 = mSystem.loadImage("/mainImage/myTexture2dlineColor21.png");
   public static Image frBarPow2 = mSystem.loadImage("/mainImage/myTexture2dlineColor22.png");
   public static Image frBarPow20 = mSystem.loadImage("/mainImage/myTexture2dlineColor00.png");
   public static Image frBarPow21 = mSystem.loadImage("/mainImage/myTexture2dlineColor01.png");
   public static Image frBarPow22 = mSystem.loadImage("/mainImage/myTexture2dlineColor02.png");
   public static byte vcData;
   public static byte vcMap;
   public static byte vcSkill;
   public static byte vcItem;
   public static byte vsData;
   public static byte vsMap;
   public static byte vsSkill;
   public static byte vsItem;
   public static boolean clientDataReadySent = false;
   public static boolean waitingForItemData = false;
   public static boolean hasLoadedItemOptions = false;
   public static boolean hasLoadedItemTemplates = false;
   public static boolean hasLoadedHeadFrames = false;
   public static boolean hasLoadedFlyingHeadFrames = false;
   public static long lastItemDataPacketAt = 0L;
   private static Image imgChat;
   private static Image imgChat2;
   public static Image imgMenu = mSystem.loadImage("/mainImage/myTexture2dmenu.png");
   private static Image imgFocus = mSystem.loadImage("/mainImage/myTexture2dfocus.png");
   private static Image imgFocus2;
   public static Image imgSkill = mSystem.loadImage("/mainImage/myTexture2dskill.png");
   public static Image imgSkill2 = mSystem.loadImage("/mainImage/myTexture2dskill2.png");
   private static Image imgHP1;
   private static Image imgHP2;
   private static Image imgHP3;
   private static Image imgHP4;
   private static Image imgFire0;
   private static Image imgFire1;
   private static Image imgNR1;
   private static Image imgNR2;
   private static Image imgNR3;
   private static Image imgNR4;
   public static Image imgLbtn = mSystem.loadImage("/mainImage/myTexture2dbtnl.png");
   public static Image imgLbtnFocus = mSystem.loadImage("/mainImage/myTexture2dbtnlf.png");
   public static Image imgLbtn2 = mSystem.loadImage("/mainImage/myTexture2dbtnl2.png");
   public static Image imgLbtnFocus2 = mSystem.loadImage("/mainImage/myTexture2dbtnlf2.png");
   public static Image imgAnalog1;
   public static Image imgAnalog2;
   public int timeLengthMap;
   public int timeStartMap;
   public static byte typeActive = 0;
   public static InfoMe info1 = new InfoMe();
   public static InfoMe info2 = new InfoMe();
   private static Image imgPanel = mSystem.loadImage("/mainImage/myTexture2dpanel.png");
   private static Image imgPanel2 = mSystem.loadImage("/mainImage/panel2.png");
   private static Image imgHP = mSystem.loadImage("/mainImage/myTexture2dHP.png");
   public static Image imgMP = mSystem.loadImage("/mainImage/myTexture2dMP.png");
   private static Image imgSP = mSystem.loadImage("/mainImage/SP.png");
   private static Image imgHPLost = mSystem.loadImage("/mainImage/myTexture2dhpLost.png");
   public static Image imgMPLost = mSystem.loadImage("/mainImage/myTexture2dmpLost.png");
   public static Image imgHP_tm_do = mSystem.loadImage("/mainImage/tm-do.png");
   public static Image imgHP_tm_vang = mSystem.loadImage("/mainImage/tm-vang.png");
   public static Image imgHP_tm_xam = mSystem.loadImage("/mainImage/tm-xam.png");
   public static Image imgHP_tm_xanh = mSystem.loadImage("/mainImage/tm-xanh.png");
   public Mob mobCapcha;
   public MagicTree magicTree;
   private int updateCycleTick;
   public static int countEff;
   private static main.GamePad gamePad = new main.GamePad();
   public static int isAnalog = 0;
   private static Image img_ct_bar_0 = mSystem.loadImage("/mainImage/i_pve_bar_0.png");
   private Command cmdDoiCo;
   private Command cmdLogOut;
   private Command cmdChatTheGioi;
   private Command cmdshowInfo;
   public static Skill[] keySkill;
   public static Skill[] onScreenSkill;
   public Command cmdMenu;
   public static int firstY;
   private static int wSkill;
   public static long deltaTime;
   public boolean isPointerDowning;
   private boolean isChangingCameraMode;
   private int ptLastDownX;
   private int ptLastDownY;
   private int ptFirstDownX;
   private int ptFirstDownY;
   private int ptDownTime;
   private boolean disableSingleClick;
   public long lastSingleClick;
   boolean clickMoving;
   private boolean clickOnTileTop;
   private int clickToX;
   private int clickToY;
   private int lastClickCMX;
   private int lastClickCMY;
   public static boolean isNewClanMessage;
   private long lastFire;
   private long lastUsePotion;
   public int auto;
   int dem;
   public boolean isFreez;
   public boolean isUseFreez;
   private static Image imgTrans;
   public boolean isRongThanXuatHien = false;
   public boolean isRongNamek = false;
   private boolean isSuperPower = false;
   private int tPower;
   private int xPower;
   private int yPower;
   private int dxPower;
   public boolean activeRongThan;
   private boolean isMeCallRongThan;
   public int mautroi;
   public int xR;
   public int yR;
   public int mapRID;
   public int zoneRID;
   public int bgRID;
   private static int tam;
   public static boolean isAutoPlay;
   public static boolean canAutoPlay;
   public static boolean isChangeZone;
   private int timeSkill;
   private int nSkill = 0;
   private int selectedIndexSkill = -1;
   private Skill lastSkill;
   public String strCapcha;
   private boolean flareFindFocus;
   private int flareTime;
   private int keyTouchSkill = -1;
   private static long lastTick;
   private static long currTick;
   private static long lastXS;
   private static long currXS;
   private static int secondXS;
   private int runArrow = 0;
   public static int isPaintRada;
   public static Image imgNut;
   public static Image imgNutF;
   public int[] keyCapcha;
   public static Image imgCapcha;
   public String keyInput;
   private static int disXC;
   public static boolean isPaint;
   public static int shock_scr;
   private static long lastPaintCtErrorLogAt;
   private static long lastPaintCharacterLogAt;
   private static int[] shock_x;
   private static int[] shock_y;
   public static MyVector textTime;
   private String paintStage = "idle";
   private Char paintActor;
   private int doubleClickCooldownTicks = 0;
   public static Image arrow;
   private static int yTouchBar;
   private static int xC;
   private static int yC;
   private static int xF;
   private static int yF;
   public static int xHP;
   public static int yHP;
   private static int xTG;
   private static int yTG;
   private static int[] xS;
   private static int[] yS;
   private static int xSkill;
   private static int ySkill;
   public long dMP;
   public long twMp;
   public boolean isInjureMp;
   public long dHP;
   public long twHp;
   public boolean isInjureHp;
   private long curr;
   private long last;
   private int secondVS;
   public static String[] flyTextString;
   public static int[] flyTextX;
   public static int[] flyTextY;
   private static int[] flyTextYTo;
   private static int[] flyTextDx;
   private static int[] flyTextDy;
   public static int[] flyTextState;
   public static int[] flyTextColor;
   private static int[] flyTime;
   private static int[] splashX;
   private static int[] splashY;
   private static int[] splashState;
   private static int[] splashF;
   private static int[] splashDir;
   private static Image[] imgSplash;
   private static int spBarW;
   private static int mpBarW;
   private static long hpBarW;
   private int imgScrW;
   public static int popupY;
   public static int popupX;
   public static int popupW;
   public static int popupH;
   private static int cmySK;
   private static int cmtoYSK;
   private static int cmdySK;
   private static int cmvySK;
   private Command cmdDead;
   public static boolean field_cb_7bcdf178;
   public static boolean showOnScreenDebug;
   public PopUpYesNo popUpYesNo;
   private static Vector vChatVip;
   public boolean isFireWorks;
   private int[] winnumber;
   private int[] randomNumber;
   private int[] tMove;
   private int[] moveCount;
   private int[] delayMove;
   private int moveIndex;
   private String strFinish;
   private int tShow;
   private int xChatVip;
   private int currChatWidth;
   private boolean startChat;
   public byte percentMabu;
   public boolean mabuEff;
   public int tMabuEff;
   public static boolean isPaintChatVip;
   public static byte mabuPercent;
   public static byte isNewMember;
   private String yourNumber;
   private String[] strPaint;
   private static Image imgHP_NEW = mSystem.loadImage("/mainImage/i_hp.png");
   public static InfoPhuBan phuban_Info;
   private static FrameImage fra_PVE_Bar_0 = new FrameImage(mSystem.loadImage("/mainImage/i_pve_bar_0.png"), 6, 15);
   private static FrameImage fra_PVE_Bar_1 = new FrameImage(mSystem.loadImage("/mainImage/i_pve_bar_1.png"), 38, 21);
   private static Image imgVS = mSystem.loadImage("/mainImage/i_vs.png");
   private static Image imgBall = mSystem.loadImage("/mainImage/i_charlife.png");
   private static Image imgKhung = mSystem.loadImage("/mainImage/i_khung.png");
   public static int nCT_TeamB;
   public static int nCT_TeamA;
   public static long nCT_timeBallte;
   public static String nCT_team;
   public static int nCT_nBoyBaller;
   public static boolean isPaint_CT;
   public static byte nCT_floor;
   public static MyVector res_CT;
   private static int nTop;
   public static boolean isPickNgocRong;
   public static int nUSER_CT;
   public static int nUSER_MAX_CT;
   public static boolean isudungCapsun4;
   public static boolean isudungCapsun3;

   static {
      new MyVector("vPtMap");
      new MyVector("vSet");
      new MyVector("vItemUpGrade");
      mSystem.loadImage("/mainImage/i_pve_bar_1.png");
      if (GameCanvas.isTouch) {
         mSystem.loadImage("/mainImage/myTexture2darrow.png");
         mSystem.loadImage("/mainImage/myTexture2darrow2.png");
         imgChat = mSystem.loadImage("/mainImage/myTexture2dchat.png");
         imgChat2 = mSystem.loadImage("/mainImage/myTexture2dchat2.png");
         imgFocus2 = mSystem.loadImage("/mainImage/myTexture2dfocus2.png");
         imgHP1 = mSystem.loadImage("/mainImage/myTexture2dPea0.png");
         imgHP2 = mSystem.loadImage("/mainImage/myTexture2dPea1.png");
         imgAnalog1 = mSystem.loadImage("/mainImage/myTexture2danalog1.png");
         imgAnalog2 = mSystem.loadImage("/mainImage/myTexture2danalog2.png");
         imgHP3 = mSystem.loadImage("/mainImage/myTexture2dPea2.png");
         imgHP4 = mSystem.loadImage("/mainImage/myTexture2dPea3.png");
         imgFire0 = mSystem.loadImage("/mainImage/myTexture2dfirebtn0.png");
         imgFire1 = mSystem.loadImage("/mainImage/myTexture2dfirebtn1.png");
      }

      imgNR1 = mSystem.loadImage("/mainImage/myTexture2dPea_0.png");
      imgNR2 = mSystem.loadImage("/mainImage/myTexture2dPea_1.png");
      imgNR3 = mSystem.loadImage("/mainImage/myTexture2dPea_2.png");
      imgNR4 = mSystem.loadImage("/mainImage/myTexture2dPea_3.png");
      isAnalog = Rms.loadRMSInt("analog") == 1 ? 1 : 0;
      gamePad = new main.GamePad();
      byte[] storedNRdataVersion = Rms.loadRMS("NRdataVersion");
      byte[] storedNRmapVersion = Rms.loadRMS("NRmapVersion");
      byte[] storedNRskillVersion = Rms.loadRMS("NRskillVersion");
      byte[] storedNRitemVersion = Rms.loadRMS("NRitemVersion");
      if (storedNRdataVersion != null) {
         vcData = storedNRdataVersion[0];
      }

      if (storedNRmapVersion != null) {
         vcMap = storedNRmapVersion[0];
      }

      if (storedNRskillVersion != null) {
         vcSkill = storedNRskillVersion[0];
      }

      if (storedNRitemVersion != null) {
         vcItem = storedNRitemVersion[0];
      }

      keySkill = new Skill[10];
      onScreenSkill = new Skill[10];
      imgTrans = mSystem.loadImage("/bg/trans.png");
      tam = 0;
      imgNut = mSystem.loadImage("/mainImage/myTexture2dnut.png");
      imgNutF = mSystem.loadImage("/mainImage/myTexture2dnutF.png");
      isPaint = true;
      shock_x = new int[]{1, -1, 1, -1};
      shock_y = new int[]{1, -1, -1, 1};
      textTime = new MyVector("");
      arrow = mSystem.loadImage("/mainImage/myTexture2darrow3.png");
      flyTextX = new int[5];
      flyTextY = new int[5];
      flyTextDx = new int[5];
      flyTextDy = new int[5];
      flyTextState = new int[5];
      flyTextString = new String[5];
      flyTextYTo = new int[5];
      flyTime = new int[5];
      flyTextColor = new int[8];

      for (int flyTextStateIndex = 0; flyTextStateIndex < 5; flyTextStateIndex++) {
         flyTextState[flyTextStateIndex] = -1;
      }

      popupW = 140;
      popupH = 160;
      field_cb_7bcdf178 = false;
      showOnScreenDebug = false;
      vChatVip = new Vector();
      nCT_TeamB = 50;
      nCT_TeamA = 50;
      nCT_nBoyBaller = 100;
      res_CT = new MyVector("");
      nTop = 1;
      isPickNgocRong = false;
   }

   public static void resetClientDataReadiness() {
      clientDataReadySent = false;
      waitingForItemData = false;
      hasLoadedItemOptions = false;
      hasLoadedItemTemplates = false;
      hasLoadedHeadFrames = false;
      hasLoadedFlyingHeadFrames = false;
      lastItemDataPacketAt = 0L;
   }

   public final void readPart() {
      DataInputStream input = null;

      try {
         short partCount;
         parts = new Part[partCount = (input = new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NR_part")))).readShort()];

         for (int partIndex = 0; partIndex < partCount; partIndex++) {
            byte type = input.readByte();
            parts[partIndex] = new Part(type);

            for (int piIndex = 0; piIndex < parts[partIndex].pi.length; piIndex++) {
               parts[partIndex].pi[piIndex] = new PartImage();
               parts[partIndex].pi[piIndex].id = input.readShort();
               parts[partIndex].pi[piIndex].dx = input.readByte();
               parts[partIndex].pi[piIndex].dy = input.readByte();
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         try {
            input.close();
         } catch (IOException iOException) {
            iOException.printStackTrace();
         }
      }

      SmallImage.init();
   }

   public static void paintOngMauPercent(Image img0, Image img1, Image img2, float x, float y, int size, float pixelPercent, mGraphics graphics) {
      size = graphics.getClipX();
      int y2 = graphics.getClipY();
      int width = graphics.getClipWidth();
      int height = graphics.getClipHeight();
      graphics.setClip((int)x, (int)y, (int)pixelPercent, 13);

      for (int index = 0; index < 4; index++) {
         graphics.drawImage(img1, ((float) (x + (index + 1) * 15)), ((float) (y)), 0);
      }

      graphics.drawImage(img0, ((float) (x)), ((float) (y)), 0);
      graphics.drawImage(img1, ((float) (x + 100.0F - 30.0F)), ((float) (y)), 0);
      graphics.drawImage(img2, ((float) (x + 100.0F - 15.0F)), ((float) (y)), 0);
      graphics.setClip(size, y2, width, height);
   }

   public static boolean isMapDocNhan() {
      return TileMap.mapID >= 53 && TileMap.mapID <= 62;
   }

   public static boolean isMapFize() {
      return TileMap.mapID >= 63;
   }

   public final void switchToMe() {
      ServerListScreen.isWait = false;
      if (BackgroudEffect.isHaveRain()) {
         SoundMn.gI();
         SoundMn.rain();
      }

      LoginScr.isContinueToLogin = false;
      Char.isLoadingMap = false;
      if (!isPaintOther) {
         Service.gI().finishLoadMap();
      }

      if (TileMap.isTrainingMap()) {
         GameScr gameScreen = this;
         if (CreateCharScr.isCreateChar) {
            CreateCharScr.isCreateChar = false;
            gameScreen.right = null;
         }
      }

      info1.isUpdate = true;
      info2.isUpdate = true;
      isLoadAllData = true;
      this.updateCycleTick = 0;
      isPaintOther = false;
      super.switchToMe();
   }

   public static void resetAllvector() {
      Teleport.vTeleport.removeAllElements();
      vCharInMap.removeAllElements();
      vItemMap.removeAllElements();
      Effect2.vEffect2.removeAllElements();
      Effect2.vAnimateEffect.removeAllElements();
      Effect2.vEffect2Outside.removeAllElements();
      Effect2.vEffectFeet.removeAllElements();
      Effect2.vEffect3.removeAllElements();
      vMobAttack.removeAllElements();
      vMob.removeAllElements();
      vNpc.removeAllElements();
      Char.myCharz().vMovePoints.removeAllElements();
   }

   public static void loadSkillShortcut() {
   }

   public final void onOSkill(byte[] oSkillID) {
      Res.outz("GET ONSCREENSKILL!");
      onScreenSkill = new Skill[10];
      if (oSkillID == null) {
         Res.outz("null");
         Res.outz("LOAD DEFAULT ONSCREEN SKILL");

         for (int onScreenSkillIndex = 0; onScreenSkillIndex < onScreenSkill.length && onScreenSkillIndex < Char.myCharz().vSkillFight.size(); onScreenSkillIndex++) {
            Skill skillFight = (Skill)Char.myCharz().vSkillFight.elementAt(onScreenSkillIndex);
            onScreenSkill[onScreenSkillIndex] = skillFight;
         }

         saveonScreenSkillToRMS();
      } else {
         for (int oSkillIDIndex = 0; oSkillIDIndex < oSkillID.length; oSkillIDIndex++) {
            for (int skillFightIndex = 0; skillFightIndex < Char.myCharz().vSkillFight.size(); skillFightIndex++) {
               Skill skillFight2;
               if ((skillFight2 = (Skill)Char.myCharz().vSkillFight.elementAt(skillFightIndex)).template.id == oSkillID[oSkillIDIndex]) {
                  onScreenSkill[oSkillIDIndex] = skillFight2;
                  break;
               }
            }
         }
      }
   }

   public final void onKSkill(byte[] kSkillID) {
      Res.outz("GET KEYSKILL!");
      keySkill = new Skill[10];
      if (kSkillID == null) {
         Res.outz("LOAD DEFAULT KEY SKILL");

         for (int keySkillIndex = 0; keySkillIndex < keySkill.length && keySkillIndex < Char.myCharz().vSkillFight.size(); keySkillIndex++) {
            Skill skillFight = (Skill)Char.myCharz().vSkillFight.elementAt(keySkillIndex);
            keySkill[keySkillIndex] = skillFight;
         }

         saveKeySkillToRMS();
      } else {
         for (int kSkillIDIndex = 0; kSkillIDIndex < kSkillID.length; kSkillIDIndex++) {
            for (int skillFightIndex = 0; skillFightIndex < Char.myCharz().vSkillFight.size(); skillFightIndex++) {
               Skill skillFight2;
               if ((skillFight2 = (Skill)Char.myCharz().vSkillFight.elementAt(skillFightIndex)).template.id == kSkillID[kSkillIDIndex]) {
                  keySkill[kSkillIDIndex] = skillFight2;
                  break;
               }
            }
         }
      }
   }

   public final void onCSkill(byte[] cSkillID) {
      Res.outz("GET CURRENTSKILL!");
      if (cSkillID != null && cSkillID.length != 0) {
         for (int skillFightIndex = 0; skillFightIndex < Char.myCharz().vSkillFight.size(); skillFightIndex++) {
            Skill myskill;
            if ((myskill = (Skill)Char.myCharz().vSkillFight.elementAt(skillFightIndex)).template.id == cSkillID[0]) {
               Char.myCharz().myskill = myskill;
               break;
            }
         }
      } else if (Char.myCharz().vSkillFight.size() > 0) {
         Char.myCharz().myskill = (Skill)Char.myCharz().vSkillFight.elementAt(0);
      }

      if (Char.myCharz().myskill != null) {
         Service.gI().selectSkill(Char.myCharz().myskill.template.id);
      }
   }

   protected static void doSetOnScreenSkill(SkillTemplate skillTemplate) {
      Res.outz("DO SET ONSCREEN SKILL");
      Skill skill = Char.myCharz().getSkill(skillTemplate);
      MyVector menuItems = new MyVector("");

      for (int onScreenSkillIndex = 0; onScreenSkillIndex < (GameCanvas.isTouch ? 10 : 5); onScreenSkillIndex++) {
         Object[] p;
         (p = new Object[2])[0] = skill;
         p[1] = String.valueOf(onScreenSkillIndex);
         Command entry = new Command(mResources.into_place + (onScreenSkillIndex + 1), 11120, p);
         Skill onScreenSkillValue;
         if ((onScreenSkillValue = onScreenSkill[onScreenSkillIndex]) != null) {
            entry.isDisplay = true;
         }

         menuItems.addElement(entry);
      }

      GameCanvas.menu.startAt(menuItems);
   }

   protected static void doSetKeySkill(SkillTemplate skillTemplate) {
      Res.outz("DO SET KEY SKILL");
      Skill skill = Char.myCharz().getSkill(skillTemplate);
      String[] texts = TField.isQwerty ? mResources.key_skill_qwerty : mResources.key_skill;
      MyVector menuItems = new MyVector("");

      for (int index = 0; index < (GameCanvas.isTouch ? 10 : 5); index++) {
         Object[] p;
         (p = new Object[2])[0] = skill;
         p[1] = String.valueOf(index);
         menuItems.addElement(new Command(texts[index], 11121, p));
      }

      GameCanvas.menu.startAt(menuItems);
   }

   private static void saveonScreenSkillToRMS() {
      byte[] skill = new byte[onScreenSkill.length];

      for (int onScreenSkillIndex = 0; onScreenSkillIndex < onScreenSkill.length; onScreenSkillIndex++) {
         if (onScreenSkill[onScreenSkillIndex] == null) {
            skill[onScreenSkillIndex] = -1;
         } else {
            skill[onScreenSkillIndex] = onScreenSkill[onScreenSkillIndex].template.id;
         }
      }

      Service.gI().changeOnKeyScr(skill);
   }

   private static void saveKeySkillToRMS() {
      byte[] skill = new byte[keySkill.length];

      for (int keySkillIndex = 0; keySkillIndex < keySkill.length; keySkillIndex++) {
         if (keySkill[keySkillIndex] == null) {
            skill[keySkillIndex] = -1;
         } else {
            skill[keySkillIndex] = keySkill[keySkillIndex].template.id;
         }
      }

      Service.gI().changeOnKeyScr(skill);
   }

   public static boolean isBagFull() {
      for (int itemBagIndex = Char.myCharz().arrItemBag.length - 1; itemBagIndex >= 0; itemBagIndex--) {
         if (Char.myCharz().arrItemBag[itemBagIndex] == null) {
            return false;
         }
      }

      return true;
   }

   public static void createMenu(String[] menu, Npc npc) {
      MyVector menuItems = new MyVector("");

      for (int menuIndex = 0; menuIndex < menu.length; menuIndex++) {
         menuItems.addElement(new Command(menu[menuIndex], 11057, npc));
      }

      GameCanvas.menu.startAt(menuItems);
   }



   private static void readEfect() {
      byte[] storedEffect;
      if ((storedEffect = Rms.loadRMS("NR_effect")) != null) {
         DataInputStream input = null;
         boolean cleanupPending = false                             ;

         label95: {
            try {
               cleanupPending = true;
               short effectCharPaintCount;
               efs = new EffectCharPaint[effectCharPaintCount = (input = new DataInputStream(new ByteArrayInputStream(storedEffect))).readShort()];

               for (int efsIndex = 0; efsIndex < effectCharPaintCount; efsIndex++) {
                  efs[efsIndex] = new EffectCharPaint();
                  input.readShort();
                  efs[efsIndex].arrEfInfo = new EffectInfoPaint[input.readByte()];

                  for (int efInfoIndex = 0; efInfoIndex < efs[efsIndex].arrEfInfo.length; efInfoIndex++) {
                     efs[efsIndex].arrEfInfo[efInfoIndex] = new EffectInfoPaint();
                     efs[efsIndex].arrEfInfo[efInfoIndex].idImg = input.readShort();
                     efs[efsIndex].arrEfInfo[efInfoIndex].dx = input.readByte();
                     efs[efsIndex].arrEfInfo[efInfoIndex].dy = input.readByte();
                  }
               }

               cleanupPending = false;
               break label95;
            } catch (Exception exception) {
               exception.printStackTrace();
               cleanupPending = false;
            } finally {
               if (cleanupPending) {
                  try {
                     if (input != null) {
                        input.close();
                     }
                  } catch (IOException iOException) {
                     iOException.printStackTrace();
                  }
               }
            }

            try {
               if (input != null) {
                  input.close();
                  return;
               }

               return;
            } catch (IOException iOException2) {
               iOException2.printStackTrace();
               return;
            }
         }

         try {
            input.close();
         } catch (IOException iOException3) {
            iOException3.printStackTrace();
         }
      }
   }



   private static void readArrow() {
      byte[] storedArrow;
      if ((storedArrow = Rms.loadRMS("NR_arrow")) != null) {
         DataInputStream input = null;
         boolean cleanupPending = false                             ;

         label81: {
            try {
               cleanupPending = true;
               short arrowpaintCount;
               arrs = new Arrowpaint[arrowpaintCount = (input = new DataInputStream(new ByteArrayInputStream(storedArrow))).readShort()];

               for (int arrIndex = 0; arrIndex < arrowpaintCount; arrIndex++) {
                  arrs[arrIndex] = new Arrowpaint();
                  arrs[arrIndex].id = input.readShort();
                  arrs[arrIndex].imgId[0] = input.readShort();
                  arrs[arrIndex].imgId[1] = input.readShort();
                  arrs[arrIndex].imgId[2] = input.readShort();
               }

               cleanupPending = false;
               break label81;
            } catch (Exception exception) {
               exception.printStackTrace();
               cleanupPending = false;
            } finally {
               if (cleanupPending) {
                  try {
                     if (input != null) {
                        input.close();
                     }
                  } catch (IOException iOException) {
                     iOException.printStackTrace();
                  }
               }
            }

            try {
               if (input != null) {
                  input.close();
                  return;
               }

               return;
            } catch (IOException iOException2) {
               iOException2.printStackTrace();
               return;
            }
         }

         try {
            input.close();
         } catch (IOException iOException3) {
            iOException3.printStackTrace();
         }
      }
   }



   private static void readDart() {
      byte[] storedDart;
      if ((storedDart = Rms.loadRMS("NR_dart")) != null) {
         DataInputStream input = null;
         boolean cleanupPending = false                             ;

         label196: {
            try {
               cleanupPending = true;
               short dartInfoCount;
               darts = new DartInfo[dartInfoCount = (input = new DataInputStream(new ByteArrayInputStream(storedDart))).readShort()];

               for (int dartIndex = 0; dartIndex < dartInfoCount; dartIndex++) {
                  darts[dartIndex] = new DartInfo();
                  input.readShort();
                  darts[dartIndex].nUpdate = input.readShort();
                  darts[dartIndex].va = input.readShort() << 8;
                  darts[dartIndex].xdPercent = input.readShort();
                  short headBorderCount = input.readShort();
                  darts[dartIndex].tail = new short[headBorderCount];

                  for (int tailIndex = 0; tailIndex < headBorderCount; tailIndex++) {
                     darts[dartIndex].tail[tailIndex] = input.readShort();
                  }

                  headBorderCount = input.readShort();
                  darts[dartIndex].tailBorder = new short[headBorderCount];

                  for (int tailBorderIndex = 0; tailBorderIndex < headBorderCount; tailBorderIndex++) {
                     darts[dartIndex].tailBorder[tailBorderIndex] = input.readShort();
                  }

                  headBorderCount = input.readShort();
                  darts[dartIndex].xd1 = new short[headBorderCount];

                  for (int xd1Index = 0; xd1Index < headBorderCount; xd1Index++) {
                     darts[dartIndex].xd1[xd1Index] = input.readShort();
                  }

                  headBorderCount = input.readShort();
                  darts[dartIndex].xd2 = new short[headBorderCount];

                  for (int xd2Index = 0; xd2Index < headBorderCount; xd2Index++) {
                     darts[dartIndex].xd2[xd2Index] = input.readShort();
                  }

                  headBorderCount = input.readShort();
                  darts[dartIndex].head = new short[headBorderCount][];

                  for (int headIndex = 0; headIndex < headBorderCount; headIndex++) {
                     short headCount = input.readShort();
                     darts[dartIndex].head[headIndex] = new short[headCount];

                     for (int headIndex2 = 0; headIndex2 < headCount; headIndex2++) {
                        darts[dartIndex].head[headIndex][headIndex2] = input.readShort();
                     }
                  }

                  headBorderCount = input.readShort();
                  darts[dartIndex].headBorder = new short[headBorderCount][];

                  for (int headBorderIndex = 0; headBorderIndex < headBorderCount; headBorderIndex++) {
                     short headBorderCount2 = input.readShort();
                     darts[dartIndex].headBorder[headBorderIndex] = new short[headBorderCount2];

                     for (int headBorderIndex2 = 0; headBorderIndex2 < headBorderCount2; headBorderIndex2++) {
                        darts[dartIndex].headBorder[headBorderIndex][headBorderIndex2] = input.readShort();
                     }
                  }
               }

               cleanupPending = false;
               break label196;
            } catch (Exception exception) {
               exception.printStackTrace();
               cleanupPending = false;
            } finally {
               if (cleanupPending) {
                  try {
                     if (input != null) {
                        input.close();
                     }
                  } catch (IOException iOException) {
                     iOException.printStackTrace();
                  }
               }
            }

            try {
               if (input != null) {
                  input.close();
                  return;
               }

               return;
            } catch (IOException iOException2) {
               iOException2.printStackTrace();
               return;
            }
         }

         try {
            input.close();
         } catch (IOException iOException3) {
            iOException3.printStackTrace();
         }
      }
   }



   private static void readSkill() {
      byte[] storedSkill;
      if ((storedSkill = Rms.loadRMS("NR_skill")) != null) {
         DataInputStream input = null;
         boolean cleanupPending = false                             ;

         label119: {
            label118: {
               try {
                  cleanupPending = true;
                  short packetShort = (input = new DataInputStream(new ByteArrayInputStream(storedSkill))).readShort();
                  sks = new SkillPaint[Skills.skills.size()];

                  for (int index = 0; index < packetShort; index++) {
                     short id;
                     if ((id = input.readShort()) == 1111) {
                        id = (short)(packetShort - 1);
                     }

                     sks[id] = new SkillPaint();
                     sks[id].id = id;
                     sks[id].effectHappenOnMob = input.readShort();
                     if (sks[id].effectHappenOnMob <= 0) {
                        sks[id].effectHappenOnMob = 80;
                     }

                     input.readByte();
                     sks[id].skillStand = new SkillInfoPaint[input.readByte()];

                     for (int skillStandIndex = 0; skillStandIndex < sks[id].skillStand.length; skillStandIndex++) {
                        sks[id].skillStand[skillStandIndex] = new SkillInfoPaint();
                        sks[id].skillStand[skillStandIndex].status = input.readByte();
                        sks[id].skillStand[skillStandIndex].effS0Id = input.readShort();
                        sks[id].skillStand[skillStandIndex].e0dx = input.readShort();
                        sks[id].skillStand[skillStandIndex].e0dy = input.readShort();
                        sks[id].skillStand[skillStandIndex].effS1Id = input.readShort();
                        sks[id].skillStand[skillStandIndex].e1dx = input.readShort();
                        sks[id].skillStand[skillStandIndex].e1dy = input.readShort();
                        sks[id].skillStand[skillStandIndex].effS2Id = input.readShort();
                        sks[id].skillStand[skillStandIndex].e2dx = input.readShort();
                        sks[id].skillStand[skillStandIndex].e2dy = input.readShort();
                        sks[id].skillStand[skillStandIndex].arrowId = input.readShort();
                        sks[id].skillStand[skillStandIndex].adx = input.readShort();
                        sks[id].skillStand[skillStandIndex].ady = input.readShort();
                     }

                     sks[id].skillfly = new SkillInfoPaint[input.readByte()];

                     for (int skillflyIndex = 0; skillflyIndex < sks[id].skillfly.length; skillflyIndex++) {
                        sks[id].skillfly[skillflyIndex] = new SkillInfoPaint();
                        sks[id].skillfly[skillflyIndex].status = input.readByte();
                        sks[id].skillfly[skillflyIndex].effS0Id = input.readShort();
                        sks[id].skillfly[skillflyIndex].e0dx = input.readShort();
                        sks[id].skillfly[skillflyIndex].e0dy = input.readShort();
                        sks[id].skillfly[skillflyIndex].effS1Id = input.readShort();
                        sks[id].skillfly[skillflyIndex].e1dx = input.readShort();
                        sks[id].skillfly[skillflyIndex].e1dy = input.readShort();
                        sks[id].skillfly[skillflyIndex].effS2Id = input.readShort();
                        sks[id].skillfly[skillflyIndex].e2dx = input.readShort();
                        sks[id].skillfly[skillflyIndex].e2dy = input.readShort();
                        sks[id].skillfly[skillflyIndex].arrowId = input.readShort();
                        sks[id].skillfly[skillflyIndex].adx = input.readShort();
                        sks[id].skillfly[skillflyIndex].ady = input.readShort();
                     }
                  }

                  cleanupPending = false;
                  break label118;
               } catch (Exception exception) {
                  exception.printStackTrace();
                  cleanupPending = false;
               } finally {
                  if (cleanupPending) {
                     try {
                        if (input != null) {
                           input.close();
                        }
                     } catch (IOException iOException) {
                        iOException.printStackTrace();
                     }
                  }
               }

               try {
                  if (input != null) {
                     input.close();
                  }
               } catch (IOException iOException2) {
                  iOException2.printStackTrace();
               }
               break label119;
            }

            try {
               input.close();
            } catch (IOException iOException3) {
               iOException3.printStackTrace();
            }
         }

         Res.outz("READ SKILL DONE");
      }
   }

   public static void readOk() {
      try {
         if (clientDataReadySent) {
            return;
         }

         Res.outz("<readOk><vsData<" + vsData + "==" + vcData);
         Res.outz("<readOk><vsMap<" + vsMap + "==" + vcMap);
         Res.outz("<readOk><vsSkill<" + vsSkill + "==" + vcSkill);
         Res.outz("<readOk><vsItem<" + vsItem + "==" + vcItem);
         if (vsData == vcData
            && vsMap == vcMap
            && vsSkill == vcSkill
            && vsItem == vcItem) {
            boolean condition = hasLoadedItemOptions && hasLoadedItemTemplates && hasLoadedHeadFrames && hasLoadedFlyingHeadFrames;
            boolean condition2 = hasLoadedItemOptions && hasLoadedItemTemplates && lastItemDataPacketAt > 0L && mSystem.currentTimeMillis() - lastItemDataPacketAt >= 500L;
            if (waitingForItemData && !condition && !condition2) {
               Res.outz(
                  "<readOk> Dang cho du cac type Item: [0="
                     + hasLoadedItemOptions
                     + ", 1="
                     + hasLoadedItemTemplates
                     + ", 100="
                     + hasLoadedHeadFrames
                     + ", 101="
                     + hasLoadedFlyingHeadFrames
                     + "]"
               );
               return;
            }

            clientDataReadySent = true;
            waitingForItemData = false;
            Res.outz(
               ">>>>> DA DU TYPE VA VERSION -> GUI CLIENT_OK: "
                  + vsData
                  + ","
                  + vsMap
                  + ","
                  + vsSkill
                  + ","
                  + vsItem
            );
            gI();
            readDart();
            gI();
            readEfect();
            gI();
            readArrow();
            gI();
            readSkill();
            Service.gI().clientOk();
            return;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public static GameScr gI() {
      if (instance == null) {
         instance = new GameScr();
      }

      return instance;
   }

   public static void clearGameScr() {
      instance = null;
   }

   public final void loadSplash() {
      if (imgSplash == null) {
         imgSplash = new Image[3];

         for (int imgSplashIndex = 0; imgSplashIndex < 3; imgSplashIndex++) {
            imgSplash[imgSplashIndex] = mSystem.loadImage("/e/sp" + imgSplashIndex + ".png");
         }
      }

      splashX = new int[2];
      splashY = new int[2];
      splashState = new int[2];
      splashF = new int[2];
      splashDir = new int[2];
      splashState[0] = splashState[1] = -1;
      this.imgScrW = 84;
      hpBarW = 66L;
      mpBarW = 59;
      spBarW = 61;
   }

   public GameScr() {
      int[] intValues = new int[]{-1, -1};
      this.isFireWorks = false;
      this.yourNumber = "";
      if (GameCanvas.w != 128) {
         ;
      }

      new Command("", 11021);
      this.cmdMenu = new Command("  ", 11000);
      this.cmdFocus = new Command("", 11001);
      this.cmdMenu.img = imgMenu;
      this.cmdMenu.w = mGraphics.getImageWidth(this.cmdMenu.img) + 20;
      this.cmdMenu.isPlaySoundButton = false;
      this.cmdFocus.img = imgFocus;
      if (GameCanvas.isTouch) {
         this.cmdMenu.x = 0;
         this.cmdMenu.y = 50;
         this.cmdFocus = null;
      } else {
         this.cmdMenu.x = 0;
         this.cmdMenu.y = gH - 30;
         this.cmdFocus.x = gW - 32;
         this.cmdFocus.y = gH - 32;
      }

      super.right = this.cmdFocus;
      isPaintRada = 1;
      if (GameCanvas.isTouch) {
         isHaveSelectSkill = true;
      }

      this.cmdDoiCo = new Command("Đổi cờ", GameCanvas.gI(), 100001, null);
      this.cmdLogOut = new Command("Logout", GameCanvas.gI(), 100002, null);
      this.cmdChatTheGioi = new Command("chat world", GameCanvas.gI(), 100003, null);
      this.cmdshowInfo = new Command("InfoLog", GameCanvas.gI(), 100004, null);
      this.cmdDoiCo.setType();
      this.cmdLogOut.setType();
      this.cmdChatTheGioi.setType();
      this.cmdshowInfo.setType();
      this.cmdChatTheGioi.x = GameCanvas.w - this.cmdChatTheGioi.w;
      this.cmdshowInfo.x = GameCanvas.w - this.cmdshowInfo.w;
      this.cmdLogOut.x = GameCanvas.w - this.cmdLogOut.w;
      this.cmdDoiCo.x = GameCanvas.w - this.cmdDoiCo.w;
      this.cmdChatTheGioi.y = this.cmdChatTheGioi.h + mFont.tahoma_7_white.getHeight();
      this.cmdshowInfo.y = (this.cmdChatTheGioi.h << 1) + mFont.tahoma_7_white.getHeight();
      this.cmdLogOut.y = this.cmdChatTheGioi.h * 3 + mFont.tahoma_7_white.getHeight();
      this.cmdDoiCo.y = (this.cmdChatTheGioi.h << 2) + mFont.tahoma_7_white.getHeight();
   }

   public static final void loadCamera(int value, int value2) {
      gW = GameCanvas.w;
      cmdBarH = 39;
      gH = GameCanvas.h;
      if ((csPadMaxH = GameCanvas.h / 6) < 48) {
         csPadMaxH = 48;
      }

      gW2 = gW >> 1;
      gH2 = gH >> 1;
      gW23 = gH - 120;
      gH23 = (gH << 1) / 3;
      gW6 = gW / 6;
      gssw = gW / TileMap.size + 2;
      gssh = gH / TileMap.size + 2;
      if (gW % 24 != 0) {
         gssw++;
      }

      cmxLim = (TileMap.tmw - 1) * TileMap.size - gW;
      cmyLim = (TileMap.tmh - 1) * TileMap.size - gH;
      if (value == -1 && value2 == -1) {
         cmx = cmtoX = Char.myCharz().cx
            - gW2
            + gW6 * Char.myCharz().cdir;
         cmy = cmtoY = Char.myCharz().cy - gH23 - 1;
      } else {
         cmx = cmtoX = value - gW23 + gW6 * Char.myCharz().cdir;
         cmy = cmtoY = value2 - gH23 - 1;
      }

      if (cmx < 24) {
         cmtoX = 24;
         cmx = 24;
      }

      if (cmx > cmxLim) {
         cmx = cmtoX = cmxLim;
      }

      if (cmy < 0) {
         cmtoY = 0;
         cmy = 0;
      }

      if (cmy > cmyLim) {
         cmy = cmtoY = cmyLim;
      }

      if ((gssx = cmx / TileMap.size - 1) < 0) {
         gssx = 0;
      }

      gssy = cmy / TileMap.size;
      gssxe = gssx + gssw;
      gssye = gssy + gssh;
      if (gssy < 0) {
         gssy = 0;
      }

      if (gssye > TileMap.tmh - 1) {
         gssye = TileMap.tmh - 1;
      }

      if ((TileMap.countx = gssxe - gssx << 2) > TileMap.tmw) {
         TileMap.countx = TileMap.tmw;
      }

      if ((TileMap.county = gssye - gssy << 2) > TileMap.tmh) {
         TileMap.county = TileMap.tmh;
      }

      if ((TileMap.gssx = (Char.myCharz().cx - 2 * gW) / TileMap.size) < 0) {
         TileMap.gssx = 0;
      }

      if ((TileMap.gssxe = TileMap.gssx + TileMap.countx) > TileMap.tmw) {
         TileMap.gssxe = TileMap.tmw;
      }

      if ((TileMap.gssy = (Char.myCharz().cy - 2 * gH) / TileMap.size) < 0) {
         TileMap.gssy = 0;
      }

      if ((TileMap.gssye = TileMap.gssy + TileMap.county) > TileMap.tmh) {
         TileMap.gssye = TileMap.tmh;
      }

      ChatTextField.gI().parentScreen = instance;
      ChatTextField.gI().tfChat.y = GameCanvas.h
         - 35
         - ChatTextField.gI().tfChat.height;
      ChatTextField.gI().initChatTextField();
      if (GameCanvas.isTouch) {
         yTouchBar = gH - 88;
         xC = gW - 40;
         yC = 2;
         if (GameCanvas.w <= 240) {
            xC = gW - 35;
            yC = 5;
         }

         xF = gW - 55;
         yF = yTouchBar + 35;
         xTG = gW - 37;
         yTG = yTouchBar - 1;
         if (GameCanvas.w >= 450) {
            yTG -= 12;
            yHP -= 7;
            xF -= 10;
            yF -= 5;
            xTG -= 10;
         }
      }

      setSkillBarPosition();
      disXC = GameCanvas.w > 200 ? 40 : 30;
      Panel panel;
      boolean isViewChatServer;
      if (Rms.loadRMSInt("viewchat") == -1) {
         panel = GameCanvas.panel;
         isViewChatServer = true;
      } else {
         panel = GameCanvas.panel;
         isViewChatServer = Rms.loadRMSInt("viewchat") == 1;
      }

      panel.isViewChatServer = isViewChatServer;
   }

   public static void setSkillBarPosition() {
      Skill[] skills = GameCanvas.isTouch ? onScreenSkill : keySkill;
      xS = new int[skills.length];
      yS = new int[skills.length];
      wSkill = 30;
      if (GameCanvas.w <= 320) {
         ySkill = gH - wSkill - 6;
         xSkill = gW2 - skills.length * wSkill / 2 - 25;
      } else {
         wSkill = 40;
         xSkill = 10;
         ySkill = GameCanvas.h - wSkill + 7;
      }

      for (int xSIndex = 0; xSIndex < xS.length; xSIndex++) {
         xS[xSIndex] = xSIndex * wSkill;
         yS[xSIndex] = ySkill;
         if (xS.length > 5 && xSIndex >= xS.length / 2) {
            xS[xSIndex] = (xSIndex - xS.length / 2) * wSkill;
            yS[xSIndex] = ySkill - 32;
         }
      }

      xHP = skills.length * wSkill;
      yHP = ySkill;
      if (GameCanvas.isTouch) {
         xSkill = 17;
         ySkill = GameCanvas.h - 40;
         if (gamePad.isSmallGamePad && isAnalog == 1) {
            xHP = skills.length * wSkill;
            yHP = ySkill;
         } else {
            xHP = GameCanvas.w - 45;
            yHP = GameCanvas.h - 45;
         }

         if (isAnalog != 0) {
            xTG = xF = GameCanvas.w - 45;
            if (gamePad.isLargeGamePad) {
               xSkill = gamePad.wZone + 20;
               wSkill = 35;
               xHP = xF - 45;
            } else if (gamePad.isMediumGamePad) {
               xHP = xF - 45;
            }

            yTG = (yF = GameCanvas.h - 45) - 45;
         }

         for (int xSIndex2 = 0; xSIndex2 < xS.length; xSIndex2++) {
            xS[xSIndex2] = xSIndex2 * wSkill;
            yS[xSIndex2] = ySkill;
            if (xS.length > 5 && xSIndex2 >= xS.length / 2) {
               xS[xSIndex2] = (xSIndex2 - xS.length / 2) * wSkill;
               yS[xSIndex2] = ySkill - 32;
            }
         }
      }
   }

   public final void clanInvite(String strInvite, int clanID, int code) {
      ClanObject p;
      (p = new ClanObject()).code = code;
      p.clanID = clanID;
      this.startYesNoPopUp(strInvite, new Command(mResources.YES, 12002, p), new Command(mResources.NO, 12003, p));
   }

   private void playerMenu() {
      this.auto = 0;
      GameCanvas.clearKeyHold();
      if (Char.myCharz().charFocus.charID >= 0) {
         if (Char.myCharz().charID >= 0) {
            MyVector playerMenu = GameCanvas.panel.vPlayerMenu;
            if (GameCanvas.panel.vPlayerMenu.size() <= 0) {
               if (Char.myCharz().taskMaint != null && Char.myCharz().taskMaint.taskId > 1) {
                  playerMenu.addElement(new Command(mResources.make_friend, 11112, Char.myCharz().charFocus));
                  playerMenu.addElement(new Command(mResources.trade, 11113, Char.myCharz().charFocus));
               }

               if (Char.myCharz().clan != null
                  && Char.myCharz().role < 2
                  && Char.myCharz().charFocus.clanID == -1) {
                  playerMenu.addElement(new Command(mResources.CHAR_ORDER[4], 110391));
               }

               if (Char.myCharz().charFocus.statusMe != 14
                  && Char.myCharz().charFocus.statusMe != 5
                  && Char.myCharz().taskMaint != null
                  && Char.myCharz().taskMaint.taskId >= 14) {
                  playerMenu.addElement(new Command(mResources.CHAR_ORDER[0], 2003));
               }

               if (Char.myCharz().clan != null
                  && Char.myCharz().clan.ID == Char.myCharz().charFocus.clanID
                  && Char.myCharz().charFocus.statusMe != 14
                  && Char.myCharz().taskMaint != null
                  && Char.myCharz().taskMaint.taskId >= 14) {
                  playerMenu.addElement(new Command(mResources.CHAR_ORDER[1], 2004));
               }

               int skillTemplateCount = Char.myCharz().nClass.skillTemplates.length;

               for (int skillTemplateIndex = 0; skillTemplateIndex < skillTemplateCount; skillTemplateIndex++) {
                  SkillTemplate skillTemplate = Char.myCharz().nClass.skillTemplates[skillTemplateIndex];
                  Skill p;
                  if ((p = Char.myCharz().getSkill(skillTemplate)) != null && skillTemplate.isBuffToPlayer() && p.point > 0) {
                     playerMenu.addElement(new Command(skillTemplate.name, 12004, p));
                  }
               }
            }
         }
      }
   }

   public static boolean isMeCanAttackMob(Mob m) {
      if (m == null) {
         return false;
      } else if (Char.myCharz().cTypePk == 5) {
         return true;
      } else if (Char.myCharz().isAttacPlayerStatus() && !m.isMobMe) {
         return false;
      } else if (Char.myCharz().mobMe != null && m.equals(Char.myCharz().mobMe)) {
         return false;
      } else {
         Char cAtt;
         if ((cAtt = findCharInMap(m.mobId)) == null) {
            return true;
         } else {
            return cAtt.cTypePk == 5 ? true : Char.myCharz().isMeCanAttackOtherPlayer(cAtt);
         }
      }
   }

   private boolean checkSkillValid() {
      if (Char.myCharz().myskill == null
         || (
               Char.myCharz().myskill.template.manaUseType == 1
                  || Char.myCharz().cMP >= Char.myCharz().myskill.manaUse
            )
            && (
               Char.myCharz().myskill.template.manaUseType != 1
                  || Char.myCharz().cMP
                     >= Char.myCharz().cMPFull * Char.myCharz().myskill.manaUse / 100L
            )) {
         if (Char.myCharz().myskill != null
            && (
               Char.myCharz().myskill.template.maxPoint <= 0
                  || Char.myCharz().myskill.point != 0
            )) {
            return true;
         }

         GameCanvas.startOKDlg(mResources.SKILL_FAIL);
         return false;
      } else {
         info1.addInfo(mResources.NOT_ENOUGH_MP, 0);
         this.auto = 0;
         return false;
      }
   }

   public final void resetButton() {
      GameCanvas.menu.showMenu = false;
      ChatTextField chatTextField;
      (chatTextField = ChatTextField.gI()).tfChat.setText("");
      chatTextField.isShow = false;
      ChatTextField.gI().center = null;
      this.isLockKey = false;
      this.typeTrade = 0;
      indexMenu = 0;
      indexSelect = 0;
      this.indexItemUse = -1;
      indexRow = -1;
      this.typeTrade = this.typeTradeOrder = 0;
      mSystem.endKey();
      if (Char.myCharz().cHP > 0L && Char.myCharz().statusMe != 14 && Char.myCharz().statusMe != 5) {
         isHaveSelectSkill = true;
      } else {
         if (Char.myCharz().meDead) {
            this.cmdDead = new Command(mResources.DIES[0], 11038);
            super.center = this.cmdDead;
            Char.myCharz().cHP = 0L;
         }

         isHaveSelectSkill = false;
      }

      Scroll scrMainValue = scrMain;
      scrMain.cmtoX = 0;
      scrMainValue.cmtoY = 0;
      scrMainValue.cmx = 0;
      scrMainValue.cmy = 0;
      scrMainValue.cmvx = 0;
      scrMainValue.cmvy = 0;
      scrMainValue.cmdx = 0;
      scrMainValue.cmdy = 0;
      scrMainValue.cmxLim = 0;
      scrMainValue.cmyLim = 0;
      scrMainValue.width = 0;
      scrMainValue.height = 0;
   }

   public final void keyPress(int keyCode) {
      super.keyPress(keyCode);
   }

   public final void updateKey() {
      if (!Controller.isStopReadMessage && !Char.myCharz().isTeleport && !Char.myCharz().isPaintNewSkill) {
         if (!InfoDlg.isLock) {
            if (GameCanvas.isTouch && !ChatTextField.gI().isShow && !GameCanvas.menu.showMenu) {
               GameScr gameScreen = this;
               label1090:
               if (!this.isNotPaintTouchControl()) {
                  mScreen.keyTouch = -1;
                  if (GameCanvas.isTouchControl) {
                     if (GameCanvas.isPointerHoldIn(0, 0, 60, 50) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                        if (Char.myCharz().cmdMenu != null) {
                           Char.myCharz().cmdMenu.performAction();
                        }

                        Char.myCharz().currentMovePoint = null;
                        GameCanvas.clearAllPointerEvent();
                        gameScreen.flareFindFocus = true;
                        gameScreen.flareTime = 5;
                        break label1090;
                     }

                     if (!TileMap.isOfflineMap() && GameCanvas.isPointerHoldIn(xC, yC, 34, 34)) {
                        mScreen.keyTouch = 15;
                        GameCanvas.isPointerJustDown = false;
                        gameScreen.isPointerDowning = false;
                        ChatTextField.gI().strChat = mResources.CHAT;
                        if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                           ChatTextField.gI().startChat("");
                           SoundMn.gI();
                           SoundMn.buttonClick();
                           Char.myCharz().currentMovePoint = null;
                           GameCanvas.clearAllPointerEvent();
                           break label1090;
                        }
                     }

                     if (Char.myCharz().cmdMenu != null
                        && GameCanvas.isPointerHoldIn(
                           Char.myCharz().cmdMenu.x - 17,
                           Char.myCharz().cmdMenu.y - 17,
                           34,
                           34
                        )) {
                        mScreen.keyTouch = 20;
                        GameCanvas.isPointerJustDown = false;
                        gameScreen.isPointerDowning = false;
                        if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                           GameCanvas.clearAllPointerEvent();
                           Char.myCharz().cmdMenu.performAction();
                           break label1090;
                        }
                     }

                     GameScr gameScreen2 = gameScreen;
                     if (isAnalog != 0 && Char.myCharz().statusMe != 14) {
                        if (GameCanvas.isPointerHoldIn(xF, yF, 40, 40)) {
                           mScreen.keyTouch = 5;
                           if (GameCanvas.isPointerJustRelease) {
                              GameCanvas.keyPressed[5] = true;
                              GameCanvas.isPointerJustRelease = false;
                              GameCanvas.isPointerJustDown = false;
                              GameCanvas.isPointerClick = false;
                           }
                        }

                        gamePad.update();
                        if (GameCanvas.isPointerHoldIn(xTG, yTG, 34, 34)) {
                           mScreen.keyTouch = 13;
                           GameCanvas.isPointerJustDown = false;
                           gameScreen2.isPointerDowning = false;
                           if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                              Char.myCharz().findNextFocusByKey();
                              GameCanvas.isPointerJustRelease = false;
                              GameCanvas.isPointerJustDown = false;
                              GameCanvas.isPointerClick = false;
                           }
                        }
                     }

                     if ((
                           isAnalog == 0
                              ? GameCanvas.isPointerHoldIn(xHP, yHP + 10, 40, 40)
                              : GameCanvas.isPointerHoldIn(xHP, yHP + 10, 34, 34)
                        )
                        && Char.myCharz().statusMe != 14
                        && gameScreen.mobCapcha == null) {
                        mScreen.keyTouch = 10;
                        GameCanvas.isPointerJustDown = false;
                        gameScreen.isPointerDowning = false;
                        if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                           GameCanvas.keyPressed[10] = true;
                           GameCanvas.isPointerJustRelease = false;
                           GameCanvas.isPointerJustDown = false;
                           GameCanvas.isPointerClick = false;
                        }
                     }

                     if ((
                           isAnalog == 0
                              ? GameCanvas.isPointerHoldIn(xHP + 5, yHP - 6 - 40 + 10, 40, 40)
                              : GameCanvas.isPointerHoldIn(xHP + 5, yHP - 6 - 34 + 10, 34, 34)
                        )
                        && Char.myCharz().statusMe != 14
                        && gameScreen.mobCapcha == null) {
                        if (isPickNgocRong) {
                           mScreen.keyTouch = 14;
                           GameCanvas.isPointerJustDown = false;
                           gameScreen.isPointerDowning = false;
                           if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                              GameCanvas.keyPressed[14] = true;
                              GameCanvas.isPointerJustRelease = false;
                              GameCanvas.isPointerJustDown = false;
                              GameCanvas.isPointerClick = false;
                              isPickNgocRong = false;
                              Service.gI().useItem((byte)-1, (byte)-1, (byte)-1, (short)-1);
                           }
                        } else if (isudungCapsun4) {
                           mScreen.keyTouch = 14;
                           GameCanvas.isPointerJustDown = false;
                           gameScreen.isPointerDowning = false;
                           if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                              GameCanvas.keyPressed[14] = true;
                              GameCanvas.isPointerJustRelease = false;
                              GameCanvas.isPointerJustDown = false;
                              GameCanvas.isPointerClick = false;

                              for (int itemBagIndex = 0; itemBagIndex < Char.myCharz().arrItemBag.length; itemBagIndex++) {
                                 Item itemBag;
                                 if ((itemBag = Char.myCharz().arrItemBag[itemBagIndex]) != null) {
                                    Res.err("find " + itemBag.template.id);
                                    if (itemBag.template.id == 194 && (isudungCapsun4 = itemBag.quantity > 0)) {
                                       Service.gI().useItem((byte)0, (byte)1, (byte)itemBagIndex, (short)-1);
                                       break;
                                    }
                                 }
                              }
                           }
                        } else if (isudungCapsun3) {
                           mScreen.keyTouch = 14;
                           GameCanvas.isPointerJustDown = false;
                           gameScreen.isPointerDowning = false;
                           if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                              GameCanvas.keyPressed[14] = true;
                              GameCanvas.isPointerJustRelease = false;
                              GameCanvas.isPointerJustDown = false;
                              GameCanvas.isPointerClick = false;

                              for (int itemBagIndex2 = 0; itemBagIndex2 < Char.myCharz().arrItemBag.length; itemBagIndex2++) {
                                 Item itemBag2;
                                 if ((itemBag2 = Char.myCharz().arrItemBag[itemBagIndex2]) != null
                                    && itemBag2.template.id == 193
                                    && (isudungCapsun3 = itemBag2.quantity > 0)) {
                                    Service.gI().useItem((byte)0, (byte)1, (byte)itemBagIndex2, (short)-1);
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (gameScreen.mobCapcha != null) {
                     gameScreen.updateKeyTouchCapcha();
                  } else if (isHaveSelectSkill) {
                     if (gameScreen.isCharging()) {
                        break label1090;
                     }

                     gameScreen.keyTouchSkill = -1;
                     boolean condition = false;
                     if (onScreenSkill.length > 5
                        && (
                           GameCanvas.isPointerHoldIn(
                                 xSkill + xS[0] - wSkill / 2 + 12,
                                 yS[0] - wSkill / 2 + 12,
                                 5 * wSkill,
                                 wSkill
                              )
                              || GameCanvas.isPointerHoldIn(
                                 xSkill + xS[5] - wSkill / 2 + 12,
                                 yS[5] - wSkill / 2 + 12,
                                 5 * wSkill,
                                 wSkill
                              )
                        )) {
                        condition = true;
                     }

                     if (condition
                        || GameCanvas.isPointerHoldIn(
                           xSkill + xS[0] - wSkill / 2 + 12,
                           yS[0] - wSkill / 2 + 12,
                           5 * wSkill,
                           wSkill
                        )
                        || !GameCanvas.isTouchControl
                           && GameCanvas.isPointerHoldIn(
                              xSkill + xS[0] - wSkill / 2 + 12,
                              yS[0] - wSkill / 2 + 12,
                              wSkill,
                              onScreenSkill.length * wSkill
                           )) {
                        GameCanvas.isPointerJustDown = false;
                        gameScreen.isPointerDowning = false;
                        int keyTouchSkillValue = (GameCanvas.pxLast - (xSkill + xS[0] - wSkill / 2 + 12)) / wSkill;
                        if (condition && GameCanvas.pyLast < yS[0]) {
                           keyTouchSkillValue += 5;
                        }

                        gameScreen.keyTouchSkill = keyTouchSkillValue;
                        if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                           GameCanvas.isPointerJustRelease = false;
                           GameCanvas.isPointerJustDown = false;
                           GameCanvas.isPointerClick = false;
                           gameScreen.selectedIndexSkill = keyTouchSkillValue;
                           if (gameScreen.selectedIndexSkill > onScreenSkill.length - 1) {
                              gameScreen.selectedIndexSkill = onScreenSkill.length - 1;
                           }

                           Skill skill;
                           if ((skill = onScreenSkill[gameScreen.selectedIndexSkill]) != null) {
                              gameScreen.doSelectSkill(skill, true);
                           }
                        }
                     }
                  }

                  if (GameCanvas.isPointerJustRelease) {
                     if (GameCanvas.keyHold[1]
                        || GameCanvas.keyHold[2]
                        || GameCanvas.keyHold[3]
                        || GameCanvas.keyHold[4]
                        || GameCanvas.keyHold[6]) {
                        GameCanvas.isPointerJustRelease = false;
                     }

                     GameCanvas.keyHold[1] = false;
                     GameCanvas.keyHold[2] = false;
                     GameCanvas.keyHold[3] = false;
                     GameCanvas.keyHold[4] = false;
                     GameCanvas.keyHold[6] = false;
                  }
               }
            }

            GameScr gameScreen3 = this;
            long lastFireValue = System.currentTimeMillis();
            if (GameCanvas.keyPressed[2]
               || GameCanvas.keyPressed[4]
               || GameCanvas.keyPressed[6]
               || GameCanvas.keyPressed[1]
               || GameCanvas.keyPressed[3]) {
               gameScreen3.auto = 0;
               isAutoPlay = false;
            }

            if (GameCanvas.keyPressed[5]) {
               if (gameScreen3.auto == 0) {
                  if (lastFireValue - gameScreen3.lastFire < 800L
                     && (
                        Char.myCharz().myskill == null
                              || (
                                    Char.myCharz().myskill.template.manaUseType == 1
                                       || Char.myCharz().cMP >= Char.myCharz().myskill.manaUse
                                 )
                                 && (
                                    Char.myCharz().myskill.template.manaUseType != 1
                                       || Char.myCharz().cMP
                                          >= Char.myCharz().cMPFull * Char.myCharz().myskill.manaUse / 100L
                                 )
                           ? Char.myCharz().myskill != null
                              && (
                                 Char.myCharz().myskill.template.maxPoint <= 0
                                    || Char.myCharz().myskill.point != 0
                              )
                           : false
                     )
                     && (
                        Char.myCharz().mobFocus != null
                           || Char.myCharz().charFocus != null
                              && Char.myCharz().isMeCanAttackOtherPlayer(Char.myCharz().charFocus)
                     )) {
                     gameScreen3.auto = 10;
                     GameCanvas.keyPressed[5] = false;
                  }
               } else {
                  gameScreen3.auto = 0;
                  GameCanvas.keyPressed[4] = GameCanvas.keyPressed[6] = false;
               }

               gameScreen3.lastFire = lastFireValue;
            }

            label959: {
               if (GameCanvas.gameTick % 5 == 0 && gameScreen3.auto > 0 && Char.myCharz().currentMovePoint == null) {
                  if (Char.myCharz().myskill != null
                     && (
                        Char.myCharz().myskill.template.isUseAlone()
                           || Char.myCharz().myskill.paintCanNotUseSkill
                     )) {
                     break label959;
                  }

                  if (Char.myCharz().mobFocus != null
                        && Char.myCharz().mobFocus.status != 1
                        && Char.myCharz().mobFocus.status != 0
                        && Char.myCharz().charFocus == null
                     || Char.myCharz().charFocus != null
                        && Char.myCharz().isMeCanAttackOtherPlayer(Char.myCharz().charFocus)) {
                     if (Char.myCharz().myskill.paintCanNotUseSkill) {
                        break label959;
                     }

                     gameScreen3.doFire(false, true);
                  }
               }

               if (gameScreen3.auto > 1) {
                  gameScreen3.auto--;
               }
            }

            GameCanvas.debug();
            if (ChatPopup.currChatPopup != null) {
               Command cmd = ChatPopup.currChatPopup.cmdNextLine;
               if ((GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(cmd)) && cmd != null) {
                  GameCanvas.isPointerJustRelease = false;
                  GameCanvas.keyPressed[5] = false;
                  mScreen.keyTouch = -1;
                  if (cmd != null) {
                     cmd.performAction();
                  }
               }
            } else if (!ChatTextField.gI().isShow) {
               if ((GameCanvas.keyPressed[12] || mScreen.getCmdPointerLast(GameCanvas.currentScreen.left))
                  && super.left != null) {
                  GameCanvas.isPointerJustRelease = false;
                  GameCanvas.isPointerClick = false;
                  GameCanvas.keyPressed[12] = false;
                  mScreen.keyTouch = -1;
                  if (super.left != null) {
                     super.left.performAction();
                  }
               }

               if ((GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(GameCanvas.currentScreen.right))
                  && super.right != null) {
                  GameCanvas.isPointerJustRelease = false;
                  GameCanvas.isPointerClick = false;
                  GameCanvas.keyPressed[13] = false;
                  mScreen.keyTouch = -1;
                  if (super.right != null) {
                     super.right.performAction();
                  }
               }

               if ((GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(GameCanvas.currentScreen.center))
                  && super.center != null) {
                  GameCanvas.isPointerJustRelease = false;
                  GameCanvas.keyPressed[5] = false;
                  mScreen.keyTouch = -1;
                  if (super.center != null) {
                     super.center.performAction();
                  }
               }
            } else {
               if (ChatTextField.gI().left != null
                  && (GameCanvas.keyPressed[12] || mScreen.getCmdPointerLast(ChatTextField.gI().left))
                  && ChatTextField.gI().left != null) {
                  ChatTextField.gI().left.performAction();
               }

               if (ChatTextField.gI().right != null
                  && (GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(ChatTextField.gI().right))
                  && ChatTextField.gI().right != null) {
                  ChatTextField.gI().right.performAction();
               }

               if (ChatTextField.gI().center != null
                  && (GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(ChatTextField.gI().center))
                  && ChatTextField.gI().center != null) {
                  ChatTextField.gI().center.performAction();
               }
            }

            GameCanvas.debug();
            GameCanvas.debug();
            if (Char.myCharz().currentMovePoint != null) {
               for (int keyPressedIndex = 0; keyPressedIndex < GameCanvas.keyPressed.length; keyPressedIndex++) {
                  if (GameCanvas.keyPressed[keyPressedIndex]) {
                     Char.myCharz().currentMovePoint = null;
                     break;
                  }
               }
            }

            GameCanvas.debug();
            if (ChatTextField.gI().isShow && GameCanvas.keyAsciiPress != 0) {
               ChatTextField.gI().keyPressed(GameCanvas.keyAsciiPress);
               GameCanvas.keyAsciiPress = 0;
            } else if (!GameCanvas.menu.showMenu && !Char.isLockKey) {
               if (GameCanvas.keyPressed[10]) {
                  GameCanvas.keyPressed[10] = false;
                  this.doUseHP();
                  GameCanvas.clearKeyPressed();
               }

               if (GameCanvas.keyPressed[11] && this.mobCapcha == null) {
                  if (this.popUpYesNo != null) {
                     this.popUpYesNo.cmdYes.performAction();
                  } else if (info2.info.info != null
                     && info2.info.info.charInfo != null) {
                     GameCanvas.panel.setTypeMessage();
                     GameCanvas.panel.show();
                  }

                  GameCanvas.keyPressed[11] = false;
                  GameCanvas.clearKeyPressed();
               }

               if (GameCanvas.keyAsciiPress != 0 && TField.isQwerty && GameCanvas.keyAsciiPress == 32) {
                  this.doUseHP();
                  GameCanvas.keyAsciiPress = 0;
                  GameCanvas.clearKeyPressed();
               }

               if (GameCanvas.keyAsciiPress != 0 && this.mobCapcha == null && TField.isQwerty && GameCanvas.keyAsciiPress == 121) {
                  if (this.popUpYesNo != null) {
                     this.popUpYesNo.cmdYes.performAction();
                     GameCanvas.keyAsciiPress = 0;
                     GameCanvas.clearKeyPressed();
                  } else if (info2.info.info != null
                     && info2.info.info.charInfo != null) {
                     GameCanvas.panel.setTypeMessage();
                     GameCanvas.panel.show();
                     GameCanvas.keyAsciiPress = 0;
                     GameCanvas.clearKeyPressed();
                  }
               }

               if (GameCanvas.keyPressed[10] && this.mobCapcha == null) {
                  GameCanvas.keyPressed[10] = false;
                  byte byteValue = 10;
                  InfoMe info2Value = info2;
                  info2.timeDelay = 10;
                  GameCanvas.clearKeyPressed();
               }

               gameScreen3 = this;
               if (!gamePad.disableCheckDrag() && isAnalog != 1) {
                  Char.myCharz().cmtoChar = true;
                  if (GameCanvas.isPointerJustDown) {
                     GameCanvas.isPointerJustDown = false;
                     gameScreen3.isPointerDowning = true;
                     gameScreen3.ptDownTime = 0;
                     gameScreen3.ptLastDownX = gameScreen3.ptFirstDownX = GameCanvas.px;
                     gameScreen3.ptLastDownY = gameScreen3.ptFirstDownY = GameCanvas.py;
                  }

                  if (gameScreen3.isPointerDowning) {
                     int cmxValue = GameCanvas.px - gameScreen3.ptLastDownX;
                     int cmyValue = GameCanvas.py - gameScreen3.ptLastDownY;
                     if (!gameScreen3.isChangingCameraMode
                        && (
                           Res.abs(GameCanvas.px - gameScreen3.ptFirstDownX) > 15
                              || Res.abs(GameCanvas.py - gameScreen3.ptFirstDownY) > 15
                        )) {
                        gameScreen3.isChangingCameraMode = true;
                     }

                     gameScreen3.ptLastDownX = GameCanvas.px;
                     gameScreen3.ptLastDownY = GameCanvas.py;
                     gameScreen3.ptDownTime++;
                     if (gameScreen3.isChangingCameraMode) {
                        Char.myCharz().cmtoChar = false;
                        cmx -= cmxValue;
                        cmy -= cmyValue;
                        int value;
                        if (cmx < 24 && (value = (24 - cmx) / 3) != 0) {
                           cmx += cmxValue - cmxValue / value;
                        }

                        if (cmx < 24) {
                           cmx = 24;
                        }

                        if (cmx > cmxLim && (value = (cmx - cmxLim) / 3) != 0) {
                           cmx += cmxValue - cmxValue / value;
                        }

                        if (cmx > cmxLim) {
                           cmx = cmxLim;
                        }

                        if (cmy < 0 && (value = (0 - cmy) / 3) != 0) {
                           cmy += cmyValue - cmyValue / value;
                        }

                        if (cmy < 0) {
                           cmy = 0;
                        }

                        if (cmy > cmyLim) {
                           cmy = cmyLim;
                        }

                        cmtoX = cmx;
                        cmtoY = cmy;
                     }
                  }

                  if (gameScreen3.isPointerDowning && GameCanvas.isPointerJustRelease) {
                     gameScreen3.isPointerDowning = false;
                     gameScreen3.isChangingCameraMode = false;
                     if (Res.abs(GameCanvas.px - gameScreen3.ptFirstDownX) > 15
                        || Res.abs(GameCanvas.py - gameScreen3.ptFirstDownY) > 15) {
                        GameCanvas.isPointerJustRelease = false;
                     }
                  }
               }

               if (!Char.myCharz().isFlyAndCharge) {
                  gameScreen3 = this;
                  if (!this.isCharging()) {
                     if (gameScreen3.popUpYesNo != null
                        && gameScreen3.popUpYesNo.cmdYes != null
                        && gameScreen3.popUpYesNo.cmdYes.isPointerPressInside()) {
                        gameScreen3.popUpYesNo.cmdYes.performAction();
                     } else {
                        boolean condition2;
                        label847: {
                           if (gameScreen3.mobCapcha != null) {
                              int x = (GameCanvas.w - 5 * disXC) / 2;
                              int w = 5 * disXC;
                              int y = GameCanvas.h - 40;
                              int h = disXC;
                              if (GameCanvas.isPointerHoldIn(x, y, w, h)) {
                                 condition2 = true;
                                 break label847;
                              }
                           }

                           condition2 = false;
                        }

                        if (!condition2) {
                           lastFireValue = System.currentTimeMillis();
                           if (gameScreen3.lastSingleClick != 0L) {
                              gameScreen3.lastSingleClick = 0L;
                              GameCanvas.isPointerJustDown = false;
                              if (!gameScreen3.disableSingleClick) {
                                 GameScr gameScreen4 = gameScreen3;
                                 int clickToXValue = GameCanvas.px + gameScreen4.lastClickCMX;
                                 int clickToYValue = GameCanvas.py + gameScreen4.lastClickCMY;
                                 if (!gameScreen4.checkClickToPopup(clickToXValue, clickToYValue) && !gameScreen4.checkClipTopChatPopUp(clickToXValue, clickToYValue)) {
                                    gameScreen4.checkClickMoveTo(clickToXValue, clickToYValue);
                                 }

                                 GameCanvas.isPointerJustRelease = false;
                                 gameScreen3.isWaitingDoubleClick = true;
                                 gameScreen3.timeStartDblClick = mSystem.currentTimeMillis();
                              }
                           }

                           if (gameScreen3.isWaitingDoubleClick) {
                              gameScreen3.timeEndDblClick = mSystem.currentTimeMillis();
                              label814:
                              if (gameScreen3.timeEndDblClick - gameScreen3.timeStartDblClick < 300L && GameCanvas.isPointerJustRelease) {
                                 gameScreen3.isWaitingDoubleClick = false;
                                 GameScr gameScreen5 = gameScreen3;
                                 int clickToXValue2 = GameCanvas.px + gameScreen5.lastClickCMX;
                                 int clickToYValue2 = GameCanvas.py + gameScreen5.lastClickCMY;
                                 IMapObject m;
                                 if ((m = gameScreen5.findClickToItem(clickToXValue2, clickToYValue2)) != null) {
                                    if (!(m instanceof Mob) || isMeCanAttackMob((Mob)m)) {
                                       if (gameScreen5.checkClickToBotton(m)
                                          || !m.equals(Char.myCharz().npcFocus) && gameScreen5.mobCapcha != null) {
                                          break label814;
                                       }

                                       Char iMapObject;
                                       if ((
                                             !Char.myCharz().isAttacPlayerStatus()
                                                || Char.myCharz().charFocus == null
                                                || m.equals(Char.myCharz().charFocus)
                                                || m.equals(Char.myCharz().charFocus.mobMe)
                                                || !(m instanceof Char)
                                                || (iMapObject = (Char)m).cTypePk == 5
                                                || iMapObject.isAttacPlayerStatus()
                                          )
                                          && (TileMap.mapID != 51 || !m.equals(Char.myCharz().npcFocus))) {
                                          gameScreen5.doDoubleClickToObj(m);
                                          break label814;
                                       }
                                    }
                                 } else if (gameScreen5.checkClickToPopup(clickToXValue2, clickToYValue2) || gameScreen5.checkClipTopChatPopUp(clickToXValue2, clickToYValue2)) {
                                    break label814;
                                 }

                                 gameScreen5.checkClickMoveTo(clickToXValue2, clickToYValue2);
                              }
                           }

                           if (GameCanvas.isPointerJustRelease) {
                              boolean disableSingleClickValue;
                              label1160: {
                                 GameScr gameScreen6 = gameScreen3;
                                 int clickToXValue3 = GameCanvas.px + cmx;
                                 int clickToYValue3 = GameCanvas.py + cmy;
                                 Char.myCharz();
                                 Char.cancelAttack();
                                 IMapObject object;
                                 if ((object = gameScreen6.findClickToItem(clickToXValue3, clickToYValue3)) != null) {
                                    Char iMapObject2;
                                    if (Char.myCharz().isAttacPlayerStatus()
                                       && Char.myCharz().charFocus != null
                                       && !object.equals(Char.myCharz().charFocus)
                                       && !object.equals(Char.myCharz().charFocus.mobMe)
                                       && object instanceof Char
                                       && (iMapObject2 = (Char)object).cTypePk != 5
                                       && !iMapObject2.isAttacPlayerStatus()) {
                                       gameScreen6.checkClickMoveTo(clickToXValue3, clickToYValue3);
                                    } else {
                                       if (Char.myCharz().mobFocus == object || Char.myCharz().itemFocus == object) {
                                          gameScreen6.doDoubleClickToObj(object);
                                          disableSingleClickValue = true;
                                          break label1160;
                                       }

                                       if (TileMap.mapID == 51 && object.equals(Char.myCharz().npcFocus)) {
                                          gameScreen6.checkClickMoveTo(clickToXValue3, clickToYValue3);
                                       } else {
                                          if (Char.myCharz().skillPaint != null
                                             || Char.myCharz().arr != null
                                             || Char.myCharz().dart != null
                                             || Char.myCharz().skillInfoPaint() != null) {
                                             disableSingleClickValue = false;
                                             break label1160;
                                          }

                                          Char.myCharz().focusManualTo(object);
                                          object.stopMoving();
                                       }
                                    }
                                 }

                                 disableSingleClickValue = false;
                              }

                              gameScreen3.disableSingleClick = disableSingleClickValue;
                              gameScreen3.lastSingleClick = lastFireValue;
                              gameScreen3.lastClickCMX = cmx;
                              gameScreen3.lastClickCMY = cmy;
                              GameCanvas.isPointerJustRelease = false;
                           }
                        }
                     }
                  }
               }

               if (Char.myCharz().cmdMenu != null && Char.myCharz().cmdMenu.isPointerPressInside()) {
                  Char.myCharz().cmdMenu.performAction();
               }

               if (Char.myCharz().skillPaint == null) {
                  if (Char.myCharz().statusMe == 1) {
                     GameCanvas.debug();
                     if (GameCanvas.keyPressed[5]) {
                        GameCanvas.keyPressed[5] = false;
                        this.doFire(false, false);
                     } else if (GameCanvas.keyHold[2]) {
                        if (!Char.myCharz().isLockMove) {
                           boolean condition3 = false;
                           if (Char.myCharz().cx - Char.myCharz().cxSend != 0
                              || Char.myCharz().cy - Char.myCharz().cySend != 0) {
                              Service.gI().charMove();
                           }

                           Char.myCharz().cvy = -10;
                           Char.myCharz().cvx = 0;
                           Char.myCharz().statusMe = 3;
                           Char.myCharz().cp1 = 0;
                        }
                     } else if (GameCanvas.keyHold[4]) {
                        isAutoPlay = false;
                        Char.myCharz().isAttack = false;
                        if (Char.myCharz().cdir == 1) {
                           Char.myCharz().cdir = -1;
                        } else if (!Char.myCharz().isLockMove) {
                           if (Char.myCharz().cx - Char.myCharz().cxSend != 0) {
                              Service.gI().charMove();
                           }

                           Char.myCharz().statusMe = 2;
                           Char.myCharz().cvx = -Char.myCharz().cspeed;
                        }

                        Char.myCharz().holder = false;
                     } else if (GameCanvas.keyHold[6]) {
                        isAutoPlay = false;
                        Char.myCharz().isAttack = false;
                        if (Char.myCharz().cdir == -1) {
                           Char.myCharz().cdir = 1;
                        } else if (!Char.myCharz().isLockMove) {
                           if (Char.myCharz().cx - Char.myCharz().cxSend != 0) {
                              Service.gI().charMove();
                           }

                           Char.myCharz().statusMe = 2;
                           Char.myCharz().cvx = Char.myCharz().cspeed;
                        }

                        Char.myCharz().holder = false;
                     }
                  } else if (Char.myCharz().statusMe == 2) {
                     GameCanvas.debug();
                     if (GameCanvas.keyPressed[5]) {
                        GameCanvas.keyPressed[5] = false;
                        this.doFire(false, true);
                     } else if (GameCanvas.keyHold[2]) {
                        if (Char.myCharz().cx - Char.myCharz().cxSend != 0
                           || Char.myCharz().cy - Char.myCharz().cySend != 0) {
                           Service.gI().charMove();
                        }

                        Char.myCharz().cvy = -10;
                        Char.myCharz().statusMe = 3;
                        Char.myCharz().cp1 = 0;
                     } else if (GameCanvas.keyHold[4]) {
                        isAutoPlay = false;
                        if (Char.myCharz().cdir == 1) {
                           Char.myCharz().cdir = -1;
                        } else {
                           Char player = Char.myCharz();
                           int cvx = -Char.myCharz().cspeed;
                           Char.myCharz();
                           player.cvx = cvx;
                        }
                     } else if (GameCanvas.keyHold[6]) {
                        isAutoPlay = false;
                        if (Char.myCharz().cdir == -1) {
                           Char.myCharz().cdir = 1;
                        } else {
                           Char player2 = Char.myCharz();
                           int cvx2 = Char.myCharz().cspeed;
                           Char.myCharz();
                           player2.cvx = cvx2;
                        }
                     }
                  } else if (Char.myCharz().statusMe == 3) {
                     isAutoPlay = false;
                     GameCanvas.debug();
                     if (GameCanvas.keyPressed[5]) {
                        GameCanvas.keyPressed[5] = false;
                        this.doFire(false, true);
                     }

                     if (!GameCanvas.keyHold[4] && (!GameCanvas.keyHold[1] || this.mobCapcha != null)) {
                        if (GameCanvas.keyHold[6] || GameCanvas.keyHold[3] && this.mobCapcha == null) {
                           if (Char.myCharz().cdir == -1) {
                              Char.myCharz().cdir = 1;
                           } else {
                              Char.myCharz().cvx = Char.myCharz().cspeed;
                           }
                        }
                     } else if (Char.myCharz().cdir == 1) {
                        Char.myCharz().cdir = -1;
                     } else {
                        Char.myCharz().cvx = -Char.myCharz().cspeed;
                     }

                     if ((
                           GameCanvas.keyHold[2]
                              || (GameCanvas.keyHold[1] || GameCanvas.keyHold[3]) && this.mobCapcha == null
                        )
                        && Char.myCharz().canFly
                        && Char.myCharz().cMP > 0L
                        && Char.myCharz().cp1 < 8
                        && Char.myCharz().cvy > -4) {
                        Char.myCharz().cp1++;
                        Char.myCharz().cvy = -7;
                     }
                  } else if (Char.myCharz().statusMe == 4) {
                     GameCanvas.debug();
                     if (GameCanvas.keyPressed[5]) {
                        GameCanvas.keyPressed[5] = false;
                        this.doFire(false, true);
                     }

                     if (GameCanvas.keyHold[2] && Char.myCharz().cMP > 0L && Char.myCharz().canFly) {
                        isAutoPlay = false;
                        if ((
                              Char.myCharz().cx - Char.myCharz().cxSend != 0
                                 || Char.myCharz().cy - Char.myCharz().cySend != 0
                           )
                           && (
                              Res.abs(Char.myCharz().cx - Char.myCharz().cxSend) > 96
                                 || Res.abs(Char.myCharz().cy - Char.myCharz().cySend) > 24
                           )) {
                           Service.gI().charMove();
                        }

                        Char.myCharz().cvy = -10;
                        Char.myCharz().statusMe = 3;
                        Char.myCharz().cp1 = 0;
                     }

                     if (GameCanvas.keyHold[4]) {
                        isAutoPlay = false;
                        if (Char.myCharz().cdir == 1) {
                           Char.myCharz().cdir = -1;
                        } else {
                           Char.myCharz().cp1++;
                           Char.myCharz().cvx = -Char.myCharz().cspeed;
                           if (Char.myCharz().cp1 > 5 && Char.myCharz().cvy > 6) {
                              Char.myCharz().statusMe = 10;
                              Char.myCharz().cp1 = 0;
                              Char.myCharz().cvy = 0;
                           }
                        }
                     } else if (GameCanvas.keyHold[6]) {
                        isAutoPlay = false;
                        if (Char.myCharz().cdir == -1) {
                           Char.myCharz().cdir = 1;
                        } else {
                           Char.myCharz().cp1++;
                           Char.myCharz().cvx = Char.myCharz().cspeed;
                           if (Char.myCharz().cp1 > 5 && Char.myCharz().cvy > 6) {
                              Char.myCharz().statusMe = 10;
                              Char.myCharz().cp1 = 0;
                              Char.myCharz().cvy = 0;
                           }
                        }
                     }
                  } else if (Char.myCharz().statusMe == 10) {
                     GameCanvas.debug();
                     if (GameCanvas.keyPressed[5]) {
                        GameCanvas.keyPressed[5] = false;
                        this.doFire(false, true);
                     }

                     if (Char.myCharz().canFly && Char.myCharz().cMP > 0L) {
                        if (GameCanvas.keyHold[2]) {
                           isAutoPlay = false;
                           if ((
                                 Char.myCharz().cx - Char.myCharz().cxSend != 0
                                    || Char.myCharz().cy - Char.myCharz().cySend != 0
                              )
                              && (
                                 Res.abs(Char.myCharz().cx - Char.myCharz().cxSend) > 96
                                    || Res.abs(Char.myCharz().cy - Char.myCharz().cySend)
                                       > 24
                              )) {
                              Service.gI().charMove();
                           }

                           Char.myCharz().cvy = -10;
                           Char.myCharz().statusMe = 3;
                           Char.myCharz().cp1 = 0;
                        } else if (GameCanvas.keyHold[4]) {
                           isAutoPlay = false;
                           if (Char.myCharz().cdir == 1) {
                              Char.myCharz().cdir = -1;
                           } else {
                              Char.myCharz().cvx = -(Char.myCharz().cspeed + 1);
                           }
                        } else if (GameCanvas.keyHold[6]) {
                           if (Char.myCharz().cdir == -1) {
                              Char.myCharz().cdir = 1;
                           } else {
                              Char.myCharz().cvx = Char.myCharz().cspeed + 1;
                           }
                        }
                     }
                  } else if (Char.myCharz().statusMe == 7) {
                     GameCanvas.debug();
                     if (GameCanvas.keyPressed[5]) {
                        GameCanvas.keyPressed[5] = false;
                     }

                     if (GameCanvas.keyHold[4]) {
                        isAutoPlay = false;
                        if (Char.myCharz().cdir == 1) {
                           Char.myCharz().cdir = -1;
                        } else {
                           Char.myCharz().cvx = -Char.myCharz().cspeed + 2;
                        }
                     } else if (GameCanvas.keyHold[6]) {
                        isAutoPlay = false;
                        if (Char.myCharz().cdir == -1) {
                           Char.myCharz().cdir = 1;
                        } else {
                           Char.myCharz().cvx = Char.myCharz().cspeed - 2;
                        }
                     }
                  }

                  GameCanvas.debug();
                  if (GameCanvas.keyPressed[8] && GameCanvas.keyAsciiPress != 56) {
                     GameCanvas.keyPressed[8] = false;
                     Char.myCharz().delayFall = 0;
                  }

                  if (GameCanvas.keyPressed[10]) {
                     GameCanvas.keyPressed[10] = false;
                     this.doUseHP();
                  }

                  GameCanvas.debug();
                  if (GameCanvas.keyAsciiPress != 0) {
                     if (this.mobCapcha == null) {
                        if (TField.isQwerty) {
                           if (GameCanvas.keyAsciiPress == 113) {
                              if (keySkill[0] != null) {
                                 this.doSelectSkill(keySkill[0], true);
                              }
                           } else if (GameCanvas.keyAsciiPress == 119) {
                              if (keySkill[1] != null) {
                                 this.doSelectSkill(keySkill[1], true);
                              }
                           } else if (GameCanvas.keyAsciiPress == 101) {
                              if (keySkill[2] != null) {
                                 this.doSelectSkill(keySkill[2], true);
                              }
                           } else if (GameCanvas.keyAsciiPress == 114) {
                              if (keySkill[3] != null) {
                                 this.doSelectSkill(keySkill[3], true);
                              }
                           } else if (GameCanvas.keyAsciiPress == 116) {
                              if (keySkill[4] != null) {
                                 this.doSelectSkill(keySkill[4], true);
                              }
                           } else if (GameCanvas.keyAsciiPress != 50 && GameCanvas.keyAsciiPress != 53) {
                              ChatTextField.gI().startChat(GameCanvas.keyAsciiPress, this, "");
                           }
                        } else if (!GameCanvas.isMoveNumberPad) {
                           ChatTextField.gI().startChat(GameCanvas.keyAsciiPress, this, "");
                        } else if (GameCanvas.keyAsciiPress == 55) {
                           if (keySkill[0] != null) {
                              this.doSelectSkill(keySkill[0], true);
                           }
                        } else if (GameCanvas.keyAsciiPress == 56) {
                           if (keySkill[1] != null) {
                              this.doSelectSkill(keySkill[1], true);
                           }
                        } else if (GameCanvas.keyAsciiPress == 57) {
                           if (keySkill[2] != null) {
                              this.doSelectSkill(keySkill[2], true);
                           }
                        } else if (GameCanvas.keyAsciiPress == 49) {
                           if (keySkill[3] != null) {
                              this.doSelectSkill(keySkill[3], true);
                           }
                        } else if (GameCanvas.keyAsciiPress == 51) {
                           if (keySkill[4] != null) {
                              this.doSelectSkill(keySkill[4], true);
                           }
                        } else if (GameCanvas.keyAsciiPress == 48) {
                           ChatTextField.gI().startChat("");
                        }
                     } else {
                        char[] charValues = this.keyInput.toCharArray();
                        MyVector entries = new MyVector("");

                        for (int index = 0; index < charValues.length; index++) {
                           entries.addElement(String.valueOf(charValues[index]));
                        }

                        entries.removeElementAt(0);
                        String text;
                        if ((text = String.valueOf((char)GameCanvas.keyAsciiPress)).equals("") || text == null || text.equals("\n")) {
                           text = "-";
                        }

                        entries.insertElementAt(text, entries.size());
                        this.keyInput = "";

                        for (int entryIndex = 0; entryIndex < entries.size(); entryIndex++) {
                           this.keyInput = this.keyInput + ((String)entries.elementAt(entryIndex)).toUpperCase();
                        }

                        Service.gI().mobCapcha((char)GameCanvas.keyAsciiPress);
                     }

                     GameCanvas.keyAsciiPress = 0;
                  }

                  GameCanvas.clearKeyPressed();
                  GameCanvas.debug();
               }
            }
         }
      }
   }

   private IMapObject findClickToItem(int px, int py) {
      IMapObject result = null;
      int value = 0;
      MyVector[] entriess = new MyVector[]{vMob, vNpc, vItemMap, vCharInMap};

      for (int index = 0; index < entriess.length; index++) {
         for (int index2 = 0; index2 < entriess[index].size(); index2++) {
            IMapObject iMapObject;
            Mob mob;
            if (!(iMapObject = (IMapObject)entriess[index].elementAt(index2)).isInvisible()
               && (!(iMapObject instanceof Mob) || !(mob = (Mob)iMapObject).isMobMe || !mob.equals(Char.myCharz().mobMe))) {
               int x = iMapObject.getX();
               int y = iMapObject.getY();
               int w = iMapObject.getW();
               int h = iMapObject.getH();
               if (inRectangle(px, py, x - w / 2 - 30, y - h - 30, w + 60, h + 60)) {
                  if (result == null) {
                     result = iMapObject;
                     value = Res.abs(px - x) + Res.abs(py - y);
                     if (index == 1) {
                        return result;
                     }
                  } else {
                     int value2;
                     if ((value2 = Res.abs(px - x) + Res.abs(py - y)) < value) {
                        result = iMapObject;
                        value = value2;
                     }
                  }
               }
            }
         }
      }

      return result;
   }

   private static boolean inRectangle(int xClick, int yClick, int x, int y, int w, int h) {
      return xClick >= x && xClick <= x + w && yClick >= y && yClick <= y + h;
   }

   private boolean checkClickToBotton(IMapObject Object) {
      if (Object == null) {
         return false;
      }

      int y = Object.getY();
      int py = Char.myCharz().cy;
      if (y < py) {
         while (y < py) {
            py -= 5;
            if (TileMap.tileTypeAt(Char.myCharz().cx, py, 8192)) {
               this.auto = 0;
               Char.myCharz();
               Char.cancelAttack();
               Char.myCharz().currentMovePoint = null;
               return true;
            }
         }
      }

      return false;
   }

   private void doDoubleClickToObj(IMapObject object) {
      if (object.equals(Char.myCharz().npcFocus) || this.mobCapcha == null) {
         if (!this.checkClickToBotton(object)) {
            int value = 0;
            IMapObject object2 = object;
            GameScr gameScreen = this;
            if (object2 != null && gameScreen.doubleClickCooldownTicks <= 0) {
               gameScreen.doubleClickCooldownTicks = 10;
               value = object2.getX();
               byte loopCount;
               if ((value = Res.abs(Char.myCharz().cx - value)) <= 80) {
                  loopCount = 1;
               } else if (value > 80 && value <= 200) {
                  loopCount = 2;
               } else if (value > 200 && value <= 400) {
                  loopCount = 3;
               } else {
                  loopCount = 4;
               }

               if (!object2.equals(Char.myCharz().mobFocus)
                  && (
                     !object2.equals(Char.myCharz().charFocus)
                        || !Char.myCharz().isMeCanAttackOtherPlayer(Char.myCharz().charFocus)
                  )) {
                  if (object2.equals(Char.myCharz().npcFocus)
                     || object2.equals(Char.myCharz().itemFocus)
                     || object2.equals(Char.myCharz().charFocus)) {
                     ServerEffect.addServerEffect(136, object2.getX(), object2.getY(), loopCount);
                  }
               } else {
                  ServerEffect.addServerEffect(135, object2.getX(), object2.getY(), loopCount);
               }
            }

            Char.myCharz();
            Char.cancelAttack();
            Char.myCharz().currentMovePoint = null;
            Char.myCharz().cvx = Char.myCharz().cvy = 0;
            object.stopMoving();
            this.auto = 10;
            this.doFire(false, true);
            this.clickToX = object.getX();
            this.clickToY = object.getY();
            this.clickOnTileTop = false;
            this.clickMoving = true;
         }
      }
   }

   private boolean checkClipTopChatPopUp(int xClick, int yClick) {
      if (this.equals(info2) && gI().popUpYesNo != null) {
         return false;
      }

      if (info2.info.info != null && info2.info.info.charInfo != null) {
         int x = Res.abs(info2.cmx) + info2.info.X - 40;
         int y = Res.abs(info2.cmy) + info2.info.Y;
         if (inRectangle(xClick - cmx, yClick - cmy, x, y, 200, info2.info.H)) {
            byte byteValue = 10;
            InfoMe info2Value = info2;
            info2.timeDelay = 10;
            return true;
         }
      }

      return false;
   }

   private boolean checkClickToPopup(int xClick, int yClick) {
      for (int popupIndex = 0; popupIndex < PopUp.vPopups.size(); popupIndex++) {
         PopUp popup = (PopUp)PopUp.vPopups.elementAt(popupIndex);
         if (inRectangle(xClick, yClick, popup.cx, popup.cy, popup.cw, popup.ch)) {
            if (popup.cy <= 24 && TileMap.isInAirMap() && Char.myCharz().cTypePk != 0) {
               return false;
            }

            if (popup.isPaint) {
               popup.doClick(10);
               return true;
            }
         }
      }

      return false;
   }

   private void checkClickMoveTo(int clickToXValue, int clickToYValue) {
      if (!gamePad.disableClickMove()) {
         Char.myCharz();
         Char.cancelAttack();
         if (clickToXValue < TileMap.pxw && clickToXValue > TileMap.pxw - 32) {
            Char.myCharz().currentMovePoint = new MovePoint(TileMap.pxw, clickToYValue);
         } else if (clickToXValue < 32 && clickToXValue > 0) {
            Char.myCharz().currentMovePoint = new MovePoint(0, clickToYValue);
         } else {
            this.clickToX = clickToXValue;
            this.clickToY = clickToYValue;
            this.clickOnTileTop = false;
            Char.myCharz().delayFall = 0;
            clickToXValue = Char.myCharz().canFly && Char.myCharz().cMP > 0L ? 0 : 1000;
            if (this.clickToY <= Char.myCharz().cy
               || Res.abs(this.clickToX - Char.myCharz().cx) >= 12) {
               for (int value = 0; value < clickToXValue + 60 && this.clickToY + value < TileMap.pxh - 24; value += 24) {
                  if (TileMap.tileTypeAt(this.clickToX, this.clickToY + value, 2)) {
                     this.clickToY = TileMap.tileYofPixel(this.clickToY + value);
                     this.clickOnTileTop = true;
                     break;
                  }
               }

               for (int value2 = 0; value2 < clickToXValue + 40; value2 += 24) {
                  if (TileMap.tileTypeAt(this.clickToX, this.clickToY - value2, 2)) {
                     this.clickToY = TileMap.tileYofPixel(this.clickToY - value2);
                     this.clickOnTileTop = true;
                     break;
                  }
               }

               this.clickMoving = true;
               if (this.clickOnTileTop && clickToYValue >= this.clickToY) {
                  ;
               }

               Char.myCharz().delayFall = 0;
               if (!this.clickOnTileTop && this.clickToY < Char.myCharz().cy - 50) {
                  Char.myCharz().delayFall = 20;
               }

               this.auto = 0;
               if (Char.myCharz().holder) {
                  Char.myCharz().removeHoleEff();
               }

               Char.myCharz().currentMovePoint = new MovePoint(this.clickToX, this.clickToY);
               Char.myCharz().cdir = Char.myCharz().cx
                        - Char.myCharz().currentMovePoint.xEnd
                     > 0
                  ? -1
                  : 1;
               Char.myCharz().endMovePointCommand = null;
               isAutoPlay = false;
            }
         }
      }
   }

   private void doUseHP() {
      if (!Char.myCharz().stone) {
         if (!Char.myCharz().blindEff) {
            if (Char.myCharz().holdEffID <= 0) {
               long lastUsePotionValue;
               if ((lastUsePotionValue = System.currentTimeMillis()) - this.lastUsePotion >= 10000L) {
                  if (!Char.myCharz().doUsePotion()) {
                     info1.addInfo(mResources.HP_EMPTY, 0);
                  } else {
                     ServerEffect.addServerEffect(11, ((nro.Char) (Char.myCharz())), 5);
                     ServerEffect.addServerEffect(104, ((nro.Char) (Char.myCharz())), 4);
                     this.lastUsePotion = lastUsePotionValue;
                     SoundMn.gI();
                     SoundMn.eatPeans();
                  }
               }
            }
         }
      }
   }

   public final void activeSuperPower(int x, int y) {
      if (!this.isSuperPower) {
         SoundMn.gI();
         SoundMn.bigeExlode();
         Res.outz("ACTIVE SUPER POWER");
         this.isSuperPower = true;
         this.tPower = 0;
         this.dxPower = 0;
         this.xPower = x - cmx;
         this.yPower = y - cmy;
      }
   }

   public final void activeRongThanEff(boolean isMe) {
      this.activeRongThan = true;
      this.isUseFreez = true;
      this.isMeCallRongThan = true;
      if (isMe) {
         EffecMn.addEff(new Effect(20, Char.myCharz().cx, Char.myCharz().cy - 77, 2, 8, 1));
      }
   }

   public final void hideRongThanEff() {
      this.activeRongThan = false;
      this.isUseFreez = true;
      this.isMeCallRongThan = false;
   }

   public final void doiMauTroi() {
      this.isRongThanXuatHien = true;
      this.mautroi = mGraphics.blendColor(0.4F, 0, GameCanvas.colorTop[GameCanvas.colorTop.length - 1]);
   }

   public final void callRongThan(int x, int y) {
      Res.outz("VE RONG THAN O VI TRI x= " + x + " y=" + y);
      this.doiMauTroi();
      EffecMn.addEff(new Effect(this.isRongNamek ? 25 : 17, x, y - 77, 2, -1, 1));
   }

   private void doFire(boolean isFireByShortCut, boolean skipWaypoint) {
   int intSlot2;
   if (Char.myCharz().charFocus != null
         && Char.myCharz().charFocus.cy >= Char.myCharz().cy + 48
         && TileMap.tileTypeAt(Char.myCharz().cx, Char.myCharz().cy, 2)) {
         this.auto = 0;
         Char.myCharz().currentMovePoint = null;
         Char.isLockKey = false;
         Char.myCharz().isLockMove = false;
      } else {
         Res.outz("do fire");
         tam++;
         Waypoint waypoint = Char.myCharz().isInEnterOfflinePoint();
         Waypoint waypoint2 = Char.myCharz().isInEnterOnlinePoint();
         if (skipWaypoint
            || waypoint == null
            || Char.myCharz().mobFocus != null
               && (Char.myCharz().mobFocus == null || Char.myCharz().mobFocus.templateId != 0)) {
            if (skipWaypoint
               || waypoint2 == null
               || Char.myCharz().mobFocus != null
                  && (Char.myCharz().mobFocus == null || Char.myCharz().mobFocus.templateId != 0)) {
               if (TileMap.mapID != 51 || Char.myCharz().npcFocus == null) {
                  if (Char.myCharz().statusMe != 14) {
                     Char.myCharz().cvx = Char.myCharz().cvy = 0;
                     if (!Char.myCharz().isSelectingSkillUseAlone() || !Char.myCharz().focusToAttack()) {
                        GameScr gameScreen = this;
                        boolean condition;
                        if (this.checkClickToBotton(Char.myCharz().charFocus)) {
                           condition = false;
                        } else if (gameScreen.checkClickToBotton(Char.myCharz().mobFocus)) {
                           condition = false;
                        } else if (gameScreen.checkClickToBotton(Char.myCharz().npcFocus)) {
                           condition = false;
                        } else if (ChatTextField.gI().isShow) {
                           condition = false;
                        } else {
                           label480: {
                              if (!InfoDlg.isLock) {
                                 Char.myCharz();
                                 if (!Char.isLockKey) {
                                    if (Char.myCharz().myskill != null
                                       && Char.myCharz().myskill.template.id == 6
                                       && Char.myCharz().itemFocus != null) {
                                       gameScreen.pickItem();
                                       condition = false;
                                    } else if (Char.myCharz().myskill != null
                                       && Char.myCharz().myskill.template.type == 2
                                       && Char.myCharz().npcFocus == null
                                       && Char.myCharz().myskill.template.id != 6) {
                                       condition = gameScreen.checkSkillValid();
                                    } else if (Char.myCharz().skillPaint == null
                                       && (
                                          Char.myCharz().mobFocus != null
                                             || Char.myCharz().npcFocus != null
                                             || Char.myCharz().charFocus != null
                                             || Char.myCharz().itemFocus != null
                                       )) {
                                       if (Char.myCharz().mobFocus != null) {
                                          if (Char.myCharz().mobFocus.isBigBoss()
                                             && Char.myCharz().mobFocus.status == 4) {
                                             Char.myCharz().mobFocus = null;
                                             Char.myCharz().currentMovePoint = null;
                                          }

                                          isAutoPlay = true;
                                          if (!isMeCanAttackMob(Char.myCharz().mobFocus)) {
                                             condition = false;
                                             break label480;
                                          }

                                          if (gameScreen.mobCapcha != null) {
                                             condition = false;
                                             break label480;
                                          }

                                          if (Char.myCharz().myskill == null) {
                                             condition = false;
                                             break label480;
                                          }

                                          if (Char.myCharz().isSelectingSkillUseAlone()) {
                                             condition = false;
                                             break label480;
                                          }

                                          int value = -1;
                                          int value2 = Res.abs(Char.myCharz().cx - cmx)
                                             * mGraphics.zoomLevel;
                                          if (Char.myCharz().charFocus != null) {
                                             value = Res.abs(
                                                   Char.myCharz().cx - Char.myCharz().charFocus.cx
                                                )
                                                * mGraphics.zoomLevel;
                                          } else if (Char.myCharz().mobFocus != null) {
                                             value = Res.abs(
                                                   Char.myCharz().cx - Char.myCharz().mobFocus.x
                                                )
                                                * mGraphics.zoomLevel;
                                          }

                                          if ((
                                                Char.myCharz().mobFocus.status == 1
                                                   || Char.myCharz().mobFocus.status == 0
                                                   || Char.myCharz().myskill.template.type == 4
                                                   || value == -1
                                                   || value > value2
                                             )
                                             && Char.myCharz().myskill.template.type == 4) {
                                             if (Char.myCharz().mobFocus.x < Char.myCharz().cx) {
                                                Char.myCharz().cdir = -1;
                                             } else {
                                                Char.myCharz().cdir = 1;
                                             }

                                             gameScreen.doSelectSkill(Char.myCharz().myskill, true);
                                          }

                                          if (!gameScreen.checkSkillValid()) {
                                             condition = false;
                                             break label480;
                                          }

                                          if (Char.myCharz().cx < Char.myCharz().mobFocus.getX()) {
                                             Char.myCharz().cdir = 1;
                                          } else {
                                             Char.myCharz().cdir = -1;
                                          }

                                          intSlot2 = Math.abs(
                                             Char.myCharz().cx - Char.myCharz().mobFocus.getX()
                                          );
                                          int value3 = Math.abs(
                                             Char.myCharz().cy - Char.myCharz().mobFocus.getY()
                                          );
                                          Char.myCharz().cvx = 0;
                                          if (intSlot2 > Char.myCharz().myskill.dx
                                             || value3 > Char.myCharz().myskill.dy) {
                                             boolean condition2 = false;
                                             if (Char.myCharz().mobFocus instanceof BigBoss
                                                || Char.myCharz().mobFocus instanceof BigBoss2) {
                                                condition2 = true;
                                             }

                                             int value4 = (Char.myCharz().myskill.dx - (condition2 ? 50 : 20))
                                                * (
                                                   Char.myCharz().cx > Char.myCharz().mobFocus.getX()
                                                      ? 1
                                                      : -1
                                                );
                                             if (intSlot2 <= Char.myCharz().myskill.dx) {
                                                value4 = 0;
                                             }

                                             Char.myCharz().currentMovePoint = new MovePoint(
                                                Char.myCharz().mobFocus.getX() + value4,
                                                Char.myCharz().mobFocus.getY()
                                             );
                                             Char.myCharz().endMovePointCommand = new Command(null, null, 8002, null);
                                             GameCanvas.clearKeyHold();
                                             GameCanvas.clearKeyPressed();
                                             condition = false;
                                             break label480;
                                          }

                                          if (Char.myCharz().myskill.template.id != 20) {
                                             if (value3 > intSlot2
                                                && Res.abs(
                                                      Char.myCharz().cy
                                                         - Char.myCharz().mobFocus.getY()
                                                   )
                                                   > 30
                                                && Char.myCharz().mobFocus.getTemplate().type == 4) {
                                                Char.myCharz().currentMovePoint = new MovePoint(
                                                   Char.myCharz().cx + Char.myCharz().cdir,
                                                   Char.myCharz().mobFocus.getY()
                                                );
                                                Char.myCharz().endMovePointCommand = new Command(null, null, 8002, null);
                                                GameCanvas.clearKeyHold();
                                                GameCanvas.clearKeyPressed();
                                                condition = false;
                                                break label480;
                                             }

                                             byte byteValue = 20;
                                             boolean condition3 = false;
                                             if (Char.myCharz().mobFocus instanceof BigBoss
                                                || Char.myCharz().mobFocus instanceof BigBoss2) {
                                                condition3 = true;
                                             }

                                             if (Char.myCharz().myskill.dx > 100) {
                                                byteValue = 60;
                                                if (intSlot2 < 20) {
                                                   Char.myCharz()
                                                      .createShadow(
                                                         Char.myCharz().cx, Char.myCharz().cy, 10
                                                      );
                                                }
                                             }

                                             Res.outz("dt= " + byteValue);
                                             boolean condition4 = false;
                                             if ((
                                                   TileMap.tileTypeAtPixel(
                                                         Char.myCharz().cx, Char.myCharz().cy + 3
                                                      )
                                                      & 2
                                                )
                                                == 2) {
                                                int value5 = Char.myCharz().cx
                                                      > Char.myCharz().mobFocus.getX()
                                                   ? 1
                                                   : -1;
                                                if ((
                                                      TileMap.tileTypeAtPixel(
                                                            Char.myCharz().mobFocus.getX() + byteValue * value5,
                                                            Char.myCharz().cy + 3
                                                         )
                                                         & 2
                                                   )
                                                   != 2) {
                                                   condition4 = true;
                                                }
                                             }

                                             if (intSlot2 <= byteValue && !condition4) {
                                                if (Char.myCharz().cx > Char.myCharz().mobFocus.getX()) {
                                                   int cx = Char.myCharz().mobFocus.getX() + byteValue + (condition3 ? 30 : 0);
                                                   int px = Char.myCharz().mobFocus.getX();
                                                   boolean condition5 = false;

                                                   while (px < cx) {
                                                      if (TileMap.tileTypeAtPixel(px, Char.myCharz().cy + 3) == 8
                                                         || TileMap.tileTypeAtPixel(px, Char.myCharz().cy + 3) == 4) {
                                                         condition5 = true;
                                                         break;
                                                      }

                                                      px += 24;
                                                   }

                                                   if (condition5) {
                                                      Char.myCharz().cx = px - 24;
                                                   } else {
                                                      Char.myCharz().cx = cx;
                                                   }

                                                   Char.myCharz().cdir = -1;
                                                } else {
                                                   int cx2 = Char.myCharz().mobFocus.getX() - byteValue - (condition3 ? 30 : 0);
                                                   int px2 = Char.myCharz().mobFocus.getX();
                                                   boolean condition6 = false;

                                                   while (px2 > cx2) {
                                                      if (TileMap.tileTypeAtPixel(px2, Char.myCharz().cy + 3) == 8
                                                         || TileMap.tileTypeAtPixel(px2, Char.myCharz().cy + 3) == 4) {
                                                         condition6 = true;
                                                         break;
                                                      }

                                                      px2 -= 24;
                                                   }

                                                   if (condition6) {
                                                      Char.myCharz().cx = px2 + 24;
                                                   } else {
                                                      Char.myCharz().cx = cx2;
                                                   }

                                                   Char.myCharz().cdir = 1;
                                                }

                                                Service.gI().charMove();
                                             }

                                             GameCanvas.clearKeyHold();
                                             GameCanvas.clearKeyPressed();
                                          }
                                       } else {
                                          if (Char.myCharz().npcFocus != null) {
                                             if (Char.myCharz().npcFocus.isHide) {
                                                condition = false;
                                             } else {
                                                if (Char.myCharz().cx < Char.myCharz().npcFocus.cx) {
                                                   Char.myCharz().cdir = 1;
                                                } else {
                                                   Char.myCharz().cdir = -1;
                                                }

                                                if (Char.myCharz().cx < Char.myCharz().npcFocus.cx) {
                                                   Char.myCharz().npcFocus.cdir = -1;
                                                } else {
                                                   Char.myCharz().npcFocus.cdir = 1;
                                                }

                                                int value6 = Math.abs(
                                                   Char.myCharz().cx - Char.myCharz().npcFocus.cx
                                                );
                                                if (Math.abs(
                                                      Char.myCharz().cy - Char.myCharz().npcFocus.cy
                                                   )
                                                   > 40) {
                                                   Char.myCharz().cy = Char.myCharz().npcFocus.cy - 40;
                                                }

                                                if (value6 < 60) {
                                                   GameCanvas.clearKeyHold();
                                                   GameCanvas.clearKeyPressed();
                                                   if (gameScreen.tMenuDelay == 0) {
                                                      if (Char.myCharz().taskMaint != null
                                                         && Char.myCharz().taskMaint.taskId == 0) {
                                                         if (Char.myCharz().taskMaint.index < 4
                                                            && Char.myCharz().npcFocus.template.npcTemplateId == 4) {
                                                            condition = false;
                                                            break label480;
                                                         }

                                                         if (Char.myCharz().taskMaint.index < 3
                                                            && Char.myCharz().npcFocus.template.npcTemplateId == 3) {
                                                            condition = false;
                                                            break label480;
                                                         }
                                                      }

                                                      gameScreen.tMenuDelay = 50;
                                                      InfoDlg.showWait();
                                                      Service.gI().charMove();
                                                      Service.gI()
                                                         .openMenu(Char.myCharz().npcFocus.template.npcTemplateId);
                                                   }
                                                } else {
                                                   intSlot2 = (20 + Res.r.nextInt(20))
                                                      * (
                                                         Char.myCharz().cx
                                                               > Char.myCharz().npcFocus.cx
                                                            ? 1
                                                            : -1
                                                      );
                                                   Char.myCharz().currentMovePoint = new MovePoint(
                                                      Char.myCharz().npcFocus.cx + intSlot2,
                                                      Char.myCharz().cy
                                                   );
                                                   Char.myCharz().endMovePointCommand = new Command(null, null, 8002, null);
                                                   GameCanvas.clearKeyHold();
                                                   GameCanvas.clearKeyPressed();
                                                }

                                                condition = false;
                                             }
                                             break label480;
                                          }

                                          if (Char.myCharz().charFocus != null) {
                                             if (gameScreen.mobCapcha != null) {
                                                condition = false;
                                                break label480;
                                             }

                                             if (Char.myCharz().cx < Char.myCharz().charFocus.cx) {
                                                Char.myCharz().cdir = 1;
                                             } else {
                                                Char.myCharz().cdir = -1;
                                             }

                                             int value7 = Math.abs(
                                                Char.myCharz().cx - Char.myCharz().charFocus.cx
                                             );
                                             int value8 = Math.abs(
                                                Char.myCharz().cy - Char.myCharz().charFocus.cy
                                             );
                                             if (!Char.myCharz().isMeCanAttackOtherPlayer(Char.myCharz().charFocus)
                                                && !Char.myCharz().isSelectingSkillBuffToPlayer()) {
                                                if (value7 < 60 && value8 < 40) {
                                                   gameScreen.playerMenu();
                                                   if (!GameCanvas.isTouch
                                                      && Char.myCharz().charFocus.charID >= 0
                                                      && TileMap.mapID != 51
                                                      && TileMap.mapID != 52
                                                      && gameScreen.popUpYesNo == null) {
                                                      GameCanvas.panel.setTypePlayerMenu(Char.myCharz().charFocus);
                                                      GameCanvas.panel.show();
                                                      Service.gI()
                                                         .getPlayerMenu(Char.myCharz().charFocus.charID);
                                                      Service.gI()
                                                         .messagePlayerMenu(Char.myCharz().charFocus.charID);
                                                   }
                                                } else {
                                                   intSlot2 = (20 + Res.r.nextInt(20))
                                                      * (
                                                         Char.myCharz().cx
                                                               > Char.myCharz().charFocus.cx
                                                            ? 1
                                                            : -1
                                                      );
                                                   Char.myCharz().currentMovePoint = new MovePoint(
                                                      Char.myCharz().charFocus.cx + intSlot2,
                                                      Char.myCharz().charFocus.cy
                                                   );
                                                   Char.myCharz().endMovePointCommand = new Command(null, null, 8002, null);
                                                   GameCanvas.clearKeyHold();
                                                   GameCanvas.clearKeyPressed();
                                                }

                                                condition = false;
                                                break label480;
                                             }

                                             if (Char.myCharz().myskill == null) {
                                                condition = false;
                                                break label480;
                                             }

                                             if (!gameScreen.checkSkillValid()) {
                                                condition = false;
                                                break label480;
                                             }

                                             if (Char.myCharz().cx < Char.myCharz().charFocus.cx) {
                                                Char.myCharz().cdir = 1;
                                             } else {
                                                Char.myCharz().cdir = -1;
                                             }

                                             Char.myCharz().cvx = 0;
                                             if (value7 > Char.myCharz().myskill.dx
                                                || value8 > Char.myCharz().myskill.dy) {
                                                int value9 = (Char.myCharz().myskill.dx - 20)
                                                   * (
                                                      Char.myCharz().cx > Char.myCharz().charFocus.cx
                                                         ? 1
                                                         : -1
                                                   );
                                                if (value7 <= Char.myCharz().myskill.dx) {
                                                   value9 = 0;
                                                }

                                                Char.myCharz().currentMovePoint = new MovePoint(
                                                   Char.myCharz().charFocus.cx + value9,
                                                   Char.myCharz().charFocus.cy
                                                );
                                                Char.myCharz().endMovePointCommand = new Command(null, null, 8002, null);
                                                GameCanvas.clearKeyHold();
                                                GameCanvas.clearKeyPressed();
                                                condition = false;
                                                break label480;
                                             }

                                             if (Char.myCharz().myskill.template.id != 20) {
                                                byte byteValue2 = 20;
                                                if (Char.myCharz().myskill.dx > 60) {
                                                   byteValue2 = 60;
                                                   if (value7 < 20) {
                                                      Char.myCharz()
                                                         .createShadow(
                                                            Char.myCharz().cx, Char.myCharz().cy, 10
                                                         );
                                                   }
                                                }

                                                boolean condition7 = false;
                                                if ((
                                                      TileMap.tileTypeAtPixel(
                                                            Char.myCharz().cx, Char.myCharz().cy + 3
                                                         )
                                                         & 2
                                                   )
                                                   == 2) {
                                                   int value10 = Char.myCharz().cx
                                                         > Char.myCharz().charFocus.cx
                                                      ? 1
                                                      : -1;
                                                   if ((
                                                         TileMap.tileTypeAtPixel(
                                                               Char.myCharz().charFocus.cx + byteValue2 * value10,
                                                               Char.myCharz().cy + 3
                                                            )
                                                            & 2
                                                      )
                                                      != 2) {
                                                      condition7 = true;
                                                   }
                                                }

                                                if (value7 <= byteValue2 && !condition7) {
                                                   if (Char.myCharz().cx > Char.myCharz().charFocus.cx) {
                                                      Char.myCharz().cx = Char.myCharz().charFocus.cx
                                                         + byteValue2;
                                                      Char.myCharz().cdir = -1;
                                                   } else {
                                                      Char.myCharz().cx = Char.myCharz().charFocus.cx
                                                         - byteValue2;
                                                      Char.myCharz().cdir = 1;
                                                   }

                                                   Service.gI().charMove();
                                                }

                                                GameCanvas.clearKeyHold();
                                                GameCanvas.clearKeyPressed();
                                             }
                                          } else if (Char.myCharz().itemFocus != null) {
                                             gameScreen.pickItem();
                                             condition = false;
                                             break label480;
                                          }
                                       }

                                       condition = true;
                                    } else {
                                       condition = false;
                                    }
                                    break label480;
                                 }
                              }

                              condition = false;
                           }
                        }

                        if (condition) {
                           if (!Char.myCharz().isUseChargeSkill() || !Char.myCharz().focusToAttack()) {
                              boolean condition8 = TileMap.tileTypeAt(Char.myCharz().cx, Char.myCharz().cy, 2);
                              Char.myCharz()
                                 .setSkillPaint(sks[Char.myCharz().myskill.skillId], condition8 ? 0 : 1);
                              if (condition8) {
                                 Char.myCharz().delayFall = 20;
                              }

                              Char.myCharz().currentFireByShortcut = isFireByShortCut;
                           } else if (this.checkSkillValid()) {
                              Char.myCharz().currentFireByShortcut = isFireByShortCut;
                              Char.myCharz().sendUseChargeSkill();
                           } else {
                              Char.myCharz().stopUseChargeSkill();
                           }
                        }
                     } else if (this.checkSkillValid()) {
                        Char.myCharz().currentFireByShortcut = isFireByShortCut;
                        Char.myCharz();
                        Char.useSkillNotFocus();
                     }

                     if (Char.myCharz().isSelectingSkillBuffToPlayer()) {
                        this.auto = 0;
                     }
                  }
               }
            } else {
               waypoint2.popup.command.performAction();
            }
         } else {
            waypoint.popup.command.performAction();
         }
      }
   }

   private void pickItem() {
      if (Char.myCharz().itemFocus != null) {
         if (Char.myCharz().cx < Char.myCharz().itemFocus.x) {
            Char.myCharz().cdir = 1;
         } else {
            Char.myCharz().cdir = -1;
         }

         int value = Math.abs(Char.myCharz().cx - Char.myCharz().itemFocus.x);
         int value2 = Math.abs(Char.myCharz().cy - Char.myCharz().itemFocus.y);
         if (value <= 40 && value2 < 40) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            if (Char.myCharz().itemFocus.template.id != 673) {
               Service.gI().pickItem(Char.myCharz().itemFocus.itemMapID);
               return;
            }

            Npc c = new Npc(5, 0, -100, 100, 5, info1.charId[Char.myCharz().cgender][2]);
            Res.outz(Char.myCharz().npcFocus != null ? "!null" : "null");
            String chat = mResources.nhatvatpham;
            String[] menu = new String[]{mResources.YES, mResources.NO};
            Res.outz(Char.myCharz().npcFocus != null ? "!null" : "null");
            c.idItem = 673;
            gI();
            createMenu(menu, c);
            ChatPopup.addChatPopupWithIcon(chat, 100000, c, 5820);
            return;
         }

         Char.myCharz().currentMovePoint = new MovePoint(
            Char.myCharz().itemFocus.x, Char.myCharz().itemFocus.y
         );
         Char.myCharz().endMovePointCommand = new Command(null, null, 8002, null);
         GameCanvas.clearKeyHold();
         GameCanvas.clearKeyPressed();
      }
   }

   private boolean isCharging() {
      return Char.myCharz().isFlyAndCharge
         || Char.myCharz().isUseSkillAfterCharge
         || Char.myCharz().isStandAndCharge
         || Char.myCharz().isWaitMonkey
         || this.isSuperPower
         || Char.myCharz().isFreez;
   }

   private void doSelectSkill(Skill skill, boolean isShortcut) {
      if (!Char.myCharz().isCreateDark) {
         if (!this.isCharging()) {
            if (Char.myCharz().taskMaint.taskId > 1) {
               Char.myCharz().myskill = skill;
               if (this.lastSkill != skill && this.lastSkill != null) {
                  Service.gI().selectSkill(skill.template.id);
                  this.resetButton();
                  this.lastSkill = skill;
                  this.selectedIndexSkill = -1;
                  gI().auto = 0;
               } else if (Char.myCharz().isUseSkillSpec()) {
                  Res.outz(">>>use skill spec: " + skill.template.id);
                  Char.myCharz().sendNewAttack(skill.template.id);
                  this.resetButton();
                  this.lastSkill = skill;
                  this.selectedIndexSkill = -1;
                  gI().auto = 0;
               } else if (Char.myCharz().isSelectingSkillUseAlone()) {
                  Res.outz("use skill not focus");
                  this.doUseSkillNotFocus(skill);
                  this.lastSkill = skill;
               } else {
                  this.selectedIndexSkill = -1;
                  if (skill != null) {
                     Res.outz("only select skill");
                     if (this.lastSkill != skill) {
                        Service.gI().selectSkill(skill.template.id);
                        this.resetButton();
                     }

                     if (Char.myCharz().charFocus == null && Char.myCharz().isSelectingSkillBuffToPlayer()) {
                        Res.outz("return o day");
                        return;
                     }

                     if (Char.myCharz().focusToAttack()) {
                        this.doFire(true, true);
                     }

                     this.lastSkill = skill;
                  }
               }
            }
         }
      }
   }

   private void doUseSkill(Skill skill, boolean isShortcut) {
      if (TileMap.mapID != 112 && TileMap.mapID != 113 || Char.myCharz().cTypePk != 0) {
         if (Char.myCharz().isSelectingSkillUseAlone()) {
            this.doUseSkillNotFocus(skill);
         } else {
            this.selectedIndexSkill = -1;
            if (skill != null) {
               Service.gI().selectSkill(skill.template.id);
               this.resetButton();
               Char.myCharz().myskill = skill;
               this.doFire(isShortcut, true);
            }
         }
      }
   }

   private void doUseSkillNotFocus(Skill skill) {
      if (TileMap.mapID != 112 && TileMap.mapID != 113 || Char.myCharz().cTypePk != 0) {
         if (this.checkSkillValid()) {
            this.selectedIndexSkill = -1;
            if (skill != null) {
               Service.gI().selectSkill(skill.template.id);
               this.resetButton();
               Char.myCharz().myskill = skill;
               Char.myCharz();
               Char.useSkillNotFocus();
               Char.myCharz().currentFireByShortcut = true;
               this.auto = 0;
            }
         }
      }
   }

   public static void sortSkill() {
      for (int skillFightIndex = 0; skillFightIndex < Char.myCharz().vSkillFight.size() - 1; skillFightIndex++) {
         Skill skillFight = (Skill)Char.myCharz().vSkillFight.elementAt(skillFightIndex);

         for (int skillFightIndex2 = skillFightIndex + 1; skillFightIndex2 < Char.myCharz().vSkillFight.size(); skillFightIndex2++) {
            Skill skillFight2;
            if ((skillFight2 = (Skill)Char.myCharz().vSkillFight.elementAt(skillFightIndex2)).template.id
               < skillFight.template.id) {
               Skill skillFight3 = skillFight2;
               skillFight2 = skillFight;
               skillFight = skillFight3;
               Char.myCharz().vSkillFight.setElementAt(skillFight, skillFightIndex);
               Char.myCharz().vSkillFight.setElementAt(skillFight2, skillFightIndex2);
            }
         }
      }
   }

   private void updateKeyTouchCapcha() {
      if (!this.isNotPaintTouchControl()) {
         for (int characterIndex = 0; characterIndex < this.strCapcha.length(); characterIndex++) {
            this.keyCapcha[characterIndex] = -1;
            if (GameCanvas.isTouchControl) {
               int x = (GameCanvas.w - this.strCapcha.length() * disXC) / 2;
               int w = this.strCapcha.length() * disXC;
               int y = GameCanvas.h - 40;
               int h = disXC;
               if (GameCanvas.isPointerHoldIn(x, y, w, h)) {
                  x = (GameCanvas.px - x) / disXC;
                  if (characterIndex == x) {
                     this.keyCapcha[characterIndex] = 1;
                  }

                  if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease && characterIndex == x) {
                     char[] charValues = this.keyInput.toCharArray();
                     MyVector entries = new MyVector("");

                     for (int index = 0; index < charValues.length; index++) {
                        entries.addElement(String.valueOf(charValues[index]));
                     }

                     entries.removeElementAt(0);
                     entries.insertElementAt(String.valueOf(this.strCapcha.charAt(characterIndex)), entries.size());
                     this.keyInput = "";

                     for (int entryIndex = 0; entryIndex < entries.size(); entryIndex++) {
                        this.keyInput = this.keyInput + ((String)entries.elementAt(entryIndex)).toUpperCase();
                     }

                     Service.gI().mobCapcha(this.strCapcha.charAt(characterIndex));
                  }
               }
            }
         }
      }
   }

   public final void update() {
      if (GameCanvas.gameTick % 100 == 0 && TileMap.mapID == 137) {
         shock_scr = 30;
      }

      if (isAutoPlay && GameCanvas.gameTick % 20 == 0) {
         GameScr gameScreen = this;
         if (this.timeSkill > 0) {
            gameScreen.timeSkill--;
         }

         if (canAutoPlay
            && !isChangeZone
            && Char.myCharz().statusMe != 14
            && Char.myCharz().statusMe != 5
            && !Char.myCharz().isCharge
            && !Char.myCharz().isFlyAndCharge
            && !Char.myCharz().isUseChargeSkill()) {
            boolean condition = false;

            for (int mobIndex = 0; mobIndex < vMob.size(); mobIndex++) {
               Mob mob;
               if ((mob = (Mob)vMob.elementAt(mobIndex)).status != 0 && mob.status != 1) {
                  condition = true;
               }
            }

            if (condition) {
               boolean condition2 = false;

               for (int itemBagIndex = 0; itemBagIndex < Char.myCharz().arrItemBag.length; itemBagIndex++) {
                  Item itemBag;
                  if ((itemBag = Char.myCharz().arrItemBag[itemBagIndex]) != null && itemBag.template.type == 6) {
                     condition2 = true;
                     break;
                  }
               }

               if (!condition2 && GameCanvas.gameTick % 150 == 0) {
                  Service.gI().requestPean();
               }

               if (Char.myCharz().cHP <= Char.myCharz().cHPFull * 20L / 100L
                  || Char.myCharz().cMP <= Char.myCharz().cMPFull * 20L / 100L) {
                  gameScreen.doUseHP();
               }

               if (Char.myCharz().mobFocus == null
                  || Char.myCharz().mobFocus != null && Char.myCharz().mobFocus.isMobMe) {
                  for (int mobIndex2 = 0; mobIndex2 < vMob.size(); mobIndex2++) {
                     Mob mobFocus;
                     if ((mobFocus = (Mob)vMob.elementAt(mobIndex2)).status != 0
                        && mobFocus.status != 1
                        && mobFocus.hp > 0L
                        && !mobFocus.isMobMe) {
                        Char.myCharz().cx = mobFocus.x;
                        Char.myCharz().cy = mobFocus.y;
                        Char.myCharz().mobFocus = mobFocus;
                        Service.gI().charMove();
                        Res.outz("focus 1 con bossssssssssssssssssssssssssssssssssssssssssssssssss");
                        break;
                     }
                  }
               } else if (Char.myCharz().mobFocus.hp <= 0L
                  || Char.myCharz().mobFocus.status == 1
                  || Char.myCharz().mobFocus.status == 0) {
                  Char.myCharz().mobFocus = null;
               }

               if (Char.myCharz().mobFocus != null
                  && gameScreen.timeSkill == 0
                  && (
                     Char.myCharz().skillInfoPaint() == null
                        || Char.myCharz().indexSkill >= Char.myCharz().skillInfoPaint().length
                        || Char.myCharz().dart == null
                        || Char.myCharz().arr == null
                  )) {
                  Skill skill = null;
                  if (GameCanvas.isTouch) {
                     for (int onScreenSkillIndex = 0; onScreenSkillIndex < onScreenSkill.length; onScreenSkillIndex++) {
                        if (onScreenSkill[onScreenSkillIndex] != null
                           && !onScreenSkill[onScreenSkillIndex].paintCanNotUseSkill
                           && onScreenSkill[onScreenSkillIndex].template.id != 10
                           && onScreenSkill[onScreenSkillIndex].template.id != 11
                           && onScreenSkill[onScreenSkillIndex].template.id != 14
                           && onScreenSkill[onScreenSkillIndex].template.id != 23
                           && onScreenSkill[onScreenSkillIndex].template.id != 7
                           && Char.myCharz().skillInfoPaint() == null
                           && !onScreenSkill[onScreenSkillIndex].template.isSkillSpec()) {
                           long manaUse;
                           if (onScreenSkill[onScreenSkillIndex].template.manaUseType == 2) {
                              manaUse = 1L;
                           } else if (onScreenSkill[onScreenSkillIndex].template.manaUseType != 1) {
                              manaUse = onScreenSkill[onScreenSkillIndex].manaUse;
                           } else {
                              manaUse = onScreenSkill[onScreenSkillIndex].manaUse * Char.myCharz().cMPFull / 100L;
                           }

                           if (Char.myCharz().cMP >= manaUse) {
                              if (skill == null) {
                                 skill = onScreenSkill[onScreenSkillIndex];
                              } else if (skill.coolDown < onScreenSkill[onScreenSkillIndex].coolDown) {
                                 skill = onScreenSkill[onScreenSkillIndex];
                              }
                           }
                        }
                     }

                     if (skill != null) {
                        gameScreen.doSelectSkill(skill, true);
                        gameScreen.doDoubleClickToObj(Char.myCharz().mobFocus);
                     }
                  } else {
                     for (int keySkillIndex = 0; keySkillIndex < keySkill.length; keySkillIndex++) {
                        if (keySkill[keySkillIndex] != null
                           && !keySkill[keySkillIndex].paintCanNotUseSkill
                           && keySkill[keySkillIndex].template.id != 10
                           && keySkill[keySkillIndex].template.id != 11
                           && keySkill[keySkillIndex].template.id != 14
                           && keySkill[keySkillIndex].template.id != 23
                           && keySkill[keySkillIndex].template.id != 7
                           && Char.myCharz().skillInfoPaint() == null) {
                           long manaUse2;
                           if (keySkill[keySkillIndex].template.manaUseType == 2) {
                              manaUse2 = 1L;
                           } else if (keySkill[keySkillIndex].template.manaUseType != 1) {
                              manaUse2 = keySkill[keySkillIndex].manaUse;
                           } else {
                              manaUse2 = keySkill[keySkillIndex].manaUse * Char.myCharz().cMPFull / 100L;
                           }

                           if (Char.myCharz().cMP >= manaUse2) {
                              if (skill == null) {
                                 skill = keySkill[keySkillIndex];
                              } else if (skill.coolDown < keySkill[keySkillIndex].coolDown) {
                                 skill = keySkill[keySkillIndex];
                              }
                           }
                        }
                     }

                     if (skill != null) {
                        gameScreen.doSelectSkill(skill, true);
                        gameScreen.doDoubleClickToObj(Char.myCharz().mobFocus);
                     }
                  }
               }
            }
         }
      }

      GameScr gameScreen2 = this;
      if (this.tShow != 0) {
         if ((currXS = System.currentTimeMillis()) - lastXS > 1000L) {
            lastXS = System.currentTimeMillis();
            secondXS++;
         }

         if (secondXS > 20) {
            for (int randomNumberIndex = 0; randomNumberIndex < gameScreen2.winnumber.length; randomNumberIndex++) {
               gameScreen2.randomNumber[randomNumberIndex] = gameScreen2.winnumber[randomNumberIndex];
            }

            gameScreen2.tShow--;
            if (gameScreen2.tShow == 0) {
               gameScreen2.yourNumber = "";
               info1.addInfo(gameScreen2.strFinish, 0);
               secondXS = 0;
            }
         } else if (gameScreen2.moveIndex > gameScreen2.winnumber.length - 1) {
            gameScreen2.tShow--;
            if (gameScreen2.tShow == 0) {
               gameScreen2.yourNumber = "";
               info1.addInfo(gameScreen2.strFinish, 0);
            }
         } else {
            if (gameScreen2.moveIndex < gameScreen2.randomNumber.length) {
               if (gameScreen2.tMove[gameScreen2.moveIndex] == 15) {
                  if (gameScreen2.randomNumber[gameScreen2.moveIndex] == gameScreen2.winnumber[gameScreen2.moveIndex] - 1) {
                     gameScreen2.delayMove[gameScreen2.moveIndex] = 10;
                  }

                  if (gameScreen2.randomNumber[gameScreen2.moveIndex] == gameScreen2.winnumber[gameScreen2.moveIndex]) {
                     gameScreen2.tMove[gameScreen2.moveIndex] = -1;
                     gameScreen2.moveIndex++;
                  }
               } else if (GameCanvas.gameTick % 5 == 0) {
                  gameScreen2.tMove[gameScreen2.moveIndex]++;
               }
            }

            for (int delayMoveIndex = 0; delayMoveIndex < gameScreen2.winnumber.length; delayMoveIndex++) {
               if (gameScreen2.tMove[delayMoveIndex] != -1) {
                  gameScreen2.moveCount[delayMoveIndex]++;
                  if (gameScreen2.moveCount[delayMoveIndex] > gameScreen2.tMove[delayMoveIndex] + gameScreen2.delayMove[delayMoveIndex]) {
                     gameScreen2.moveCount[delayMoveIndex] = 0;
                     gameScreen2.randomNumber[delayMoveIndex]++;
                     if (gameScreen2.randomNumber[delayMoveIndex] >= 10) {
                        gameScreen2.randomNumber[delayMoveIndex] = 0;
                     }
                  }
               }
            }
         }
      }

      mSystem.checkAdComlete();
      SmallImage.update();
      this.updateCycleTick++;
      if (this.updateCycleTick == 100) {
         this.updateCycleTick = 0;
      }

      try {
         if (LoginScr.isContinueToLogin) {
            LoginScr.isContinueToLogin = false;
         }

         if (tickMove == 1) {
            lastTick = System.currentTimeMillis();
         }

         if (tickMove == 100) {
            tickMove = 0;
            int second = (int)((currTick = System.currentTimeMillis()) - lastTick) / 1000;
            Service.gI().checkMMove(second);
         }

         if (lockTick > 0 && --lockTick == 0) {
            Controller.isStopReadMessage = false;
         }

         GameCanvas.debug();
         if (!isPaintOther) {
            if (cmx != cmtoX || cmy != cmtoY) {
               cmvx = cmtoX - cmx << 2;
               cmvy = cmtoY - cmy << 2;
               cmdx = cmdx + cmvx;
               cmx = cmx + (cmdx >> 4);
               cmdx &= 15;
               cmdy = cmdy + cmvy;
               cmy = cmy + (cmdy >> 4);
               cmdy &= 15;
               if (cmx < 24) {
                  cmx = 24;
               }

               if (cmx > cmxLim) {
                  cmx = cmxLim;
               }

               if (cmy < 0) {
                  cmy = 0;
               }

               if (cmy > cmyLim) {
                  cmy = cmyLim;
               }
            }

            if ((gssx = cmx / TileMap.size - 1) < 0) {
               gssx = 0;
            }

            gssy = cmy / TileMap.size;
            gssxe = gssx + gssw;
            gssye = gssy + gssh;
            if (gssy < 0) {
               gssy = 0;
            }

            if (gssye > TileMap.tmh - 1) {
               gssye = TileMap.tmh - 1;
            }

            if ((TileMap.gssx = (Char.myCharz().cx - 2 * gW) / TileMap.size) < 0) {
               TileMap.gssx = 0;
            }

            if ((TileMap.gssxe = TileMap.gssx + TileMap.countx) > TileMap.tmw) {
               TileMap.gssx = (TileMap.gssxe = TileMap.tmw) - TileMap.countx;
            }

            if ((TileMap.gssy = (Char.myCharz().cy - 2 * gH) / TileMap.size) < 0) {
               TileMap.gssy = 0;
            }

            if ((TileMap.gssye = TileMap.gssy + TileMap.county) > TileMap.tmh) {
               TileMap.gssy = (TileMap.gssye = TileMap.tmh) - TileMap.county;
            }

            scrMain.updatecm();
            scrInfo.updatecm();
         }

         GameCanvas.debug();
         ChatTextField.gI().update();
         GameCanvas.debug();

         for (int charInMapIndex = 0; charInMapIndex < vCharInMap.size(); charInMapIndex++) {
            ((Char)vCharInMap.elementAt(charInMapIndex)).update();
         }

         for (int teleportIndex = 0; teleportIndex < Teleport.vTeleport.size(); teleportIndex++) {
            ((Teleport)Teleport.vTeleport.elementAt(teleportIndex)).update();
         }

         Char.myCharz().update();
         if (Char.myCharz().statusMe == 1 && GameCanvas.gameTick % 100 == 0) {
            System.gc();
         }

         if (this.popUpYesNo != null) {
            this.popUpYesNo.update();
         }

         EffecMn.update();
         GameCanvas.debug();

         for (int mobIndex3 = 0; mobIndex3 < vMob.size(); mobIndex3++) {
            ((Mob)vMob.elementAt(mobIndex3)).update();
         }

         GameCanvas.debug();

         for (int npcIndex = 0; npcIndex < vNpc.size(); npcIndex++) {
            ((Npc)vNpc.elementAt(npcIndex)).update();
         }

         this.nSkill = onScreenSkill.length;

         for (int onScreenSkillIndex2 = onScreenSkill.length - 1; onScreenSkillIndex2 >= 0; onScreenSkillIndex2--) {
            Skill onScreenSkillValue;
            if ((onScreenSkillValue = onScreenSkill[onScreenSkillIndex2]) != null) {
               this.nSkill = onScreenSkillIndex2 + 1;
               break;
            }

            this.nSkill--;
         }

         setSkillBarPosition();
         GameCanvas.debug();
         GameCanvas.gI().updateDust();
         GameCanvas.debug();

         for (int flyTextDxIndex = 0; flyTextDxIndex < 5; flyTextDxIndex++) {
            if (flyTextState[flyTextDxIndex] != -1) {
               if (flyTextState[flyTextDxIndex] > flyTextYTo[flyTextDxIndex]) {
                  flyTime[flyTextDxIndex]++;
                  if (flyTime[flyTextDxIndex] == 25) {
                     flyTime[flyTextDxIndex] = 0;
                     flyTextState[flyTextDxIndex] = -1;
                     flyTextYTo[flyTextDxIndex] = 0;
                     flyTextDx[flyTextDxIndex] = 0;
                     flyTextX[flyTextDxIndex] = 0;
                  }
               } else {
                  flyTextState[flyTextDxIndex] = flyTextState[flyTextDxIndex] + Res.abs(flyTextDy[flyTextDxIndex]);
                  flyTextX[flyTextDxIndex] = flyTextX[flyTextDxIndex] + flyTextDx[flyTextDxIndex];
                  flyTextY[flyTextDxIndex] = flyTextY[flyTextDxIndex] + flyTextDy[flyTextDxIndex];
               }
            }
         }

         PopUp.update();

         for (int splashDirIndex = 0; splashDirIndex < 2; splashDirIndex++) {
            if (splashState[splashDirIndex] != -1) {
               splashState[splashDirIndex]++;
               splashX[splashDirIndex] = splashX[splashDirIndex] + (splashDir[splashDirIndex] << 2);
               splashY[splashDirIndex]--;
               if (splashState[splashDirIndex] >= 6) {
                  splashState[splashDirIndex] = -1;
               } else {
                  splashF[splashDirIndex] = (splashState[splashDirIndex] >> 1) % 3;
               }
            }
         }

         if (cmySK != cmtoYSK) {
            cmvySK = cmtoYSK - cmySK << 2;
            cmdySK = cmdySK + cmvySK;
            cmySK = cmySK + (cmdySK >> 4);
            cmdySK &= 15;
         }

         if (Math.abs(cmtoYSK - cmySK) < 15 && cmySK < 0) {
            cmtoYSK = 0;
         }

         if (Math.abs(cmtoYSK - cmySK) < 15 && cmySK > 0) {
            cmtoYSK = 0;
         }

         GameCanvas.updateBG();
         GameCanvas.debug();
         gameScreen2 = this;
         if (this.doubleClickCooldownTicks > 0) {
            gameScreen2.doubleClickCooldownTicks--;
         }

         if (gameScreen2.clickMoving) {
            gameScreen2.clickMoving = false;
            IMapObject iMapObject;
            if ((iMapObject = gameScreen2.findClickToItem(gameScreen2.clickToX, gameScreen2.clickToY)) == null
               || iMapObject != null && iMapObject.equals(Char.myCharz().npcFocus) && TileMap.mapID == 51) {
               ServerEffect.addServerEffect(134, gameScreen2.clickToX, gameScreen2.clickToY + GameCanvas.transY / 2, 3);
            }
         }

         GameCanvas.debug();

         for (int itemMapIndex = 0; itemMapIndex < vItemMap.size(); itemMapIndex++) {
            ((ItemMap)vItemMap.elementAt(itemMapIndex)).update();
         }

         GameCanvas.debug();
         GameCanvas.debug();

         for (int removeEffect2Index = Effect2.vRemoveEffect2.size() - 1; removeEffect2Index >= 0; removeEffect2Index--) {
            Effect2.vEffect2.removeElement(Effect2.vRemoveEffect2.elementAt(removeEffect2Index));
            Effect2.vRemoveEffect2.removeElementAt(removeEffect2Index);
         }

         for (int effect2Index = 0; effect2Index < Effect2.vEffect2.size(); effect2Index++) {
            ((Effect2)Effect2.vEffect2.elementAt(effect2Index)).update();
         }

         for (int effect2OutsideIndex = 0; effect2OutsideIndex < Effect2.vEffect2Outside.size(); effect2OutsideIndex++) {
            ((Effect2)Effect2.vEffect2Outside.elementAt(effect2OutsideIndex)).update();
         }

         for (int animateEffectIndex = 0; animateEffectIndex < Effect2.vAnimateEffect.size(); animateEffectIndex++) {
            ((Effect2)Effect2.vAnimateEffect.elementAt(animateEffectIndex)).update();
         }

         for (int effectFeetIndex = 0; effectFeetIndex < Effect2.vEffectFeet.size(); effectFeetIndex++) {
            ((Effect2)Effect2.vEffectFeet.elementAt(effectFeetIndex)).update();
         }

         for (int effect3Index = 0; effect3Index < Effect2.vEffect3.size(); effect3Index++) {
            ((Effect2)Effect2.vEffect3.elementAt(effect3Index)).update();
         }

         BackgroudEffect.update();
         info1.update();
         info2.update();
         GameCanvas.debug();
         if (currentCharViewInfo != null && !currentCharViewInfo.equals(Char.myCharz())) {
            currentCharViewInfo.update();
         }

         this.runArrow++;
         if (this.runArrow > 3) {
            this.runArrow = 0;
         }

         if (this.isInjureHp) {
            this.twHp++;
            if (this.twHp == 20L) {
               this.twHp = 0L;
               this.isInjureHp = false;
            }
         } else if (this.dHP > Char.myCharz().cHP) {
            long dHPValue;
            if ((dHPValue = this.dHP - Char.myCharz().cHP >> 1) < 1L) {
               dHPValue = 1L;
            }

            this.dHP -= dHPValue;
         } else {
            this.dHP = Char.myCharz().cHP;
         }

         if (this.isInjureMp) {
            this.twMp++;
            if (this.twMp == 20L) {
               this.twMp = 0L;
               this.isInjureMp = false;
            }
         } else if (this.dMP > Char.myCharz().cMP) {
            long dMPValue;
            if ((dMPValue = this.dMP - Char.myCharz().cMP >> 1) < 1L) {
               dMPValue = 1L;
            }

            this.dMP -= dMPValue;
         } else {
            this.dMP = Char.myCharz().cMP;
         }

         if (this.tMenuDelay > 0) {
            this.tMenuDelay--;
         }

         if (this.isRongThanMenu()) {
            while (this.yR - 100 < cmy) {
               cmy--;
            }
         }

         for (int itemTimeIndex = 0; itemTimeIndex < Char.vItemTime.size(); itemTimeIndex++) {
            ((ItemTime)Char.vItemTime.elementAt(itemTimeIndex)).update();
         }

         for (int textTimeIndex = 0; textTimeIndex < textTime.size(); textTimeIndex++) {
            ((ItemTime)textTime.elementAt(textTimeIndex)).update();
         }

         gameScreen2 = this;
         if (this.startChat) {
            int xChatVipValue = SoundMn.gameSpeed > 0 ? 1 : 2;
            gameScreen2.xChatVip -= xChatVipValue;
            if (gameScreen2.xChatVip < -gameScreen2.currChatWidth) {
               gameScreen2.xChatVip = GameCanvas.w;
               vChatVip.removeElementAt(0);
               if (vChatVip.size() == 0) {
                  gameScreen2.isFireWorks = false;
                  gameScreen2.startChat = false;
               } else {
                  gameScreen2.currChatWidth = mFont.tahoma_7b_white.getWidth((String)vChatVip.elementAt(0));
               }
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      int value;
      if ((value = GameCanvas.gameTick % 4000) == 1000) {
         ImgByName.checkDelHash(ImgByName.hashImagePath, 10);
      }

      EffectManager.update();
   }

   public final boolean isRongThanMenu() {
      return this.isMeCallRongThan;
   }

   public static void paintBgItem(mGraphics g, int layer) {
      for (int currItemIndex = 0; currItemIndex < TileMap.vCurrItem.size(); currItemIndex++) {
         BgItem currItem;
         if ((currItem = (BgItem)TileMap.vCurrItem.elementAt(currItemIndex)).idImage != -1 && currItem.layer == layer) {
            currItem.paint(g);
         }
      }

      if (TileMap.mapID == 48 && layer == 3 && GameCanvas.bgW != null && GameCanvas.bgW[0] != 0) {
         for (int index = 0; index < TileMap.pxw / GameCanvas.bgW[0] + 1; index++) {
            g.drawImage(
               GameCanvas.imgBG[0], ((int) (index * GameCanvas.bgW[0])), ((int) (TileMap.pxh - GameCanvas.bgH[0] - 70)), 0
            );
         }
      }
   }

   public static void paintBlackSky(mGraphics g) {
      if (!GameCanvas.lowGraphic) {
         g.fillTrans(imgTrans, 0, 0, GameCanvas.w, GameCanvas.h);
      }
   }

   public final void logPaintNullPointerContext() {
      Char paintActorValue = this.paintActor;
      if (this.paintActor == null) {
         System.err.println("[PAINT-NPE] GameScr.paint stage=" + this.paintStage + " actor=null");
      } else {
         SkillPaint skillPaint = paintActorValue.skillPaint;
         SkillPaint skillPaintRandomPaint = paintActorValue.skillPaintRandomPaint;
         SkillInfoPaint[] skillInfoPaints;
         int value = (skillInfoPaints = paintActorValue.skillPaintRandomPaint == null ? null : (paintActorValue.sType == 0 ? skillPaintRandomPaint.skillStand : skillPaintRandomPaint.skillfly)) == null
            ? -1
            : skillInfoPaints.length;
         boolean condition = skillInfoPaints != null && paintActorValue.indexSkill >= 0 && paintActorValue.indexSkill < skillInfoPaints.length && skillInfoPaints[paintActorValue.indexSkill] == null;
         int value2 = paintActorValue.eff0 == null
            ? -1
            : (paintActorValue.eff0.arrEfInfo == null ? -2 : paintActorValue.eff0.arrEfInfo.length);
         int value3 = paintActorValue.eff1 == null
            ? -1
            : (paintActorValue.eff1.arrEfInfo == null ? -2 : paintActorValue.eff1.arrEfInfo.length);
         int value4 = paintActorValue.eff2 == null
            ? -1
            : (paintActorValue.eff2.arrEfInfo == null ? -2 : paintActorValue.eff2.arrEfInfo.length);
         System.err
            .println(
               "[PAINT-NPE] GameScr.paint stage="
                  + this.paintStage
                  + " actorId="
                  + paintActorValue.charID
                  + " actorName="
                  + paintActorValue.cName
                  + " local="
                  + paintActorValue.me
                  + " skillId="
                  + (skillPaint == null ? -1 : skillPaint.id)
                  + " randomPaintId="
                  + (skillPaintRandomPaint == null ? -1 : skillPaintRandomPaint.id)
                  + " skillType="
                  + paintActorValue.sType
                  + " indexSkill="
                  + paintActorValue.indexSkill
                  + " skillFrameCount="
                  + value
                  + " currentSkillFrameNull="
                  + condition
                  + " effectFrames="
                  + paintActorValue.i0
                  + "/"
                  + value2
                  + ","
                  + paintActorValue.i1
                  + "/"
                  + value3
                  + ","
                  + paintActorValue.i2
                  + "/"
                  + value4
            );
      }
   }

   private static void logNullPaintCharacter(String text, int value) {
      long lastPaintCharacterLogAtValue;
      if ((lastPaintCharacterLogAtValue = System.currentTimeMillis()) - lastPaintCharacterLogAt >= 1000L) {
         lastPaintCharacterLogAt = lastPaintCharacterLogAtValue;
         System.err
            .println(
               "[PAINT-NULL-CHAR] stage="
                  + text
                  + " index="
                  + value
                  + " listSize="
                  + vCharInMap.size()
                  + " tick="
                  + GameCanvas.gameTick
                  + " map="
                  + TileMap.mapID
            );
      }
   }

   private static void logPaintCharacterLookupError(String text, int value, Exception exception) {
      long lastPaintCharacterLogAtValue;
      if ((lastPaintCharacterLogAtValue = System.currentTimeMillis()) - lastPaintCharacterLogAt >= 1000L) {
         lastPaintCharacterLogAt = lastPaintCharacterLogAtValue;
         System.err
            .println(
               "[PAINT-CHAR-LOOKUP] stage="
                  + text
                  + " index="
                  + value
                  + " listSize="
                  + vCharInMap.size()
                  + " tick="
                  + GameCanvas.gameTick
                  + " map="
                  + TileMap.mapID
                  + " exception="
                  + exception.toString()
            );
         exception.printStackTrace();
      }
   }

   public final void paint(mGraphics g) {
      countEff = 0;
      this.paintStage = "start";
      this.paintActor = null;
      if (isPaint) {
         GameCanvas.debug();
         if (this.isFreez || this.isUseFreez && ChatPopup.currChatPopup == null) {
            this.dem++;
            if (this.dem < 30 && this.dem >= 0 && GameCanvas.gameTick % 4 == 0
               || this.dem >= 30 && this.dem <= 50 && GameCanvas.gameTick % 3 == 0
               || this.dem > 50) {
               g.setColor(16777215);
               g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
               if (this.dem > 50) {
                  if (this.isUseFreez) {
                     this.isUseFreez = false;
                     this.dem = 0;
                     if (this.activeRongThan) {
                        this.callRongThan(this.xR, this.yR);
                     } else {
                        GameScr gameScreen = this;
                        this.isRongThanXuatHien = false;
                        EffecMn.removeEff(17);
                        if (gameScreen.isRongNamek) {
                           gameScreen.isRongNamek = false;
                           EffecMn.removeEff(25);
                        }
                     }
                  }

                  this.paintInfoBar(g);
                  g.translate(-cmx, -cmy);
                  g.translate(0, GameCanvas.transY);
                  this.paintStage = "freeze-local-character";
                  this.paintActor = Char.myCharz();
                  Char.myCharz().paint(g);
                  this.paintActor = null;
                  mSystem.paintFlyText(g);
                  resetTranslate(g);
                  this.paintSelectedSkill(g);
                  return;
               } else {
                  return;
               }
            }
         }

         GameCanvas.debug();
         this.paintStage = "background";
         GameCanvas.paintBGGameScr(g);
         if (mSystem.clientType != 5) {
         }

         if ((this.isRongThanXuatHien || this.isFireWorks) && TileMap.bgID != 3) {
            paintBlackSky(g);
         }

         GameCanvas.debug();
         if (shock_scr > 0) {
            g.translate(
               -cmx + shock_x[shock_scr % shock_x.length],
               -cmy + shock_y[shock_scr % shock_y.length]
            );
            shock_scr--;
         } else {
            g.translate(-cmx, -cmy);
         }

         if (this.isSuperPower) {
            int deltaX = GameCanvas.gameTick % 3 == 0 ? 3 : -3;
            g.translate(deltaX, 0);
         }

         BackgroudEffect.paintBehindTile(g);
         EffecMn.paintLayer1(g);
         this.paintStage = "tilemap";
         TileMap.paintTilemap(g);
         TileMap.paintOutTilemap(g);

         for (int charInMapIndex = 0; charInMapIndex < vCharInMap.size(); charInMapIndex++) {
            Char charInMap;
            if ((charInMap = (Char)vCharInMap.elementAt(charInMapIndex)).isMabuHold && TileMap.mapID == 128) {
               charInMap.paintHeadWithXY(g, charInMap.cx, charInMap.cy, 0);
            }
         }

         if (Char.myCharz().isMabuHold && TileMap.mapID == 128) {
            Char.myCharz().paintHeadWithXY(g, Char.myCharz().cx, Char.myCharz().cy, 0);
         }

         paintBgItem(g, 2);
         if (Char.myCharz().cmdMenu != null && GameCanvas.isTouch) {
            if (mScreen.keyTouch == 20) {
               g.drawImage(
                  imgChat2,
                  ((int) (Char.myCharz().cmdMenu.x + cmx)),
                  ((int) (Char.myCharz().cmdMenu.y + cmy)),
                  3
               );
            } else {
               g.drawImage(
                  imgChat,
                  ((int) (Char.myCharz().cmdMenu.x + cmx)),
                  ((int) (Char.myCharz().cmdMenu.y + cmy)),
                  3
               );
            }
         }

         GameCanvas.debug();
         GameCanvas.debug();
         BackgroudEffect.paintBackAll(g);
         EffectManager.lowEffects.paintAll(g);

         for (int effectFeetIndex = 0; effectFeetIndex < Effect2.vEffectFeet.size(); effectFeetIndex++) {
            ((Effect2)Effect2.vEffectFeet.elementAt(effectFeetIndex)).paint(g);
         }

         for (int teleportIndex = 0; teleportIndex < Teleport.vTeleport.size(); teleportIndex++) {
            ((Teleport)Teleport.vTeleport.elementAt(teleportIndex)).paintHole(g);
         }

         for (int npcIndex = 0; npcIndex < vNpc.size(); npcIndex++) {
            Npc npc;
            if ((npc = (Npc)vNpc.elementAt(npcIndex)).cHP > 0L) {
               npc.paintShadow(g);
            }
         }

         for (int npcIndex2 = 0; npcIndex2 < vNpc.size(); npcIndex2++) {
            ((Npc)vNpc.elementAt(npcIndex2)).paint(g);
         }

         g.translate(0, GameCanvas.transY);
         GameCanvas.debug();
         GameCanvas.debug();
         this.paintStage = "character-shadow";

         for (int charInMapIndex2 = 0; charInMapIndex2 < vCharInMap.size(); charInMapIndex2++) {
            Char paintActorValue = null;
            this.paintActor = null;

            try {
               paintActorValue = (Char)vCharInMap.elementAt(charInMapIndex2);
            } catch (Exception exception) {
               logPaintCharacterLookupError("character-shadow", charInMapIndex2, exception);
               continue;
            }

            if (paintActorValue == null) {
               logNullPaintCharacter("character-shadow", charInMapIndex2);
            } else {
               this.paintActor = paintActorValue;
               if ((!GameCanvas.panel.isShow || !GameCanvas.panel.isTypeShop()) && paintActorValue.isShadown) {
                  paintActorValue.paintShadow(g);
               }
            }
         }

         this.paintActor = Char.myCharz();
         Char.myCharz().paintShadow(g);
         this.paintActor = null;
         EffecMn.paintLayer2(g);
         this.paintStage = "mob";

         for (int mobIndex = 0; mobIndex < vMob.size(); mobIndex++) {
            ((Mob)vMob.elementAt(mobIndex)).paint(g);
         }

         this.paintStage = "teleport";

         for (int teleportIndex2 = 0; teleportIndex2 < Teleport.vTeleport.size(); teleportIndex2++) {
            ((Teleport)Teleport.vTeleport.elementAt(teleportIndex2)).paint(g);
         }

         this.paintStage = "character";

         for (int charInMapIndex3 = 0; charInMapIndex3 < vCharInMap.size(); charInMapIndex3++) {
            Char paintActorValue2 = null;
            this.paintActor = null;

            try {
               paintActorValue2 = (Char)vCharInMap.elementAt(charInMapIndex3);
            } catch (Exception exception2) {
               logPaintCharacterLookupError("character", charInMapIndex3, exception2);
               continue;
            }

            if (paintActorValue2 == null) {
               logNullPaintCharacter("character", charInMapIndex3);
            } else if (!GameCanvas.panel.isShow || !GameCanvas.panel.isTypeShop()) {
               this.paintActor = paintActorValue2;
               paintActorValue2.paint(g);
            }
         }

         this.paintStage = "local-character";
         this.paintActor = Char.myCharz();
         Char.myCharz().paint(g);
         this.paintStage = "local-skill";
         if (Char.myCharz().skillPaint != null
            && Char.myCharz().skillInfoPaint() != null
            && Char.myCharz().indexSkill < Char.myCharz().skillInfoPaint().length) {
            Char.myCharz().paintCharWithSkill(g);
            Char.myCharz().paintMount2(g);
         }

         this.paintStage = "character-skill";

         for (int charInMapIndex4 = 0; charInMapIndex4 < vCharInMap.size(); charInMapIndex4++) {
            Char paintActorValue3 = null;
            this.paintActor = null;

            try {
               paintActorValue3 = (Char)vCharInMap.elementAt(charInMapIndex4);
            } catch (Exception exception3) {
               logPaintCharacterLookupError("character-skill", charInMapIndex4, exception3);
               continue;
            }

            if (paintActorValue3 == null) {
               logNullPaintCharacter("character-skill", charInMapIndex4);
            } else if (!GameCanvas.panel.isShow || !GameCanvas.panel.isTypeShop()) {
               this.paintActor = paintActorValue3;
               if (paintActorValue3.skillPaint != null && paintActorValue3.skillInfoPaint() != null && paintActorValue3.indexSkill < paintActorValue3.skillInfoPaint().length) {
                  this.paintActor = paintActorValue3;
                  paintActorValue3.paintCharWithSkill(g);
                  paintActorValue3.paintMount2(g);
               }
            }
         }

         this.paintStage = "item-map";
         this.paintActor = null;

         for (int itemMapIndex = 0; itemMapIndex < vItemMap.size(); itemMapIndex++) {
            ((ItemMap)vItemMap.elementAt(itemMapIndex)).paint(g);
         }

         this.paintStage = "effects-and-hud";
         g.translate(0, -GameCanvas.transY);
         GameCanvas.debug();
         mGraphics g2 = g;

         for (int splashDirIndex = 0; splashDirIndex < 2; splashDirIndex++) {
            if (splashState[splashDirIndex] != -1) {
               if (splashDir[splashDirIndex] == 1) {
                  g2.drawImage(imgSplash[splashF[splashDirIndex]], ((int) (splashX[splashDirIndex])), ((int) (splashY[splashDirIndex])), 3);
               } else {
                  g2.drawRegion(
                     imgSplash[splashF[splashDirIndex]],
                     0,
                     0,
                     mGraphics.getImageWidth(imgSplash[splashF[splashDirIndex]]),
                     mGraphics.getImageHeight(imgSplash[splashF[splashDirIndex]]),
                     2,
                     splashX[splashDirIndex],
                     splashY[splashDirIndex],
                     3
                  );
               }
            }
         }

         GameCanvas.debug();
         GameCanvas.debug();
         GameCanvas.debug();
         g2 = g;

         for (int effect2Index = 0; effect2Index < Effect2.vEffect2.size(); effect2Index++) {
            Effect2 effect2;
            if ((effect2 = (Effect2)Effect2.vEffect2.elementAt(effect2Index)) != null && !(effect2 instanceof ChatPopup)) {
               effect2.paint(g2);
            }
         }

         if (!GameCanvas.lowGraphic) {
            for (int animateEffectIndex = 0; animateEffectIndex < Effect2.vAnimateEffect.size(); animateEffectIndex++) {
               ((Effect2)Effect2.vAnimateEffect.elementAt(animateEffectIndex)).paint(g2);
            }
         }

         for (int effect2OutsideIndex = 0; effect2OutsideIndex < Effect2.vEffect2Outside.size(); effect2OutsideIndex++) {
            ((Effect2)Effect2.vEffect2Outside.elementAt(effect2OutsideIndex)).paint(g2);
         }

         paintBgItem(g, 3);

         for (int npcIndex3 = 0; npcIndex3 < vNpc.size(); npcIndex3++) {
            ((Npc)vNpc.elementAt(npcIndex3)).paintName(g);
         }

         EffecMn.paintLayer3(g);

         for (int npcIndex4 = 0; npcIndex4 < vNpc.size(); npcIndex4++) {
            Npc npc2;
            if ((npc2 = (Npc)vNpc.elementAt(npcIndex4)) != null && npc2.chatInfo != null) {
               npc2.chatInfo
                  .paint(g, npc2.cx, npc2.cy - npc2.ch - GameCanvas.transY, npc2.cdir);
            }
         }

         for (int charInMapIndex5 = 0; charInMapIndex5 < vCharInMap.size(); charInMapIndex5++) {
            Char charInMap2 = null;

            try {
               charInMap2 = (Char)vCharInMap.elementAt(charInMapIndex5);
            } catch (Exception ignoredException) {
            }

            if (charInMap2 != null && charInMap2.chatInfo != null) {
               charInMap2.chatInfo.paint(g, charInMap2.cx, charInMap2.cy - charInMap2.ch, charInMap2.cdir);
            }
         }

         if (Char.myCharz().chatInfo != null) {
            Char.myCharz()
               .chatInfo
               .paint(
                  g,
                  Char.myCharz().cx,
                  Char.myCharz().cy - Char.myCharz().ch,
                  Char.myCharz().cdir
               );
         }

         EffectManager.mid_2Effects.paintAll(g);
         EffectManager.midEffects.paintAll(g);
         BackgroudEffect.paintFrontAll(g);

         for (int currItemIndex = 0; currItemIndex < TileMap.vCurrItem.size(); currItemIndex++) {
            BgItem currItem;
            if ((currItem = (BgItem)TileMap.vCurrItem.elementAt(currItemIndex)).idImage != -1 && currItem.layer > 3) {
               currItem.paint(g);
            }
         }

         PopUp.paintAll(g);
         if (TileMap.mapID == 120) {
            if (this.percentMabu != 100) {
               int width = this.percentMabu * mGraphics.getImageWidth(imgHPLost) / 100;
               g.drawImage(imgHPLost, ((int) (TileMap.pxw / 2 - mGraphics.getImageWidth(imgHPLost) / 2)), ((int) (220)), 0);
               g.setClip(TileMap.pxw / 2 - mGraphics.getImageWidth(imgHPLost) / 2, 220, width, 10);
               g.drawImage(imgHP, ((int) (TileMap.pxw / 2 - mGraphics.getImageWidth(imgHPLost) / 2)), ((int) (220)), 0);
               g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
            }

            if (this.mabuEff) {
               this.tMabuEff++;
               if (GameCanvas.gameTick % 3 == 0) {
                  EffecMn.addEff(
                     new Effect(19, Res.random(TileMap.pxw / 2 - 50, TileMap.pxw / 2 + 50), 340, 2, 1, -1)
                  );
               }

               if (GameCanvas.gameTick % 15 == 0) {
                  EffecMn.addEff(
                     new Effect(
                        18,
                        Res.random(TileMap.pxw / 2 - 5, TileMap.pxw / 2 + 5),
                        Res.random(300, 320),
                        2,
                        1,
                        -1
                     )
                  );
               }

               if (this.tMabuEff == 100) {
                  this.activeSuperPower(TileMap.pxw / 2, 300);
               }

               if (this.tMabuEff == 110) {
                  this.tMabuEff = 0;
                  this.mabuEff = false;
               }
            }
         }

         BackgroudEffect.paintFog(g);
         int width2 = 1;

         for (int bgEffectIndex = 0; bgEffectIndex < BackgroudEffect.vBgEffect.size(); bgEffectIndex++) {
            if (((BackgroudEffect)BackgroudEffect.vBgEffect.elementAt(bgEffectIndex)).typeEff == 0) {
               width2 = 0;
               break;
            }
         }

         if (mGraphics.zoomLevel <= 1) {
            width2 = 0;
         }

         if (width2 != 0 && !this.isRongThanXuatHien) {
            int value;
            if ((value = TileMap.pxw / (mGraphics.getImageWidth(TileMap.imgLight) + 50)) <= 0) {
               value = 1;
            }

            if (TileMap.tileID != 28) {
               for (int index = 0; index < value; index++) {
                  int value2 = 100 + index * (mGraphics.getImageWidth(TileMap.imgLight) + 50) - cmx / 2;
                  int imageWidth = mGraphics.getImageWidth(TileMap.imgLight);
                  if (value2 + imageWidth >= cmx
                     && value2 <= cmx + GameCanvas.w
                     && -20 + mGraphics.getImageHeight(TileMap.imgLight) >= cmy
                     && -20 <= cmy + GameCanvas.h) {
                     g.drawImage(
                        TileMap.imgLight, ((int) (100 + index * (mGraphics.getImageWidth(TileMap.imgLight) + 50) - cmx / 2)), ((int) (-20)), 0
                     );
                  }
               }
            }
         }

         mSystem.paintFlyText(g);
         GameCanvas.debug();
         GameCanvas.debug();
         GameCanvas.debug();
         g2 = g;

         try {
            if (ChatPopup.currChatPopup == null && (width2 = getTaskNpcId()) != -1) {
               Npc npc3 = null;

               for (int npcIndex5 = 0; npcIndex5 < vNpc.size(); npcIndex5++) {
                  Npc npc4;
                  if ((npc4 = (Npc)vNpc.elementAt(npcIndex5)).template.npcTemplateId == width2) {
                     if (npc3 == null) {
                        npc3 = npc4;
                     } else if (Res.abs(npc4.cx - Char.myCharz().cx)
                        < Res.abs(npc3.cx - Char.myCharz().cx)) {
                        npc3 = npc4;
                     }
                  }
               }

               if (npc3 != null
                  && npc3.statusMe != 15
                  && (
                     npc3.cx <= cmx
                        || npc3.cx >= cmx + gW
                        || npc3.cy <= cmy
                        || npc3.cy >= cmy + gH
                  )
                  && GameCanvas.gameTick % 10 >= 5) {
                  int deltaX2 = npc3.cx - Char.myCharz().cx;
                  int deltaY = npc3.cy - Char.myCharz().cy;
                  int x = 0;
                  int y = 0;
                  byte transform = 0;
                  if (deltaX2 > 0 && deltaY >= 0) {
                     if (Res.abs(deltaX2) >= Res.abs(deltaY)) {
                        x = gW - 10;
                        y = gH / 2 + 30;
                        if (GameCanvas.isTouch) {
                           y = gH / 2 + 10;
                        }

                        transform = 0;
                     } else {
                        x = gW / 2;
                        y = gH - 10;
                        transform = 5;
                     }
                  } else if (deltaX2 >= 0 && deltaY < 0) {
                     if (Res.abs(deltaX2) >= Res.abs(deltaY)) {
                        x = gW - 10;
                        y = gH / 2 + 30;
                        if (GameCanvas.isTouch) {
                           y = gH / 2 + 10;
                        }

                        transform = 0;
                     } else {
                        x = gW / 2;
                        y = 10;
                        transform = 6;
                     }
                  }

                  if (deltaX2 < 0 && deltaY >= 0) {
                     if (Res.abs(deltaX2) >= Res.abs(deltaY)) {
                        x = 10;
                        y = gH / 2 + 30;
                        if (GameCanvas.isTouch) {
                           y = gH / 2 + 10;
                        }

                        transform = 3;
                     } else {
                        x = gW / 2;
                        y = gH - 10;
                        transform = 5;
                     }
                  } else if (deltaX2 <= 0 && deltaY < 0) {
                     if (Res.abs(deltaX2) >= Res.abs(deltaY)) {
                        x = 10;
                        y = gH / 2 + 30;
                        if (GameCanvas.isTouch) {
                           y = gH / 2 + 10;
                        }

                        transform = 3;
                     } else {
                        x = gW / 2;
                        y = 10;
                        transform = 6;
                     }
                  }

                  resetTranslate(g2);
                  g2.drawRegion(arrow, 0, 0, 13, 16, transform, x, y, StaticObj.VCENTER_HCENTER);
               }
            }
         } catch (Exception ignoredException2) {
         }

         GameCanvas.debug();
         if (!isPaintOther && isPaintRada == 1 && !GameCanvas.panel.isShow) {
            this.paintInfoBar(g);
         }

         resetTranslate(g);
         g2 = g;
         g.setColor(8421504);
         g2.fillRect(0, GameCanvas.h - 2, GameCanvas.w, 2);
         width2 = (int)(Char.myCharz().cLevelPercent * GameCanvas.w / 10000L);
         g2.setColor(16777215);
         g2.fillRect(0, GameCanvas.h - 2, width2, 2);
         g2.setColor(0);
         width2 = GameCanvas.w / 10;

         for (int index2 = 1; index2 < 10; index2++) {
            g2.fillRect(index2 * width2, GameCanvas.h - 2, 1, 2);
         }

         if (!isPaintOther) {
            if (GameCanvas.open3Hour && TileMap.mapID != 170) {
               if (GameCanvas.w > 250) {
                  g.drawImage(GameCanvas.img18, ((int) (160)), ((int) (6)), 0);
                  mFont.tahoma_7_white.drawString(g, "Chơi quá 180 phút một ngày ", 180, 2, 0);
                  mFont.tahoma_7_white.drawString(g, "sẽ ảnh hưởng xấu đến sức khỏe.", 180, 12, 0);
               } else {
                  g.drawImage(GameCanvas.img18, ((int) (5)), ((int) (GameCanvas.h - 67)), 0);
                  mFont.tahoma_7_white
                     .drawString(g, "Chơi quá 180 phút một ngày sẽ ảnh hưởng xấu đến sức khỏe.", 25, GameCanvas.h - 70, 0);
               }
            }

            GameCanvas.debug();
            GameCanvas.debug();
            g.translate(-g.getTranslateX(), -g.getTranslateY());
            if ((TileMap.mapID == 128 || TileMap.mapID == 127) && mabuPercent != 0) {
               g.setColor(0);
               g.fillRect(3, 88, 54, 8);
               g.setColor(16711680);
               g.setClip(5, 90, mabuPercent, 4);
               g.fillRect(5, 90, 50, 4);
               g.setClip(0, 0, 3000, 3000);
               mFont.tahoma_7b_white.drawString(g, "Mabu", 30, 98, 2, mFont.tahoma_7b_dark);
            }

            if (Char.myCharz().isFusion) {
               Char.myCharz().tFusion++;
               if (GameCanvas.gameTick % 3 == 0) {
                  g.setColor(16777215);
                  g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
               }

               if (Char.myCharz().tFusion >= 100) {
                  Char.myCharz().fusionComplete();
               }
            }

            for (int charInMapIndex6 = 0; charInMapIndex6 < vCharInMap.size(); charInMapIndex6++) {
               Char c = null;

               try {
                  c = (Char)vCharInMap.elementAt(charInMapIndex6);
               } catch (Exception ignoredException3) {
               }

               if (c != null && c.isFusion && Char.isCharInScreen(c)) {
                  c.tFusion++;
                  if (GameCanvas.gameTick % 3 == 0) {
                     g.setColor(16777215);
                     g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
                  }

                  if (c.tFusion >= 100) {
                     c.fusionComplete();
                  }
               }
            }

            Paint.paintTabSoft();
            GameCanvas.debug();
            GameCanvas.debug();
            resetTranslate(g);
            this.paintSelectedSkill(g);
            GameCanvas.debug();
            resetTranslate(g);
            if (GameCanvas.isTouch && GameCanvas.isTouchControl) {
               mGraphics g3 = g;
               GameScr gameScreen2 = this;
               if (!this.isNotPaintTouchControl()) {
                  resetTranslate(g3);
                  if (!TileMap.isOfflineMap() && !gameScreen2.isVS()) {
                     if (mScreen.keyTouch == 15) {
                        g3.drawImage(imgChat2, ((int) (xC + 17)), ((int) (yC + 17)), 3);
                     } else {
                        g3.drawImage(imgChat, ((int) (xC + 17)), ((int) (yC + 17)), 3);
                     }
                  }
               }
            }

            resetTranslate(g);
            mGraphics graphics = g;
            GameScr gameScreen3 = this;
            if (vChatVip.size() != 0 && isPaintChatVip) {
               graphics.setClip(0, GameCanvas.h - 13, GameCanvas.w, 15);
               graphics.fillRect(0, GameCanvas.h - 13, GameCanvas.w, 15, 0);
               String text = (String)vChatVip.elementAt(0);
               mFont.tahoma_7b_yellowSmall.drawString(graphics, text, gameScreen3.xChatVip, GameCanvas.h - 12, 0);
            }

            if (!GameCanvas.panel.isShow
               && GameCanvas.currentDialog == null
               && ChatPopup.currChatPopup == null
               && ChatPopup.serverChatPopUp == null
               && GameCanvas.currentScreen.equals(instance)) {
               super.paint(g);
            }

            resetTranslate(g);
            int value3 = 100 + (Char.vItemTime.size() != 0 ? textTime.size() * 12 : 0);
            if (Char.myCharz().clan != null) {
               int index3 = 0;
               int index4 = 0;
               int value4 = (GameCanvas.h - 100 - 60) / 12;

               for (int charInMapIndex7 = 0; charInMapIndex7 < vCharInMap.size(); charInMapIndex7++) {
                  Char charInMap3;
                  if ((charInMap3 = (Char)vCharInMap.elementAt(charInMapIndex7)).clanID != -1
                     && charInMap3.clanID == Char.myCharz().clan.ID) {
                     if (charInMap3.isOutX() && charInMap3.cx < Char.myCharz().cx) {
                        int value5 = value4;
                        if (Char.vItemTime.size() != 0) {
                           value5 -= textTime.size();
                        }

                        if (index3 <= value5) {
                           mFont.tahoma_7_green
                              .drawString(g, charInMap3.cName, 20, value3 - 12 + index3 * 12, 0, mFont.tahoma_7_grey);
                           charInMap3.paintHp(g, 10, value3 + index3 * 12 - 5);
                           index3++;
                        }
                     } else if (charInMap3.isOutX() && charInMap3.cx > Char.myCharz().cx && index4 <= value4) {
                        mFont.tahoma_7_green
                           .drawString(
                              g, charInMap3.cName, GameCanvas.w - 25, value3 - 12 + index4 * 12, 1, mFont.tahoma_7_grey
                           );
                        charInMap3.paintHp(g, GameCanvas.w - 15, value3 + index4 * 12 - 5);
                        index4++;
                     }
                  }
               }
            }

            ChatTextField.gI().paint(g);
            if (isNewClanMessage && !GameCanvas.panel.isShow && GameCanvas.gameTick % 4 == 0) {
               g.drawImage(
                  ItemMap.imageFlare, ((int) (this.cmdMenu.x + 15)), ((int) (this.cmdMenu.y + 30)), 33
               );
            }

            if (this.isSuperPower) {
               this.dxPower += 5;
               if (this.tPower >= 0) {
                  this.tPower = this.tPower + this.dxPower;
               }

               if (this.tPower < 0) {
                  this.tPower--;
                  if (this.tPower == -20) {
                     this.isSuperPower = false;
                     this.tPower = 0;
                     this.dxPower = 0;
                  }
               } else if ((this.xPower - this.tPower > 0 || this.tPower < TileMap.pxw)
                  && this.tPower > 0) {
                  g.setColor(16777215);
                  if (!GameCanvas.lowGraphic && mSystem.clientType != 3 && mSystem.clientType != 5) {
                     g.fillArg(
                        this.xPower - this.tPower,
                        this.yPower - this.tPower,
                        2 * this.tPower,
                        2 * this.tPower,
                        0,
                        360
                     );
                  } else {
                     g.fillRect(0, 0, GameCanvas.w, GameCanvas.h);
                  }
               } else {
                  this.tPower = -1;
               }
            }

            for (int itemTimeIndex = 0; itemTimeIndex < Char.vItemTime.size(); itemTimeIndex++) {
               ItemTime itemTime = (ItemTime)Char.vItemTime.elementAt(itemTimeIndex);
               int value6 = this.cmdMenu.x + 32 + itemTimeIndex * 24;
               byte byteValue = 55;
               width2 = value6;
               graphics = g;
               ItemTime itemTime2 = itemTime;
               SmallImage.drawSmallImage(graphics, itemTime2.idIcon, width2, 55, 0, 3);
               String st = itemTime2.minute + "'";
               if (itemTime2.minute == 0) {
                  st = itemTime2.second + "s";
               }

               mFont.tahoma_7b_white.drawString(graphics, st, width2, 70, 2, mFont.tahoma_7b_dark);
            }

            for (int textTimeIndex = 0; textTimeIndex < textTime.size(); textTimeIndex++) {
               ((ItemTime)textTime.elementAt(textTimeIndex))
                  .paintText(
                     g,
                     this.cmdMenu.x + (Char.vItemTime.size() != 0 ? 5 : 25),
                     (Char.vItemTime.size() != 0 ? 90 : 45) + textTimeIndex * 12
                  );
            }

            this.paintXoSo(g);
            if (mResources.language == 1) {
               long elapsedMillis = mSystem.currentTimeMillis() - deltaTime;
               mFont tahoma_7b_white = mFont.tahoma_7b_white;
               long elapsedMillis2 = elapsedMillis;
               Calendar instanceValue;
               (instanceValue = Calendar.getInstance()).setTime(new Date(elapsedMillis2 + 25200000L));
               value3 = instanceValue.get(11);
               int value7 = instanceValue.get(12);
               tahoma_7b_white.drawString(g, value3 + "h" + value7 + "m", 10, GameCanvas.h - 65, 0, mFont.tahoma_7b_dark);
            }

            if (!this.yourNumber.equals("")) {
               for (int strPaintIndex = 0; strPaintIndex < this.strPaint.length; strPaintIndex++) {
                  mFont.tahoma_7b_white.drawString(g, this.strPaint[strPaintIndex], 5, 85 + strPaintIndex * 18, 0, mFont.tahoma_7b_dark);
               }
            }
         }

         int w = GameCanvas.hw;
         if (GameCanvas.hw > 200) {
            w = 200;
         }

         paintPhuBanBar(g, 0 + GameCanvas.w / 2, 0, w);
         EffectManager.hiEffects.paintAll(g);
         if (nCT_timeBallte > mSystem.currentTimeMillis() && TileMap.mapID == 170 && isPaint_CT && nCT_nBoyBaller / 2 > 0) {
            try {
               this.paint_CT(g, 0 + GameCanvas.w / 2, 0);
            } catch (NullPointerException nullPointerException) {
               long lastPaintCtErrorLogAtValue;
               if ((lastPaintCtErrorLogAtValue = System.currentTimeMillis()) - lastPaintCtErrorLogAt >= 1000L) {
                  lastPaintCtErrorLogAt = lastPaintCtErrorLogAtValue;
                  System.err.println("[PAINT-NPE] GameScr.paint stage=paint_CT tick=" + GameCanvas.gameTick + " map=" + TileMap.mapID);
                  nullPointerException.printStackTrace();
               }
            } catch (Exception ignoredException4) {
            }
         }

         if (TileMap.mapID == 172) {
            new StringBuffer(String.valueOf(mResources.WAIT))
               .append("  ")
               .append(nUSER_CT)
               .append("/")
               .append(nUSER_MAX_CT)
               .toString();
            mFont.tahoma_7b_dark
               .drawString(
                  g, mResources.WAIT + "  " + nUSER_CT + "/" + nUSER_MAX_CT, GameCanvas.w - 10, 40, 1
               );
         }
      }
   }

   private void paintXoSo(mGraphics g) {
      if (this.tShow != 0) {
         String text = "";

         for (int randomNumberIndex = 0; randomNumberIndex < this.winnumber.length; randomNumberIndex++) {
            text = text + this.randomNumber[randomNumberIndex] + " ";
         }

         PopUp.paintPopUp(g, 20, 45, 95, 35, 16777215, false);
         mFont.tahoma_7b_dark.drawString(g, mResources.kquaVongQuay, 68, 50, 2);
         mFont.tahoma_7b_dark.drawString(g, text, 68, 65, 2);
      }
   }

   public static Npc findNPCInMap(short id) {
      for (int npcIndex = 0; npcIndex < vNpc.size(); npcIndex++) {
         Npc npc;
         if ((npc = (Npc)vNpc.elementAt(npcIndex)).template.npcTemplateId == id) {
            return npc;
         }
      }

      return null;
   }

   public static Char findCharInMap(int charId) {
      for (int charInMapIndex = 0; charInMapIndex < vCharInMap.size(); charInMapIndex++) {
         Char charInMap;
         if ((charInMap = (Char)vCharInMap.elementAt(charInMapIndex)).charID == charId) {
            return charInMap;
         }
      }

      return null;
   }

   public static Mob findMobInMap(int mobId) {
      for (int mobIndex = 0; mobIndex < vMob.size(); mobIndex++) {
         Mob mob;
         if ((mob = (Mob)vMob.elementAt(mobIndex)).mobId == mobId) {
            return mob;
         }
      }

      return null;
   }

   public static Mob findMobInMap(byte mobIndex) {
      return (Mob)vMob.elementAt(mobIndex);
   }

   public static Npc getNpcTask() {
      for (int npcIndex = 0; npcIndex < vNpc.size(); npcIndex++) {
         Npc npc;
         if ((npc = (Npc)vNpc.elementAt(npcIndex)).template.npcTemplateId == getTaskNpcId()) {
            return npc;
         }
      }

      return null;
   }

   public static void resetTranslate(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      g.setClip(0, -200, GameCanvas.w, 200 + GameCanvas.h);
   }

   private void paintImageBarRight(mGraphics g, Char c) {
      int width = (int)(c.cHP * hpBarW / c.cHPFull);
      int width2 = (int)(c.cMP * mpBarW / c.cMPFull);
      int width3 = (int)(this.dHP * hpBarW / c.cHPFull);
      int width4 = (int)(this.dMP * mpBarW / c.cMPFull);
      g.setClip(GameCanvas.w / 2 + 58 - mGraphics.getImageWidth(imgPanel), 0, 95, 100);
      g.drawRegionAtLeftEdge(
         imgPanel,
         0,
         0,
         mGraphics.getImageWidth(imgPanel),
         mGraphics.getImageHeight(imgPanel),
         2,
         GameCanvas.w / 2 + 60,
         0,
         24
      );
      g.setClip((int)(GameCanvas.w / 2 + 60 - 83 - hpBarW + hpBarW - width3), 5, width3, 10);
      g.drawImage(imgHPLost, ((int) (GameCanvas.w / 2 + 60 - 83)), ((int) (5)), 24);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      g.setClip((int)(GameCanvas.w / 2 + 60 - 83 - hpBarW + hpBarW - width), 5, width, 10);
      g.drawImage(imgHP, ((int) (GameCanvas.w / 2 + 60 - 83)), ((int) (5)), 24);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      g.setClip((int)(GameCanvas.w / 2 + 60 - 83 - mpBarW + hpBarW - width4), 20, width4, 6);
      g.drawImage(imgMPLost, ((int) (GameCanvas.w / 2 + 60 - 83)), ((int) (20)), 24);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      g.setClip((int)(GameCanvas.w / 2 + 60 - 83 - mpBarW + hpBarW - width2), 20, width2, 6);
      g.drawImage(imgMP, ((int) (GameCanvas.w / 2 + 60 - 83)), ((int) (20)), 24);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
   }

   private void paintImageBar(mGraphics g, boolean isLeft, Char c) {
   int intSlot2;
   if (c != null) {
         int width;
         int width2;
         if (c.charID == Char.myCharz().charID) {
            intSlot2 = (int)(this.dHP * hpBarW / c.cHPFull);
            width = (int)(this.dMP * mpBarW / c.cMPFull);
            width2 = (int)(c.cHP * hpBarW / c.cHPFull);
         } else {
            intSlot2 = (int)(c.dHP * hpBarW / c.cHPFull);
            width = c.perCentMp * mpBarW / 100;
            width2 = (int)(c.cHP * hpBarW / c.cHPFull);
         }

         if (Char.myCharz().secondPower > 0) {
            int width3 = Char.myCharz().powerPoint * spBarW / Char.myCharz().maxPowerPoint;
            g.drawImage(imgPanel2, ((int) (58)), ((int) (29)), 0);
            g.setClip(83, 31, width3, 10);
            g.drawImage(imgSP, ((int) (83)), ((int) (31)), 0);
            g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
            mFont.tahoma_7_white
               .drawString(
                  g,
                  Char.myCharz().strInfo
                     + ":"
                     + Char.myCharz().powerPoint
                     + "/"
                     + Char.myCharz().maxPowerPoint,
                  115,
                  29,
                  2
               );
         }

         if (c.charID != Char.myCharz().charID) {
            g.setClip(mGraphics.getImageWidth(imgPanel) - 95, 0, 95, 100);
         }

         g.drawImage(imgPanel, ((int) (0)), ((int) (0)), 0);
         g.setClip(83, 5, intSlot2, 10);
         g.drawImage(imgHPLost, ((int) (83)), ((int) (5)), 0);
         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         g.setClip(83, 5, width2, 10);
         g.drawImage(imgHP, ((int) (83)), ((int) (5)), 0);
         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         g.setClip(83, 20, width, 6);
         g.drawImage(imgMPLost, ((int) (83)), ((int) (20)), 0);
         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         g.setClip(83, 20, width, 6);
         g.drawImage(imgMP, ((int) (83)), ((int) (20)), 0);
         g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         if (Char.myCharz().cMP == 0L && GameCanvas.gameTick % 10 > 5) {
            g.setClip(83, 20, 2, 6);
            g.drawImage(imgMPLost, ((int) (83)), ((int) (20)), 0);
            g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
         }
      }
   }

   public final void starVS() {
      this.curr = this.last = System.currentTimeMillis();
      this.secondVS = 180;
   }

   private static Char findCharVS1() {
      for (int charInMapIndex = 0; charInMapIndex < vCharInMap.size(); charInMapIndex++) {
         Char charInMap;
         if ((charInMap = (Char)vCharInMap.elementAt(charInMapIndex)).cTypePk != 0) {
            return charInMap;
         }
      }

      return null;
   }

   private Char findCharVS2() {
      for (int charInMapIndex = 0; charInMapIndex < vCharInMap.size(); charInMapIndex++) {
         Char charInMap;
         if ((charInMap = (Char)vCharInMap.elementAt(charInMapIndex)).cTypePk != 0 && charInMap != findCharVS1()) {
            return charInMap;
         }
      }

      return null;
   }

   private void paintInfoBar(mGraphics graphics) {
      if (!Char.isLoadingMap) {
         resetTranslate(graphics);
         if (TileMap.mapID == 130 && findCharVS1() != null && this.findCharVS2() != null) {
            graphics.translate(GameCanvas.w / 2 - 62, 0);
            this.paintImageBar(graphics, true, findCharVS1());
            graphics.translate(-(GameCanvas.w / 2 - 65), 0);
            this.paintImageBarRight(graphics, this.findCharVS2());
            findCharVS1().paintHeadWithXY(graphics, 137, 25, 0);
            this.findCharVS2().paintHeadWithXY(graphics, GameCanvas.w - 15 - 122, 25, 2);
         } else if (this.isVS() && Char.myCharz().charFocus != null) {
            graphics.translate(GameCanvas.w / 2 - 62, 0);
            this.paintImageBar(graphics, true, Char.myCharz().charFocus);
            graphics.translate(-(GameCanvas.w / 2 - 65), 0);
            this.paintImageBarRight(graphics, Char.myCharz());
            Char.myCharz().paintHeadWithXY(graphics, 137, 25, 0);
            Char.myCharz().charFocus.paintHeadWithXY(graphics, GameCanvas.w - 15 - 122, 25, 2);
         } else if (ispaintPhubangBar() && GameCanvas.w <= 320) {
            Char player = Char.myCharz();
            boolean condition = true;
            condition = true;
            mGraphics graphics2 = graphics;
            graphics.drawImage(imgKhung, ((int) (1)), ((int) (1)), 0);
            int imageWidth = imgHP_NEW.getWidth();
            int height = imgHP_NEW.getHeight() / 2;
            int width;
            if ((width = (int)(player.cHP * imageWidth / player.cHPFull)) <= 0) {
               width = 1;
            } else if (width > imageWidth) {
               width = imageWidth;
            }

            graphics2.drawRegion(imgHP_NEW, 0, height, width, height, 0, 4, 20, 0);
            if ((width = (int)(player.cMP * imageWidth / player.cMPFull)) <= 0) {
               width = 1;
            } else if (width > imageWidth) {
               width = imageWidth;
            }

            graphics2.drawRegion(imgHP_NEW, 0, 0, width, height, 0, 4, 26, 0);
            width = 1 + imgKhung.getWidth() / 2 + 1;
            mFont.tahoma_7_green2.drawString(graphics2, player.cName, width, 5, 2);
            if (player.mobFocus != null) {
               if (player.mobFocus.getTemplate() != null) {
                  mFont.tahoma_7_green2.drawString(graphics2, player.mobFocus.getTemplate().name, width, 33, 2);
               }
            } else if (player.npcFocus != null) {
               mFont.tahoma_7_green2.drawString(graphics2, player.npcFocus.template.name, width, 33, 2);
            } else if (player.charFocus != null) {
               mFont.tahoma_7_green2.drawString(graphics2, player.charFocus.cName, width, 33, 2);
            }
         } else {
            this.paintImageBar(graphics, true, Char.myCharz());
            if (Char.myCharz().isInEnterOfflinePoint() != null || Char.myCharz().isInEnterOnlinePoint() != null) {
               mFont.tahoma_7_green2.drawString(graphics, mResources.enter, this.imgScrW / 2, 8, 2);
            } else if (Char.myCharz().mobFocus != null) {
               if (Char.myCharz().mobFocus.getTemplate() != null) {
                  int x = this.imgScrW / 2;
                  mFont.tahoma_7_green2
                     .drawString(graphics, Char.myCharz().mobFocus.getTemplate().name, x, 9, 2);
               }

               if (Char.myCharz().mobFocus.templateId != 0) {
                  mFont.tahoma_7_green2
                     .drawString(
                        graphics, Firework.getMoneys(Char.myCharz().mobFocus.hp), this.imgScrW / 2, 22, 2
                     );
               }
            } else if (Char.myCharz().npcFocus != null) {
               int x2 = this.imgScrW / 2;
               mFont.tahoma_7_green2.drawString(graphics, Char.myCharz().npcFocus.template.name, x2, 9, 2);
               if (Char.myCharz().npcFocus.template.npcTemplateId == 4) {
                  mFont.tahoma_7_green2
                     .drawString(
                        graphics,
                        gI().magicTree.currPeas + "/" + gI().magicTree.maxPeas,
                        this.imgScrW / 2,
                        22,
                        2
                     );
               }
            } else if (Char.myCharz().charFocus != null) {
               int x3 = this.imgScrW / 2;
               mFont.tahoma_7_green2.drawString(graphics, Char.myCharz().charFocus.cName, x3, 9, 2);
               mFont.tahoma_7_green2
                  .drawString(
                     graphics, Firework.getMoneys(Char.myCharz().charFocus.cHP), this.imgScrW / 2, 22, 2
                  );
            } else {
               int x4 = this.imgScrW / 2;
               mFont.tahoma_7_green2.drawString(graphics, Char.myCharz().cName, x4, 9, 2);
               mFont.tahoma_7_green2
                  .drawString(graphics, Firework.getMoneys(Char.myCharz().cPower), this.imgScrW / 2, 22, 2);
            }
         }

         graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
         if (this.isVS() && this.secondVS > 0) {
            this.curr = System.currentTimeMillis();
            if (this.curr - this.last >= 1000L) {
               this.last = System.currentTimeMillis();
               this.secondVS--;
            }

            mFont.tahoma_7b_white
               .drawString(graphics, String.valueOf(this.secondVS), GameCanvas.w / 2, 13, 2, mFont.tahoma_7b_dark);
         }

         if (this.flareFindFocus) {
            graphics.drawImage(ItemMap.imageFlare, ((int) (40)), ((int) (35)), 33);
            this.flareTime--;
            if (this.flareTime < 0) {
               this.flareTime = 0;
               this.flareFindFocus = false;
            }
         }
      }
   }

   public final boolean isVS() {
      return TileMap.isVoDaiMap()
         && (
            Char.myCharz().cTypePk != 0
               || TileMap.mapID == 130 && findCharVS1() != null && this.findCharVS2() != null
         );
   }

   private void paintSelectedSkill(mGraphics g) {
      if (this.mobCapcha != null) {
         mGraphics graphics = g;
         GameScr gameScreen = this;
         MobCapcha.paint(graphics, Char.myCharz().cx, Char.myCharz().cy);
         graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
         if (!GameCanvas.menu.showMenu
            && !GameCanvas.panel.isShow
            && ChatPopup.currChatPopup == null
            && GameCanvas.isTouch) {
            for (int characterIndex = 0; characterIndex < gameScreen.strCapcha.length(); characterIndex++) {
               int x = (GameCanvas.w - gameScreen.strCapcha.length() * disXC) / 2
                  + characterIndex * disXC
                  + disXC / 2;
               if (gameScreen.keyCapcha[characterIndex] == -1) {
                  graphics.drawImage(imgNut, ((int) (x)), ((int) (GameCanvas.h - 25)), 3);
                  mFont.tahoma_7b_dark
                     .drawString(graphics, String.valueOf(gameScreen.strCapcha.charAt(characterIndex)), x, GameCanvas.h - 30, 2);
               } else {
                  graphics.drawImage(imgNutF, ((int) (x)), ((int) (GameCanvas.h - 25)), 3);
                  mFont.tahoma_7b_green2
                     .drawString(graphics, String.valueOf(gameScreen.strCapcha.charAt(characterIndex)), x, GameCanvas.h - 30, 2);
               }
            }
         }
      } else if (GameCanvas.currentDialog == null
         && ChatPopup.currChatPopup == null
         && !GameCanvas.menu.showMenu
         && !GameCanvas.panel.isShow
         && Char.myCharz().taskMaint.taskId != 0
         && !ChatTextField.gI().isShow
         && GameCanvas.currentScreen != MoneyCharge.instance) {
         long elapsedMillis = mSystem.currentTimeMillis() - this.lastUsePotion;
         int value = 0;
         if (elapsedMillis < 10000L) {
            value = (int)(elapsedMillis * 20L / 10000L);
         }

         if (!GameCanvas.isTouch) {
            g.drawImage(
               mScreen.keyTouch == 10 ? imgSkill2 : imgSkill, ((int) (xSkill + xHP - 1)), ((int) (yHP - 1)), 0
            );
            SmallImage.drawSmallImage(g, 542, xSkill + xHP + 3, yHP + 3, 0, 0);
            mFont.number_gray.drawString(g, "" + hpPotion, xSkill + xHP + 22, yHP + 15, 1);
            if (elapsedMillis < 10000L) {
               g.setColor(2721889);
               value = (int)(elapsedMillis * 20L / 10000L);
               g.fillRect(xSkill + xHP + 3, yHP + 3 + value, 20, 20 - value);
            }
         } else if (Char.myCharz().statusMe != 14) {
            if (gamePad.isSmallGamePad) {
               if (isAnalog != 1) {
                  g.setColor(9670800);
                  g.fillRect(xHP + 9, yHP + 10 + 10, 22, 20);
                  g.setColor(16777215);
                  g.fillRect(xHP + 9, yHP + 10 + (value != 0 ? 20 - value : 0) + 10, 22, value != 0 ? value : 20);
                  g.drawImage(
                     mScreen.keyTouch == 10 ? imgHP2 : imgHP1, ((int) (xHP)), ((int) (yHP + 10)), 0
                  );
                  mFont.tahoma_7_red.drawString(g, "" + hpPotion, xHP + 20, yHP + 15 + 10, 2);
                  if (isPickNgocRong) {
                     g.drawImage(
                        mScreen.keyTouch == 14 ? imgNR2 : imgNR1, ((int) (xHP + 5)), ((int) (yHP - 6 - 40 + 10)), 0
                     );
                  } else if (isudungCapsun4) {
                     g.drawImage(
                        mScreen.keyTouch == 14 ? imgNutF : imgNut, ((int) (xHP + 5)), ((int) (yHP - 6 - 40 + 10)), 0
                     );
                     SmallImage.drawSmallImage(g, 1088, xHP - 7 + 5, yHP - 6 - 40 - 7 + 10, 0, 0);
                  } else if (isudungCapsun3) {
                     g.drawImage(
                        mScreen.keyTouch == 14 ? imgNutF : imgNut, ((int) (xHP + 5)), ((int) (yHP - 6 - 40 + 10)), 0
                     );
                     SmallImage.drawSmallImage(g, 1087, xHP - 7 + 5, yHP - 6 - 40 - 7 + 10, 0, 0);
                  }
               } else if (isAnalog == 1) {
                  g.drawImage(
                     mScreen.keyTouch == 10 ? imgSkill2 : imgSkill,
                     ((int) (xSkill + xHP - 1)),
                     ((int) (yHP - 1 + 10)),
                     0
                  );
                  SmallImage.drawSmallImage(g, 542, xSkill + xHP + 3, yHP + 3 + 10, 0, 0);
                  mFont.number_gray
                     .drawString(g, "" + hpPotion, xSkill + xHP + 22, yHP + 13 + 10, 1);
                  if (elapsedMillis < 10000L) {
                     g.setColor(2721889);
                     value = (int)(elapsedMillis * 20L / 10000L);
                     g.fillRect(xSkill + xHP + 3, yHP + 3 + value + 10, 20, 20 - value);
                  }

                  if (isPickNgocRong) {
                     g.drawImage(
                        mScreen.keyTouch == 14 ? imgNR4 : imgNR3,
                        ((int) (xHP + 20 + 5)),
                        ((int) (yHP + 20 - 6 - 40 + 10)),
                        3
                     );
                  } else if (isudungCapsun4) {
                     g.drawImage(
                        mScreen.keyTouch == 14 ? imgNutF : imgNut,
                        ((int) (xHP + 20 + 5)),
                        ((int) (yHP + 20 - 6 - 40 + 10)),
                        3
                     );
                     SmallImage.drawSmallImage(g, 1088, xHP + 20 - 7 + 5, yHP + 20 - 6 - 40 - 7 + 10, 0, 0);
                  } else if (isudungCapsun3) {
                     g.drawImage(
                        mScreen.keyTouch == 14 ? imgNutF : imgNut,
                        ((int) (xHP + 20 + 5)),
                        ((int) (yHP + 20 - 6 - 40 + 10)),
                        3
                     );
                     SmallImage.drawSmallImage(g, 1087, xHP + 20 - 7 + 5, yHP + 20 - 6 - 40 - 7 + 10, 0, 0);
                  }
               }
            } else if (isAnalog != 1) {
               g.setColor(9670800);
               g.fillRect(xHP + 9, yHP + 10 - 6, 22, 20);
               g.setColor(16777215);
               g.fillRect(xHP + 9, yHP + 10 + (value != 0 ? 20 - value : 0) - 6, 22, value != 0 ? value : 20);
               g.drawImage(mScreen.keyTouch == 10 ? imgHP2 : imgHP1, ((int) (xHP)), ((int) (yHP - 6)), 0);
               mFont.tahoma_7_red.drawString(g, "" + hpPotion, xHP + 20, yHP + 15 - 6, 2);
               if (isPickNgocRong) {
                  g.drawImage(
                     mScreen.keyTouch == 14 ? imgNR2 : imgNR1, ((int) (xHP)), ((int) (yHP - 6 - 40)), 0
                  );
               } else if (isudungCapsun4) {
                  g.drawImage(
                     mScreen.keyTouch == 14 ? imgNutF : imgNut, ((int) (xHP + 20)), ((int) (yHP + 20 - 6 - 40)), 3
                  );
                  SmallImage.drawSmallImage(g, 1088, xHP + 20 - 7, yHP + 20 - 6 - 40 - 7, 0, 0);
               } else if (isudungCapsun3) {
                  g.drawImage(
                     mScreen.keyTouch == 14 ? imgNutF : imgNut, ((int) (xHP + 20)), ((int) (yHP + 20 - 6 - 40)), 3
                  );
                  SmallImage.drawSmallImage(g, 1087, xHP + 20 - 7, yHP + 20 - 6 - 40 - 7, 0, 0);
               }
            } else {
               g.setColor(9670800);
               g.fillRect(xHP + 10, yHP + 10 - 6 + 10, 20, 18);
               g.setColor(16777215);
               g.fillRect(xHP + 10, yHP + 10 + (value != 0 ? 20 - value : 0) - 6 + 10, 20, value != 0 ? value : 18);
               g.drawImage(
                  mScreen.keyTouch == 10 ? imgHP4 : imgHP3, ((int) (xHP + 20)), ((int) (yHP + 20 - 6 + 10)), 3
               );
               mFont.tahoma_7_red.drawString(g, "" + hpPotion, xHP + 20, yHP + 15 - 6 + 10, 2);
               if (isPickNgocRong) {
                  g.drawImage(
                     mScreen.keyTouch == 14 ? imgNR4 : imgNR3,
                     ((int) (xHP + 20 + 5)),
                     ((int) (yHP + 20 - 6 - 40 + 10)),
                     3
                  );
               } else if (isudungCapsun4) {
                  g.drawImage(
                     mScreen.keyTouch == 14 ? imgNutF : imgNut,
                     ((int) (xHP + 20 + 5)),
                     ((int) (yHP + 20 - 6 - 40 + 10)),
                     3
                  );
                  SmallImage.drawSmallImage(g, 1088, xHP + 20 - 7 + 5, yHP + 20 - 6 - 40 - 7 + 10, 0, 0);
               } else if (isudungCapsun3) {
                  g.drawImage(
                     mScreen.keyTouch == 14 ? imgNutF : imgNut,
                     ((int) (xHP + 20 + 5)),
                     ((int) (yHP + 20 - 6 - 40 + 10)),
                     3
                  );
                  SmallImage.drawSmallImage(g, 1087, xHP + 20 - 7 + 5, yHP + 20 - 6 - 40 - 7 + 10, 0, 0);
               }
            }
         }

         if (isHaveSelectSkill) {
            Skill[] skills = GameCanvas.isTouch ? onScreenSkill : keySkill;
            if (!GameCanvas.isTouch) {
               g.setColor(11152401);
               g.fillRect(xSkill + xHP + 2, yHP - 10 + 6, 20, 10);
               mFont.tahoma_7_white.drawString(g, "*", xSkill + xHP + 12, yHP - 8 + 6, 2);
            }

            int value2 = GameCanvas.isTouch ? this.nSkill : skills.length;

            for (int index = 0; index < value2; index++) {
               if (!GameCanvas.isTouch) {
                  String[] texts = TField.isQwerty ? new String[]{"Q", "W", "E", "R", "T"} : new String[]{"7", "8", "9", "1", "3"};
                  g.setColor(11152401);
                  g.fillRect(xSkill + xS[index] + 2, yS[index] - 10 + 8, 20, 10);
                  mFont.tahoma_7_white
                     .drawString(g, texts[index], xSkill + xS[index] + 12, yS[index] - 10 + 6, 2);
               }

               Skill skill;
               if ((skill = skills[index]) != Char.myCharz().myskill) {
                  g.drawImage(imgSkill, ((int) (xSkill + xS[index] - 1)), ((int) (yS[index] - 1)), 0);
               }

               if (skill != null) {
                  if (skill == Char.myCharz().myskill) {
                     g.drawImage(imgSkill2, ((int) (xSkill + xS[index] - 1)), ((int) (yS[index] - 1)), 0);
                     if (GameCanvas.isTouch) {
                        g.drawRegion(
                           Mob.imgHP, 0, 12, 9, 6, 0, xSkill + xS[index] + 8, yS[index] - 7, 0
                        );
                     }
                  }

                  int value3 = xSkill + xS[index] + 13;
                  int value4 = yS[index] + 13;
                  mGraphics g2 = g;
                  int y = value4;
                  int x2 = value3;
                  Skill skill2 = skill;
                  SmallImage.drawSmallImage(g2, skill2.template.iconId, x2, y, 0, StaticObj.VCENTER_HCENTER);
                  long elapsedMillis2;
                  if ((elapsedMillis2 = System.currentTimeMillis() - skill2.lastTimeUseThisSkill) < skill2.coolDown) {
                     g2.setColor(2721889);
                     if (skill2.paintCanNotUseSkill && GameCanvas.gameTick % 6 > 2) {
                        g2.setColor(876862);
                     }

                     int value5 = (int)(elapsedMillis2 * 20L / skill2.coolDown);
                     g2.fillRect(x2 - 10, y - 10 + value5, 20, 20 - value5);
                  } else {
                     skill2.paintCanNotUseSkill = false;
                  }

                  if (index == this.selectedIndexSkill && GameCanvas.gameTick % 10 > 5 || index == this.keyTouchSkill) {
                     g.drawImage(ItemMap.imageFlare, ((int) (xSkill + xS[index] + 13)), ((int) (yS[index] + 14)), 3);
                  }
               }
            }
         }

         mGraphics g3 = g;
         if (isAnalog != 0 && Char.myCharz().statusMe != 14) {
            g3.drawImage(
               mScreen.keyTouch == 5 ? imgFire1 : imgFire0, ((int) (xF + 20)), ((int) (yF + 20)), 3
            );
            gamePad.paint(g3);
            g3.drawImage(
               mScreen.keyTouch == 13 ? imgFocus2 : imgFocus, ((int) (xTG + 20)), ((int) (yTG + 20)), 3
            );
         }
      }
   }

   public static final void startFlyText(String flyString, int x, int y, int dx, int dy, int color) {
      int flyTextColorIndex = -1;

      for (int flyTextStateIndex = 0; flyTextStateIndex < 5; flyTextStateIndex++) {
         if (flyTextState[flyTextStateIndex] == -1) {
            flyTextColorIndex = flyTextStateIndex;
            break;
         }
      }

      if (flyTextColorIndex != -1) {
         flyTextColor[flyTextColorIndex] = color;
         flyTextString[flyTextColorIndex] = flyString;
         flyTextX[flyTextColorIndex] = x;
         flyTextY[flyTextColorIndex] = y;
         flyTextDx[flyTextColorIndex] = 0;
         flyTextDy[flyTextColorIndex] = dy < 0 ? -5 : 5;
         flyTextState[flyTextColorIndex] = 0;
         flyTime[flyTextColorIndex] = 0;
         flyTextYTo[flyTextColorIndex] = 10;

         for (int flyTextStateIndex2 = 0; flyTextStateIndex2 < 5; flyTextStateIndex2++) {
            if (flyTextState[flyTextStateIndex2] != -1
               && flyTextColorIndex != flyTextStateIndex2
               && flyTextDy[flyTextColorIndex] < 0
               && Res.abs(flyTextX[flyTextColorIndex] - flyTextX[flyTextStateIndex2]) <= 20
               && flyTextYTo[flyTextColorIndex] == flyTextYTo[flyTextStateIndex2]) {
               flyTextYTo[flyTextColorIndex] = flyTextYTo[flyTextColorIndex] + 10;
            }
         }
      }
   }

   public static final boolean startSplash(int x, int y, int dir) {
      int splashDirIndex = splashState[0] == -1 ? 0 : 1;
      if (splashState[splashDirIndex] != -1) {
         return false;
      }

      splashState[splashDirIndex] = 0;
      splashDir[splashDirIndex] = dir;
      splashX[splashDirIndex] = x;
      splashY[splashDirIndex] = y;
      return true;
   }

   private boolean isNotPaintTouchControl() {
      if (!GameCanvas.isTouchControl && GameCanvas.currentScreen == gI()) {
         return true;
      } else if (!GameCanvas.isTouch) {
         return true;
      } else if (ChatTextField.gI().isShow) {
         return true;
      } else {
         return InfoDlg.isShow
            ? true
            : GameCanvas.currentDialog != null
               || ChatPopup.currChatPopup != null
               || GameCanvas.menu.showMenu
               || GameCanvas.panel.isShow;
      }
   }

   public static void setPopupSize(int width, int height) {
      if (GameCanvas.w == 128 || GameCanvas.h <= 208) {
         width = 126;
         height = 160;
      }

      popupW = width;
      popupH = height;
      popupX = gW2 - width / 2;
      popupY = gH2 - height / 2;
      if (GameCanvas.isTouch) {
         if (GameCanvas.h <= 240) {
            popupY -= 10;
         }

         if (GameCanvas.isTouch && !GameCanvas.isTouchControlSmallScreen && GameCanvas.currentScreen instanceof GameScr) {
            popupW = 310;
            popupX = gW / 2 - popupW / 2;
         }
      }

      if (popupY < -10) {
         popupY = -10;
      }

      if (GameCanvas.h > 208 && popupY < 0) {
         popupY = 0;
      }

      if (GameCanvas.h == 208 && popupY < 10) {
         popupY = 10;
      }
   }

   public static void loadImg() {
      TileMap.loadTileImage();
   }

   public static int getTaskMapId() {
      int mapTask;
      if (Char.myCharz().taskMaint == null) {
         mapTask = -1;
      } else {
         mapTask = mapTasks[Char.myCharz().taskMaint.index];
      }

      return mapTask;
   }

   public static byte getTaskNpcId() {
      byte task = 0;
      if (Char.myCharz().taskMaint == null) {
         task = -1;
      } else {
         int taskCount = tasks.length;
         if (Char.myCharz().taskMaint.index <= taskCount - 1) {
            task = (byte)tasks[Char.myCharz().taskMaint.index];
         }
      }

      return task;
   }

   public final void onChatFromMe(String text, String to) {
      ChatTextField.gI().isShow = false;
      if (to.equals(mResources.chat_player)) {
         if (info2.playerID != Char.myCharz().charID) {
            Service.gI().chatPlayer(text, info2.playerID);
         }
      } else if (!text.equals("")) {
         if (text.equals("pingABCD")) {
            Service.gI().sendCheckController();
            Service.gI().sendCheckMap();
            showOnScreenDebug = !showOnScreenDebug;
         }

         if (text.equals("icon")) {
            Res.isIcon = !Res.isIcon;
         }

         if (text.equals("big")) {
            Res.isBig = !Res.isBig;
         }

         Service.gI().chat(text);
      }
   }

   public final void onCancelChat() {
   }

   public final void openUIZone(Message message) {
      InfoDlg.hide();

      try {
         this.zones = new int[message.reader().readByte()];
         this.pts = new int[this.zones.length];
         this.numPlayer = new int[this.zones.length];
         this.maxPlayer = new int[this.zones.length];
         this.rank1 = new int[this.zones.length];
         this.rankName1 = new String[this.zones.length];
         this.rank2 = new int[this.zones.length];
         this.rankName2 = new String[this.zones.length];

         for (int maxPlayerIndex = 0; maxPlayerIndex < this.zones.length; maxPlayerIndex++) {
            this.zones[maxPlayerIndex] = message.reader().readByte();
            this.pts[maxPlayerIndex] = message.reader().readByte();
            this.numPlayer[maxPlayerIndex] = message.reader().readByte();
            this.maxPlayer[maxPlayerIndex] = message.reader().readByte();
            if (message.reader().readByte() == 1) {
               this.rankName1[maxPlayerIndex] = message.reader().readUTF();
               this.rank1[maxPlayerIndex] = message.reader().readInt();
               this.rankName2[maxPlayerIndex] = message.reader().readUTF();
               this.rank2[maxPlayerIndex] = message.reader().readInt();
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      GameCanvas.panel.setTypeZone();
      GameCanvas.panel.show();
   }

   private void startYesNoPopUp(String info, Command cmdYes, Command cmdNo) {
      this.popUpYesNo = new PopUpYesNo();
      this.popUpYesNo.setPopUp(info, cmdYes, cmdNo);
   }

   public final void player_vs_player(int charId, String info, byte byteValue) {
      Char p;
      if ((p = findCharInMap(charId)) != null) {
         if (byteValue == 3) {
            this.startYesNoPopUp(info, new Command(mResources.OK, 2000, p), new Command(mResources.CLOSE, 2009, p));
         }

         if (byteValue == 4) {
            this.startYesNoPopUp(info, new Command(mResources.OK, 2005, p), new Command(mResources.CLOSE, 2009, p));
         }
      }
   }

   public final void giaodich(int playerID) {
      Char p;
      if ((p = findCharInMap(playerID)) != null) {
         this.startYesNoPopUp(
            p.cName + mResources.want_to_trade,
            new Command(mResources.YES, 11114, p),
            new Command(mResources.NO, 2009, p)
         );
      }
   }

   public static void getFlagImage(int charID, byte cflag) {
      if (vFlag.size() == 0) {
         Service.gI().getFlag((byte)2, cflag);
         Res.outz("getFlag1");
      } else {
         if (charID == Char.myCharz().charID) {
            Res.outz("my cflag: isme");
            Char.myCharz();
            if (Char.isGetFlagImage(cflag)) {
               Res.outz("my cflag: true");

               for (int flagIndex = 0; flagIndex < vFlag.size(); flagIndex++) {
                  PKFlag flag;
                  if ((flag = (PKFlag)vFlag.elementAt(flagIndex)) != null && flag.cflag == cflag) {
                     Res.outz("my cflag: cflag==");
                     Char.myCharz().flagImage = flag.IDimageFlag;
                  }
               }

               return;
            }

            Char.myCharz();
            if (!Char.isGetFlagImage(cflag)) {
               Res.outz("my cflag: false");
               Service.gI().getFlag((byte)2, cflag);
               return;
            }
         } else {
            Res.outz("my cflag: not me");
            if (findCharInMap(charID) != null) {
               findCharInMap(charID);
               if (Char.isGetFlagImage(cflag)) {
                  Res.outz("my cflag: true");

                  for (int flagIndex2 = 0; flagIndex2 < vFlag.size(); flagIndex2++) {
                     PKFlag flag2;
                     if ((flag2 = (PKFlag)vFlag.elementAt(flagIndex2)) != null && flag2.cflag == cflag) {
                        Res.outz("my cflag: cflag==");
                        findCharInMap(charID).flagImage = flag2.IDimageFlag;
                     }
                  }

                  return;
               }

               findCharInMap(charID);
               if (!Char.isGetFlagImage(cflag)) {
                  Res.outz("my cflag: false");
                  Service.gI().getFlag((byte)2, cflag);
               }
            }
         }
      }
   }

   public final void actionPerform(int idAction, Object p) {
      Res.outz("PERFORM WITH ID = " + idAction);
      switch (idAction) {
         case 1:
            GameCanvas.endDlg();
            return;
         case 2:
            GameCanvas.menu.showMenu = false;
            return;
         case 2000:
            this.popUpYesNo = null;
            if ((Char)p == null) {
               Service.gI().player_vs_player((byte)1, (byte)3, -1);
               return;
            }

            Service.gI().player_vs_player((byte)1, (byte)3, ((Char)p).charID);
            Service.gI().charMove();
            return;
         case 2001:
            GameCanvas.endDlg();
            return;
         case 2003:
            GameCanvas.endDlg();
            InfoDlg.showWait();
            Service.gI().player_vs_player((byte)0, (byte)3, Char.myCharz().charFocus.charID);
            return;
         case 2004:
            GameCanvas.endDlg();
            Service.gI().player_vs_player((byte)0, (byte)4, Char.myCharz().charFocus.charID);
            return;
         case 2005:
            GameCanvas.endDlg();
            this.popUpYesNo = null;
            if ((Char)p == null) {
               Service.gI().player_vs_player((byte)1, (byte)4, -1);
               return;
            }

            Service.gI().player_vs_player((byte)1, (byte)4, ((Char)p).charID);
            return;
         case 2006:
            GameCanvas.endDlg();
            Service.gI().player_vs_player((byte)2, (byte)4, Char.myCharz().charFocus.charID);
            return;
         case 2007:
            GameCanvas.endDlg();
         case 12006:
            GameMidlet.instance.exit();
            break;
         case 2009:
            this.popUpYesNo = null;
            return;
         case 8002:
            this.doFire(false, true);
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            return;
         case 11000:
            GameCanvas.panel.setTypeMain();
            GameCanvas.panel.show();
            return;
         case 11001:
            Char.myCharz().findNextFocusByKey();
            return;
         case 11002:
            GameCanvas.panel.hide();
            return;
         case 11038:
            MyVector menuItems;
            (menuItems = new MyVector("")).addElement(new Command(mResources.DIES[1], 110381));
            menuItems.addElement(new Command(mResources.DIES[2], 110382));
            menuItems.addElement(new Command(mResources.DIES[3], 110383));
            GameCanvas.menu.startAt(menuItems);
            return;
         case 11057:
            Effect2.vEffect2Outside.removeAllElements();
            Effect2.vEffect2.removeAllElements();
            Npc p2;
            if ((p2 = (Npc)p).idItem == 0) {
               Service.gI()
                  .confirmMenu((short)p2.template.npcTemplateId, (byte)GameCanvas.menu.menuSelectedItem);
               return;
            }

            if (GameCanvas.menu.menuSelectedItem == 0) {
               Service.gI().pickItem(p2.idItem);
               return;
            }
            break;
         case 11059:
            Skill skill = onScreenSkill[this.selectedIndexSkill];
            this.doUseSkill(skill, false);
            super.center = null;
            return;
         case 11067:
            if (TileMap.zoneID != 0) {
               Service.gI().requestChangeZone(0);
               InfoDlg.showWait();
               return;
            }

            info1.addInfo(mResources.ZONE_HERE, 0);
            return;
         case 11111:
            if (Char.myCharz().charFocus == null) {
               return;
            }

            InfoDlg.showWait();
            if (GameCanvas.panel.vPlayerMenu.size() <= 0) {
               this.playerMenu();
            }

            GameCanvas.panel.setTypePlayerMenu(Char.myCharz().charFocus);
            GameCanvas.panel.show();
            Service.gI().getPlayerMenu(Char.myCharz().charFocus.charID);
            Service.gI().messagePlayerMenu(Char.myCharz().charFocus.charID);
            return;
         case 11112:
            Char p3 = (Char)p;
            Service.gI().friend((byte)1, p3.charID);
            return;
         case 11113:
            Char p4;
            if ((p4 = (Char)p) != null) {
               Service.gI().giaodich((byte)0, p4.charID, (byte)-1, -1);
               return;
            }
            break;
         case 11114:
            this.popUpYesNo = null;
            Char p5;
            if ((p5 = (Char)p) == null) {
               return;
            }

            Service.gI().giaodich((byte)1, p5.charID, (byte)-1, -1);
            return;
         case 11115:
            if (Char.myCharz().charFocus == null) {
               return;
            }

            InfoDlg.showWait();
            Service.gI()
               .playerMenuAction(
                  Char.myCharz().charFocus.charID, (short)Char.myCharz().charFocus.menuSelect
               );
            return;
         case 11120:
            Object[] p6;
            Skill p7 = (Skill)(p6 = (Object[])p)[0];
            idAction = Integer.parseInt((String)p6[1]);

            for (int onScreenSkillIndex = 0; onScreenSkillIndex < onScreenSkill.length; onScreenSkillIndex++) {
               if (onScreenSkill[onScreenSkillIndex] == p7) {
                  onScreenSkill[onScreenSkillIndex] = null;
               }
            }

            onScreenSkill[idAction] = p7;
            saveonScreenSkillToRMS();
            return;
         case 11121:
            Object[] p8;
            Skill p9 = (Skill)(p8 = (Object[])p)[0];
            int keySkillIndex = Integer.parseInt((String)p8[1]);

            for (int keySkillIndex2 = 0; keySkillIndex2 < keySkill.length; keySkillIndex2++) {
               if (keySkill[keySkillIndex2] == p9) {
                  keySkill[keySkillIndex2] = null;
               }
            }

            keySkill[keySkillIndex] = p9;
            saveKeySkillToRMS();
            return;
         case 12000:
            Service.gI().getClan((byte)1, -1, null);
            return;
         case 12001:
            GameCanvas.endDlg();
            return;
         case 12002:
            GameCanvas.endDlg();
            ClanObject p10 = (ClanObject)p;
            Service.gI().clanInvite((byte)1, -1, p10.clanID, p10.code);
            this.popUpYesNo = null;
            return;
         case 12003:
            ClanObject p11 = (ClanObject)p;
            GameCanvas.endDlg();
            Service.gI().clanInvite((byte)2, -1, p11.clanID, p11.code);
            this.popUpYesNo = null;
            return;
         case 12004:
            Skill skill2 = (Skill)p;
            this.doUseSkill(skill2, true);
            Char.myCharz();
            Char.saveLoadPreviousSkill();
            return;
         case 12005:
            if (GameCanvas.serverScr == null) {
               GameCanvas.serverScr = new ServerScr();
            }

            GameCanvas.serverScr.switchToMe();
            GameCanvas.endDlg();
            return;
         case 110001:
            GameCanvas.panel.setTypeMain();
            GameCanvas.panel.show();
            return;
         case 110004:
            GameCanvas.menu.showMenu = false;
            return;
         case 110382:
            Service.gI().returnTownFromDead();
            return;
         case 110383:
            Service.gI().wakeUpFromDead();
            return;
         case 110391:
            Service.gI().clanInvite((byte)0, Char.myCharz().charFocus.charID, -1, -1);
            return;
         case 888351:
            Service.gI().petStatus((byte)5);
            GameCanvas.endDlg();
            return;
      }
   }

   public final void showWinNumber(String text, String finish) {
      String text2 = text;
      this.winnumber = new int[text2.length()];
      this.randomNumber = new int[text2.length()];
      this.tMove = new int[text2.length()];
      this.moveCount = new int[text2.length()];
      this.delayMove = new int[text2.length()];

      for (int startIndex = 0; startIndex < text2.length(); startIndex++) {
         this.winnumber[startIndex] = Short.parseShort(text2.substring(startIndex, startIndex + 1));
         this.randomNumber[startIndex] = Res.random(0, 11);
         this.tMove[startIndex] = 1;
         this.delayMove[startIndex] = 0;
      }

      this.tShow = 100;
      this.moveIndex = 0;
      this.strFinish = finish;
      lastXS = currXS = mSystem.currentTimeMillis();
   }

   public final void chatVip(String chatVip) {
      if (!this.startChat) {
         this.currChatWidth = mFont.tahoma_7b_yellowSmall.getWidth(chatVip);
         this.xChatVip = GameCanvas.w;
         this.startChat = true;
      }

      if (chatVip.startsWith("!")) {
         chatVip = chatVip.substring(1, chatVip.length());
         this.isFireWorks = true;
      }

      vChatVip.addElement(chatVip);
   }

   public final void showYourNumber(String strNum) {
      this.yourNumber = strNum;
      this.strPaint = mFont.tahoma_7.splitFontArray(this.yourNumber, 500);
   }

   private static boolean ispaintPhubangBar() {
      return TileMap.mapPhuBang() && phuban_Info.type_PB == 0;
   }

   private static void paintPhuBanBar(mGraphics g, int x, int y, int w) {
      if (phuban_Info != null) {
         if (!isPaintOther && isPaintRada == 1 && !GameCanvas.panel.isShow && ispaintPhubangBar()) {
            if (w < fra_PVE_Bar_1.frameWidth + (fra_PVE_Bar_0.frameWidth << 2)) {
               w = fra_PVE_Bar_1.frameWidth + (fra_PVE_Bar_0.frameWidth << 2);
            }

            if (x > GameCanvas.w - w / 2) {
               x = GameCanvas.w - w / 2;
            }

            if (x < mGraphics.getImageWidth(imgKhung) + w / 2 + 10) {
               x = mGraphics.getImageWidth(imgKhung) + w / 2 + 10;
            }

            y = fra_PVE_Bar_0.frameHeight;
            int y2 = y + 0 + mGraphics.getImageHeight(imgBall) / 2 + 2;
            int frameWidth = fra_PVE_Bar_1.frameWidth;
            int value = w / 2 - frameWidth / 2;
            int x2 = x - w / 2;
            int x3 = x + frameWidth / 2;
            int value2;
            int index = (value2 = value - fra_PVE_Bar_0.frameWidth) / fra_PVE_Bar_0.frameWidth;
            if (value2 % fra_PVE_Bar_0.frameWidth > 0) {
               index++;
            }

            for (int index2 = 0; index2 < index; index2++) {
               if (index2 < index - 1) {
                  fra_PVE_Bar_0.drawFrame(1, x2 + fra_PVE_Bar_0.frameWidth + index2 * fra_PVE_Bar_0.frameWidth, 3, 0, 0, g);
               } else {
                  fra_PVE_Bar_0.drawFrame(1, x2 + value2, 3, 0, 0, g);
               }

               if (index2 < index - 1) {
                  fra_PVE_Bar_0.drawFrame(1, x3 + index2 * fra_PVE_Bar_0.frameWidth, 3, 0, 0, g);
               } else {
                  fra_PVE_Bar_0.drawFrame(1, x3 + value2 - fra_PVE_Bar_0.frameWidth, 3, 0, 0, g);
               }
            }

            fra_PVE_Bar_0.drawFrame(0, x2, 3, 2, 0, g);
            fra_PVE_Bar_0.drawFrame(0, x3 + value2, 3, 0, 0, g);
            if (phuban_Info.pointTeam1 > 0) {
               byte idx = 2;
               byte idx2 = 3;
               if (phuban_Info.color_1 == 4) {
                  idx = 4;
                  idx2 = 5;
               }

               int width;
               if ((width = phuban_Info.pointTeam1 * value / phuban_Info.maxPoint) < 0) {
                  width = 0;
               }

               if (width > value) {
                  width = value;
               }

               g.setClip(x2 + value - width, 3, width, y);

               for (int index3 = 0; index3 < index; index3++) {
                  if (index3 < index - 1) {
                     fra_PVE_Bar_0.drawFrame(
                        idx2, x2 + fra_PVE_Bar_0.frameWidth + index3 * fra_PVE_Bar_0.frameWidth, 3, 0, 0, g
                     );
                  } else {
                     fra_PVE_Bar_0.drawFrame(idx2, x2 + value2, 3, 0, 0, g);
                  }
               }

               fra_PVE_Bar_0.drawFrame(idx, x2, 3, 2, 0, g);
               GameCanvas.resetTrans(g);
            }

            if (phuban_Info.pointTeam2 > 0) {
               byte idx3 = 2;
               byte idx4 = 3;
               if (phuban_Info.color_2 == 4) {
                  idx3 = 4;
                  idx4 = 5;
               }

               int width2;
               if ((width2 = phuban_Info.pointTeam2 * value / phuban_Info.maxPoint) < 0) {
                  width2 = 0;
               }

               if (width2 > value) {
                  width2 = value;
               }

               g.setClip(x3, 3, width2, y);

               for (int index4 = 0; index4 < index; index4++) {
                  if (index4 < index - 1) {
                     fra_PVE_Bar_0.drawFrame(idx4, x3 + index4 * fra_PVE_Bar_0.frameWidth, 3, 0, 0, g);
                  } else {
                     fra_PVE_Bar_0.drawFrame(idx4, x3 + value2 - fra_PVE_Bar_0.frameWidth, 3, 0, 0, g);
                  }
               }

               fra_PVE_Bar_0.drawFrame(idx3, x3 + value2, 3, 0, 0, g);
               GameCanvas.resetTrans(g);
            }

            fra_PVE_Bar_1.drawFrame(0, x - frameWidth / 2, 0, 0, 0, g);
            String text = mSystem.getTimeCountDown(phuban_Info.timeStart, phuban_Info.timeSecond, true, false);
            mFont.tahoma_7b_yellow
               .drawString(g, text, x + 1, 0 + fra_PVE_Bar_1.frameHeight / 2 - mFont.tahoma_7b_green2.getHeight() / 2, 2);
            Panel.setTextColor(phuban_Info.color_1, 1)
               .drawStringBorder(g, phuban_Info.nameTeam1, x - 5, y2 + 5, 1, mFont.tahoma_7b_dark);
            Panel.setTextColor(phuban_Info.color_2, 1)
               .drawStringBorder(g, phuban_Info.nameTeam2, x + 5, y2 + 5, 0, mFont.tahoma_7b_dark);
            if (phuban_Info.type_PB != 0) {
               int y3 = 0 + y / 2 - 2;
               mFont.bigNumber_While.drawString(g, "" + phuban_Info.pointTeam1, x2 + value / 2, y3, 2);
               mFont.bigNumber_While.drawString(g, "" + phuban_Info.pointTeam2, x3 + value / 2, y3, 2);
            }

            g.drawImage(imgVS, ((int) (x)), ((int) (0 + fra_PVE_Bar_1.frameHeight + 2)), 3);
            if (phuban_Info.type_PB == 0) {
               paintChienTruong_Life(
                  g, phuban_Info.maxLife, phuban_Info.lifeTeam1, x - 13, phuban_Info.lifeTeam2, x + 13, y2
               );
            }
         }
      }
   }

   private static void paintChienTruong_Life(mGraphics graphics, int value, int value2, int value3, int value4, int value5, int y) {
      if (imgBall != null) {
         int height = mGraphics.getImageHeight(imgBall) / 2;

         for (int index = 0; index < value; index++) {
            byte byteValue = 0;
            if (index < value2) {
               byteValue = 1;
            }

            graphics.drawRegion(
               imgBall, 0, byteValue * height, mGraphics.getImageWidth(imgBall), height, 0, value3 - index * (height + 1), y, 3
            );
         }

         for (int index2 = 0; index2 < value; index2++) {
            byte byteValue2 = 0;
            if (index2 < value4) {
               byteValue2 = 1;
            }

            graphics.drawRegion(
               imgBall, 0, byteValue2 * height, mGraphics.getImageWidth(imgBall), height, 0, value5 + index2 * (height + 1), y, 3
            );
         }
      }
   }

   public static void addEffectEnd(int type, int subtype, int typePaint, int x, int y, int levelPaint, int dir, short timeRemove, Point[] listObj) {
      addEffect2Vector(new Effect_End(type, subtype, typePaint, x, y, levelPaint, dir, timeRemove, listObj));
   }

   public static void addEffectEnd_Target(int type, int subtype, int typePaint, Char charUse, Point target, int levelPaint, short timeRemove, short range) {
      addEffect2Vector(new Effect_End(type, subtype, typePaint, charUse.clone(), target, levelPaint, timeRemove, range));
   }

   private static void addEffect2Vector(Effect_End eff) {
      if (eff.levelPaint == 0) {
         EffectManager.addHiEffect(eff);
      } else if (eff.levelPaint == 1) {
         EffectManager.addMidEffects(eff);
      } else if (eff.levelPaint == 2) {
         EffectManager.addMid_2Effects(eff);
      } else {
         EffectManager.addLowEffect(eff);
      }
   }

   private void paint_CT(mGraphics graphics, int value, int frameWidth) {
      if (value > GameCanvas.w - 85) {
         value = GameCanvas.w - 85;
      }

      if (value < mGraphics.getImageWidth(imgKhung) + 85 + 10) {
         value = mGraphics.getImageWidth(imgKhung) + 85 + 10;
      }

      mGraphics.getImageHeight(imgBall);
      frameWidth = fra_PVE_Bar_1.frameWidth;
      int imageWidth = 85 - frameWidth / 2;
      int x = value - 85 + 3;
      int value2 = value + frameWidth / 2;
      int value3;
      int index = (value3 = imageWidth - fra_PVE_Bar_0.frameWidth) / fra_PVE_Bar_0.frameWidth;
      if (value3 % fra_PVE_Bar_0.frameWidth > 0) {
         index++;
      }

      for (int index2 = 0; index2 < index; index2++) {
         if (index2 < index - 1) {
            graphics.drawRegionAtLeftEdge(
               img_ct_bar_0,
               0,
               15,
               mGraphics.getImageWidth(img_ct_bar_0),
               15,
               2,
               x + fra_PVE_Bar_0.frameWidth + index2 * fra_PVE_Bar_0.frameWidth,
               3,
               20
            );
         } else {
            graphics.drawRegionAtLeftEdge(img_ct_bar_0, 0, 15, mGraphics.getImageWidth(img_ct_bar_0), 15, 2, x + value3, 3, 20);
         }

         if (index2 < index - 1) {
            graphics.drawRegionAtLeftEdge(
               img_ct_bar_0, 0, 15, mGraphics.getImageWidth(img_ct_bar_0), 15, 2, value2 + index2 * fra_PVE_Bar_0.frameWidth, 3, 20
            );
         } else {
            graphics.drawRegionAtLeftEdge(
               img_ct_bar_0, 0, 15, mGraphics.getImageWidth(img_ct_bar_0), 15, 2, value2 + value3 - fra_PVE_Bar_0.frameWidth, 3, 20
            );
         }
      }

      fra_PVE_Bar_0.drawFrame(0, x, 3, 2, 0, graphics);
      fra_PVE_Bar_0.drawFrame(0, value2 + value3, 3, 0, 0, graphics);
      int width;
      if ((width = nCT_TeamA * 100 / (nCT_nBoyBaller / 2) * 66 / 100) > 0) {
         if (width < 6) {
            width = 6;
         }

         graphics.setClip(x, 3, width, 15);
      }

      if (nCT_TeamA > 0) {
         for (int index3 = 0; index3 < 11; index3++) {
            if (index3 == 0) {
               graphics.drawRegionAtLeftEdge(img_ct_bar_0, 0, 60, mGraphics.getImageWidth(img_ct_bar_0), 15, 2, x, 3, 20);
            } else {
               graphics.drawRegionAtLeftEdge(img_ct_bar_0, 0, 75, mGraphics.getImageWidth(img_ct_bar_0), 15, 2, x + index3 * 6, 3, 20);
            }
         }
      }

      GameCanvas.resetTrans(graphics);
      int value4 = nCT_TeamB * 100 / (nCT_nBoyBaller / 2) * 66 / 100;
      if (66 - (66 - value4) > 0) {
         if (value4 < 6) {
            value4 = 6;
         }

         graphics.setClip(value2 + 66 - value4, 3, 66 - (66 - value4), 15);
      }

      if (nCT_TeamB > 0) {
         for (int index4 = 0; index4 < 11; index4++) {
            if (index4 == 0) {
               graphics.drawRegionAtLeftEdge(img_ct_bar_0, 0, 30, mGraphics.getImageWidth(img_ct_bar_0), 15, 0, value2 + value3, 3, 20);
            } else {
               graphics.drawRegionAtLeftEdge(img_ct_bar_0, 0, 45, mGraphics.getImageWidth(img_ct_bar_0), 15, 0, value2 + value3 - index4 * 6, 3, 20);
            }
         }
      }

      GameCanvas.resetTrans(graphics);
      fra_PVE_Bar_1.drawFrame(0, value - frameWidth / 2 + 1, 0, 0, 0, graphics);
      String text = Firework.formatCountdownDuration((int)((nCT_timeBallte - mSystem.currentTimeMillis()) / 1000L));
      mFont.tahoma_7b_yellow.drawString(graphics, text, x + 85 - 2, 5, 2);
      mFont.tahoma_7_grey.drawString(graphics, "Tầng " + nCT_floor, x + 85 - 3, 0 + fra_PVE_Bar_1.frameHeight, 2);
      imageWidth = mFont.tahoma_7b_red.getWidth(String.valueOf(nCT_TeamA));
      mFont.tahoma_7b_blue
         .drawString(graphics, String.valueOf(nCT_TeamA), value - frameWidth / 2 - imageWidth, 3 + fra_PVE_Bar_1.frameHeight, 0);
      SmallImage.drawSmallImage(graphics, 2325, value - frameWidth / 2 - imageWidth - 15, 3 + fra_PVE_Bar_1.frameHeight, 2, 20);
      imageWidth = mFont.tahoma_7b_red.getWidth(String.valueOf(nCT_TeamB));
      mFont.tahoma_7b_red.drawString(graphics, String.valueOf(nCT_TeamB), value + frameWidth / 2, 3 + fra_PVE_Bar_1.frameHeight, 0);
      SmallImage.drawSmallImage(graphics, 2323, value + frameWidth / 2 + imageWidth + 3, 3 + fra_PVE_Bar_1.frameHeight, 0, 20);
      mFont.tahoma_7b_dark.getWidth("#01 AAAAAAAAAA");
      paint_board_CT(graphics, 40);
      GameCanvas.resetTrans(graphics);
   }

   private static void paint_board_CT(mGraphics g, int imageWidth) {
      String text = "#01 nnnnnnnnnnnn";
      imageWidth = mFont.tahoma_7.getWidth(text);
      int value = GameCanvas.w - imageWidth - 20;

      for (int res_CTIndex = 0; res_CTIndex < nTop; res_CTIndex++) {
         mFont tahoma_7_blue = mFont.tahoma_7_white;
         if (res_CTIndex == 0) {
            tahoma_7_blue = mFont.tahoma_7_red;
         } else if (res_CTIndex == 1) {
            tahoma_7_blue = mFont.tahoma_7_yellow;
         } else if (res_CTIndex == 2) {
            tahoma_7_blue = mFont.tahoma_7_blue;
         }

         if (res_CTIndex == nTop - 1) {
            tahoma_7_blue = mFont.tahoma_7_green;
         }

         String[] partsValue = Res.split((String)res_CT.elementAt(res_CTIndex), "|", 0);
         int[] intValues = new int[]{0, 18};

         for (int index = 0; index < 2; index++) {
            tahoma_7_blue.drawString(
               g, partsValue[index], value + intValues[index], 40 + res_CTIndex * mFont.tahoma_7.getHeight(), 0, mFont.tahoma_7
            );
         }
      }

      GameCanvas.resetTrans(g);
      mFont.tahoma_7b_dark.getHeight();
      GameCanvas.resetTrans(g);
   }
}
