//给定一个二叉树，返回它的 后序 遍历。
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
//输出: [3,2,1]
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树
// 👍 395 👎 0


package leetcode2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();
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
     * 手动模拟递归栈
     */
    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<Object> stack = new LinkedList<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                resolve(stack, res);
            }
            return res;
        }

        private void resolve(Deque<Object> stack, List<Integer> res) {
            Object obj = stack.poll();
            if (obj instanceof TreeNode) {
                push(stack, ((TreeNode) obj).val);
                push(stack, ((TreeNode) obj).right);
                push(stack, ((TreeNode) obj).left);
            } else if (obj instanceof Integer) {
                res.add((Integer) obj);
            }
        }

        private void push(Deque<Object> stack, Object obj) {
            if (obj == null) {
                return;
            }
            stack.push(obj);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            resolve(root, list);
            return list;
        }

        private void resolve(TreeNode node, List<Integer> list) {
            if (node == null) {
                return;
            }
            resolve(node.left, list);
            resolve(node.right, list);
            list.add(node.val);
        }
    }
}
