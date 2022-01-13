package cc.bitky.test.idea.integration.action;

import cc.bitky.test.idea.integration.service.constant.TradeTestPath;
import cc.bitky.test.idea.integration.service.dto.SimbusinessKvInfo;
import cc.bitky.test.idea.integration.service.externalconfig.SimbusinessWriter;
import cc.bitky.test.idea.integration.service.util.BitkyUtils;
import cc.bitky.test.idea.integration.service.util.TradeTestHttpClient;
import com.alibaba.fastjson.JSON;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * @author limingliang
 */
public class ActionPullSimBusinessConfig extends AnAction {
    private static final String url_sim_business = "http://configservice.apps01.ali-bj-sit03.shuheo.net/configservice/simbusiness/pull";
    private static final String body = "{\n" +
            "    \"appName\": \"lendengine\",\n" +
            "    \"ip\": \"localhost\"\n" +
            "}";

    @Override
    public void actionPerformed(AnActionEvent e) {
        String resp = TradeTestHttpClient.postJson(url_sim_business, body);
        List<SimbusinessKvInfo> simbusinessKvInfoList = JSON.parseArray(resp, SimbusinessKvInfo.class);
        Optional<Path> path = BitkyUtils.solvePath();
        try {
            if (!path.isPresent()) {
                Messages.showMessageDialog("请先配置路径", "无法执行", Messages.getInformationIcon());
            } else {
                Path realPath = TradeTestPath.simBusinessByJava(path.get());
                SimbusinessWriter.write(realPath, simbusinessKvInfoList);
                Messages.showMessageDialog(
                        "简单业务配置写入成功！\n\n" +
                                "写入路径：\n" + realPath + "\n\n" +
                                "写入配置：" + simbusinessKvInfoList.size() + " 条",
                        "执行成功", Messages.getInformationIcon());
            }
        } catch (Exception ex) {
            Messages.showMessageDialog("有异常发生，请查看IDEA的日志\n\n" + ex.getMessage(), "有异常啦", Messages.getInformationIcon());
            throw ex;
        }

    }
}
