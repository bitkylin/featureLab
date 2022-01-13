package cc.bitky.test.idea.integration.service.util;

import cc.bitky.test.idea.integration.config.BitkylinPersistentStateComponent;
import cc.bitky.test.idea.integration.dto.BitkylinConfigState;
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

    public static Optional<Path> solvePath() {
        BitkylinConfigState state = BitkylinPersistentStateComponent.getInstance();
        String path = state.getPath();
        if (StringUtils.isBlank(path)) {
            return Optional.empty();
        }
        return Optional.of(Paths.get(path));
    }
}
