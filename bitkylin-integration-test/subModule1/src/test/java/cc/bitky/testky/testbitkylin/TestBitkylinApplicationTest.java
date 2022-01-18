package cc.bitky.testky.testbitkylin;

import cc.bitky.testky.testbitkylin.controller.TestController;
import cc.bitky.testky.testbitkylin.service.sub.TestSub2Service;
import cc.bitky.testky.testbitkylin.service.sub.TestSubService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
//@RunWith(PowerMockRunner.class)
@RunWith(SpringRunner.class)
//@PowerMockRunnerDelegate(SpringRunner.class)
@SpringBootTest(classes = TestBitkylinApplication.class)
//@PrepareForTest(TestUtils.class)
public class TestBitkylinApplicationTest {

    @Autowired
    private TestController testController;

    @Autowired
    private TestBitkylinApplication testBitkylinApplication;

    @SpyBean
    private TestSubService testSubService;

    @MockBean
    private TestSub2Service testSub2Service;

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
//        Mockito.when(testSubService.oneSub()).thenReturn("oneSub-mock");
//        PowerMockito.mockStatic(TestUtils.class);
        Mockito.doReturn("oneSub-spy").when(testSubService).oneSub();
//        Mockito.when(TestUtils.fetchTwoStatic()).thenReturn("fetchTwoStatic-mock");
//        Mockito.when(TestUtils.fetchTwoStatic2(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("fetchTwoStatic2-mock");
        TestSub2Service testSub2Service = context.getBean(TestSub2Service.class);
        log.info(testController.one());
        log.info(testController.two());
        log.info(testBitkylinApplication.abc());

    }
}
