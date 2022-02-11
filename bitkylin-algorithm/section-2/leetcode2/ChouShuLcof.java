/**
 * <p>我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例:</strong></p>
 *
 * <pre><strong>输入:</strong> n = 10
 * <strong>输出:</strong> 12
 * <strong>解释: </strong><code>1, 2, 3, 4, 5, 6, 8, 9, 10, 12</code> 是前 10 个丑数。</pre>
 *
 * <p><strong>说明:&nbsp;</strong>&nbsp;</p>
 *
 * <ol>
 * <li><code>1</code>&nbsp;是丑数。</li>
 * <li><code>n</code>&nbsp;<strong>不超过</strong>1690。</li>
 * </ol>
 *
 * <p>注意：本题与主站 264 题相同：<a href="https://leetcode-cn.com/problems/ugly-number-ii/">https://leetcode-cn.com/problems/ugly-number-ii/</a></p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>数学</li><li>动态规划</li><li>堆（优先队列）</li></div></div><br><div><li>👍 247</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ChouShuLcof {

    public static void main(String[] args) {
        new ChouShuLcof().new Solution().nthUglyNumber(10);
    }

    /**
     * 使用默认的小顶堆
     * 注：值太大时需用 long
     */
    class Solution {
        public int nthUglyNumber(int n) {
            PriorityQueue<Long> queue = new PriorityQueue<>();
            queue.offer(1L);
            Set<Long> set = new HashSet<>();
            for (int i = 0; i < n - 1; i++) {
                long val = queue.poll();
                offer(queue, set, val * 2L);
                offer(queue, set, val * 3L);
                offer(queue, set, val * 5L);
            }
            return queue.poll().intValue();
        }

        private void offer(PriorityQueue<Long> queue, Set<Long> set, long val) {
            if (!set.contains(val)) {
                set.add(val);
                queue.offer(val);
            }
        }
    }

    /**
     * 1. 所有丑数均是前序丑数乘以2、3、5得到
     * 2. 例如：乘数2在索引n的位置，f(n)是已计算过的最小的丑数，则2的索引位置应该设为n+1，因为再计算f(n)没有意义了
     */
    class Solution2 {
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            dp[0] = 1;
            int a = 0, b = 0, c = 0;
            for (int i = 1; i < n; i++) {
                int av = dp[a] * 2;
                int bv = dp[b] * 3;
                int cv = dp[c] * 5;
                int val = Math.min(Math.min(av, bv), cv);
                dp[i] = val;
                if (av == val) a++;
                if (bv == val) b++;
                if (cv == val) c++;
            }
            return dp[n - 1];
        }
    }
}