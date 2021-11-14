/**
 * <p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code> 的字符串 <code>s</code> ，判断字符串是否有效。</p>
 *
 * <p>有效字符串需满足：</p>
 *
 * <ol>
 * <li>左括号必须用相同类型的右括号闭合。</li>
 * <li>左括号必须以正确的顺序闭合。</li>
 * </ol>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "()"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "()[]{}"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "(]"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "([)]"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "{[]}"
 * <strong>输出：</strong>true</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 10<sup>4</sup></code></li>
 * <li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>字符串</li></div></div><br><div><li>👍 2757</li><li>👎 0</li></div>
 */


package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ValidParentheses {

    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
        }};

        public boolean isValid(String s) {
            Deque<Character> deque = new ArrayDeque<>(s.length());
            for (char c : s.toCharArray()) {
                if (map.containsKey(c)) {
                    deque.push(c);
                } else {
                    if (deque.isEmpty() || c != map.get(deque.pop())) {
                        return false;
                    }
                }
            }
            return deque.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 优化
     */
    class Solution2 {
        public boolean isValid(String s) {
            Deque<Character> deque = new ArrayDeque<>(s.length());
            for (Character c : s.toCharArray()) {
                if ('[' == c) {
                    deque.push(']');
                } else if ('{' == c) {
                    deque.push('}');
                } else if ('(' == c) {
                    deque.push(')');
                } else if (deque.isEmpty() || !c.equals(deque.pop())) {
                    return false;
                }
            }
            return deque.isEmpty();
        }
    }
}
