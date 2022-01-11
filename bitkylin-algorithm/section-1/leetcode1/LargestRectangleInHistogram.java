/**
 * <p>给定 <em>n</em> 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。</p>
 *
 * <p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong>heights = [2,1,5,6,2,3]
 * <strong>输出：</strong>10
 * <strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong> heights = [2,4]
 * <b>输出：</b> 4</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= heights.length <=10<sup>5</sup></code></li>
 * <li><code>0 <= heights[i] <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>单调栈</li></div></div><br><div><li>👍 1626</li><li>👎 0</li></div>
 */


package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {

        new LargestRectangleInHistogram().new Solution().largestRectangleArea(new int[]{2, 1, 2})
        ;
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 单调栈「递增」
     * 单调栈「递减」可参考这个题 {@link TrappingRainWater}
     * 时间复杂度O(n)
     * 注：确实稍有难度
     */
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int area = 0;
            Deque<Integer> deque = new ArrayDeque<>(heights.length);
            for (int i = 0; i <= heights.length; i++) {
                while (!deque.isEmpty() && heights[deque.peek()] > (i == heights.length ? 0 : heights[i])) {
                    int h = heights[deque.pop()];
                    area = Math.max(area, (deque.isEmpty() ? i : (i - deque.peek() - 1)) * h);
                }
                deque.push(i);
            }
            return area;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 暴力遍历，对高度进行遍历
     * 时间复杂度O(n2)
     * 注：暂时放弃
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
