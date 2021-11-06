//给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
// 示例:
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//]
// Related Topics 回溯算法
// 👍 480 👎 0


package leetcode3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsIi {

    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 相比于 {@link Permutations}，仅增加了一个Set记录当前层已处理过的元素
     *
     * @see Permutations
     */
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            resolve(nums, res, 0);
            return res;
        }

        private void resolve(int[] nums, List<List<Integer>> res, int start) {
            if (start == nums.length) {
                List<Integer> list = new ArrayList<>();
                for (int num : nums) {
                    list.add(num);
                }
                res.add(list);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    continue;
                }
                set.add(nums[i]);
                swapNum(nums, i, start);
                resolve(nums, res, start + 1);
                swapNum(nums, i, start);
            }
        }

        private void swapNum(int[] nums, int i, int j) {
            int value = nums[i];
            nums[i] = nums[j];
            nums[j] = value;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
