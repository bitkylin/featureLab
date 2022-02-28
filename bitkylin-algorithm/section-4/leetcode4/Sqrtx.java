/**
 * <p>给你一个非负整数 <code>x</code> ，计算并返回&nbsp;<code>x</code>&nbsp;的 <strong>算术平方根</strong> 。</p>
 *
 * <p>由于返回类型是整数，结果只保留 <strong>整数部分 </strong>，小数部分将被 <strong>舍去 。</strong></p>
 *
 * <p><strong>注意：</strong>不允许使用任何内置指数函数和算符，例如 <code>pow(x, 0.5)</code> 或者 <code>x ** 0.5</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 4
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 8
 * <strong>输出：</strong>2
 * <strong>解释：</strong>8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 842</li><li>👎 0</li></div>
 */

package leetcode4;

public class Sqrtx {

    public static void main(String[] args) {
        Solution solution = new Sqrtx().new Solution();
    }

    /**
     * mid取偏右的值，当大于x时，right直接限制到mid的左值。
     * 计算mid时始终覆盖x，且最终mid始终是left值。
     * <p>
     * 注：注意溢出场景的处理，使用long类型：right - left + 1，当left为0时，就是right+1了
     */
    class Solution {
        public int mySqrt(int x) {
            long left = 0, right = x;
            while (left < right) {
                long mid = (right - left + 1) / 2 + left;
                if (mid * mid > x) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return (int) left;
        }
    }
}
