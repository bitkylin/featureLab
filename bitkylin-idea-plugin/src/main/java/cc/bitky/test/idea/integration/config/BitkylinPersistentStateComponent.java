package cc.bitky.test.idea.integration.config;

import cc.bitky.test.idea.integration.dto.BitkylinConfigState;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author limingliang
 */
@State(name = "bitkylin_integration_test", storages = {@Storage("bitkylin_integration_test_storage.xml")})
public class BitkylinPersistentStateComponent implements PersistentStateComponent<BitkylinConfigState> {

    private static BitkylinPersistentStateComponent staticComponent = new BitkylinPersistentStateComponent() {{
        loadState(new BitkylinConfigState());
    }};

    private BitkylinConfigState state;

    @Nullable
    public static BitkylinConfigState getInstance() {
        BitkylinPersistentStateComponent stateComponent = ServiceManager.getService(BitkylinPersistentStateComponent.class);
        if (stateComponent == null) {
            stateComponent = staticComponent;
        }
        BitkylinConfigState state = stateComponent.getState();
        if (state == null) {
            stateComponent.loadState(new BitkylinConfigState());
        }
        return stateComponent.getState();
    }

    @Override
    public @Nullable BitkylinConfigState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull BitkylinConfigState state) {
        this.state = state;
    }
}
