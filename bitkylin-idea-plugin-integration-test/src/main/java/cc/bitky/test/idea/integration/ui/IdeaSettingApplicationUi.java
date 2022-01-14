package cc.bitky.test.idea.integration.ui;

import cc.bitky.test.idea.integration.config.PersistentStateApplicationComponent;
import cc.bitky.test.idea.integration.dto.ConfigApplicationState;
import cc.bitky.test.idea.integration.service.constant.TradeTestConstants;

import javax.swing.*;

/**
 * @author limingliang
 */
public class IdeaSettingApplicationUi {

    private static final IdeaSettingApplicationUi SETTING_APPLICATION_UI = new IdeaSettingApplicationUi();

    private JTextField sysConfigTemplateUrl;

    private JTextField simBusinessTemplateUrl;

    private JPanel mainPanel;

    private JButton buttonResetSysConfigTemplateUrl;

    private JButton buttonResetSimBusinessTemplateUrl;

    public static IdeaSettingApplicationUi instance() {
        return SETTING_APPLICATION_UI;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void init() {
        ConfigApplicationState state = PersistentStateApplicationComponent.getInstance();
        sysConfigTemplateUrl.setText(state.getSysConfigTemplateUrl() == null ?
                TradeTestConstants.URL_SYS_CONFIG : state.getSysConfigTemplateUrl());
        simBusinessTemplateUrl.setText(state.getSimBusinessTemplateUrl() == null ?
                TradeTestConstants.URL_SIM_BUSINESS : state.getSimBusinessTemplateUrl());

        buttonResetSysConfigTemplateUrl.addActionListener(e -> {
            sysConfigTemplateUrl.setText(TradeTestConstants.URL_SYS_CONFIG);
        });
        buttonResetSimBusinessTemplateUrl.addActionListener(e -> {
            simBusinessTemplateUrl.setText(TradeTestConstants.URL_SIM_BUSINESS);
        });
    }

    private String fetchSysConfigTemplateUrl() {
        return sysConfigTemplateUrl.getText();
    }

    private String fetchSimBusinessTemplateUrl() {
        return simBusinessTemplateUrl.getText();
    }

    public void updateState(ConfigApplicationState state) {
        state.setSysConfigTemplateUrl(fetchSysConfigTemplateUrl());
        state.setSimBusinessTemplateUrl(fetchSimBusinessTemplateUrl());
    }
}
