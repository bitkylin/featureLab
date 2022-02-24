/**
 * <p>给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre><strong>输入：</strong>&quot;Let&#39;s take LeetCode contest&quot;
 * <strong>输出：</strong>&quot;s&#39;teL ekat edoCteeL tsetnoc&quot;
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong><strong><strong><strong>提示：</strong></strong></strong></strong></p>
 *
 * <ul>
 * <li>在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 396</li><li>👎 0</li></div>
 */

package leetcode9;

public class ReverseWordsInAStringIii {

    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAStringIii().new Solution();
    }

    /**
     * 参考解法 {@link ReverseWordsInAString}
     * 由前到后
     */
    class Solution {
        public String reverseWords(String s) {
            char[] arr = s.toCharArray();
            for (int left = 0; left < s.length(); ) {
                while (left < s.length() && s.charAt(left) == ' ') left++;
                int right = left;
                while (right < s.length() && s.charAt(right) != ' ') right++;
                reverse(arr, left, right - 1);
                left = right;
            }
            return new String(arr);
        }

        private void reverse(char[] arr, int left, int right) {
            while (left < right) {
                swap(arr, left++, right--);
            }
        }

        private void swap(char[] arr, int left, int right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }

    /**
     * 由后到前
     */
    class Solution2 {
        public String reverseWords(String s) {
            char[] arr = (" " + s).toCharArray();
            int i = arr.length - 1;
            while (true) {
                while (i >= 0 && arr[i] == ' ') i--;
                int end = i;
                while (i >= 0 && arr[i] != ' ') i--;
                if (i < 0) return new String(arr).substring(1, arr.length);
                reverse(arr, i + 1, end);
            }

        }

        private void reverse(char[] arr, int start, int end) {
            for (; start < end; start++, end--) {
                char temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
    }
}
