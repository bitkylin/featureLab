/**
 * 给你一个 <code>m x n</code> 的矩阵 <code>board</code> ，由若干字符 <code>'X'</code> 和 <code>'O'</code> ，找到所有被 <code>'X'</code> 围绕的区域，并将这些区域里所有的 <code>'O'</code> 用 <code>'X'</code> 填充。
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg" style="width: 550px; height: 237px;" />
 * <pre>
 * <strong>输入：</strong>board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * <strong>输出：</strong>[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * <strong>解释：</strong>被围绕的区间不会存在于边界上，换句话说，任何边界上的 <code>'O'</code> 都不会被填充为 <code>'X'</code>。 任何不在边界上，或不与边界上的 <code>'O'</code> 相连的 <code>'O'</code> 最终都会被填充为 <code>'X'</code>。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>board = [["X"]]
 * <strong>输出：</strong>[["X"]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n == board[i].length</code></li>
 * <li><code>1 <= m, n <= 200</code></li>
 * <li><code>board[i][j]</code> 为 <code>'X'</code> 或 <code>'O'</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 690</li><li>👎 0</li></div>
 */

package leetcode7;

public class SurroundedRegions {

    public static void main(String[] args) {
        Solution solution = new SurroundedRegions().new Solution();
    }

    class Solution {
        public void solve(char[][] board) {
            int yMax = board.length - 1;
            int xMax = board[0].length - 1;
            for (int i = 0; i <= yMax; i++) {
                solve(board, yMax, xMax, i, 0);
                solve(board, yMax, xMax, i, xMax);
            }
            for (int i = 0; i <= xMax; i++) {
                solve(board, yMax, xMax, 0, i);
                solve(board, yMax, xMax, yMax, i);
            }
            for (int i = 0; i <= yMax; i++) {
                for (int j = 0; j <= xMax; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    } else if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private void solve(char[][] board, int yMax, int xMax, int i, int j) {
            if (i >= 0 && i <= yMax && j >= 0 && j <= xMax && board[i][j] == 'O') {
                board[i][j] = '#';
                solve(board, yMax, xMax, i + 1, j);
                solve(board, yMax, xMax, i - 1, j);
                solve(board, yMax, xMax, i, j + 1);
                solve(board, yMax, xMax, i, j - 1);
            }
        }
    }

    /**
     * 换一种写法
     */
    class Solution2 {
        int[][] pointList = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public void solve(char[][] board) {
            int xMax = board.length - 1;
            int yMax = board[0].length - 1;
            for (int x = 0; x <= xMax; x++) {
                reset(board, xMax, yMax, x, 0);
                reset(board, xMax, yMax, x, yMax);
            }
            for (int y = 0; y <= yMax; y++) {
                reset(board, xMax, yMax, 0, y);
                reset(board, xMax, yMax, xMax, y);
            }
            for (int x = 0; x <= xMax; x++) {
                for (int y = 0; y <= yMax; y++) {
                    if (board[x][y] == 'O') {
                        board[x][y] = 'X';
                    } else if (board[x][y] == 0) {
                        board[x][y] = 'O';
                    }
                }
            }
        }

        private void reset(char[][] board, int xMax, int yMax, int x, int y) {
            if (x < 0 || y < 0 || x > xMax || y > yMax || board[x][y] != 'O') {
                return;
            }
            board[x][y] = 0;
            for (int[] point : pointList) {
                reset(board, xMax, yMax, x + point[0], y + point[1]);
            }
        }
    }
}
