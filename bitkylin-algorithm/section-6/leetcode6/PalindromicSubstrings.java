//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//
//
//
// 示例 1：
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
//
//
// 示例 2：
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//
//
//
// 提示：
//
//
// 输入的字符串长度不会超过 1000 。
//
// Related Topics 字符串 动态规划
// 👍 424 👎 0


package leetcode6;

public class PalindromicSubstrings {

    public static void main(String[] args) {
        Solution solution = new PalindromicSubstrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 压缩到一维DP
     */
    class Solution {
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int length = s.length();
            char[] arr = s.toCharArray();
            int res = 0;
            boolean[] dp = new boolean[length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i - j <= 1 && arr[i] == arr[j]) {
                        dp[j] = true;
                        res++;
                    } else if (i - j > 1 && arr[i] == arr[j] && dp[j + 1]) {
                        dp[j] = true;
                        res++;
                    } else {
                        dp[j] = false;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 二维DP
     */
    class Solution2 {
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int length = s.length();
            char[] arr = s.toCharArray();
            int res = 0;
            boolean[][] dp = new boolean[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i - j <= 1) {
                        if (arr[i] == arr[j]) {
                            dp[i][j] = true;
                            res++;
                        }
                    } else if (arr[i] == arr[j] && dp[i - 1][j + 1]) {
                        dp[i][j] = true;
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
