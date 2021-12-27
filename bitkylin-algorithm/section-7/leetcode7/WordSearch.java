/**
 * <p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code> 和一个字符串单词 <code>word</code> 。如果 <code>word</code> 存在于网格中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n = board[i].length</code></li>
 * <li><code>1 <= m, n <= 6</code></li>
 * <li><code>1 <= word.length <= 15</code></li>
 * <li><code>board</code> 和 <code>word</code> 仅由大小写英文字母组成</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你可以使用搜索剪枝的技术来优化解决方案，使其在 <code>board</code> 更大的情况下可以更快解决问题？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 1132</li><li>👎 0</li></div>
 */

package leetcode7;

public class WordSearch {

    public static void main(String[] args) {
        Solution solution = new WordSearch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int[][] pointList = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (solve(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean solve(char[][] board, String word, int i, int j, int level) {
            if (level >= word.length()) {
                return true;
            }
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                    || board[i][j] != word.charAt(level)) {
                return false;
            }
            board[i][j] = 0;
            for (int[] point : pointList) {
                if (solve(board, word, i + point[0], j + point[1], level + 1)) {
                    return true;
                }
            }
            board[i][j] = word.charAt(level);
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
