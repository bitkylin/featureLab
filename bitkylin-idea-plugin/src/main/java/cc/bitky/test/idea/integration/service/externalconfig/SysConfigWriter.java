package cc.bitky.test.idea.integration.service.externalconfig;

import cc.bitky.test.idea.integration.service.dto.SysGroupDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.List;

import static cc.bitky.test.idea.integration.service.util.FileUtils.createNewDir;
import static cc.bitky.test.idea.integration.service.util.FileUtils.doWrite;


/**
 * @author limingliang
 */
@Slf4j
public class SysConfigWriter {

    public static void write(Path path, List<SysGroupDto> sysGroupDtoList) {
        doWrite(path, JSON.toJSONString(sysGroupDtoList, SerializerFeature.PrettyFormat));
    }
}
