package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class CrackBallScr extends mScreen implements IActionListener {
   public static CrackBallScr instance;
   private BallInfo[] listBall;
   byte step = 0;
   private byte typePrice;
   private int rO;
   private int xO;
   private int yO;
   private int angle;
   private int iAngle;
   private int iDot;
   private int yTo;
   private int indexSelect;
   int indexSkillSelect;
   private int numTicket;
   private int xP;
   private int yP;
   private int wP;
   private int hP;
   private int price;
   private int cost;
   private int countFr = 0;
   private int countKame = 0;
   private int frame;
   private int vp;
   private int[] xArg;
   private int[] yArg;
   private int[] xDot;
   private int[] yDot;
   private short[] idItem;
   private long timeStart;
   private long timeKame;
   private boolean isKame;
   private boolean isCanSkill;
   private boolean isSendSv;
   private short idTicket;
   private static int ySkill;
   private static int[] xSkill;
   private static FrameImage fraImgKame;
   private static FrameImage fraImgKame_1;
   private static Image imgX;
   private static Image imgReplay;
   public static boolean isAutoCrackBall = false;
   public static boolean isCallStop = false;
   private byte[] fr = new byte[]{19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 20};
   private byte[] nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};

   public CrackBallScr() {
      (xSkill = new int[3])[0] = 16;
      ySkill = GameCanvas.h - 41;
      xSkill[1] = GameCanvas.w - 40;
      xSkill[2] = (xSkill[0] + xSkill[1]) / 2;
      Image img = mSystem.loadImage("/e/e_1.png");
      fraImgKame = new FrameImage(img, 30, 30);
      img = mSystem.loadImage("/e/e_0.png");
      fraImgKame_1 = new FrameImage(img, 68, 65);
      img = mSystem.loadImage("/e/e_2.png");
      new FrameImage(img, 66, 70);
      imgReplay = mSystem.loadImage("/e/nut2.png");
      imgX = mSystem.loadImage("/e/nut3.png");
      this.wP = 230;
      this.xP = GameCanvas.hw - this.wP / 2;
      this.hP = 40;
      this.yP = -this.hP;
   }

   public static CrackBallScr gI() {
      if (instance == null) {
         instance = new CrackBallScr();
      }

      return instance;
   }

   public final void SetCrackBallScr(short[] idImage, byte typePriceValue, int priceValue, short idTicketValue) {
      if (idImage != null && idImage.length > 0) {
         this.yTo = Char.myCharz().cy - 10;
         CrackBallScr crackBallScr = this;
         this.rO = GameCanvas.hh / 3 + 10;
         if (crackBallScr.rO > 50) {
            crackBallScr.rO = 50;
         }

         crackBallScr.xO = 360;
         GameScr.cmx = GameScr.cmxLim / 2;
         crackBallScr.yO = GameScr.cmy + GameCanvas.hh / 3 + 30;
         crackBallScr.iDot = 175;
         crackBallScr.angle = 0;
         crackBallScr.iAngle = 360 / crackBallScr.iDot;
         crackBallScr.xArg = new int[crackBallScr.iDot];
         crackBallScr.yArg = new int[crackBallScr.iDot];
         crackBallScr.xDot = new int[crackBallScr.iDot];
         crackBallScr.yDot = new int[crackBallScr.iDot];
         CrackBallScr crackBallScr2 = crackBallScr;
         if (!GameCanvas.lowGraphic) {
            for (int xArgIndex = 0; xArgIndex < crackBallScr2.yArg.length; xArgIndex++) {
               crackBallScr2.yArg[xArgIndex] = Res.abs(crackBallScr2.rO * Res.sin(crackBallScr2.angle) / 1024);
               crackBallScr2.xArg[xArgIndex] = Res.abs(crackBallScr2.rO * Res.cos(crackBallScr2.angle) / 1024);
               if (crackBallScr2.angle < 90) {
                  crackBallScr2.xDot[xArgIndex] = crackBallScr2.xO + crackBallScr2.xArg[xArgIndex];
                  crackBallScr2.yDot[xArgIndex] = crackBallScr2.yO - crackBallScr2.yArg[xArgIndex];
               } else if (crackBallScr2.angle >= 90 && crackBallScr2.angle < 180) {
                  crackBallScr2.xDot[xArgIndex] = crackBallScr2.xO - crackBallScr2.xArg[xArgIndex];
                  crackBallScr2.yDot[xArgIndex] = crackBallScr2.yO - crackBallScr2.yArg[xArgIndex];
               } else if (crackBallScr2.angle >= 180 && crackBallScr2.angle < 270) {
                  crackBallScr2.xDot[xArgIndex] = crackBallScr2.xO - crackBallScr2.xArg[xArgIndex];
                  crackBallScr2.yDot[xArgIndex] = crackBallScr2.yO + crackBallScr2.yArg[xArgIndex];
               } else {
                  crackBallScr2.xDot[xArgIndex] = crackBallScr2.xO + crackBallScr2.xArg[xArgIndex];
                  crackBallScr2.yDot[xArgIndex] = crackBallScr2.yO + crackBallScr2.yArg[xArgIndex];
               }

               crackBallScr2.angle = crackBallScr2.angle + crackBallScr2.iAngle;
            }
         }

         this.listBall = new BallInfo[idImage.length];

         for (int idImageIndex = 0; idImageIndex < this.listBall.length; idImageIndex++) {
            this.listBall[idImageIndex] = new BallInfo();
            this.listBall[idImageIndex].idImg = idImage[idImageIndex];
            this.listBall[idImageIndex].count = idImageIndex * 25;
            this.listBall[idImageIndex].yTo = -999;
            this.listBall[idImageIndex].vx = Res.random(2, 5);
            this.listBall[idImageIndex].dir = Res.random(-1, 2);
            BallInfo listBallValue;
            (listBallValue = this.listBall[idImageIndex]).cFocus = new Char();
            listBallValue.cFocus.charID = Res.random(-999, -800);
            listBallValue.cFocus.head = -1;
            listBallValue.cFocus.body = -1;
            listBallValue.cFocus.leg = -1;
            listBallValue.cFocus.bag = -1;
            listBallValue.cFocus.cName = "";
            listBallValue.cFocus.cHP = listBallValue.cFocus.cHPFull = 20L;
         }

         this.isCanSkill = false;
         this.isKame = false;
         this.isSendSv = false;
         this.timeStart = GameCanvas.timeNow + Res.random(1000, 2000);
         this.step = 0;
         this.indexSelect = -1;
         this.indexSkillSelect = -1;
         this.typePrice = typePriceValue;
         this.price = priceValue;
         this.cost = 0;
         Char.myCharz().moveTo(470, 408, 1);
         Char.myCharz().cdir = -1;
         Char.myCharz().statusMe = 1;
         this.countFr = 0;
         this.countKame = 0;
         this.frame = 0;
         this.vp = 0;
         this.yP = -this.hP;
         this.idTicket = idTicketValue;
         this.numTicket = 0;
         this.checkNumTicket();
         this.switchToMe();
         SoundMn.gI();
         SoundMn.hoisinh();
      }
   }

   public final void perform(int idAction, Object p) {
   }

   public final void update() {
      try {
         this.cost = this.price * this.checkNum();
         this.checkNumTicket();
         GameScr.gI().update();
         if (this.timeStart - GameCanvas.timeNow > 0L) {
            for (int listBallIndex = 0; listBallIndex < this.listBall.length; listBallIndex++) {
               this.listBall[listBallIndex].count += 2;
               if (this.listBall[listBallIndex].count >= this.iDot) {
                  this.listBall[listBallIndex].count = 0;
               }

               this.listBall[listBallIndex].x = this.xDot[this.listBall[listBallIndex].count];
               this.listBall[listBallIndex].y = this.yDot[this.listBall[listBallIndex].count];
            }

            return;
         }

         if (this.step == 0) {
            this.step = 1;
         }

         if (this.step == 1) {
            for (int listBallIndex2 = 0; listBallIndex2 < this.listBall.length; listBallIndex2++) {
               if (this.listBall[listBallIndex2].yTo != -999 && !this.listBall[listBallIndex2].isDone) {
                  if (this.listBall[listBallIndex2].y < this.listBall[listBallIndex2].yTo) {
                     if (this.listBall[listBallIndex2].vy < 0) {
                        this.listBall[listBallIndex2].vy = 0;
                     }

                     if (this.listBall[listBallIndex2].y + this.listBall[listBallIndex2].vy
                        > this.listBall[listBallIndex2].yTo) {
                        this.listBall[listBallIndex2].y = this.listBall[listBallIndex2].yTo;
                     } else {
                        this.listBall[listBallIndex2].y = this.listBall[listBallIndex2].y
                           + this.listBall[listBallIndex2].vy;
                     }

                     this.listBall[listBallIndex2].vy++;
                  } else {
                     if (this.listBall[listBallIndex2].vy > 0) {
                        this.listBall[listBallIndex2].vy = 0;
                     }

                     this.listBall[listBallIndex2].y = this.listBall[listBallIndex2].y + this.listBall[listBallIndex2].vy;
                     this.listBall[listBallIndex2].vy--;
                  }

                  if (this.listBall[listBallIndex2].y == this.listBall[listBallIndex2].yTo) {
                     EffecMn.addEff(
                        new Effect(19, this.listBall[listBallIndex2].x - 5, this.listBall[listBallIndex2].y + 25, 2, 1, -1)
                     );
                     SoundMn.gI();
                     SoundMn.charFall();
                     this.listBall[listBallIndex2].isDone = true;
                     if (!this.isCanSkill) {
                        this.isCanSkill = true;
                     }
                  }
               }
            }
         }

         if (this.step == 2) {
            for (int listBallIndex3 = 0; listBallIndex3 < this.listBall.length; listBallIndex3++) {
               if (!this.listBall[listBallIndex3].isDone) {
                  if (this.listBall[listBallIndex3].y > -10) {
                     if (this.listBall[listBallIndex3].vy > 0) {
                        this.listBall[listBallIndex3].vy = 0;
                     }

                     this.listBall[listBallIndex3].y = this.listBall[listBallIndex3].y + this.listBall[listBallIndex3].vy;
                     this.listBall[listBallIndex3].vy--;
                     this.listBall[listBallIndex3].x = this.listBall[listBallIndex3].x
                        + this.listBall[listBallIndex3].vx * this.listBall[listBallIndex3].dir;
                     this.listBall[listBallIndex3].vx -= 3;
                  }

                  if (this.listBall[listBallIndex3].y == -10) {
                     this.listBall[listBallIndex3].isPaint = false;
                  }
               }
            }

            this.countFr++;
            if (this.countFr > this.fr.length - 1) {
               this.countFr = this.fr.length - 1;
               this.isKame = true;
               SoundMn.gI();
               SoundMn.newKame();
               if (!this.isSendSv && this.timeKame - GameCanvas.timeNow < 0L) {
                  Service.gI().SendCrackBall((byte)2, (byte)(this.checkTicket() + this.checkNum()));
                  this.isSendSv = true;
               }
            }

            Char.myCharz().cf = this.fr[this.countFr];
            this.countKame++;
            if (this.countKame > 5) {
               this.countKame = 0;
            }

            this.frame = this.nFrame[this.countKame];
         }

         if (this.step == 3) {
            if (this.countKame <= 5) {
               this.countKame = 5;
            }

            this.countKame++;
            if (this.countKame > this.nFrame.length - 1) {
               this.countKame = this.nFrame.length - 1;
               this.step = 4;
               this.isKame = false;
               int idItemIndex = 0;

               for (int listBallIndex4 = 0; listBallIndex4 < this.listBall.length; listBallIndex4++) {
                  if (this.listBall[listBallIndex4].isDone && !this.listBall[listBallIndex4].isSetImg) {
                     this.listBall[listBallIndex4].idImg = this.idItem[idItemIndex];
                     this.listBall[listBallIndex4].isSetImg = true;
                     idItemIndex++;
                  }
               }
            }

            this.frame = this.nFrame[this.countKame];
         }

         if (this.step == 4) {
            for (int listBallIndex5 = 0; listBallIndex5 < this.listBall.length; listBallIndex5++) {
               if (this.listBall[listBallIndex5].isPaint) {
                  this.listBall[listBallIndex5].xTo = Char.myCharz().cx;
               }
            }

            this.step = 5;
         }

         if (this.step == 5) {
            this.vp++;
            if (this.yP < GameCanvas.hh / 3) {
               if (this.yP + this.vp > GameCanvas.hh / 3) {
                  this.yP = GameCanvas.hh / 3;
               } else {
                  this.yP = this.yP + this.vp;
               }
            }

            for (int listBallIndex6 = 0; listBallIndex6 < this.listBall.length; listBallIndex6++) {
               if (this.listBall[listBallIndex6].isPaint) {
                  if (this.listBall[listBallIndex6].x < this.listBall[listBallIndex6].xTo) {
                     if (this.listBall[listBallIndex6].vx < 0) {
                        this.listBall[listBallIndex6].vx = 0;
                     }

                     if (this.listBall[listBallIndex6].x + this.listBall[listBallIndex6].vx
                        > this.listBall[listBallIndex6].xTo) {
                        this.listBall[listBallIndex6].x = this.listBall[listBallIndex6].xTo;
                     } else {
                        this.listBall[listBallIndex6].x = this.listBall[listBallIndex6].x
                           + this.listBall[listBallIndex6].vx;
                     }

                     this.listBall[listBallIndex6].vx++;
                  } else {
                     if (this.listBall[listBallIndex6].vx > 0) {
                        this.listBall[listBallIndex6].vx = 0;
                     }

                     this.listBall[listBallIndex6].x = this.listBall[listBallIndex6].x + this.listBall[listBallIndex6].vx;
                     this.listBall[listBallIndex6].vx--;
                  }

                  if (this.listBall[listBallIndex6].x == this.listBall[listBallIndex6].xTo) {
                     this.listBall[listBallIndex6].isPaint = false;
                  }
               }
            }

            return;
         }
      } catch (Exception exception) {
         System.out.println("-upd--null: " + exception.toString());
      }
   }

   public final void updateKey() {
      if (!InfoDlg.isLock) {
         if (GameCanvas.isTouch && !ChatTextField.gI().isShow && !GameCanvas.menu.showMenu) {
            CrackBallScr crackBallScr = this;
            if (this.step == 1 && GameCanvas.isPointerClick) {
               for (int listBallIndex = 0; listBallIndex < crackBallScr.listBall.length; listBallIndex++) {
                  if (GameCanvas.isPointerHoldIn(
                        crackBallScr.listBall[listBallIndex].x - 20 - GameScr.cmx,
                        crackBallScr.listBall[listBallIndex].y - 10 - GameScr.cmy,
                        30,
                        30
                     )
                     && GameCanvas.isPointerClick
                     && GameCanvas.isPointerJustRelease) {
                     crackBallScr.doClickBall(listBallIndex);
                  }
               }
            }

            if (GameCanvas.isPointerClick) {
               for (int xSkillIndex = 0; xSkillIndex < xSkill.length; xSkillIndex++) {
                  if (GameCanvas.isPointerHoldIn(xSkill[xSkillIndex], ySkill, 36, 36)
                     && GameCanvas.isPointerClick
                     && GameCanvas.isPointerJustRelease) {
                     if (isAutoCrackBall && xSkillIndex != 2) {
                        break;
                     }

                     crackBallScr.doClickSkill(xSkillIndex);
                  }
               }
            }
         }

         if (isAutoCrackBall && !GameCanvas.keyPressed[0]) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
         }

         if (GameCanvas.keyPressed[0]) {
            this.doClickSkill(2);
         }

         for (int keyPressedIndex = 1; keyPressedIndex < 8; keyPressedIndex++) {
            if (GameCanvas.keyPressed[keyPressedIndex]) {
               GameCanvas.keyPressed[keyPressedIndex] = false;
               this.doClickBall(keyPressedIndex - 1);
            }
         }

         if (GameCanvas.keyPressed[12]) {
            GameCanvas.keyPressed[12] = false;
            this.doClickSkill(0);
         }

         if (GameCanvas.keyPressed[13]) {
            GameCanvas.keyPressed[13] = false;
            this.doClickSkill(1);
         }

         GameCanvas.clearKeyPressed();
      }
   }

   private void doClickBall(int index) {
      if (!this.listBall[index].isDone) {
         SoundMn.gI();
         SoundMn.getItem();
         long longValue = this.typePrice == 0 ? Char.myCharz().xu : Char.myCharz().checkLuong();
         if (this.checkTicket() >= this.numTicket && longValue < this.cost + this.price) {
            String s = mResources.not_enough_money_1 + " " + (this.typePrice == 0 ? mResources.XU : mResources.LUONG);
            GameScr.info1.addInfo(s, 0);
         } else {
            this.indexSelect = index;
            this.listBall[this.indexSelect].yTo = this.yTo + Res.random(-3, 3);
         }
      }
   }

   private void doClickSkill(int index) {
      this.indexSkillSelect = index;
      if (this.indexSkillSelect == 2) {
         if (isAutoCrackBall = !isAutoCrackBall) {
            CrackBallScr screen = this;
            new Thread(new CrackBallAnimationTask(screen)).start();
         } else {
            isCallStop = true;
         }
      } else {
         if (index == 0) {
            if (this.step < 2) {
               if (this.checkTicket() + this.checkNum() > 0) {
                  this.step = 2;
                  SoundMn.gI();
                  SoundMn.gong();
                  Char.myCharz().setSkillPaint(GameScr.sks[13], 0);
                  this.timeKame = GameCanvas.timeNow + Res.random(2000, 3000);
                  return;
               }
            } else if (this.yP == GameCanvas.hh / 3) {
               Service.gI().SendCrackBall(this.typePrice, (byte)0);
               return;
            }
         } else {
            if (isAutoCrackBall) {
               this.stopAutoCrackBall();
            }

            GameScr.gI().isRongThanXuatHien = false;
            GameScr.gI().switchToMe();
         }
      }
   }

   public final void paint(mGraphics g) {
      try {
         GameScr.gI().paint(g);
         g.translate(-GameScr.cmx, -GameScr.cmy);
         g.translate(0, GameCanvas.transY);

         for (int listBallIndex = 0; listBallIndex < this.listBall.length; listBallIndex++) {
            if (this.listBall[listBallIndex].isPaint
               && this.listBall[listBallIndex].y > this.listBall[listBallIndex].yTo - 20) {
               g.drawImage(
                  TileMap.bong, ((int) (this.listBall[listBallIndex].x)), ((int) (this.listBall[listBallIndex].yTo + 7)), 3
               );
            }
         }

         for (int listBallIndex2 = 0; listBallIndex2 < this.listBall.length; listBallIndex2++) {
            if (this.listBall[listBallIndex2].isPaint) {
               SmallImage.drawSmallImage(
                  g,
                  this.listBall[listBallIndex2].idImg,
                  this.listBall[listBallIndex2].x,
                  this.listBall[listBallIndex2].y,
                  0,
                  3
               );
            }
         }

         if (this.isKame) {
            if (fraImgKame != null) {
               int value = Char.myCharz().cx - fraImgKame.frameWidth - 28;

               for (int index = 0; index < GameCanvas.w / fraImgKame.frameWidth + 1; index++) {
                  fraImgKame.drawFrame(
                     this.frame,
                     value - index * (fraImgKame.frameWidth - 1),
                     Char.myCharz().cy - fraImgKame.frameHeight / 2 - 12 + 2,
                     0,
                     0,
                     g
                  );
               }
            }

            if (fraImgKame_1 != null) {
               int value2 = Char.myCharz().cx - fraImgKame_1.frameWidth - 10;
               fraImgKame_1.drawFrame(
                  this.frame, value2 - 5, Char.myCharz().cy - fraImgKame_1.frameHeight / 2 - 12, 0, 0, g
               );
            }
         }

         GameScr.resetTranslate(g);
         int x = GameCanvas.w - 240;
         g.setColor(13524492);
         g.fillRect(x, 0, 240, 15);
         g.drawImage(Panel.imgXu, ((int) (x + 11)), ((int) (8)), 3);
         g.drawImage(Panel.imgLuong, ((int) (x + 90)), ((int) (7)), 3);
         mFont.tahoma_7_yellow.drawString(g, Char.myCharz().xuStr, x + 24, 2, 0, mFont.tahoma_7_grey);
         mFont.tahoma_7_yellow.drawString(g, Char.myCharz().luongStr, x + 100, 2, 0, mFont.tahoma_7_grey);
         g.drawImage(Panel.imgLuongKhoa, ((int) (x + 150)), ((int) (8)), 3);
         mFont.tahoma_7_yellow.drawString(g, Char.myCharz().luongKhoaStr, x + 160, 2, 0, mFont.tahoma_7_grey);
         g.drawImage(Panel.imgTicket, ((int) (x + 200)), ((int) (8)), 3);
         mFont.tahoma_7_yellow.drawString(g, String.valueOf(this.numTicket), x + 210, 2, 0, mFont.tahoma_7_grey);
         if (this.step < 4) {
            x = GameCanvas.w - 140;
            g.setColor(11837316);
            g.fillRect(x, 15, 140, 15);
            if (this.typePrice == 0) {
               g.drawImage(Panel.imgXu, ((int) (x + 21)), ((int) (23)), 3);
            } else {
               g.drawImage(Panel.imgLuongKhoa, ((int) (x + 21)), ((int) (22)), 3);
               g.drawImage(Panel.imgLuong, ((int) (x + 18)), ((int) (22)), 3);
            }

            mFont.tahoma_7_red.drawString(g, "-" + this.cost, x + 30, 17, 0, mFont.tahoma_7_grey);
            g.drawImage(Panel.imgTicket, ((int) (x + 80)), ((int) (22)), 3);
            mFont.tahoma_7_red.drawString(g, "-" + this.checkTicket(), x + 90, 17, 0, mFont.tahoma_7_grey);
         }

         g.drawImage(GameScr.imgSkill, ((int) (xSkill[0])), ((int) (ySkill)), 0);
         if (this.indexSkillSelect == 0) {
            g.drawImage(GameScr.imgSkill2, ((int) (xSkill[0])), ((int) (ySkill)), 0);
         }

         if (this.step < 3) {
            SmallImage.drawSmallImage(g, 540, xSkill[0] + 14, ySkill + 14, 0, StaticObj.VCENTER_HCENTER);
         } else {
            g.drawImage(imgReplay, ((int) (xSkill[0] + 14 - 10)), ((int) (ySkill + 14 - 10)), 0);
         }

         g.drawImage(GameScr.imgSkill, ((int) (xSkill[1])), ((int) (ySkill)), 0);
         if (this.indexSkillSelect == 1) {
            g.drawImage(GameScr.imgSkill2, ((int) (xSkill[1])), ((int) (ySkill)), 0);
         }

         g.drawImage(imgX, ((int) (xSkill[1] + 14 - 10)), ((int) (ySkill + 14 - 10)), 0);
         if (this.step > 3) {
            Paint.paintFrameSimple(this.xP, this.yP, this.wP, this.hP, g);
            int value3 = GameCanvas.hw - this.idItem.length * 30 / 2;

            for (int idItemIndex = 0; idItemIndex < this.idItem.length; idItemIndex++) {
               SmallImage.drawSmallImage(g, this.idItem[idItemIndex], value3 + 5 + idItemIndex * 30, this.yP + 10, 0, 0);
            }
         }

         if (isAutoCrackBall) {
            g.drawImage(GameScr.imgSkill2, ((int) ((xSkill[0] + xSkill[1]) / 2)), ((int) (ySkill)), 0);
         } else {
            g.drawImage(GameScr.imgSkill, ((int) ((xSkill[0] + xSkill[1]) / 2)), ((int) (ySkill)), 0);
         }

         SmallImage.drawSmallImage(g, 4387, (xSkill[0] + xSkill[1]) / 2 + 14, ySkill + 14, 0, StaticObj.VCENTER_HCENTER);
      } catch (Exception exception) {
         System.out.println("-paint--null: " + exception.toString());
      }
   }

   public final void DoneCrackBallScr(short[] idImage) {
      this.step = 3;
      this.idItem = idImage;
   }

   public final void switchToMe() {
      GameScr.isPaintOther = true;
      GameScr.gI().isRongThanXuatHien = true;
      super.switchToMe();
   }

   private byte checkTicket() {
      byte numTicketValue = 0;

      for (int listBallIndex = 0; listBallIndex < this.listBall.length; listBallIndex++) {
         if (this.listBall[listBallIndex].isDone) {
            numTicketValue++;
         }
      }

      if (numTicketValue > this.numTicket) {
         numTicketValue = (byte)this.numTicket;
      }

      return numTicketValue;
   }

   private byte checkNum() {
      byte index = 0;

      for (int listBallIndex = 0; listBallIndex < this.listBall.length; listBallIndex++) {
         if (this.listBall[listBallIndex].isDone) {
            index++;
         }
      }

      if ((index = (byte)(index - this.checkTicket())) <= 0) {
         index = 0;
      }

      return index;
   }

   private void checkNumTicket() {
      for (int itemBagIndex = 0; itemBagIndex < Char.myCharz().arrItemBag.length; itemBagIndex++) {
         if (Char.myCharz().arrItemBag[itemBagIndex] != null
            && Char.myCharz().arrItemBag[itemBagIndex].template.id == this.idTicket) {
            this.numTicket = Char.myCharz().arrItemBag[itemBagIndex].quantity;
            return;
         }
      }
   }

   public final void stopAutoCrackBall() {
      isAutoCrackBall = false;
      this.indexSkillSelect = -1;
      isCallStop = false;
   }

   static void dispatchBallClick(CrackBallScr crackBallScr, int index) {
      crackBallScr.doClickBall(index);
   }

   static void useSkillCrackBall(CrackBallScr crackBallScr) {
      if ((crackBallScr = crackBallScr).step < 2) {
         if (crackBallScr.checkTicket() + crackBallScr.checkNum() > 0) {
            crackBallScr.step = 2;
            SoundMn.gI();
            SoundMn.gong();
            Char.myCharz().setSkillPaint(GameScr.sks[13], 0);
            crackBallScr.timeKame = GameCanvas.timeNow + Res.random(2000, 3000);
            return;
         }
      } else if (crackBallScr.yP == GameCanvas.hh / 3) {
         Service.gI().SendCrackBall(crackBallScr.typePrice, (byte)0);
      }
   }
}
