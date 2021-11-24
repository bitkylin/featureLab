/**
 * <p>翻转一棵二叉树。</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <p>输入：</p>
 *
 * <pre>     4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9</pre>
 *
 * <p>输出：</p>
 *
 * <pre>     4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1</pre>
 *
 * <p><strong>备注:</strong><br>
 * 这个问题是受到 <a href="https://twitter.com/mxcl" target="_blank">Max Howell </a>的 <a href="https://twitter.com/mxcl/status/608682016205344768" target="_blank">原问题</a> 启发的 ：</p>
 *
 * <blockquote>谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。</blockquote>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1071</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvertBinaryTree {

    public static void main(String[] args) {
        Solution solution = new InvertBinaryTree().new Solution();
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
     * 「递归」时间复杂度：O(n)
     */
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 「循环」「DFS『深度优先遍历』」时间复杂度：O(n)
     */
    class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node.left != null) stack.push(node.left);
                if (node.right != null) stack.push(node.right);
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            return root;
        }
    }

    /**
     * 「循环」「BFS『广度优先遍历』『层序遍历』」时间复杂度：O(n)
     */
    class Solution3 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(root);
            while (!deque.isEmpty()) {
                TreeNode node = deque.poll();
                if (node.left != null) deque.push(node.left);
                if (node.right != null) deque.push(node.right);
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            return root;
        }
    }

}