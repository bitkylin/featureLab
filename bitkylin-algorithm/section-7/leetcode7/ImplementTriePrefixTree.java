//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
//
// 示例:
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");
//trie.search("app");     // 返回 true
//
// 说明:
//
//
// 你可以假设所有的输入都是由小写字母 a-z 构成的。
// 保证所有输入均为非空字符串。
//
// Related Topics 设计 字典树
// 👍 439 👎 0


package leetcode7;

public class ImplementTriePrefixTree {

    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {

        Trie[] arr = new Trie[26];
        boolean end;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            Trie trie = this;
            for (int i = 0; i < word.length(); i++) {
                int j = word.charAt(i) - 'a';
                if (trie.arr[j] == null) {
                    trie.arr[j] = new Trie();
                }
                trie = trie.arr[j];
            }
            trie.end = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie trie = searchPrefix(word);
            return trie != null && trie.end;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            if (prefix == null || prefix.length() == 0) {
                return null;
            }
            Trie trie = this;
            for (int i = 0; i < prefix.length(); i++) {
                int j = prefix.charAt(i) - 'a';
                trie = trie.arr[j];
                if (trie == null) {
                    return null;
                }
            }
            return trie;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
