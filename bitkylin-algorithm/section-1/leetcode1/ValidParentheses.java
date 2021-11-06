//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串
// 👍 1849 👎 0


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
        private final Map<Character, Character> MAP = new HashMap<Character, Character>() {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
        }};

        public boolean isValid(String s) {
            Deque<Character> deque = new ArrayDeque<>(s.length());
            for (char c : s.toCharArray()) {
                if (MAP.containsKey(c)) {
                    deque.push(c);
                } else {
                    Character poll = deque.poll();
                    if (poll == null || c != MAP.get(poll)) {
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
            for (char c : s.toCharArray()) {
                Character peek = deque.peek();
                if (peek != null && peek == c) {
                    deque.poll();
                } else {
                    if (c == '[') {
                        deque.push(']');
                    } else if (c == '(') {
                        deque.push(')');
                    } else if (c == '{') {
                        deque.push('}');
                    } else {
                        return false;
                    }
                }
            }
            return deque.isEmpty();
        }
    }
}
