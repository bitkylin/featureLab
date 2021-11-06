//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例:
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//
// 说明：
//
//
// 所有输入均为小写字母。
// 不考虑答案输出的顺序。
//
// Related Topics 哈希表 字符串
// 👍 464 👎 0


package leetcode2;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 字符数组排序 + hash
     */
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {
                char[] array = strs[i].toCharArray();
                Arrays.sort(array);
                String s = String.valueOf(array);
                List<String> list = map.computeIfAbsent(s, key -> new ArrayList<>());
                list.add(strs[i]);
            }
            return new ArrayList<>(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 字符数组统计每个字母数量 + hash
     */
    class Solution2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {
                int[] array = new int[26];
                for (char c : strs[i].toCharArray()) {
                    array[c - 'a']++;
                }
                String s = Arrays.toString(array);
                List<String> list = map.computeIfAbsent(s, key -> new ArrayList<>());
                list.add(strs[i]);
            }
            return new ArrayList<>(map.values());
        }
    }
}
