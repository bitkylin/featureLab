/**
 * <p>字典 <code>wordList</code> 中从单词 <code>beginWord</code><em> </em>和 <code>endWord</code> 的 <strong>转换序列 </strong>是一个按下述规格形成的序列：</p>
 *
 * <ul>
 * <li>序列中第一个单词是 <code>beginWord</code> 。</li>
 * <li>序列中最后一个单词是 <code>endWord</code> 。</li>
 * <li>每次转换只能改变一个字母。</li>
 * <li>转换过程中的中间单词必须是字典 <code>wordList</code> 中的单词。</li>
 * </ul>
 *
 * <p>给你两个单词<em> </em><code>beginWord</code><em> </em>和 <code>endWord</code> 和一个字典 <code>wordList</code> ，找到从 <code>beginWord</code> 到 <code>endWord</code> 的 <strong>最短转换序列</strong> 中的 <strong>单词数目</strong> 。如果不存在这样的转换序列，返回 0。</p>
 *
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * <strong>输出：</strong>5
 * <strong>解释：</strong>一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>endWord "cog" 不在字典中，所以无法进行转换。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= beginWord.length <= 10</code></li>
 * <li><code>endWord.length == beginWord.length</code></li>
 * <li><code>1 <= wordList.length <= 5000</code></li>
 * <li><code>wordList[i].length == beginWord.length</code></li>
 * <li><code>beginWord</code>、<code>endWord</code> 和 <code>wordList[i]</code> 由小写英文字母组成</li>
 * <li><code>beginWord != endWord</code></li>
 * <li><code>wordList</code> 中的所有字符串 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 910</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        new WordLadder().new Solution().ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    /**
     * BFS，Set实现BeginSet，迭代wordList，每个word去BeginSet中比较
     * 注：题目要求是最短转换序列的单词数目，不是转换次数
     * 最优解法
     */
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return 0;
            }
            int res = 1;
            Set<String> beginSet = new HashSet<>();
            beginSet.add(beginWord);
            while (!beginSet.isEmpty()) {
                if (beginSet.contains(endWord)) {
                    return res;
                }
                beginSet = solve(beginSet, wordList);
                res++;
            }
            return 0;
        }

        private Set<String> solve(Set<String> beginSet, List<String> wordList) {
            Set<String> set = new HashSet<>();
            Iterator<String> iterator = wordList.iterator();
            while (iterator.hasNext()) {
                String word = iterator.next();
                for (String beginWord : beginSet) {
                    if (calc(word, beginWord)) {
                        set.add(word);
                        iterator.remove();
                        break;
                    }
                }
            }
            return set;
        }

        private boolean calc(String word1, String word2) {
            if (word1.length() != word2.length()) {
                return false;
            }
            int res = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i) && ++res > 1) {
                    return false;
                }
            }
            return res == 1;
        }
    }

    /**
     * BFS，队列实现BeginList，每次poll一个BeginWord判断WordList
     */
    class Solution1 {
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

    /**
     * 单向 BFS，数组实现
     * 注：双向 BFS 太复杂，暂不研究
     */
    class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            List<String> begin = new ArrayList<>();
            begin.add(beginWord);
            int level = 1;
            while (!begin.isEmpty()) {
                if (begin.contains(endWord)) {
                    return level;
                }
                level++;
                begin = solve(begin, wordList);
            }
            return 0;
        }

        private List<String> solve(List<String> begin, List<String> wordList) {
            List<String> res = new ArrayList<>();
            for (String b : begin) {
                Iterator<String> iterator = wordList.iterator();
                while (iterator.hasNext()) {
                    String word = iterator.next();
                    if (calc(b, word)) {
                        res.add(word);
                        iterator.remove();
                    }
                }
            }
            return res;
        }

        private boolean calc(String aStr, String bStr) {
            char[] a = aStr.toCharArray();
            char[] b = bStr.toCharArray();
            if (a.length != b.length) {
                return false;
            }
            int cnt = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    cnt++;
                    if (cnt > 1) {
                        return false;
                    }
                }
            }
            return cnt == 1;
        }
    }

    /**
     * BFS，队列实现
     */
    class Solution3 {
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
    class Solution4 {
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

    /**
     * BFS，效率适中
     * 双向BFS解法待补充
     */
    class Solution5 {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            boolean[] visited = new boolean[wordList.size()];
            Deque<String> deque = new ArrayDeque<>();
            deque.offer(beginWord);
            int level = 1;
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    String str = deque.poll();
                    if (endWord.equals(str)) {
                        return level;
                    }
                    for (int j = 0; j < wordList.size(); j++) {
                        String word = wordList.get(j);
                        if (visited[j]) {
                            continue;
                        }

                        int diff = 0;
                        for (int k = 0; k < word.length(); k++) {
                            if (word.charAt(k) != str.charAt(k)) {
                                diff++;
                            }
                        }
                        if (diff == 1) {
                            visited[j] = true;
                            deque.offer(word);
                        }
                    }
                }
                level++;
            }
            return 0;
        }
    }

    /**
     * DFS + 回溯，超时，无法通过
     */
    class Solution6 {

        int min = Integer.MAX_VALUE;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            boolean[] visited = new boolean[wordList.size()];
            resolve(beginWord, endWord, wordList, visited, 1);
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        private void resolve(String beginWord, String endWord, List<String> wordList, boolean[] visited, int level) {
            if (endWord.equals(beginWord)) {
                min = Math.min(min, level);
                return;
            }
            for (int i = 0; i < wordList.size(); i++) {
                if (visited[i]) {
                    continue;
                }
                String word = wordList.get(i);
                int diff = 0;
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) != beginWord.charAt(j)) {
                        diff++;
                    }
                }
                if (diff == 1) {
                    visited[i] = true;
                    resolve(word, endWord, wordList, visited, level + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
