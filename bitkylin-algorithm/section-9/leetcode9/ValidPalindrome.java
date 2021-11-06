//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。
//
// 示例 1:
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
//
//
// 示例 2:
//
// 输入: "race a car"
//输出: false
//
// Related Topics 双指针 字符串
// 👍 293 👎 0


package leetcode9;

public class ValidPalindrome {

    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 使用库函数，简化行数
     */
    class Solution {
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

    //leetcode submit region end(Prohibit modification and deletion)

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
    class Solution3 {
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
