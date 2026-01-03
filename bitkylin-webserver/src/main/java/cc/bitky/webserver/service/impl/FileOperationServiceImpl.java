package cc.bitky.webserver.service.impl;

import cc.bitky.webserver.config.FileBasePathProperties;
import cc.bitky.webserver.exception.DirectoryNotFoundException;
import cc.bitky.webserver.exception.FileAccessDeniedException;
import cc.bitky.webserver.exception.FileNotFoundException;
import cc.bitky.webserver.service.IFileOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作服务实现
 */
@Slf4j
@Service
public class FileOperationServiceImpl implements IFileOperationService {

    private final FileBasePathProperties properties;

    public FileOperationServiceImpl(FileBasePathProperties properties) {
        this.properties = properties;
    }

    @Override
    public Path locateFile(String basePath, String fileName) {
        Path filePath = Paths.get(basePath, fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            log.warn("文件不存在: {}", filePath);
            throw new FileNotFoundException(filePath.toString());
        }

        if (!file.canRead()) {
            log.warn("文件不可读: {}", filePath);
            throw new FileAccessDeniedException(filePath.toString());
        }

        return filePath;
    }

    @Override
    public List<FileDetail> listFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            log.warn("目录不存在: {}", directoryPath);
            throw new DirectoryNotFoundException(directoryPath);
        }

        if (!directory.isDirectory()) {
            log.warn("路径不是目录: {}", directoryPath);
            throw new DirectoryNotFoundException(directoryPath);
        }

        File[] files = directory.listFiles();
        if (files == null) {
            log.warn("无法读取目录内容: {}", directoryPath);
            throw new DirectoryNotFoundException(directoryPath);
        }

        List<FileDetail> result = new ArrayList<>();
        for (File file : files) {
            result.add(new FileDetail(
                    file.getName(),
                    file.getAbsolutePath(),
                    file.isDirectory(),
                    file.isDirectory() ? 0 : file.length(),
                    file.lastModified()
            ));
        }
        return result;
    }

    @Override
    public ValidationResult validatePaths(List<String> paths) {
        List<String> invalidPaths = new ArrayList<>();

        for (String pathStr : paths) {
            Path path = Path.of(pathStr);
            File file = path.toFile();

            if (!file.exists() || !file.canRead()) {
                invalidPaths.add(pathStr);
            }
        }

        if (invalidPaths.isEmpty()) {
            return ValidationResult.success();
        }
        return ValidationResult.failure(invalidPaths);
    }
}
