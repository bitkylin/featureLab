/**
 * <p>给定一个仅包含数字&nbsp;<code>2-9</code>&nbsp;的字符串，返回所有它能表示的字母组合。答案可以按 <strong>任意顺序</strong> 返回。</p>
 *
 * <p>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。</p>
 *
 * <p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/11/09/200px-telephone-keypad2svg.png" style="width: 200px;" /></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = "23"
 * <strong>输出：</strong>["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = ""
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>digits = "2"
 * <strong>输出：</strong>["a","b","c"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= digits.length &lt;= 4</code></li>
 * <li><code>digits[i]</code> 是范围 <code>['2', '9']</code> 的一个数字。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 1630</li><li>👎 0</li></div>
 */

package leetcode3;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }

    /**
     * 递归「回溯」，不需要进行当前层的清理，因为递归的结果不会直接作为结果集，后续递归会覆盖前面的
     */
    class Solution {

        private Map<Character, List<Character>> map = new HashMap<>();

        {
            map.put('2', Arrays.asList('a', 'b', 'c'));
            map.put('3', Arrays.asList('d', 'e', 'f'));
            map.put('4', Arrays.asList('g', 'h', 'i'));
            map.put('5', Arrays.asList('j', 'k', 'l'));
            map.put('6', Arrays.asList('m', 'n', 'o'));
            map.put('7', Arrays.asList('p', 'q', 'r', 's'));
            map.put('8', Arrays.asList('t', 'u', 'v'));
            map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        }

        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            if (digits.isEmpty()) {
                return res;
            }
            char[] arr = new char[digits.length()];
            solve(res, arr, digits, 0);
            return res;
        }

        private void solve(List<String> res, char[] arr, String digits, int level) {
            if (level >= arr.length) {
                res.add(String.valueOf(arr));
                return;
            }
            List<Character> list = map.get(digits.charAt(level));
            for (Character c : list) {
                arr[level] = c;
                solve(res, arr, digits, level + 1);
            }
        }
    }

    class Solution1_1 {
        public List<String> letterCombinations(String digits) {
            String[] raw = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            List<String> list = new ArrayList<>();
            char[] arr = digits.toCharArray();
            if (arr.length == 0) {
                return list;
            }
            solve(raw, list, new char[digits.length()], arr, 0);
            return list;
        }

        private void solve(String[] raw, List<String> list, char[] res, char[] arr, int i) {
            if (i >= arr.length) {
                list.add(String.valueOf(res));
                return;
            }
            for (char c : raw[arr[i] - 0x30].toCharArray()) {
                res[i] = c;
                solve(raw, list, res, arr, i + 1);
            }
        }
    }

    /**
     * 回溯算法，全程不使用List，不适用Queue，效率最高
     */
    class Solution1 {
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