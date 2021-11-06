//编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
//
//
//
// 示例 1：
//
// 输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
//
//
// 示例 2：
//
// 输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
//
//
// 示例 3：
//
// 输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
//
//
//
// 提示：
//
//
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的
//还是无符号的，其内部的二进制表示形式都是相同的。
// 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
//
//
//
//
// 进阶:
//如果多次调用这个函数，你将如何优化你的算法？
// Related Topics 位运算
// 👍 231 👎 0


package leetcode8;

public class NumberOf1Bits {

    public static void main(String[] args) {
        new NumberOf1Bits().new Solution().hammingWeight(11);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最优解
     * 最全解法题解：https://leetcode-cn.com/problems/number-of-1-bits/solution/javade-17chong-jie-fa-by-sdwwld/
     */
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res++;
                n = n & (n - 1);
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class Solution2 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res += n & 1;
                n = n >>> 1;
            }
            return res;
        }
    }

    /**
     * 由于n可能小于0，n % 2 可能等于-1或1，需注意
     */
    public class Solution3 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                if ((n % 2) != 0) {
                    res++;
                }
                n = n >>> 1;
            }

            return res;
        }
    }

    public class Solution4 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            char[] arr = Integer.toBinaryString(n).toCharArray();
            int res = 0;
            for (char c : arr) {
                if (c == '1') {
                    res++;
                }
            }
            return res;
        }
    }
}
