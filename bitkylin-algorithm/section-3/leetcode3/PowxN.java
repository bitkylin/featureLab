//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
// 示例 1:
//
// 输入: 2.00000, 10
//输出: 1024.00000
//
//
// 示例 2:
//
// 输入: 2.10000, 3
//输出: 9.26100
//
//
// 示例 3:
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25
//
// 说明:
//
//
// -100.0 < x < 100.0
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
//
// Related Topics 数学 二分查找
// 👍 500 👎 0


package leetcode3;

public class PowxN {

    public static void main(String[] args) {
        new PowxN().new Solution().myPow(2, -2147483648);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 循环法
     */
    class Solution {
        public double myPow(double x, int n) {
            long m = n;
            if (m < 0) {
                x = 1 / x;
                m = -m;
            }
            double res = 1;
            while (m > 0) {
                if ((m % 2) == 1) {
                    res *= x;
                }
                x *= x;
                m /= 2;
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归法
     */
    class Solution2 {
        public double myPow(double x, int n) {
            if (n == 0) {
                return 1;
            }
            if (n == -1) {
                return 1 / x;
            }
            if (n == 1) {
                return x;
            }
            double a = myPow(x, n / 2);
            double b = myPow(x, n % 2);
            return a * b * a;
        }
    }
}
