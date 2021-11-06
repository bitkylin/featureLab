//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1：
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
//
// 示例 2：
//
// 输入: "cbbd"
//输出: "bb"
//
// Related Topics 字符串 动态规划
// 👍 2922 👎 0


package leetcode9;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        new LongestPalindromicSubstring().new Solution().longestPalindrome("babad");
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP
     * <p>
     * 状态转移方程：
     * right - left < 2：dp[i][j] = true;
     * other           ：dp[i][j] = dp[i-1][j+1];
     */
    class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            String res = s.substring(0, 1);
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j <= i; j++) {
                    if (s.charAt(i) != s.charAt(j)) {
                        continue;
                    }
                    if ((i - j) < 2) {
                        dp[i][j] = true;
                    } else {
                        if (dp[i - 1][j + 1]) {
                            dp[i][j] = true;
                        }
                    }
                    if (dp[i][j] && i - j + 1 > res.length()) {
                        res = s.substring(j, i + 1);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP
     * <p>
     * 状态转移方程：
     * right - left < 2：dp[i][j] = i - j + 1;
     * other           ：dp[i][j] = dp[i-1][j+1] == 0 ? 0 : dp[i-1][j+1] + 2;
     */
    class Solution2 {
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
    class Solution3 {
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
    class Solution4 {
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
