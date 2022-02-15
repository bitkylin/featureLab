/**
 * <p>给定一个非负整数数组 <code>nums</code> ，你最初位于数组的 <strong>第一个下标</strong> 。</p>
 *
 * <p>数组中的每个元素代表你在该位置可以跳跃的最大长度。</p>
 *
 * <p>判断你是否能够到达最后一个下标。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,3,1,1,4]
 * <strong>输出：</strong>true
 * <strong>解释：</strong>可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,1,0,4]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>0 <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1536</li><li>👎 0</li></div>
 */

package leetcode4;

public class JumpGame {

    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
    }

    /**
     * 双指针，挨个跳
     */
    class Solution0 {
        public boolean canJump(int[] nums) {
            int right = 0;
            for (int left = 0; left <= right && left < nums.length; left++) {
                right = Math.max(right, nums[left] + left);
            }
            return right >= nums.length - 1;
        }
    }

    /**
     * DP完整版：DP[n] = Max( DP[n - 1], arr[n] + n )
     */
    class Solution {
        public boolean canJump(int[] nums) {
            if (nums.length < 0) {
                return true;
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (dp[i - 1] < i) {
                    return false;
                }
                dp[i] = Math.max(dp[i - 1], i + nums[i]);
            }
            return true;
        }
    }

    /**
     * DP优化空间复杂度
     */
    class Solution1 {
        public boolean canJump(int[] nums) {
            int prev = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (prev < i) {
                    return false;
                }
                prev = Math.max(prev, i + nums[i]);
            }
            return true;
        }
    }

    // ------ 后面的解法都不研究了 ------

    /**
     * 倒序
     */
    class Solution2 {
        public boolean canJump(int[] nums) {
            if (nums.length <= 1) {
                return true;
            }
            int min = nums.length - 1;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (i + nums[i] >= min) {
                    min = i;
                }
            }
            return min == 0;
        }
    }

    /**
     * DFS，耗时较高
     */
    class Solutio3 {
        public boolean canJump(int[] nums) {
            boolean[] memo = new boolean[nums.length];
            return resolve(0, nums, memo);
        }

        private boolean resolve(int i, int[] nums, boolean[] memo) {
            if (i >= nums.length - 1) {
                return true;
            }
            if (memo[i]) {
                return false;
            }
            for (int num = nums[i]; num > 0; num--) {
                int j = i + num;
                if (resolve(j, nums, memo)) {
                    return true;
                } else {
                    memo[j] = true;
                }
            }
            return false;
        }
    }

    /**
     * 遍历，耗时较高
     */
    class Solution4 {
        public boolean canJump(int[] nums) {
            if (nums.length <= 1) {
                return true;
            }
            boolean[] memo = new boolean[nums.length];
            memo[0] = true;
            for (int i = 0; i < nums.length - 1; i++) {
                if (!memo[i]) {
                    continue;
                }
                for (int j = 1; j <= nums[i]; j++) {
                    memo[Math.min(nums.length - 1, i + j)] = true;
                }
            }
            return memo[nums.length - 1];
        }
    }
}
