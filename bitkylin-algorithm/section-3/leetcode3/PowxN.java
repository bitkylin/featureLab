/**
 * <p>实现 <a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a> ，即计算 x 的 n 次幂函数（即，x<sup><span style="font-size:10.8333px">n</span></sup>）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 2.00000, n = 10
 * <strong>输出：</strong>1024.00000
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 2.10000, n = 3
 * <strong>输出：</strong>9.26100
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 2.00000, n = -2
 * <strong>输出：</strong>0.25000
 * <strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-100.0 < x < 100.0</code></li>
 * <li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup>-1</code></li>
 * <li><code>-10<sup>4</sup> <= x<sup>n</sup> <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 797</li><li>👎 0</li></div>
 */

package leetcode3;

public class PowxN {

    public static void main(String[] args) {
        new PowxN().new Solution().myPow(2, 10);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 递归法
     */
    class Solution {
        public double myPow(double x, int n) {
            if (n == -1) {
                return 1 / x;
            } else if (n == 0) {
                return 1;
            } else if (n == 1) {
                return x;
            }
            double res = myPow(x, n / 2);
            return res * res * myPow(x, n % 2);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 循环法
     */
    class Solution2 {
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
}
