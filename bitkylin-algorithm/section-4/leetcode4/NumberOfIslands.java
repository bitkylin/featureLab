//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1:
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
//
//
// 示例 2:
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
//
// Related Topics 深度优先搜索 广度优先搜索 并查集
// 👍 790 👎 0


package leetcode4;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};

        int count = new NumberOfIslands().new Solution().numIslands(grid1);
        System.out.println(count);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * BFS，速度平庸
     */
    class Solution {

        private final int[][] addrs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int count = 0;
            int xMax = grid.length - 1;
            int yMax = grid[0].length - 1;
            Deque<Entry> deque = new ArrayDeque<>();
            for (int x = 0; x <= xMax; x++) {
                for (int y = 0; y <= yMax; y++) {
                    if (grid[x][y] == '1') {
                        grid[x][y] = 0;
                        deque.offer(new Entry(x, y));
                        while (!deque.isEmpty()) {
                            resolve(grid, xMax, yMax, deque);
                        }
                        count++;
                    }
                }
            }
            return count;
        }

        private void resolve(char[][] grid, int xMax, int yMax, Deque<Entry> deque) {
            Entry entry = deque.poll();
            int x = entry.x;
            int y = entry.y;
            for (int[] addr : addrs) {
                int newX = addr[0] + x;
                int newY = addr[1] + y;
                if (valid(newX, newY, xMax, yMax) && grid[newX][newY] == '1') {
                    grid[newX][newY] = 0;
                    deque.offer(new Entry(newX, newY));
                }
            }
        }

        private boolean valid(int x, int y, int xMax, int yMax) {
            return x >= 0 && x <= xMax && y >= 0 && y <= yMax;
        }

        class Entry {
            int x;
            int y;

            Entry(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，直接修改入参数组模拟visited
     */
    class Solution3 {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int count = 0;
            int xMax = grid.length - 1;
            int yMax = grid[0].length - 1;

            for (int x = 0; x <= xMax; x++) {
                for (int y = 0; y <= yMax; y++) {
                    if (grid[x][y] == '1') {
                        resolve(grid, xMax, yMax, x, y);
                        count++;
                        grid[x][y] = 0;
                    }
                }
            }
            return count;
        }

        private void resolve(char[][] grid, int xMax, int yMax, int x, int y) {
            if (valid(x, y, xMax, yMax) && grid[x][y] == '1') {
                grid[x][y] = 0;
                resolve(grid, xMax, yMax, x + 1, y);
                resolve(grid, xMax, yMax, x - 1, y);
                resolve(grid, xMax, yMax, x, y + 1);
                resolve(grid, xMax, yMax, x, y - 1);
            }
        }

        private boolean valid(int x, int y, int xMax, int yMax) {
            return x >= 0 && x <= xMax && y >= 0 && y <= yMax;
        }
    }

    /**
     * DFS，使用visited
     */
    class Solution2 {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int count = 0;
            int xMax = grid.length - 1;
            int yMax = grid[0].length - 1;
            boolean[][] visited = new boolean[xMax + 1][yMax + 1];

            for (int x = 0; x <= xMax; x++) {
                for (int y = 0; y <= yMax; y++) {
                    if (!visited[x][y] && grid[x][y] == '1') {
                        resolve(grid, visited, xMax, yMax, x, y);
                        count++;
                        visited[x][y] = true;
                    }
                }
            }
            return count;
        }

        private void resolve(char[][] grid, boolean[][] visited, int xMax, int yMax, int x, int y) {
            if (valid(x, y, xMax, yMax) && !visited[x][y] && grid[x][y] == '1') {
                visited[x][y] = true;
                resolve(grid, visited, xMax, yMax, x + 1, y);
                resolve(grid, visited, xMax, yMax, x - 1, y);
                resolve(grid, visited, xMax, yMax, x, y + 1);
                resolve(grid, visited, xMax, yMax, x, y - 1);
            }
        }

        private boolean valid(int x, int y, int xMax, int yMax) {
            return x >= 0 && x <= xMax && y >= 0 && y <= yMax;
        }
    }
}
