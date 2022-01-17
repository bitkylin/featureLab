/**
 * <p>给定一个 N 叉树，返回其节点值的<em>层序遍历</em>。（即从左到右，逐层遍历）。</p>
 *
 * <p>树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
 * <strong>输出：</strong>[[1],[3,2,4],[5,6]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png" style="width: 296px; height: 241px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * <strong>输出：</strong>[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树的高度不会超过 <code>1000</code></li>
 * <li>树的节点总数在 <code>[0, 10^4]</code> 之间</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li></div></div><br><div><li>👍 182</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.*;

public class NAryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new NAryTreeLevelOrderTraversal().new Solution();
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<>();
            ArrayDeque<Node> deque = new ArrayDeque<>();
            if (root != null) {
                deque.push(root);
            }
            while (!deque.isEmpty()) {
                int size = deque.size();
                List<Integer> list = new ArrayList<>();
                res.add(list);
                for (int i = 0; i < size; i++) {
                    Node node = deque.poll();
                    list.add(node.val);
                    if (node.children != null) {
                        for (Node child : node.children) {
                            deque.offer(child);
                        }
                    }
                }
            }
            return res;
        }
    }

    /**
     * 自实现的循环法，每次大循环需要创建新的Deque，性能较差
     * 注：该解法无需关注
     */
    class Solution2 {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<Node> deque = new LinkedList<>();
            deque.addFirst(root);
            while (!deque.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                Deque<Node> dequeInner = new LinkedList<>();
                while (!deque.isEmpty()) {
                    Node node = deque.removeFirst();
                    list.add(node.val);
                    if (node.children != null) {
                        for (Node child : node.children) {
                            dequeInner.addLast(child);
                        }
                    }
                }
                res.add(list);
                deque = dequeInner;
            }
            return res;
        }
    }
}
