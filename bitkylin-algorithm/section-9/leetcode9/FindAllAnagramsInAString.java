/**
 * <p>给定两个字符串&nbsp;<code>s</code>&nbsp;和 <code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有&nbsp;<code>p</code><strong>&nbsp;</strong>的&nbsp;<strong>异位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>
 *
 * <p><strong>异位词 </strong>指由相同字母重排列形成的字符串（包括相同的字符串）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "cbaebabacd", p = "abc"
 * <strong>输出: </strong>[0,6]
 * <strong>解释:</strong>
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * </pre>
 *
 * <p><strong>&nbsp;示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abab", p = "ab"
 * <strong>输出: </strong>[0,1,2]
 * <strong>解释:</strong>
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>s</code>&nbsp;和&nbsp;<code>p</code>&nbsp;仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 759</li><li>👎 0</li></div>
 */

package leetcode9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        new FindAllAnagramsInAString().new Solution().findAnagrams("baa", "aa");
    }

    /**
     * 基于数组的计数法
     * 注意：数据准备阶段、数据验证阶段，计数方式证号相反。「增加、减少相反」
     */
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            if (p.length() > s.length()) {
                return res;
            }
            int[] memo = new int[26];
            for (char c : p.toCharArray()) {
                memo[c - 'a']--;
            }
            for (int i = 0; i < p.length(); i++) {
                memo[s.charAt(i) - 'a']++;
            }
            calc(res, memo, 0);
            for (int i = 0, j = p.length(); j < s.length(); i++, j++) {
                memo[s.charAt(i) - 'a']--;
                memo[s.charAt(j) - 'a']++;
                calc(res, memo, i + 1);
            }
            return res;
        }

        private void calc(List<Integer> res, int[] memo, int i) {
            for (int val : memo) {
                if (val != 0) {
                    return;
                }
            }
            res.add(i);
        }
    }

    /**
     * 基于Map的计数法
     */
    class Solution2 {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            if (p.length() > s.length()) {
                return res;
            }
            Map<Character, Integer> map = new HashMap<>();
            for (char c : p.toCharArray()) {
                add(map, c);
            }
            for (int i = 0; i < p.length(); i++) {
                remove(map, s.charAt(i));
            }
            if (map.isEmpty()) {
                res.add(0);
            }
            for (int i = 0, j = p.length(); j < s.length(); i++, j++) {
                add(map, s.charAt(i));
                remove(map, s.charAt(j));
                if (map.isEmpty()) {
                    res.add(i + 1);
                }
            }
            return res;
        }

        private void add(Map<Character, Integer> map, char c) {
            Integer val = map.get(c);
            val = val == null ? 1 : val + 1;
            if (val == 0) map.remove(c);
            else map.put(c, val);
        }

        private void remove(Map<Character, Integer> map, char c) {
            Integer val = map.get(c);
            val = val == null ? -1 : val - 1;
            if (val == 0) map.remove(c);
            else map.put(c, val);
        }
    }
}
