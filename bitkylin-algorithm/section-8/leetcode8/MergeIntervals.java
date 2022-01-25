/**
 * <p>以数组 <code>intervals</code> 表示若干个区间的集合，其中单个区间为 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>intervals = [[1,3],[2,6],[8,10],[15,18]]
 * <strong>输出：</strong>[[1,6],[8,10],[15,18]]
 * <strong>解释：</strong>区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>intervals = [[1,4],[4,5]]
 * <strong>输出：</strong>[[1,5]]
 * <strong>解释：</strong>区间 [1,4] 和 [4,5] 可被视为重叠区间。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= intervals.length <= 10<sup>4</sup></code></li>
 * <li><code>intervals[i].length == 2</code></li>
 * <li><code>0 <= start<sub>i</sub> <= end<sub>i</sub> <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>排序</li></div></div><br><div><li>👍 1277</li><li>👎 0</li></div>
 */

package leetcode8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 1. 对数组中，每区间首位排序
     * 2. 遍历各区间，基于区间首位的包含关系进行合并
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
            List<int[]> list = new ArrayList<>();
            int[] arr = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                int[] item = intervals[i];
                if (item[0] >= arr[0] && item[0] <= arr[1]) {
                    arr[1] = Math.max(arr[1], item[1]);
                } else {
                    list.add(arr);
                    arr = item;
                }
            }
            list.add(arr);
            return list.toArray(new int[0][0]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
