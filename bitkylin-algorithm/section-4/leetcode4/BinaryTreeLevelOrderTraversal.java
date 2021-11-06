//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
//
//
// 示例：
//二叉树：[3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其层次遍历结果：
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
//
// Related Topics 树 广度优先搜索
// 👍 655 👎 0


package leetcode4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
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
     * DFS，递归，效率极高
     */
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> res = new ArrayList<>();
            resolve(root, 0, res);
            return res;
        }

        private void resolve(TreeNode node, int depth, List<List<Integer>> res) {
            if (node == null) {
                return;
            }
            if (res.size() < depth + 1) {
                res.add(new ArrayList<>());
            }
            res.get(depth).add(node.val);
            resolve(node.left, depth + 1, res);
            resolve(node.right, depth + 1, res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * BFS，循环，分别读取每一层，效率极高
     */
    class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> res = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(root);

            while (!deque.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                res.add(list);
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    list.add(node.val);
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                }
            }
            return res;
        }
    }
}
