//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//
//
// 任何左括号 ( 必须有相应的右括号 )。
// 任何右括号 ) 必须有相应的左括号 ( 。
// 左括号 ( 必须在对应的右括号之前 )。
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
// 一个空字符串也被视为有效字符串。
//
//
// 示例 1:
//
//
//输入: "()"
//输出: True
//
//
// 示例 2:
//
//
//输入: "(*)"
//输出: True
//
//
// 示例 3:
//
//
//输入: "(*))"
//输出: True
//
//
// 注意:
//
//
// 字符串大小将在 [1，100] 范围内。
//
// Related Topics 字符串
// 👍 167 👎 0


package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesisString {

    public static void main(String[] args) {
        new ValidParenthesisString().new Solution().checkValidString("(()(())()())*((()(())))*()(*)()()(*((()((*(*))))()*()(()((()(*((()))*(((())(())))*))(()*))(()*)");
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 双栈
     */
    class Solution {
        public boolean checkValidString(String s) {
            if (s == null || s.isEmpty()) {
                return true;
            }
            Deque<Integer> left = new ArrayDeque<>();
            Deque<Integer> blear = new ArrayDeque<>();

            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '(') {
                    left.push(i);
                } else if (arr[i] == '*') {
                    blear.push(i);
                } else {
                    if (!left.isEmpty()) {
                        left.pop();
                    } else if (!blear.isEmpty()) {
                        blear.pop();
                    } else {
                        return false;
                    }
                }
            }
            while (!left.isEmpty() && !blear.isEmpty()) {
                if (left.pop() > blear.pop()) {
                    return false;
                }
            }
            return left.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS
     */
    class Solution2 {
        public boolean checkValidString(String s) {
            if (s == null || s.isEmpty()) {
                return true;
            }
            char[] arr = s.toCharArray();
            return recur(0, 0, arr);
        }

        private boolean recur(int i, int left, char[] arr) {
            if (i >= arr.length) {
                return left == 0;
            }
            if (left < 0) {
                return false;
            }

            char c = arr[i];
            boolean res = false;
            if (c == '(') {
                res = res || recur(i + 1, left + 1, arr);
            } else if (c == '*') {
                res = res || recur(i + 1, left + 1, arr);
                res = res || recur(i + 1, left - 1, arr);
                res = res || recur(i + 1, left, arr);
            } else {
                res = res || recur(i + 1, left - 1, arr);
            }
            return res;
        }
    }

}
