//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例：
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
//
// Related Topics 字符串 回溯算法
// 👍 1390 👎 0


package leetcode7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * {@link leetcode3.GenerateParentheses}之前的解法「STACK，QUEUE」不再赘述
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS，剪枝写法有两种
     */
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            solve("", 0, 0, n, res);
            return res;
        }

        private void solve(String val, int left, int right, int n, List<String> res) {
            if (right > left || left > n) {
                return;
            }
            if (right == n) {
                res.add(val);
                return;
            }

            solve(val + "(", left + 1, right, n, res);
            solve(val + ")", left, right + 1, n, res);
        }

        private void solve2(String val, int left, int right, int n, List<String> res) {
            if (left == n && right == n) {
                res.add(val);
                return;
            }

            if (left < n) {
                solve(val + "(", left + 1, right, n, res);
            }
            if (right < left) {
                solve(val + ")", left, right + 1, n, res);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，非递归
     */
    class Solution2 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(new Node("", 0, 0));
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node.left == n && node.right == n) {
                    res.add(node.val);
                    continue;
                }
                if (node.right < node.left) {
                    stack.push(new Node(node.val + ")", node.left, node.right + 1));
                }
                if (node.left < n) {
                    stack.push(new Node(node.val + "(", node.left + 1, node.right));
                }
            }
            return res;
        }
    }

    class Node {

        String val;
        int left;
        int right;

        public Node(String val, int left, int right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * DFS，回溯
     * 此处需要撤销上一步结果
     */
    class Solution3 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            solve(new StringBuilder(), 0, 0, n, res);
            return res;
        }

        private void solve(StringBuilder builder, int left, int right, int n, List<String> res) {
            if (left > n || right > left) {
                return;
            }

            if (left == n && right == n) {
                res.add(builder.toString());
            }

            if (left < n) {
                builder.append("(");
                solve(builder, left + 1, right, n, res);
                builder.deleteCharAt(left + right);
            }
            if (right < left) {
                builder.append(")");
                solve(builder, left, right + 1, n, res);
                builder.deleteCharAt(left + right);
            }
        }
    }

    /**
     * DFS，回溯
     * 但是此处确实不需要撤销上一步计算结果，因为得到RES时之前计算的错误结果一定会被覆盖掉
     */
    class Solution4 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            char[] arr = new char[n * 2];
            solve(arr, 0, 0, n, res);
            return res;
        }

        private void solve(char[] arr, int left, int right, int n, List<String> res) {
            if (left > n || right > left) {
                return;
            }

            if (left == n && right == n) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < n * 2; i++) {
                    builder.append(arr[i]);
                }
                res.add(builder.toString());
            }

            if (left < n) {
                arr[left + right] = '(';
                solve(arr, left + 1, right, n, res);
            }
            if (right < left) {
                arr[left + right] = ')';
                solve(arr, left, right + 1, n, res);
            }
        }
    }
}
