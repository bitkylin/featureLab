/**
 * <p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 </p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
 * <strong>输出：</strong>[1,1,2,3,4,4]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [], l2 = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [], l2 = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>两个链表的节点数目范围是 <code>[0, 50]</code></li>
 * <li><code>-100 <= Node.val <= 100</code></li>
 * <li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2025</li><li>👎 0</li></div>
 */

package leetcode1;

public class MergeTwoSortedLists {

    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
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
     * 循环
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode node = new ListNode(0);
            ListNode prev = node;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                node = node.next;
            }
            node.next = l1 == null ? l2 : l1;
            return prev.next;
        }
    }

    /**
     * 递归
     */
    class Solution2 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l2.next, l1);
                return l2;
            }
        }
    }

    /**
     * 循环第二种写法
     */
    class Solution3 {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

            ListNode node = new ListNode(0);
            ListNode curr = node;
            while (true) {
                if (l1 == null) {
                    curr.next = l2;
                    return node.next;
                }
                if (l2 == null) {
                    curr.next = l1;
                    return node.next;
                }
                if (l1.val < l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }
                curr = curr.next;
            }
        }
    }
}
