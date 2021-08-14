package cc.bitky.featurelab.casperlab.service.juc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author limingliang
 */
@Slf4j
public class ThreadTest {

    private static final Object O = new Object();


    public static void main(String[] args) {
        sleepInterrupted();
//        waitThread();
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

    /**
     * 线程sleep时，中断操作直接异常
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    @SneakyThrows
    private static void sleepInterrupted() {
        interruptForSleep();
        log.info("=== interruptForSleep ===");
        interruptForSleep2();
    }

    /**
     * 线程未启动时，中断无效
     * 线程中断后，再sleep，直接异常
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    private static void interruptForSleep2() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("线程的中断标志位：" + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    log.info("线程的中断标志位：" + Thread.currentThread().isInterrupted());
                    sleep();
                } catch (Exception e) {
                    log.warn("忽略一个异常" + e.getClass().getSimpleName());
                }
            }
        });
        log.info("准备中断");
        thread.interrupt();
        log.info("中断了");
        thread.start();
    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    private static void interruptForSleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                sleep();
            } catch (Exception e) {
                log.warn("忽略一个异常" + e.getClass().getSimpleName());
            }
        });
        thread.start();
        Thread.sleep(500);
        log.info("准备中断");
        thread.interrupt();
        log.info("中断了");
        Thread.sleep(500);
    }

    @SneakyThrows
    private static void sleep() {
        log.info("线程准备睡觉");
        Thread.sleep(10000);
        log.info("线程睡醒了");
    }
}
