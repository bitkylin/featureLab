/**
 * <p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n×n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>
 *
 * <p>给你一个整数 <code>n</code> ，返回所有不同的&nbsp;<strong>n<em>&nbsp;</em>皇后问题</strong> 的解决方案。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p>每一种解法包含一个不同的&nbsp;<strong>n 皇后问题</strong> 的棋子放置方案，该方案中 <code>'Q'</code> 和 <code>'.'</code> 分别代表了皇后和空位。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>[["Q"]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 9</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1114</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    public static void main(String[] args) {
        new NQueens().new Solution().solveNQueens(4);
    }

    class Solution {
        public List<List<String>> solveNQueens(int n) {
            boolean[] left = new boolean[2 * n];
            boolean[] right = new boolean[2 * n];
            boolean[] column = new boolean[n];

            List<List<String>> res = new ArrayList<>();
            int[] arr = new int[n];
            solve(res, arr, left, right, column, 0);
            return res;
        }

        private void solve(List<List<String>> res, int[] arr, boolean[] left, boolean[] right, boolean[] column, int x) {
            int n = arr.length;
            if (x >= n) {
                write(res, arr);
                return;
            }
            for (int y = 0; y < n; y++) {
                if (left[y + x] || right[y - x + n - 1] || column[y]) {
                    continue;
                }
                left[y + x] = true;
                right[y - x + n - 1] = true;
                column[y] = true;
                arr[x] = y;
                solve(res, arr, left, right, column, x + 1);
                left[y + x] = false;
                right[y - x + n - 1] = false;
                column[y] = false;
            }
        }

        private void write(List<List<String>> res, int[] arr) {
            List<String> list = new ArrayList<>();
            for (int y = 0; y < arr.length; y++) {
                StringBuilder builder = new StringBuilder();
                for (int x = 0; x < arr.length; x++) {
                    builder.append(arr[y] == x ? "Q" : ".");
                }
                list.add(builder.toString());
            }
            res.add(list);
        }
    }

    class Solution2 {
        public List<List<String>> solveNQueens(int n) {
            Set<Integer> left = new HashSet<>();
            Set<Integer> right = new HashSet<>();
            Set<Integer> column = new HashSet<>();
            List<List<String>> res = new ArrayList<>();
            int[] arr = new int[n];
            solve(res, arr, left, right, column, 0);
            return res;
        }

        private void solve(List<List<String>> res, int[] arr, Set<Integer> left, Set<Integer> right, Set<Integer> column, int y) {
            int n = arr.length;
            if (y >= n) {
                write(res, arr);
                return;
            }
            for (int x = 0; x < n; x++) {
                if (left.contains(x + y) || right.contains(x - y) || column.contains(x)) {
                    continue;
                }
                left.add(x + y);
                right.add(x - y);
                column.add(x);
                arr[y] = x;
                solve(res, arr, left, right, column, y + 1);
                left.remove(x + y);
                right.remove(x - y);
                column.remove(x);
            }
        }

        private void write(List<List<String>> res, int[] arr) {
            List<String> list = new ArrayList<>();
            for (int y = 0; y < arr.length; y++) {
                StringBuilder builder = new StringBuilder();
                for (int x = 0; x < arr.length; x++) {
                    builder.append(arr[y] == x ? "Q" : ".");
                }
                list.add(builder.toString());
            }
            res.add(list);
        }
    }
}
