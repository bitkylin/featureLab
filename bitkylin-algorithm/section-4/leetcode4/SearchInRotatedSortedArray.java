/**
 * <p>整数数组 <code>nums</code> 按升序排列，数组中的值 <strong>互不相同</strong> 。</p>
 *
 * <p>在传递给函数之前，<code>nums</code> 在预先未知的某个下标 <code>k</code>（<code>0 <= k < nums.length</code>）上进行了 <strong>旋转</strong>，使数组变为 <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code>（下标 <strong>从 0 开始</strong> 计数）。例如， <code>[0,1,2,4,5,6,7]</code> 在下标 <code>3</code> 处经旋转后可能变为 <code>[4,5,6,7,0,1,2]</code> 。</p>
 *
 * <p>给你 <strong>旋转后</strong> 的数组 <code>nums</code> 和一个整数 <code>target</code> ，如果 <code>nums</code> 中存在这个目标值 <code>target</code> ，则返回它的下标，否则返回 <code>-1</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>4,5,6,7,0,1,2]</code>, target = 0
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [<code>4,5,6,7,0,1,2]</code>, target = 3
 * <strong>输出：</strong>-1</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1], target = 0
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 5000</code></li>
 * <li><code>-10^4 <= nums[i] <= 10^4</code></li>
 * <li><code>nums</code> 中的每个值都 <strong>独一无二</strong></li>
 * <li>题目数据保证 <code>nums</code> 在预先未知的某个下标上进行了旋转</li>
 * <li><code>-10^4 <= target <= 10^4</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(log n)</code> 的解决方案吗？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 1725</li><li>👎 0</li></div>
 */

package leetcode4;

/**
 * 还有这种解法，看起来是这道题专供的解法，不管它：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/jian-ji-rong-yi-li-jie-java-er-fen-fa-by-breezean/
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        new SearchInRotatedSortedArray().new Solution()
                .search(new int[]{1}, 1);
    }

    /**
     * 直接二分查找，mid拆分的数组有序时查找目标值，有序数组查找失败则继续收缩左右边界
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[left] <= target && target <= nums[mid]) {
                    return solve(nums, left, mid, target);
                }
                if (nums[mid] < target && target <= nums[right]) {
                    return solve(nums, mid, right, target);
                }
                if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return nums[left] == target ? left : -1;
        }

        private int solve(int[] nums, int left, int right, int target) {
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return nums[left] == target ? left : -1;
        }

        private int solve2(int[] nums, int left, int right, int target) {
            while (left < right) {
                int mid = (right - left + 1) / 2 + left;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return nums[left] == target ? left : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 先求最小值，拆为两个有序数组再求值
     * 参考 153. 寻找旋转排序数组中的最小值: {@link FindMinimumInRotatedSortedArray}
     */
    class Solution2 {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (nums[left] <= target && target <= nums[nums.length - 1]) {
                return solve(nums, left, nums.length - 1, target);
            } else if (left > 0 && nums[0] <= target && target <= nums[left - 1]) {
                return solve(nums, 0, left - 1, target);
            } else {
                return -1;
            }
        }

        /**
         * mid靠右，right需额外左移
         */
        private int solve(int[] nums, int left, int right, int target) {
            while (left < right) {
                int mid = (right - left + 1) / 2 + left;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return nums[left] == target ? left : -1;
        }

        /**
         * mid靠左，left需额外右移
         */
        private int solve2(int[] nums, int left, int right, int target) {
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return nums[left] == target ? left : -1;
        }

    }
}
