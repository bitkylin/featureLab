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
// 👍 1320 👎 0


package leetcode4;

import java.util.*;

public class GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS，简单遍历
     */
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            resolve(res, "", n, 0, 0);
            return res;
        }

        private void resolve(List<String> res, String value, int n, int left, int right) {
            if (left < right || left > n) {
                return;
            }
            if (left == n && right == n) {
                res.add(value);
                return;
            }
            resolve(res, value + "(", n, left + 1, right);
            resolve(res, value + ")", n, left, right + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS改为回溯，效率有明显下降
     */
    class Solution2 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            resolve(res, n, new ArrayDeque<>(2 * n), 0, 0);
            return res;
        }

        private void resolve(List<String> res, int n, Deque<String> stack, int left, int right) {
            if (left < right) {
                return;
            }
            if (left > n) {
                return;
            }
            if (left == n && right == n) {
                StringBuilder builder = new StringBuilder();
                Iterator<String> iterator = stack.descendingIterator();
                while (iterator.hasNext()) {
                    builder.append(iterator.next());
                }
                res.add(builder.toString());
            }
            stack.push("(");
            resolve(res, n, stack, left + 1, right);
            stack.pop();
            stack.push(")");
            resolve(res, n, stack, left, right + 1);
            stack.pop();
        }
    }


    /**
     * 循环法，BFS层序遍历，效率较低
     * 将offer改为push，将poll改为pop，调换压栈顺序，即可整体改为DFS，效率仍较低
     */
    class Solution3 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(new TreeNode("", 0, 0));
            while (!deque.isEmpty()) {
                TreeNode node = deque.poll();
                resolve(res, deque, node, n);
            }
            return res;
        }

        private void resolve(List<String> res, Deque<TreeNode> deque, TreeNode node, int n) {
            if (node.left > n || node.left < node.right) {
                return;
            }
            if (node.left == n && node.right == n) {
                res.add(node.value);
                return;
            }
            deque.offer(new TreeNode(node.value + "(", node.left + 1, node.right));
            deque.offer(new TreeNode(node.value + ")", node.left, node.right + 1));
        }

        class TreeNode {
            String value;
            int left;
            int right;

            public TreeNode(String value, int left, int right) {
                this.value = value;
                this.left = left;
                this.right = right;
            }

        }
    }
}
