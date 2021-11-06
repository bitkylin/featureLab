//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 935 👎 0


package leetcode3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 回溯算法，全程不使用List，不适用Queue，效率最高
     */
    class Solution {
        private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) {
                return new ArrayList<>();
            }
            List<String> res = new ArrayList<>();
            resolve(res, digits, 0, new StringBuilder(digits.length()));
            return res;
        }

        private void resolve(List<String> res, String digits, int length, StringBuilder builder) {
            if (length >= digits.length()) {
                res.add(builder.toString());
                return;
            }
            for (char c : map[digits.charAt(length) - '2'].toCharArray()) {
                builder.append(c);
                resolve(res, digits, length + 1, builder);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 回溯算法，全程使用List，只在生成最后结果时转为String，效率较高
     */
    class Solution2 {
        private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) {
                return new ArrayList<>();
            }
            Deque<Character> queue = new ArrayDeque<>();
            for (char c : digits.toCharArray()) {
                queue.addLast(c);
            }
            List<String> res = new ArrayList<>();
            resolve(res, queue, new ArrayList<>());
            return res;
        }

        private void resolve(List<String> res, Deque<Character> queue, List<Character> charList) {
            if (queue.isEmpty()) {
                res.add(concat(charList));
                return;
            }
            Character key = queue.removeFirst();
            for (char c : map[key - '2'].toCharArray()) {
                charList.add(c);
                resolve(res, queue, charList);
                charList.remove(charList.size() - 1);
            }
            queue.addFirst(key);
        }

        private String concat(List<Character> charList) {
            StringBuilder builder = new StringBuilder();
            for (Character character : charList) {
                builder.append(character);
            }
            return builder.toString();
        }
    }

    /**
     * 使用String代替StringBuilder，可以看到效率明显变差
     */
    class Solution3 {
        private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) {
                return new ArrayList<>();
            }
            Deque<Character> queue = new ArrayDeque<>();
            for (char c : digits.toCharArray()) {
                queue.addLast(c);
            }
            List<String> res = new ArrayList<>();
            resolve(res, queue, "");
            return res;
        }

        private void resolve(List<String> res, Deque<Character> queue, String item) {
            if (queue.isEmpty()) {
                res.add(item);
                return;
            }
            Character key = queue.removeFirst();
            for (char c : map[key - '2'].toCharArray()) {
                resolve(res, queue, item + c);
            }
            queue.addFirst(key);
        }
    }
}