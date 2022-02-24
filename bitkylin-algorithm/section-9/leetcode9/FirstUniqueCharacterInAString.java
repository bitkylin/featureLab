/**
 * <p>给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>s = &quot;leetcode&quot;
 * 返回 0
 *
 * s = &quot;loveleetcode&quot;
 * 返回 2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong>你可以假定该字符串只包含小写字母。</p>
 * <div><div>Related Topics</div><div><li>队列</li><li>哈希表</li><li>字符串</li><li>计数</li></div></div><br><div><li>👍 497</li><li>👎 0</li></div>
 */

package leetcode9;

public class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        Solution solution = new FirstUniqueCharacterInAString().new Solution();
    }

    class Solution {
        public int firstUniqChar(String s) {
            int[] arr = new int[26];
            for (int i = 0; i < s.length(); i++) {
                arr[s.charAt(i) - 0x61]++;
            }
            for (int i = 0; i < s.length(); i++) {
                if (arr[s.charAt(i) - 0x61] == 1) {
                    return i;
                }
            }
            return -1;
        }
    }

    class Solution2 {
        public int firstUniqChar(String s) {
            if (s == null || s.length() == 0) {
                return -1;
            }
            int[] memo = new int[26];
            char[] arr = s.toCharArray();
            for (char c : arr) {
                memo[c - 'a']++;
            }
            for (int i = 0; i < arr.length; i++) {
                if (memo[arr[i] - 'a'] < 2) {
                    return i;
                }
            }
            return -1;
        }
    }
}

