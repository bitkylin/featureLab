package cc.bitky.test.idea.integration.action;

import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import cc.bitky.test.idea.integration.service.constant.TradeTestPath;
import cc.bitky.test.idea.integration.service.dto.SimbusinessKvInfo;
import cc.bitky.test.idea.integration.service.externalconfig.SimbusinessWriter;
import cc.bitky.test.idea.integration.service.util.TradeTestHttpClient;
import cc.bitky.test.idea.integration.util.BitkyUtils;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * @author limingliang
 */
public class ActionPullSimBusinessConfig extends AnAction {
    private static final String bodyTemplate = "{\n" +
            "    \"appName\": \"%s\",\n" +
            "    \"ip\": \"localhost\"\n" +
            "}";

    @Override
    public void actionPerformed(AnActionEvent e) {
        Optional<ConfigProjectState> configProjectStateOptional = BitkyUtils.solveConfigProjectState(e.getProject());
        Optional<String> simBusinessTemplateUrlOptional = BitkyUtils.solveSimBusinessTemplateUrl();
        if (configProjectStateOptional.isEmpty() || simBusinessTemplateUrlOptional.isEmpty()) {
            return;
        }
        ConfigProjectState projectState = configProjectStateOptional.get();
        String url = String.format(simBusinessTemplateUrlOptional.get(), projectState.getProjectName());
        String body = String.format(bodyTemplate, projectState.getProjectName());
        String resp = TradeTestHttpClient.postJson(url, body);
        List<SimbusinessKvInfo> simbusinessKvInfoList = JSON.parseArray(resp, SimbusinessKvInfo.class);
        try {
            Path realPath = TradeTestPath.simBusinessByJava(Paths.get(projectState.getPath()));
            SimbusinessWriter.write(realPath, simbusinessKvInfoList);
            Messages.showMessageDialog(
                    "简单业务配置写入成功！\n\n" +
                            "写入路径：\n" + realPath + "\n\n" +
                            "写入配置：" + (simbusinessKvInfoList.size() == 1 ?
                            simbusinessKvInfoList.get(0).getEnableConfigs().size()
                            : simbusinessKvInfoList.size()) + " 条",
                    "执行成功", Messages.getInformationIcon());

        } catch (Exception ex) {
            Messages.showMessageDialog("有异常发生，请查看IDEA的日志\n\n" + ex.getMessage(), "有异常啦", Messages.getInformationIcon());
            throw ex;
        }

    }
}
