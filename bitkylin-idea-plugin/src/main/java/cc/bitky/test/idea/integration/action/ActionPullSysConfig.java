package cc.bitky.test.idea.integration.action;

import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import cc.bitky.test.idea.integration.service.constant.TradeTestPath;
import cc.bitky.test.idea.integration.service.dto.SysGroupDto;
import cc.bitky.test.idea.integration.service.externalconfig.SysConfigWriter;
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
public class ActionPullSysConfig extends AnAction {
    private static final String bodyTemplate = "{\n" +
            "    \"appName\": \"%s\",\n" +
            "    \"ip\": \"localhost\"\n" +
            "}";

    @Override
    public void actionPerformed(AnActionEvent e) {
        Optional<ConfigProjectState> configProjectStateOptional = BitkyUtils.solveConfigProjectState(e.getProject());
        Optional<String> sysConfigTemplateUrlOptional = BitkyUtils.solveSysConfigTemplateUrl();
        if (configProjectStateOptional.isEmpty() || sysConfigTemplateUrlOptional.isEmpty()) {
            return;
        }
        ConfigProjectState projectState = configProjectStateOptional.get();
        String url = String.format(sysConfigTemplateUrlOptional.get(), projectState.getProjectName());
        String body = String.format(bodyTemplate, projectState.getProjectName());
        String resp = TradeTestHttpClient.postJson(url, body);
        List<SysGroupDto> sysGroupDtoList = JSON.parseArray(resp, SysGroupDto.class);
        try {
            Path realPath = TradeTestPath.sysConfigByJava(Paths.get(projectState.getPath()));
            SysConfigWriter.write(realPath, sysGroupDtoList);
            Messages.showMessageDialog(
                    "系统配置写入成功！\n\n" +
                            "写入路径：\n" + realPath + "\n\n" +
                            "写入配置：" + sysGroupDtoList.size() + " 条",
                    "执行成功", Messages.getInformationIcon());

        } catch (Exception ex) {
            Messages.showMessageDialog("有异常发生，请查看IDEA的日志\n\n" + ex.getMessage(), "有异常啦", Messages.getInformationIcon());
            throw ex;
        }
    }

}
