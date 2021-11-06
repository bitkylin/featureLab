//根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// Related Topics 树 深度优先搜索 数组
// 👍 688 👎 0


package leetcode3;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
     * 递归，
     * 技巧：二叉树的左子树在前序遍历、中序遍历中，占用连续的序号，且元素个数相同；右子树同样
     */
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return resolve(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
        }

        private TreeNode resolve(int[] preorder, int pi, int pj, int[] inorder, int ii, int ij, Map<Integer, Integer> map) {
            if (pi > pj) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[pi]);
            int middle = map.get(preorder[pi]);
            int length = middle - ii;

            root.left = resolve(preorder, pi + 1, pi + length, inorder, ii, middle - 1, map);
            root.right = resolve(preorder, pi + length + 1, pj, inorder, middle + 1, ij, map);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
