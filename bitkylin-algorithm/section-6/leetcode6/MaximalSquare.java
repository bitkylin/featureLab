/**
 * <p>在一个由 <code>'0'</code> 和 <code>'1'</code> 组成的二维矩阵内，找到只包含 <code>'1'</code> 的最大正方形，并返回其面积。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/max1grid.jpg" style="width: 400px; height: 319px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/max2grid.jpg" style="width: 165px; height: 165px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [["0","1"],["1","0"]]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>matrix = [["0"]]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 <= m, n <= 300</code></li>
 * <li><code>matrix[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 967</li><li>👎 0</li></div>
 */

package leetcode6;

public class MaximalSquare {

    public static void main(String[] args) {
        new MaximalSquare().new Solution().maximalSquare(new char[][]{
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}
        });
    }

    /**
     * 二维DP
     * if x == 0 || y ==0     DP[x][y] = matrix[x][y]
     * else
     * if matrix[x][y] = 0    DP[x][y] = 0;
     * else                   DP[x][y] = Min( DP[x-1][y-1], DP[x][y-1], DP[x-1][y] ) + 1;
     */
    class Solution {
        public int maximalSquare(char[][] matrix) {
            int res = 0;
            int[][] dp = new int[matrix.length][matrix[0].length];
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[0].length; y++) {
                    if (x == 0 || y == 0) {
                        dp[x][y] = matrix[x][y] - 0x30;
                    } else {
                        dp[x][y] = matrix[x][y] == '0' ? 0
                                : Math.min(Math.min(dp[x - 1][y - 1], dp[x - 1][y]), dp[x][y - 1]) + 1;
                    }
                    res = Math.max(res, dp[x][y]);
                }
            }
            return res * res;
        }
    }

    /**
     * 二维DP，写法二
     */
    class Solution2 {
        public int maximalSquare(char[][] matrix) {
            int res = 0;
            int[][] dp = new int[matrix.length][matrix[0].length];
            for (int x = 0; x < matrix.length; x++) {
                dp[x][0] = matrix[x][0] - 0x30;
                if (dp[x][0] == 1) {
                    res = 1;
                }
            }
            for (int y = 0; y < matrix[0].length; y++) {
                dp[0][y] = matrix[0][y] - 0x30;
                if (dp[0][y] == 1) {
                    res = 1;
                }
            }
            for (int x = 1; x < matrix.length; x++) {
                for (int y = 1; y < matrix[0].length; y++) {
                    dp[x][y] = matrix[x][y] == '0' ? 0
                            : Math.min(Math.min(dp[x - 1][y - 1], dp[x - 1][y]), dp[x][y - 1]) + 1;
                    res = Math.max(res, dp[x][y]);
                }
            }
            return res * res;
        }
    }

    /**
     * 压缩到一维DP
     */
    class Solution3 {
        // 这个太难，放弃了
    }
}

