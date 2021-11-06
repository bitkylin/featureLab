//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
// 上图为 8 皇后问题的一种解法。
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例：
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
//
//
//
//
// 提示：
//
//
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
// Related Topics 回溯算法
// 👍 655 👎 0


package leetcode7;

import java.util.*;

/**
 * {@link leetcode3.NQueens}
 */
public class NQueens {

    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            Deque<Integer> deque = new ArrayDeque<>();
            List<List<String>> res = new ArrayList<>();
            Set<Integer> column = new HashSet<>();
            Set<Integer> left = new HashSet<>();
            Set<Integer> right = new HashSet<>();
            solve(0, n, deque, res, column, left, right);
            return res;
        }

        private void solve(int row, int n, Deque<Integer> deque, List<List<String>> res, Set<Integer> column, Set<Integer> left, Set<Integer> right) {
            if (row == n) {
                res.add(buildRtn(deque, n));
            }

            for (int i = 0; i < n; i++) {
                if (column.contains(i) || left.contains(row + i) || right.contains(row - i)) {
                    continue;
                }
                column.add(i);
                left.add(row + i);
                right.add(row - i);
                deque.push(i);

                solve(row + 1, n, deque, res, column, left, right);

                column.remove(i);
                left.remove(row + i);
                right.remove(row - i);
                deque.pop();
            }
        }

        private List<String> buildRtn(Deque<Integer> deque, int n) {
            List<String> list = new ArrayList<>();
            for (Integer val : deque) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (val == i) {
                        builder.append("Q");
                    } else {
                        builder.append(".");
                    }
                }
                list.add(builder.toString());
            }
            return list;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {
        public List<List<String>> solveNQueens(int n) {
            Deque<Integer> deque = new ArrayDeque<>();
            List<List<String>> res = new ArrayList<>();
            boolean[] column = new boolean[n];
            boolean[] left = new boolean[2 * n - 1];
            boolean[] right = new boolean[2 * n - 1];
            solve(0, n, deque, res, column, left, right);
            return res;
        }

        private void solve(int row, int n, Deque<Integer> deque, List<List<String>> res, boolean[] column, boolean[] left, boolean[] right) {

            if (row == n) {
                res.add(buildRtn(deque, n));
            }

            for (int i = 0; i < n; i++) {
                if (column[i] || left[row + i] || right[row - i + n - 1]) {
                    continue;
                }
                column[i] = true;
                left[row + i] = true;
                right[row - i + n - 1] = true;
                deque.push(i);

                solve(row + 1, n, deque, res, column, left, right);

                column[i] = false;
                left[row + i] = false;
                right[row - i + n - 1] = false;
                deque.pop();
            }
        }

        private List<String> buildRtn(Deque<Integer> deque, int n) {
            List<String> list = new ArrayList<>();
            for (Integer val : deque) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    builder.append(val == i ? "Q" : ".");
                }
                list.add(builder.toString());
            }
            return list;
        }
    }
}
