package nro;

import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import main.GameCanvas;

public final class SelectCharScr extends mScreen implements IActionListener {
   public static boolean isWait;
   private Char mychar;
   private int indexGender;
   private int cx;
   private int cy;
   private int dy = 45;
   private Command cmdSelectSv;
   private int[] bgID = new int[]{0, 4, 8};
   private int[] f = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
   private int count = 0;

   private static void loadMapTableFromResource(byte[] mapID) {
      if (!GameCanvas.lowGraphic) {
         InputStream inputStream = null;
         inputStream = null;

         try {
            for (int currItemIndex = 0; currItemIndex < mapID.length; currItemIndex++) {
               inputStream = SmallDart.openResourceStream("/mymap/mapTable" + mapID[currItemIndex]);
               DataInputStream input;
               short packetShort = (input = new DataInputStream(inputStream)).readShort();
               MapTemplate.vCurrItem[currItemIndex] = new MyVector("MapTemplate.vCurrItem[a] ");

               for (int index = 0; index < packetShort; index++) {
                  short id = input.readShort();
                  short packetShort2 = input.readShort();
                  short packetShort3 = input.readShort();
                  if (TileMap.getBIById(id) != null) {
                     BgItem bIById = TileMap.getBIById(id);
                     BgItem currItem;
                     (currItem = new BgItem()).id = id;
                     currItem.idImage = bIById.idImage;
                     currItem.dx = bIById.dx;
                     currItem.dy = bIById.dy;
                     currItem.x = packetShort2 * TileMap.size;
                     currItem.y = packetShort3 * TileMap.size;
                     currItem.layer = bIById.layer;
                     MapTemplate.vCurrItem[currItemIndex].addElement(currItem);
                     if (!BgItem.imgNew.containsKey(String.valueOf(currItem.idImage))) {
                        try {
                           Image image = mSystem.loadImage("/mapBackGround/" + currItem.idImage + ".png");
                           BgItem.imgNew.put(String.valueOf(currItem.idImage), image);
                        } catch (Exception ignoredException) {
                           Image rGBImage;
                           if ((rGBImage = mSystem.loadZoomedResourceImage("/mapBackGround/" + currItem.idImage + ".png")) == null) {
                              rGBImage = Image.createRGBImage(new int[]{-2013265920}, 1, 1, true);
                              Service.gI().getBgTemplate(currItem.idImage);
                           }

                           BgItem.imgNew.put(String.valueOf(currItem.idImage), rGBImage);
                        }

                        BgItem.vKeysLast.addElement(String.valueOf(currItem.idImage));
                     }

                     if (!BgItem.isExistKeyNews(String.valueOf(currItem.idImage))) {
                        BgItem.vKeysNew.addElement(String.valueOf(currItem.idImage));
                     }

                     BgItem.changeColor();
                  }
               }
            }
         } catch (Exception exception) {
            exception.printStackTrace();
         }
      }
   }

   public SelectCharScr() {
      try {
         if (!GameCanvas.lowGraphic) {
            byte[] data = new byte[]{39, 40, 41};
            InputStream inputStream = null;
            inputStream = null;

            for (int index = 0; index < data.length; index++) {
               inputStream = SmallDart.openResourceStream("/mymap/" + data[index]);
               DataInputStream input = new DataInputStream(inputStream);
               MapTemplate.tmw[index] = (char)input.read();
               MapTemplate.tmh[index] = (char)input.read();
               MapTemplate.maps[index] = new int[input.available()];

               for (int mapIndex = 0; mapIndex < MapTemplate.tmw[index] * MapTemplate.tmh[index]; mapIndex++) {
                  MapTemplate.maps[index][mapIndex] = input.read();
               }

               MapTemplate.types[index] = new int[MapTemplate.maps[index].length];
            }
         }

         loadMapTableFromResource(new byte[]{39, 40, 41});
      } catch (Exception ignoredException) {
      }

      this.cx = 168;
      this.cy = 350;
      SmallImage.imgNew = new Small[32000];
      SmallImage.newSmallVersion = new byte[32000];
      SmallImage.maxSmall = 32000;
   }

   public final void SetInfoChar(Char temp) {
      this.mychar = new Char();
      this.indexGender = this.mychar.cgender = temp.cgender;
      this.mychar.head = temp.head;
      this.mychar.headICON = temp.headICON;
      this.mychar.body = temp.body;
      this.mychar.leg = temp.leg;
      this.mychar.bag = temp.bag;
      this.mychar.cName = temp.cName;
      this.switchToMe();
   }

   public final void switchToMe() {
      GameCanvas.menu.showMenu = false;
      GameCanvas.endDlg();
      GameScr.gI().readPart();
      super.switchToMe();
      SelectCharScr selectCharScr = this;
      TileMap.maps = new int[MapTemplate.maps[selectCharScr.indexGender].length];

      for (int mapIndex = 0; mapIndex < MapTemplate.maps[selectCharScr.indexGender].length; mapIndex++) {
         TileMap.maps[mapIndex] = MapTemplate.maps[selectCharScr.indexGender][mapIndex];
      }

      TileMap.types = MapTemplate.types[selectCharScr.indexGender];
      TileMap.pxh = MapTemplate.pxh[selectCharScr.indexGender];
      TileMap.pxw = MapTemplate.pxw[selectCharScr.indexGender];
      TileMap.tileID = MapTemplate.pxw[selectCharScr.indexGender];
      TileMap.tmw = MapTemplate.tmw[selectCharScr.indexGender];
      TileMap.tmh = MapTemplate.tmh[selectCharScr.indexGender];
      TileMap.tileID = selectCharScr.bgID[selectCharScr.indexGender] + 1;
      TileMap.getTile();
      GameCanvas.loadBG(selectCharScr.bgID[selectCharScr.indexGender]);
      GameScr.loadCamera(selectCharScr.cx, selectCharScr.cy);
      Char.isLoadingMap = false;
      ServerListScreen.countDieConnect = 0;
      super.center = new Command(mResources.SELECT, this, 100, null);
      super.left = new Command(mResources.BACK, this, 101, null);
      this.cmdSelectSv = new Command(ServerListScreen.nameServer[ServerListScreen.ipSelect], this, 102, null);
      this.cmdSelectSv.x = 1;
      this.cmdSelectSv.y = 3;
   }

   public final void paint(mGraphics g) {
      if (Controller.isGet_CLIENT_INFO) {
         if (!isWait) {
            if (!Char.isLoadingMap) {
               GameCanvas.paintBGGameScr(g);
               g.translate(-GameScr.cmx, -GameScr.cmy);

               for (int currItemIndex = 0; currItemIndex < MapTemplate.vCurrItem[this.indexGender].size(); currItemIndex++) {
                  BgItem currItem;
                  if ((currItem = (BgItem)MapTemplate.vCurrItem[this.indexGender].elementAt(currItemIndex)).idImage != -1
                     && currItem.layer == 1) {
                     currItem.paint(g);
                  }
               }

               TileMap.paintTilemap(g);
               g.drawImage(
                  TileMap.bong, ((int) (GameScr.cmx + GameCanvas.hw)), ((int) (this.cy + this.dy + 1)), 3
               );
               if (this.mychar != null) {
                  this.mychar
                     .paintCharBody(
                        g,
                        GameScr.cmx + GameCanvas.hw,
                        this.cy + this.dy,
                        1,
                        this.f[this.count],
                        true
                     );
                  mFont.tahoma_7b_yellow
                     .drawString(
                        g,
                        this.mychar.cName,
                        GameScr.cmx + GameCanvas.hw,
                        this.cy - 15,
                        2,
                        mFont.tahoma_7_greySmall
                     );
               }

               g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
               super.paint(g);
               this.cmdSelectSv.paint(g);
            }
         }
      }
   }

   public final void update() {
      super.update();
      if (!Session_ME.gI().isConnected()) {
         isWait = true;
         this.count++;
         if (this.count > 50) {
            ServerListScreen.ConnectIP();
            this.count = 0;
         }
      } else {
         isWait = false;
         this.count++;
         if (this.count > this.f.length - 1) {
            this.count = 0;
         }

         if (this.cmdSelectSv != null && this.cmdSelectSv.isPointerPressInside()) {
            this.cmdSelectSv.performAction();
         }
      }
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 100:
            ServerListScreen.Login_New();
            return;
         case 101:
            ServerListScreen.isAutoLogin = false;
            GameCanvas.serverScreen.switchToMe();
            return;
         case 102:
            ServerListScreen.SetIpSelect(-1, true);
            ServerScr.isShowSv_HaveChar = false;
            Controller.isEXTRA_LINK = false;
            GameCanvas.serverScr.switchToMe();
      }
   }
}
