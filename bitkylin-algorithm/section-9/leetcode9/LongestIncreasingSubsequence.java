/**
 * <p>给你一个整数数组 <code>nums</code> ，找到其中最长严格递增子序列的长度。</p>
 *
 * <p>子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，<code>[3,6,2,7]</code> 是数组 <code>[0,3,1,6,2,2,7]</code> 的子序列。</p>
 *
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [10,9,2,5,3,7,101,18]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1,0,3,2,3]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [7,7,7,7,7,7,7]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 2500</code></li>
 * <li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><b>进阶：</b></p>
 *
 * <ul>
 * <li>你可以设计时间复杂度为 <code>O(n<sup>2</sup>)</code> 的解决方案吗？</li>
 * <li>你能将算法的时间复杂度降低到 <code>O(n log(n))</code> 吗?</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li></div></div><br><div><li>👍 2174</li><li>👎 0</li></div>
 */

package leetcode9;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence().new Solution();
    }

    /**
     * 时间复杂度：O(n^2)
     * for i in (1 ~ n):
     * if (dp(i) > dp(j)) : dp(i) = Max( dp(i), dp(j)+1 )
     * <p>
     * 注：dp(i) 是以 i 为结尾时的最优值，不是全局最优值
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);
            int res = 1;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }
}
