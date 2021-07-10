package cc.bitky.featurelab.casperlab.service.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author limingliang
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        try {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1000 * 4);
                    System.out.println("finish : ");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }).get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("haha : ");
        Thread.sleep(1000 * 3);
    }
}
