//给定一个 N 叉树，返回其节点值的后序遍历。
//
// 例如，给定一个 3叉树 :
//
//
//
//
//
//
//
// 返回其后序遍历: [5,6,3,2,4,1].
//
//
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树
// 👍 100 👎 0


package leetcode2;

import java.util.*;

public class NAryTreePostorderTraversal {

    public static void main(String[] args) {
        Solution solution = new NAryTreePostorderTraversal().new Solution();
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

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 栈 - 仅存放 node
     */
    class Solution {
        public List<Integer> postorder(Node root) {
            LinkedList<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Deque<Node> deque = new LinkedList<>();
            deque.addFirst(root);
            while (!deque.isEmpty()) {
                Node node = deque.removeFirst();
                res.addFirst(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        deque.addFirst(child);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 栈 - 存放 node + value
     */
    class Solution3 {
        public List<Integer> postorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<Object> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                resolve(stack, res);
            }
            return res;
        }

        private void resolve(Deque<Object> stack, List<Integer> res) {
            Object obj = stack.poll();
            if (obj instanceof Node) {
                stack.push(((Node) obj).val);
                push(stack, ((Node) obj).children);
            } else if (obj instanceof Integer) {
                res.add((Integer) obj);
            }
        }

        private void push(Deque<Object> stack, List<Node> list) {
            if (list == null) {
                return;
            }
            Collections.reverse(list);
            for (Node node : list) {
                stack.push(node);
            }
        }
    }

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> postorder(Node root) {
            List<Integer> res = new ArrayList<>();
            resolve(root, res);
            return res;
        }

        private void resolve(Node root, List<Integer> res) {
            if (root == null) {
                return;
            }
            if (root.children != null) {
                for (Node node : root.children) {
                    resolve(node, res);
                }
            }
            res.add(root.val);
        }
    }
}
