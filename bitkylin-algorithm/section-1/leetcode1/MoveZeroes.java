/**
 * <p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre><strong>输入:</strong> <code>[0,1,0,3,12]</code>
 * <strong>输出:</strong> <code>[1,3,12,0,0]</code></pre>
 *
 * <p><strong>说明</strong>:</p>
 *
 * <ol>
 * <li>必须在原数组上操作，不能拷贝额外的数组。</li>
 * <li>尽量减少操作次数。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 1368</li><li>👎 0</li></div>
 */

package leetcode1;

public class MoveZeroes {

    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 交换
     */
    class Solution {
        public void moveZeroes(int[] nums) {
            for (int i = 0, j = 0; j < nums.length; j++) {
                if (nums[j] != 0) {
                    swap(nums, i++, j);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            if (i == j) {
                return;
            }
            int k = nums[i];
            nums[i] = nums[j];
            nums[j] = k;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 两遍循环
     */
    class Solution2 {
        public void moveZeroes(int[] nums) {
            int j = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[j++] = nums[i];
                }
            }

            for (; j < nums.length; j++) {
                nums[j] = 0;
            }
        }
    }
}
