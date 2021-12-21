/**
 * <p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> [2,3,-2,4]
 * <strong>输出:</strong> <code>6</code>
 * <strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre><strong>输入:</strong> [-2,0,-1]
 * <strong>输出:</strong> 0
 * <strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1410</li><li>👎 0</li></div>
 */

package leetcode6;

public class MaximumProductSubarray {

    public static void main(String[] args) {
        Solution solution = new MaximumProductSubarray().new Solution();
    }

    /**
     * DP
     * <p>
     * if arr(n) >= 0
     * DPMax(n) = Max( DPMax(n-1) * arr(n), arr(n) )
     * DPMin(n) = Max( DPMin(n-1) * arr(n), arr(n) )
     * <p>
     * if arr(n) < 0
     * DPMax(n) = Max( DPMin(n-1) * arr(n), arr(n) )
     * DPMin(n) = Max( DPMax(n-1) * arr(n), arr(n) )
     * <p>
     * DP(i) 为包含第 i 个数时的最大值 / 最小值
     * DPMin[i]始终维护最小值
     * DPMax[i]始终维护最大值
     * 注1：乘以负数时，最大/最小值必然翻转
     * 注2：变量max始终跟踪最大值
     * 注3：DP[i]始终包含nums[i]的信息，必须为包含nums[i]时的最大值/最小值
     * <p>
     * 精简版，压缩空间复杂度
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProduct(int[] nums) {
            int dpMin = nums[0];
            int dpMax = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int curMax;
                int curMin;
                if (nums[i] > 0) {
                    curMax = Math.max(dpMax * nums[i], nums[i]);
                    curMin = Math.min(dpMin * nums[i], nums[i]);
                } else {
                    curMax = Math.max(dpMin * nums[i], nums[i]);
                    curMin = Math.min(dpMax * nums[i], nums[i]);
                }
                dpMax = curMax;
                dpMin = curMin;
                max = Math.max(max, dpMax);
            }
            return max;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 完整版
     */
    class Solution2 {
        public int maxProduct(int[] nums) {
            int[] dpMin = new int[nums.length];
            int[] dpMax = new int[nums.length];
            dpMin[0] = nums[0];
            dpMax[0] = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > 0) {
                    dpMax[i] = Math.max(dpMax[i - 1] * nums[i], nums[i]);
                    dpMin[i] = Math.min(dpMin[i - 1] * nums[i], nums[i]);
                } else {
                    dpMax[i] = Math.max(dpMin[i - 1] * nums[i], nums[i]);
                    dpMin[i] = Math.min(dpMax[i - 1] * nums[i], nums[i]);
                }
                max = Math.max(max, dpMax[i]);
            }
            return max;
        }
    }
}
