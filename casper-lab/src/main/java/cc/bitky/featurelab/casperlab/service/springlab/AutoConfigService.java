package cc.bitky.featurelab.casperlab.service.springlab;

import cc.bitky.featurelab.casperlab.config.propertybind.PropertyBindConfig;
import cc.bitky.featurelab.casperlab.config.propertybind.PropertyBindExternalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @author liMingLiang
 * @date 2019/9/14
 */
@Service
public class AutoConfigService implements EnvironmentAware {

    @Autowired
    private PropertyBindConfig propertyBindConfig;

    @Autowired
    private PropertyBindExternalConfig propertyBindExternalConfig;
    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public PropertyBindConfig getPropertyBindConfig() {
        return propertyBindConfig;
    }

    public PropertyBindExternalConfig getPropertyBindExternalConfig() {
        return propertyBindExternalConfig;
    }


    public static void main(String[] args) throws Exception {
        File root =  new File("C:\\Users\\bitkylin\\Downloads\\HEU_KMS_Activator_v23.0.0.zip");
        System.out.println();
    }

    protected final void createArchive() throws Exception {
        ProtectionDomain protectionDomain = getClass().getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URI location = (codeSource != null ? codeSource.getLocation().toURI() : null);
        String path = (location != null ? location.getSchemeSpecificPart() : null);
        if (path == null) {
            throw new IllegalStateException("Unable to determine code source archive");
        }
        File root = new File(path);
        if (!root.exists()) {
            throw new IllegalStateException(
                    "Unable to determine code source archive from " + root);
        }
    }
}
