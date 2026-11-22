package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class Effect_End {
   private MyVector VecEffEnd = new MyVector("EffectEnd VecEffEnd");
   private FrameImage fraImgEff;
   private byte[] nFrame = new byte[10];
   private byte[] nFrame_2 = new byte[10];
   private int typePaint = 0;
   private int typeEffect = 0;
   private int typeSub = 0;
   private int range = 0;
   private int fRemove;
   private int fMove;
   private int n_frame;
   private int x;
   private int y;
   private int w;
   private int h;
   private int dir;
   private int dir_nguoc;
   public int levelPaint;
   private int f;
   private int frame;
   private int fSpeed;
   private int vy;
   private int x1000;
   private int y1000;
   private int vy1000;
   private int dy_throw;
   private int vMax;
   private int toY;
   private int stt;
   private int dy;
   private short timeRemove;
   private long time;
   public boolean isRemove;
   private boolean isAddSub;
   private Char charUse;
   private Point[] listObj;
   private Point target;
   public static short[][] arrInfoEff = new short[][]{
      {68, 264, 4},
      {30, 120, 4},
      {66, 280, 4},
      {0, 0, 1},
      {111, 68, 2},
      {90, 68, 2},
      {125, 68, 2},
      {47, 282, 6},
      {10, 40, 4},
      {92, 525, 7},
      {62, 372, 6},
      {80, 352, 4},
      {80, 352, 4},
      {80, 352, 4},
      {72, 240, 3},
      {20, 42, 3},
      {65, 160, 4},
      {50, 300, 6},
      {84, 168, 2},
      {90, 540, 6},
      {180, 900, 6},
      {62, 186, 3},
      {34, 80, 4},
      {140, 560, 4},
      {64, 600, 6},
      {36, 200, 5},
      {35, 200, 5},
      {50, 250, 5},
      {50, 240, 6}
   };
   private int rS;
   private int angleS;
   private int angleO;
   private int iAngleS;
   private int iDotS;
   private int[] xArgS;
   private int[] yArgS;
   private int[] xDotS;
   private int[] yDotS;
   private static int[][] colorStar = new int[][]{{16310304, 16298056, 16777215}, {7045120, 12643960, 16777215}, {2407423, 11987199, 16777215}};
   private int[] colorpaint;
   private int indexColorStar;
   private int xline;
   private int yline;
   private FrameImage[] fra_skill;

   public static Image getImage(int id) {
      if (id < 0) {
         return null;
      }

      String path = "/e/e_" + id + ".png";
      Image image = null;

      try {
         image = mSystem.loadImage(path);
      } catch (Exception ignoredException) {
      }

      return image;
   }

   public Effect_End(int type, int typeSubValue, int typePaintValue, Char charUseValue, Point targetValue, int levelPaintValue, short timeRemoveValue, short rangeValue) {
      byte[] data = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
      data = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
      data = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
      this.indexColorStar = 0;
      this.f = 0;
      this.stt = 0;
      this.typeEffect = type;
      this.typeSub = typeSubValue;
      this.typePaint = typePaintValue;
      this.charUse = charUseValue;
      if (charUseValue.containsCaiTrang(1265)) {
         if (this.typeEffect == 21 || this.typeEffect == 22 || this.typeEffect == 23) {
            this.charUse.cx = this.charUse.cx + 10 * this.charUse.cdir;
         } else if (this.typeEffect != 18 && this.typeEffect != 19 && this.typeEffect != 20) {
            this.charUse.cx = this.charUse.cx + 15 * this.charUse.cdir;
         } else {
            this.charUse.cx = this.charUse.cx + -15 * this.charUse.cdir;
         }
      }

      this.x = this.charUse.cx;
      this.y = this.charUse.cy;
      this.dir = this.charUse.cdir;
      this.dir_nguoc = this.dir == -1 ? 2 : 0;
      this.target = targetValue;
      this.levelPaint = levelPaintValue;
      this.time = mSystem.currentTimeMillis();
      this.timeRemove = timeRemoveValue;
      this.range = rangeValue;
      this.isRemove = this.isAddSub = false;
      this.n_frame = 4;
      this.get_Img_Skill();
      this.create_Effect();
   }

   public Effect_End(int type, int typeSubValue, int typePaintValue, int xValue, int yValue, int levelPaintValue, int dirValue, short timeRemoveValue, Point[] listObjValue) {
      byte[] data = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
      data = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
      data = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
      this.indexColorStar = 0;
      this.f = 0;
      this.stt = 0;
      this.typeEffect = type;
      this.typeSub = typeSubValue;
      this.typePaint = typePaintValue;
      this.x = xValue;
      this.y = yValue;
      this.levelPaint = levelPaintValue;
      this.dir = dirValue;
      this.dir_nguoc = dirValue == -1 ? 2 : 0;
      this.time = mSystem.currentTimeMillis();
      this.timeRemove = timeRemoveValue;
      this.isRemove = this.isAddSub = false;
      this.n_frame = 4;
      if (listObjValue != null) {
         this.listObj = new Point[listObjValue.length];

         for (int listObjIndex = 0; listObjIndex < this.listObj.length; listObjIndex++) {
            this.listObj[listObjIndex] = listObjValue[listObjIndex];
         }
      }

      this.get_Img_Skill();
      this.create_Effect();
   }

   private void create_Effect() {
      try {
         int typeEffectValue = this.y;
         int xValue = this.x;

         try {
            Res.random(3);
         } catch (Exception exception) {
            Res.err("ERR setSoundSkill_END: " + exception.toString());
         }

         switch (this.typeEffect) {
            case 0:
            case 1:
            case 2:
               typeEffectValue = this.typeEffect;
               Effect_End effect_End = this;
               switch (typeEffectValue) {
                  case 0:
                     effect_End.fraImgEff = new FrameImage(4);
                     break;
                  case 1:
                     effect_End.fraImgEff = new FrameImage(5);
                     break;
                  case 2:
                     effect_End.fraImgEff = new FrameImage(6);
               }

               effect_End.fRemove = 100;
               effect_End.dy_throw = GameCanvas.h / 3 + 10;
               effect_End.vy = 10;
               effect_End.y1000 = 0;
               effect_End.isAddSub = false;
               return;
            case 3:
               this.set_FireWork();
               return;
            case 9:
               Effect_End effect_End2 = this;
               this.indexColorStar = effect_End2.typeSub;
               effect_End2.x1000 = effect_End2.x * 1000;
               effect_End2.y1000 = effect_End2.y * 1000;
               effect_End2.fRemove = Res.random(4, 6);
               effect_End2.vMax = 5;
               effect_End2.xline = 10;
               effect_End2.yline = 20;
               effect_End2.create_Star_Line_In(effect_End2.vMax, effect_End2.xline, effect_End2.yline, 0);
               return;
            case 10:
            case 11:
               this.set_End_Rock();
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 12:
            case 13:
            case 14:
            case 15:
            default:
               return;
            case 16:
            case 17:
               Effect_End effect_End3 = this;
               if (this.typeEffect == 17) {
                  effect_End3.x = effect_End3.x + (effect_End3.dir == 0 ? 0 : -effect_End3.fra_skill[0].frameWidth);
                  effect_End3.y = effect_End3.y - effect_End3.fra_skill[0].frameHeight / 2;
               }

               return;
            case 18:
            case 19:
            case 20:
               Effect_End effect_End4 = this;
               this.nFrame = null;
               effect_End4.n_frame = 3;
               if (effect_End4.typeEffect == 18) {
                  if (effect_End4.typeSub == 0) {
                     effect_End4.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
                     break;
                  }

                  effect_End4.nFrame = new byte[]{3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6};
               }

               return;
            case 21:
            case 22:
            case 23:
               Effect_End effect_End5 = this;
               if (this.charUse != null) {
                  if (effect_End5.typeEffect == 21) {
                     effect_End5.x = effect_End5.charUse.cx - 3 * effect_End5.charUse.cdir;
                     effect_End5.y = effect_End5.charUse.cy;
                     SoundMn.playSound();
                     break;
                  }

                  if (effect_End5.typeEffect == 22) {
                     effect_End5.x = effect_End5.charUse.cx + 20 * effect_End5.charUse.cdir;
                     effect_End5.y = effect_End5.charUse.cy - 4;
                     SoundMn.playSound();
                     break;
                  }

                  if (effect_End5.typeEffect == 23) {
                     effect_End5.x = effect_End5.charUse.cx;
                     effect_End5.y = effect_End5.charUse.cy - 50;
                     SoundMn.playSound();
                     break;
                  }

                  effect_End5.x = effect_End5.charUse.cx;
                  effect_End5.y = effect_End5.charUse.cy;
               }

               return;
            case 24:
               Effect_End effect_End6 = this;
               this.w = effect_End6.fra_skill[0].frameWidth;
               effect_End6.h = effect_End6.fra_skill[0].frameHeight;
               effect_End6.vMax = Res.abs(effect_End6.x - effect_End6.target.x);
               effect_End6.nFrame = new byte[]{0, 0, 0, 1, 1, 1};
               effect_End6.isAddSub = false;
               SoundMn.playSound();
               return;
            case 25:
               this.set_Skill_Destroy();
               return;
            case 26:
               Effect_End effect_End7 = this;
               this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
               effect_End7.isAddSub = false;
               effect_End7.fMove = 10;
               effect_End7.x1000 = effect_End7.x;
               effect_End7.y1000 = effect_End7.y + 12;
               effect_End7.dy = 25;
               effect_End7.dy_throw = 19;
               if (effect_End7.typeSub == 1) {
                  effect_End7.dy_throw = 21;
               } else if (effect_End7.typeSub == 2) {
                  effect_End7.dy_throw = 31;
               }

               effect_End7.h = effect_End7.fra_skill[1].frameHeight + 50 - effect_End7.dy_throw;
               effect_End7.vy = 1;
               effect_End7.vy1000 = 1;
               effect_End7.y = effect_End7.y1000 - effect_End7.h;
               effect_End7.rS = 90;
               effect_End7.vMax = 1;
               effect_End7.angleS = effect_End7.angleO = 25;
               effect_End7.iDotS = 1;
               if (effect_End7.listObj != null && effect_End7.listObj.length > 0) {
                  effect_End7.iDotS = effect_End7.listObj.length;
               }

               effect_End7.iAngleS = 360 / effect_End7.iDotS;
               effect_End7.xArgS = new int[effect_End7.iDotS];
               effect_End7.yArgS = new int[effect_End7.iDotS];
               effect_End7.xDotS = new int[effect_End7.iDotS];
               effect_End7.yDotS = new int[effect_End7.iDotS];
               GameScr.addEffectEnd(16, 0, effect_End7.typePaint, effect_End7.x1000, effect_End7.y1000, 1, 0, (short)-1, null);
               SoundMn.playSound();
               return;
         }
      } catch (Exception exception2) {
         Res.err("ERR create_Effect: " + exception2.toString());
         this.isRemove = true;
      }
   }

   public final void update() {
      try {
         this.f++;
         switch (this.typeEffect) {
            case 0:
            case 1:
            case 2:
               Effect_End effect_End = this;
               this.x = GameCanvas.hw;
               effect_End.y = effect_End.y1000;
               if (effect_End.f > effect_End.fRemove) {
                  effect_End.isRemove = true;
               }

               effect_End.vy++;
               if (effect_End.vy > 15) {
                  effect_End.vy = 15;
               }

               if (effect_End.y1000 + effect_End.vy >= effect_End.dy_throw) {
                  effect_End.y1000 = effect_End.dy_throw;
                  if (!effect_End.isAddSub) {
                     effect_End.isAddSub = true;
                     if (effect_End.typeSub != -1) {
                        GameScr.addEffectEnd(
                           effect_End.typeSub, 0, 0, effect_End.x, effect_End.y, effect_End.levelPaint, 0, (short)-1, null
                        );
                     }
                  }

                  return;
               }

               effect_End.y1000 = effect_End.y1000 + effect_End.vy;
               break;
            case 3:
               Effect_End effect_End2 = this;

               for (int vecEffEndIndex = 0; vecEffEndIndex < effect_End2.VecEffEnd.size(); vecEffEndIndex++) {
                  Point vecEffEnd;
                  (vecEffEnd = (Point)effect_End2.VecEffEnd.elementAt(vecEffEndIndex)).update();
                  if (vecEffEnd.f == vecEffEnd.fRe) {
                     SoundMn.playSound();
                  }

                  if (vecEffEnd.f - vecEffEnd.fRe > vecEffEnd.fraImgEff.nFrame * 3 - 1) {
                     vecEffEnd.f = 0;
                     if (effect_End2.typeSub == 0) {
                        vecEffEnd.fRe = Res.random(10);
                        byte byteValue = 1;
                        if (vecEffEndIndex % 2 == 0) {
                           byteValue = -1;
                        }

                        vecEffEnd.x = effect_End2.x + Res.random(arrInfoEff[5][0] / 2) * byteValue;
                        vecEffEnd.y = effect_End2.y - Res.random(arrInfoEff[5][1] / 2);
                     }
                  }
               }

               if (effect_End2.f >= effect_End2.fRemove) {
                  effect_End2.isRemove = true;
               }

               return;
            case 9:
               Effect_End effect_End3 = this;

               for (int vecEffEndIndex2 = 0; vecEffEndIndex2 < effect_End3.VecEffEnd.size(); vecEffEndIndex2++) {
                  Line vecEffEnd2;
                  Line vecEffEnd3;
                  Line vecEffEnd4 = vecEffEnd3 = vecEffEnd2 = (Line)effect_End3.VecEffEnd.elementAt(vecEffEndIndex2);
                  vecEffEnd4.x0 = vecEffEnd4.x0 + vecEffEnd3.vx;
                  vecEffEnd3.x1 = vecEffEnd3.x1 + vecEffEnd3.vx;
                  vecEffEnd3.y0 = vecEffEnd3.y0 + vecEffEnd3.vy;
                  vecEffEnd3.y1 = vecEffEnd3.y1 + vecEffEnd3.vy;
                  vecEffEnd3.f++;
                  if (effect_End3.f >= effect_End3.fRemove) {
                     effect_End3.VecEffEnd.removeElement(vecEffEnd2);
                     vecEffEndIndex2--;
                  }
               }

               if (effect_End3.f >= effect_End3.fRemove) {
                  if (GameCanvas.timeNow - effect_End3.time >= effect_End3.timeRemove) {
                     effect_End3.VecEffEnd.removeAllElements();
                     effect_End3.isRemove = true;
                     break;
                  }

                  effect_End3.fRemove = Res.random(4, 6);
                  effect_End3.f = 0;
                  effect_End3.create_Star_Line_In(effect_End3.vMax, effect_End3.xline, effect_End3.yline, 0);
               }

               return;
            case 10:
            case 11:
               Effect_End effect_End4 = this;

               for (int vecEffEndIndex3 = 0; vecEffEndIndex3 < effect_End4.VecEffEnd.size(); vecEffEndIndex3++) {
                  Point vecEffEnd5;
                  (vecEffEnd5 = (Point)effect_End4.VecEffEnd.elementAt(vecEffEndIndex3)).update();
                  if (vecEffEnd5.y < effect_End4.toY) {
                     effect_End4.VecEffEnd.removeElementAt(vecEffEndIndex3);
                     vecEffEndIndex3--;
                  }
               }

               if (effect_End4.f >= effect_End4.fRemove) {
                  effect_End4.isRemove = true;
               }
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 12:
            case 13:
            case 14:
            case 15:
            default:
               return;
            case 16:
            case 17:
               Effect_End effect_End5 = this;
               if (this.timeRemove > 0) {
                  if (GameCanvas.timeNow - effect_End5.time >= effect_End5.timeRemove) {
                     effect_End5.isRemove = true;
                     break;
                  }
               } else if (effect_End5.f >= effect_End5.fra_skill[0].nFrame * effect_End5.n_frame) {
                  effect_End5.isRemove = true;
               }

               return;
            case 18:
            case 19:
            case 20:
               Effect_End effect_End6 = this;
               if (this.charUse != null) {
                  effect_End6.x = effect_End6.charUse.cx;
                  effect_End6.y = effect_End6.charUse.cy + 13;
               }

               if (effect_End6.timeRemove > 0) {
                  if (GameCanvas.timeNow - effect_End6.time >= effect_End6.timeRemove) {
                     effect_End6.isRemove = true;
                     break;
                  }
               } else if (effect_End6.nFrame != null) {
                  if (effect_End6.f > effect_End6.nFrame.length) {
                     effect_End6.isRemove = true;
                     break;
                  }
               } else if (effect_End6.f >= effect_End6.fra_skill[0].nFrame * effect_End6.n_frame) {
                  effect_End6.isRemove = true;
               }

               return;
            case 21:
            case 22:
            case 23:
               Effect_End effect_End7 = this;
               if (this.charUse != null) {
                  if (effect_End7.typeEffect == 21) {
                     effect_End7.x = effect_End7.charUse.cx - 3 * effect_End7.charUse.cdir;
                     effect_End7.y = effect_End7.charUse.cy;
                  } else if (effect_End7.typeEffect == 22) {
                     effect_End7.x = effect_End7.charUse.cx + 20 * effect_End7.charUse.cdir;
                     effect_End7.y = effect_End7.charUse.cy - 4;
                  } else if (effect_End7.typeEffect == 23) {
                     effect_End7.x = effect_End7.charUse.cx;
                     effect_End7.y = effect_End7.charUse.cy - 50;
                  } else {
                     effect_End7.x = effect_End7.charUse.cx;
                     effect_End7.y = effect_End7.charUse.cy;
                  }
               }

               if (effect_End7.timeRemove > 0) {
                  if (GameCanvas.timeNow - effect_End7.time >= effect_End7.timeRemove) {
                     effect_End7.isRemove = true;
                     break;
                  }
               } else if (effect_End7.f >= effect_End7.fra_skill[0].nFrame * effect_End7.n_frame) {
                  effect_End7.isRemove = true;
               }

               return;
            case 24:
               Effect_End effect_End8 = this;
               this.fSpeed++;
               effect_End8.w += 20;
               if (effect_End8.w > effect_End8.vMax) {
                  effect_End8.w = effect_End8.vMax;
               }

               effect_End8.x = effect_End8.charUse.cx + 10;
               effect_End8.y = effect_End8.charUse.cy - 3;
               if (effect_End8.dir == -1) {
                  effect_End8.x = effect_End8.charUse.cx - effect_End8.w - 10;
               }

               if (!effect_End8.isAddSub && GameCanvas.timeNow - effect_End8.time >= effect_End8.timeRemove) {
                  effect_End8.f = 0;
                  effect_End8.nFrame = new byte[]{2, 2, 2, 3, 3, 3};
                  effect_End8.isAddSub = true;
               }

               if (effect_End8.f > effect_End8.nFrame.length - 1) {
                  if (effect_End8.isAddSub) {
                     effect_End8.isRemove = true;
                     break;
                  }

                  effect_End8.f = 0;
               }

               return;
            case 25:
               Effect_End effect_End9 = this;
               int index = 0;

               for (int vecEffEndIndex4 = 0; vecEffEndIndex4 < effect_End9.VecEffEnd.size(); vecEffEndIndex4++) {
                  Point vecEffEnd6;
                  if (!(vecEffEnd6 = (Point)effect_End9.VecEffEnd.elementAt(vecEffEndIndex4)).isPaint
                     && GameCanvas.timeNow - effect_End9.time >= vecEffEndIndex4 * effect_End9.fMove) {
                     vecEffEnd6.isPaint = true;
                     GameScr.addEffectEnd(
                        17,
                        0,
                        effect_End9.typePaint,
                        effect_End9.charUse.cx,
                        effect_End9.charUse.cy - 3,
                        2,
                        effect_End9.dir_nguoc,
                        (short)-1,
                        null
                     );
                     if (vecEffEndIndex4 == effect_End9.VecEffEnd.size() - 1) {
                        SoundMn.playSound();
                     } else {
                        SoundMn.playSound();
                     }
                  }

                  if (vecEffEnd6.isPaint && !vecEffEnd6.isRemove) {
                     vecEffEnd6.f++;
                     if (!vecEffEnd6.isChange) {
                        if (vecEffEnd6.f < 10
                           && vecEffEndIndex4 == effect_End9.VecEffEnd.size() - 1
                           && effect_End9.charUse != null
                           && !TileMap.tileTypeAt(
                              effect_End9.charUse.cx
                                 - (effect_End9.charUse.chw + 1) * effect_End9.charUse.cdir,
                              effect_End9.charUse.cy,
                              effect_End9.charUse.cdir == 1 ? 8 : 4
                           )) {
                           effect_End9.charUse.cx = effect_End9.charUse.cx - effect_End9.charUse.cdir;
                        }

                        int yValue = vecEffEnd6.toY;
                        int xValue = vecEffEnd6.toX;
                        Point vecEffEnd7 = vecEffEnd6;
                        int deltaX = xValue - vecEffEnd7.x;
                        int deltaY = yValue - vecEffEnd7.y;
                        if (deltaX > 1) {
                           deltaX = Res.angle(deltaX, deltaY);
                           vecEffEnd7.frame = vecEffEnd7.setFrameAngle(deltaX);
                        }

                        if (Res.abs(vecEffEnd7.vx) > 0
                           && Res.abs(vecEffEnd7.x - xValue) >= Res.abs(vecEffEnd7.vx)) {
                           vecEffEnd7.x = vecEffEnd7.x + vecEffEnd7.vx;
                        } else {
                           vecEffEnd7.x = xValue;
                           vecEffEnd7.vx = 0;
                        }

                        if (Res.abs(vecEffEnd7.vy) > 0
                           && Res.abs(vecEffEnd7.y - yValue) >= Res.abs(vecEffEnd7.vy)) {
                           vecEffEnd7.y = vecEffEnd7.y + vecEffEnd7.vy;
                        } else {
                           vecEffEnd7.y = yValue;
                           vecEffEnd7.vy = 0;
                        }

                        if (vecEffEnd6.x == vecEffEnd6.toX) {
                           vecEffEnd6.isChange = true;
                           vecEffEnd6.f = 0;
                        }
                     }

                     if (vecEffEnd6.isChange && vecEffEnd6.f >= effect_End9.n_frame * vecEffEnd6.fraImgEff_2.nFrame) {
                        vecEffEnd6.isRemove = true;
                     }
                  }

                  if (vecEffEnd6.isRemove) {
                     index++;
                  }
               }

               if (index == effect_End9.VecEffEnd.size()) {
                  effect_End9.isRemove = true;
               }

               return;
            case 26:
               Effect_End effect_End10 = this;
               if (this.stt == 0) {
                  if (effect_End10.f == 3) {
                     SoundMn.playSound();
                  }

                  effect_End10.frame++;
                  if (effect_End10.frame > effect_End10.nFrame.length - 1) {
                     effect_End10.frame = effect_End10.nFrame.length - 1;
                  }

                  if (effect_End10.f == effect_End10.fMove + 4) {
                     GameScr.addEffectEnd(16, 1, effect_End10.typePaint, effect_End10.x, effect_End10.y, 3, 0, (short)2945, null);
                  }

                  if (effect_End10.f > effect_End10.fMove + 4) {
                     effect_End10.rS--;
                     if (effect_End10.rS < 0) {
                        effect_End10.rS = 0;
                        effect_End10.f = 0;
                        effect_End10.fSpeed = 0;
                        effect_End10.nFrame_2 = new byte[]{1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 2};
                        boolean condition = true;
                        Effect_End effect_End11 = effect_End10;
                        if (effect_End10.listObj != null) {
                           for (int listObjIndex = 0; listObjIndex < effect_End11.listObj.length; listObjIndex++) {
                              if (effect_End11.listObj[listObjIndex] != null) {
                                 if (effect_End11.listObj[listObjIndex].type == 0) {
                                    Mob mob;
                                    if ((mob = GameScr.findMobInMap(((int) (effect_End11.listObj[listObjIndex].id)))) != null) {
                                       mob.isHide = true;
                                    }
                                 } else {
                                    Char character;
                                    if (Char.myCharz().charID == effect_End11.listObj[listObjIndex].id) {
                                       character = Char.myCharz();
                                    } else {
                                       character = GameScr.findCharInMap(effect_End11.listObj[listObjIndex].id);
                                    }

                                    if (character != null) {
                                       character.isHide = true;
                                    }
                                 }
                              }
                           }
                        }

                        effect_End10.stt = 1;
                     } else {
                        Effect_End effect_End12 = effect_End10;
                        if (effect_End10.vMax < 40) {
                           effect_End12.vMax += 2;
                        }

                        effect_End12.angleS = effect_End12.angleO;
                        effect_End12.angleS = effect_End12.angleS - effect_End12.vMax;
                        if (effect_End12.angleS >= 360) {
                           effect_End12.angleS -= 360;
                        }

                        if (effect_End12.angleS < 0) {
                           effect_End12.angleS += 360;
                        }

                        effect_End12.angleO = effect_End12.angleS;
                        effect_End12 = effect_End10;

                        for (int xArgSIndex = 0; xArgSIndex < effect_End12.yArgS.length; xArgSIndex++) {
                           if (effect_End12.angleS >= 360) {
                              effect_End12.angleS -= 360;
                           }

                           if (effect_End12.angleS < 0) {
                              effect_End12.angleS += 360;
                           }

                           effect_End12.yArgS[xArgSIndex] = Res.abs(
                              effect_End12.rS * Res.sin(effect_End12.angleS) / 1024
                           );
                           effect_End12.xArgS[xArgSIndex] = Res.abs(
                              effect_End12.rS * Res.cos(effect_End12.angleS) / 1024
                           );
                           if (effect_End12.angleS < 90) {
                              effect_End12.xDotS[xArgSIndex] = effect_End12.x + effect_End12.xArgS[xArgSIndex];
                              effect_End12.yDotS[xArgSIndex] = effect_End12.y - effect_End12.yArgS[xArgSIndex];
                           } else if (effect_End12.angleS >= 90 && effect_End12.angleS < 180) {
                              effect_End12.xDotS[xArgSIndex] = effect_End12.x - effect_End12.xArgS[xArgSIndex];
                              effect_End12.yDotS[xArgSIndex] = effect_End12.y - effect_End12.yArgS[xArgSIndex];
                           } else if (effect_End12.angleS >= 180 && effect_End12.angleS < 270) {
                              effect_End12.xDotS[xArgSIndex] = effect_End12.x - effect_End12.xArgS[xArgSIndex];
                              effect_End12.yDotS[xArgSIndex] = effect_End12.y + effect_End12.yArgS[xArgSIndex];
                           } else {
                              effect_End12.xDotS[xArgSIndex] = effect_End12.x + effect_End12.xArgS[xArgSIndex];
                              effect_End12.yDotS[xArgSIndex] = effect_End12.y + effect_End12.yArgS[xArgSIndex];
                           }

                           effect_End12.angleS = effect_End12.angleS - effect_End12.iAngleS;
                        }

                        effect_End10.updListObj_Mafuba(true);
                     }

                     return;
                  }
               } else if (effect_End10.stt == 1) {
                  effect_End10.fSpeed++;
                  if (effect_End10.fSpeed > effect_End10.nFrame_2.length - 1) {
                     effect_End10.fSpeed = effect_End10.nFrame_2.length - 1;
                     if (GameCanvas.gameTick % 2 == 0) {
                        effect_End10.vy1000++;
                     }

                     effect_End10.vy = effect_End10.vy + effect_End10.vy1000;
                     if (effect_End10.vy
                        >= effect_End10.h - effect_End10.fra_skill[0].frameHeight - effect_End10.dy + effect_End10.dy_throw) {
                        effect_End10.vy = effect_End10.h
                           - effect_End10.fra_skill[0].frameHeight
                           - effect_End10.dy
                           + effect_End10.dy_throw;
                        effect_End10.f = 0;
                        effect_End10.fSpeed = 0;
                        effect_End10.stt = 2;
                        effect_End10.nFrame_2 = new byte[]{3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5};
                        return;
                     }
                  }
               } else if (effect_End10.stt == 2) {
                  effect_End10.fSpeed++;
                  if (effect_End10.fSpeed > effect_End10.nFrame_2.length - 1) {
                     effect_End10.stt = 3;
                     effect_End10.frame = 0;
                     effect_End10.nFrame = new byte[]{2, 2, 1, 1, 0, 0, 3, 3, 3, 0, 0, 0, 4, 4, 4, 0, 0};
                     return;
                  }
               } else if (effect_End10.stt == 3) {
                  effect_End10.frame++;
                  if (effect_End10.frame == 3) {
                     SoundMn.playSound();
                  }

                  if (effect_End10.frame > effect_End10.nFrame.length - 1) {
                     effect_End10.frame = 0;
                     effect_End10.stt = 4;
                     effect_End10.nFrame = new byte[]{
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        3,
                        3,
                        3,
                        0,
                        0,
                        0,
                        4,
                        4,
                        4,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        3,
                        3,
                        0,
                        0,
                        4,
                        4
                     };
                     return;
                  }
               } else {
                  effect_End10.frame++;
                  if (effect_End10.frame > effect_End10.nFrame.length - 1) {
                     effect_End10.frame = 0;
                  }

                  if (GameCanvas.timeNow - effect_End10.time >= effect_End10.timeRemove) {
                     GameScr.addEffectEnd(16, 0, effect_End10.typePaint, effect_End10.x1000, effect_End10.y1000, 1, 0, (short)-1, null);
                     effect_End10.updListObj_Mafuba(false);
                     effect_End10.isRemove = true;
                  }
               }

               return;
         }
      } catch (Exception exception) {
         Res.err("ERR update: " + exception.toString());
         this.isRemove = true;
      }
   }

   public final void paint(mGraphics graphics) {
      try {
         if (this.isRemove || this.f < 0) {
            return;
         }

         switch (this.typeEffect) {
            case 0:
            case 1:
            case 2:
               mGraphics g = graphics;
               Effect_End effect_End = this;
               if (this.fraImgEff != null) {
                  effect_End.fraImgEff
                     .drawFrame(
                        effect_End.f / 5 % effect_End.fraImgEff.nFrame, effect_End.x, effect_End.y, 0, 33, g
                     );
               }

               return;
            case 3:
               mGraphics g2 = graphics;
               Effect_End effect_End2 = this;

               for (int vecEffEndIndex = 0; vecEffEndIndex < effect_End2.VecEffEnd.size(); vecEffEndIndex++) {
                  Point vecEffEnd;
                  if ((vecEffEnd = (Point)effect_End2.VecEffEnd.elementAt(vecEffEndIndex)).f - vecEffEnd.fRe >= 0
                     && vecEffEnd.fraImgEff != null) {
                     vecEffEnd.fraImgEff
                        .drawFrame(
                           (vecEffEnd.f - vecEffEnd.fRe) / 3 % vecEffEnd.fraImgEff.nFrame,
                           vecEffEnd.x,
                           vecEffEnd.y,
                           0,
                           3,
                           g2
                        );
                  }
               }

               return;
            case 9:
               mGraphics graphics2 = graphics;
               Effect_End effect_End3 = this;

               for (int vecEffEndIndex2 = 0; vecEffEndIndex2 < effect_End3.VecEffEnd.size(); vecEffEndIndex2++) {
                  Line vecEffEnd2;
                  if ((vecEffEnd2 = (Line)effect_End3.VecEffEnd.elementAt(vecEffEndIndex2)) != null) {
                     int color = 0;
                     if (vecEffEndIndex2 / 2 < effect_End3.colorpaint.length) {
                        color = effect_End3.colorpaint[vecEffEndIndex2 / 2];
                     }

                     graphics2.setColor(color);
                     graphics2.drawLine(
                        vecEffEnd2.x0 / 1000, vecEffEnd2.y0 / 1000, vecEffEnd2.x1 / 1000, vecEffEnd2.y1 / 1000
                     );
                     if (vecEffEnd2.is2Line) {
                        graphics2.drawLine(
                           vecEffEnd2.x0 / 1000 + 1, vecEffEnd2.y0 / 1000, vecEffEnd2.x1 / 1000 + 1, vecEffEnd2.y1 / 1000
                        );
                     }
                  }
               }

               return;
            case 10:
            case 11:
               mGraphics g3 = graphics;
               Effect_End effect_End4 = this;

               for (int vecEffEndIndex3 = 0; vecEffEndIndex3 < effect_End4.VecEffEnd.size(); vecEffEndIndex3++) {
                  Point vecEffEnd3 = (Point)effect_End4.VecEffEnd.elementAt(vecEffEndIndex3);
                  if (effect_End4.fraImgEff != null) {
                     effect_End4.fraImgEff.drawFrame(vecEffEnd3.frame, vecEffEnd3.x, vecEffEnd3.y, 0, 3, g3);
                  }
               }
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 12:
            case 13:
            case 14:
            case 15:
            default:
               return;
            case 16:
               if (this.typeSub == 0) {
                  this.pnt_Sub(graphics, 33);
                  return;
               }

               this.pnt_Sub(graphics, 3);
               return;
            case 17:
               this.pnt_Sub(graphics, 0);
               return;
            case 18:
            case 19:
            case 20:
               byte byteValue = 33;
               mGraphics g4 = graphics;
               Effect_End effect_End5 = this;
               if (this.fra_skill[0] != null) {
                  if (effect_End5.nFrame != null) {
                     effect_End5.fra_skill[0]
                        .drawFrame(
                           effect_End5.nFrame[effect_End5.f % effect_End5.nFrame.length],
                           effect_End5.x,
                           effect_End5.y,
                           effect_End5.dir_nguoc,
                           33,
                           g4
                        );
                     break;
                  }

                  effect_End5.fra_skill[0]
                     .drawFrame(
                        effect_End5.f / effect_End5.n_frame % effect_End5.fra_skill[0].nFrame,
                        effect_End5.x,
                        effect_End5.y,
                        effect_End5.dir_nguoc,
                        33,
                        g4
                     );
               }

               return;
            case 21:
            case 22:
            case 23:
               byte byteValue2 = 3;
               mGraphics g5 = graphics;
               Effect_End effect_End6 = this;
               if (this.fra_skill[0] != null) {
                  effect_End6.fra_skill[0]
                     .drawFrame(
                        effect_End6.f / effect_End6.n_frame % effect_End6.fra_skill[0].nFrame,
                        effect_End6.x,
                        effect_End6.y,
                        effect_End6.dir_nguoc,
                        3,
                        g5
                     );
               }

               return;
            case 24:
               mGraphics g6 = graphics;
               Effect_End effect_End7 = this;
               if (this.fra_skill != null) {
                  g6.setClip(
                     effect_End7.x, effect_End7.y - effect_End7.h / 2, effect_End7.w, effect_End7.h
                  );
                  FrameImage fra_skillValue = effect_End7.fra_skill[0];
                  FrameImage fra_skillValue2 = effect_End7.fra_skill[1];
                  FrameImage fra_skillValue3 = effect_End7.fra_skill[2];
                  byte nFrameValue = effect_End7.nFrame[effect_End7.f];
                  int max = effect_End7.vMax;
                  int yValue = effect_End7.y;
                  int xValue = effect_End7.x;
                  byte idx = nFrameValue;
                  FrameImage fra_skillValue4 = fra_skillValue3;
                  FrameImage fra_skillValue5 = fra_skillValue2;
                  FrameImage fra_skillValue6 = fra_skillValue;
                  mGraphics g7 = g6;
                  Effect_End effect_End8 = effect_End7;
                  boolean condition = false;
                  int deltaX = max;
                  boolean condition2 = false;
                  if (fra_skillValue6 != null && fra_skillValue4 != null) {
                     condition2 = true;
                     deltaX = max - (fra_skillValue6.frameWidth + fra_skillValue4.frameWidth);
                  }

                  if (deltaX > 0) {
                     int index = deltaX / fra_skillValue5.frameWidth;
                     if (deltaX % fra_skillValue5.frameWidth > 0) {
                        index++;
                     }

                     if (effect_End8.dir == -1) {
                        for (int index2 = 0; index2 < index; index2++) {
                           int xValue2;
                           if (index2 == index - 1) {
                              if (condition2) {
                                 xValue2 = xValue + fra_skillValue4.frameWidth;
                              } else {
                                 xValue2 = xValue + max - fra_skillValue5.frameWidth;
                              }
                           } else if (condition2) {
                              xValue2 = xValue + fra_skillValue4.frameWidth + fra_skillValue5.frameWidth + index2 * fra_skillValue5.frameWidth;
                           } else {
                              xValue2 = xValue + index2 * fra_skillValue5.frameWidth;
                           }

                           fra_skillValue5.drawFrame(idx, xValue2, yValue - fra_skillValue5.frameHeight / 2, 2, 0, g7);
                        }
                     } else {
                        for (int index3 = 0; index3 < index; index3++) {
                           int xValue3;
                           if (index3 == index - 1) {
                              if (condition2) {
                                 xValue3 = xValue + max - (fra_skillValue5.frameWidth + fra_skillValue4.frameWidth);
                              } else {
                                 xValue3 = xValue + max - fra_skillValue5.frameWidth;
                              }
                           } else if (condition2) {
                              xValue3 = xValue + index3 * fra_skillValue5.frameWidth + fra_skillValue6.frameWidth;
                           } else {
                              xValue3 = xValue + index3 * fra_skillValue5.frameWidth;
                           }

                           fra_skillValue5.drawFrame(idx, xValue3, yValue - fra_skillValue5.frameHeight / 2, 0, 0, g7);
                        }
                     }
                  }

                  if (effect_End8.dir == -1) {
                     if (fra_skillValue6 != null) {
                        fra_skillValue6.drawFrame(idx, xValue + max - fra_skillValue6.frameWidth, yValue - fra_skillValue6.frameHeight / 2, 2, 0, g7);
                     }

                     if (fra_skillValue4 != null) {
                        fra_skillValue4.drawFrame(idx, xValue, yValue - fra_skillValue4.frameHeight / 2, 2, 0, g7);
                     }
                  } else {
                     if (fra_skillValue6 != null) {
                        fra_skillValue6.drawFrame(idx, xValue, yValue - fra_skillValue6.frameHeight / 2, 0, 0, g7);
                     }

                     if (fra_skillValue4 != null) {
                        fra_skillValue4.drawFrame(idx, xValue + max - fra_skillValue4.frameWidth - 1, yValue - fra_skillValue4.frameHeight / 2, 0, 0, g7);
                     }
                  }

                  GameCanvas.resetTransGameScr(g6);
                  if (effect_End7.dir == -1 && effect_End7.fra_skill[0] != null) {
                     effect_End7.fra_skill[0]
                        .drawFrame(
                           effect_End7.nFrame[effect_End7.f],
                           effect_End7.x + effect_End7.w - effect_End7.fra_skill[0].frameWidth,
                           effect_End7.y - effect_End7.fra_skill[0].frameHeight / 2 - 1,
                           2,
                           0,
                           g6
                        );
                  }
               }

               return;
            case 25:
               mGraphics g8 = graphics;
               Effect_End effect_End9 = this;

               for (int vecEffEndIndex4 = 0; vecEffEndIndex4 < effect_End9.VecEffEnd.size(); vecEffEndIndex4++) {
                  Point vecEffEnd4;
                  if ((vecEffEnd4 = (Point)effect_End9.VecEffEnd.elementAt(vecEffEndIndex4)).isPaint && !vecEffEnd4.isRemove) {
                     if (!vecEffEnd4.isChange) {
                        boolean condition3 = false;
                        byte byteValue3 = 3;
                        FrameImage fraImgEffValue = vecEffEnd4.fraImgEff;
                        mGraphics g9 = g8;
                        Point vecEffEnd5 = vecEffEnd4;
                        if (fraImgEffValue != null) {
                           int value;
                           if ((value = fraImgEffValue.nFrame / 3) <= 0) {
                              value = 1;
                           }

                           int value2;
                           if (fraImgEffValue.nFrame > 3) {
                              value2 = vecEffEnd5.f / 3 % 2 == 0 ? 0 : 3;
                           } else {
                              value2 = vecEffEnd5.f % value;
                           }

                           int idx2 = value * vecEffEnd5.mImageArrow[vecEffEnd5.frame] + value2;
                           if (fraImgEffValue.nFrame < 3) {
                              idx2 = vecEffEnd5.f / 3 % fraImgEffValue.nFrame;
                           }

                           fraImgEffValue.drawFrame(idx2, vecEffEnd5.x, vecEffEnd5.y, vecEffEnd5.mXoayArrow[vecEffEnd5.frame], 3, g9);
                        }
                     }

                     if (vecEffEnd4.isChange) {
                        vecEffEnd4.fraImgEff_2
                           .drawFrame(
                              vecEffEnd4.f / effect_End9.n_frame % vecEffEnd4.fraImgEff_2.nFrame,
                              vecEffEnd4.x,
                              vecEffEnd4.y,
                              effect_End9.dir_nguoc,
                              3,
                              g8
                           );
                     }
                  }
               }

               return;
            case 26:
               mGraphics g10 = graphics;
               Effect_End effect_End10 = this;
               if (this.fra_skill != null) {
                  if (effect_End10.nFrame != null) {
                     effect_End10.fra_skill[0]
                        .drawFrame(effect_End10.nFrame[effect_End10.frame], effect_End10.x1000, effect_End10.y1000, 0, 33, g10);
                  }

                  if (effect_End10.stt == 1 || effect_End10.stt == 2) {
                     byte anchor = 33;
                     int dyValue = effect_End10.dy;
                     if (effect_End10.nFrame_2[effect_End10.fSpeed] == 0 || effect_End10.nFrame_2[effect_End10.fSpeed] == 1) {
                        anchor = 3;
                        dyValue = 0;
                     }

                     effect_End10.fra_skill[1]
                        .drawFrame(
                           effect_End10.nFrame_2[effect_End10.fSpeed],
                           effect_End10.x,
                           effect_End10.y + dyValue + effect_End10.vy,
                           0,
                           anchor,
                           g10
                        );
                  }
               }

               return;
         }
      } catch (Exception exception) {
         Res.err(exception.toString());
         this.isRemove = true;
      }
   }

   private void set_FireWork() {
      int vecEffEndCount = Res.random(3, 5);
      this.fRemove = 90;

      for (int vecEffEndIndex = 0; vecEffEndIndex < vecEffEndCount; vecEffEndIndex++) {
         Point entry;
         (entry = new Point()).x = this.x + Res.random_Am_0(4);
         entry.y = this.y + Res.random_Am_0(5);
         if (this.typeSub == 0) {
            entry.fRe = Res.random(10);
            byte byteValue = 1;
            if (vecEffEndIndex % 2 == 0) {
               byteValue = -1;
            }

            entry.x = this.x + Res.random(arrInfoEff[5][0] / 2) * byteValue;
            entry.y = this.y - Res.random(arrInfoEff[5][1] / 2);
            entry.fraImgEff = new FrameImage(7);
         }

         this.VecEffEnd.addElement(entry);
      }
   }

   private void set_Skill_Destroy() {
      this.x = this.charUse.cx + 20 * this.charUse.cdir;
      this.fMove = this.timeRemove / 15;
      if (this.target != null) {
         for (int vecEffEndIndex = 0; vecEffEndIndex < 15; vecEffEndIndex++) {
            Point entry;
            (entry = new Point()).fraImgEff = this.fra_skill[0];
            entry.fraImgEff_2 = this.fra_skill[2];
            entry.x = this.x;
            entry.y = this.y;
            if (this.target != null) {
               entry.toX = this.target.x;
               entry.toY = this.target.y;
               if (this.range > 0) {
                  entry.toX = entry.toX + Res.random_Am(0, this.range);
                  entry.toY = entry.toY + Res.random_Am(0, this.range);
               }
            }

            this.vMax = Res.random(9, 12);
            if (vecEffEndIndex == 14) {
               entry.fraImgEff = this.fra_skill[1];
               entry.fraImgEff_2 = this.fra_skill[3];
               entry.toX = this.target.x;
               entry.toY = this.target.y;
               this.vMax = 9;
            }

            entry.isPaint = false;
            entry.isChange = false;
            entry.isRemove = false;
            int max = this.vMax;
            Point vecEffEnd = entry;
            entry.vMax = max;
            max = vecEffEnd.toX - vecEffEnd.x;
            int deltaY = vecEffEnd.toY - vecEffEnd.y;
            int deltaY2 = deltaY;
            int deltaX = max;
            Point point = vecEffEnd;
            int goc = Res.angle(deltaX, deltaY2);
            point.frame = point.setFrameAngle(goc);
            int value;
            if ((value = Res.getDistance(deltaX, deltaY2) / point.vMax) == 0) {
               value = 1;
            }

            int vx = deltaX / value;
            int vyValue = deltaY2 / value;
            if (vx == 0 && deltaX < value) {
               vx = deltaX < 0 ? -1 : 1;
            }

            if (vyValue == 0 && deltaY2 < value) {
               vyValue = deltaY2 < 0 ? -1 : 1;
            }

            if (Res.abs(vx) > Res.abs(deltaX)) {
               vx = deltaX;
            }

            if (Res.abs(vyValue) > Res.abs(deltaY2)) {
               vyValue = deltaY2;
            }

            point.vx = vx;
            point.vy = vyValue;
            this.VecEffEnd.addElement(entry);
         }
      } else {
         this.isRemove = true;
      }
   }

   private void create_Star_Line_In(int vline, int minline, int maxline, int numpoint) {
      if (this.f == -1) {
         this.VecEffEnd.removeAllElements();
      }

      this.colorpaint = new int[4];
      if (maxline <= minline) {
         maxline = minline + 1;
      }

      for (int colorpaintIndex = 0; colorpaintIndex < 4; colorpaintIndex++) {
         if (Res.random(2) == 0) {
            this.colorpaint[colorpaintIndex] = colorStar[this.indexColorStar][Res.random(3)];
         } else {
            this.colorpaint[colorpaintIndex] = colorStar[this.indexColorStar][2];
         }
      }

      for (int vecEffEndIndex = 0; vecEffEndIndex < 4; vecEffEndIndex++) {
         Line entry = new Line();
         int minimum = 5 + vecEffEndIndex * 45;
         int maximum;
         if ((maximum = 45 + vecEffEndIndex * 45 - 5) <= minimum) {
            maximum = minimum + 1;
         }

         int randomValue = Res.random(minline, maxline);
         int randomValue2 = Res.random(vline, vline + 3);
         minimum = Res.random(minimum, maximum);
         maximum = Res.random(13, 23);
         boolean is2Line = Res.random(4) == 0;
         minimum = Res.fixangle(minimum % 360);
         entry.setLine(
            this.x1000 - Res.sin(minimum) * (randomValue + maximum),
            this.y1000 - Res.cos(minimum) * (randomValue + maximum),
            this.x1000 - Res.sin(minimum) * maximum,
            this.y1000 - Res.cos(minimum) * maximum,
            Res.sin(minimum) * randomValue2,
            Res.cos(minimum) * randomValue2,
            is2Line
         );
         this.VecEffEnd.addElement(entry);
         entry = new Line();
         minimum = Res.fixangle((minimum + 180 + Res.random_Am(2, 5)) % 360);
         entry.setLine(
            this.x1000 - Res.sin(minimum) * (randomValue + maximum),
            this.y1000 - Res.cos(minimum) * (randomValue + maximum),
            this.x1000 - Res.sin(minimum) * maximum,
            this.y1000 - Res.cos(minimum) * maximum,
            Res.sin(minimum) * randomValue2,
            Res.cos(minimum) * randomValue2,
            is2Line
         );
         this.VecEffEnd.addElement(entry);
      }
   }

   private void set_End_Rock() {
      this.fraImgEff = new FrameImage(8);
      this.fRemove = Res.random(23, 27);
      int vecEffEndCount = Res.random(1, 3);
      this.toY = this.y - 40;

      for (int vecEffEndIndex = 0; vecEffEndIndex < vecEffEndCount; vecEffEndIndex++) {
         Point entry;
         (entry = new Point()).x = this.x + Res.random_Am(0, 20);
         entry.y = this.y + Res.random_Am_0(7);
         if (this.typeEffect == 10) {
            entry.frame = Res.random(0, this.fraImgEff.nFrame - 2);
         } else if (this.typeEffect == 11) {
            entry.frame = Res.random(2, this.fraImgEff.nFrame);
         } else {
            entry.frame = Res.random(0, this.fraImgEff.nFrame);
         }

         Res.random(2);
         entry.vy = -Res.random(1, 4);
         this.VecEffEnd.addElement(entry);
      }
   }

   private void updListObj_Mafuba(boolean ismafuba) {
      if (this.listObj != null) {
         for (int listObjIndex = 0; listObjIndex < this.listObj.length; listObjIndex++) {
            if (this.listObj[listObjIndex] != null) {
               if (this.listObj[listObjIndex].type == 0) {
                  Mob mob;
                  if ((mob = GameScr.findMobInMap(((int) (this.listObj[listObjIndex].id)))) != null) {
                     mob.isMafuba = ismafuba;
                     mob.isHide = false;
                     mob.xMFB = this.xDotS[listObjIndex];
                     mob.yMFB = this.yDotS[listObjIndex];
                  }
               } else {
                  Char character;
                  if (Char.myCharz().charID == this.listObj[listObjIndex].id) {
                     character = Char.myCharz();
                  } else {
                     character = GameScr.findCharInMap(this.listObj[listObjIndex].id);
                  }

                  if (character != null) {
                     character.isMafuba = ismafuba;
                     character.isHide = false;
                     character.xMFB = this.xDotS[listObjIndex];
                     character.yMFB = this.yDotS[listObjIndex];
                  }
               }
            }
         }
      }
   }

   private void get_Img_Skill() {
      byte byteValue = 0;
      int[] intValues = null;
      int[] intValues2 = null;
      switch (this.typeEffect) {
         case 16:
            byteValue = 26;
            if (this.typeSub == 0) {
               intValues = new int[]{7};
               intValues2 = new int[]{28};
            }

            if (this.typeSub == 1) {
               intValues = new int[]{2};
               intValues2 = new int[]{23};
            }
            break;
         case 17:
            byteValue = 25;
            intValues = new int[]{2};
            intValues2 = new int[]{16};
            break;
         case 18:
            byteValue = 24;
            intValues = new int[1];
            intValues2 = new int[]{9};
            break;
         case 19:
            byteValue = 25;
            intValues = new int[1];
            intValues2 = new int[]{14};
            break;
         case 20:
            byteValue = 26;
            intValues = new int[1];
            intValues2 = new int[]{21};
            break;
         case 21:
            byteValue = 24;
            intValues = new int[]{1};
            intValues2 = new int[]{10};
            break;
         case 22:
            byteValue = 25;
            intValues = new int[]{1};
            intValues2 = new int[]{15};
            break;
         case 23:
            byteValue = 26;
            intValues = new int[]{1};
            intValues2 = new int[]{22};
            break;
         case 24:
            byteValue = 24;
            intValues = new int[]{2, 3, 4};
            intValues2 = new int[]{11, 12, 13};
            break;
         case 25:
            byteValue = 25;
            intValues = new int[]{3, 4, 5, 6};
            intValues2 = new int[]{17, 18, 19, 20};
            break;
         case 26:
            byteValue = 26;
            byte byteValue2 = 0;
            byte byteValue3 = 0;
            if (this.typeSub == 0) {
               byteValue2 = 4;
               byteValue3 = 25;
            } else if (this.typeSub == 1) {
               byteValue2 = 5;
               byteValue3 = 26;
            } else if (this.typeSub == 2) {
               byteValue2 = 6;
               byteValue3 = 27;
            }

            intValues = new int[]{byteValue2, 3};
            intValues2 = new int[]{byteValue3, 24};
      }

      if (intValues != null && intValues2 != null) {
         this.fra_skill = new FrameImage[intValues.length];

         for (int fra_skillIndex = 0; fra_skillIndex < intValues.length; fra_skillIndex++) {
            FrameImage fraImage;
            if ((fraImage = mSystem.getFraImage("Skills_" + byteValue + "_" + this.typePaint + "_" + intValues[fra_skillIndex])) == null) {
               fraImage = new FrameImage(intValues2[fra_skillIndex]);
            }

            if (fraImage != null) {
               this.fra_skill[fra_skillIndex] = fraImage;
            }
         }
      }
   }

   private void pnt_Sub(mGraphics g, int anchor) {
      this.fra_skill[0]
         .drawFrame(
            this.f / this.n_frame % this.fra_skill[0].nFrame,
            this.x,
            this.y,
            this.dir,
            anchor,
            g
         );
   }
}
