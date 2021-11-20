/**
 * <p>给定一个二叉树，返回它的 <em>后序&nbsp;</em>遍历。</p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre><strong>输入:</strong> [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 *
 * <strong>输出:</strong> [3,2,1]</pre>
 *
 * <p><strong>进阶:</strong>&nbsp;递归算法很简单，你可以通过迭代算法完成吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 710</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 手动模拟递归栈
     */
    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            ArrayDeque<Object> stack = new ArrayDeque<>();
            push(stack, root);
            while (!stack.isEmpty()) {
                Object obj = stack.pop();
                if (obj instanceof TreeNode) {
                    push(stack, ((TreeNode) obj).val);
                    push(stack, ((TreeNode) obj).right);
                    push(stack, ((TreeNode) obj).left);
                } else {
                    res.add((Integer) obj);
                }
            }
            return res;
        }

        private void push(Deque<Object> deque, Object obj) {
            if (obj != null) {
                deque.push(obj);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            solve(root, res);
            return res;
        }

        private void solve(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            solve(root.left, res);
            solve(root.right, res);
            res.add(root.val);
        }
    }
}
