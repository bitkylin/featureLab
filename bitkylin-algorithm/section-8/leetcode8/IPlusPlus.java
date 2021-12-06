package leetcode8;

import java.util.Arrays;

/**
 * @author limingliang
 */
public class IPlusPlus {

    public static void main(String[] args) {
        int i = 0, j = 0, k = 0;

        // 响应 3 = 0 + 1 + 2
        log("test(i++, i++, i++)");
        log(test(i++, i++, i++));
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        // 入参：0, 0, 0
        log("test(i++, j++, k++)");
        log(test(i++, j++, k++));
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        // 响应：0
        log("i++ + j++ + k++");
        log(i++ + j++ + k++);
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        // 响应：1 = 0 + 1 + 0
        log("j++ + j++ + k++");
        log(j++ + j++ + k++);
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        // 响应：0
        log("test(i++) + test(j++) + test(k++)");
        log(test(i++) + test(j++) + test(k++));
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        // 响应：1 = 0 + 0 + 1
        log("test(i++) + test(j++) + test(i++)");
        log(test(i++) + test(j++) + test(i++));
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        // 响应：7 = 0 + 1 + 2 + 4 + 0 + 0
        log("i++ + i++ + i++ + ++i + j++ + k++");
        log(i++ + i++ + i++ + ++i + j++ + k++);
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        j = 1 > i++ ? i++ : i++;
        // 响应：1:2
        log("j + : + i");
        log(j + ":" + i);
        logEnd();

        i = 0;
        j = 0;
        k = 0;
        int[] arr = new int[]{0, 1, 2, 3, 4};
        arr[test(i++)] = arr[test(i++)];
        // 就算是在表达式中，也是先计算等号左边，再计算等号右边
        // 响应：[1, 1, 2, 3, 4]
        log("Arrays.toString(arr)");
        log(Arrays.toString(arr));
        logEnd();

        i = 1;
        j = 1;
        int x = 2;
        int y = 2;
        log("i:" + i + "\nj:" + j + "\nx:" + x + "\ny:" + y);
        log("i++ * j++ * (i+2) * (j+2) == i * j * i++ * j++");
        log((i++ * j++ * (i + 2) * (j + 2) == i * j * i++ * j++));
        i = 1;
        j = 1;
        x = 2;
        y = 2;
        log("i++ * j++ * (i + 2) * (j + 2) = " + (i++ * j++ * (i + 2) * (j + 2)));
        i = 1;
        j = 1;
        log("i * j * i++ * j++ = " + (i * j * i++ * j++));
        log("x * y * x++ * y++ = " + (x * y * x++ * y++));
        logEnd();
    }

    private static void logEnd() {
        log("\n---");
    }

    private static void log(Object msg) {
        System.out.println(msg);
    }

    private static int test(int i, int j, int k) {
        return i + j + k;
    }

    private static int test(int i) {
        return i;
    }
}
