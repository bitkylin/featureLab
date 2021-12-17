/**
 * <p>给定一个 <strong>正整数</strong> <code>num</code> ，编写一个函数，如果 <code>num</code> 是一个完全平方数，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>
 *
 * <p><strong>进阶：不要</strong> 使用任何内置的库函数，如  <code>sqrt</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>num = 16
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>num = 14
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= num <= 2^31 - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 330</li><li>👎 0</li></div>
 */

package leetcode4;

public class ValidPerfectSquare {

    public static void main(String[] args) {
        Solution solution = new ValidPerfectSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPerfectSquare(int num) {
            long left = 0, right = num;
            while (left < right) {
                long mid = (right - left + 1) / 2 + left;
                long val = mid * mid;
                if (val == num) {
                    return true;
                } else if (val > num) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        public boolean isPerfectSquare(int num) {
            long left = 0;
            long right = (num + 1) / 2;
            while (left < right) {
                long mid = (right - left + 1) / 2 + left;
                long m = mid * mid;
                if (m <= num) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return left * left == num;
        }
    }
}
