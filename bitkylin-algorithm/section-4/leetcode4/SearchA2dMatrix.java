//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
//
// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。
//
//
// 示例 1:
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
//
//
// 示例 2:
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false
// Related Topics 数组 二分查找
// 👍 254 👎 0


package leetcode4;

public class SearchA2dMatrix {

    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            long left = 0;
            long right = matrix.length - 1;
            while (left < right) {
                long mid = left + (right - left + 1) / 2;
                if (matrix[(int) mid][0] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            int[] arr = matrix[(int) left];
            left = 0;
            right = arr.length - 1;
            while (left < right) {
                long mid = left + (right - left + 1) / 2;
                if (arr[(int) mid] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return arr[(int) left] == target;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
