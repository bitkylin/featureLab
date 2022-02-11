/**
 * <p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回其中出现频率前 <code>k</code> 高的元素。你可以按 <strong>任意顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>nums = [1,1,1,2,2,3], k = 2
 * <strong>输出: </strong>[1,2]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>nums = [1], k = 1
 * <strong>输出: </strong>[1]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
 * <li><code>k</code> 的取值范围是 <code>[1, 数组中不相同的元素的个数]</code></li>
 * <li>题目数据保证答案唯一，换句话说，数组中前 <code>k</code> 个高频元素的集合是唯一的</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>你所设计算法的时间复杂度 <strong>必须</strong> 优于 <code>O(n log n)</code> ，其中 <code>n</code><em> </em>是数组大小。</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>桶排序</li><li>计数</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1010</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
    }

    /**
     * 使用定长的小顶堆
     * 时间复杂度：O(NlogK) = O(n) + O(NlogK)
     */
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            Map<Integer, Integer> map = new HashMap<>(nums.length);
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                queue.add(entry);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            int[] res = new int[k];
            for (int i = 0; !queue.isEmpty() && i < k; i++) {
                res[i] = queue.poll().getKey();
            }
            return res;
        }
    }

    /**
     * 大顶堆，无限制容量
     * O(NlogN)，时间复杂度不符合要求
     */
    class Solution2 {

        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                Integer count = map.get(num);
                map.put(num, count == null ? 1 : count + 1);
            }
            PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
//            map.entrySet().forEach(item -> queue.add(item));
            queue.addAll(map.entrySet());
            int[] res = new int[k];
            for (int i = 0; i < k && !queue.isEmpty(); i++) {
                res[i] = queue.poll().getKey();
            }
            return res;
        }

    }
}

