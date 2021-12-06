/**
 * <p>一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 <code>"A"</code>, <code>"C"</code>, <code>"G"</code>, <code>"T"</code>中的任意一个。</p>
 *
 * <p>假设我们要调查一个基因序列的变化。<strong>一次</strong>基因变化意味着这个基因序列中的<strong>一个</strong>字符发生了变化。</p>
 *
 * <p>例如，基因序列由<code>"AACCGGTT"</code> 变化至 <code>"AACCGGTA" </code>即发生了一次基因变化。</p>
 *
 * <p>与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。</p>
 *
 * <p>现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。</p>
 *
 * <p><strong>注意：</strong></p>
 *
 * <ol>
 * <li>起始基因序列默认是合法的，但是它并不一定会出现在基因库中。</li>
 * <li>如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。</li>
 * <li>假定起始基因序列与目标基因序列是不一样的。</li>
 * </ol>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * 返回值: 1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * 返回值: 2
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * 返回值: 3
 * </pre>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 94</li><li>👎 0</li></div>
 */

package leetcode4;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本问题的优秀解法参考 {@link leetcode7.MinimumGeneticMutation}
 *
 * @author bitkylin
 */
public class MinimumGeneticMutation {

    public static void main(String[] args) {
        new MinimumGeneticMutation().new Solution().minMutation("AACCGGTT", "AAACGGTA",
                new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS，回溯
     */
    class Solution {
        public int minMutation(String start, String end, String[] bank) {
            AtomicInteger res = new AtomicInteger(Integer.MAX_VALUE);
            solve(start, end, bank, res, new HashSet<>(), 0);
            return res.get() == Integer.MAX_VALUE ? -1 : res.get();
        }

        private void solve(String start, String end, String[] bank, AtomicInteger res, Set<String> set, int level) {
            if (res.get() < level) {
                return;
            }
            if (start.equals(end)) {
                res.set(level);
                return;
            }
            for (String b : bank) {
                if (!set.contains(b) && match(start, b)) {
                    set.add(b);
                    solve(b, end, bank, res, set, level + 1);
                    set.remove(b);
                }
            }
        }

        private boolean match(String start, String end) {
            if (start.length() != end.length()) {
                return false;
            }
            int count = 0;
            for (int i = 0; i < start.length(); i++) {
                if (start.charAt(i) != end.charAt(i)) {
                    if (++count > 1) return false;
                }
            }
            return count == 1;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * BFS，循环
     */
    class Solution1 {
        public int minMutation(String start, String end, String[] bank) {
            int step = 0;
            List<String> src = new ArrayList<>();
            List<String> bankList = new ArrayList<>(Arrays.asList(bank));

            src.add(start);
            while (!src.isEmpty()) {
                if (src.contains(end)) {
                    return step;
                }
                step++;
                src = solve(src, bankList);
            }
            return -1;
        }

        private List<String> solve(List<String> src, List<String> bankList) {
            List<String> res = new ArrayList<>();
            for (String s : src) {
                Iterator<String> bankIterator = bankList.iterator();
                while (bankIterator.hasNext()) {
                    String b = bankIterator.next();
                    if (match(b, s)) {
                        res.add(b);
                        bankIterator.remove();
                    }
                }
            }
            return res;
        }

        private boolean match(String start, String end) {
            if (start.length() != end.length()) {
                return false;
            }
            int count = 0;
            for (int i = 0; i < start.length(); i++) {
                if (start.charAt(i) != end.charAt(i)) {
                    if (++count > 1) return false;
                }
            }
            return count == 1;
        }
    }
}
