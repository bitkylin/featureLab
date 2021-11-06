//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。
//
// 注意:
//
//
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
// 所有的目标基因序列必须是合法的。
// 假定起始基因序列与目标基因序列是不一样的。
//
//
// 示例 1:
//
//
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
//
//
// 示例 2:
//
//
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
//
//
// 示例 3:
//
//
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
//
// 👍 51 👎 0


package leetcode4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinimumGeneticMutation {

    public static void main(String[] args) {
        Solution solution = new MinimumGeneticMutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * BFS，循环，效率极高
     */
    class Solution {
        public int minMutation(String start, String end, String[] bank) {
            Set<String> set = new HashSet<>();
            Deque<String> queue = new ArrayDeque<>();
            queue.offer(start);

            int level = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String str = queue.poll();
                    if (end.equals(str)) {
                        return level;
                    }
                    for (String bankStr : bank) {
                        if (set.contains(bankStr)) {
                            continue;
                        }
                        int diff = 0;
                        for (int j = 0; j < str.length(); j++) {
                            if (str.charAt(j) != bankStr.charAt(j)) {
                                diff++;
                            }
                        }
                        if (diff == 1) {
                            queue.offer(bankStr);
                            set.add(bankStr);
                        }
                    }
                }
                level++;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS，回溯，效率极高
     */
    class Solution2 {
        private int min = Integer.MAX_VALUE;

        public int minMutation(String start, String end, String[] bank) {
            Set<String> set = new HashSet<>();
            resolve(start, 0, end, set, bank);
            return min == Integer.MAX_VALUE ? -1 : min;
        }

        private void resolve(String start, int level, String end, Set<String> set, String[] bank) {
            if (end.equals(start)) {
                min = Math.min(min, level);
                return;
            }
            for (String bankStr : bank) {
                if (set.contains(bankStr)) {
                    continue;
                }
                int diff = 0;
                for (int i = 0; i < bankStr.length(); i++) {
                    if (bankStr.charAt(i) != start.charAt(i)) {
                        diff++;
                    }
                }
                if (diff == 1) {
                    set.add(bankStr);
                    resolve(bankStr, level + 1, end, set, bank);
                    set.remove(bankStr);
                }
            }
        }
    }
}
