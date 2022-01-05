/**
 * <p>在二维网格 <code>grid</code> 上，有 4 种类型的方格：</p>
 *
 * <ul>
 * <li><code>1</code> 表示起始方格。且只有一个起始方格。</li>
 * <li><code>2</code> 表示结束方格，且只有一个结束方格。</li>
 * <li><code>0</code> 表示我们可以走过的空方格。</li>
 * <li><code>-1</code> 表示我们无法跨越的障碍。</li>
 * </ul>
 *
 * <p>返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目<strong>。</strong></p>
 *
 * <p><strong>每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格</strong>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * <strong>输出：</strong>2
 * <strong>解释：</strong>我们有以下两条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>我们有以下四条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>[[0,1],[2,0]]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>
 * 没有一条路能完全穿过每一个空的方格一次。
 * 请注意，起始和结束方格可以位于网格中的任意位置。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= grid.length * grid[0].length &lt;= 20</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 176</li><li>👎 0</li></div>
 */

package leetcode9;

public class UniquePathsIii {

    public static void main(String[] args) {
        new UniquePathsIii().new Solution().uniquePathsIII(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        });
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS + 回溯
     */
    class Solution {

        private final int[][] addr = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0},};

        public int uniquePathsIII(int[][] grid) {
            int count = 1;
            int[] start = null;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        start = new int[]{i, j};
                    }
                    if (grid[i][j] == 0) {
                        count++;
                    }
                }
            }
            grid[start[0]][start[1]] = 0;
            return solve(grid, start[0], start[1], count);
        }

        private int solve(int[][] grid, int x, int y, int count) {
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == -1) {
                return 0;
            }
            if (grid[x][y] == 2) {
                return count == 0 ? 1 : 0;
            }
            int res = 0;
            grid[x][y] = -1;
            for (int[] point : addr) {
                res += solve(grid, x + point[0], y + point[1], count - 1);
            }
            grid[x][y] = 0;
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS + 回溯，换一种写法
     */
    class Solution2 {

        public int uniquePathsIII(int[][] grid) {
            // 当grid[i][j] == 2, stepNum++, 这里直接初始化为1
            int startX = 0, startY = 0, stepNum = 1;
            // 遍历获取起始位置和统计总步数
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        startY = i;
                        startX = j;
                        continue;
                    }
                    if (grid[i][j] == 0) stepNum++;
                }
            }

            return dfs(startX, startY, stepNum, grid);
        }


        public int dfs(int x, int y, int stepSur, int[][] grid) {
            //排除越界的情况和遇到障碍的情况
            if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length || grid[y][x] == -1) return 0;
            if (grid[y][x] == 2) return stepSur == 0 ? 1 : 0;
            grid[y][x] = -1;  //已走过的标记为障碍
            int res = 0;
            res += dfs(x - 1, y, stepSur - 1, grid);
            res += dfs(x + 1, y, stepSur - 1, grid);
            res += dfs(x, y - 1, stepSur - 1, grid);
            res += dfs(x, y + 1, stepSur - 1, grid);
            grid[y][x] = 0;  //dfs遍历完该位置为起始位置的情况后，置零，以不影响后面的dfs
            return res;
        }
    }

}
