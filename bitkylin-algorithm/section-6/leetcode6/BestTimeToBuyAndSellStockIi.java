/**
 * <p>给定一个数组 <code>prices</code> ，其中 <code>prices[i]</code> 是一支给定股票第 <code>i</code> 天的价格。</p>
 *
 * <p>设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。</p>
 *
 * <p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> prices = [7,1,5,3,6,4]
 * <strong>输出:</strong> 7
 * <strong>解释:</strong> 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> prices = [1,2,3,4,5]
 * <strong>输出:</strong> 4
 * <strong>解释:</strong> 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> prices = [7,6,4,3,1]
 * <strong>输出:</strong> 0
 * <strong>解释:</strong> 在这种情况下, 没有交易完成, 所以最大利润为 0。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= prices.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>0 <= prices[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1486</li><li>👎 0</li></div>
 */

package leetcode6;

public class BestTimeToBuyAndSellStockIi {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStockIi().new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }

    /**
     * DP
     * 详细题解参考 {@link BestTimeToBuyAndSellStockIv}
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][1] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 简单解法
     */
    class Solution1 {
        public int maxProfit(int[] prices) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
        }
    }

    /**
     * DP，优化空间复杂度
     */
    class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }
            int dp00 = 0;
            int dp01 = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                int dp11 = Math.max(dp01, dp00 - prices[i]);
                int dp10 = Math.max(dp00, dp01 + prices[i]);
                dp00 = dp10;
                dp01 = dp11;
            }
            return dp00;
        }
    }
}
