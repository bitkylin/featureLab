//给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
//
// 说明：不要使用任何内置的库函数，如 sqrt。
//
// 示例 1：
//
// 输入：16
//输出：True
//
// 示例 2：
//
// 输入：14
//输出：False
//
// Related Topics 数学 二分查找
// 👍 170 👎 0


package leetcode4;

public class ValidPerfectSquare {

    public static void main(String[] args) {
        Solution solution = new ValidPerfectSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
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
//leetcode submit region end(Prohibit modification and deletion)

}
