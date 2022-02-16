/**
 * <p>一个机器人位于一个 <code>m x n</code><em> </em>网格的左上角 （起始点在下图中标记为 “Start” ）。</p>
 *
 * <p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。</p>
 *
 * <p>问总共有多少条不同的路径？</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" />
 * <pre>
 * <strong>输入：</strong>m = 3, n = 7
 * <strong>输出：</strong>28</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 3, n = 2
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 7, n = 3
 * <strong>输出：</strong>28
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>m = 3, n = 3
 * <strong>输出：</strong>6</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= m, n <= 100</code></li>
 * <li>题目数据保证答案小于等于 <code>2 * 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>动态规划</li><li>组合数学</li></div></div><br><div><li>👍 1225</li><li>👎 0</li></div>
 */

package leetcode9;

public class UniquePaths {

    public static void main(String[] args) {
        new UniquePaths().new Solution().uniquePaths(3, 7);
    }

    /**
     * DFS + 记忆化搜索
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] memo = new int[m][n];
            return solve(m - 1, n - 1, memo);
        }

        private int solve(int x, int y, int[][] memo) {
            if (x == 0 || y == 0) {
                return 1;
            }
            if (memo[x][y] == 0) {
                memo[x][y] = solve(x - 1, y, memo) + solve(x, y - 1, memo);
            }
            return memo[x][y];
        }
    }

    /**
     * DP完整版
     * DP[i][j] = DP[i-1][j] + DP[i][j-1]
     */
    class Solution1 {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int x = 0; x < m; x++) {
                dp[x][0] = 1;
            }
            for (int y = 0; y < n; y++) {
                dp[0][y] = 1;
            }
            for (int x = 1; x < m; x++) {
                for (int y = 1; y < n; y++) {
                    dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }

    /**
     * DP精简版，DP二维数组多出一行一列：
     * if i==1 && j==1  DP[i][j] = 1
     * else             DP[i][j] = DP[i-1][j] + DP[i][j-1]
     */
    class Solution2 {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == 1 && j == 1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m][n];
        }
    }

    /**
     * DP，另类解法
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
