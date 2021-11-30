/**
 * <p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>
 *
 * <p>你可以按 <strong>任何顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 4, k = 2
 * <strong>输出：</strong>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1, k = 1
 * <strong>输出：</strong>[[1]]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 20</code></li>
 * <li><code>1 <= k <= n</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 783</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Combinations {

    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 递归，遍历出所有list并返回
     */
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            solve(res, new ArrayList<>(), n, k, 1);
            return res;
        }

        private void solve(List<List<Integer>> res, List<Integer> list, int n, int k, int i) {
            if (list.size() == k) {
                res.add(list);
                return;
            }

            for (; i <= n - k + list.size() + 1; i++) {
                List<Integer> next = new ArrayList<>(list);
                next.add(i);
                solve(res, next, n, k, i + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归，使用stack求出所有解，而后转为list输出
     * 剪枝优化：剩下数字不足以填充stack时，停止遍历
     * 备注：剪枝优化前，solution1耗时少；剪枝优化后，两种方法耗时等同
     */
    class Solution2 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            Deque<Integer> stack = new ArrayDeque<>(k);
            resolve(n, k, 1, stack, res);
            return res;
        }

        private void resolve(int n, int k, int start, Deque<Integer> stack, List<List<Integer>> res) {
            if (stack.size() == k) {
                res.add(new ArrayList<>(stack));
                return;
            }
            for (int i = start; i <= n - (k - stack.size()) + 1; i++) {
                stack.addLast(i);
                resolve(n, k, i + 1, stack, res);
                stack.removeLast();
            }
        }
    }
}
