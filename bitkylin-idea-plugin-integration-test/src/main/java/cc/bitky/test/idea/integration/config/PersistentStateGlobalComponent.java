package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.ConfigGlobalState;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author limingliang
 */
@State(name = "bitkylin_integration_test_global", storages = {@Storage("bitkylin_integration_test_storage_global.xml")})
public class PersistentStateGlobalComponent implements PersistentStateComponent<ConfigGlobalState> {

    private ConfigGlobalState state;

    @Nullable
    public static ConfigGlobalState getInstance() {
        PersistentStateGlobalComponent stateComponent = ApplicationManager.getApplication().getService(PersistentStateGlobalComponent.class);
        ConfigGlobalState state = stateComponent.getState();
        if (state == null) {
            stateComponent.loadState(new ConfigGlobalState());
        }
        return stateComponent.getState();
    }

    @Override
    public @Nullable ConfigGlobalState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull ConfigGlobalState state) {
        this.state = state;
    }
}
