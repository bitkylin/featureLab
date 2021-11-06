//一条包含字母 A-Z 的消息通过以下方式进行了编码：
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。
//
// 题目数据保证答案肯定是一个 32 位的整数。
//
//
//
// 示例 1：
//
// 输入："12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
//
//
// 示例 2：
//
// 输入："226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
//
//
// 示例 3：
//
// 输入：s = "0"
//输出：0
//
//
// 示例 4：
//
// 输入：s = "1"
//输出：1
//
//
// 示例 5：
//
// 输入：s = "2"
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 100
// s 只包含数字，并且可以包含前导零。
//
// Related Topics 字符串 动态规划
// 👍 539 👎 0


package leetcode6;

public class DecodeWays {

    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
            if (s.length() <= 1) {
                return calc(0, 0, s);
            }
            int[] dp = new int[s.length()];
            dp[0] = calc(0, 0, s);
            dp[1] = dp[0] * calc(1, 1, s) + calc(0, 1, s);
            for (int i = 2; i < s.length(); i++) {
                dp[i] = dp[i - 2] * calc(i - 1, i, s)
                        + dp[i - 1] * calc(i, i, s);
            }
            return dp[s.length() - 1];
        }

        private int calc(int left, int right, String s) {
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
