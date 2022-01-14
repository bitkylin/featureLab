package cc.bitky.test.idea.integration.util;

import cc.bitky.test.idea.integration.config.PersistentStateGlobalComponent;
import cc.bitky.test.idea.integration.config.PersistentStateProjectComponent;
import cc.bitky.test.idea.integration.dto.ConfigGlobalState;
import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author limingliang
 */
public class BitkyUtils {

    private BitkyUtils() {
    }

    public static Optional<ConfigProjectState> solveConfigProjectState(Project project) {
        ConfigProjectState state = PersistentStateProjectComponent.getInstance(project);
        if (StringUtils.isBlank(state.getProjectName())) {
            Messages.showMessageDialog("请先在IDEA的配置页面配置应用名", "无法执行", Messages.getInformationIcon());
            return Optional.empty();
        }
        if (StringUtils.isBlank(state.getPath())) {
            Messages.showMessageDialog("请先在IDEA的配置页面配置路径", "无法执行", Messages.getInformationIcon());
            return Optional.empty();
        }
        return Optional.of(state);
    }

    public static Optional<String> solveSimBusinessTemplateUrl() {
        ConfigGlobalState state = PersistentStateGlobalComponent.getInstance();
        if (StringUtils.isBlank(state.getSimBusinessTemplateUrl())) {
            Messages.showMessageDialog("请先在IDEA的配置页面配置-简单业务配置URL模板", "无法执行", Messages.getInformationIcon());
            return Optional.empty();
        }
        return Optional.of(state.getSimBusinessTemplateUrl());
    }

    public static Optional<String> solveSysConfigTemplateUrl() {
        ConfigGlobalState state = PersistentStateGlobalComponent.getInstance();
        if (StringUtils.isBlank(state.getSysConfigTemplateUrl())) {
            Messages.showMessageDialog("请先在IDEA的配置页面配置-系统配置URL模板", "无法执行", Messages.getInformationIcon());
            return Optional.empty();
        }
        return Optional.of(state.getSysConfigTemplateUrl());
    }
}
