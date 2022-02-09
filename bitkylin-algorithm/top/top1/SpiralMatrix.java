/**
 * <p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵 <code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;" />
 * <pre>
 * <strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * <strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>m == matrix.length</code></li>
 * <li><code>n == matrix[i].length</code></li>
 * <li><code>1 <= m, n <= 10</code></li>
 * <li><code>-100 <= matrix[i][j] <= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>矩阵</li><li>模拟</li></div></div><br><div><li>👍 977</li><li>👎 0</li></div>
 */

package top1;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            int xStart = 0;
            int xEnd = matrix.length - 1;
            int yStart = 0;
            int yEnd = matrix[0].length - 1;
            while (true) {
                if (inValid(xStart, xEnd, yStart, yEnd)) return res;
                for (int y = yStart; y <= yEnd; y++) {
                    res.add(matrix[xStart][y]);
                }
                xStart++;
                if (inValid(xStart, xEnd, yStart, yEnd)) return res;
                for (int x = xStart; x <= xEnd; x++) {
                    res.add(matrix[x][yEnd]);
                }
                yEnd--;
                if (inValid(xStart, xEnd, yStart, yEnd)) return res;
                for (int y = yEnd; y >= yStart; y--) {
                    res.add(matrix[xEnd][y]);
                }
                xEnd--;
                if (inValid(xStart, xEnd, yStart, yEnd)) return res;
                for (int x = xEnd; x >= xStart; x--) {
                    res.add(matrix[x][yStart]);
                }
                yStart++;
            }
        }

        private boolean inValid(int xStart, int xEnd, int yStart, int yEnd) {
            return xStart > xEnd || yStart > yEnd;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
