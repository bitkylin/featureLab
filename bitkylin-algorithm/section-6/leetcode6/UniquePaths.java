//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
//
// 问总共有多少条不同的路径？
//
//
//
// 例如，上图是一个7 x 3 的网格。有多少可能的路径？
//
//
//
// 示例 1:
//
// 输入: m = 3, n = 2
//输出: 3
//解释:
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向右 -> 向下
//2. 向右 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向右
//
//
// 示例 2:
//
// 输入: m = 7, n = 3
//输出: 28
//
//
//
// 提示：
//
//
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 10 ^ 9
//
// Related Topics 数组 动态规划
// 👍 707 👎 0


package leetcode6;

public class UniquePaths {

    public static void main(String[] args) {
        new UniquePaths().new Solution().uniquePaths(3, 7);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            dp[m - 1][n - 1] = 1;
            for (int i = m - 2; i >= 0; i--) {
                dp[i][n - 1] = dp[i + 1][n - 1];
            }
            for (int i = n - 2; i >= 0; i--) {
                dp[m - 1][i] = dp[m - 1][i + 1];
            }
            for (int i = m - 2; i >= 0; i--) {
                for (int j = n - 2; j >= 0; j--) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
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
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            return resolve(0, 0, m, n, dp);
        }

        private int resolve(int x, int y, int m, int n, int[][] dp) {
            if (x >= m || y >= n) {
                return 0;
            }
            if (x == m - 1 || y == n - 1) {
                return 1;
            }
            if (dp[x][y] != 0) {
                return dp[x][y];
            }
            dp[x][y] = resolve(x + 1, y, m, n, dp) + resolve(x, y + 1, m, n, dp);
            return dp[x][y];
        }
    }
}
