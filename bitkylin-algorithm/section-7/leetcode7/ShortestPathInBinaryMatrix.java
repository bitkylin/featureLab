/**
 * <p>给你一个 <code>n x n</code> 的二进制矩阵 <code>grid</code> 中，返回矩阵中最短 <strong>畅通路径</strong> 的长度。如果不存在这样的路径，返回 <code>-1</code> 。</p>
 *
 * <p>二进制矩阵中的 畅通路径 是一条从 <strong>左上角</strong> 单元格（即，<code>(0, 0)</code>）到 右下角 单元格（即，<code>(n - 1, n - 1)</code>）的路径，该路径同时满足下述要求：</p>
 *
 * <ul>
 * <li>路径途经的所有单元格都的值都是 <code>0</code> 。</li>
 * <li>路径中所有相邻的单元格应当在 <strong>8 个方向之一</strong> 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。</li>
 * </ul>
 *
 * <p><strong>畅通路径的长度</strong> 是该路径途经的单元格总数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/example1_1.png" style="width: 500px; height: 234px;" />
 * <pre>
 * <strong>输入：</strong>grid = [[0,1],[1,0]]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/example2_1.png" style="height: 216px; width: 500px;" />
 * <pre>
 * <strong>输入：</strong>grid = [[0,0,0],[1,1,0],[1,1,0]]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>grid = [[1,0,0],[1,1,0],[1,1,0]]
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == grid.length</code></li>
 * <li><code>n == grid[i].length</code></li>
 * <li><code>1 <= n <= 100</code></li>
 * <li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 160</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.ArrayList;
import java.util.List;

public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        new ShortestPathInBinaryMatrix().new Solution().shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}});
    }

    /**
     * BFS
     */
    class Solution {

        int[][] pointList = new int[][]{
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1},
        };

        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid[0][0] == 1) {
                return -1;
            }
            int n = grid.length - 1;
            int res = 1;
            List<int[]> startList = new ArrayList<>();
            startList.add(new int[]{0, 0});
            grid[0][0] = 1;
            while (!startList.isEmpty()) {
                if (contains(startList, n, n)) {
                    return res;
                }
                startList = solve(startList, grid);
                res++;
            }
            return -1;
        }

        private boolean contains(List<int[]> startList, int x, int y) {
            for (int[] start : startList) {
                if (start[0] == x && start[1] == y) {
                    return true;
                }
            }
            return false;
        }

        private List<int[]> solve(List<int[]> startList, int[][] grid) {
            List<int[]> newList = new ArrayList<>();
            for (int[] start : startList) {
                for (int[] point : pointList) {
                    calc(newList, grid, start[0] + point[0], start[1] + point[1]);
                }
            }
            return newList;
        }

        private void calc(List<int[]> newList, int[][] grid, int x, int y) {
            if (x < 0 || y < 0 || x >= grid.length || y >= grid.length || grid[x][y] == 1) {
                return;
            }
            newList.add(new int[]{x, y});
            grid[x][y] = 1;
        }
    }

    /**
     * DFS，执行超时，不过看起来没问题，正解是上面的 BFS 解法
     */
    class Solution2 {

        private int[][] pointList = new int[][]{
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1},
        };

        public int shortestPathBinaryMatrix(int[][] grid) {
            return solve(grid, 0, 0, 1);
        }

        private int solve(int[][] grid, int x, int y, int level) {
            int xMax = grid.length - 1;
            int yMax = grid[0].length - 1;
            if (x < 0 || y < 0 || x > xMax || y > yMax || grid[x][y] == 1) {
                return -1;
            }
            if (x == xMax && y == yMax) {
                return level;
            }
            int min = Integer.MAX_VALUE;
            grid[x][y] = 1;
            for (int[] point : pointList) {
                int val = solve(grid, x + point[0], y + point[1], level + 1);
                if (val > 0) {
                    min = Math.min(val, min);
                }
            }
            grid[x][y] = 0;
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }
}
