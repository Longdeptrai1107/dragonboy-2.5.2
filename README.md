# DragonBoy 2.5.2 — source Java ME

Source Java decompile từ file `Dragonboy 2.5.2.jar`. Mình chỉnh lại để project build được bằng ECJ, chạy được trên MicroEmulator và debug được trong Eclipse. Không có DLL, không có bản gốc, không kèm JDK.

Repo: https://github.com/Longdeptrai1107/dragonboy-2.5.2

## Thông tin nhanh

| Mục | Giá trị |
|---|---|
| Tên game | Ngọc Rồng Online — DragonBoy252 |
| Phiên bản | 2.5.2 (`intVERSION = 252`) |
| Nền tảng | Java ME, CLDC 1.1 / MIDP 2.0 |
| File Java | 157 file, 63.123 dòng |
| Resource | 488 file, khoảng 876 KB |
| Classfile | 45.3 (Java 1.1) |
| MIDlet chính | `main.GameMidlet` |
| Server mặc định | `112.213.94.23:14445` (khai báo trong `GameMidlet`) |
| Build tool | ECJ 3.33.0 + `toolchain/StandaloneJarBuild.java` |
| Runtime test | MicroEmulator 2.0.4 trên JDK 17+ |

## Yêu cầu

- JDK 17 trở lên. Script ưu tiên `toolchain/java`, sau đó `JAVA_HOME`, cuối cùng là `java` trong PATH.
- Windows: PowerShell 5.1+.
- Linux/macOS: dùng `java` trực tiếp, hoặc cài `pwsh` nếu muốn chạy script `.ps1`.

Repo không kèm JDK. Nếu máy chưa có, cài Temurin 17 rồi set `JAVA_HOME`, hoặc truyền `-JavaHome` khi build.

## Build

### Windows

```powershell
# Build release, output nằm trong dist/
powershell -NoProfile -ExecutionPolicy Bypass -File .\build.ps1

# Build kèm debug symbol, output nằm trong debug-dist/
powershell -NoProfile -ExecutionPolicy Bypass -File .\build.ps1 -DebugSymbols

# Chỉ định JDK cụ thể
.\build.ps1 -JavaHome "C:\Program Files\Eclipse Adoptium\jdk-17.0.21.1+2"
```

### Linux / macOS

```bash
# Release
java -Dfile.encoding=UTF-8 toolchain/StandaloneJarBuild.java . dist release

# Debug
java -Dfile.encoding=UTF-8 toolchain/StandaloneJarBuild.java . debug-dist debug
```

### Kết quả

```
dist/
├── DragonBoy252.jar          # JAR game
├── DragonBoy252.jad          # descriptor
├── build-report.json         # kết quả kiểm tra
├── compiler.log              # log ECJ
└── compiler.xml              # log chi tiết
```

`build-report.json` có dạng:

```json
{
  "passed": true,
  "compiler": "ECJ 3.33.0",
  "source_files": 157,
  "class_files": 157,
  "classfile_version": "45.3",
  "resources": 488,
  "sha256": "..."
}
```

Build sẽ dừng nếu:

- Hash của `ecj-3.33.0.jar`, `cldcapi11-2.0.4.jar` hoặc `midpapi20-2.0.4.jar` không khớp.
- Thiếu resource, resource sai hash, hoặc có resource lạ không nằm trong `config/resources.lock.json`.
- Số file Java khác 157, số class sinh ra khác 157, hoặc classfile không phải 45.3.
- JAR đọc lại bị sai CRC hoặc sai nội dung.

## Chạy và debug

```powershell
# Build rồi chạy
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1

# Build debug rồi chờ debugger ở cổng 5005
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 -Debug

# Đổi cổng
powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 -Debug -Port 5006
```

Trên Linux/macOS:

```bash
java -cp lib/microemulator-2.0.4.jar org.microemu.app.Main \
     --rms memory dist/DragonBoy252.jar
```

Debug bằng Eclipse:

1. File → Import → General → Existing Projects into Workspace, chọn thư mục repo, import project **DragonBoy252**.
2. Project → Build Project. Builder đã trỏ sẵn sang `eclipse-build.ps1`.
3. Đặt breakpoint ở `main.GameMidlet.startApp()` hoặc `nro.MotherCanvas`.
4. Chạy `run.ps1 -Debug`. JVM sẽ đứng chờ debugger.
5. Run → Debug Configurations → Remote Java Application, Host `127.0.0.1`, Port `5005`, bấm Debug rồi Resume (F8).
6. Khi MicroEmulator mở, chọn game và bấm Start.

`run.ps1 -Debug` dùng `suspend=y`, nên emulator chỉ chạy sau khi debugger attach. Nếu build bằng `-DebugSymbols`, biến cục bộ còn trong class; bản release chỉ giữ tên file và số dòng.

## Cấu trúc thư mục

```
DragonBoy252/
├── build.ps1                     # gọi eclipse-build.ps1
├── eclipse-build.ps1             # tìm JDK rồi chạy StandaloneJarBuild
├── run.ps1                       # build + chạy MicroEmulator, có -Debug
├── .project / .classpath         # project Eclipse
├── .settings/                    # cấu hình JDT
├── .externalToolBuilders/        # builder Eclipse
├── config/
│   ├── MANIFEST.MF               # manifest JAR/MIDlet
│   └── resources.lock.json       # 488 resource + size + sha256
├── lib/
│   ├── ecj-3.33.0.jar            # compiler
│   ├── cldcapi11-2.0.4.jar       # API compile-only
│   ├── midpapi20-2.0.4.jar       # API compile-only
│   └── microemulator-2.0.4.jar   # emulator
├── toolchain/
│   └── StandaloneJarBuild.java   # build tool, không cần dependency ngoài
├── src/main/java/
│   ├── main/                     # 4 file
│   └── nro/                      # 153 file
├── src/main/resources/           # 488 resource
├── SOURCE-CHECKSUMS.json         # hash 661 file source/meta
├── LICENSE.md
└── THIRD_PARTY_NOTICES.md
```

Resource chia theo thư mục:

```
src/main/resources/
├── icon.png
├── info
├── login_codes_generated.txt
├── myfont/          (4 file)
├── mymap/           (6 file)
└── x1/              (475 file)
    ├── bg/           93
    ├── e/            45
    ├── effectdata/   52
    ├── img/           7
    ├── mainImage/   143
    ├── mapBackGround/64
    ├── myfont/       26
    ├── radar/        24
    ├── SmallImage/    2
    └── t/            17
```

Ảnh trong `x1/` bị XOR với key `{-22, 2, 12, 4, 5, 2, -10}` và được giải mã lúc load bởi `ImageLoader`. Mở trực tiếp bằng trình xem ảnh sẽ ra file hỏng, đó là bình thường.

## Kiến trúc

### Khởi động

`GameMidlet.startApp()` làm 6 việc:

```java
if (!hasStarted) {
   (gameCanvas = new GameCanvas()).startCanvasThread();
   Session_ME.gI().setHandler(Controller.gI());
   Session_ME.gI2().setHandler(Controller.gI());
   Session_ME.gI2().isMainSession = false;
   SplashScr.loadImg();
   SplashScr.loadSplashScr();
   GameCanvas.currentScreen = new SplashScr();
   MotherCanvas.showGameCanvas(instance);
   hasStarted = true;
}
```

Thứ tự thực tế:

1. `new GameCanvas()` → `MotherCanvas` set fullscreen, tính `mGraphics.zoomLevel`, kiểm tra font tương ứng có tồn tại không.
2. `startCanvasThread()` → tạo thread chạy `MotherCanvas.run()`.
3. Gắn `Controller` làm handler cho cả session chính và session phụ.
4. Nạp ảnh splash, tạo `SplashScr`.
5. `Display.setCurrent(GameCanvas)` để bắt đầu nhận input và vẽ.
6. Thread canvas vào vòng lặp.

### Vòng lặp frame

`MotherCanvas.run()`:

```java
while (isCanvasThreadRunning) {
   long now = System.currentTimeMillis();
   update();
   repaint();
   serviceRepaints();
   long elapsed = System.currentTimeMillis() - now;
   if (elapsed < 27) Thread.sleep(27 - elapsed);
}
```

Mục tiêu khoảng 27 ms/frame, tức ~37 FPS. Nếu frame trước chạy lâu hơn 27 ms thì chỉ sleep 1 ms để không tụt thêm.

`GameCanvas.update()` xử lý theo thứ tự: dialog hiện tại → chat popup → `currentScreen.update()` → effect → info → `SoundMn.update()` → phím. `GameCanvas.paint()` vẽ nền trước, rồi `currentScreen.paint()`, rồi dialog/chat/info.

`mGraphics.zoomLevel` được chọn theo diện tích màn hình:

| Diện tích | Zoom |
|---|---|
| ≥ 2.073.600 px | 4× |
| ≥ 691.200 px | 3× |
| > 153.600 px | 2× |
| còn lại | 1× |

Sau đó game kiểm tra `/x{zoom}/myfont/tahoma_8b.png`. Nếu không có thì giảm zoom. Repo hiện chỉ có `x1`, nên thực tế luôn chạy zoom 1×.

### Màn hình

Mọi màn hình kế thừa `nro.mScreen`:

| Hàm | Việc |
|---|---|
| `switchToMe()` | Gán `GameCanvas.currentScreen = this` |
| `update()` | Logic mỗi frame |
| `updateKey()` | Xử lý phím giữ/nhả |
| `paint(mGraphics)` | Vẽ |
| `keyPress(int)` | Xử lý phím bấm |

Các màn hình chính: `SplashScr`, `ServerListScreen`, `ServerScr`, `LoginScr`, `RegisterScreen`, `SelectCharScr`, `ChooseCharScr`, `CreateCharScr`, `GameScr`, `RadarScr`, `CrackBallScr`, `TransportScr`, `ClientInput`, `MoneyCharge`. `Panel` là overlay mở trên `GameScr`.

### Mạng

Game dùng 2 session:

| Session | Lấy bằng | Vai trò |
|---|---|---|
| Chính | `Session_ME.gI()` | Đăng nhập, gameplay |
| Phụ | `Session_ME.gI2()` | Kênh phụ, tải dữ liệu |

Luồng gửi:

```
Service.xxx()
  → new Message(cmd), ghi payload bằng writer()
  → Session_ME.sendMessage()
  → Sender queue
  → Sender thread (10 ms/lần)
  → Socket TCP
```

Luồng nhận:

```
Socket TCP
  → MessageCollector thread
  → đọc command (1 byte)
  → đọc length (2 byte, big-endian)
  → đọc payload
  → giải mã XOR nếu đã handshake
  → new Message(cmd, data)
  → Controller.onMessage()
```

Định dạng frame:

```
+---------+-------------+------------------+
| command | length (2B) | payload (length) |
| 1 byte  | big-endian  | tùy command      |
+---------+-------------+------------------+
```

Trước handshake, dữ liệu gửi/đọc thô. Sau khi `getKeyComplete = true`, từng byte được XOR bằng keystream sinh từ `key[]` qua `readKey()` / `writeKey()`. Một số command không có trường length và được `MessageCollector` xử lý riêng: `-32, -66, 11, -67, -74, -87, 66, 12`.

Mỗi lần connect, `connectionGeneration` tăng lên. Các thread cũ (`Sender`, `MessageCollector`, connector) kiểm tra generation trước mỗi vòng nên tự thoát, không ghi nhầm vào session mới. `SessionHandshakeWatchdog` đóng socket nếu handshake quá lâu.

### Điều phối message

`Controller.onMessage()` chia theo command:

- Nhóm login/server list → `messageNotLogin()`.
- Nhóm ngoài map → `messageNotMap()`.
- Nhóm trong map → `messageSubCommand()`.
- Nhóm mở rộng → `Controller2`.

Mỗi nhánh đọc dữ liệu bằng `Message.reader()` theo đúng thứ tự server gửi. Các hàm đọc chính: `readCharInfo()`, `readItemOption()`, `readExtra()`, `loadInfoMap()`, `readFrameBoss()`, `read_cmdExtra()`.

### Render

```
GameCanvas.paint(Graphics)
  → new mGraphics(g)
  → paintBGGameScr()          # trời, mây, núi, biển
  → currentScreen.paint()     # tilemap → item → mob → char → effect → UI
  → dialog / chat / info
```

`mGraphics` nhân tọa độ với `zoomLevel`, giữ trạng thái `translate` và `clip`, vẽ ảnh/region/hình chữ nhật. `SmallImage` cache ảnh nhỏ theo ID. `FrameImage` quản lý sprite nhiều frame. `mFont` vẽ text từ atlas bitmap.

### Lưu trữ

`nro.Rms` bọc `RecordStore`:

| Hàm | Việc |
|---|---|
| `saveRMS(name, byte[])` / `loadRMS(name)` | Ghi/đọc mảng byte |
| `saveRMSInt` / `loadRMSInt` | Ghi/đọc int |
| `saveRMSString` / `loadRMSString` | Ghi/đọc chuỗi |
| `deleteRecord(name)` | Xóa một record |
| `clearAll()` | Xóa toàn bộ |

Dùng cho tài khoản, cấu hình âm thanh, vị trí skill, cache ảnh và tùy chọn đồ họa.

## Chi tiết các module chính

### main/GameMidlet.java

Điểm vào MIDlet.

| Hàm / field | Việc |
|---|---|
| `IP`, `PORT` | Server mặc định `112.213.94.23:14445` |
| `IP2`, `PORT2` | Server dự phòng |
| `intVERSION = 252` | Phiên bản protocol |
| `startApp()` | Khởi tạo canvas, session, splash, hiển thị game |
| `pauseApp()` / `destroyApp()` | Hook vòng đời, để trống |
| `exit()` | Dừng thread canvas, `System.gc()`, `notifyDestroyed()` |

### main/GameCanvas.java

Canvas chính, 2.869 dòng, implement `IActionListener`.

| Nhóm | Hàm |
|---|---|
| Singleton, kết nối | `gI()`, `connect()` |
| Vòng lặp | `update()`, `paint(Graphics)` |
| Nền | `paintBGGameScr()`, `paintBackgroundtLayer()`, `drawSun1/2()`, `loadBG()`, `resetBg()`, `updateBG()` |
| Input | `keyPressed()`, `keyReleased()`, `onPointerPressed/Dragged/Released()`, `isPointer()`, `isPointerHoldIn()` |
| Dialog | `startOKDlg()`, `startWaitDlg()`, `startYesNoDlg()`, `endDlg()` |
| Hiệu ứng | `startDust()`, `updateDust()`, `paintShukiren()` |
| Reset | `clearKeyPressed()`, `clearKeyHold()`, `clearAllPointerEvent()` |

`update()` gọi dialog, chat, screen, effect, info, âm thanh rồi xử lý phím. `paint()` vẽ nền theo lớp, sau đó screen hiện tại và overlay.

### main/GamePad.java

Joystick cảm ứng. Giữ tâm `(xC, yC)`, bán kính `R`, vector `(deltaX, deltaY)`, góc `angle`, vùng chạm `wZone/hZone`. Có 3 cỡ pad: nhỏ, vừa, lớn, chọn theo `GameCanvas.w`. Kết quả được dùng để set hướng di chuyển cho `Char`.

### main/LoginCodeResource.java

Đọc `/login_codes_generated.txt`, parse từng dòng thành cặp khóa/giá trị, cache trong `cachedCode`. Nếu thiếu file thì log qua `Res.err` và trả chuỗi rỗng, không crash.

### nro/Session_ME.java

Quản lý một phiên socket.

```java
public final synchronized void connect(String host, int port) {
   if (connected || connecting) return;
   closeCurrentGeneration();
   connecting = true;
   long generation = connectionGeneration;
   new Thread(new SessionConnector(this, host, port, generation)).start();
}
```

Các điểm chính:

- `connectionGeneration` tăng mỗi lần connect. Thread cũ tự dừng khi generation đổi.
- `isConnected()` kiểm tra `connected`, `connection`, `connection.connection`, `dis`.
- `sendMessage()` đẩy message vào `Sender`.
- `doSendMessage()` ghi command, length 2 byte, payload; nếu đã handshake thì XOR từng byte.
- `readKey()` / `writeKey()` sinh keystream từ `key[]`, quản lý `curR/curW`.
- `close()` / `disconnectAndResetClientInfo()` đóng stream, reset cờ, hủy watchdog và reset trạng thái login của `Service`.
- `failConnection()` báo lỗi qua handler rồi đóng tài nguyên.

### nro/Message.java

```java
Message m = new Message((byte) cmd);
m.writer().writeByte(x);
Session_ME.gI().sendMessage(m);

DataInputStream in = msg.reader();
int value = in.readInt();
```

Constructor `(byte)` tạo buffer ghi. Constructor `(byte, byte[])` tạo buffer đọc. `getData()` trả payload. `cleanup()` đóng stream.

### nro/Sender.java

Thread gửi. Chỉ chạy khi generation còn đúng và session còn connected. Chỉ gửi khi `getKeyComplete` đã true. Mỗi 10 ms gửi hết hàng đợi. Lỗi IO thì gọi `Session_ME.failConnection()`.

### nro/MessageCollector.java

Thread nhận:

1. Đọc command, giải mã nếu cần.
2. Nếu command thuộc nhóm không length thì xử lý riêng.
3. Đọc 2 byte length.
4. Đọc đủ payload, giải mã từng byte.
5. Tạo `Message` và gọi `messageHandler.onMessage()`.
6. Cộng `recvByteCount` để hiển thị KB.

### nro/Service.java

Tầng API gửi lệnh, hơn 200 hàm. Mỗi hàm dựng `Message`, ghi tham số rồi gửi. Ví dụ:

```java
public final void login(String user, String pass, String version, byte type) {
   Message m = new Message((byte) cmd);
   m.writer().writeUTF(user);
   m.writer().writeUTF(pass);
   // ...
   Session_ME.gI().sendMessage(m);
}
```

Nhóm chức năng: đăng nhập/đăng ký, chuyển map/khu, chat, mua/bán, skill, giao dịch, clan, bạn bè, pet, nạp thẻ, nhiệm vụ, top, phụ bản.

### nro/GameScr.java

Màn chơi, 6.445 dòng.

| Nhóm | Hàm |
|---|---|
| Vòng đời | `switchToMe()`, `update()`, `paint()`, `keyPress()`, `updateKey()` |
| Camera | `loadCamera()`, `resetTranslate()`, `cmx/cmy/cmtoX/cmtoY` |
| Skill | `doSelectSkill()`, `doUseSkill()`, `doUseSkillNotFocus()`, `sortSkill()`, `onOSkill/onKSkill/onCSkill` |
| Combat | `doFire()`, `pickItem()`, `isMeCanAttackMob()`, `activeSuperPower()`, `callRongThan()` |
| Effect | `addEffectEnd()`, `addEffectEnd_Target()`, `startFlyText()`, `startSplash()` |
| UI | `paintInfoBar()`, `paintImageBar()`, `paintSelectedSkill()`, `paintPhuBanBar()` |
| Tìm object | `findNPCInMap()`, `findCharInMap()`, `findMobInMap()`, `getNpcTask()` |
| Chat | `onChatFromMe()`, `onCancelChat()`, `chatVip()` |
| PvP | `player_vs_player()`, `starVS()`, `isVS()` |

`update()` xử lý auto-play nếu bật, hồi chiêu, camera, char/mob/item/effect, input, va chạm rồi UI. `paint()` vẽ bg item, tilemap, item rơi, mob, char, effect, info bar, skill bar, popup.

### nro/Char.java

Model và logic nhân vật, 6.859 dòng.

```java
public String cName;
public int cClass;          // 0 Trái Đất, 1 Namek, 2 Xayda
public long cHP, cMP;
public long cHPFull, cMPFull;
public long cPower;
public int cx, cy;
public byte statusMe;
public Skill[] skills;
public Item[] arrItemBag;
```

Nhóm hàm: di chuyển (`moveTo`, `update`), chiến đấu (`doInjure`, `isAttacK`, `skillInfoPaint`), render (`paint`, `paintShadow`, `paintHead`), biến hình (`setMabu`, `setCosmetic`), pet (`pet`, `petFollow`), chat (`chat`, `addInfo`).

### nro/Panel.java

Bảng điều khiển, 9.866 dòng, file lớn nhất. Mỗi tab là một type:

| Tab | Hàm set |
|---|---|
| Hành trang / thân thể | `setTypeBodyOnly()`, `setTabInventory()` |
| Skill | `setTabSkill()` |
| Nhiệm vụ | `setTypeMain()`, `paintTask()` |
| Map / khu | `setTypeZone()`, `setTabMap()` |
| Shop / rương | `setTypeShop()`, `setTypeBox()` |
| Ghép đá | `setTypeCombine()`, `setTabCombine()` |
| Clan | `initTabClans()`, `setTabClans()` |
| Bạn bè / kẻ thù | `setTypeFriend()`, `setTypeEnemy()` |
| Top / thành tựu | `setTypeTop()`, `setTypeArchivement()` |
| Giao dịch | `setTypeGiaoDich()`, `paintGiaoDich()` |
| Pet | `setTypePetMain()`, `paintPetStatus()` |
| Nạp tiền | `paintPaymentPackages()` |

`paint()` chọn hàm `paintX()` theo type. `updateKey()` xử lý phím cuộn/chọn.

### Nhóm mob và boss

- `Mob`: quái thường, máu, trạng thái, di chuyển, tấn công, render theo frame.
- `Npc`: NPC, kế thừa `Char`, có hội thoại/menu/nhiệm vụ.
- `BigBoss`, `BigBoss2`, `NewBoss`: boss nhiều giai đoạn, kỹ năng riêng, thanh máu riêng.
- `BachTuoc`: boss bạch tuộc, animation xúc tu.
- `Mabu`: nhân vật Mabu.
- `MobCapcha`: mob captcha chống bot.

### Nhóm hiệu ứng

`Effect_End` (1.536 dòng) xử lý animation chiêu thức theo `EffectData`: mỗi effect có frame, thời gian, vị trí, hướng. `update()` tăng thời gian, `paint()` chọn frame, hết thời gian thì xóa. `EffectManager` và `EffecMn` giữ danh sách effect đang chạy. `Effect` là hiệu ứng đơn giản (hạt, khói, nổ nhỏ). `BackgroudEffect` lo mưa, tuyết, lá rơi. `Teleport` vẽ hố dịch chuyển. `PlayerDart`, `MonsterDart`, `SmallDart`, `Arrow` là đạn/phi tiêu.

### Nhóm map

`TileMap.loadMap(id)` đọc dữ liệu map, dựng 3 lớp tile, load ảnh từ `x1/t/`. `paintTilemap()` vẽ theo camera, `paintOutTilemap()` vẽ lớp phủ. `tileTypeAt()`, `setTileTypeAtPixel()`, `killTileTypeAt()` cho phép đọc/sửa tile runtime. `BgItem` là vật trang trí nền. `Waypoint` là cổng chuyển map. `MovePoint` là điểm di chuyển.

### Nhóm đồ họa

| File | Việc |
|---|---|
| `mGraphics` | Wrapper Graphics, zoom, translate, clip, blend màu |
| `mFont` | Font bitmap, đo text, vẽ viền |
| `Paint` | Tiện ích vẽ gradient, khung, icon |
| `SmallImage` | Cache và vẽ ảnh nhỏ theo ID |
| `ImageLoader` | Giải mã XOR ảnh PNG |
| `ImgByName` | Cache ảnh theo tên, lưu RMS |
| `ImageSource` | Đồng bộ danh sách ảnh với server |
| `FrameImage` | Sprite nhiều frame |
| `Small`, `MainImage` | Một frame ảnh nhỏ / ảnh lớn nhiều frame |
| `Part`, `PartImage` | Bộ phận nhân vật (đầu, thân, chân) |
| `ClanImage` | Icon clan |

`SmallImage` có `init()`, `readImage()`, `createImage(id)`, `drawSmallImage()`, `update()` để giải phóng cache, và các hàm kiểm tra ảnh lỗi (`hasAtlasImage`, `getValidatedCachedImage`).

### Nhóm UI

`Command` là đơn vị tương tác: `performAction()` gọi `IActionListener.perform(id, data)`. Hầu hết màn hình implement `IActionListener` để nhận action. `Menu` vẽ menu dọc/ngang. `ChatTextField` là ô chat, `ChatPopup` là bong bóng chat trên đầu nhân vật. `TField` là text field. `Scroll` là scroll view. `Dialog`, `MsgDlg`, `InputDlg`, `PopUp`, `PopUpYesNo` là các loại hộp thoại. `Info`, `InfoMe`, `InfoDlg`, `InfoItem`, `InfoPhuBan` hiển thị thông tin.

### Nhóm model

Các class dữ liệu thuần, thường chỉ có field và constructor, dùng để parse từ server: `Item`, `ItemTemplate`, `ItemTemplates`, `ItemOption`, `ItemOptionTemplate`, `ItemObject`, `ItemTime`, `Skill`, `SkillTemplate`, `Skills`, `SkillOptionTemplate`, `Task`, `TaskOrder`, `Clan`, `ClanObject`, `Member`, `ClanMessage`, `NClass`, `PKFlag`, `Point`, `Line`, `DartInfo`, `MobTemplate`, `NpcTemplate`, `MapTemplate`, `ImageInfo`, `PartImage`, `Frame`, `TopInfo`, `Archivement`, `GameInfo`, `BallInfo`, `ScrollResult`, `Position`, `PlayerData`, `StaticObj`.

Logic không nằm trong các class này mà ở `Controller`, `Service`, `GameScr`, `Panel`.

### Nhóm tiện ích

| File | Việc |
|---|---|
| `Res` | Bảng sin/cos, random, toán, log |
| `mSystem` | Thời gian, nạp ảnh, hotline, banner |
| `mResources` | Chuỗi giao diện tiếng Việt |
| `T2` | Bộ chuỗi tiếng Anh, kế thừa `mResources` |
| `Rms` | Bọc RecordStore |
| `MyVector` | Vector có tên |
| `MyHashTable` | Hashtable có tên |
| `SoundMn` | Âm thanh và hiệu ứng |
| `StaticObj` | Hằng số dùng chung |
| `PaymentCatalog` | Danh mục gói nạp |
| `PostLoginRequestHook` | Hook rỗng sau khi gửi login |

### Nhóm màn hình khởi động

| File | Luồng |
|---|---|
| `SplashScr` | Logo, loading, chuyển sang server list hoặc login |
| `ServerListScreen` | Danh sách server, ping, tải resource |
| `ServerScr` | Lưới chọn server |
| `LoginScr` | Form đăng nhập |
| `RegisterScreen` | Form đăng ký |
| `SelectCharScr` | Chọn nhân vật |
| `ChooseCharScr` | Chọn class/hành tinh |
| `CreateCharScr` | Tạo nhân vật |
| `TransportScr` | Màn tàu/thuyền |
| `MoneyCharge` | Nạp tiền/ngọc |
| `ClientInput` | Nhập dữ liệu xác thực/captcha |
| `CrackBallScr` | Minigame đập bóng |
| `CrackBallAnimationTask` | Animation cho minigame |
| `RadarScr` | Radar |
| `Info_RadaScr` | Thông tin mục tiêu radar |

## Danh mục 157 file Java

Cột "Hàm chính" lấy từ class đã compile, rút gọn kiểu dữ liệu và bỏ constructor. Class dữ liệu thuần sẽ không có hàm nào ngoài constructor.

### main/ — 4 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `GameCanvas.java` | 2869 | MotherCanvas | Canvas chính: vòng lặp frame, input, vẽ nền, dialog, hiệu ứng bụi. | `gI()`, `connect()`, `resetTrans(mGraphics)`, `resetTransGameScr(mGraphics)`, `debug()`, `update()`, `doResetToLoginScr()` |
| `GamePad.java` | 278 | — | Joystick cảm ứng: tâm, bán kính, vector, vùng chạm, 3 cỡ pad. | `update()`, `paint(mGraphics)`, `disableCheckDrag()`, `disableClickMove()` |
| `LoginCodeResource.java` | 87 | — | Đọc và cache mã đăng nhập từ login_codes_generated.txt. | `getCode()`, `parseCodeLine(String)` |
| `GameMidlet.java` | 51 | MIDlet | MIDlet entry: khởi tạo canvas, session, splash rồi hiển thị game. | `destroyApp(boolean)`, `pauseApp()`, `startApp()`, `exit()` |

### Mạng và phiên — 13 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `Session_ME.java` | 346 | — | Một phiên socket: connect, gửi/nhận, generation, đóng, watchdog. | `gI()`, `gI2()`, `clearSendingMessage()`, `isConnected()`, `getConnectionGeneration()`, `setHandler(IMessageHandler)`, `connect(String, int)` |
| `SessionConnector.java` | 93 | — | Thread mở socket, bắt tay, khởi động collector. | `run()` |
| `MessageCollector.java` | 184 | — | Thread đọc frame, giải mã XOR, tạo Message, đẩy cho handler. | `run()` |
| `Sender.java` | 49 | — | Thread gửi message từ hàng đợi, 10 ms mỗi vòng. | `AddMessage(Message)`, `run()`, `getSendingMessage(Sender)` |
| `SessionHandshakeWatchdog.java` | 25 | — | Hết hạn handshake thì đóng phiên. | `run()` |
| `Message.java` | 61 | — | Đóng gói command + payload, cấp reader/writer. | `getData()`, `reader()`, `writer()`, `cleanup()` |
| `SocketConnectionHandle.java` | 19 | — | Bọc StreamConnection và Connection. | — |
| `ISession.java` | 6 | — | Interface phiên mạng. | `sendMessage(Message)` |
| `IMessageHandler.java` | 12 | — | Interface nhận sự kiện message và kết nối. | `onMessage(Message)`, `onConnectionFail(boolean)`, `onDisconnected(boolean)`, `onConnectOK(boolean)` |
| `Service.java` | 2063 | — | Tầng gửi lệnh: dựng Message cho từng chức năng. | `gI()`, `gotoPlayer(int)`, `onPrimaryClientInfoSent()`, `charInfo(String, String, String, String, String, String, String, String, String)`, `onSecondaryClientInfoSent()`, `combine(byte, MyVector)`, `giaodich(byte, int, byte, int)` |
| `PostLoginRequestHook.java` | 9 | — | Hook rỗng sau khi login được xếp hàng gửi. | `afterLoginRequestQueued()` |
| `Controller.java` | 5713 | — | Nhận message, chia nhánh login/ngoài map/trong map, dựng object. | `gI()`, `onConnectOK(boolean)`, `onConnectionFail(boolean)`, `onDisconnected(boolean)`, `requestItemPlayer(Message)`, `onMessage(Message)`, `createImage(byte[])` |
| `Controller2.java` | 1186 | — | Xử lý nhóm opcode mở rộng và dữ liệu phụ. | `readMessage(Message)`, `readLuckyRound(Message)`, `readActionBoss(Message, int)` |

### Màn hình — 17 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `MotherCanvas.java` | 129 | Canvas | Canvas gốc: fullscreen, chọn zoom, thread 27 ms, chia tọa độ chạm. | `showGameCanvas(GameMidlet)`, `startCanvasThread()`, `getPlatformName()`, `getHeight()`, `getWidth()`, `getWidthz()`, `getHeightz()` |
| `mScreen.java` | 155 | — | Base màn hình: switchToMe, update, updateKey, paint. | `switchToMe()`, `keyPress(int)`, `update()`, `updateKey()`, `getCmdPointerLast(Command)`, `paint(mGraphics)` |
| `SplashScr.java` | 110 | mScreen | Logo, nạp ảnh, đọc IP, chuyển màn hình đầu. | `loadSplashScr()`, `update()`, `loadIP()`, `paint(mGraphics)`, `loadImg()` |
| `ServerListScreen.java` | 1311 | mScreen | Danh sách server, ping, tải resource, chọn server. | `createDeleteRMS()`, `initCommand()`, `doUpdateServer()`, `getServerList(String)`, `paint(mGraphics)`, `update()`, `processInput()` |
| `ServerScr.java` | 664 | mScreen | Lưới chọn server. | `switchToMe()`, `sort()`, `update()`, `paint(mGraphics)`, `updateKey()`, `perform(int, Object)`, `SetNewSelectMenu(int, int)` |
| `LoginScr.java` | 746 | mScreen | Form đăng nhập tài khoản. | `switchToMe()`, `doLogin()`, `savePass()`, `update()`, `keyPress(int)`, `paint(mGraphics)`, `updateKey()` |
| `RegisterScreen.java` | 574 | mScreen | Form đăng ký tài khoản. | `switchToMe()`, `update()`, `keyPress(int)`, `paint(mGraphics)`, `updateKey()`, `perform(int, Object)` |
| `SelectCharScr.java` | 241 | mScreen | Chọn nhân vật. | `loadMapTableFromResource(byte[])`, `SetInfoChar(Char)`, `switchToMe()`, `paint(mGraphics)`, `update()`, `perform(int, Object)` |
| `ChooseCharScr.java` | 188 | mScreen | Chọn class/hành tinh khi tạo nhân vật. | `switchToMe()`, `update()`, `paint(mGraphics)`, `updateChooseCharacter(byte)`, `perform(int, Object)` |
| `CreateCharScr.java` | 634 | mScreen | Tạo nhân vật: tên, class, ngoại hình. | `gI()`, `switchToMe()`, `doChangeMap()`, `keyPress(int)`, `update()`, `updateKey()`, `paint(mGraphics)` |
| `TransportScr.java` | 169 | mScreen | Màn tàu/thuyền di chuyển. | `gI()`, `switchToMe()`, `paint(mGraphics)`, `update()`, `updateKey()`, `perform(int, Object)` |
| `MoneyCharge.java` | 197 | mScreen | Nạp tiền/ngọc. | `gI()`, `switchToMe()`, `paint(mGraphics)`, `update()`, `keyPress(int)`, `updateKey()`, `perform(int, Object)` |
| `ClientInput.java` | 199 | mScreen | Nhập dữ liệu xác thực/captcha từ client. | `gI()`, `switchToMe()`, `setInput(int, String)`, `paint(mGraphics)`, `update()`, `keyPress(int)`, `updateKey()` |
| `CrackBallScr.java` | 670 | mScreen | Minigame đập bóng. | `gI()`, `SetCrackBallScr(short[], byte, int, short)`, `perform(int, Object)`, `update()`, `updateKey()`, `doClickBall(int)`, `doClickSkill(int)` |
| `CrackBallAnimationTask.java` | 58 | — | Task animation cho minigame đập bóng. | `run()` |
| `RadarScr.java` | 600 | mScreen | Màn radar. | `gI()`, `SetRadarScr(MyVector, int, int)`, `SetNum(int, int)`, `SetListUse()`, `listIndex()`, `update()`, `updateKey()` |
| `Info_RadaScr.java` | 177 | — | Thông tin mục tiêu trên radar. | `SetInfo(int, int, int, byte, byte, short, String, String, Char, ItemOption[])`, `SetAmount(byte, byte)`, `SetLevel(byte)`, `SetUse(byte)`, `GetInfo(MyVector, int)`, `addItemDetail()`, `paintEff(mGraphics, int, int)` |

### Gameplay và thực thể — 25 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `GameScr.java` | 6445 | mScreen | Màn chơi: camera, combat, skill, chat, PvP, effect, UI. | `resetClientDataReadiness()`, `readPart()`, `paintOngMauPercent(Image, Image, Image, float, float, int, float, mGraphics)`, `isMapDocNhan()`, `isMapFize()`, `switchToMe()`, `resetAllvector()` |
| `Char.java` | 6859 | — | Nhân vật: thuộc tính, di chuyển, chiến đấu, render. | `applyCharLevelPercent()`, `getdxSkill()`, `getdySkill()`, `taskAction(boolean)`, `getStrLevel()`, `avatarz()`, `setPowerInfo(String, short, short, short)` |
| `Mob.java` | 1263 | — | Quái thường: máu, trạng thái, di chuyển, tấn công. | `isBigBoss()`, `getData()`, `setBody(short)`, `clearBody()`, `isExistNewMob(String)`, `checkData()`, `checkFrameTick(int[])` |
| `Npc.java` | 574 | Char | NPC, kế thừa Char, có hội thoại và menu. | `setStatus(byte, int)`, `clearEffTask()`, `update()`, `paint(mGraphics)`, `paintName(mGraphics)`, `hide()` |
| `BigBoss.java` | 534 | Mob | Boss lớn nhiều giai đoạn. | `getDataB2()`, `setBody(short)`, `clearBody()`, `checkFrameTick(int[])`, `updateSuperEff()`, `update()`, `setInjure()` |
| `BigBoss2.java` | 460 | Mob | Boss lớn thứ hai, kỹ năng riêng. | `setBody(short)`, `clearBody()`, `checkFrameTick(int[])`, `updateSuperEff()`, `update()`, `setInjure()`, `setAttack(Char)` |
| `NewBoss.java` | 489 | Mob | Boss kiểu mới, thanh máu và animation riêng. | `setBody(short)`, `clearBody()`, `checkFrameTick(int[])`, `updateSuperEff()`, `update()`, `setAttack(Char)`, `setFly()` |
| `BachTuoc.java` | 405 | Mob | Boss bạch tuộc, animation xúc tu. | `setBody(short)`, `clearBody()`, `checkFrameTick(int[])`, `updateSuperEff()`, `update()`, `setInjure()`, `setAttack(Char)` |
| `Mabu.java` | 184 | Char | Nhân vật Mabu. | `eat(int)`, `checkFrameTick(int[])`, `setSkill(byte, short, short, Char[], long[])`, `update()`, `paint(mGraphics)` |
| `MagicTree.java` | 200 | Npc | Cây thần, menu và tương tác. | `paint(mGraphics)`, `update()`, `perform(int, Object)` |
| `ItemMap.java` | 245 | — | Item rơi trên map. | `setPoint(int, int)`, `update()`, `paint(mGraphics)`, `isAuraItem()`, `setDotPosition()`, `getX()`, `getY()` |
| `PetFollow.java` | 71 | — | Đệ tử đi theo nhân vật. | `SetImg(int, int[], int, int)`, `update()`, `remove()` |
| `PlayerDart.java` | 200 | — | Đạn/phi tiêu của người chơi. | `update()`, `endMe()`, `paint(mGraphics)` |
| `MonsterDart.java` | 179 | Effect2 | Đạn của quái. | `addMonsterDart(int, int, boolean, long, long, Char, int)`, `update()`, `findDirIndexFromAngle(int)`, `paint(mGraphics)` |
| `SmallDart.java` | 22 | — | Đạn nhỏ. | `openResourceStream(String)` |
| `Arrow.java` | 134 | — | Mũi tên. | `update()`, `endMe()`, `paint(mGraphics)` |
| `Arrowpaint.java` | 13 | — | Dữ liệu vẽ mũi tên. | — |
| `MobCapcha.java` | 97 | — | Mob captcha chống bot. | `paint(mGraphics, int, int)` |
| `Waypoint.java` | 111 | — | Cổng chuyển map. | `perform(int, Object)` |
| `MovePoint.java` | 19 | — | Điểm di chuyển. | — |
| `BgItem.java` | 147 | — | Vật trang trí nền: cây, đá, nhà. | `clearHashTable()`, `isExistKeyNews(String)`, `isMiniBg()`, `paint(mGraphics)`, `changeColor()` |
| `BackgroudEffect.java` | 936 | — | Mưa, tuyết, lá rơi và hiệu ứng nền. | `clearImage()`, `isHaveRain()`, `getCachedLeafImage()`, `initCloud()`, `paintCloud2(mGraphics)`, `paintFog(mGraphics)`, `reloadShip()` |
| `TileMap.java` | 471 | — | Load map, 3 lớp tile, vật cản, nước. | `isVoDaiMap()`, `isTrainingMap()`, `mapPhuBang()`, `getBIById(int)`, `isOfflineMap()`, `isExistMoreOne(int)`, `loadTileImage()` |
| `MapTemplate.java` | 12 | — | Metadata map. | — |
| `Teleport.java` | 398 | — | Hố/ánh sáng dịch chuyển và animation. | `addTeleport(Teleport)`, `paintHole(mGraphics)`, `paint(mGraphics)`, `update()` |

### Hiệu ứng — 18 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `Effect.java` | 447 | — | Hiệu ứng đơn giản: hạt, khói, nổ nhỏ. | `GetCharEff(Char)`, `removeEffData(int)`, `getEffDataById(int)`, `isExistNewEff(String)`, `isPaintz()`, `paintUnderBackground(mGraphics, int, int)`, `paint(mGraphics)` |
| `Effect2.java` | 15 | — | Base class cho effect có vòng đời. | `update()`, `paint(mGraphics)` |
| `Effect_End.java` | 1536 | — | Animation chiêu thức/kết thúc theo EffectData. | `getImage(int)`, `create_Effect()`, `update()`, `paint(mGraphics)`, `set_FireWork()`, `set_Skill_Destroy()`, `create_Star_Line_In(int, int, int, int)` |
| `EffectData.java` | 392 | — | Dữ liệu effect đọc từ effectdata/. | `readData(String)`, `readData2(String)`, `readEffect(java.io.DataInputStream)`, `readData(java.io.DataInputStream)`, `readDataNewBoss(byte[], byte)`, `readData(byte[])`, `paintFrame(mGraphics, int, int, int, int, int)` |
| `EffectManager.java` | 74 | MyVector | Hàng đợi effect toàn cục. | `updateAll()`, `update()`, `paintAll(mGraphics)`, `removeAll()`, `remove()`, `addHiEffect(Effect_End)`, `addMidEffects(Effect_End)` |
| `EffecMn.java` | 73 | — | Danh sách effect đang chạy. | `addEff(Effect)`, `removeEff(int)`, `getEffById(int)`, `paintBackGroundUnderLayer(mGraphics, int, int, int)`, `paintLayer1(mGraphics)`, `paintLayer2(mGraphics)`, `paintLayer3(mGraphics)` |
| `EffectPanel.java` | 55 | Effect2 | Effect hiển thị trong panel. | `addServerEffect(int, int, int, int)`, `paint(mGraphics)`, `update()` |
| `ServerEffect.java` | 107 | Effect2 | Effect do server kích hoạt. | `addServerEffect(int, int, int, int)`, `addServerEffect(int, int, int, int, int)`, `addServerEffect(int, Mob, int)`, `addServerEffect(int, Char, int)`, `paint(mGraphics)`, `update()` |
| `Firework.java` | 224 | — | Pháo hoa. | `Drawline(mGraphics, int, int, int)`, `readMessageBytes(Message)`, `readLengthPrefixedBytes(java.io.DataInputStream)`, `replaceAllLiteral(String, String, String)`, `getDate(int)`, `formatCountdownDuration(int)`, `getMoneys(long)` |
| `FireWorkEff.java` | 144 | — | Hiệu ứng pháo hoa. | `paint(mGraphics)`, `add()` |
| `SkillPaint.java` | 9 | — | Dữ liệu vẽ chiêu thức. | — |
| `SkillInfoPaint.java` | 18 | — | Thông tin frame của chiêu. | — |
| `EffectInfoPaint.java` | 8 | — | Thông tin frame của effect. | — |
| `EffectTemplate.java` | 5 | — | Template effect. | — |
| `DartInfo.java` | 14 | — | Thông tin đạn/phi tiêu. | — |
| `EffectChar.java` | 15 | — | Effect gắn vào nhân vật. | — |
| `EffectCharPaint.java` | 6 | — | Dữ liệu vẽ effect nhân vật. | — |
| `EffectPaint.java` | 14 | — | Dữ liệu frame để vẽ effect. | `getImgId()` |

### Đồ họa và tài nguyên — 13 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `mGraphics.java` | 194 | — | Wrapper Graphics: zoom, translate, clip, blend màu. | `fillArg(int, int, int, int, int, int)`, `drawImage(Image, int, int, int)`, `drawImage(Image, float, float, int)`, `drawLine(int, int, int, int)`, `fillRect(int, int, int, int, int)`, `fillRectDelegated(int, int, int, int)`, `drawRect(int, int, int, int)` |
| `mFont.java` | 255 | — | Font bitmap, đo text, vẽ viền. | `getHeight()`, `getWidth(String)`, `drawString(mGraphics, String, int, int, int)`, `drawString(mGraphics, String, int, int, int, mFont)`, `splitFontArray(String, int)`, `drawStringBorder(mGraphics, String, int, int, int)`, `drawStringBorder(mGraphics, String, int, int, int, mFont)` |
| `Paint.java` | 136 | — | Tiện ích vẽ gradient, khung, icon. | `paintCmdBar(mGraphics, Command, Command, Command)`, `paintTabSoft()`, `paintInputDlg(mGraphics, int, int, int, int, String[])`, `paintPopUp(int, int, int, int, mGraphics)`, `paintFrame(int, int, int, int, mGraphics)`, `paintFrameSimple(int, int, int, int, mGraphics)` |
| `SmallImage.java` | 379 | — | Cache và vẽ ảnh nhỏ theo ID. | `loadBigRMS()`, `loadBigImage()`, `init()`, `readImage()`, `clearHastable()`, `hasAtlasImage(int)`, `getValidatedCachedImage(int)` |
| `ImageLoader.java` | 51 | — | Giải mã XOR ảnh PNG từ resource. | `loadImage(String)` |
| `ImgByName.java` | 94 | — | Cache ảnh theo tên, lưu RMS. | `SetImage(String, Image, byte)`, `getImagePath(String, MyHashTable)`, `getFromRms(String)`, `saveRMS(String, byte, byte[])`, `checkDelHash(MyHashTable, int)` |
| `ImageSource.java` | 70 | — | Đồng bộ danh sách ảnh với server. | `checkRMS()`, `saveRMS()` |
| `FrameImage.java` | 52 | — | Sprite nhiều frame. | `drawFrame(int, int, int, int, int, mGraphics)` |
| `Small.java` | 68 | — | Một frame ảnh nhỏ. | `paint(mGraphics, int, int, int, int)`, `paint(mGraphics, int, int, int, int, int, int, int)` |
| `MainImage.java` | 22 | — | Ảnh lớn nhiều frame. | — |
| `Part.java` | 24 | — | Bộ phận nhân vật. | — |
| `PartImage.java` | 8 | — | Ảnh của một bộ phận. | — |
| `ClanImage.java` | 38 | — | Icon clan. | `addClanImage(ClanImage)`, `getClanImage(short)`, `isExistClanImage(int)` |

### UI và nhập liệu — 22 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `Command.java` | 210 | — | Nút lệnh: label, action, vị trí. | `performAction()`, `setType()`, `paint(mGraphics)`, `paintOngMau(Image, Image, Image, int, int, int, mGraphics)`, `isPointerPressInside()`, `isPointerPressInsideCamera(int, int)` |
| `Menu.java` | 483 | — | Menu dọc/ngang. | `startWithoutCloseButton(MyVector, int)`, `startAt(MyVector, int, int)`, `startAt(MyVector)`, `isScrolling()`, `updateMenuKey()`, `paintMenu(mGraphics)`, `updateMenu()` |
| `ChatTextField.java` | 163 | — | Ô chat và gửi. | `initChatTextField()`, `keyPressed(int)`, `gI()`, `startChat(int, IChatable, String)`, `startChat(String)`, `update()`, `paint(mGraphics)` |
| `ChatPopup.java` | 805 | Effect2 | Bong bóng chat trên đầu nhân vật. | `clearAllChatPopups()`, `removeChatPopupsFrom(MyVector)`, `addNextPopUpMultiLine(String, Npc)`, `addBigMessage(String, int, Npc)`, `addChatPopupMultiLine(String, int, Npc)`, `addChatPopup(String, int, Npc)`, `addChatPopupWithIcon(String, int, Npc, int)` |
| `TField.java` | 497 | — | Text field nhập liệu. | `openTouchKeyboard()`, `doChangeToTextBox()`, `clear()`, `setOffset()`, `keyPressedAscii(int)`, `keyPressed(int)`, `paint(mGraphics)` |
| `TextBoxCommandListener.java` | 29 | — | Cầu nối TextBox hệ thống. | `commandAction(Command, Displayable)` |
| `Scroll.java` | 364 | — | Thanh cuộn và scroll view. | `updateKey()`, `updatecm()`, `setStyle(int, int, int, int, int, int, boolean, int)`, `moveTo(int)` |
| `Dialog.java` | 90 | — | Base dialog. | `paint(mGraphics)`, `keyPress(int)`, `update()` |
| `MsgDlg.java` | 103 | Dialog | Dialog thông báo. | `pleasewait()`, `show()`, `setInfo(String)`, `setInfo(String, Command, Command, Command)`, `paint(mGraphics)`, `update()` |
| `InputDlg.java` | 57 | Dialog | Dialog nhập liệu. | `show(String, Command, int)`, `paint(mGraphics)`, `keyPress(int)`, `update()` |
| `PopUp.java` | 295 | — | Popup thông tin. | `loadBg()`, `updateXYWH(String[], int, int)`, `addPopUp(PopUp)`, `paintPopUp(mGraphics, int, int, int, int, int, boolean)`, `doClick(int)`, `paintAll(mGraphics)`, `update()` |
| `PopUpYesNo.java` | 121 | — | Popup xác nhận có/không. | `setPopUp(String, Command, Command)`, `paint(mGraphics)`, `update()`, `perform(int, Object)` |
| `Info.java` | 302 | — | Hiển thị thông tin. | `hide()`, `paint(mGraphics, int, int, int)`, `update()`, `getInfo()`, `addInfo(String, int, Char, boolean)`, `perform(int, Object)` |
| `InfoMe.java` | 284 | — | Thông tin của người chơi. | `paint(mGraphics)`, `update()`, `addInfoWithChar(String, Char, boolean)`, `addInfo(String, int)` |
| `InfoDlg.java` | 59 | — | Dialog info. | `show(String, String, int)`, `showWait()`, `paint(mGraphics)`, `update()`, `hide()` |
| `InfoItem.java` | 19 | — | Thông tin item. | — |
| `InfoPhuBan.java` | 46 | — | Thông tin phụ bản. | `updateLife(int, int, int)` |
| `Hint.java` | 121 | — | Gợi ý. | `isOnTask(int, int)`, `clickNpc()`, `nextMap(int)`, `clickMob()`, `isHaveItem()` |
| `TabClanIcon.java` | 154 | — | Icon tab clan. | `show(boolean)`, `hide()`, `paintIcon(mGraphics)`, `perform(int, Object)` |
| `ListNew.java` | 162 | — | Danh sách tin tức. | `update_Pos_UP_DOWN()`, `moveCamera()` |
| `TextInfo.java` | 61 | — | Vẽ text info. | `reset()`, `paint(mGraphics, String, int, int, int, int, mFont)` |
| `Panel.java` | 9866 | — | Bảng điều khiển nhiều tab: hành trang, skill, shop, clan, top... | `init()`, `getXMap()`, `getYMap()`, `getXMapTask()`, `getYMapTask()`, `setType(int)`, `setTypeMapTrans()` |

### Model dữ liệu — 33 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `Item.java` | 85 | — | Item trong hành trang/rương. | `getCompare()`, `isHaveOption(int)`, `isTypeBody()`, `setPartTemp(int, int, int, int)` |
| `ItemTemplate.java` | 28 | — | Template item. | — |
| `ItemTemplates.java` | 14 | — | Danh sách template item. | `add(ItemTemplate)`, `get(short)` |
| `ItemOption.java` | 34 | — | Option của item. | `getOptionString()`, `getOptiongColor()` |
| `ItemOptionTemplate.java` | 8 | — | Template option. | — |
| `ItemObject.java` | 8 | — | Object item. | — |
| `ItemTime.java` | 157 | — | Item có thời hạn. | `isExistItem(int)`, `isExistMessage(int)`, `getItemById(int)`, `getMessageById(int)`, `initTimeText(byte, String, int)`, `initTime(int, boolean)`, `paintText(mGraphics, int, int)` |
| `Skill.java` | 27 | — | Skill đã học. | `strTimeReplay()` |
| `SkillTemplate.java` | 26 | — | Template skill. | `isBuffToPlayer()`, `isUseAlone()`, `isSkillSpec()` |
| `Skills.java` | 14 | — | Danh sách skill. | `add(Skill)`, `get(short)` |
| `SkillOptionTemplate.java` | 5 | — | Template option skill. | — |
| `Task.java` | 24 | — | Nhiệm vụ. | — |
| `TaskOrder.java` | 20 | — | Bước nhiệm vụ. | — |
| `Clan.java` | 16 | — | Clan. | — |
| `ClanObject.java` | 7 | — | Object clan. | — |
| `Member.java` | 28 | — | Thành viên clan. | `getRole(int)` |
| `ClanMessage.java` | 108 | — | Tin nhắn clan. | `addMessage(ClanMessage, int, boolean)`, `paint(mGraphics, int, int)`, `perform(int, Object)`, `update()` |
| `NClass.java` | 7 | — | Class nhân vật. | — |
| `PKFlag.java` | 7 | — | Cờ PK. | — |
| `Point.java` | 46 | — | Điểm tọa độ. | `update()`, `setFrameAngle(int)` |
| `Line.java` | 23 | — | Đường thẳng. | `setLine(int, int, int, int, int, int, boolean)` |
| `MobTemplate.java` | 12 | — | Template mob. | — |
| `NpcTemplate.java` | 11 | — | Template NPC. | — |
| `ImageInfo.java` | 10 | — | Thông tin ảnh. | — |
| `Frame.java` | 8 | — | Một frame. | — |
| `TopInfo.java` | 14 | — | Thông tin top. | — |
| `Archivement.java` | 10 | — | Thành tựu. | — |
| `GameInfo.java` | 9 | — | Thông tin game. | — |
| `BallInfo.java` | 18 | — | Thông tin bóng cho minigame. | — |
| `ScrollResult.java` | 8 | — | Kết quả cuộn. | — |
| `Position.java` | 20 | — | Vị trí. | — |
| `PlayerData.java` | 20 | — | Dữ liệu người chơi. | — |
| `StaticObj.java` | 30 | — | Hằng số dùng chung. | — |

### Tiện ích — 9 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `Res.java` | 638 | — | Bảng sin/cos, random, toán, log. | `init()`, `sin(int)`, `cos(int)`, `angle(int, int)`, `fixangle(int)`, `paintOnScreenDebug(mGraphics)`, `updateOnScreenDebug()` |
| `mSystem.java` | 331 | — | Thời gian, nạp ảnh, hotline, banner. | `AddIpTest()`, `resetCurInapp()`, `getWidth(Image)`, `getHeight(Image)`, `getTimeCountDown(long, int, boolean, boolean)`, `numberTostring(long)`, `callHotlineJava()` |
| `mResources.java` | 1249 | — | Chuỗi giao diện tiếng Việt. | `loadLanguague(byte)` |
| `T2.java` | 423 | mResources | Chuỗi tiếng Anh, kế thừa mResources. | `load()` |
| `Rms.java` | 116 | — | Bọc RecordStore. | `saveRMS(String, byte[])`, `deleteRecord(String)`, `clearAll()`, `loadRMS(String)`, `saveRMSInt(String, int)`, `saveRMSString(String, String)`, `loadRMSString(String)` |
| `MyVector.java` | 43 | Vector | Vector có tên. | `removeAllElements()`, `removeElement(Object)`, `removeElementAt(int)`, `addElement(Object)`, `insertElementAt(Object, int)` |
| `MyHashTable.java` | 21 | Hashtable | Hashtable có tên. | `clear()`, `put(Object, Object)`, `remove(Object)` |
| `SoundMn.java` | 377 | — | Âm thanh và hiệu ứng. | `gI()`, `AuraToolOption()`, `analogToolOption()`, `CaseSizeScr()`, `AuraToolOption2()`, `loadSound()`, `charPunch()` |
| `PaymentCatalog.java` | 11 | — | Danh mục gói nạp. | `onPurchaseSelection()` |

### Interface — 3 file

| File | Dòng | Kế thừa | Chức năng | Hàm chính |
|---|---:|---|---|---|
| `IMapObject.java` | 16 | — | Interface object trên map. | `getX()`, `getY()`, `getW()`, `getH()`, `stopMoving()`, `isInvisible()` |
| `IChatable.java` | 8 | — | Interface màn hình nhận chat. | `onChatFromMe(String, String)`, `onCancelChat()` |
| `IActionListener.java` | 6 | — | Interface nhận action từ Command. | `perform(int, Object)` |

## Resource

### Cấu trúc

| Thư mục | Số file | Nội dung |
|---|---:|---|
| `x1/bg/` | 93 | Nền từng map |
| `x1/mainImage/` | 143 | Ảnh lớn: nhân vật, boss, item |
| `x1/mapBackGround/` | 64 | Nền nhiều lớp cho map |
| `x1/effectdata/` | 52 | Dữ liệu effect + atlas ảnh |
| `x1/e/` | 45 | Hiệu ứng tĩnh |
| `x1/myfont/` | 26 | Font bitmap theo zoom |
| `x1/radar/` | 24 | Icon radar |
| `x1/t/` | 17 | Tile map |
| `x1/img/` | 7 | Ảnh giao diện |
| `x1/SmallImage/` | 2 | Atlas ảnh nhỏ |
| `x1/` (gốc) | 2 | `gamelogo.png`, `redeye.png` |
| `myfont/` | 4 | Font gốc: `number`, `tahoma_7`, `tahoma_7b`, `tahoma_8b` |
| `mymap/` | 6 | Map 39, 40, 41 + `mapTable39/40/41` |
| gốc resource | 3 | `icon.png`, `info`, `login_codes_generated.txt` |

Tổng cộng 488 file.

### Cách load ảnh

`ImageLoader.loadImage(path)`:

1. Ghép đường dẫn `/x{zoomLevel}` + path.
2. Đọc toàn bộ byte.
3. XOR từng byte với key `{-22, 2, 12, 4, 5, 2, -10}`, quay vòng key.
4. `Image.createImage(data, 0, length)`.

Nếu ảnh lỗi hoặc không tồn tại, trả `null` và game dùng ảnh fallback. Vì vậy đừng sửa trực tiếp file PNG trong `x1/` bằng trình chỉnh ảnh, game sẽ không đọc được.

### Font

`mFont` dùng atlas PNG + file dữ liệu đi kèm trong `x1/myfont/`. Mỗi zoom level có bộ font riêng. Nếu thiếu font của zoom hiện tại, `MotherCanvas` tự giảm zoom cho đến khi tìm được bộ hợp lệ.

## config/

### `MANIFEST.MF`

```text
Manifest-Version: 1.0
MIDlet-Name: DragonBoy252
MIDlet-Version: 2.5.2
MIDlet-Vendor: Longdeptrai1107
MIDlet-1: DragonBoy252,,main.GameMidlet
MicroEdition-Configuration: CLDC-1.1
MicroEdition-Profile: MIDP-2.0
```

Build tool đọc file này, chuẩn hóa CRLF rồi chèn vào JAR. File `.jad` được sinh từ cùng nội dung, thêm `MIDlet-Jar-URL` và `MIDlet-Jar-Size`.

### `resources.lock.json`

Danh sách 488 resource, mỗi mục gồm `path`, `size`, `sha256`. Build tool chỉ đóng gói những file có trong danh sách và kiểm tra hash từng file. File lạ trong `src/main/resources` sẽ làm build fail.

Cấu trúc:

```json
{
  "schema_version": 1,
  "input_jar_sha256": "...",
  "resource_count": 488,
  "resources": [
    { "path": "icon.png", "size": 3202, "sha256": "..." }
  ]
}
```

## toolchain/

### `StandaloneJarBuild.java`

Công cụ build một file, không dùng dependency ngoài JDK. Các bước:

1. Đọc tham số: thư mục project, thư mục output, mode (`release` hoặc `debug`).
2. Kiểm tra SHA-256 của `lib/ecj-3.33.0.jar`, `lib/cldcapi11-2.0.4.jar`, `lib/midpapi20-2.0.4.jar`. Sai hash thì dừng.
3. Đọc `config/resources.lock.json`, kiểm tra đủ 488 resource, đúng size và hash, không trùng path, không path thoát ra ngoài thư mục resource.
4. Liệt kê `src/main/java`, yêu cầu đúng 157 file.
5. Gọi ECJ:

```text
java -jar ecj-3.33.0.jar
  -1.3
  -encoding UTF-8
  -g:lines,source        (debug: -g)
  -proc:none
  -bootclasspath cldcapi11:midpapi20
  -classpath cldcapi11:midpapi20
  -d <classes>
  -maxProblems 1000
  -log compiler.xml
  @sources.args
```

6. Kiểm tra đúng 157 file `.class`, mỗi file phải có magic `0xCAFEBABE`, major version 45, minor 3.
7. Gộp class + resource + manifest thành ZIP, nén mức 9, timestamp cố định `1980-01-01` để build lặp lại cho ra cùng một JAR.
8. Mở lại JAR, đọc từng entry, so CRC và so nội dung với dữ liệu gốc.
9. Ghi `dist/DragonBoy252.jar`, `dist/DragonBoy252.jad`, `build-report.json`, `compiler.log`, `compiler.xml`.
10. Copy class sang `bin/` cho Eclipse, xóa class cũ không còn dùng.
11. Dọn thư mục tạm trong `.build/`.

Vì timestamp và thứ tự entry cố định, build nhiều lần trên cùng JDK/ECJ sẽ ra cùng SHA-256.

### Script

| File | Việc |
|---|---|
| `build.ps1` | Chuyển tiếp tham số sang `eclipse-build.ps1` |
| `eclipse-build.ps1` | Tìm `java` theo thứ tự: `-JavaHome` → `toolchain/java` → `JAVA_HOME` → PATH; chọn output `dist` hoặc `debug-dist`; chạy build tool |
| `run.ps1` | Build rồi chạy MicroEmulator; `-Debug` thêm JDWP và `suspend=y`; `-Port` đổi cổng |
| `.externalToolBuilders/Build-JAR.launch` | Builder Eclipse gọi `eclipse-build.ps1` mỗi lần build project |

## lib/

| File | Dùng để |
|---|---|
| `ecj-3.33.0.jar` | Compile source 1.3, target classfile 45.3 |
| `cldcapi11-2.0.4.jar` | Boot classpath CLDC 1.1, chỉ compile |
| `midpapi20-2.0.4.jar` | Boot classpath MIDP 2.0, chỉ compile |
| `microemulator-2.0.4.jar` | Chạy MIDlet trên desktop |

`cldcapi11` và `midpapi20` không được đóng vào JAR game. `microemulator` chỉ dùng lúc chạy, không nằm trong JAR.

## Kiểm tra toàn vẹn

`SOURCE-CHECKSUMS.json` lưu SHA-256 của 661 file: 157 Java, 488 resource và 16 file cấu hình/script/lib.

Kiểm tra nhanh bằng Python:

```bash
python3 - <<'EOF'
import json, hashlib, os
d = json.load(open('SOURCE-CHECKSUMS.json'))['files']
bad = [p for p, h in d.items()
       if not os.path.isfile(p) or hashlib.sha256(open(p, 'rb').read()).hexdigest() != h]
print('OK' if not bad else bad)
EOF
```

Hoặc chỉ cần build: build tool tự kiểm tra hash resource và hash thư viện.

## Lỗi thường gặp

**`JDK 17+ not found`**
Chưa có JDK hoặc `JAVA_HOME` sai. Cài Temurin 17, set `JAVA_HOME`, hoặc chạy `.\build.ps1 -JavaHome "đường\dẫn\jdk"`.

**`running scripts is disabled on this system`**
Chạy lại với `-ExecutionPolicy Bypass` như lệnh mẫu, hoặc `Set-ExecutionPolicy -Scope Process Bypass`.

**`Expected exactly 488 locked resources`**
Có file bị thêm/xóa trong `src/main/resources`. Khôi phục đúng file hoặc cập nhật `config/resources.lock.json` rồi build lại.

**`Resource differs from lock`**
File resource bị sửa. Khôi phục bản gốc, hoặc nếu cố ý sửa thì cập nhật hash trong lock.

**`Tool/API missing or hash differs`**
JAR trong `lib/` bị thay hoặc tải lỗi. Tải lại đúng phiên bản ghi trong `THIRD_PARTY_NOTICES.md`.

**Emulator mở nhưng màn hình đen**
Kiểm tra `compiler.log` có lỗi không, và chắc chắn đang chạy đúng JAR trong `dist/`. Bản debug có thể đang chờ debugger, bấm Resume (F8).

**Ảnh hiện sai màu hoặc không hiện**
Thiếu resource hoặc resource bị sửa. `ImageLoader` chỉ giải mã đúng key XOR gốc.

**Không kết nối được server**
Server mặc định nằm trong `GameMidlet.IP` / `PORT`. Có thể đổi sang server khác hoặc dùng `ServerListScreen`.

## Ghi chú

- Source là decompile từ `Dragonboy 2.5.2.jar`, sau đó chỉnh để compile lại được. Tên biến kiểu `value`, `value2`, `iOException` là do decompiler sinh ra, không phải code viết tay.
- Không có source decompile cũ, DLL, bản gốc hay dữ liệu phân tích trong repo.
- `bin/`, `dist/`, `debug-dist/`, `.build/`, `toolchain/java/` nằm trong `.gitignore`.
- Nếu muốn build trên Linux/macOS mà không cài PowerShell, dùng lệnh `java` trực tiếp ở mục Build.

## Giấy phép

Repo không chọn một giấy phép nguồn mở chung. Mã game, tài nguyên và JAR đầu vào không được tuyên bố là tài sản mới của người phục hồi. Công cụ bên thứ ba giữ giấy phép của nhà phát hành.

Đọc kỹ [LICENSE.md](LICENSE.md) và [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) trước khi phân phối lại.

## Tác giả

Vi Thành Long

- Facebook: https://facebook.com/longls025
- Donate Momo: 0339571494

Cám ơn mọi người đã ghé qua và dùng source.
