//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
//
//
// 示例 1:
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
//
//
// 示例 2:
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
// Related Topics 数组 动态规划
// 👍 787 👎 0


package leetcode6;

public class MaximumProductSubarray {

    public static void main(String[] args) {
        new MaximumProductSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 关注这篇题解: https://leetcode-cn.com/problems/maximum-product-subarray/solution/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw/
     * 重点：
     * 1. 由于状态的设计 nums[i] 必须被选取
     * 2. 关注「第 2 步：推导状态转移方程」，写的很好
     * 3. 状态转移方程，理解起来比较难，需要加强理解
     */
    class Solution {
        public int maxProduct(int[] nums) {
            int[][] dp = new int[2][nums.length];
            int max = nums[0];
            dp[0][0] = max;
            dp[1][0] = max;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] >= 0) {
                    dp[0][i] = Math.min(dp[0][i - 1] * nums[i], nums[i]);
                    dp[1][i] = Math.max(dp[1][i - 1] * nums[i], nums[i]);
                } else {
                    dp[0][i] = Math.min(dp[1][i - 1] * nums[i], nums[i]);
                    dp[1][i] = Math.max(dp[0][i - 1] * nums[i], nums[i]);
                }
                max = Math.max(max, dp[1][i]);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP数组压缩，空间复杂度压缩为O(1)
     */
    class Solution2 {
        public int maxProduct(int[] nums) {
            int max = nums[0];
            int min = nums[0];
            int res = max;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] >= 0) {
                    max = Math.max(max * nums[i], nums[i]);
                    min = Math.min(min * nums[i], nums[i]);
                } else {
                    int subMin = Math.min(max * nums[i], nums[i]);
                    int subMax = Math.max(min * nums[i], nums[i]);
                    max = subMax;
                    min = subMin;
                }
                max = Math.max(max, res);
            }
            return max;
        }
    }
}
