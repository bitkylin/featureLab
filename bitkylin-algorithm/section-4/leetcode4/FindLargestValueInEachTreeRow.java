/**
 * <p>给定一棵二叉树的根节点 <code>root</code> ，请找出该二叉树中每一层的最大值。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例1：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [1,3,2,5,3,null,9]
 * <strong>输出: </strong>[1,3,9]
 * <strong>解释:</strong>
 * 1
 * / \
 * 3   2
 * / \   \
 * 5   3   9
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [1,2,3]
 * <strong>输出: </strong>[1,3]
 * <strong>解释:</strong>
 * 1
 * / \
 * 2   3
 * </pre>
 *
 * <p><strong>示例3：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [1]
 * <strong>输出: </strong>[1]
 * </pre>
 *
 * <p><strong>示例4：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [1,null,2]
 * <strong>输出: </strong>[1,2]
 * <strong>解释:</strong>
 * 1
 * \
 * 2
 * </pre>
 *
 * <p><strong>示例5：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>root = []
 * <strong>输出: </strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>二叉树的节点个数的范围是 <code>[0,10<sup>4</sup>]</code></li>
 * <li><meta charset="UTF-8" /><code>-2<sup>31</sup> <= Node.val <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 160</li><li>👎 0</li></div>
 */

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

    /**
     * DFS
     */
    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            solve(res, root, 0);
            return res;
        }

        private void solve(List<Integer> res, TreeNode node, int level) {
            if (node == null) {
                return;
            }
            if (res.size() <= level) {
                res.add(node.val);
            } else {
                res.set(level, Math.max(res.get(level), node.val));
            }
            solve(res, node.left, level + 1);
            solve(res, node.right, level + 1);
        }
    }

    /**
     * BFS
     */
    class Solution1 {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            offer(deque, root);
            while (!deque.isEmpty()) {
                int max = Integer.MIN_VALUE;
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.poll();
                    max = Math.max(max, node.val);
                    offer(deque, node.left);
                    offer(deque, node.right);
                }
                res.add(max);
            }
            return res;
        }

        private void offer(Deque<TreeNode> deque, TreeNode node) {
            if (node != null) {
                deque.offer(node);
            }
        }
    }
}
