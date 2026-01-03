package cc.bitky.webserver.service;

import java.nio.file.Path;
import java.util.List;

/**
 * 文件操作服务接口
 */
public interface IFileOperationService {

    /**
     * 定位文件
     *
     * @param basePath  基准路径
     * @param fileName  文件名
     * @return 文件 Path
     * @throws cc.bitky.webserver.exception.FileNotFoundException   文件不存在
     * @throws cc.bitky.webserver.exception.FileAccessDeniedException 文件不可读
     */
    Path locateFile(String basePath, String fileName);

    /**
     * 列出目录下的文件
     *
     * @param directoryPath 目录路径
     * @return 文件详情列表
     * @throws cc.bitky.webserver.exception.DirectoryNotFoundException 目录不存在
     */
    List<FileDetail> listFiles(String directoryPath);

    /**
     * 批量验证路径
     *
     * @param paths 路径列表
     * @return 验证结果
     */
    ValidationResult validatePaths(List<String> paths);

    /**
     * 文件详情
     */
    record FileDetail(String name, String path, boolean directory, long size, long lastModified) {
    }

    /**
     * 验证结果
     */
    record ValidationResult(boolean isValid, List<String> invalidPaths) {
        public static ValidationResult success() {
            return new ValidationResult(true, List.of());
        }

        public static ValidationResult failure(List<String> invalidPaths) {
            return new ValidationResult(false, invalidPaths);
        }
    }
}
