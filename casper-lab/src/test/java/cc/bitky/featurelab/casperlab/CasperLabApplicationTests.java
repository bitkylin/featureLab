package cc.bitky.featurelab.casperlab;

import cc.bitky.featurelab.casperlab.tools.validator.PayPropertyValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CasperLabApplicationTests {

    @Autowired
    private PayPropertyValidator validator;

    @Test
    public void contextLoads() {
        validator.requireVerified(null);
    }

}
