package cc.bitky.webserver.service;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 文件打包服务
 */
@Slf4j
@Service
public class FilePackService {

    private static final String ZIP_PASSWORD_ENV = "ZIP_DOWNLOAD_PASSWORD";
    private static final String DEFAULT_ZIP_PASSWORD = "123lml123";

    /**
     * 获取 ZIP 密码
     *
     * @return 密码信息，包含密码和是否使用默认密码的标识
     */
    public PasswordInfo getZipPassword() {
        String password = System.getenv(ZIP_PASSWORD_ENV);
        if (password == null || password.isBlank()) {
            log.warn("环境变量 {} 未设置，使用默认密码进行加密", ZIP_PASSWORD_ENV);
            return new PasswordInfo(DEFAULT_ZIP_PASSWORD, true);
        }
        return new PasswordInfo(password, false);
    }

    /**
     * 密码信息
     */
    public record PasswordInfo(String password, boolean usingDefault) {
    }

    /**
     * 创建加密 ZIP 文件
     *
     * @param sourcePaths 要打包的文件/文件夹路径列表
     * @return 生成的 ZIP 文件
     * @throws IOException       IO 异常
     * @throws ZipException      ZIP 操作异常
     */
    public File createEncryptedZip(List<String> sourcePaths) throws IOException, ZipException {
        Path tempDir = Files.createTempDirectory("zip_temp_");
        String zipFileName = "packed_files_" + System.currentTimeMillis() + ".zip";
        File zipFile = tempDir.resolve(zipFileName).toFile();

        PasswordInfo passwordInfo = getZipPassword();
        String password = passwordInfo.password();

        // 计算最近公共祖先目录
        Path commonAncestor = findCommonAncestor(sourcePaths);

        try (ZipFile zip = new ZipFile(zipFile)) {
            // 设置密码（zip4j 2.x 使用此方式）
            zip.setPassword(password.toCharArray());

            // 创建加密参数
            ZipParameters parameters = createEncryptionParameters();

            for (String pathStr : sourcePaths) {
                Path sourcePath = Path.of(pathStr);
                File sourceFile = sourcePath.toFile();

                if (!sourceFile.exists()) {
                    log.warn("路径不存在，跳过: {}", pathStr);
                    continue;
                }

                // 使用相对路径作为 ZIP 条目名称
                Path relativePath = commonAncestor.relativize(sourcePath);
                String entryName = relativePath.toString();

                if (sourceFile.isDirectory()) {
                    addFolderToZip(zip, sourceFile, entryName, parameters);
                } else {
                    addFileToZip(zip, sourceFile, entryName, parameters);
                }
            }
        }

        // 清理临时目录
        cleanupTempDirectory(tempDir);

        log.info("已创建加密 ZIP 文件: {} (使用环境变量密码)", zipFile.getAbsolutePath());
        return zipFile;
    }

    /**
     * 创建加密参数
     */
    private ZipParameters createEncryptionParameters() {
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptionMethod(EncryptionMethod.AES);
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        return parameters;
    }

    /**
     * 添加文件到 ZIP（带自定义路径）
     */
    private void addFileToZip(ZipFile zip, File file, String entryName, ZipParameters parameters) throws IOException {
        parameters.setFileNameInZip(entryName);
        zip.addFile(file, parameters);
    }

    /**
     * 递归添加文件夹到 ZIP
     */
    private void addFolderToZip(ZipFile zip, File folder, String entryName, ZipParameters parameters) throws IOException {
        File[] files = folder.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            String fileEntryName = entryName.isEmpty() ? file.getName() : entryName + "/" + file.getName();

            if (file.isDirectory()) {
                addFolderToZip(zip, file, fileEntryName, parameters);
            } else {
                addFileToZip(zip, file, fileEntryName, parameters);
            }
        }
    }

    /**
     * 查找最近公共祖先目录
     */
    private Path findCommonAncestor(List<String> paths) {
        if (paths == null || paths.isEmpty()) {
            return Path.of("/");
        }

        Path commonAncestor = Path.of(paths.get(0)).getParent();
        if (commonAncestor == null) {
            commonAncestor = Path.of("/");
        }

        for (int i = 1; i < paths.size(); i++) {
            Path currentPath = Path.of(paths.get(i));
            Path parent = currentPath.getParent();

            while (parent != null && !commonAncestor.toAbsolutePath().startsWith(parent.toAbsolutePath())) {
                parent = parent.getParent();
            }

            if (parent != null) {
                commonAncestor = parent;
            }
        }

        return commonAncestor;
    }

    /**
     * 生成 ZIP 文件名
     */
    private String generateZipFileName() {
        return "files_" + System.currentTimeMillis() + ".zip";
    }

    /**
     * 清理临时目录（保留 ZIP 文件）
     */
    private void cleanupTempDirectory(Path tempDir) throws IOException {
        File tempDirFile = tempDir.toFile();
        File[] files = tempDirFile.listFiles();

        if (files != null) {
            for (File file : files) {
                if (!file.getName().endsWith(".zip")) {
                    deleteRecursively(file);
                }
            }
        }
    }

    /**
     * 递归删除文件/文件夹
     */
    private void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursively(child);
                }
            }
        }
        file.delete();
    }

}
