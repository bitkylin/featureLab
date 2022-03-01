/**
 * <p>给定一个 <em>n&nbsp;</em>×&nbsp;<em>n</em> 的二维矩阵&nbsp;<code>matrix</code> 表示一个图像。请你将图像顺时针旋转 90 度。</p>
 *
 * <p>你必须在<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a></strong> 旋转图像，这意味着你需要直接修改输入的二维矩阵。<strong>请不要 </strong>使用另一个矩阵来旋转图像。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="height: 188px; width: 500px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <strong>输出：</strong>[[7,4,1],[8,5,2],[9,6,3]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="height: 201px; width: 500px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * <strong>输出：</strong>[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == matrix.length == matrix[i].length</code></li>
 * <li><code>1 &lt;= n &lt;= 20</code></li>
 * <li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>矩阵</li></div></div><br><div><li>👍 1184</li><li>👎 0</li></div>
 */

package top1;

public class RotateImage {

    public static void main(String[] args) {
        new RotateImage().new Solution().rotate(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 两次翻转
     */
    class Solution {
        public void rotate(int[][] matrix) {
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y <  x; y++) {
                    swap(matrix, x, y);
                }
            }
            for (int left = 0, right = matrix[0].length - 1; left < right; left++, right--) {
                for (int x = 0; x < matrix.length; x++) {
                    int temp = matrix[x][left];
                    matrix[x][left] = matrix[x][right];
                    matrix[x][right] = temp;
                }
            }
        }

        private void swap(int[][] matrix, int x, int y) {
            int temp = matrix[x][y];
            matrix[x][y] = matrix[y][x];
            matrix[y][x] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
