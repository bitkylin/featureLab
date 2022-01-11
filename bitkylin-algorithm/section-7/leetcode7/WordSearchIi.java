/**
 * <p>给定一个 <code>m x n</code> 二维字符网格 <code>board</code><strong> </strong>和一个单词（字符串）列表 <code>words</code>，找出所有同时在二维网格和字典中出现的单词。</p>
 *
 * <p>单词必须按照字母顺序，通过 <strong>相邻的单元格</strong> 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/07/search1.jpg" style="width: 322px; height: 322px;" />
 * <pre>
 * <strong>输入：</strong>board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * <strong>输出：</strong>["eat","oath"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/07/search2.jpg" style="width: 162px; height: 162px;" />
 * <pre>
 * <strong>输入：</strong>board = [["a","b"],["c","d"]], words = ["abcb"]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == board.length</code></li>
 * <li><code>n == board[i].length</code></li>
 * <li><code>1 <= m, n <= 12</code></li>
 * <li><code>board[i][j]</code> 是一个小写英文字母</li>
 * <li><code>1 <= words.length <= 3 * 10<sup>4</sup></code></li>
 * <li><code>1 <= words[i].length <= 10</code></li>
 * <li><code>words[i]</code> 由小写英文字母组成</li>
 * <li><code>words</code> 中的所有字符串互不相同</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字典树</li><li>数组</li><li>字符串</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 598</li><li>👎 0</li></div>
 */

package leetcode7;

import java.util.*;

public class WordSearchIi {

    public static void main(String[] args) {
        new WordSearchIi().new Solution().findWords(
                new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"});
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS + 回溯
     * 时间复杂度高
     * 注：回溯时注意不要漏了恢复当前层，一定要恢复全
     */
    class Solution {

        private int[][] pointList = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0},};

        public List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            for (String word : words) {
                String wordRes = calc(board, res, word);
                if (wordRes != null) {
                    res.add(wordRes);
                }
            }
            return res;
        }

        private String calc(char[][] board, List<String> res, String word) {
            for (int x = 0; x <= board.length; x++) {
                for (int y = 0; y <= board[0].length; y++) {
                    if (solve(board, word, x, y, 0)) {
                        return word;
                    }
                }
            }
            return null;
        }

        private boolean solve(char[][] board, String word, int x, int y, int level) {
            int xMax = board.length - 1;
            int yMax = board[0].length - 1;
            if (level >= word.length()) {
                return true;
            }
            if (x < 0 || y < 0 || x > xMax || y > yMax
                    || word.charAt(level) != board[x][y]) {
                return false;
            }
            board[x][y] = 0;
            for (int[] p : pointList) {
                if (solve(board, word, x + p[0], y + p[1], level + 1)) {
                    board[x][y] = word.charAt(level);
                    return true;
                }
            }
            board[x][y] = word.charAt(level);
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 字典树
     * 时间复杂度高
     * 暂不研究了
     */
    class Solution1 {

        int[][] addr = new int[][]{
                {0, 1},
                {0, -1},
                {-1, 0},
                {1, 0},
        };

        public List<String> findWords(char[][] board, String[] words) {
            Set<String> res = new HashSet<>();
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }

            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    dfs(x, y, "", board, res, trie);
                }
            }
            return new ArrayList<>(res);
        }

        private void dfs(int x, int y, String str, char[][] board, Set<String> res, Trie trie) {
            if (trie == null) {
                return;

            }
            if (trie.end) {
                res.add(str);
            }
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == 0) {
                return;
            }
            Trie node = trie.map.get(board[x][y]);
            if (node == null) {
                return;
            }
            trie = node;
            char backup = board[x][y];
            board[x][y] = 0;
            for (int[] arr : addr) {
                dfs(x + arr[0], y + arr[1], str + backup, board, res, trie);
            }
            board[x][y] = backup;
        }

        /**
         * 一定要注意：操作的对象应来自于获取到的Trie对象，而不能直接用this
         */
        public class Trie {

            Map<Character, Trie> map = new HashMap<>();
            boolean end;

            public void insert(String s) {
                Trie trie = this;
                for (char c : s.toCharArray()) {
                    if (trie.map.get(c) == null) {
                        trie.map.put(c, new Trie());
                    }
                    trie = trie.map.get(c);
                }
                trie.end = true;
            }
        }
    }
}

