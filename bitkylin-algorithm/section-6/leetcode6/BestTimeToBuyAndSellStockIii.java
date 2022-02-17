/**
 * <p>给定一个数组，它的第<em> </em><code>i</code> 个元素是一支给定的股票在第 <code>i</code><em> </em>天的价格。</p>
 *
 * <p>设计一个算法来计算你所能获取的最大利润。你最多可以完成 <strong>两笔 </strong>交易。</p>
 *
 * <p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [3,3,5,0,0,3,1,4]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [1,2,3,4,5]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [7,6,4,3,1]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>在这个情况下, 没有交易完成, 所以最大利润为 0。</pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>prices = [1]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= prices.length <= 10<sup>5</sup></code></li>
 * <li><code>0 <= prices[i] <= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 955</li><li>👎 0</li></div>
 */

package leetcode6;

public class BestTimeToBuyAndSellStockIii {

    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 详细题解参考 {@link BestTimeToBuyAndSellStockIv}
     */
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }
            int[][][] dp = new int[2][2][prices.length];
            dp[0][1][0] = -prices[0];
            dp[1][1][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[0][1][i] = Math.max(dp[0][1][i - 1], -prices[i]);
                dp[0][0][i] = Math.max(dp[0][0][i - 1], dp[0][1][i - 1] + prices[i]);
                dp[1][1][i] = Math.max(dp[1][1][i - 1], dp[0][0][i - 1] - prices[i]);
                dp[1][0][i] = Math.max(dp[1][0][i - 1], dp[1][1][i - 1] + prices[i]);
            }
            return dp[1][0][prices.length - 1];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP，优化空间复杂度
     */
    class Solution1 {
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }
            int dp001 = -prices[0];
            int dp000 = 0;
            int dp011 = -prices[0];
            int dp010 = 0;
            for (int i = 1; i < prices.length; i++) {
                int dp101 = Math.max(dp001, -prices[i]);
                int dp100 = Math.max(dp000, dp001 + prices[i]);
                int dp111 = Math.max(dp011, dp000 - prices[i]);
                int dp110 = Math.max(dp010, dp011 + prices[i]);

                dp001 = dp101;
                dp000 = dp100;
                dp011 = dp111;
                dp010 = dp110;
            }
            return dp010;
        }
    }

    // 后面的解法不再研究

    /**
     * DP，优化空间复杂度「进一步优化」
     * <p>
     * 1. 由于刚开始迭代时，dp3&dp4 与 dp1&dp2 有重复，dp3&dp4已包含所有情况，
     * 2. dp3 仅买入，收益一定小于dp4
     * 结论：所以只需比较dp4即可
     */
    class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int dp1 = -prices[0];
            int dp2 = 0;
            int dp3 = -prices[0];
            int dp4 = 0;
            for (int i = 1; i < prices.length; i++) {
                dp4 = Math.max(dp4, dp3 + prices[i]);
                dp3 = Math.max(dp3, dp2 - prices[i]);
                dp2 = Math.max(dp2, dp1 + prices[i]);
                dp1 = Math.max(dp1, -prices[i]);
            }
            return Math.max(dp4, 0);
        }
    }

    /**
     * DFS + 剪枝，通过大部分测试用例，超时，不过看起来是正确解
     */
    class Solution3 {
        public int maxProfit(int[] prices) {
            return dfs(prices, 0, 0, 0, 0);
        }

        private int dfs(int[] prices, int i, int o1, int o2, int price) {
            if (i >= prices.length || o2 >= 2) {
                return price;
            }
            if (o1 > o2) {
                return Math.max(
                        dfs(prices, i + 1, o1, o2 + 1, price + prices[i]),
                        dfs(prices, i + 1, o1, o2, price)
                );
            }
            if (o1 == o2) {
                return Math.max(
                        dfs(prices, i + 1, o1 + 1, o2, price - prices[i]),
                        dfs(prices, i + 1, o1, o2, price));
            }
            return -1;
        }
    }

    /**
     * 三维DP：持股+不持股为一个周期，只需要取最后一个周期，比较最大值即可
     */
    class Solution4 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int[][][] dp = new int[prices.length][2][2];
            for (int i = 0; i < dp[0].length; i++) {
                dp[0][i][1] = -prices[0];
            }
            for (int i = 1; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    if (j == 0) {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], -prices[i]);
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    } else if (j < dp[i].length) {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    }
                }
            }
            return Math.max(0, dp[prices.length - 1][1][0]);
        }
    }

    /**
     * 三维DP：不持股+持股为一个周期，故需要多出一个周期
     */
    class Solution5 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length <= 1) {
                return 0;
            }
            int[][][] dp = new int[prices.length][3][2];
            for (int i = 0; i < dp[0].length; i++) {
                dp[0][i][1] = -prices[0];
            }
            for (int i = 1; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    if (j == 0) {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
                    } else if (j < dp[i].length - 1) {
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
                    } else {
                        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                    }
                }
            }
            return Math.max(0, dp[prices.length - 1][2][0]);
        }
    }
}
