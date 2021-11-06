//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
//
//
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
//
//
// 示例:
//
// 输入: [1,2,3,0,2]
//输出: 3
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
// Related Topics 动态规划
// 👍 572 👎 0


package leetcode6;

public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStockWithCooldown().new Solution()
                .maxProfit(new int[]{2, 1, 4});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP
     * 0：不持股
     * 1：持股
     * 2：冷静期
     */
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int[][] dp = new int[3][prices.length];
            dp[1][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] - prices[i]);
                dp[2][i] = dp[1][i - 1] + prices[i];
                dp[0][i] = Math.max(dp[2][i - 1], dp[0][i - 1]);
            }
            return Math.max(dp[0][prices.length - 1], dp[2][prices.length - 1]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP
     * 0：初始
     * 1：持股
     * 2：不持股
     */
    class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int[][] dp = new int[3][prices.length];
            dp[1][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] - prices[i]);
                dp[2][i] = Math.max(dp[2][i - 1], dp[1][i - 1] + prices[i]);
                if (i - 2 >= 0) {
                    dp[1][i] = Math.max(dp[1][i], dp[2][i - 2] - prices[i]);
                }
            }
            return Math.max(dp[0][prices.length - 1], dp[2][prices.length - 1]);
        }
    }
}
