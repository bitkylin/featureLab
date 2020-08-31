package cc.bitky.demo.featurelab;

import cc.bitky.demo.featurelab.tools.validator.PayPropertyValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeatureLabApplicationTests {

    @Autowired
    private PayPropertyValidator validator;

    @Test
    public void contextLoads() {
        validator.requireVerified(null);
    }

}
