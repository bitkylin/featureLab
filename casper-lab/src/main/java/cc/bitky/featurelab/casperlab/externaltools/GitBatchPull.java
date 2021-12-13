package cc.bitky.featurelab.casperlab.externaltools;

import cc.bitky.featurelab.casperlab.externaltools.dto.RepoPullInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对多个git仓库批量执行命令 {git pull}
 *
 * @author limingliang
 */
public class GitBatchPull {

    public static void main(String[] args) throws IOException {
        List<RepoPullInfo> res = new ArrayList<>();

        List<File> fileList = new ArrayList<>();
        listFile(fileList, Paths.get("D:\\develop\\project").toFile(), 0, 5);
        System.out.println("-------------------");
        System.out.println("共有仓库个数：" + fileList.size());
        System.out.println("-------------------");
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            RepoPullInfo repo = RepoPullInfo.of(file);
            res.add(repo);

            System.out.println("\n\n>>>" + i + ": " + repo.getPath());
            Runtime.getRuntime().exec("git switch main", null, file);
            Runtime.getRuntime().exec("git switch master", null, file);
            Process process = Runtime.getRuntime().exec("git branch", null, file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String branch = repo.alertBranch(StringUtils.trimToEmpty(StringUtils.removeStart(reader.readLine(), "*")));
            System.out.println("\n\n>>> branch: " + branch);
            if (!Lists.newArrayList("master", "main").contains(branch)) {
                System.out.println("跳过");
                continue;
            }

            process = Runtime.getRuntime().exec("git pull", null, file);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println("---「NORMAL」---");
            System.out.println(repo.alertNormalMsg(reader.lines().collect(Collectors.joining())));

            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            System.out.println("---「ERROR」---");
            System.out.println(repo.alertErrorMsg(reader.lines().collect(Collectors.joining())));
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
