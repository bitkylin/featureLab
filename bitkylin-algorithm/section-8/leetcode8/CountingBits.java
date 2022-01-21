/**
 * <p>给你一个整数 <code>n</code> ，对于&nbsp;<code>0 &lt;= i &lt;= n</code> 中的每个 <code>i</code> ，计算其二进制表示中 <strong><code>1</code> 的个数</strong> ，返回一个长度为 <code>n + 1</code> 的数组 <code>ans</code> 作为答案。</p>
 *
 * <p>&nbsp;</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 2
 * <strong>输出：</strong>[0,1,1]
 * <strong>解释：</strong>
 * 0 --&gt; 0
 * 1 --&gt; 1
 * 2 --&gt; 10
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 5
 * <strong>输出：</strong>[0,1,1,2,1,2]
 * <strong>解释：</strong>
 * 0 --&gt; 0
 * 1 --&gt; 1
 * 2 --&gt; 10
 * 3 --&gt; 11
 * 4 --&gt; 100
 * 5 --&gt; 101
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>很容易就能实现时间复杂度为 <code>O(n log n)</code> 的解决方案，你可以在线性时间复杂度 <code>O(n)</code> 内用一趟扫描解决此问题吗？</li>
 * <li>你能不使用任何内置函数解决此问题吗？（如，C++ 中的&nbsp;<code>__builtin_popcount</code> ）</li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>位运算</li><li>动态规划</li></div></div><br><div><li>👍 878</li><li>👎 0</li></div>
 */

package leetcode8;

public class CountingBits {

    public static void main(String[] args) {
        Solution solution = new CountingBits().new Solution();
    }

    /**
     * DP
     * if (奇数)  DP( n ) = DP( n-1 ) + 1
     * else      DP( n ) = DP( n/2 )
     * 优质题解：https://leetcode-cn.com/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
     */
    class Solution {
        public int[] countBits(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 1) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i >> 1];
                }
            }
            return dp;
        }
    }

    class Solution2 {

        public int[] countBits(int n) {
            int[] res = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                res[i] = counting(i);
            }
            return res;
        }

        private int counting(int n) {
            int res = 0;
            while (n != 0) {
                res += n & 1;
                n >>>= 1;
            }
            return res;
        }

        private int counting1(int n) {
            int res = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) != 0) {
                    res++;
                }
            }
            return res;
        }
    }
}
