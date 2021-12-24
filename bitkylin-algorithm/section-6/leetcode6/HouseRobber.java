/**
 * <p>你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>
 *
 * <p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong> 不触动警报装置的情况下 </strong>，一夜之内能够偷窃到的最高金额。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,2,3,1]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[2,7,9,3,1]
 * <strong>输出：</strong>12
 * <strong>解释：</strong>偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 100</code></li>
 * <li><code>0 <= nums[i] <= 400</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1804</li><li>👎 0</li></div>
 */

package leetcode6;

public class HouseRobber {

    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DP，完整版
     * DP(n) = Max( DP(n-1), DP(n-2) + arr(n) )
     */
    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[nums.length - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * DP，精简版「版本1」
     */
    class Solution1_1 {
        public int rob(int[] nums) {
            int[] dp = new int[nums.length];
            if (nums.length >= 1) dp[0] = nums[0];
            if (nums.length >= 2) dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[nums.length - 1];
        }
    }

    /**
     * DP，精简版「版本2」
     */
    class Solution1_2 {
        public int rob(int[] nums) {
            int[] dp = new int[nums.length + 2];
            for (int i = 0; i < nums.length; i++) {
                dp[i + 2] = Math.max(dp[i + 1], dp[i] + nums[i]);
            }
            return dp[nums.length + 1];
        }
    }

    /**
     * DP优化空间复杂度为O(1)
     */
    class Solution2 {
        public int rob(int[] nums) {
            int dp0 = 0;
            int dp1 = 0;
            for (int i = 0; i < nums.length; i++) {
                int dp2 = Math.max(dp1, dp0 + nums[i]);
                dp0 = dp1;
                dp1 = dp2;
            }
            return dp1;
        }
    }

    /**
     * DPS，用例超时，需要剪枝
     */
    class Solution3 {
        public int rob(int[] nums) {
            return solve(nums, 0);
        }

        private int solve(int[] nums, int i) {
            if (i >= nums.length) {
                return 0;
            }
            return Math.max(
                    solve(nums, i + 1),
                    solve(nums, i + 2) + nums[i]
            );
        }
    }
}
