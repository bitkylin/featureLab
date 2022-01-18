# 字典树、并查集、剪枝、回溯、双向BFS、AVL树、红黑树

## 心得

### 回溯算法

- 回溯一般与DFS、递归配套使用
- 八皇后，使用Deque-stack数据结构，存储每一行的计算结果，从而方便撤销上一步计算结果
- 解数独，数组中计算并选择可以填充元素的格子「cell」，遍历1-9，无效时可撤销之前计算结果
- 八皇后只需要一维数组，解数独需要二维数组，所以造成了算法实现的差异

## 字典树、并查集

todo

## 剪枝、回溯、双向BFS

### 初级搜索

1. 朴素搜索
2. 优化方式：不重复（ﬁbonacci）、剪枝（生成括号问题）
3. 搜索方向：

- DFS: depth ﬁrst search 深度优先搜索
- BFS: breadth ﬁrst search 广度优先搜索

## LeetCode

### 字典树 Trie，还是使用了剪枝、回溯解法

| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 208. 实现 Trie (字典树「前缀树」) | [ImplementTriePrefixTree](leetcode7/ImplementTriePrefixTree.java) | [implement-trie-prefix-tree](https://leetcode-cn.com/problems/implement-trie-prefix-tree/) | 标准实现 |
| 79. 单词搜索 | [WordSearch](leetcode7/WordSearch.java) | [word-search](https://leetcode-cn.com/problems/word-search/) | DFS + 回溯 |
| 212. 单词搜索 II | [WordSearchIi](leetcode7/WordSearchIi.java) | [word-search-ii](https://leetcode-cn.com/problems/word-search-ii/) | 字典树 |
| 130. 被围绕的区域 | [SurroundedRegions](leetcode7/SurroundedRegions.java) | [surrounded-regions](https://leetcode-cn.com/problems/surrounded-regions/) | DFS |
| 547. 省份数量 | [NumberOfProvinces](leetcode7/NumberOfProvinces.java) | [number-of-provinces](https://leetcode-cn.com/problems/number-of-provinces/) | DFS |

### 剪枝、回溯

| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 70. 爬楼梯 | [ClimbingStairs](leetcode7/ClimbingStairs.java) | [climbing-stairs](https://leetcode-cn.com/problems/climbing-stairs/) | DP |
| 22. 括号生成 | [GenerateParentheses](leetcode7/GenerateParentheses.java) | [generate-parentheses](https://leetcode-cn.com/problems/generate-parentheses/) | DFS、BFS「递归、非递归」、回溯 |
| 51. N皇后 | [NQueens](leetcode7/NQueens.java) | [n-queens](https://leetcode-cn.com/problems/n-queens/) | 回溯，Stack存储中间结果 |
| 36. 有效的数独 | [ValidSudoku](leetcode7/ValidSudoku.java) | [valid-sudoku](https://leetcode-cn.com/problems/valid-sudoku/) | 主要看代码实现是否优雅 |
| 37. 解数独 | [SudokuSolver](leetcode7/SudokuSolver.java) | [sudoku-solver](https://leetcode-cn.com/problems/sudoku-solver/) | 回溯，入参二维数组存储中间结果 |
| 433. 最小基因变化 | [MinimumGeneticMutation](leetcode7/MinimumGeneticMutation.java) | [minimum-genetic-mutation](https://leetcode-cn.com/problems/minimum-genetic-mutation/) | BFS，新增双向BFS，不过难度确实较大 |
| 127. 单词接龙 | [WordLadder](leetcode7/WordLadder.java) | [word-ladder](https://leetcode-cn.com/problems/word-ladder/) | BFS，双向BFS待补充 |
| 1091. 二进制矩阵中的最短路径 | [ShortestPathInBinaryMatrix](leetcode7/ShortestPathInBinaryMatrix.java) | [shortest-path-in-binary-matrix](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/) | BFS |
| 773. 滑动谜题 | [SlidingPuzzle](leetcode7/SlidingPuzzle.java) | [sliding-puzzle](https://leetcode-cn.com/problems/sliding-puzzle/) | BFS |
