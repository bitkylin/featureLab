/**
 * <p>给你一个字符串 <code>s</code> ，请你统计并返回这个字符串中 <strong>回文子串</strong> 的数目。</p>
 *
 * <p><strong>回文字符串</strong> 是正着读和倒过来读一样的字符串。</p>
 *
 * <p><strong>子字符串</strong> 是字符串中的由连续字符组成的一个序列。</p>
 *
 * <p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abc"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>三个回文子串: "a", "b", "c"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aaa"
 * <strong>输出：</strong>6
 * <strong>解释：</strong>6个回文子串: "a", "a", "a", "aa", "aa", "aaa"</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 1000</code></li>
 * <li><code>s</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 743</li><li>👎 0</li></div>
 */

package leetcode6;

public class PalindromicSubstrings {

    public static void main(String[] args) {
        Solution solution = new PalindromicSubstrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 压缩到一维DP，注意需要手动把无效的DP位进行重置「二维DP默认已重置；一维会复用，所以手动重置」
     */
    class Solution {
        public int countSubstrings(String s) {
            int res = 0;
            boolean[] dp = new boolean[s.length()];
            for (int y = 0; y < s.length(); y++) {
                for (int x = 0; x <= y; x++) {
                    if ((y - x <= 1 && s.charAt(x) == s.charAt(y))
                            || (y - x > 1 && dp[x + 1] && s.charAt(x) == s.charAt(y))) {
                        dp[x] = true;
                        res++;
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
    class Solution1 {
        public int countSubstrings(String s) {
            int res = 0;
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int y = 0; y < s.length(); y++) {
                for (int x = 0; x <= y; x++) {
                    if (y - x <= 1) {
                        if (s.charAt(x) == s.charAt(y)) {
                            dp[x][y] = true;
                            res++;
                        }
                    } else {
                        if (dp[x + 1][y - 1] && s.charAt(x) == s.charAt(y)) {
                            dp[x][y] = true;
                            res++;
                        }
                    }
                }
            }
            return res;
        }
    }

    /**
     * 二维DP，极限压缩
     */
    class Solution2 {
        public int countSubstrings(String s) {
            int res = 0;
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int y = 0; y < s.length(); y++) {
                for (int x = 0; x <= y; x++) {
                    if ((y - x <= 1 && s.charAt(x) == s.charAt(y))
                            || (y - x > 1 && dp[x + 1][y - 1] && s.charAt(x) == s.charAt(y))) {
                        dp[x][y] = true;
                        res++;
                    }
                }
            }
            return res;
        }
    }

    /**
     * 暴力法
     */
    class Solution3 {
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int res = 0;
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (int j = i; j < arr.length; j++) {
                    if (calc(i, j, arr)) {
                        res++;
                    }
                }
            }
            return res;
        }

        private boolean calc(int left, int right, char[] arr) {
            while (left < right) {
                if (arr[left++] != arr[right--]) {
                    return false;
                }
            }
            return true;
        }
    }
}
