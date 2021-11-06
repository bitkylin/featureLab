//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
// 输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
// 输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
// 输入：coins = [1], amount = 0
//输出：0
//
//
// 示例 4：
//
// 输入：coins = [1], amount = 1
//输出：1
//
//
// 示例 5：
//
// 输入：coins = [1], amount = 2
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 231 - 1
//
// Related Topics 动态规划
// 👍 852 👎 0


package leetcode4;

import java.util.ArrayDeque;
import java.util.Deque;

public class CoinChange {

    public static void main(String[] args) {
        new CoinChange().new Solution().coinChange(new int[]{186, 419, 83, 408}, 6249);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP
     */
    class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，要注意剪枝
     */
    class Solution2 {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0 || coins == null || coins.length == 0) {
                return 0;
            }
            int[] memo = new int[amount];
            resolve(coins, amount, memo);
            return memo[amount - 1];
        }

        private int resolve(int[] coins, int amount, int[] memo) {
            if (amount <= 0) {
                return amount;
            }
            if (memo[amount - 1] != 0) {
                return memo[amount - 1];
            }

            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int val = resolve(coins, amount - coin, memo);
                if (val >= 0) {
                    min = Math.min(val + 1, min);
                }
            }
            memo[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
            return memo[amount - 1];
        }
    }

    /**
     * BFS，超出内存限制，不过通过了一些测试用例，所以该方法应该没问题
     */
    class Solution3 {
        public int coinChange(int[] coins, int amount) {
            if (coins.length == 0 || amount <= 0) {
                return 0;
            }
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offer(amount);
            int level = 0;
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    int amt = deque.poll();
                    for (int j = 0; j < coins.length; j++) {
                        if (amt - coins[j] == 0) {
                            return level + 1;
                        } else if (amt - coins[j] > 0) {
                            deque.offer(amt - coins[j]);
                        }
                    }
                }
                level++;
            }
            return -1;
        }
    }
}
