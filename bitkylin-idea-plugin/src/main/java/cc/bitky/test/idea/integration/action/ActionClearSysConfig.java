package cc.bitky.test.idea.integration.action;

import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import cc.bitky.test.idea.integration.service.constant.TradeTestPath;
import cc.bitky.test.idea.integration.service.util.FileUtils;
import cc.bitky.test.idea.integration.util.BitkyUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author limingliang
 */
public class ActionClearSysConfig extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Optional<ConfigProjectState> configProjectStateOptional = BitkyUtils.solveConfigProjectState(e.getProject());
        if (configProjectStateOptional.isEmpty()) {
            return;
        }
        ConfigProjectState projectState = configProjectStateOptional.get();
        try {
            Path realPath = TradeTestPath.sysConfigByJava(Paths.get(projectState.getPath()));
            FileUtils.delFileOrDir(realPath);
            Messages.showMessageDialog(
                    "系统配置文件删除成功！\n\n" +
                            "已删除文件：\n" + realPath,
                    "文件删除成功", Messages.getInformationIcon());
        } catch (Exception ex) {
            Messages.showMessageDialog("有异常发生，请查看IDEA的日志\n\n" + ex.getMessage(), "有异常啦", Messages.getInformationIcon());
            throw ex;
        }
    }
}
