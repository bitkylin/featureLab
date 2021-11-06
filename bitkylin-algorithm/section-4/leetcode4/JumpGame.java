//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 判断你是否能够到达最后一个位置。
//
// 示例 1:
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
//
//
// 示例 2:
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
//
// Related Topics 贪心算法 数组
// 👍 845 👎 0


package leetcode4;

public class JumpGame {

    public static void main(String[] args) {
        new JumpGame().new Solution().canJump(new int[]{3, 2, 1, 0, 4});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 正序
     */
    class Solution {
        public boolean canJump(int[] nums) {
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > max) {
                    return false;
                }
                max = Math.max(max, i + nums[i]);
                if (max >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

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
