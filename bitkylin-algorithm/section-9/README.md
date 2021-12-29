# 高级动态规划、字符串

## 心得

### 高级动态规划
1. 常练常新，根据要求重新练习 section 6 动态规划相关题目时，总能直接写出更加精简的写法，能体会到自己的算法功底有明显提升。
2. 高级动态规划的难点是，状态空间需要升维。section 6 中已练习的题目大部分为二维，少数三维，本章题目多为「困难」难度，先练好基础系统， 学有余力时再挑战困难题目。
3. 之前练习时，习惯确定状态数组边界为最大索引值，现发现使用数组长度值「最大索引值+1」更容易解题。

## 字符串
1. 基础题目偏简单，其余题目较复杂，通常需要结合动态规划。


## LeetCode

不同路径 2 状态转移方程：

DP[x][y] = (DP[x - 1][y] + DP[x][y - 1]) * (1 ^ Grid[x][y])

### 高级动态规划
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 62. 不同路径 | [UniquePaths](leetcode9/UniquePaths.java) | [unique-paths](https://leetcode-cn.com/problems/unique-paths/) | dp、递归 |
| 63. 不同路径 II | [UniquePathsIi](leetcode9/UniquePathsIi.java) | [unique-paths-ii](https://leetcode-cn.com/problems/unique-paths-ii/) | 仅加上对障碍的判断即可，需要注意初始化时也会有障碍 |
| 91. 解码方法 | [DecodeWays](leetcode9/DecodeWays.java) | [decode-ways/](https://leetcode-cn.com/problems/decode-ways/) |   |
| 300. 最长上升子序列 | [LongestIncreasingSubsequence](leetcode9/LongestIncreasingSubsequence.java) | [longest-increasing-subsequence/](https://leetcode-cn.com/problems/longest-increasing-subsequence/) | dp，二分查找法待补充 |
| 279. 完全平方数 | [PerfectSquares](leetcode9/PerfectSquares.java) | [perfect-squares](https://leetcode-cn.com/problems/perfect-squares/) |   |
| 72. 编辑距离 | [EditDistance](leetcode9/EditDistance.java) | [edit-distance](https://leetcode-cn.com/problems/edit-distance/) |   |
| 32. 最长有效括号 | [LongestValidParentheses](leetcode9/LongestValidParentheses.java) | [longest-valid-parentheses](https://leetcode-cn.com/problems/longest-valid-parentheses/) |   |
| 5. 最长回文子串 | [LongestPalindromicSubstring](leetcode9/LongestPalindromicSubstring.java) | [longest-palindromic-substring](https://leetcode-cn.com/problems/longest-palindromic-substring/) | DP，暴力 |
|  | []() | []() |   |
|  |  |  |   |

### 字符串
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 709. 转换成小写字母 | [ToLowerCase](leetcode9/ToLowerCase.java) | [to-lower-case](https://leetcode-cn.com/problems/to-lower-case/) | 题目很简单 |
| 58. 最后一个单词的长度 | [LengthOfLastWord](leetcode9/LengthOfLastWord.java) | [length-of-last-word](https://leetcode-cn.com/problems/length-of-last-word/) |   |
| 771. 宝石与石头 | [JewelsAndStones](leetcode9/JewelsAndStones.java) | [jewels-and-stones](https://leetcode-cn.com/problems/jewels-and-stones/) |   |
| 387. 字符串中的第一个唯一字符 | [FirstUniqueCharacterInAString](leetcode9/FirstUniqueCharacterInAString.java) | [first-unique-character-in-a-string](https://leetcode-cn.com/problems/first-unique-character-in-a-string/) |   |
| 8. 字符串转换整数 (atoi) | [StringToIntegerAtoi](leetcode9/StringToIntegerAtoi.java) | [string-to-integer-atoi](https://leetcode-cn.com/problems/string-to-integer-atoi/) |   |
| 14. 最长公共前缀 | [LongestCommonPrefix](leetcode9/LongestCommonPrefix.java) | [longest-common-prefix](https://leetcode-cn.com/problems/longest-common-prefix/) |   |
| 344. 反转字符串 | [ReverseString](leetcode9/ReverseString.java) | [reverse-string](https://leetcode-cn.com/problems/reverse-string/) |   |
| 541. 反转字符串 II | [ReverseStringIi](leetcode9/ReverseStringIi.java) | [reverse-string-ii](https://leetcode-cn.com/problems/reverse-string-ii/) |   |
| 151. 翻转字符串里的单词 | [ReverseWordsInAString](leetcode9/ReverseWordsInAString.java) | [reverse-words-in-a-string](https://leetcode-cn.com/problems/reverse-words-in-a-string/) |   |
| 557. 反转字符串中的单词 III | [ReverseWordsInAStringIii](leetcode9/ReverseWordsInAStringIii.java) | [reverse-words-in-a-string-iii](https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/) |   |
| 917. 仅仅反转字母 | [ReverseOnlyLetters](leetcode9/ReverseOnlyLetters.java) | [reverse-only-letters](https://leetcode-cn.com/problems/reverse-only-letters/) | 双指针 |
| 438. 找到字符串中所有字母异位词 | [FindAllAnagramsInAString](leetcode9/FindAllAnagramsInAString.java) | [find-all-anagrams-in-a-string](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/) |   |
| 125. 验证回文串 | [ValidPalindrome](leetcode9/ValidPalindrome.java) | [valid-palindrome](https://leetcode-cn.com/problems/valid-palindrome/) |   |
| 680. 验证回文字符串 Ⅱ | [ValidPalindromeIi](leetcode9/ValidPalindromeIi.java) | [valid-palindrome-ii](https://leetcode-cn.com/problems/valid-palindrome-ii/) |   |
|  | []() | []() |   |
|  |  |  |   |
