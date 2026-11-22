# Công cụ và thành phần bên thứ ba

Các binary bên dưới được dùng cục bộ. Repository lưu phiên bản, nguồn và hash để tái tạo môi trường, thay vì commit JDK, decompiler, emulator hoặc SDK JAR.

| Thành phần | Phiên bản dùng | Vai trò | Nguồn chuẩn |
|---|---|---|---|
| Eclipse Temurin | 17.0.21.1+2 trên máy kiểm thử | Host JDK | Eclipse Adoptium |
| Eclipse ECJ | 3.33.0 | Compile source 1.3 / target 1.1 | Eclipse JDT, Maven Central |
| MicroEmulator | 2.0.4 | Runtime MIDP/CLDC trên JVM desktop | Dự án MicroEmulator, Maven Central |
| cldcapi11 / midpapi20 | 2.0.4 | Compile-only boot API | Artifact của MicroEmulator trên Maven Central |
| ASM | 9.7.1 | Đọc, kiểm tra và remap JVM bytecode | OW2 ASM, Maven Central |
| Gson | 2.11.0 | JSON cho công cụ/harness host | Google Gson, Maven Central |
| CFR | 0.152 | Decompiler độc lập | benf/cfr, Maven Central |
| Vineflower | 1.10.1 và 1.12.0 | Decompiler độc lập/đối chiếu | Vineflower, Maven Central |
| JavaParser | 3.26.3 | Sửa/transplant method theo AST | JavaParser, Maven Central |
| ILSpyCmd | 9.1.0.7988 | Đọc assembly .NET/C#/IL | ICSharpCode ILSpy, NuGet |
| .NET Runtime | 8.0.34 | Host cho ILSpyCmd | Microsoft .NET |
| 7-Zip | 23.01 | Kiểm tra CRC và giải nén RAR5 | Igor Pavlov / 7-zip.org |
| UnityPy | 1.25.4 | Đọc Unity 5.6.7f1 assets để đối chiếu | UnityPy / PyPI |
| Git for Windows | Ghi trong log công cụ cục bộ | Chuẩn bị repository | git-for-windows/git |

Kiểm tra các LICENSE/NOTICE/POM đi kèm đúng artifact khi phân phối lại một binary. MicroEmulator tự mô tả giấy phép LGPL trong manifest; JDK, compiler, thư viện và decompiler có điều khoản riêng, không được xem là cùng giấy phép với source game.

`source/lib/cldcapi11-2.0.4.jar` và `midpapi20-2.0.4.jar` chỉ dùng để compile, không được chèn vào JAR game. Tương tự, ASM và Gson chỉ phục vụ công cụ/harness; ASM 9 không được thêm vào runtime classpath của MicroEmulator 2.0.4.

Không thay đổi hoặc nhận quyền sở hữu đối với tên game, hình ảnh, âm thanh, dữ liệu, mã Java đã phục hồi hay C# tham chiếu của đầu vào.
