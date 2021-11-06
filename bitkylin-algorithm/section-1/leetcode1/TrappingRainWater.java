//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。
//
// 示例:
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6
// Related Topics 栈 数组 双指针
// 👍 1747 👎 0


package leetcode1;

public class TrappingRainWater {

    public static void main(String[] args) {
        new TrappingRainWater().new Solution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 按列求 + 动态规划，O(n)
     */
    class Solution {
        public int trap(int[] height) {
            if (height == null || height.length <= 2) {
                return 0;
            }
            int res = 0;
            int end = height.length - 1;
            int[][] dp = new int[2][end + 1];
            dp[0][0] = height[0];
            dp[1][end] = height[end];
            for (int i = 1; i <= end; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], height[i]);
            }
            for (int i = end - 1; i > 0; i--) {
                dp[1][i] = Math.max(dp[1][i + 1], height[i]);
            }
            for (int i = 1; i < end; i++) {
                int min = Math.min(dp[0][i - 1], dp[1][i + 1]);
                if (height[i] < min) {
                    res += min - height[i];
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 按行求，几乎通过所有用例，最后的用例超时，O(n * maxHeight)
     */
    class Solution2 {
        public int trap(int[] height) {
            if (height == null || height.length <= 2) {
                return 0;
            }
            int max = 0;
            int res = 0;
            for (int i : height) {
                max = Math.max(max, i);
            }
            for (int i = 1; i <= max; i++) {
                int left = 0;
                int right = height.length - 1;
                while (height[left++] < i) ;
                while (height[right--] < i) ;
                while (left <= right) {
                    if (height[left++] < i) {
                        res++;
                    }
                }
            }
            return res;
        }
    }
}
