/**
 * <p>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。</p>
 *
 * <p><strong>说明：</strong>本题中，我们将空字符串定义为有效的回文串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> "A man, a plan, a canal: Panama"
 * <strong>输出:</strong> true
 * <strong>解释：</strong>"amanaplanacanalpanama" 是回文串
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> "race a car"
 * <strong>输出:</strong> false
 * <strong>解释：</strong>"raceacar" 不是回文串
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 2 * 10<sup>5</sup></code></li>
 * <li>字符串 <code>s</code> 由 ASCII 字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 465</li><li>👎 0</li></div>
 */

package leetcode9;

public class ValidPalindrome {

    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
    }

    class Solution {
        public boolean isPalindrome(String s) {
            int i = 0;
            int j = s.length();
            s = s.toLowerCase();
            while (i <= j) {
                while (i <= j && !valid(s, i)) i++;
                while (i <= j && !valid(s, j)) j--;
                if (i > j) return true;
                if (s.charAt(i++) != s.charAt(j--)) return false;
            }
            return true;
        }

        private boolean valid(String s, int i) {
            if (i < 0 || i >= s.length()) return false;
            return ((s.charAt(i) >= '0' && s.charAt(i) <= '9')
                    || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                    || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'));
        }
    }

    /**
     * 使用库函数，简化行数
     */
    class Solution1 {
        public boolean isPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            s = s.toLowerCase();
            char[] arr = s.toCharArray();
            int left = 0;
            int right = arr.length - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(arr[left])) left++;
                while (left < right && !Character.isLetterOrDigit(arr[right])) right--;
                if (left >= right) {
                    return true;
                }
                if (arr[left++] != arr[right--]) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 去除特殊符号，只保留字母数字然后比较
     */
    class Solution2 {
        public boolean isPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            s = s.toLowerCase();
            StringBuilder builder = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    builder.append(c);
                }
            }
            return builder.toString().equals(builder.reverse().toString());
        }
    }

    /**
     * 不使用库函数，自己硬写
     */
    class Solution3_1 {
        public boolean isPalindrome(String s) {
            char[] arr = s.toCharArray();
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                while (left < right && !solve(arr, left)) left++;
                while (left < right && !solve(arr, right)) right--;
                if (arr[left++] != arr[right--]) {
                    return false;
                }
            }
            return true;
        }

        private boolean solve(char[] arr, int index) {
            if ((arr[index] >= 'a' && arr[index] <= 'z') || (arr[index] >= '0' && arr[index] <= '9')) {
                return true;
            }
            if (arr[index] >= 'A' && arr[index] <= 'Z') {
                arr[index] += 0x20;
                return true;
            }
            return false;
        }
    }

    class Solution3_2 {
        public boolean isPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            char[] arr = s.toCharArray();
            int left = 0;
            int right = arr.length - 1;
            while (left < right) {
                while (left < right && !valid(arr[left])) left++;
                while (left < right && !valid(arr[right])) right--;
                if (left >= right) return true;
                toLowerCase(arr, left);
                toLowerCase(arr, right);
                if (arr[left++] != arr[right--]) {
                    return false;
                }
            }
            return true;
        }

        private void toLowerCase(char[] arr, int left) {
            if (arr[left] >= 0x41 && arr[left] <= 0x5a) {
                arr[left] += 0x20;
            }
        }

        private boolean valid(char c) {
            if (c >= 0x30 && c <= 0x39) {
                return true;
            }
            if (c >= 0x41 && c <= 0x5a) {
                return true;
            }
            if (c >= 0x61 && c <= 0x7a) {
                return true;
            }
            return false;
        }
    }
}
