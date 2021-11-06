//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
//
// 示例 1:
//
//
//输入: "aba"
//输出: True
//
//
// 示例 2:
//
//
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
//
//
// 注意:
//
//
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
//
// Related Topics 字符串
// 👍 282 👎 0


package leetcode9;

public class ValidPalindromeIi {

    public static void main(String[] args) {
        Solution solution = new ValidPalindromeIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 跟随题意，判断两次回文串即可
     */
    class Solution {

        private int left;
        private int right;

        public boolean validPalindrome(String s) {
            if (isPalindrome(s)) {
                return true;
            }
            int left = this.left - 1;
            int right = this.right + 1;
            return isPalindrome(s.substring(left, right)) ||
                    isPalindrome(s.substring(left + 1, right + 1));
        }

        public boolean isPalindrome(String s) {
            if (s == null || s.length() <= 1) {
                return true;
            }
            left = 0;
            right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left++) != s.charAt(right--)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 暴力法，超时
     */
    class Solution2 {
        public boolean validPalindrome(String s) {
            if (isPalindrome(s)) {
                return true;
            }
            for (int i = 0; i < s.length(); i++) {
                if (isPalindrome(new StringBuilder(s).deleteCharAt(i).toString())) {
                    return true;
                }
            }
            return false;
        }

        public boolean isPalindrome(String s) {
            return s.equals(new StringBuilder(s).reverse().toString());
        }
    }
}
