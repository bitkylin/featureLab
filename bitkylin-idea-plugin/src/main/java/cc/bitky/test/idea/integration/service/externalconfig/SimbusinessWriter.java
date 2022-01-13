package cc.bitky.test.idea.integration.service.externalconfig;

import cc.bitky.test.idea.integration.service.dto.EnableSimbusiness;
import cc.bitky.test.idea.integration.service.dto.EnableSimbusinessValue;
import cc.bitky.test.idea.integration.service.dto.SimbusinessKvInfo;
import cc.bitky.test.idea.integration.service.util.LELog;
import cc.bitky.test.idea.integration.service.util.dto.LogPayLoad;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.util.List;

import static cc.bitky.test.idea.integration.service.util.FileUtils.*;


/**
 * @author limingliang
 */
@Slf4j
public class SimbusinessWriter {

    public static void write(Path path, List<SimbusinessKvInfo> infoList) {
        createNewDir(path);
        SimbusinessKvInfo info = infoList.get(0);
        List<EnableSimbusiness> enabledList = info.getEnableConfigs();
        for (EnableSimbusiness enableSimbusiness : enabledList) {
            check(enableSimbusiness);
            write(path, enableSimbusiness);
        }
    }

    private static void check(EnableSimbusiness enableSimbusiness) {
        for (EnableSimbusinessValue value : enableSimbusiness.getValues()) {
            if (value.getVersion() == null || value.getDefStatus() == null || StringUtils.isBlank(value.getValue())) {
                LELog.warn(log, null, () -> LogPayLoad.of("enableSimbusiness的value格式有误", value));
                throw new RuntimeException("enableSimbusiness的value格式有误");
            }
        }
    }

    private static void write(Path path, EnableSimbusiness simbusiness) {
        simbusiness = JSON.parseObject(JSON.toJSONString(simbusiness), EnableSimbusiness.class);
        if (inValid(simbusiness.getConfig())) {
            return;
        }
        Path configPath = path.resolve(simbusiness.getConfig());
        createNewDir(configPath);
        for (EnableSimbusinessValue value : simbusiness.getValues()) {
            Path versionPath = configPath.resolve(simbusiness.getConfig() + "." + value.getVersion() + "." + value.getDefStatus() + ".json");
            doWrite(versionPath, prettyConfplusValue(value.getValue()));
        }
        simbusiness.setValues(null);
        doWrite(configPath.resolve(simbusiness.getConfig() + ".info.json"), JSON.toJSONString(simbusiness, SerializerFeature.PrettyFormat));
    }

    private static boolean inValid(String fileName) {
        if (fileName.contains("|")) {
            return true;
        }
        return false;
    }

}
