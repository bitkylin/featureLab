//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 918 👎 0


package leetcode3;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        new Permutations().new Solution().permute(new int[]{1, 2, 3});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 回溯算法，数组元素交换
     * 特点：进行改变 -> 进入下一层 -> 撤销改变
     * 推荐题解：
     * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
     * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     */
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            resolve(0, nums, res);
            return res;
        }

        private void resolve(int start, int[] nums, List<List<Integer>> res) {
            if (start == nums.length) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < nums.length; i++) {
                    list.add(nums[i]);
                }
                res.add(list);
                return;
            }

            for (int i = start; i < nums.length; i++) {
                swap(nums, i, start);
                resolve(start + 1, nums, res);
                swap(nums, i, start);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int k = nums[i];
            nums[i] = nums[j];
            nums[j] = k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
