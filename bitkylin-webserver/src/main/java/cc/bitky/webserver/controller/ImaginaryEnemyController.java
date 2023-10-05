package cc.bitky.webserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

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
     * 元素刺探
     */
    @GetMapping("/spyElement/" + "**")
    public ResponseEntity<Resource> spyElement(@RequestParam String path) {
        Path filePath = Paths.get(path);
        return createResourceResponseEntity(filePath);
    }

    @GetMapping("/spy/doc")
    public ResponseEntity<Resource> spyDoc() {
        return createResourceResponseEntity(Paths.get("/Users/lml/Documents/backup/doc"));
    }

    @GetMapping("/spy/dev")
    public ResponseEntity<Resource> spyDev() {
        return createResourceResponseEntity(Paths.get("/Users/lml/Documents/backup/dev"));
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

}
