package cc.bitky.webserver.service;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 文件打包服务测试 - 加密 ZIP 密码验证测试
 *
 * zip4j 库在无密码或错误密码解压 AES 加密的 ZIP 时，会抛出 ZipException。
 * 加密的 ZIP 文件无法在未提供密码或密码错误的情况下解压。
 */
@DisplayName("加密 ZIP 密码验证测试")
class EncryptedZipPasswordTest {

    @TempDir
    Path tempDir;

    private FilePackService filePackService;

    private static final String CORRECT_PASSWORD = "bitkylin";
    private static final String WRONG_PASSWORD = "wrong_password";

    // ==================== 测试正确密码解压成功 ====================

    @Test
    @DisplayName("使用正确密码解压加密 ZIP 应成功并获得正确内容")
    void decryptZipWithCorrectPasswordShouldSucceed() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        String originalContent = "这是机密内容 - " + System.currentTimeMillis();
        Path testFile = tempDir.resolve("secret.txt");
        Files.writeString(testFile, originalContent);

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));
        assertTrue(zipFile.exists());

        // 使用正确密码解压应成功
        Path extractDir = tempDir.resolve("extract_correct");
        try (ZipFile zip = new ZipFile(zipFile)) {
            zip.setPassword(CORRECT_PASSWORD.toCharArray());
            zip.extractAll(extractDir.toString());
        }

        // 验证解压后的文件内容正确
        Path extractedFile = extractDir.resolve("secret.txt");
        assertTrue(extractedFile.toFile().exists(), "正确密码解压后文件应存在");
        assertEquals(originalContent, Files.readString(extractedFile),
                "正确密码解压内容应与原始内容一致");
    }

    @Test
    @DisplayName("使用正确密码解压多层目录结构应成功")
    void decryptNestedDirectoryWithCorrectPasswordShouldSucceed() throws Exception {
        filePackService = new FilePackService();

        // 创建多层目录结构
        Path subDir = tempDir.resolve("subdir");
        Files.createDirectories(subDir);
        Path file1 = subDir.resolve("file1.txt");
        Path file2 = subDir.resolve("file2.txt");
        String content1 = "文件1内容 - " + System.currentTimeMillis();
        String content2 = "文件2内容 - " + System.currentTimeMillis();
        Files.writeString(file1, content1);
        Files.writeString(file2, content2);

        File zipFile = filePackService.createEncryptedZip(List.of(subDir.toString()));
        assertTrue(zipFile.exists());

        // 使用正确密码解压
        Path extractDir = tempDir.resolve("extract_nested");
        try (ZipFile zip = new ZipFile(zipFile)) {
            zip.setPassword(CORRECT_PASSWORD.toCharArray());
            zip.extractAll(extractDir.toString());
        }

        // 验证解压后的文件内容正确
        Path extractedFile1 = extractDir.resolve("subdir/file1.txt");
        Path extractedFile2 = extractDir.resolve("subdir/file2.txt");
        assertTrue(extractedFile1.toFile().exists(), "文件1应存在");
        assertTrue(extractedFile2.toFile().exists(), "文件2应存在");
        assertEquals(content1, Files.readString(extractedFile1));
        assertEquals(content2, Files.readString(extractedFile2));
    }

    // ==================== 测试无密码解压失败 ====================

    @Test
    @DisplayName("无密码解压加密 ZIP 应抛出异常")
    void decryptZipWithoutPasswordShouldThrowException() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        String originalContent = "机密内容 - " + System.currentTimeMillis();
        Path testFile = tempDir.resolve("secret.txt");
        Files.writeString(testFile, originalContent);

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));
        assertTrue(zipFile.exists());

        // 无密码解压应抛出异常
        Path extractDir = tempDir.resolve("extract_no_pwd");

        ZipException exception = assertThrows(ZipException.class, () -> {
            try (ZipFile zip = new ZipFile(zipFile)) {
                zip.extractAll(extractDir.toString());
            }
        }, "无密码解压加密 ZIP 应抛出 ZipException");

        // 异常信息应提示需要密码
        assertTrue(exception.getMessage().toLowerCase().contains("password") ||
                   exception.getMessage().toLowerCase().contains("encrypt") ||
                   exception.getMessage().toLowerCase().contains("wrong"),
                "异常信息应提示与密码/加密相关，异常信息: " + exception.getMessage());
    }

    // ==================== 测试错误密码解压失败 ====================

    @Test
    @DisplayName("使用错误密码解压加密 ZIP 应抛出异常")
    void decryptZipWithWrongPasswordShouldThrowException() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        String originalContent = "机密内容 - " + System.currentTimeMillis();
        Path testFile = tempDir.resolve("secret.txt");
        Files.writeString(testFile, originalContent);

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));
        assertTrue(zipFile.exists());

        // 使用错误密码解压应抛出异常
        Path extractDir = tempDir.resolve("extract_wrong_pwd");

        ZipException exception = assertThrows(ZipException.class, () -> {
            try (ZipFile zip = new ZipFile(zipFile)) {
                zip.setPassword(WRONG_PASSWORD.toCharArray());
                zip.extractAll(extractDir.toString());
            }
        }, "错误密码解压加密 ZIP 应抛出 ZipException");

        // 异常信息应提示密码错误
        assertTrue(exception.getMessage().toLowerCase().contains("password") ||
                   exception.getMessage().toLowerCase().contains("wrong") ||
                   exception.getMessage().toLowerCase().contains("invalid") ||
                   exception.getMessage().toLowerCase().contains("failed"),
                "异常信息应提示密码错误，异常信息: " + exception.getMessage());
    }

    // ==================== 完整流程测试 ====================

    @Test
    @DisplayName("加密 ZIP 密码验证完整流程测试")
    void encryptedZipPasswordVerificationFullFlow() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        String originalContent = "密码验证测试内容 - " + System.currentTimeMillis();
        Path testFile = tempDir.resolve("verify.txt");
        Files.writeString(testFile, originalContent);

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));
        assertTrue(zipFile.exists());

        Path extractDir = tempDir.resolve("extract_verify");

        // 场景1: 无密码解压 - 应抛出异常
        Path noPwdExtractDir = extractDir.resolve("no_pwd");
        assertThrows(ZipException.class, () -> {
            try (ZipFile zip = new ZipFile(zipFile)) {
                zip.extractAll(noPwdExtractDir.toString());
            }
        }, "无密码解压应抛出异常");

        // 场景2: 错误密码解压 - 应抛出异常
        Path wrongPwdExtractDir = extractDir.resolve("wrong_pwd");
        assertThrows(ZipException.class, () -> {
            try (ZipFile zip = new ZipFile(zipFile)) {
                zip.setPassword(WRONG_PASSWORD.toCharArray());
                zip.extractAll(wrongPwdExtractDir.toString());
            }
        }, "错误密码解压应抛出异常");

        // 场景3: 正确密码解压 - 应成功
        Path correctPwdExtractDir = extractDir.resolve("correct_pwd");
        try (ZipFile zip = new ZipFile(zipFile)) {
            zip.setPassword(CORRECT_PASSWORD.toCharArray());
            zip.extractAll(correctPwdExtractDir.toString());
        }

        // 验证解压内容
        Path correctPwdFile = correctPwdExtractDir.resolve("verify.txt");
        assertTrue(correctPwdFile.toFile().exists(), "正确密码解压后文件应存在");
        assertEquals(originalContent, Files.readString(correctPwdFile),
                "正确密码解压内容应与原始内容一致");
    }

    // ==================== ZIP 文件加密验证 ====================

    @Test
    @DisplayName("创建的 ZIP 文件应确实是加密的 - 无密码解压抛出异常")
    void createdZipShouldBeEncrypted() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        String originalContent = "测试内容 - " + System.currentTimeMillis();
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, originalContent);

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));
        assertTrue(zipFile.exists());

        // 无密码解压应抛出异常
        Path extractDir = tempDir.resolve("extract_encrypt_verify");
        assertThrows(ZipException.class, () -> {
            try (ZipFile zip = new ZipFile(zipFile)) {
                zip.extractAll(extractDir.toString());
            }
        }, "无密码解压加密的 ZIP 应抛出异常");
    }

    @Test
    @DisplayName("创建的 ZIP 文件应确实是加密的 - 错误密码解压抛出异常")
    void createdZipWithWrongPasswordShouldThrowException() throws Exception {
        filePackService = new FilePackService();

        // 创建测试文件
        String originalContent = "测试内容 - " + System.currentTimeMillis();
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, originalContent);

        File zipFile = filePackService.createEncryptedZip(List.of(testFile.toString()));
        assertTrue(zipFile.exists());

        // 错误密码解压应抛出异常
        Path extractDir = tempDir.resolve("extract_wrong_verify");
        assertThrows(ZipException.class, () -> {
            try (ZipFile zip = new ZipFile(zipFile)) {
                zip.setPassword(WRONG_PASSWORD.toCharArray());
                zip.extractAll(extractDir.toString());
            }
        }, "错误密码解压加密的 ZIP 应抛出异常");
    }
}
