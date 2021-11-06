//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
//
// 例如，给定一个 3叉树 :
//
//
//
//
//
//
//
// 返回其层序遍历:
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
//
//
//
//
// 说明:
//
//
// 树的深度不会超过 1000。
// 树的节点总数不会超过 5000。
// Related Topics 树 广度优先搜索
// 👍 108 👎 0


package leetcode2;

import java.util.*;

public class NAryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> list = new ArrayList<>();
        list.add(node5);
        list.add(node6);
        Node node3 = new Node(3, list);

        List<Node> list2 = new ArrayList<>();
        Node node2 = new Node(2);
        Node node4 = new Node(4);

        list2.add(node3);
        list2.add(node2);
        list2.add(node4);
        Node node1 = new Node(1, list2);

        new NAryTreeLevelOrderTraversal().new Solution().levelOrder(node1);
    }

    static class Node {
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

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 队列法
     */
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    list.add(node.val);
                    if (node.children != null) {
                        queue.addAll(node.children);
                    }
                }
                res.add(list);
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 自实现的循环法，每次大循环需要创建新的Deque，性能较差
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
