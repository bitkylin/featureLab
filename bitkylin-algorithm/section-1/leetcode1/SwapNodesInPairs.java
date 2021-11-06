//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例:
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
//
// Related Topics 链表
// 👍 620 👎 0


package leetcode1;

public class SwapNodesInPairs {

    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 非递归
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode prev = new ListNode(0);
            prev.next = head;
            ListNode tail = prev;
            while (tail.next != null && tail.next.next != null) {
                ListNode a = tail.next;
                ListNode b = tail.next.next;
                a.next = b.next;
                b.next = a;
                tail.next = b;
                tail = a;
            }
            return prev.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

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
