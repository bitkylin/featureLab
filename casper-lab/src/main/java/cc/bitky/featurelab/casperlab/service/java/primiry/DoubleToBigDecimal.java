package cc.bitky.featurelab.casperlab.service.java.primiry;

import java.math.BigDecimal;

/**
 * @author limingliang
 */
public class DoubleToBigDecimal {

    public static void main(String[] args) {
        double a = 0.3;
        String b = 0.3 + "";
        String c = String.valueOf(a);
        BigDecimal aa = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        BigDecimal cc = new BigDecimal(c);
        System.out.println();
    }
}
