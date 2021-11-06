//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
// 示例 1:
//
// 输入: [2,4,1], k = 2
//输出: 2
//解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
//
//
// 示例 2:
//
// 输入: [3,2,6,5,0,3], k = 2
//输出: 7
//解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3
//。
//
// Related Topics 动态规划
// 👍 312 👎 0


package leetcode6;

public class BestTimeToBuyAndSellStockIv {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStockIv().new Solution()
                .maxProfit(2, new int[]{1, 2, 4});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 三维DP：持股+不持股为一个周期，只需要取最后一个周期，比较最大值即可
     * 压缩了DP数组，优化空间复杂度
     * 可通过TC
     */
    class Solution {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length <= 1 || k == 0) {
                return 0;
            }
            k = Math.min(k, prices.length / 2);
            int[][][] dp = new int[2][k][2];
            for (int i = 0; i < dp[0].length; i++) {
                dp[0][i][1] = -prices[0];
            }
            for (int i = 1; i < prices.length; i++) {
                for (int j = 0; j < k; j++) {
                    if (j == 0) {
                        dp[1][j][1] = Math.max(dp[0][j][1], -prices[i]);
                        dp[1][j][0] = Math.max(dp[0][j][0], dp[0][j][1] + prices[i]);
                    } else {
                        dp[1][j][1] = Math.max(dp[0][j][1], dp[0][j - 1][0] - prices[i]);
                        dp[1][j][0] = Math.max(dp[0][j][0], dp[0][j][1] + prices[i]);
                    }
                }
                for (int j = 0; j < k; j++) {
                    dp[0][j][0] = dp[1][j][0];
                    dp[0][j][1] = dp[1][j][1];
                }
            }
            return Math.max(0, dp[0][k - 1][0]);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 三维DP：持股+不持股为一个周期，只需要取最后一个周期，比较最大值即可
     * 此解法可通过大部分TC，最后的TC会超时
     * 详细的其他解法，请参考 {123. 买卖股票的最佳时机 III}
     */
    class Solution2 {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length <= 1 || k == 0) {
                return 0;
            }
            int[][][] dp = new int[prices.length][k][2];
            for (int i = 0; i < dp[0].length; i++) {
                dp[0][i][1] = -prices[0];
            }
            for (int i = 1; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    if (j == 0) {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], -prices[i]);
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    } else {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    }
                }
            }
            return Math.max(0, dp[prices.length - 1][k - 1][0]);
        }
    }
}
