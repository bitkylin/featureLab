//给定一个二叉树，返回它的 前序 遍历。
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
//输出: [1,2,3]
//
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树
// 👍 367 👎 0


package leetcode2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
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
     * 手动模拟递归栈
     */
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            Deque<Object> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                resolve(stack, list);
            }
            return list;
        }

        private void resolve(Deque<Object> stack, List<Integer> list) {
            Object obj = stack.poll();
            if (obj instanceof TreeNode) {
                push(stack, ((TreeNode) obj).right);
                push(stack, ((TreeNode) obj).left);
                push(stack, ((TreeNode) obj).val);
            } else if (obj instanceof Integer) {
                list.add((Integer) obj);
            }
        }

        private void push(Deque<Object> stack, Object obj) {
            if (obj != null) {
                stack.push(obj);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            resolve(root, list);
            return list;
        }

        private void resolve(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }
            list.add(node.val);
            resolve(node.left, list);
            resolve(node.right, list);
        }
    }
}
