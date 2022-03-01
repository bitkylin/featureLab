/**
 * <p>给定一个按照升序排列的整数数组 <code>nums</code>，和一个目标值 <code>target</code>。找出给定目标值在数组中的开始位置和结束位置。</p>
 *
 * <p>如果数组中不存在目标值 <code>target</code>，返回 <code>[-1, -1]</code>。</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你可以设计并实现时间复杂度为 <code>O(log n)</code> 的算法解决此问题吗？</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>5,7,7,8,8,10]</code>, target = 8
 * <strong>输出：</strong>[3,4]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>5,7,7,8,8,10]</code>, target = 6
 * <strong>输出：</strong>[-1,-1]</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [], target = 0
 * <strong>输出：</strong>[-1,-1]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
 * <li><code>nums</code> 是一个非递减数组</li>
 * <li><code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 1485</li><li>👎 0</li></div>
 */

package top1;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length <= 0) {
                return new int[]{-1, -1};
            }
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (right - left + 1) / 2 + left;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            right = left;
            while (left > 0 && nums[left - 1] == target) {
                left--;
            }
            while (right < nums.length - 1 && nums[right + 1] == target) {
                right++;
            }
            if (nums[left] == target) {
                return new int[]{left, right};
            } else {
                return new int[]{-1, -1};
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
