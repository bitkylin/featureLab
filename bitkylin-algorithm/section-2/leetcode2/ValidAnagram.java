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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

public class ValidAnagram {

    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 数组模拟hash
     */
    class Solution {
        public boolean isAnagram(String s, String t) {
            int[] memo = new int[26];
            for (char c : s.toCharArray()) {
                memo[c - 'a']++;
            }
            for (char c : t.toCharArray()) {
                memo[c - 'a']--;
            }
            for (int i : memo) {
                if (i != 0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * hash 法
     */
    class Solution2 {
        public boolean isAnagram(String s, String t) {
            Map<Character, LongAdder> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                LongAdder longAdder = map.computeIfAbsent(c, key -> new LongAdder());
                longAdder.increment();
            }
            for (char c : t.toCharArray()) {
                LongAdder longAdder = map.get(c);
                if (longAdder == null) {
                    return false;
                }
                longAdder.decrement();
            }
            if (map.values().stream().anyMatch(a -> a.intValue() != 0)) {
                return false;
            }
            return true;
        }
    }

}
