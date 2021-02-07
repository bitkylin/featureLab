package cc.bitky.featurelab.casperlab.service.io;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author liMingLiang
 * @date 2019-05-13
 */
@Slf4j
public class KyFile {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/limingliang/Downloads", "test.txt");
        if (path.toFile().isFile()) {
            List<String> strings = Files.readAllLines(path, StandardCharsets.UTF_8);
            log.info(JSON.toJSONString(strings));
        }
    }
}
