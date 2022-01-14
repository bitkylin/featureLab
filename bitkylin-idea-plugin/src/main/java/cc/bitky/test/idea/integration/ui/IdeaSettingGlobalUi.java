package cc.bitky.test.idea.integration.ui;

import cc.bitky.test.idea.integration.config.PersistentStateGlobalComponent;
import cc.bitky.test.idea.integration.dto.ConfigGlobalState;
import cc.bitky.test.idea.integration.service.constant.TradeTestConstants;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IdeaSettingGlobalUi {

    private static final IdeaSettingGlobalUi settingGlobalUi = new IdeaSettingGlobalUi();

    private JTextField sysConfigTemplateUrl;

    private JTextField simBusinessTemplateUrl;

    private JPanel mainPanel;

    private JButton buttonResetSysConfigTemplateUrl;

    private JButton buttonResetSimBusinessTemplateUrl;

    public static IdeaSettingGlobalUi instance() {
        return settingGlobalUi;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void init() {
        ConfigGlobalState state = PersistentStateGlobalComponent.getInstance();
        sysConfigTemplateUrl.setText(state.getSysConfigTemplateUrl() == null ?
                TradeTestConstants.URL_SYS_CONFIG : state.getSysConfigTemplateUrl());
        simBusinessTemplateUrl.setText(state.getSimBusinessTemplateUrl() == null ?
                TradeTestConstants.URL_SIM_BUSINESS : state.getSimBusinessTemplateUrl());

        buttonResetSysConfigTemplateUrl.addActionListener(e -> {
            sysConfigTemplateUrl.setText(TradeTestConstants.URL_SYS_CONFIG);
        });
        buttonResetSimBusinessTemplateUrl.addItemListener(e -> {
            sysConfigTemplateUrl.setText(TradeTestConstants.URL_SIM_BUSINESS);
        });
    }

    private String fetchSysConfigTemplateUrl() {
        return sysConfigTemplateUrl.getText();
    }

    private String fetchSimBusinessTemplateUrl() {
        return simBusinessTemplateUrl.getText();
    }

    public void updateState(ConfigGlobalState state) {
        state.setSysConfigTemplateUrl(fetchSysConfigTemplateUrl());
        state.setSimBusinessTemplateUrl(fetchSimBusinessTemplateUrl());
    }
}
