//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
// 上图为 8 皇后问题的一种解法。
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
//
// 示例:
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//
//
//
//
// 提示：
//
//
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N
//-1 步，可进可退。（引用自 百度百科 - 皇后 ）
//
// Related Topics 回溯算法
// 👍 151 👎 0


package leetcode3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.LongAdder;

public class NQueensIi {

    public static void main(String[] args) {
        Solution solution = new NQueensIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 使用int实例变量代替 {@link LongAdder}，性能明显高了很多
     */
    class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)

}
