package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.BitkylinConfigState;
import cc.bitky.test.idea.integration.ui.IntegrationTestConfig;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author limingliang
 */
public class BitkylinConfigurable implements Configurable {

    private static IntegrationTestConfig integrationTestConfig;

    private BitkylinConfigurable() {
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "集成测试";
    }

    @Override
    public @Nullable JComponent createComponent() {
        integrationTestConfig = IntegrationTestConfig.instance();
        integrationTestConfig.init();
        return integrationTestConfig.getMainPanel();
    }

    @Override
    public boolean isModified() {
        BitkylinConfigState state = BitkylinPersistentStateComponent.getInstance();
        if (state == null) {
            return true;
        }
        if (Boolean.FALSE.equals(state.getModified())) {
            return false;
        }
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        String pathText = integrationTestConfig.fetchPathText();
        BitkylinConfigState state = BitkylinPersistentStateComponent.getInstance();
        state.setModified(false);
        state.setPath(pathText);
    }
}
