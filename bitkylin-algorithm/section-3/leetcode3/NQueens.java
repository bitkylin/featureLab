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
// 👍 622 👎 0


package leetcode3;

import java.util.*;

public class NQueens {

    public static void main(String[] args) {
        new NQueens().new Solution().solveNQueens(2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 回溯，使用数组
     * 数组效率明显高于Set
     */
    class Solution {

        public List<List<String>> solveNQueens(int n) {
            boolean[] left = new boolean[2 * n - 1];
            boolean[] right = new boolean[2 * n - 1];
            boolean[] column = new boolean[n];

            List<List<String>> res = new ArrayList<>();
            Deque<Integer> queue = new ArrayDeque<>(n);
            resolve(res, queue, left, right, column, n, 0);
            return res;
        }

        private void resolve(List<List<String>> res, Deque<Integer> queue, boolean[] left, boolean[] right, boolean[] column, int n, int y) {
            if (y >= n) {
                res.add(calcRtn(queue));
                return;
            }

            for (int x = 0; x < n; x++) {
                if (left[x - y + n - 1] || right[x + y] || column[x]) {
                    continue;
                }
                queue.addLast(x);
                left[x - y + n - 1] = true;
                right[x + y] = true;
                column[x] = true;

                resolve(res, queue, left, right, column, n, y + 1);

                queue.removeLast();
                left[x - y + n - 1] = false;
                right[x + y] = false;
                column[x] = false;
            }
        }

        private List<String> calcRtn(Deque<Integer> queue) {
            List<String> rtnStr = new ArrayList<>();
            for (Integer re : queue) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < queue.size(); i++) {
                    builder.append(i == re ? "Q" : ".");
                }
                rtnStr.add(builder.toString());
            }
            return rtnStr;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    /**
     * 回溯，使用set
     */
    class Solution2 {

        public List<List<String>> solveNQueens(int n) {

            List<List<String>> res = new ArrayList<>();
            Deque<Integer> deque = new ArrayDeque<>();

            Set<Integer> left = new HashSet<>();
            Set<Integer> right = new HashSet<>();
            Set<Integer> column = new HashSet<>();

            resolve(res, deque, n, left, right, column, 0);

            return res;
        }

        private void resolve(List<List<String>> res, Deque<Integer> deque, int n, Set<Integer> left, Set<Integer> right, Set<Integer> column, int y) {
            if (y >= n) {
                res.add(calcRtn(deque));
                return;
            }

            for (int x = 0; x < n; x++) {
                if (left.contains(x + y) || right.contains(x - y) || column.contains(x)) {
                    continue;
                }

                deque.addLast(x);
                left.add(x + y);
                right.add(x - y);
                column.add(x);

                resolve(res, deque, n, left, right, column, y + 1);

                deque.removeLast();
                left.remove(x + y);
                right.remove(x - y);
                column.remove(x);
            }
        }

        private List<String> calcRtn(Deque<Integer> deque) {
            List<String> list = new ArrayList<>();
            for (Integer value : deque) {
                StringBuilder builder = new StringBuilder(deque.size());
                for (int i = 0; i < deque.size(); i++) {
                    builder.append(i == value ? "Q" : ".");
                }
                list.add(builder.toString());
            }
            return list;
        }
    }
}
