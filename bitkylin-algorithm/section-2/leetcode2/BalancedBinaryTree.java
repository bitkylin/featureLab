/**
 * <p>给定一个二叉树，判断它是否是高度平衡的二叉树。</p>
 *
 * <p>本题中，一棵高度平衡二叉树定义为：</p>
 *
 * <blockquote>
 * <p>一个二叉树<em>每个节点 </em>的左右两个子树的高度差的绝对值不超过 1 。</p>
 * </blockquote>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg" style="width: 342px; height: 221px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg" style="width: 452px; height: 301px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,2,3,3,null,null,4,4]
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中的节点数在范围 <code>[0, 5000]</code> 内</li>
 * <li><code>-10<sup>4</sup> <= Node.val <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 855</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.HashMap;
import java.util.Map;

public class BalancedBinaryTree {

    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
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
     * 自底向上
     */

    class Solution {
        boolean res = true;

        public boolean isBalanced(TreeNode root) {
            height(root);
            return res;
        }

        private int height(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = height(node.left);
            int right = height(node.right);
            if (Math.abs(left - right) > 1) {
                res = false;
            }
            return Math.max(left, right) + 1;
        }
    }

    class Solution1 {
        public boolean isBalanced(TreeNode root) {
            int val = height(root);
            return val >= 0;
        }

        private int height(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = height(node.left);
            int right = height(node.right);
            if (left < 0 || right < 0) return -1;
            if (Math.abs(left - right) > 1) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }


    /**
     * 自顶向下
     */
    class Solution2 {

        public boolean isBalanced(TreeNode root) {
            Map<TreeNode, Integer> map = new HashMap<>();
            return solve(root, map);
        }

        private boolean solve(TreeNode node, Map<TreeNode, Integer> map) {
            if (node == null) {
                return true;
            }
            int left = depth(map, node.left);
            int right = depth(map, node.right);
            return Math.abs(left - right) <= 1
                    && solve(node.left, map)
                    && solve(node.right, map);
        }

        private int depth(Map<TreeNode, Integer> map, TreeNode node) {
            if (node == null) {
                return 0;
            }
            Integer value = map.get(node);
            if (value != null) {
                return value;
            }
            int left = depth(map, node.left);
            int right = depth(map, node.right);
            value = Math.max(left, right) + 1;
            map.put(node, value);
            return value;
        }
    }
}
