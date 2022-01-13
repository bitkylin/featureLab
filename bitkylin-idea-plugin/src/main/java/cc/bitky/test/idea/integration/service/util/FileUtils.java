package cc.bitky.test.idea.integration.service.util;

import cc.bitky.test.idea.integration.service.util.dto.LogPayLoad;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.Set;

/**
 * @author liMingLiang
 * @date 2020/7/23
 */
@Slf4j
public class FileUtils {

    private FileUtils() {
    }

    public static String concatUri(String first, String second) {
        first = StringUtils.removeEnd(first, "/");
        second = StringUtils.removeStart(second, "/");
        return first + "/" + second;
    }

    public static String doRead(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(builder::append);
            return StringUtils.trim(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("文件IO异常", e);
        }
    }

    @SneakyThrows
    public static Properties getProperties(Path propertiePath) {
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(propertiePath, StandardCharsets.UTF_8));
        return properties;
    }

    public static void doWrite(Path configPath, String value) {
        try (BufferedWriter writer = Files.newBufferedWriter(configPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(value);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("文件IO异常", e);
        }
    }

    public static void createNewDir(Path path) {
        FileUtils.delFileOrDir(path);
        FileUtils.mkDir(path);
    }

    public static String prettyConfplusValue(String value) {
        String valuePretty = StringUtils.trim((String) value);
        if (StringUtils.startsWith(valuePretty, "{")) {
            JSONObject valueJson = JSON.parseObject(valuePretty);
            valuePretty = JSON.toJSONString(valueJson, SerializerFeature.PrettyFormat);
        } else if (StringUtils.startsWith(valuePretty, "[")) {
            JSONArray valueJson = JSON.parseArray(valuePretty);
            valuePretty = JSON.toJSONString(valueJson, SerializerFeature.PrettyFormat);
        }
        return valuePretty;
    }

    public static Set<String> fetchConfigNameSetFromFileSystem(Path filePath) {
        Set<String> strSet = Sets.newHashSet();
        if (!Files.exists(filePath)) {
            return strSet;
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            reader.lines().forEach(item -> {
                if (StringUtils.isBlank(item)) {
                    return;
                }
                if (StringUtils.startsWith(item, "#")) {
                    return;
                }
                strSet.add(item);
            });
        } catch (IOException e) {
            throw new RuntimeException("文件IO异常", e);
        }
        return strSet;
    }

    public static void mkDir(Path path) {
        File file = path.toFile();
        if (file.exists() && file.isDirectory()) {
            return;
        }
        if (file.mkdirs()) {
            LELog.info(log, () -> LogPayLoad.of("创建指定目录成功", path));
        } else {
            LogPayLoad msg = LogPayLoad.of("创建指定目录失败", path);
            LELog.info(log, () -> msg);
            throw new RuntimeException(JSON.toJSONString(msg));
        }
    }

    public static boolean delFileOrDir(Path path) {
        if (!path.toString().contains("\\src\\test\\resources\\configlocal")) {
            throw new RuntimeException("不能删除非法的路径: " + path);
        }

        File file = path.toFile();
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return false;
            }
            for (File f : files) {
                delFileOrDir(f.toPath());
            }
        }
        return file.delete();
    }
}
