package cc.bitky.testky.testbitkylin.service.sub;

import cc.bitky.testky.testbitkylin.util.TestUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author limingliang
 */
@Slf4j
public class TestSub2Service {

    public String oneSub() {
        return "oneSub2";
    }

    public String twoSub() {
        return TestUtils.fetchTwoStatic();
    }
}
