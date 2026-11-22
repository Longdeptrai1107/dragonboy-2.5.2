package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Info implements IActionListener {
   public MyVector infoWaitToShow = new MyVector("infoWaitToShow");
   public InfoItem info;
   public int sayWidth = 100;
   private int sayRun;
   public String[] says;
   private int cx;
   private int cy;
   private boolean outSide = false;
   private static Image gocnhon = mSystem.loadImage("/mainImage/myTexture2dgocnhon.png");
   public int time;
   public int timeW;
   private int type;
   public int X;
   public int Y;
   public int W;
   public int H;

   public final void hide() {
      this.says = null;
      this.infoWaitToShow.removeAllElements();
   }

   public final void paint(mGraphics g, int x, int y, int dir) {
      if (this.infoWaitToShow.size() != 0) {
         g.translate(x, y);
         if (this.says != null && this.says.length != 0 && this.type != 1) {
            int value = mGraphics.zoomLevel == 1 ? 0 : 10;
            if (this.info.charInfo == null) {
               PopUp.paintPopUp(g, this.X, this.Y, this.W, this.H, 16777215, false);
            } else {
               mSystem.paintTiledInfoBackground(g, this.X - 23, this.Y - value / 2, this.W + 15);
            }

            if (this.info.charInfo == null) {
               g.drawRegion(
                  gocnhon,
                  0,
                  0,
                  9,
                  8,
                  dir == 1 ? 0 : 2,
                  this.cx - 3 + (dir == 1 ? -15 : 20),
                  this.cy - 20 + this.sayRun + 2,
                  17
               );
            }

            int value2 = -1;

            for (int sayIndex = 0; sayIndex < this.says.length; sayIndex++) {
               mFont tahoma_7 = mFont.tahoma_7;
               String text = this.says[sayIndex];
               int x2;
               if (this.says[sayIndex].startsWith("|")) {
                  String[] parts;
                  if ((parts = Res.split(this.says[sayIndex], "|", 0)).length == 3) {
                     text = parts[2];
                  }

                  if (parts.length == 4) {
                     text = parts[3];
                     Integer.parseInt(parts[2]);
                  }

                  value2 = x2 = Integer.parseInt(parts[1]);
               } else {
                  x2 = value2;
               }

               switch (x2) {
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
               }

               if (this.info.charInfo == null) {
                  tahoma_7.drawString(
                     g,
                     text,
                     this.cx,
                     this.cy - 15 + this.sayRun + sayIndex * 12 - this.says.length * 12 - 9,
                     2
                  );
               } else {
                  x2 = this.X - 23;
                  int deltaY = this.Y - value / 2;
                  int width = mSystem.clientType == 1 ? this.W + 28 : this.W + 25;
                  int value3 = this.H + (GameCanvas.isTouch ? 0 : 14) + value;
                  g.setColor(4465169);
                  g.fillRect(x2, deltaY + value3, width, 2);
                  if ((width = this.info.timeCount * width / this.info.maxTime) < 0) {
                     width = 0;
                  }

                  g.setColor(43758);
                  g.fillRect(x2, deltaY + value3, width, 2);
                  if (this.info.timeCount == 0) {
                     return;
                  }

                  this.info
                     .charInfo
                     .paintHead(g, this.X + 5, this.Y + this.H / 2, 0);
                  if (mGraphics.zoomLevel == 1) {
                     (this.info.isChatServer ? mFont.tahoma_7b_yellowSmall2 : mFont.tahoma_7b_greenSmall)
                        .drawString(
                           g, this.info.charInfo.cName, this.X + 12, this.Y + 3, 0
                        );
                  } else {
                     (this.info.isChatServer ? mFont.tahoma_7b_yellowSmall2 : mFont.tahoma_7b_greenSmall)
                        .drawString(
                           g, this.info.charInfo.cName, this.X + 12, this.Y - 3, 0
                        );
                  }

                  if (!GameCanvas.isTouch) {
                     if (!TField.isQwerty) {
                        mFont.tahoma_7b_green2Small
                           .drawString(
                              g, "Nhấn # để chat", this.X + this.W / 2 + 10, this.Y + this.H, 2
                           );
                     } else {
                        mFont.tahoma_7b_green2Small
                           .drawString(
                              g, "Nhấn Y để chat", this.X + this.W / 2 + 10, this.Y + this.H, 2
                           );
                     }
                  }

                  if (mGraphics.zoomLevel == 1) {
                     TextInfo.paint(
                        g,
                        text,
                        this.X + 14,
                        this.Y + this.H / 2 + 2,
                        this.W - 16,
                        this.H,
                        mFont.tahoma_7_whiteSmall
                     );
                  } else {
                     String[] texts = mFont.tahoma_7_whiteSmall.splitFontArray(text, 120);

                     for (int textIndex = 0; textIndex < texts.length; textIndex++) {
                        mFont.tahoma_7_whiteSmall
                           .drawString(g, texts[textIndex], this.X + 12, this.Y + 12 + textIndex * 12 - 3, 0);
                     }

                     GameCanvas.resetTrans(g);
                  }
               }
            }
         }

         g.translate(-x, -y);
      }
   }

   public final void update() {
      if (this.infoWaitToShow.size() != 0 && this.info.timeCount == 0) {
         this.time++;
         if (this.time >= this.info.speed) {
            this.time = 0;
            this.infoWaitToShow.removeElementAt(0);
            if (this.infoWaitToShow.size() == 0) {
               return;
            }

            InfoItem infoValue = (InfoItem)this.infoWaitToShow.firstElement();
            this.info = infoValue;
            this.getInfo();
         }
      }
   }

   public final void getInfo() {
      this.sayWidth = 100;
      if (GameCanvas.w == 128) {
         this.sayWidth = 128;
      }

      int sayCount;
      if (this.info.charInfo != null) {
         this.says = new String[]{this.info.s};
         if (mGraphics.zoomLevel == 1) {
            sayCount = this.says.length;
         } else {
            sayCount = mFont.tahoma_7_whiteSmall.splitFontArray(this.info.s, 120).length;
         }
      } else {
         this.says = mFont.tahoma_7.splitFontArray(this.info.s, this.sayWidth - 10);
         sayCount = this.says.length;
      }

      this.sayRun = 7;
      this.X = this.cx - this.sayWidth / 2 - 1;
      this.Y = this.cy - 15 + this.sayRun - sayCount * 12 - 15;
      this.W = this.sayWidth + 2 + (this.info.charInfo != null ? 30 : 0);
      this.H = (sayCount + 1) * 12 + 1 + (this.info.charInfo != null ? 5 : 0);
   }

   public final void addInfo(String s, int Type, Char cInfo, boolean isChatServer) {
      this.type = Type;
      if (this.infoWaitToShow.size() > 10) {
         this.infoWaitToShow.removeElementAt(0);
      }

      if (this.infoWaitToShow.size() > 0) {
         s.equals(((InfoItem)this.infoWaitToShow.lastElement()).s);
      }

      InfoItem entry = new InfoItem(s);
      if (this.type == 0) {
         entry.speed = s.length();
      }

      if (entry.speed < 70) {
         entry.speed = 70;
      }

      if (this.type == 1) {
         entry.speed = 10000000;
      }

      if (this.type == 3) {
         entry.speed = 300;
         entry.last = mSystem.currentTimeMillis();
         entry.timeCount = s.length();
         if (entry.timeCount < 15) {
            entry.timeCount = 15;
         }

         if (entry.timeCount > 100) {
            entry.timeCount = 100;
         }

         entry.maxTime = entry.timeCount;
      }

      if (cInfo != null) {
         entry.charInfo = cInfo;
         entry.isChatServer = isChatServer;
         GameCanvas.panel.addChatMessage(entry);
         if (GameCanvas.isTouch && GameCanvas.panel.isViewChatServer) {
            GameScr.info2.cmdChat = new Command(mResources.CHAT, this, 1000, entry);
         }
      }

      if (cInfo != null && GameCanvas.panel.isViewChatServer || cInfo == null) {
         this.infoWaitToShow.addElement(entry);
      }

      if (this.infoWaitToShow.size() == 1) {
         this.info = (InfoItem)this.infoWaitToShow.firstElement();
         this.getInfo();
      }

      if (GameCanvas.isTouch
         && cInfo != null
         && GameCanvas.panel.isViewChatServer
         && GameCanvas.w - 50 > 155 + this.W) {
         GameScr.info2.cmdChat.x = GameCanvas.w - this.W - 50;
         GameScr.info2.cmdChat.y = 35;
      }
   }

   public final void perform(int idAction, Object p) {
      if (idAction == 1000) {
         ChatTextField chatTextField = ChatTextField.gI();
         GameScr.gI();
         chatTextField.startChat(mResources.chat_player);
      }
   }
}
