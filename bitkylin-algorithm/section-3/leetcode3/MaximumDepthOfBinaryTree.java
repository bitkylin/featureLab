/**
 * <p>给定一个二叉树，找出其最大深度。</p>
 *
 * <p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>
 *
 * <p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>
 *
 * <p><strong>示例：</strong><br>
 * 给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</p>
 *
 * <pre>    3
 * / \
 * 9  20
 * /  \
 * 15   7</pre>
 *
 * <p>返回它的最大深度&nbsp;3 。</p>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1042</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
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
     * 「递归」
     */
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 「循环」「BFS」「广度优先遍历」
     */
    class Solution2 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(root);
            int max = 0;
            while (!deque.isEmpty()) {
                max++;
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    offer(deque, node.left);
                    offer(deque, node.right);
                }
            }
            return max;
        }

        private void offer(Deque<TreeNode> deque, TreeNode node) {
            if (node != null) {
                deque.offer(node);
            }
        }
    }
}