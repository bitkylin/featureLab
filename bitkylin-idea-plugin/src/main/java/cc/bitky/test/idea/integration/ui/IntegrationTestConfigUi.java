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
public class IntegrationTestConfigUi {

    private static final IntegrationTestConfigUi INTEGRATION_TEST_CONFIG_UI = new IntegrationTestConfigUi();

    private TextFieldWithBrowseButton fileFolderBtn;

    private JPanel mainPanel;

    public static IntegrationTestConfigUi instance() {
        return INTEGRATION_TEST_CONFIG_UI;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void init() {
        fileFolderBtn.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor()) {
        });
        BitkylinConfigState config = BitkylinPersistentStateComponent.getInstance();
        if (config.getPath() != null) {
            fileFolderBtn.setText(config.getPath());
        }
    }

    private String fetchPathText() {
        return fileFolderBtn.getText();
    }

    public void updateState(BitkylinConfigState state) {
        state.setPath(fetchPathText());
    }
}
