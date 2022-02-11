/**
 * <p>给你一个字符串数组，请你将 <strong>字母异位词</strong> 组合在一起。可以按任意顺序返回结果列表。</p>
 *
 * <p><strong>字母异位词</strong> 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>["eat", "tea", "tan", "ate", "nat", "bat"]</code>
 * <strong>输出: </strong>[["bat"],["nat","tan"],["ate","eat","tea"]]</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>[""]</code>
 * <strong>输出: </strong>[[""]]
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>["a"]</code>
 * <strong>输出: </strong>[["a"]]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
 * <li><code>strs[i]</code>&nbsp;仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 995</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        new GroupAnagrams().new Solution().groupAnagrams(new String[]{"bdddddddddd", "bbbbbbbbbbc"});
    }

    /**
     * 方案一：极快
     * 字符数组排序 + hash
     */
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] arr = str.toCharArray();
                Arrays.sort(arr);
                String s = String.valueOf(arr);
                map.computeIfAbsent(s, key -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }

    /**
     * 方案二：较慢
     * 字符数组统计每个字母数量 + hash
     */
    class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                int[] arr = new int[26];
                for (char c : str.toCharArray()) {
                    arr[c - 'a']++;
                }
                String s = Arrays.toString(arr);
                map.computeIfAbsent(s, key -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(map.values());
        }
    }

    /**
     * 方案二：全手工打造版本
     */
    class Solution3 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                String key = calc(str);
                List<String> list = map.get(key);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(key, list);
                }
                list.add(str);
            }
            return new ArrayList<>(map.values());
        }

        private String calc(String str) {
            int[] memo = new int[26];
            for (int i = 0; i < str.length(); i++) {
                memo[str.charAt(i) - 0x61]++;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < memo.length; i++) {
                builder.append(memo[i]).append("-");
            }
            return builder.toString();
        }
    }
}
