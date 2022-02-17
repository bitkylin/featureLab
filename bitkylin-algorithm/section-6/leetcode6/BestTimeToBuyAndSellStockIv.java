/**
 * <p>给定一个整数数组 <code>prices</code> ，它的第<em> </em><code>i</code> 个元素 <code>prices[i]</code> 是一支给定的股票在第 <code>i</code><em> </em>天的价格。</p>
 *
 * <p>设计一个算法来计算你所能获取的最大利润。你最多可以完成 <strong>k</strong> 笔交易。</p>
 *
 * <p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>k = 2, prices = [2,4,1]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>k = 2, prices = [3,2,6,5,0,3]
 * <strong>输出：</strong>7
 * <strong>解释：</strong>在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= k <= 100</code></li>
 * <li><code>0 <= prices.length <= 1000</code></li>
 * <li><code>0 <= prices[i] <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 632</li><li>👎 0</li></div>
 */

package leetcode6;

public class BestTimeToBuyAndSellStockIv {

    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIv().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP - 完整版
     * <p>
     * 优秀题解：
     * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/javayi-ge-si-lu-da-bao-suo-you-gu-piao-t-pd1p/
     * - https://leetcode-cn.com/circle/article/qiAgHn/
     * dp[i][1][k] = Max(dp[i-1][1][k], dp[i-1][0][k-1] - prices[i])
     * dp[i][0][k] = Max(dp[i-1][0][k], dp[i-1][1][k] + prices[i])
     * <p>
     * 注1：动态规划一般是对上一次的最优值进行操作，转化为本次的最优值。一般不针对本轮次的最优值进行操作
     * 注2：无后效性：抽象出最优子结构，其入参为前一轮次的最优值。可认为是前一轮次的全局最优值（非局部最优值）
     * 注3：从第2天开始计算，因为第1天已经预先赋值过了，无需再次计算。
     * 注4：不能仅针对第1轮次赋值，需要针对所有0 - k轮次赋值。因为不一定是k轮次才能达到全局最优，前几伦次即有可能全局最优。
     * 故所有轮次均需要进行相同的计算，计算完毕时标号为k的轮次可能是第1-k中某个轮次的最优值。
     */
    class Solution {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length <= 1 || k == 0) {
                return 0;
            }
            int[][][] dp = new int[k][2][prices.length];
            for (int j = 0; j < k; j++) {
                dp[j][1][0] = -prices[0];
            }
            for (int i = 1; i < prices.length; i++) {
                dp[0][1][i] = Math.max(dp[0][1][i - 1], -prices[i]);
                dp[0][0][i] = Math.max(dp[0][0][i - 1], dp[0][1][i - 1] + prices[i]);
                for (int j = 1; j < k; j++) {
                    dp[j][1][i] = Math.max(dp[j][1][i - 1], dp[j - 1][0][i - 1] - prices[i]);
                    dp[j][0][i] = Math.max(dp[j][0][i - 1], dp[j][1][i - 1] + prices[i]);
                }
            }
            return dp[k - 1][0][prices.length - 1];
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP - 压缩了DP数组，优化空间复杂度
     */
    class Solution1 {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length <= 1 || k == 0) {
                return 0;
            }
            int[][][] dp = new int[2][k][2];
            for (int j = 0; j < k; j++) {
                dp[0][j][1] = -prices[0];
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
                    dp[0][j][1] = dp[1][j][1];
                    dp[0][j][0] = dp[1][j][0];
                }
            }
            return dp[1][k - 1][0];
        }
    }
}
