package cc.bitky.test.idea.integration.ui;

import cc.bitky.test.idea.integration.config.BitkylinPersistentStateComponent;
import cc.bitky.test.idea.integration.dto.BitkylinConfigState;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IntegrationTestConfig {

    private static final IntegrationTestConfig integrationTestConfig = new IntegrationTestConfig();

    private TextFieldWithBrowseButton fileFolderBtn;

    private JPanel mainPanel;

    public static IntegrationTestConfig instance() {
        return integrationTestConfig;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String fetchPathText() {
        return fileFolderBtn.getText();
    }

    public void init() {
        fileFolderBtn.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor()) {
        });
        BitkylinConfigState config = BitkylinPersistentStateComponent.getInstance();
        config.setModified(true);
        if (config.getPath() != null) {
            fileFolderBtn.setText(config.getPath());
        }
    }
}
