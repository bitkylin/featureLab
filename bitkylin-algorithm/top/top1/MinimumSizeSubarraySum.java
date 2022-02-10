/**
 * <p>给定一个含有 <code>n</code><strong> </strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>
 *
 * <p>找出该数组中满足其和<strong> </strong><code>≥ target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong> <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>子数组 <code>[4,3]</code> 是该条件下的长度最小的子数组。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>target = 4, nums = [1,4,4]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= target <= 10<sup>9</sup></code></li>
 * <li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 930</li><li>👎 0</li></div>
 */

package top1;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        Solution solution = new MinimumSizeSubarraySum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int val = 0;
                for (int j = i; j < nums.length; j++) {
                    val += nums[j];
                    if (val >= target) {
                        min = Math.min(j - i + 1, min);
                        break;
                    }
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 滑动窗口模型
     */
    class Solution2 {
        public int minSubArrayLen(int target, int[] nums) {
            Deque<Integer> deque = new ArrayDeque<>();
            int sum = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                deque.offer(nums[i]);
                sum += nums[i];
                while (sum >= target) {
                    min = Math.min(min, deque.size());
                    if (deque.isEmpty()) break;
                    int val = deque.poll();
                    sum -= val;
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }

}
