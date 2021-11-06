//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 
// 👍 705 👎 0


package leetcode3;

public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 由下到上逐层计算高度，进而求得最大高度
     */
    class Solution {
        public int maxDepth(TreeNode root) {
            return resolve(root);
        }

        private int resolve(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = resolve(root.left);
            int right = resolve(root.right);
            return Math.max(left, right) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 逐层下探，找寻最大深度并更新局部变量
     */
    class Solution2 {
        int max = 0;

        public int maxDepth(TreeNode root) {
            resolve(root, 1);
            return max;
        }

        private void resolve(TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            if (max < depth) {
                max = depth;
            }
            resolve(root.left, depth + 1);
            resolve(root.right, depth + 1);
        }
    }
}