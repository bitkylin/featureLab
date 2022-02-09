/**
 * <p>给你二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>锯齿形层序遍历</strong> 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>[[3],[20,9],[15,7]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 2000]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 586</li><li>👎 0</li></div>
 */

package top1;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();
        TreeNode root = new TreeNode();
        root.val = 0;
        root.left = new TreeNode();
        root.right = new TreeNode();

        TreeNode node1 = root.left;
        node1.val = 2;
        node1.left = new TreeNode();


        TreeNode node2 = root.right;
        node2.val = 4;
        node2.left = new TreeNode();
        node2.right = new TreeNode();

        TreeNode node21 = node1.left;
        node21.val = 1;
        node21.left = new TreeNode();
        node21.right = new TreeNode();
        TreeNode node23 = node2.left;
        node23.val = 3;
        node23.right = new TreeNode();
        TreeNode node24 = node2.right;
        node24.val = -1;
        node24.right = new TreeNode();

        TreeNode node31 = node21.left;
        node31.val = 5;
        TreeNode node32 = node21.right;
        node32.val = 1;

        TreeNode node33 = node23.right;
        node33.val = 6;
        TreeNode node34 = node24.right;
        node34.val = 8;

        solution.zigzagLevelOrder(root);
    }

    public static class TreeNode {
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
     * 写代码要细心，真的没有难度
     */
    class Solution {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            offer(deque, root);
            int level = 0;
            while (!deque.isEmpty()) {
                int size = deque.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    offer(deque, node.left);
                    offer(deque, node.right);
                    list.add(node.val);
                }
                if (level % 2 == 1) Collections.reverse(list);
                level++;
                res.add(list);
            }
            return res;
        }

        private void offer(Deque<TreeNode> deque, TreeNode node) {
            if (node != null) {
                deque.offer(node);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
