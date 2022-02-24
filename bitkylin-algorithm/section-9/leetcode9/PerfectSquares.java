/**
 * <p>给定正整数 <em>n</em>，找到若干个完全平方数（比如 <code>1, 4, 9, 16, ...</code>）使得它们的和等于<em> n</em>。你需要让组成和的完全平方数的个数最少。</p>
 *
 * <p>给你一个整数 <code>n</code> ，返回和为 <code>n</code> 的完全平方数的 <strong>最少数量</strong> 。</p>
 *
 * <p><strong>完全平方数</strong> 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，<code>1</code>、<code>4</code>、<code>9</code> 和 <code>16</code> 都是完全平方数，而 <code>3</code> 和 <code>11</code> 不是。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = <code>12</code>
 * <strong>输出：</strong>3
 * <strong>解释：</strong><code>12 = 4 + 4 + 4</code></pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = <code>13</code>
 * <strong>输出：</strong>2
 * <strong>解释：</strong><code>13 = 4 + 9</code></pre>
 *
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 1176</li><li>👎 0</li></div>
 */

package leetcode9;

import java.util.ArrayList;
import java.util.List;

public class PerfectSquares {

    public static void main(String[] args) {
        new PerfectSquares().new Solution().numSquares(13);
    }

    /**
     * DP:
     * 先求 sqrt(n) = m，生成初始组件集合「1 .. m」，然后参考题目 {零钱兑换}
     * dp(n) =  Min(dp(n), dp(n-arr(n)) + 1 )
     */
    class Solution {
        public int numSquares(int n) {
            List<Integer> src = new ArrayList<>();
            int m = sqrt(n);
            for (int i = 1; i <= m; i++) {
                src.add(i * i);
            }
            int[] dp = new int[n];
            for (Integer s : src) {
                dp[s - 1] = 1;
            }
            for (int i = 1; i < n; i++) {
                for (int s : src) {
                    if (i >= s && dp[i - s] > 0) {
                        dp[i] = dp[i] > 0 ? Math.min(dp[i], dp[i - s] + 1) : dp[i - s] + 1;
                    }
                }
            }
            return dp[n - 1];
        }

        private int sqrt(int n) {
            int left = 0;
            int right = n;
            while (left < right) {
                int mid = (right - left + 1) / 2 + left;
                if (mid * mid > n) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return left;
        }
    }
}
