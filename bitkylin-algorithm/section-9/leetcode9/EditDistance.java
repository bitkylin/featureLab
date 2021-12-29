/**
 * <p>给你两个单词 <code>word1</code> 和 <code>word2</code>，请你计算出将 <code>word1</code> 转换成 <code>word2</code><em> </em>所使用的最少操作数 。</p>
 *
 * <p>你可以对一个单词进行如下三种操作：</p>
 *
 * <ul>
 * <li>插入一个字符</li>
 * <li>删除一个字符</li>
 * <li>替换一个字符</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>word1 = "horse", word2 = "ros"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>word1 = "intention", word2 = "execution"
 * <strong>输出：</strong>5
 * <strong>解释：</strong>
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= word1.length, word2.length <= 500</code></li>
 * <li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 2015</li><li>👎 0</li></div>
 */

package leetcode9;

public class EditDistance {

    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP，二维数组
     * if ( i == j ) DP[i][j] = DP[i-1][j-1] + 1
     * else          DP[i][j] = Min( DP[i-1][j-1], DP[i][j-1], DP[i-1][j] ) + 1
     */
    class Solution {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= word2.length(); j++) {
                dp[0][j] = j;
            }
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 历史解法
     * 数组边界值取最大索引+1更合理
     */
    class Solution1 {
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

    /**
     * 历史解法
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
