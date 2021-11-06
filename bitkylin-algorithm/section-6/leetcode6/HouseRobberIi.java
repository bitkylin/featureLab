//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
//装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
//
// 示例 1:
//
// 输入: [2,3,2]
//输出: 3
//解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
//
// 示例 2:
//
// 输入: [1,2,3,1]
//输出: 4
//解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
// Related Topics 动态规划
// 👍 390 👎 0


package leetcode6;

public class HouseRobberIi {

    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 优化空间复杂度
     */
    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int max1 = resolve(0, nums.length - 2, nums);
            int max2 = resolve(1, nums.length - 1, nums);
            return Math.max(max1, max2);
        }

        private int resolve(int a, int b, int[] nums) {
            int dp1 = 0;
            int dp2 = 0;
            for (int i = a; i <= b; i++) {
                int val = Math.max(dp1 + nums[i], dp2);
                dp1 = dp2;
                dp2 = val;
            }
            return dp2;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP
     */
    class Solution2 {
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int[] dp = new int[nums.length + 1];
            for (int i = 0; i < nums.length - 1; i++) {
                dp[i + 2] = Math.max(dp[i] + nums[i], dp[i + 1]);
            }
            int max1 = dp[nums.length];

            dp = new int[nums.length + 1];
            for (int i = 1; i < nums.length; i++) {
                dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
            }
            return Math.max(max1, dp[nums.length]);
        }
    }
}
