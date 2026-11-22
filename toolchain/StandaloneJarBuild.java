import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.zip.*;

public class StandaloneJarBuild {
    static String hash(byte[] data) throws Exception {
        return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(data));
    }
    static void requireHash(Path file, String expected) throws Exception {
        if (!Files.isRegularFile(file) || !hash(Files.readAllBytes(file)).equals(expected))
            throw new IllegalStateException("Tool/API missing or hash differs: " + file);
    }
    static List<Path> files(Path root, String suffix) throws IOException {
        try (Stream<Path> stream = Files.walk(root)) {
            return stream.filter(Files::isRegularFile).filter(p -> suffix == null || p.toString().endsWith(suffix)).sorted().collect(Collectors.toList());
        }
    }
    static Map<String, byte[]> resources(Path project) throws Exception {
        String lock = Files.readString(project.resolve("config/resources.lock.json"), StandardCharsets.UTF_8);
        Matcher matcher = Pattern.compile("\\{\\s*\"path\"\\s*:\\s*\"([^\"\\\\]+)\"\\s*,\\s*\"size\"\\s*:\\s*(\\d+)\\s*,\\s*\"sha256\"\\s*:\\s*\"([0-9a-f]{64})\"\\s*\\}").matcher(lock);
        Path directory = project.resolve("src/main/resources");
        Map<String, byte[]> result = new TreeMap<>();
        while (matcher.find()) {
            String name = matcher.group(1);
            Path path = directory.resolve(name).normalize();
            if (!path.startsWith(directory) || !Files.isRegularFile(path)) throw new IllegalStateException("Missing/unsafe resource: " + name);
            byte[] data = Files.readAllBytes(path);
            if (data.length != Long.parseLong(matcher.group(2)) || !hash(data).equals(matcher.group(3)))
                throw new IllegalStateException("Resource differs from lock: " + name);
            if (result.put(name, data) != null) throw new IllegalStateException("Duplicate resource: " + name);
        }
        if (result.size() != 488 || files(directory, null).size() != result.size()) throw new IllegalStateException("Expected exactly 488 locked resources");
        return result;
    }
    public static void main(String[] args) throws Exception {
        Path project = Paths.get(args[0]).toAbsolutePath().normalize();
        Path output = Paths.get(args[1]).toAbsolutePath().normalize();
        Path compiler = project.resolve("lib/ecj-3.33.0.jar");
        Path cldc = project.resolve("lib/cldcapi11-2.0.4.jar");
        Path midp = project.resolve("lib/midpapi20-2.0.4.jar");
        requireHash(compiler, "f7686c4960cf70c2ebc5c500a73a8cfc04541b730c18f1c5c21329889b137f45");
        requireHash(cldc, "f86f41bfb4097e4bd9cdfeca528960f6977cbda0f5785342adef9909a6a3baef");
        requireHash(midp, "b7f6ed5e0344c7000643ecc114e6edff3587e8e77face546af0e12ffd6dbe826");
        Map<String, byte[]> entries = resources(project);
        List<Path> sources = files(project.resolve("src/main/java"), ".java");
        if (sources.size() != 157) throw new IllegalStateException("Expected exactly 157 Java source files");
        Files.createDirectories(project.resolve(".build"));
        Path work = Files.createTempDirectory(project.resolve(".build"), "jar-");
        Path classes = Files.createDirectory(work.resolve("classes"));
        Path arguments = work.resolve("sources.args");
        Files.writeString(arguments, sources.stream().map(p -> "\"" + p.toString().replace('\\', '/') + "\"").collect(Collectors.joining("\n")), StandardCharsets.UTF_8);
        String javaName = System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win") ? "java.exe" : "java";
        Path java = Paths.get(System.getProperty("java.home"), "bin", javaName);
        String api = cldc + File.pathSeparator + midp;
        List<String> command = Arrays.asList(java.toString(), "-Dfile.encoding=UTF-8", "-Xmx3g", "-jar", compiler.toString(),
            "-1.3", "-encoding", "UTF-8", (args.length > 2 && "debug".equals(args[2]) ? "-g" : "-g:lines,source"), "-proc:none", "-bootclasspath", api, "-classpath", api,
            "-d", classes.toString(), "-maxProblems", "1000", "-log", work.resolve("compiler.xml").toString(), "@" + arguments);
        Process process = new ProcessBuilder(command).directory(project.toFile()).redirectErrorStream(true).start();
        byte[] compilerOutput = process.getInputStream().readAllBytes();
        int exit = process.waitFor();
        Files.write(work.resolve("compiler.log"), compilerOutput);
        if (exit != 0) System.err.write(compilerOutput);
        if (exit != 0) throw new IllegalStateException("ECJ compilation failed; logs retained in " + work);
        List<Path> compiled = files(classes, ".class");
        if (compiled.size() != 157) throw new IllegalStateException("Expected 157 newly compiled class files");
        for (Path file : compiled) {
            byte[] data = Files.readAllBytes(file);
            ByteBuffer header = ByteBuffer.wrap(data);
            if (header.getInt() != 0xcafebabe || Short.toUnsignedInt(header.getShort()) != 3 || Short.toUnsignedInt(header.getShort()) != 45)
                throw new IllegalStateException("Unexpected classfile version: " + file);
            String name = classes.relativize(file).toString().replace('\\', '/');
            if (entries.put(name, data) != null) throw new IllegalStateException("Entry collision: " + name);
        }
        String manifest = Files.readString(project.resolve("config/MANIFEST.MF"), StandardCharsets.UTF_8)
            .lines().collect(Collectors.joining("\r\n")).stripTrailing() + "\r\n\r\n";
        entries.put("META-INF/MANIFEST.MF", manifest.getBytes(StandardCharsets.UTF_8));
        Files.createDirectories(output);
        Files.write(output.resolve("compiler.log"), compilerOutput);
        Files.copy(work.resolve("compiler.xml"), output.resolve("compiler.xml"), StandardCopyOption.REPLACE_EXISTING);
        Path temporary = Files.createTempFile(output, ".building-", ".jar");
        try (ZipOutputStream archive = new ZipOutputStream(Files.newOutputStream(temporary))) {
            archive.setLevel(9);
            for (Map.Entry<String, byte[]> item : entries.entrySet()) {
                ZipEntry entry = new ZipEntry(item.getKey());
                entry.setTimeLocal(LocalDateTime.of(1980, 1, 1, 0, 0));
                archive.putNextEntry(entry);
                archive.write(item.getValue());
                archive.closeEntry();
            }
        }
        int verified = 0;
        try (ZipFile archive = new ZipFile(temporary.toFile())) {
            Enumeration<? extends ZipEntry> iterator = archive.entries();
            while (iterator.hasMoreElements()) {
                ZipEntry entry = iterator.nextElement();
                byte[] data;
                try (InputStream input = archive.getInputStream(entry)) { data = input.readAllBytes(); }
                CRC32 crc = new CRC32(); crc.update(data);
                if (crc.getValue() != entry.getCrc() || !Arrays.equals(entries.get(entry.getName()), data))
                    throw new IllegalStateException("JAR payload/CRC differs: " + entry.getName());
                verified++;
            }
        }
        if (verified != 646) throw new IllegalStateException("JAR entry count differs");
        Path jar = output.resolve("DragonBoy252.jar");
        Files.move(temporary, jar, StandardCopyOption.REPLACE_EXISTING);
        String jad = manifest.replace("Manifest-Version: 1.0\r\n", "").stripTrailing()
            + "\r\nMIDlet-Jar-URL: " + jar.getFileName() + "\r\nMIDlet-Jar-Size: " + Files.size(jar) + "\r\n";
        Files.writeString(output.resolve("DragonBoy252.jad"), jad, StandardCharsets.UTF_8);
        Path bin = project.resolve("bin");
        Files.createDirectories(bin);
        for (Path file : files(bin, null))
            if (!file.toString().endsWith(".class")) throw new IllegalStateException("Unexpected non-generated file in Eclipse output directory: " + file);
        Set<String> generated = new HashSet<>();
        for (Path file : compiled) {
            Path relative = classes.relativize(file), destination = bin.resolve(relative);
            Files.createDirectories(destination.getParent());
            Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);
            generated.add(relative.toString());
        }
        for (Path file : files(bin, ".class")) if (!generated.contains(bin.relativize(file).toString())) Files.delete(file);
        String digest = hash(Files.readAllBytes(jar));
        String report = "{\n  \"passed\": true,\n  \"compiler\": \"ECJ 3.33.0\",\n  \"source_files\": 157,\n  \"class_files\": 157,\n  \"classfile_version\": \"45.3\",\n  \"resources\": 488,\n  \"sha256\": \"" + digest + "\"\n}\n";
        Files.writeString(output.resolve("build-report.json"), report, StandardCharsets.UTF_8);
        for (Path file : files(work, null)) Files.delete(file);
        try (Stream<Path> stream = Files.walk(work)) {
            for (Path directory : stream.sorted(Comparator.reverseOrder()).collect(Collectors.toList())) Files.delete(directory);
        }
        System.out.println("Built 157 classes + 488 verified resources, classfile 45.3");
        System.out.println("ECJ: 0 errors; warnings/details in " + output.resolve("compiler.log"));
        System.out.println("JAR: " + jar);
        System.out.println("SHA-256: " + digest);
    }
}
