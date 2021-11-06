//编写一个程序，通过填充空格来解决数独问题。
//
// 一个数独的解法需遵循如下规则：
//
//
// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
//
//
// 空白格用 '.' 表示。
//
//
//
// 一个数独。
//
//
//
// 答案被标成红色。
//
// 提示：
//
//
// 给定的数独序列只包含数字 1-9 和字符 '.' 。
// 你可以假设给定的数独只有唯一解。
// 给定数独永远是 9x9 形式的。
//
// Related Topics 哈希表 回溯算法
// 👍 674 👎 0


package leetcode7;

import java.util.ArrayDeque;
import java.util.Deque;

public class SudokuSolver {

    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solveSudoku(char[][] board) {
            solve(board);
            return;
        }

        private boolean solve(char[][] board) {
            int[] dir = select(board);
            if (dir == null) {
                return true;
            }
            for (int i = 0; i < 9; i++) {
                char val = (char) (i + '1');
                board[dir[0]][dir[1]] = val;
                if (valid(board)) {
                    if (solve(board)) {
                        return true;
                    }
                }
                board[dir[0]][dir[1]] = '.';
            }
            return false;
        }

        private int[] select(char[][] board) {
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    if (board[x][y] == '.') {
                        return new int[]{x, y};
                    }
                }
            }
            return null;
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
    }
//leetcode submit region end(Prohibit modification and deletion)

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
