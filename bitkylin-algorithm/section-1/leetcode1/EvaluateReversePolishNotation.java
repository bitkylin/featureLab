/**
 * <p>根据<a href="https://baike.baidu.com/item/%E9%80%86%E6%B3%A2%E5%85%B0%E5%BC%8F/128437" target="_blank"> 逆波兰表示法</a>，求表达式的值。</p>
 *
 * <p>有效的算符包括 <code>+</code>、<code>-</code>、<code>*</code>、<code>/</code> 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。</p>
 *
 * <p> </p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <ul>
 * <li>整数除法只保留整数部分。</li>
 * <li>给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>tokens = ["2","1","+","3","*"]
 * <strong>输出：</strong>9
 * <strong>解释：</strong>该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>tokens = ["4","13","5","/","+"]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * <strong>输出：</strong>22
 * <strong>解释：</strong>
 * 该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= tokens.length <= 10<sup>4</sup></code></li>
 * <li><code>tokens[i]</code> 要么是一个算符（<code>"+"</code>、<code>"-"</code>、<code>"*"</code> 或 <code>"/"</code>），要么是一个在范围 <code>[-200, 200]</code> 内的整数</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>逆波兰表达式：</strong></p>
 *
 * <p>逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。</p>
 *
 * <ul>
 * <li>平常使用的算式则是一种中缀表达式，如 <code>( 1 + 2 ) * ( 3 + 4 )</code> 。</li>
 * <li>该算式的逆波兰表达式写法为 <code>( ( 1 2 + ) ( 3 4 + ) * )</code> 。</li>
 * </ul>
 *
 * <p>逆波兰表达式主要有以下两个优点：</p>
 *
 * <ul>
 * <li>去掉括号后表达式无歧义，上式即便写成 <code>1 2 + 3 4 + * </code>也可以依据次序计算出正确结果。</li>
 * <li>适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>数学</li></div></div><br><div><li>👍 414</li><li>👎 0</li></div>
 */

package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        new Solution().evalRPN(new String[]{"2", "1", "+", "3", "*"});
    }

    /**
     * 数组模拟栈
     */
    public static class Solution {

        public static int calc(int v1, int v2, String token) {
            if ("+".equals(token)) return v1 + v2;
            if ("-".equals(token)) return v1 - v2;
            if ("*".equals(token)) return v1 * v2;
            if ("/".equals(token)) return v1 / v2;
            return 0;
        }

        public int evalRPN(String[] tokens) {
            int[] stack = new int[tokens.length];
            int j = 0;
            for (int i = 0; i < tokens.length; i++) {
                if ("+-*/".contains(tokens[i])) {
                    stack[j - 2] = calc(stack[j - 2], stack[j - 1], tokens[i]);
                    j--;
                } else {
                    stack[j++] = Integer.parseInt(tokens[i]);
                }
            }
            return stack[j - 1];
        }
    }


    /**
     * 使用栈
     */
    class Solution2 {
        public int evalRPN(String[] tokens) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (String token : tokens) {
                if ("+-*/".contains(token)) {
                    int v2 = deque.removeLast();
                    int v1 = deque.removeLast();
                    deque.addLast(Solution.calc(v1, v2, token));
                } else {
                    deque.addLast(Integer.parseInt(token));
                }
            }
            return deque.peekLast();
        }
    }
}