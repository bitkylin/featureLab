/**
 * <p>给你一个非负整数数组 <code>nums</code> ，你最初位于数组的第一个位置。</p>
 *
 * <p>数组中的每个元素代表你在该位置可以跳跃的最大长度。</p>
 *
 * <p>你的目标是使用最少的跳跃次数到达数组的最后一个位置。</p>
 *
 * <p>假设你总是可以到达数组的最后一个位置。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [2,3,1,1,4]
 * <strong>输出:</strong> 2
 * <strong>解释:</strong> 跳到最后一个位置的最小跳跃数是 <code>2</code>。
 * 从下标为 0 跳到下标为 1 的位置，跳 <code>1</code> 步，然后跳 <code>3</code> 步到达数组的最后一个位置。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = [2,3,0,1,4]
 * <strong>输出:</strong> 2
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
 * <li><code>0 <= nums[i] <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1320</li><li>👎 0</li></div>
 */

package leetcode4;

public class JumpGameIi {

    public static void main(String[] args) {
        new JumpGameIi().new Solution().jump(new int[]{2, 3, 1, 1, 4});
    }

    /**
     * 画个图更好理解
     */
    class Solution {
        public int jump(int[] nums) {
            int res = 0, min = 0, max = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > min) {
                    min = max;
                    res++;
                }
                max = Math.max(max, nums[i] + i);
            }
            return res;
        }
    }
}
