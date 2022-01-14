package cc.bitky.test.idea.integration.ui;

import cc.bitky.test.idea.integration.config.PersistentStateProjectComponent;
import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IdeaSettingProjectUi {

    private static final IdeaSettingProjectUi settingProjectUi = new IdeaSettingProjectUi();

    private TextFieldWithBrowseButton fileFolderBtn;

    private JPanel mainPanel;

    private JTextField appName;

    public static IdeaSettingProjectUi instance() {
        return settingProjectUi;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void init(Project project) {
        fileFolderBtn.addBrowseFolderListener(new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileOrFolderDescriptor()) {
        });
        ConfigProjectState config = PersistentStateProjectComponent.getInstance(project);
        if (config.getPath() != null) {
            fileFolderBtn.setText(config.getPath());
        }
        if (config.getProjectName() != null) {
            appName.setText(config.getProjectName());
        }
    }

    private String fetchPathText() {
        return fileFolderBtn.getText();
    }

    private String fetchAppName() {
        return appName.getText();
    }

    public void updateState(ConfigProjectState state) {
        state.setPath(fetchPathText());
        state.setProjectName(fetchAppName());
    }
}
