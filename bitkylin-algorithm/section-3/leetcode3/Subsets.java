/**
 * <p>给你一个整数数组 <code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>
 *
 * <p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[[],[0]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * <li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1403</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
    }

    /**
     * 递归
     */
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            solve(res, nums, new ArrayList<>(), 0);
            return res;
        }

        private void solve(List<List<Integer>> res, int[] nums, List<Integer> list, int i) {
            if (i == nums.length) {
                res.add(list);
                return;
            }

            solve(res, nums, list, i + 1);
            list = new ArrayList<>(list);
            list.add(nums[i]);
            solve(res, nums, list, i + 1);
        }
    }

    /**
     * 技巧性做法
     */
    class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> sub = new ArrayList<>();
                for (List<Integer> list : res) {
                    list = new ArrayList<>(list);
                    list.add(nums[i]);
                    sub.add(list);
                }
                res.addAll(sub);
            }
            return res;
        }
    }
}
