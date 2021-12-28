/**
 * <p>请你判断一个&nbsp;<code>9 x 9</code> 的数独是否有效。只需要<strong> 根据以下规则</strong> ，验证已经填入的数字是否有效即可。</p>
 *
 * <ol>
 * <li>数字&nbsp;<code>1-9</code>&nbsp;在每一行只能出现一次。</li>
 * <li>数字&nbsp;<code>1-9</code>&nbsp;在每一列只能出现一次。</li>
 * <li>数字&nbsp;<code>1-9</code>&nbsp;在每一个以粗实线分隔的&nbsp;<code>3x3</code>&nbsp;宫内只能出现一次。（请参考示例图）</li>
 * </ol>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>注意：</strong></p>
 *
 * <ul>
 * <li>一个有效的数独（部分已被填充）不一定是可解的。</li>
 * <li>只需要根据以上规则，验证已经填入的数字是否有效即可。</li>
 * <li>空白格用&nbsp;<code>'.'</code>&nbsp;表示。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png" style="height:250px; width:250px" />
 * <pre>
 * <strong>输入：</strong>board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>除了第一行的第一个数字从<strong> 5</strong> 改为 <strong>8 </strong>以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board.length == 9</code></li>
 * <li><code>board[i].length == 9</code></li>
 * <li><code>board[i][j]</code> 是一位数字（<code>1-9</code>）或者 <code>'.'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>矩阵</li></div></div><br><div><li>👍 728</li><li>👎 0</li></div>
 */

package leetcode7;

public class ValidSudoku {

    public static void main(String[] args) {
        new ValidSudoku().new Solution().isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        });
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidSudoku(char[][] board) {
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
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 冗长的解法,空间复杂度 O(n)
     * 暂不研究
     */
    class Solution2 {
        public boolean isValidSudoku(char[][] board) {
            int length = 9;
            for (int i = 0; i < length; i++) {
                boolean[] memo = new boolean[length];
                for (int j = 0; j < length; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    if (memo[board[i][j] - '1']) {
                        return false;
                    }
                    memo[board[i][j] - '1'] = true;
                }
            }
            for (int j = 0; j < length; j++) {
                boolean[] memo = new boolean[length];
                for (int i = 0; i < length; i++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    if (memo[board[i][j] - '1']) {
                        return false;
                    }
                    memo[board[i][j] - '1'] = true;
                }
            }
            for (int i = 0; i < length; i += 3) {
                for (int j = 0; j < length; j += 3) {
                    boolean[] memo = new boolean[length];
                    for (int x = i; x < i + 3; x++) {
                        for (int y = j; y < j + 3; y++) {
                            if (board[x][y] == '.') {
                                continue;
                            }
                            if (memo[board[x][y] - '1']) {
                                return false;
                            }
                            memo[board[x][y] - '1'] = true;
                        }
                    }
                }

            }
            return true;
        }
    }
}
