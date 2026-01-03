package cc.bitky.webserver.controller;

import cc.bitky.webserver.dto.FileInfo;
import cc.bitky.webserver.dto.PackDownloadReq;
import cc.bitky.webserver.service.FilePackService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 假想敌服务
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ImaginaryEnemyController {

    /**
     * 胡同
     */
    @Setter
    private static String alley = "";

    private final FilePackService filePackService;

    /**
     * 伙计刺探
     */
    @GetMapping("/spyGuy/**")
    public ResponseEntity<Resource> spyElement(@RequestParam String guy) {
        return run(() -> createResourceResponseEntity(searchGuy(alley, guy)));
    }

    /**
     * 刺探医生
     */
    @GetMapping("/spy/doc")
    public ResponseEntity<Resource> spyDoc() {
        return run(() -> createResourceResponseEntity(searchGuy(alley, "doc")));
    }

    /**
     * 刺探德芙
     */
    @GetMapping("/spy/dev")
    public ResponseEntity<Resource> spyDev() {
        return run(() -> createResourceResponseEntity(searchGuy(alley, "dev")));
    }

    /**
     * 查找指定的伙计
     */
    private Path searchGuy(String alley, String guy) {
        Path houseNumber = Paths.get(alley, guy);
        File house = houseNumber.toFile();
        if (!house.exists()) {
            throw new RuntimeException("伙计不在家:" + houseNumber);
        }
        if (!house.canRead()) {
            throw new RuntimeException("伙计拒绝见面:" + houseNumber);
        }
        return houseNumber;
    }

    private ResponseEntity run(Supplier<ResponseEntity> runnable) {
        try {
            return runnable.get();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.parseMediaType("text/plain; charset=utf-8"))
                    .body(e.getMessage());
        }
    }

    private ResponseEntity<Resource> createResourceResponseEntity(Path filePath) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename(filePath.getFileName().toString(), StandardCharsets.UTF_8)
                    .build());
            Resource resource = new FileSystemResource(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            log.error("创建资源响应失败", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * 获取当前基准路径
     */
    @GetMapping("/spy/current")
    public ResponseEntity<Map<String, String>> getCurrentPath() {
        return ResponseEntity.ok(Map.of("path", alley));
    }

    /**
     * 列出目录下的所有文件（结构化数据）
     */
    @GetMapping("/spy/list")
    public ResponseEntity<List<FileInfo>> listFiles(@RequestParam(required = false) String dir) {
        return run(() -> {
            String targetDir = dir != null ? dir : alley;
            File directory = new File(targetDir);

            if (!directory.exists()) {
                throw new RuntimeException("目录不存在: " + targetDir);
            }

            if (!directory.isDirectory()) {
                throw new RuntimeException("路径不是目录: " + targetDir);
            }

            File[] files = directory.listFiles();
            if (files == null) {
                throw new RuntimeException("无法读取目录内容: " + targetDir);
            }

            List<FileInfo> fileList = Arrays.stream(files)
                    .map(file -> FileInfo.builder()
                            .name(file.getName())
                            .path(file.getAbsolutePath())
                            .directory(file.isDirectory())
                            .size(file.isDirectory() ? 0 : file.length())
                            .lastModified(file.lastModified())
                            .build())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileList);
        });
    }

    /**
     * 打包下载文件/文件夹
     */
    @PostMapping("/spy/pack")
    public ResponseEntity<Resource> packDownload(@RequestBody PackDownloadReq req) {
        return run(() -> {
            List<String> paths = req.getPaths();
            if (paths == null || paths.isEmpty()) {
                throw new RuntimeException("请选择要打包的文件或文件夹");
            }

            // 验证所有路径存在且可读
            for (String pathStr : paths) {
                Path path = Path.of(pathStr);
                File file = path.toFile();
                if (!file.exists()) {
                    throw new RuntimeException("路径不存在: " + pathStr);
                }
                if (!file.canRead()) {
                    throw new RuntimeException("无法读取: " + pathStr);
                }
            }

            // 获取密码信息
            FilePackService.PasswordInfo passwordInfo = filePackService.getZipPassword();

            // 创建加密 ZIP
            File zipFile;
            try {
                zipFile = filePackService.createEncryptedZip(paths);
            } catch (Exception e) {
                throw new RuntimeException("创建压缩包失败: " + e.getMessage());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename(zipFile.getName(), StandardCharsets.UTF_8)
                    .build());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 允许前端读取自定义响应头
            headers.set("Access-Control-Expose-Headers", "X-Using-Default-Password");

            // 添加是否使用默认密码的响应头
            if (passwordInfo.usingDefault()) {
                headers.set("X-Using-Default-Password", "true");
            }

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new FileSystemResource(zipFile));
        });
    }

}
