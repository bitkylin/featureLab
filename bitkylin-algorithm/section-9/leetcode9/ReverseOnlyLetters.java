//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
//
//
//
//
//
//
// 示例 1：
//
// 输入："ab-cd"
//输出："dc-ba"
//
//
// 示例 2：
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
//
//
// 示例 3：
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
//
//
//
//
// 提示：
//
//
// S.length <= 100
// 33 <= S[i].ASCIIcode <= 122
// S 中不包含 \ or "
//
// Related Topics 字符串
// 👍 66 👎 0


package leetcode9;

public class ReverseOnlyLetters {

    public static void main(String[] args) {
        Solution solution = new ReverseOnlyLetters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseOnlyLetters(String S) {
            if (S == null || S.length() == 0) {
                return S;
            }
            char[] arr = S.toCharArray();
            int left = 0;
            int right = arr.length - 1;
            while (left < right) {
                while (left < right && !calc(arr, left)) left++;
                while (left < right && !calc(arr, right)) right--;
                if (left < right) {
                    swap(arr, left++, right--);
                }
            }
            return String.valueOf(arr);
        }

        private void swap(char[] arr, int left, int right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        private boolean calc(char[] arr, int i) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                return true;
            }
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                return true;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
