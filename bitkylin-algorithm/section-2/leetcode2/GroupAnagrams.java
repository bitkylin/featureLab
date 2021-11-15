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
//leetcode submit region end(Prohibit modification and deletion)

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
}
