package cc.bitky.test.idea.integration.action;

import cc.bitky.test.idea.integration.service.constant.TradeTestPath;
import cc.bitky.test.idea.integration.service.util.BitkyUtils;
import cc.bitky.test.idea.integration.service.util.FileUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

import java.nio.file.Path;
import java.util.Optional;

import static cc.bitky.test.idea.integration.service.util.FileUtils.createNewDir;

/**
 * @author limingliang
 */
public class ActionClearSysConfig extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Optional<Path> path = BitkyUtils.solvePath();
        try {
            if (!path.isPresent()) {
                Messages.showMessageDialog("请先配置路径", "无法执行", Messages.getInformationIcon());
            } else {
                Path realPath = TradeTestPath.sysConfigByJava(path.get());
                FileUtils.delFileOrDir(realPath);
                Messages.showMessageDialog(
                        "系统配置目录初始化成功！\n\n" +
                                "已初始化路径：\n" + realPath,
                        "初始化成功", Messages.getInformationIcon());
            }
        } catch (Exception ex) {
            Messages.showMessageDialog("有异常发生，请查看IDEA的日志\n\n" + ex.getMessage(), "有异常啦", Messages.getInformationIcon());
            throw ex;
        }
    }
}
