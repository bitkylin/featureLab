package cc.bitky.test.idea.integration.action;

import cc.bitky.test.idea.integration.dto.ConfigProjectState;
import cc.bitky.test.idea.integration.service.constant.TradeTestPath;
import cc.bitky.test.idea.integration.util.BitkyUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static cc.bitky.test.idea.integration.service.util.FileUtils.createNewDir;

/**
 * @author limingliang
 */
public class ActionClearSimBusinessConfig extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Optional<ConfigProjectState> configProjectStateOptional = BitkyUtils.solveConfigProjectState(e.getProject());
        if (configProjectStateOptional.isEmpty()) {
            return;
        }
        ConfigProjectState projectState = configProjectStateOptional.get();
        try {
            Path realPath = TradeTestPath.simBusinessByJava(Paths.get(projectState.getPath()));
            createNewDir(realPath);
            Messages.showMessageDialog(
                    "简单业务配置目录初始化成功！\n\n" +
                            "已初始化路径：\n" + realPath,
                    "初始化成功", Messages.getInformationIcon());
        } catch (Exception ex) {
            Messages.showMessageDialog("有异常发生，请查看IDEA的日志\n\n" + ex.getMessage(), "有异常啦", Messages.getInformationIcon());
            throw ex;
        }

    }
}
