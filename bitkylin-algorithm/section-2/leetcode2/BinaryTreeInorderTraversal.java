//给定一个二叉树，返回它的中序 遍历。
//
// 示例:
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 哈希表
// 👍 714 👎 0


package leetcode2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2, node3, null);
        TreeNode node1 = new TreeNode(1, null, node2);
        new BinaryTreeInorderTraversal().new Solution().inorderTraversal(node1);
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
     * 手动模拟递归栈
     */
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            Deque<Object> stack = new ArrayDeque<>();
            if (root != null) {
                stack.push(root);
            }
            List<Integer> list = new ArrayList<>();
            while (!stack.isEmpty()) {
                resolve(stack, list);
            }
            return list;
        }

        private void resolve(Deque<Object> stack, List<Integer> list) {
            Object obj = stack.poll();
            if (obj instanceof TreeNode) {
                if (((TreeNode) obj).right != null) {
                    stack.push(((TreeNode) obj).right);
                }
                stack.push(((TreeNode) obj).val);
                if (((TreeNode) obj).left != null) {
                    stack.push(((TreeNode) obj).left);
                }
            } else if (obj instanceof Integer) {
                list.add((Integer) obj);
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            resolve(root, list);
            return list;
        }

        private void resolve(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }
            resolve(node.left, list);
            list.add(node.val);
            resolve(node.right, list);
        }
    }
}
