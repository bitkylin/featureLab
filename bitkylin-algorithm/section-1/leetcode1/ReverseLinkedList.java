/**
 * 给你单链表的头节点 <code>head</code> ，请你反转链表，并返回反转后的链表。
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg" style="width: 542px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5]
 * <strong>输出：</strong>[5,4,3,2,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg" style="width: 182px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2]
 * <strong>输出：</strong>[2,1]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目范围是 <code>[0, 5000]</code></li>
 * <li><code>-5000 <= Node.val <= 5000</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？</p>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2191</li><li>👎 0</li></div>
 */

package leetcode1;

public class ReverseLinkedList {

    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
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
     * 懵逼的话，画个图，理解理解
     * 常规三指针法
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }
    }
}
