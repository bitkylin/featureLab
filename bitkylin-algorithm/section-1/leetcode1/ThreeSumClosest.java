//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。
//
//
//
// 示例：
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4
//
// Related Topics 数组 双指针
// 👍 569 👎 0

package leetcode1;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        new ThreeSumClosest().new Solution().threeSumClosest(new int[]{0, 0, 0}, 0);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int rtnSum = Integer.MAX_VALUE;
            int rtnRange = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int currRange = Math.abs(sum - target);
                    if (currRange < rtnRange) {
                        rtnRange = currRange;
                        rtnSum = sum;
                    }
                    if (sum < target) {
                        j++;
                    } else if (sum > target) {
                        k--;
                    } else {
                        return rtnSum;
                    }
                }
            }
            return rtnSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
