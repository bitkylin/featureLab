/**
 * <p>给你两个数组，<code>arr1</code> 和 <code>arr2</code>，</p>
 *
 * <ul>
 * <li><code>arr2</code> 中的元素各不相同</li>
 * <li><code>arr2</code> 中的每个元素都出现在 <code>arr1</code> 中</li>
 * </ul>
 *
 * <p>对 <code>arr1</code> 中的元素进行排序，使 <code>arr1</code> 中项的相对顺序和 <code>arr2</code> 中的相对顺序相同。未在 <code>arr2</code> 中出现过的元素需要按照升序放在 <code>arr1</code> 的末尾。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * <strong>输出：</strong>[2,2,2,1,4,3,3,9,6,7,19]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= arr1.length, arr2.length <= 1000</code></li>
 * <li><code>0 <= arr1[i], arr2[i] <= 1000</code></li>
 * <li><code>arr2</code> 中的元素 <code>arr2[i]</code> 各不相同</li>
 * <li><code>arr2</code> 中的每个元素 <code>arr2[i]</code> 都出现在 <code>arr1</code> 中</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数排序</li><li>排序</li></div></div><br><div><li>👍 198</li><li>👎 0</li></div>
 */

package leetcode8;

import java.util.*;

public class RelativeSortArray {

    public static void main(String[] args) {
        new RelativeSortArray().new Solution().relativeSortArray(
                new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 计数排序
     */
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int[] memo = new int[1001];
            for (int i : arr1) {
                memo[i]++;
            }
            int i = 0;
            for (int val : arr2) {
                while (memo[val]-- > 0) {
                    arr1[i++] = val;
                }
            }
            for (int val = 0; val < memo.length; val++) {
                while (memo[val]-- > 0) {
                    arr1[i++] = val;
                }
            }
            return arr1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 重写集合的排序方法
     */
    class Solution2 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            List<Integer> list = new ArrayList<>();
            for (int i : arr1) {
                list.add(i);
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < arr2.length; i++) {
                map.put(arr2[i], i);
            }
            Collections.sort(list, (o1, o2) -> {
                int v1 = fetch(map, o1);
                int v2 = fetch(map, o2);
                return v1 - v2;
            });
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = list.get(i);
            }
            return arr1;
        }

        private Integer fetch(Map<Integer, Integer> map, int i) {
            Integer val = map.get(i);
            if (val == null) {
                val = i + 1001;
            }
            return val;
        }
    }
}
