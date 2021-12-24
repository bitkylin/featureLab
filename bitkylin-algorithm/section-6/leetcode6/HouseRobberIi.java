/**
 * <p>你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 <strong>围成一圈</strong> ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong> 。</p>
 *
 * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你 <strong>在不触动警报装置的情况下</strong> ，今晚能够偷窃到的最高金额。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,3,2]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3,1]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 100</code></li>
 * <li><code>0 <= nums[i] <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 865</li><li>👎 0</li></div>
 */

package leetcode6;

public class HouseRobberIi {

    public static void main(String[] args) {
        Solution solution = new HouseRobberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP，精简版
     * 执行两次 {@link HouseRobber} 即可
     */
    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            int val1 = solve(nums, 0, nums.length - 2);
            int val2 = solve(nums, 1, nums.length - 1);
            return Math.max(val1, val2);
        }

        private int solve(int[] nums, int i, int j) {
            int dp0 = 0;
            int dp1 = 0;
            for (int k = i; k <= j; k++) {
                int dp2 = Math.max(dp1, dp0 + nums[k]);
                dp0 = dp1;
                dp1 = dp2;
            }
            return dp1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DP，完整版
     * 执行两次 {@link HouseRobber} 即可
     */
    class Solution1 {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);
            int val1 = solve(nums, 0, nums.length - 2);
            int val2 = solve(nums, 1, nums.length - 1);
            return Math.max(val1, val2);
        }

        private int solve(int[] nums, int i, int j) {
            int[] dp = new int[nums.length];
            dp[i] = nums[i];
            dp[i + 1] = Math.max(nums[i], nums[i + 1]);
            for (int k = i + 2; k <= j; k++) {
                dp[k] = Math.max(dp[k - 1], dp[k - 2] + nums[k]);
            }
            return dp[j];
        }
    }

    /**
     * DP，另一种写法，暂不研究
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
