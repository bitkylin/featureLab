package cc.bitky.testky.testbitkylin.service.sub;

import cc.bitky.testky.testbitkylin.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author limingliang
 */
@Slf4j
@Service
public class TestSubService {

    public String oneSub() {
        return "oneSub";
    }

    public String twoSub() {
        log.info(TestUtils.fetchTwoStatic2(null, null, null, null));
        return TestUtils.fetchTwoStatic();
    }
}
