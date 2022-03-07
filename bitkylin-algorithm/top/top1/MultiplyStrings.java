/**
 * <p>给定两个以字符串形式表示的非负整数&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>，返回&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;的乘积，它们的乘积也表示为字符串形式。</p>
 *
 * <p><strong>注意：</strong>不能使用任何内置的 BigInteger 库或直接将输入转换为整数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num1 = "2", num2 = "3"
 * <strong>输出:</strong> "6"</pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> num1 = "123", num2 = "456"
 * <strong>输出:</strong> "56088"</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= num1.length, num2.length &lt;= 200</code></li>
 * <li><code>num1</code>&nbsp;和 <code>num2</code>&nbsp;只能由数字组成。</li>
 * <li><code>num1</code>&nbsp;和 <code>num2</code>&nbsp;都不包含任何前导零，除了数字0本身。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>字符串</li><li>模拟</li></div></div><br><div><li>👍 852</li><li>👎 0</li></div>
 */

package top1;

public class MultiplyStrings {

    public static void main(String[] args) {
        Solution solution = new MultiplyStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            int[] res = new int[num1.length() + num2.length()];
            for (int i = num1.length() - 1; i >= 0; i--) {
                int n1 = num1.charAt(i) - '0';
                for (int j = num2.length() - 1; j >= 0; j--) {
                    int n2 = num2.charAt(j) - '0';
                    int sum = (res[i + j + 1] + n1 * n2);
                    res[i + j + 1] = sum % 10;
                    res[i + j] += sum / 10;
                }
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i == 0 && res[i] == 0) continue;
                result.append(res[i]);
            }
            return result.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
