/**
 * <p>给定一个可包含重复数字的序列 <code>nums</code> ，<strong>按任意顺序</strong> 返回所有不重复的全排列。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,2]
 * <strong>输出：</strong>
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 8</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 878</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.*;
import java.util.stream.Collectors;

public class PermutationsIi {

    public static void main(String[] args) {
        new PermutationsIi().new Solution().permuteUnique(new int[]{0, 1, 0, 0, 9});
    }

    /**
     * 只要理解原理，这道题就很简单
     * 递归过程中，对当前层涉及的元素去重
     * 相比于 {@link Permutations}，仅增加了一个Set记录当前层已处理过的元素
     * 注：不能简单比较相邻两元素，因为使用 swap 会导致乱序
     */
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            solve(res, nums, 0);
            return new ArrayList<>(res);
        }

        private void solve(List<List<Integer>> res, int[] nums, int i) {
            if (i == nums.length) {
                res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
                return;
            }
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                if (set.contains(nums[j])) {
                    continue;
                }
                set.add(nums[j]);
                swap(nums, i, j);
                solve(res, nums, i + 1);
                swap(nums, i, j);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 对结果集用Set去重
     */
    class Solution2 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            Set<List<Integer>> res = new HashSet<>();
            solve(res, nums, 0);
            return new ArrayList<>(res);
        }

        private void solve(Set<List<Integer>> res, int[] nums, int i) {
            if (i == nums.length) {
                res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
                return;
            }

            for (int j = i; j < nums.length; j++) {
                swap(nums, i, j);
                solve(res, nums, i + 1);
                swap(nums, i, j);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
