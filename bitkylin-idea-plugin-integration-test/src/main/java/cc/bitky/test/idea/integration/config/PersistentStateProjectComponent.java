package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author limingliang
 */
@State(name = "bitkylin_integration_test_project", storages = {@Storage("bitkylin_integration_test_storage_project.xml")})
public class PersistentStateProjectComponent implements PersistentStateComponent<ConfigProjectState> {

    private ConfigProjectState state;

    @Nullable
    public static ConfigProjectState getInstance( Project project) {
        PersistentStateProjectComponent stateComponent = project.getService(PersistentStateProjectComponent.class);
        ConfigProjectState state = stateComponent.getState();
        if (state == null) {
            stateComponent.loadState(new ConfigProjectState());
        }
        return stateComponent.getState();
    }

    @Override
    public @Nullable ConfigProjectState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull ConfigProjectState state) {
        this.state = state;
    }
}
