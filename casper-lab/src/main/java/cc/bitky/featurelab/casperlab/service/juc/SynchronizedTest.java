package cc.bitky.featurelab.casperlab.service.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author limingliang
 */
@Slf4j
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(lockTest::test1);
        executorService.execute(lockTest::test2);
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
    }

    public static class LockTest {

        public synchronized void test1() {
            try {
                log.info("test1 start");
                Thread.sleep(5000);
                log.info("test1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        public synchronized void test2() {
            try {
                log.info("test2 start");
                Thread.sleep(5000);
                log.info("test2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
