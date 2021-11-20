package cc.bitky.featurelab.casperlab.service.java.primiry;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 字符串拼接各种方法性能测试
 * 字符串直接相加，性能基本最优
 * <p>
 * - java虚拟机预热开始
 * - java虚拟机预热结束
 * - 开始测试：
 * - plus: 1260
 * - stringBuilderCapacity: 1597
 * - stringBuilder: 1614
 * - String.join: 1486
 * - stringJoiner: 1431
 * - loop-Plus: 169
 * - loop-StringBuilder: 243
 *
 * @author limingliang
 */
@Slf4j
public class StringConcat {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("java虚拟机预热开始");
        String[] strs = new String[8000000];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = id();
        }
        stringJoiner(strs);
        stringJoin(strs);
        stringBuilder(strs);
        log.info("java虚拟机预热结束");
        Thread.sleep(1000);
        log.info("开始测试：");

        Thread.sleep(1000);
        Stopwatch stopwatchPlus = Stopwatch.createStarted();
        plus(strs);
        log.info("plus: " + stopwatchPlus.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stringBuilderCapacity = Stopwatch.createStarted();
        stringBuilderCapacity(strs);
        log.info("stringBuilderCapacity: " + stringBuilderCapacity.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchStringBuilder = Stopwatch.createStarted();
        stringBuilder(strs);
        log.info("stringBuilder: " + stopwatchStringBuilder.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchJoin = Stopwatch.createStarted();
        stringJoin(strs);
        log.info("String.join: " + stopwatchJoin.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchStringJoin = Stopwatch.createStarted();
        stringJoiner(strs);
        log.info("stringJoiner: " + stopwatchStringJoin.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchLoopPlus = Stopwatch.createStarted();
        for (int i = 0; i < 100000; i++) {
            loopPlus(id(), id(), id());
        }
        log.info("loop-Plus: " + stopwatchLoopPlus.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchLoopStringBuilder = Stopwatch.createStarted();
        for (int i = 0; i < 100000; i++) {
            loopStringBuilder(id(), id(), id());
        }
        log.info("loop-StringBuilder: " + stopwatchLoopStringBuilder.elapsed(TimeUnit.MILLISECONDS));

    }

    private static String plus(String[] strs) {
        String str = "";
        for (String s : strs) {
            str = "+" + s;
        }
        return str;
    }

    private static String stringBuilder(String[] strs) {
        StringBuilder str = new StringBuilder();
        for (String s : strs) {
            str.append("+");
            str.append(s);
        }
        return str.toString();
    }

    private static String stringBuilderCapacity(String[] strs) {
        StringBuilder str = new StringBuilder(strs[0].length() * strs.length);
        for (String s : strs) {
            str.append("+");
            str.append(s);
        }
        return str.toString();
    }

    private static String stringJoin(String[] strs) {
        StringJoiner joiner = new StringJoiner("+");
        for (String str : strs) {
            joiner.add(str);
        }
        return joiner.toString();
    }

    private static String stringJoiner(String[] strs) {
        return String.join("+", strs);
    }

    private static String loopPlus(String a, String b, String c) {
        return a + "+" + b + "+" + c;
    }

    private static String loopStringBuilder(String a, String b, String c) {
        StringBuilder builder = new StringBuilder();
        builder.append(a);
        builder.append("+");
        builder.append(b);
        builder.append("+");
        builder.append(c);
        return builder.toString();
    }

    private static String id() {
        return UUID.randomUUID().toString();
    }

}
