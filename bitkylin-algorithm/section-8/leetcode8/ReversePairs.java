/**
 * <p>给定一个数组&nbsp;<code>nums</code>&nbsp;，如果&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i] &gt; 2*nums[j]</code>&nbsp;我们就将&nbsp;<code>(i, j)</code>&nbsp;称作一个<strong><em>重要翻转对</em></strong>。</p>
 *
 * <p>你需要返回给定数组中的重要翻转对的数量。</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入</strong>: [1,3,2,3,1]
 * <strong>输出</strong>: 2
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入</strong>: [2,4,3,5,1]
 * <strong>输出</strong>: 3
 * </pre>
 *
 * <p><strong>注意:</strong></p>
 *
 * <ol>
 * <li>给定数组的长度不会超过<code>50000</code>。</li>
 * <li>输入数组中的所有数字都在32位整数的表示范围内。</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>树状数组</li><li>线段树</li><li>数组</li><li>二分查找</li><li>分治</li><li>有序集合</li><li>归并排序</li></div></div><br><div><li>👍 326</li><li>👎 0</li></div>
 */

package leetcode8;

public class ReversePairs {

    public static void main(String[] args) {
        new ReversePairs().new Solution().reversePairs(new int[]{1, 3, 2, 3, 1});
        System.out.println();
    }

    /**
     * 先写出归并排序，上面加两笔就行了
     */
    class Solution {

        public int reversePairs(int[] nums) {
            return solve(nums, 0, nums.length - 1, new int[nums.length]);
        }

        private int solve(int[] nums, int left, int right, int[] tempArr) {
            int res = 0;
            if (right - left <= 1) {
                res = calc(nums, left, left, right);
                sort(nums, left, right);
                return res;
            }
            int mid = (right - left) / 2 + left;
            res += solve(nums, left, mid, tempArr);
            res += solve(nums, mid + 1, right, tempArr);
            res += calc(nums, left, mid, right);
            merge(nums, left, mid, right, tempArr);
            return res;
        }

        private int calc(int[] nums, int left, int mid, int right) {
            int res = 0;
            int i = left;
            int j = mid + 1;
            while (i <= mid && j <= right) {
                if (nums[i] / 2.0 > nums[j]) {
                    res += mid - i + 1;
                    j++;
                } else {
                    i++;
                }
            }
            return res;
        }

        private void sort(int[] nums, int left, int right) {
            if (nums[left] > nums[right]) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }

        private void merge(int[] nums, int left, int mid, int right, int[] tempArr) {
            int i = left;
            int k = left;
            int j = mid + 1;
            while (i <= mid && j <= right) {
                if (nums[i] < nums[j]) {
                    tempArr[k++] = nums[i++];
                } else {
                    tempArr[k++] = nums[j++];
                }
            }
            while (i <= mid) {
                tempArr[k++] = nums[i++];
            }
            while (j <= right) {
                tempArr[k++] = nums[j++];
            }
            while (left <= right) {
                nums[left] = tempArr[left++];
            }
        }
    }
}

