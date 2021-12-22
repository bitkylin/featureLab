/**
 * <p>给定一个整数数组，其中第<em>&nbsp;i</em>&nbsp;个元素代表了第&nbsp;<em>i</em>&nbsp;天的股票价格 。​</p>
 *
 * <p>设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:</p>
 *
 * <ul>
 * <li>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</li>
 * <li>卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。</li>
 * </ul>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre><strong>输入:</strong> [1,2,3,0,2]
 * <strong>输出: </strong>3
 * <strong>解释:</strong> 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]</pre>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 991</li><li>👎 0</li></div>
 */

package leetcode6;

public class BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        new BestTimeToBuyAndSellStockWithCooldown().new Solution().maxProfit(new int[]{1, 2});
    }

    /**
     * 详细题解参考 {@link BestTimeToBuyAndSellStockIv}
     * <p>
     * 0空仓；1冷冻期；2持仓
     * 注：不要太自信，每一行代码都要检查
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }
            int[][] dp = new int[prices.length][3];
            dp[0][2] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            }
            return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 优化解法，不予以研究
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
