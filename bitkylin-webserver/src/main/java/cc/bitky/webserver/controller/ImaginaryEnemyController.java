package cc.bitky.webserver.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 假想敌服务
 *
 * @author limingliang
 */
@Controller
@RestController
@Slf4j
public class ImaginaryEnemyController {

    /**
     * 胡同
     */
    @Setter
    private static String alley = "";

    /**
     * 伙计刺探
     */
    @GetMapping("/spyGuy/" + "**")
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
            return ResponseEntity.status(400)
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
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * 列出目录下的所有文件
     */
    @GetMapping("/spy/list")
    public ResponseEntity<List<String>> listFiles(@RequestParam(required = false) String dir) {
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

            List<String> fileList = Arrays.stream(files)
                    .map(file -> {
                        String type = file.isDirectory() ? "[目录] " : "[文件] ";
                        return type + file.getName();
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileList);
        });
    }

}
