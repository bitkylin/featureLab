/**
 * <p>给定一个二叉树的根节点 <code>root</code> ，返回它的 <strong>中序</strong> 遍历。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="width: 202px; height: 324px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3]
 * <strong>输出：</strong>[1,3,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_5.jpg" style="width: 202px; height: 202px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2]
 * <strong>输出：</strong>[2,1]
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_4.jpg" style="width: 202px; height: 202px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>-100 <= Node.val <= 100</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶:</strong> 递归算法很简单，你可以通过迭代算法完成吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1168</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
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

    /**
     * 手动模拟递归栈
     */
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            ArrayDeque<Object> stack = new ArrayDeque<>();
            push(stack, root);
            while (!stack.isEmpty()) {
                Object obj = stack.pop();
                if (obj instanceof TreeNode) {
                    push(stack, ((TreeNode) obj).right);
                    push(stack, ((TreeNode) obj).val);
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

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            solve(root, res);
            return res;
        }

        private void solve(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            solve(root.left, res);
            res.add(root.val);
            solve(root.right, res);
        }
    }
}
