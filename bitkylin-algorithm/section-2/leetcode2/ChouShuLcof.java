//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
//
//
// 示例:
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
//
// 说明:
//
//
// 1 是丑数。
// n 不超过1690。
//
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
// Related Topics 数学
// 👍 72 👎 0


package leetcode2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ChouShuLcof {

    public static void main(String[] args) {
        new ChouShuLcof().new Solution().nthUglyNumber(5);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 其他方案待后续学习后补全
     */
    class Solution {

        public int nthUglyNumber(int n) {
            //todo
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 小顶堆
     */
    class Solution2 {
        private final List<Integer> DEFAULT_VALUES = new ArrayList<Integer>() {{
            add(2);
            add(3);
            add(5);
        }};

        public int nthUglyNumber(int n) {
            PriorityQueue<Long> queue = new PriorityQueue<>();
            queue.add(1L);
            int i = 0;
            while (true) {
                long num = queue.poll();
                for (Integer defaultValue : DEFAULT_VALUES) {
                    long value = num * defaultValue;
                    if (!queue.contains(value)) {
                        queue.add(value);
                    }
                }
                if (++i >= n) {
                    return (int) num;
                }
            }
        }
    }
}
