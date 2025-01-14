/**
 * <p>给你一个字符串 <code>s</code>，找到 <code>s</code> 中最长的回文子串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "babad"
 * <strong>输出：</strong>"bab"
 * <strong>解释：</strong>"aba" 同样是符合题意的答案。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "cbbd"
 * <strong>输出：</strong>"bb"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a"
 * <strong>输出：</strong>"a"
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ac"
 * <strong>输出：</strong>"a"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 1000</code></li>
 * <li><code>s</code> 仅由数字和英文字母（大写和/或小写）组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 4513</li><li>👎 0</li></div>
 */

package leetcode9;

import leetcode6.PalindromicSubstrings;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 一维DP
     * 二维DP，极限压缩后，再次压缩到一维DP
     * 前序解法，参考 {@link PalindromicSubstrings}
     */
    class Solution {
        public String longestPalindrome(String s) {
            int[] point = new int[3];
            char[] arr = s.toCharArray();
            boolean[] dp = new boolean[s.length()];
            for (int x = 0; x < arr.length; x++) {
                for (int y = 0; y <= x; y++) {
                    if (x - y <= 1) {
                        dp[y] = arr[x] == arr[y];
                    } else {
                        dp[y] = dp[y + 1] && (arr[x] == arr[y]);
                    }
                    if (dp[y] && ((x - y) > point[0])) {
                        point[0] = x - y;
                        point[1] = y;
                        point[2] = x;
                    }
                }
            }
            return s.substring(point[1], point[2] + 1);
        }
    }

    class Solution1_2 {
        public String longestPalindrome(String s) {
            boolean[] dp = new boolean[s.length()];
            int max = 0;
            String res = "";
            for (int y = 0; y < s.length(); y++) {
                for (int x = 0; x <= y; x++) {
                    if ((y - x <= 1 && s.charAt(x) == s.charAt(y))
                            || (y - x > 1 && dp[x + 1] && s.charAt(x) == s.charAt(y))) {
                        dp[x] = true;
                        if (y - x + 1 > max) {
                            max = Math.max(max, y - x + 1);
                            res = s.substring(x, y + 1);
                        }
                    } else {
                        dp[x] = false;
                    }
                }
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 二维DP
     * if y - x <= 1    DP[x][y] = DP[x+1][y-1] && equal(x, y)
     * else             DP[x][y] = equal(x, y)
     */
    class Solution2_1 {
        public String longestPalindrome(String s) {
            boolean[][] dp = new boolean[s.length()][s.length()];
            int left = 0;
            int right = 0;
            for (int x = 0; x < s.length(); x++) {
                for (int y = x; y >= 0; y--) {
                    if (s.charAt(x) == s.charAt(y)) {
                        if (x - y <= 1) {
                            dp[x][y] = true;
                        } else {
                            dp[x][y] = dp[x - 1][y + 1];
                        }
                    }
                    if (dp[x][y] && x - y > right - left) {
                        left = y;
                        right = x;
                    }
                }
            }
            return s.substring(left, right + 1);
        }
    }

    /**
     * 二维DP，极限压缩
     */
    class Solution2_2 {
        public String longestPalindrome(String s) {
            boolean[][] dp = new boolean[s.length()][s.length()];
            int max = 0;
            String res = "";
            for (int y = 0; y < s.length(); y++) {
                for (int x = 0; x <= y; x++) {
                    if ((y - x <= 1 && s.charAt(x) == s.charAt(y))
                            || (y - x > 1 && dp[x + 1][y - 1] && s.charAt(x) == s.charAt(y))) {
                        dp[x][y] = true;
                        if (y - x + 1 > max) {
                            max = Math.max(max, y - x + 1);
                            res = s.substring(x, y + 1);
                        }
                    }
                }
            }
            return res;
        }
    }

// ------- 后面都是历史解法 -------------

    /**
     * DP
     * <p>
     * 状态转移方程：
     * right - left < 2：dp[i][j] = i - j + 1;
     * other           ：dp[i][j] = dp[i-1][j+1] == 0 ? 0 : dp[i-1][j+1] + 2;
     */
    class Solution3 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            String res = s.substring(0, 1);
            int[][] dp = new int[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j <= i; j++) {
                    if (s.charAt(i) != s.charAt(j)) {
                        continue;
                    }
                    if ((i - j) < 2) {
                        dp[i][j] = i - j + 1;
                    } else {
                        if (dp[i - 1][j + 1] == 0) {
                            continue;
                        }
                        dp[i][j] = dp[i - 1][j + 1] + 2;
                    }
                    if (dp[i][j] > res.length()) {
                        res = s.substring(j, i + 1);
                    }
                }
            }
            return res;
        }
    }

    /**
     * 中心扩散
     */
    class Solution4 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            String res = s.substring(0, 1);
            for (int i = 0; i < s.length() - 1; i++) {
                String str1 = solve(i, i, s);
                String str2 = solve(i, i + 1, s);
                str1 = str1.length() > str2.length() ? str1 : str2;
                res = str1.length() > res.length() ? str1 : res;
            }
            return res;
        }

        private String solve(int i, int j, String s) {
            while (i >= 0 && j < s.length()) {
                if (s.charAt(i) != s.charAt(j)) {
                    if (i + 1 > j - 1) {
                        return "";
                    }
                    return s.substring(i + 1, j);
                }
                i--;
                j++;
            }
            return s.substring(i + 1, j);
        }
    }

    /**
     * 暴力法
     */
    class Solution5 {
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    if ((j - i + 1) > res.length() && solve(i, j, s)) {
                        res = s.substring(i, j + 1);
                    }
                }
            }
            return res;
        }

        private boolean solve(int i, int j, String s) {
            while (i < j) {
                if (s.charAt(i++) != s.charAt(j--)) {
                    return false;
                }
            }
            return true;
        }
    }
}
