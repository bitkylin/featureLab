/**
 * <p>给定一棵树的前序遍历 <code>preorder</code> 与中序遍历  <code>inorder</code>。请构造二叉树并返回其根节点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" />
 * <pre>
 * <strong>Input:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * <strong>Output:</strong> [3,9,20,null,null,15,7]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>Input:</strong> preorder = [-1], inorder = [-1]
 * <strong>Output:</strong> [-1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= preorder.length <= 3000</code></li>
 * <li><code>inorder.length == preorder.length</code></li>
 * <li><code>-3000 <= preorder[i], inorder[i] <= 3000</code></li>
 * <li><code>preorder</code> 和 <code>inorder</code> 均无重复元素</li>
 * <li><code>inorder</code> 均出现在 <code>preorder</code></li>
 * <li><code>preorder</code> 保证为二叉树的前序遍历序列</li>
 * <li><code>inorder</code> 保证为二叉树的中序遍历序列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>数组</li><li>哈希表</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 1311</li><li>👎 0</li></div>
 */

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
     * 递归「DFS」
     * 技巧：二叉树的左子树在前序遍历、中序遍历中，占用连续的序号，且元素个数相同；右子树同样
     * 画个简单的AVL树，写出他的前序和中序遍历，对照着写就行
     * 参考题解：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/dong-hua-yan-shi-105-cong-qian-xu-yu-zhong-xu-bian/
     */
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> memo = new HashMap<>(inorder.length);
            for (int i = 0; i < inorder.length; i++) {
                memo.put(inorder[i], i);
            }
            return solve(memo, preorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        private TreeNode solve(Map<Integer, Integer> memo, int[] preorder, int preLeft, int preRight, int inLeft, int inRight) {
            if (preLeft > preRight || inLeft > inRight) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[preLeft]);
            int i = memo.get(preorder[preLeft]);
            node.left = solve(memo, preorder, preLeft + 1, preLeft + i - inLeft, inLeft, i - 1);
            node.right = solve(memo, preorder, preRight - inRight + i + 1, preRight, i + 1, inRight);
            return node;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}