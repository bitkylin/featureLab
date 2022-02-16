/**
 * <p>给定两个字符串 <code>text1</code> 和 <code>text2</code>，返回这两个字符串的最长 <strong>公共子序列</strong> 的长度。如果不存在 <strong>公共子序列</strong> ，返回 <code>0</code> 。</p>
 *
 * <p>一个字符串的 <strong>子序列</strong><em> </em>是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。</p>
 *
 * <ul>
 * <li>例如，<code>"ace"</code> 是 <code>"abcde"</code> 的子序列，但 <code>"aec"</code> 不是 <code>"abcde"</code> 的子序列。</li>
 * </ul>
 *
 * <p>两个字符串的 <strong>公共子序列</strong> 是这两个字符串所共同拥有的子序列。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text1 = "abcde", text2 = "ace"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>最长公共子序列是 "ace" ，它的长度为 3 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text1 = "abc", text2 = "abc"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>最长公共子序列是 "abc" ，它的长度为 3 。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>text1 = "abc", text2 = "def"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>两个字符串没有公共子序列，返回 0 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= text1.length, text2.length <= 1000</code></li>
 * <li><code>text1</code> 和 <code>text2</code> 仅由小写英文字符组成。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 824</li><li>👎 0</li></div>
 */

package leetcode6;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        new LongestCommonSubsequence().new Solution().longestCommonSubsequence("abcde", "ace");
    }

    /**
     * DP:
     * if arr[x] == arr[y]: dp[x][y] = dp[x-1][y-1] + 1
     * else                 dp[x][y] = Max( dp[x-1][y], dp[x][y-1] )
     */
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            text1 = " " + text1;
            text2 = " " + text2;
            int[][] dp = new int[text1.length()][text2.length()];
            for (int x = 1; x < text1.length(); x++) {
                for (int y = 1; y < text2.length(); y++) {
                    if (text1.charAt(x) == text2.charAt(y)) {
                        dp[x][y] = dp[x - 1][y - 1] + 1;
                    } else {
                        dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - 1]);
                    }
                }
            }
            return dp[text1.length() - 1][text2.length() - 1];
        }
    }

    class Solution2 {
        public int longestCommonSubsequence(String text1, String text2) {
            int[][] dp = new int[text1.length()][text2.length()];
            if (text1.charAt(0) == text2.charAt(0)) {
                dp[0][0] = 1;
            }
            for (int x = 1; x < text1.length(); x++) {
                if (text1.charAt(x) == text2.charAt(0)) {
                    dp[x][0] = 1;
                } else {
                    dp[x][0] = dp[x - 1][0];
                }
            }
            for (int y = 1; y < text2.length(); y++) {
                if (text2.charAt(y) == text1.charAt(0)) {
                    dp[0][y] = 1;
                } else {
                    dp[0][y] = dp[0][y - 1];

                }
            }
            for (int x = 1; x < text1.length(); x++) {
                for (int y = 1; y < text2.length(); y++) {
                    if (text1.charAt(x) == text2.charAt(y)) {
                        dp[x][y] = dp[x - 1][y - 1] + 1;
                    } else {
                        dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - 1]);
                    }
                }
            }
            return dp[text1.length() - 1][text2.length() - 1];
        }
    }

    /**
     * 两个字符串的问题，都可以转化为二维DP数组的形式「做题经验」
     * dp状态矩阵始终跟踪arr[x-1][y-1]
     */
    class Solution3 {
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1.length() == 0 || text2.length() == 0) {
                return 0;
            }
            char[] cx = text1.toCharArray();
            char[] cy = text2.toCharArray();
            return resolve(cx.length - 1, cy.length - 1, cx, cy, new int[cx.length][cy.length]);
        }

        private int resolve(int x, int y, char[] cx, char[] cy, int[][] dp) {
            if (x < 0 || y < 0) {
                return 0;
            }
            if (cx[x] == cy[y]) {
                return resolve(x - 1, y - 1, cx, cy, dp);
            } else {
                dp[x][y] = Math.max(resolve(x - 1, y, cx, cy, dp), resolve(x, y - 1, cx, cy, dp));
                return dp[x][y];
            }
        }
    }

    /**
     * 对于边界条件进行额外判断
     */
    class Solution4 {
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1.length() == 0 || text2.length() == 0) {
                return 0;
            }
            char[] cx = text1.toCharArray();
            char[] cy = text2.toCharArray();
            int[][] dp = new int[cx.length][cy.length];
            for (int x = 0; x < cx.length; x++) {
                for (int y = 0; y < cy.length; y++) {
                    if (cx[x] == cy[y]) {
                        dp[x][y] = resolve(x - 1, y - 1, dp) + 1;
                    } else {
                        dp[x][y] = Math.max(resolve(x - 1, y, dp), resolve(x, y - 1, dp));
                    }
                }
            }
            return dp[cx.length - 1][cy.length - 1];
        }

        private int resolve(int x, int y, int[][] dp) {
            if (x < 0 || y < 0) {
                return 0;
            }
            return dp[x][y];
        }
    }
}
