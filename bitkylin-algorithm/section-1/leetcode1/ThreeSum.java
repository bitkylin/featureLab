//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例：
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
// Related Topics 数组 双指针
// 👍 2571 👎 0

package leetcode1;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        new ThreeSum().new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4});

    }

    /**
     * 简单解法「hash法」，内含很多冗余结果，故用 Set 去重
     * 双指针法，外层循环 + 内层夹逼：O(n^2)
     */
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            Set<List<Integer>> res = new HashSet<>();
            for (int k = 0; k < nums.length - 2; k++) {
                for (int i = k + 1, j = nums.length - 1, sum = -nums[k]; i < j; ) {
                    if (nums[i] + nums[j] == sum) {
                        res.add(Arrays.asList(nums[k], nums[i++], nums[j--]));
                    } else if (nums[i] + nums[j] > sum) j--;
                    else i++;
                }
            }
            return new ArrayList<>(res);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 此解法是三数之和的基础
     */
    class TwoSum {
        public int[] twoSum(int[] nums, int target) {
            Arrays.sort(nums);
            for (int i = 0, j = nums.length - 1; i < j; ) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                } else {
                    if (nums[i] + nums[j] < target) i++;
                    else j--;
                }
            }
            return null;
        }
    }

    /**
     * 根据上面的简单解法改进，去除所有重复结果，从而去除 Set
     * 双指针法，外层循环 + 内层夹逼：O(n^2)
     */
    class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int k = 0; k < nums.length - 2; k++) {
                if (k == 0 || nums[k] != nums[k - 1]) {
                    if (nums[k] > 0) {
                        continue;
                    }
                    for (int i = k + 1, j = nums.length - 1, sum = -nums[k]; i < j; ) {
                        if (nums[i] + nums[j] == sum) {
                            res.add(Arrays.asList(nums[k], nums[i], nums[j]));
                            while (i < j && nums[i] == nums[++i]) ;
                            while (i < j && nums[j] == nums[--j]) ;
                        } else if (nums[i] + nums[j] > sum) j--;
                        else i++;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
