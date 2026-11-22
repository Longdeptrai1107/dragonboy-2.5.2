package nro;

import javax.microedition.lcdui.Image;
import main.GameCanvas;

public class Mob implements IMapObject {
   public static MobTemplate[] arrMobTemplate;
   boolean changBody;
   short smallBody;
   public boolean isHintFocus;
   public boolean isFreez = false;
   public int seconds;
   public long last;
   public long cur;
   public int holdEffID;
   public long hp;
   public long maxHp;
   public long hpInjure;
   public int x;
   public int y;
   public int dir = 1;
   private int dirV = 1;
   public int status;
   public int p1;
   public int p2;
   public int p3;
   public int xFirst;
   public int yFirst;
   public int w;
   public int h;
   public int timeStatus;
   public int mobId;
   public boolean isDisable;
   public boolean isDontMove;
   public boolean isFire;
   public boolean isIce;
   public boolean isWind;
   private boolean isDie;
   private MyVector vMobMove = new MyVector("vMobMove");
   public int templateId;
   public Char cFocus;
   public long dame;
   public long dameMp;
   public int sys;
   public byte levelBoss;
   public boolean isBoss;
   public boolean isMobMe;
   public static MyVector lastMob = new MyVector("lastMob");
   public static MyVector newMob = new MyVector("newMob");
   public boolean isMafuba = false;
   public int xMFB;
   public int yMFB;
   public int xSd;
   public int ySd;
   private boolean isOutMap;
   private int wCount;
   private boolean isShadown = true;
   private int tick;
   private int frame;
   public static Image imgHP = mSystem.loadImage("/mainImage/myTexture2dmobHP.png");
   private boolean wy;
   private int wt;
   private int fy;
   private int ty;
   public int typeSuperEff;
   public boolean isBusyAttackSomeOne = true;
   private int[] stand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
   private int[] move = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2};
   private int[] moveFast = new int[]{1, 1, 2, 2, 3, 3, 2};
   private int[] attack1 = new int[]{4, 5, 6};
   private int[] attack2 = new int[]{7, 8, 9};
   private int[] hurt = new int[1];
   public int color = 24;
   public int len = 100;
   public int w_hp_bar = 100;
   public Image imgHPtem;
   private int per = 0;
   public boolean isHide = false;
   public Char injureBy;
   public boolean injureThenDie;
   private Mob mobToAttack;
   private int forceWait;
   public boolean blindEff;
   public boolean sleepEff;
   private int[][] frameArr;
   private boolean isGetFr;

   public final boolean isBigBoss() {
      return this instanceof BachTuoc || this instanceof BigBoss2 || this instanceof BigBoss || this instanceof NewBoss;
   }

   public Mob() {
      byte[] data = new byte[]{-1, 1};
      this.injureThenDie = false;
      this.frameArr = new int[][]{
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1}
      };
      this.isGetFr = true;
   }

   public final void getData() {
      if (arrMobTemplate[this.templateId].data == null) {
         arrMobTemplate[this.templateId].data = new EffectData();
         String text = "/Mob/" + this.templateId;
         if ("".getClass().getResourceAsStream(text) != null) {
            arrMobTemplate[this.templateId].data.readData(((java.lang.String) (text + "/data")));
            arrMobTemplate[this.templateId].data.img = mSystem.loadImage(text + "/img.png");
         } else {
            Service.gI().requestModTemplate(this.templateId);
         }

         if (lastMob.size() > 15) {
            arrMobTemplate[Integer.parseInt((String)lastMob.elementAt(0))].data = null;
            lastMob.removeElementAt(0);
         }

         lastMob.addElement(String.valueOf(this.templateId));
      } else {
         this.w = arrMobTemplate[this.templateId].data.width;
         this.h = arrMobTemplate[this.templateId].data.height;
      }
   }

   public void setBody(short id) {
      this.changBody = true;
      this.smallBody = id;
   }

   public void clearBody() {
      this.changBody = false;
   }

   public Mob(
      int mobIdValue,
      boolean isDisableValue,
      boolean isDontMoveValue,
      boolean isFireValue,
      boolean isIceValue,
      boolean isWindValue,
      int templateIdValue,
      int sysValue,
      long hpValue,
      byte level,
      long maxp,
      short pointx,
      short pointy,
      byte statusValue,
      byte levelBossValue
   ) {
      byte[] data = new byte[]{-1, 1};
      this.injureThenDie = false;
      this.frameArr = new int[][]{
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1},
         {0, 0, 0, 0, 1, 1, 1, 1}
      };
      this.isGetFr = true;
      this.isDontMove = isDontMoveValue;
      this.isIce = isIceValue;
      this.isWind = isWindValue;
      this.sys = sysValue;
      this.mobId = mobIdValue;
      this.templateId = templateIdValue;
      this.hp = hpValue;
      this.xFirst = this.x = pointx;
      this.yFirst = this.y = pointy;
      this.status = statusValue;
      if (templateIdValue != 70) {
         checkData();
         this.getData();
      }

      if (!isExistNewMob(String.valueOf(templateIdValue))) {
         newMob.addElement(String.valueOf(templateIdValue));
      }

      this.maxHp = maxp;
      this.levelBoss = levelBossValue;
      this.updateHp_bar();
      this.isDie = false;
      this.xSd = pointx;
      this.ySd = pointy;
      if (this.isNewModStand()) {
         this.stand = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2};
         this.move = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2};
         this.moveFast = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2};
         this.attack1 = new int[]{3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
         this.attack2 = new int[]{3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
      } else if (this.isNewMod()) {
         this.stand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
         this.move = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3};
         this.moveFast = new int[]{1, 1, 2, 2, 1, 1, 3, 3};
         this.attack1 = new int[]{4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6};
         this.attack2 = new int[]{7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 9};
      } else if (this.isSpecial()) {
         this.stand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
         this.move = new int[]{2, 2, 3, 3, 2, 2, 4, 4, 2, 2, 3, 3, 2, 2, 4, 4};
         this.moveFast = new int[]{2, 2, 3, 3, 2, 2, 4, 4};
         this.attack1 = new int[]{5, 6, 7, 8, 9, 10, 11, 12};
         this.attack2 = new int[]{5, 12, 13, 14};
      } else {
         this.stand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
         this.move = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2};
         this.moveFast = new int[]{1, 1, 2, 2, 3, 3, 2};
         this.attack1 = new int[]{4, 5, 6};
         this.attack2 = new int[]{7, 8, 9};
      }

      Res.outz("MOB ID= " + mobIdValue);
   }

   private static boolean isExistNewMob(String id) {
      for (int newMobIndex = 0; newMobIndex < newMob.size(); newMobIndex++) {
         if (((String)newMob.elementAt(newMobIndex)).equals(id)) {
            return true;
         }
      }

      return false;
   }

   private static void checkData() {
      int index = 0;

      for (int mobTemplateIndex = 0; mobTemplateIndex < arrMobTemplate.length; mobTemplateIndex++) {
         if (arrMobTemplate[mobTemplateIndex].data != null) {
            index++;
         }
      }

      if (index >= 10) {
         for (int mobTemplateIndex2 = 0; mobTemplateIndex2 < arrMobTemplate.length; mobTemplateIndex2++) {
            if (arrMobTemplate[mobTemplateIndex2].data != null && index > 5) {
               arrMobTemplate[mobTemplateIndex2].data = null;
            }
         }
      }
   }

   public void checkFrameTick(int[] array) {
      if (this.tick > array.length - 1) {
         this.tick = 0;
      }

      this.frame = array[this.tick];
      this.tick++;
   }

   public void updateSuperEff() {
      if (this.typeSuperEff == 0 && GameCanvas.gameTick % 25 == 0) {
         ServerEffect.addServerEffect(114, ((nro.Mob) (this)), 1);
      }

      if (this.typeSuperEff == 1 && GameCanvas.gameTick % 4 == 0) {
         ServerEffect.addServerEffect(132, ((nro.Mob) (this)), 1);
      }

      if (this.typeSuperEff == 2 && GameCanvas.gameTick % 7 == 0) {
         ServerEffect.addServerEffect(131, ((nro.Mob) (this)), 1);
      }
   }

   public void update() {
      if (!this.isMafuba) {
         this.GetFrame();
         if (this.blindEff && GameCanvas.gameTick % 5 == 0) {
            ServerEffect.addServerEffect(113, this.x, this.y, 1);
         }

         if (this.sleepEff && GameCanvas.gameTick % 10 == 0) {
            EffecMn.addEff(new Effect(41, this.x, this.y, 3, 1, 1));
         }

         if (!GameCanvas.lowGraphic
            && this.status != 1
            && this.status != 0
            && !GameCanvas.lowGraphic
            && GameCanvas.gameTick % (15 + (this.mobId << 1)) == 0) {
            for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
               Char charInMap;
               if ((charInMap = (Char)GameScr.vCharInMap.elementAt(charInMapIndex)) != null && charInMap.isFlyAndCharge && charInMap.cf == 32) {
                  Char c;
                  (c = new Char()).cx = charInMap.cx;
                  c.cy = charInMap.cy - charInMap.ch;
                  if (charInMap.cgender == 0) {
                     MonsterDart.addMonsterDart(
                        this.x + this.dir * this.w,
                        this.y,
                        this.checkIsBoss(),
                        -100L,
                        -100L,
                        c,
                        25
                     );
                  }
               }
            }

            if (Char.myCharz().isFlyAndCharge && Char.myCharz().cf == 32) {
               Char c2;
               (c2 = new Char()).cx = Char.myCharz().cx;
               c2.cy = Char.myCharz().cy - Char.myCharz().ch;
               if (Char.myCharz().cgender == 0) {
                  MonsterDart.addMonsterDart(
                     this.x + this.dir * this.w,
                     this.y,
                     this.checkIsBoss(),
                     -100L,
                     -100L,
                     c2,
                     25
                  );
               }
            }
         }

         if (this.holdEffID != 0 && GameCanvas.gameTick % 5 == 0) {
            EffecMn.addEff(new Effect(this.holdEffID, this.x, this.y + 24, 3, 5, 1));
         }

         if (this.isFreez) {
            if (GameCanvas.gameTick % 5 == 0) {
               ServerEffect.addServerEffect(113, this.x, this.y, 1);
            }

            long lastValue;
            if ((lastValue = System.currentTimeMillis()) - this.last >= 1000L) {
               this.seconds--;
               this.last = lastValue;
               if (this.seconds < 0) {
                  this.isFreez = false;
                  this.seconds = 0;
               }
            }

            if (this.isTypeNewMod()) {
               this.frame = this.hurt[GameCanvas.gameTick % this.hurt.length];
            } else if (this.isNewModStand()) {
               this.frame = this.attack1[GameCanvas.gameTick % this.attack1.length];
            } else if (this.isNewMod()) {
               if (GameCanvas.gameTick % 20 > 5) {
                  this.frame = 11;
               } else {
                  this.frame = 10;
               }
            } else if (this.isSpecial()) {
               if (GameCanvas.gameTick % 20 > 5) {
                  this.frame = 1;
               } else {
                  this.frame = 15;
               }
            } else if (GameCanvas.gameTick % 20 > 5) {
               this.frame = 11;
            } else {
               this.frame = 10;
            }
         }

         if (this.isUpdate()) {
            if (this.isShadown) {
               Mob mob = this;
               byte size = TileMap.size;
               mob.xSd = mob.x;
               mob.wCount = 0;
               if (mob.ySd > 0 && !TileMap.tileTypeAt(mob.xSd, mob.ySd, 2)) {
                  if (TileMap.tileTypeAt(mob.xSd / size, mob.ySd / size) == 0) {
                     mob.isOutMap = true;
                  } else if (TileMap.tileTypeAt(mob.xSd / size, mob.ySd / size) != 0
                     && !TileMap.tileTypeAt(mob.xSd, mob.ySd, 2)) {
                     mob.xSd = mob.x;
                     mob.ySd = mob.y;
                     mob.isOutMap = false;
                  }

                  while (mob.isOutMap && mob.wCount < 10) {
                     mob.wCount++;
                     mob.ySd += 24;
                     if (TileMap.tileTypeAt(mob.xSd, mob.ySd, 2)) {
                        if (mob.ySd % 24 != 0) {
                           mob.ySd = mob.ySd - mob.ySd % 24;
                        }
                        break;
                     }
                  }
               }
            }

            if (this.vMobMove != null || arrMobTemplate[this.templateId].rangeMove == 0) {
               if (this.status != 3 && this.isBusyAttackSomeOne) {
                  if (this.cFocus != null) {
                     this.cFocus.doInjure(this.dame, this.dameMp, false, true);
                  } else if (this.mobToAttack != null) {
                     this.mobToAttack.setInjure();
                  }

                  this.isBusyAttackSomeOne = false;
               }

               if (this.levelBoss > 0) {
                  this.updateSuperEff();
               }

               switch (this.status) {
                  case 1:
                     this.isDontMove = false;
                     this.isIce = false;
                     this.isWind = false;
                     this.y = this.y + this.p1;
                     if (GameCanvas.gameTick % 2 == 0) {
                        if (this.p2 > 1) {
                           this.p2--;
                        } else if (this.p2 < -1) {
                           this.p2++;
                        }
                     }

                     this.x = this.x + this.p2;
                     if (this.isTypeNewMod()) {
                        this.frame = this.hurt[GameCanvas.gameTick % this.hurt.length];
                     } else if (this.isNewModStand()) {
                        this.frame = this.attack1[GameCanvas.gameTick % this.attack1.length];
                     } else if (!this.isNewMod() && this.isSpecial()) {
                        this.frame = 15;
                     } else {
                        this.frame = 11;
                     }

                     if (this.isDie) {
                        this.isDie = false;
                        if (this.isMobMe) {
                           for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
                              if (((Mob)GameScr.vMob.elementAt(mobIndex)).mobId == this.mobId) {
                                 GameScr.vMob.removeElementAt(mobIndex);
                              }
                           }
                        }

                        this.p1 = 0;
                        this.p2 = 0;
                        this.x = this.y = 0;
                        this.hp = this.getTemplate().hp;
                        this.status = 0;
                        this.timeStatus = 0;
                        return;
                     }

                     if ((TileMap.tileTypeAtPixel(this.x, this.y) & 2) == 2) {
                        this.p1 = this.p1 > 4 ? -4 : -this.p1;
                        if (this.p3 == 0) {
                           this.p3 = 16;
                        }
                     } else {
                        this.p1++;
                     }

                     if (this.p3 > 0) {
                        this.p3--;
                        if (this.p3 == 0) {
                           this.isDie = true;
                           return;
                        }
                     }
                     break;
                  case 2:
                     if (this.holdEffID != 0) {
                        return;
                     }

                     if (this.isFreez) {
                        return;
                     }

                     if (this.blindEff) {
                        return;
                     }

                     if (this.sleepEff) {
                        return;
                     }

                     this.timeStatus = 0;
                     Mob mob2 = this;
                     this.checkFrameTick(mob2.stand);
                     switch (arrMobTemplate[mob2.templateId].type) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                           mob2.p1++;
                           if (mob2.p1 > 10 + mob2.mobId % 10
                              && (
                                 mob2.cFocus == null
                                    || Res.abs(mob2.cFocus.cx - mob2.x) > 80
                              )
                              && (
                                 mob2.mobToAttack == null
                                    || Res.abs(mob2.mobToAttack.x - mob2.x) > 80
                              )) {
                              mob2.status = 5;
                           }
                           break;
                        case 4:
                        case 5:
                           mob2.p1++;
                           if (mob2.p1 > mob2.mobId % 3
                              && (
                                 mob2.cFocus == null
                                    || Res.abs(mob2.cFocus.cx - mob2.x) > 80
                              )
                              && (
                                 mob2.mobToAttack == null
                                    || Res.abs(mob2.mobToAttack.x - mob2.x) > 80
                              )) {
                              mob2.status = 5;
                           }
                     }

                     if (mob2.cFocus != null && GameCanvas.gameTick % (10 + mob2.p1 % 20) == 0) {
                        if (mob2.cFocus.cx > mob2.x) {
                           mob2.dir = 1;
                        } else {
                           mob2.dir = -1;
                        }
                     } else if (mob2.mobToAttack != null && GameCanvas.gameTick % (10 + mob2.p1 % 20) == 0) {
                        if (mob2.mobToAttack.x > mob2.x) {
                           mob2.dir = 1;
                        } else {
                           mob2.dir = -1;
                        }
                     }

                     if (mob2.forceWait > 0) {
                        mob2.forceWait--;
                        mob2.status = 2;
                     }

                     return;
                  case 3:
                     if (this.holdEffID != 0) {
                        return;
                     }

                     if (this.blindEff) {
                        return;
                     }

                     if (this.sleepEff) {
                        return;
                     }

                     if (this.isFreez) {
                        return;
                     }

                     this.updateMobAttack();
                     return;
                  case 4:
                     if (this.holdEffID != 0) {
                        return;
                     }

                     if (this.blindEff) {
                        return;
                     }

                     if (this.sleepEff) {
                        return;
                     }

                     if (this.isFreez) {
                        return;
                     }

                     this.timeStatus = 0;
                     this.p1++;
                     if (this.p1 > 40 + this.mobId % 5) {
                        this.y -= 2;
                        this.status = 5;
                        this.p1 = 0;
                        return;
                     }
                     break;
                  case 5:
                     if (this.holdEffID != 0) {
                        return;
                     }

                     if (this.blindEff) {
                        return;
                     }

                     if (this.sleepEff) {
                        return;
                     }

                     if (this.isFreez) {
                        if (arrMobTemplate[this.templateId].type == 4) {
                           this.ty++;
                           this.wt++;
                           this.fy = this.fy + (!this.wy ? 1 : -1);
                           if (this.wt == 10) {
                              this.wt = 0;
                              this.wy = !this.wy;
                           }
                        }

                        return;
                     }

                     this.timeStatus = 0;
                     this.updateMobWalk();
                     return;
                  case 6:
                     this.timeStatus = 0;
                     this.p1++;
                     this.y = this.y + this.p1;
                     if (this.y >= this.yFirst) {
                        this.y = this.yFirst;
                        this.p1 = 0;
                        this.status = 5;
                        return;
                     }
                     break;
                  case 7:
                     Mob mob3 = this;
                     if (!this.isBusyAttackSomeOne && GameCanvas.gameTick % 4 == 0) {
                        if (mob3.isTypeNewMod()) {
                           mob3.frame = mob3.hurt[GameCanvas.gameTick % mob3.hurt.length];
                        } else if (mob3.isNewModStand()) {
                           mob3.frame = mob3.attack1[GameCanvas.gameTick % mob3.attack1.length];
                        } else if (mob3.isNewMod()) {
                           if (mob3.frame != 10) {
                              mob3.frame = 10;
                           } else {
                              mob3.frame = 11;
                           }
                        } else if (mob3.isSpecial()) {
                           if (mob3.frame != 1) {
                              mob3.frame = 1;
                           } else {
                              mob3.frame = 15;
                           }
                        } else if (mob3.frame != 10) {
                           mob3.frame = 10;
                        } else {
                           mob3.frame = 11;
                        }
                     }

                     mob3.timeStatus--;
                     if (mob3.timeStatus <= 0
                        && (
                           mob3.isTypeNewMod()
                              || mob3.isNewModStand()
                              || mob3.isNewMod() && mob3.frame == 11
                              || mob3.isSpecial() && mob3.frame == 15
                              || mob3.templateId < 58 && mob3.frame == 11
                        )) {
                        if ((mob3.injureBy == null || !mob3.injureThenDie) && mob3.hp != 0L) {
                           mob3.status = 5;
                           if (mob3.injureBy != null) {
                              mob3.dir = -mob3.injureBy.cdir;
                              if (Res.abs(mob3.x - mob3.injureBy.cx) < 24) {
                                 mob3.status = 2;
                              }
                           }

                           mob3.p1 = mob3.p2 = mob3.p3 = 0;
                           mob3.timeStatus = 0;
                        } else {
                           mob3.status = 1;
                           mob3.p2 = mob3.injureBy.cdir << 1;
                           mob3.p1 = -3;
                           mob3.p3 = 0;
                        }

                        mob3.injureBy = null;
                        return;
                     }

                     if (arrMobTemplate[mob3.templateId].type != 0 && mob3.injureBy != null) {
                        int xValue = -mob3.injureBy.cdir << 1;
                        if (mob3.x > mob3.xFirst - arrMobTemplate[mob3.templateId].rangeMove
                           && mob3.x < mob3.xFirst + arrMobTemplate[mob3.templateId].rangeMove) {
                           mob3.x -= xValue;
                        }
                     }
               }
            }
         }
      }
   }

   public void setInjure() {
      if (this.hp > 0L && this.status != 3 && this.status != 7) {
         this.timeStatus = 4;
         this.status = 7;
         if (this.getTemplate().type != 0 && Res.abs(this.x - this.xFirst) < 30) {
            this.x = this.x - 10 * this.dir;
         }
      }
   }

   public static BigBoss getBigBoss() {
      for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
         Mob mob;
         if ((mob = (Mob)GameScr.vMob.elementAt(mobIndex)) instanceof BigBoss) {
            return (BigBoss)mob;
         }
      }

      return null;
   }

   public static BigBoss2 getBigBoss2() {
      for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
         Mob mob;
         if ((mob = (Mob)GameScr.vMob.elementAt(mobIndex)) instanceof BigBoss2) {
            return (BigBoss2)mob;
         }
      }

      return null;
   }

   public static BachTuoc getBachTuoc() {
      for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
         Mob mob;
         if ((mob = (Mob)GameScr.vMob.elementAt(mobIndex)) instanceof BachTuoc) {
            return (BachTuoc)mob;
         }
      }

      return null;
   }

   public static NewBoss getNewBoss(byte idBoss) {
      Mob mob;
      return (mob = (Mob)GameScr.vMob.elementAt(idBoss)) instanceof NewBoss ? (NewBoss)mob : null;
   }

   public void setAttack(Char cFocus) {
      this.isBusyAttackSomeOne = true;
      this.mobToAttack = null;
      this.cFocus = cFocus;
      this.p1 = 0;
      this.p2 = 0;
      this.status = 3;
      this.tick = 0;
      this.dir = cFocus.cx > this.x ? 1 : -1;
      int cx = cFocus.cx;
      int cy = cFocus.cy;
      if (Res.abs(cx - this.x) < this.w << 1
         && Res.abs(cy - this.y) < this.h << 1) {
         this.p3 = 0;
      } else {
         this.p3 = 1;
      }
   }

   boolean isSpecial() {
      return this.templateId >= 58 && this.templateId <= 65 || this.templateId == 67 || this.templateId == 68;
   }

   private boolean isNewModStand() {
      return this.templateId == 76;
   }

   private boolean isNewMod() {
      return this.templateId >= 73 && !this.isNewModStand();
   }

   public void updateMobAttack() {
      int[] array = this.p3 == 0 ? this.attack1 : this.attack2;
      if (this.tick < array.length) {
         this.checkFrameTick(array);
         if (this.x >= GameScr.cmx
            && this.x <= GameScr.cmx + GameCanvas.w
            && this.p3 == 0
            && GameCanvas.gameTick % 2 == 0) {
            SoundMn.gI();
            SoundMn.charPunch();
         }
      }

      if (this.p1 == 0) {
         int value = this.cFocus != null ? this.cFocus.cx : this.mobToAttack.x;
         int value2 = this.cFocus != null ? this.cFocus.cy : this.mobToAttack.y;
         if (!this.isNewMod()) {
            if (this.x > this.xFirst + arrMobTemplate[this.templateId].rangeMove) {
               this.p1 = 1;
            }

            if (this.x < this.xFirst - arrMobTemplate[this.templateId].rangeMove) {
               this.p1 = 1;
            }
         }

         if ((arrMobTemplate[this.templateId].type == 4 || arrMobTemplate[this.templateId].type == 5)
            && !this.isDontMove) {
            this.y = this.y + (value2 - this.y) / 20;
         }

         this.p2++;
         if (this.p2 > array.length - 1 || this.p1 == 1) {
            this.p1 = 1;
            if (this.p3 == 0) {
               if (this.cFocus != null) {
                  this.cFocus.doInjure(this.dame, this.dameMp, false, true);
               } else {
                  this.mobToAttack.setInjure();
               }

               this.isBusyAttackSomeOne = false;
            } else {
               if (this.cFocus != null) {
                  MonsterDart.addMonsterDart(
                     this.x + this.dir * this.w,
                     this.y,
                     this.checkIsBoss(),
                     this.dame,
                     this.dameMp,
                     this.cFocus,
                     this.getTemplate().dartType
                  );
               } else {
                  Char c;
                  (c = new Char()).cx = this.mobToAttack.x;
                  c.cy = this.mobToAttack.y;
                  c.charID = -100;
                  MonsterDart.addMonsterDart(
                     this.x + this.dir * this.w,
                     this.y,
                     this.checkIsBoss(),
                     this.dame,
                     this.dameMp,
                     c,
                     this.getTemplate().dartType
                  );
               }

               this.isBusyAttackSomeOne = false;
            }
         }

         this.dir = this.x < value ? 1 : -1;
      } else if (this.p1 == 1) {
         if (arrMobTemplate[this.templateId].type != 0 && !this.isDontMove) {
            ;
         }

         if (this.tick == array.length) {
            this.status = 2;
            this.p1 = 0;
            this.p2 = 0;
            this.tick = 0;
         }
      }

      if (this.tick == 5 && this.cFocus != null && this.cFocus.charID == Char.myCharz().charID) {
         if (this.templateId == 88 && this.p3 != 0) {
            GameScr.shock_scr = 2;
         }

         if (this.templateId == 89) {
            GameScr.shock_scr = 2;
         }
      }
   }

   public void updateMobWalk() {
      try {
         if (this.injureThenDie) {
            this.status = 1;
            this.p2 = this.injureBy.cdir << 3;
            this.p1 = -5;
            this.p3 = 0;
         }

         if (this.isIce) {
            return;
         }

         if (this.isDontMove || this.isWind) {
            this.checkFrameTick(this.stand);
            return;
         }

         switch (arrMobTemplate[this.templateId].type) {
            case 0:
               if (!this.isNewModStand()) {
                  this.frame = 0;
                  return;
               }

               this.frame = this.stand[GameCanvas.gameTick % this.stand.length];
               break;
            case 1:
            case 2:
            case 3:
               byte speed = arrMobTemplate[this.templateId].speed;
               if (arrMobTemplate[this.templateId].speed == 1) {
                  if (GameCanvas.gameTick % 2 == 1) {
                     return;
                  }
               } else if (speed > 2) {
                  speed = (byte)(speed + this.mobId % 2);
               } else if (GameCanvas.gameTick % 2 == 1) {
                  speed--;
               }

               this.x = this.x + speed * this.dir;
               if (this.x > this.xFirst + arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = -1;
               } else if (this.x < this.xFirst - arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = 1;
               }

               if (Res.abs(this.x - Char.myCharz().cx) < 40
                  && Res.abs(this.x - this.xFirst) < arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = this.x > Char.myCharz().cx ? -1 : 1;
                  if (Res.abs(this.x - Char.myCharz().cx) < 20) {
                     this.x = this.x - this.dir * 10;
                  }

                  this.status = 2;
                  this.forceWait = 20;
               }

               this.checkFrameTick(this.w > 30 ? this.move : this.moveFast);
               return;
            case 4:
               byte speed2 = arrMobTemplate[this.templateId].speed;
               speed2 = (byte)(arrMobTemplate[this.templateId].speed + this.mobId % 2);
               this.x = this.x + speed2 * this.dir;
               if (GameCanvas.gameTick % 10 > 2) {
                  this.y = this.y + speed2 * this.dirV;
               }

               if (this.x > this.xFirst + arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = -1;
                  this.status = 2;
                  this.forceWait = GameCanvas.gameTick % 20 + 20;
                  this.p1 = 0;
               } else if (this.x < this.xFirst - arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = 1;
                  this.status = 2;
                  this.forceWait = GameCanvas.gameTick % 20 + 20;
                  this.p1 = 0;
               }

               if (this.y > this.yFirst + 24) {
                  this.dirV = -1;
               } else if (this.y < this.yFirst - (20 + GameCanvas.gameTick % 10)) {
                  this.dirV = 1;
               }

               this.checkFrameTick(this.move);
               return;
            case 5:
               byte speed3 = arrMobTemplate[this.templateId].speed;
               speed3 = (byte)(arrMobTemplate[this.templateId].speed + this.mobId % 2);
               this.x = this.x + speed3 * this.dir;
               byte byteValue = (byte)(speed3 + (GameCanvas.gameTick + this.mobId) % 2);
               if (GameCanvas.gameTick % 10 > 2) {
                  this.y = this.y + byteValue * this.dirV;
               }

               if (this.x > this.xFirst + arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = -1;
                  this.status = 2;
                  this.forceWait = GameCanvas.gameTick % 20 + 20;
                  this.p1 = 0;
               } else if (this.x < this.xFirst - arrMobTemplate[this.templateId].rangeMove) {
                  this.dir = 1;
                  this.status = 2;
                  this.forceWait = GameCanvas.gameTick % 20 + 20;
                  this.p1 = 0;
               }

               if (this.y > this.yFirst + 24) {
                  this.dirV = -1;
               } else if (this.y < this.yFirst - (20 + GameCanvas.gameTick % 10)) {
                  this.dirV = 1;
               }

               if (!TileMap.tileTypeAt(this.x, this.y, 2)) {
                  break;
               }

               if (GameCanvas.gameTick % 10 > 5) {
                  this.y = TileMap.tileYofPixel(this.y);
                  this.status = 4;
                  this.p1 = 0;
                  this.dirV = -1;
                  return;
               }

               this.dirV = -1;
            default:
               return;
         }
      } catch (Exception ignoredException) {
      }
   }

   public final MobTemplate getTemplate() {
      return arrMobTemplate[this.templateId];
   }

   public boolean isPaint() {
      if (this.x < GameScr.cmx) {
         return false;
      } else if (this.x > GameScr.cmx + GameScr.gW) {
         return false;
      } else if (this.y < GameScr.cmy) {
         return false;
      } else if (this.y > GameScr.cmy + GameScr.gH + 30) {
         return false;
      } else if (arrMobTemplate[this.templateId] == null) {
         return false;
      } else if (arrMobTemplate[this.templateId].data == null) {
         return false;
      } else {
         return arrMobTemplate[this.templateId].data.img == null ? false : this.status != 0;
      }
   }

   public boolean isUpdate() {
      if (arrMobTemplate[this.templateId] == null) {
         return false;
      } else {
         return arrMobTemplate[this.templateId].data == null ? false : this.status != 0;
      }
   }

   public boolean checkIsBoss() {
      return this.isBoss || this.levelBoss > 0;
   }

   public final void updateHp_bar() {
      this.len = (int)(this.hp * 100L / this.maxHp);
      if (this.len >= 100) {
         this.w_hp_bar = this.len;
      }

      this.per = 0;
      if (this.len < 30) {
         this.imgHPtem = GameScr.imgHP_tm_do;
      } else if (this.len < 60) {
         this.imgHPtem = GameScr.imgHP_tm_vang;
      } else {
         this.imgHPtem = GameScr.imgHP_tm_xanh;
      }
   }

   public void paint(mGraphics g) {
      if (!this.isHide) {
         if (this.isMafuba) {
            if (!this.changBody) {
               arrMobTemplate[this.templateId]
                  .data
                  .paintFrame(g, this.frame, this.xMFB, this.yMFB, this.dir == 1 ? 0 : 1, 2);
            } else {
               SmallImage.drawSmallImage(g, this.smallBody, this.xMFB, this.yMFB, this.dir == 1 ? 0 : 2, 33);
            }
         } else {
            if (this.isShadown && this.status != 0) {
               mGraphics g2 = g;
               Mob mob = this;
               byte width = TileMap.size;
               if (TileMap.tileTypeAt(mob.xSd + width / 2, mob.ySd + 1, 4)) {
                  g2.setClip(mob.xSd / width * width, (mob.ySd - 30) / width * width, width, 100);
               } else if (TileMap.tileTypeAt((mob.xSd - width / 2) / width, (mob.ySd + 1) / width) == 0) {
                  g2.setClip(mob.xSd / width * width, (mob.ySd - 30) / width * width, 100, 100);
               } else if (TileMap.tileTypeAt((mob.xSd + width / 2) / width, (mob.ySd + 1) / width) == 0) {
                  g2.setClip(mob.xSd / width * width, (mob.ySd - 30) / width * width, width, 100);
               } else if (TileMap.tileTypeAt(mob.xSd - width / 2, mob.ySd + 1, 8)) {
                  g2.setClip(mob.xSd / 24 * width, (mob.ySd - 30) / width * width, width, 100);
               }

               g2.drawImage(TileMap.bong, ((int) (mob.xSd)), ((int) (mob.ySd)), 3);
               g2.setClip(
                  GameScr.cmx,
                  GameScr.cmy - GameCanvas.transY,
                  GameScr.gW,
                  GameScr.gH + 2 * GameCanvas.transY
               );
            }

            if (this.isPaint()) {
               if (this.status != 1 || this.p3 <= 0 || GameCanvas.gameTick % 3 != 0) {
                  g.translate(0, GameCanvas.transY);
                  if (!this.changBody) {
                     arrMobTemplate[this.templateId]
                        .data
                        .paintFrame(
                           g,
                           this.frame,
                           this.x,
                           this.y + this.fy,
                           this.dir == 1 ? 0 : 1,
                           2
                        );
                  } else {
                     SmallImage.drawSmallImage(
                        g,
                        this.smallBody,
                        this.x,
                        this.y + this.fy - 9,
                        this.dir == 1 ? 0 : 2,
                        33
                     );
                  }

                  g.translate(0, -GameCanvas.transY);
                  if (Char.myCharz().mobFocus != null
                     && Char.myCharz().mobFocus.equals(this)
                     && this.status != 1
                     && this.hp > 0L
                     && this.imgHPtem != null) {
                     int imageWidth = mGraphics.getImageWidth(this.imgHPtem);
                     int height = mGraphics.getImageHeight(this.imgHPtem);
                     int width2;
                     int width3 = width2 = imageWidth * this.len / 100;
                     if (this.w_hp_bar >= this.len) {
                        width3 = imageWidth
                           * (
                              this.w_hp_bar = this.w_hp_bar
                                 - (GameCanvas.gameTick % 6 > 3 ? this.per++ : this.per)
                           )
                           / 100;
                        if (this.w_hp_bar <= 0) {
                           this.w_hp_bar = 0;
                        }

                        if (this.w_hp_bar < this.len) {
                           this.w_hp_bar = this.len;
                        }

                        if (this.per >= 3) {
                           this.per = 3;
                        }
                     }

                     g.drawImage(
                        GameScr.imgHP_tm_xam, ((int) (this.x - (imageWidth >> 1))), ((int) (this.y - this.h - 5)), 20
                     );
                     g.setColor(16777215);
                     g.fillRect(this.x - (imageWidth >> 1), this.y - this.h - 5, width3, 2);
                     g.drawRegion(
                        this.imgHPtem, 0, 0, width2, height, 0, this.x - (imageWidth >> 1), this.y - this.h - 5, 20
                     );
                  }
               }
            }
         }
      }
   }

   public void startDie() {
      this.hp = 0L;
      this.injureThenDie = true;
      this.hp = 0L;
      this.status = 1;
      this.p1 = -3;
      this.p2 = -this.dir;
      this.p3 = 0;
   }

   public void attackOtherMob(Mob mobToAttack) {
      this.mobToAttack = mobToAttack;
      this.isBusyAttackSomeOne = true;
      this.cFocus = null;
      this.p1 = 0;
      this.p2 = 0;
      this.status = 3;
      this.tick = 0;
      this.dir = mobToAttack.x > this.x ? 1 : -1;
      int xValue = mobToAttack.x;
      int yValue = mobToAttack.y;
      if (Res.abs(xValue - this.x) < this.w << 1
         && Res.abs(yValue - this.y) < this.h << 1) {
         if (this.x < xValue) {
            this.x = xValue - this.w;
         } else {
            this.x = xValue + this.w;
         }

         this.p3 = 0;
      } else {
         this.p3 = 1;
      }
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getH() {
      return this.h;
   }

   public int getW() {
      return this.w;
   }

   public void stopMoving() {
      if (this.status == 5) {
         this.status = 2;
         this.p1 = this.p2 = this.p3 = 0;
         this.forceWait = 50;
      }
   }

   public boolean isInvisible() {
      return this.status == 0 || this.status == 1;
   }

   public void removeHoldEff() {
      if (this.holdEffID != 0) {
         this.holdEffID = 0;
      }
   }

   public void removeBlindEff() {
      this.blindEff = false;
   }

   public void removeSleepEff() {
      this.sleepEff = false;
   }

   public void GetFrame() {
      if (this.isGetFr && this.isTypeNewMod() && arrMobTemplate[this.templateId].data != null) {
         this.frameArr = (int[][])Controller.frameHT_NEWBOSS.get(String.valueOf(this.templateId));
         this.stand = this.frameArr[0];
         this.move = this.frameArr[1];
         this.moveFast = this.frameArr[2];
         this.attack1 = this.frameArr[3];
         this.attack2 = this.frameArr[4];
         this.hurt = this.frameArr[5];
         this.isGetFr = false;
      }
   }

   private boolean isTypeNewMod() {
      return arrMobTemplate[this.templateId].data != null && arrMobTemplate[this.templateId].data.typeData == 2;
   }
}
