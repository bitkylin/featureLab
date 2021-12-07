/**
 * <p>数字 <code>n</code>&nbsp;代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>["((()))","(()())","(())()","()(())","()()()"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>["()"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 8</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 2195</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 最佳实践（1）
     * DFS，在使用数组时，无需剪枝
     */
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            solve(res, n, new char[2 * n], 0, 0);
            return res;
        }

        private void solve(List<String> res, int n, char[] arr, int left, int right) {
            if (right >= n) {
                res.add(String.valueOf(arr));
                return;
            }
            if (left < n) {
                arr[left + right] = '(';
                solve(res, n, arr, left + 1, right);
            }
            if (left > right) {
                arr[left + right] = ')';
                solve(res, n, arr, left, right + 1);
            }
        }
    }


    /**
     * 最佳实践（2）
     * DFS，剪枝，使用 Deque
     */
    class Solution0 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            solve(res, n, new ArrayDeque<>(2 * n), 0, 0);
            return res;
        }

        private void solve(List<String> res, int n, Deque<Character> deque, int left, int right) {
            if (right >= n) {
                calc(res, deque);
                return;
            }
            if (left < n) {
                deque.addLast('(');
                solve(res, n, deque, left + 1, right);
                deque.removeLast();
            }
            if (left > right) {
                deque.addLast(')');
                solve(res, n, deque, left, right + 1);
                deque.removeLast();
            }
        }

        private void calc(List<String> res, Deque<Character> deque) {
            StringBuilder builder = new StringBuilder();
            for (Character c : deque) {
                builder.append(c);
            }
            res.add(builder.toString());
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，剪枝写法有两种
     */
    class Solution1 {
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
