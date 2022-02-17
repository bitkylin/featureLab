/**
 * <p>给定一个包含非负整数的 <code><em>m</em> x <em>n</em></code> 网格 <code>grid</code> ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。</p>
 *
 * <p><strong>说明：</strong>每次只能向下或者向右移动一步。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>grid = [[1,3,1],[1,5,1],[4,2,1]]
 * <strong>输出：</strong>7
 * <strong>解释：</strong>因为路径 1→3→1→1→1 的总和最小。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[1,2,3],[4,5,6]]
 * <strong>输出：</strong>12
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 <= m, n <= 200</code></li>
 * <li><code>0 <= grid[i][j] <= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 1101</li><li>👎 0</li></div>
 */

package leetcode6;

public class MinimumPathSum {

    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 一维DP，二维DP压缩而成
     */
    class Solution {
        public int minPathSum(int[][] grid) {
            int[] dp = new int[grid[0].length];
            int sum = 0;
            for (int y = 0; y < grid[0].length; y++) {
                sum += grid[0][y];
                dp[y] = sum;
            }
            for (int x = 1; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (y == 0) {
                        dp[y] = dp[y] + grid[x][y];
                    } else {
                        dp[y] = Math.min(dp[y], dp[y - 1]) + grid[x][y];
                    }
                }
            }
            return dp[grid[0].length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 二维DP
     * DP[x][y] = Min( DP[x-1][y], DP[x][y-1] ) + grid[x][y]
     */
    class Solution1 {
        public int minPathSum(int[][] grid) {
            int xMax = grid.length - 1;
            int yMax = grid[0].length - 1;
            int[][] dp = new int[xMax + 1][yMax + 1];
            dp[0][0] = grid[0][0];
            for (int x = 1; x <= xMax; x++) {
                dp[x][0] = dp[x - 1][0] + grid[x][0];
            }
            for (int y = 1; y <= yMax; y++) {
                dp[0][y] = dp[0][y - 1] + grid[0][y];
            }
            for (int x = 1; x <= xMax; x++) {
                for (int y = 1; y <= yMax; y++) {
                    dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]) + grid[x][y];
                }
            }
            return dp[xMax][yMax];
        }
    }
}
