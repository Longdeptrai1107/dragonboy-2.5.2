package nro;

import main.GameCanvas;

public final class Waypoint implements IActionListener {
   public short minX;
   public short minY;
   public short maxX;
   public short maxY;
   public boolean isEnter;
   public boolean isOffline;
   public PopUp popup;

   public Waypoint(short minXValue, short minYValue, short maxXValue, short maxYValue, boolean isEnterValue, boolean isOfflineValue, String name) {
      this.minX = (short)minXValue;
      this.minY = minYValue;
      this.maxX = maxXValue;
      this.maxY = maxYValue;
      String info = Res.changeString(name);
      this.isEnter = isEnterValue;
      this.isOffline = isOfflineValue;
      if (TileMap.mapID != 21 && TileMap.mapID != 22 && TileMap.mapID != 23
         || this.minX < 0
         || this.minX > 24) {
         if ((TileMap.mapID != 0 || Char.myCharz().cgender == 0)
               && (TileMap.mapID != 7 || Char.myCharz().cgender == 1)
               && (TileMap.mapID != 14 || Char.myCharz().cgender == 2)
            || !isOfflineValue) {
            if (!TileMap.isInAirMap() && TileMap.mapID != 47) {
               if (!isEnterValue && !isOfflineValue) {
                  this.popup = new PopUp(info, minXValue, minYValue - 24);
                  this.popup.command = new Command(null, this, 1, this);
                  this.popup.isWayPoint = true;
                  this.popup.isPaint = false;
                  PopUp.addPopUp(this.popup);
               } else {
                  if (TileMap.isTrainingMap()) {
                     this.popup = new PopUp(info, minXValue, minYValue - 16);
                  } else {
                     minXValue += (maxXValue - minXValue) / 2;
                     this.popup = new PopUp(info, minXValue, minYValue - (minYValue != 0 ? 16 : -32));
                  }

                  this.popup.command = new Command(null, this, 2, this);
                  this.popup.isWayPoint = true;
                  this.popup.isPaint = false;
                  PopUp.addPopUp(this.popup);
               }

               TileMap.vGo.addElement(this);
            } else if (minYValue <= 150 || !TileMap.isInAirMap()) {
               this.popup = new PopUp(info, minXValue + (maxXValue - minXValue) / 2, maxYValue - (minXValue > 100 ? 24 : 48));
               this.popup.command = new Command(null, this, 1, this);
               this.popup.isWayPoint = true;
               this.popup.isPaint = false;
               PopUp.addPopUp(this.popup);
               TileMap.vGo.addElement(this);
            }
         }
      }
   }

   public final void perform(int idAction, Object p) {
      switch (idAction) {
         case 1:
            idAction = (this.minX + this.maxX) / 2;
            int yEnd = this.maxY;
            if (this.maxY > this.minY + 24) {
               yEnd = (this.minY + this.maxY) / 2;
            }

            GameScr.gI().auto = 0;
            Char.myCharz().currentMovePoint = new MovePoint(idAction, yEnd);
            Char.myCharz().cdir = Char.myCharz().cx - Char.myCharz().currentMovePoint.xEnd
                  > 0
               ? -1
               : 1;
            Service.gI().charMove();
            return;
         case 2:
            GameScr.gI().auto = 0;
            if (Char.myCharz().isInEnterOfflinePoint() != null) {
               Service.gI().charMove();
               InfoDlg.showWait();
               Service.gI().getMapOffline();
               Char.ischangingMap = true;
               return;
            } else if (Char.myCharz().isInEnterOnlinePoint() != null) {
               Service.gI().charMove();
               Service.gI().requestChangeMap();
               Char.isLockKey = true;
               Char.ischangingMap = true;
               GameCanvas.clearKeyHold();
               GameCanvas.clearKeyPressed();
               InfoDlg.showWait();
               return;
            } else {
               idAction = (this.minX + this.maxX) / 2;
               short yEnd2 = this.maxY;
               Char.myCharz().currentMovePoint = new MovePoint(idAction, yEnd2);
               Char.myCharz().cdir = Char.myCharz().cx
                        - Char.myCharz().currentMovePoint.xEnd
                     > 0
                  ? -1
                  : 1;
               Char.myCharz().endMovePointCommand = new Command(null, this, 2, null);
            }
      }
   }
}
