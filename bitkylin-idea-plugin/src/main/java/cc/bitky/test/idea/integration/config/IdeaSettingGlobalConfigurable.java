package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.ConfigGlobalState;
import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import cc.bitky.test.idea.integration.ui.IdeaSettingGlobalUi;
import cc.bitky.test.idea.integration.ui.IdeaSettingProjectUi;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IdeaSettingGlobalConfigurable implements Configurable {

    private ConfigGlobalState globalState;
    private IdeaSettingGlobalUi settingGlobalUi;

    private IdeaSettingGlobalConfigurable()  {
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "集成测试全局";
    }

    @Override
    public @Nullable JComponent createComponent() {
        ConfigGlobalState state = PersistentStateGlobalComponent.getInstance();
        globalState = JSON.parseObject(JSON.toJSONString(state), ConfigGlobalState.class);
        settingGlobalUi = IdeaSettingGlobalUi.instance();
        settingGlobalUi.init();
        return settingGlobalUi.getMainPanel();
    }

    @Override
    public boolean isModified() {
        ConfigGlobalState state = PersistentStateGlobalComponent.getInstance();
        settingGlobalUi.updateState(globalState);
        return !state.equals(globalState);
    }

    @Override
    public void apply() throws ConfigurationException {
        ConfigGlobalState state = PersistentStateGlobalComponent.getInstance();
        settingGlobalUi.updateState(state);
    }
}
