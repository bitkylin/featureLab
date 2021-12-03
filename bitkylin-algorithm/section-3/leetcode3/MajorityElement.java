/**
 * <p>给定一个大小为 <em>n </em>的数组，找到其中的多数元素。多数元素是指在数组中出现次数 <strong>大于</strong> <code>⌊ n/2 ⌋</code> 的元素。</p>
 *
 * <p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[3,2,3]
 * <strong>输出：</strong>3</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>[2,2,1,1,1,2,2]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>计数</li><li>排序</li></div></div><br><div><li>👍 1219</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MajorityElement {

    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * topK，此处大顶堆和小顶堆结果一致，可以任意用
     * 时间复杂度: O(NlogN)
     * 空间复杂度：O(n)
     */
    class Solution {
        public int majorityElement(int[] nums) {
            int count = ((nums.length + 1) >> 1) + 1;
            PriorityQueue<Integer> queue = new PriorityQueue<>(count);
//            PriorityQueue<Integer> queue = new PriorityQueue<>(max, (o1, o2) -> o2 - o1);
            for (int num : nums) {
                queue.offer(num);
                if (queue.size() == count) {
                    queue.poll();
                }
            }
            return queue.poll();
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 时间复杂度: O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution1 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length >> 1];
        }
    }

    /**
     * Map中找最大值
     * 时间复杂度: O(NlogN)
     * 空间复杂度：O(n)
     */
    class Solution2 {
        public int majorityElement(int[] nums) {
            int countSub = (nums.length + 1) >> 1;
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                Integer count = map.get(num);
                if (count == null) {
                    map.put(num, 1);
                } else {
                    if (++count == countSub) return num;
                    map.put(num, count);
                }
            }
            return nums[0];
        }
    }
}
