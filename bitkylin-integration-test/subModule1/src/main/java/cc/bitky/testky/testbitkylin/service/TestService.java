package cc.bitky.testky.testbitkylin.service;

import cc.bitky.testky.testbitkylin.service.sub.TestSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author limingliang
 */
@Service
public class TestService {

    @Autowired
    private TestSubService testSubService;

    public String one() {
        return testSubService.oneSub();
    }

    public String two() {
        return testSubService.twoSub();
    }
}
