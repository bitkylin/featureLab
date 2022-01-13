package cc.bitky.test.idea.integration.service.externalconfig;

import cc.bitky.test.idea.integration.service.dto.ConfigType;
import cc.bitky.test.idea.integration.service.dto.EnableSimbusiness;
import cc.bitky.test.idea.integration.service.dto.EnableSimbusinessValue;
import cc.bitky.test.idea.integration.service.dto.SimbusinessKvInfo;
import cc.bitky.test.idea.integration.service.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

/**
 * @author limingliang
 */
public class SimbusinessReader {

    private static ModelMapper modelMapper = new ModelMapper();

    public static List<SimbusinessKvInfo> read(Path path) {
        List<SimbusinessKvInfo> res = Lists.newArrayList();
        List<EnableSimbusiness> enableSimbusinesseList = Lists.newArrayList();
        res.add(getBuild(enableSimbusinesseList));
        try (DirectoryStream<Path> configPathStream = Files.newDirectoryStream(path)) {
            for (Path configPath : configPathStream) {
                enableSimbusinesseList.add(extracted(configPath));
            }
        } catch (IOException e) {
            throw new RuntimeException("文件IO异常", e);
        }
        return res;
    }

    private static SimbusinessKvInfo getBuild(List<EnableSimbusiness> enableSimbusinesseList) {
        SimbusinessKvInfo simbusinessKvInfo = new SimbusinessKvInfo();
        simbusinessKvInfo.setEnableConfigs(enableSimbusinesseList);
        simbusinessKvInfo.setConfigType(ConfigType.SIM_BIZ_CONF);
        simbusinessKvInfo.setApplication("lendengine");
        simbusinessKvInfo.setEnv("local");
        return simbusinessKvInfo;
    }

    private static EnableSimbusiness extracted(Path configPath) throws IOException {
        Path configFileName = configPath.getFileName();
        List<EnableSimbusinessValue> list = Lists.newArrayList();
        EnableSimbusiness enableSimbusiness = null;
        try (DirectoryStream<Path> configItemStream = Files.newDirectoryStream(configPath)) {
            Iterator<Path> iterator = configItemStream.iterator();
            while (iterator.hasNext()) {
                Path configItemPath = iterator.next();
                String str = FileUtils.doRead(configItemPath);
                if (configItemPath.getFileName().toString().endsWith("info.json")) {
                    enableSimbusiness = resolveEnableSimbusiness(str);
                } else {
                    EnableSimbusinessValue value = resolveEnableSimbusinessValue(configFileName, configItemPath, str);
                    list.add(value);
                }
            }
            enableSimbusiness.setValues(list);
        }
        return enableSimbusiness;
    }

    private static EnableSimbusiness resolveEnableSimbusiness(String str) {
        return JSON.parseObject(str, EnableSimbusiness.class);
    }

    private static EnableSimbusinessValue resolveEnableSimbusinessValue(Path configFileName, Path configItemPath, String str) {
        Path configValueFileName = configItemPath.getFileName();
        String infoStr = StringUtils.removeStart(configValueFileName.toString(), configFileName.toString() + ".");
        infoStr = StringUtils.removeEnd(infoStr, ".json");
        String[] arr = StringUtils.split(infoStr, '.');

        EnableSimbusinessValue value = EnableSimbusinessValue.builder()
                .version(Integer.parseInt(arr[0]))
                .defStatus(Boolean.parseBoolean(arr[1]))
                .value(str)
                .build();
        return value;
    }
}
