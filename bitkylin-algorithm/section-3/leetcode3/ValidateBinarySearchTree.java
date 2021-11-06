//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 772 👎 0


package leetcode3;

public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node2.left = node1;
        node2.right = node3;

        new ValidateBinarySearchTree().new Solution().isValidBST(node2);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 二叉搜索树的中序遍历是单调递增的，直接使用中序遍历验证即可
     */
    class Solution {
        Integer min = null;

        public boolean isValidBST(TreeNode root) {
            return resolve(root);
        }

        private boolean resolve(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!resolve(root.left)) {
                return false;
            }
            if (min != null && min >= root.val) {
                return false;
            }
            min = root.val;
            return resolve(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}