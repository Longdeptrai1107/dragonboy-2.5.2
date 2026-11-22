package nro;

import main.GameCanvas;

public class Npc extends Char {
   public NpcTemplate template;
   public static NpcTemplate[] arrNpcTemplate;
   public boolean isHide;
   private int duaHauIndex;
   private int dyEff;
   public static boolean mabuEff;
   private static int tMabuEff;
   public int[] duahau;
   private int seconds;
   private long last;
   private long cur;
   public int idItem;

   static {
      int[] intValues = new int[]{1, -1, 1, -1};
      intValues = new int[]{1, -1, -1, 1};
   }

   public Npc(int npcId, int status, int cxValue, int cyValue, int templateId, int avatarValue) {
      super.isShadown = true;
      super.avatar = avatarValue;
      Res.outz("npc avatar= " + super.avatar);
      super.cx = cxValue;
      super.cy = cyValue;
      super.xSd = cxValue;
      super.ySd = cyValue;
      super.statusMe = status;
      if (npcId != -1) {
         this.template = arrNpcTemplate[templateId];
      }

      if (templateId == 23 || templateId == 42) {
         super.ch = 45;
      }

      if (templateId == 51) {
         super.isShadown = false;
         this.duaHauIndex = status;
         Res.outz("duaHau = " + status);
      }

      if (this.template != null) {
         if (this.template.name == null) {
            this.template.name = "";
         }

         this.template.name = Res.changeString(this.template.name);
      }
   }

   public final void setStatus(byte s, int sc) {
      this.duaHauIndex = s;
      this.last = this.cur = System.currentTimeMillis();
      this.seconds = sc;
      Res.outz("in ra second= " + this.seconds);
   }

   public static void clearEffTask() {
      for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
         Npc npc;
         (npc = (Npc)GameScr.vNpc.elementAt(npcIndex)).effTask = null;
         npc.indexEffTask = -1;
      }
   }

   public void update() {
      if (this.template.npcTemplateId == 51) {
         this.cur = System.currentTimeMillis();
         if (this.cur - this.last >= 1000L) {
            this.seconds--;
            this.last = this.cur;
            if (this.seconds < 0) {
               this.seconds = 0;
            }
         }
      }

      if (super.isShadown) {
         this.updateShadown();
      }

      label66:
      if (super.effTask == null) {
         byte[] data = new byte[]{-1, 9, 9, 10, 10, 11, 11};
         if (Char.myCharz().ctaskId < 9
            || Char.myCharz().ctaskId > 10
            || Char.myCharz().nClass.classId <= 0
            || data[Char.myCharz().nClass.classId] != this.template.npcTemplateId) {
            GameScr.gI();
            byte taskNpcId = GameScr.getTaskNpcId();
            if (Char.myCharz().taskMaint != null || taskNpcId != this.template.npcTemplateId) {
               if (Char.myCharz().taskMaint == null || taskNpcId != this.template.npcTemplateId) {
                  break label66;
               }

               int value = Char.myCharz().taskMaint.index + 1;
               int subNameCount = Char.myCharz().taskMaint.subNames.length;
               super.effTask = GameScr.efs[98];
            }
         } else if (Char.myCharz().taskMaint == null) {
            super.effTask = GameScr.efs[57];
         } else {
            if (Char.myCharz().taskMaint == null
               || Char.myCharz().taskMaint.index + 1 != Char.myCharz().taskMaint.subNames.length) {
               break label66;
            }

            super.effTask = GameScr.efs[62];
         }

         super.indexEffTask = 0;
      }

      super.update();
      if (TileMap.mapID == 51) {
         if (super.cx > Char.myCharz().cx) {
            super.cdir = -1;
         } else {
            super.cdir = 1;
         }

         if (this.template.npcTemplateId % 2 == 0) {
            if (super.cf == 1) {
               super.cf = 0;
               return;
            }

            super.cf = 1;
         }
      }
   }

   public void paint(mGraphics g) {
      if (!Char.isLoadingMap) {
         if (!this.isHide) {
            if (this.isPaint()) {
               if (super.statusMe != 15) {
                  if (super.cTypePk != 0) {
                     super.paint(g);
                  } else if (this.template != null) {
                     if (this.template.npcTemplateId != 4
                        && this.template.npcTemplateId != 51
                        && this.template.npcTemplateId != 50) {
                        g.drawImage(TileMap.bong, ((int) (super.cx)), ((int) (super.cy)), 3);
                     }

                     if (this.template.npcTemplateId == 3) {
                        SmallImage.drawSmallImage(g, 265, super.cx, super.cy, 0, 33);
                        if (Char.myCharz().npcFocus != null
                           && Char.myCharz().npcFocus.equals(this)
                           && ChatPopup.currChatPopup == null) {
                           g.drawRegion(
                              Mob.imgHP, 0, 0, 9, 6, 0, super.cx, super.cy - super.ch + 4, 33
                           );
                        }

                        this.dyEff = 60;
                     } else if (this.template.npcTemplateId != 4) {
                        if (this.template.npcTemplateId != 50 && this.template.npcTemplateId != 51) {
                           if (this.template.npcTemplateId == 6) {
                              SmallImage.drawSmallImage(g, 545, super.cx, super.cy + 5, 0, 33);
                              if (Char.myCharz().npcFocus != null
                                 && Char.myCharz().npcFocus.equals(this)
                                 && ChatPopup.currChatPopup == null) {
                                 g.drawRegion(
                                    Mob.imgHP,
                                    0,
                                    0,
                                    9,
                                    6,
                                    0,
                                    super.cx,
                                    super.cy - super.ch - 9,
                                    33
                                 );
                              }

                              mFont.tahoma_7b_white
                                 .drawString(
                                    g,
                                    String.valueOf(TileMap.zoneID),
                                    super.cx,
                                    super.cy - super.ch + 19 - mFont.tahoma_7.getHeight(),
                                    2
                                 );
                           } else {
                              int partIndex = this.template.headId;
                              int partIndex2 = this.template.legId;
                              int partIndex3 = this.template.bodyId;
                              Part part = GameScr.parts[partIndex];
                              Part part2 = GameScr.parts[partIndex2];
                              Part part3 = GameScr.parts[partIndex3];
                              if (super.cdir == 1) {
                                 SmallImage.drawSmallImage(
                                    g,
                                    part.pi[Char.CharInfo[super.cf][0][0]].id,
                                    super.cx
                                       + Char.CharInfo[super.cf][0][1]
                                       + part.pi[Char.CharInfo[super.cf][0][0]].dx,
                                    super.cy
                                       - Char.CharInfo[super.cf][0][2]
                                       + part.pi[Char.CharInfo[super.cf][0][0]].dy,
                                    0,
                                    0
                                 );
                                 SmallImage.drawSmallImage(
                                    g,
                                    part2.pi[Char.CharInfo[super.cf][1][0]].id,
                                    super.cx
                                       + Char.CharInfo[super.cf][1][1]
                                       + part2.pi[Char.CharInfo[super.cf][1][0]].dx,
                                    super.cy
                                       - Char.CharInfo[super.cf][1][2]
                                       + part2.pi[Char.CharInfo[super.cf][1][0]].dy,
                                    0,
                                    0
                                 );
                                 SmallImage.drawSmallImage(
                                    g,
                                    part3.pi[Char.CharInfo[super.cf][2][0]].id,
                                    super.cx
                                       + Char.CharInfo[super.cf][2][1]
                                       + part3.pi[Char.CharInfo[super.cf][2][0]].dx,
                                    super.cy
                                       - Char.CharInfo[super.cf][2][2]
                                       + part3.pi[Char.CharInfo[super.cf][2][0]].dy,
                                    0,
                                    0
                                 );
                              } else {
                                 SmallImage.drawSmallImage(
                                    g,
                                    part.pi[Char.CharInfo[super.cf][0][0]].id,
                                    super.cx
                                       - Char.CharInfo[super.cf][0][1]
                                       - part.pi[Char.CharInfo[super.cf][0][0]].dx,
                                    super.cy
                                       - Char.CharInfo[super.cf][0][2]
                                       + part.pi[Char.CharInfo[super.cf][0][0]].dy,
                                    2,
                                    24
                                 );
                                 SmallImage.drawSmallImage(
                                    g,
                                    part2.pi[Char.CharInfo[super.cf][1][0]].id,
                                    super.cx
                                       - Char.CharInfo[super.cf][1][1]
                                       - part2.pi[Char.CharInfo[super.cf][1][0]].dx,
                                    super.cy
                                       - Char.CharInfo[super.cf][1][2]
                                       + part2.pi[Char.CharInfo[super.cf][1][0]].dy,
                                    2,
                                    24
                                 );
                                 SmallImage.drawSmallImage(
                                    g,
                                    part3.pi[Char.CharInfo[super.cf][2][0]].id,
                                    super.cx
                                       - Char.CharInfo[super.cf][2][1]
                                       - part3.pi[Char.CharInfo[super.cf][2][0]].dx,
                                    super.cy
                                       - Char.CharInfo[super.cf][2][2]
                                       + part3.pi[Char.CharInfo[super.cf][2][0]].dy,
                                    2,
                                    24
                                 );
                              }

                              if (TileMap.mapID != 51) {
                                 byte byteValue = 15;
                                 if (this.template.npcTemplateId == 47) {
                                    byteValue = 47;
                                 }

                                 if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
                                    if (ChatPopup.currChatPopup == null) {
                                       byte byteValue2 = 0;
                                       byte byteValue3 = 0;
                                       if (Char.myCharz().npcFocus.template.npcTemplateId == 28
                                          || Char.myCharz().npcFocus.template.npcTemplateId == 41) {
                                          byteValue2 = 3;
                                          byteValue3 = -12;
                                       }

                                       g.drawRegion(
                                          Mob.imgHP,
                                          0,
                                          0,
                                          9,
                                          6,
                                          0,
                                          super.cx + byteValue2,
                                          super.cy - super.ch - (byteValue - 8) + byteValue3,
                                          33
                                       );
                                    }
                                 }
                              }

                              this.dyEff = 65;
                           }
                        } else if (this.duahau != null) {
                           if (this.template.npcTemplateId == 50 && mabuEff) {
                              tMabuEff++;
                              if (GameCanvas.gameTick % 3 == 0) {
                                 EffecMn.addEff(
                                    new Effect(19, super.cx + Res.random(-50, 50), super.cy, 2, 1, -1)
                                 );
                              }

                              if (GameCanvas.gameTick % 15 == 0) {
                                 EffecMn.addEff(
                                    new Effect(
                                       18,
                                       super.cx + Res.random(-5, 5),
                                       super.cy + Res.random(-90, 0),
                                       2,
                                       1,
                                       -1
                                    )
                                 );
                              }

                              if (tMabuEff == 100) {
                                 GameScr.gI().activeSuperPower(super.cx, super.cy);
                              }

                              if (tMabuEff == 110) {
                                 mabuEff = false;
                                 this.template.npcTemplateId = 4;
                              }
                           }

                           int imageHeight = 0;
                           if (SmallImage.imgNew[this.duahau[this.duaHauIndex]] != null
                              && SmallImage.imgNew[this.duahau[this.duaHauIndex]].img != null) {
                              imageHeight = mGraphics.getImageHeight(SmallImage.imgNew[this.duahau[this.duaHauIndex]].img);
                           }

                           SmallImage.drawSmallImage(
                              g,
                              this.duahau[this.duaHauIndex],
                              super.cx + Res.random(-1, 1),
                              super.cy,
                              0,
                              33
                           );
                           if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
                              if (ChatPopup.currChatPopup == null) {
                                 g.drawRegion(
                                    Mob.imgHP,
                                    0,
                                    0,
                                    9,
                                    6,
                                    0,
                                    super.cx,
                                    super.cy - super.ch - 9 + 16 - imageHeight,
                                    33
                                 );
                              }

                              mFont.tahoma_7b_white
                                 .drawString(
                                    g,
                                    Firework.formatCountdownDuration(this.seconds),
                                    super.cx,
                                    super.cy - super.ch - 16 - mFont.tahoma_7.getHeight() - 20 - imageHeight + 16,
                                    2,
                                    mFont.tahoma_7b_dark
                                 );
                           } else {
                              mFont.tahoma_7b_white
                                 .drawString(
                                    g,
                                    Firework.formatCountdownDuration(this.seconds),
                                    super.cx,
                                    super.cy - super.ch - 8 - mFont.tahoma_7.getHeight() - 20 - imageHeight + 16,
                                    2,
                                    mFont.tahoma_7b_dark
                                 );
                           }
                        }
                     }

                     if (super.indexEffTask >= 0 && super.effTask != null && super.cTypePk == 0) {
                        SmallImage.drawSmallImage(
                           g,
                           super.effTask.arrEfInfo[super.indexEffTask].idImg,
                           super.cx + super.effTask.arrEfInfo[super.indexEffTask].dx,
                           super.cy + super.effTask.arrEfInfo[super.indexEffTask].dy - this.dyEff,
                           0,
                           3
                        );
                        if (GameCanvas.gameTick % 2 == 0) {
                           super.indexEffTask++;
                           if (super.indexEffTask >= super.effTask.arrEfInfo.length) {
                              super.indexEffTask = 0;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public final void paintName(mGraphics g) {
      if (!Char.isLoadingMap) {
         if (!this.isHide) {
            if (this.isPaint()) {
               if (super.statusMe != 15) {
                  if (this.template != null) {
                     if (this.template.npcTemplateId == 3) {
                        if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
                           mFont.tahoma_7_yellow
                              .drawString(
                                 g,
                                 this.template.name,
                                 super.cx,
                                 super.cy - super.ch - mFont.tahoma_7.getHeight() - 5,
                                 2,
                                 mFont.tahoma_7_grey
                              );
                        } else {
                           mFont.tahoma_7_yellow
                              .drawString(
                                 g,
                                 this.template.name,
                                 super.cx,
                                 super.cy - super.ch - 3 - mFont.tahoma_7.getHeight(),
                                 2,
                                 mFont.tahoma_7_grey
                              );
                        }

                        this.dyEff = 60;
                     } else {
                        if (this.template.npcTemplateId != 4) {
                           if (this.template.npcTemplateId != 50 && this.template.npcTemplateId != 51) {
                              if (this.template.npcTemplateId == 6) {
                                 if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
                                    mFont.tahoma_7_yellow
                                       .drawString(
                                          g,
                                          this.template.name,
                                          super.cx,
                                          super.cy - super.ch - mFont.tahoma_7.getHeight() - 16,
                                          2,
                                          mFont.tahoma_7_grey
                                       );
                                    return;
                                 }

                                 mFont.tahoma_7_yellow
                                    .drawString(
                                       g,
                                       this.template.name,
                                       super.cx,
                                       super.cy - super.ch - 8 - mFont.tahoma_7.getHeight(),
                                       2,
                                       mFont.tahoma_7_grey
                                    );
                                 return;
                              }

                              if (TileMap.mapID != 51) {
                                 byte byteValue = 15;
                                 if (this.template.npcTemplateId == 47) {
                                    byteValue = 47;
                                 }

                                 if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
                                    if (TileMap.mapID != 113) {
                                       byte byteValue2 = 0;
                                       byte byteValue3 = 0;
                                       if (Char.myCharz().npcFocus.template.npcTemplateId == 28
                                          || Char.myCharz().npcFocus.template.npcTemplateId == 41) {
                                          byteValue2 = 3;
                                          byteValue3 = -12;
                                       }

                                       mFont.tahoma_7_yellow
                                          .drawString(
                                             g,
                                             this.template.name,
                                             super.cx + byteValue2,
                                             super.cy - super.ch - mFont.tahoma_7.getHeight() - byteValue + byteValue3,
                                             2,
                                             mFont.tahoma_7_grey
                                          );
                                    }
                                 } else {
                                    byte byteValue4 = 8;
                                    if (this.template.npcTemplateId == 47) {
                                       byteValue4 = 40;
                                    }

                                    if (TileMap.mapID != 113) {
                                       byte byteValue5 = 0;
                                       byte byteValue6 = 0;
                                       if (this.template.npcTemplateId == 28 || this.template.npcTemplateId == 41) {
                                          byteValue5 = 3;
                                          byteValue6 = -12;
                                       }

                                       mFont.tahoma_7_yellow
                                          .drawString(
                                             g,
                                             this.template.name,
                                             super.cx + byteValue5,
                                             super.cy - super.ch - byteValue4 - mFont.tahoma_7.getHeight() + byteValue6,
                                             2,
                                             mFont.tahoma_7_grey
                                          );
                                    }
                                 }
                              }

                              this.dyEff = 65;
                           } else if (this.duahau != null) {
                              int imageHeight = 0;
                              if (SmallImage.imgNew[this.duahau[this.duaHauIndex]] != null
                                 && SmallImage.imgNew[this.duahau[this.duaHauIndex]].img != null) {
                                 imageHeight = mGraphics.getImageHeight(
                                    SmallImage.imgNew[this.duahau[this.duaHauIndex]].img
                                 );
                              }

                              if (Char.myCharz().npcFocus != null && Char.myCharz().npcFocus.equals(this)) {
                                 mFont.tahoma_7_yellow
                                    .drawString(
                                       g,
                                       this.template.name,
                                       super.cx,
                                       super.cy - super.ch - mFont.tahoma_7.getHeight() - imageHeight,
                                       2,
                                       mFont.tahoma_7_grey
                                    );
                                 return;
                              }

                              mFont.tahoma_7_yellow
                                 .drawString(
                                    g,
                                    this.template.name,
                                    super.cx,
                                    super.cy - super.ch - 8 - mFont.tahoma_7.getHeight() - imageHeight + 16,
                                    2,
                                    mFont.tahoma_7_grey
                                 );
                              return;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public final void hide() {
      super.statusMe = 15;
      Char.chatPopup = null;
   }
}
