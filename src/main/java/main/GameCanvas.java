package main;

import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import nro.BackgroudEffect;
import nro.Char;
import nro.Controller;
import nro.GameScr;
import nro.Command;
import nro.Hint;
import nro.IActionListener;
import nro.Item;
import nro.ItemMap;
import nro.Paint;
import nro.Position;
import nro.Small;
import nro.Waypoint;
import nro.mResources;
import nro.CrackBallScr;
import nro.CreateCharScr;
import nro.Dialog;
import nro.InfoDlg;
import nro.InfoMe;
import nro.InputDlg;
import nro.Menu;
import nro.MsgDlg;
import nro.Res;
import nro.SelectCharScr;
import nro.StaticObj;
import nro.Rms;
import nro.Session_ME;
import nro.MotherCanvas;
import nro.mGraphics;
import nro.mSystem;
import nro.ChatTextField;
import nro.LoginScr;
import nro.MyHashTable;
import nro.MyVector;
import nro.SoundMn;
import nro.mFont;
import nro.mScreen;
import nro.ChatPopup;
import nro.EffecMn;
import nro.Effect;
import nro.Effect2;
import nro.FireWorkEff;
import nro.Panel;
import nro.PopUp;
import nro.RegisterScreen;
import nro.ServerListScreen;
import nro.ServerScr;
import nro.Service;
import nro.SmallImage;
import nro.SplashScr;
import nro.TileMap;

public final class GameCanvas extends MotherCanvas implements IActionListener {
   public static boolean lowGraphic = false;
   public static long timeNow = 0L;
   public static boolean isMoveNumberPad = true;
   public static boolean isLoading;
   public static boolean isTouch = false;
   public static boolean isTouchControl;
   public static boolean isTouchControlSmallScreen;
   public static GameCanvas instance;
   public static boolean[] keyPressed = new boolean[15];
   private static boolean[] keyReleased = new boolean[15];
   public static boolean[] keyHold = new boolean[15];
   public static boolean isPointerDown;
   public static boolean isPointerClick;
   public static boolean isPointerJustRelease;
   public static boolean isPointerMove;
   public static boolean isPointerSelect;
   public static int px;
   public static int py;
   public static int pxFirst;
   public static int pyFirst;
   public static int pxLast;
   public static int pyLast;
   public static Position[] arrPos = new Position[4];
   public static int gameTick;
   public static int taskTick;
   private static int shurikenFrameTick;
   public static boolean isEff1;
   public static boolean isEff2;
   private static long timeTickEff1;
   private static long timeTickEff2;
   public static int w;
   public static int h;
   public static int hw;
   public static int hh;
   public static mScreen currentScreen;
   private static long lastPaintNpeLogAt;
   public static Menu menu;
   public static Panel panel;
   public static Panel panel2;
   public static LoginScr loginScr;
   public static RegisterScreen registerScr;
   public static Dialog currentDialog;
   public static MsgDlg msgdlg;
   private static MyVector currentPopup;
   public static Paint paintz;
   public static Image[] imgBG;
   public static int skyColor;
   private static int curPos = 0;
   public static int[] bgW;
   public static int[] bgH;
   private mGraphics g = new mGraphics();
   public static Image[] imgBlue = new Image[7];
   public static Image[] imgViolet = new Image[7];
   public static MyHashTable danhHieu = new MyHashTable("");
   public static MyVector messageServer = new MyVector("");
   private static MyVector debugUpdate;
   private static MyVector debugPaint;
   public static MyVector debugSession;
   public static boolean isFocusPanel2;
   public static int isRequestMapID = -1;
   public static long waitingTimeChangeMap;
   public static boolean field_Z_2135a5a0 = false;
   private static int dir_ = -1;
   private int tickWaitThongBao;
   public static boolean paintBG;
   private static Image imgSun;
   private static Image imgSun2;
   public static Image[] imgBorder = new Image[3];
   private static Image[] imgSunSpec = new Image[3];
   public static int[] cloudX;
   public static int[] cloudY;
   private static int sunX;
   private static int sunY;
   private static int sunX2;
   private static int sunY2;
   private static int[] layerSpeed;
   private static int[] moveX;
   private static int[] moveXSpeed;
   public static boolean isBoltEff;
   private static boolean boltActive;
   private static int tBolt;
   public static int typeBg = -1;
   public static int transY;
   private static int[] yb = new int[5];
   public static int[] colorTop;
   private static int[] colorBotton;
   private static int nBg = 0;
   private static int[] bgRain = new int[]{1, 4, 11};
   private static Image imgCaycot;
   private static Image tam;
   private static boolean isLoadBGok;
   private static long lastTimePress = 0L;
   public static int keyAsciiPress;
   public static Image img18;
   private static int fps = 0;
   private static int up;
   private long timefps = System.currentTimeMillis() + 1000L;
   private long timeup = System.currentTimeMillis() + 1000L;
   public static long timeBreakLoading;
   private static String thongBaoTest;
   private static int xThongBaoTranslate = w - 60;
   private static MyVector overlayDebugLines = new MyVector("");
   private int overlayDebugLineHeight = 12;
   public static boolean isPointerJustDown = false;
   private int[] dustX;
   private int[] dustY;
   private int[] dustState;
   private static int[] wsState;
   private static Image[] imgWS;
   private static Image imgShuriken;
   private static Image[][] imgDust;
   public static boolean connectionErrorOccurred;
   public static boolean isPlaySound = true;
   public static ServerListScreen serverScreen;
   public static ServerScr serverScr;
   public static SelectCharScr _SelectCharScr;
   public static boolean open3Hour;
   private volatile boolean isResetDialogPending;
   private static volatile String pendingResetDialogText;
   private static int pendingResetDialogAction;
   private static Object pendingResetDialogPayload;
   private static long TIMEOUT;
   private static int timeLoading = 15;

   static {
      new Object();
      int[] intValues = new int[]{-1};
      new MyVector("vFlyText");
      new Random();
   }

   public GameCanvas() {
      w = this.getWidthz();
      h = this.getHeightz();
      mScreen.ITEM_HEIGHT = mFont.tahoma_8b.getHeight() + 8;
      menu = new Menu();
      panel = new Panel();
      currentPopup = new MyVector("vCurrPopup");
      hw = w / 2;
      hh = h / 2;
      if (this.hasPointerEvents()) {
         isTouch = true;
         if (w >= 240) {
            isTouchControl = true;
         }

         if (w < 320) {
            isTouchControlSmallScreen = true;
         }
      }

      msgdlg = new MsgDlg();
      if (h <= 160) {
         Paint.hTab = 15;
         mScreen.cmdH = 17;
      }

      GameScr.maxScreenDimensionWithMargin = (w > h ? w : h) + 20;
      instance = this;
      System.gc();
      paintz = new Paint();
      if (!lowGraphic) {
         if (imgDust == null) {
            imgDust = new Image[2][5];

            for (int imgDustIndex = 0; imgDustIndex < 2; imgDustIndex++) {
               for (int imgDustIndex2 = 0; imgDustIndex2 < 5; imgDustIndex2++) {
                  imgDust[imgDustIndex][imgDustIndex2] = mSystem.loadImage("/e/d" + imgDustIndex + imgDustIndex2 + ".png");
               }
            }
         }

         this.dustX = new int[2];
         this.dustY = new int[2];
         this.dustState = new int[2];
         this.dustState[0] = this.dustState[1] = -1;
      }

      loadWaterSplash();
      imgShuriken = mSystem.loadImage("/mainImage/myTexture2df.png");
      int clientType;
      if ((clientType = Rms.loadRMSInt(Rms.RMS_clienttype)) != -1) {
         mSystem.clientType = clientType;
      }

      if (mSystem.clientType == 7 && (Rms.loadRMSString("fake") == null || Rms.loadRMSString("fake") == "")) {
         imgShuriken = mSystem.loadImage("/mainImage/wait.png");
      }

      debugUpdate = new MyVector("vDebugUpdate");
      debugPaint = new MyVector("vDeBugPait");
      debugSession = new MyVector("vDebugSesion");

      for (int imgBorderIndex = 0; imgBorderIndex < 3; imgBorderIndex++) {
         imgBorder[imgBorderIndex] = mSystem.loadImage("/mainImage/myTexture2dbd" + imgBorderIndex + ".png");
      }

      mGraphics.getImageWidth(imgBorder[0]);
      mGraphics.getImageHeight(imgBorder[0]);
      mGraphics.getImageWidth(imgBorder[1]);
      mGraphics.getImageHeight(imgBorder[1]);
      Panel.graphics = Rms.loadRMSInt("lowGraphic");
      lowGraphic = Rms.loadRMSInt("lowGraphic") == 1;
      GameScr.isPaintChatVip = Rms.loadRMSInt("serverchat") != 1;
      Char.isPaintAura = Rms.loadRMSInt("isPaintAura") == 1;
      Char.isPaintAura2 = Rms.loadRMSInt("isPaintAura2") == 1;
      Res.init();
      SmallImage.loadBigImage();
      PopUp.loadBg();
      Panel.WIDTH_PANEL = 176;
      if (176 > w) {
         Panel.WIDTH_PANEL = w;
      }

      img18 = mSystem.loadImage("/mainImage/18+.png");

      for (int imgBlueIndex = 0; imgBlueIndex < 7; imgBlueIndex++) {
         imgBlue[imgBlueIndex] = mSystem.loadImage("/effectdata/blue/" + imgBlueIndex + ".png");
         imgViolet[imgBlueIndex] = mSystem.loadImage("/effectdata/violet/" + imgBlueIndex + ".png");
      }

      serverScreen = new ServerListScreen();
      serverScr = new ServerScr();
      loginScr = new LoginScr();
      _SelectCharScr = new SelectCharScr();
   }

   public static GameCanvas gI() {
      return instance;
   }

   public static void connect() {
      if (!Session_ME.gI().isConnected()) {
         Session_ME.gI().connect(GameMidlet.IP, GameMidlet.PORT);
      }
   }

   public static void resetTrans(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      g.setClip(0, 0, w, h);
   }

   public static void resetTransGameScr(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      g.translate(0, 0);
      g.setClip(0, 0, w, h);
      g.translate(-GameScr.cmx, -GameScr.cmy);
   }

   public static void debug() {
   }

   public final void update() {
      if (isRequestMapID == 1 && waitingTimeChangeMap < mSystem.currentTimeMillis()) {
         waitingTimeChangeMap = mSystem.currentTimeMillis();
         isRequestMapID = -1;
         Char.isLoadingMap = false;
         currentDialog = null;
         InfoDlg.hide();
         mSystem.onConnectionFail(true);
      }

      if (isRequestMapID == 2 && waitingTimeChangeMap < mSystem.currentTimeMillis() && gameTick % 2 == 0 && currentScreen != null) {
         if (currentScreen == GameScr.gI()) {
            if (Char.isLoadingMap) {
               Char.isLoadingMap = false;
            }

            if (ServerListScreen.waitToLogin) {
               ServerListScreen.waitToLogin = false;
            }
         }

         if (SmallImage.vt_images_watingDowload != null && SmallImage.vt_images_watingDowload.size() > 0) {
            Object vt_images_watingDowload;
            Service service;
            if ((vt_images_watingDowload = SmallImage.vt_images_watingDowload.elementAt(0)) instanceof Small && (service = Service.gI()) != null) {
               service.requestIcon(((Small)vt_images_watingDowload).id);
            }

            SmallImage.vt_images_watingDowload.removeElementAt(0);
         }

         Effect.vdowloadEff.size();
      }

      if (mSystem.currentTimeMillis() > this.timefps) {
         this.timefps += 1000L;
         fps = 0;
      }

      fps++;
      if (messageServer.size() > 0 && thongBaoTest == null) {
         thongBaoTest = (String)messageServer.elementAt(0);
         xThongBaoTranslate = w - 60;
         dir_ = -1;
         messageServer.removeElementAt(0);
      }

      if (gameTick % 5 == 0) {
         timeNow = mSystem.currentTimeMillis();
      }

      if (mSystem.currentTimeMillis() > this.timeup) {
         this.timeup += 1000L;
         up = 0;
      }

      up++;
      debugUpdate.removeAllElements();
      long timeTickEff1Value = System.currentTimeMillis();
      if (GameScr.showOnScreenDebug) {
         Res.updateOnScreenDebug();
      }

      if (!GameScr.clientDataReadySent && GameScr.lastItemDataPacketAt > 0L && mSystem.currentTimeMillis() - GameScr.lastItemDataPacketAt >= 500L) {
         GameScr.gI();
         GameScr.readOk();
      }

      if (timeTickEff1Value - timeTickEff1 >= 780L && !isEff1) {
         timeTickEff1 = timeTickEff1Value;
         isEff1 = true;
      } else {
         isEff1 = false;
      }

      if (timeTickEff1Value - timeTickEff2 >= 7800L && !isEff2) {
         timeTickEff2 = timeTickEff1Value;
         isEff2 = true;
      } else {
         isEff2 = false;
      }

      if (taskTick > 0) {
         taskTick--;
      }

      if (++gameTick > 10000) {
         if (System.currentTimeMillis() - lastTimePress > 20000L && currentScreen == loginScr) {
            GameMidlet.instance.exit();
         }

         gameTick = 0;
      }

      if (currentScreen != null) {
         if (ChatPopup.serverChatPopUp != null) {
            ChatPopup.serverChatPopUp.update();
            ChatPopup.serverChatPopUp.updateKey();
         } else if (ChatPopup.currChatPopup != null) {
            ChatPopup.currChatPopup.update();
            ChatPopup.currChatPopup.updateKey();
         } else if (currentDialog != null) {
            currentDialog.update();
         } else if (menu.showMenu) {
            menu.updateMenu();
            menu.updateMenuKey();
         } else if (panel.isShow) {
            panel.doFireClanOption();
            if (isPointer(panel.X, 0, panel.W, panel.H)) {
               isFocusPanel2 = false;
            }

            if (panel2 != null && panel2.isShow) {
               panel2.doFireClanOption();
               if (isPointer(panel2.X, 0, panel2.W, panel2.H)) {
                  isFocusPanel2 = true;
               }
            }

            if (panel2 != null) {
               if (isFocusPanel2) {
                  panel2.updateKey();
               } else {
                  panel.updateKey();
               }
            } else {
               panel.updateKey();
            }

            if (panel.chatTField != null && panel.chatTField.isShow) {
               panel.chatTFUpdateKey();
            } else if (panel2 != null && panel2.chatTField != null && panel2.chatTField.isShow) {
               panel2.chatTFUpdateKey();
            } else if ((
                  !isPointer(panel.X, 0, panel.W, panel.H)
                     || panel2 == null
               )
               && panel2 != null) {
               if (panel2 != null
                  && panel2.isShow
                  && isPointer(panel2.X, 0, panel2.W, panel2.H)) {
                  panel2.updateKey();
               }
            } else {
               panel.updateKey();
            }

            if (isPointer(
                  panel.X + panel.W,
                  0,
                  w - (panel.W << 1),
                  panel.H
               )
               && isPointerJustRelease
               && panel.isDoneCombine) {
               panel.hide();
            }
         }

         if (!isLoading) {
            currentScreen.update();
         }

         if (!panel.isShow && ChatPopup.serverChatPopUp == null) {
            currentScreen.updateKey();
         }

         label412: {
            if (Char.myCharz().taskMaint != null && currentScreen == GameScr.instance) {
               short taskId = Char.myCharz().taskMaint.taskId;
               int index = Char.myCharz().taskMaint.index;
               Hint.isCamera = false;
               Hint.trans = 0;
               Hint.type = 0;
               Hint.isPaint = true;
               Hint.isPaintArrow = true;
               if (menu.showMenu && taskId > 0) {
                  Hint.isPaint = false;
               }

               switch (taskId) {
                  case 0:
                     if (ChatPopup.currChatPopup == null && Char.myCharz().statusMe != 14) {
                        if (index == 0 && TileMap.vGo.size() != 0) {
                           Hint.x = ((Waypoint)TileMap.vGo.elementAt(0)).minX - 100;
                           Hint.y = ((Waypoint)TileMap.vGo.elementAt(0)).minY + 40;
                           Hint.isCamera = true;
                        }

                        if (index == 1) {
                           Hint.nextMap(0);
                        }

                        if (index == 2) {
                           Hint.clickNpc();
                        }

                        if (index == 3) {
                           if (!panel.isShow) {
                              Hint.clickNpc();
                           } else if (panel.currentTabIndex == 0) {
                              if (panel.cp == null) {
                                 Hint.x = panel.xScroll + panel.wScroll / 2;
                                 Hint.y = panel.yScroll + 20;
                              } else if (menu.tDelay != 0) {
                                 Hint.x = panel.xScroll + 25;
                                 Hint.y = panel.yScroll + 60;
                              }
                           } else if (panel.currentTabIndex == 1) {
                              Hint.x = panel.startTabPos + 10;
                              Hint.y = 65;
                           }
                        }

                        if (index == 4) {
                           if (panel.isShow) {
                              Hint.x = panel.cmdClose.x + 5;
                              Hint.y = panel.cmdClose.y + 5;
                           } else if (menu.showMenu) {
                              Hint.x = w / 2;
                              Hint.y = h - 20;
                           } else {
                              Hint.clickNpc();
                           }
                        }

                        if (index == 5) {
                           Hint.clickNpc();
                        }
                        break label412;
                     }

                     Hint.x = w / 2;
                     Hint.y = h - 15;
                     break label412;
                  case 1:
                     if (ChatPopup.currChatPopup == null && Char.myCharz().statusMe != 14) {
                        if (index == 0) {
                           if (TileMap.isOfflineMap()) {
                              Hint.nextMap(0);
                           } else {
                              Hint.clickMob();
                           }
                        }

                        if (index == 1) {
                           if (!TileMap.isOfflineMap()) {
                              Hint.nextMap(1);
                           } else {
                              Hint.clickNpc();
                           }
                        }
                        break label412;
                     }

                     Hint.x = w / 2;
                     Hint.y = h - 15;
                     break label412;
                  case 2:
                     if (ChatPopup.currChatPopup != null || Char.myCharz().statusMe == 14) {
                        Hint.x = w / 2;
                        Hint.y = h - 15;
                        break label412;
                     }

                     if (index == 0) {
                        if (!TileMap.isOfflineMap()) {
                           Hint.isViewMap = true;
                        }

                        if (!panel.isShow) {
                           if (!Hint.isViewMap) {
                              Hint.x = GameScr.gI().cmdMenu.x;
                              Hint.y = GameScr.gI().cmdMenu.y + 13;
                              Hint.trans = 1;
                           } else {
                              if (GameScr.getTaskMapId() == TileMap.mapID) {
                                 if (!Hint.isHaveItem()) {
                                    Hint.clickMob();
                                 }
                              } else {
                                 Hint.nextMap(0);
                              }

                              if (Hint.isViewMap) {
                                 Hint.isCloseMap = true;
                              }
                           }
                        } else if (!Hint.isViewMap) {
                           if (panel.currentTabIndex == 0) {
                              int value = h > 300 ? 15 : 10;
                              Hint.x = panel.xScroll + panel.wScroll / 2;
                              Hint.y = panel.yScroll + panel.hScroll - value;
                           } else {
                              Hint.x = panel.startTabPos + 10;
                              Hint.y = 65;
                           }
                        } else if (!Hint.isCloseMap) {
                           Hint.x = panel.cmdClose.x + 5;
                           Hint.y = panel.cmdClose.y + 5;
                        } else {
                           Hint.isPaint = false;
                        }

                        if (Char.myCharz().cMP <= 0L) {
                           Hint.x = GameScr.xHP + 5;
                           Hint.y = GameScr.yHP + 13;
                           Hint.isCamera = false;
                        }
                     }

                     if (index != 1) {
                        break label412;
                     }
                     break;
                  case 3:
                     if (ChatPopup.currChatPopup == null && Char.myCharz().statusMe != 14) {
                        if (index == 0) {
                           if (!panel.isShow) {
                              if (!Hint.isViewPotential) {
                                 Hint.x = GameScr.gI().cmdMenu.x;
                                 Hint.y = GameScr.gI().cmdMenu.y + 13;
                                 Hint.trans = 1;
                              } else {
                                 if (GameScr.getTaskMapId() == TileMap.mapID) {
                                    if (!Hint.isHaveItem()) {
                                       Hint.clickMob();
                                    }
                                 } else {
                                    Hint.nextMap(0);
                                 }

                                 if (Hint.isViewMap) {
                                    Hint.isCloseMap = true;
                                 }
                              }
                           } else if (!Hint.isViewPotential) {
                              Hint.x = panel.xScroll + 10 + 108 - 18;
                              Hint.y = 65;
                           } else if (!Hint.isCloseMap) {
                              Hint.x = panel.cmdClose.x + 5;
                              Hint.y = panel.cmdClose.y + 5;
                           } else {
                              Hint.isPaint = false;
                           }

                           if (Char.myCharz().cMP <= 0L) {
                              Hint.x = GameScr.xHP + 5;
                              Hint.y = GameScr.yHP + 13;
                              Hint.isCamera = false;
                           }
                        } else {
                           Hint.isPaint = false;
                           Hint.isPaintArrow = false;
                        }
                        break label412;
                     }

                     Hint.x = w / 2;
                     Hint.y = h - 15;
                     break label412;
                  default:
                     if (Char.myCharz().taskMaint.taskId == 9 && Char.myCharz().taskMaint.index == 2) {
                        for (int popupIndex = 0; popupIndex < PopUp.vPopups.size(); popupIndex++) {
                           PopUp popup;
                           if ((popup = (PopUp)PopUp.vPopups.elementAt(popupIndex)).cy <= 24) {
                              Hint.x = popup.cx + popup.sayWidth / 2;
                              Hint.y = popup.cy + 30;
                              Hint.isCamera = true;
                              Hint.isPaint = false;
                              Hint.isPaintArrow = true;
                              break label412;
                           }
                        }
                     }

                     Hint.isPaint = false;
                     Hint.isPaintArrow = false;
                     break label412;
               }
            }

            Hint.isPaint = false;
            Hint.isPaintArrow = false;
         }

         int x = Hint.trans == 0 ? 2 : -2;
         if (!Hint.activeClick) {
            Hint.paintFlare = false;
            if (++Hint.t == 50) {
               Hint.t = 0;
               Hint.activeClick = true;
            }
         } else {
            Hint.t++;
            if (Hint.type == 0) {
               if (Hint.t == 2) {
                  Hint.x += x * 2;
                  Hint.y -= 4;
                  Hint.paintFlare = true;
               }

               if (Hint.t == 4) {
                  Hint.x -= x * 2;
                  Hint.y += 4;
                  Hint.activeClick = false;
                  Hint.paintFlare = false;
                  Hint.t = 0;
               }

               if (Hint.t > 4) {
                  Hint.activeClick = false;
               }
            }

            if (Hint.type == 1) {
               if (Hint.t == 2) {
                  if (isTouch) {
                     GameScr.startFlyText(mResources.press_twice, Hint.x, Hint.y + 10, 0, 20, 7);
                  }

                  Hint.paintFlare = true;
                  Hint.x += x * 2;
                  Hint.y -= 4;
               }

               if (Hint.t == 4) {
                  Hint.paintFlare = false;
                  Hint.x -= x;
                  Hint.y += 2;
               }

               if (Hint.t == 6) {
                  Hint.paintFlare = true;
                  Hint.x += x;
                  Hint.y -= 2;
               }

               if (Hint.t == 8) {
                  Hint.paintFlare = false;
                  Hint.x -= x;
                  Hint.y += 2;
               }

               if (Hint.t == 10) {
                  Hint.x -= x;
                  Hint.y += 2;
                  Hint.activeClick = false;
                  Hint.t = 0;
               }
            }
         }

         SoundMn.gI();
         SoundMn.update();
      }

      System.currentTimeMillis();
      InfoDlg.update();
      if (this.isResetDialogPending) {
         this.isResetDialogPending = false;
         doResetToLoginScr();
         String info = pendingResetDialogText;
         int actionID = pendingResetDialogAction;
         Object p = pendingResetDialogPayload;
         pendingResetDialogText = null;
         pendingResetDialogAction = 0;
         pendingResetDialogPayload = null;
         if (info != null) {
            startOK(info, actionID, p);
         }
      }

      if (currentScreen != null && currentScreen instanceof GameScr) {
         int value2 = SoundMn.gameSpeed > 0 ? 1 : 2;
         if ((xThongBaoTranslate = xThongBaoTranslate + dir_ * value2) - Panel.imgNew.getWidth() <= 60) {
            dir_ = 0;
            this.tickWaitThongBao++;
            if (this.tickWaitThongBao > 150) {
               this.tickWaitThongBao = 0;
               thongBaoTest = null;
            }
         }
      }

      if (currentScreen != null && currentScreen.equals(GameScr.gI())) {
         if (GameScr.info1 != null) {
            GameScr.info1.update();
         }

         if (GameScr.info2 != null) {
            GameScr.info2.update();
         }
      }

      isPointerSelect = false;
   }

   public static void doResetToLoginScr() {
      try {
         Char.isLoadingMap = false;
         Char.ischangingMap = false;
         SoundMn.gI();
         SoundMn.stopAll();
         LoginScr.isContinueToLogin = false;
         TileMap.bgType = 0;
         TileMap.lastType = 0;
         Char.clearMyChar();
         GameScr.clearGameScr();
         GameScr.resetAllvector();
         InfoDlg.hide();
         InfoMe info1 = GameScr.info1;
         GameScr.info1.info.hide();
         info1 = GameScr.info2;
         GameScr.info2.info.hide();
         GameScr.info2.cmdChat = null;
         ChatPopup.currChatPopup = null;
         Controller.isStopReadMessage = false;
         GameScr.loadCamera(-1, -1);
         GameScr.textTime.removeAllElements();
         GameScr.cmx = 100;
         panel.currentTabIndex = 0;
         panel.selected = isTouch ? -1 : 0;
         panel.init();
         panel2 = null;
         GameScr.isPaint = true;
         nro.ClanMessage.vMessage.removeAllElements();
         GameScr.vClan.removeAllElements();
         GameScr.vFriend.removeAllElements();
         GameScr.vEnemies.removeAllElements();
         TileMap.vCurrItem.removeAllElements();
         BackgroudEffect.vBgEffect.removeAllElements();
         EffecMn.vEff.removeAllElements();
         Effect.newEff.removeAllElements();
         menu.showMenu = false;
         panel.isShow = false;
         panel.vItemCombine.removeAllElements();
         if (panel.tabIcon != null) {
            panel.tabIcon.isShow = false;
         }

         if (mGraphics.zoomLevel == 1) {
            SmallImage.clearHastable();
         }

         Session_ME.gI().close();
         Session_ME.gI2().close();
      } catch (Exception ignoredException) {
      }

      ServerListScreen.isAutoConect = true;
      ServerListScreen.countDieConnect = 0;
      ServerListScreen.testConnect = -1;
      ServerListScreen.loadScreen = true;
      clearTransientOverlays();
      if (ServerListScreen.ipSelect == -1) {
         serverScr.switchToMe();
      } else {
         if (serverScreen == null) {
            serverScreen = new ServerListScreen();
         }

         serverScreen.switchToMe();
      }
   }

   public static void clearTransientOverlays() {
      currentDialog = null;
      InfoDlg.hide();
      if (menu != null) {
         menu.showMenu = false;
      }

      if (panel != null) {
         panel.isShow = false;
         if (panel.chatTField != null) {
            panel.chatTField.isShow = false;
         }
      }

      if (panel2 != null) {
         panel2.isShow = false;
         if (panel2.chatTField != null) {
            panel2.chatTField.isShow = false;
         }
      }

      if (msgdlg != null) {
         msgdlg.isWait = false;
      }

      if (GameScr.instance != null) {
         GameScr.instance.popUpYesNo = null;
      }

      ChatPopup.clearAllChatPopups();
      Hint.activeClick = false;
      PopUp.vPopups.removeAllElements();
      if (currentPopup != null) {
         currentPopup.removeAllElements();
      }
   }

   public static void messageDispatchErrorHook() {
   }

   public static void updateBG() {
   }

   private static void fillRect(mGraphics g, int color, int x, int y, int w, int hValue, int detalY) {
      g.setColor(color);
      int cmy = GameScr.cmy;
      if (GameScr.cmy > h) {
         cmy = h;
      }

      g.fillRect(0, y - (detalY != 0 ? cmy >> detalY : 0), w, hValue + (detalY != 0 ? cmy >> detalY : 0));
   }

   private static void paintBackgroundtLayer(mGraphics g, int layer, int deltaY, int color1, int color2) {
      try {
         int bgHIndex;
         if ((bgHIndex = layer - 1) == imgBG.length - 1
            && (GameScr.gI().isRongThanXuatHien || GameScr.gI().isFireWorks)) {
            g.setColor(GameScr.gI().mautroi);
            g.fillRect(0, 0, w, h);
            if (typeBg == 2 || typeBg == 4 || typeBg == 7) {
               drawSun1(g);
               drawSun2(g);
            }

            if (GameScr.gI().isFireWorks && !lowGraphic) {
               FireWorkEff.paint(g);
            }

            return;
         }

         if (imgBG != null && imgBG[bgHIndex] != null) {
            if (moveX[bgHIndex] != 0) {
               moveX[bgHIndex] = moveX[bgHIndex] + moveXSpeed[bgHIndex];
            }

            int cmy = GameScr.cmy;
            if (GameScr.cmy > h) {
               cmy = h;
            }

            if (layerSpeed[bgHIndex] != 0) {
               if (bgW[bgHIndex] > 0) {
                  for (int x = -((GameScr.cmx + moveX[bgHIndex] >> layerSpeed[bgHIndex]) % bgW[bgHIndex]);
                     x < GameScr.gW;
                     x += bgW[bgHIndex]
                  ) {
                     g.drawImage(imgBG[bgHIndex], ((int) (x)), ((int) (yb[bgHIndex] - (deltaY > 0 ? cmy >> deltaY : 0))), 0);
                  }
               }
            } else if (bgW[bgHIndex] > 0) {
               for (int x2 = 0; x2 < GameScr.gW; x2 += bgW[bgHIndex]) {
                  g.drawImage(imgBG[bgHIndex], ((int) (x2)), ((int) (yb[bgHIndex] - (deltaY > 0 ? cmy >> deltaY : 0))), 0);
               }
            }

            if (color1 != -1) {
               if (bgHIndex == nBg - 1) {
                  fillRect(g, color1, 0, -(cmy >> deltaY), GameScr.gW, yb[bgHIndex], deltaY);
               } else {
                  fillRect(
                     g,
                     color1,
                     0,
                     yb[bgHIndex - 1] + bgH[bgHIndex - 1],
                     GameScr.gW,
                     yb[bgHIndex] - (yb[bgHIndex - 1] + bgH[bgHIndex - 1]),
                     deltaY
                  );
               }
            }

            if (color2 != -1) {
               if (bgHIndex == 0) {
                  fillRect(
                     g,
                     color2,
                     0,
                     yb[bgHIndex] + bgH[bgHIndex],
                     GameScr.gW,
                     GameScr.gH - (yb[bgHIndex] + bgH[bgHIndex]),
                     deltaY
                  );
               } else {
                  fillRect(
                     g,
                     color2,
                     0,
                     yb[bgHIndex] + bgH[bgHIndex],
                     GameScr.gW,
                     yb[bgHIndex - 1] - (yb[bgHIndex] + bgH[bgHIndex]) + 80,
                     deltaY
                  );
               }
            }

            if (currentScreen == GameScr.instance) {
               if (layer == 1 && typeBg == 11 && TileMap.bgType == 0) {
                  g.drawImage(
                     imgSun2,
                     ((int) (-(GameScr.cmx >> layerSpeed[0]) + 400)),
                     ((int) (yb[0] + 30 - (cmy >> 2))),
                     StaticObj.BOTTOM_HCENTER
                  );
               }

               if (layer == 1 && typeBg == 13) {
                  g.drawImage(
                     imgBG[1],
                     ((int) (-(GameScr.cmx >> layerSpeed[0]) + TileMap.tmw * 24 / 4)),
                     ((int) (yb[0] - (cmy >> 3) + 30)),
                     0
                  );
                  g.drawRegion(
                     imgBG[1],
                     0,
                     0,
                     bgW[1],
                     bgH[1],
                     2,
                     -(GameScr.cmx >> layerSpeed[0]) + TileMap.tmw * 24 / 4 + bgW[1],
                     yb[0] - (cmy >> 3) + 30,
                     0
                  );
               }

               if (layer == 3 && TileMap.mapID == 1) {
                  for (int index = 0; index < TileMap.pxh / mGraphics.getImageHeight(imgCaycot); index++) {
                     g.drawImage(
                        imgCaycot,
                        ((int) (-(GameScr.cmx >> layerSpeed[2]) + 300)),
                        ((int) (index * mGraphics.getImageHeight(imgCaycot) - (cmy >> 3))),
                        0
                     );
                  }
               }
            }

            int x3 = -(GameScr.cmx + moveX[bgHIndex] >> layerSpeed[bgHIndex]);
            EffecMn.paintBackGroundUnderLayer(g, x3, yb[bgHIndex] + bgH[bgHIndex] - (cmy >> deltaY), bgHIndex);
            return;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private static void drawSun1(mGraphics g) {
      if (imgSun != null) {
         g.drawImage(imgSun, ((int) (sunX)), ((int) (sunY)), 0);
      }

      if (isBoltEff) {
         if (gameTick % 200 == 0) {
            boltActive = true;
         }

         if (boltActive) {
            if (++tBolt == 10) {
               tBolt = 0;
               boltActive = false;
            }

            if (tBolt % 2 == 0) {
               g.setColor(16777215);
               g.fillRect(0, 0, w, h);
            }
         }
      }
   }

   private static void drawSun2(mGraphics graphics) {
      if (imgSun2 != null) {
         graphics.drawImage(imgSun2, ((int) (sunX2)), ((int) (sunY2)), 0);
      }
   }

   public static void paintBGGameScr(mGraphics g) {
      if (!isLoadBGok) {
         g.setColor(0);
         g.fillRect(0, 0, w, h);
      }

      if (!Char.isLoadingMap) {
         int width = GameScr.gW;
         g.translate(-g.getTranslateX(), -g.getTranslateY());
         g.setColor(0);
         g.fillRect(0, 0, w, h);

         try {
            if (paintBG && (!lowGraphic || currentScreen == serverScreen || currentScreen == loginScr)) {
               if (currentScreen == GameScr.gI()) {
                  if (TileMap.mapID != 172
                     && (
                        TileMap.mapID == 137
                           || TileMap.mapID == 115
                           || TileMap.mapID == 117
                           || TileMap.mapID == 118
                           || TileMap.mapID == 120
                           || TileMap.isMapDouble
                     )) {
                     g.setColor(0);
                     g.fillRect(0, 0, w, h);
                     return;
                  }

                  if (TileMap.mapID == 138) {
                     g.setColor(6776679);
                     g.fillRect(0, 0, w, h);
                     return;
                  }
               }

               if (typeBg == 0) {
                  paintBackgroundtLayer(g, 4, 6, colorTop[3], colorBotton[3]);
                  paintBackgroundtLayer(g, 3, 4, -1, colorBotton[2]);
                  paintBackgroundtLayer(g, 2, 3, -1, colorBotton[1]);
                  paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                  return;
               }

               if (typeBg == 1) {
                  paintBackgroundtLayer(g, 4, 6, -1, -1);
                  paintBackgroundtLayer(g, 3, 3, -1, -1);
                  fillRect(g, colorTop[2], 0, -(GameScr.cmy >> 5), width, yb[2], 5);
                  fillRect(g, colorBotton[2], 0, yb[2] + bgH[2] - (GameScr.cmy >> 3), width, 70, 3);
                  paintBackgroundtLayer(g, 2, 2, -1, -1);
                  paintBackgroundtLayer(g, 1, 1, -1, colorBotton[0]);
                  return;
               }

               if (typeBg == 2) {
                  paintBackgroundtLayer(g, 5, 10, colorTop[4], colorBotton[4]);
                  paintBackgroundtLayer(g, 4, 8, -1, colorTop[2]);
                  paintBackgroundtLayer(g, 3, 5, -1, colorBotton[2]);
                  paintBackgroundtLayer(g, 2, 2, -1, colorBotton[1]);
                  paintBackgroundtLayer(g, 1, 1, -1, colorBotton[0]);
                  return;
               }

               if (typeBg == 3) {
                  int deltaY = GameScr.cmy - (325 - GameScr.gH23);
                  g.translate(0, -deltaY);
                  fillRect(
                     g,
                     !GameScr.gI().isRongThanXuatHien && !GameScr.gI().isFireWorks
                        ? colorTop[2]
                        : GameScr.gI().mautroi,
                     0,
                     deltaY - (GameScr.cmy >> 3),
                     width,
                     yb[2] - deltaY + (GameScr.cmy >> 3) + 100,
                     2
                  );
                  paintBackgroundtLayer(g, 3, 2, -1, colorBotton[2]);
                  paintBackgroundtLayer(g, 2, 0, -1, -1);
                  paintBackgroundtLayer(g, 1, 0, -1, colorBotton[0]);
                  g.translate(0, -g.getTranslateY());
                  return;
               }

               if (typeBg == 4) {
                  paintBackgroundtLayer(g, 4, 7, colorTop[3], -1);
                  paintBackgroundtLayer(g, 3, 3, -1, mGraphics.zoomLevel > 1 ? colorBotton[2] : colorTop[1]);
                  paintBackgroundtLayer(g, 2, 2, colorTop[1], colorBotton[1]);
                  paintBackgroundtLayer(g, 1, 1, -1, colorBotton[0]);
                  return;
               }

               if (typeBg == 5) {
                  paintBackgroundtLayer(g, 4, 15, colorTop[3], -1);
                  drawSun1(g);
                  g.translate(100, 10);
                  drawSun1(g);
                  g.translate(-100, -10);
                  drawSun2(g);
                  paintBackgroundtLayer(g, 3, 10, -1, -1);
                  paintBackgroundtLayer(g, 2, 6, -1, -1);
                  paintBackgroundtLayer(g, 1, 4, -1, -1);
                  g.translate(0, 27);
                  paintBackgroundtLayer(g, 1, 2, -1, -1);
                  g.translate(0, 20);
                  paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                  g.translate(-g.getTranslateX(), -g.getTranslateY());
                  return;
               }

               if (typeBg == 6) {
                  paintBackgroundtLayer(g, 5, 10, colorTop[4], colorBotton[4]);
                  drawSun1(g);
                  drawSun2(g);
                  g.translate(60, 40);
                  drawSun2(g);
                  g.translate(-60, -40);
                  paintBackgroundtLayer(g, 4, 7, -1, colorBotton[3]);
                  BackgroudEffect.paintFar(g);
                  paintBackgroundtLayer(g, 3, 4, -1, -1);
                  paintBackgroundtLayer(g, 2, 3, -1, colorBotton[1]);
                  paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                  return;
               }

               if (typeBg == 7) {
                  paintBackgroundtLayer(g, 4, 6, colorTop[3], colorBotton[3]);
                  paintBackgroundtLayer(g, 3, 5, -1, -1);
                  paintBackgroundtLayer(g, 2, 4, -1, -1);
                  paintBackgroundtLayer(g, 1, 3, -1, colorBotton[0]);
                  return;
               }

               if (typeBg != 8) {
                  if (typeBg == 9) {
                     paintBackgroundtLayer(g, 4, 8, colorTop[3], colorBotton[3]);
                     drawSun1(g);
                     drawSun2(g);
                     g.translate(-80, 20);
                     drawSun2(g);
                     g.translate(80, -20);
                     BackgroudEffect.paintFar(g);
                     paintBackgroundtLayer(g, 3, 5, -1, -1);
                     paintBackgroundtLayer(g, 2, 3, -1, -1);
                     paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                     return;
                  }

                  if (typeBg == 10) {
                     int deltaY2 = GameScr.cmy - (380 - GameScr.gH23);
                     g.translate(0, -deltaY2);
                     fillRect(
                        g,
                        GameScr.gI().isRongThanXuatHien ? GameScr.gI().mautroi : colorTop[1],
                        0,
                        deltaY2 - (GameScr.cmy >> 2),
                        width,
                        yb[1] - deltaY2 + (GameScr.cmy >> 2) + 100,
                        2
                     );
                     paintBackgroundtLayer(g, 2, 2, -1, colorBotton[1]);
                     drawSun1(g);
                     drawSun2(g);
                     paintBackgroundtLayer(g, 1, 0, -1, -1);
                     g.translate(0, -g.getTranslateY());
                     return;
                  }

                  if (typeBg == 11) {
                     paintBackgroundtLayer(g, 3, 6, colorTop[2], colorBotton[2]);
                     drawSun1(g);
                     paintBackgroundtLayer(g, 2, 3, -1, colorBotton[1]);
                     paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                     return;
                  }

                  if (typeBg == 12) {
                     g.setColor(9161471);
                     g.fillRect(0, 0, w, h);
                     paintBackgroundtLayer(g, 3, 4, -1, 14417919);
                     paintBackgroundtLayer(g, 2, 3, -1, 14417919);
                     paintBackgroundtLayer(g, 1, 2, -1, 14417919);
                     return;
                  }

                  if (typeBg == 13) {
                     g.setColor(15268088);
                     g.fillRect(0, 0, w, h);
                     paintBackgroundtLayer(g, 1, 5, -1, 15268088);
                     return;
                  }

                  if (typeBg == 15) {
                     g.setColor(2631752);
                     g.fillRect(0, 0, w, h);
                     paintBackgroundtLayer(g, 2, 3, -1, colorBotton[1]);
                     paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                     return;
                  }

                  if (typeBg != 16) {
                     if (typeBg == 19) {
                        paintBackgroundtLayer(g, 5, 10, colorTop[4], colorBotton[4]);
                        paintBackgroundtLayer(g, 4, 8, -1, colorTop[2]);
                        paintBackgroundtLayer(g, 3, 5, -1, colorBotton[2]);
                        paintBackgroundtLayer(g, 2, 2, -1, colorBotton[1]);
                        paintBackgroundtLayer(g, 1, 1, -1, colorBotton[0]);
                        return;
                     }

                     fillRect(
                        g,
                        colorBotton[3],
                        0,
                        yb[3] + bgH[3],
                        GameScr.gW,
                        yb[2] + bgH[2],
                        6
                     );
                     paintBackgroundtLayer(g, 4, 6, colorTop[3], colorBotton[3]);
                     drawSun1(g);
                     paintBackgroundtLayer(g, 3, 4, -1, colorBotton[2]);
                     paintBackgroundtLayer(g, 2, 3, -1, colorBotton[1]);
                     paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                     return;
                  }

                  paintBackgroundtLayer(g, 4, 6, colorTop[3], colorBotton[3]);

                  for (int cloudXIndex = 0; cloudXIndex < imgSunSpec.length; cloudXIndex++) {
                     g.drawImage(imgSunSpec[cloudXIndex], ((int) (cloudX[cloudXIndex])), ((int) (cloudY[cloudXIndex])), 33);
                  }

                  paintBackgroundtLayer(g, 3, 4, -1, colorBotton[2]);
                  paintBackgroundtLayer(g, 2, 3, -1, colorBotton[1]);
                  paintBackgroundtLayer(g, 1, 2, -1, colorBotton[0]);
                  return;
               }

               paintBackgroundtLayer(g, 4, 8, colorTop[3], colorBotton[3]);
               drawSun1(g);
               drawSun2(g);
               paintBackgroundtLayer(g, 3, 4, -1, colorBotton[2]);
               paintBackgroundtLayer(g, 2, 2, -1, colorBotton[1]);
               if ((TileMap.mapID < 92 || TileMap.mapID > 96) && TileMap.mapID != 51 && TileMap.mapID != 52
                  || currentScreen == loginScr) {
                  paintBackgroundtLayer(g, 1, 1, -1, colorBotton[0]);
                  return;
               }
            } else {
               g.setColor(2315859);
               g.fillRect(0, 0, w, h);
               if (tam != null) {
                  for (int x = -((GameScr.cmx >> 2) % mGraphics.getImageWidth(tam));
                     x < GameScr.gW;
                     x += mGraphics.getImageWidth(tam)
                  ) {
                     g.drawImage(tam, ((int) (x)), ((int) ((GameScr.cmy >> 3) + h / 2 - 50)), 0);
                  }

                  g.setColor(5084791);
                  g.fillRect(
                     0, (GameScr.cmy >> 3) + h / 2 - 50 + mGraphics.getImageHeight(tam), width, h
                  );
                  return;
               }
            }
         } catch (Exception ignoredException) {
            g.setColor(0);
            g.fillRect(0, 0, w, h);
         }
      }
   }

   public static void resetBg() {
   }

   private static void getYBackground(int typeBg) {
      try {
         int gH23 = GameScr.gH23;
         switch (typeBg) {
            case 0:
               yb[0] = gH23 - bgH[0] + 70;
               yb[1] = yb[0] - bgH[1] + 20;
               yb[2] = yb[1] - bgH[2] + 30;
               yb[3] = yb[2] - bgH[3] + 50;
               return;
            case 1:
               yb[0] = gH23 - bgH[0] + 120;
               yb[1] = yb[0] - bgH[1] + 40;
               yb[2] = yb[1] - 90;
               yb[3] = yb[2] - 25;
               return;
            case 2:
               yb[0] = gH23 - bgH[0] + 150;
               yb[1] = yb[0] - bgH[1] - 60;
               yb[2] = yb[1] - bgH[2] - 40;
               yb[3] = yb[2] - bgH[3] - 10;
               yb[4] = yb[3] - bgH[4];
               return;
            case 3:
               yb[0] = gH23 - bgH[0] + 10;
               yb[1] = yb[0] + 80;
               yb[2] = yb[1] - bgH[2] - 10;
               return;
            case 4:
               yb[0] = gH23 - bgH[0] + 130;
               yb[1] = yb[0] - bgH[1];
               yb[2] = yb[1] - bgH[2] - 20;
               yb[3] = yb[1] - bgH[2] - 80;
               return;
            case 5:
               yb[0] = gH23 - bgH[0] + 40;
               yb[1] = yb[0] - bgH[1] + 10;
               yb[2] = yb[1] - bgH[2] + 15;
               yb[3] = yb[2] - bgH[3] + 50;
               return;
            case 6:
               yb[0] = gH23 - bgH[0] + 100;
               yb[1] = yb[0] - bgH[1] - 30;
               yb[2] = yb[1] - bgH[2] + 10;
               yb[3] = yb[2] - bgH[3] + 15;
               yb[4] = yb[3] - bgH[4] + 15;
               return;
            case 7:
               yb[0] = gH23 - bgH[0] + 20;
               yb[1] = yb[0] - bgH[1] + 15;
               yb[2] = yb[1] - bgH[2] + 20;
               yb[3] = yb[1] - bgH[2] - 10;
               return;
            case 8:
               yb[0] = gH23 - 103 + 150;
               if (TileMap.mapID == 103) {
                  yb[0] = yb[0] - 100;
               }

               yb[1] = yb[0] - bgH[1] - 10;
               yb[2] = yb[1] - bgH[2] + 40;
               yb[3] = yb[2] - bgH[3] + 10;
               return;
            case 9:
               yb[0] = gH23 - bgH[0] + 100;
               yb[1] = yb[0] - bgH[1] + 22;
               yb[2] = yb[1] - bgH[2] + 50;
               yb[3] = yb[2] - bgH[3];
               return;
            case 10:
               yb[0] = gH23 - bgH[0] - 45;
               yb[1] = yb[0] - bgH[1] - 10;
               return;
            case 11:
               yb[0] = gH23 - bgH[0] + 60;
               yb[1] = yb[0] - bgH[1] + 5;
               yb[2] = yb[1] - bgH[2] - 15;
               return;
            case 12:
               yb[0] = gH23 + 40;
               yb[1] = yb[0] - 40;
               yb[2] = yb[1] - 40;
               return;
            case 13:
               yb[0] = gH23 - 80;
               yb[1] = yb[0];
               return;
            case 14:
            case 17:
            case 18:
            default:
               yb[0] = gH23 - bgH[0] + 75;
               yb[1] = yb[0] - bgH[1] + 50;
               yb[2] = yb[1] - bgH[2] + 50;
               yb[3] = yb[2] - bgH[3] + 90;
               return;
            case 15:
               yb[0] = gH23 - 20;
               yb[1] = yb[0] - 80;
               return;
            case 16:
               yb[0] = gH23 - bgH[0] + 75;
               yb[1] = yb[0] - bgH[1] + 50;
               yb[2] = yb[1] - bgH[2] + 50;
               yb[3] = yb[2] - bgH[3] + 90;
               return;
            case 19:
               yb[0] = gH23 - bgH[0] + 150;
               yb[1] = yb[0] - bgH[1] - 60;
               yb[2] = yb[1] - bgH[2] - 40;
               yb[3] = yb[2] - bgH[3] - 10;
               yb[4] = yb[3] - bgH[4];
         }
      } catch (Exception ignoredException) {
         for (int ybIndex = 0; ybIndex < yb.length; ybIndex++) {
            yb[ybIndex] = 1;
         }
      }
   }

   public static void loadBG(int typeBG) {
      try {
         isLoadBGok = true;
         if (typeBg == 12) {
            BackgroudEffect.yfog = TileMap.pxh - 100;
         } else {
            BackgroudEffect.yfog = TileMap.pxh - 160;
         }

         Res.outz("load bg id= " + typeBG + "map ID= " + TileMap.mapID);
         BackgroudEffect.clearImage();
         randomRaintEff(typeBG);
         if (TileMap.lastBgID == typeBG && TileMap.lastType == TileMap.bgType && paintBG && imgBG != null
            || typeBG == -1) {
            return;
         }

         transY = 12;
         TileMap.lastBgID = (byte)typeBG;
         TileMap.lastType = (byte)TileMap.bgType;
         layerSpeed = new int[]{1, 2, 3, 7, 8};
         moveX = new int[5];
         moveXSpeed = new int[5];
         typeBg = typeBG;
         isBoltEff = false;
         GameScr.firstY = GameScr.cmy;
         imgBG = null;
         imgSun = null;
         imgCaycot = null;
         BackgroudEffect.vBgEffect.removeAllElements();
         GameScr.firstY = -1;
         switch (typeBg) {
            case 0:
               imgCaycot = mSystem.loadImageRMS("/bg/caycot.png");
               layerSpeed = new int[]{1, 3, 5, 7};
               nBg = 4;
               if (TileMap.bgType == 2) {
                  transY = 8;
               }
               break;
            case 1:
               transY = 7;
               nBg = 4;
               break;
            case 2:
               moveX = new int[]{0, 0, 1, 0, 0};
               moveXSpeed = new int[]{0, 0, 2, 0, 0};
               nBg = 5;
               break;
            case 3:
               nBg = 3;
               break;
            case 4:
               BackgroudEffect.addEffect(3);
               moveX = new int[]{0, 1, 0, 0, 0};
               moveXSpeed = new int[]{0, 1, 0, 0, 0};
               nBg = 4;
               break;
            case 5:
               nBg = 4;
               break;
            case 6:
               moveX = new int[]{1, 0, 0, 0, 0};
               moveXSpeed = new int[]{2, 0, 0, 0, 0};
               nBg = 5;
               break;
            case 7:
               nBg = 4;
               break;
            case 8:
               transY = 8;
               nBg = 4;
               break;
            case 9:
               BackgroudEffect.addEffect(9);
               nBg = 4;
               break;
            case 10:
               nBg = 2;
               break;
            case 11:
               transY = 7;
               layerSpeed[2] = 0;
               nBg = 3;
               break;
            case 12:
               moveX = new int[]{1, 1, 0, 0, 0};
               moveXSpeed = new int[]{2, 1, 0, 0, 0};
               nBg = 3;
               break;
            case 13:
               nBg = 2;
               break;
            case 14:
            case 17:
            case 18:
            default:
               layerSpeed = new int[]{1, 3, 5, 7};
               nBg = 4;
               break;
            case 15:
               Res.outz("HELL");
               nBg = 2;
               break;
            case 16:
               layerSpeed = new int[]{1, 3, 5, 7};
               nBg = 4;
               break;
            case 19:
               moveX = new int[]{0, 2, 1, 0, 0};
               moveXSpeed = new int[]{0, 2, 1, 0, 0};
               nBg = 5;
         }

         if (typeBG <= 16) {
            skyColor = StaticObj.SKYCOLOR[typeBg];
         } else {
            try {
               String path = "/bg/b" + typeBg + 3 + ".png";
               if (TileMap.bgType != 0) {
                  path = "/bg/b" + typeBg + 3 + "-" + TileMap.bgType + ".png";
               }

               int[] intValues = new int[1];
               Image img;
               (img = mSystem.loadImageRMS(path)).getRGB(intValues, 0, 1, mGraphics.getRealImageWidth(img) / 2, 0, 1, 1);
               skyColor = intValues[0];
            } catch (Exception ignoredException) {
               skyColor = StaticObj.SKYCOLOR[StaticObj.SKYCOLOR.length - 1];
            }
         }

         colorTop = new int[StaticObj.SKYCOLOR.length];
         colorBotton = new int[StaticObj.SKYCOLOR.length];

         for (int colorBottonIndex = 0; colorBottonIndex < StaticObj.SKYCOLOR.length; colorBottonIndex++) {
            colorTop[colorBottonIndex] = StaticObj.SKYCOLOR[colorBottonIndex];
            colorBotton[colorBottonIndex] = StaticObj.SKYCOLOR[colorBottonIndex];
         }

         if (lowGraphic) {
            tam = mSystem.loadImageRMS("/bg/b63.png");
            return;
         }

         imgBG = new Image[nBg];
         bgW = new int[nBg];
         bgH = new int[nBg];
         colorBotton = new int[nBg];
         colorTop = new int[nBg];
         if (TileMap.bgType == 100) {
            imgBG[0] = mSystem.loadImageRMS("/bg/b100.png");
            imgBG[1] = mSystem.loadImageRMS("/bg/b100.png");
            imgBG[2] = mSystem.loadImageRMS("/bg/b82-1.png");
            imgBG[3] = mSystem.loadImageRMS("/bg/b93.png");

            for (int bgHIndex = 0; bgHIndex < nBg; bgHIndex++) {
               if (imgBG[bgHIndex] != null) {
                  int[] intValues2 = new int[1];
                  imgBG[bgHIndex].getRGB(intValues2, 0, 1, mGraphics.getRealImageWidth(imgBG[bgHIndex]) / 2, 0, 1, 1);
                  colorTop[bgHIndex] = intValues2[0];
                  intValues2 = new int[1];
                  imgBG[bgHIndex]
                     .getRGB(
                        intValues2, 0, 1, mGraphics.getRealImageWidth(imgBG[bgHIndex]) / 2, mGraphics.getRealImageHeight(imgBG[bgHIndex]) - 1, 1, 1
                     );
                  colorBotton[bgHIndex] = intValues2[0];
                  bgW[bgHIndex] = mGraphics.getImageWidth(imgBG[bgHIndex]);
                  bgH[bgHIndex] = mGraphics.getImageHeight(imgBG[bgHIndex]);
               } else if (nBg > 1) {
                  imgBG[bgHIndex] = mSystem.loadImageRMS("/bg/b" + typeBg + "0.png");
                  bgW[bgHIndex] = mGraphics.getImageWidth(imgBG[bgHIndex]);
                  bgH[bgHIndex] = mGraphics.getImageHeight(imgBG[bgHIndex]);
               }
            }
         } else {
            for (int bgHIndex2 = 0; bgHIndex2 < nBg; bgHIndex2++) {
               String path2 = "/bg/b" + typeBg + bgHIndex2 + ".png";
               if (TileMap.bgType != 0) {
                  path2 = "/bg/b" + typeBg + bgHIndex2 + "-" + TileMap.bgType + ".png";
               }

               imgBG[bgHIndex2] = mSystem.loadImageRMS(path2);
               if (imgBG[bgHIndex2] != null) {
                  int[] intValues3 = new int[1];
                  imgBG[bgHIndex2].getRGB(intValues3, 0, 1, mGraphics.getRealImageWidth(imgBG[bgHIndex2]) / 2, 0, 1, 1);
                  colorTop[bgHIndex2] = intValues3[0];
                  intValues3 = new int[1];
                  imgBG[bgHIndex2]
                     .getRGB(
                        intValues3, 0, 1, mGraphics.getRealImageWidth(imgBG[bgHIndex2]) / 2, mGraphics.getRealImageHeight(imgBG[bgHIndex2]) - 1, 1, 1
                     );
                  colorBotton[bgHIndex2] = intValues3[0];
                  bgW[bgHIndex2] = mGraphics.getImageWidth(imgBG[bgHIndex2]);
                  bgH[bgHIndex2] = mGraphics.getImageHeight(imgBG[bgHIndex2]);
               } else if (nBg > 1) {
                  imgBG[bgHIndex2] = mSystem.loadImageRMS("/bg/b" + typeBg + "0.png");
                  if (imgBG[bgHIndex2] != null) {
                     bgW[bgHIndex2] = mGraphics.getImageWidth(imgBG[bgHIndex2]);
                     bgH[bgHIndex2] = mGraphics.getImageHeight(imgBG[bgHIndex2]);
                  }
               }
            }
         }

         getYBackground(typeBg);
         Res.outz("5");
         cloudX = new int[]{
            GameScr.gW / 2 - 40,
            GameScr.gW / 2 + 40,
            GameScr.gW / 2 - 100,
            GameScr.gW / 2 - 80,
            GameScr.gW / 2 - 120
         };
         cloudY = new int[]{130, 100, 150, 140, 80};
         imgSunSpec = null;
         if (typeBg != 0) {
            if (typeBg == 2) {
               imgSun = mSystem.loadImageRMS("/bg/sun0.png");
               sunX = GameScr.gW / 2 + 50;
               sunY = yb[4] - 40;
               TileMap.imgWaterflow = mSystem.loadImageRMS("/tWater/wts");
            } else if (typeBg == 19) {
               TileMap.imgWaterflow = mSystem.loadImageRMS("/tWater/water_flow_32");
            } else if (typeBg == 4) {
               imgSun = mSystem.loadImageRMS("/bg/sun2.png");
               sunX = GameScr.gW / 2 + 30;
               sunY = yb[3];
            } else if (typeBg == 7) {
               imgSun = mSystem.loadImageRMS("/bg/sun3" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/sun4" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW - GameScr.gW / 3;
               sunY = yb[3] - 80;
               sunX2 = sunX - 100;
               sunY2 = yb[3] - 30;
            } else if (typeBg == 6) {
               imgSun = mSystem.loadImageRMS("/bg/sun5" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/sun6" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW - GameScr.gW / 3;
               sunY = yb[4];
               sunX2 = sunX - 100;
               sunY2 = yb[4] + 20;
            } else if (typeBG == 5) {
               imgSun = mSystem.loadImageRMS("/bg/sun8" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/sun7" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW / 2 - 50;
               sunY = yb[3] + 20;
               sunX2 = GameScr.gW / 2 + 20;
               sunY2 = yb[3] - 30;
            } else if (typeBg == 8 && TileMap.mapID < 90) {
               imgSun = mSystem.loadImageRMS("/bg/sun9" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/sun10" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW / 2 - 30;
               sunY = yb[3] + 60;
               sunX2 = GameScr.gW / 2 + 20;
               sunY2 = yb[3] + 10;
            } else if (typeBG == 9) {
               imgSun = mSystem.loadImageRMS("/bg/sun11" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/sun12" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW - GameScr.gW / 3;
               sunY = yb[4] + 20;
               sunX2 = sunX - 80;
               sunY2 = yb[4] + 40;
            } else if (typeBG == 10) {
               imgSun = mSystem.loadImageRMS("/bg/sun13" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/sun14" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW - GameScr.gW / 3;
               sunY = yb[1] - 30;
               sunX2 = sunX - 80;
               sunY2 = yb[1];
            } else if (typeBG == 11) {
               imgSun = mSystem.loadImageRMS("/bg/sun15" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               imgSun2 = mSystem.loadImageRMS("/bg/b113" + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png");
               sunX = GameScr.gW / 2 - 30;
               sunY = yb[2] - 30;
            } else if (typeBG == 12) {
               cloudY = new int[]{200, 170, 220, 150, 250};
            } else if (typeBG == 16) {
               cloudX = new int[]{90, 170, 250, 320, 400, 450, 500};
               cloudY = new int[]{
                  yb[2] + 5,
                  yb[2] - 20,
                  yb[2] - 50,
                  yb[2] - 30,
                  yb[2] - 50,
                  yb[2],
                  yb[2] - 40
               };
               imgSunSpec = new Image[7];

               for (int imgSunSpecIndex = 0; imgSunSpecIndex < imgSunSpec.length; imgSunSpecIndex++) {
                  short shortValue = 160;
                  if (imgSunSpecIndex == 1 || imgSunSpecIndex == 3) {
                     shortValue = 161;
                  }

                  imgSunSpec[imgSunSpecIndex] = mSystem.loadImageRMS("/bg/sun" + shortValue + ".png");
               }
            } else if (typeBG == 19) {
               moveX = new int[]{0, 2, 1, 0, 0};
               moveXSpeed = new int[]{0, 2, 1, 0, 0};
               nBg = 5;
            } else {
               imgSun = null;
               imgSun2 = null;
               imgSun = mSystem.loadImageRMS(
                  "/bg/sun" + typeBG + (TileMap.bgType == 0 ? "" : "-" + TileMap.bgType) + ".png"
               );
               if (mSystem.loadImageRMS("/tWater/water_flow_" + typeBG) != null) {
                  TileMap.imgWaterflow = mSystem.loadImageRMS("/tWater/water_flow_" + typeBG);
               }

               sunX = GameScr.gW - GameScr.gW / 3;
               sunY = yb[2] - 30;
            }
         }

         paintBG = false;
         if (true) {
            paintBG = true;
            return;
         }
      } catch (Exception ignoredException2) {
         isLoadBGok = false;
      }
   }

   private static void randomRaintEff(int typeBG) {
      for (int bgRainIndex = 0; bgRainIndex < bgRain.length; bgRainIndex++) {
         if (typeBG == bgRain[bgRainIndex] && Res.random(0, 2) == 0) {
            BackgroudEffect.addEffect(0);
            return;
         }
      }
   }

   public final void keyPressed(int keyAsciiPressValue) {
      lastTimePress = System.currentTimeMillis();
      if (keyAsciiPressValue >= 48 && keyAsciiPressValue <= 57 || keyAsciiPressValue >= 65 && keyAsciiPressValue <= 122 || keyAsciiPressValue == 10 || keyAsciiPressValue == 8 || keyAsciiPressValue == 13 || keyAsciiPressValue == 32 || keyAsciiPressValue == 31) {
         keyAsciiPress = keyAsciiPressValue;
      }

      if (currentDialog != null) {
         currentDialog.keyPress(keyAsciiPressValue);
         keyAsciiPress = 0;
      } else {
         currentScreen.keyPress(keyAsciiPressValue);
         switch (keyAsciiPressValue) {
            case -39:
            case -2:
               if (currentScreen instanceof GameScr || currentScreen instanceof CrackBallScr) {
                  Char.myCharz();
               }

               keyHold[8] = true;
               keyPressed[8] = true;
               return;
            case -38:
            case -1:
               if (currentScreen instanceof GameScr || currentScreen instanceof CrackBallScr) {
                  Char.myCharz();
               }

               keyHold[2] = true;
               keyPressed[2] = true;
               return;
            case -22:
            case -7:
               keyHold[13] = true;
               keyPressed[13] = true;
               return;
            case -21:
            case -6:
               keyHold[12] = true;
               keyPressed[12] = true;
               return;
            case -5:
            case 10:
               if (currentScreen instanceof GameScr || currentScreen instanceof CrackBallScr) {
                  Char.myCharz();
               }

               keyHold[5] = true;
               keyPressed[5] = true;
               return;
            case -4:
               if (currentScreen instanceof GameScr || currentScreen instanceof CrackBallScr) {
                  Char.myCharz();
               }

               keyHold[6] = true;
               keyPressed[6] = true;
               return;
            case -3:
               if (currentScreen instanceof GameScr || currentScreen instanceof CrackBallScr) {
                  Char.myCharz();
               }

               keyHold[4] = true;
               keyPressed[4] = true;
               return;
            case 35:
               keyHold[11] = true;
               keyPressed[11] = true;
               return;
            case 42:
               keyHold[10] = true;
               keyPressed[10] = true;
               return;
            case 48:
               keyHold[0] = true;
               keyPressed[0] = true;
               return;
            case 49:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[1] = true;
                  keyPressed[1] = true;
               }

               return;
            case 50:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[2] = true;
                  keyPressed[2] = true;
               }

               return;
            case 51:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[3] = true;
                  keyPressed[3] = true;
               }

               return;
            case 52:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[4] = true;
                  keyPressed[4] = true;
               }

               return;
            case 53:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[5] = true;
                  keyPressed[5] = true;
               }
            default:
               return;
            case 54:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[6] = true;
                  keyPressed[6] = true;
               }

               return;
            case 55:
               keyHold[7] = true;
               keyPressed[7] = true;
               return;
            case 56:
               if (currentScreen == CrackBallScr.instance
                  || currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
                  keyHold[8] = true;
                  keyPressed[8] = true;
               }

               return;
            case 57:
               keyHold[9] = true;
               keyPressed[9] = true;
         }
      }
   }

   public final void keyReleased(int subcommand) {
      keyAsciiPress = 0;
      switch (subcommand) {
         case -39:
         case -2:
            keyHold[8] = false;
            return;
         case -38:
         case -1:
            keyHold[2] = false;
            return;
         case -22:
         case -7:
            keyHold[13] = false;
            keyReleased[13] = true;
            return;
         case -21:
         case -6:
            keyHold[12] = false;
            keyReleased[12] = true;
            return;
         case -5:
         case 10:
            keyHold[5] = false;
            keyReleased[5] = true;
            return;
         case -4:
            keyHold[6] = false;
            return;
         case -3:
            keyHold[4] = false;
            return;
         case 35:
            keyHold[11] = false;
            keyReleased[11] = true;
            return;
         case 42:
            keyHold[10] = false;
            keyReleased[10] = true;
            return;
         case 48:
            keyHold[0] = false;
            keyReleased[0] = true;
            return;
         case 49:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[1] = false;
               keyReleased[1] = true;
            }

            return;
         case 50:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[2] = false;
               keyReleased[2] = true;
            }

            return;
         case 51:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[3] = false;
               keyReleased[3] = true;
            }

            return;
         case 52:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[4] = false;
               keyReleased[4] = true;
            }

            return;
         case 53:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[5] = false;
               keyReleased[5] = true;
            }
         default:
            return;
         case 54:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[6] = false;
               keyReleased[6] = true;
            }

            return;
         case 55:
            keyHold[7] = false;
            keyReleased[7] = true;
            return;
         case 56:
            if (currentScreen == GameScr.instance && isMoveNumberPad && !ChatTextField.gI().isShow) {
               keyHold[8] = false;
               keyReleased[8] = true;
            }

            return;
         case 57:
            keyHold[9] = false;
            keyReleased[9] = true;
      }
   }

   public final void onPointerDragged(int x, int y) {
      isPointerSelect = false;
      if (Res.abs(x - pxLast) >= 10 || Res.abs(y - pyLast) >= 10) {
         isPointerClick = false;
         isPointerDown = true;
         isPointerMove = true;
      }

      px = x;
      py = y;
      if (++curPos > 3) {
         curPos = 0;
      }

      arrPos[curPos] = new Position(x, y);
   }

   public final void onPointerPressed(int x, int y) {
      isPointerSelect = false;
      isPointerJustRelease = false;
      isPointerJustDown = true;
      isPointerDown = true;
      isPointerClick = false;
      isPointerMove = false;
      lastTimePress = System.currentTimeMillis();
      pxFirst = x;
      pyFirst = y;
      pxLast = x;
      pyLast = y;
      px = x;
      py = y;
   }

   public final void onPointerReleased(int x, int y) {
      if (!isPointerMove) {
         isPointerSelect = true;
      }

      isPointerDown = false;
      isPointerJustRelease = true;
      isPointerMove = false;
      isPointerClick = true;
      mScreen.keyTouch = -1;
      px = x;
      py = y;
   }

   public static boolean isPointerHoldIn(int x, int y, int w, int h) {
      return !isPointerDown && !isPointerJustRelease
         ? false
         : px >= x && px <= x + w && py >= y && py <= y + h;
   }

   public static void clearKeyPressed() {
      for (int keyPressedIndex = 0; keyPressedIndex < keyPressed.length; keyPressedIndex++) {
         keyPressed[keyPressedIndex] = false;
      }

      isPointerJustRelease = false;
   }

   public static void clearKeyHold() {
      for (int keyHoldIndex = 0; keyHoldIndex < keyHold.length; keyHoldIndex++) {
         keyHold[keyHoldIndex] = false;
      }
   }

   public final void paint(Graphics graphics) {
      this.g.graphics = graphics;
      debugPaint.removeAllElements();
      mScreen currentScreenValue = currentScreen;
      if (currentScreen != null) {
         try {
            currentScreenValue.paint(this.g);
         } catch (NullPointerException nullPointerException) {
            long lastPaintNpeLogAtValue;
            if ((lastPaintNpeLogAtValue = System.currentTimeMillis()) - lastPaintNpeLogAt >= 1000L) {
               lastPaintNpeLogAt = lastPaintNpeLogAtValue;
               System.err.println("[PAINT-NPE] screen=" + currentScreenValue.getClass().getName() + " tick=" + gameTick + " map=" + TileMap.mapID);
               if (currentScreenValue instanceof GameScr) {
                  ((GameScr)currentScreenValue).logPaintNullPointerContext();
               }

               nullPointerException.printStackTrace();
            }

            throw nullPointerException;
         }
      }

      this.g.translate(-this.g.getTranslateX(), -this.g.getTranslateY());
      this.g.setClip(0, 0, w, h);
      if (panel.isShow) {
         panel.paint(this.g);
         if (panel2 != null && panel2.isShow) {
            panel2.paint(this.g);
         }

         if (panel.chatTField != null && panel.chatTField.isShow) {
            panel.chatTField.paint(this.g);
         }

         if (panel2 != null && panel2.chatTField != null && panel2.chatTField.isShow) {
            panel2.chatTField.paint(this.g);
         }
      }

      if (GameScr.showOnScreenDebug) {
         Res.paintOnScreenDebug(this.g);
      }

      InfoDlg.paint(this.g);
      if (currentDialog != null) {
         currentDialog.paint(this.g);
      } else if (menu.showMenu) {
         resetTrans(this.g);
         menu.paintMenu(this.g);
      }

      GameScr.info1.paint(this.g);
      GameScr.info2.paint(this.g);
      if (GameScr.gI().popUpYesNo != null) {
         GameScr.gI().popUpYesNo.paint(this.g);
      }

      if (ChatPopup.currChatPopup != null) {
         ChatPopup.currChatPopup.paint(this.g);
      }

      mGraphics graphics2 = this.g;
      if (ChatPopup.serverChatPopUp == null && !Char.myCharz().isUsePlane && !Char.myCharz().isTeleport) {
         mGraphics gValue = graphics2;

         try {
            if (Hint.isPaintArrow
               && (
                  Hint.x <= GameScr.cmx
                     || Hint.x >= GameScr.cmx + GameScr.gW
                     || Hint.y <= GameScr.cmy
                     || Hint.y >= GameScr.cmy + GameScr.gH
               )
               && gameTick % 10 >= 5
               && ChatPopup.currChatPopup == null
               && ChatPopup.serverChatPopUp == null
               && !panel.isShow
               && Hint.isCamera) {
               int deltaX = Hint.x - Char.myCharz().cx;
               int deltaY = Hint.y - Char.myCharz().cy;
               int x = 0;
               int y = 0;
               byte transform = 0;
               if (deltaX > 0 && deltaY >= 0) {
                  if (Res.abs(deltaX) >= Res.abs(deltaY)) {
                     x = GameScr.gW - 10;
                     y = GameScr.gH / 2 + 30;
                     if (isTouch) {
                        y = GameScr.gH / 2 + 10;
                     }

                     transform = 0;
                  } else {
                     x = GameScr.gW / 2;
                     y = GameScr.gH - 10;
                     transform = 5;
                  }
               } else if (deltaX >= 0 && deltaY < 0) {
                  if (Res.abs(deltaX) >= Res.abs(deltaY)) {
                     x = GameScr.gW - 10;
                     y = GameScr.gH / 2 + 30;
                     if (isTouch) {
                        y = GameScr.gH / 2 + 10;
                     }

                     transform = 0;
                  } else {
                     x = GameScr.gW / 2;
                     y = 10;
                     transform = 6;
                  }
               }

               if (deltaX < 0 && deltaY >= 0) {
                  if (Res.abs(deltaX) >= Res.abs(deltaY)) {
                     x = 10;
                     y = GameScr.gH / 2 + 30;
                     if (isTouch) {
                        y = GameScr.gH / 2 + 10;
                     }

                     transform = 3;
                  } else {
                     x = GameScr.gW / 2;
                     y = GameScr.gH - 10;
                     transform = 5;
                  }
               } else if (deltaX <= 0 && deltaY < 0) {
                  if (Res.abs(deltaX) >= Res.abs(deltaY)) {
                     x = 10;
                     y = GameScr.gH / 2 + 30;
                     if (isTouch) {
                        y = GameScr.gH / 2 + 10;
                     }

                     transform = 3;
                  } else {
                     x = GameScr.gW / 2;
                     y = 10;
                     transform = 6;
                  }
               }

               GameScr.resetTranslate(gValue);
               gValue.drawRegion(GameScr.arrow, 0, 0, 13, 16, transform, x, y, StaticObj.VCENTER_HCENTER);
            }
         } catch (Exception ignoredException) {
         }

         if (menu.tDelay == 0
            && Hint.isPaint
            && ChatPopup.scr == null
            && !Char.ischangingMap
            && currentScreen == GameScr.gI()
            && (!panel.isShow || panel.cmx == 0)) {
            if (Hint.isCamera) {
               graphics2.translate(-GameScr.cmx, -GameScr.cmy);
            }

            if (Hint.trans == 0) {
               graphics2.drawImage(Panel.imgBantay, ((int) (Hint.x - 15)), ((int) (Hint.y)), 0);
            }

            if (Hint.trans == 1) {
               graphics2.drawRegion(
                  Panel.imgBantay, 0, 0, 14, 16, 2, Hint.x + 15, Hint.y, StaticObj.TOP_RIGHT
               );
            }

            if (Hint.paintFlare) {
               graphics2.drawImage(ItemMap.imageFlare, ((int) (Hint.x)), ((int) (Hint.y)), 3);
            }

            graphics2.translate(-graphics2.getTranslateX(), -graphics2.getTranslateY());
         }
      }

      if (ChatPopup.serverChatPopUp != null) {
         ChatPopup.serverChatPopUp.paint(this.g);
      }

      for (int effect2Index = 0; effect2Index < Effect2.vEffect2.size(); effect2Index++) {
         Effect2 effect2;
         if ((effect2 = (Effect2)Effect2.vEffect2.elementAt(effect2Index)) instanceof ChatPopup
            && !effect2.equals(ChatPopup.currChatPopup)
            && !effect2.equals(ChatPopup.serverChatPopUp)) {
            effect2.paint(this.g);
         }
      }

      if (currentDialog != null) {
         currentDialog.paint(this.g);
      }

      if (Char.isLoadingMap || LoginScr.isContinueToLogin || ServerListScreen.waitToLogin || SelectCharScr.isWait) {
         mGraphics graphics3 = this.g;
         String text = "";
         resetTrans(graphics3);
         graphics3.setColor(0);
         graphics3.fillRect(0, 0, w, h);
         graphics3.drawImage(LoginScr.imgTitle, ((int) (w / 2)), ((int) (h / 2 - 24)), StaticObj.BOTTOM_HCENTER);
         paintShukiren(hw, h / 2 + 24, graphics3);
         mFont.tahoma_7b_white
            .drawString(
               graphics3,
               mResources.PLEASEWAIT + (LoginScr.timeLogin > 0 ? " " + LoginScr.timeLogin + "s" : text),
               w / 2,
               h / 2,
               2
            );
         if (timeLoading > 0 && LoginScr.timeLogin <= 0 && mSystem.currentTimeMillis() - TIMEOUT >= 1000L) {
            if (--timeLoading == 0) {
               timeLoading = 15;
            }

            TIMEOUT = mSystem.currentTimeMillis();
         }
      }

      resetTrans(this.g);
      EffecMn.paintLayer4(this.g);
      if (open3Hour && !isLoading) {
         if (currentScreen == loginScr || currentScreen == serverScreen || currentScreen == serverScr) {
            this.g.drawImage(img18, ((int) (5)), ((int) (5)), 0);
         }

         if (currentScreen == CreateCharScr.instance) {
            this.g.drawImage(img18, ((int) (hw)), ((int) (5)), 0);
         }
      }

      for (int overlayDebugLineIndex = 0; overlayDebugLineIndex < overlayDebugLines.size(); overlayDebugLineIndex++) {
         String text2 = (String)overlayDebugLines.elementAt(overlayDebugLineIndex);
         mFont.tahoma_7b_red
            .drawString(this.g, text2, w, h - this.overlayDebugLineHeight - overlayDebugLineIndex * this.overlayDebugLineHeight, 1);
         mFont.tahoma_7b_white
            .drawString(
               this.g, text2, w - 1, h - this.overlayDebugLineHeight - overlayDebugLineIndex * this.overlayDebugLineHeight - 1, 1
            );
      }

      resetTrans(this.g);
      int y2 = h / 4;
      if (currentScreen != null && currentScreen instanceof GameScr && thongBaoTest != null) {
         this.g.setClip(60, y2, w - 120, mFont.tahoma_7_white.getHeight() + 2);
         mFont.tahoma_7_grey.drawString(this.g, thongBaoTest, xThongBaoTranslate, y2 + 1, 0);
         mFont.tahoma_7_yellow.drawString(this.g, thongBaoTest, xThongBaoTranslate, y2, 0);
         this.g.setClip(0, 0, w, h);
      }
   }

   public static void endDlg() {
      currentDialog = null;
      InfoDlg.hide();
   }

   public static void startOKDlg(String info) {
      msgdlg.setInfo(info, null, new Command(mResources.OK, instance, 8882, null), null);
      currentDialog = msgdlg;
   }

   public static void startWaitDlg(String info) {
      msgdlg.setInfo(info, null, new Command(mResources.CANCEL, instance, 8882, null), null);
      currentDialog = msgdlg;
      msgdlg.isWait = true;
   }

   public static void showActionWaitDialog(String info) {
      msgdlg.setInfo(info, null, new Command(mResources.CANCEL, instance, 8882, null), null);
      currentDialog = msgdlg;
      msgdlg.isWait = true;
   }

   public static void startWaitDlg() {
      Char.isLoadingMap = true;
   }

   public static void startOK(String info, int actionID, Object p) {
      msgdlg.setInfo(info, null, new Command(mResources.OK, instance, actionID, p), null);
      msgdlg.show();
   }

   public static void startYesNoDlg(String info, Command cmdYes, Command cmdNo) {
      msgdlg.setInfo(info, cmdYes, null, cmdNo);
      msgdlg.show();
   }

   public final void sizeChanged(int value, int value2) {
   }

   public static boolean isGetResourceFromServer() {
      return true;
   }

   public final boolean startDust(int dir, int x, int y) {
      if (lowGraphic) {
         return false;
      } else {
         int dustStateIndex = dir == 1 ? 0 : 1;
         if (this.dustState[dustStateIndex] != -1) {
            return false;
         } else {
            this.dustState[dustStateIndex] = 0;
            this.dustX[dustStateIndex] = x;
            this.dustY[dustStateIndex] = y;
            return true;
         }
      }
   }

   private static void loadWaterSplash() {
      if (!lowGraphic) {
         imgWS = new Image[3];

         for (int imgWSIndex = 0; imgWSIndex < 3; imgWSIndex++) {
            imgWS[imgWSIndex] = mSystem.loadImage("/e/w" + imgWSIndex + ".png");
         }

         (wsState = new int[2])[0] = wsState[1] = -1;
      }
   }

   public final void updateDust() {
      if (!lowGraphic) {
         try {
            for (int dustStateIndex = 0; dustStateIndex < 2; dustStateIndex++) {
               if (this.dustState[dustStateIndex] != -1) {
                  this.dustState[dustStateIndex]++;
                  if (this.dustState[dustStateIndex] >= 5) {
                     this.dustState[dustStateIndex] = -1;
                  }

                  if (dustStateIndex == 0) {
                     this.dustX[dustStateIndex]--;
                  } else {
                     this.dustX[dustStateIndex]++;
                  }

                  this.dustY[dustStateIndex]--;
               }
            }
         } catch (Exception ignoredException) {
         }
      }
   }

   public static boolean isPaint(int x, int y) {
      if (x < GameScr.cmx) {
         return false;
      } else if (x > GameScr.cmx + GameScr.gW) {
         return false;
      } else {
         return y < GameScr.cmy ? false : y <= GameScr.cmy + GameScr.gH + 30;
      }
   }

   public static void paintShukiren(int x, int y, mGraphics g) {
      int value = shurikenFrameTick % (mSystem.clientType == 7 ? 8 : 9);
      if (++shurikenFrameTick == 1000) {
         shurikenFrameTick = 0;
      }

      g.drawRegion(imgShuriken, 0, value << 4, 16, 16, 0, x, y, 3);
   }

   public static void queueResetDialog(String pendingResetDialogTextValue, int pendingResetDialogActionValue, Object entry) {
      pendingResetDialogAction = pendingResetDialogActionValue;
      pendingResetDialogPayload = null;
      pendingResetDialogText = pendingResetDialogTextValue;
      if (instance != null) {
         GameCanvas instanceValue = instance;
         instance.isResetDialogPending = true;
      }
   }

   public static boolean isPointer(int x, int y, int w, int h) {
      return !isPointerDown && !isPointerJustRelease
         ? false
         : px >= x && px <= x + w && py >= y && py <= y + h;
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 999:
            mSystem.closeBanner();
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 8881:
            String p2 = (String)p;

            try {
               GameMidlet.instance.platformRequest(p2);
            } catch (Exception exception) {
               exception.printStackTrace();
            }

            currentDialog = null;
            return;
         case 8882:
            InfoDlg.hide();
            currentDialog = null;
            if (LoginScr.isLoggingIn) {
               Service.gI().clearPendingLogin();
               LoginScr.isLoggingIn = false;
            }

            ServerListScreen.clearPendingLoginAction();
            ServerListScreen.isWaitingForConnection = 0;
            ServerListScreen.field_r_b491fafc = 0;
            ServerListScreen.connectionFailureCheckAt = 0L;
            ServerListScreen.loginActionAfterConnect = 0;
            ServerListScreen.isAutoConect = false;
            ServerListScreen.countDieConnect = 0;
            return;
         case 8884:
            currentDialog = null;
            InfoDlg.hide();
            if (serverScr == null) {
               serverScr = new ServerScr();
            }

            serverScr.switchToMe();
            return;
         case 8885:
            GameMidlet.instance.exit();
            return;
         case 8886:
            currentDialog = null;
            InfoDlg.hide();
            String name = (String)p;
            Service.gI().addFriend(name);
            return;
          case 8887:
             currentDialog = null;
             InfoDlg.hide();
             idAction = ((Integer)p).intValue();
            Service.gI().addPartyAccept(idAction);
            return;
          case 8888:
             idAction = ((Integer)p).intValue();
            Service.gI().addPartyCancel(idAction);
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 8889:
            String str = (String)p;
            currentDialog = null;
            InfoDlg.hide();
            Service.gI().acceptPleaseParty(str);
            return;
         case 9000:
            currentDialog = null;
            InfoDlg.hide();
            SplashScr.imgLogo = null;
            SmallImage.loadBigRMS();
            System.gc();
            ServerListScreen.bigOk = true;
            ServerListScreen.loadScreen = true;
            GameScr.gI().loadSplash();
            if (currentScreen != loginScr) {
               serverScreen.switchToMe2();
               return;
            }
            break;
         case 9999:
            currentDialog = null;
            InfoDlg.hide();
            connect();
            loginScr.doLogin();
            return;
         case 10000:
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 10001:
            currentDialog = null;
            InfoDlg.hide();
            return;
          case 88810:
             idAction = ((Integer)p).intValue();
            currentDialog = null;
            InfoDlg.hide();
            Service.gI().acceptInviteTrade(idAction);
            return;
         case 88811:
            currentDialog = null;
            InfoDlg.hide();
            Service.gI().cancelInviteTrade();
            return;
         case 88814:
            Item[] items = (Item[])p;
            currentDialog = null;
            InfoDlg.hide();
            Service.gI().crystalCollectLock(items);
            return;
         case 88815:
            return;
         case 88817:
            ChatPopup.addChatPopup("", 1, Char.myCharz().npcFocus);
            Service.gI()
               .menu(Char.myCharz().npcFocus.template.npcTemplateId, menu.menuSelectedItem, 0);
            return;
         case 88818:
            short menuId = ((Short)p).shortValue();
            Service.gI().textBoxId(menuId, ((InputDlg)null).tfInput.getText());
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 88819:
            short menuId2 = ((Short)p).shortValue();
            Service.gI().menuId(menuId2);
            return;
         case 88820:
            String[] p3 = (String[])p;
            if (Char.myCharz().npcFocus == null) {
               return;
            }

            p = new Integer(menu.menuSelectedItem);
            if (p3.length <= 1) {
               ChatPopup.addChatPopup("", 1, Char.myCharz().npcFocus);
                Service.gI().menu(Char.myCharz().npcFocus.template.npcTemplateId, ((Integer)p).intValue(), 0);
               return;
            }

            MyVector menuItems = new MyVector("vSub");

            for (int entryIndex = 0; entryIndex < p3.length - 1; entryIndex++) {
               menuItems.addElement(new Command(p3[entryIndex + 1], instance, 88821, p));
            }

            menu.startAt(menuItems);
            return;
          case 88821:
             int menuId3 = ((Integer)p).intValue();
            ChatPopup.addChatPopup("", 1, Char.myCharz().npcFocus);
            Service.gI()
               .menu(Char.myCharz().npcFocus.template.npcTemplateId, menuId3, menu.menuSelectedItem);
            return;
         case 88822:
            ChatPopup.addChatPopup("", 1, Char.myCharz().npcFocus);
            Service.gI()
               .menu(Char.myCharz().npcFocus.template.npcTemplateId, menu.menuSelectedItem, 0);
            return;
         case 88823:
            startOKDlg(mResources.SENTMSG);
            return;
         case 88824:
            startOKDlg(mResources.NOSENDMSG);
            return;
         case 88825:
            showActionWaitDialog(mResources.sendMsgSuccess);
            return;
         case 88826:
            showActionWaitDialog(mResources.cannotSendMsg);
            return;
         case 88827:
            startOKDlg(mResources.sendGuessMsgSuccess);
            return;
         case 88828:
            startOKDlg(mResources.sendMsgFail);
            return;
         case 88829:
            String name2;
            if ((name2 = ((InputDlg)null).tfInput.getText()).equals("")) {
               return;
            }

             Service.gI().changeName(name2, ((Integer)p).intValue());
            InfoDlg.showWait();
            return;
         case 88836:
            ((InputDlg)null).tfInput.setMaxTextLenght(6);
            ((nro.InputDlg) null).show(mResources.INPUT_PRIVATE_PASS, new Command(mResources.ACCEPT, instance, 888361, null), 1);
            return;
         case 88837:
            String text = ((InputDlg)null).tfInput.getText();
            currentDialog = null;
            InfoDlg.hide();

            try {
               Service.gI().openLockAccProtect(Integer.parseInt(text.trim()));
               return;
            } catch (Exception ignoredException) {
               return;
            }
         case 88839:
            String y = ((InputDlg)null).tfInput.getText();
            currentDialog = null;
            InfoDlg.hide();
            if (y.length() >= 6 && !y.equals("")) {
               try {
                  Object entry = null;
                  int value = 8882;
                  value = 888391;
                  String info = mResources.cancelAccountProtection;
                  msgdlg.setInfo(
                     info,
                     new Command(mResources.YES, instance, 888391, y),
                     new Command("", instance, 888391, y),
                     new Command(mResources.NO, instance, 8882, null)
                  );
                  msgdlg.show();
                  return;
               } catch (Exception ignoredException2) {
                  startOKDlg(mResources.ALERT_PRIVATE_PASS_2);
                  return;
               }
            }

            startOKDlg(mResources.ALERT_PRIVATE_PASS_1);
            return;
         case 100001:
            Service.gI().getFlag((byte)0, (byte)-1);
            InfoDlg.showWait();
            return;
         case 100002:
            if (loginScr == null) {
               loginScr = new LoginScr();
            }

            LoginScr.backToRegister();
            return;
         case 100003:
            return;
         case 100004:
            return;
         case 100005:
            if (Char.myCharz().statusMe == 14) {
               startOKDlg(mResources.can_not_do_when_die);
               return;
            }

            Service.gI().openUIZone();
            return;
         case 100006:
            mSystem.onDisconnected(true);
            return;
         case 100016:
            ServerListScreen.SetIpSelect(17, false);
            doResetToLoginScr();
            ServerListScreen.waitToLogin = true;
            currentDialog = null;
            InfoDlg.hide();
            break;
         case 101024:
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 101025:
            currentDialog = null;
            InfoDlg.hide();
            if (ServerListScreen.loadScreen) {
               serverScreen.switchToMe();
               return;
            }

            serverScreen.show2();
            return;
         case 101026:
            mSystem.onDisconnected(true);
            return;
         case 888361:
            String text2 = ((InputDlg)null).tfInput.getText();
            currentDialog = null;
            InfoDlg.hide();
            if (text2.length() >= 6 && !text2.equals("")) {
               try {
                  Service.gI().activeAccProtect(Integer.parseInt(text2));
                  return;
               } catch (Exception ignoredException3) {
                  startOKDlg(mResources.ALERT_PRIVATE_PASS_2);
                  return;
               }
            }

            startOKDlg(mResources.ALERT_PRIVATE_PASS_1);
            return;
         case 888391:
            String p4 = (String)p;
            currentDialog = null;
            InfoDlg.hide();
            Service.gI().clearAccProtect(Integer.parseInt(p4));
            return;
         case 888392:
            Service.gI().menu(4, menu.menuSelectedItem, 0);
            return;
         case 888393:
            loginScr.doLogin();
            return;
         case 888394:
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 888395:
            if (!currentScreen.equals(SplashScr.instance) && !currentScreen.equals(serverScreen)) {
               loginScr.switchToMe();
            } else {
               serverScreen.switchToMe();
            }

            currentDialog = null;
            InfoDlg.hide();
            return;
         case 888396:
            currentDialog = null;
            InfoDlg.hide();
            return;
         case 888397:
            currentDialog = null;
            InfoDlg.hide();
            return;
      }
   }

   public static void clearAllPointerEvent() {
      isPointerClick = false;
      isPointerDown = false;
      isPointerJustDown = false;
      isPointerJustRelease = false;
      isPointerSelect = false;
      GameScr.gI().lastSingleClick = 0L;
      GameScr.gI().isPointerDowning = false;
   }
}
