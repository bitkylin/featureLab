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
// 👍 746 👎 0


package leetcode9;

public class UniquePaths {

    public static void main(String[] args) {
        new UniquePaths().new Solution().uniquePaths(3, 7);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 历史解法链接：{@link leetcode6.UniquePaths}
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] memo = new int[m][n];
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    memo[x][y] = -1;
                }
            }
            return solve(m - 1, n - 1, memo);
        }

        private int solve(int x, int y, int[][] memo) {
            if (x == 0 || y == 0) {
                return 1;
            }
            if (memo[x][y] != -1) {
                return memo[x][y];
            }
            int val = solve(x - 1, y, memo) + solve(x, y - 1, memo);
            memo[x][y] = val;
            return val;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * dp
     */
    class Solution2 {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (int y = 1; y <= n; y++) {
                for (int x = 1; x <= m; x++) {
                    if (x == 1 && y == 1) {
                        dp[1][1] = 1;
                        continue;
                    }
                    dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
                }
            }
            return dp[m][n];
        }
    }

    /**
     * dp
     */
    class Solution3 {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            dp[1][1] = 1;
            for (int x = 2; x <= m; x++) {
                dp[x][1] = dp[x - 1][1] + dp[x][0];
            }
            for (int y = 2; y <= n; y++) {
                for (int x = 1; x <= m; x++) {
                    dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
                }
            }
            return dp[m][n];
        }
    }
}
