//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
//
// 示例:
//
// 输入:
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4
// Related Topics 动态规划
// 👍 595 👎 0


package leetcode6;

public class MaximalSquare {

    public static void main(String[] args) {
        Solution solution = new MaximalSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int res = 0;
            int xMax = matrix.length - 1;
            int yMax = matrix[0].length - 1;
            int[][] dp = new int[xMax + 2][yMax + 2];
            for (int i = 0; i <= xMax; i++) {
                for (int j = 0; j <= yMax; j++) {
                    if (matrix[i][j] == '0') {
                        continue;
                    }
                    int min = Math.min(dp[i][j], dp[i][j + 1]);
                    min = Math.min(min, dp[i + 1][j]);
                    dp[i + 1][j + 1] = min + 1;
                    res = Math.max(min + 1, res);
                }
            }
            return res * res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
