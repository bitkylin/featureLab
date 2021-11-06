//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。
//
//
//
//
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
//
//
// 示例：
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49
// Related Topics 数组 双指针
// 👍 1824 👎 0

package leetcode1;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 左右夹逼 O(n)，易理解的解法
     * 双指针法，易理解的证明：
     * https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
     */
    class Solution {
        public int maxArea(int[] height) {
            int i = 0;
            int j = height.length - 1;
            int maxArea = 0;

            while (i < j) {
                if (height[i] < height[j]) {
                    maxArea = Math.max(maxArea, height[i] * (j - i));
                    i++;
                } else {
                    maxArea = Math.max(maxArea, height[j] * (j - i));
                    j--;
                }
            }
            return maxArea;

        }
    }

    /**
     * 左右夹逼 O(n)，极致优化写法
     */
    class Solution2 {
        public int maxArea(int[] height) {
            int area = 0;
            for (int i = 0, j = height.length - 1; i < j; ) {
                int h = (height[i] < height[j]) ? height[i++] : height[j--];
                int newArea = h * (j - i + 1);
                area = Math.max(newArea, area);
            }
            return area;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 暴力遍历 O(n2)
     */
    class Solution3 {
        public int maxArea(int[] height) {
            int maxArea = 0;
            for (int i = 0; i < height.length; i++) {
                for (int j = i + 1; j < height.length; j++) {
                    int area = Math.min(height[j], height[i]) * (j - i);
                    maxArea = Math.max(maxArea, area);
                }
            }
            return maxArea;
        }
    }

}
