/**
 * <p>给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg" style="width: 422px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4]
 * <strong>输出：</strong>[2,1,4,3]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>0 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 1194</li><li>👎 0</li></div>
 */

package leetcode1;

public class SwapNodesInPairs {

    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 四指针法，非递归
     * 注：node节点交换后，再使用节点时注意使用已交换的节点，此处不要被坑了
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode prev = new ListNode();
            prev.next = head;
            head = prev;
            while (prev.next != null && prev.next.next != null) {
                ListNode a = prev.next;
                ListNode b = a.next;
                ListNode next = b.next;
                prev.next = b;
                b.next = a;
                a.next = next;
                prev = a;
            }
            return head.next;
        }
    }

    /**
     * 递归
     */
    class Solution2 {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
    }
}
