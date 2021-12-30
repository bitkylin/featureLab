/**
 * <p>一条包含字母 <code>A-Z</code> 的消息通过以下映射进行了 <strong>编码</strong> ：</p>
 *
 * <pre>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * </pre>
 *
 * <p>要 <strong>解码</strong> 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，<code>"11106"</code> 可以映射为：</p>
 *
 * <ul>
 * <li><code>"AAJF"</code> ，将消息分组为 <code>(1 1 10 6)</code></li>
 * <li><code>"KJF"</code> ，将消息分组为 <code>(11 10 6)</code></li>
 * </ul>
 *
 * <p>注意，消息不能分组为  <code>(1 11 06)</code> ，因为 <code>"06"</code> 不能映射为 <code>"F"</code> ，这是由于 <code>"6"</code> 和 <code>"06"</code> 在映射中并不等价。</p>
 *
 * <p>给你一个只含数字的 <strong>非空 </strong>字符串 <code>s</code> ，请计算并返回 <strong>解码</strong> 方法的 <strong>总数</strong> 。</p>
 *
 * <p>题目数据保证答案肯定是一个 <strong>32 位</strong> 的整数。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "12"
 * <strong>输出：</strong>2
 * <strong>解释：</strong>它可以解码为 "AB"（1 2）或者 "L"（12）。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "226"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "0"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "06"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>"06" 不能映射到 "F" ，因为字符串含有前导 0（<code>"6"</code> 和 <code>"06"</code> 在映射中并不等价）。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 100</code></li>
 * <li><code>s</code> 只包含数字，并且可能包含前导零。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 1035</li><li>👎 0</li></div>
 */

package leetcode9;

public class DecodeWays {

    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int numDecodings(String s) {
            int[] dp = new int[s.length()];
            if (s.length() >= 1) dp[0] = solve(s, 0, 0);
            if (s.length() >= 2) dp[1] = solve(s, 1, 1) * dp[0] + solve(s, 0, 1);
            if (s.length() <= 2) return dp[s.length() - 1];
            for (int i = 2; i < s.length(); i++) {
                dp[i] = dp[i - 1] * solve(s, i, i)
                        + dp[i - 2] * solve(s, i - 1, i);
            }
            return dp[s.length() - 1];
        }

        public int numDecodings2(String s) {
            if (s.length() == 1) return solve(s, 0, 0);
            char[] arr = s.toCharArray();
            int[] dp = new int[arr.length];
            dp[0] = solve(s, 0, 0);
            dp[1] = dp[0] * solve(s, 1, 1) + solve(s, 0, 1);
            for (int i = 2; i < arr.length; i++) {
                dp[i] = dp[i - 2] * solve(s, i - 1, i)
                        + dp[i - 1] * solve(s, i, i);
            }
            return dp[arr.length - 1];
        }

        /**
         * 三种反序列化方法都可以
         */
        private int solve(String s, int left, int right) {
            if (left == right) {
                int val = s.charAt(right) - 0x30;
                return val >= 1 && val <= 9 ? 1 : 0;
            }
            int val = (s.charAt(left) - 0x30) * 10 + (s.charAt(right) - 0x30);
            return val >= 10 && val <= 26 ? 1 : 0;
        }

        private int solve2(String s, int left, int right) {
            int val = Integer.parseInt(s.substring(left, right + 1));
            if (left == right) {
                return val >= 1 && val <= 9 ? 1 : 0;
            }
            return val >= 10 && val <= 26 ? 1 : 0;
        }

        private int solve3(String s, int left, int right) {
            if (s.charAt(left) == '0') {
                return 0;
            }
            int val = Integer.parseInt(s.substring(left, right + 1));
            if (val >= 1 && val <= 26) {
                return 1;
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
