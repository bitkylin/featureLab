package cc.bitky.featurelab.casperlab.externaltools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对多个git仓库批量执行命令 {git pull}
 *
 * @author limingliang
 */
public class GitBatchPull {

    public static void main(String[] args) throws IOException {
        List<File> fileList = new ArrayList<>();
        listFile(fileList, Paths.get("D:\\develop\\project").toFile(), 0, 5);
        System.out.println("-------------------");
        System.out.println("共有仓库个数：" + fileList.size());
        System.out.println("-------------------");
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            System.out.println("\n\n>>>" + i + ": " + file.getAbsolutePath());
            Process process = Runtime.getRuntime().exec("git pull", null, file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            reader.lines().forEach(System.out::println);
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            reader.lines().forEach(str -> System.out.println("「ERROR」" + str));
        }

    }

    private static void listFile(List<File> res, File file, int level, int top) {
        if (!file.isDirectory()) {
            return;
        }
        if (".git".equals(file.getName())) {
            return;
        }
        if (Arrays.stream(file.listFiles()).anyMatch(fileItem -> ".git".equals(fileItem.getName()))) {
            res.add(file);
        }
        if (level >= top) {
            return;
        }
        List<File> fileList = new ArrayList<>(Arrays.asList(file.listFiles()));
        for (File fileSub : fileList) {
            listFile(res, fileSub, level + 1, top);
        }
    }
}
