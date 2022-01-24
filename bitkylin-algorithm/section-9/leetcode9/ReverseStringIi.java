/**
 * <p>给定一个字符串 <code>s</code> 和一个整数 <code>k</code>，从字符串开头算起，每计数至 <code>2k</code> 个字符，就反转这 <code>2k</code> 字符中的前 <code>k</code> 个字符。</p>
 *
 * <ul>
 * <li>如果剩余字符少于 <code>k</code> 个，则将剩余字符全部反转。</li>
 * <li>如果剩余字符小于 <code>2k</code> 但大于或等于 <code>k</code> 个，则反转前 <code>k</code> 个字符，其余字符保持原样。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abcdefg", k = 2
 * <strong>输出：</strong>"bacdfeg"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abcd", k = 2
 * <strong>输出：</strong>"bacd"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅由小写英文组成</li>
 * <li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 244</li><li>👎 0</li></div>
 */

package leetcode9;

public class ReverseStringIi {

    public static void main(String[] args) {
        Solution solution = new ReverseStringIi().new Solution();
    }

    class Solution {
        public String reverseStr(String s, int k) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i += 2 * k) {
                solve(arr, i, Math.min(arr.length - 1, i + k - 1));
            }
            return new String(arr);
        }

        private void solve(char[] arr, int left, int right) {
            for (; left < right; left++, right--) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
    }

    /**
     * 另一种思路吧，不过这个解法没太大价值
     */
    class Solution2 {
        public String reverseStr(String s, int k) {
            char[] arr = new char[s.length()];
            for (int i = 0; i < s.length(); i += 2 * k) {
                solve(arr, s, i, k);
            }
            return new String(arr);
        }

        private void solve(char[] arr, String s, int l, int k) {
            int left = l;
            int r = Math.min(s.length() - 1, l + k - 1);
            int right = r;
            while (left <= right) {
                arr[left] = s.charAt(right);
                arr[right] = s.charAt(left);
                left++;
                right--;
            }

            left = r + 1;
            right = Math.min(s.length() - 1, l + 2 * k - 1);
            while (left <= right) {
                arr[left] = s.charAt(left++);
            }
        }
    }
}
