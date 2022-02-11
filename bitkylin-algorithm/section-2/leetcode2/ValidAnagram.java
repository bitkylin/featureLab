/**
 * <p>给定两个字符串 <code><em>s</em></code> 和 <code><em>t</em></code> ，编写一个函数来判断 <code><em>t</em></code> 是否是 <code><em>s</em></code> 的字母异位词。</p>
 *
 * <p><strong>注意：</strong>若 <code><em>s</em></code> 和 <code><em>t</em></code><em> </em>中每个字符出现的次数都相同，则称 <code><em>s</em></code> 和 <code><em>t</em></code><em> </em>互为字母异位词。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <em>s</em> = "anagram", <em>t</em> = "nagaram"
 * <strong>输出:</strong> true
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> <em>s</em> = "rat", <em>t</em> = "car"
 * <strong>输出: </strong>false</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length, t.length <= 5 * 10<sup>4</sup></code></li>
 * <li><code>s</code> 和 <code>t</code> 仅包含小写字母</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶: </strong>如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 486</li><li>👎 0</li></div>
 */

package leetcode2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }

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

    class Solution1_2 {
        public boolean isAnagram(String s, String t) {
            int[] memo = new int[26];
            for (char c : s.toCharArray()) {
                memo[c - 0x61]++;
            }
            for (char c : t.toCharArray()) {
                memo[c - 0x61]--;
            }
            for (int i = 0; i < memo.length; i++) {
                if (memo[i] != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 只需要关注前两个解法

    /**
     * 解法二：
     * hash 法
     */
    class Solution2_1 {
        public boolean isAnagram(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                Integer val = map.get(c);
                map.put(c, val == null ? 1 : val + 1);
            }
            for (char c : t.toCharArray()) {
                Integer val = map.get(c);
                map.put(c, val == null ? -1 : val - 1);
            }
            return map.values().stream().allMatch(val -> val == 0);
        }
    }

    /**
     * 解法二「优化」：
     * hash 法
     */
    class Solution2_2 {
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
