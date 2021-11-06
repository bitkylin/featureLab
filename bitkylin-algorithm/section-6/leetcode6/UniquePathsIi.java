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
// 说明：m 和 n 的值均不超过 100。
//
// 示例 1:
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
//
// Related Topics 数组 动态规划
// 👍 420 👎 0


package leetcode6;

public class UniquePathsIi {

    public static void main(String[] args) {
        new UniquePathsIi().new Solution().uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        });
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 考虑初始化时，第一行有障碍的情况，直接跳出初始化循环
     */
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int xMax = obstacleGrid.length - 1;
            int yMax = obstacleGrid[0].length - 1;
            if (obstacleGrid[xMax][yMax] == 1) {
                return 0;
            }
            int[][] dp = new int[xMax + 1][yMax + 1];
            dp[xMax][yMax] = 1;
            for (int i = xMax - 1; i >= 0 && obstacleGrid[i][yMax] == 0; i--) {
                dp[i][yMax] = 1;
            }
            for (int i = yMax - 1; i >= 0 && obstacleGrid[xMax][i] == 0; i--) {
                dp[xMax][i] = 1;
            }
            for (int i = xMax - 1; i >= 0; i--) {
                for (int j = yMax - 1; j >= 0; j--) {
                    dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i + 1][j] + dp[i][j + 1];
                }
            }
            return dp[0][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归法
     */
    class Solution2 {
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
