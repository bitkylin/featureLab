/**
 * <p>给定一个只包含三种字符的字符串：<code>（&nbsp;</code>，<code>）</code>&nbsp;和 <code>*</code>，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：</p>
 *
 * <ol>
 * <li>任何左括号 <code>(</code>&nbsp;必须有相应的右括号 <code>)</code>。</li>
 * <li>任何右括号 <code>)</code>&nbsp;必须有相应的左括号 <code>(</code>&nbsp;。</li>
 * <li>左括号 <code>(</code> 必须在对应的右括号之前 <code>)</code>。</li>
 * <li><code>*</code>&nbsp;可以被视为单个右括号 <code>)</code>&nbsp;，或单个左括号 <code>(</code>&nbsp;，或一个空字符串。</li>
 * <li>一个空字符串也被视为有效字符串。</li>
 * </ol>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> &quot;()&quot;
 * <strong>输出:</strong> True
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> &quot;(*)&quot;
 * <strong>输出:</strong> True
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> &quot;(*))&quot;
 * <strong>输出:</strong> True
 * </pre>
 *
 * <p><strong>注意:</strong></p>
 *
 * <ol>
 * <li>字符串大小将在 [1，100] 范围内。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>栈</li><li>贪心</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 439</li><li>👎 0</li></div>
 */

package leetcode1;

public class ValidParenthesisString {

    public static void main(String[] args) {
        Solution solution = new ValidParenthesisString().new Solution();
    }

    /**
     * 双指针法，始终维护左括号数量的最大值和最小值
     */
    class Solution {
        public boolean checkValidString(String s) {
            int left = 0;
            int right = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    left++;
                    right++;
                } else if (c == ')') {
                    left--;
                    right--;
                } else {
                    left--;
                    right++;
                }
                left = Math.max(0, left);
                if (left > right) return false;
            }
            return left == 0;
        }
    }

    /**
     * DFS，该解法其实是正确的，不过最后几个刁钻的用例过不去
     */
    class Solution2 {
        public boolean checkValidString(String s) {
            return solve(s, 0, 0, 0);
        }

        private boolean solve(String s, int i, int left, int right) {

            if (i >= s.length()) return left == right;
            if (right > left) return false;

            if (s.charAt(i) == '(') {
                return solve(s, i + 1, left + 1, right);
            } else if (s.charAt(i) == ')') {
                return solve(s, i + 1, left, right + 1);
            } else {
                return solve(s, i + 1, left, right + 1) ||
                        solve(s, i + 1, left + 1, right) ||
                        solve(s, i + 1, left, right);
            }
        }
    }
}
