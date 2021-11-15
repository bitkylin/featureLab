//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "rat", t = "car"
//输出: false
//
// 说明:
//你可以假设字符串只包含小写字母。
//
// 进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表
// 👍 249 👎 0


package leetcode2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 解法一：
     * 数组模拟hash
     */
    class Solution {
        public boolean isAnagram(String s, String t) {
            int[] arr = new int[26];
            for (char c : s.toCharArray()) {
                arr[c - 'a']++;
            }
            for (char c : t.toCharArray()) {
                arr[c - 'a']--;
            }
            return Arrays.stream(arr).allMatch(value -> value == 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 解法二：
     * hash 法
     */
    class Solution2 {
        class Solution {
            public boolean isAnagram(String s, String t) {
                Map<Character, Integer> map = new HashMap<>();
                for (char c : s.toCharArray()) {
                    map.merge(c, 1, Integer::sum);
                }
                for (char c : t.toCharArray()) {
                    map.merge(c, -1, Integer::sum);
                }
                return map.values().stream().noneMatch(integer -> integer != 0);
            }
        }
    }

    /**
     * 解法三：
     * 排序后比较
     */
    class Solution3 {
        public boolean isAnagram(String s, String t) {
            char[] a = s.toCharArray();
            char[] b = t.toCharArray();
            Arrays.sort(a);
            Arrays.sort(b);
            return Arrays.toString(a).equals(Arrays.toString(b));
        }
    }
}
