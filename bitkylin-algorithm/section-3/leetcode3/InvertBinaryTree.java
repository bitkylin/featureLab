//翻转一棵二叉树。 
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树 
// 👍 631 👎 0


package leetcode3;

import java.util.Deque;
import java.util.LinkedList;

public class InvertBinaryTree {

    public static void main(String[] args) {
        Solution solution = new InvertBinaryTree().new Solution();
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
     * 直接套用递归模板即可，很简单
     */
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeNode k = root.left;
            root.left = root.right;
            root.right = k;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS「深度优先遍历」使用栈
     */
    class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Deque<TreeNode> deque = new LinkedList<>();
            deque.addLast(root);

            while (!deque.isEmpty()) {
                TreeNode node = deque.removeLast();
                if (node.right != null) {
                    deque.addLast(node.right);
                }
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                TreeNode k = node.left;
                node.left = node.right;
                node.right = k;
            }

            return root;
        }
    }

    /**
     * 层序遍历，BFS「广度优先遍历」使用队列
     */
    class Solution3 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Deque<TreeNode> deque = new LinkedList<>();
            deque.addLast(root);

            while (!deque.isEmpty()) {
                TreeNode node = deque.removeFirst();
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
                TreeNode k = node.left;
                node.left = node.right;
                node.right = k;
            }

            return root;
        }
    }


}