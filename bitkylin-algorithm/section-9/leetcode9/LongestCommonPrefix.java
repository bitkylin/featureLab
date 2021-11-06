//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串
// 👍 1341 👎 0


package leetcode9;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            int max = strs[0].length() - 1;
            for (int i = 0; i <= max; i++) {
                char c = strs[0].charAt(i);
                for (String str : strs) {
                    if (i >= str.length()) {
                        return builder.toString();
                    }
                    if (str.charAt(i) != c) {
                        return builder.toString();
                    }
                }
                builder.append(c);
            }
            return builder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
