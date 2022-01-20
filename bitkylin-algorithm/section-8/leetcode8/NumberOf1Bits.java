/**
 * <p>编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F" target="_blank">汉明重量</a>）。</p>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li>
 * <li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在上面的 <strong>示例 3</strong> 中，输入表示有符号整数 <code>-3</code>。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>00000000000000000000000000001011
 * <strong>输出：</strong>3
 * <strong>解释：</strong>输入的二进制串 <code><strong>00000000000000000000000000001011</strong> 中，共有三位为 '1'。</code>
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>00000000000000000000000010000000
 * <strong>输出：</strong>1
 * <strong>解释：</strong>输入的二进制串 <strong>00000000000000000000000010000000</strong> 中，共有一位为 '1'。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>11111111111111111111111111111101
 * <strong>输出：</strong>31
 * <strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 中，共有 31 位为 '1'。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>输入必须是长度为 <code>32</code> 的 <strong>二进制串</strong> 。</li>
 * </ul>
 *
 * <ul>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶</strong>：</p>
 *
 * <ul>
 * <li>如果多次调用这个函数，你将如何优化你的算法？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li></div></div><br><div><li>👍 409</li><li>👎 0</li></div>
 */

package leetcode8;

public class NumberOf1Bits {

    public static void main(String[] args) {
        Solution solution = new NumberOf1Bits().new Solution();
    }

    /**
     * 最优解
     * 最全解法题解：https://leetcode-cn.com/problems/number-of-1-bits/solution/javade-17chong-jie-fa-by-sdwwld/
     */
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res++;
                n = n & (n - 1);
            }
            return res;
        }
    }

    public class Solution2 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res += n & 1;
                n = n >>> 1;
            }
            return res;
        }
    }

    public class Solution3 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) != 0) {
                    res++;
                }
            }
            return res;
        }
    }

    //  ----- 后面的不用看了 -----

    /**
     * 由于n可能小于0，n % 2 可能等于-1或1，需注意
     */
    public class Solution4 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                if ((n % 2) != 0) {
                    res++;
                }
                n = n >>> 1;
            }

            return res;
        }
    }

    public class Solution5 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            char[] arr = Integer.toBinaryString(n).toCharArray();
            int res = 0;
            for (char c : arr) {
                if (c == '1') {
                    res++;
                }
            }
            return res;
        }
    }
}
