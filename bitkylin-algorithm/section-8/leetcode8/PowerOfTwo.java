/**
 * <p>给你一个整数 <code>n</code>，请你判断该整数是否是 2 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>如果存在一个整数 <code>x</code> 使得 <code>n == 2<sup>x</sup></code> ，则认为 <code>n</code> 是 2 的幂次方。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>true
 * <strong>解释：</strong>2<sup>0</sup> = 1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 16
 * <strong>输出：</strong>true
 * <strong>解释：</strong>2<sup>4</sup> = 16
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 5
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你能够不使用循环/递归解决此问题吗？</p>
 * <div><div>Related Topics</div><div><li>位运算</li><li>递归</li><li>数学</li></div></div><br><div><li>👍 455</li><li>👎 0</li></div>
 */

package leetcode8;

public class PowerOfTwo {

    public static void main(String[] args) {
        Solution solution = new PowerOfTwo().new Solution();
    }

    /**
     * 注意，n的幂场景，n必须大于0
     * 或参考 {@link NumberOf1Bits}，取结果为1的解即可
     */
    class Solution {
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && ((n & (n - 1)) == 0);
        }
    }

    /**
     * 注意，n的幂场景，n必须大于0
     */
    class Solution2 {
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && ((n & -n) == n);
        }
    }

    /**
     * 注意，n的幂场景，n必须大于0
     */
    class Solution3_1 {
        public boolean isPowerOfTwo(int n) {
            while (n > 0) {
                if ((n & 1) == 1) {
                    return n == 1;
                }
                n >>>= 1;
            }
            return false;
        }
    }

    class Solution3_2 {
        public boolean isPowerOfTwo(int n) {
            while ((n > 0) && ((n & 1) == 0)) {
                n >>= 1;
            }
            return n == 1;
        }
    }
}

