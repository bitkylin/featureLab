//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。
//
// 示例:
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
//
// Related Topics 数组 动态规划
// 👍 691 👎 0


package leetcode6;

public class MinimumPathSum {

    public static void main(String[] args) {
        new MinimumPathSum().new Solution()
                .minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int xMax = grid.length - 1;
            int yMax = grid[0].length - 1;
            int[] dp = new int[yMax + 1];
            dp[0] = grid[0][0];
            for (int i = 1; i < grid[0].length; i++) {
                dp[i] = dp[i - 1] + grid[0][i];
            }
            for (int i = 1; i <= xMax; i++) {
                for (int j = 0; j <= yMax; j++) {
                    if (j == 0) {
                        dp[j] = dp[j] + grid[i][j];
                    } else {
                        dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                    }
                }
            }
            return dp[yMax];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}
