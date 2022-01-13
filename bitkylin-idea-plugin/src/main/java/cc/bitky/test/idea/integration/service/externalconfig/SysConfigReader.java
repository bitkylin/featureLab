package cc.bitky.test.idea.integration.service.externalconfig;

import cc.bitky.test.idea.integration.service.dto.SysGroupDto;
import cc.bitky.test.idea.integration.service.util.FileUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;

/**
 * @author limingliang
 */
@Slf4j
public class SysConfigReader {

    public static List<SysGroupDto> read(Path path) {
        String str = FileUtils.doRead(path);
        return JSON.parseArray(str, SysGroupDto.class);
    }
}
