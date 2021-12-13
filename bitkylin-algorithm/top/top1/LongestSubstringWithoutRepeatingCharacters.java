/**
 * <p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的 <strong>最长子串 </strong>的长度。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abcabcbb"
 * <strong>输出: </strong>3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"，所以其</code>长度为 3。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "bbbbb"
 * <strong>输出: </strong>1
 * <strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "pwwkew"
 * <strong>输出: </strong>3
 * <strong>解释: </strong>因为无重复字符的最长子串是 <code>"wke"</code>，所以其长度为 3。
 * 请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code> 是一个<em>子序列，</em>不是子串。
 * </pre>
 *
 * <p><strong>示例 4:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = ""
 * <strong>输出: </strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length <= 5 * 10<sup>4</sup></code></li>
 * <li><code>s</code> 由英文字母、数字、符号和空格组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 6550</li><li>👎 0</li></div>
 */

package top1;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            char[] arr = s.toCharArray();
            int res = 0;
            Set<Character> set = new HashSet<>();
            for (int i = 0, j = 0; i < arr.length; i++) {
                while (set.contains(arr[i])) {
                    set.remove(arr[j++]);
                }
                set.add(arr[i]);
                res = Math.max(res, i - j + 1);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}