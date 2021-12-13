package cc.bitky.featurelab.casperlab.externaltools.dto;

import lombok.Data;

import java.io.File;

/**
 * @author limingliang
 */
@Data
public class RepoPullInfo {

    private String name;
    private String path;
    private String branch;

    private String normalMsg;
    private String errorMsg;

    public static RepoPullInfo of(File file) {
        RepoPullInfo repo = new RepoPullInfo();
        repo.setName(file.getName());
        repo.setPath(file.getAbsolutePath());
        return repo;
    }

    public String alertName(String name) {
        this.name = name;
        return name;
    }

    public String alertPath(String path) {
        this.path = path;
        return path;
    }

    public String alertBranch(String branch) {
        this.branch = branch;
        return branch;
    }

    public String alertNormalMsg(String normalMsg) {
        this.normalMsg = normalMsg;
        return normalMsg;
    }

    public String alertErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return errorMsg;
    }
}
