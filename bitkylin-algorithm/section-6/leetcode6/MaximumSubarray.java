/**
 * <p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>
 *
 * <p><strong>子数组 </strong>是数组中的一个连续部分。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [5,4,-1,7,8]
 * <strong>输出：</strong>23
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>动态规划</li></div></div><br><div><li>👍 4120</li><li>👎 0</li></div>
 */

package leetcode6;

public class MaximumSubarray {

    public static void main(String[] args) {
        new MaximumSubarray().new Solution().maxSubArray(new int[]{5, 4, -1, 7, 8});
    }

    /**
     * DP
     * if DP(n-1) > 0
     * <br>DP(n) = DP(DP(n-1) + arr(n) )
     * else
     * <br>DP(n) = arr(n) )
     * <p>
     * 遍历数组，主线是遍历+SUM，当SUM<=0时重置，重新开始SUM。在遍历期间，始终追踪SUM的最大值。
     * 场景1：开头为0或负数，之后有正数 - 开头SUM始终为0，之后从正值开始
     * 场景2：SUM为正值，后面需要累计负值，之后再累计正值 - SUM直到不大于0时，累计就无意义了；如果SUM一直大于0，累计就一直有意义。如：5，-3，4
     * 注：DP(i)始终追踪 **包含** nums[i]时的最大值
     * <p>
     * 精简版，压缩空间复杂度
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int dp0 = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp0 = dp0 > 0 ? dp0 + nums[i] : nums[i];
                max = Math.max(max, dp0);
            }
            return max;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 完整版
     */
    class Solution2 {
        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
}
