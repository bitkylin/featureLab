/**
 * <p>给你一个整数数组 <code>coins</code> 表示不同面额的硬币，另给一个整数 <code>amount</code> 表示总金额。</p>
 *
 * <p>请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 <code>0</code> 。</p>
 *
 * <p>假设每一种面额的硬币有无限个。 </p>
 *
 * <p>题目数据保证结果符合 32 位带符号整数。</p>
 *
 * <p> </p>
 *
 * <ul>
 * </ul>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>amount = 5, coins = [1, 2, 5]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>amount = 3, coins = [2]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>只用面额 2 的硬币不能凑成总金额 3 。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>amount = 10, coins = [10]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= coins.length <= 300</code></li>
 * <li><code>1 <= coins[i] <= 5000</code></li>
 * <li><code>coins</code> 中的所有值 <strong>互不相同</strong></li>
 * <li><code>0 <= amount <= 5000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 687</li><li>👎 0</li></div>
 */

package leetcode4;

public class CoinChange2 {

    public static void main(String[] args) {
        Solution solution = new CoinChange2().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 外层循环的是硬币，保证硬币不会被重用
     */
    class Solution {
        public int change(int amount, int[] coins) {
            if (amount < 1) {
                return 1;
            }
            int[] dp = new int[amount];
            for (int coin : coins) {
                for (int i = 0; i < amount; i++) {
                    if (i - coin == -1) {
                        dp[i] += 1;
                    }
                    if (i >= coin) {
                        dp[i] += dp[i - coin];
                    }
                }
            }
            return dp[amount - 1];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 两种做法没差别
     */
    class Solution1 {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = 1; i <= amount; i++) {
                    if (i >= coin) {
                        dp[i] += dp[i - coin];
                    }
                }
            }
            return dp[amount];
        }
    }
}
