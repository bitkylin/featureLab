/**
 * <p>一个机器人位于一个 <em>m x n </em>网格的左上角 （起始点在下图中标记为“Start” ）。</p>
 *
 * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。</p>
 *
 * <p>现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？</p>
 *
 * <p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png" style="height: 183px; width: 400px;" /></p>
 *
 * <p>网格中的障碍物和空位置分别用 <code>1</code> 和 <code>0</code> 来表示。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/robot1.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 <code>2</code> 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/robot2.jpg" style="width: 162px; height: 162px;" />
 * <pre>
 * <strong>输入：</strong>obstacleGrid = [[0,1],[0,0]]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == obstacleGrid.length</code></li>
 * <li><code>n == obstacleGrid[i].length</code></li>
 * <li><code>1 <= m, n <= 100</code></li>
 * <li><code>obstacleGrid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 695</li><li>👎 0</li></div>
 */

package leetcode9;

public class UniquePathsIi {

    public static void main(String[] args) {
        Solution solution = new UniquePathsIi().new Solution();
    }

    /**
     * DP完整版
     * DP[i][j] = DP[i-1][j] + DP[i][j-1]
     */
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int xMax = obstacleGrid.length - 1;
            int yMax = obstacleGrid[0].length - 1;
            int[][] dp = new int[xMax + 1][yMax + 1];
            for (int x = 0; x <= xMax && obstacleGrid[x][0] == 1; x++) {
                dp[x][0] = 1;
            }
            for (int y = 0; y <= yMax && obstacleGrid[0][y] == 0; y++) {
                dp[0][y] = 1;
            }
            for (int x = 1; x <= xMax; x++) {
                for (int y = 1; y <= yMax; y++) {
                    if (obstacleGrid[x][y] != 1) {
                        dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
                    }
                }
            }
            return dp[xMax][yMax];
        }
    }

    /**
     * DP精简版，DP二维数组多出一行一列：
     * if i==1 && j==1  DP[i][j] = 1
     * else             DP[i][j] = DP[i-1][j] + DP[i][j-1]
     */
    class Solution2_1 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int xMax = obstacleGrid.length - 1;
            int yMax = obstacleGrid[0].length - 1;
            int[][] dp = new int[xMax + 2][yMax + 2];
            for (int x = 1; x <= xMax + 1; x++) {
                for (int y = 1; y <= yMax + 1; y++) {
                    if (obstacleGrid[x - 1][y - 1] == 1) {
                        dp[x][y] = 0;
                    } else if (x == 1 && y == 1) {
                        dp[x][y] = 1;
                    } else {
                        dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
                    }
                }
            }
            return dp[xMax + 1][yMax + 1];
        }
    }

    /**
     * DFS
     */
    class Solution3 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int xMax = obstacleGrid.length - 1;
            int yMax = obstacleGrid[0].length - 1;
            int[][] memo = new int[xMax + 1][yMax + 1];
            return solve(obstacleGrid, memo, xMax, yMax);
        }

        private int solve(int[][] obstacleGrid, int[][] memo, int x, int y) {
            if (x < 0 || y < 0 || obstacleGrid[x][y] == 1) {
                return 0;
            }
            if (x == 0 && y == 0) {
                return 1;
            }
            if (memo[x][y] > 0) {
                return memo[x][y];
            }
            memo[x][y] = solve(memo, obstacleGrid, x - 1, y) + solve(memo, obstacleGrid, x, y - 1);
            return memo[x][y];
        }
    }

    // --- 后面的解法不再研究 ---

    /**
     * DP灵巧版，
     * 状态转移方程：
     * <p>
     * DP[x][y] = (DP[x - 1][y] + DP[x][y - 1]) * (1 ^ Grid[x][y])
     */
    class Solution2_2 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
                return 0;
            }
            int xMax = obstacleGrid.length - 1;
            int yMax = obstacleGrid[0].length - 1;
            int[][] dp = new int[xMax + 2][yMax + 2];
            dp[1][1] = 1 ^ obstacleGrid[0][0];
            for (int x = 0; x <= xMax; x++) {
                for (int y = 0; y <= yMax; y++) {
                    if (x == 0 && y == 0) {
                        continue;
                    }
                    dp[x + 1][y + 1] = (dp[x + 1][y] + dp[x][y + 1]) * (1 ^ obstacleGrid[x][y]);
                }
            }
            return dp[xMax + 1][yMax + 1];
        }
    }

    /**
     * DFS
     */
    class Solution4 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
                return 0;
            }
            int xMax = obstacleGrid.length - 1;
            int yMax = obstacleGrid[0].length - 1;
            int[][] dp = new int[xMax + 1][yMax + 1];
            dp[xMax][yMax] = 1;
            return resolve(0, 0, xMax, yMax, dp, obstacleGrid);
        }

        private int resolve(int x, int y, int xMax, int yMax, int[][] dp, int[][] obstacleGrid) {
            if (x > xMax || y > yMax) {
                return 0;
            }
            if (obstacleGrid[x][y] == 1) {
                return 0;
            }
            // 以下注释掉的内容不适合一维数组时的特例
//            if (x == xMax || y == yMax) {
//                return 1;
//            }
            if (dp[x][y] != 0) {
                return dp[x][y];
            }
            dp[x][y] = resolve(x + 1, y, xMax, yMax, dp, obstacleGrid)
                    + resolve(x, y + 1, xMax, yMax, dp, obstacleGrid);
            return dp[x][y];
        }
    }

}
