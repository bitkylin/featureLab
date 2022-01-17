/**
 * <p>给你一个二叉树，请你返回其按 <strong>层序遍历</strong> 得到的节点值。 （即逐层地，从左到右访问所有节点）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong><br />
 * 二叉树：<code>[3,9,20,null,null,15,7]</code>,</p>
 *
 * <pre>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * </pre>
 *
 * <p>返回其层序遍历结果：</p>
 *
 * <pre>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * </pre>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1113</li><li>👎 0</li></div>
 */

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
     * BFS，循环
     */
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            offer(deque, root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                List<Integer> list = new ArrayList<>();
                res.add(list);
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    list.add(node.val);
                    offer(deque, node.left);
                    offer(deque, node.right);
                }
            }
            return res;
        }

        private void offer(Deque<TreeNode> deque, TreeNode node) {
            if (node != null) {
                deque.offer(node);
            }
        }
    }

    /**
     * DFS，递归
     */
    class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            solve(res, root, 1);
            return res;
        }

        private void solve(List<List<Integer>> res, TreeNode node, int level) {
            if (node == null) {
                return;
            }
            if (res.size() < level) {
                res.add(new ArrayList<>());
            }
            res.get(level - 1).add(node.val);
            solve(res, node.left, level + 1);
            solve(res, node.right, level + 1);
        }
    }
}
