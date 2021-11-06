//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针
// 👍 731 👎 0

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
