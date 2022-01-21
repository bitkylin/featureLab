/**
 * <p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n × n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>
 *
 * <p>给你一个整数 <code>n</code> ，返回 <strong>n 皇后问题</strong> 不同的解决方案的数量。</p>
 *
 * <p>&nbsp;</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>2
 * <strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>1
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
 * <div><div>Related Topics</div><div><li>回溯</li></div></div><br><div><li>👍 325</li><li>👎 0</li></div>
 */

package leetcode8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.LongAdder;

public class NQueensIi {

    public static void main(String[] args) {
        new NQueensIi().new Solution().totalNQueens(4);
    }


    /**
     * 该题正解，位运算
     * 因为已限定 n < 32，int类型可以作为记忆数组使用
     */
    class Solution {
        public int totalNQueens(int n) {
            return solve(n, 0, 0, 0, 0);
        }

        private int solve(int n, int column, int left, int right, int level) {
            if (level >= n) return 1;
            // 当前棋盘未填充的位置 = (32位棋盘未填充的位置) & (当前棋盘的大小)
            int bits = (~(column | left | right)) & ((1 << n) - 1);
            int res = 0;
            // 未填充的位置进行遍历
            while (bits != 0) {
                // 选取末位的1 : x & -x
                int pick = bits & -bits;
                // 下探到下一层
                res += solve(n, column | pick, (left | pick) << 1, (right | pick) >>> 1, level + 1);
                // 清零末位的1 : x & ( x-1 )
                bits = bits & (bits - 1);
            }
            return res;
        }
    }

    /**
     * 使用int实例变量代替 {@link LongAdder}，性能明显高了很多
     */
    class Solution2 {
        int adder = 0;

        public int totalNQueens(int n) {
            Deque<Integer> deque = new ArrayDeque<>(n);
            boolean[] left = new boolean[2 * n - 1];
            boolean[] right = new boolean[2 * n - 1];
            boolean[] column = new boolean[n];
            resolve(deque, n, left, right, column, 0);
            return adder;
        }

        private void resolve(Deque<Integer> deque, int n, boolean[] left, boolean[] right, boolean[] column, int y) {
            if (y >= n) {
                adder++;
                return;
            }

            for (int x = 0; x < n; x++) {
                if (left[x + y] || right[x - y + n - 1] || column[x]) {
                    continue;
                }

                deque.addLast(x);
                left[x + y] = true;
                right[x - y + n - 1] = true;
                column[x] = true;

                resolve(deque, n, left, right, column, y + 1);

                deque.removeLast();
                left[x + y] = false;
                right[x - y + n - 1] = false;
                column[x] = false;
            }
        }
    }
}
