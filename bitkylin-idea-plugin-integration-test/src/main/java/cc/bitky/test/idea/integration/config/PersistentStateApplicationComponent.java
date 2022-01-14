package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.ConfigApplicationState;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author limingliang
 */
@State(name = "bitkylin-integration-test-application", storages = {@Storage("bitkylin-integration-test-application.xml")})
public class PersistentStateApplicationComponent implements PersistentStateComponent<ConfigApplicationState> {

    private ConfigApplicationState state;

    @Nullable
    public static ConfigApplicationState getInstance() {
        PersistentStateApplicationComponent stateComponent = ApplicationManager.getApplication().getService(PersistentStateApplicationComponent.class);
        ConfigApplicationState state = stateComponent.getState();
        if (state == null) {
            stateComponent.loadState(new ConfigApplicationState());
        }
        return stateComponent.getState();
    }

    @Override
    public @Nullable ConfigApplicationState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull ConfigApplicationState state) {
        this.state = state;
    }
}
