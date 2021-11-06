//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
// 示例:
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// Related Topics 回溯算法
// 👍 399 👎 0


package leetcode3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Combinations {

    public static void main(String[] args) {
        new Combinations().new Solution().combine(4, 2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 递归，使用stack求出所有解，而后转为list输出
     * 剪枝优化：剩下数字不足以填充stack时，停止遍历
     * 备注：剪枝优化前，solution1耗时少；剪枝优化后，两种方法耗时等同
     */
    class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 递归，遍历出所有list并返回
     */
    class Solution2 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            resolve(n, k, 1, new ArrayList<>(k), res);
            return res;
        }

        private void resolve(int n, int k, int start, List<Integer> beforeList, List<List<Integer>> res) {
            if (beforeList.size() == k) {
                res.add(beforeList);
                return;
            }

            for (int i = start; i <= n - (k - beforeList.size()) + 1; i++) {
                List<Integer> afterList = new ArrayList<>(k);
                afterList.addAll(beforeList);
                afterList.add(i);
                resolve(n, k, i + 1, afterList, res);
            }
        }
    }
}
