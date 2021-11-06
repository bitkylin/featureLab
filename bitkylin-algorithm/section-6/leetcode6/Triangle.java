//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
//
//
//
// 例如，给定三角形：
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
//
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//
//
//
// 说明：
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
// Related Topics 数组 动态规划
// 👍 611 👎 0


package leetcode6;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(2);
        l2.add(3);
        list.add(l1);
        list.add(l2);
        new Triangle().new Solution().minimumTotal(list);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 重点是求DP方程
     */
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size() - 1;
            int[] dp = new int[n + 2];
            for (int i = n; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，效率高
     */
    class Solution2 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int xMax = triangle.size() - 1;
            int yMax = triangle.get(xMax).size() - 1;
            int[][] memo = new int[xMax + 1][yMax + 1];
            return resolve(0, 0, xMax, yMax, triangle, memo);
        }

        private int resolve(int x, int y, int xMax, int yMax, List<List<Integer>> triangle, int[][] memo) {
            if (x > xMax) {
                return 0;
            }
            if (memo[x][y] != 0) {
                return memo[x][y];
            }
            int val = Math.min(resolve(x + 1, y, xMax, yMax, triangle, memo),
                    resolve(x + 1, y + 1, xMax, yMax, triangle, memo))
                    + triangle.get(x).get(y);
            memo[x][y] = val;
            return val;
        }
    }
}
