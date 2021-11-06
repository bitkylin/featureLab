//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
//
//
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。
//
//
//
// 示例 1：
//
//
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
//
//
// 示例 2：
//
//
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
//
//
//
//
// 提示：
//
//
// m == obstacleGrid.length
// n == obstacleGrid[i].length
// 1 <= m, n <= 100
// obstacleGrid[i][j] 为 0 或 1
//
// Related Topics 数组 动态规划
// 👍 443 👎 0


package leetcode9;

public class UniquePathsIi {

    public static void main(String[] args) {
        Solution solution = new UniquePathsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 状态转移方程：
     * <p>
     * DP[x][y] = (DP[x - 1][y] + DP[x][y - 1]) * (1 ^ Grid[x][y])
     * <p>
     * 原解法较为笨拙：{@link leetcode6.UniquePathsIi}
     */
    class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)

}
