/**
 * <p>给定一个不含重复数字的数组 <code>nums</code> ，返回其 <strong>所有可能的全排列</strong> 。你可以 <strong>按任意顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1]
 * <strong>输出：</strong>[[0,1],[1,0]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 6</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1662</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
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
            solve(res, nums, 0);
            return res;
        }

        private void solve(List<List<Integer>> res, int[] nums, int i) {
            if (i == nums.length) {
                res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            }
            for (int j = i; j < nums.length; j++) {
                swap(nums, j, i);
                solve(res, nums, i + 1);
                swap(nums, j, i);
            }
        }

        private void swap(int[] nums, int j, int i) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
