//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
//
// 示例 1:
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
//
//
// 示例 2:
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
//
// Related Topics 字符串 动态规划
// 👍 1077 👎 0


package leetcode9;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

    public static void main(String[] args) {
        Solution solution = new LongestValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     *
     */
    class Solution {
        public int longestValidParentheses(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }
            s = ")" + s;
            int max = 0;
            int[] dp = new int[s.length()];
            for (int i = 2; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    continue;
                }
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                }
                max = Math.max(dp[i], max);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 栈
     */
    class Solution2 {
        public int longestValidParentheses(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1);
            int max = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
            return max;
        }
    }
}
