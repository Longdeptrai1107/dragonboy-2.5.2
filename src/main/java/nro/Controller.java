package nro;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import javax.microedition.lcdui.Image;
import main.GameCanvas;
import main.GameMidlet;

public final class Controller implements IMessageHandler {
   private static Controller me;
   private Message messWait;
   public static boolean isLoadingData = false;
   private float demCount;
   public static boolean isStopReadMessage;
   public static boolean isGet_CLIENT_INFO = false;
   public static MyHashTable frameHT_NEWBOSS = new MyHashTable("frame new boss");
   public static boolean isEXTRA_LINK;

   public static Controller gI() {
      if (me == null) {
         me = new Controller();
      }

      return me;
   }

   public final void onConnectOK(boolean isMain) {
      if (isMain) {
         System.out.println("connect 1 ok");
         if (Service.gI().sendClientInfo(false)) {
            Service.gI().onPrimaryClientInfoSent();
            return;
         }
      } else if (Service.gI().sendClientInfo(true)) {
         Service.gI().onSecondaryClientInfoSent();
      }
   }

   public final void onConnectionFail(boolean isMain) {
      Service.gI().resetClientInfoState(isMain);
      if (!isMain) {
         if (ServerListScreen.isWaitingForConnection <= 0
            && GameCanvas.currentScreen == GameCanvas.serverScreen
            && ServerListScreen.isGetData
            && !Service.reciveFromMainSession) {
            ServerListScreen.testConnect = 0;
            GameCanvas.serverScreen.cancel();
         }

         mSystem.onConnectionFail(isMain);
      } else {
         Session_ME.gI();
         Session_ME.isSecondarySessionForcedOff();
         mSystem.onConnectionFail(isMain);
      }
   }

   public final void onDisconnected(boolean isMain) {
      Service.gI().resetClientInfoState(isMain);
      if (!isMain) {
         if (GameCanvas.currentScreen == GameCanvas.serverScreen && !Service.reciveFromMainSession) {
            GameCanvas.serverScreen.cancel();
         }

         if (GameCanvas.currentScreen == GameCanvas.loginScr && !Service.reciveFromMainSession) {
            mSystem.onDisconnected(isMain);
            return;
         }
      } else {
         Session_ME.gI();
         Session_ME.isSecondarySessionForcedOff();
         mSystem.onDisconnected(isMain);
      }
   }

   private void requestItemPlayer(Message message) {
      try {
         int itemBodyIndex = message.reader().readUnsignedByte();
         Item itemBody;
         (itemBody = GameScr.currentCharViewInfo.arrItemBody[itemBodyIndex]).saleCoinLock = message.reader().readInt();
         itemBody.sys = message.reader().readByte();
         itemBody.options = new MyVector("item.options");

         try {
            while (true) {
               ItemOption entry;
               while ((entry = readItemOption(message)) == null) {
               }

               itemBody.options.addElement(entry);
            }
         } catch (Exception ignoredException) {
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public final void onMessage(Message message) {
       mSystem.waitWhileMessageReadingPaused();
       GameCanvas.debugSession.removeAllElements();
       GameCanvas.debug();
       try {
           try {
               System.out.println("Receive message cmd " + message.command);
               Res.outz("cmd= " + message.command);
               Controller2.readMessage(message);
               label4488: switch(message.command) {
                   case -112:
                       byte mobBodyAction;
                       if ((mobBodyAction = message.reader().readByte()) == 0) {
                           GameScr.findMobInMap(((byte) (message.reader().readByte()))).clearBody();
                       }
                       if (mobBodyAction == 1) {
                           GameScr.findMobInMap(((byte) (message.reader().readByte()))).setBody(message.reader().readShort());
                       }
                   case -111:
                   case -110:
                   case -109:
                   case -108:
                   case -106:
                   case -105:
                   case -104:
                   case -103:
                   case -102:
                   case -101:
                   case -100:
                   case -89:
                   case -78:
                   case -75:
                   case -73:
                   case -72:
                   case -71:
                   case -58:
                   case -56:
                   case -55:
                   case -54:
                   case -49:
                   case -48:
                   case -40:
                   case -39:
                   case -38:
                   case -33:
                   case -27:
                   case -23:
                   case -17:
                   case -16:
                   case -15:
                   case -13:
                   case -12:
                   case -11:
                   case -10:
                   case -9:
                   case -8:
                   case -7:
                   case -6:
                   case -5:
                   case -3:
                   case -2:
                   case -1:
                   case 3:
                   case 4:
                   case 5:
                   case 8:
                   case 9:
                   case 10:
                   case 13:
                   case 14:
                   case 15:
                   case 16:
                   case 17:
                   case 18:
                   case 19:
                   case 21:
                   case 22:
                   case 23:
                   case 25:
                   case 26:
                   case 28:
                   case 30:
                   case 31:
                   case 34:
                   case 35:
                   case 36:
                   case 37:
                   case 42:
                   case 44:
                   case 45:
                   case 48:
                   case 49:
                   case 51:
                   case 52:
                   case 53:
                   case 55:
                   case 59:
                   case 60:
                   case 61:
                   case 67:
                   case 70:
                   case 71:
                   case 72:
                   case 73:
                   case 74:
                   case 75:
                   case 76:
                   case 77:
                   case 78:
                   case 79:
                   case 80:
                   case 89:
                   case 91:
                   case 93:
                   case 95:
                   case 96:
                   case 97:
                   case 98:
                   case 99:
                   case 100:
                   case 101:
                   case 102:
                   case 103:
                   case 104:
                   case 105:
                   case 106:
                   case 107:
                   case 108:
                   case 109:
                   case 110:
                   case 111:
                   default:
                       break;
                   case -107:
                       byte type;
                       if ((type = message.reader().readByte()) == 0) {
                           Char.myCharz().havePet = false;
                       }
                       if (type == 1) {
                           Char.myCharz().havePet = true;
                       }
                       if (type == 2) {
                           InfoDlg.hide();
                           Char.myPetz().head = message.reader().readShort();
                           Char.myPetz().setDefaultPart();
                           int itemBodyCount = message.reader().readUnsignedByte();
                           Char.myPetz().arrItemBody = new Item[itemBodyCount];
                           for (int itemBodyIndex = 0; itemBodyIndex < itemBodyCount; itemBodyIndex++) {
                               short id = message.reader().readShort();
                               Res.outz("template id= " + id);
                               if (id != -1) {
                                   Res.outz("1");
                                   Char.myPetz().arrItemBody[itemBodyIndex] = new Item();
                                   Char.myPetz().arrItemBody[itemBodyIndex].template = ItemTemplates.get(id);
                                   type = Char.myPetz().arrItemBody[itemBodyIndex].template.type;
                                   Char.myPetz().arrItemBody[itemBodyIndex].quantity = message.reader().readInt();
                                   Res.outz("3");
                                   message.reader().readUTF();
                                   message.reader().readUTF();
                                   int itemOptionCount = message.reader().readUnsignedByte();
                                   Res.outz("option size= " + itemOptionCount);
                                   if (itemOptionCount != 0) {
                                       Char.myPetz().arrItemBody[itemBodyIndex].itemOption = new ItemOption[itemOptionCount];
                                       for (int itemOptionIndex = 0; itemOptionIndex < Char.myPetz().arrItemBody[itemBodyIndex].itemOption.length; itemOptionIndex++) {
                                           ItemOption itemOption;
                                           if ((itemOption = readItemOption(message)) != null) {
                                               Char.myPetz().arrItemBody[itemBodyIndex].itemOption[itemOptionIndex] = itemOption;
                                           }
                                       }
                                   }
                                   if (type == 0) {
                                       Char.myPetz().body = Char.myPetz().arrItemBody[itemBodyIndex].template.part;
                                   } else if (type == 1) {
                                       Char.myPetz().leg = Char.myPetz().arrItemBody[itemBodyIndex].template.part;
                                   }
                               }
                           }
                           Char.myPetz().cHP = message.reader().readLong();
                           Char.myPetz().cHPFull = message.reader().readLong();
                           Char.myPetz().cMP = message.reader().readLong();
                           Char.myPetz().cMPFull = message.reader().readLong();
                           Char.myPetz().cDamFull = message.reader().readLong();
                           Char.myPetz().cName = message.reader().readUTF();
                           Char.myPetz().currStrLevel = message.reader().readUTF();
                           Char.myPetz().cPower = message.reader().readLong();
                           Char.myPetz().cTiemNang = message.reader().readLong();
                           Char.myPetz().petStatus = message.reader().readByte();
                           Char.myPetz().cStamina = message.reader().readShort();
                           Char.myPetz().cMaxStamina = message.reader().readShort();
                           Char.myPetz().cCriticalFull = message.reader().readByte();
                           Char.myPetz().cDefull = message.reader().readLong();
                           Char.myPetz().arrPetSkill = new Skill[message.reader().readByte()];
                           Res.outz("SKILLENT = " + Char.myPetz().arrPetSkill);
                           for (int petSkillIndex = 0; petSkillIndex < Char.myPetz().arrPetSkill.length; petSkillIndex++) {
                               short skillId;
                               if ((skillId = message.reader().readShort()) != -1) {
                                   Char.myPetz().arrPetSkill[petSkillIndex] = Skills.get(skillId);
                               } else {
                                   Char.myPetz().arrPetSkill[petSkillIndex] = new Skill();
                                   Char.myPetz().arrPetSkill[petSkillIndex].template = null;
                                   Char.myPetz().arrPetSkill[petSkillIndex].moreInfo = message.reader().readUTF();
                               }
                           }
                           Char.myPetz().cGiamST = message.reader().readByte();
                           Char.myPetz().cCritDameFull = message.reader().readShort();
                           if (GameCanvas.w > 2 * Panel.WIDTH_PANEL) {
                               (GameCanvas.panel2 = new Panel()).tabName[7] = new String[][] { { "" } };
                               GameCanvas.panel2.setTypeBodyOnly();
                               GameCanvas.panel2.show();
                               GameCanvas.panel.setTypePetMain();
                               GameCanvas.panel.show();
                           } else {
                               GameCanvas.panel.tabName[21] = mResources.petMainTab;
                               GameCanvas.panel.setTypePetMain();
                               GameCanvas.panel.show();
                           }
                       }
                       break;
                   case -99:
                       InfoDlg.hide();
                       byte enemyListAction = message.reader().readByte();
                       Res.outz("byte size= " + enemyListAction);
                       if (enemyListAction == 0) {
                           GameCanvas.panel.vEnemy.removeAllElements();
                           int enemyCount = message.reader().readUnsignedByte();
                           for (int enemyIndex = 0; enemyIndex < enemyCount; enemyIndex++) {
                               Char charInfo;
                               (charInfo = new Char()).charID = message.reader().readInt();
                               charInfo.head = message.reader().readShort();
                               charInfo.headICON = message.reader().readShort();
                               charInfo.body = message.reader().readShort();
                               charInfo.leg = message.reader().readShort();
                               charInfo.bag = message.reader().readShort();
                               charInfo.cName = message.reader().readUTF();
                               Res.outz("head = " + charInfo.head + " body" + charInfo.body + " leg=" + charInfo.leg);
                               InfoItem enemy = new InfoItem(message.reader().readUTF());
                               boolean isOnline = message.reader().readBoolean();
                               enemy.charInfo = charInfo;
                               enemy.isOnline = isOnline;
                               Res.outz("isonline = " + isOnline);
                               GameCanvas.panel.vEnemy.addElement(enemy);
                           }
                           GameCanvas.panel.setTypeEnemy();
                           GameCanvas.panel.show();
                       }
                       break;
                   case -98:
                       byte confirmationAction = message.reader().readByte();
                       GameCanvas.menu.showMenu = false;
                       if (confirmationAction == 0) {
                           GameCanvas.startYesNoDlg(message.reader().readUTF(), new Command(mResources.YES, GameCanvas.instance, 888397, message.reader().readUTF()), new Command(mResources.NO, GameCanvas.instance, 888396, null));
                       }
                       break;
                   case -97:
                       Char.myCharz().cNangdong = (long) message.reader().readInt();
                       break;
                   case -96:
                       byte thachDauMode = message.reader().readByte();
                       GameCanvas.panel.vTop.removeAllElements();
                       String topName = message.reader().readUTF();
                       byte rankingEntryCount = message.reader().readByte();
                       for (int rankingEntryIndex = 0; rankingEntryIndex < rankingEntryCount; rankingEntryIndex++) {
                           int rank = message.reader().readInt();
                           int pId = message.reader().readInt();
                           short headID = message.reader().readShort();
                           short headICON = message.reader().readShort();
                           short body = message.reader().readShort();
                           short leg = message.reader().readShort();
                           String name = message.reader().readUTF();
                           String info = message.reader().readUTF();
                           TopInfo entry;
                           (entry = new TopInfo()).rank = rank;
                           entry.headID = headID;
                           entry.headICON = headICON;
                           entry.body = body;
                           entry.leg = leg;
                           entry.name = name;
                           entry.info = info;
                           entry.info2 = message.reader().readUTF();
                           entry.pId = pId;
                           GameCanvas.panel.vTop.addElement(entry);
                       }
                       GameCanvas.panel.topName = topName;
                       GameCanvas.panel.setTypeTop(thachDauMode);
                       GameCanvas.panel.show();
                       break;
                   case -95:
                       byte packetByte;
                       if ((packetByte = message.reader().readByte()) == 0) {
                           int charId = message.reader().readInt();
                           short templateId = message.reader().readShort();
                           long hp = message.reader().readLong();
                           SoundMn.gI();
                           SoundMn.explode_1();
                           if (charId == Char.myCharz().charID) {
                               Char.myCharz().mobMe = new Mob(charId, false, false, false, false, false, templateId, 1, hp, (byte) 0, hp, (short) (Char.myCharz().cx + (Char.myCharz().cdir == 1 ? 40 : -40)), (short) Char.myCharz().cy, (byte) 4, (byte) 0);
                               Char.myCharz().mobMe.isMobMe = true;
                               EffecMn.addEff(new Effect(18, Char.myCharz().mobMe.x, Char.myCharz().mobMe.y, 2, 10, -1));
                               Char.myCharz().tMobMeBorn = 30;
                               GameScr.vMob.addElement(Char.myCharz().mobMe);
                           } else {
                               Char character;
                               if ((character = GameScr.findCharInMap(charId)) != null) {
                                   Mob mobMe;
                                   (mobMe = new Mob(charId, false, false, false, false, false, templateId, 1, hp, (byte) 0, hp, (short) character.cx, (short) character.cy, (byte) 4, (byte) 0)).isMobMe = true;
                                   character.mobMe = mobMe;
                                   GameScr.vMob.addElement(character.mobMe);
                               } else if (GameScr.findMobInMap(((int) (charId))) == null) {
                                   Mob entry2;
                                   (entry2 = new Mob(charId, false, false, false, false, false, templateId, 1, hp, (byte) 0, hp, (short) -100, (short) -100, (byte) 4, (byte) 0)).isMobMe = true;
                                   GameScr.vMob.addElement(entry2);
                               }
                           }
                       }
                       if (packetByte == 1) {
                           int charId2 = message.reader().readInt();
                           byte mobId = message.reader().readByte();
                           Res.outz("mod attack id= " + charId2);
                           if (charId2 == Char.myCharz().charID) {
                               if (GameScr.findMobInMap(((int) (mobId))) != null) {
                                   Char.myCharz().mobMe.attackOtherMob(GameScr.findMobInMap(((int) (mobId))));
                               }
                           } else {
                               Char character2;
                               if ((character2 = GameScr.findCharInMap(charId2)) != null && GameScr.findMobInMap(((int) (mobId))) != null) {
                                   character2.mobMe.attackOtherMob(GameScr.findMobInMap(((int) (mobId))));
                               }
                           }
                       }
                       if (packetByte == 2) {
                           int mobId2 = message.reader().readInt();
                           int charId3 = message.reader().readInt();
                           long dame = message.reader().readLong();
                           long cHPNew = message.reader().readLong();
                           if (mobId2 == Char.myCharz().charID) {
                               Res.outz("mob dame= " + dame);
                               Char cFocus;
                               if ((cFocus = GameScr.findCharInMap(charId3)) != null) {
                                   cFocus.cHPNew = cHPNew;
                                   if (Char.myCharz().mobMe.isBusyAttackSomeOne) {
                                       cFocus.doInjure(dame, 0L, false, true);
                                   } else {
                                       Char.myCharz().mobMe.dame = dame;
                                       Char.myCharz().mobMe.setAttack(cFocus);
                                   }
                               }
                           } else {
                               Mob mob;
                               if ((mob = GameScr.findMobInMap(((int) (mobId2)))) != null) {
                                   if (charId3 == Char.myCharz().charID) {
                                       Char.myCharz().cHPNew = cHPNew;
                                       if (mob.isBusyAttackSomeOne) {
                                           Char.myCharz().doInjure(dame, 0L, false, true);
                                       } else {
                                           mob.dame = dame;
                                           mob.setAttack(Char.myCharz());
                                       }
                                   } else {
                                       Char cFocus2;
                                       if ((cFocus2 = GameScr.findCharInMap(charId3)) != null) {
                                           cFocus2.cHPNew = cHPNew;
                                           if (mob.isBusyAttackSomeOne) {
                                               cFocus2.doInjure(dame, 0L, false, true);
                                           } else {
                                               mob.dame = dame;
                                               mob.setAttack(cFocus2);
                                           }
                                       }
                                   }
                               }
                           }
                       }
                       if (packetByte == 3) {
                           int charId4 = message.reader().readInt();
                           int mobId3 = message.reader().readInt();
                           long hp2 = message.reader().readLong();
                           long damage = message.reader().readLong();
                           Char character3;
                           if (Char.myCharz().charID == charId4) {
                               character3 = Char.myCharz();
                           } else {
                               character3 = GameScr.findCharInMap(charId4);
                           }
                           if (character3 != null) {
                               Mob mobToAttack = GameScr.findMobInMap(((int) (mobId3)));
                               if (character3.mobMe != null) {
                                   character3.mobMe.attackOtherMob(mobToAttack);
                               }
                               if (mobToAttack != null) {
                                   mobToAttack.hp = hp2;
                                   mobToAttack.updateHp_bar();
                                   if (damage == 0L) {
                                       mobToAttack.x = mobToAttack.xFirst;
                                       mobToAttack.y = mobToAttack.yFirst;
                                       GameScr.startFlyText(mResources.miss, mobToAttack.x, mobToAttack.y - mobToAttack.h, 0, -2, 4);
                                   } else {
                                       GameScr.startFlyText("-" + damage, mobToAttack.x, mobToAttack.y - mobToAttack.h, 0, -2, 5);
                                   }
                               }
                           }
                       }
                       if (packetByte == 5) {
                           int charId5 = message.reader().readInt();
                           byte sksIndex = message.reader().readByte();
                           int mobId4 = message.reader().readInt();
                           long damage2 = message.reader().readLong();
                           long hp3 = message.reader().readLong();
                           Char character4;
                           if (charId5 == Char.myCharz().charID) {
                               character4 = Char.myCharz();
                           } else {
                               character4 = GameScr.findCharInMap(charId5);
                           }
                           if (character4 == null) {
                               return;
                           }
                           if ((TileMap.tileTypeAtPixel(character4.cx, character4.cy) & 2) == 2) {
                               character4.setSkillPaint(GameScr.sks[sksIndex], 0);
                           } else {
                               character4.setSkillPaint(GameScr.sks[sksIndex], 1);
                           }
                           Mob mobFocus = GameScr.findMobInMap(((int) (mobId4)));
                           if (character4.cx <= mobFocus.x) {
                               character4.cdir = 1;
                           } else {
                               character4.cdir = -1;
                           }
                           character4.mobFocus = mobFocus;
                           mobFocus.hp = hp3;
                           mobFocus.updateHp_bar();
                           GameCanvas.debug();
                           if (damage2 == 0L) {
                               mobFocus.x = mobFocus.xFirst;
                               mobFocus.y = mobFocus.yFirst;
                               GameScr.startFlyText(mResources.miss, mobFocus.x, mobFocus.y - mobFocus.h, 0, -2, 4);
                           } else {
                               GameScr.startFlyText("-" + damage2, mobFocus.x, mobFocus.y - mobFocus.h, 0, -2, 5);
                           }
                       }
                       if (packetByte == 6) {
                           Res.outz("REMOVE QUÁI");
                           int charId6;
                           if ((charId6 = message.reader().readInt()) == Char.myCharz().charID) {
                               Char.myCharz().mobMe.startDie();
                           } else {
                               Char character5;
                               if ((character5 = GameScr.findCharInMap(charId6)) != null) {
                                   character5.mobMe.startDie();
                               }
                           }
                       }
                       if (packetByte == 7) {
                           int charId7;
                           if ((charId7 = message.reader().readInt()) == Char.myCharz().charID) {
                               Char.myCharz().mobMe = null;
                               for (int mobIndex = 0; mobIndex < GameScr.vMob.size(); mobIndex++) {
                                   if (((Mob) GameScr.vMob.elementAt(mobIndex)).mobId == charId7) {
                                       GameScr.vMob.removeElementAt(mobIndex);
                                   }
                               }
                           } else {
                               Char character6 = GameScr.findCharInMap(charId7);
                               for (int mobIndex2 = 0; mobIndex2 < GameScr.vMob.size(); mobIndex2++) {
                                   if (((Mob) GameScr.vMob.elementAt(mobIndex2)).mobId == charId7) {
                                       GameScr.vMob.removeElementAt(mobIndex2);
                                   }
                               }
                               if (character6 != null) {
                                   character6.mobMe = null;
                               }
                           }
                       }
                       break;
                   case -94:
                       while (message.reader().available() > 0) {
                           short packetShort = message.reader().readShort();
                           int packetInt = message.reader().readInt();
                           for (int skillIndex = 0; skillIndex < Char.myCharz().vSkill.size(); skillIndex++) {
                               Skill skill;
                               if ((skill = (Skill) Char.myCharz().vSkill.elementAt(skillIndex)) != null && skill.skillId == packetShort && packetInt < skill.coolDown) {
                                   skill.lastTimeUseThisSkill = System.currentTimeMillis() - (long) (skill.coolDown - packetInt);
                               }
                           }
                       }
                       break;
                   case -93:
                       short dataLength;
                       BgItem.newSmallVersion = new byte[dataLength = message.reader().readShort()];
                       for (int newSmallVersionIndex = 0; newSmallVersionIndex < dataLength; newSmallVersionIndex++) {
                           BgItem.newSmallVersion[newSmallVersionIndex] = message.reader().readByte();
                       }
                       break;
                   case -92:
                       mSystem.clientType = message.reader().readByte();
                       if (Rms.loadRMSString(Rms.RMS_ResVersion) != null) {
                           Rms.clearAll();
                       }
                       Rms.saveRMSInt(Rms.RMS_clienttype, mSystem.clientType);
                       Rms.saveRMSInt(Rms.RMS_lastZoomlevel, mGraphics.zoomLevel);
                       if (Rms.loadRMSString(Rms.RMS_ResVersion) == null) {
                           GameCanvas.startOK(mResources.plsRestartGame, 8885, null);
                       }
                       break;
                   case -91:
                       byte mapNameCount = message.reader().readByte();
                       Res.outz("nList= " + mapNameCount);
                       GameCanvas.panel.mapNames = new String[mapNameCount];
                       GameCanvas.panel.planetNames = new String[mapNameCount];
                       for (int mapNameIndex = 0; mapNameIndex < mapNameCount; mapNameIndex++) {
                           GameCanvas.panel.mapNames[mapNameIndex] = message.reader().readUTF();
                           GameCanvas.panel.planetNames[mapNameIndex] = message.reader().readUTF();
                       }
                       GameCanvas.panel.setTypeMapTrans();
                       GameCanvas.panel.show();
                       break;
                   case -90:
                       byte packetByte2 = message.reader().readByte();
                       int charId8 = message.reader().readInt();
                       Res.outz("===> UPDATE_BODY:    type = " + packetByte2);
                       Char c = Char.myCharz().charID == charId8 ? Char.myCharz() : GameScr.findCharInMap(charId8);
                       if (packetByte2 != -1) {
                           short head = message.reader().readShort();
                           short body2 = message.reader().readShort();
                           short leg2 = message.reader().readShort();
                           byte isMonkey = message.reader().readByte();
                           Res.err("====> Cmd: -90 UPDATE_BODY   \n  isMonkey= " + isMonkey + " head=  " + head + " body= " + body2 + " legU= " + leg2);
                           if (c != null) {
                               if (c.charID == charId8) {
                                   c.isMask = true;
                                   c.isMonkey = isMonkey;
                                   if (c.isMonkey != 0) {
                                       c.isWaitMonkey = false;
                                       c.isLockMove = false;
                                   }
                               } else if (c != null) {
                                   c.isMask = true;
                                   c.isMonkey = isMonkey;
                               }
                               if (head != -1) {
                                   c.head = head;
                               }
                               if (body2 != -1) {
                                   c.body = body2;
                               }
                               if (leg2 != -1) {
                                   c.leg = leg2;
                               }
                           }
                       }
                       if (packetByte2 == -1 && c != null) {
                           c.isMask = false;
                           c.isMonkey = 0;
                       }
                       if (c != null) {
                           Effect.GetCharEff(c);
                           if (c.bag == 30 && c.me) {
                               GameScr.isPickNgocRong = true;
                           }
                           if (c.me) {
                               GameScr.isudungCapsun4 = false;
                               GameScr.isudungCapsun3 = false;
                               for (int itemBagIndex = 0; itemBagIndex < Char.myCharz().arrItemBag.length; itemBagIndex++) {
                                   Item itemBag;
                                   if ((itemBag = Char.myCharz().arrItemBag[itemBagIndex]) != null) {
                                       if (itemBag.template.id == 194) {
                                           if (GameScr.isudungCapsun4 = itemBag.quantity > 0) {
                                               break label4488;
                                           }
                                       } else if (itemBag.template.id == 193) {
                                           GameScr.isudungCapsun3 = itemBag.quantity > 0;
                                       }
                                   }
                               }
                           }
                       }
                       break;
                   case -88:
                       GameCanvas.endDlg();
                       GameCanvas.serverScreen.switchToMe();
                       break;
                   case -87:
                       Res.outz("GET UPDATE_DATA " + message.reader().available() + " bytes");
                       message.reader().mark(100000);
                       createData(message.reader(), true);
                       message.reader().reset();
                       byte[] data = new byte[message.reader().available()];
                       message.reader().readFully(data);
                       byte[] data2 = new byte[] { GameScr.vcData };
                       Rms.saveRMS("NRdataVersion", data2);
                       LoginScr.isUpdateData = false;
                       GameScr.gI();
                       GameScr.readOk();
                       break;
                   case -86:
                       byte packetByte3;
                       if ((packetByte3 = message.reader().readByte()) == 0) {
                           int playerID = message.reader().readInt();
                           GameScr.gI().giaodich(playerID);
                       }
                       if (packetByte3 == 1) {
                           int playerID2;
                           Char cGD;
                           if ((cGD = GameScr.findCharInMap(playerID2 = message.reader().readInt())) == null) {
                               return;
                           }
                           GameCanvas.panel.setTypeGiaoDich(cGD);
                           GameCanvas.panel.show();
                           Service.gI().getPlayerMenu(playerID2);
                       }
                       if (packetByte3 == 2) {
                           byte packetByte4 = message.reader().readByte();
                           for (int myGDIndex = 0; myGDIndex < GameCanvas.panel.vMyGD.size(); myGDIndex++) {
                               Item myGD;
                               if ((myGD = (Item) GameCanvas.panel.vMyGD.elementAt(myGDIndex)).indexUI == packetByte4) {
                                   GameCanvas.panel.vMyGD.removeElement(myGD);
                                   break;
                               }
                           }
                       }
                       if (packetByte3 == 6) {
                           GameCanvas.panel.isFriendLock = true;
                           if (GameCanvas.panel2 != null) {
                               GameCanvas.panel2.isFriendLock = true;
                           }
                           GameCanvas.panel.vFriendGD.removeAllElements();
                           if (GameCanvas.panel2 != null) {
                               GameCanvas.panel2.vFriendGD.removeAllElements();
                           }
                           int friendMoneyGD = message.reader().readInt();
                           byte friendGDCount = message.reader().readByte();
                           for (int friendGDIndex = 0; friendGDIndex < friendGDCount; friendGDIndex++) {
                               Item entry3;
                               (entry3 = new Item()).template = ItemTemplates.get(message.reader().readShort());
                               entry3.quantity = message.reader().readInt();
                               int itemOptionCount2;
                               if ((itemOptionCount2 = message.reader().readUnsignedByte()) != 0) {
                                   entry3.itemOption = new ItemOption[itemOptionCount2];
                                   for (int itemOptionIndex2 = 0; itemOptionIndex2 < entry3.itemOption.length; itemOptionIndex2++) {
                                       ItemOption itemOption2;
                                       if ((itemOption2 = readItemOption(message)) != null) {
                                           entry3.itemOption[itemOptionIndex2] = itemOption2;
                                           entry3.compare = GameCanvas.panel.getCompare(entry3);
                                       }
                                   }
                               }
                               if (GameCanvas.panel2 != null) {
                                   GameCanvas.panel2.vFriendGD.addElement(entry3);
                               } else {
                                   GameCanvas.panel.vFriendGD.addElement(entry3);
                               }
                           }
                           if (GameCanvas.panel2 != null) {
                               GameCanvas.panel2.setTabGiaoDich(false);
                               GameCanvas.panel2.friendMoneyGD = friendMoneyGD;
                           } else {
                               GameCanvas.panel.friendMoneyGD = friendMoneyGD;
                               if (GameCanvas.panel.currentTabIndex == 2) {
                                   GameCanvas.panel.setTabGiaoDich(false);
                               }
                           }
                       }
                       if (packetByte3 == 7) {
                           InfoDlg.hide();
                           if (GameCanvas.panel.isShow) {
                               GameCanvas.panel.hide();
                           }
                       }
                       break;
                   case -85:
                       byte packetByte5;
                       if ((packetByte5 = message.reader().readByte()) == 0) {
                           int dataLength2;
                           byte[] data3 = new byte[dataLength2 = message.reader().readUnsignedShort()];
                           message.reader().read(data3, 0, dataLength2);
                           GameScr.gI();
                           GameScr.imgCapcha = Image.createImage(data3, 0, dataLength2);
                           GameScr.gI().keyInput = "-----";
                           GameScr.gI().strCapcha = message.reader().readUTF();
                           GameScr.gI().keyCapcha = new int[GameScr.gI().strCapcha.length()];
                           GameScr.gI().mobCapcha = new Mob();
                           GameScr.gI().right = null;
                       }
                       if (packetByte5 == 1) {
                           MobCapcha.isAttack = true;
                       }
                       if (packetByte5 == 2) {
                           MobCapcha.explode = true;
                           GameScr.gI().right = GameScr.gI().cmdFocus;
                       }
                       break;
                   case -84:
                       int mobIndex3 = message.reader().readUnsignedByte();
                       Mob mob2 = null;
                       try {
                           mob2 = (Mob) GameScr.vMob.elementAt(mobIndex3);
                       } catch (Exception ignoredException) {
                       }
                       if (mob2 != null) {
                           mob2.maxHp = message.reader().readLong();
                       }
                       break;
                   case -83:
                       byte packetByte6;
                       if ((packetByte6 = message.reader().readByte()) == 0) {
                           short mapRID = message.reader().readShort();
                           short bgRID = message.reader().readShort();
                           int zoneRID = message.reader().readUnsignedByte();
                           int packetInt2 = message.reader().readInt();
                           message.reader().readUTF();
                           Object entry4 = null;
                           short xR = message.reader().readShort();
                           short yR = message.reader().readShort();
                           if (message.reader().readByte() == 1) {
                               GameScr.gI().isRongNamek = true;
                           } else {
                               GameScr.gI().isRongNamek = false;
                           }
                           GameScr.gI().xR = xR;
                           GameScr.gI().yR = yR;
                           Res.outz("xR= " + xR + " yR= " + yR + " +++++++++++++++++++++++++++++++++++++++");
                           if (Char.myCharz().charID == packetInt2) {
                               GameCanvas.panel.hideNow();
                               GameScr.gI().activeRongThanEff(true);
                           } else if (TileMap.mapID == mapRID && TileMap.zoneID == zoneRID) {
                               GameScr.gI().activeRongThanEff(false);
                           } else if (mGraphics.zoomLevel > 1) {
                               GameScr.gI().doiMauTroi();
                           }
                           GameScr.gI().mapRID = mapRID;
                           GameScr.gI().bgRID = bgRID;
                           GameScr.gI().zoneRID = zoneRID;
                       }
                       if (packetByte6 == 1) {
                           Res.outz("map RID = " + GameScr.gI().mapRID + " zone RID= " + GameScr.gI().zoneRID);
                           Res.outz("map ID = " + TileMap.mapID + " zone ID= " + TileMap.zoneID);
                           if (TileMap.mapID == GameScr.gI().mapRID && TileMap.zoneID == GameScr.gI().zoneRID) {
                               GameScr.gI().hideRongThanEff();
                           } else {
                               GameScr.gI().isRongThanXuatHien = false;
                               if (GameScr.gI().isRongNamek) {
                                   GameScr.gI().isRongNamek = false;
                               }
                           }
                       }
                       break;
                   case -82:
                       byte valueCount;
                       TileMap.tileIndex = new int[valueCount = message.reader().readByte()][][];
                       TileMap.tileType = new int[valueCount][];
                       for (int tileIndexIndex = 0; tileIndexIndex < valueCount; tileIndexIndex++) {
                           byte tileTypeCount = message.reader().readByte();
                           TileMap.tileType[tileIndexIndex] = new int[tileTypeCount];
                           TileMap.tileIndex[tileIndexIndex] = new int[tileTypeCount][];
                           for (int tileIndexIndex2 = 0; tileIndexIndex2 < tileTypeCount; tileIndexIndex2++) {
                               TileMap.tileType[tileIndexIndex][tileIndexIndex2] = message.reader().readInt();
                               byte tileIndexCount = message.reader().readByte();
                               TileMap.tileIndex[tileIndexIndex][tileIndexIndex2] = new int[tileIndexCount];
                               for (int tileIndexIndex3 = 0; tileIndexIndex3 < tileIndexCount; tileIndexIndex3++) {
                                   TileMap.tileIndex[tileIndexIndex][tileIndexIndex2][tileIndexIndex3] = message.reader().readByte();
                               }
                           }
                       }
                       break;
                   case -81:
                       byte packetByte7 = message.reader().readByte();
                       Res.outz("combine action =" + packetByte7);
                       if (packetByte7 == 0) {
                           String src = message.reader().readUTF();
                           String src2 = message.reader().readUTF();
                           GameCanvas.panel.setTypeCombine();
                           GameCanvas.panel.combineInfo = mFont.tahoma_7b_blue.splitFontArray(src, Panel.WIDTH_PANEL);
                           GameCanvas.panel.combineTopInfo = mFont.tahoma_7.splitFontArray(src2, Panel.WIDTH_PANEL);
                           GameCanvas.panel.show();
                       }
                       if (packetByte7 == 1) {
                           GameCanvas.panel.vItemCombine.removeAllElements();
                           byte itemCombineCount = message.reader().readByte();
                           for (int itemCombineIndex = 0; itemCombineIndex < itemCombineCount; itemCombineIndex++) {
                               byte packetByte8 = message.reader().readByte();
                               for (int itemBagIndex2 = 0; itemBagIndex2 < Char.myCharz().arrItemBag.length; itemBagIndex2++) {
                                   Item entry5;
                                   if ((entry5 = Char.myCharz().arrItemBag[itemBagIndex2]) != null && entry5.indexUI == packetByte8) {
                                       entry5.isSelect = true;
                                       GameCanvas.panel.vItemCombine.addElement(entry5);
                                   }
                               }
                           }
                           if (GameCanvas.panel.isShow) {
                               GameCanvas.panel.setTabCombine();
                           }
                       }
                       if (packetByte7 == 2) {
                           GameCanvas.panel.combineSuccess = 0;
                           GameCanvas.panel.setCombineEff(0);
                       }
                       if (packetByte7 == 3) {
                           GameCanvas.panel.combineSuccess = 1;
                           GameCanvas.panel.setCombineEff(0);
                       }
                       if (packetByte7 == 4) {
                           short iconID3 = message.reader().readShort();
                           GameCanvas.panel.iconID3 = iconID3;
                           GameCanvas.panel.combineSuccess = 0;
                           GameCanvas.panel.setCombineEff(1);
                       }
                       if (packetByte7 == 5) {
                           short iconID32 = message.reader().readShort();
                           GameCanvas.panel.iconID3 = iconID32;
                           GameCanvas.panel.combineSuccess = 0;
                           GameCanvas.panel.setCombineEff(2);
                       }
                       if (packetByte7 == 6) {
                           short iconID1 = message.reader().readShort();
                           short iconID33 = message.reader().readShort();
                           GameCanvas.panel.combineSuccess = 0;
                           GameCanvas.panel.setCombineEff(3);
                           GameCanvas.panel.iconID1 = iconID1;
                           GameCanvas.panel.iconID3 = iconID33;
                       }
                       if (packetByte7 == 7) {
                           short iconID34 = message.reader().readShort();
                           GameCanvas.panel.iconID3 = iconID34;
                           GameCanvas.panel.combineSuccess = 0;
                           GameCanvas.panel.setCombineEff(4);
                       }
                       if (packetByte7 == 8) {
                           GameCanvas.panel.iconID3 = -1;
                           GameCanvas.panel.combineSuccess = 1;
                           GameCanvas.panel.setCombineEff(4);
                       }
                       short idNPC = 21;
                       short packetShort2 = 0;
                       short packetShort3 = 0;
                       try {
                           idNPC = message.reader().readShort();
                           packetShort2 = message.reader().readShort();
                           packetShort3 = message.reader().readShort();
                           GameCanvas.panel.xS = packetShort2 - GameScr.cmx;
                           GameCanvas.panel.yS = packetShort3 - GameScr.cmy;
                       } catch (Exception ignoredException2) {
                       }
                       for (int npcIndex = 0; npcIndex < GameScr.vNpc.size(); npcIndex++) {
                           Npc npc;
                           if ((npc = (Npc) GameScr.vNpc.elementAt(npcIndex)).template.npcTemplateId == idNPC) {
                               GameCanvas.panel.xS = npc.cx - GameScr.cmx;
                               GameCanvas.panel.yS = npc.cy - GameScr.cmy;
                               GameCanvas.panel.idNPC = idNPC;
                               break label4488;
                           }
                       }
                       break;
                   case -80:
                       byte packetByte9 = message.reader().readByte();
                       InfoDlg.hide();
                       if (packetByte9 == 0) {
                           GameCanvas.panel.vFriend.removeAllElements();
                           int friendCount = message.reader().readUnsignedByte();
                           for (int friendIndex = 0; friendIndex < friendCount; friendIndex++) {
                               Char charInfo2;
                               (charInfo2 = new Char()).charID = message.reader().readInt();
                               charInfo2.head = message.reader().readShort();
                               charInfo2.headICON = message.reader().readShort();
                               charInfo2.body = message.reader().readShort();
                               charInfo2.leg = message.reader().readShort();
                               charInfo2.bag = message.reader().readShort();
                               charInfo2.cName = message.reader().readUTF();
                               Res.outz("head = " + charInfo2.head + " body" + charInfo2.body + " leg=" + charInfo2.leg);
                               boolean isOnline2 = message.reader().readBoolean();
                               InfoItem entry6;
                               (entry6 = new InfoItem(mResources.power + ": " + message.reader().readUTF())).charInfo = charInfo2;
                               entry6.isOnline = isOnline2;
                               GameCanvas.panel.vFriend.addElement(entry6);
                           }
                           GameCanvas.panel.setTypeFriend();
                           GameCanvas.panel.show();
                       }
                       if (packetByte9 == 3) {
                           MyVector friend = GameCanvas.panel.vFriend;
                           int packetInt3 = message.reader().readInt();
                           Res.outz("online offline id=" + packetInt3);
                           for (int friendIndex2 = 0; friendIndex2 < friend.size(); friendIndex2++) {
                               InfoItem friend2;
                               if ((friend2 = (InfoItem) friend.elementAt(friendIndex2)).charInfo != null && friend2.charInfo.charID == packetInt3) {
                                   Res.outz("online= " + friend2.isOnline);
                                   friend2.isOnline = message.reader().readBoolean();
                                   break;
                               }
                           }
                       }
                       if (packetByte9 == 2) {
                           MyVector friend3 = GameCanvas.panel.vFriend;
                           int packetInt4 = message.reader().readInt();
                           for (int friendIndex3 = 0; friendIndex3 < friend3.size(); friendIndex3++) {
                               InfoItem friend4;
                               if ((friend4 = (InfoItem) friend3.elementAt(friendIndex3)).charInfo != null && friend4.charInfo.charID == packetInt4) {
                                   friend3.removeElement(friend4);
                                   break;
                               }
                           }
                           if (GameCanvas.panel.isShow) {
                               GameCanvas.panel.setTabFriend();
                           }
                       }
                       break;
                   case -79:
                       InfoDlg.hide();
                       message.reader().readInt();
                       Char charMenu = GameCanvas.panel.charMenu;
                       if (GameCanvas.panel.charMenu == null) {
                           return;
                       }
                       charMenu.cPower = message.reader().readLong();
                       charMenu.currStrLevel = message.reader().readUTF();
                       break;
                   case -77:
                       short maxSmall;
                       SmallImage.newSmallVersion = new byte[maxSmall = message.reader().readShort()];
                       SmallImage.maxSmall = maxSmall;
                       SmallImage.imgNew = new Small[maxSmall];
                       for (int newSmallVersionIndex2 = 0; newSmallVersionIndex2 < maxSmall; newSmallVersionIndex2++) {
                           SmallImage.newSmallVersion[newSmallVersionIndex2] = message.reader().readByte();
                       }
                       break;
                   case -76:
                       byte packetByte10;
                       if ((packetByte10 = message.reader().readByte()) == 0) {
                           byte archiveCount;
                           if ((archiveCount = message.reader().readByte()) <= 0) {
                               return;
                           }
                           Char.myCharz().arrArchive = new Archivement[archiveCount];
                           for (int archiveIndex = 0; archiveIndex < archiveCount; archiveIndex++) {
                               Char.myCharz().arrArchive[archiveIndex] = new Archivement();
                               Char.myCharz().arrArchive[archiveIndex].info1 = archiveIndex + 1 + ". " + message.reader().readUTF();
                               Char.myCharz().arrArchive[archiveIndex].info2 = message.reader().readUTF();
                               Char.myCharz().arrArchive[archiveIndex].money = message.reader().readShort();
                               Char.myCharz().arrArchive[archiveIndex].isFinish = message.reader().readBoolean();
                               Char.myCharz().arrArchive[archiveIndex].isRecieve = message.reader().readBoolean();
                           }
                           GameCanvas.panel.setTypeArchivement();
                           GameCanvas.panel.show();
                       } else if (packetByte10 == 1) {
                           int archiveIndex2 = message.reader().readUnsignedByte();
                           if (Char.myCharz().arrArchive[archiveIndex2] != null) {
                               Char.myCharz().arrArchive[archiveIndex2].isRecieve = true;
                           }
                       }
                       break;
                   case -74:
                       GameCanvas.isGetResourceFromServer();
                       byte packetByte11 = message.reader().readByte();
                       if (ServerListScreen.stopDownload && packetByte11 != 0) {
                           return;
                       }
                       Res.outz("[Controler: cmd-74] action = " + packetByte11);
                       if (packetByte11 == 0) {
                           int packetInt5 = message.reader().readInt();
                           String storedResVersion;
                           int value;
                           if ((value = (storedResVersion = Rms.loadRMSString(Rms.RMS_ResVersion)) != null && storedResVersion != "" ? Integer.parseInt(storedResVersion) : -1) != -1 && value == packetInt5) {
                               boolean condition = Service.gI().acknowledgeMainClientInfo();
                               Res.outz("login ngay");
                               SmallImage.loadBigRMS();
                               SplashScr.imgLogo = null;
                               ServerListScreen.loadScreen = true;
                               if (GameCanvas.loginScr == null) {
                                   GameCanvas.loginScr = new LoginScr();
                               }
                               boolean condition2;
                               if (GameCanvas.currentScreen == GameCanvas._SelectCharScr) {
                                   condition2 = ServerListScreen.consumePendingLoginAction(!condition);
                               } else if (GameCanvas.currentScreen != GameCanvas.loginScr) {
                                   GameCanvas.serverScreen.switchToMe();
                                   condition2 = ServerListScreen.consumePendingLoginAction(!condition);
                               } else {
                                   condition2 = ServerListScreen.consumePendingLoginAction(!condition);
                                   if (!condition && !condition2) {
                                       GameCanvas.loginScr.doLogin();
                                   }
                               }
                               if (!condition && !condition2 && (GameCanvas.currentScreen != GameCanvas.loginScr || !LoginScr.isLoggingIn)) {
                                   GameCanvas.endDlg();
                               }
                           } else {
                               GameCanvas.serverScreen.show2();
                           }
                       }
                       if (packetByte11 == 1) {
                           ServerListScreen.strWait = mResources.downloading_data;
                           ServerListScreen.nBig = message.reader().readShort();
                           Service.gI().getResource((byte) 2, null);
                       }
                       if (packetByte11 == 2) {
                           try {
                               isLoadingData = true;
                               GameCanvas.endDlg();
                               String[] parts;
                               String part = (parts = Res.split(message.reader().readUTF().replace('\\', '/'), "/", 0))[parts.length - 1];
                               String filename = "x" + mGraphics.zoomLevel + part;
                               byte[] data4 = new byte[message.reader().readInt()];
                               message.reader().readFully(data4);
                               Res.outz("path save= " + filename + " data save lent= " + data4.length + " === " + ServerListScreen.percent + "% " + ServerListScreen.nBig);
                               Rms.saveRMS(filename, data4);
                               if (part.endsWith(".png")) {
                                   String textPart = part.substring(0, part.length() - 4);
                                   Rms.saveRMS("x" + mGraphics.zoomLevel + textPart, data4);
                               }
                               ServerListScreen.percent = ++ServerListScreen.demPercent * 100 / ServerListScreen.nBig;
                               if (mSystem.clientType != 5) {
                                   ;
                               }
                           } catch (Exception ignoredException3) {
                               GameCanvas.startOK(mResources.pls_restart_game_error, 8885, null);
                           }
                       }
                       if (packetByte11 == 3) {
                           isLoadingData = false;
                           int packetInt6 = message.reader().readInt();
                           Res.outz("last version= " + packetInt6);
                           Rms.saveRMSString(Rms.RMS_ResVersion, String.valueOf(packetInt6));
                           Service.gI().getResource((byte) 3, null);
                           GameCanvas.endDlg();
                           SplashScr.imgLogo = null;
                           SmallImage.loadBigRMS();
                           System.gc();
                           ServerListScreen.bigOk = true;
                           ServerListScreen.loadScreen = true;
                           GameScr.gI().loadSplash();
                           GameScr.isLoadAllData = false;
                           Service.gI().updateData();
                           boolean condition3;
                           boolean condition4 = ServerListScreen.consumePendingLoginAction(!(condition3 = Service.gI().acknowledgeMainClientInfo()));
                           if (!condition3 && !condition4 && GameCanvas.currentScreen != GameCanvas.loginScr) {
                               if (GameCanvas.serverScreen == null) {
                                   GameCanvas.serverScreen = new ServerListScreen();
                               }
                               GameCanvas.serverScreen.switchToMe();
                           }
                       }
                       break;
                   case -70:
                       Res.outz("BIG MESSAGE .......................................");
                       GameCanvas.endDlg();
                       short avatar = message.reader().readShort();
                       String chat = message.reader().readUTF();
                       Npc c2;
                       (c2 = new Npc(-1, 0, 0, 0, 0, 0)).avatar = avatar;
                       ChatPopup.addBigMessage(chat, 100000, c2);
                       byte packetByte12;
                       if ((packetByte12 = message.reader().readByte()) == 0) {
                           ChatPopup.serverChatPopUp.cmdMsg1 = new Command(mResources.CLOSE, ChatPopup.serverChatPopUp, 1001, null);
                           ChatPopup.serverChatPopUp.cmdMsg1.x = GameCanvas.w / 2 - 35;
                           ChatPopup.serverChatPopUp.cmdMsg1.y = GameCanvas.h - 35;
                       }
                       if (packetByte12 == 1) {
                           String packetText = message.reader().readUTF();
                           String caption = message.reader().readUTF();
                           ChatPopup.serverChatPopUp.cmdMsg1 = new Command(caption, ChatPopup.serverChatPopUp, 1000, packetText);
                           ChatPopup.serverChatPopUp.cmdMsg1.x = GameCanvas.w / 2 - 75;
                           ChatPopup.serverChatPopUp.cmdMsg1.y = GameCanvas.h - 35;
                           ChatPopup.serverChatPopUp.cmdMsg2 = new Command(mResources.CLOSE, ChatPopup.serverChatPopUp, 1001, null);
                           ChatPopup.serverChatPopUp.cmdMsg2.x = GameCanvas.w / 2 + 11;
                           ChatPopup.serverChatPopUp.cmdMsg2.y = GameCanvas.h - 35;
                       }
                       break;
                   case -69:
                       Char.myCharz().cMaxStamina = message.reader().readShort();
                       break;
                   case -68:
                       Char.myCharz().cStamina = message.reader().readShort();
                       break;
                   case -67:
                       this.demCount++;
                       int index = message.reader().readInt();
                       Object arr = null;
                       try {
                           arr = Firework.readMessageBytes(message);
                           if (SmallImage.imgNew != null) {
                               SmallImage.putCachedSmallImage(index, createImage((byte[]) arr));
                           }
                       } catch (Exception ignoredException4) {
                           arr = null;
                           SmallImage.putCachedSmallImage(index, Image.createRGBImage(new int[] { -2013265920 }, 1, 1, true));
                       }
                       if (arr != null && mGraphics.zoomLevel > 1) {
                           Rms.saveRMS(mGraphics.zoomLevel + "Small" + index, (byte[]) arr);
                       }
                       break;
                   case -66:
                       short id2 = message.reader().readShort();
                       byte[] data5 = Firework.readMessageBytes(message);
                       EffectData effDataById = Effect.getEffDataById(id2);
                       byte typeread;
                       if ((typeread = message.reader().readByte()) == 0) {
                           effDataById.readData(((byte[]) (data5)));
                       } else {
                           effDataById.readDataNewBoss(data5, typeread);
                       }
                       byte[] messageBytes = Firework.readMessageBytes(message);
                       effDataById.img = Image.createImage(messageBytes, 0, messageBytes.length);
                       break;
                   case -65:
                       InfoDlg.hide();
                       int id3 = message.reader().readInt();
                       byte packetByte13 = message.reader().readByte();
                       Res.outz("TELEPORT ..................................................." + packetByte13);
                       if (packetByte13 != 0) {
                           if (Char.myCharz().charID == id3) {
                               isStopReadMessage = true;
                               GameScr.lockTick = 500;
                               GameScr.gI().center = null;
                               if (packetByte13 == 0 || packetByte13 == 1 || packetByte13 == 3) {
                                   int planet = packetByte13 == 1 ? Char.myCharz().cgender : packetByte13;
                                   Teleport.addTeleport(new Teleport(Char.myCharz().cx, Char.myCharz().cy, Char.myCharz().head, Char.myCharz().cdir, 0, true, planet));
                               }
                               if (packetByte13 == 2) {
                                   GameScr.lockTick = 50;
                                   Char.myCharz().hide();
                               }
                           } else {
                               Char character7 = GameScr.findCharInMap(id3);
                               if ((packetByte13 == 0 || packetByte13 == 1 || packetByte13 == 3) && character7 != null) {
                                   character7.isUsePlane = true;
                                   Teleport p;
                                   (p = new Teleport(character7.cx, character7.cy, character7.head, character7.cdir, 0, false, packetByte13 == 1 ? character7.cgender : packetByte13)).id = id3;
                                   Teleport.addTeleport(p);
                               }
                               if (packetByte13 == 2) {
                                   character7.hide();
                               }
                           }
                       }
                       break;
                   case -64:
                       int charId9 = message.reader().readInt();
                       short bag = message.reader().readShort();
                       Char c3;
                       if (charId9 == Char.myCharz().charID) {
                           c3 = Char.myCharz();
                       } else {
                           c3 = GameScr.findCharInMap(charId9);
                       }
                       if (c3 == null) {
                           return;
                       }
                       c3.bag = bag;
                       Effect.GetCharEff(c3);
                       Res.outz("cmd:-64 UPDATE BAG PLAER = " + (c3 == null ? "" : c3.cName) + charId9 + " BAG ID= " + bag);
                       if (bag == 30 && c3.me) {
                           GameScr.isPickNgocRong = true;
                       }
                       break;
                   case -63:
                       Res.outz("GET BAG");
                       short iD = message.reader().readShort();
                       byte idImageCount = message.reader().readByte();
                       ClanImage clanImage;
                       (clanImage = new ClanImage()).ID = iD;
                       if (idImageCount > 0) {
                           clanImage.idImage = new short[idImageCount];
                           for (int idImageIndex = 0; idImageIndex < idImageCount; idImageIndex++) {
                               clanImage.idImage[idImageIndex] = message.reader().readShort();
                               Res.outz("ID=  " + iD + " frame= " + clanImage.idImage[idImageIndex]);
                           }
                           ClanImage.idImages.put(String.valueOf((int) iD), clanImage);
                       }
                       break;
                   case -62:
                       int iD2 = message.reader().readUnsignedByte();
                       byte keyCount = message.reader().readByte();
                       Res.outz("cmd: -62 CLAN_IMAGE id= " + iD2 + " nImage= " + keyCount);
                       ClanImage clanImage2;
                       if (keyCount > 0 && (clanImage2 = ClanImage.getClanImage((short) iD2)) != null) {
                           clanImage2.idImage = new short[keyCount];
                           for (int idImageIndex2 = 0; idImageIndex2 < keyCount; idImageIndex2++) {
                               clanImage2.idImage[idImageIndex2] = message.reader().readShort();
                               if (clanImage2.idImage[idImageIndex2] > 0) {
                                   SmallImage.vKeys.addElement(String.valueOf(clanImage2.idImage[idImageIndex2]));
                               }
                           }
                       }
                       break;
                   case -61:
                       int charId10;
                       if ((charId10 = message.reader().readInt()) != Char.myCharz().charID) {
                           if (GameScr.findCharInMap(charId10) != null) {
                               GameScr.findCharInMap(charId10).clanID = message.reader().readInt();
                               if (GameScr.findCharInMap(charId10).clanID == -2) {
                                   GameScr.findCharInMap(charId10).isCopy = true;
                               }
                           }
                       } else if (Char.myCharz().clan != null) {
                           Char.myCharz().clan.ID = message.reader().readInt();
                       }
                       break;
                   case -60:
                       GameCanvas.debug();
                       int charId11 = message.reader().readInt();
                       int charId12 = -1;
                       if (charId11 == Char.myCharz().charID) {
                           message.reader().readByte();
                           message.reader().readByte();
                           boolean condition5 = false;
                           charId12 = message.reader().readInt();
                       } else {
                           Char character8;
                           if ((character8 = GameScr.findCharInMap(charId11)) == null) {
                               return;
                           }
                           if (character8.currentMovePoint != null) {
                               character8.createShadow(character8.cx, character8.cy, 10);
                               character8.cx = character8.currentMovePoint.xEnd;
                               character8.cy = character8.currentMovePoint.yEnd;
                           }
                           int sksIndex2 = message.reader().readUnsignedByte();
                           if ((TileMap.tileTypeAtPixel(character8.cx, character8.cy) & 2) == 2) {
                               character8.setSkillPaint(GameScr.sks[sksIndex2], 0);
                           } else {
                               character8.setSkillPaint(GameScr.sks[sksIndex2], 1);
                           }
                           Char[] characters = new Char[message.reader().readByte()];
                           int index2;
                           for (index2 = 0; index2 < characters.length; index2 += 1) {
                               Char character9;
                               if ((charId12 = message.reader().readInt()) == Char.myCharz().charID) {
                                   character9 = Char.myCharz();
                                   if (!GameScr.isChangeZone && GameScr.isAutoPlay && GameScr.canAutoPlay) {
                                       Service.gI().requestChangeZone(-1);
                                       GameScr.isChangeZone = true;
                                   }
                               } else {
                                   character9 = GameScr.findCharInMap(charId12);
                               }
                               characters[index2] = character9;
                               if (index2 == 0) {
                                   if (character8.cx <= character9.cx) {
                                       character8.cdir = 1;
                                   } else {
                                       character8.cdir = -1;
                                   }
                               }
                           }
                           if (index2 > 0) {
                               character8.attChars = new Char[index2];
                               for (int attCharIndex = 0; attCharIndex < character8.attChars.length; attCharIndex += 1) {
                                   character8.attChars[attCharIndex] = characters[attCharIndex];
                               }
                               character8.mobFocus = null;
                               character8.charFocus = character8.attChars[0];
                           }
                       }
                       try {
                           if (message.reader().readByte() == 1) {
                               byte packetByte14 = message.reader().readByte();
                               if (charId12 == Char.myCharz().charID) {
                                   Char player = Char.myCharz();
                                   long damHP = message.reader().readLong();
                                   player.isDie = message.reader().readBoolean();
                                   if (player.isDie) {
                                       Char.isLockKey = true;
                                   }
                                   boolean isCrit = message.reader().readBoolean();
                                   player.isCrit = isCrit;
                                   player.isMob = false;
                                   player.damHP = damHP;
                                   if (packetByte14 == 0) {
                                       player.doInjure(damHP, 0L, isCrit, false);
                                   }
                               } else {
                                   Char character10;
                                   if ((character10 = GameScr.findCharInMap(charId12)) == null) {
                                       return;
                                   }
                                   long damHP2 = message.reader().readLong();
                                   character10.isDie = message.reader().readBoolean();
                                   boolean isCrit2 = message.reader().readBoolean();
                                   character10.isCrit = isCrit2;
                                   character10.isMob = false;
                                   character10.damHP = damHP2;
                                   if (packetByte14 == 0) {
                                       character10.doInjure(damHP2, 0L, isCrit2, false);
                                   }
                               }
                           }
                       } catch (Exception ignoredException5) {
                       }
                       break;
                   case -59:
                       byte packetByte15 = message.reader().readByte();
                       GameScr gameScreen = GameScr.gI();
                       int charId13 = message.reader().readInt();
                       message.reader().readInt();
                       gameScreen.player_vs_player(charId13, message.reader().readUTF(), packetByte15);
                       break;
                   case -57:
                       String strInvite = message.reader().readUTF();
                       int clanID = message.reader().readInt();
                       int code = message.reader().readInt();
                       GameScr.gI().clanInvite(strInvite, clanID, code);
                       break;
                   case -53:
                       Res.outz("MY CLAN INFO");
                       InfoDlg.hide();
                       int iD3 = message.reader().readInt();
                       Res.outz("clanId= " + iD3);
                       if (iD3 == -1) {
                           Char.myCharz().clan = null;
                           ClanMessage.vMessage.removeAllElements();
                           if (GameCanvas.panel.member != null) {
                               GameCanvas.panel.member.removeAllElements();
                           }
                           if (GameCanvas.panel.myMember != null) {
                               GameCanvas.panel.myMember.removeAllElements();
                           }
                           if (GameCanvas.currentScreen == GameScr.gI()) {
                               GameCanvas.panel.setTabClans();
                           }
                           return;
                       }
                       GameCanvas.panel.tabIcon = null;
                       if (Char.myCharz().clan == null) {
                           Char.myCharz().clan = new Clan();
                       }
                       Char.myCharz().clan.ID = iD3;
                       Char.myCharz().clan.name = message.reader().readUTF();
                       Char.myCharz().clan.slogan = message.reader().readUTF();
                       Char.myCharz().clan.imgID = message.reader().readShort();
                       Char.myCharz().clan.powerPoint = message.reader().readUTF();
                       Char.myCharz().clan.leaderName = message.reader().readUTF();
                       Char.myCharz().clan.currMember = message.reader().readUnsignedByte();
                       Char.myCharz().clan.maxMember = message.reader().readUnsignedByte();
                       Char.myCharz().role = message.reader().readByte();
                       Char.myCharz().clan.clanPoint = message.reader().readInt();
                       Char.myCharz().clan.level = message.reader().readByte();
                       GameCanvas.panel.myMember = new MyVector("GameCanvas.panel.myMember ");
                       for (int myMemberIndex = 0; myMemberIndex < Char.myCharz().clan.currMember; myMemberIndex++) {
                           Member entry7;
                           (entry7 = new Member()).ID = message.reader().readInt();
                           entry7.head = message.reader().readShort();
                           entry7.headICON = message.reader().readShort();
                           entry7.leg = message.reader().readShort();
                           entry7.body = message.reader().readShort();
                           entry7.name = message.reader().readUTF();
                           entry7.role = message.reader().readByte();
                           entry7.powerPoint = message.reader().readUTF();
                           entry7.donate = message.reader().readInt();
                           entry7.receive_donate = message.reader().readInt();
                           entry7.clanPoint = message.reader().readInt();
                           entry7.curClanPoint = message.reader().readInt();
                           entry7.joinTime = Firework.getDate(message.reader().readInt());
                           GameCanvas.panel.myMember.addElement(entry7);
                       }
                       iD3 = message.reader().readUnsignedByte();
                       for (int index3 = 0; index3 < iD3; index3++) {
                           readClanMsg(message, -1);
                       }
                       if (GameCanvas.panel.isSearchClan || GameCanvas.panel.isViewMember || GameCanvas.panel.isMessage) {
                           GameCanvas.panel.setTabClans();
                       }
                       break;
                   case -52:
                       byte packetByte16;
                       if ((packetByte16 = message.reader().readByte()) == 0) {
                           Member entry8;
                           (entry8 = new Member()).ID = message.reader().readInt();
                           entry8.head = message.reader().readShort();
                           entry8.headICON = message.reader().readShort();
                           entry8.leg = message.reader().readShort();
                           entry8.body = message.reader().readShort();
                           entry8.name = message.reader().readUTF();
                           entry8.role = message.reader().readByte();
                           entry8.powerPoint = message.reader().readUTF();
                           entry8.donate = message.reader().readInt();
                           entry8.receive_donate = message.reader().readInt();
                           entry8.clanPoint = message.reader().readInt();
                           entry8.joinTime = Firework.getDate(message.reader().readInt());
                           if (GameCanvas.panel.myMember == null) {
                               GameCanvas.panel.myMember = new MyVector("ameCanvas.panel.myMember");
                           }
                           GameCanvas.panel.myMember.addElement(entry8);
                           GameCanvas.panel.initTabClans();
                       }
                       if (packetByte16 == 1) {
                           GameCanvas.panel.myMember.removeElementAt(message.reader().readByte());
                           GameCanvas.panel.currentListLength--;
                           GameCanvas.panel.initTabClans();
                       }
                       if (packetByte16 == 2) {
                           Member member;
                           (member = new Member()).ID = message.reader().readInt();
                           member.head = message.reader().readShort();
                           member.headICON = message.reader().readShort();
                           member.leg = message.reader().readShort();
                           member.body = message.reader().readShort();
                           member.name = message.reader().readUTF();
                           member.role = message.reader().readByte();
                           member.powerPoint = message.reader().readUTF();
                           member.donate = message.reader().readInt();
                           member.receive_donate = message.reader().readInt();
                           member.clanPoint = message.reader().readInt();
                           member.joinTime = Firework.getDate(message.reader().readInt());
                           for (int myMemberIndex2 = 0; myMemberIndex2 < GameCanvas.panel.myMember.size(); myMemberIndex2++) {
                               Member myMember;
                               if ((myMember = (Member) GameCanvas.panel.myMember.elementAt(myMemberIndex2)).ID == member.ID) {
                                   if (Char.myCharz().charID == member.ID) {
                                       Char.myCharz().role = member.role;
                                   }
                                   GameCanvas.panel.myMember.removeElement(myMember);
                                   GameCanvas.panel.myMember.insertElementAt(member, myMemberIndex2);
                                   return;
                               }
                           }
                       }
                       break;
                   case -51:
                       InfoDlg.hide();
                       readClanMsg(message, 0);
                       if (GameCanvas.panel.isMessage && GameCanvas.panel.type == 5) {
                           GameCanvas.panel.initTabClans();
                       }
                       break;
                   case -50:
                       InfoDlg.hide();
                       GameCanvas.panel.member = new MyVector(".panel.member");
                       byte memberCount = message.reader().readByte();
                       for (int memberIndex = 0; memberIndex < memberCount; memberIndex++) {
                           Member entry9;
                           (entry9 = new Member()).ID = message.reader().readInt();
                           entry9.head = message.reader().readShort();
                           entry9.headICON = message.reader().readShort();
                           entry9.leg = message.reader().readShort();
                           entry9.body = message.reader().readShort();
                           entry9.name = message.reader().readUTF();
                           entry9.role = message.reader().readByte();
                           entry9.powerPoint = message.reader().readUTF();
                           entry9.donate = message.reader().readInt();
                           entry9.receive_donate = message.reader().readInt();
                           entry9.clanPoint = message.reader().readInt();
                           entry9.joinTime = Firework.getDate(message.reader().readInt());
                           GameCanvas.panel.member.addElement(entry9);
                       }
                       GameCanvas.panel.isViewMember = true;
                       GameCanvas.panel.isSearchClan = false;
                       GameCanvas.panel.isMessage = false;
                       GameCanvas.panel.currentListLength = GameCanvas.panel.member.size() + 2;
                       GameCanvas.panel.initTabClans();
                       break;
                   case -47:
                       InfoDlg.hide();
                       byte clanCount = message.reader().readByte();
                       Res.outz("clan = " + clanCount);
                       if (clanCount == 0) {
                           GameCanvas.panel.clanReport = mResources.cannot_find_clan;
                           GameCanvas.panel.clans = null;
                       } else {
                           GameCanvas.panel.clans = new Clan[clanCount];
                           Res.outz("clan search lent= " + GameCanvas.panel.clans.length);
                           for (int clanIndex = 0; clanIndex < GameCanvas.panel.clans.length; clanIndex++) {
                               GameCanvas.panel.clans[clanIndex] = new Clan();
                               GameCanvas.panel.clans[clanIndex].ID = message.reader().readInt();
                               GameCanvas.panel.clans[clanIndex].name = message.reader().readUTF();
                               GameCanvas.panel.clans[clanIndex].slogan = message.reader().readUTF();
                               GameCanvas.panel.clans[clanIndex].imgID = message.reader().readShort();
                               GameCanvas.panel.clans[clanIndex].powerPoint = message.reader().readUTF();
                               GameCanvas.panel.clans[clanIndex].leaderName = message.reader().readUTF();
                               GameCanvas.panel.clans[clanIndex].currMember = message.reader().readUnsignedByte();
                               GameCanvas.panel.clans[clanIndex].maxMember = message.reader().readUnsignedByte();
                               GameCanvas.panel.clans[clanIndex].date = message.reader().readInt();
                           }
                       }
                       GameCanvas.panel.isSearchClan = true;
                       GameCanvas.panel.isViewMember = false;
                       GameCanvas.panel.isMessage = false;
                       if (GameCanvas.panel.isSearchClan) {
                           GameCanvas.panel.initTabClans();
                       }
                       break;
                   case -46:
                       InfoDlg.hide();
                       byte packetByte17 = message.reader().readByte();
                       Res.outz("cAction= " + packetByte17);
                       if (packetByte17 == 1 || packetByte17 == 3) {
                           GameCanvas.endDlg();
                           ClanImage.vClanImage.removeAllElements();
                           short packetShort4 = message.reader().readShort();
                           for (int index4 = 0; index4 < packetShort4; index4++) {
                               ClanImage cm;
                               (cm = new ClanImage()).ID = message.reader().readShort();
                               cm.name = message.reader().readUTF();
                               cm.xu = message.reader().readInt();
                               cm.luong = message.reader().readInt();
                               if (!ClanImage.isExistClanImage(cm.ID)) {
                                   ClanImage.addClanImage(cm);
                               } else {
                                   ClanImage.getClanImage((short) cm.ID).name = cm.name;
                                   ClanImage.getClanImage((short) cm.ID).xu = cm.xu;
                                   ClanImage.getClanImage((short) cm.ID).luong = cm.luong;
                               }
                           }
                           if (Char.myCharz().clan != null) {
                               GameCanvas.panel.changeIcon();
                           }
                       }
                       if (packetByte17 == 4) {
                           Char.myCharz().clan.imgID = message.reader().readShort();
                           Char.myCharz().clan.slogan = message.reader().readUTF();
                           Res.outz("change slogan " + Char.myCharz().clan.slogan);
                       }
                       break;
                   case -45:
                       byte packetByte18 = message.reader().readByte();
                       int charId14 = message.reader().readInt();
                       short skillTemplateId = message.reader().readShort();
                       Res.outz(">.SKILL_NOT_FOCUS  skill type= " + packetByte18 + "   player use= " + charId14);
                       if (packetByte18 == 20) {
                           byte typeFrame = message.reader().readByte();
                           byte dir = message.reader().readByte();
                           short timeGong = message.reader().readShort();
                           boolean isFly = message.reader().readByte() != 0;
                           byte typePaint = message.reader().readByte();
                           byte typeItem = -1;
                           try {
                               typeItem = message.reader().readByte();
                           } catch (Exception ignoredException6) {
                           }
                           Res.outz(">.SKILL_NOT_FOCUS  skill playerDir= " + dir);
                           Char character11;
                           if (Char.myCharz().charID == charId14) {
                               character11 = Char.myCharz();
                           } else {
                               character11 = GameScr.findCharInMap(charId14);
                           }
                           character11.SetSkillPaint_NEW(skillTemplateId, isFly, typeFrame, typePaint, dir, timeGong, typeItem);
                       }
                       if (packetByte18 == 21) {
                           Point targetDame;
                           (targetDame = new Point()).x = message.reader().readShort();
                           targetDame.y = message.reader().readShort();
                           short timeDame = message.reader().readShort();
                           short rangeDame = message.reader().readShort();
                           byte typePaint2 = 0;
                           byte typeItem2 = -1;
                           Point[] listObj = null;
                           Char character12;
                           if (Char.myCharz().charID == charId14) {
                               character12 = Char.myCharz();
                           } else {
                               character12 = GameScr.findCharInMap(charId14);
                           }
                           try {
                               typePaint2 = message.reader().readByte();
                               byte pointCount;
                               if ((pointCount = message.reader().readByte()) > 0) {
                                   listObj = new Point[pointCount];
                                   for (int listObjIndex = 0; listObjIndex < listObj.length; listObjIndex++) {
                                       listObj[listObjIndex] = new Point();
                                       listObj[listObjIndex].type = message.reader().readByte();
                                       if (listObj[listObjIndex].type == 0) {
                                           listObj[listObjIndex].id = message.reader().readByte();
                                       } else {
                                           listObj[listObjIndex].id = message.reader().readInt();
                                       }
                                   }
                               }
                           } catch (Exception ignoredException7) {
                           }
                           try {
                               typeItem2 = message.reader().readByte();
                           } catch (Exception ignoredException8) {
                           }
                           Res.outz(">.SKILL_NOT_FOCUS  skill targetDame= " + targetDame.x + ":" + targetDame.y + "    c:" + character12.cx + ":" + character12.cy + "   cdir:" + character12.cdir);
                           character12.SetSkillPaint_STT(1, skillTemplateId, targetDame, timeDame, rangeDame, typePaint2, listObj, typeItem2);
                       }
                       if (packetByte18 == 0) {
                           Res.outz("id use= " + charId14);
                           if (Char.myCharz().charID != charId14) {
                               Char character13;
                               if ((TileMap.tileTypeAtPixel((character13 = GameScr.findCharInMap(charId14)).cx, character13.cy) & 2) == 2) {
                                   character13.setSkillPaint(GameScr.sks[skillTemplateId], 0);
                               } else {
                                   character13.setSkillPaint(GameScr.sks[skillTemplateId], 1);
                                   character13.delayFall = 20;
                               }
                           } else {
                               Char.myCharz();
                               Char.saveLoadPreviousSkill();
                               Res.outz("LOAD LAST SKILL");
                           }
                           byte packetByte19 = message.reader().readByte();
                           Res.outz("npc size= " + packetByte19);
                           for (int index5 = 0; index5 < packetByte19; index5++) {
                               byte mobIndex4 = message.reader().readByte();
                               byte seconds = message.reader().readByte();
                               Res.outz("index= " + mobIndex4);
                               if (skillTemplateId >= 42 && skillTemplateId <= 48) {
                                   ((Mob) GameScr.vMob.elementAt(mobIndex4)).isFreez = true;
                                   ((Mob) GameScr.vMob.elementAt(mobIndex4)).seconds = seconds;
                                   ((Mob) GameScr.vMob.elementAt(mobIndex4)).last = ((Mob) GameScr.vMob.elementAt(mobIndex4)).cur = System.currentTimeMillis();
                               }
                           }
                           byte packetByte20 = message.reader().readByte();
                           for (int index6 = 0; index6 < packetByte20; index6++) {
                               int charId15 = message.reader().readInt();
                               byte freezSeconds = message.reader().readByte();
                               Res.outz("player ID= " + charId15 + " my ID= " + Char.myCharz().charID);
                               if (skillTemplateId >= 42 && skillTemplateId <= 48) {
                                   if (charId15 == Char.myCharz().charID) {
                                       if (!Char.myCharz().isFlyAndCharge && !Char.myCharz().isStandAndCharge) {
                                           GameScr.gI().isFreez = true;
                                           Char.myCharz().isFreez = true;
                                           Char.myCharz().freezSeconds = freezSeconds;
                                           Char.myCharz().lastFreez = Char.myCharz().currFreez = System.currentTimeMillis();
                                           Char.myCharz().isLockMove = true;
                                       }
                                   } else {
                                       Char character14;
                                       if ((character14 = GameScr.findCharInMap(charId15)) != null && !character14.isFlyAndCharge && !character14.isStandAndCharge) {
                                           character14.isFreez = true;
                                           character14.seconds = freezSeconds;
                                           character14.freezSeconds = freezSeconds;
                                           character14.lastFreez = GameScr.findCharInMap(charId15).currFreez = System.currentTimeMillis();
                                       }
                                   }
                               }
                           }
                       }
                       if (packetByte18 == 1 && charId14 != Char.myCharz().charID) {
                           try {
                               GameScr.findCharInMap(charId14).isCharge = true;
                           } catch (Exception exception) {
                               exception.printStackTrace();
                           }
                       }
                       if (packetByte18 == 3) {
                           if (charId14 == Char.myCharz().charID) {
                               Char.myCharz().isCharge = false;
                               SoundMn.gI();
                               SoundMn.taitaoPause();
                               Char.myCharz();
                               Char.saveLoadPreviousSkill();
                           } else {
                               try {
                                   GameScr.findCharInMap(charId14).isCharge = false;
                               } catch (Exception exception2) {
                                   exception2.printStackTrace();
                               }
                           }
                       }
                       if (packetByte18 == 4) {
                           if (charId14 == Char.myCharz().charID) {
                               Char.myCharz().seconds = message.reader().readShort() - 1000;
                               Char.myCharz().last = System.currentTimeMillis();
                               Res.outz(" <1> Tra ve thoi gian gong   = " + Char.myCharz().seconds + " last= " + Char.myCharz().last);
                           } else if (GameScr.findCharInMap(charId14) != null) {
                               Char character15;
                               int cgender;
                               if ((cgender = (character15 = GameScr.findCharInMap(charId14)).cgender) == 0) {
                                   if (TileMap.mapID != 170) {
                                       character15.useChargeSkill(false);
                                   } else {
                                       if (skillTemplateId >= 77 && skillTemplateId <= 83) {
                                           character15.useChargeSkill(true);
                                       }
                                       if (skillTemplateId >= 70 && skillTemplateId <= 76) {
                                           character15.useChargeSkill(false);
                                       }
                                   }
                               } else if (cgender == 1) {
                                   if (TileMap.mapID != 170) {
                                       character15.useChargeSkill(true);
                                   } else {
                                       boolean isGround = true;
                                       if (skillTemplateId >= 70 && skillTemplateId <= 76) {
                                           isGround = false;
                                       }
                                       if (skillTemplateId >= 77 && skillTemplateId <= 83) {
                                           isGround = true;
                                       }
                                       character15.useChargeSkill(isGround);
                                   }
                               } else if (TileMap.mapID == 170) {
                                   boolean isGround2 = true;
                                   if (skillTemplateId >= 70 && skillTemplateId <= 76) {
                                       isGround2 = false;
                                   }
                                   if (skillTemplateId >= 77 && skillTemplateId <= 83) {
                                       isGround2 = true;
                                   }
                                   character15.useChargeSkill(isGround2);
                               }
                               character15.skillTemplateId = skillTemplateId;
                               if (skillTemplateId >= 70 && skillTemplateId <= 76) {
                                   character15.isUseSkillAfterCharge = true;
                               }
                               character15.seconds = message.reader().readShort();
                               character15.last = System.currentTimeMillis();
                           }
                       }
                       if (packetByte18 == 5) {
                           if (charId14 == Char.myCharz().charID) {
                               Char.myCharz().stopUseChargeSkill();
                           } else if (GameScr.findCharInMap(charId14) != null) {
                               GameScr.findCharInMap(charId14).stopUseChargeSkill();
                           }
                       }
                       if (packetByte18 == 6) {
                           if (charId14 == Char.myCharz().charID) {
                               Char.myCharz().setAutoSkillPaint(GameScr.sks[skillTemplateId], 0);
                           } else if (GameScr.findCharInMap(charId14) != null) {
                               GameScr.findCharInMap(charId14).setAutoSkillPaint(GameScr.sks[skillTemplateId], 0);
                               SoundMn.gI();
                               SoundMn.gong();
                           }
                       }
                       if (packetByte18 == 7) {
                           if (charId14 == Char.myCharz().charID) {
                               Char.myCharz().seconds = message.reader().readShort();
                               Res.outz("second = " + Char.myCharz().seconds);
                               Char.myCharz().last = System.currentTimeMillis();
                           } else if (GameScr.findCharInMap(charId14) != null) {
                               GameScr.findCharInMap(charId14).useChargeSkill(true);
                               GameScr.findCharInMap(charId14).seconds = message.reader().readShort();
                               GameScr.findCharInMap(charId14).last = System.currentTimeMillis();
                               SoundMn.gI();
                               SoundMn.gong();
                           }
                       }
                       if (packetByte18 == 8 && charId14 != Char.myCharz().charID && GameScr.findCharInMap(charId14) != null) {
                           GameScr.findCharInMap(charId14).setAutoSkillPaint(GameScr.sks[skillTemplateId], 0);
                       }
                       break;
                   case -44:
                       boolean condition6 = false;
                       if (GameCanvas.w > 2 * Panel.WIDTH_PANEL) {
                           condition6 = true;
                       }
                       byte typeShop = message.reader().readByte();
                       int shopTabNameIndex = message.reader().readUnsignedByte();
                       Char.myCharz().arrItemShop = new Item[shopTabNameIndex][];
                       GameCanvas.panel.shopTabName = new String[shopTabNameIndex + (!condition6 ? 1 : 0)][2];
                       if (typeShop == 2) {
                           GameCanvas.panel.maxPageShop = new int[shopTabNameIndex];
                           GameCanvas.panel.currPageShop = new int[shopTabNameIndex];
                       }
                       if (!condition6) {
                           GameCanvas.panel.shopTabName[shopTabNameIndex] = mResources.inventory;
                       }
                       for (int itemShopIndex = 0; itemShopIndex < shopTabNameIndex; itemShopIndex++) {
                           String[] parts2 = Res.split(message.reader().readUTF(), "\n", 0);
                           if (typeShop == 2) {
                               GameCanvas.panel.maxPageShop[itemShopIndex] = message.reader().readUnsignedByte();
                           }
                           if (parts2.length == 2) {
                               GameCanvas.panel.shopTabName[itemShopIndex] = parts2;
                           }
                           if (parts2.length == 1) {
                               GameCanvas.panel.shopTabName[itemShopIndex][0] = parts2[0];
                               GameCanvas.panel.shopTabName[itemShopIndex][1] = "";
                           }
                           int itemShopCount = message.reader().readUnsignedByte();
                           Char.myCharz().arrItemShop[itemShopIndex] = new Item[itemShopCount];
                           Panel.strWantToBuy = mResources.say_wat_do_u_want_to_buy;
                           if (typeShop == 1) {
                               Panel.strWantToBuy = mResources.say_wat_do_u_want_to_buy2;
                           }
                           for (int itemShopIndex2 = 0; itemShopIndex2 < itemShopCount; itemShopIndex2++) {
                               short id4;
                               if ((id4 = message.reader().readShort()) != -1) {
                                   Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2] = new Item();
                                   Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].template = ItemTemplates.get(id4);
                                   Panel.strWantToBuy = mResources.say_wat_do_u_want_to_buy;
                                   if (typeShop == 8) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyCoin = message.reader().readInt();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyGold = message.reader().readInt();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].quantity = message.reader().readInt();
                                   } else if (typeShop == 4) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].reason = message.reader().readUTF();
                                   } else if (typeShop == 0) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyCoin = message.reader().readInt();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyGold = message.reader().readInt();
                                   } else if (typeShop == 1) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].powerRequire = message.reader().readLong();
                                       Panel.strWantToBuy = mResources.say_wat_do_u_want_to_buy2;
                                   } else if (typeShop == 2) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].itemId = message.reader().readShort();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyCoin = message.reader().readInt();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyGold = message.reader().readInt();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buyType = message.reader().readByte();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].quantity = message.reader().readInt();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].isMe = message.reader().readByte();
                                   } else if (typeShop == 3) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].isBuySpec = true;
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].iconSpec = message.reader().readShort();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].buySpec = message.reader().readInt();
                                   }
                                   int itemOptionCount3;
                                   if ((itemOptionCount3 = message.reader().readUnsignedByte()) != 0) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].itemOption = new ItemOption[itemOptionCount3];
                                       for (int itemOptionIndex3 = 0; itemOptionIndex3 < Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].itemOption.length; itemOptionIndex3++) {
                                           ItemOption itemOption3;
                                           if ((itemOption3 = readItemOption(message)) != null) {
                                               Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].itemOption[itemOptionIndex3] = itemOption3;
                                               Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].compare = GameCanvas.panel.getCompare(Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2]);
                                           }
                                       }
                                   }
                                   byte packetByte21 = message.reader().readByte();
                                   Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].newItem = packetByte21 != 0;
                                   if (message.reader().readByte() == 1) {
                                       short headTemp = message.reader().readShort();
                                       short bodyTemp = message.reader().readShort();
                                       short legTemp = message.reader().readShort();
                                       short bagTemp = message.reader().readShort();
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].setPartTemp(headTemp, bodyTemp, legTemp, bagTemp);
                                   }
                                   if (typeShop == 2 && GameMidlet.intVERSION >= 237) {
                                       Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].nameNguoiKyGui = message.reader().readUTF();
                                       Res.err("nguoi ki gui  " + Char.myCharz().arrItemShop[itemShopIndex][itemShopIndex2].nameNguoiKyGui);
                                   }
                               }
                           }
                       }
                       if (condition6) {
                           if (typeShop != 2) {
                               (GameCanvas.panel2 = new Panel()).tabName[7] = new String[][] { { "" } };
                               GameCanvas.panel2.setTypeBodyOnly();
                               GameCanvas.panel2.show();
                           } else {
                               (GameCanvas.panel2 = new Panel()).setTypeKiGuiOnly();
                               GameCanvas.panel2.show();
                           }
                       }
                       GameCanvas.panel.tabName[1] = GameCanvas.panel.shopTabName;
                       if (typeShop == 2) {
                           String[][] tabName = GameCanvas.panel.tabName[1];
                           if (condition6) {
                               GameCanvas.panel.tabName[1] = new String[][] { tabName[0], tabName[1], tabName[2], tabName[3] };
                           } else {
                               GameCanvas.panel.tabName[1] = new String[][] { tabName[0], tabName[1], tabName[2], tabName[3], tabName[4] };
                           }
                       }
                       GameCanvas.panel.setTypeShop(typeShop);
                       GameCanvas.panel.show();
                       break;
                   case -43:
                       byte itemAction = message.reader().readByte();
                       byte where = message.reader().readByte();
                       byte index7 = message.reader().readByte();
                       String info2 = message.reader().readUTF();
                       GameCanvas.panel.itemRequest(itemAction, info2, where, index7);
                       break;
                   case -42:
                       Char.myCharz().cHPGoc = message.readInt3Byte();
                       Char.myCharz().cMPGoc = message.readInt3Byte();
                       Char.myCharz().cDamGoc = message.reader().readInt();
                       Char.myCharz().cHPFull = message.reader().readLong();
                       Char.myCharz().cMPFull = message.reader().readLong();
                       Char.myCharz().cHP = message.reader().readLong();
                       Char.myCharz().cMP = message.reader().readLong();
                       Char.myCharz().cspeed = message.reader().readByte();
                       Char.myCharz().hpFrom1000TiemNang = message.reader().readByte();
                       Char.myCharz().mpFrom1000TiemNang = message.reader().readByte();
                       Char.myCharz().damFrom1000TiemNang = message.reader().readByte();
                       Char.myCharz().cDamFull = message.reader().readLong();
                       Char.myCharz().cDefull = message.reader().readLong();
                       Char.myCharz().cCriticalFull = message.reader().readByte();
                       Char.myCharz().cTiemNang = message.reader().readLong();
                       Char.myCharz().expForOneAdd = message.reader().readShort();
                       Char.myCharz().cDefGoc = message.reader().readInt();
                       Char.myCharz().cCriticalGoc = message.reader().readByte();
                       Char.myCharz().cGiamST = message.reader().readByte();
                       Char.myCharz().cCritDameFull = message.reader().readShort();
                       InfoDlg.hide();
                       break;
                   case -41:
                       byte strLevelCount = message.reader().readByte();
                       Char.myCharz().strLevel = new String[strLevelCount];
                       for (int strLevelIndex = 0; strLevelIndex < strLevelCount; strLevelIndex++) {
                           String packetText2 = message.reader().readUTF();
                           Char.myCharz().strLevel[strLevelIndex] = packetText2;
                       }
                       break;
                   case -37:
                       byte packetByte22 = message.reader().readByte();
                       Res.outz("cAction= " + packetByte22);
                       if (packetByte22 == 0) {
                           Char.myCharz().head = message.reader().readShort();
                           Char.myCharz().setDefaultPart();
                           int itemBodyCount2 = message.reader().readUnsignedByte();
                           Res.outz("num body = " + itemBodyCount2);
                           Char.myCharz().arrItemBody = new Item[itemBodyCount2];
                           for (int itemBodyIndex2 = 0; itemBodyIndex2 < itemBodyCount2; itemBodyIndex2++) {
                               short id5;
                               if ((id5 = message.reader().readShort()) != -1) {
                                   Char.myCharz().arrItemBody[itemBodyIndex2] = new Item();
                                   Char.myCharz().arrItemBody[itemBodyIndex2].template = ItemTemplates.get(id5);
                                   byte type2 = Char.myCharz().arrItemBody[itemBodyIndex2].template.type;
                                   Char.myCharz().arrItemBody[itemBodyIndex2].quantity = message.reader().readInt();
                                   message.reader().readUTF();
                                   message.reader().readUTF();
                                   int itemOptionCount4;
                                   if ((itemOptionCount4 = message.reader().readUnsignedByte()) != 0) {
                                       Char.myCharz().arrItemBody[itemBodyIndex2].itemOption = new ItemOption[itemOptionCount4];
                                       for (int itemOptionIndex4 = 0; itemOptionIndex4 < Char.myCharz().arrItemBody[itemBodyIndex2].itemOption.length; itemOptionIndex4++) {
                                           ItemOption itemOption4;
                                           if ((itemOption4 = readItemOption(message)) != null) {
                                               Char.myCharz().arrItemBody[itemBodyIndex2].itemOption[itemOptionIndex4] = itemOption4;
                                           }
                                       }
                                   }
                                   if (type2 == 0) {
                                       Char.myCharz().body = Char.myCharz().arrItemBody[itemBodyIndex2].template.part;
                                   } else if (type2 == 1) {
                                       Char.myCharz().leg = Char.myCharz().arrItemBody[itemBodyIndex2].template.part;
                                   }
                               }
                           }
                       }
                       Res.outz("head= " + Char.myCharz().head + " body= " + Char.myCharz().body + " leg= " + Char.myCharz().leg);
                       break;
                   case -36:
                       byte packetByte23 = message.reader().readByte();
                       Res.outz("cAction= " + packetByte23);
                       GameScr.isudungCapsun4 = false;
                       GameScr.isudungCapsun3 = false;
                       if (packetByte23 == 0) {
                           int itemCount = message.reader().readUnsignedByte();
                           Char.myCharz().arrItemBag = new Item[itemCount];
                           GameScr.hpPotion = 0;
                           Res.outz("numC=" + itemCount);
                           for (int indexUI = 0; indexUI < itemCount; indexUI++) {
                               short id6;
                               if ((id6 = message.reader().readShort()) != -1) {
                                   Char.myCharz().arrItemBag[indexUI] = new Item();
                                   Char.myCharz().arrItemBag[indexUI].template = ItemTemplates.get(id6);
                                   Char.myCharz().arrItemBag[indexUI].quantity = message.reader().readInt();
                                   message.reader().readUTF();
                                   message.reader().readUTF();
                                   Char.myCharz().arrItemBag[indexUI].indexUI = indexUI;
                                   int itemOptionCount5;
                                   if ((itemOptionCount5 = message.reader().readUnsignedByte()) != 0) {
                                       Char.myCharz().arrItemBag[indexUI].itemOption = new ItemOption[itemOptionCount5];
                                       for (int itemOptionIndex5 = 0; itemOptionIndex5 < Char.myCharz().arrItemBag[indexUI].itemOption.length; itemOptionIndex5++) {
                                           ItemOption itemOption5;
                                           if ((itemOption5 = readItemOption(message)) != null) {
                                               Char.myCharz().arrItemBag[indexUI].itemOption[itemOptionIndex5] = itemOption5;
                                           }
                                       }
                                       Char.myCharz().arrItemBag[indexUI].compare = GameCanvas.panel.getCompare(Char.myCharz().arrItemBag[indexUI]);
                                   }
                                   if (Char.myCharz().arrItemBag[indexUI].template.type == 6) {
                                       GameScr.hpPotion = GameScr.hpPotion + Char.myCharz().arrItemBag[indexUI].quantity;
                                   }
                                   if (Char.myCharz().arrItemBag[indexUI].template.id == 194) {
                                       GameScr.isudungCapsun4 = Char.myCharz().arrItemBag[indexUI].quantity > 0;
                                   } else if (Char.myCharz().arrItemBag[indexUI].template.id == 193 && !GameScr.isudungCapsun4) {
                                       GameScr.isudungCapsun3 = Char.myCharz().arrItemBag[indexUI].quantity > 0;
                                   }
                               }
                           }
                       }
                       if (packetByte23 == 2) {
                           byte itemBagIndex3 = message.reader().readByte();
                           int quantity = message.reader().readInt();
                           int quantity2 = Char.myCharz().arrItemBag[itemBagIndex3].quantity;
                           short id7 = Char.myCharz().arrItemBag[itemBagIndex3].template.id;
                           Char.myCharz().arrItemBag[itemBagIndex3].quantity = quantity;
                           if (Char.myCharz().arrItemBag[itemBagIndex3].quantity < quantity2 && Char.myCharz().arrItemBag[itemBagIndex3].template.type == 6) {
                               GameScr.hpPotion = GameScr.hpPotion - (quantity2 - Char.myCharz().arrItemBag[itemBagIndex3].quantity);
                           }
                           if (Char.myCharz().arrItemBag[itemBagIndex3].quantity == 0) {
                               Char.myCharz().arrItemBag[itemBagIndex3] = null;
                           }
                           if (id7 == 194) {
                               GameScr.isudungCapsun4 = quantity > 0;
                           } else if (id7 == 193) {
                               GameScr.isudungCapsun3 = quantity > 0;
                           }
                       }
                       break;
                   case -35:
                       byte packetByte24 = message.reader().readByte();
                       Res.outz("cAction= " + packetByte24);
                       if (packetByte24 == 0) {
                           int itemBoxCount = message.reader().readUnsignedByte();
                           Char.myCharz().arrItemBox = new Item[itemBoxCount];
                           GameCanvas.panel.hasUse = 0;
                           for (int itemBoxIndex = 0; itemBoxIndex < itemBoxCount; itemBoxIndex++) {
                               short id8;
                               if ((id8 = message.reader().readShort()) != -1) {
                                   Char.myCharz().arrItemBox[itemBoxIndex] = new Item();
                                   Char.myCharz().arrItemBox[itemBoxIndex].template = ItemTemplates.get(id8);
                                   Char.myCharz().arrItemBox[itemBoxIndex].quantity = message.reader().readInt();
                                   message.reader().readUTF();
                                   message.reader().readUTF();
                                   int itemOptionCount6;
                                   if ((itemOptionCount6 = message.reader().readUnsignedByte()) != 0) {
                                       Char.myCharz().arrItemBox[itemBoxIndex].itemOption = new ItemOption[itemOptionCount6];
                                       for (int itemOptionIndex6 = 0; itemOptionIndex6 < Char.myCharz().arrItemBox[itemBoxIndex].itemOption.length; itemOptionIndex6++) {
                                           ItemOption itemOption6;
                                           if ((itemOption6 = readItemOption(message)) != null) {
                                               Char.myCharz().arrItemBox[itemBoxIndex].itemOption[itemOptionIndex6] = itemOption6;
                                           }
                                       }
                                   }
                                   GameCanvas.panel.hasUse++;
                               }
                           }
                       }
                       if (packetByte24 == 1) {
                           boolean isBoxClan = false;
                           try {
                               if (message.reader().readByte() == 1) {
                                   isBoxClan = true;
                               }
                           } catch (Exception ignoredException9) {
                           }
                           GameCanvas.panel.setTypeBox();
                           GameCanvas.panel.isBoxClan = isBoxClan;
                           GameCanvas.panel.show();
                       }
                       if (packetByte24 == 2) {
                           byte itemBoxIndex2 = message.reader().readByte();
                           int quantity3 = message.reader().readInt();
                           Char.myCharz().arrItemBox[itemBoxIndex2].quantity = quantity3;
                           if (Char.myCharz().arrItemBox[itemBoxIndex2].quantity == 0) {
                               Char.myCharz().arrItemBox[itemBoxIndex2] = null;
                           }
                       }
                       break;
                   case -34:
                       byte packetByte25 = message.reader().readByte();
                       Res.outz("act= " + packetByte25);
                       if (packetByte25 == 0 && GameScr.gI().magicTree != null) {
                           Res.outz("toi duoc day");
                           MagicTree magicTree;
                           (magicTree = GameScr.gI().magicTree).id = message.reader().readShort();
                           magicTree.name = message.reader().readUTF();
                           magicTree.name = Res.changeString(magicTree.name);
                           magicTree.x = message.reader().readShort();
                           magicTree.y = message.reader().readShort();
                           magicTree.level = message.reader().readByte();
                           magicTree.currPeas = message.reader().readShort();
                           magicTree.maxPeas = message.reader().readShort();
                           Res.outz("curr Peas= " + magicTree.currPeas);
                           magicTree.strInfo = message.reader().readUTF();
                           magicTree.seconds = message.reader().readInt();
                           magicTree.timeToRecieve = magicTree.seconds;
                           byte peaPostionXCount = message.reader().readByte();
                           magicTree.peaPostionX = new int[peaPostionXCount];
                           magicTree.peaPostionY = new int[peaPostionXCount];
                           for (int peaPostionXIndex = 0; peaPostionXIndex < peaPostionXCount; peaPostionXIndex++) {
                               magicTree.peaPostionX[peaPostionXIndex] = message.reader().readByte();
                               magicTree.peaPostionY[peaPostionXIndex] = message.reader().readByte();
                           }
                           magicTree.isUpdate = message.reader().readBoolean();
                           magicTree.last = magicTree.cur = System.currentTimeMillis();
                           GameScr.gI().magicTree.isUpdateTree = true;
                       }
                       if (packetByte25 == 1) {
                           MyVector menuItems = new MyVector("vmenu controle 2");
                           try {
                               while (message.reader().available() > 0) {
                                   String caption2 = message.reader().readUTF();
                                   menuItems.addElement(new Command(caption2, GameCanvas.instance, 888392, null));
                               }
                           } catch (Exception ignoredException10) {
                           }
                           GameCanvas.menu.startAt(menuItems);
                       }
                       if (packetByte25 == 2) {
                           GameScr.gI().magicTree.remainPeas = message.reader().readShort();
                           Res.outz("Curr pea= " + GameScr.gI().magicTree.remainPeas);
                           GameScr.gI().magicTree.seconds = message.reader().readInt();
                           GameScr.gI().magicTree.last = GameScr.gI().magicTree.cur = System.currentTimeMillis();
                           GameScr.gI().magicTree.isUpdateTree = true;
                           GameScr.gI().magicTree.isPeasEffect = true;
                       }
                       break;
                   case -32:
                       short packetShort5 = message.reader().readShort();
                       Res.outz("BACK GROUND template id= " + packetShort5);
                       int dataCount = message.reader().readInt();
                       Object data6 = null;
                       Object image = null;
                       try {
                           data6 = new byte[dataCount];
                           message.reader().read((byte[]) data6, 0, dataCount);
                           image = Image.createImage((byte[]) data6, 0, dataCount);
                           BgItem.imgNew.put(String.valueOf((int) packetShort5), image);
                       } catch (Exception ignoredException11) {
                           data6 = null;
                           BgItem.imgNew.put(String.valueOf((int) packetShort5), Image.createRGBImage(new int[] { -2013265920 }, 1, 1, true));
                       }
                       if (data6 != null && mGraphics.zoomLevel > 1) {
                           Rms.saveRMS(mGraphics.zoomLevel + "bgItem" + packetShort5, (byte[]) data6);
                       }
                       break;
                   case -31:
                       TileMap.vItemBg.removeAllElements();
                       short itemBgCount = message.reader().readShort();
                       Res.outz("nItem= " + itemBgCount);
                       for (int id9 = 0; id9 < itemBgCount; id9 += 1) {
                           BgItem entry10;
                           (entry10 = new BgItem()).id = id9;
                           entry10.idImage = message.reader().readShort();
                           entry10.layer = message.reader().readByte();
                           entry10.dx = message.reader().readShort();
                           entry10.dy = message.reader().readShort();
                           byte intValueCount = message.reader().readByte();
                           entry10.tileX = new int[intValueCount];
                           entry10.tileY = new int[intValueCount];
                           for (int intValueIndex = 0; intValueIndex < intValueCount; intValueIndex += 1) {
                               entry10.tileX[id9] = message.reader().readByte();
                               entry10.tileY[id9] = message.reader().readByte();
                           }
                           TileMap.vItemBg.addElement(entry10);
                       }
                       break;
                   case -30:
                       this.messageSubCommand(message);
                       break;
                   case -29:
                       messageNotLogin(message);
                       break;
                   case -28:
                       this.messageNotMap(message);
                       break;
                   case -26:
                       ServerListScreen.testConnect = 2;
                       GameCanvas.debug();
                       GameCanvas.startOKDlg(message.reader().readUTF());
                       InfoDlg.hide();
                       LoginScr.isContinueToLogin = false;
                       Char.isLoadingMap = false;
                       if (GameCanvas.currentScreen == GameCanvas.loginScr) {
                           GameCanvas.serverScreen.switchToMe();
                       }
                       break;
                   case -25:
                       GameCanvas.debug();
                       GameScr.info1.addInfo(message.reader().readUTF(), 0);
                       break;
                   case -24:
                       Res.outz("***************MAP_INFO**************");
                       GameScr.isPickNgocRong = false;
                       Char.isLoadingMap = true;
                       Res.outz("GET MAP INFO");
                       GameScr.gI().magicTree = null;
                       GameCanvas.isLoading = true;
                       GameCanvas.debug();
                       GameScr.resetAllvector();
                       GameCanvas.endDlg();
                       TileMap.vGo.removeAllElements();
                       PopUp.vPopups.removeAllElements();
                       System.gc();
                       TileMap.mapID = message.reader().readUnsignedByte();
                       TileMap.planetID = message.reader().readByte();
                       TileMap.tileID = message.reader().readByte();
                       TileMap.bgID = message.reader().readByte();
                       GameScr.isPaint_CT = TileMap.mapID != 170;
                       TileMap.typeMap = message.reader().readByte();
                       TileMap.mapName = message.reader().readUTF();
                       TileMap.zoneID = message.reader().readByte();
                       GameCanvas.debug();
                       try {
                           TileMap.loadMapFromResource(TileMap.mapID);
                       } catch (Exception ignoredException12) {
                           Service.gI().requestMaptemplate(TileMap.mapID);
                           this.messWait = message;
                           break;
                       }
                       this.loadInfoMap(message);
                       try {
                           TileMap.isMapDouble = message.reader().readByte() != 0;
                       } catch (Exception ignoredException13) {
                       }
                       GameScr.cmx = GameScr.cmtoX;
                       GameScr.cmy = GameScr.cmtoY;
                       GameCanvas.isRequestMapID = 2;
                       GameCanvas.waitingTimeChangeMap = mSystem.currentTimeMillis() + 1000L;
                       break;
                   case -22:
                       GameCanvas.debug();
                       Char.isLockKey = true;
                       Char.ischangingMap = true;
                       GameScr.gI().timeStartMap = 0;
                       GameScr.gI().timeLengthMap = 0;
                       Char.myCharz().mobFocus = null;
                       Char.myCharz().npcFocus = null;
                       Char.myCharz().charFocus = null;
                       Char.myCharz().itemFocus = null;
                       Char.myCharz().focus.removeAllElements();
                       Char.myCharz().testCharId = -9999;
                       Char.myCharz().killCharId = -9999;
                       GameCanvas.resetBg();
                       GameScr.gI().resetButton();
                       GameScr.gI().center = null;
                       if (Effect.vEffData.size() > 15) {
                           for (int index8 = 0; index8 < 5; index8++) {
                               Effect.vEffData.removeElementAt(0);
                           }
                       }
                       break;
                   case -21:
                       GameCanvas.debug();
                       short packetShort6 = message.reader().readShort();
                       for (int itemMapIndex = 0; itemMapIndex < GameScr.vItemMap.size(); itemMapIndex++) {
                           if (((ItemMap) GameScr.vItemMap.elementAt(itemMapIndex)).itemMapID == packetShort6) {
                               GameScr.vItemMap.removeElementAt(itemMapIndex);
                               break label4488;
                           }
                       }
                       break;
                   case -20:
                       GameCanvas.debug();
                       Char.myCharz().itemFocus = null;
                       short packetShort7 = message.reader().readShort();
                       for (int itemMapIndex2 = 0; itemMapIndex2 < GameScr.vItemMap.size(); itemMapIndex2++) {
                           ItemMap itemMap;
                           if ((itemMap = (ItemMap) GameScr.vItemMap.elementAt(itemMapIndex2)).itemMapID == packetShort7) {
                               itemMap.setPoint(Char.myCharz().cx, Char.myCharz().cy - 10);
                               String s = message.reader().readUTF();
                               short luong = 0;
                               try {
                                   luong = message.reader().readShort();
                                   if (itemMap.template.type == 9) {
                                       luong = message.reader().readShort();
                                       Char.myCharz().xu += (long) luong;
                                       Char.myCharz().xuStr = Res.formatNumber(Char.myCharz().xu);
                                   } else if (itemMap.template.type == 10) {
                                       luong = message.reader().readShort();
                                       Char.myCharz().luong += luong;
                                       Char.myCharz().luongStr = mSystem.numberTostring((long) Char.myCharz().luong);
                                   } else if (itemMap.template.type == 34) {
                                       luong = message.reader().readShort();
                                       Char.myCharz().luongKhoa += luong;
                                       Char.myCharz().luongKhoaStr = mSystem.numberTostring((long) Char.myCharz().luongKhoa);
                                   }
                               } catch (Exception ignoredException14) {
                               }
                               if (s.equals("")) {
                                   if (itemMap.template.type == 9) {
                                       GameScr.startFlyText((luong < 0 ? "" : "+") + luong, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch, 0, -2, 1);
                                       SoundMn.gI();
                                       SoundMn.getItem();
                                   } else if (itemMap.template.type == 10) {
                                       GameScr.startFlyText((luong < 0 ? "" : "+") + luong, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch, 0, -2, 2);
                                       SoundMn.gI();
                                       SoundMn.getItem();
                                   } else if (itemMap.template.type == 34) {
                                       GameScr.startFlyText((luong < 0 ? "" : "+") + luong, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch, 0, -2, 0);
                                       SoundMn.gI();
                                       SoundMn.getItem();
                                   } else {
                                       GameScr.info1.addInfo(mResources.you_receive + " " + (luong > 0 ? luong + " " : "") + itemMap.template.name, 0);
                                       SoundMn.gI();
                                       SoundMn.getItem();
                                   }
                                   if (luong > 0 && Char.myCharz().petFollow != null && Char.myCharz().petFollow.smallID == 4683) {
                                       ServerEffect.addServerEffect(55, Char.myCharz().petFollow.cmx, Char.myCharz().petFollow.cmy, 1);
                                       ServerEffect.addServerEffect(55, Char.myCharz().cx, Char.myCharz().cy, 1);
                                   }
                               } else if (s.length() != 1) {
                                   GameScr.info1.addInfo(s, 0);
                               }
                               break label4488;
                           }
                       }
                       break;
                   case -19:
                       GameCanvas.debug();
                       short packetShort8 = message.reader().readShort();
                       Char character16 = GameScr.findCharInMap(message.reader().readInt());
                       for (int itemMapIndex3 = 0; itemMapIndex3 < GameScr.vItemMap.size(); itemMapIndex3++) {
                           ItemMap itemMap2;
                           if ((itemMap2 = (ItemMap) GameScr.vItemMap.elementAt(itemMapIndex3)).itemMapID == packetShort8) {
                               if (character16 == null) {
                                   return;
                               }
                               itemMap2.setPoint(character16.cx, character16.cy - 10);
                               if (itemMap2.x < character16.cx) {
                                   character16.cdir = -1;
                               } else if (itemMap2.x > character16.cx) {
                                   character16.cdir = 1;
                               }
                               break label4488;
                           }
                       }
                       break;
                   case -18:
                       GameCanvas.debug();
                       byte itemBagIndex4 = message.reader().readByte();
                       GameScr.vItemMap.addElement(new ItemMap(message.reader().readShort(), Char.myCharz().arrItemBag[itemBagIndex4].template.id, Char.myCharz().cx, Char.myCharz().cy, message.reader().readShort(), message.reader().readShort()));
                       Char.myCharz().arrItemBag[itemBagIndex4] = null;
                       break;
                   case -14:
                       GameCanvas.debug();
                       Char character17;
                       if ((character17 = GameScr.findCharInMap(message.reader().readInt())) == null) {
                           return;
                       }
                       GameScr.vItemMap.addElement(new ItemMap(message.reader().readShort(), message.reader().readShort(), character17.cx, character17.cy, message.reader().readShort(), message.reader().readShort()));
                       break;
                   case -4:
                       GameCanvas.debug();
                       Char character18;
                       if ((character18 = GameScr.findCharInMap(message.reader().readInt())) == null) {
                           return;
                       }
                       GameCanvas.debug();
                       if ((TileMap.tileTypeAtPixel(character18.cx, character18.cy) & 2) == 2) {
                           character18.setSkillPaint(GameScr.sks[message.reader().readUnsignedByte()], 0);
                       } else {
                           character18.setSkillPaint(GameScr.sks[message.reader().readUnsignedByte()], 1);
                       }
                       GameCanvas.debug();
                       character18.attMobs = new Mob[message.reader().readByte()];
                       for (int attMobIndex = 0; attMobIndex < character18.attMobs.length; attMobIndex += 1) {
                           Mob mob3 = (Mob) GameScr.vMob.elementAt(message.reader().readByte());
                           character18.attMobs[attMobIndex] = mob3;
                           if (attMobIndex == 0) {
                               if (character18.cx <= mob3.x) {
                                   character18.cdir = 1;
                               } else {
                                   character18.cdir = -1;
                               }
                           }
                       }
                       GameCanvas.debug();
                       character18.charFocus = null;
                       character18.mobFocus = character18.attMobs[0];
                       Char[] characters2 = new Char[10];
                       int index9 = 0;
                       try {
                           for (index9 = 0; index9 < characters2.length; index9 += 1) {
                               int charId16;
                               Char character19;
                               if ((charId16 = message.reader().readInt()) == Char.myCharz().charID) {
                                   character19 = Char.myCharz();
                               } else {
                                   character19 = GameScr.findCharInMap(charId16);
                               }
                               characters2[index9] = character19;
                               if (index9 == 0) {
                                   if (character18.cx <= character19.cx) {
                                       character18.cdir = 1;
                                   } else {
                                       character18.cdir = -1;
                                   }
                               }
                           }
                       } catch (Exception ignoredException15) {
                       }
                       GameCanvas.debug();
                       if (index9 > 0) {
                           character18.attChars = new Char[index9];
                           for (int attCharIndex2 = 0; attCharIndex2 < character18.attChars.length; attCharIndex2 += 1) {
                               character18.attChars[attCharIndex2] = characters2[attCharIndex2];
                           }
                           character18.charFocus = character18.attChars[0];
                           character18.mobFocus = null;
                       }
                       GameCanvas.debug();
                       break;
                   case 0:
                       readLogin(message);
                       break;
                   case 1:
                       boolean packetFlag = message.reader().readBoolean();
                       Res.outz("isRes= " + packetFlag);
                       if (!packetFlag) {
                           GameCanvas.startOKDlg(message.reader().readUTF());
                       } else {
                           GameCanvas.loginScr.isLogin2 = false;
                           Rms.saveRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect, "");
                           GameCanvas.endDlg();
                           GameCanvas.loginScr.doLogin();
                       }
                       break;
                   case 2:
                       Char.isLoadingMap = true;
                       LoginScr.isLoggingIn = false;
                       if (!GameScr.isLoadAllData) {
                           GameScr.gI().readPart();
                       }
                       BgItem.clearHashTable();
                       GameCanvas.endDlg();
                       CreateCharScr.isCreateChar = true;
                       CreateCharScr.gI().switchToMe();
                       break;
                   case 6:
                       GameCanvas.debug();
                       Char.myCharz().xu = message.reader().readLong();
                       Char.myCharz().luong = message.reader().readInt();
                       Char.myCharz().luongKhoa = message.reader().readInt();
                       Char.myCharz().xuStr = Res.formatNumber(Char.myCharz().xu);
                       Char.myCharz().luongStr = mSystem.numberTostring((long) Char.myCharz().luong);
                       Char.myCharz().luongKhoaStr = mSystem.numberTostring((long) Char.myCharz().luongKhoa);
                       GameCanvas.endDlg();
                       break;
                   case 7:
                       byte type3 = message.reader().readByte();
                       short id10 = message.reader().readShort();
                       String info3 = message.reader().readUTF();
                       GameCanvas.panel.saleRequest(type3, info3, id10);
                       break;
                   case 11:
                       GameCanvas.debug();
                       short mobTemplateIndex = message.reader().readShort();
                       Res.outz("====> 2 NEW DATA TEMPLATE  = " + mobTemplateIndex);
                       byte typeread2;
                       if ((typeread2 = message.reader().readByte()) != 0) {
                           Mob.arrMobTemplate[mobTemplateIndex].data.readDataNewBoss(Firework.readMessageBytes(message), typeread2);
                       } else {
                           Mob.arrMobTemplate[mobTemplateIndex].data.readData(((byte[]) (Firework.readMessageBytes(message))));
                       }
                       for (int mobIndex5 = 0; mobIndex5 < GameScr.vMob.size(); mobIndex5++) {
                           Mob mob4;
                           if ((mob4 = (Mob) GameScr.vMob.elementAt(mobIndex5)).templateId == mobTemplateIndex) {
                               mob4.w = Mob.arrMobTemplate[mobTemplateIndex].data.width;
                               mob4.h = Mob.arrMobTemplate[mobTemplateIndex].data.height;
                           }
                       }
                       for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
                           Char charInMap;
                           if ((charInMap = (Char) GameScr.vCharInMap.elementAt(charInMapIndex)) != null && charInMap.mobMe != null && charInMap.mobMe.templateId == mobTemplateIndex) {
                               charInMap.mobMe.w = Mob.arrMobTemplate[mobTemplateIndex].data.width;
                               charInMap.mobMe.h = Mob.arrMobTemplate[mobTemplateIndex].data.height;
                           }
                       }
                       if (Char.myCharz().mobMe != null && Char.myCharz().mobMe.templateId == mobTemplateIndex) {
                           Char.myCharz().mobMe.w = Mob.arrMobTemplate[mobTemplateIndex].data.width;
                           Char.myCharz().mobMe.h = Mob.arrMobTemplate[mobTemplateIndex].data.height;
                       }
                       byte[] messageBytes2 = Firework.readMessageBytes(message);
                       Res.outz("mob image lent= " + messageBytes2.length);
                       Image img = Image.createImage(messageBytes2, 0, messageBytes2.length);
                       Mob.arrMobTemplate[mobTemplateIndex].data.img = img;
                       byte typeData = message.reader().readByte();
                       Mob.arrMobTemplate[mobTemplateIndex].data.typeData = typeData;
                       if (typeData == 1 || typeData == 2) {
                           readFrameBoss(message, mobTemplateIndex);
                       }
                       break;
                   case 12:
                       this.read_cmdExtraBig(message);
                       LoginScr.isUpdateItem = false;
                       GameScr.gI();
                       GameScr.readOk();
                       GameCanvas.endDlg();
                       break;
                   case 20:
                       this.phuban_Info(message);
                       break;
                   case 24:
                       this.read_cmdExtra(message);
                       break;
                   case 27:
                       MyVector menuItems2 = new MyVector("menu open ui");
                       message.reader().readUTF();
                       byte menuItemCount = message.reader().readByte();
                       for (int menuItemIndex = 0; menuItemIndex < menuItemCount; menuItemIndex++) {
                           String caption3 = message.reader().readUTF();
                           Short boxedShort = new Short(message.reader().readShort());
                           menuItems2.addElement(new Command(caption3, GameCanvas.instance, 88819, boxedShort));
                       }
                       GameCanvas.menu.startWithoutCloseButton(menuItems2, 3);
                       break;
                   case 29:
                       GameCanvas.debug();
                       GameScr.gI().openUIZone(message);
                       break;
                   case 32:
                       GameCanvas.debug();
                       short npcId = message.reader().readShort();
                       Res.outz("npc template id= " + npcId);
                       for (int npcIndex2 = 0; npcIndex2 < GameScr.vNpc.size(); npcIndex2++) {
                           Npc c4;
                           if ((c4 = (Npc) GameScr.vNpc.elementAt(npcIndex2)).template.npcTemplateId == npcId && c4.equals(Char.myCharz().npcFocus)) {
                               String chat2 = message.reader().readUTF();
                               String[] menu = new String[message.reader().readByte()];
                               for (int menuIndex = 0; menuIndex < menu.length; menuIndex += 1) {
                                   menu[menuIndex] = message.reader().readUTF();
                               }
                               GameScr.gI();
                               GameScr.createMenu(menu, c4);
                               ChatPopup.addChatPopup(chat2, 100000, c4);
                               return;
                           }
                       }
                       Npc c5 = new Npc(npcId, 0, -100, 100, npcId, GameScr.info1.charId[Char.myCharz().cgender][2]);
                       Res.outz(Char.myCharz().npcFocus != null ? "!null" : "null");
                       String chat3 = message.reader().readUTF();
                       String[] menu2 = new String[message.reader().readByte()];
                       for (int menuIndex2 = 0; menuIndex2 < menu2.length; menuIndex2 += 1) {
                           menu2[menuIndex2] = message.reader().readUTF();
                       }
                       try {
                           short avatar2 = message.reader().readShort();
                           c5.avatar = avatar2;
                       } catch (Exception ignoredException16) {
                       }
                       Res.outz(Char.myCharz().npcFocus != null ? "!null" : "null");
                       GameScr.gI();
                       GameScr.createMenu(menu2, c5);
                       ChatPopup.addChatPopup(chat3, 100000, c5);
                       break;
                   case 33:
                       GameCanvas.debug();
                       InfoDlg.hide();
                       GameCanvas.clearKeyHold();
                       GameCanvas.clearKeyPressed();
                       MyVector menuItems3 = new MyVector("vt menu");
                       try {
                           while (true) {
                               String caption4 = message.reader().readUTF();
                               menuItems3.addElement(new Command(caption4, GameCanvas.instance, 88822, null));
                           }
                       } catch (Exception ignoredException17) {
                           if (Char.myCharz().npcFocus == null) {
                               return;
                           }
                           for (int menuIndex3 = 0; menuIndex3 < Char.myCharz().npcFocus.template.menu.length; menuIndex3++) {
                               String[] menu3 = Char.myCharz().npcFocus.template.menu[menuIndex3];
                               menuItems3.addElement(new Command(menu3[0], GameCanvas.instance, 88820, menu3));
                           }
                           GameCanvas.menu.startAt(menuItems3);
                           break;
                       }
                   case 38:
                       GameCanvas.debug();
                       InfoDlg.hide();
                       short npcId2 = message.reader().readShort();
                       Res.outz("OPEN_UI_SAY ID= " + npcId2);
                       String chat4 = Res.changeString(message.reader().readUTF());
                       for (int npcIndex3 = 0; npcIndex3 < GameScr.vNpc.size(); npcIndex3++) {
                           Npc c6 = (Npc) GameScr.vNpc.elementAt(npcIndex3);
                           Res.outz("npc id= " + c6.template.npcTemplateId);
                           if (c6.template.npcTemplateId == npcId2) {
                               ChatPopup.addChatPopupMultiLine(chat4, 100000, c6);
                               GameCanvas.panel.hideNow();
                               return;
                           }
                       }
                       Npc c7;
                       if ((c7 = new Npc(npcId2, 0, 0, 0, npcId2, GameScr.info1.charId[Char.myCharz().cgender][2])).template.npcTemplateId == 5) {
                           c7.charID = 5;
                       }
                       try {
                           c7.avatar = message.reader().readShort();
                       } catch (Exception ignoredException18) {
                       }
                       ChatPopup.addChatPopupMultiLine(chat4, 100000, c7);
                       GameCanvas.panel.hideNow();
                       break;
                   case 39:
                       GameCanvas.debug();
                       GameScr.gI().typeTradeOrder = 2;
                       GameScr.gI();
                       break;
                   case 40:
                       GameCanvas.debug();
                       GameCanvas.taskTick = 150;
                       short taskId = message.reader().readShort();
                       byte index10 = message.reader().readByte();
                       Res.outz("TASK GET index= " + index10);
                       String name2 = Res.changeString(message.reader().readUTF());
                       String detail = Res.changeString(message.reader().readUTF());
                        String[] subNames = new String[message.reader().readByte()];
                        String[] contentInfo = new String[subNames.length];
                       GameScr.tasks = new int[subNames.length];
                       GameScr.mapTasks = new int[subNames.length];
                       short[] counts = new short[subNames.length];
                       short count = -1;
                       for (int contentInfoIndex = 0; contentInfoIndex < subNames.length; contentInfoIndex++) {
                           String text = Res.changeString(message.reader().readUTF());
                           GameScr.tasks[contentInfoIndex] = message.reader().readByte();
                           GameScr.mapTasks[contentInfoIndex] = message.reader().readShort();
                           String text2 = Res.changeString(message.reader().readUTF());
                           counts[contentInfoIndex] = -1;
                           subNames[contentInfoIndex] = text;
                           if (!text2.equals("")) {
                               contentInfo[contentInfoIndex] = text2;
                           }
                       }
                       try {
                           count = message.reader().readShort();
                           for (int countIndex = 0; countIndex < subNames.length; countIndex++) {
                               counts[countIndex] = message.reader().readShort();
                           }
                       } catch (Exception ignoredException19) {
                       }
                       Char.myCharz().taskMaint = new Task(taskId, index10, name2, detail, subNames, counts, count, contentInfo);
                       if (Char.myCharz().npcFocus != null) {
                           Npc.clearEffTask();
                       }
                       Char.taskAction(true);
                       break;
                   case 41:
                       GameCanvas.debug();
                       GameCanvas.taskTick = 100;
                       Res.outz("TASK NEXT");
                       Char.myCharz().taskMaint.index++;
                       Char.myCharz().taskMaint.count = 0;
                       Npc.clearEffTask();
                       Char.taskAction(true);
                       break;
                   case 43:
                       GameCanvas.taskTick = 50;
                       GameCanvas.debug();
                       Char.myCharz().taskMaint.count = message.reader().readShort();
                       if (Char.myCharz().npcFocus != null) {
                           Npc.clearEffTask();
                       }
                       try {
                           short x_hint = message.reader().readShort();
                           short y_hint = message.reader().readShort();
                           Char.myCharz().x_hint = x_hint;
                           Char.myCharz().y_hint = y_hint;
                       } catch (Exception ignoredException20) {
                       }
                       break;
                   case 46:
                       GameCanvas.debug();
                       Char.isLockKey = false;
                       Char.myCharz().setResetPoint(message.reader().readShort(), message.reader().readShort());
                       break;
                   case 47:
                       GameCanvas.debug();
                       GameScr.gI().resetButton();
                       break;
                   case 50:
                       byte gameInfoCount = message.reader().readByte();
                       Panel.vGameInfo.removeAllElements();
                       for (int gameInfoIndex = 0; gameInfoIndex < gameInfoCount; gameInfoIndex++) {
                           GameInfo entry11;
                           (entry11 = new GameInfo()).id = message.reader().readShort();
                           entry11.main = message.reader().readUTF();
                           entry11.content = message.reader().readUTF();
                           Panel.vGameInfo.addElement(entry11);
                           boolean hasRead = Rms.loadRMSInt(String.valueOf(entry11.id)) != -1;
                           entry11.hasRead = hasRead;
                       }
                       break;
                   case 54:
                       Char character20;
                       if ((character20 = GameScr.findCharInMap(message.reader().readInt())) == null) {
                           return;
                       }
                       int sksIndex3 = message.reader().readUnsignedByte();
                       if ((TileMap.tileTypeAtPixel(character20.cx, character20.cy) & 2) == 2) {
                           character20.setSkillPaint(GameScr.sks[sksIndex3], 0);
                       } else {
                           character20.setSkillPaint(GameScr.sks[sksIndex3], 1);
                       }
                       GameCanvas.debug();
                       Mob[] mobs = new Mob[10];
                       int index11 = 0;
                       try {
                           GameCanvas.debug();
                           for (index11 = 0; index11 < mobs.length; index11 += 1) {
                               new StringBuffer("SA769991v4-num").append((int) index11).toString();
                               GameCanvas.debug();
                               Mob mob5 = (Mob) GameScr.vMob.elementAt(message.reader().readByte());
                               mobs[index11] = mob5;
                               if (index11 == 0) {
                                   if (character20.cx <= mob5.x) {
                                       character20.cdir = 1;
                                   } else {
                                       character20.cdir = -1;
                                   }
                               }
                               new StringBuffer("SA769991v5-num").append((int) index11).toString();
                               GameCanvas.debug();
                           }
                       } catch (Exception ignoredException21) {
                       }
                       GameCanvas.debug();
                       if (index11 > 0) {
                           character20.attMobs = new Mob[index11];
                           for (int attMobIndex2 = 0; attMobIndex2 < character20.attMobs.length; attMobIndex2 += 1) {
                               character20.attMobs[attMobIndex2] = mobs[attMobIndex2];
                           }
                           character20.charFocus = null;
                           character20.mobFocus = character20.attMobs[0];
                       }
                       break;
                   case 56:
                       GameCanvas.debug();
                       int charId17;
                       if ((charId17 = message.reader().readInt()) == Char.myCharz().charID) {
                           boolean packetFlag2 = false;
                           Char player2;
                           (player2 = Char.myCharz()).cHP = message.reader().readLong();
                           long damage3;
                           if ((damage3 = message.reader().readLong()) != 0L) {
                               player2.doInjure();
                           }
                           try {
                               packetFlag2 = message.reader().readBoolean();
                               byte id11;
                               if ((id11 = message.reader().readByte()) != -1) {
                                   Res.outz("hit eff= " + id11);
                                   EffecMn.addEff(new Effect(id11, player2.cx, player2.cy, 3, 1, -1));
                               }
                           } catch (Exception ignoredException22) {
                           }
                           if (Char.myCharz().cTypePk != 4) {
                               if (damage3 == 0L) {
                                   GameScr.startFlyText(mResources.miss, player2.cx, player2.cy - player2.ch, 0, -3, 7);
                               } else {
                                   GameScr.startFlyText("-" + damage3, player2.cx, player2.cy - player2.ch, 0, -3, !packetFlag2 ? 0 : 3);
                               }
                           }
                       } else {
                           Char character21;
                           if ((character21 = GameScr.findCharInMap(charId17)) == null) {
                               return;
                           }
                           character21.cHP = message.reader().readLong();
                           boolean packetFlag3 = false;
                           long damage4;
                           if ((damage4 = message.reader().readLong()) != 0L) {
                               character21.doInjure();
                           }
                           try {
                               packetFlag3 = message.reader().readBoolean();
                               byte id12;
                               if ((id12 = message.reader().readByte()) != -1) {
                                   Res.outz("hit eff= " + id12);
                                   EffecMn.addEff(new Effect(id12, character21.cx, character21.cy, 3, 1, -1));
                               }
                           } catch (Exception ignoredException23) {
                           }
                           if (character21.cTypePk != 4) {
                               if (damage4 == 0L) {
                                   GameScr.startFlyText(mResources.miss, character21.cx, character21.cy - character21.ch, 0, -3, 4);
                               } else {
                                   GameScr.startFlyText("-" + damage4, character21.cx, character21.cy - character21.ch, 0, -3, !packetFlag3 ? 5 : 3);
                               }
                           }
                       }
                       break;
                   case 57:
                       GameCanvas.debug();
                       MyVector menuItems4;
                       (menuItems4 = new MyVector("vmenu UI")).addElement(new Command(message.reader().readUTF(), GameCanvas.instance, 88817, null));
                       GameCanvas.menu.startAt(menuItems4);
                       break;
                   case 58:
                       GameCanvas.debug();
                       Char character22;
                       int charId18;
                       if ((charId18 = message.reader().readInt()) == Char.myCharz().charID) {
                           character22 = Char.myCharz();
                       } else {
                           character22 = GameScr.findCharInMap(charId18);
                       }
                       character22.moveFast = new short[3];
                       character22.moveFast[0] = 0;
                       short cx = message.reader().readShort();
                       short cy = message.reader().readShort();
                       character22.moveFast[1] = cx;
                       character22.moveFast[2] = cy;
                       try {
                           Char character23;
                           if ((charId18 = message.reader().readInt()) == Char.myCharz().charID) {
                               character23 = Char.myCharz();
                           } else {
                               character23 = GameScr.findCharInMap(charId18);
                           }
                           character23.cx = cx;
                           character23.cy = cy;
                       } catch (Exception exception3) {
                           exception3.printStackTrace();
                       }
                       break;
                   case 62:
                       GameCanvas.debug();
                       Char charFocus;
                       if ((charFocus = GameScr.findCharInMap(message.reader().readInt())) != null) {
                           charFocus.killCharId = Char.myCharz().charID;
                           Char.myCharz().npcFocus = null;
                           Char.myCharz().mobFocus = null;
                           Char.myCharz().itemFocus = null;
                           Char.myCharz().charFocus = charFocus;
                           Char.isManualFocus = true;
                           GameScr.info1.addInfo(charFocus.cName + mResources.CUU_SAT, 0);
                       }
                       break;
                   case 63:
                       GameCanvas.debug();
                       Char.myCharz().killCharId = message.reader().readInt();
                       Char.myCharz().npcFocus = null;
                       Char.myCharz().mobFocus = null;
                       Char.myCharz().itemFocus = null;
                       Char.myCharz().charFocus = GameScr.findCharInMap(Char.myCharz().killCharId);
                       Char.isManualFocus = true;
                       break;
                   case 64:
                       GameCanvas.debug();
                       Char character24 = Char.myCharz();
                       try {
                           character24 = GameScr.findCharInMap(message.reader().readInt());
                       } catch (Exception ignoredException24) {
                       }
                       character24.killCharId = -9999;
                       break;
                   case 65:
                       byte id13 = message.reader().readByte();
                       String text3 = message.reader().readUTF();
                       short time = message.reader().readShort();
                       if (ItemTime.isExistMessage(id13)) {
                           if (time != 0) {
                               ItemTime.getMessageById(id13).initTimeText(id13, text3, time);
                           } else {
                               GameScr.textTime.removeElement(ItemTime.getMessageById(id13));
                           }
                       } else {
                           ItemTime entry12;
                           (entry12 = new ItemTime()).initTimeText(id13, text3, time);
                           GameScr.textTime.addElement(entry12);
                       }
                       Res.outz("idMessageTime + messageStr + timeMessage:::  " + id13 + "_" + text3 + "_" + time);
                       break;
                   case 66:
                       this.readGetImgByName(message);
                       break;
                   case 68:
                       Res.outz("ADD ITEM TO MAP --------------------------------------");
                       GameCanvas.debug();
                       short packetShort9 = message.reader().readShort();
                       short packetShort10 = message.reader().readShort();
                       short packetShort11 = message.reader().readShort();
                       short packetShort12 = message.reader().readShort();
                       int packetInt7 = message.reader().readInt();
                       short yEnd = 0;
                       if (packetInt7 == -2) {
                           yEnd = message.reader().readShort();
                       }
                       ItemMap entry13 = new ItemMap(packetInt7, packetShort9, packetShort10, packetShort11, packetShort12, yEnd);
                       boolean condition7 = false;
                       for (int itemMapIndex4 = 0; itemMapIndex4 < GameScr.vItemMap.size(); itemMapIndex4++) {
                           if (((ItemMap) GameScr.vItemMap.elementAt(itemMapIndex4)).itemMapID == entry13.itemMapID) {
                               condition7 = true;
                               break;
                           }
                       }
                       if (!condition7) {
                           GameScr.vItemMap.addElement(entry13);
                       }
                       break;
                   case 69:
                       SoundMn.IsDelAcc = message.reader().readByte() != 0;
                       break;
                   case 81:
                       GameCanvas.debug();
                       ((Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte())).isDisable = message.reader().readBoolean();
                       break;
                   case 82:
                       GameCanvas.debug();
                       ((Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte())).isDontMove = message.reader().readBoolean();
                       break;
                   case 83:
                       GameCanvas.debug();
                       Char character25;
                       int charId19;
                       if ((charId19 = message.reader().readInt()) == Char.myCharz().charID) {
                           character25 = Char.myCharz();
                       } else {
                           character25 = GameScr.findCharInMap(charId19);
                       }
                       if (character25 == null) {
                           return;
                       }
                       Mob mobToAttack2 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       if (character25.mobMe != null) {
                           character25.mobMe.attackOtherMob(mobToAttack2);
                       }
                       break;
                   case 84:
                       Char character26;
                       int charId20;
                       if ((charId20 = message.reader().readInt()) == Char.myCharz().charID) {
                           character26 = Char.myCharz();
                       } else if ((character26 = GameScr.findCharInMap(charId20)) == null) {
                           return;
                       }
                       character26.cHP = character26.cHPFull;
                       character26.cMP = character26.cMPFull;
                       character26.cx = message.reader().readShort();
                       character26.cy = message.reader().readShort();
                       character26.liveFromDead();
                       break;
                   case 85:
                       GameCanvas.debug();
                       ((Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte())).isFire = message.reader().readBoolean();
                       break;
                   case 86:
                       GameCanvas.debug();
                       Mob mob6;
                       (mob6 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte())).isIce = message.reader().readBoolean();
                       if (!mob6.isIce) {
                           ServerEffect.addServerEffect(77, mob6.x, mob6.y - 9, 1);
                       }
                       break;
                   case 87:
                       GameCanvas.debug();
                       ((Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte())).isWind = message.reader().readBoolean();
                       break;
                   case 88:
                       String info4 = message.reader().readUTF();
                       Short boxedShort2 = new Short(message.reader().readShort());
((InputDlg)null).show(info4, new Command(mResources.ACCEPT, GameCanvas.instance, 88818, boxedShort2), 0);
                       break;
                   case 90:
                       GameCanvas.debug();
                       this.requestItemPlayer(message);
                       break;
                   case 92:
                       if (GameCanvas.currentScreen == GameScr.instance) {
                           GameCanvas.endDlg();
                       }
                       String cName = message.reader().readUTF();
                       String text4 = Res.changeString(message.reader().readUTF());
                       Res.outz("chat text= " + text4);
                       String s2 = "";
                       Char c8 = null;
                       byte packetByte26 = 0;
                       if (!cName.equals("")) {
                           (c8 = new Char()).charID = message.reader().readInt();
                           c8.head = message.reader().readShort();
                           c8.headICON = message.reader().readShort();
                           c8.body = message.reader().readShort();
                           c8.bag = message.reader().readShort();
                           c8.leg = message.reader().readShort();
                           packetByte26 = message.reader().readByte();
                           c8.cName = cName;
                       }
                       s2 = s2 + text4;
                       InfoDlg.hide();
                       if (cName.equals("")) {
                           GameScr.info1.addInfo(s2, 0);
                       } else {
                           GameScr.info2.addInfoWithChar(s2, c8, packetByte26 == 0);
                           if (GameCanvas.panel.isShow && GameCanvas.panel.type == 8) {
                               GameCanvas.panel.initLogMessage();
                           }
                       }
                       break;
                   case 94:
                       GameCanvas.debug();
                       GameScr.info1.addInfo(message.reader().readUTF(), 0);
                       break;
                   case 112:
                       byte imgSpeacialSkillCount;
                       if ((imgSpeacialSkillCount = message.reader().readByte()) == 0) {
                           Panel.spearcialImage = message.reader().readShort();
                           Panel.specialInfo = message.reader().readUTF();
                       } else if (imgSpeacialSkillCount == 1) {
                           byte speacialTabNameCount = message.reader().readByte();
                           Char.myCharz().infoSpeacialSkill = new String[speacialTabNameCount][];
                           Char.myCharz().imgSpeacialSkill = new short[speacialTabNameCount][];
                           GameCanvas.panel.speacialTabName = new String[speacialTabNameCount][];
                           for (int imgSpeacialSkillIndex = 0; imgSpeacialSkillIndex < speacialTabNameCount; imgSpeacialSkillIndex++) {
                               GameCanvas.panel.speacialTabName[imgSpeacialSkillIndex] = new String[2];
                                String[] parts3 = Res.split(message.reader().readUTF(), "\n", 0);
                                if (parts3.length == 2) {
                                   GameCanvas.panel.speacialTabName[imgSpeacialSkillIndex] = parts3;
                               }
                               if (parts3.length == 1) {
                                   GameCanvas.panel.speacialTabName[imgSpeacialSkillIndex][0] = parts3[0];
                                   GameCanvas.panel.speacialTabName[imgSpeacialSkillIndex][1] = "";
                               }
                               imgSpeacialSkillCount = message.reader().readByte();
                               Char.myCharz().infoSpeacialSkill[imgSpeacialSkillIndex] = new String[imgSpeacialSkillCount];
                               Char.myCharz().imgSpeacialSkill[imgSpeacialSkillIndex] = new short[imgSpeacialSkillCount];
                               for (int imgSpeacialSkillIndex2 = 0; imgSpeacialSkillIndex2 < imgSpeacialSkillCount; imgSpeacialSkillIndex2++) {
                                   Char.myCharz().imgSpeacialSkill[imgSpeacialSkillIndex][imgSpeacialSkillIndex2] = message.reader().readShort();
                                   Char.myCharz().infoSpeacialSkill[imgSpeacialSkillIndex][imgSpeacialSkillIndex2] = message.reader().readUTF();
                               }
                           }
                           GameCanvas.panel.tabName[25] = GameCanvas.panel.speacialTabName;
                           GameCanvas.panel.setTypeSpeacialSkill();
                           GameCanvas.panel.show();
                       }
                   }
               label3780: switch(message.command) {
                   case -75:
                       Mob mob7 = null;
                       try {
                           mob7 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       } catch (Exception ignoredException25) {
                       }
                       if (mob7 != null) {
                           mob7.levelBoss = message.reader().readByte();
                           if (mob7.levelBoss > 0) {
                               mob7.typeSuperEff = Res.random(0, 3);
                           }
                       }
                       break;
                   case -73:
                       byte packetByte27 = message.reader().readByte();
                       for (int npcIndex4 = 0; npcIndex4 < GameScr.vNpc.size(); npcIndex4++) {
                           Npc npc2;
                           if ((npc2 = (Npc) GameScr.vNpc.elementAt(npcIndex4)).template.npcTemplateId == packetByte27) {
                               if (message.reader().readByte() == 0) {
                                   npc2.isHide = true;
                               } else {
                                   npc2.isHide = false;
                               }
                               break label3780;
                           }
                       }
                       break;
                   case -17:
                       GameCanvas.debug();
                       Char.myCharz().meDead = true;
                       Char.myCharz().cPk = message.reader().readByte();
                       Char.myCharz().startDie(message.reader().readShort(), message.reader().readShort());
                       try {
                           Char.myCharz().cPower = message.reader().readLong();
                           Char.myCharz().applyCharLevelPercent();
                       } catch (Exception ignoredException26) {
                       }
                       Char.myCharz().countKill = 0;
                       break;
                   case -16:
                       GameCanvas.debug();
                       if (Char.myCharz().wdx != 0 || Char.myCharz().wdy != 0) {
                           Char.myCharz().cx = Char.myCharz().wdx;
                           Char.myCharz().cy = Char.myCharz().wdy;
                           Char.myCharz().wdx = Char.myCharz().wdy = 0;
                       }
                       Char.myCharz().liveFromDead();
                       Char.myCharz().isLockMove = false;
                       Char.myCharz().meDead = false;
                       break;
                   case -13:
                       GameCanvas.debug();
                       int mobIndex6;
                       if ((mobIndex6 = message.reader().readUnsignedByte()) > GameScr.vMob.size() - 1 || mobIndex6 < 0) {
                           return;
                       }
                       Mob mob8;
                       (mob8 = (Mob) GameScr.vMob.elementAt(mobIndex6)).sys = message.reader().readByte();
                       mob8.levelBoss = message.reader().readByte();
                       if (mob8.levelBoss != 0) {
                           mob8.typeSuperEff = Res.random(0, 3);
                       }
                       mob8.x = mob8.xFirst;
                       mob8.y = mob8.yFirst;
                       Res.outz("HOI SINH x= " + mob8.xFirst + " y= " + mob8.yFirst);
                       mob8.status = 5;
                       mob8.injureThenDie = false;
                       mob8.hp = message.reader().readLong();
                       mob8.maxHp = mob8.hp;
                       mob8.updateHp_bar();
                       ServerEffect.addServerEffect(60, mob8.x, mob8.y, 1);
                       break;
                   case -12:
                       Res.outz("SERVER SEND MOB DIE");
                       GameCanvas.debug();
                       Mob mob9 = null;
                       try {
                           mob9 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       } catch (Exception ignoredException27) {
                       }
                       if (mob9 != null && mob9.status != 0 && mob9.status != 0) {
                           mob9.startDie();
                           try {
                               long damage5 = message.reader().readLong();
                               if (message.reader().readBoolean()) {
                                   GameScr.startFlyText("-" + damage5, mob9.x, mob9.y - mob9.h, 0, -2, 3);
                               } else {
                                   GameScr.startFlyText("-" + damage5, mob9.x, mob9.y - mob9.h, 0, -2, 5);
                               }
                               byte itemMapCount = message.reader().readByte();
                               for (int itemMapIndex5 = 0; itemMapIndex5 < itemMapCount; itemMapIndex5++) {
                                   ItemMap entry14 = new ItemMap(message.reader().readShort(), message.reader().readShort(), mob9.x, mob9.y, message.reader().readShort(), message.reader().readShort());
                                   int playerId = message.reader().readInt();
                                   entry14.playerId = playerId;
                                   Res.outz("playerid= " + playerId + " my id= " + Char.myCharz().charID);
                                   GameScr.vItemMap.addElement(entry14);
                                   if (Res.abs(entry14.y - Char.myCharz().cy) < 24 && Res.abs(entry14.x - Char.myCharz().cx) < 24) {
                                       Char.myCharz().charFocus = null;
                                   }
                               }
                           } catch (Exception ignoredException28) {
                           }
                       }
                       break;
                   case -11:
                       GameCanvas.debug();
                       Mob mob10 = null;
                       try {
                           int mobIndex7 = message.reader().readUnsignedByte();
                           Res.outz("mob index= " + mobIndex7);
                           mob10 = (Mob) GameScr.vMob.elementAt(mobIndex7);
                       } catch (Exception ignoredException29) {
                       }
                       if (mob10 != null) {
                           Char.myCharz().isDie = false;
                           Char.isLockKey = false;
                           long dame2 = message.reader().readLong();
                           long dameMp;
                           try {
                               dameMp = message.reader().readLong();
                           } catch (Exception ignoredException30) {
                               dameMp = 0L;
                           }
                           if (mob10.isBusyAttackSomeOne) {
                               Char.myCharz().doInjure(dame2, dameMp, false, true);
                           } else {
                               mob10.dame = dame2;
                               mob10.dameMp = dameMp;
                               mob10.setAttack(Char.myCharz());
                           }
                       }
                       break;
                   case -10:
                       GameCanvas.debug();
                       Mob mob11 = null;
                       try {
                           mob11 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       } catch (Exception ignoredException31) {
                       }
                       GameCanvas.debug();
                       if (mob11 != null) {
                           GameCanvas.debug();
                           Char cFocus3;
                           if ((cFocus3 = GameScr.findCharInMap(message.reader().readInt())) == null) {
                               return;
                           }
                           GameCanvas.debug();
                           long cHPNew2 = message.reader().readLong();
                           mob11.dame = cFocus3.cHP - cHPNew2;
                           cFocus3.cHPNew = cHPNew2;
                           GameCanvas.debug();
                           try {
                               cFocus3.cMP = message.reader().readLong();
                           } catch (Exception ignoredException32) {
                           }
                           GameCanvas.debug();
                           if (mob11.isBusyAttackSomeOne) {
                               cFocus3.doInjure(mob11.dame, 0L, false, true);
                           } else {
                               mob11.setAttack(cFocus3);
                           }
                           GameCanvas.debug();
                       }
                       break;
                   case -9:
                       GameCanvas.debug();
                       Mob mob12 = null;
                       try {
                           mob12 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       } catch (Exception ignoredException33) {
                       }
                       GameCanvas.debug();
                       if (mob12 != null) {
                           mob12.hp = message.reader().readLong();
                           mob12.updateHp_bar();
                           long damage6;
                           if ((damage6 = message.reader().readLong()) == 1L) {
                               return;
                           }
                           if (damage6 > 1L) {
                               mob12.setInjure();
                           }
                           boolean packetFlag4 = message.reader().readBoolean();
                           byte id14;
                           if ((id14 = message.reader().readByte()) != -1) {
                               EffecMn.addEff(new Effect(id14, mob12.x, mob12.getY(), 3, 1, -1));
                           }
                           GameCanvas.debug();
                           if (packetFlag4) {
                               GameScr.startFlyText("-" + damage6, mob12.x, mob12.getY() - mob12.getH(), 0, -2, 3);
                           } else if (damage6 == 0L) {
                               mob12.x = mob12.xFirst;
                               mob12.y = mob12.yFirst;
                               GameScr.startFlyText(mResources.miss, mob12.x, mob12.getY() - mob12.getH(), 0, -2, 4);
                           } else if (damage6 > 1L) {
                               GameScr.startFlyText("-" + damage6, mob12.x, mob12.getY() - mob12.getH(), 0, -2, 5);
                           }
                       }
                       GameCanvas.debug();
                       break;
                   case -8:
                       GameCanvas.debug();
                       Char character27;
                       if ((character27 = GameScr.findCharInMap(message.reader().readInt())) == null) {
                           return;
                       }
                       character27.cPk = message.reader().readByte();
                       character27.waitToDie(message.reader().readShort(), message.reader().readShort());
                       break;
                   case -7:
                       GameCanvas.debug();
                       int packetInt8 = message.reader().readInt();
                       for (int charInMapIndex2 = 0; charInMapIndex2 < GameScr.vCharInMap.size(); charInMapIndex2++) {
                           Char charInMap2 = null;
                           try {
                               charInMap2 = (Char) GameScr.vCharInMap.elementAt(charInMapIndex2);
                           } catch (Exception ignoredException34) {
                               continue;
                           }
                           if (charInMap2 != null && charInMap2.charID == packetInt8) {
                               new StringBuffer("SA8x2y").append(charInMapIndex2).toString();
                               GameCanvas.debug();
                               charInMap2.moveTo(message.reader().readShort(), message.reader().readShort(), 0);
                               charInMap2.lastUpdateTime = System.currentTimeMillis();
                               break;
                           }
                       }
                       GameCanvas.debug();
                       break;
                   case -6:
                       Res.outz("PLAYER REMOVED!!!!!============");
                       GameCanvas.debug();
                       int packetInt9 = message.reader().readInt();
                       for (int charInMapIndex3 = 0; charInMapIndex3 < GameScr.vCharInMap.size(); charInMapIndex3++) {
                           Char charInMap3;
                           if ((charInMap3 = (Char) GameScr.vCharInMap.elementAt(charInMapIndex3)) != null && charInMap3.charID == packetInt9) {
                               if (!charInMap3.isInvisiblez && !charInMap3.isUsePlane) {
                                   ServerEffect.addServerEffect(60, charInMap3.cx, charInMap3.cy, 1);
                               }
                               if (!charInMap3.isUsePlane) {
                                   GameScr.vCharInMap.removeElementAt(charInMapIndex3);
                               }
                               return;
                           }
                       }
                       break;
                   case -5:
                       GameCanvas.debug();
                       int charID = message.reader().readInt();
                       int clanID2;
                       Object c9;
                       if ((clanID2 = message.reader().readInt()) != -100) {
                           ((Char) (c9 = new Char())).charID = charID;
                           ((Char) c9).clanID = clanID2;
                       } else {
                           ((Char) (c9 = new Mabu())).charID = charID;
                           ((Char) c9).clanID = clanID2;
                       }
                       if (((Char) c9).clanID == -2) {
                           ((Char) c9).isCopy = true;
                       }
                       if (readCharInfo((Char) c9, message)) {
                           byte packetByte28 = message.reader().readByte();
                           if (((Char) c9).cy <= 10 && packetByte28 != 0 && packetByte28 != 2) {
                               Teleport p2;
                               (p2 = new Teleport(((Char) c9).cx, ((Char) c9).cy, ((Char) c9).head, ((Char) c9).cdir, 1, false, packetByte28 == 1 ? ((Char) c9).cgender : packetByte28)).id = ((Char) c9).charID;
                               ((Char) c9).isTeleport = true;
                               Teleport.addTeleport(p2);
                           }
                           if (packetByte28 == 2) {
                               ((Char) c9).show();
                           }
                           for (int mobIndex8 = 0; mobIndex8 < GameScr.vMob.size(); mobIndex8++) {
                               Mob mobMe2;
                               if ((mobMe2 = (Mob) GameScr.vMob.elementAt(mobIndex8)) != null && mobMe2.isMobMe && mobMe2.mobId == ((Char) c9).charID) {
                                   Res.outz("co 1 con quai");
                                   ((Char) c9).mobMe = mobMe2;
                                   ((Char) c9).mobMe.x = ((Char) c9).cx;
                                   ((Char) c9).mobMe.y = ((Char) c9).cy - 40;
                                   break;
                               }
                           }
                           if (GameScr.findCharInMap(((Char) c9).charID) == null) {
                               GameScr.vCharInMap.addElement(c9);
                           }
                           ((Char) c9).isMonkey = message.reader().readByte();
                           short idMount;
                           if ((idMount = message.reader().readShort()) != -1) {
                               ((Char) c9).isHaveMount = true;
                               if (idMount == 346 || idMount == 347 || idMount == 348) {
                                   ((Char) c9).isMountVip = false;
                               } else if (idMount == 349 || idMount == 350 || idMount == 351) {
                                   ((Char) c9).isMountVip = true;
                               } else if (idMount == 396) {
                                   ((Char) c9).isEventMount = true;
                               } else if (idMount == 532) {
                                   ((Char) c9).isSpeacialMount = true;
                               } else if (idMount >= Char.ID_NEW_MOUNT) {
                                   ((Char) c9).idMount = idMount;
                               }
                           } else {
                               ((Char) c9).isHaveMount = false;
                           }
                       }
                       byte cFlag = message.reader().readByte();
                       ((Char) c9).cFlag = cFlag;
                       ((Char) c9).isNhapThe = message.reader().readByte() == 1;
                       try {
                           ((Char) c9).idAuraEff = message.reader().readShort();
                           ((Char) c9).idEff_Set_Item = message.reader().readByte();
                           ((Char) c9).idHat = message.reader().readShort();
                       } catch (Exception ignoredException35) {
                       }
                       GameScr.gI();
                       GameScr.getFlagImage(((Char) c9).charID, ((Char) c9).cFlag);
                       Effect.GetCharEff((Char) c9);
                       break;
                   case -3:
                       GameCanvas.debug();
                       byte packetByte29 = message.reader().readByte();
                       int cPower = message.reader().readInt();
                       if (packetByte29 == 0) {
                           Char.myCharz().cPower += (long) cPower;
                       }
                       if (packetByte29 == 1) {
                           Char.myCharz().cTiemNang += (long) cPower;
                       }
                       if (packetByte29 == 2) {
                           Char.myCharz().cPower += (long) cPower;
                           Char.myCharz().cTiemNang += (long) cPower;
                       }
                       Char.myCharz().applyCharLevelPercent();
                       if (Char.myCharz().cTypePk != 3) {
                           GameScr.startFlyText((cPower > 0 ? "+" : "") + cPower, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch, 0, -4, 2);
                           if (cPower > 0 && Char.myCharz().petFollow != null && Char.myCharz().petFollow.smallID == 5002) {
                               ServerEffect.addServerEffect(55, Char.myCharz().petFollow.cmx, Char.myCharz().petFollow.cmy, 1);
                               ServerEffect.addServerEffect(55, Char.myCharz().cx, Char.myCharz().cy, 1);
                           }
                       }
                       break;
                   case -2:
                       GameCanvas.debug();
                       int yen = message.reader().readInt();
                       Char.myCharz().yen += yen;
                       GameScr.startFlyText(yen > 0 ? "+" + yen : "" + yen, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch - 10, 0, -2, 1);
                       break;
                   case -1:
                       GameCanvas.debug();
                       int xu = message.reader().readInt();
                       Char.myCharz().xu += (long) xu;
                       Char.myCharz().xuStr = Res.formatNumber(Char.myCharz().xu);
                       Char.myCharz().yen -= xu;
                       GameScr.startFlyText("+" + xu, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch - 10, 0, -2, 1);
                       break;
                   case 18:
                       byte packetByte30 = message.reader().readByte();
                       for (int index12 = 0; index12 < packetByte30; index12++) {
                           int charId21 = message.reader().readInt();
                           short cx2 = message.reader().readShort();
                           short cy2 = message.reader().readShort();
                           long cHPShow = message.reader().readLong();
                           Char character28;
                           if ((character28 = GameScr.findCharInMap(charId21)) != null) {
                               character28.cx = cx2;
                               character28.cy = cy2;
                               character28.cHP = character28.cHPShow = cHPShow;
                               character28.lastUpdateTime = System.currentTimeMillis();
                           }
                       }
                       break;
                   case 19:
                       Char.myCharz().countKill = message.reader().readUnsignedShort();
                       Char.myCharz().countKillMax = message.reader().readUnsignedShort();
                       break;
                   case 44:
                       GameCanvas.debug();
                       int charId22 = message.reader().readInt();
                       String info5 = message.reader().readUTF();
                       Res.outz("user id= " + charId22 + " text= " + info5);
                       Char character29;
                       if (Char.myCharz().charID == charId22) {
                           character29 = Char.myCharz();
                       } else {
                           character29 = GameScr.findCharInMap(charId22);
                       }
                       if (character29 == null) {
                           return;
                       }
                       character29.addInfo(info5);
                       break;
                   case 45:
                       GameCanvas.debug();
                       Mob mob13 = null;
                       try {
                           mob13 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       } catch (Exception ignoredException36) {
                       }
                       if (mob13 != null) {
                           mob13.hp = message.reader().readLong();
                           mob13.updateHp_bar();
                           GameScr.startFlyText(mResources.miss, mob13.x, mob13.y - mob13.h, 0, -2, 4);
                       }
                       break;
                   case 66:
                       Res.outz("ME DIE XP DOWN NOT IMPLEMENT YET!!!!!!!!!!!!!!!!!!!!!!!!!!");
                       break;
                   case 74:
                       GameCanvas.debug();
                       Mob mob14 = null;
                       try {
                           mob14 = (Mob) GameScr.vMob.elementAt(message.reader().readUnsignedByte());
                       } catch (Exception ignoredException37) {
                       }
                       if (mob14 != null && mob14.status != 0 && mob14.status != 0) {
                           mob14.status = 0;
                           ServerEffect.addServerEffect(60, mob14.x, mob14.y, 1);
                           ItemMap entry15 = new ItemMap(message.reader().readShort(), message.reader().readShort(), mob14.x, mob14.y, message.reader().readShort(), message.reader().readShort());
                           GameScr.vItemMap.addElement(entry15);
                           if (Res.abs(entry15.y - Char.myCharz().cy) < 24 && Res.abs(entry15.x - Char.myCharz().cx) < 24) {
                               Char.myCharz().charFocus = null;
                           }
                       }
                       break;
                   case 95:
                       GameCanvas.debug();
                       int xu2 = message.reader().readInt();
                       Char.myCharz().xu += (long) xu2;
                       Char.myCharz().xuStr = Res.formatNumber(Char.myCharz().xu);
                       GameScr.startFlyText(xu2 > 0 ? "+" + xu2 : "" + xu2, Char.myCharz().cx, Char.myCharz().cy - Char.myCharz().ch - 10, 0, -2, 1);
                       break;
                   case 96:
                       GameCanvas.debug();
                       Char.myCharz().taskOrders.addElement(new TaskOrder(message.reader().readByte(), message.reader().readShort(), message.reader().readShort(), message.reader().readUTF(), message.reader().readUTF(), message.reader().readByte(), message.reader().readByte()));
                       break;
                   case 97:
                       byte packetByte31 = message.reader().readByte();
                       for (int taskOrderIndex = 0; taskOrderIndex < Char.myCharz().taskOrders.size(); taskOrderIndex++) {
                           TaskOrder taskOrder;
                           if ((taskOrder = (TaskOrder) Char.myCharz().taskOrders.elementAt(taskOrderIndex)).taskId == packetByte31) {
                               taskOrder.count = message.reader().readShort();
                               break;
                           }
                       }
                   }
               GameCanvas.debug();
           } catch (Exception exception4) {
               new StringBuffer(String.valueOf(exception4.getMessage())).append(" ").append(exception4.toString()).toString();
               GameCanvas.messageDispatchErrorHook();
               exception4.printStackTrace();
           }
       } finally {
           if (message != null) {
               message.cleanup();
           }
       }
   }

   private static void readLogin(Message msg) throws java.io.IOException {
      byte len;
      ChooseCharScr.playerData = new PlayerData[len = msg.reader().readByte()];
      Res.outz("[LEN] sl nguoi choi " + len);

      for (int playerDataIndex = 0; playerDataIndex < len; playerDataIndex++) {
         int playerID = msg.reader().readInt();
         String name = msg.reader().readUTF();
         short head = msg.reader().readShort();
         short body = msg.reader().readShort();
         short leg = msg.reader().readShort();
         long ppoint = msg.reader().readLong();
         ChooseCharScr.playerData[playerDataIndex] = new PlayerData(playerID, name, head, body, leg, ppoint);
      }

      ((nro.ChooseCharScr) null).switchToMe();
      ((nro.ChooseCharScr) null).updateChooseCharacter(len);
   }

   private static void createSkill(DataInputStream d) throws java.io.IOException {
      GameScr.vcSkill = d.readByte();
      GameScr.gI().sOptionTemplates = new SkillOptionTemplate[d.readByte()];

      for (int sOptionTemplateIndex = 0; sOptionTemplateIndex < GameScr.gI().sOptionTemplates.length; sOptionTemplateIndex++) {
         GameScr.gI().sOptionTemplates[sOptionTemplateIndex] = new SkillOptionTemplate();
         d.readUTF();
      }

      GameScr.nClasss = new NClass[d.readByte()];

      for (int classId = 0; classId < GameScr.nClasss.length; classId++) {
         GameScr.nClasss[classId] = new NClass();
         GameScr.nClasss[classId].classId = classId;
         d.readUTF();
         GameScr.nClasss[classId].skillTemplates = new SkillTemplate[d.readByte()];

         for (int skillTemplateIndex = 0; skillTemplateIndex < GameScr.nClasss[classId].skillTemplates.length; skillTemplateIndex++) {
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex] = new SkillTemplate();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].id = d.readByte();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].name = d.readUTF();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].maxPoint = d.readByte();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].manaUseType = d.readByte();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].type = d.readByte();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].iconId = d.readShort();
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].damInfo = d.readUTF();
            short lineWidth = 130;
            if (GameCanvas.w == 128 || GameCanvas.h <= 208) {
               lineWidth = 100;
            }

            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].description = mFont.tahoma_7_green2.splitFontArray(d.readUTF(), lineWidth);
            GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills = new Skill[d.readByte()];

            for (int skillIndex = 0; skillIndex < GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills.length; skillIndex++) {
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex] = new Skill();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].skillId = d.readShort();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].template = GameScr.nClasss[classId].skillTemplates[skillTemplateIndex];
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].point = d.readByte();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].powRequire = d.readLong();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].manaUse = d.readShort();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].coolDown = d.readInt();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].dx = d.readShort();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].dy = d.readShort();
               d.readByte();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].damage = d.readShort();
               d.readShort();
               GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex].moreInfo = d.readUTF();
               Skills.add(GameScr.nClasss[classId].skillTemplates[skillTemplateIndex].skills[skillIndex]);
            }
         }
      }
   }

   private static void createMap(DataInputStream input) throws java.io.IOException {
      System.out.println(">>>createMap: " + input.available());
      GameScr.vcMap = input.readByte();
      TileMap.mapNames = new String[input.readShort()];

      for (int mapNameIndex = 0; mapNameIndex < TileMap.mapNames.length; mapNameIndex++) {
         TileMap.mapNames[mapNameIndex] = input.readUTF();
      }

      Npc.arrNpcTemplate = new NpcTemplate[input.readByte()];

      for (byte npcTemplateId = 0; npcTemplateId < Npc.arrNpcTemplate.length; npcTemplateId++) {
         Npc.arrNpcTemplate[npcTemplateId] = new NpcTemplate();
         Npc.arrNpcTemplate[npcTemplateId].npcTemplateId = npcTemplateId;
         Npc.arrNpcTemplate[npcTemplateId].name = input.readUTF();
         Npc.arrNpcTemplate[npcTemplateId].headId = input.readShort();
         Npc.arrNpcTemplate[npcTemplateId].bodyId = input.readShort();
         Npc.arrNpcTemplate[npcTemplateId].legId = input.readShort();
         Npc.arrNpcTemplate[npcTemplateId].menu = new String[input.readByte()][];

         for (int menuIndex = 0; menuIndex < Npc.arrNpcTemplate[npcTemplateId].menu.length; menuIndex++) {
            Npc.arrNpcTemplate[npcTemplateId].menu[menuIndex] = new String[input.readByte()];

            for (int menuIndex2 = 0; menuIndex2 < Npc.arrNpcTemplate[npcTemplateId].menu[menuIndex].length; menuIndex2++) {
               Npc.arrNpcTemplate[npcTemplateId].menu[menuIndex][menuIndex2] = input.readUTF();
            }
         }
      }

      Mob.arrMobTemplate = new MobTemplate[input.readShort()];

      for (int mobTemplateIndex = 0; mobTemplateIndex < Mob.arrMobTemplate.length; mobTemplateIndex++) {
         Mob.arrMobTemplate[mobTemplateIndex] = new MobTemplate();
         Mob.arrMobTemplate[mobTemplateIndex].type = input.readByte();
         Mob.arrMobTemplate[mobTemplateIndex].name = input.readUTF();
         Mob.arrMobTemplate[mobTemplateIndex].hp = input.readLong();
         Mob.arrMobTemplate[mobTemplateIndex].rangeMove = input.readByte();
         Mob.arrMobTemplate[mobTemplateIndex].speed = input.readByte();
         Mob.arrMobTemplate[mobTemplateIndex].dartType = input.readByte();
      }
   }

   private static void createData(DataInputStream d, boolean isSaveRMS) throws java.io.IOException {
      GameScr.vcData = d.readByte();
      Res.outz("vcData= " + GameScr.vcData + " vData=" + GameScr.vsData);
      Rms.saveRMS("NR_dart", Firework.readLengthPrefixedBytes(d));
      Rms.saveRMS("NR_arrow", Firework.readLengthPrefixedBytes(d));
      Rms.saveRMS("NR_effect", Firework.readLengthPrefixedBytes(d));
      Rms.saveRMS("NR_image", Firework.readLengthPrefixedBytes(d));
      Rms.saveRMS("NR_part", Firework.readLengthPrefixedBytes(d));
      Rms.saveRMS("NR_skill", Firework.readLengthPrefixedBytes(d));
      Rms.deleteRecord("NRdata");
   }

   private static Image createImage(byte[] arr) {
      try {
         return Image.createImage(arr, 0, arr.length);
      } catch (Exception ignoredException) {
         return null;
      }
   }

   private static void readClanMsg(Message msg, int index) {
      try {
         ClanMessage cm = new ClanMessage();
         byte type = msg.reader().readByte();
         cm.type = type;
         cm.id = msg.reader().readInt();
         cm.playerId = msg.reader().readInt();
         cm.playerName = msg.reader().readUTF();
         cm.role = msg.reader().readByte();
         cm.time = msg.reader().readInt() + 1000000000;
         Res.outz(
            "type= " + cm.type + " id= " + cm.id + " playID= " + cm.playerId + " time= " + cm.time
         );
         boolean upToTop = false;
         GameScr.isNewClanMessage = false;
         if (type == 0) {
            String text = msg.reader().readUTF();
            Res.outz("str chat " + text);
            GameScr.isNewClanMessage = true;
            if (mFont.tahoma_7.getWidth(text) > Panel.WIDTH_PANEL - 60) {
               cm.chat = mFont.tahoma_7.splitFontArray(text, Panel.WIDTH_PANEL - 10);
            } else {
               cm.chat = new String[1];
               cm.chat[0] = text;
            }

            cm.color = msg.reader().readByte();
         } else if (type == 1) {
            cm.recieve = msg.reader().readByte();
            cm.maxCap = msg.reader().readByte();
            if (upToTop = msg.reader().readByte() == 1) {
               GameScr.isNewClanMessage = true;
            }

            if (cm.playerId != Char.myCharz().charID) {
               if (cm.recieve < cm.maxCap) {
                  cm.option = new String[]{mResources.donate};
               } else {
                  cm.option = null;
               }
            }

            if (GameCanvas.panel.cp != null) {
               GameCanvas.panel.updateRequest(cm.recieve, cm.maxCap);
            }
         } else if (type == 2 && Char.myCharz().role == 0) {
            GameScr.isNewClanMessage = true;
            cm.option = new String[]{mResources.CANCEL, mResources.receive};
         }

         if (GameCanvas.currentScreen != GameScr.instance) {
            GameScr.isNewClanMessage = false;
         } else if (GameCanvas.panel.isShow
            && GameCanvas.panel.type == 0
            && GameCanvas.panel.currentTabIndex == 3) {
            GameScr.isNewClanMessage = false;
         }

         ClanMessage.addMessage(cm, index, upToTop);
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private static void loadCurrMap(byte teleport3) {
      Res.outz("is loading map = " + Char.isLoadingMap);
      GameScr.gI().auto = 0;
      GameScr.isChangeZone = false;
      CreateCharScr.instance = null;
      GameScr.info1.isUpdate = false;
      GameScr.info2.isUpdate = false;
      GameScr.lockTick = 0;
      GameCanvas.panel.isShow = false;
      SoundMn.gI();
      SoundMn.stopAll();
      if (!GameScr.isLoadAllData && !CreateCharScr.isCreateChar) {
         GameScr.gI().readPart();
      }

      GameScr.loadCamera(teleport3 == 1 ? Char.myCharz().cx : -1, teleport3 == 0 ? -1 : 0);
      TileMap.getTile();
      TileMap.loadMap(TileMap.tileID);
      Res.outz("LOAD GAMESCR 2");
      Char.myCharz().cvx = 0;
      Char.myCharz().statusMe = 4;
      Char.myCharz().currentMovePoint = null;
      Char.myCharz().mobFocus = null;
      Char.myCharz().charFocus = null;
      Char.myCharz().npcFocus = null;
      Char.myCharz().itemFocus = null;
      Char.myCharz().skillPaint = null;
      Char.myCharz().setMabuHold(false);
      Char.myCharz().skillPaintRandomPaint = null;
      GameCanvas.clearAllPointerEvent();
      if (Char.myCharz().cy >= TileMap.pxh - 100) {
         Char.myCharz().isFlyUp = true;
         Char player = Char.myCharz();
         player.cx = player.cx + Res.abs(Res.random(0, 80));
         Service.gI().charMove();
      }

      GameScr.gI().loadSplash();
      GameCanvas.loadBG(TileMap.bgID);
      Char.isLockKey = false;

      for (int effIndex = 0; effIndex < Char.myCharz().vEff.size(); effIndex++) {
         EffectChar eff = (EffectChar)Char.myCharz().vEff.elementAt(effIndex);
      }

      GameCanvas.clearKeyHold();
      GameCanvas.clearKeyPressed();
      GameScr.gI().dHP = Char.myCharz().cHP;
      GameScr.gI().dMP = Char.myCharz().cMP;
      Char.ischangingMap = false;
      GameScr.gI().switchToMe();
      if (Char.myCharz().cy <= 10 && teleport3 != 0 && teleport3 != 2) {
         int planet = teleport3 == 1 ? Char.myCharz().cgender : teleport3;
         Teleport.addTeleport(
            new Teleport(
               Char.myCharz().cx,
               Char.myCharz().cy,
               Char.myCharz().head,
               Char.myCharz().cdir,
               1,
               true,
               planet
            )
         );
         Char.myCharz().isTeleport = true;
      }

      if (teleport3 == 2) {
         Char.myCharz().show();
      }

      if (GameScr.gI().isRongThanXuatHien) {
         if (TileMap.mapID == GameScr.gI().mapRID
            && TileMap.zoneID == GameScr.gI().zoneRID) {
            GameScr.gI().callRongThan(GameScr.gI().xR, GameScr.gI().yR);
         }

         if (mGraphics.zoomLevel > 1) {
            GameScr.gI().doiMauTroi();
         }
      }

      InfoDlg.hide();
      InfoDlg.show(TileMap.mapName, mResources.zone + " " + TileMap.zoneID, 30);
      GameCanvas.endDlg();
      GameCanvas.isLoading = false;
      Hint.clickMob();
      Hint.clickNpc();
      GameCanvas.debug();
      GameCanvas.isRequestMapID = 2;
      GameCanvas.waitingTimeChangeMap = mSystem.currentTimeMillis() + 1000L;
   }

   private void loadInfoMap(Message msg) {
      try {
         if (mGraphics.zoomLevel == 1) {
            SmallImage.clearHastable();
         }

         Char.myCharz().cx = Char.myCharz().cxSend = Char.myCharz().cxFocus = msg.reader()
            .readShort();
         Char.myCharz().cy = Char.myCharz().cySend = Char.myCharz().cyFocus = msg.reader()
            .readShort();
         Char.myCharz().xSd = Char.myCharz().cx;
         Char.myCharz().ySd = Char.myCharz().cy;
         Res.outz(
            "head= "
               + Char.myCharz().head
               + " body= "
               + Char.myCharz().body
               + " left= "
               + Char.myCharz().leg
               + " x= "
               + Char.myCharz().cx
               + " y= "
               + Char.myCharz().cy
               + " chung toc= "
               + Char.myCharz().cgender
         );
         if (Char.myCharz().cx >= 0 && Char.myCharz().cx <= 100) {
            Char.myCharz().cdir = 1;
         } else if (Char.myCharz().cx >= TileMap.tmw - 100
            && Char.myCharz().cx <= TileMap.tmw) {
            Char.myCharz().cdir = -1;
         }

         GameCanvas.debug();
         byte itemMapCount = msg.reader().readByte();
         Res.outz("vGo size= " + itemMapCount);
         if (!GameScr.info1.isDone) {
            GameScr.info1.cmx = Char.myCharz().cx - GameScr.cmx;
            GameScr.info1.cmy = Char.myCharz().cy - GameScr.cmy;
         }

         for (int mobIndex = 0; mobIndex < itemMapCount; mobIndex++) {
            Waypoint waypoint = new Waypoint(
               msg.reader().readShort(),
               msg.reader().readShort(),
               msg.reader().readShort(),
               msg.reader().readShort(),
               msg.reader().readBoolean(),
               msg.reader().readBoolean(),
               msg.reader().readUTF()
            );
            if ((TileMap.mapID == 21 || TileMap.mapID == 22 || TileMap.mapID == 23) && waypoint.minX >= 0) {
               ;
            }
         }

         GameCanvas.debug();
         itemMapCount = msg.reader().readByte();
         Mob.newMob.removeAllElements();

         for (byte id = 0; id < itemMapCount; id++) {
            Mob entry;
            (entry = new Mob(
                  id,
                  msg.reader().readBoolean(),
                  msg.reader().readBoolean(),
                  msg.reader().readBoolean(),
                  msg.reader().readBoolean(),
                  msg.reader().readBoolean(),
                  msg.reader().readShort(),
                  msg.reader().readByte(),
                  msg.reader().readLong(),
                  msg.reader().readByte(),
                  msg.reader().readLong(),
                  msg.reader().readShort(),
                  msg.reader().readShort(),
                  msg.reader().readByte(),
                  msg.reader().readByte()
               ))
               .xSd = entry.x;
            entry.ySd = entry.y;
            entry.isBoss = msg.reader().readBoolean();
            if (Mob.arrMobTemplate[entry.templateId].type != 0) {
               if (id % 3 == 0) {
                  entry.dir = -1;
               } else {
                  entry.dir = 1;
               }

               entry.x += 10 - id % 20;
            }

            entry.isMobMe = false;
            BigBoss entry2 = null;
            BachTuoc entry3 = null;
            BigBoss2 entry4 = null;
            NewBoss entry5 = null;
            if (entry.templateId == 70) {
               entry2 = new BigBoss(
                  id,
                  (short)entry.x,
                  (short)entry.y,
                  70,
                  entry.hp,
                  entry.maxHp,
                  entry.sys
               );
            }

            if (entry.templateId == 71) {
               entry3 = new BachTuoc(id, (short)entry.x, (short)entry.y, 71, entry.hp, entry.maxHp);
            }

            if (entry.templateId == 72) {
               entry4 = new BigBoss2(id, (short)entry.x, (short)entry.y, 72, entry.hp, entry.maxHp);
            }

            if (entry.isBoss) {
               entry5 = new NewBoss(
                  id, (short)entry.x, (short)entry.y, entry.templateId, entry.hp, entry.maxHp
               );
            }

            if (entry5 != null) {
               GameScr.vMob.addElement(entry5);
            } else if (entry2 != null) {
               GameScr.vMob.addElement(entry2);
            } else if (entry3 != null) {
               GameScr.vMob.addElement(entry3);
            } else if (entry4 != null) {
               GameScr.vMob.addElement(entry4);
            } else {
               GameScr.vMob.addElement(entry);
            }
         }

         if (Char.myCharz().mobMe != null
            && GameScr.findMobInMap(((int) (Char.myCharz().mobMe.mobId))) == null) {
            Char.myCharz().mobMe.getData();
            Char.myCharz().mobMe.x = Char.myCharz().cx;
            Char.myCharz().mobMe.y = Char.myCharz().cy - 40;
            GameScr.vMob.addElement(Char.myCharz().mobMe);
         }

         msg.reader().readByte();
         GameCanvas.debug();
         itemMapCount = msg.reader().readByte();
         Res.outz("NPC size= " + itemMapCount);

         for (int npcId = 0; npcId < itemMapCount; npcId++) {
            byte status = msg.reader().readByte();
            short cx = msg.reader().readShort();
            short cy = msg.reader().readShort();
            byte templateId = msg.reader().readByte();
            Res.outz("tempalte = " + templateId);
            short avatar = msg.reader().readShort();
            Res.outz("1");
            if (templateId != 6) {
               Res.outz("2");
               if (Char.myCharz().taskMaint.taskId < 7
                  || Char.myCharz().taskMaint.taskId == 7 && Char.myCharz().taskMaint.index <= 1) {
                  if (templateId == 7 || templateId == 8 || templateId == 9) {
                     continue;
                  }

                  Res.outz("3");
               }

               if (Char.myCharz().taskMaint.taskId >= 6 || templateId != 16) {
                  if (templateId == 4) {
                     GameScr.gI().magicTree = new MagicTree(npcId, status, cx, cy, templateId, avatar);
                     Service.gI().magicTree((byte)2);
                     GameScr.vNpc.addElement(GameScr.gI().magicTree);
                     Res.outz("5");
                  } else {
                     Npc entry6 = new Npc(npcId, status, cx, cy + 3, templateId, avatar);
                     GameScr.vNpc.addElement(entry6);
                     Res.outz("6");
                  }
               }
            }
         }

         GameCanvas.debug();
         itemMapCount = msg.reader().readByte();
         Res.outz("item size = " + itemMapCount);

         for (int itemMapIndex = 0; itemMapIndex < itemMapCount; itemMapIndex++) {
            short packetShort = msg.reader().readShort();
            short packetShort2 = msg.reader().readShort();
            short packetShort3 = msg.reader().readShort();
            short packetShort4 = msg.reader().readShort();
            int packetInt = msg.reader().readInt();
            short yEnd = 0;
            if (packetInt == -2) {
               yEnd = msg.reader().readShort();
            }

            ItemMap entry7 = new ItemMap(packetInt, packetShort, packetShort2, packetShort3, packetShort4, yEnd);
            boolean condition = false;

            for (int itemMapIndex2 = 0; itemMapIndex2 < GameScr.vItemMap.size(); itemMapIndex2++) {
               if (((ItemMap)GameScr.vItemMap.elementAt(itemMapIndex2)).itemMapID == entry7.itemMapID) {
                  condition = true;
                  break;
               }
            }

            if (!condition) {
               GameScr.vItemMap.addElement(entry7);
            }
         }

         TileMap.vCurrItem.removeAllElements();
         if (mGraphics.zoomLevel == 1) {
            BgItem.clearHashTable();
         }

         BgItem.vKeysNew.removeAllElements();
         if (GameCanvas.lowGraphic
            && (!GameCanvas.lowGraphic || !TileMap.isVoDaiMap())
            && TileMap.mapID != 45
            && TileMap.mapID != 46
            && TileMap.mapID != 47
            && TileMap.mapID != 48
            && TileMap.mapID != 120
            && TileMap.mapID != 128
            && TileMap.mapID != 170
            && TileMap.mapID != 49) {
            short packetShort5 = msg.reader().readShort();

            for (int index = 0; index < packetShort5; index++) {
               msg.reader().readShort();
               msg.reader().readShort();
               msg.reader().readShort();
            }

            short packetShort6 = msg.reader().readShort();

            for (int index2 = 0; index2 < packetShort6; index2++) {
               msg.reader().readUTF();
               msg.reader().readUTF();
            }
         } else {
            int packetShort7 = msg.reader().readShort();

            for (int index3 = 0; index3 < packetShort7; index3++) {
               short id2 = msg.reader().readShort();
               short packetShort8 = msg.reader().readShort();
               short packetShort9 = msg.reader().readShort();
               if (TileMap.getBIById(id2) != null) {
                  BgItem bIById = TileMap.getBIById(id2);
                  BgItem currItem;
                  (currItem = new BgItem()).id = id2;
                  currItem.idImage = bIById.idImage;
                  currItem.dx = bIById.dx;
                  currItem.dy = bIById.dy;
                  currItem.x = packetShort8 * TileMap.size;
                  currItem.y = packetShort9 * TileMap.size;
                  currItem.layer = bIById.layer;
                  if (TileMap.isExistMoreOne(currItem.id)) {
                     currItem.trans = index3 % 2 == 0 ? 0 : 2;
                     if (TileMap.mapID == 45) {
                        currItem.trans = 0;
                     }
                  }

                  Object referenceSlot2 = null;
                  if (!BgItem.imgNew.containsKey(String.valueOf(currItem.idImage))) {
                     if (mGraphics.zoomLevel == 1) {
                        Image rGBImage;
                        if ((rGBImage = mSystem.loadZoomedResourceImage("/mapBackGround/" + currItem.idImage + ".png")) == null) {
                           rGBImage = Image.createRGBImage(new int[]{-2013265920}, 1, 1, true);
                           Session_ME.gI2().isConnected();
                           Service.gI().getBgTemplate(currItem.idImage);
                        }

                        BgItem.imgNew.put(String.valueOf(currItem.idImage), rGBImage);
                     } else {
                        boolean condition2 = false;
                        byte[] rMS;
                        if ((rMS = Rms.loadRMS(mGraphics.zoomLevel + "bgItem" + currItem.idImage)) != null) {
                           if (BgItem.newSmallVersion != null) {
                              Res.outz(
                                 "Small  last= " + rMS.length % 127 + "new Version= " + BgItem.newSmallVersion[currItem.idImage]
                              );
                              if (rMS.length % 127 != BgItem.newSmallVersion[currItem.idImage]) {
                                 condition2 = true;
                              }
                           }

                           if (!condition2) {
                              Image image;
                              if ((image = Image.createImage(rMS, 0, rMS.length)) != null) {
                                 BgItem.imgNew.put(String.valueOf(currItem.idImage), image);
                              } else {
                                 condition2 = true;
                              }
                           }
                        } else {
                           condition2 = true;
                        }

                        if (condition2) {
                           Image rGBImage2;
                           if ((rGBImage2 = mSystem.loadZoomedResourceImage("/mapBackGround/" + currItem.idImage + ".png")) == null) {
                              rGBImage2 = Image.createRGBImage(new int[]{-2013265920}, 1, 1, true);
                              Service.gI().getBgTemplate(currItem.idImage);
                           }

                           BgItem.imgNew.put(String.valueOf(currItem.idImage), rGBImage2);
                        }
                     }

                     BgItem.vKeysLast.addElement(String.valueOf(currItem.idImage));
                  }

                  if (!BgItem.isExistKeyNews(String.valueOf(currItem.idImage))) {
                     BgItem.vKeysNew.addElement(String.valueOf(currItem.idImage));
                  }

                  BgItem.changeColor();
                  TileMap.vCurrItem.addElement(currItem);
               }
            }

            for (int keysLastIndex = 0; keysLastIndex < BgItem.vKeysLast.size(); keysLastIndex++) {
               String keysLast;
               if (!BgItem.isExistKeyNews(keysLast = (String)BgItem.vKeysLast.elementAt(keysLastIndex))) {
                  BgItem.imgNew.remove(keysLast);
                  if (BgItem.imgNew.containsKey(keysLast + "blend" + 1)) {
                     BgItem.imgNew.remove(keysLast + "blend" + 1);
                  }

                  if (BgItem.imgNew.containsKey(keysLast + "blend" + 3)) {
                     BgItem.imgNew.remove(keysLast + "blend" + 3);
                  }

                  BgItem.vKeysLast.removeElementAt(keysLastIndex);
                  keysLastIndex--;
               }
            }

            BackgroudEffect.isFog = false;
            BackgroudEffect.nCloud = 0;
            EffecMn.vEff.removeAllElements();
            BackgroudEffect.vBgEffect.removeAllElements();
            Effect.newEff.removeAllElements();
            short packetShort10 = msg.reader().readShort();
            System.out.println("nKey= " + packetShort10);

            for (int index4 = 0; index4 < packetShort10; index4++) {
               String packetText = msg.reader().readUTF();
               String packetText2 = msg.reader().readUTF();
               System.out.println("key= " + packetText + " value= " + packetText2 + "--------------------------------------------------------------------");
               String original = packetText2;
               String text = packetText;
               if (packetText.equals("eff")) {
                  if (Panel.graphics <= 0) {
                     String[] parts;
                     int id3 = Integer.parseInt((parts = Res.split(original, ".", 0))[0]);
                     int value = Integer.parseInt(parts[1]);
                     int x = Integer.parseInt(parts[2]);
                     int value2 = Integer.parseInt(parts[3]);
                     int value3;
                     if (parts.length <= 4) {
                        packetShort7 = -1;
                        value3 = 1;
                     } else {
                        packetShort7 = Integer.parseInt(parts[4]);
                        value3 = Integer.parseInt(parts[5]);
                     }

                     Effect meValue = new Effect(id3, x, value2, value, packetShort7, value3);
                     if (parts.length > 6) {
                        meValue.typeEff = Integer.parseInt(parts[6]);
                        if (parts.length > 7) {
                           meValue.indexFrom = Integer.parseInt(parts[7]);
                           meValue.indexTo = Integer.parseInt(parts[8]);
                        }
                     }

                     EffecMn.addEff(meValue);
                  }
               } else if (text.equals("beff") && Panel.graphics <= 1) {
                  BackgroudEffect.addEffect(Integer.parseInt(original));
               }
            }
         }

         TileMap.bgType = msg.reader().readByte();
         loadCurrMap(msg.reader().readByte());
         GameCanvas.debug();
      } catch (Exception ignoredException) {
         Res.err(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Loadmap khong thanh cong");
         GameCanvas.doResetToLoginScr();
         ServerListScreen.waitToLogin = true;
         GameCanvas.endDlg();
      }

      GameCanvas.isLoading = false;
      Res.err(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Loadmap thanh cong");
   }

   private void messageNotMap(Message msg) {
      GameCanvas.debug();

      try {
         byte longValueCount = msg.reader().readByte();
         mSystem.println("messageNotMap: cmd:" + longValueCount);
         switch (longValueCount) {
            case 4:
               Res.outz("vao cmd 4 UPDATE_VERSION SA8", 2);
               GameCanvas.loginScr.savePass();
               GameScr.isAutoPlay = false;
               GameScr.canAutoPlay = false;
               LoginScr.isUpdateAll = true;
               LoginScr.isUpdateData = true;
               LoginScr.isUpdateMap = true;
               LoginScr.isUpdateSkill = true;
               LoginScr.isUpdateItem = true;
               GameScr.vsData = msg.reader().readByte();
               GameScr.vsMap = msg.reader().readByte();
               GameScr.vsSkill = msg.reader().readByte();
               GameScr.vsItem = msg.reader().readByte();
               msg.reader().readByte();
               GameScr.resetClientDataReadiness();
               if (GameCanvas.loginScr.isLogin2) {
                  Rms.saveRMSString(Rms.RMS_acc, "");
                  Rms.saveRMSString(Rms.RMS_pass, "");
               } else {
                  Rms.saveRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect, "");
               }

               Res.outz("****** DATA VERSION: Server " + GameScr.vsData + " Client " + GameScr.vcData);
               Res.outz("****** MAP VERSION: Server " + GameScr.vsMap + " Client " + GameScr.vcMap);
               Res.outz("****** SKILL VERSION: Server " + GameScr.vsSkill + " Client " + GameScr.vcSkill);
               Res.outz("****** ITEM VERSION: Server " + GameScr.vsItem + " Client " + GameScr.vcItem);
               if (GameScr.vsData != GameScr.vcData) {
                  Res.outz("send update data");
                  GameScr.isLoadAllData = false;
                  Service.gI().updateData();
               } else {
                  try {
                     LoginScr.isUpdateData = false;
                  } catch (Exception exception) {
                     exception.printStackTrace();
                     GameScr.vcData = -1;
                     Service.gI().updateData();
                  }
               }

               if (GameScr.vsMap != GameScr.vcMap) {
                  GameScr.isLoadAllData = false;
                  Service.gI().updateMap();
               } else {
                  try {
                     if (!GameScr.isLoadAllData) {
                        createMap(new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NRmap"))));
                     }

                     LoginScr.isUpdateMap = false;
                  } catch (Exception ignoredException) {
                     GameScr.vcMap = -1;
                     Service.gI().updateMap();
                  }
               }

               if (GameScr.vsSkill != GameScr.vcSkill) {
                  GameScr.isLoadAllData = false;
                  Service.gI().updateSkill();
               } else {
                  try {
                     if (!GameScr.isLoadAllData) {
                        createSkill(new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NRskill"))));
                     }

                     LoginScr.isUpdateSkill = false;
                  } catch (Exception ignoredException2) {
                     GameScr.vcSkill = -1;
                     Service.gI().updateSkill();
                  }
               }

               if (GameScr.vsItem != GameScr.vcItem) {
                  GameScr.isLoadAllData = false;
                  GameScr.waitingForItemData = true;
                  GameScr.lastItemDataPacketAt = mSystem.currentTimeMillis();
                  Service.gI().updateItem();
               } else {
                  GameScr.waitingForItemData = false;

                  try {
                     DataInputStream input = new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NRitem0")));
                     this.loadItemNew(input, false);
                     DataInputStream input2 = new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NRitem1")));
                     this.loadItemNew(input2, false);
                     DataInputStream input3 = new DataInputStream(new ByteArrayInputStream(Rms.loadRMS("NRitem100")));
                     this.loadItemNew(input3, false);
                     LoginScr.isUpdateItem = false;
                  } catch (Exception ignoredException3) {
                     GameScr.vcItem = -1;
                     Service.gI().updateItem();
                  }

                  try {
                     byte[] storedNRitem101;
                     if ((storedNRitem101 = Rms.loadRMS("NRitem101")) != null) {
                        DataInputStream input4 = new DataInputStream(new ByteArrayInputStream(storedNRitem101));
                        this.loadItemNew(input4, false);
                     }
                  } catch (Exception ignoredException4) {
                  }
               }

               if (!GameScr.isLoadAllData) {
                  GameScr.gI();
                  GameScr.readOk();
               } else {
                  Service.gI().clientOk();
               }

               longValueCount = msg.reader().readByte();
               Res.outz("CAPTION LENT= " + longValueCount);
               GameScr.exps = new long[longValueCount];

               for (int expIndex = 0; expIndex < GameScr.exps.length; expIndex++) {
                  GameScr.exps[expIndex] = msg.reader().readLong();
               }
               break;
            case 6:
               Res.outz("GET UPDATE_MAP " + msg.reader().available() + " bytes");
               msg.reader().mark(100000);
               createMap(msg.reader());
               msg.reader().reset();
               byte[] data = new byte[msg.reader().available()];
               msg.reader().readFully(data);
               Rms.saveRMS("NRmap", data);
               byte[] data2 = new byte[]{GameScr.vcMap};
               Rms.saveRMS("NRmapVersion", data2);
               Res.outz("GET UPDATE_MAP NRmapVersion:" + data2);
               LoginScr.isUpdateMap = false;
               GameScr.gI();
               GameScr.readOk();
               break;
            case 7:
               Res.outz("GET UPDATE_SKILL " + msg.reader().available() + " bytes");
               msg.reader().mark(100000);
               createSkill(msg.reader());
               msg.reader().reset();
               byte[] data3 = new byte[msg.reader().available()];
               msg.reader().readFully(data3);
               Rms.saveRMS("NRskill", data3);
               byte[] data4 = new byte[]{GameScr.vcSkill};
               Rms.saveRMS("NRskillVersion", data4);
               LoginScr.isUpdateSkill = false;
               GameScr.gI();
               GameScr.readOk();
               break;
            case 8:
               Res.outz("GET UPDATE_ITEM " + msg.reader().available() + " bytes");
               DataInputStream input5 = msg.reader();
               Controller controller = this;

               try {
                  controller.loadItemNew(input5, true);
               } catch (Exception exception2) {
                  exception2.printStackTrace();
               }

               GameScr.gI();
               GameScr.readOk();
               break;
            case 9:
               GameCanvas.debug();
               break;
            case 10:
               try {
                  Char.isLoadingMap = true;
                  Res.outz("REQUEST MAP TEMPLATE");
                  GameCanvas.isLoading = true;
                  TileMap.maps = null;
                  TileMap.types = null;
                  System.gc();
                  GameCanvas.debug();
                  TileMap.tmw = msg.reader().readByte();
                  TileMap.tmh = msg.reader().readByte();
                  TileMap.maps = new int[TileMap.tmw * TileMap.tmh];
                  Res.outz(
                     "Cmd.REQUEST_MAPTEMPLATE=10  w|H= "
                        + TileMap.tmw
                        + "|"
                        + TileMap.tmh
                        + "\n Dien Tich = "
                        + TileMap.tmw * TileMap.tmh
                  );

                  for (int mapIndex = 0; mapIndex < TileMap.maps.length; mapIndex++) {
                     short packetByte;
                     if ((packetByte = msg.reader().readByte()) < 0) {
                        packetByte += 256;
                     }

                     TileMap.maps[mapIndex] = (char)packetByte;
                  }

                  TileMap.types = new int[TileMap.maps.length];
                  msg = this.messWait;
                  this.loadInfoMap(msg);

                  try {
                     TileMap.isMapDouble = msg.reader().readByte() != 0;
                  } catch (Exception ignoredException5) {
                  }
               } catch (Exception exception3) {
                  exception3.printStackTrace();
               }

               msg.cleanup();
               this.messWait.cleanup();
               msg = this.messWait = null;
               break;
            case 16:
               MoneyCharge.gI().switchToMe();
               break;
            case 17:
               GameCanvas.debug();
               Char.myCharz();
               Char.clearTask();
               break;
            case 18:
               GameCanvas.isLoading = false;
               GameCanvas.endDlg();
               Integer boxedInteger = new Integer(msg.reader().readInt());
               ((nro.InputDlg) null)
                  .show(
                     mResources.changeNameChar, new Command(mResources.OK, GameCanvas.instance, 88829, boxedInteger), 0
                  );
               break;
            case 20:
               Char.myCharz().cPk = msg.reader().readByte();
               GameScr.info1.addInfo(mResources.PK_NOW + " " + Char.myCharz().cPk, 0);
            case 33:
            default:
               break;
            case 35:
               GameCanvas.endDlg();
               GameScr.gI().resetButton();
               GameScr.info1.addInfo(msg.reader().readUTF(), 0);
               break;
            case 36:
               GameScr.typeActive = msg.reader().readByte();
               Res.outz("load Me Active: " + GameScr.typeActive);
         }
      } catch (Exception ignoredException6) {
      } finally {
         if (msg != null) {
            msg.cleanup();
         }
      }
   }

   private static void messageNotLogin(Message msg) {
      try {
         switch (msg.reader().readByte()) {
            case 2:
               ServerListScreen.linkDefault = msg.reader().readUTF();
               mSystem.AddIpTest();
               ServerListScreen.getServerList(ServerListScreen.linkDefault);

               try {
                  Panel.CanNapTien = msg.reader().readByte() == 1;
               } catch (Exception ignoredException) {
               }

               isGet_CLIENT_INFO = true;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         if (msg != null) {
            msg.cleanup();
         }
      }
   }

   private void messageSubCommand(Message msg) {
      try {
         GameCanvas.debug();
         int charId = msg.reader().readByte();
         Res.outz("sub: " + charId);
         switch (charId) {
            case 0:
               RadarScr.list = new MyVector("");
               GameCanvas.debug();
               Teleport.vTeleport.removeAllElements();
               GameScr.vCharInMap.removeAllElements();
               GameScr.vItemMap.removeAllElements();
               Char.vItemTime.removeAllElements();
               GameScr.loadImg();
               GameScr.currentCharViewInfo = Char.myCharz();
               Char.myCharz().charID = msg.reader().readInt();
               Char.myCharz().ctaskId = msg.reader().readByte();
               Char.myCharz().cgender = msg.reader().readByte();
               Char.myCharz().head = msg.reader().readShort();
               Res.outz("Char head = " + Char.myCharz().head + "+++++++++++++++++++++++++++++++++");
               Char.myCharz().cName = msg.reader().readUTF();
               Char.myCharz().cPk = msg.reader().readByte();
               Char.myCharz().cTypePk = msg.reader().readByte();
               Char.myCharz().cPower = msg.reader().readLong();
               Char.myCharz().applyCharLevelPercent();
               Char.myCharz().eff5BuffHp = msg.reader().readShort();
               Char.myCharz().eff5BuffMp = msg.reader().readShort();
               Char.myCharz().nClass = GameScr.nClasss[msg.reader().readByte()];
               Char.myCharz().vSkill.removeAllElements();
               Char.myCharz().vSkillFight.removeAllElements();
               GameScr.gI().dHP = Char.myCharz().cHP;
               GameScr.gI().dMP = Char.myCharz().cMP;
               byte packetByte = msg.reader().readByte();

               for (byte index = 0; index < packetByte; index++) {
                  useSkill(Skills.get(msg.reader().readShort()));
               }

               GameScr.gI();
               GameScr.sortSkill();
               GameScr.gI();
               GameScr.loadSkillShortcut();
               Char.myCharz().xu = msg.reader().readLong();
               Char.myCharz().luongKhoa = msg.reader().readInt();
               Char.myCharz().luong = msg.reader().readInt();
               Char.myCharz().luongStr = mSystem.numberTostring(Char.myCharz().luong);
               Char.myCharz().luongKhoaStr = mSystem.numberTostring(Char.myCharz().luongKhoa);
               Char.myCharz().xuStr = Res.formatNumber(Char.myCharz().xu);
               Char.myCharz().arrItemBody = new Item[msg.reader().readByte()];

               try {
                  Char.myCharz().setDefaultPart();

                  for (int itemBodyIndex = 0; itemBodyIndex < Char.myCharz().arrItemBody.length; itemBodyIndex++) {
                     short id;
                     if ((id = msg.reader().readShort()) != -1) {
                        ItemTemplate template;
                        byte type = (template = ItemTemplates.get(id)).type;
                        Char.myCharz().arrItemBody[itemBodyIndex] = new Item();
                        Char.myCharz().arrItemBody[itemBodyIndex].template = template;
                        Char.myCharz().arrItemBody[itemBodyIndex].quantity = msg.reader().readInt();
                        msg.reader().readUTF();
                        msg.reader().readUTF();
                        int itemOptionCount = msg.reader().readUnsignedByte();
                        Res.outz("option size= " + itemOptionCount);
                        if (itemOptionCount != 0) {
                           Char.myCharz().arrItemBody[itemBodyIndex].itemOption = new ItemOption[itemOptionCount];

                           for (int itemOptionIndex = 0; itemOptionIndex < Char.myCharz().arrItemBody[itemBodyIndex].itemOption.length; itemOptionIndex++) {
                              ItemOption itemOption;
                              if ((itemOption = readItemOption(msg)) != null) {
                                 Char.myCharz().arrItemBody[itemBodyIndex].itemOption[itemOptionIndex] = itemOption;
                              }
                           }
                        }

                        if (type == 0) {
                           Res.outz("toi day =======================================" + Char.myCharz().body);
                           Char.myCharz().body = Char.myCharz().arrItemBody[itemBodyIndex].template.part;
                        } else if (type == 1) {
                           Char.myCharz().leg = Char.myCharz().arrItemBody[itemBodyIndex].template.part;
                           Res.outz("toi day =======================================" + Char.myCharz().leg);
                        }
                     }
                  }
               } catch (Exception exception) {
                  exception.printStackTrace();
               }

               Char.myCharz().arrItemBag = new Item[msg.reader().readByte()];
               GameScr.hpPotion = 0;
               GameScr.isudungCapsun4 = false;
               GameScr.isudungCapsun3 = false;

               for (int indexUI = 0; indexUI < Char.myCharz().arrItemBag.length; indexUI++) {
                  short id2;
                  if ((id2 = msg.reader().readShort()) != -1) {
                     Char.myCharz().arrItemBag[indexUI] = new Item();
                     Char.myCharz().arrItemBag[indexUI].template = ItemTemplates.get(id2);
                     Char.myCharz().arrItemBag[indexUI].quantity = msg.reader().readInt();
                     msg.reader().readUTF();
                     msg.reader().readUTF();
                     Char.myCharz().arrItemBag[indexUI].indexUI = indexUI;
                     byte itemOptionCount2;
                     if ((itemOptionCount2 = msg.reader().readByte()) != 0) {
                        Char.myCharz().arrItemBag[indexUI].itemOption = new ItemOption[itemOptionCount2];

                        for (int itemOptionIndex2 = 0; itemOptionIndex2 < Char.myCharz().arrItemBag[indexUI].itemOption.length; itemOptionIndex2++) {
                           ItemOption itemOption2;
                           if ((itemOption2 = readItemOption(msg)) != null) {
                              Char.myCharz().arrItemBag[indexUI].itemOption[itemOptionIndex2] = itemOption2;
                              Char.myCharz().arrItemBag[indexUI].getCompare();
                           }
                        }
                     }

                     if (Char.myCharz().arrItemBag[indexUI].template.type == 6) {
                        GameScr.hpPotion = GameScr.hpPotion + Char.myCharz().arrItemBag[indexUI].quantity;
                     }

                     if (id2 == 194) {
                        GameScr.isudungCapsun4 = Char.myCharz().arrItemBag[indexUI].quantity > 0;
                     } else if (id2 == 193 && !GameScr.isudungCapsun4) {
                        GameScr.isudungCapsun3 = Char.myCharz().arrItemBag[indexUI].quantity > 0;
                     }
                  }
               }

               Char.myCharz().arrItemBox = new Item[msg.reader().readByte()];
               GameCanvas.panel.hasUse = 0;

               for (int itemBoxIndex = 0; itemBoxIndex < Char.myCharz().arrItemBox.length; itemBoxIndex++) {
                  short id3;
                  if ((id3 = msg.reader().readShort()) != -1) {
                     Char.myCharz().arrItemBox[itemBoxIndex] = new Item();
                     Char.myCharz().arrItemBox[itemBoxIndex].template = ItemTemplates.get(id3);
                     Char.myCharz().arrItemBox[itemBoxIndex].quantity = msg.reader().readInt();
                     msg.reader().readUTF();
                     msg.reader().readUTF();
                     Char.myCharz().arrItemBox[itemBoxIndex].itemOption = new ItemOption[msg.reader().readByte()];

                     for (int itemOptionIndex3 = 0; itemOptionIndex3 < Char.myCharz().arrItemBox[itemBoxIndex].itemOption.length; itemOptionIndex3++) {
                        ItemOption itemOption3;
                        if ((itemOption3 = readItemOption(msg)) != null) {
                           Char.myCharz().arrItemBox[itemBoxIndex].itemOption[itemOptionIndex3] = itemOption3;
                           Char.myCharz().arrItemBox[itemBoxIndex].getCompare();
                        }
                     }

                     GameCanvas.panel.hasUse++;
                  }
               }

               Char.myCharz().statusMe = 4;
               if (Rms.loadRMSInt(Char.myCharz().cName + "vci") <= 0) {
                  GameScr.isViewClanInvite = false;
               } else {
                  GameScr.isViewClanInvite = true;
               }

               short shortValueCount;
               Char.idHead = new short[shortValueCount = msg.reader().readShort()];
               Char.idAvatar = new short[shortValueCount];

               for (int idAvatarIndex = 0; idAvatarIndex < shortValueCount; idAvatarIndex++) {
                  Char.idHead[idAvatarIndex] = msg.reader().readShort();
                  Char.idAvatar[idAvatarIndex] = msg.reader().readShort();
               }

               GameScr.info1.charId[Char.myCharz().cgender][0] = msg.reader().readShort();
               GameScr.info1.charId[Char.myCharz().cgender][1] = msg.reader().readShort();
               GameScr.info1.charId[Char.myCharz().cgender][2] = msg.reader().readShort();
               Char.myCharz().isNhapThe = msg.reader().readByte() == 1;
               GameScr.deltaTime = mSystem.currentTimeMillis() - msg.reader().readInt() * 1000L;
               GameScr.isNewMember = msg.reader().readByte();
               Res.outz("delta time= " + GameScr.deltaTime);
               Service.gI().updateCaption((byte)Char.myCharz().cgender);
               Service.gI().onPrimaryClientInfoSent();

               try {
                  Char.myCharz().idAuraEff = msg.reader().readShort();
                  Char.myCharz().idEff_Set_Item = msg.reader().readByte();
                  Char.myCharz().idHat = msg.reader().readShort();
               } catch (Exception ignoredException) {
               }

               return;
            case 1:
               GameCanvas.debug();
               Char.myCharz().nClass = GameScr.nClasss[msg.reader().readByte()];
               Char.myCharz().cTiemNang = msg.reader().readLong();
               Char.myCharz().vSkill.removeAllElements();
               Char.myCharz().vSkillFight.removeAllElements();
               Char.myCharz().myskill = null;
               return;
            case 2:
               GameCanvas.debug();
               if (Char.myCharz().statusMe != 14 && Char.myCharz().statusMe != 5) {
                  Char.myCharz().cHP = Char.myCharz().cHPFull;
                  Char.myCharz().cMP = Char.myCharz().cMPFull;
               }

               Char.myCharz().vSkill.removeAllElements();
               Char.myCharz().vSkillFight.removeAllElements();
               byte packetByte2 = msg.reader().readByte();

               for (byte index2 = 0; index2 < packetByte2; index2++) {
                  short skillId = msg.reader().readShort();
                  Res.outz("skidd id my  char= " + skillId);
                  useSkill(Skills.get(skillId));
               }

               GameScr.gI();
               GameScr.sortSkill();
               return;
            case 4:
               GameCanvas.debug();
               Char.myCharz().xu = msg.reader().readLong();
               Char.myCharz().luong = msg.reader().readInt();
               Char.myCharz().cHP = msg.reader().readLong();
               Char.myCharz().cMP = msg.reader().readLong();
               Char.myCharz().luongKhoa = msg.reader().readInt();
               Char.myCharz().luongStr = mSystem.numberTostring(Char.myCharz().luong);
               Char.myCharz().luongKhoaStr = mSystem.numberTostring(Char.myCharz().luongKhoa);
               Char.myCharz().xuStr = Res.formatNumber2(Char.myCharz().xu);
               return;
            case 5:
               GameCanvas.debug();
               long cHP = Char.myCharz().cHP;
               Char.myCharz().cHP = msg.reader().readLong();
               Res.outz(
                  "hpgoc  =" + Char.myCharz().cHPGoc + " hp full= " + Char.myCharz().cHPFull
               );
               if (Char.myCharz().cHP > cHP && Char.myCharz().cTypePk != 4) {
                  GameScr.startFlyText(
                     "+" + (Char.myCharz().cHP - cHP) + " " + mResources.HP,
                     Char.myCharz().cx,
                     Char.myCharz().cy - Char.myCharz().ch - 20,
                     0,
                     -1,
                     9
                  );
                  SoundMn.gI();
                  SoundMn.HP_MPup();
                  if (Char.myCharz().petFollow != null && Char.myCharz().petFollow.smallID == 5003) {
                     MonsterDart.addMonsterDart(
                        Char.myCharz().petFollow.cmx
                           + (Char.myCharz().petFollow.dir == 1 ? 10 : -10),
                        Char.myCharz().petFollow.cmy + 10,
                        true,
                        -1L,
                        -1L,
                        Char.myCharz(),
                        29
                     );
                  }
               }

               if (Char.myCharz().cHP < cHP) {
                  GameScr.startFlyText(
                     "-" + (cHP - Char.myCharz().cHP) + " " + mResources.HP,
                     Char.myCharz().cx,
                     Char.myCharz().cy - Char.myCharz().ch - 20,
                     0,
                     -1,
                     9
                  );
               }

               Res.outz("curr HP= " + Char.myCharz().cHP);
               GameScr.gI().dHP = Char.myCharz().cHP;
               return;
            case 6:
               GameCanvas.debug();
               long cMP = Char.myCharz().cMP;
               Char.myCharz().cMP = msg.reader().readLong();
               if (Char.myCharz().cMP > cMP) {
                  GameScr.startFlyText(
                     "+" + (Char.myCharz().cMP - cMP) + " " + mResources.KI,
                     Char.myCharz().cx,
                     Char.myCharz().cy - Char.myCharz().ch - 23,
                     0,
                     -2,
                     10
                  );
                  SoundMn.gI();
                  SoundMn.HP_MPup();
                  if (Char.myCharz().petFollow != null && Char.myCharz().petFollow.smallID == 5001) {
                     MonsterDart.addMonsterDart(
                        Char.myCharz().petFollow.cmx
                           + (Char.myCharz().petFollow.dir == 1 ? 10 : -10),
                        Char.myCharz().petFollow.cmy + 10,
                        true,
                        -1L,
                        -1L,
                        Char.myCharz(),
                        29
                     );
                  }
               }

               if (Char.myCharz().cMP < cMP) {
                  GameScr.startFlyText(
                     "-" + (cMP - Char.myCharz().cMP) + " " + mResources.KI,
                     Char.myCharz().cx,
                     Char.myCharz().cy - Char.myCharz().ch - 23,
                     0,
                     -2,
                     10
                  );
               }

               GameScr.gI().dMP = Char.myCharz().cMP;
               return;
            case 7:
               Char c;
               if ((c = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  c.clanID = msg.reader().readInt();
                  if (c.clanID == -2) {
                     c.isCopy = true;
                  }

                  readCharInfo(c, msg);

                  try {
                     c.idAuraEff = msg.reader().readShort();
                     c.idEff_Set_Item = msg.reader().readByte();
                     c.idHat = msg.reader().readShort();
                     Effect.GetCharEff(c);
                  } catch (Exception ignoredException2) {
                  }

                  return;
               }

               return;
            case 8:
               GameCanvas.debug();
               Char character;
               if ((character = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  character.cspeed = msg.reader().readByte();
                  return;
               }

               return;
            case 9:
               GameCanvas.debug();
               Char character2;
               if ((character2 = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  character2.cHP = msg.reader().readLong();
                  character2.cHPFull = msg.reader().readLong();
                  return;
               }

               return;
            case 10:
               GameCanvas.debug();
               Char character3;
               if ((character3 = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  character3.cHP = msg.reader().readLong();
                  character3.cHPFull = msg.reader().readLong();
                  character3.eff5BuffHp = msg.reader().readShort();
                  character3.eff5BuffMp = msg.reader().readShort();
                  character3.wp = msg.reader().readShort();
                  if (character3.wp == -1) {
                     character3.setDefaultWeapon();
                  }

                  return;
               }

               return;
            case 11:
               GameCanvas.debug();
               Char character4;
               if ((character4 = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  character4.cHP = msg.reader().readLong();
                  character4.cHPFull = msg.reader().readLong();
                  character4.eff5BuffHp = msg.reader().readShort();
                  character4.eff5BuffMp = msg.reader().readShort();
                  character4.body = msg.reader().readShort();
                  if (character4.body == -1) {
                     character4.setDefaultBody();
                  }

                  return;
               }

               return;
            case 12:
               GameCanvas.debug();
               Char character5;
               if ((character5 = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  character5.cHP = msg.reader().readLong();
                  character5.cHPFull = msg.reader().readLong();
                  character5.eff5BuffHp = msg.reader().readShort();
                  character5.eff5BuffMp = msg.reader().readShort();
                  character5.leg = msg.reader().readShort();
                  if (character5.leg == -1) {
                     character5.setDefaultLeg();
                  }

                  return;
               }

               return;
            case 13:
               GameCanvas.debug();
               Char character6;
               if ((charId = msg.reader().readInt()) == Char.myCharz().charID) {
                  character6 = Char.myCharz();
               } else {
                  character6 = GameScr.findCharInMap(charId);
               }

               if (character6 != null) {
                  character6.cHP = msg.reader().readLong();
                  character6.cHPFull = msg.reader().readLong();
                  character6.eff5BuffHp = msg.reader().readShort();
                  character6.eff5BuffMp = msg.reader().readShort();
                  return;
               }

               return;
            case 14:
               GameCanvas.debug();
               Char c2;
               if ((c2 = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  c2.cHP = msg.reader().readLong();
                  byte packetByte3;
                  if ((packetByte3 = msg.reader().readByte()) == 1) {
                     ServerEffect.addServerEffect(11, ((nro.Char) (c2)), 5);
                     ServerEffect.addServerEffect(104, ((nro.Char) (c2)), 4);
                  }

                  if (packetByte3 == 2) {
                     c2.doInjure();
                  }

                  try {
                     c2.cHPFull = msg.reader().readLong();
                  } catch (Exception ignoredException3) {
                  }

                  return;
               }

               return;
            case 15:
               GameCanvas.debug();
               Char c3;
               if ((c3 = GameScr.findCharInMap(msg.reader().readInt())) != null) {
                  c3.cHP = msg.reader().readLong();
                  c3.cHPFull = msg.reader().readLong();
                  c3.cx = msg.reader().readShort();
                  c3.cy = msg.reader().readShort();
                  c3.statusMe = 1;
                  c3.cp3 = 0;
                  ServerEffect.addServerEffect(109, ((nro.Char) (c3)), 2);
                  return;
               }

               return;
            case 19:
               GameCanvas.debug();
               Char.myCharz().boxSort();
               return;
            case 23:
               Skill skill;
               useSkill(skill = Skills.get((short) (charId = msg.reader().readShort())));
               if (charId != 0 && charId != 14 && charId != 28) {
                  GameScr.info1.addInfo(mResources.LEARN_SKILL + " " + skill.template.name, 0);
               }

               return;
            case 35:
               GameCanvas.debug();
               charId = msg.reader().readInt();
               Res.outz("CID = " + charId);
               if (TileMap.mapID == 130) {
                  GameScr.gI().starVS();
               }

               if (charId == Char.myCharz().charID) {
                  Char.myCharz().cTypePk = msg.reader().readByte();
                  if (GameScr.gI().isVS() && Char.myCharz().cTypePk != 0) {
                     GameScr.gI().starVS();
                  }

                  Res.outz("type pk= " + Char.myCharz().cTypePk);
                  Char.myCharz().npcFocus = null;
                  GameScr.gI();
                  if (!GameScr.isMeCanAttackMob(Char.myCharz().mobFocus)) {
                     Char.myCharz().mobFocus = null;
                  }

                  Char.myCharz().itemFocus = null;
               } else {
                  Char charFocus;
                  if ((charFocus = GameScr.findCharInMap(charId)) != null) {
                     Res.outz("type pk= " + charFocus.cTypePk);
                     charFocus.cTypePk = msg.reader().readByte();
                     if (charFocus.isAttacPlayerStatus()) {
                        Char.myCharz().charFocus = charFocus;
                     }
                  }
               }

               for (int charId2 = 0; charId2 < GameScr.vCharInMap.size(); charId2++) {
                  Char character7;
                  if ((character7 = GameScr.findCharInMap(charId2)) != null
                     && character7.cTypePk != 0
                     && character7.cTypePk == Char.myCharz().cTypePk) {
                     if (!Char.myCharz().mobFocus.isMobMe) {
                        Char.myCharz().mobFocus = null;
                     }

                     Char.myCharz().npcFocus = null;
                     Char.myCharz().itemFocus = null;
                     break;
                  }
               }

               Res.outz("update type pk= ");
               return;
            case 61:
               String packetText = msg.reader().readUTF();
               byte[] cSkillID = new byte[msg.reader().readInt()];
               msg.reader().read(cSkillID);
               if (cSkillID.length == 0) {
                  cSkillID = null;
               }

               if (packetText.equals("KSkill")) {
                  GameScr.gI().onKSkill(cSkillID);
                  return;
               } else {
                  if (packetText.equals("OSkill")) {
                     GameScr.gI().onOSkill(cSkillID);
                  } else if (packetText.equals("CSkill")) {
                     GameScr.gI().onCSkill(cSkillID);
                     return;
                  }

                  return;
               }
            case 62:
               Res.outz("ME UPDATE SKILL");
               read_UpdateSkill(msg);
               return;
            case 63:
               byte packetByte4;
               if ((packetByte4 = msg.reader().readByte()) > 0) {
                  GameCanvas.panel.vPlayerMenu_id.removeAllElements();
                  InfoDlg.showWait();
                  MyVector playerMenu = GameCanvas.panel.vPlayerMenu;

                  for (int index3 = 0; index3 < packetByte4; index3++) {
                     String caption = msg.reader().readUTF();
                     String caption2 = msg.reader().readUTF();
                     short menuSelect = msg.reader().readShort();
                     GameCanvas.panel.vPlayerMenu_id.addElement(String.valueOf(menuSelect));
                     Char.myCharz().charFocus.menuSelect = menuSelect;
                     Command entry;
                     (entry = new Command(caption, 11115, Char.myCharz().charFocus)).caption2 = caption2;
                     playerMenu.addElement(entry);
                  }

                  InfoDlg.hide();
                  GameCanvas.panel.setTabPlayerMenu();
               }

               return;
            default:
               return;
         }
      } catch (Exception exception2) {
         exception2.printStackTrace();
      } finally {
         if (msg != null) {
            msg.cleanup();
         }
      }
   }

   private static void useSkill(Skill skill) {
      if (Char.myCharz().myskill == null) {
         Char.myCharz().myskill = skill;
      } else if (skill.template.equals(Char.myCharz().myskill.template)) {
         Char.myCharz().myskill = skill;
      }

      Char.myCharz().vSkill.addElement(skill);
      if ((
            skill.template.type == 1
               || skill.template.type == 4
               || skill.template.type == 2
               || skill.template.type == 3
         )
         && (skill.template.maxPoint == 0 || skill.template.maxPoint > 0 && skill.point > 0)) {
         if (skill.template.id == Char.myCharz().skillTemplateId) {
            Service.gI().selectSkill(Char.myCharz().skillTemplateId);
         }

         Char.myCharz().vSkillFight.addElement(skill);
      }
   }

   private static boolean readCharInfo(Char c, Message msg) {
      try {
         c.clevel = msg.reader().readByte();
         c.isInvisiblez = msg.reader().readBoolean();
         c.cTypePk = msg.reader().readByte();
         Res.outz("ADD TYPE PK= " + c.cTypePk + " to player " + c.charID);
         c.nClass = GameScr.nClasss[msg.reader().readByte()];
         c.cgender = msg.reader().readByte();
         c.head = msg.reader().readShort();
         c.cName = msg.reader().readUTF();
         c.cHP = msg.reader().readLong();
         c.dHP = c.cHP;
         if (c.cHP == 0L) {
            c.statusMe = 14;
         }

         c.cHPFull = msg.reader().readLong();
         if (c.cy >= TileMap.pxh - 100) {
            c.isFlyUp = true;
         }

         c.body = msg.reader().readShort();
         c.leg = msg.reader().readShort();
         c.bag = msg.reader().readShort();
         Res.outz(
            " body= "
               + c.body
               + " leg= "
               + c.leg
               + " bag="
               + c.bag
               + "BAG =="
               + c.bag
               + "*********************************"
         );
         c.isShadown = true;
         msg.reader().readByte();
         if (c.wp == -1) {
            c.setDefaultWeapon();
         }

         if (c.body == -1) {
            c.setDefaultBody();
         }

         if (c.leg == -1) {
            c.setDefaultLeg();
         }

         Res.outz("1");
         c.cx = msg.reader().readShort();
         c.cy = msg.reader().readShort();
         c.xSd = c.cx;
         c.ySd = c.cy;
         c.eff5BuffHp = msg.reader().readShort();
         c.eff5BuffMp = msg.reader().readShort();
         byte effCount = msg.reader().readByte();

         for (int effIndex = 0; effIndex < effCount; effIndex++) {
            EffectChar eff = new EffectChar(
               msg.reader().readByte(),
               msg.reader().readInt(),
               msg.reader().readInt(),
               msg.reader().readShort()
            );
            c.vEff.addElement(eff);
         }

         Res.outz("Controller readCharInfo body: body|leg|bag " + c.body + c.leg + c.bag);
         return true;
      } catch (Exception exception) {
         exception.printStackTrace();
         return false;
      }
   }

   private void readGetImgByName(Message message) {
      try {
         String name = message.reader().readUTF();
         byte nFrame = message.reader().readByte();
         byte[] data;
         Image img = createImage(data = Firework.readMessageBytes(message));
         ImgByName.SetImage(name, img, nFrame);
         if (data != null) {
            ImgByName.saveRMS(name, nFrame, data);
            return;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private void loadItemNew(DataInputStream input, boolean condition) {
      try {
         input.mark(1000000);
         GameScr.vcItem = input.readByte();
         short shortValueCount = input.readByte();
         mSystem.println(">>loadItemNew:  type:" + shortValueCount);
         GameScr.lastItemDataPacketAt = mSystem.currentTimeMillis();
         if (shortValueCount == 0) {
            GameScr.hasLoadedItemOptions = true;
            GameScr.gI().iOptionTemplates = new ItemOptionTemplate[input.readShort()];

            for (int id = 0; id < GameScr.gI().iOptionTemplates.length; id++) {
               GameScr.gI().iOptionTemplates[id] = new ItemOptionTemplate();
               GameScr.gI().iOptionTemplates[id].id = id;
               GameScr.gI().iOptionTemplates[id].name = input.readUTF();
               input.readByte();
            }

            try {
               shortValueCount = input.readShort();

               for (int shortValueIndex = 0; shortValueIndex < shortValueCount; shortValueIndex++) {
                  short iOptionTemplateIndex = input.readShort();
                  GameScr.gI().iOptionTemplates[iOptionTemplateIndex].type = input.readUnsignedByte();
               }
            } catch (Exception ignoredException) {
            }

            if (condition) {
               input.reset();
               byte[] data = new byte[input.available()];
               input.readFully(data);
               Rms.saveRMS("NRitem0", data);
            }
         } else if (shortValueCount == 1) {
            GameScr.hasLoadedItemTemplates = true;
            ItemTemplates.itemTemplates.clear();
            shortValueCount = input.readShort();

            for (int templateID = 0; templateID < shortValueCount; templateID++) {
               ItemTemplates.add(
                  new ItemTemplate(
                     (short)templateID,
                     input.readByte(),
                     input.readByte(),
                     input.readUTF(),
                     input.readUTF(),
                     input.readByte(),
                     input.readInt(),
                     input.readShort(),
                     input.readShort(),
                     input.readBoolean()
                  )
               );
            }

            if (condition) {
               input.reset();
               byte[] data2 = new byte[input.available()];
               input.readFully(data2);
               Rms.saveRMS("NRitem1", data2);
               byte[] data3 = new byte[]{GameScr.vcItem};
               Rms.saveRMS("NRitemVersion", data3);
            }
         } else if (shortValueCount != 2) {
            if (shortValueCount == 100) {
               GameScr.hasLoadedHeadFrames = true;
               Char.Arr_Head_2Fr = readArrHead(input);
               if (condition) {
                  input.reset();
                  byte[] data4 = new byte[input.available()];
                  input.readFully(data4);
                  Rms.saveRMS("NRitem100", data4);
               }
            } else if (shortValueCount == 101) {
               GameScr.hasLoadedFlyingHeadFrames = true;

               try {
                  Char.Arr_Head_FlyMove = new short[shortValueCount = input.readShort()];

                  for (int arr_Head_FlyMoveIndex = 0; arr_Head_FlyMoveIndex < shortValueCount; arr_Head_FlyMoveIndex++) {
                     short packetShort = input.readShort();
                     Char.Arr_Head_FlyMove[arr_Head_FlyMoveIndex] = packetShort;
                  }

                  if (condition) {
                     input.reset();
                     byte[] data5 = new byte[input.available()];
                     input.readFully(data5);
                     Rms.saveRMS("NRitem101", data5);
                  }
               } catch (Exception ignoredException2) {
                  Char.Arr_Head_FlyMove = new short[0];
               }
            }
         }

         GameScr.gI();
         GameScr.readOk();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private static void readFrameBoss(Message msg, int mobTemplateId) {
      try {
         byte valueCount;
         int[][] intValuesByRow = new int[valueCount = msg.reader().readByte()][];

         for (int index = 0; index < valueCount; index++) {
            byte intValueCount = msg.reader().readByte();
            intValuesByRow[index] = new int[intValueCount];

            for (int index2 = 0; index2 < intValueCount; index2++) {
               intValuesByRow[index][index2] = msg.reader().readByte();
            }
         }

         frameHT_NEWBOSS.put(String.valueOf(mobTemplateId), intValuesByRow);
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   private static int[][] readArrHead(DataInputStream input) {
      int[][] intValuesByRow = new int[][]{{542, 543}};

      try {
         intValuesByRow = new int[input.readShort()][];

         for (int index = 0; index < intValuesByRow.length; index++) {
            byte intValueCount = input.readByte();
            intValuesByRow[index] = new int[intValueCount];

            for (int index2 = 0; index2 < intValueCount; index2++) {
               intValuesByRow[index][index2] = input.readShort();
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      return intValuesByRow;
   }

   private void phuban_Info(Message message) {
      try {
         byte type_PB;
         if ((type_PB = message.reader().readByte()) == 0) {
            byte type_PB2 = type_PB;
            Message message2 = message;

            try {
               int pointTeam1;
               if ((pointTeam1 = message2.reader().readByte()) == 0) {
                  pointTeam1 = message2.reader().readShort();
                  String nameTeam1 = message2.reader().readUTF();
                  String nameTeam2 = message2.reader().readUTF();
                  int maxPoint = message2.reader().readInt();
                  short timeSecond = message2.reader().readShort();
                  byte maxLife = message2.reader().readByte();
                  (GameScr.phuban_Info = new InfoPhuBan(type_PB2, (short)pointTeam1, nameTeam1, nameTeam2, maxPoint, timeSecond)).maxLife = maxLife;
                  GameScr.phuban_Info.updateLife(type_PB2, 0, 0);
                  return;
               }

               if (pointTeam1 == 1) {
                  pointTeam1 = message2.reader().readInt();
                  int pointTeam2 = message2.reader().readInt();
                  if (GameScr.phuban_Info != null) {
                     pointTeam2 = pointTeam2;
                     pointTeam1 = pointTeam1;
                     type_PB = type_PB2;
                     InfoPhuBan phuban_Info = GameScr.phuban_Info;
                     GameScr.phuban_Info.type_PB = type_PB;
                     phuban_Info.pointTeam1 = pointTeam1;
                     phuban_Info.pointTeam2 = pointTeam2;
                     return;
                  }
               } else {
                  if (pointTeam1 == 2) {
                     byte packetByte = message2.reader().readByte();
                     byte type = 0;
                     if (packetByte == 1) {
                        type = 1;
                     } else if (packetByte == 2) {
                        type = 2;
                     }

                     GameScr.phuban_Info = null;
                     GameScr.addEffectEnd(type, -1, 0, GameCanvas.hw, GameCanvas.hh, 0, 0, (short)-1, null);
                     return;
                  }

                  if (pointTeam1 == 5) {
                     short timeSecond2 = message2.reader().readShort();
                     if (GameScr.phuban_Info != null) {
                        timeSecond2 = timeSecond2;
                        type_PB = type_PB2;
                        InfoPhuBan phuban_Info2 = GameScr.phuban_Info;
                        GameScr.phuban_Info.type_PB = type_PB;
                        phuban_Info2.timeSecond = timeSecond2;
                        phuban_Info2.timeStart = GameCanvas.timeNow;
                        return;
                     }
                  } else if (pointTeam1 == 4) {
                     byte lifeTeam1 = message2.reader().readByte();
                     byte lifeTeam2 = message2.reader().readByte();
                     if (GameScr.phuban_Info != null) {
                        GameScr.phuban_Info.updateLife(type_PB2, lifeTeam1, lifeTeam2);
                        return;
                     }
                  }
               }
            } catch (Exception ignoredException) {
            }

            return;
         }
      } catch (Exception ignoredException2) {
      }
   }

   private void read_cmdExtra(Message msg) {
      try {
         int sub;
         if ((sub = msg.reader().readByte()) == 0) {
            short idHat = msg.reader().readShort();
            Char.myCharz().idHat = idHat;
            SoundMn.gI();
            SoundMn.getStrOption();
         } else if (sub == 2) {
            int packetInt = msg.reader().readInt();
            byte packetByte = msg.reader().readByte();
            sub = msg.reader().readShort();
            String text = sub + "," + packetByte;
            ImgByName.getImagePath("banner_" + sub, ImgByName.hashImagePath);
            GameCanvas.danhHieu.put(String.valueOf(packetInt), text);
         } else if (sub == 3) {
            short id_water1;
            SmallImage.createImage(id_water1 = msg.reader().readShort());
            BackgroudEffect.id_water1 = id_water1;
         } else if (sub == 4) {
            String entry = msg.reader().readUTF();
            GameCanvas.messageServer.addElement(entry);
         } else if (sub != 5) {
            readExtra((byte)sub, msg);
         } else {
            String text2 = "\n|ChienTruong|Log: ";
            byte sub_sub;
            if ((sub_sub = msg.reader().readByte()) == 0) {
               GameScr.nCT_team = msg.reader().readUTF();
               GameScr.nCT_nBoyBaller = (GameScr.nCT_TeamA = GameScr.nCT_TeamB = msg.reader().readByte()) << 1;
               GameScr.isPaint_CT = false;
               text2 = text2 + "\tsub    0|  nCT_team= " + GameScr.nCT_team + "|nCT_TeamA =" + GameScr.nCT_TeamA + "  isPaint_CT=false \n";
            } else if (sub_sub == 1) {
               sub = msg.reader().readInt();
               GameScr.nCT_floor = sub_sub = msg.reader().readByte();
               GameScr.nCT_timeBallte = sub * 1000 + mSystem.currentTimeMillis();
               GameScr.isPaint_CT = true;
               text2 = text2 + "\tsub    1 floor= " + sub_sub + "|timeBallte= " + sub + "isPaint_CT=true \n";
            } else if (sub_sub == 2) {
               GameScr.nCT_TeamA = msg.reader().readByte();
               GameScr.nCT_TeamB = msg.reader().readByte();
               GameScr.res_CT.removeAllElements();
               byte res_CTCount = msg.reader().readByte();

               for (int res_CTIndex = 0; res_CTIndex < res_CTCount; res_CTIndex++) {
                  String entry2 = "";
                  entry2 = entry2 + msg.reader().readByte() + "|";
                  entry2 = entry2 + msg.reader().readUTF() + "|";
                  entry2 = entry2 + msg.reader().readShort() + "|";
                  entry2 = entry2 + msg.reader().readInt();
                  GameScr.res_CT.addElement(entry2);
               }

               text2 = text2 + "\tsub   2|  A= " + GameScr.nCT_TeamA + "|B =" + GameScr.nCT_TeamB + "  isPaint_CT=true \n";
            } else if (sub_sub == 3) {
               Service.gI().sendCT_ready((byte)sub, sub_sub);
               GameScr.nCT_floor = 0;
               GameScr.nCT_timeBallte = 0L;
               GameScr.isPaint_CT = false;
               text2 = text2 + "\tsub    3|  isPaint_CT=false \n";
            } else if (sub_sub == 4) {
               GameScr.nUSER_CT = msg.reader().readByte();
               GameScr.nUSER_MAX_CT = msg.reader().readByte();
            }

            Res.err(text2 + "END LOG CT.");
         }
      } catch (Exception ignoredException) {
      }
   }

   private static void read_UpdateSkill(Message msg) {
      try {
         short skillId = msg.reader().readShort();
         byte packetByte = -1;

         try {
            packetByte = msg.reader().readByte();
         } catch (Exception ignoredException) {
         }

         if (packetByte == 0) {
            short curExp = msg.reader().readShort();

            for (int skillIndex = 0; skillIndex < Char.myCharz().vSkill.size(); skillIndex++) {
               Skill skill;
               if ((skill = (Skill)Char.myCharz().vSkill.elementAt(skillIndex)).skillId == skillId) {
                  skill.curExp = curExp;
                  return;
               }
            }

            return;
         }

         if (packetByte == 1) {
            byte packetByte2 = msg.reader().readByte();

            for (int skillIndex2 = 0; skillIndex2 < Char.myCharz().vSkill.size(); skillIndex2++) {
               Skill skill2;
               if ((skill2 = (Skill)Char.myCharz().vSkill.elementAt(skillIndex2)).skillId == skillId) {
                  for (int index = 0; index < 20; index++) {
                     ImgByName.getImagePath("Skills_" + skill2.template.id + "_" + packetByte2 + "_" + index, ImgByName.hashImagePath);
                  }

                  return;
               }
            }

            return;
         }

         if (packetByte == -1) {
            Skill myskill = Skills.get(skillId);

            for (int skillIndex3 = 0; skillIndex3 < Char.myCharz().vSkill.size(); skillIndex3++) {
               if (((Skill)Char.myCharz().vSkill.elementAt(skillIndex3)).template.id
                  == myskill.template.id) {
                  Char.myCharz().vSkill.setElementAt(myskill, skillIndex3);
                  break;
               }
            }

            for (int skillFightIndex = 0; skillFightIndex < Char.myCharz().vSkillFight.size(); skillFightIndex++) {
               if (((Skill)Char.myCharz().vSkillFight.elementAt(skillFightIndex)).template.id
                  == myskill.template.id) {
                  Char.myCharz().vSkillFight.setElementAt(myskill, skillFightIndex);
                  break;
               }
            }

            for (int onScreenSkillIndex = 0; onScreenSkillIndex < GameScr.onScreenSkill.length; onScreenSkillIndex++) {
               if (GameScr.onScreenSkill[onScreenSkillIndex] != null
                  && GameScr.onScreenSkill[onScreenSkillIndex].template.id == myskill.template.id) {
                  GameScr.onScreenSkill[onScreenSkillIndex] = myskill;
                  break;
               }
            }

            for (int keySkillIndex = 0; keySkillIndex < GameScr.keySkill.length; keySkillIndex++) {
               if (GameScr.keySkill[keySkillIndex] != null
                  && GameScr.keySkill[keySkillIndex].template.id == myskill.template.id) {
                  GameScr.keySkill[keySkillIndex] = myskill;
                  break;
               }
            }

            if (Char.myCharz().myskill.template.id == myskill.template.id) {
               Char.myCharz().myskill = myskill;
            }

            GameScr.info1
               .addInfo(
                  mResources.hasJustUpgrade1 + myskill.template.name + mResources.hasJustUpgrade2 + myskill.point, 0
               );
            return;
         }
      } catch (Exception ignoredException2) {
      }
   }

   private static void readExtra(byte sub, Message msg) {
      try {
         if (sub == 127) {
            GameCanvas.endDlg();
            String packetText;
            ServerListScreen.linkDefault = packetText = msg.reader().readUTF();
            mSystem.AddIpTest();
            ServerListScreen.getServerList(ServerListScreen.linkDefault);
            Res.outz(">>>>read.isEXTRA_LINK " + packetText);
            sub = msg.reader().readByte();
            Res.outz(">>>>read.isEXTRA_LINK  numSv: " + sub);
            if (sub > 0) {
               ServerListScreen.typeClass = new byte[sub];
               ServerListScreen.listChar = new Char[sub];

               for (int listCharIndex = 0; listCharIndex < sub; listCharIndex++) {
                  ServerListScreen.typeClass[listCharIndex] = msg.reader().readByte();
                  Res.outz(
                     ServerListScreen.nameServer[listCharIndex] + ">>>>read.isEXTRA_LINK  typeClass: " + ServerListScreen.typeClass[listCharIndex]
                  );
                  if (ServerListScreen.typeClass[listCharIndex] >= 0) {
                     ServerListScreen.isHaveChar = true;
                     ServerListScreen.listChar[listCharIndex] = new Char();
                     ServerListScreen.listChar[listCharIndex].cgender = ServerListScreen.typeClass[listCharIndex];
                     ServerListScreen.listChar[listCharIndex].head = msg.reader().readShort();
                     ServerListScreen.listChar[listCharIndex].body = msg.reader().readShort();
                     ServerListScreen.listChar[listCharIndex].leg = msg.reader().readShort();
                     ServerListScreen.listChar[listCharIndex].bag = msg.reader().readShort();
                     ServerListScreen.listChar[listCharIndex].cName = msg.reader().readUTF();
                  }
               }
            }

            ServerListScreen.saveRMS_ExtraLink();
            ServerListScreen.isWait = false;
            Char.isLoadingMap = false;
            LoginScr.isContinueToLogin = false;
            ServerListScreen.waitToLogin = false;
            boolean condition = false;
            boolean condition2 = false;

            try {
               if (!Rms.loadRMSString(Rms.RMS_acc).equals("")) {
                  condition = true;
               }

               if (!Rms.loadRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect).equals("")) {
                  condition2 = true;
               }
            } catch (Exception ignoredException) {
            }

            if (!ServerListScreen.isHaveChar && !condition && !condition2) {
               ServerListScreen.Login_New();
               return;
            }

            if (Rms.loadRMSInt(ServerListScreen.RMS_svselect) == -1) {
               ServerScr.isShowSv_HaveChar = false;
               GameCanvas.serverScr.switchToMe();
               return;
            }

            ServerListScreen.SetIpSelect(Rms.loadRMSInt(ServerListScreen.RMS_svselect), false);
            if (ServerListScreen.listChar != null && ServerListScreen.listChar[ServerListScreen.ipSelect] != null) {
               GameCanvas._SelectCharScr.SetInfoChar(ServerListScreen.listChar[ServerListScreen.ipSelect]);
               return;
            }

            ServerListScreen.Login_New();
            return;
         }
      } catch (Exception ignoredException2) {
         Res.outz(">>>>read.isEXTRA_LINK  errr:");
         GameCanvas.serverScr.switchToMe();
      }
   }

   public static ItemOption readItemOption(Message msg) {
      ItemOption itemOption = null;

      try {
         short optionTemplateId = msg.reader().readShort();
         int param = msg.reader().readInt();
         if (optionTemplateId != -1) {
            itemOption = new ItemOption(optionTemplateId, param);
         }
      } catch (Exception ignoredException) {
         Res.err(">>>>read.ItemOption  errr:");
      }

      return itemOption;
   }

   private void read_cmdExtraBig(Message msg) {
      try {
         byte packetByte = msg.reader().readByte();
         Res.outz(">>---read_cmdExtraBig-sub:" + packetByte + "  " + msg.reader().available());
         if (packetByte == 0) {
            this.loadItemNew(msg.reader(), true);
            return;
         }
      } catch (Exception ignoredException) {
      }
   }
}
