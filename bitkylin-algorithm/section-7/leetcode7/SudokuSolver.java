/**
 * <p>编写一个程序，通过填充空格来解决数独问题。</p>
 *
 * <p>数独的解法需<strong> 遵循如下规则</strong>：</p>
 *
 * <ol>
 * <li>数字 <code>1-9</code> 在每一行只能出现一次。</li>
 * <li>数字 <code>1-9</code> 在每一列只能出现一次。</li>
 * <li>数字 <code>1-9</code> 在每一个以粗实线分隔的 <code>3x3</code> 宫内只能出现一次。（请参考示例图）</li>
 * </ol>
 *
 * <p>数独部分空格内已填入了数字，空白格用 <code>'.'</code> 表示。</p>
 *
 * <p> </p>
 *
 * <div class="top-view__1vxA">
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例：</strong></p>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png" style="height:250px; width:250px" />
 * <pre>
 * <strong>输入：</strong>board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * <strong>输出：</strong>[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * <strong>解释：</strong>输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 * <img src=" https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714_solutionsvg.png" style="height:250px; width:250px" />
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board.length == 9</code></li>
 * <li><code>board[i].length == 9</code></li>
 * <li><code>board[i][j]</code> 是一位数字或者 <code>'.'</code></li>
 * <li>题目数据 <strong>保证</strong> 输入数独仅有一个解</li>
 * </ul>
 * </div>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 1065</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.ArrayDeque;
import java.util.Deque;

public class SudokuSolver {

    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
    }

    /**
     * 数独数字范围是0-8或1-9，不要搞错了
     */
    class Solution {
        public void solveSudoku(char[][] board) {
            solve(board);
        }

        private boolean solve(char[][] board) {
            int[] point = select(board);
            if (point == null) {
                return true;
            }
            for (int i = 0; i < 9; i++) {
                board[point[0]][point[1]] = (char) (i + '1');
                if (isValid(board) && solve(board)) {
                    return true;
                }
            }
            board[point[0]][point[1]] = '.';
            return false;
        }

        private int[] select(char[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }

        public boolean isValid(char[][] board) {
            int len = 9;
            boolean[][] column = new boolean[len][len];
            boolean[][] row = new boolean[len][len];
            boolean[][] boxed = new boolean[len][len];

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    int val = board[i][j] - '1';
                    int boxIndex = i / 3 * 3 + j / 3;
                    if (column[i][val] || row[j][val] || boxed[boxIndex][val]) {
                        return false;
                    }
                    column[i][val] = true;
                    row[j][val] = true;
                    boxed[boxIndex][val] = true;
                }
            }
            return true;
        }
    }

    /**
     * 预先取出所有需填空的格子，然后直接遍历
     * 时间复杂度并没有明显提升
     */
    class Solution2 {
        public void solveSudoku(char[][] board) {
            Deque<Node> deque = select(board);
            solve(board, deque);
        }

        private boolean solve(char[][] board, Deque<Node> deque) {
            if (deque.isEmpty()) {
                return true;
            }
            Node node = deque.pop();
            for (int i = 0; i < 9; i++) {
                char val = (char) (i + '1');
                board[node.x][node.y] = val;
                if (valid(board)) {
                    if (solve(board, deque)) {
                        return true;
                    }
                }
            }
            board[node.x][node.y] = '.';
            deque.push(node);
            return false;
        }

        private Deque<Node> select(char[][] board) {
            Deque<Node> deque = new ArrayDeque<>();
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    if (board[x][y] == '.') {
                        deque.offer(new Node(x, y));
                    }
                }
            }
            return deque;
        }

        private boolean valid(char[][] board) {
            int size = board.length;
            boolean[][] row = new boolean[size][size];
            boolean[][] column = new boolean[size][size];
            boolean[][] box = new boolean[size][size];
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (board[x][y] == '.') {
                        continue;
                    }
                    int boxi = x / 3 * 3 + y / 3;
                    int val = board[x][y] - '1';
                    if (row[x][val] || column[y][val] || box[boxi][val]) {
                        return false;
                    }
                    row[x][val] = true;
                    column[y][val] = true;
                    box[boxi][val] = true;
                }
            }
            return true;
        }

        public class Node {
            int x;
            int y;
            int val;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
