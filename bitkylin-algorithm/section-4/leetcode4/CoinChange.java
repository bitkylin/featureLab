/**
 * <p>给你一个整数数组 <code>coins</code> ，表示不同面额的硬币；以及一个整数 <code>amount</code> ，表示总金额。</p>
 *
 * <p>计算并返回可以凑成总金额所需的 <strong>最少的硬币个数</strong> 。如果没有任何一种硬币组合能组成总金额，返回 <code>-1</code> 。</p>
 *
 * <p>你可以认为每种硬币的数量是无限的。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>
 * <strong>输出：</strong><code>3</code>
 * <strong>解释：</strong>11 = 5 + 5 + 1</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = <code>[2]</code>, amount = <code>3</code>
 * <strong>输出：</strong>-1</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 0
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 1
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 2
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= coins.length <= 12</code></li>
 * <li><code>1 <= coins[i] <= 2<sup>31</sup> - 1</code></li>
 * <li><code>0 <= amount <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1618</li><li>👎 0</li></div>
 */

package leetcode4;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        new CoinChange().new Solution().coinChange(new int[]{1, 2, 5}, 11);
    }

    /**
     * 这题要多做
     * DP，完整版
     * DP(n) = Min( DP(n - arr[i]) + 1, DP(n) )
     * <p>
     * 注：后面这个状态转移方程基本没有错，不过 n + arr[i] 容易超出Integer的范围，导致数组越界，所以最好用减法的方式
     * DP(n + arr[i]) = Min( DP(n) + 1, DP(n + arr[i]) )
     */
    class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount <= 0) return amount;
            int[] dp = new int[amount + 1];
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin < 0) continue;
                    if (i - coin == 0) {
                        dp[i] = 1;
                        continue;
                    }
                    if (dp[i - coin] > 0) {
                        if (dp[i] == 0) {
                            dp[i] = dp[i - coin] + 1;
                        } else {
                            dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                        }
                    }
                }
            }
            return dp[amount] == 0 ? -1 : dp[amount];
        }
    }

    /**
     * DP，稍微简化版
     */
    class SolutionSimpleDp {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0 || coins == null || coins.length == 0) {
                return 0;
            }
            int[] dp = new int[amount + 1];
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    int amt = i - coins[j];
                    if (amt == 0) {
                        dp[i] = 1;
                    } else if (amt > 0 && dp[amt] != 0) {
                        dp[i] = dp[i] == 0 ? dp[amt] + 1 : Math.min(dp[i], dp[amt] + 1);
                    }
                }
            }
            return dp[amount] == 0 ? -1 : dp[amount];
        }
    }

    /**
     * DP，精简版
     * 注：这个精简版太具有技巧性了，还是看完整版吧
     */
    class SolutionVerySimpleDp {
        public int coinChange(int[] coins, int amount) {
            if (amount <= 0) return amount;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin < 0) continue;
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    /**
     * DFS，对coins数组进行循环递归，重复子结构非常简单，方便记忆化搜索
     * 注：缓存需要同时缓存有用的结果和无效的结果，因为无效的结果也是在无后效性条件下，计算出的最优结果
     */
    class Solution0 {
        public int coinChange(int[] coins, int amount) {
            if (amount <= 0) return 0;
            int[] memo = new int[amount + 1];
            return solve(coins, memo, amount);
        }

        private int solve(int[] coins, int[] memo, int amount) {
            if (amount <= 0) return amount < 0 ? -1 : 0;
            if (memo[amount] != 0) return memo[amount];
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                int val = solve(coins, memo, amount - coins[i]);
                if (val >= 0) min = Math.min(min, val + 1);
            }
            memo[amount] = min == Integer.MAX_VALUE ? -1 : min;
            return memo[amount];
        }
    }

    /**
     * DFS，对coins数组深度递归，超时未通过
     * 重复子结构复杂，难以记忆化搜索
     */
    class Solution1 {
        public int coinChange(int[] coins, int amount) {
            if (amount <= 0) return 0;
            return solve(coins, amount, 0);
        }

        private int solve(int[] coins, int amount, int level) {
            if (amount <= 0) return amount < 0 ? -1 : 0;
            if (level >= coins.length) return -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; amount >= 0; i++) {
                int val = solve(coins, amount, level + 1);
                if (val >= 0) min = Math.min(min, val + i);
                amount -= coins[level];
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }
}
