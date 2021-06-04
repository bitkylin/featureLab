package cc.bitky.featurelab.casperlab.service.juc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author limingliang
 */
@Slf4j
public class ThreadTest {

    private static final Object O = new Object();


    public static void main(String[] args) {
//        sleepInterrupted();
        waitThread();
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    private static void waitThread() {
        try {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        while (true) {
                            log.warn("调用wait");
                            getClass().wait();
                            log.warn("从wait中醒来");
                        }
                    } catch (Exception e) {
                        log.warn("忽略一个异常" + e.getClass().getSimpleName());
                    }
                }
            });
            thread.start();
            Thread.sleep(1500);
//            Thread.sleep(100);
            O.notify();
            Thread.sleep(100);
            O.notify();
            Thread.sleep(100);
            thread.interrupt();
            Thread.sleep(100);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    @SneakyThrows
    private static void sleepInterrupted() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    sleep();
                } catch (Exception e) {
                    log.warn("忽略一个异常" + e.getClass().getSimpleName());
                }
            }

            @SneakyThrows
            private void sleep() {
                Thread.sleep(20000);
            }
        });
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
        Thread.sleep(500);
    }

}
