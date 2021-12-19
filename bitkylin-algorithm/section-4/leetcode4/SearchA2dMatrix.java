/**
 * <p>编写一个高效的算法来判断 <code>m x n</code> 矩阵中，是否存在一个目标值。该矩阵具有如下特性：</p>
 *
 * <ul>
 * <li>每行中的整数从左到右按升序排列。</li>
 * <li>每行的第一个整数大于前一行的最后一个整数。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/mat2.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 <= m, n <= 100</code></li>
 * <li><code>-10<sup>4</sup> <= matrix[i][j], target <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>矩阵</li></div></div><br><div><li>👍 547</li><li>👎 0</li></div>
 */

package leetcode4;

public class SearchA2dMatrix {

    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 把矩阵当做一位数组，直接二分查找
     */
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int len = matrix[0].length;
            int left = 0, right = matrix.length * len - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (matrix[mid / len][mid % len] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return matrix[left / len][left % len] == target;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 对row二分查找，right可以左移，left不能主动右移，所以mid必须靠右
     * 对单行二分查找，mid靠左靠右均可
     */
    class Solution1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int left = 0, right = matrix.length - 1;
            while (left < right) {
                int mid = (right - left + 1) / 2 + left;
                if (matrix[mid][0] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            int[] arr = matrix[left];
            left = 0;
            right = arr.length - 1;
            while (left < right) {
                int mid = (right - left + 1) / 2 + left;
                if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            return arr[left] == target;
        }
    }
}
