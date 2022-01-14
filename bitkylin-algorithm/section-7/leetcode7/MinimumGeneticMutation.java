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
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 101</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.*;

public class MinimumGeneticMutation {

    public static void main(String[] args) {
        Solution solution = new MinimumGeneticMutation().new Solution();
    }

    /**
     * BFS + Set
     * 最优解法
     */
    class Solution {
        public int minMutation(String start, String end, String[] bank) {
            Set<String> startSet = new HashSet<>();
            List<String> bankList = new ArrayList<>(Arrays.asList(bank));
            startSet.add(start);
            int res = 0;
            while (!startSet.isEmpty()) {
                res++;
                startSet = update(startSet, bankList);
                if (startSet.contains(end)) {
                    return res;
                }
            }
            return -1;
        }

        private Set<String> update(Set<String> startSet, List<String> bankList) {
            Set<String> res = new HashSet<>();
            Iterator<String> iterator = bankList.iterator();
            while (iterator.hasNext()) {
                String bank = iterator.next();
                for (String start : startSet) {
                    if (calc(start, bank)) {
                        res.add(bank);
                        iterator.remove();
                        break;
                    }
                }
            }
            return res;
        }

        private boolean calc(String w1, String w2) {
            int res = 0;
            for (int i = 0; i < w1.length(); i++) {
                res += w1.charAt(i) == w2.charAt(i) ? 0 : 1;
            }
            return res == 1;
        }
    }

    /**
     * 双向BFS，需要注意以下几点：
     * 1. start和end的不同之处：end如果不在bank中，则直接返回失败
     * 2. bank不能像BFS一样，移除已使用的元素。如果移除的话，两边无法包含重复的元素
     */
    class Solution2 {

        public int minMutation(String start, String end, String[] bank) {
            Set<String> bankSet = new HashSet<>();
            for (int i = 0; i < bank.length; i++) {
                bankSet.add(bank[i]);
            }
            if (!bankSet.contains(end)) {
                return -1;
            }
            Set<String> sSet = new HashSet<>();
            Set<String> eSet = new HashSet<>();
            sSet.add(start);
            eSet.add(end);
            int level = 0;
            while (!sSet.isEmpty() && !eSet.isEmpty()) {
                if (sSet.size() > eSet.size()) {
                    Set<String> temp = sSet;
                    sSet = eSet;
                    eSet = temp;
                }
                Set<String> temp = new HashSet<>();
                for (String s : sSet) {
                    if (eSet.contains(s)) {
                        return level;
                    }
                    Iterator<String> iterator = bankSet.iterator();
                    while (iterator.hasNext()) {
                        String t = iterator.next();
                        if (calc(s, t)) {
                            temp.add(t);
//                            iterator.remove();
                        }
                    }
                }
                level++;
                sSet = temp;
            }
            return -1;
        }

        private boolean calc(String s, String t) {
            int val = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    val++;
                }
            }
            return val == 1;
        }
    }

    /**
     * BFS
     */
    class Solution3 {

        public int minMutation(String start, String end, String[] bank) {
            boolean[] memo = new boolean[bank.length];
            int level = 0;
            Deque<String> deque = new ArrayDeque<>();
            deque.offer(start);
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    String t = deque.poll();
                    if (t.equals(end)) {
                        return level;
                    }
                    for (int j = 0; j < bank.length; j++) {
                        if (memo[j]) {
                            continue;
                        }
                        String s = bank[j];
                        int val = 0;
                        for (int k = 0; k < s.length(); k++) {
                            if (s.charAt(k) != t.charAt(k)) {
                                val++;
                            }
                        }
                        if (val == 1) {
                            memo[j] = true;
                            deque.offer(s);
                        }
                    }
                }
                level++;
            }
            return -1;
        }
    }

    /**
     * DFS
     */
    class Solution4 {

        int levelMax = Integer.MAX_VALUE;

        public int minMutation(String start, String end, String[] bank) {
            boolean[] memo = new boolean[bank.length];
            solve(0, start, memo, end, bank);
            return levelMax == Integer.MAX_VALUE ? -1 : levelMax;
        }

        private void solve(int level, String start, boolean[] memo, String end, String[] bank) {
            if (start.equals(end)) {
                levelMax = Math.min(levelMax, level);
                return;
            }

            for (int i = 0; i < bank.length; i++) {
                if (memo[i]) {
                    continue;
                }
                String t = bank[i];
                int val = calc(start, t);
                if (val == 1) {
                    memo[i] = true;
                    solve(level + 1, t, memo, end, bank);
                    memo[i] = false;
                }
            }
        }

        private int calc(String start, String t) {
            int val = 0;
            for (int j = 0; j < start.length(); j++) {
                if (start.charAt(j) != t.charAt(j)) {
                    val++;
                }
            }
            return val;
        }
    }
}
