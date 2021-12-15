/**
 * <p>假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。</p>
 *
 * <p>对每个孩子 <code>i</code>，都有一个胃口值 <code>g[i]</code><sub>，</sub>这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 <code>j</code>，都有一个尺寸 <code>s[j]</code><sub> </sub>。如果 <code>s[j] >= g[i]</code>，我们可以将这个饼干 <code>j</code> 分配给孩子 <code>i</code> ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。</p>
 *
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> g = [1,2,3], s = [1,1]
 * <strong>输出:</strong> 1
 * <strong>解释:</strong>
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> g = [1,2], s = [1,2,3]
 * <strong>输出:</strong> 2
 * <strong>解释:</strong>
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= g.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>0 <= s.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>1 <= g[i], s[j] <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>排序</li></div></div><br><div><li>👍 421</li><li>👎 0</li></div>
 */

package leetcode4;

import java.util.Arrays;

public class AssignCookies {

    public static void main(String[] args) {
        Solution solution = new AssignCookies().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 正序，直接使用贪心，局部最优即为全局最优
     */
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int res = 0;
            if (g.length == 0 || s.length == 0) {
                return res;
            }
            int j = 0;
            for (int i = 0; i < s.length && j < g.length; i++) {
                if (s[i] >= g[j]) j++;
            }
            return j;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 倒序
     */
    class Solution2 {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int res = 0;
            if (g.length == 0 || s.length == 0) {
                return res;
            }
            for (int i = s.length - 1, j = g.length - 1; j >= 0; j--) {
                if (s[i] >= g[j]) {
                    res++;
                    if (--i < 0) return res;
                }
            }
            return res;
        }
    }
}