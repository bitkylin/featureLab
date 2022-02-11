/**
 * <p>给定一个 N 叉树，返回其节点值的<strong> 后序遍历</strong> 。</p>
 *
 * <p>N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 <code>null</code> 分隔（请参见示例）。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <p>递归法很简单，你可以使用迭代法完成此题吗?</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
 * <strong>输出：</strong>[5,6,3,2,4,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png" style="width: 296px; height: 241px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * <strong>输出：</strong>[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>N 叉树的高度小于或等于 <code>1000</code></li>
 * <li>节点总数在范围 <code>[0, 10^4]</code> 内</li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li></div></div><br><div><li>👍 169</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.*;

public class NAryTreePostorderTraversal {

    public static void main(String[] args) {
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

    /**
     * 栈 - 存放 node + value
     */
    class Solution1_1 {
        public List<Integer> postorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            ArrayDeque<Object> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Object obj = stack.pop();
                if (obj instanceof Integer) {
                    res.add((Integer) obj);
                } else {
                    Node node = (Node) obj;
                    stack.push(node.val);
                    if (node.children != null) {
                        Collections.reverse(node.children);
                        for (Node child : node.children) {
                            stack.push(child);
                        }
                    }
                }
            }
            return res;
        }
    }

    class Solution1_2 {
        public List<Integer> postorder(Node root) {
            List<Integer> res = new ArrayList<>();
            ArrayDeque<Object> stack = new ArrayDeque<>();
            if (root != null) {
                stack.push(root);
            }
            while (!stack.isEmpty()) {
                Object obj = stack.pop();
                if (obj instanceof Node) {
                    stack.push(((Node) obj).val);
                    if (((Node) obj).children != null) {
                        Collections.reverse(((Node) obj).children);
                        for (Node child : ((Node) obj).children) {
                            stack.push(child);
                        }
                    }
                } else if (obj instanceof Integer) {
                    res.add((Integer) obj);
                }
            }
            return res;
        }
    }

    /**
     * 递归
     */
    class Solution2 {
        public List<Integer> postorder(Node root) {
            List<Integer> res = new ArrayList<>();
            solve(root, res);
            return res;
        }

        private void solve(Node root, List<Integer> res) {
            if (root == null) {
                return;
            }
            if (root.children == null) {
                return;
            }
            for (Node child : root.children) {
                solve(child, res);
            }
            res.add(root.val);
        }
    }

    /**
     * 栈 - 仅存放 node
     * 对于每一个Node：res开头添加val，子节点列表正序入栈
     */
    class Solution3 {
        public List<Integer> postorder(Node root) {
            LinkedList<Integer> res = new LinkedList<>();
            ArrayDeque<Node> stack = new ArrayDeque<>();
            if (root != null) {
                stack.push(root);
            }
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                res.addFirst(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        stack.push(child);
                    }
                }
            }
            return res;
        }
    }
}
