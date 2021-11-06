//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
//
//
//
// 每次转换只能改变一个字母。
// 转换过程中的中间单词必须是字典中的单词。
//
//
// 说明:
//
//
// 如果不存在这样的转换序列，返回 0。
// 所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
//
//
// 示例 1:
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
//
//
// 示例 2:
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。
// Related Topics 广度优先搜索
// 👍 492 👎 0


package leetcode7;

import java.util.*;

/**
 * {@link leetcode4.WordLadder}
 */
public class WordLadder {

    public static void main(String[] args) {
        new WordLadder().new Solution()
                .ladderLength("hit",
                        "cog",
                        new ArrayList<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * BFS，队列实现
     */
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Deque<String> deque = new ArrayDeque<>();
            deque.offer(beginWord);
            int level = 1;
            while (!deque.isEmpty()) {
                if (deque.contains(endWord)) {
                    return level;
                }
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    String s = deque.poll();
                    Iterator<String> iterator = wordList.iterator();
                    while (iterator.hasNext()) {
                        String t = iterator.next();
                        if (calc(s, t)) {
                            iterator.remove();
                            deque.offer(t);
                        }
                    }
                }
                level++;
            }
            return 0;
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

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * BFS，数组实现
     */
    class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            List<String> target = new ArrayList<>();
            target.add(beginWord);
            int level = 1;
            while (!target.isEmpty()) {
                if (target.contains(endWord)) {
                    return level;
                }
                target = calc(wordList, target);
                level++;
            }
            return 0;
        }

        private List<String> calc(List<String> wordList, List<String> target) {
            List<String> res = new ArrayList<>();
            Iterator<String> iterator = wordList.iterator();
            while (iterator.hasNext()) {
                String s = iterator.next();
                for (int i = 0; i < target.size(); i++) {
                    String t = target.get(i);
                    if (solve(s, t)) {
                        res.add(s);
                        iterator.remove();
                        break;
                    }
                }
            }
            return res;
        }

        private boolean solve(String o1, String o2) {
            int val = 0;
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) != o2.charAt(i)) {
                    val++;
                }
            }
            return val == 1;
        }
    }
}
