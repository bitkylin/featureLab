package cc.bitky.webserver.controller;

import cc.bitky.webserver.config.FileBasePathProperties;
import cc.bitky.webserver.dto.FileInfo;
import cc.bitky.webserver.dto.PackDownloadReq;
import cc.bitky.webserver.service.FilePackService;
import cc.bitky.webserver.service.IFileOperationService;
import cc.bitky.webserver.service.IFilePackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

/**
 * 假想敌服务
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ImaginaryEnemyController {

    private final IFileOperationService fileOperationService;
    private final IFilePackService filePackService;
    private final FileBasePathProperties fileBasePathProperties;

    /**
     * 伙计刺探
     */
    @GetMapping("/spyGuy/**")
    public ResponseEntity<Resource> spyElement(@RequestParam String guy) {
        String basePath = fileBasePathProperties.getBasePath();
        Path filePath = fileOperationService.locateFile(basePath, guy);
        return createResourceResponseEntity(filePath);
    }

    /**
     * 刺探医生
     */
    @GetMapping("/spy/doc")
    public ResponseEntity<Resource> spyDoc() {
        String basePath = fileBasePathProperties.getBasePath();
        Path filePath = fileOperationService.locateFile(basePath, "doc");
        return createResourceResponseEntity(filePath);
    }

    /**
     * 刺探德芙
     */
    @GetMapping("/spy/dev")
    public ResponseEntity<Resource> spyDev() {
        String basePath = fileBasePathProperties.getBasePath();
        Path filePath = fileOperationService.locateFile(basePath, "dev");
        return createResourceResponseEntity(filePath);
    }

    /**
     * 获取当前基准路径
     */
    @GetMapping("/spy/current")
    public ResponseEntity<PathInfo> getCurrentPath() {
        return ResponseEntity.ok(new PathInfo(fileBasePathProperties.getBasePath()));
    }

    /**
     * 路径信息
     */
    public record PathInfo(String path) {
    }

    /**
     * 列出目录下的所有文件（结构化数据）
     */
    @GetMapping("/spy/list")
    public ResponseEntity<List<FileInfo>> listFiles(@RequestParam(required = false) String dir) {
        String targetDir = dir != null ? dir : fileBasePathProperties.getBasePath();
        List<IFileOperationService.FileDetail> files = fileOperationService.listFiles(targetDir);

        List<FileInfo> fileList = files.stream()
                .map(f -> FileInfo.builder()
                        .name(f.name())
                        .path(f.path())
                        .directory(f.directory())
                        .size(f.size())
                        .lastModified(f.lastModified())
                        .build())
                .toList();

        return ResponseEntity.ok(fileList);
    }

    /**
     * 打包下载文件/文件夹
     */
    @PostMapping("/spy/pack")
    public ResponseEntity<Resource> packDownload(@RequestBody PackDownloadReq req) {
        List<String> paths = req.getPaths();
        if (paths == null || paths.isEmpty()) {
            throw new IllegalArgumentException("请选择要打包的文件或文件夹");
        }

        // 验证所有路径
        IFileOperationService.ValidationResult validation = fileOperationService.validatePaths(paths);
        if (!validation.isValid()) {
            throw new IllegalArgumentException("部分路径无效: " + validation.invalidPaths());
        }

        // 获取密码信息
        FilePackService.PasswordInfo passwordInfo = filePackService.getZipPassword();

        // 创建加密 ZIP
        File zipFile;
        try {
            zipFile = filePackService.createEncryptedZip(paths);
        } catch (Exception e) {
            throw new RuntimeException("创建压缩包失败: " + e.getMessage(), e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(zipFile.getName(), StandardCharsets.UTF_8)
                .build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Access-Control-Expose-Headers", "X-Using-Default-Password");

        if (passwordInfo.usingDefault()) {
            headers.set("X-Using-Default-Password", "true");
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(new FileSystemResource(zipFile));
    }

    private ResponseEntity<Resource> createResourceResponseEntity(Path filePath) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment()
                .filename(filePath.getFileName().toString(), StandardCharsets.UTF_8)
                .build());
        Resource resource = new FileSystemResource(filePath);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers)
                .body(resource);
    }
}
