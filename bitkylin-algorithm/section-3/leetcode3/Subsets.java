//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。
//
// 示例:
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
// Related Topics 位运算 数组 回溯算法
// 👍 811 👎 0


package leetcode3;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            resolve(res, nums, new ArrayList<>(nums.length), 0);
            return res;
        }

        private void resolve(List<List<Integer>> res, int[] nums, List<Integer> list, int i) {
            if (i == nums.length) {
                res.add(list);
                return;
            }
            resolve(res, nums, list, i + 1);
            List<Integer> afterList = new ArrayList<>(nums.length);
            afterList.addAll(list);
            afterList.add(nums[i]);
            resolve(res, nums, afterList, i + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
