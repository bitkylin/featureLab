/**
 * <p>给你一个包含 <code>n</code> 个整数的数组 <code>nums</code>，判断 <code>nums</code> 中是否存在三个元素 <em>a，b，c ，</em>使得 <em>a + b + c = </em>0 ？请你找出所有和为 <code>0</code> 且不重复的三元组。</p>
 *
 * <p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
 * <strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 3000</code></li>
 * <li><code>-10<sup>5</sup> <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 4175</li><li>👎 0</li></div>
 */

package leetcode1;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
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
}
