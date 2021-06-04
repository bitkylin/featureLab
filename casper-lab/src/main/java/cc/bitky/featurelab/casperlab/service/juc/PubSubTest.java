package cc.bitky.featurelab.casperlab.service.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author limingliang
 */

@Slf4j
public class PubSubTest {

    public static void main(String[] args) {
        // 仓库对象
        AbstractStorage abstractStorage = new Storage1();

        // 生产者对象
        Producer p1 = new Producer(abstractStorage, "Producer-1");
        Producer p2 = new Producer(abstractStorage, "Producer-2");
        Producer p3 = new Producer(abstractStorage, "Producer-3");
        Producer p4 = new Producer(abstractStorage, "Producer-4");
        Producer p5 = new Producer(abstractStorage, "Producer-5");
        Producer p6 = new Producer(abstractStorage, "Producer-6");
        Producer p7 = new Producer(abstractStorage, "Producer-7");

        // 消费者对象
        Consumer c1 = new Consumer(abstractStorage, "Consumer-1");
        Consumer c2 = new Consumer(abstractStorage, "Consumer-2");
        Consumer c3 = new Consumer(abstractStorage, "Consumer-3");

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }


    public static class Consumer extends Thread {
        // 每次消费的产品数量
        private int num;

        // 所在放置的仓库
        private final AbstractStorage abstractStorage1;

        // 构造函数，设置仓库
        public Consumer(AbstractStorage abstractStorage1, String name) {
            this.abstractStorage1 = abstractStorage1;
            setName(name);
        }

        // 线程run函数
        @Override
        public void run() {
            consume(num);
        }

        // 调用仓库Storage的生产函数
        public void consume(int num) {
            abstractStorage1.consume(num);
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


    public static class Producer extends Thread {
        //每次生产的数量
        private int num;

        //所属的仓库
        public AbstractStorage abstractStorage;

        public Producer(AbstractStorage abstractStorage, String name) {
            this.abstractStorage = abstractStorage;
            setName(name);
        }

        public void setNum(int num) {
            this.num = num;
        }

        // 线程run函数
        @Override
        public void run() {
            produce(num);
        }

        // 调用仓库Storage的生产函数
        public void produce(int num) {
            abstractStorage.produce(num);
        }
    }

    public interface AbstractStorage {

        void consume(int num);

        void produce(int num);
    }

    /**
     * 生产者和消费者的问题
     * wait、notify/notifyAll() 实现
     */
    public static class Storage1 implements AbstractStorage {
        //仓库最大容量
        private static final int MAX_SIZE = 100;
        //仓库存储的载体
        private final LinkedList<Object> list = new LinkedList<>();

        //生产产品
        @Override
        public void produce(int num) {
            //同步
            synchronized (list) {
                //仓库剩余的容量不足以存放即将要生产的数量，暂停生产
                while (list.size() + num > MAX_SIZE) {
                    log.warn("【要生产的产品数量】:" + num + "\t【库存量】:"
                            + list.size() + "\t暂时不能执行生产任务!");

                    try {
                        //条件不满足，生产阻塞
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < num; i++) {
                    list.add(new Object());
                }

                log.warn("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

                list.notifyAll();
            }
        }

        //消费产品
        @Override
        public void consume(int num) {
            synchronized (list) {

                //不满足消费条件
                while (num > list.size()) {
                    log.warn("【要消费的产品数量】:" + num + "\t【库存量】:"
                            + list.size() + "\t暂时不能执行生产任务!");

                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //消费条件满足，开始消费
                for (int i = 0; i < num; i++) {
                    list.remove();
                }

                log.warn("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

                list.notifyAll();
            }
        }
    }
}