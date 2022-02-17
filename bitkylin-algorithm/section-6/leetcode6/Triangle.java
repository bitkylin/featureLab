/**
 * <p>给定一个三角形 <code>triangle</code> ，找出自顶向下的最小路径和。</p>
 *
 * <p>每一步只能移动到下一行中相邻的结点上。<strong>相邻的结点 </strong>在这里指的是 <strong>下标</strong> 与 <strong>上一层结点下标</strong> 相同或者等于 <strong>上一层结点下标 + 1</strong> 的两个结点。也就是说，如果正位于当前行的下标 <code>i</code> ，那么下一步可以移动到下一行的下标 <code>i</code> 或 <code>i + 1</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * <strong>输出：</strong>11
 * <strong>解释：</strong>如下面简图所示：
 * <strong>2</strong>
 * <strong>3</strong> 4
 * 6 <strong>5</strong> 7
 * 4 <strong>1</strong> 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>triangle = [[-10]]
 * <strong>输出：</strong>-10
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= triangle.length <= 200</code></li>
 * <li><code>triangle[0].length == 1</code></li>
 * <li><code>triangle[i].length == triangle[i - 1].length + 1</code></li>
 * <li><code>-10<sup>4</sup> <= triangle[i][j] <= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你可以只使用 <code>O(n)</code> 的额外空间（<code>n</code> 为三角形的总行数）来解决这个问题吗？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 919</li><li>👎 0</li></div>
 */

package leetcode6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(2)));
        input.add(new ArrayList<>(Arrays.asList(3, 4)));
        input.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        input.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
        new Triangle().new Solution().minimumTotal(input);
    }

    /**
     * DP:
     * dp(n) = arr(n) + Min( dp(n), dp(n+1) )
     */
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int[] dp = new int[triangle.get(triangle.size() - 1).size() + 1];
            for (int i = triangle.size() - 1; i >= 0; i--) {
                List<Integer> list = triangle.get(i);
                for (int j = 0; j < list.size(); j++) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + list.get(j);
                }
            }
            return dp[0];
        }
    }

    /**
     * 刁钻的用例执行超时了，优先用上面的方法吧
     * <p>
     * 树状结构DFS：
     * 不能将上层计算结果叠加后传入下层「缓存中间结果」，下层每个cell会因为上层路径不同有不同的计算结果，故不能缓存
     * 应该由下层计算结果叠加后进行缓存，并交由上层使用，下层计算结果是终态，不会变化
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        public int minimumTotal(List<List<Integer>> triangle) {
            Integer[][] memo = new Integer[triangle.size()][triangle.get(triangle.size() - 1).size()];
            return solve(triangle, memo, 0, 0);
        }

        private int solve(List<List<Integer>> triangle, Integer[][] memo, int row, int column) {
            if (row >= triangle.size()) {
                return 0;
            }
            if (memo[row][column] != null) {
                return memo[row][column];
            }
            memo[row][column] = triangle.get(row).get(column) + Math.min(
                    solve(triangle, memo, row + 1, column),
                    solve(triangle, memo, row + 1, column + 1)
            );
            return memo[row][column];
        }
    }
}
