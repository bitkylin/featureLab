/**
 * <p>假设你正在爬楼梯。需要 <em>n</em>&nbsp;阶你才能到达楼顶。</p>
 *
 * <p>每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？</p>
 *
 * <p><strong>注意：</strong>给定 <em>n</em> 是一个正整数。</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong> 2
 * <strong>输出：</strong> 2
 * <strong>解释：</strong> 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong> 3
 * <strong>输出：</strong> 3
 * <strong>解释：</strong> 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * </pre>
 * <div><div>Related Topics</div><div><li>记忆化搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 2073</li><li>👎 0</li></div>
 */

package leetcode7;

public class ClimbingStairs {

    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
    }


    /**
     * 「斐波那契数列」正向求解，DP优化解法
     */
    class Solution {
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int val = 0;
            for (int i = 1, j = 2, k = 3; k <= n; k++) {
                val = i + j;
                i = j;
                j = val;
            }
            return val;
        }
    }

    /**
     * 「斐波那契数列」递归 + 存储中间计算结果
     */
    class Solution2 {
        public int climbStairs(int n) {
            int[] memo = new int[n + 1];
            return solve(n, memo);
        }

        private int solve(int i, int[] memo) {
            if (i <= 2) {
                return i;
            }
            if (memo[i] > 0) {
                return memo[i];
            }
            memo[i] = solve(i - 1, memo) + solve(i - 2, memo);
            return memo[i];
        }
    }

    /**
     * 「斐波那契数列」DP
     */
    class Solution3 {
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 2] + dp[i - 1];
            }
            return dp[n];
        }
    }
}
