/**
 * <p>给定一个非空字符串 <code>s</code>，<strong>最多</strong>删除一个字符。判断是否能成为回文字符串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "aba"
 * <strong>输出:</strong> true
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "abca"
 * <strong>输出:</strong> true
 * <strong>解释:</strong> 你可以删除c字符。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "abc"
 * <strong>输出:</strong> false</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 10<sup>5</sup></code></li>
 * <li><code>s</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 443</li><li>👎 0</li></div>
 */

package leetcode9;

public class ValidPalindromeIi {

    public static void main(String[] args) {
        Solution solution = new ValidPalindromeIi().new Solution();
    }

    /**
     * 跟随题意，判断两次回文串即可
     */
    class Solution {

        public boolean validPalindrome(String s) {
            int[] point = check(s, 0, s.length() - 1);
            if (point == null) {
                return true;
            }
            return check(s, point[0], point[1] - 1) == null
                    || check(s, point[0] + 1, point[1]) == null;
        }

        private int[] check(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return new int[]{left, right};
                }
                left++;
                right--;
            }
            return null;
        }
    }

    class Solution2 {

        private int left;
        private int right;

        public boolean validPalindrome(String s) {
            if (check(s, 0, s.length() - 1)) {
                return true;
            }
            int l = left;
            int r = right;
            return check(s, l, r - 1) || check(s, l + 1, r);
        }

        private boolean check(String s, int l, int r) {
            left = l;
            right = r;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    /**
     * 暴力法，超时
     */
    class Solution3 {
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
