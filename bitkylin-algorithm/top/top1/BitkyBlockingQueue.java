package top1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author limingliang
 */
public class BitkyBlockingQueue {

    public static void main(String[] args) {
        KyQueue<String> queue = new KyQueue<>(3);
        LongAdder adder = new LongAdder();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adder.increment();
                String str = "a : " + adder;
                queue.offer(str);
                System.out.println("offer成功 : " + str);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String str = queue.poll();
                System.out.println("poll成功 : " + str);
            }
        }).start();
    }

    public static class KyQueue<T> {

        private final Lock lock = new ReentrantLock();
        private final Condition empty;
        private final Condition full;
        private final int capacity;
        private final Deque<T> queue = new LinkedList<>();
        private int count = 0;

        public KyQueue(int capacity) {
            empty = lock.newCondition();
            full = lock.newCondition();
            this.capacity = capacity;
        }

        public void offer(T obj) {
            lock.lock();
            try {
                while (count >= capacity) {
                    empty.signalAll();
                    full.await();
                }
                queue.offer(obj);
                count++;
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public T poll() {
            lock.lock();
            try {
                while (count <= 0) {
                    full.signalAll();
                    empty.await();
                }
                T obj = queue.poll();
                count--;
                empty.signalAll();
                return obj;
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
