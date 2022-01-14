package cc.bitky.test.idea.integration.service.constant;

import java.nio.file.Path;

/**
 * @author limingliang
 */
public class TradeTestPath {

    /**
     * 应用源代码resources路径 by Java
     */
    private static final String URI_LOCAL_SOURCE_RESOURCE_JAVA_PATH = System.getProperty("user.dir") + "/lendengine/src/test/resources";

    /**
     * configlocal
     */
    private static final String RELATIVE_PATH_CONFIG_LOCAL = "configlocal";

    /**
     * simBusiness 目录
     */
    private static final String RELATIVE_PATH_SIM_BUSINESS = "simbusiness";

    /**
     * sysConfig-test 目录
     */
    private static final String RELATIVE_PATH_SYS_CONFIG = "sysconfig.properties.json";


    private TradeTestPath() {
    }

    /**
     * simBusiness by Java
     */
    public static Path simBusinessByJava(Path path) {
        return path.resolve(RELATIVE_PATH_CONFIG_LOCAL).resolve(RELATIVE_PATH_SIM_BUSINESS);
    }

    /**
     * sysConfig by Java
     */
    public static Path sysConfigByJava(Path path) {
        return path.resolve(RELATIVE_PATH_CONFIG_LOCAL).resolve(RELATIVE_PATH_SYS_CONFIG);
    }

}
