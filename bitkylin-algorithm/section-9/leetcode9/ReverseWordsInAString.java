/**
 * <p>给你一个字符串 <code>s</code> ，逐个翻转字符串中的所有 <strong>单词</strong> 。</p>
 *
 * <p><strong>单词</strong> 是由非空格字符组成的字符串。<code>s</code> 中使用至少一个空格将字符串中的 <strong>单词</strong> 分隔开。</p>
 *
 * <p>请你返回一个翻转 <code>s</code> 中单词顺序并用单个空格相连的字符串。</p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <ul>
 * <li>输入字符串 <code>s</code> 可以在前面、后面或者单词间包含多余的空格。</li>
 * <li>翻转后单词间应当仅用一个空格分隔。</li>
 * <li>翻转后的字符串中不应包含额外的空格。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "<code>the sky is blue</code>"
 * <strong>输出：</strong>"<code>blue is sky the</code>"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "  hello world  "
 * <strong>输出：</strong>"world hello"
 * <strong>解释：</strong>输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a good   example"
 * <strong>输出：</strong>"example good a"
 * <strong>解释：</strong>如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "  Bob    Loves  Alice   "
 * <strong>输出：</strong>"Alice Loves Bob"
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "Alice does not even like bob"
 * <strong>输出：</strong>"bob like even not does Alice"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 10<sup>4</sup></code></li>
 * <li><code>s</code> 包含英文大小写字母、数字和空格 <code>' '</code></li>
 * <li><code>s</code> 中 <strong>至少存在一个</strong> 单词</li>
 * </ul>
 *
 * <ul>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>请尝试使用 <code><em>O</em>(1)</code> 额外空间复杂度的原地解法。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 436</li><li>👎 0</li></div>
 */

package leetcode9;

import java.util.*;
import java.util.stream.Collectors;

public class ReverseWordsInAString {

    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAString().new Solution();
    }

    /**
     * 自行编写转换方法，后到前遍历，优化行数
     */
    class Solution {
        public String reverseWords(String s) {
            char[] arr = s.toCharArray();
            StringBuilder builder = new StringBuilder();
            int i = arr.length - 1;
            while (true) {
                while (i >= 0 && arr[i] == ' ') i--;
                int end = i;
                while (i >= 0 && arr[i] != ' ') i--;
                int start = i + 1;
                if (start < 0 || end < 0) {
                    return builder.toString();
                }
                if (builder.length() != 0) {
                    builder.append(" ");
                }
                builder.append(s, start, end + 1);
            }
        }
    }

    class Solution2 {
        public String reverseWords(String s) {
            char[] arr = s.toCharArray();
            StringBuilder builder = new StringBuilder();
            int i = arr.length - 1;
            while (true) {
                while (i >= 0 && arr[i] == ' ') i--;
                int end = i;
                while (i >= 0 && arr[i] != ' ') i--;
                int start = i + 1;
                if (start < 0 || end < 0) return builder.toString().trim();
                builder.append(s, start, end + 1).append(" ");
            }
        }
    }

    /**
     * 系统库函数
     */
    class Solution3 {
        public String reverseWords(String s) {
            List<String> list = Arrays.stream(s.split(" "))
                    .filter(str -> !str.isEmpty())
                    .collect(Collectors.toList());
            Collections.reverse(list);
            return String.join(" ", list);
        }
    }

    // 后面的不看了

    /**
     * 自行编写转换方法，前到后遍历
     */
    class Solution4 {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] arr = s.toCharArray();
            int max = arr.length - 1;
            Deque<String> deque = new ArrayDeque<>();
            for (int i = 0; i <= max; ) {
                while (i <= max && arr[i] == ' ') {
                    i++;
                }
                int start = i;
                while (i <= max && arr[i] != ' ') {
                    i++;
                }
                int end = i;
                if (start > max) {
                    continue;
                }
                deque.addFirst(s.substring(start, end));
            }
            return String.join(" ", deque);
        }
    }

    /**
     * 自行编写转换方法，从后到前遍历
     */
    class Solution5 {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] arr = s.toCharArray();
            int idx = arr.length - 1;
            StringBuilder builder = new StringBuilder();
            while (idx >= 0) {
                while (idx >= 0 && arr[idx] == ' ') {
                    idx--;
                }
                int end = idx;
                while (idx >= 0 && arr[idx] != ' ') {
                    idx--;
                }
                int start = idx + 1;
                if (end >= 0) {
                    while (start <= end) {
                        builder.append(arr[start++]);
                    }
                    builder.append(' ');
                }
            }
            if (builder.charAt(builder.length() - 1) == ' ') {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
    }
}
