/**
 * <p>序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。</p>
 *
 * <p>请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。</p>
 *
 * <p><strong>提示: </strong>输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 <a href="/faq/#binary-tree">LeetCode 序列化二叉树的格式</a>。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" style="width: 442px; height: 324px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,3,null,null,4,5]
 * <strong>输出：</strong>[1,2,3,null,null,4,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中结点数在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>设计</li><li>字符串</li><li>二叉树</li></div></div><br><div><li>👍 691</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.*;

public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        new SerializeAndDeserializeBinaryTree();
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
     * 使用DFS「深度优先遍历『前序遍历』」
     * 注：序列化后字符串末尾会多一个","，不过不受影响
     * 注：与示例中序列化后字符串不同
     * 注：示例使用BFS，方法较难，暂不研究
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            solve(root, builder);
            return builder.substring(0, builder.length() - 1);
        }

        private void solve(TreeNode node, StringBuilder builder) {
            if (node == null) {
                builder.append("null,");
                return;
            }
            builder.append(node.val).append(",");
            solve(node.left, builder);
            solve(node.right, builder);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> deque = new ArrayDeque<>(Arrays.asList(data.split(",")));
            return parse(deque);
        }

        private TreeNode parse(Deque<String> deque) {
            if (deque.isEmpty()) {
                return null;
            }
            String val = deque.poll();
            if ("null".equals(val)) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = parse(deque);
            node.right = parse(deque);
            return node;
        }
    }

    /**
     * 序列化结果与示例一致
     * 注：方法较难，放弃
     */
    public class Codec2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "[]";
            }
            List<String> list = new ArrayList<>();
            Deque<Object> deque = new LinkedList<>();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                Object obj = deque.poll();
                if (obj instanceof TreeNode) {
                    putObj(deque, ((TreeNode) obj).left);
                    putObj(deque, ((TreeNode) obj).right);
                    list.add(((TreeNode) obj).val + "");
                } else {
                    list.add(obj.toString());
                }
            }
            while (!list.isEmpty()) {
                String v = list.remove(list.size() - 1);
                if (!"null".equals(v)) {
                    list.add(v);
                    break;
                }
            }
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (String s : list) {
                builder.append(s).append(",");
            }
            return builder.substring(0, builder.length() - 1) + "]";
        }

        private void putObj(Deque<Object> deque, TreeNode node) {
            if (node == null) {
                deque.addLast("null");
            } else {
                deque.addLast(node);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() <= 2) {
                return null;
            }
            String[] arr = data.substring(1, data.length() - 1).split(",");
            int i = 1;
            TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
            Deque<TreeNode> deque = new LinkedList<>();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                if (i > arr.length - 1) {
                    break;
                }
                TreeNode node = deque.removeFirst();
                String left = arr[i++];
                if (!"null".equals(left)) {
                    TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                    deque.addLast(leftNode);
                    node.left = leftNode;
                }
                if (i > arr.length - 1) {
                    break;
                }
                String right = arr[i++];
                if (!"null".equals(right)) {
                    TreeNode rightNode = new TreeNode(Integer.valueOf(right));
                    deque.addLast(rightNode);
                    node.right = rightNode;
                }
            }
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


}
