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
 * - loop-plus: 超时
 * - loop-stringBuilderCapacity: 285
 * - loop-stringBuilder: 1968
 * - loop-String.join: 1313
 * - loop-stringJoiner: 1238
 * - simple-Plus: 812
 * - simple-StringBuilder: 840
 * - simple-StringBuffer: 857
 *
 * @author limingliang
 */
@Slf4j
public class StringConcat {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("java虚拟机预热开始");
        String[] strs = new String[6000000];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = id();
        }
        loopStringJoiner(strs);
        loopStringJoin(strs);
        loopStringBuilder(strs);
        log.info("java虚拟机预热结束");
        Thread.sleep(1000);
        log.info("开始测试：");

        Thread.sleep(1000);
        Stopwatch stopwatchLoopPlus = Stopwatch.createStarted();
//        loopPlus(strs);
        log.info("loop-plus: " + stopwatchLoopPlus.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchLoopStringBuilderCapacity = Stopwatch.createStarted();
        loopStringBuilderCapacity(strs);
        log.info("loop-stringBuilderCapacity: " + stopwatchLoopStringBuilderCapacity.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchLoopStringBuilder = Stopwatch.createStarted();
        loopStringBuilder(strs);
        log.info("loop-stringBuilder: " + stopwatchLoopStringBuilder.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchLoopJoin = Stopwatch.createStarted();
        loopStringJoin(strs);
        log.info("loop-String.join: " + stopwatchLoopJoin.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchLoopStringJoiner = Stopwatch.createStarted();
        loopStringJoiner(strs);
        log.info("loop-stringJoiner: " + stopwatchLoopStringJoiner.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchSimplePlus = Stopwatch.createStarted();
        for (int i = 0; i < 500000; i++) {
            simplePlus(id(), id(), id());
        }
        log.info("simple-Plus: " + stopwatchSimplePlus.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchSimpleStringBuilder = Stopwatch.createStarted();
        for (int i = 0; i < 500000; i++) {
            simpleStringBuilder(id(), id(), id());
        }
        log.info("simple-StringBuilder: " + stopwatchSimpleStringBuilder.elapsed(TimeUnit.MILLISECONDS));

        Thread.sleep(1000);
        Stopwatch stopwatchSimpleStringBuffer = Stopwatch.createStarted();
        for (int i = 0; i < 500000; i++) {
            simpleStringBuffer(id(), id(), id());
        }
        log.info("simple-StringBuffer: " + stopwatchSimpleStringBuffer.elapsed(TimeUnit.MILLISECONDS));

    }

    private static String loopPlus(String[] strs) {
        String str = "";
        for (String s : strs) {
            str = str + "+" + s;
        }
        return str;
    }

    private static String loopStringBuilder(String[] strs) {
        StringBuilder str = new StringBuilder();
        for (String s : strs) {
            str.append("+");
            str.append(s);
        }
        return str.toString();
    }

    private static String loopStringBuilderCapacity(String[] strs) {
        StringBuilder str = new StringBuilder(strs[0].length() * strs.length);
        for (String s : strs) {
            str.append("+");
            str.append(s);
        }
        return str.toString();
    }

    private static String loopStringJoin(String[] strs) {
        StringJoiner joiner = new StringJoiner("+");
        for (String str : strs) {
            joiner.add(str);
        }
        return joiner.toString();
    }

    private static String loopStringJoiner(String[] strs) {
        return String.join("+", strs);
    }

    private static String simplePlus(String a, String b, String c) {
        return a + "+" + b + "+" + c;
    }

    private static String simpleStringBuilder(String a, String b, String c) {
        StringBuilder builder = new StringBuilder();
        builder.append(a);
        builder.append("+");
        builder.append(b);
        builder.append("+");
        builder.append(c);
        return builder.toString();
    }

    private static String simpleStringBuffer(String a, String b, String c) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(a);
        buffer.append("+");
        buffer.append(b);
        buffer.append("+");
        buffer.append(c);
        return buffer.toString();
    }

    private static String id() {
        return UUID.randomUUID().toString();
    }

}
