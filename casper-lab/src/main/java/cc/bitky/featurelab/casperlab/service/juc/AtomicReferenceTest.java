package cc.bitky.featurelab.casperlab.service.juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author limingliang
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        Integer a = new Integer("1");
        Integer b = new Integer("1");
        Integer c = 1;
        Integer d = Integer.valueOf(1);
        Integer e = Integer.valueOf("1");

        // a != b != c == d == e
        AtomicReference<Integer> atomicReference = new AtomicReference<>();
        atomicReference.set(a);
        boolean success = atomicReference.compareAndSet(e, b);
        System.out.println();
//        atomicReference.

    }
}
