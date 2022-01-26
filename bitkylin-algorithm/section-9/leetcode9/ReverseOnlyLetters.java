/**
 * <p>给定一个字符串&nbsp;<code>S</code>，返回&nbsp;&ldquo;反转后的&rdquo;&nbsp;字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。</p>
 *
 * <p>&nbsp;</p>
 *
 * <ol>
 * </ol>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>&quot;ab-cd&quot;
 * <strong>输出：</strong>&quot;dc-ba&quot;
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>&quot;a-bC-dEf-ghIj&quot;
 * <strong>输出：</strong>&quot;j-Ih-gfE-dCba&quot;
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>&quot;Test1ng-Leet=code-Q!&quot;
 * <strong>输出：</strong>&quot;Qedo1ct-eeLg=ntse-T!&quot;
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li><code>S.length &lt;= 100</code></li>
 * <li><code>33 &lt;= S[i].ASCIIcode &lt;= 122</code>&nbsp;</li>
 * <li><code>S</code> 中不包含&nbsp;<code>\</code> or <code>&quot;</code></li>
 * </ol>
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 98</li><li>👎 0</li></div>
 */

package leetcode9;

public class ReverseOnlyLetters {

    public static void main(String[] args) {
        Solution solution = new ReverseOnlyLetters().new Solution();
    }

    class Solution {
        public String reverseOnlyLetters(String s) {
            char[] arr = s.toCharArray();
            for (int left = 0, right = arr.length - 1; left < right; left++, right--) {
                while (left < right && !valid(arr, left)) left++;
                while (left < right && !valid(arr, right)) right--;
                if (left < right) swap(arr, left, right);
            }
            return new String(arr);
        }

        private void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private boolean valid(char[] arr, int i) {
            return (arr[i] >= 'a' && arr[i] <= 'z')
                    || (arr[i] >= 'A' && arr[i] <= 'Z');
        }
    }
}
