//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
//个问题。 
//
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。 
// Related Topics 树 设计 
// 👍 358 👎 0


package leetcode3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        String str1 = "[1,2,3,null,null,4]";
        TreeNode root = new SerializeAndDeserializeBinaryTree().new Codec().deserialize(str1);
        String str2 = new SerializeAndDeserializeBinaryTree().new Codec().serialize(root);
        System.out.println(str1 + ":" + str2);
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
     * 序列化结果与示例一致
     */
    public class Codec {

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