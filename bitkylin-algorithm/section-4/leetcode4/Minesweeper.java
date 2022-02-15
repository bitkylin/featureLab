/**
 * <p>让我们一起来玩扫雷游戏！</p>
 *
 * <p>给你一个大小为 <code>m x n</code> 二维字符矩阵&nbsp;<code>board</code> ，表示扫雷游戏的盘面，其中：</p>
 *
 * <ul>
 * <li><code>'M'</code>&nbsp;代表一个 <strong>未挖出的</strong> 地雷，</li>
 * <li><code>'E'</code>&nbsp;代表一个<strong> 未挖出的 </strong>空方块，</li>
 * <li><code>'B'</code><strong>&nbsp;</strong>代表没有相邻（上，下，左，右，和所有4个对角线）地雷的<strong> 已挖出的 </strong>空白方块，</li>
 * <li><strong>数字</strong>（<code>'1'</code> 到 <code>'8'</code>）表示有多少地雷与这块<strong> 已挖出的</strong> 方块相邻，</li>
 * <li><code>'X'</code>&nbsp;则表示一个<strong> 已挖出的</strong> 地雷。</li>
 * </ul>
 *
 * <p>给你一个整数数组 <code>click</code> ，其中 <code>click = [click<sub>r</sub>, click<sub>c</sub>]</code> 表示在所有<strong> 未挖出的 </strong>方块（<code>'M'</code> 或者 <code>'E'</code>）中的下一个点击位置（<code>click<sub>r</sub></code> 是行下标，<code>click<sub>c</sub></code> 是列下标）。</p>
 *
 * <p>根据以下规则，返回相应位置被点击后对应的盘面：</p>
 *
 * <ol>
 * <li>如果一个地雷（<code>'M'</code>）被挖出，游戏就结束了- 把它改为&nbsp;<code>'X'</code> 。</li>
 * <li>如果一个<strong> 没有相邻地雷 </strong>的空方块（<code>'E'</code>）被挖出，修改它为（<code>'B'</code>），并且所有和其相邻的<strong> 未挖出 </strong>方块都应该被递归地揭露。</li>
 * <li>如果一个<strong> 至少与一个地雷相邻</strong> 的空方块（<code>'E'</code>）被挖出，修改它为数字（<code>'1'</code> 到 <code>'8'</code> ），表示相邻地雷的数量。</li>
 * <li>如果在此次点击中，若无更多方块可被揭露，则返回盘面。</li>
 * </ol>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img src="https://assets.leetcode.com/uploads/2018/10/12/minesweeper_example_1.png" style="width: 500px; max-width: 400px; height: 269px;" />
 * <pre>
 * <strong>输入：</strong>board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
 * <strong>输出：</strong>[["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img src="https://assets.leetcode.com/uploads/2018/10/12/minesweeper_example_2.png" style="width: 500px; max-width: 400px; height: 275px;" />
 * <pre>
 * <strong>输入：</strong>board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
 * <strong>输出：</strong>[["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n == board[i].length</code></li>
 * <li><code>1 &lt;= m, n &lt;= 50</code></li>
 * <li><code>board[i][j]</code> 为 <code>'M'</code>、<code>'E'</code>、<code>'B'</code> 或数字 <code>'1'</code> 到 <code>'8'</code> 中的一个</li>
 * <li><code>click.length == 2</code></li>
 * <li><code>0 &lt;= click<sub>r</sub> &lt; m</code></li>
 * <li><code>0 &lt;= click<sub>c</sub> &lt; n</code></li>
 * <li><code>board[click<sub>r</sub>][click<sub>c</sub>]</code> 为 <code>'M'</code> 或 <code>'E'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 257</li><li>👎 0</li></div>
 */

package leetcode4;

public class Minesweeper {

    public static void main(String[] args) {
        Solution solution = new Minesweeper().new Solution();
    }

    /**
     * DFS
     */
    class Solution {
        public char[][] updateBoard(char[][] board, int[] click) {
            int row = click[0];
            int column = click[1];
            int rowMax = board.length - 1;
            int columnMax = board[0].length - 1;
            if (row < 0 || row > rowMax || column < 0 || column > columnMax) {
                return board;
            }
            if (board[row][column] == 'M') {
                board[row][column] = 'X';
                return board;
            }
            solve(board, rowMax, columnMax, row, column);
            return board;
        }

        private void solve(char[][] board, int rowMax, int columnMax, int row, int column) {
            if (row < 0 || row > rowMax || column < 0 || column > columnMax || board[row][column] != 'E') {
                return;
            }
            int count = calc(board, rowMax, columnMax, row - 1, column - 1)
                    + calc(board, rowMax, columnMax, row, column - 1)
                    + calc(board, rowMax, columnMax, row + 1, column - 1)
                    + calc(board, rowMax, columnMax, row - 1, column)
                    + calc(board, rowMax, columnMax, row + 1, column)
                    + calc(board, rowMax, columnMax, row - 1, column + 1)
                    + calc(board, rowMax, columnMax, row, column + 1)
                    + calc(board, rowMax, columnMax, row + 1, column + 1);

            if (count != 0) {
                board[row][column] = (char) (0x30 + count);
                return;
            }
            board[row][column] = 'B';

            solve(board, rowMax, columnMax, row - 1, column - 1);
            solve(board, rowMax, columnMax, row, column - 1);
            solve(board, rowMax, columnMax, row + 1, column - 1);
            solve(board, rowMax, columnMax, row - 1, column);
            solve(board, rowMax, columnMax, row + 1, column);
            solve(board, rowMax, columnMax, row - 1, column + 1);
            solve(board, rowMax, columnMax, row, column + 1);
            solve(board, rowMax, columnMax, row + 1, column + 1);
        }

        private int calc(char[][] board, int rowMax, int columnMax, int row, int column) {
            if (row < 0 || row > rowMax || column < 0 || column > columnMax || board[row][column] != 'M') {
                return 0;
            }
            return 1;
        }
    }

    /**
     * 另一种写法
     */

    class Solution2 {
        private int[][] pointList = new int[][]{
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1},
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
        };

        public char[][] updateBoard(char[][] board, int[] click) {
            int xMax = board.length - 1;
            int yMax = board[0].length - 1;
            int x = click[0];
            int y = click[1];
            if (x < 0 || x > xMax || y < 0 || y > yMax) {
                return board;
            }
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return board;
            }
            solve(board, x, y, xMax, yMax);
            return board;
        }

        private void solve(char[][] board, int x, int y, int xMax, int yMax) {
            if (x < 0 || x > xMax || y < 0 || y > yMax || board[x][y] != 'E') {
                return;
            }
            int val = 0;
            for (int[] point : pointList) {
                val += calc(board, x + point[0], y + point[1], xMax, yMax);
            }
            if (val > 0) {
                board[x][y] = (char) (val + 0x30);
                return;
            }
            board[x][y] = 'B';
            for (int[] point : pointList) {
                solve(board, x + point[0], y + point[1], xMax, yMax);
            }
        }

        private int calc(char[][] board, int x, int y, int xMax, int yMax) {
            if (x < 0 || x > xMax || y < 0 || y > yMax) {
                return 0;
            }
            return board[x][y] == 'M' ? 1 : 0;
        }
    }
}
