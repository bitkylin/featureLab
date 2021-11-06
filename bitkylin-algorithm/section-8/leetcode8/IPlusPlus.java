package leetcode8;

/**
 * @author limingliang
 */
public class IPlusPlus {

    public static void main(String[] args) {
        int i = 0, j = 0, k = 0;

        // 响应 3 = 0 + 1 + 2
        System.out.println(test(i++, i++, i++));

        i = 0;
        j = 0;
        k = 0;
        // 入参：0, 0, 0
        System.out.println(test(i++, j++, k++));

        i = 0;
        j = 0;
        k = 0;
        // 响应：0
        System.out.println((i++ + j++ + k++));

        i = 0;
        j = 0;
        k = 0;
        // 响应：1 = 0 + 1 + 0
        System.out.println((j++ + j++ + k++));

        i = 0;
        j = 0;
        k = 0;
        // 响应：0
        System.out.println(test(i++) + test(j++) + test(k++));


        i = 0;
        j = 0;
        k = 0;
        // 响应：7 = 0 + 1 + 2 + 4 + 0 + 0
        System.out.println(i++ + i++ + i++ + ++i + j++ + k++);

        i = 0;
        j = 0;
        k = 0;
        j = 1 > i++ ? i++ : i++;
        // 响应：1:2
        System.out.println(j + ":" + i);
    }

    public static int test(int i, int j, int k) {
        return i + j + k;
    }

    public static int test(int i) {
        return i;
    }
}
