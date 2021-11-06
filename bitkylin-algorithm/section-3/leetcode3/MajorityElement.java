//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法 
// 👍 750 👎 0


package leetcode3;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MajorityElement {

    public static void main(String[] args) {
        new MajorityElement().new Solution().majorityElement(new int[]{3, 2, 3});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * topK
     */
    class Solution {
        public int majorityElement(int[] nums) {
            int length = nums.length + 1 >> 1;
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for (int num : nums) {
                queue.add(num);
                if (queue.size() > length) {
                    queue.poll();
                }
            }
            return queue.poll();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 排序取中
     */
    class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length >> 1];
        }
    }

    /**
     * Map中找最大值
     */
    class Solution1 {
        public int majorityElement(int[] nums) {
            Map<Integer, Long> map = Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int length = nums.length >> 1;
            for (Map.Entry<Integer, Long> entry : map.entrySet()) {
                if (entry.getValue() > length) {
                    return entry.getKey();
                }
            }
            return -1;
        }
    }
}