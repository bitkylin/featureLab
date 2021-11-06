//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
//
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
//
//
//
//
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
//
//
//
// 示例:
//
// 输入: [2,1,5,6,2,3]
//输出: 10
// Related Topics 栈 数组
// 👍 901 👎 0


package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        new LargestRectangleInHistogram().new Solution()
                .largestRectangleArea(new int[]{2, 0, 2, 1, 2});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 使用单调栈
     * 时间复杂度O(n)
     */
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int area = 0;
            int[] arr = new int[heights.length + 1];
            System.arraycopy(heights, 0, arr, 0, heights.length);
            Deque<Integer> deque = new ArrayDeque<>(heights.length + 1);
            for (int i = 0; i < arr.length; ++i) {
                while (!deque.isEmpty() && arr[deque.peek()] > arr[i]) {
                    int h = arr[deque.pop()];
                    int len = deque.isEmpty() ? i : (i - deque.peek() - 1);
                    area = Math.max(area, h * len);
                }
                deque.push(i);
            }
            return area;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 未通过，待继续分析
     */
    class Solution3 {

        public int largestRectangleArea(int[] heights) {
            int area = 0;
            int[] arr = new int[heights.length + 1];
            System.arraycopy(heights, 0, arr, 0, heights.length);
            Deque<Integer> deque = new ArrayDeque<>(heights.length + 1);
            for (int i = 0; i < arr.length; i++) {
                while (!deque.isEmpty() && arr[deque.peek()] > arr[i]) {
                    int pop = deque.pop();
                    area = Math.max(area, (i - pop) * arr[pop]);
                }
                deque.push(i);
            }
            return area;
        }
    }

    /**
     * 暴力遍历，对高度进行遍历
     * 时间复杂度O(n2)
     */
    class Solution2 {

        public int largestRectangleArea(int[] heights) {
            int area = 0;
            for (int i = 0; i < heights.length; i++) {
                if (i > 0 && heights[i] == heights[i - 1]) {
                    continue;
                }
                area = Math.max(area, calcArea(heights, i));
            }
            return area;
        }

        private int calcArea(int[] heights, int i) {
            int a = i;
            int b = i;
            while (a >= 0 && heights[a] >= heights[i]) {
                a--;
            }
            while (b < heights.length && heights[b] >= heights[i]) {
                b++;
            }
            return (b - a - 1) * heights[i];
        }
    }
}
