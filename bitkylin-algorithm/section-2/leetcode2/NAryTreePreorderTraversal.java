//给定一个 N 叉树，返回其节点值的前序遍历。
//
// 例如，给定一个 3叉树 :
//
//
//
//
//
//
//
// 返回其前序遍历: [1,3,5,6,2,4]。
//
//
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树
// 👍 101 👎 0


package leetcode2;

import java.util.*;

public class NAryTreePreorderTraversal {

    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();
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
        public List<Integer> preorder(Node root) {
            LinkedList<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Deque<Node> deque = new LinkedList<>();
            deque.push(root);

            while (!deque.isEmpty()) {
                Node node = deque.removeFirst();
                res.add(node.val);
                if (node.children != null) {
                    Collections.reverse(node.children);
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
        public List<Integer> preorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<Object> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                resolve(stack, res);
            }
            return res;
        }

        private void resolve(Deque<Object> stack, List<Integer> list) {
            Object obj = stack.poll();
            if (obj instanceof Node) {
                push(stack, ((Node) obj).children);
                stack.push(((Node) obj).val);
            } else if (obj instanceof Integer) {
                list.add((Integer) obj);
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
        public List<Integer> preorder(Node root) {
            List<Integer> res = new ArrayList<>();
            resolve(root, res);
            return res;
        }

        private void resolve(Node node, List<Integer> list) {
            if (node == null) {
                return;
            }
            list.add(node.val);
            if (node.children == null) {
                return;
            }

            for (Node child : node.children) {
                resolve(child, list);
            }
        }
    }
}
