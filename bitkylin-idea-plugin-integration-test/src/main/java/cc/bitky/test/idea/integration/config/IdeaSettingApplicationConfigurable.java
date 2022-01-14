package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.ConfigApplicationState;
import cc.bitky.test.idea.integration.ui.IdeaSettingApplicationUi;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IdeaSettingApplicationConfigurable implements Configurable {

    private ConfigApplicationState applicationState;
    private IdeaSettingApplicationUi settingApplicationUi;

    private IdeaSettingApplicationConfigurable()  {
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "集成测试Application";
    }

    @Override
    public @Nullable JComponent createComponent() {
        ConfigApplicationState state = PersistentStateApplicationComponent.getInstance();
        applicationState = JSON.parseObject(JSON.toJSONString(state), ConfigApplicationState.class);
        settingApplicationUi = IdeaSettingApplicationUi.instance();
        settingApplicationUi.init();
        return settingApplicationUi.getMainPanel();
    }

    @Override
    public boolean isModified() {
        ConfigApplicationState state = PersistentStateApplicationComponent.getInstance();
        settingApplicationUi.updateState(applicationState);
        return !state.equals(applicationState);
    }

    @Override
    public void apply() throws ConfigurationException {
        ConfigApplicationState state = PersistentStateApplicationComponent.getInstance();
        settingApplicationUi.updateState(state);
    }
}
