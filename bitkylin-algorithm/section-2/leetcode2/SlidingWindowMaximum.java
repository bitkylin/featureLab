/**
 * <p>给你一个整数数组 <code>nums</code>，有一个大小为 <code>k</code><em> </em>的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 <code>k</code> 个数字。滑动窗口每次只向右移动一位。</p>
 *
 * <p>返回滑动窗口中的最大值。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [1,3,-1,-3,5,3,6,7], k = 3
 * <b>输出：</b>[3,3,5,5,6,7]
 * <b>解释：</b>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       <strong>3</strong>
 * 1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
 * 1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
 * 1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
 * 1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
 * 1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [1], k = 1
 * <b>输出：</b>[1]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [1,-1], k = 1
 * <b>输出：</b>[1,-1]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [9,11], k = 2
 * <b>输出：</b>[11]
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <b>输入：</b>nums = [4,-2], k = 2
 * <b>输出：</b>[4]</pre>
 *
 * <p> </p>
 *
 * <p><b>提示：</b></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
 * <li><code>1 <= k <= nums.length</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>队列</li><li>数组</li><li>滑动窗口</li><li>单调队列</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1345</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        new SlidingWindowMaximum().new Solution().maxSlidingWindow(new int[]{1, -1}, 1);
    }

    /**
     * 最优解法，解法不难
     * 双端队列, O(n)
     * 1. 队首始终保持最大
     * 2. 遍历到第 k 个元素时，往结果数组中设值
     * 3. 移除队首溢出的元素
     */
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            Deque<Integer> deque = new ArrayDeque<>(k);
            int[] res = new int[nums.length - k + 1];
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.removeLast();
                }
                deque.addLast(i);
                if (i + 1 < k) {
                    continue;
                }
                res[j++] = nums[deque.peekFirst()];
                if (i - deque.peekFirst() + 1 >= k) {
                    deque.removeFirst();
                }
            }
            return res;
        }
    }

    /**
     * 大顶堆，O(NlogK),数量庞大时执行超时
     * 不过看起来能通过简单的用例
     * 注：暂时放弃
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
     * 注：暂时放弃
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
