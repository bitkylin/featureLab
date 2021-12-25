//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
//
//
// 若这两个字符串没有公共子序列，则返回 0。
//
//
//
// 示例 1:
//
// 输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace"，它的长度为 3。
//
//
// 示例 2:
//
// 输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
//
//
// 示例 3:
//
// 输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
//
//
//
//
// 提示:
//
//
// 1 <= text1.length <= 1000
// 1 <= text2.length <= 1000
// 输入的字符串只含有小写英文字符。
//
// Related Topics 动态规划
// 👍 250 👎 0


package leetcode6;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        Solution solution = new LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 两个字符串的问题，都可以转化为二维DP数组的形式「做题经验」
     * dp状态矩阵始终跟踪arr[x-1][y-1]
     */
    class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 对于边界条件进行额外判断
     */
    class Solution2 {
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
