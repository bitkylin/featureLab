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

        i = 0;
        j = 0;
        k = 0;
        // 入参：0, 0, 0
        log("test(i++, j++, k++)");
        log(test(i++, j++, k++));

        i = 0;
        j = 0;
        k = 0;
        // 响应：0
        log("i++ + j++ + k++");
        log(i++ + j++ + k++);

        i = 0;
        j = 0;
        k = 0;
        // 响应：1 = 0 + 1 + 0
        log("j++ + j++ + k++");
        log(j++ + j++ + k++);

        i = 0;
        j = 0;
        k = 0;
        // 响应：0
        log("test(i++) + test(j++) + test(k++)");
        log(test(i++) + test(j++) + test(k++));


        i = 0;
        j = 0;
        k = 0;
        // 响应：1 = 0 + 0 + 1
        log("test(i++) + test(j++) + test(i++)");
        log(test(i++) + test(j++) + test(i++));


        i = 0;
        j = 0;
        k = 0;
        // 响应：7 = 0 + 1 + 2 + 4 + 0 + 0
        log("i++ + i++ + i++ + ++i + j++ + k++");
        log(i++ + i++ + i++ + ++i + j++ + k++);

        i = 0;
        j = 0;
        k = 0;
        j = 1 > i++ ? i++ : i++;
        // 响应：1:2
        log("j + : + i");
        log(j + ":" + i);

        i = 0;
        j = 0;
        k = 0;
        int[] arr = new int[]{0, 1, 2, 3, 4};
        arr[test(i++)] = arr[test(i++)];
        // 就算是在表达式中，也是先计算表达式左边，再计算表达式右边
        // 响应：[1, 1, 2, 3, 4]
        log("Arrays.toString(arr)");
        log(Arrays.toString(arr));
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
