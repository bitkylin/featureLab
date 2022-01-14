package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import cc.bitky.test.idea.integration.ui.IdeaSettingProjectUi;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IdeaSettingProjectConfigurable implements Configurable {

    private ConfigProjectState stateUi;
    private IdeaSettingProjectUi integrationTestConfigUi;
    private Project project;

    private IdeaSettingProjectConfigurable(Project project)  {
        this.project = project;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "集成测试Project";
    }

    @Override
    public @Nullable JComponent createComponent() {
        ConfigProjectState state = PersistentStateProjectComponent.getInstance(project);
        stateUi = JSON.parseObject(JSON.toJSONString(state), ConfigProjectState.class);
        integrationTestConfigUi = IdeaSettingProjectUi.instance();
        integrationTestConfigUi.init(project);
        return integrationTestConfigUi.getMainPanel();
    }

    @Override
    public boolean isModified() {
        ConfigProjectState state = PersistentStateProjectComponent.getInstance(project);
        integrationTestConfigUi.updateState(stateUi);
        return !state.equals(stateUi);
    }

    @Override
    public void apply() throws ConfigurationException {
        ConfigProjectState state = PersistentStateProjectComponent.getInstance(project);
        integrationTestConfigUi.updateState(state);
    }
}
