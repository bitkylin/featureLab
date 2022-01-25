/**
 * <p>给你一个字符串 <code>s</code> ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "Hello"
 * <strong>输出：</strong>"hello"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "here"
 * <strong>输出：</strong>"here"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "LOVELY"
 * <strong>输出：</strong>"lovely"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 100</code></li>
 * <li><code>s</code> 由 ASCII 字符集中的可打印字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 198</li><li>👎 0</li></div>
 */

package leetcode9;

public class ToLowerCase {

    public static void main(String[] args) {
        Solution solution = new ToLowerCase().new Solution();
    }

    class Solution {
        public String toLowerCase(String s) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 'A' && arr[i] <= 'Z') {
                    arr[i] += 0x20;
                }
            }
            return new String(arr);
        }
    }

    class Solution2 {
        public String toLowerCase(String str) {
            StringBuilder builder = new StringBuilder();
            for (char c : str.toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    builder.append((char) (c - 'A' + 'a'));
                } else {
                    builder.append(c);
                }
            }
            return builder.toString();
        }
    }
}
