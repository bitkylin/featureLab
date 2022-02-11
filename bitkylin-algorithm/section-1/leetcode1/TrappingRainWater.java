/**
 * <p>给定&nbsp;<code>n</code> 个非负整数表示每个宽度为 <code>1</code> 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png" style="height: 161px; width: 412px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * <strong>输出：</strong>6
 * <strong>解释：</strong>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [4,2,0,3,2,5]
 * <strong>输出：</strong>9
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == height.length</code></li>
 * <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>双指针</li><li>动态规划</li><li>单调栈</li></div></div><br><div><li>👍 2857</li><li>👎 0</li></div>
 */

package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {

    public static void main(String[] args) {
        new TrappingRainWater().new Solution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }

    /**
     * 单调栈「递减」，两题解法一致
     * 单调栈「递增」可参考这个题 {@link LargestRectangleInHistogram}
     */
    class Solution {
        public int trap(int[] height) {
            int res = 0;
            Deque<Integer> stack = new ArrayDeque<>(height.length);
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    int prev = stack.pop();
                    if (stack.isEmpty()) {
                        continue;
                    }
                    int h = Math.min(height[stack.peek()], height[i]) - height[prev];
                    res += h * (i - stack.peek() - 1);
                }
                stack.push(i);
            }
            return res;
        }
    }

    /**
     * 方法二：
     * 按列求 + 动态规划，O(n)，空间复杂度：O(n)
     * 左侧最大值方程：dp[n] = max(dp[n - 1], height[n - 1])
     * 右侧最大值方程：dp[n] = max(dp[n + 1], height[n + 1])
     * 注：注意循环的边界条件
     */
    class Solution2 {
        public int trap(int[] height) {
            int res = 0;
            int n = height.length;
            int[] left = new int[n];
            int[] right = new int[n];
            for (int i = 1; i < n - 1; i++) {
                left[i] = Math.max(left[i - 1], height[i - 1]);
            }
            for (int i = n - 2; i > 0; i--) {
                right[i] = Math.max(right[i + 1], height[i + 1]);
            }
            for (int i = 1; i < n - 1; i++) {
                res += Math.max(Math.min(left[i], right[i]) - height[i], 0);
            }
            return res;
        }
    }

    // 只关注前两个方法

    /**
     * 方法三：
     * 按行求，几乎通过所有用例，最后的用例超时，O(n * maxHeight)
     * 1. 算出最大高度
     * 2. 从下到上遍历
     * 3. 累计各层的值
     */
    class Solution3 {
        /**
         * 选出左右边界，然后统计其中的空格
         */
        public int trap(int[] height) {
            int max = calcMax(height);
            int res = 0;
            for (int h = 1; h <= max; h++) {
                int left = 0;
                int right = height.length - 1;
                while (height[left++] < h) ;
                while (height[right--] < h) ;
                while (left <= right) res += height[left++] < h ? 1 : 0;
            }
            return res;
        }

        /**
         * 「不推荐」
         * 整体从下到上，局部从左到右遍历，有一定技巧性
         * 注：注意 {if (height[i] >= h)} 的条件判断，不要漏了条件
         */
        public int trap2(int[] height) {
            int max = calcMax(height);
            int res = 0;
            for (int h = 1; h <= max; h++) {
                int temp = 0;
                boolean open = false;
                for (int i = 0; i < height.length; i++) {
                    if (open && height[i] < h) {
                        temp++;
                    }
                    if (height[i] >= h) {
                        res += temp;
                        open = true;
                        temp = 0;
                    }
                }
            }
            return res;
        }

        private int calcMax(int[] height) {
            int max = 0;
            for (int i : height) {
                max = Math.max(max, i);
            }
            return max;
        }
    }

    /**
     * 方法四：「不推荐」
     * 双指针法，O(n)，空间复杂度：O(1)
     * 可看作动态规划版本的优化，不必记录中间结果
     * 两侧各一个高度，高度最低值可累计到res中
     */
    class Solution4 {
        public int trap(int[] height) {
            int left = height[0];
            int right = height[height.length - 1];
            int res = 0;
            int i = 1;
            int j = height.length - 2;
            while (i <= j) {
                if (left < right) {
                    res += Math.max(0, left - height[i]);
                    left = Math.max(left, height[i++]);
                } else {
                    res += Math.max(0, right - height[j]);
                    right = Math.max(right, height[j--]);
                }
            }
            return res;
        }
    }
}
