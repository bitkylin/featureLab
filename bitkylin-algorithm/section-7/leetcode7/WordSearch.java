//给定一个二维网格和一个单词，找出该单词是否存在于网格中。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 示例:
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false
//
//
//
// 提示：
//
//
// board 和 word 中只包含大写和小写英文字母。
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// 1 <= word.length <= 10^3
//
// Related Topics 数组 回溯算法
// 👍 660 👎 0


package leetcode7;

public class WordSearch {

    public static void main(String[] args) {
        new WordSearch().new Solution()
                .exist(new char[][]{
                                {'A', 'B', 'C', 'E'},
                                {'S', 'F', 'C', 'S'},
                                {'A', 'D', 'E', 'E'}
                        }
                        , "ABCCED");
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS + 回溯
     * 注意方向数组不要写错
     */
    class Solution {
        int[][] addr = new int[][]{
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        public boolean exist(char[][] board, String word) {
            int xMax = board.length - 1;
            int yMax = board[0].length - 1;

            for (int x = 0; x <= xMax; x++) {
                for (int y = 0; y <= yMax; y++) {
                    if (dfs(x, y, xMax, yMax, 0, word, board)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int x, int y, int xMax, int yMax, int i, String word, char[][] board) {
            if (i == word.length()) {
                return true;
            }
            if (x < 0 || y < 0 || x > xMax || y > yMax || board[x][y] == 0) {
                return false;
            }
            if (board[x][y] != word.charAt(i)) {
                return false;
            }
            char backup = board[x][y];
            board[x][y] = 0;

            for (int[] arr : addr) {
                if (dfs(x + arr[0], y + arr[1], xMax, yMax, i + 1, word, board)) {
                    board[x][y] = backup;
                    return true;
                }
            }
            board[x][y] = backup;
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
