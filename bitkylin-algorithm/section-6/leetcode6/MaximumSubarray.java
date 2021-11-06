//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例:
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
// 进阶:
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
// Related Topics 数组 分治算法 动态规划
// 👍 2505 👎 0


package leetcode6;

public class MaximumSubarray {

    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    class Solution {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = dp[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    class Solution2 {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int max = nums[0];
            int dp = max;
            for (int i = 1; i < nums.length; i++) {
                dp = Math.max(dp + nums[i], nums[i]);
                max = Math.max(max, dp);
            }
            return max;
        }
    }
}
