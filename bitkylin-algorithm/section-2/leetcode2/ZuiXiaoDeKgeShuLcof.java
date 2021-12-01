/**
 * <p>输入整数数组 <code>arr</code> ，找出其中最小的 <code>k</code> 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [3,2,1], k = 2
 * <strong>输出：</strong>[1,2] 或者 [2,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>arr = [0,1,2,1], k = 1
 * <strong>输出：</strong>[0]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= k &lt;= arr.length &lt;= 10000</code></li>
 * <li><code>0 &lt;= arr[i]&nbsp;&lt;= 10000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 330</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.PriorityQueue;

public class ZuiXiaoDeKgeShuLcof {

    public static void main(String[] args) {
        Solution solution = new ZuiXiaoDeKgeShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 默认小顶堆，需转换为大顶堆：O(nlog(k))
     * 其他解法：排序后取前K个，O(nlog(n))
     */
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            for (int i : arr) {
                queue.offer(i);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
            int[] res = new int[k];
            int i = 0;
            for (Integer val : queue) {
                res[i++] = val;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
