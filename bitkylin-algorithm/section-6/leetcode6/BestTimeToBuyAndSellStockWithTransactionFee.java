/**
 * <p>给定一个整数数组 <code>prices</code>，其中第 <code>i</code> 个元素代表了第 <code>i</code> 天的股票价格 ；整数 <code>fee</code> 代表了交易股票的手续费用。</p>
 *
 * <p>你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。</p>
 *
 * <p>返回获得利润的最大值。</p>
 *
 * <p><strong>注意：</strong>这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [1, 3, 2, 8, 4, 9], fee = 2
 * <strong>输出：</strong>8
 * <strong>解释：</strong>能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [1,3,7,5,10,3], fee = 3
 * <strong>输出：</strong>6
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= prices.length <= 5 * 10<sup>4</sup></code></li>
 * <li><code>1 <= prices[i] < 5 * 10<sup>4</sup></code></li>
 * <li><code>0 <= fee < 5 * 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 598</li><li>👎 0</li></div>
 */

package leetcode6;

public class BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStockWithTransactionFee().new Solution()
                .maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
    }

    /**
     * 详细题解参考 {@link BestTimeToBuyAndSellStockIv}
     * <p>
     * fee在卖出时累计，只需要在一处减去fee
     */
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            if (prices.length <= 1) {
                return 0;
            }
            int[][] dp = new int[2][prices.length];
            dp[1][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i] - fee);
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] - prices[i]);
            }
            return dp[0][prices.length - 1];
        }
    }

    /**
     * fee在买入时累计，需要注意不要漏减去fee
     */
    class Solution2 {
        public int maxProfit(int[] prices, int fee) {
            if (prices.length <= 1) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][1] = -prices[0] - fee;
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
            }
            return dp[prices.length - 1][0];
        }
    }
}
