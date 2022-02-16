/**
 * <p>给你链表的头结点&nbsp;<code>head</code>&nbsp;，请将其按 <strong>升序</strong> 排列并返回 <strong>排序后的链表</strong> 。</p>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg" style="width: 450px;" />
 * <pre>
 * <b>输入：</b>head = [4,2,1,3]
 * <b>输出：</b>[1,2,3,4]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg" style="width: 550px;" />
 * <pre>
 * <b>输入：</b>head = [-1,5,3,4,0]
 * <b>输出：</b>[-1,0,3,4,5]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <b>输入：</b>head = []
 * <b>输出：</b>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>提示：</b></p>
 *
 * <ul>
 * <li>链表中节点的数目在范围&nbsp;<code>[0, 5 * 10<sup>4</sup>]</code>&nbsp;内</li>
 * <li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>进阶：</b>你可以在&nbsp;<code>O(n&nbsp;log&nbsp;n)</code> 时间复杂度和常数级空间复杂度下，对链表进行排序吗？</p>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li><li>分治</li><li>排序</li><li>归并排序</li></div></div><br><div><li>👍 1464</li><li>👎 0</li></div>
 */

package top1;

public class SortList {

    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
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

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode mid = slow.next;
            slow.next = null;
            head = sortList(head);
            mid = sortList(mid);
            return merge(head, mid);
        }

        private ListNode merge(ListNode node1, ListNode node2) {
            if (node1 != null && node2 != null) {
                if (node1.val <= node2.val) {
                    node1.next = merge(node1.next, node2);
                    return node1;
                } else {
                    node2.next = merge(node1, node2.next);
                    return node2;
                }
            }
            return node1 == null ? node2 : node1;
        }

        private ListNode merge2(ListNode node1, ListNode node2) {
            ListNode prev = new ListNode();
            ListNode head = prev;
            while (node1 != null && node2 != null) {
                if (node1.val <= node2.val) {
                    prev.next = node1;
                    node1 = node1.next;
                } else {
                    prev.next = node2;
                    node2 = node2.next;
                }
                prev = prev.next;
            }
            prev.next = node1 == null ? node2 : node1;
            return head.next;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
