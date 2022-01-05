/**
 * <p>给你 <code>n</code> 个非负整数 <code>a<sub>1</sub>，a<sub>2，</sub>...，a</code><sub><code>n</code>，</sub>每个数代表坐标中的一个点&nbsp;<code>(i,&nbsp;a<sub>i</sub>)</code> 。在坐标内画 <code>n</code> 条垂直线，垂直线 <code>i</code>&nbsp;的两个端点分别为&nbsp;<code>(i,&nbsp;a<sub>i</sub>)</code> 和 <code>(i, 0)</code> 。找出其中的两条线，使得它们与&nbsp;<code>x</code>&nbsp;轴共同构成的容器可以容纳最多的水。</p>
 *
 * <p><strong>说明：</strong>你不能倾斜容器。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" style="height: 287px; width: 600px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>[1,8,6,2,5,4,8,3,7]
 * <strong>输出：</strong>49
 * <strong>解释：</strong>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为&nbsp;49。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [1,1]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [4,3,2,1,4]
 * <strong>输出：</strong>16
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>height = [1,2,1]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == height.length</code></li>
 * <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li></div></div><br><div><li>👍 3076</li><li>👎 0</li></div>
 */

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
