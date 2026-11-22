package nro;

import main.GameCanvas;

public final class ChatTextField implements IActionListener {
   private static ChatTextField instance;
   public TField tfChat;
   public boolean isShow = false;
   public IChatable parentScreen;
   private long lastChatTime = 0L;
   public Command left;
   public Command right;
   public Command center = null;
   private int x;
   private int y;
   private int w;
   private int h;
   public String to;
   public String strChat = mResources.CHAT + " ";

   public final void initChatTextField() {
      this.left = new Command(
         mResources.OK, this, 8000, null, 1, GameCanvas.h - mScreen.cmdH + 1
      );
      this.right = new Command(
         mResources.DELETE, this, 8001, null, GameCanvas.w - 70, GameCanvas.h - mScreen.cmdH + 1
      );
      this.center = null;
      this.w = this.tfChat.width + 28;
      this.h = this.tfChat.height + 26;
      this.x = GameCanvas.w / 2 - this.w / 2;
      this.y = this.tfChat.y - 18;
      if (this.w > 320) {
         this.w = 320;
      }

      this.left.x = this.x;
      this.right.x = this.x + this.w - 68;
      if (GameCanvas.isTouch) {
         this.tfChat.y -= 5;
         this.y -= 20;
         this.h += 30;
         this.left.x = GameCanvas.w / 2 - 68 - 5;
         this.right.x = GameCanvas.w / 2 + 5;
         this.left.y = GameCanvas.h - 30;
         this.right.y = GameCanvas.h - 30;
      }
   }

   public final void keyPressed(int keyCode) {
      if (this.isShow) {
         this.tfChat.keyPressed(keyCode);
      }

      if (this.tfChat.getText().equals("")) {
         this.right.caption = mResources.CLOSE;
      } else {
         this.right.caption = mResources.DELETE;
      }
   }

   public static ChatTextField gI() {
      return instance == null ? (instance = new ChatTextField()) : instance;
   }

   public ChatTextField() {
      this.tfChat = new TField();
      this.tfChat.name = mResources.CHAT;
      this.tfChat.width = GameCanvas.w - 32;
      if (this.tfChat.width > 250) {
         this.tfChat.width = 250;
      }

      this.tfChat.height = mScreen.ITEM_HEIGHT + 2;
      this.tfChat.x = GameCanvas.w / 2 - this.tfChat.width / 2;
      this.tfChat.isFocus = true;
      this.tfChat.setMaxTextLenght(80);
   }

   public final void startChat(int firstCharacter, IChatable parentScreen, String to) {
      this.right.caption = mResources.CLOSE;
      this.to = to;
      this.tfChat.keyPressed(firstCharacter);
      if (!this.tfChat.getText().equals("") && GameCanvas.currentDialog == null) {
         this.parentScreen = parentScreen;
         this.isShow = true;
      }
   }

   public final void startChat(String toValue) {
      this.right.caption = mResources.CLOSE;
      this.to = toValue;
      if (GameCanvas.currentDialog == null) {
         this.isShow = true;
         if (GameCanvas.isTouch) {
            this.tfChat.doChangeToTextBox();
         }
      }
   }

   public final void update() {
      if (this.isShow) {
         if (mSystem.clientType != 5 && mSystem.clientType != 3) {
            this.tfChat.update();
         }

         if (this.tfChat.justReturnFromTextBox) {
            this.tfChat.justReturnFromTextBox = false;
            this.parentScreen.onChatFromMe(this.tfChat.getText(), this.to);
            this.tfChat.setText("");
            this.right.caption = mResources.CLOSE;
         }
      }
   }

   public final void paint(mGraphics g) {
      if (this.isShow) {
         if (mSystem.clientType != 5 && mSystem.clientType != 3) {
            PopUp.paintPopUp(g, this.x, this.y, this.w, this.h, -1, true);
            mFont.tahoma_7b_green2
               .drawString(
                  g,
                  this.strChat + this.to,
                  this.tfChat.x,
                  this.tfChat.y - (GameCanvas.isTouch ? 17 : 12),
                  0
               );
            Paint.paintCmdBar(g, this.left, this.center, this.right);
            this.tfChat.paint(g);
         }
      }
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 8000:
            Res.outz("perform chat 1");
            if (this.parentScreen != null) {
               long lastChatTimeValue;
               if ((lastChatTimeValue = System.currentTimeMillis()) - this.lastChatTime < 1000L) {
                  return;
               }

               this.lastChatTime = lastChatTimeValue;
               this.parentScreen.onChatFromMe(this.tfChat.getText(), this.to);
               this.tfChat.setText("");
               this.right.caption = mResources.CLOSE;
               return;
            }
            break;
         case 8001:
            Res.outz("perform chat 2");
            if (this.tfChat.getText().equals("")) {
               this.isShow = false;
               this.parentScreen.onCancelChat();
            }

            this.tfChat.clear();
         case 8002:
      }
   }
}
