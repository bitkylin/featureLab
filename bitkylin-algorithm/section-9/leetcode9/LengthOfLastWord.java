/**
 * <p>给你一个字符串 <code>s</code>，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。</p>
 *
 * <p><strong>单词</strong> 是指仅由字母组成、不包含任何空格字符的最大子字符串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "Hello World"
 * <strong>输出：</strong>5
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "   fly me   to   the moon  "
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "luffy is still joyboy"
 * <strong>输出：</strong>6
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅有英文字母和空格 <code>' '</code> 组成</li>
 * <li><code>s</code> 中至少存在一个单词</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 417</li><li>👎 0</li></div>
 */

package leetcode9;

public class LengthOfLastWord {

    public static void main(String[] args) {
        Solution solution = new LengthOfLastWord().new Solution();
    }

    class Solution {
        public int lengthOfLastWord(String s) {
            int right = s.length() - 1;
            while (right >= 0 && s.charAt(right) == ' ') right--;
            int left = right;
            while (left >= 0 && s.charAt(left) != ' ') left--;
            return right - left;
        }
    }

    class Solution2 {
        public int lengthOfLastWord(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] arr = s.toCharArray();
            int end = arr.length - 1, start = 0;
            for (; end >= 0; end--) {
                if (arr[end] != ' ') {
                    break;
                }
            }
            for (start = end; start >= 0; start--) {
                if (arr[start] == ' ') {
                    break;
                }
            }
            return end - start;
        }
    }
}

