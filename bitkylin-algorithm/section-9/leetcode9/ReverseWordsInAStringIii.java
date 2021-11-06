//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
//
//
// 示例：
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
//
//
//
//
// 提示：
//
//
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
//
// Related Topics 字符串
// 👍 255 👎 0


package leetcode9;

public class ReverseWordsInAStringIii {

    public static void main(String[] args) {
        Solution solution = new ReverseWordsInAStringIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            char[] arr = s.toCharArray();
            int max = arr.length - 1;
            for (int i = 0; i <= max; ) {
                while (i <= max && arr[i] == ' ') {
                    i++;
                }
                int start = i;
                while (i <= max && arr[i] != ' ') {
                    i++;
                }
                int end = i - 1;
                if (start <= max) {
                    while (start < end) {
                        swap(arr, start++, end--);
                    }
                }
            }
            return String.valueOf(arr);
        }

        private void swap(char[] arr, int start, int end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
