/**
 * <p>在一个 2 x 3 的板上（<code>board</code>）有 5 块砖瓦，用数字 <code>1~5</code> 来表示, 以及一块空缺用&nbsp;<code>0</code>&nbsp;来表示.</p>
 *
 * <p>一次移动定义为选择&nbsp;<code>0</code>&nbsp;与一个相邻的数字（上下左右）进行交换.</p>
 *
 * <p>最终当板&nbsp;<code>board</code>&nbsp;的结果是&nbsp;<code>[[1,2,3],[4,5,0]]</code>&nbsp;谜板被解开。</p>
 *
 * <p>给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>board = [[1,2,3],[4,0,5]]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>交换 0 和 5 ，1 步完成
 * </pre>
 *
 * <pre>
 * <strong>输入：</strong>board = [[1,2,3],[5,4,0]]
 * <strong>输出：</strong>-1
 * <strong>解释：</strong>没有办法完成谜板
 * </pre>
 *
 * <pre>
 * <strong>输入：</strong>board = [[4,1,2],[5,0,3]]
 * <strong>输出：</strong>5
 * <strong>解释：</strong>
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * </pre>
 *
 * <pre>
 * <strong>输入：</strong>board = [[3,2,4],[1,5,0]]
 * <strong>输出：</strong>14
 * </pre>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board</code>&nbsp;是一个如上所述的 2 x 3 的数组.</li>
 * <li><code>board[i][j]</code>&nbsp;是一个&nbsp;<code>[0, 1, 2, 3, 4, 5]</code>&nbsp;的排列.</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 242</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.HashSet;
import java.util.Set;

public class SlidingPuzzle {

    public static void main(String[] args) {
        Solution solution = new SlidingPuzzle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * BFS常规解法，题目很简单，注：
     * 1. 只需要使 board 数组可哈希，转为字符串即可
     * 2. 记录每一次的中间结果，避免重复计算。不必记录中间结果的次数，记录的中间结果均为最小次数。
     */
    class Solution {

        private int[][] pointList = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0},};
        private String endStr = "123450";

        public int slidingPuzzle(int[][] board) {
            Set<String> memo = new HashSet<>();
            Set<String> startSet = new HashSet<>();
            startSet.add(convert(board));
            int res = 0;
            while (!startSet.isEmpty()) {
                if (startSet.contains(endStr)) {
                    return res;
                }
                startSet = solve(startSet, memo);
                res++;
            }
            return -1;
        }

        private Set<String> solve(Set<String> startSet, Set<String> memo) {
            Set<String> newStartSet = new HashSet<>();
            for (String startStr : startSet) {
                int[][] startBoard = convert(startStr);
                int[] zero = findZero(startBoard);
                for (int[] point : pointList) {
                    if (swap(startBoard, zero[0], zero[1], zero[0] + point[0], zero[1] + point[1])) {
                        calc(startBoard, newStartSet, memo);
                        swap(startBoard, zero[0], zero[1], zero[0] + point[0], zero[1] + point[1]);
                    }
                }
            }
            return newStartSet;
        }

        private int[] findZero(int[][] board) {
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    if (board[x][y] == 0) {
                        return new int[]{x, y};
                    }
                }
            }
            return null;
        }

        private boolean swap(int[][] startBoard, int x1, int y1, int x2, int y2) {
            if (x2 < 0 || y2 < 0 || x2 >= 2 || y2 >= 3) {
                return false;
            }
            int temp = startBoard[x1][y1];
            startBoard[x1][y1] = startBoard[x2][y2];
            startBoard[x2][y2] = temp;
            return true;
        }

        private void calc(int[][] startBoard, Set<String> newStartSet, Set<String> memo) {
            String startStr = convert(startBoard);
            if (memo.contains(startStr)) {
                return;
            }
            newStartSet.add(startStr);
            memo.add(startStr);
        }

        private String convert(int[][] board) {
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    builder.append(board[x][y]);
                }
            }
            return builder.toString();
        }

        private int[][] convert(String boardStr) {
            int[][] board = new int[2][3];
            int i = 0;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    board[x][y] = boardStr.charAt(i++) - 0x30;
                }
            }
            return board;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
