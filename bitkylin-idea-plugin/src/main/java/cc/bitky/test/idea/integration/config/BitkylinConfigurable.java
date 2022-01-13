package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.BitkylinConfigState;
import cc.bitky.test.idea.integration.ui.IntegrationTestConfigUi;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author limingliang
 */
public class BitkylinConfigurable implements Configurable {

    private BitkylinConfigState stateUi;
    private IntegrationTestConfigUi integrationTestConfigUi;

    private BitkylinConfigurable() {
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "集成测试";
    }

    @Override
    public @Nullable JComponent createComponent() {
        BitkylinConfigState state = BitkylinPersistentStateComponent.getInstance();
        stateUi = JSON.parseObject(JSON.toJSONString(state), BitkylinConfigState.class);
        integrationTestConfigUi = IntegrationTestConfigUi.instance();
        integrationTestConfigUi.init();
        return integrationTestConfigUi.getMainPanel();
    }

    @Override
    public boolean isModified() {
        BitkylinConfigState state = BitkylinPersistentStateComponent.getInstance();
        integrationTestConfigUi.updateState(stateUi);
        return !state.equals(stateUi);
    }

    @Override
    public void apply() throws ConfigurationException {
        BitkylinConfigState state = BitkylinPersistentStateComponent.getInstance();
        integrationTestConfigUi.updateState(state);
    }
}
