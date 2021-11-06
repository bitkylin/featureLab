//给定一个字符串，逐个翻转字符串中的每个单词。
//
// 说明：
//
//
// 无空格字符构成一个 单词 。
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
//
//
//
// 示例 1：
//
// 输入："the sky is blue"
//输出："blue is sky the"
//
//
// 示例 2：
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
//
//
// 示例 3：
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
//
// 示例 4：
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
//
//
// 示例 5：
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 包含英文大小写字母、数字和空格 ' '
// s 中 至少存在一个 单词
//
//
//
//
//
//
//
// 进阶：
//
//
// 请尝试使用 O(1) 额外空间复杂度的原地解法。
//
// Related Topics 字符串
// 👍 242 👎 0


package leetcode9;

import java.util.*;
import java.util.stream.Collectors;

public class ReverseWordsInAString {

    public static void main(String[] args) {
        String[] a = "  dfd  df".split(" ");
        Solution solution = new ReverseWordsInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 自行编写转换方法，后到前遍历，优化行数
     */
    class Solution {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] arr = s.toCharArray();
            int idx = arr.length - 1;
            StringBuilder builder = new StringBuilder();
            while (idx >= 0) {
                while (idx >= 0 && arr[idx] == ' ') {
                    idx--;
                }
                int end = idx;
                while (idx >= 0 && arr[idx] != ' ') {
                    idx--;
                }
                int start = idx + 1;
                if (end >= 0) {
                    builder.append(s, start, end + 1).append(" ");
                }
            }
            return builder.toString().trim();
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 系统库函数
     */
    class Solution2 {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            List<String> list = Arrays.stream(s.split(" "))
                    .filter(item -> item.length() > 0)
                    .collect(Collectors.toList());
            Collections.reverse(list);
            return String.join(" ", list);
        }
    }

    /**
     * 自行编写转换方法，前到后遍历
     */
    class Solution3 {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] arr = s.toCharArray();
            int max = arr.length - 1;
            Deque<String> deque = new ArrayDeque<>();
            for (int i = 0; i <= max; ) {
                while (i <= max && arr[i] == ' ') {
                    i++;
                }
                int start = i;
                while (i <= max && arr[i] != ' ') {
                    i++;
                }
                int end = i;
                if (start > max) {
                    continue;
                }
                deque.addFirst(s.substring(start, end));
            }
            return String.join(" ", deque);
        }
    }

    /**
     * 自行编写转换方法，从后到前遍历
     */
    class Solution4 {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] arr = s.toCharArray();
            int idx = arr.length - 1;
            StringBuilder builder = new StringBuilder();
            while (idx >= 0) {
                while (idx >= 0 && arr[idx] == ' ') {
                    idx--;
                }
                int end = idx;
                while (idx >= 0 && arr[idx] != ' ') {
                    idx--;
                }
                int start = idx + 1;
                if (end >= 0) {
                    while (start <= end) {
                        builder.append(arr[start++]);
                    }
                    builder.append(' ');
                }
            }
            if (builder.charAt(builder.length() - 1) == ' ') {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
    }
}
