//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//
// 返回滑动窗口中的最大值。
//
//
//
// 进阶：
//
// 你能在线性时间复杂度内解决此题吗？
//
//
//
// 示例:
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7]
//解释:
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// 1 <= k <= nums.length
//
// Related Topics 堆 Sliding Window
// 👍 553 👎 0


package leetcode1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        new SlidingWindowMaximum().new Solution().maxSlidingWindow(new int[]{1, -1}, 1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 双端队列,O(n)
     */
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            Deque<Integer> deque = new LinkedList<>();
            if (nums == null || nums.length < 2) {
                return nums;
            }
            int[] res = new int[nums.length - k + 1];
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
                if (i >= k - 1) {
                    res[j++] = nums[deque.peekFirst()];
                }
                if (i - deque.peekFirst() + 1 >= k) {
                    deque.removeFirst();
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 大顶堆，O(NlogK),数量庞大时执行超时
     * 不过看起来能通过简单的用例
     */
    class Solution3 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            if (nums == null || nums.length < 2) {
                return nums;
            }
            int[] res = new int[nums.length - k + 1];
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                queue.add(nums[i]);
                if (queue.size() >= k) {
                    res[j++] = queue.peek();
                    queue.remove(nums[i - k + 1]);
                }
            }
            return res;
        }
    }

    /**
     * 暴力遍历
     */
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            int[] res = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length - k + 1; i++) {
                int max = nums[i];
                for (int j = 1; j < k; j++) {
                    max = Math.max(max, nums[i + j]);
                }
                res[i] = max;
            }
            return res;
        }
    }
}
