package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class ChatPopup extends Effect2 implements IActionListener {
   public int sayWidth = 100;
   public int delay;
   public int sayRun;
   public String[] says;
   public int cx;
   public int cy;
   public int ch;
   public int lim;
   public Npc c;
   private static long curr;
   private static long last;
   private int currentLine;
   private String[] lines;
   public Command cmdNextLine;
   public Command cmdMsg1;
   public Command cmdMsg2;
   public static ChatPopup currChatPopup;
   public static ChatPopup serverChatPopUp;
   private static String nextMultiChatPopUp;
   private static Npc nextChar;
   public byte starSlot = 0;
   public byte maxStarSlot = 0;
   public static Scroll scr;
   private static boolean isHavePetNpc;
   public int mH;
   private int iconID;
   private static int performDelay;
   private int second;
   public boolean[] starCuongHoa = new boolean[20];
   public static int numSlot = 7;
   private int nMaxslot_duoi = 0;
   private int nMaxslot_tren = 0;
   private int nslot_duoi = 0;
   private Image imgStar;
   public int strY;
   public boolean isClip = false;
   public static int cmyText;
   private int pxx = 0;
   private int pyy = 0;

   public static void clearAllChatPopups() {
      currChatPopup = null;
      serverChatPopUp = null;
      Char.chatPopup = null;
      Char.isLockKey = false;
      nextMultiChatPopUp = null;
      nextChar = null;
      scr = null;
      isHavePetNpc = false;
      if (GameScr.info1 != null) {
         GameScr.info1.isUpdate = true;
      }

      removeChatPopupsFrom(Effect2.vEffect2);
      removeChatPopupsFrom(Effect2.vEffect2Outside);
   }

   private static void removeChatPopupsFrom(MyVector entries) {
      if (entries != null) {
         for (int index = entries.size() - 1; index >= 0; index--) {
            if (entries.elementAt(index) instanceof ChatPopup) {
               entries.removeElementAt(index);
            }
         }
      }
   }

   public static void addNextPopUpMultiLine(String strNext, Npc next) {
      nextMultiChatPopUp = strNext;
      nextChar = next;
      if (currChatPopup == null) {
         addChatPopupMultiLine(nextMultiChatPopUp, 100000, nextChar);
         nextMultiChatPopUp = null;
         nextChar = null;
      }
   }

   public static void addBigMessage(String chat, int howLong, Npc c) {
      String[] linesValue = new String[]{chat};
      if (c.charID != 5 && GameScr.info1.isDone) {
         GameScr.info1.isUpdate = false;
      }

      Char.isLockKey = true;
      (serverChatPopUp = addChatPopup(linesValue[0], 100000, c)).strY = 5;
      serverChatPopUp.cx = GameCanvas.w / 2 - serverChatPopUp.sayWidth / 2 - 1;
      serverChatPopUp.cy = GameCanvas.h - 20 - serverChatPopUp.ch;
      serverChatPopUp.currentLine = 0;
      serverChatPopUp.lines = linesValue;
      scr = new Scroll();
      int nItem = serverChatPopUp.says.length;
      scr.setStyle(
         nItem,
         12,
         serverChatPopUp.cx,
         serverChatPopUp.cy - serverChatPopUp.strY + 12,
         serverChatPopUp.sayWidth + 2,
         serverChatPopUp.ch - 25,
         true,
         1
      );
      SoundMn.gI();
      SoundMn.openDialog();
   }

   public static void addChatPopupMultiLine(String chat, int howLong, Npc c) {
      Res.outz("chat= " + chat);
      String[] linesValue = Res.split(chat, "\n", 0);
      Char.isLockKey = true;
      (currChatPopup = addChatPopup(linesValue[0], 100000, c)).currentLine = 0;
      currChatPopup.lines = linesValue;
      String caption = mResources.CONTINUE;
      if (linesValue.length == 1) {
         caption = mResources.CLOSE;
      }

      currChatPopup.cmdNextLine = new Command(caption, currChatPopup, 8000, null);
      currChatPopup.cmdNextLine.x = GameCanvas.w / 2 - 35;
      currChatPopup.cmdNextLine.y = GameCanvas.h - 35;
      SoundMn.gI();
      SoundMn.openDialog();
   }

   public static ChatPopup addChatPopup(String chat, int howLong, Npc c) {
      Res.outz("ADD POP");
      Res.outz(Char.myCharz().npcFocus != null ? "!null" : "null");
      performDelay = 10;
      ChatPopup chatPopup;
      (chatPopup = new ChatPopup()).sayWidth = GameCanvas.w
         - 30
         - (GameCanvas.menu.showMenu ? GameCanvas.menu.menuX : 0);
      if (chatPopup.sayWidth > 320) {
         chatPopup.sayWidth = 320;
      }

      if (chat.length() < 10) {
         chatPopup.sayWidth = 64;
      }

      if (GameCanvas.w == 128) {
         chatPopup.sayWidth = 128;
      }

      chatPopup.says = mFont.tahoma_7_red.splitFontArray(chat, chatPopup.sayWidth - 10);
      chatPopup.delay = howLong;
      chatPopup.c = c;
      Char.chatPopup = chatPopup;
      chatPopup.ch = 15 - chatPopup.sayRun + chatPopup.says.length * 12 + 10;
      if (chatPopup.ch > GameCanvas.h - 80) {
         chatPopup.ch = GameCanvas.h - 80;
      }

      Effect2.vEffect2.addElement(chatPopup);
      isHavePetNpc = false;
      if (c != null && c.charID == 5) {
         isHavePetNpc = true;
         GameScr.info1.addInfo("", 1);
      }

      curr = last = mSystem.currentTimeMillis();
      return chatPopup;
   }

   public static ChatPopup addChatPopupWithIcon(String chat, int howLong, Npc c, int idIcon) {
      Res.outz("ADD POP");
      Res.outz(Char.myCharz().npcFocus != null ? "!null" : "null");
      performDelay = 10;
      ChatPopup chatPopup;
      (chatPopup = new ChatPopup()).sayWidth = GameCanvas.w
         - 30
         - (GameCanvas.menu.showMenu ? GameCanvas.menu.menuX : 0);
      if (chatPopup.sayWidth > 320) {
         chatPopup.sayWidth = 320;
      }

      if (chat.length() < 10) {
         chatPopup.sayWidth = 64;
      }

      if (GameCanvas.w == 128) {
         chatPopup.sayWidth = 128;
      }

      chatPopup.says = mFont.tahoma_7_red.splitFontArray(chat, chatPopup.sayWidth - 10);
      chatPopup.delay = 100000;
      chatPopup.c = c;
      chatPopup.iconID = 5820;
      Char.chatPopup = chatPopup;
      chatPopup.ch = 15 - chatPopup.sayRun + chatPopup.says.length * 12 + 10;
      if (chatPopup.ch > GameCanvas.h - 80) {
         chatPopup.ch = GameCanvas.h - 80;
      }

      Effect2.vEffect2.addElement(chatPopup);
      isHavePetNpc = false;
      if (c != null && c.charID == 5) {
         isHavePetNpc = true;
         GameScr.info1.addInfo("", 1);
      }

      chatPopup.ch += 15;
      curr = last = mSystem.currentTimeMillis();
      return chatPopup;
   }

   public final void update() {
      if (scr != null) {
         GameScr.info1.isUpdate = false;
         scr.updatecm();
      } else {
         GameScr.info1.isUpdate = true;
      }

      if (GameCanvas.menu.showMenu) {
         this.strY = 0;
         this.cx = GameCanvas.w / 2 - this.sayWidth / 2 - 1;
         this.cy = GameCanvas.menu.menuY - this.ch;
      } else {
         this.strY = 0;
         if (GameScr.gI().right == null
            && GameScr.gI().left == null
            && GameScr.gI().center == null
            && this.cmdNextLine == null
            && this.cmdMsg1 == null) {
            this.cx = GameCanvas.w / 2 - this.sayWidth / 2 - 1;
            this.cy = GameCanvas.h - 5 - this.ch;
         } else {
            this.strY = 5;
            this.cx = GameCanvas.w / 2 - this.sayWidth / 2 - 1;
            this.cy = GameCanvas.h - 20 - this.ch;
         }
      }

      if (this.delay > 0) {
         this.delay--;
      }

      if (performDelay > 0) {
         performDelay--;
      } else {
         GameScr.info1.info.time = 0;

         for (int infoWaitToShowIndex = 0; infoWaitToShowIndex < GameScr.info1.info.infoWaitToShow.size(); infoWaitToShowIndex++) {
            if (((InfoItem)GameScr.info1.info.infoWaitToShow.elementAt(infoWaitToShowIndex)).speed != 70) {
               ((InfoItem)GameScr.info1.info.infoWaitToShow.elementAt(infoWaitToShowIndex)).speed = 10;
            }
         }
      }

      if (this.sayRun > 1) {
         this.sayRun--;
      }

      if (this.c != null && Char.chatPopup != null && Char.chatPopup != this
         || this.c != null && Char.chatPopup == null
         || this.delay <= 0) {
         Effect2.vEffect2Outside.removeElement(this);
         Effect2.vEffect2.removeElement(this);
      }
   }

   public final void paint(mGraphics g) {
      if (!GameScr.gI().activeRongThan || !GameScr.gI().isUseFreez) {
         GameCanvas.resetTrans(g);
         int x = this.cx;
         int y = this.cy;
         int width = this.sayWidth + 2;
         int h = this.ch;
         if (x > 0 && y > 0 || GameCanvas.panel.isShow) {
            byte byteValue = 0;
            if (this.c != null) {
               if (GameCanvas.gameTick % 10 > 2) {
                  byteValue = 0;
               } else {
                  byteValue = 1;
               }

               SmallImage.drawSmallImage(
                  g, this.c.avatar, this.cx + 14, this.cy + byteValue, 0, StaticObj.BOTTOM_LEFT
               );
            }

            if (this.iconID != 0) {
               if (GameCanvas.gameTick % 10 > 2) {
                  byteValue = 0;
               } else {
                  byteValue = 1;
               }

               SmallImage.drawSmallImage(
                  g,
                  this.iconID,
                  this.cx + width / 2,
                  this.cy + this.ch - 15 + byteValue,
                  0,
                  StaticObj.VCENTER_HCENTER
               );
            }

            PopUp.paintPopUp(g, x, y, width, h, 16777215, false);
            if (scr != null) {
               g.setClip(x, y, width, h - 16);
               g.translate(0, -scr.cmy);
            }

            int deltaX = 0;
            int deltaY = 0;
            if (this.isClip) {
               deltaX = g.getTranslateX();
               deltaY = g.getTranslateY();
               g.setClip(x, y + 1, width, h - 17);
               g.translate(0, -cmyText);
            }

            int value = -1;

            for (int sayIndex = 0; sayIndex < this.says.length; sayIndex++) {
               if (this.says[sayIndex].startsWith("--")) {
                  g.setColor(0);
                  g.fillRect(x + 10, this.cy + this.sayRun + sayIndex * 12 + 6, width - 20, 1);
               } else {
                  mFont tahoma_7 = mFont.tahoma_7;
                  int align = 2;
                  String text = this.says[sayIndex];
                  int subcommand;
                  if (this.says[sayIndex].startsWith("|")) {
                     String[] parts;
                     if ((parts = Res.split(this.says[sayIndex], "|", 0)).length == 3) {
                        text = parts[2];
                     }

                     if (parts.length == 4) {
                        text = parts[3];
                        align = Integer.parseInt(parts[2]);
                     }

                     value = subcommand = Integer.parseInt(parts[1]);
                  } else {
                     subcommand = value;
                  }

                  switch (subcommand) {
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
                        break;
                     case 9:
                        tahoma_7 = mFont.tahoma_7b_yellow;
                  }

                  if (this.says[sayIndex].startsWith("<")) {
                     String[] parts2 = Res.split(Res.split(this.says[sayIndex], "<", 0)[1], ">", 1);
                     if (this.second == 0) {
                        this.second = Integer.parseInt(parts2[1]);
                     } else if ((curr = mSystem.currentTimeMillis()) - last >= 1000L) {
                        last = curr;
                        this.second--;
                     }

                     text = this.second + " " + parts2[2];
                     tahoma_7.drawString(
                        g,
                        text,
                        this.cx + this.sayWidth / 2,
                        this.cy + this.sayRun + sayIndex * 12 - this.strY + 12,
                        align
                     );
                  } else {
                     if (align == 2) {
                        tahoma_7.drawString(
                           g,
                           text,
                           this.cx + this.sayWidth / 2,
                           this.cy + this.sayRun + sayIndex * 12 - this.strY + 12,
                           align
                        );
                     }

                     if (align == 1) {
                        tahoma_7.drawString(
                           g,
                           text,
                           this.cx + this.sayWidth - 5,
                           this.cy + this.sayRun + sayIndex * 12 - this.strY + 12,
                           align
                        );
                     }
                  }
               }
            }

            if (this.isClip) {
               GameCanvas.resetTrans(g);
               g.translate(deltaX, deltaY);
            }

            if (this.maxStarSlot > 4) {
               this.nMaxslot_tren = (this.maxStarSlot + 1) / 2;
               this.nMaxslot_duoi = this.maxStarSlot - this.nMaxslot_tren;
               int[] intValues = new int[this.maxStarSlot];
               int[] intValues2 = new int[this.maxStarSlot];

               for (int index = 0; index < this.nMaxslot_tren; index++) {
                  g.drawImage(
                     Panel.imgMaxStar,
                     ((int) (x + width / 2 - this.nMaxslot_tren * 20 / 2 + index * 20 + mGraphics.getImageWidth(Panel.imgMaxStar))),
                     ((int) (y + h - 17)),
                     3
                  );
                  intValues[index] = x + width / 2 - this.nMaxslot_tren * 20 / 2 + index * 20 + mGraphics.getImageWidth(Panel.imgMaxStar);
                  intValues2[index] = y + h - 17;
               }

               for (int index2 = 0; index2 < this.nMaxslot_duoi; index2++) {
                  g.drawImage(
                     Panel.imgMaxStar,
                     ((int) (x + width / 2 - this.nMaxslot_duoi * 20 / 2 + index2 * 20 + mGraphics.getImageWidth(Panel.imgMaxStar))),
                     ((int) (y + h - 8)),
                     3
                  );
                  intValues[this.nMaxslot_tren + index2] = x
                     + width / 2
                     - this.nMaxslot_duoi * 20 / 2
                     + index2 * 20
                     + mGraphics.getImageWidth(Panel.imgMaxStar);
                  intValues2[this.nMaxslot_tren + index2] = y + h - 8;
               }

               Res.err(this.maxStarSlot + "maxStarSlot");
               if (this.maxStarSlot >= 7) {
                  for (int index3 = 7; index3 < this.maxStarSlot; index3++) {
                     if (this.starCuongHoa[index3]) {
                        g.drawImage(Panel.imgStarCuongHoa, ((int) (intValues[index3])), ((int) (intValues2[index3])), 3);
                     }
                  }
               }

               if (this.starSlot > 0) {
                  this.imgStar = Panel.imgStar;
                  if (this.starSlot >= this.nMaxslot_tren) {
                     this.nslot_duoi = this.starSlot - this.nMaxslot_tren;

                     for (int index4 = 0; index4 < this.nMaxslot_tren; index4++) {
                        g.drawImage(
                           this.imgStar,
                           ((int) (x + width / 2 - this.nMaxslot_tren * 20 / 2 + index4 * 20 + mGraphics.getImageWidth(this.imgStar))),
                           ((int) (y + h - 17)),
                           3
                        );
                     }

                     for (int index5 = 0; index5 < this.nslot_duoi; index5++) {
                        if (index5 + this.nMaxslot_tren >= numSlot) {
                           this.imgStar = Panel.imgStar8;
                        }

                        g.drawImage(
                           this.imgStar,
                           ((int) (x + width / 2 - this.nMaxslot_duoi * 20 / 2 + index5 * 20 + mGraphics.getImageWidth(this.imgStar))),
                           ((int) (y + h - 8)),
                           3
                        );
                     }
                  } else {
                     for (int index6 = 0; index6 < this.starSlot; index6++) {
                        g.drawImage(
                           this.imgStar,
                           ((int) (x + width / 2 - this.nMaxslot_tren * 20 / 2 + index6 * 20 + mGraphics.getImageWidth(this.imgStar))),
                           ((int) (y + h - 17)),
                           3
                        );
                     }
                  }
               }
            } else {
               for (int index7 = 0; index7 < this.maxStarSlot; index7++) {
                  g.drawImage(
                     Panel.imgMaxStar,
                     ((int) (x + width / 2 - this.maxStarSlot * 20 / 2 + index7 * 20 + mGraphics.getImageWidth(Panel.imgMaxStar))),
                     ((int) (y + h - 13)),
                     3
                  );
               }

               if (this.starSlot > 0) {
                  for (int index8 = 0; index8 < this.starSlot; index8++) {
                     g.drawImage(
                        Panel.imgStar,
                        ((int) (x + width / 2 - this.maxStarSlot * 20 / 2 + index8 * 20 + mGraphics.getImageWidth(Panel.imgStar))),
                        ((int) (y + h - 13)),
                        3
                     );
                  }
               }
            }

            mGraphics g2 = g;
            ChatPopup chatPopup = this;
            g2.translate(-g2.getTranslateX(), -g2.getTranslateY());
            g2.setClip(0, 0, GameCanvas.w, GameCanvas.h);
            Paint.paintTabSoft();
            if (chatPopup.cmdNextLine != null) {
               Paint.paintCmdBar(g2, null, chatPopup.cmdNextLine, null);
            }

            if (chatPopup.cmdMsg1 != null) {
               Paint.paintCmdBar(g2, chatPopup.cmdMsg1, null, chatPopup.cmdMsg2);
            }
         }
      }
   }

   public final void paintRada(mGraphics g, int cmyText) {
      int cxValue = this.cx;
      int cyValue = this.cy;
      int sayWidthValue = this.sayWidth;
      int deltaX = g.getTranslateX();
      int deltaY = g.getTranslateY();
      g.translate(0, -cmyText);
      if (cxValue > 0 && cyValue > 0 || GameCanvas.panel.isShow) {
         int value = -1;

         for (int sayIndex = 0; sayIndex < this.says.length; sayIndex++) {
            if (this.says[sayIndex].startsWith("--")) {
               g.setColor(16777215);
               g.fillRect(cxValue + 10, this.cy + this.sayRun + sayIndex * 12 - 6, sayWidthValue - 20, 1);
            } else {
               mFont tahoma_7_white = mFont.tahoma_7_white;
               int align = 2;
               String text = this.says[sayIndex];
               int subcommand;
               if (this.says[sayIndex].startsWith("|")) {
                  String[] parts;
                  if ((parts = Res.split(this.says[sayIndex], "|", 0)).length == 3) {
                     text = parts[2];
                  }

                  if (parts.length == 4) {
                     text = parts[3];
                     align = Integer.parseInt(parts[2]);
                  }

                  value = subcommand = Integer.parseInt(parts[1]);
               } else {
                  subcommand = value;
               }

               switch (subcommand) {
                  case -1:
                     tahoma_7_white = mFont.tahoma_7_white;
                     break;
                  case 0:
                     tahoma_7_white = mFont.tahoma_7b_white;
                     break;
                  case 1:
                     tahoma_7_white = mFont.tahoma_7b_green;
                     break;
                  case 2:
                     tahoma_7_white = mFont.tahoma_7b_red;
               }

               if (this.says[sayIndex].startsWith("<")) {
                  String[] parts2 = Res.split(Res.split(this.says[sayIndex], "<", 0)[1], ">", 1);
                  if (this.second == 0) {
                     this.second = Integer.parseInt(parts2[1]);
                  } else if ((curr = mSystem.currentTimeMillis()) - last >= 1000L) {
                     last = curr;
                     this.second--;
                  }

                  text = this.second + " " + parts2[2];
                  tahoma_7_white.drawString(
                     g,
                     text,
                     this.cx + this.sayWidth / 2,
                     this.cy + this.sayRun + sayIndex * 12 - this.strY,
                     align
                  );
               } else {
                  if (align == 2) {
                     tahoma_7_white.drawString(
                        g,
                        text,
                        this.cx + this.sayWidth / 2,
                        this.cy + this.sayRun + sayIndex * 12 - this.strY,
                        align
                     );
                  }

                  if (align == 1) {
                     tahoma_7_white.drawString(
                        g,
                        text,
                        this.cx + this.sayWidth - 5,
                        this.cy + this.sayRun + sayIndex * 12 - this.strY,
                        align
                     );
                  }
               }
            }
         }

         GameCanvas.resetTrans(g);
         g.translate(deltaX, deltaY);
      }
   }

   private void doKeyText(int type) {
      if ((cmyText += type * 12) < 0) {
         cmyText = 0;
      }

      if (cmyText > this.lim) {
         cmyText = this.lim;
      }
   }

   public final void updateKey() {
      if (this.isClip) {
         if (GameCanvas.keyPressed[8]) {
            GameCanvas.keyPressed[8] = false;
            this.doKeyText(1);
         }

         if (GameCanvas.keyPressed[2]) {
            GameCanvas.keyPressed[2] = false;
            this.doKeyText(-1);
         }

         if (GameCanvas.isPointerHoldIn(this.cx, 0, this.sayWidth + 2, this.ch)) {
            if (GameCanvas.isPointerMove) {
               if (this.pyy == 0) {
                  this.pyy = GameCanvas.py;
               }

               this.pxx = this.pyy - GameCanvas.py;
               if (this.pxx != 0) {
                  cmyText = cmyText + this.pxx;
                  this.pyy = GameCanvas.py;
               }

               if (cmyText < 0) {
                  cmyText = 0;
               }

               if (cmyText > this.lim) {
                  cmyText = this.lim;
               }
            } else {
               this.pyy = 0;
               this.pyy = 0;
            }
         }
      }

      if (scr != null) {
         if (GameCanvas.isTouch) {
            scr.updateKey();
         }

         if (GameCanvas.keyHold[2]) {
            scr.cmtoY -= 12;
            if (scr.cmtoY < 0) {
               scr.cmtoY = 0;
            }
         }

         if (GameCanvas.keyHold[8]) {
            GameCanvas.keyPressed[8] = false;
            scr.cmtoY += 12;
            if (scr.cmtoY > scr.cmyLim) {
               scr.cmtoY = scr.cmyLim;
            }
         }
      }

      if (GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(GameCanvas.currentScreen.center)) {
         GameCanvas.keyPressed[5] = false;
         mScreen.keyTouch = -1;
         if (this.cmdNextLine != null) {
            this.cmdNextLine.performAction();
         } else if (this.cmdMsg1 != null) {
            this.cmdMsg1.performAction();
         } else if (this.cmdMsg2 != null) {
            this.cmdMsg2.performAction();
         }
      }

      if (scr == null || !scr.pointerIsDowning) {
         if (this.cmdMsg1 != null
            && (GameCanvas.keyPressed[12] || GameCanvas.keyPressed[5] || mScreen.getCmdPointerLast(this.cmdMsg1))) {
            GameCanvas.keyPressed[12] = false;
            GameCanvas.keyPressed[5] = false;
            GameCanvas.isPointerClick = false;
            GameCanvas.isPointerJustRelease = false;
            this.cmdMsg1.performAction();
            mScreen.keyTouch = -1;
         }

         if (this.cmdMsg2 != null && (GameCanvas.keyPressed[13] || mScreen.getCmdPointerLast(this.cmdMsg2))) {
            GameCanvas.keyPressed[13] = false;
            GameCanvas.isPointerClick = false;
            GameCanvas.isPointerJustRelease = false;
            this.cmdMsg2.performAction();
            mScreen.keyTouch = -1;
         }
      }
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 1000) {
         try {
            GameMidlet.instance.platformRequest((String)p);
         } catch (Exception exception) {
            exception.printStackTrace();
         }

         GameMidlet.instance.notifyDestroyed();
         GameCanvas.endDlg();
      }

      if (idAction == 1001) {
         scr = null;
         Char.chatPopup = null;
         serverChatPopUp = null;
         GameScr.info1.isUpdate = true;
         Char.isLockKey = false;
         if (isHavePetNpc) {
            GameScr.info1.info.time = 0;
            GameScr.info1.info.info.speed = 10;
         }
      }

      if (idAction == 8000) {
         if (performDelay > 0) {
            return;
         }

         int currentLineValue = currChatPopup.currentLine;
         if (++currentLineValue >= currChatPopup.lines.length) {
            Char.chatPopup = null;
            currChatPopup = null;
            GameScr.info1.isUpdate = true;
            Char.isLockKey = false;
            if (nextMultiChatPopUp != null) {
               addChatPopupMultiLine(nextMultiChatPopUp, 100000, nextChar);
               nextMultiChatPopUp = null;
               nextChar = null;
               return;
            }

            if (isHavePetNpc) {
               GameScr.info1.info.time = 0;

               for (int infoWaitToShowIndex = 0; infoWaitToShowIndex < GameScr.info1.info.infoWaitToShow.size(); infoWaitToShowIndex++) {
                  if (((InfoItem)GameScr.info1.info.infoWaitToShow.elementAt(infoWaitToShowIndex)).speed == 10000000) {
                     ((InfoItem)GameScr.info1.info.infoWaitToShow.elementAt(infoWaitToShowIndex)).speed = 10;
                  }
               }
            }

            return;
         }

         ChatPopup currChatPopupValue;
         (currChatPopupValue = addChatPopup(currChatPopup.lines[currentLineValue], currChatPopup.delay, currChatPopup.c)).currentLine = currentLineValue;
         currChatPopupValue.lines = currChatPopup.lines;
         currChatPopupValue.cmdNextLine = currChatPopup.cmdNextLine;
         currChatPopup = currChatPopupValue;
      }
   }
}
