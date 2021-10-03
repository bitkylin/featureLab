package cc.bitky.featurelab.casperlab.service.juc;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @author limingliang
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
//        simpleRun();
        CompletableFuture f1 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom(5, 10);
            sleep(t);
            return String.valueOf(t);
        });
        CompletableFuture f2 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom(5, 10);
            sleep(t);
            return String.valueOf(t);
        });
        CompletableFuture f3 = f1.applyToEither(f2, s -> {
            return s;
        });
        System.out.println(f1.join());
        System.out.println(f2.join());
        System.out.println(f3.join());
    }

    @SneakyThrows
    private static void sleep(int time) {
        Thread.sleep(time * 100);
    }

    private static int getRandom(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    private static void simpleRun() throws InterruptedException {
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
