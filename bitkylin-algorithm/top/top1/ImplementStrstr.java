/**
 * <p>实现 <a href="https://baike.baidu.com/item/strstr/811469" target="_blank">strStr()</a> 函数。</p>
 *
 * <p>给你两个字符串 <code>haystack</code> 和 <code>needle</code> ，请你在 <code>haystack</code> 字符串中找出 <code>needle</code> 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  <code>-1</code><strong> </strong>。</p>
 *
 * <p> </p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <p>当 <code>needle</code> 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。</p>
 *
 * <p>对于本题而言，当 <code>needle</code> 是空字符串时我们应当返回 0 。这与 C 语言的 <a href="https://baike.baidu.com/item/strstr/811469" target="_blank">strstr()</a> 以及 Java 的 <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#indexOf(java.lang.String)" target="_blank">indexOf()</a> 定义相符。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>haystack = "hello", needle = "ll"
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>haystack = "aaaaa", needle = "bba"
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>haystack = "", needle = ""
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= haystack.length, needle.length <= 5 * 10<sup>4</sup></code></li>
 * <li><code>haystack</code> 和 <code>needle</code> 仅由小写英文字符组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li><li>字符串匹配</li></div></div><br><div><li>👍 1270</li><li>👎 0</li></div>
 */

package top1;

public class ImplementStrstr {

    public static void main(String[] args) {
        new ImplementStrstr().new Solution().strStr("mississippi", "mississippi");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            if (needle.length() == 0) {
                return 0;
            }
            for (int i = 0; i <= haystack.length() - needle.length(); i++) {
                if (solve(haystack, needle, i)) {
                    return i;
                }
            }
            return -1;
        }

        private boolean solve(String haystack, String needle, int i) {
            for (int j = 0; j < needle.length(); j++, i++) {
                if (haystack.charAt(i) != needle.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
