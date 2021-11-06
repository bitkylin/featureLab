//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
//
//
// 示例:
//
// 输入:
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"]
//
// 说明:
//你可以假设所有输入都由小写字母 a-z 组成。
//
// 提示:
//
//
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
//
// Related Topics 字典树 回溯算法
// 👍 267 👎 0


package leetcode7;

import java.util.*;

public class WordSearchIi {

    public static void main(String[] args) {
        new WordSearchIi().new Solution().findWords(new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                }, new String[]{"oath", "pea", "eat", "rain"}
        );
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 字典树
     * 时间复杂度高
     */
    class Solution {

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
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * DFS + 回溯
     * 时间复杂度高
     */
    class Solution2 {

        int[][] addr = new int[][]{
                {0, 1},
                {0, -1},
                {-1, 0},
                {1, 0},
        };

        public List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if (calc(board, words[i])) {
                    res.add(words[i]);
                }
            }
            return res;
        }

        private boolean calc(char[][] board, String word) {
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    if (dfs(x, y, 0, board, word)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int x, int y, int i, char[][] board, String word) {
            if (i == word.length()) {
                return true;
            }
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == 0) {
                return false;
            }
            if (board[x][y] != word.charAt(i)) {
                return false;
            }
            char backup = board[x][y];
            board[x][y] = 0;
            for (int[] arr : addr) {
                if (dfs(x + arr[0], y + arr[1], i + 1, board, word)) {
                    board[x][y] = backup;
                    return true;
                }
            }
            board[x][y] = backup;
            return false;
        }
    }

}

