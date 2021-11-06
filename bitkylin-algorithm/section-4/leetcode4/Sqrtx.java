//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
// 示例 1:
//
// 输入: 4
//输出: 2
//
//
// 示例 2:
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
//
// Related Topics 数学 二分查找
// 👍 522 👎 0


package leetcode4;

public class Sqrtx {

    public static void main(String[] args) {
        Solution solution = new Sqrtx().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 注意mid，需要取右mid
     */
    class Solution {
        public int mySqrt(int x) {
            long left = 0;
            long right = (x + 1L) / 2;
            while (left < right) {
                long mid = (right - left + 1) / 2 + left;
                long m = mid * mid;
                if (m <= x) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return (int) left;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {
        public int mySqrt(int x) {
            long left = 0;
            long right = (x + 1L) / 2;
            while (left < right) {
                long mid = (right - left + 1) / 2 + left;
                long m = mid * mid;
                if (m == x) {
                    return (int) mid;
                } else if (m < x) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return (int) left;
        }
    }
}
