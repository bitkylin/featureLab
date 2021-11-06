//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 1251 👎 0


package leetcode9;

public class EditDistance {

    public static void main(String[] args) {
        new EditDistance().new Solution().minDistance("a", "b");
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 数组边界值取最大索引+1更合理
     */
    class Solution {
        public int minDistance(String word1, String word2) {
            int max1 = word1 == null ? 0 : word1.length();
            int max2 = word2 == null ? 0 : word2.length();
            if (max1 == 0 || max2 == 0) {
                return Math.max(max1, max2);
            }

            int[][] dp = new int[max1 + 1][max2 + 1];
            for (int y = 1; y <= max2; y++) {
                dp[0][y] = dp[0][y - 1] + 1;
            }
            for (int x = 1; x <= max1; x++) {
                dp[x][0] = dp[x - 1][0] + 1;
            }
            for (int x = 1; x <= max1; x++) {
                for (int y = 1; y <= max2; y++) {
                    if (word1.charAt(x - 1) == word2.charAt(y - 1)) {
                        dp[x][y] = dp[x - 1][y - 1];
                    } else {
                        dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]);
                        dp[x][y] = Math.min(dp[x][y], dp[x - 1][y - 1]) + 1;
                    }
                }
            }
            return dp[max1][max2];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 数组边界取最大索引值，理解起来稍有困难，不推荐
     */
    class Solution2 {
        public int minDistance(String word1, String word2) {
            int max1 = word1 == null ? -1 : (word1.length() - 1);
            int max2 = word2 == null ? -1 : (word2.length() - 1);
            if (max1 == -1 || max2 == -1) {
                return Math.max(max1, max2) + 1;
            }

            int[][] dp = new int[max1 + 2][max2 + 2];
            for (int y = 1; y <= max2 + 1; y++) {
                dp[0][y] = dp[0][y - 1] + 1;
            }
            for (int x = 1; x <= max1 + 1; x++) {
                dp[x][0] = dp[x - 1][0] + 1;
            }
            for (int x = 1; x <= max1 + 1; x++) {
                for (int y = 1; y <= max2 + 1; y++) {
                    if (word1.charAt(x - 1) == word2.charAt(y - 1)) {
                        dp[x][y] = dp[x - 1][y - 1];
                    } else {
                        dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]);
                        dp[x][y] = Math.min(dp[x][y], dp[x - 1][y - 1]) + 1;
                    }
                }
            }
            return dp[max1 + 1][max2 + 1];
        }
    }
}
