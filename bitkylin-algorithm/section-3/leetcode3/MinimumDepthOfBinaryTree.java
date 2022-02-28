/**
 * <p>给定一个二叉树，找出其最小深度。</p>
 *
 * <p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。</p>
 *
 * <p><strong>说明：</strong>叶子节点是指没有子节点的节点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg" style="width: 432px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [2,null,3,null,4,null,5,null,6]
 * <strong>输出：</strong>5
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数的范围在 <code>[0, 10<sup>5</sup>]</code> 内</li>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 625</li><li>👎 0</li></div>
 */

package leetcode3;

public class MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
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

    class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null) {
                return minDepth(root.right) + 1;
            }
            if (root.right == null) {
                return minDepth(root.left) + 1;
            }
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return Math.min(left, right) + 1;
        }
    }

    /**
     * 「递归」
     */
    class Solution2_1 {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = minDepth(root.left);
            int right = minDepth(root.right);

            if (left == 0 || right == 0) {
                return left + right + 1;
            }
            return Math.min(left, right) + 1;
        }
    }

    /**
     * 根据原解法，对代码再次进行精简
     */
    class Solution2_2 {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            return left == 0 || right == 0 ? left + right + 1 : Math.min(left, right) + 1;
        }
    }
}
