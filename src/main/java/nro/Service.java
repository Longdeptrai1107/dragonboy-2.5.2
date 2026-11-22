package nro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import main.GameCanvas;

public final class Service {
   private ISession session = Session_ME.gI();
   private static Service instance;
   private static volatile boolean mainClientInfoSent = false;
   private static volatile boolean secondaryClientInfoSent = false;
   private static long mainClientInfoGeneration = Long.MIN_VALUE;
   private static long secondaryClientInfoGeneration = Long.MIN_VALUE;
   private static long acknowledgedMainGeneration = Long.MIN_VALUE;
   private final Vector pendingLoginMessages = new Vector();
   public static long curCheckController;
   public static long curCheckMap;
   public static long logController;
   public static long logMap;
   public static boolean reciveFromMainSession;

   public static Service gI() {
      if (instance == null) {
         instance = new Service();
      }

      return instance;
   }

   public final void gotoPlayer(int id) {
      Message message = null;

      try {
         (message = new Message((byte)18)).writer().writeInt(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void onPrimaryClientInfoSent() {
   }

   public final void charInfo(String day, String month, String year, String address, String cmnd, String dayCmnd, String noiCapCmnd, String sdt, String name) {
      Message message = null;

      try {
         (message = new Message((byte)42)).writer().writeUTF(day);
         message.writer().writeUTF(month);
         message.writer().writeUTF(year);
         message.writer().writeUTF(address);
         message.writer().writeUTF(cmnd);
         message.writer().writeUTF(dayCmnd);
         message.writer().writeUTF(noiCapCmnd);
         message.writer().writeUTF(sdt);
         message.writer().writeUTF(name);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void onSecondaryClientInfoSent() {
   }

   public final void combine(byte action, MyVector id) {
      Res.outz("combine");
      Message message = null;

      try {
         (message = new Message((byte)-81)).writer().writeByte(1);
         message.writer().writeByte(id.size());

         for (int idIndex = 0; idIndex < id.size(); idIndex++) {
            message.writer().writeByte(((Item)id.elementAt(idIndex)).indexUI);
            Res.outz("gui id " + ((Item)id.elementAt(idIndex)).indexUI);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void giaodich(byte action, int playerID, byte index, int num) {
      Res.outz("giao dich action = " + action);
      Message message = null;

      try {
         (message = new Message((byte)-86)).writer().writeByte(action);
         if (action == 0 || action == 1) {
            message.writer().writeInt(playerID);
         }

         if (action == 2) {
            Res.outz("gui len indxe =" + index + " num= " + num);
            message.writer().writeByte(index);
            message.writer().writeInt(num);
         }

         if (action == 4) {
            message.writer().writeByte(index);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendClientInput(TField[] t) {
      Message message = null;

      try {
         (message = new Message((byte)-125)).writer().writeByte(t.length);

         for (int tIndex = 0; tIndex < t.length; tIndex++) {
            message.writer().writeUTF(t[tIndex].getText());
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void speacialSkill(byte index) {
      Message message = null;

      try {
         (message = new Message((byte)112)).writer().writeByte(0);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void mobCapcha(char ch) {
      Res.outz("cap char c= " + ch);
      Message message = null;

      try {
         (message = new Message((byte)-85)).writer().writeChar(ch);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void friend(byte action, int playerId) {
      Res.outz("add friend");
      Message message = null;

      try {
         (message = new Message((byte)-80)).writer().writeByte(action);
         if (playerId != -1) {
            message.writer().writeInt(playerId);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getArchivemnt(int index) {
      Res.outz("get ngoc");
      Message message = null;

      try {
         (message = new Message((byte)-76)).writer().writeByte(index);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getPlayerMenu(int playerID) {
      Message message = null;

      try {
         (message = new Message((byte)-79)).writer().writeInt(playerID);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clanImage(byte id) {
      Message message = null;

      try {
         (message = new Message((byte)-62)).writer().writeByte(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void skill_not_focus(byte status) {
      Res.outz("Skill not focus so " + status);
      Message message = null;

      try {
         (message = new Message((byte)-45)).writer().writeByte(status);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clanDonate(int id) {
      Message message = null;

      try {
         Res.outz("CLAN DONATE");
         (message = new Message((byte)-54)).writer().writeInt(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clanMessage(int type, String text, int clanID) {
      Message message = null;

      try {
         Res.outz("CLAN MESSAGE");
         (message = new Message((byte)-51)).writer().writeByte(type);
         if (type == 0) {
            message.writer().writeUTF(text);
         }

         if (type == 2) {
            message.writer().writeInt(clanID);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void useItem(byte type, byte where, byte index, short template) {
      if (Char.myCharz().statusMe != 14) {
         Message message = null;

         try {
            (message = new Message((byte)-43)).writer().writeByte(type);
            message.writer().writeByte(where);
            message.writer().writeByte(index);
            if (index == -1) {
               message.writer().writeShort(template);
            }

            this.session.sendMessage(message);
            return;
         } catch (Exception exception) {
            exception.printStackTrace();
         } finally {
            message.cleanup();
         }
      }
   }

   public final void joinClan(int id, byte action) {
      Res.outz("JOIN CLAN " + id);
      Message message = null;

      try {
         (message = new Message((byte)-49)).writer().writeInt(id);
         message.writer().writeByte(action);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clanMember(int id) {
      Res.outz("MEMBER CLAN " + id);
      Message message = null;

      try {
         (message = new Message((byte)-50)).writer().writeInt(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void searchClan(String text) {
      Res.outz("send SEARCH CLAN " + text);
      Message message = null;

      try {
         (message = new Message((byte)-47)).writer().writeUTF(text);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clanRemote(int id, byte role) {
      Res.outz("REMOTE CLAN id = " + id + " role= " + role);
      Message message = null;

      try {
         (message = new Message((byte)-56)).writer().writeInt(id);
         message.writer().writeByte(role);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void leaveClan() {
      Res.outz("LEAVE CLAN");
      Message message = null;

      try {
         message = new Message((byte)-55);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clanInvite(byte action, int playerID, int clanID, int code) {
      Res.outz("invite action = " + action + "layerid= " + playerID + " clanID= " + clanID + " code= " + code);
      Message message = null;

      try {
         (message = new Message((byte)-57)).writer().writeByte(action);
         if (action == 0) {
            message.writer().writeInt(playerID);
         }

         if (action == 1 || action == 2) {
            message.writer().writeInt(clanID);
            message.writer().writeInt(code);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getClan(byte action, int id, String text) {
      Message message = null;

      try {
         (message = new Message((byte)-46)).writer().writeByte(action);
         if (action == 2 || action == 4) {
            message.writer().writeShort((short)id);
            message.writer().writeUTF(text);
            Res.outz("SEND SLOGAN= " + text);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void updateCaption(byte gender) {
      Message message = null;

      try {
         (message = new Message((byte)-41)).writer().writeByte(gender);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getItem(byte type, byte id) {
      Message message = null;

      try {
         (message = new Message((byte)-40)).writer().writeByte(type);
         message.writer().writeByte(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   private static Message messageNotLogin(byte command) throws java.io.IOException {
      Message message;
      (message = new Message((byte)-29)).writer().writeByte(command);
      return message;
   }

   private static Message messageNotMap(byte command) throws java.io.IOException {
      Message message;
      (message = new Message((byte)-28)).writer().writeByte(command);
      return message;
   }

   public final synchronized boolean sendClientInfo(boolean secondary) {
      Session_ME sessionValue;
      if ((sessionValue = secondary ? Session_ME.gI2() : Session_ME.gI()) != null && sessionValue.isConnected()) {
         long mainClientInfoGenerationValue = sessionValue.getConnectionGeneration();
         if (secondary ? secondaryClientInfoSent && secondaryClientInfoGeneration == mainClientInfoGenerationValue : mainClientInfoSent && mainClientInfoGeneration == mainClientInfoGenerationValue) {
            return false;
         }

         if (Rms.loadRMSInt(Rms.RMS_clienttype) != -1) {
            mSystem.clientType = Rms.loadRMSInt(Rms.RMS_clienttype);
         }

         Message message = null;
         InputStream resourceAsStream = null;

         try {
            Res.outz("setType " + (secondary ? "secondary" : "main"));
            (message = messageNotLogin((byte)2)).writer().writeByte(mSystem.clientType);
            message.writer().writeByte(mGraphics.zoomLevel);
            message.writer().writeBoolean(false);
            message.writer().writeInt(GameCanvas.w);
            message.writer().writeInt(GameCanvas.h);
            message.writer().writeBoolean(TField.isQwerty);
            message.writer().writeBoolean(GameCanvas.isTouch);
            message.writer().writeUTF(MotherCanvas.getPlatformName() + "|" + "2.5.2");
            if ((resourceAsStream = this.getClass().getResourceAsStream("/res\\info")) != null) {
               ByteArrayOutputStream buffer = new ByteArrayOutputStream();
               byte[] data = new byte[256];

               int value;
               while ((value = resourceAsStream.read(data)) > 0) {
                  buffer.write(data, 0, value);
               }

               byte[] data2;
               if ((data2 = buffer.toByteArray()).length > 0) {
                  message.writer().writeShort(data2.length);
                  message.writer().write(data2);
                  Res.err("write " + data2.length + "|" + "2.5.2");
               }
            }

            sessionValue.sendMessage(message);
            if (secondary) {
               secondaryClientInfoSent = true;
               secondaryClientInfoGeneration = mainClientInfoGenerationValue;
            } else {
               mainClientInfoSent = true;
               mainClientInfoGeneration = mainClientInfoGenerationValue;
            }

            return true;
         } catch (IOException iOException) {
            Res.err("send CLIENT_INFO failed: " + iOException.toString());
         } finally {
            if (resourceAsStream != null) {
               try {
                  resourceAsStream.close();
               } catch (IOException ignoredIOException) {
               }
            }

            if (message != null) {
               message.cleanup();
            }
         }

         return false;
      } else {
         Res.err("skip CLIENT_INFO: socket is not connected");
         return false;
      }
   }

   public final synchronized void resetClientInfoState(boolean secondary) {
      if (secondary) {
         secondaryClientInfoSent = false;
         secondaryClientInfoGeneration = Long.MIN_VALUE;
      } else {
         mainClientInfoSent = false;
         mainClientInfoGeneration = Long.MIN_VALUE;
         acknowledgedMainGeneration = Long.MIN_VALUE;
      }
   }

   public final synchronized void resetClientInfoStateForGeneration(boolean secondary, long longValue) {
      Session_ME sessionValue;
      if ((sessionValue = secondary ? Session_ME.gI2() : Session_ME.gI()) != null && sessionValue.getConnectionGeneration() == longValue) {
         this.resetClientInfoState(secondary);
      }
   }

   public final synchronized void resetClientInfoAndPendingLogin() {
      mainClientInfoSent = false;
      secondaryClientInfoSent = false;
      mainClientInfoGeneration = Long.MIN_VALUE;
      secondaryClientInfoGeneration = Long.MIN_VALUE;
      acknowledgedMainGeneration = Long.MIN_VALUE;
      this.releasePendingLoginMessages();
   }

   public final synchronized void clearPendingLogin() {
      this.releasePendingLoginMessages();
   }

   public final synchronized boolean isMainClientInfoAcknowledged() {
      Session_ME sessionValue;
      if ((sessionValue = Session_ME.gI()) != null && sessionValue.isConnected()) {
         long connectionGeneration = sessionValue.getConnectionGeneration();
         return mainClientInfoSent && mainClientInfoGeneration == connectionGeneration && acknowledgedMainGeneration == connectionGeneration;
      } else {
         return false;
      }
   }

   public final synchronized boolean acknowledgeMainClientInfo() {
      Session_ME sessionValue;
      if ((sessionValue = Session_ME.gI()) != null && sessionValue.isConnected()) {
         long acknowledgedMainGenerationValue = sessionValue.getConnectionGeneration();
         if (mainClientInfoSent && mainClientInfoGeneration == acknowledgedMainGenerationValue) {
            acknowledgedMainGeneration = acknowledgedMainGenerationValue;
            boolean result;
            if (result = this.pendingLoginMessages.size() > 0) {
               this.flushPendingLoginMessages();
            }

            return result;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void releasePendingLoginMessages() {
      while (this.pendingLoginMessages.size() > 0) {
         Message pendingLoginMessage = (Message)this.pendingLoginMessages.elementAt(0);
         this.pendingLoginMessages.removeElementAt(0);
         if (pendingLoginMessage != null) {
            pendingLoginMessage.cleanup();
         }
      }
   }



   private boolean flushPendingLoginMessages() {
      Session_ME sessionValue = Session_ME.gI();

      while (this.pendingLoginMessages.size() > 0) {
         Message message;
         if ((message = (Message)this.pendingLoginMessages.elementAt(0)) == null) {
            this.pendingLoginMessages.removeElementAt(0);
         } else {
            boolean condition = false;
            boolean cleanupPending = false                             ;

            try {
               cleanupPending = true;
               sessionValue.sendMessage(message);
               condition = true;
               cleanupPending = false;
            } catch (RuntimeException runtimeException) {
               Res.err("flush pending login failed: " + runtimeException.toString());
               return false;
            } finally {
               if (cleanupPending) {
                  if (condition && this.pendingLoginMessages.size() > 0 && this.pendingLoginMessages.elementAt(0) == message) {
                     this.pendingLoginMessages.removeElementAt(0);
                     message.cleanup();
                  }
               }
            }

            if (this.pendingLoginMessages.size() > 0 && this.pendingLoginMessages.elementAt(0) == message) {
               this.pendingLoginMessages.removeElementAt(0);
               message.cleanup();
            }
         }
      }

      return true;
   }

   private void replacePendingLogin(Message pendingLoginMessage) {
      this.releasePendingLoginMessages();
      this.pendingLoginMessages.addElement(pendingLoginMessage);
   }

   private synchronized void sendOrQueueLogin(Message message) {
      if (message != null) {
         if (!this.isMainClientInfoAcknowledged()) {
            this.replacePendingLogin(message);
         } else if (!this.flushPendingLoginMessages()) {
            this.replacePendingLogin(message);
         } else {
            try {
               Session_ME.gI().sendMessage(message);
               message.cleanup();
            } catch (RuntimeException runtimeException) {
               this.pendingLoginMessages.addElement(message);
               Res.err("queue login failed, will retry: " + runtimeException.toString());
            }
         }
      }
   }

   public final void sendCheckController() {
      Message message = null;

      try {
         message = new Message((byte)-120);
         this.session.sendMessage(message);
         return;
      } catch (Exception ignoredException) {
      } finally {
         curCheckController = mSystem.currentTimeMillis();
         message.cleanup();
      }
   }

   public final void sendCheckMap() {
      Message message = null;

      try {
         message = new Message((byte)-121);
         this.session.sendMessage(message);
         return;
      } catch (Exception ignoredException) {
      } finally {
         curCheckMap = mSystem.currentTimeMillis();
         message.cleanup();
      }
   }

   public final void login(String username, String pass, String version, byte type) {
      Res.outz("[LOGIN] request queued; clientType=" + mSystem.clientType);
      Message message = null;

      try {
         (message = messageNotLogin((byte)0)).writer().writeUTF(main.LoginCodeResource.getCode());
         message.writer().writeUTF(username);
         message.writer().writeUTF(pass);
         message.writer().writeUTF(version);
         message.writer().writeByte(type);
         message.writer().writeByte(mResources.language);
         this.sendOrQueueLogin(message);
         message = null;
         PostLoginRequestHook.afterLoginRequestQueued();
         return;
      } catch (IOException iOException) {
         iOException.printStackTrace();
      } finally {
         if (message != null) {
            message.cleanup();
         }
      }
   }

   public final void requestRegister(String text, String text2, String text3, String text4) {
      Res.outz("Request Register " + text + " " + text2 + " " + text4);

      try {
         Message message;
         (message = messageNotLogin((byte)1)).writer().writeUTF(text);
         message.writer().writeUTF(text2);
         if (text3 != null && !text3.equals("")) {
            message.writer().writeUTF(text3);
            message.writer().writeUTF("a");
         }

         this.session.sendMessage(message);
         message.cleanup();
      } catch (IOException iOException) {
         iOException.printStackTrace();
      }
   }

   public final void requestChangeMap() {
      Message message = new Message((byte)-23);
      this.session.sendMessage(message);
      message.cleanup();
   }

   public final void magicTree(byte type) {
      Message message = new Message((byte)-34);

      try {
         message.writer().writeByte(type);
         this.session.sendMessage(message);
         message.cleanup();
      } catch (Exception ignoredException) {
      }
   }

   public final void requestChangeZone(int value) {
      Message message = new Message((byte)21);

      try {
         message.writer().writeByte(value);
         this.session.sendMessage(message);
         message.cleanup();
      } catch (Exception ignoredException) {
      }
   }

   public final void checkMMove(int second) {
      Message message = new Message((byte)-78);

      try {
         message.writer().writeInt(second);
         this.session.sendMessage(message);
         message.cleanup();
      } catch (Exception ignoredException) {
      }
   }

   public final void charMove() {
      int deltaX = Char.myCharz().cx - Char.myCharz().cxSend;
      int deltaY = Char.myCharz().cy - Char.myCharz().cySend;
      if (!Char.ischangingMap
         && (deltaX != 0 || deltaY != 0)
         && !Controller.isStopReadMessage
         && !Char.myCharz().isTeleport
         && Char.myCharz().cy > 0
         && !Char.myCharz().telePortSkill) {
         try {
            Message message = new Message((byte)-7);
            Char.myCharz().cxSend = Char.myCharz().cx;
            Char.myCharz().cySend = Char.myCharz().cy;
            Char.myCharz().lastSentMoveDirection = Char.myCharz().cdir;
            Char.myCharz().lastSentMoveStatus = Char.myCharz().statusMe;
            if (TileMap.tileTypeAt(
                  Char.myCharz().cx / TileMap.size, Char.myCharz().cy / TileMap.size
               )
               == 0) {
               message.writer().writeByte(1);
            } else {
               message.writer().writeByte(0);
            }

            message.writer().writeShort(Char.myCharz().cx);
            if (deltaY != 0) {
               message.writer().writeShort(Char.myCharz().cy);
            }

            this.session.sendMessage(message);
            GameScr.tickMove++;
            message.cleanup();
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   public final void createChar(String name, int gender, int hair) {
      Message message = new Message((byte)-28);

      try {
         message.writer().writeByte(2);
         message.writer().writeUTF(name);
         message.writer().writeByte(gender);
         message.writer().writeByte(hair);
         Res.outz("name= " + name + " gender= " + gender + " hair=" + hair);
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      this.session.sendMessage(message);
   }

   public final void requestModTemplate(int modTemplateId) {
      Message message = null;

      try {
         (message = new Message((byte)11)).writer().writeShort(modTemplateId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void saleItem(byte action, byte type, short id) {
      Message message = null;

      try {
         (message = new Message((byte)7)).writer().writeByte(action);
         message.writer().writeByte(type);
         message.writer().writeShort(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void buyItem(byte type, int id, int quantity) {
      Message message = null;

      try {
         (message = new Message((byte)6)).writer().writeByte(type);
         message.writer().writeShort(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void selectSkill(int skillTemplateId) {
      Res.outz(Char.myCharz().cName + " SELECT SKILL " + skillTemplateId);
      Message message = null;

      try {
         (message = new Message((byte)34)).writer().writeShort(skillTemplateId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getEffData(short id) {
      Message message = null;

      try {
         (message = new Message((byte)-66)).writer().writeShort(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void openUIZone() {
      Message message = null;

      try {
         message = new Message((byte)29);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void confirmMenu(short npcID, byte select) {
      Res.outz("confirme menu=" + select + " npc= " + npcID);
      Message message = null;

      try {
         (message = new Message((byte)32)).writer().writeShort(npcID);
         message.writer().writeByte(select);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void openMenu(int npcId) {
      Res.outz("npc id" + npcId);
      Message message = null;

      try {
         (message = new Message((byte)33)).writer().writeShort(npcId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void menu(int npcId, int menuId, int optionId) {
      Message message = null;

      try {
         (message = new Message((byte)22)).writer().writeByte(npcId);
         message.writer().writeByte(menuId);
         message.writer().writeByte(optionId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void menuId(short menuId) {
      Message message = null;

      try {
         (message = new Message((byte)27)).writer().writeShort(menuId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void textBoxId(short menuId, String str) {
      Message message = null;

      try {
         (message = new Message((byte)88)).writer().writeShort(menuId);
         message.writer().writeUTF(str);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void crystalCollectLock(Item[] items) {
      GameCanvas.msgdlg.pleasewait();
      Message message = null;

      try {
         message = new Message((byte)13);

         for (int itemIndex = 0; itemIndex < items.length; itemIndex++) {
            if (items[itemIndex] != null) {
               message.writer().writeByte(items[itemIndex].indexUI);
            }
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void acceptInviteTrade(int playerMapId) {
      Message message = null;

      try {
         (message = new Message((byte)37)).writer().writeInt(playerMapId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void cancelInviteTrade() {
      Message message = null;

      try {
         message = new Message((byte)50);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendPlayerAttack(MyVector vMob, MyVector vChar, int type) {
      try {
         Res.outz("SEND ATTACT vChar=  " + vChar.size());
         Message message = null;
         if (type != 0) {
            if (vMob.size() > 0 && vChar.size() > 0) {
               if (type == 1) {
                  message = new Message((byte)-4);
               } else if (type == 2) {
                  message = new Message((byte)67);
               }

               message.writer().writeByte(vMob.size());

               for (int mobIndex = 0; mobIndex < vMob.size(); mobIndex++) {
                  Mob mob = (Mob)vMob.elementAt(mobIndex);
                  message.writer().writeByte(mob.mobId);
               }

               for (int charIndex = 0; charIndex < vChar.size(); charIndex++) {
                  Char character;
                  if ((character = (Char)vChar.elementAt(charIndex)) != null) {
                     message.writer().writeInt(character.charID);
                  } else {
                     message.writer().writeInt(-1);
                  }
               }
            } else if (vMob.size() > 0) {
               Res.outz("PLAYER ATTACK NPC");
               message = new Message((byte)54);

               for (int mobIndex2 = 0; mobIndex2 < vMob.size(); mobIndex2++) {
                  Mob mob2;
                  if (!(mob2 = (Mob)vMob.elementAt(mobIndex2)).isMobMe) {
                     message.writer().writeByte(mob2.mobId);
                  } else {
                     message.writer().writeByte(-1);
                     message.writer().writeInt(mob2.mobId);
                  }
               }
            } else if (vChar.size() > 0) {
               Res.outz("player attack player ");
               message = new Message((byte)-60);

               for (int charIndex2 = 0; charIndex2 < vChar.size(); charIndex2++) {
                  Char character2 = (Char)vChar.elementAt(charIndex2);
                  message.writer().writeInt(character2.charID);
               }
            }

            message.writer().writeByte((byte)Char.myCharz().cdir);
            if (message != null) {
               this.session.sendMessage(message);
            }
         }
      } catch (Exception ignoredException) {
      }
   }

   public final void pickItem(int itemMapId) {
      Message message = null;

      try {
         (message = new Message((byte)-20)).writer().writeShort(itemMapId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void returnTownFromDead() {
      Message message = null;

      try {
         message = new Message((byte)-15);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void wakeUpFromDead() {
      Message message = null;

      try {
         message = new Message((byte)-16);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void chat(String text) {
      Message message = null;

      try {
         Res.outz("Send chat " + text);
         (message = new Message((byte)44)).writer().writeUTF(text);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void updateData() {
      Message message = null;

      try {
         message = new Message((byte)-87);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void updateMap() {
      Message message = null;

      try {
         message = messageNotMap((byte)6);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void updateSkill() {
      Message message = null;

      try {
         message = messageNotMap((byte)7);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void updateItem() {
      Message message = null;

      try {
         message = messageNotMap((byte)8);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clientOk() {
      Message message = null;

      try {
         message = messageNotMap((byte)13);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void addFriend(String name) {
      Message message = null;

      try {
         (message = new Message((byte)53)).writer().writeUTF(name);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void addPartyAccept(int charId) {
      Message message = null;

      try {
         (message = new Message((byte)76)).writer().writeInt(charId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void addPartyCancel(int charId) {
      Message message = null;

      try {
         (message = new Message((byte)77)).writer().writeInt(charId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void player_vs_player(byte action, byte type, int playerId) {
      Res.outz("PLAYER VS PLAYER");
      Message message = null;

      try {
         (message = new Message((byte)-59)).writer().writeByte(action);
         message.writer().writeByte(type);
         message.writer().writeInt(playerId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void requestMaptemplate(int maptemplateId) {
      Message message = null;

      try {
         (message = messageNotMap((byte)10)).writer().writeByte(maptemplateId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void acceptPleaseParty(String str) {
      Message message = null;

      try {
         (message = new Message((byte)17)).writer().writeUTF(str);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void chatPlayer(String text, int id) {
      Message message = null;

      try {
         (message = new Message((byte)-72)).writer().writeInt(id);
         message.writer().writeUTF(text);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void chatGlobal(String text) {
      Message message = null;

      try {
         (message = new Message((byte)-71)).writer().writeUTF(text);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendCardInfo(String NAP, String PIN) {
      Message message = null;

      try {
         (message = messageNotMap((byte)16)).writer().writeUTF(NAP);
         message.writer().writeUTF(PIN);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void changeName(String name, int id) {
      Message message = null;

      try {
         (message = messageNotMap((byte)18)).writer().writeInt(id);
         message.writer().writeUTF(name);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void requestIcon(int id) {
      GameCanvas.connect();
      Message message = null;

      try {
         (message = new Message((byte)-67)).writer().writeInt(id);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void activeAccProtect(int pass) {
      Message message = null;

      try {
         (message = messageNotMap((byte)37)).writer().writeInt(pass);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void clearAccProtect(int pass) {
      Message message = null;

      try {
         (message = messageNotMap((byte)41)).writer().writeInt(pass);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void openLockAccProtect(int pass2) {
      Message message = null;

      try {
         (message = messageNotMap((byte)39)).writer().writeInt(pass2);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getBgTemplate(short id) {
      Res.outz("get image id= " + id);
      Message message = null;

      try {
         (message = new Message((byte)-32)).writer().writeShort(id);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getMapOffline() {
      Res.outz("send map offline");
      Message message = null;

      try {
         message = new Message((byte)-33);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void finishUpdate() {
      Message message = null;

      try {
         message = new Message((byte)-38);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void finishUpdate(int playerID) {
      Message message = null;

      try {
         (message = new Message((byte)-38)).writer().writeInt(playerID);
         this.session.sendMessage(message);
         return;
      } catch (Exception ignoredException) {
      } finally {
         message.cleanup();
      }
   }

   public final void finishLoadMap() {
      Res.outz("finishLoadMap");
      Message message = null;

      try {
         message = new Message((byte)-39);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void requestBagImage(int ID) {
      Res.outz("request bag image");
      Message message = null;

      try {
         (message = new Message((byte)-63)).writer().writeShort(ID);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void login2(String user) {
      Res.outz("Login 2");
      Message message = null;

      try {
         (message = new Message((byte)-101)).writer().writeUTF(user);
         message.writer().writeByte(1);
         this.sendOrQueueLogin(message);
         message = null;
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         if (message != null) {
            message.cleanup();
         }
      }
   }

   public final void getMagicTree(byte action) {
      Res.outz("request magic tree");
      Message message = null;

      try {
         (message = new Message((byte)-34)).writer().writeByte(2);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void upPotential(int typePotential, int value) {
      Message message = null;

      try {
         byte byteValue = 16;
         Message message2;
         (message2 = new Message((byte)-30)).writer().writeByte(16);
         message = message2;
         message2.writer().writeByte(typePotential);
         message.writer().writeShort(value);
         Res.outz("gửi tăng tiềm năng NUM= " + value + " type= " + typePotential);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getResource(byte action, MyVector vResourceIndex) {
      Res.outz("request resource action= " + action);
      Message message = null;

      try {
         (message = new Message((byte)-74)).writer().writeByte(action);
         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            reciveFromMainSession = true;
            this.session = Session_ME.gI();
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void requestMapSelect(int selected) {
      Res.outz("request magic tree");
      Message message = null;

      try {
         (message = new Message((byte)-91)).writer().writeByte(selected);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void petInfo() {
      Message message = null;

      try {
         message = new Message((byte)-107);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendTop(String topName, byte selected) {
      Message message = null;

      try {
         (message = new Message((byte)-96)).writer().writeUTF(topName);
         message.writer().writeByte(selected);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendThachDau(int id) {
      Res.outz("GUI THACH DAUA");
      Message message = null;

      try {
         (message = new Message((byte)-118)).writer().writeInt(id);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void enemy(byte b, int charID) {
      Res.outz("add friend");
      Message message = null;

      try {
         (message = new Message((byte)-99)).writer().writeByte(b);
         if (b == 1 || b == 2) {
            message.writer().writeInt(charID);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void kigui(byte action, int itemId, byte moneyType, int money, int quaintly) {
      Message message = null;

      try {
         Res.outz("ki gui action= " + action + " item id= " + itemId);
         (message = new Message((byte)-100)).writer().writeByte(action);
         if (action == 0) {
            message.writer().writeShort(itemId);
            message.writer().writeByte(moneyType);
            message.writer().writeInt(money);
            message.writer().writeInt(quaintly);
            Res.outz("ki gui so luong " + quaintly);
         }

         if (action == 1 || action == 2) {
            message.writer().writeShort(itemId);
         }

         if (action == 3) {
            message.writer().writeShort(itemId);
            message.writer().writeByte(moneyType);
            message.writer().writeInt(money);
         }

         if (action == 4) {
            message.writer().writeByte(moneyType);
            message.writer().writeByte(money);
            Res.outz("currTab= " + moneyType + " page= " + money);
         }

         if (action == 5) {
            message.writer().writeShort(itemId);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getFlag(byte action, byte flagType) {
      Message message = null;

      try {
         (message = new Message((byte)-103)).writer().writeByte(action);
         Res.outz("------------service--  " + action + "   " + flagType);
         if (action != 0) {
            message.writer().writeByte(flagType);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void setLockInventory(int pass) {
      Message message = null;

      try {
         (message = new Message((byte)-104)).writer().writeInt(pass);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void petStatus(byte status) {
      Message message = null;

      try {
         (message = new Message((byte)-108)).writer().writeByte(status);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void transportNow() {
      Message message = null;

      try {
         message = new Message((byte)-105);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void funsion(byte type) {
      Message message = null;

      try {
         Res.outz("FUNSION");
         (message = new Message((byte)125)).writer().writeByte(type);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void imageSource(MyVector vID) {
      Message message = null;

      try {
         Res.outz("IMAGE SOURCE size= " + vID.size());
         (message = new Message((byte)-111)).writer().writeShort(vID.size());
         if (vID.size() > 0) {
            for (int iDIndex = 0; iDIndex < vID.size(); iDIndex++) {
               Res.outz("gui len str " + ((ImageSource)vID.elementAt(iDIndex)).id);
               message.writer().writeUTF(((ImageSource)vID.elementAt(iDIndex)).id);
            }
         }

         if (Session_ME.gI2().isConnected() && !Session_ME.gI2().connecting) {
            this.session = Session_ME.gI2();
         } else {
            this.session = Session_ME.gI();
            reciveFromMainSession = true;
         }

         this.session.sendMessage(message);
         this.session = Session_ME.gI();
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendServerData(byte action, int id, byte[] data) {
      Message message = null;

      try {
         Res.outz("SERVER DATA");
         (message = new Message((byte)-110)).writer().writeByte(1);
         message.writer().writeInt(id);
         if (data != null) {
            id = data.length;
            message.writer().writeShort(id);
            message.writer().write(data, 0, id);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void changeOnKeyScr(byte[] skill) {
      Message message = null;

      try {
         message = new Message((byte)-113);

         for (int skillIndex = 0; skillIndex < GameScr.onScreenSkill.length; skillIndex++) {
            message.writer().writeByte(skill[skillIndex]);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void requestPean() {
      Message message = null;

      try {
         message = new Message((byte)-114);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void messagePlayerMenu(int charId) {
      Message message = null;

      try {
         (message = new Message((byte)-30)).writer().writeByte(63);
         message.writer().writeInt(charId);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void playerMenuAction(int charId, short select) {
      Message message = null;

      try {
         (message = new Message((byte)-30)).writer().writeByte(64);
         message.writer().writeInt(charId);
         message.writer().writeShort(select);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void getImgByName(String nameImg) {
      Message message = null;

      try {
         (message = new Message((byte)66)).writer().writeUTF(nameImg);
         Res.outz(">>>getGetImgByName = " + nameImg);
         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void SendCrackBall(byte type, byte soluong) {
      Message message = new Message((byte)-127);

      try {
         message.writer().writeByte(type);
         if (soluong > 0) {
            message.writer().writeByte(soluong);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void SendRada(int i, int id) {
      Message message = new Message((byte)127);

      try {
         message.writer().writeByte(i);
         if (id != -1) {
            message.writer().writeShort(id);
         }

         this.session.sendMessage(message);
         return;
      } catch (Exception exception) {
         exception.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void sendDelAcc() {
      Message message = new Message((byte)69);

      try {
         this.session.sendMessage(message);
         return;
      } catch (Exception ignoredException) {
      } finally {
         message.cleanup();
      }
   }

   public final void new_skill_not_focus(byte idTemplateSkill, byte dir, short x, short y) {
      Message message = null;

      try {
         (message = new Message((byte)-45)).writer().writeByte(20);
         message.writer().writeByte(idTemplateSkill);
         message.writer().writeShort(Char.myCharz().cx);
         message.writer().writeShort(Char.myCharz().cy);
         message.writer().writeByte(dir);
         message.writer().writeShort(x);
         message.writer().writeShort(y);
         this.session.sendMessage(message);
         return;
      } catch (Exception ignoredException) {
      } finally {
         message.cleanup();
      }
   }

   public final void sendCT_ready(byte sub, byte sub_sub) {
      Message message = null;

      try {
         (message = new Message((byte)24)).writer().writeByte(sub);
         message.writer().writeByte(sub_sub);
         Res.err(" =====> SEND OPTION_HAT " + sub + "_" + sub_sub);
         this.session.sendMessage(message);
         return;
      } catch (Exception ignoredException) {
      } finally {
         message.cleanup();
      }
   }
}
