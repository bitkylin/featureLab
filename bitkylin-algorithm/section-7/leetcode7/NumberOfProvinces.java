/**
 * <div class="original__bRMd">
 * <div>
 * <p>有 <code>n</code> 个城市，其中一些彼此相连，另一些没有相连。如果城市 <code>a</code> 与城市 <code>b</code> 直接相连，且城市 <code>b</code> 与城市 <code>c</code> 直接相连，那么城市 <code>a</code> 与城市 <code>c</code> 间接相连。</p>
 *
 * <p><strong>省份</strong> 是一组直接或间接相连的城市，组内不含其他没有相连的城市。</p>
 *
 * <p>给你一个 <code>n x n</code> 的矩阵 <code>isConnected</code> ，其中 <code>isConnected[i][j] = 1</code> 表示第 <code>i</code> 个城市和第 <code>j</code> 个城市直接相连，而 <code>isConnected[i][j] = 0</code> 表示二者不直接相连。</p>
 *
 * <p>返回矩阵中 <strong>省份</strong> 的数量。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/graph1.jpg" style="width: 222px; height: 142px;" />
 * <pre>
 * <strong>输入：</strong>isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/graph2.jpg" style="width: 222px; height: 142px;" />
 * <pre>
 * <strong>输入：</strong>isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 200</code></li>
 * <li><code>n == isConnected.length</code></li>
 * <li><code>n == isConnected[i].length</code></li>
 * <li><code>isConnected[i][j]</code> 为 <code>1</code> 或 <code>0</code></li>
 * <li><code>isConnected[i][i] == 1</code></li>
 * <li><code>isConnected[i][j] == isConnected[j][i]</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>图</li></div></div><br><div><li>👍 681</li><li>👎 0</li></div>
 */

package leetcode7;

public class NumberOfProvinces {

    public static void main(String[] args) {
        Solution solution = new NumberOfProvinces().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * DFS + 回溯
     */
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int res = 0;
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[i][i] == 1) {
                    res++;
                    solve(isConnected, i, i);
                }
            }
            return res;
        }

        private void solve(int[][] isConnected, int i, int j) {
            isConnected[j][j] = 0;
            isConnected[i][j] = 0;
            isConnected[j][i] = 0;
            for (int k = 0; k < isConnected.length; k++) {
                if (isConnected[j][k] == 1) {
                    solve(isConnected, j, k);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
