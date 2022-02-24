/**
 * <p>编写一个函数来查找字符串数组中的最长公共前缀。</p>
 *
 * <p>如果不存在公共前缀，返回空字符串&nbsp;<code>""</code>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["flower","flow","flight"]
 * <strong>输出：</strong>"fl"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["dog","racecar","car"]
 * <strong>输出：</strong>""
 * <strong>解释：</strong>输入不存在公共前缀。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 200</code></li>
 * <li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
 * <li><code>strs[i]</code> 仅由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 1987</li><li>👎 0</li></div>
 */

package leetcode9;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
    }

    class Solution {
        public String longestCommonPrefix(String[] strs) {
            int max = Integer.MAX_VALUE;
            for (String str : strs) {
                max = Math.min(max, str.length() - 1);
            }
            int i = 0;
            while (i <= max) {
                char c = strs[0].charAt(i);
                for (String str : strs) {
                    if (str.charAt(i) != c) {
                        return strs[0].substring(0, i);
                    }
                }
                i++;
            }
            return i > 0 ? strs[0].substring(0, i) : "";
        }
    }

    class Solution2 {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            int max = strs[0].length() - 1;
            for (int i = 0; i <= max; i++) {
                char c = strs[0].charAt(i);
                for (String str : strs) {
                    if (i >= str.length()) {
                        return builder.toString();
                    }
                    if (str.charAt(i) != c) {
                        return builder.toString();
                    }
                }
                builder.append(c);
            }
            return builder.toString();
        }
    }
}
