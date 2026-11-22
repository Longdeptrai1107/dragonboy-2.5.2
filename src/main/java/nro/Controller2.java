package nro;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import main.GameCanvas;
import main.GameMidlet;

public class Controller2 {
   private Random rd;
   Vector fw;
   private int[] color;

   public Controller2(int x0, int y0, int value, int value2) {
      this.rd = new Random();
      this.fw = new Vector();
      this.color = new int[]{16711680, 16776960, 65280, 16777215, 255, 65535, 15790320, 12632256};

      for (int fwIndex = 0; fwIndex < 72; fwIndex++) {
         this.fw
            .addElement(
               new Firework(
                  x0,
                  y0,
                  Math.abs(this.rd.nextInt() % 8) + 3,
                  fwIndex * 5,
                  this.color[Math.abs(this.rd.nextInt() % this.color.length)]
               )
            );
      }
   }

   public Controller2() {
   }

   public static void readMessage(Message msg) {
      try {
         switch (msg.command) {
            case -128:
               Message msg2 = msg;

               try {
                  byte packetByte = msg2.reader().readByte();
                  int charId;
                  Char x;
                  if ((charId = msg2.reader().readInt()) == Char.myCharz().charID) {
                     x = Char.myCharz();
                  } else {
                     x = GameScr.findCharInMap(charId);
                  }

                  if (packetByte == 0) {
                     short id = msg2.reader().readShort();
                     byte packetByte2 = msg2.reader().readByte();
                     byte packetByte3 = msg2.reader().readByte();
                     short packetShort = msg2.reader().readShort();
                     byte packetByte4 = msg2.reader().readByte();
                     if (x != null) {
                        x.addEffChar(new Effect(id, x, packetByte2, packetByte3, packetShort, packetByte4));
                     }
                     break;
                  }

                  if (packetByte == 1) {
                     short id2 = msg2.reader().readShort();
                     if (x != null) {
                        x.removeEffChar(0, id2);
                     }
                     break;
                  }

                  if (packetByte == 2) {
                     if (x != null) {
                        x.removeEffChar(-1, 0);
                     }
                     break;
                  }
               } catch (Exception exception) {
                  exception.printStackTrace();
               }

               return;
            case -127:
               readLuckyRound(msg);
               return;
            case -126:
               byte packetByte5 = msg.reader().readByte();
               Res.outz("type quay= " + packetByte5);
               if (packetByte5 == 1) {
                  msg.reader().readByte();
                  String packetText = msg.reader().readUTF();
                  String finish = msg.reader().readUTF();
                  GameScr.gI().showWinNumber(packetText, finish);
               }

               if (packetByte5 == 0) {
                  GameScr.gI().showYourNumber(msg.reader().readUTF());
                  return;
               }
               break;
            case -125:
               ChatTextField.gI().isShow = false;
               String title = msg.reader().readUTF();
               Res.outz("titile= " + title);
               byte type = msg.reader().readByte();
               ClientInput.gI().setInput(type, title);

               for (int tfIndex = 0; tfIndex < type; tfIndex++) {
                  ClientInput.gI().tf[tfIndex].name = msg.reader().readUTF();
                  byte packetByte6;
                  if ((packetByte6 = msg.reader().readByte()) == 0) {
                     ClientInput.gI().tf[tfIndex].setIputType(1);
                  }

                  if (packetByte6 == 1) {
                     ClientInput.gI().tf[tfIndex].setIputType(0);
                  }

                  if (packetByte6 == 2) {
                     ClientInput.gI().tf[tfIndex].setIputType(2);
                  }
               }

               return;
            case -124:
               byte packetByte7 = msg.reader().readByte();
               byte packetByte8;
               if ((packetByte8 = msg.reader().readByte()) == 0) {
                  if (packetByte7 == 2) {
                     int charId2;
                     if ((charId2 = msg.reader().readInt()) == Char.myCharz().charID) {
                        Char.myCharz().removeEffect();
                     } else if (GameScr.findCharInMap(charId2) != null) {
                        GameScr.findCharInMap(charId2).removeEffect();
                     }
                  }

                  int holdEffID = msg.reader().readUnsignedByte();
                  int charId3 = msg.reader().readInt();
                  Res.outz("playerID= " + charId3 + " skillID= " + holdEffID);
                  if (holdEffID == 32) {
                     if (packetByte7 == 1) {
                        int charId4 = msg.reader().readInt();
                        if (charId3 == Char.myCharz().charID) {
                           Char.myCharz().holdEffID = holdEffID;
                           GameScr.findCharInMap(charId4).setHoldChar(Char.myCharz());
                        } else if (GameScr.findCharInMap(charId3) != null && charId4 != Char.myCharz().charID) {
                           GameScr.findCharInMap(charId3).holdEffID = holdEffID;
                           GameScr.findCharInMap(charId4).setHoldChar(GameScr.findCharInMap(charId3));
                        } else if (GameScr.findCharInMap(charId3) != null && charId4 == Char.myCharz().charID) {
                           GameScr.findCharInMap(charId3).holdEffID = holdEffID;
                           Char.myCharz().setHoldChar(GameScr.findCharInMap(charId3));
                        }
                     } else if (charId3 == Char.myCharz().charID) {
                        Char.myCharz().removeHoleEff();
                     } else if (GameScr.findCharInMap(charId3) != null) {
                        GameScr.findCharInMap(charId3).removeHoleEff();
                     }
                  }

                  if (holdEffID == 33) {
                     if (packetByte7 == 1) {
                        if (charId3 == Char.myCharz().charID) {
                           Char.myCharz().protectEff = true;
                        } else if (GameScr.findCharInMap(charId3) != null) {
                           GameScr.findCharInMap(charId3).protectEff = true;
                        }
                     } else if (charId3 == Char.myCharz().charID) {
                        Char.myCharz().removeProtectEff();
                     } else if (GameScr.findCharInMap(charId3) != null) {
                        GameScr.findCharInMap(charId3).removeProtectEff();
                     }
                  }

                  if (holdEffID == 39) {
                     if (packetByte7 == 1) {
                        if (charId3 == Char.myCharz().charID) {
                           Char.myCharz().huytSao = true;
                        } else if (GameScr.findCharInMap(charId3) != null) {
                           GameScr.findCharInMap(charId3).huytSao = true;
                        }
                     } else if (charId3 == Char.myCharz().charID) {
                        Char.myCharz().removeHuytSao();
                     } else if (GameScr.findCharInMap(charId3) != null) {
                        GameScr.findCharInMap(charId3).removeHuytSao();
                     }
                  }

                  if (holdEffID == 40) {
                     if (packetByte7 == 1) {
                        if (charId3 == Char.myCharz().charID) {
                           Char.myCharz().blindEff = true;
                        } else if (GameScr.findCharInMap(charId3) != null) {
                           GameScr.findCharInMap(charId3).blindEff = true;
                        }
                     } else if (charId3 == Char.myCharz().charID) {
                        Char.myCharz().removeBlindEff();
                     } else if (GameScr.findCharInMap(charId3) != null) {
                        GameScr.findCharInMap(charId3).removeBlindEff();
                     }
                  }

                  if (holdEffID == 41) {
                     if (packetByte7 == 1) {
                        if (charId3 == Char.myCharz().charID) {
                           Char.myCharz().sleepEff = true;
                        } else if (GameScr.findCharInMap(charId3) != null) {
                           GameScr.findCharInMap(charId3).sleepEff = true;
                        }
                     } else if (charId3 == Char.myCharz().charID) {
                        Char.myCharz().removeSleepEff();
                     } else if (GameScr.findCharInMap(charId3) != null) {
                        GameScr.findCharInMap(charId3).removeSleepEff();
                     }
                  }

                  if (holdEffID == 42) {
                     if (packetByte7 == 1) {
                        if (charId3 == Char.myCharz().charID) {
                           Char.myCharz().stone = true;
                        }
                     } else if (charId3 == Char.myCharz().charID) {
                        Char.myCharz().stone = false;
                        Res.outz("tra ve huy chieu 2");
                     }
                  }
               }

               if (packetByte8 == 1) {
                  int holdEffID2 = msg.reader().readUnsignedByte();
                  byte mobId = msg.reader().readByte();
                  Res.outz("modbHoldID= " + mobId + " skillID= " + holdEffID2 + "eff ID= " + packetByte7);
                  if (holdEffID2 == 32) {
                     if (packetByte7 == 1) {
                        int charId5;
                        if ((charId5 = msg.reader().readInt()) == Char.myCharz().charID) {
                           GameScr.findMobInMap(((byte) (mobId))).holdEffID = holdEffID2;
                           Char.myCharz().setHoldMob(GameScr.findMobInMap(((byte) (mobId))));
                        } else if (GameScr.findCharInMap(charId5) != null) {
                           GameScr.findMobInMap(((byte) (mobId))).holdEffID = holdEffID2;
                           GameScr.findCharInMap(charId5).setHoldMob(GameScr.findMobInMap(((byte) (mobId))));
                        }
                     } else {
                        GameScr.findMobInMap(((byte) (mobId))).removeHoldEff();
                     }
                  }

                  if (holdEffID2 == 40) {
                     if (packetByte7 == 1) {
                        GameScr.findMobInMap(((byte) (mobId))).blindEff = true;
                     } else {
                        GameScr.findMobInMap(((byte) (mobId))).removeBlindEff();
                     }
                  }

                  if (holdEffID2 == 41) {
                     if (packetByte7 == 1) {
                        GameScr.findMobInMap(((byte) (mobId))).sleepEff = true;
                        return;
                     }

                     GameScr.findMobInMap(((byte) (mobId))).removeSleepEff();
                     return;
                  }
               }
               break;
            case -123:
               int charId6;
               if (GameScr.findCharInMap(charId6 = msg.reader().readInt()) != null) {
                  GameScr.findCharInMap(charId6).perCentMp = msg.reader().readByte();
                  return;
               }
               break;
            case -122:
               Npc npc = GameScr.findNPCInMap(msg.reader().readShort());
               byte duahauCount = msg.reader().readByte();
               npc.duahau = new int[duahauCount];

               for (int duahauIndex = 0; duahauIndex < duahauCount; duahauIndex++) {
                  npc.duahau[duahauIndex] = msg.reader().readShort();
               }

               npc.setStatus(msg.reader().readByte(), msg.reader().readInt());
               return;
            case -121:
               Service.logMap = mSystem.currentTimeMillis() - Service.curCheckMap;
               Service.gI().sendCheckMap();
               return;
            case -120:
               Service.logController = mSystem.currentTimeMillis() - Service.curCheckController;
               Service.gI().sendCheckController();
               return;
            case -119:
               Char.myCharz().topRankingPosition = msg.reader().readInt();
               return;
            case -117:
               GameScr.gI().tMabuEff = 0;
               GameScr.gI().percentMabu = msg.reader().readByte();
               if (GameScr.gI().percentMabu == 100) {
                  GameScr.gI().mabuEff = true;
               }

               if (GameScr.gI().percentMabu == 101) {
                  Npc.mabuEff = true;
                  return;
               }
               break;
            case -116:
               GameScr.canAutoPlay = msg.reader().readByte() == 1;
               return;
            case -115:
               Char.myCharz()
                  .setPowerInfo(
                     msg.reader().readUTF(),
                     msg.reader().readShort(),
                     msg.reader().readShort(),
                     msg.reader().readShort()
                  );
               return;
            case -113:
               byte[] cSkillID = new byte[10];

               for (int kSkillIDIndex = 0; kSkillIDIndex < 10; kSkillIDIndex++) {
                  cSkillID[kSkillIDIndex] = msg.reader().readByte();
               }

               GameScr.gI().onKSkill(cSkillID);
               GameScr.gI().onOSkill(cSkillID);
               GameScr.gI().onCSkill(cSkillID);
               return;
            case -111:
               short sourceCount = msg.reader().readShort();
               ImageSource.vSource = new MyVector("");

               for (int sourceIndex = 0; sourceIndex < sourceCount; sourceIndex++) {
                  String iD = msg.reader().readUTF();
                  byte version = msg.reader().readByte();
                  ImageSource.vSource.addElement(new ImageSource(iD, version));
               }

               ImageSource.checkRMS();
               ImageSource.saveRMS();
               return;
            case -110:
               byte packetByte9;
               if ((packetByte9 = msg.reader().readByte()) == 1) {
                  int id3 = msg.reader().readInt();
                  byte[] data;
                  if ((data = Rms.loadRMS(String.valueOf(id3))) == null) {
                     Service.gI().sendServerData((byte)1, -1, null);
                  } else {
                     Service.gI().sendServerData((byte)1, id3, data);
                  }
               }

               if (packetByte9 == 0) {
                  int packetInt = msg.reader().readInt();
                  short dataLength;
                  byte[] data2 = new byte[dataLength = msg.reader().readShort()];
                  msg.reader().read(data2, 0, dataLength);
                  Rms.saveRMS(String.valueOf(packetInt), data2);
                  return;
               }
               break;
            case -106:
               short id4 = msg.reader().readShort();
               short time = msg.reader().readShort();
               if (ItemTime.isExistItem(id4)) {
                  ItemTime.getItemById(id4).initTime(time, false);
                  return;
               }

               ItemTime itemTime = new ItemTime(id4, time);
               Char.vItemTime.addElement(itemTime);
               return;
            case -105:
               TransportScr.gI().time = 0;
               TransportScr.gI().maxTime = msg.reader().readShort();
               TransportScr.gI().last = TransportScr.gI().curr = System.currentTimeMillis();
               TransportScr.gI().type = msg.reader().readByte();
               TransportScr.gI().switchToMe();
               return;
            case -103:
               int charId7 = msg.reader().readByte();
               Res.outz("server gui ve actionFlag = " + charId7);
               if (charId7 == 0) {
                  GameCanvas.panel.vFlag.removeAllElements();
                  charId7 = msg.reader().readByte();

                  for (int flagIndex = 0; flagIndex < charId7; flagIndex++) {
                     Item entry = new Item();
                     short id5;
                     if ((id5 = msg.reader().readShort()) != -1) {
                        entry.template = ItemTemplates.get(id5);
                        byte itemOptionCount;
                        if ((itemOptionCount = msg.reader().readByte()) != -1) {
                           entry.itemOption = new ItemOption[itemOptionCount];

                           for (int itemOptionIndex = 0; itemOptionIndex < entry.itemOption.length; itemOptionIndex++) {
                              Controller.gI();
                              ItemOption itemOption;
                              if ((itemOption = Controller.readItemOption(msg)) != null) {
                                 entry.itemOption[itemOptionIndex] = itemOption;
                              }
                           }
                        }
                     }

                     GameCanvas.panel.vFlag.addElement(entry);
                  }

                  GameCanvas.panel.setTypeFlag();
                  GameCanvas.panel.show();
                  return;
               }

               if (charId7 == 1) {
                  charId7 = msg.reader().readInt();
                  byte cFlag = msg.reader().readByte();
                  Res.outz("---------------actionFlag1:  " + charId7 + " : " + cFlag);
                  if (charId7 == Char.myCharz().charID) {
                     Char.myCharz().cFlag = cFlag;
                  } else if (GameScr.findCharInMap(charId7) != null) {
                     GameScr.findCharInMap(charId7).cFlag = cFlag;
                  }

                  GameScr.gI();
                  GameScr.getFlagImage(charId7, cFlag);
                  return;
               }

               if (charId7 == 2) {
                  byte cflag = msg.reader().readByte();
                  short flagImage = msg.reader().readShort();
                  PKFlag entry2;
                  (entry2 = new PKFlag()).cflag = cflag;
                  entry2.IDimageFlag = flagImage;
                  GameScr.vFlag.addElement(entry2);

                  for (int flagIndex2 = 0; flagIndex2 < GameScr.vFlag.size(); flagIndex2++) {
                     PKFlag flag = (PKFlag)GameScr.vFlag.elementAt(flagIndex2);
                     Res.outz("i: " + flagIndex2 + "  cflag: " + flag.cflag + "   IDimageFlag: " + flag.IDimageFlag);
                  }

                  for (int charInMapIndex = 0; charInMapIndex < GameScr.vCharInMap.size(); charInMapIndex++) {
                     Char charInMap;
                     if ((charInMap = (Char)GameScr.vCharInMap.elementAt(charInMapIndex)) != null && charInMap.cFlag == cflag) {
                        charInMap.flagImage = flagImage;
                     }
                  }

                  if (Char.myCharz().cFlag == cflag) {
                     Char.myCharz().flagImage = flagImage;
                     return;
                  }
               }
               break;
            case -102:
               byte packetByte10 = msg.reader().readByte();
               LoginScr.isLoggingIn = false;
               if (packetByte10 != 0 && packetByte10 == 1) {
                  GameCanvas.loginScr.isLogin2 = false;
                  Service.gI()
                     .login(
                        Rms.loadRMSString(Rms.RMS_acc),
                        Rms.loadRMSString(Rms.RMS_pass),
                        "2.5.2",
                        (byte)0
                     );
                  LoginScr.isLoggingIn = true;
                  return;
               }
               break;
            case -101:
               if (GameCanvas.loginScr == null) {
                  GameCanvas.loginScr = new LoginScr();
               }

               GameCanvas.loginScr.isLogin2 = true;
               LoginScr.isLoggingIn = true;
               GameCanvas.connect();
               String data3 = msg.reader().readUTF();
               Rms.saveRMSString(Rms.RMS_userAo + ServerListScreen.ipSelect, data3);
               Service.gI().login(data3, "", "2.5.2", (byte)1);
               return;
            case -100:
               InfoDlg.hide();
               boolean condition = false;
               if (GameCanvas.w > 2 * Panel.WIDTH_PANEL) {
                  condition = true;
               }

               byte currPageShopIndex;
               if ((currPageShopIndex = msg.reader().readByte()) < 0) {
                  break;
               }

               Res.outz("t Indxe= " + currPageShopIndex);
               GameCanvas.panel.maxPageShop[currPageShopIndex] = msg.reader().readByte();
               GameCanvas.panel.currPageShop[currPageShopIndex] = msg.reader().readByte();
               Res.outz(
                  "max page= " + GameCanvas.panel.maxPageShop[currPageShopIndex] + " curr page= " + GameCanvas.panel.currPageShop[currPageShopIndex]
               );
               int itemShopCount = msg.reader().readUnsignedByte();
               Char.myCharz().arrItemShop[currPageShopIndex] = new Item[itemShopCount];
               Panel.strWantToBuy = mResources.say_wat_do_u_want_to_buy;

               for (int itemShopIndex = 0; itemShopIndex < itemShopCount; itemShopIndex++) {
                  short id6;
                  if ((id6 = msg.reader().readShort()) != -1) {
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex] = new Item();
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].template = ItemTemplates.get(id6);
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].itemId = msg.reader().readShort();
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].buyCoin = msg.reader().readInt();
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].buyGold = msg.reader().readInt();
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].buyType = msg.reader().readByte();
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].quantity = msg.reader().readInt();
                     Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].isMe = msg.reader().readByte();
                     byte itemOptionCount2;
                     if ((itemOptionCount2 = msg.reader().readByte()) != -1) {
                        Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].itemOption = new ItemOption[itemOptionCount2];

                        for (int itemOptionIndex2 = 0; itemOptionIndex2 < Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].itemOption.length; itemOptionIndex2++) {
                           Controller.gI();
                           ItemOption itemOption2;
                           if ((itemOption2 = Controller.readItemOption(msg)) != null) {
                              Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].itemOption[itemOptionIndex2] = itemOption2;
                              Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].compare = GameCanvas.panel
                                 .getCompare(Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex]);
                           }
                        }
                     }

                     if (msg.reader().readByte() == 1) {
                        short headTemp = msg.reader().readShort();
                        short bodyTemp = msg.reader().readShort();
                        short legTemp = msg.reader().readShort();
                        short bagTemp = msg.reader().readShort();
                        Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].setPartTemp(headTemp, bodyTemp, legTemp, bagTemp);
                     }

                     if (GameMidlet.intVERSION >= 237) {
                        Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].nameNguoiKyGui = msg.reader().readUTF();
                        Res.err("nguoi ki gui  " + Char.myCharz().arrItemShop[currPageShopIndex][itemShopIndex].nameNguoiKyGui);
                     }
                  }
               }

               if (condition) {
                  GameCanvas.panel2.setTabKiGui();
               }

               GameCanvas.panel.setTabShop();
               GameCanvas.panel.cmy = GameCanvas.panel.cmtoY = 0;
            default:
               return;
            case -89:
               GameCanvas.open3Hour = msg.reader().readByte() == 1;
               return;
            case 31:
               int charId8 = msg.reader().readInt();
               if (msg.reader().readByte() != 1) {
                  if (charId8 == Char.myCharz().charID) {
                     Char.myCharz().petFollow.remove();
                     Char.myCharz().petFollow = null;
                     return;
                  }

                  Char character;
                  (character = GameScr.findCharInMap(charId8)).petFollow.remove();
                  character.petFollow = null;
                  return;
               }

               short smallID = msg.reader().readShort();
               byte fimg = -1;
               int[] frameNew = null;
               short wimg = 0;
               short himg = 0;

               try {
                  if ((fimg = msg.reader().readByte()) > 0) {
                     byte valueCount;
                     frameNew = new int[valueCount = msg.reader().readByte()];

                     for (int frameNewIndex = 0; frameNewIndex < valueCount; frameNewIndex++) {
                        frameNew[frameNewIndex] = msg.reader().readByte();
                     }

                     wimg = msg.reader().readShort();
                     himg = msg.reader().readShort();
                  }
               } catch (Exception ignoredException) {
               }

               if (charId8 == Char.myCharz().charID) {
                  Char.myCharz().petFollow = new PetFollow();
                  Char.myCharz().petFollow.smallID = smallID;
                  if (fimg > 0) {
                     Char.myCharz().petFollow.SetImg(fimg, frameNew, wimg, himg);
                     return;
                  }
               } else {
                  Char character2;
                  (character2 = GameScr.findCharInMap(charId8)).petFollow = new PetFollow();
                  character2.petFollow.smallID = smallID;
                  if (fimg > 0) {
                     character2.petFollow.SetImg(fimg, frameNew, wimg, himg);
                     return;
                  }
               }
               break;
            case 42:
               GameCanvas.endDlg();
               LoginScr.isContinueToLogin = false;
               Char.isLoadingMap = false;
               byte haveName = msg.reader().readByte();
               if (GameCanvas.registerScr == null) {
                  GameCanvas.registerScr = new RegisterScreen(haveName);
               }

               GameCanvas.registerScr.switchToMe();
               return;
            case 48:
               ServerListScreen.SetIpSelect(msg.reader().readByte(), false);
               GameCanvas.doResetToLoginScr();
               Session_ME.gI().close();
               GameCanvas.endDlg();
               ServerListScreen.waitToLogin = true;
               return;
            case 51:
               Mabu character3 = (Mabu)GameScr.findCharInMap(msg.reader().readInt());
               byte id7 = msg.reader().readByte();
               short x2 = msg.reader().readShort();
               short y = msg.reader().readShort();
               byte characterCount;
               Char[] charHit = new Char[characterCount = msg.reader().readByte()];
               long[] damageHit = new long[characterCount];

               for (int charHitIndex = 0; charHitIndex < characterCount; charHitIndex++) {
                  int charId9 = msg.reader().readInt();
                  Res.outz("char ID=" + charId9);
                  charHit[charHitIndex] = null;
                  if (charId9 != Char.myCharz().charID) {
                     charHit[charHitIndex] = GameScr.findCharInMap(charId9);
                  } else {
                     charHit[charHitIndex] = Char.myCharz();
                  }

                  damageHit[charHitIndex] = msg.reader().readLong();
               }

               character3.setSkill(id7, x2, y, charHit, damageHit);
               return;
            case 52:
               byte packetByte11;
               if ((packetByte11 = msg.reader().readByte()) == 1) {
                  int charId10;
                  if ((charId10 = msg.reader().readInt()) == Char.myCharz().charID) {
                     Char.myCharz().setMabuHold(true);
                     Char.myCharz().cx = msg.reader().readShort();
                     Char.myCharz().cy = msg.reader().readShort();
                  } else {
                     Char character4;
                     if ((character4 = GameScr.findCharInMap(charId10)) != null) {
                        character4.setMabuHold(true);
                        character4.cx = msg.reader().readShort();
                        character4.cy = msg.reader().readShort();
                     }
                  }
               }

               if (packetByte11 == 0) {
                  int charId11;
                  if ((charId11 = msg.reader().readInt()) == Char.myCharz().charID) {
                     Char.myCharz().setMabuHold(false);
                  } else {
                     Char character5;
                     if ((character5 = GameScr.findCharInMap(charId11)) != null) {
                        character5.setMabuHold(false);
                     }
                  }
               }

               if (packetByte11 == 2) {
                  int charId12 = msg.reader().readInt();
                  int id8 = msg.reader().readInt();
                  ((Mabu)GameScr.findCharInMap(charId12)).eat(id8);
               }

               if (packetByte11 == 3) {
                  GameScr.mabuPercent = msg.reader().readByte();
                  return;
               }
               break;
            case 93:
               String chatVip = Res.changeString(msg.reader().readUTF());
               GameScr.gI().chatVip(chatVip);
               return;
            case 100:
               byte packetByte12 = msg.reader().readByte();
               short itemBagIndex = msg.reader().readByte();
               Item itemBag = null;
               if (packetByte12 == 0) {
                  itemBag = Char.myCharz().arrItemBody[itemBagIndex];
               }

               if (packetByte12 == 1) {
                  itemBag = Char.myCharz().arrItemBag[itemBagIndex];
               }

               if ((itemBagIndex = msg.reader().readShort()) != -1) {
                  itemBag.template = ItemTemplates.get(itemBagIndex);
                  itemBag.quantity = msg.reader().readInt();
                  msg.reader().readUTF();
                  msg.reader().readUTF();
                  byte itemOptionCount3;
                  if ((itemOptionCount3 = msg.reader().readByte()) != 0) {
                     itemBag.itemOption = new ItemOption[itemOptionCount3];

                     for (int itemOptionIndex3 = 0; itemOptionIndex3 < itemBag.itemOption.length; itemOptionIndex3++) {
                        Controller.gI();
                        ItemOption itemOption3;
                        if ((itemOption3 = Controller.readItemOption(msg)) != null) {
                           itemBag.itemOption[itemOptionIndex3] = itemOption3;
                        }
                     }
                  }

                  if (itemBag.quantity <= 0) {
                     return;
                  }
               }
               break;
            case 101:
               Res.outz("big boss--------------------------------------------------");
               BigBoss bigBoss;
               if ((bigBoss = Mob.getBigBoss()) == null) {
                  return;
               }

               byte type2;
               if ((type2 = msg.reader().readByte()) == 0 || type2 == 1 || type2 == 2 || type2 == 4 || type2 == 3) {
                  if (type2 == 3) {
                     bigBoss.xTo = bigBoss.xFirst = msg.reader().readShort();
                     bigBoss.yTo = bigBoss.yFirst = msg.reader().readShort();
                     bigBoss.setFly();
                  } else {
                     byte cAttackCount = msg.reader().readByte();
                     Res.outz("CHUONG nChar= " + cAttackCount);
                     Char[] cAttack = new Char[cAttackCount];
                     long[] dame = new long[cAttackCount];

                     for (int cAttackIndex = 0; cAttackIndex < cAttackCount; cAttackIndex++) {
                        int charId13 = msg.reader().readInt();
                        Res.outz("char ID=" + charId13);
                        cAttack[cAttackIndex] = null;
                        if (charId13 != Char.myCharz().charID) {
                           cAttack[cAttackIndex] = GameScr.findCharInMap(charId13);
                        } else {
                           cAttack[cAttackIndex] = Char.myCharz();
                        }

                        dame[cAttackIndex] = msg.reader().readLong();
                     }

                     bigBoss.setAttack(cAttack, dame, type2);
                  }
               }

               if (type2 == 5) {
                  bigBoss.haftBody = true;
                  bigBoss.status = 2;
               }

               if (type2 == 6) {
                  bigBoss.getDataB2();
                  bigBoss.x = msg.reader().readShort();
                  bigBoss.y = msg.reader().readShort();
               }

               if (type2 == 7) {
                  bigBoss.setAttack(null, null, type2);
               }

               if (type2 == 8) {
                  bigBoss.xTo = bigBoss.xFirst = msg.reader().readShort();
                  bigBoss.yTo = bigBoss.yFirst = msg.reader().readShort();
                  bigBoss.status = 2;
               }

               if (type2 == 9) {
                  bigBoss.x = bigBoss.y = bigBoss.xTo = bigBoss.yTo = bigBoss.xFirst = bigBoss.yFirst = -1000;
                  return;
               }
               break;
            case 102:
               byte actionBoss;
               if ((actionBoss = msg.reader().readByte()) == 0 || actionBoss == 1 || actionBoss == 2 || actionBoss == 6) {
                  BigBoss2 bigBoss2;
                  if ((bigBoss2 = Mob.getBigBoss2()) == null) {
                     return;
                  }

                  if (actionBoss == 6) {
                     bigBoss2.x = bigBoss2.y = bigBoss2.xTo = bigBoss2.yTo = bigBoss2.xFirst = bigBoss2.yFirst = -1000;
                     return;
                  }

                  byte characterCount2;
                  Char[] cAttack2 = new Char[characterCount2 = msg.reader().readByte()];
                  long[] dame2 = new long[characterCount2];

                  for (int cAttackIndex2 = 0; cAttackIndex2 < characterCount2; cAttackIndex2++) {
                     int charId14 = msg.reader().readInt();
                     cAttack2[cAttackIndex2] = null;
                     if (charId14 != Char.myCharz().charID) {
                        cAttack2[cAttackIndex2] = GameScr.findCharInMap(charId14);
                     } else {
                        cAttack2[cAttackIndex2] = Char.myCharz();
                     }

                     dame2[cAttackIndex2] = msg.reader().readLong();
                  }

                  bigBoss2.setAttack(cAttack2, dame2, actionBoss);
               }

               if (actionBoss == 3 || actionBoss == 4 || actionBoss == 5 || actionBoss == 7) {
                  BachTuoc bachTuoc;
                  if ((bachTuoc = Mob.getBachTuoc()) == null) {
                     return;
                  }

                  if (actionBoss == 7) {
                     bachTuoc.x = bachTuoc.y = bachTuoc.xTo = bachTuoc.yTo = bachTuoc.xFirst = bachTuoc.yFirst = -1000;
                     return;
                  }

                  if (actionBoss == 3 || actionBoss == 4) {
                     byte characterCount3;
                     Char[] cAttack3 = new Char[characterCount3 = msg.reader().readByte()];
                     long[] dame3 = new long[characterCount3];

                     for (int cAttackIndex3 = 0; cAttackIndex3 < characterCount3; cAttackIndex3++) {
                        int charId15 = msg.reader().readInt();
                        cAttack3[cAttackIndex3] = null;
                        if (charId15 != Char.myCharz().charID) {
                           cAttack3[cAttackIndex3] = GameScr.findCharInMap(charId15);
                        } else {
                           cAttack3[cAttackIndex3] = Char.myCharz();
                        }

                        dame3[cAttackIndex3] = msg.reader().readLong();
                     }

                     bachTuoc.setAttack(cAttack3, dame3, actionBoss);
                  }

                  if (actionBoss == 5) {
                     short xMoveTo = msg.reader().readShort();
                     bachTuoc.move(xMoveTo);
                  }
               }

               if (actionBoss > 9 && actionBoss < 30) {
                  readActionBoss(msg, actionBoss);
                  return;
               }
               break;
            case 113:
               byte packetByte13 = 0;
               byte packetByte14 = 0;
               short id9 = 0;
               short x3 = 0;
               short packetShort2 = 0;
               short packetShort3 = -1;

               try {
                  packetByte13 = msg.reader().readByte();
                  packetByte14 = msg.reader().readByte();
                  id9 = msg.reader().readShort();
                  x3 = msg.reader().readShort();
                  packetShort2 = msg.reader().readShort();
                  packetShort3 = msg.reader().readShort();
               } catch (Exception ignoredException2) {
               }

               EffecMn.addEff(new Effect(id9, x3, packetShort2, packetByte14, packetByte13, packetShort3));
               return;
            case 114:
               try {
                  msg.reader().readUTF();
                  mSystem.curINAPP = msg.reader().readByte();
                  mSystem.maxINAPP = msg.reader().readByte();
                  return;
               } catch (Exception ignoredException3) {
                  return;
               }
            case 121:
               mSystem.publicID = msg.reader().readUTF();
               mSystem.strAdmob = msg.reader().readUTF();
               Res.outz("SHOW AD public ID= " + mSystem.publicID);
               mSystem.createAdmob();
               return;
            case 122:
               short timeLogin = msg.reader().readShort();
               Res.outz("second login = " + timeLogin);
               LoginScr.timeLogin = timeLogin;
               LoginScr.currTimeLogin = LoginScr.lastTimeLogin = System.currentTimeMillis();
               GameCanvas.endDlg();
               return;
            case 123:
               int charId16 = msg.reader().readInt();
               short xPos = msg.reader().readShort();
               short yPos = msg.reader().readShort();
               byte typePos = msg.reader().readByte();
               Res.outz(
                  "SET POSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSss x= " + xPos + " y= " + yPos + "chaPos= " + charId16 + "type Pos= " + typePos
               );
               Char c = null;
               if (charId16 == Char.myCharz().charID) {
                  c = Char.myCharz();
               } else if (GameScr.findCharInMap(charId16) != null) {
                  c = GameScr.findCharInMap(charId16);
               }

               if (c != null) {
                  ServerEffect.addServerEffect(typePos == 0 ? 60 : 173, ((nro.Char) (c)), 1);
                  c.setPos(xPos, yPos, typePos);
                  return;
               }
               break;
            case 124:
               short id10 = msg.reader().readShort();
               String info = msg.reader().readUTF();
               Res.outz("noi chuyen = " + info + "npc ID= " + id10);
               Npc npc2;
               if ((npc2 = GameScr.findNPCInMap(id10)) != null) {
                  npc2.addInfo(info);
                  return;
               }
               break;
            case 125:
               byte fusion = msg.reader().readByte();
               int charId17;
               if ((charId17 = msg.reader().readInt()) == Char.myCharz().charID) {
                  Char.myCharz().setFusion(fusion);
                  return;
               }

               if (GameScr.findCharInMap(charId17) != null) {
                  GameScr.findCharInMap(charId17).setFusion(fusion);
                  return;
               }
               break;
            case 127:
               Message msg3 = msg;

               try {
                  byte packetByte15;
                  if ((packetByte15 = msg3.reader().readByte()) == 0) {
                     RadarScr.gI();
                     MyVector listValue = new MyVector("");
                     short numMax = msg3.reader().readShort();
                     int num = 0;

                     for (int listValueIndex = 0; listValueIndex < numMax; listValueIndex++) {
                        Info_RadaScr entry3 = new Info_RadaScr();
                        short id11 = msg3.reader().readShort();
                        int no = listValueIndex + 1;
                        short idIcon = msg3.reader().readShort();
                        byte rank = msg3.reader().readByte();
                        byte amount = msg3.reader().readByte();
                        byte max_amount = msg3.reader().readByte();
                        short templateId = -1;
                        Char charInfo = null;
                        byte typeMonster;
                        if ((typeMonster = msg3.reader().readByte()) == 0) {
                           templateId = msg3.reader().readShort();
                        } else {
                           short packetShort4 = msg3.reader().readShort();
                           short packetShort5 = msg3.reader().readShort();
                           short packetShort6 = msg3.reader().readShort();
                           short packetShort7 = msg3.reader().readShort();
                           short bag = packetShort7;
                           short leg = packetShort6;
                           short body = packetShort5;
                           short head = packetShort4;
                           Char character6;
                           (character6 = new Char()).head = head;
                           character6.body = body;
                           character6.leg = leg;
                           character6.bag = bag;
                           charInfo = character6;
                        }

                        String name = msg3.reader().readUTF();
                        String info2 = msg3.reader().readUTF();
                        byte level = msg3.reader().readByte();
                        byte isUse = msg3.reader().readByte();
                        byte itemOptionCount4 = msg3.reader().readByte();
                        ItemOption[] itemOption4 = null;
                        if (itemOptionCount4 != 0) {
                           itemOption4 = new ItemOption[itemOptionCount4];

                           for (int itemOptionIndex4 = 0; itemOptionIndex4 < itemOption4.length; itemOptionIndex4++) {
                              Controller.gI();
                              ItemOption itemOption5 = Controller.readItemOption(msg3);
                              byte activeCard = msg3.reader().readByte();
                              if (itemOption5 != null) {
                                 itemOption4[itemOptionIndex4] = itemOption5;
                                 itemOption4[itemOptionIndex4].activeCard = activeCard;
                              }
                           }
                        }

                        entry3.SetInfo(id11, no, idIcon, rank, typeMonster, templateId, name, info2, charInfo, itemOption4);
                        entry3.SetLevel(level);
                        entry3.SetUse(isUse);
                        entry3.SetAmount(amount, max_amount);
                        listValue.addElement(entry3);
                        if (level > 0) {
                           num++;
                        }
                     }

                     RadarScr.gI().SetRadarScr(listValue, num, numMax);
                     return;
                  }

                  if (packetByte15 == 1) {
                     short id12 = msg3.reader().readShort();
                     byte isUse2 = msg3.reader().readByte();
                     if (Info_RadaScr.GetInfo(RadarScr.list, id12) != null) {
                        Info_RadaScr.GetInfo(RadarScr.list, id12).SetUse(isUse2);
                     }

                     RadarScr.SetListUse();
                     return;
                  }

                  if (packetByte15 == 2) {
                     short id13 = msg3.reader().readShort();
                     byte level2 = msg3.reader().readByte();
                     int num2 = 0;

                     for (int listIndex = 0; listIndex < RadarScr.list.size(); listIndex++) {
                        Info_RadaScr list;
                        if ((list = (Info_RadaScr)RadarScr.list.elementAt(listIndex)) != null) {
                           if (list.id == id13) {
                              list.SetLevel(level2);
                           }

                           if (list.level > 0) {
                              num2++;
                           }
                        }
                     }

                     RadarScr.SetNum(num2, RadarScr.list.size());
                     if (Info_RadaScr.GetInfo(RadarScr.listUse, id13) != null) {
                        Info_RadaScr.GetInfo(RadarScr.listUse, id13).SetLevel(level2);
                        return;
                     }
                  } else if (packetByte15 == 3) {
                     short id14 = msg3.reader().readShort();
                     byte amount2 = msg3.reader().readByte();
                     byte max_amount2 = msg3.reader().readByte();
                     if (Info_RadaScr.GetInfo(RadarScr.list, id14) != null) {
                        Info_RadaScr.GetInfo(RadarScr.list, id14).SetAmount(amount2, max_amount2);
                     }

                     if (Info_RadaScr.GetInfo(RadarScr.listUse, id14) != null) {
                        Info_RadaScr.GetInfo(RadarScr.listUse, id14).SetAmount(amount2, max_amount2);
                        return;
                     }
                  } else if (packetByte15 == 4) {
                     int charId18 = msg3.reader().readInt();
                     short idAuraEff = msg3.reader().readShort();
                     Char character7;
                     if (charId18 == Char.myCharz().charID) {
                        character7 = Char.myCharz();
                     } else {
                        character7 = GameScr.findCharInMap(charId18);
                     }

                     if (character7 != null) {
                        character7.idAuraEff = idAuraEff;
                        character7.idEff_Set_Item = msg3.reader().readByte();
                        return;
                     }
                  }
               } catch (Exception exception2) {
                  exception2.printStackTrace();
               }

               return;
         }
      } catch (Exception ignoredException4) {
      }
   }

   private static void readLuckyRound(Message message) {
      try {
         int price;
         if ((price = message.reader().readByte()) == 0) {
            short[] idImage = new short[price = message.reader().readByte()];

            for (int idImageIndex = 0; idImageIndex < price; idImageIndex++) {
               idImage[idImageIndex] = message.reader().readShort();
            }

            byte typePrice = message.reader().readByte();
            price = message.reader().readInt();
            short idTicket = message.reader().readShort();
            CrackBallScr.gI().SetCrackBallScr(idImage, typePrice, price, idTicket);
            return;
         }

         if (price == 1) {
            byte shortValueCount;
            short[] idImage2 = new short[shortValueCount = message.reader().readByte()];

            for (int idImageIndex2 = 0; idImageIndex2 < shortValueCount; idImageIndex2++) {
               idImage2[idImageIndex2] = message.reader().readShort();
            }

            CrackBallScr.gI().DoneCrackBallScr(idImage2);
            return;
         }
      } catch (IOException iOException) {
         iOException.printStackTrace();
      }
   }

   private static void readActionBoss(Message msg, int actionBoss) {
      try {
         NewBoss newBoss;
         if ((newBoss = Mob.getNewBoss(msg.reader().readByte())) == null) {
            return;
         }

         if (actionBoss == 10) {
            short xMoveTo = msg.reader().readShort();
            short yMoveTo = msg.reader().readShort();
            newBoss.move(xMoveTo, yMoveTo);
         }

         if (actionBoss >= 11 && actionBoss <= 20) {
            byte characterCount;
            Char[] cAttack = new Char[characterCount = msg.reader().readByte()];
            long[] dame = new long[characterCount];

            for (int cAttackIndex = 0; cAttackIndex < characterCount; cAttackIndex++) {
               int charId = msg.reader().readInt();
               cAttack[cAttackIndex] = null;
               if (charId != Char.myCharz().charID) {
                  cAttack[cAttackIndex] = GameScr.findCharInMap(charId);
               } else {
                  cAttack[cAttackIndex] = Char.myCharz();
               }

               dame[cAttackIndex] = msg.reader().readLong();
            }

            byte dir = msg.reader().readByte();
            newBoss.setAttack(cAttack, dame, (byte)(actionBoss - 10), dir);
         }

         if (actionBoss == 21) {
            newBoss.xTo = msg.reader().readShort();
            newBoss.yTo = msg.reader().readShort();
            newBoss.setFly();
         }

         if (actionBoss == 23) {
            newBoss.setDie();
            return;
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }
}
