//您需要在二叉树的每一行中找到最大的值。
//
// 示例：
//
//
//输入:
//
//          1
//         / \
//        3   2
//       / \   \
//      5   3   9
//
//输出: [1, 3, 9]
//
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 93 👎 0


package leetcode4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindLargestValueInEachTreeRow {

    public static void main(String[] args) {
        Solution solution = new FindLargestValueInEachTreeRow().new Solution();
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
     * BFS，效率较高
     */
    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    max = Math.max(max, node.val);
                    if (node.left != null) {
                        deque.offer(node.left);
                    }
                    if (node.right != null) {
                        deque.offer(node.right);
                    }
                }
                res.add(max);
            }
            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，效率极高
     */
    class Solution2 {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            resolve(res, 0, root);
            return res;
        }

        private void resolve(List<Integer> res, int level, TreeNode node) {
            if (node == null) {
                return;
            }
            if (res.size() < level + 1) {
                res.add(Integer.MIN_VALUE);
            }
            res.set(level, Math.max(res.get(level), node.val));
            resolve(res, level + 1, node.left);
            resolve(res, level + 1, node.right);
        }
    }
}
