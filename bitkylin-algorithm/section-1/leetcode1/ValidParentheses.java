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
 * <div><div>Related Topics</div><div><li>栈</li><li>字符串</li></div></div><br><div><li>👍 2952</li><li>👎 0</li></div>
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

    class Solution {
        private Map<Character, Character> map = new HashMap<>();

        {
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
        }

        public boolean isValid(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            char[] arr = s.toCharArray();
            for (Character c : arr) {
                if (stack.isEmpty() || map.containsKey(c)) {
                    stack.push(c);
                } else if (!c.equals(map.get(stack.pop()))) {
                    return false;
                }
            }
            return stack.isEmpty();
        }
    }

    class Solution2 {
        private Map<Character, Character> map = new HashMap<>();

        {
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');
        }

        public boolean isValid(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            char[] arr = s.toCharArray();
            for (char c : arr) {
                if (stack.isEmpty() || !map.containsKey(c)) {
                    stack.push(c);
                } else if (!map.get(c).equals(stack.pop())) {
                    return false;
                }
            }
            return stack.isEmpty();
        }
    }

    /**
     * 优化
     */
    class Solution3 {
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
