//给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
//
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
//
// 返回获得利润的最大值。
//
// 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
//
// 示例 1:
//
// 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
//输出: 8
//解释: 能够达到的最大利润:
//在此处买入 prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
//
// 注意:
//
//
// 0 < prices.length <= 50000.
// 0 < prices[i] < 50000.
// 0 <= fee < 50000.
//
// Related Topics 贪心算法 数组 动态规划
// 👍 265 👎 0


package leetcode6;

public class BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * fee在卖出时累计，只需要在一处减去fee
     */
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length < 2) {
                return 0;
            }
            int[][] dp = new int[2][prices.length];
            dp[1][0] = -prices[0] - fee;
            for (int i = 1; i < prices.length; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] - prices[i] - fee);
            }
            return dp[0][prices.length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * fee在买入时累计，需要注意不要漏减去fee
     */
    class Solution2 {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length < 2) {
                return 0;
            }
            int[][] dp = new int[2][prices.length];
            dp[1][0] = -prices[0] - fee;
            for (int i = 1; i < prices.length; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] - prices[i] - fee);
            }
            return dp[0][prices.length - 1];
        }
    }
}
