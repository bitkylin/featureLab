/**
 * <p>给你一个二叉树的根节点 <code>root</code> ，判断其是否是一个有效的二叉搜索树。</p>
 *
 * <p><strong>有效</strong> 二叉搜索树定义如下：</p>
 *
 * <ul>
 * <li>节点的左子树只包含<strong> 小于 </strong>当前节点的数。</li>
 * <li>节点的右子树只包含 <strong>大于</strong> 当前节点的数。</li>
 * <li>所有左子树和右子树自身必须也是二叉搜索树。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;" />
 * <pre>
 * <strong>输入：</strong>root = [2,1,3]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;" />
 * <pre>
 * <strong>输入：</strong>root = [5,1,4,null,null,3,6]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>根节点的值是 5 ，但是右子节点的值是 4 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目范围在<code>[1, 10<sup>4</sup>]</code> 内</li>
 * <li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二叉树</li></div></div><br><div><li>👍 1313</li><li>👎 0</li></div>
 */

package leetcode3;

public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new ValidateBinarySearchTree().new Solution();
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
     * 「递归」二叉搜索树的中序遍历是单调递增的，直接使用中序遍历验证即可
     * 注：不可仅将节点值和其左右节点比较
     * 注：当前节点应大于左子树的所有节点，小于右子树的所有节点
     * 注；递归终止条件，处理当前层，递归下一层
     * 时间复杂度：O(n)
     */
    class Solution {
        private Integer min = null;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!isValidBST(root.left)) {
                return false;
            }
            if (min != null && min >= root.val) {
                return false;
            }
            min = root.val;
            return isValidBST(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}