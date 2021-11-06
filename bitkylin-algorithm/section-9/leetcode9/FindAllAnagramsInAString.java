//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
//
// 说明：
//
//
// 字母异位词指字母相同，但排列不同的字符串。
// 不考虑答案输出的顺序。
//
//
// 示例 1:
//
//
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
//
//
// 示例 2:
//
//
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
//
// Related Topics 哈希表
// 👍 406 👎 0


package leetcode9;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            if (s == null || p == null || p.length() == 0 || s.length() < p.length()) {
                return new ArrayList<>();
            }
            List<Integer> list = new ArrayList<>();
            int[] memo = new int[26];
            for (char c : p.toCharArray()) {
                memo[c - 'a']--;
            }
            for (int i = 0; i < p.length() - 1; i++) {
                memo[s.charAt(i) - 'a']++;
            }
            for (int i = p.length() - 1; i < s.length(); i++) {
                memo[s.charAt(i) - 'a']++;
                if (calc(memo)) {
                    list.add(i - p.length() + 1);
                }
                memo[s.charAt(i - p.length() + 1) - 'a']--;
            }
            return list;
        }

        private boolean calc(int[] memo) {
            for (int val : memo) {
                if (val != 0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
