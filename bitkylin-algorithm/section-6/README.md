# 动态规划

## 最佳实践

### 1. 优先考虑自底向上递推的解法「动态规划」

    - f(3) = f(2) + f(1);
    - f(4) = f(3) + f(2);

关键点：

1. 保证无后效性
2. 每次局部解均为局部问题的最优解（f(4)即为n=4时的最优解）：
3. 状态尽量简化，一般一维DP只有一个值，二维DP只有两个值（f(n)，f(i, j)）

### 2. 其次考虑递归的解法「递归、DFS」

    - f(n) = f(n-1) + f(n-2);
    - f(1) = 1; f(2) = 2;

或者：

    - f(n) = f(n-1) + arr(n)

关键点：

1. 函数入参尽量简化，善用返回值「比如基于返回值进行链表的调整、比较f(n)的最小值等」
2. 基于上一条，入参简化后，无后效性，每次计算结果均可以固化
3. 基于上一条，f(n)固化后，可以使用记忆化搜索「存储中间状态」

### 3. 避免点

1. 避免写 `f(n+2) = f(n) + f(n+1)` ，因为测试用例设计的刁钻，极易导致 n+2 超出 Integer.MAX_VALUE。

## 心得

### 做题关键点

1. 动态规划状态会发生转移，状态转义方程基本是：`f(x) = A * f(x - 1)` 形式，状态转移代码一定要写对

## 动态规划、递归、分治异同

- 动态规划和递归、分治没有根本上的区别（关键看有无最优的子结构）
- 共性：找到重复子问题
- 差异性：最优子结构、中途需要淘汰次优解

## 分治与动态规划的区别：是否有最优子结构

- 分治：没有最优子结构，需要把所有的子问题计算并进行合并
- 动态规划：有最优子结构，中途需要淘汰次优解

## 动态规划关键点

1. 最优子结构 opt[n] = best_of(opt[n-1], opt[n-2], …)
2. 储存中间状态：opt[i]
3. 状态转移方程或者 DP 方程

- Fib: opt[i] = opt[n-1] + opt[n-2]
- 二维路径：opt[i,j] = opt[i+1][j] + opt[i][j+1] (且判断a[i,j]是否空地）

## Fibonacci数列

- 傻递归：O(2^n)
- 递归-记忆化搜索-自顶向下：O(n)
- 循环+自底向上：O(n)

## LeetCode

### 动态规划例题

| 题目                 | 项目链接                                                                                                      | leetcode | 心得 |
|--------------------|-----------------------------------------------------------------------------------------------------------|---|---|
| 62. 不同路径           | [UniquePaths](../section-9/leetcode9/UniquePaths.java)                                                    | [unique-paths](https://leetcode-cn.com/problems/unique-paths/) | 前两道题做法一致，简单DP |
| 63. 不同路径 II        | [UniquePathsIi](../section-9/leetcode9/UniquePathsIi.java)                                                             | [unique-paths-ii](https://leetcode-cn.com/problems/unique-paths-ii/) | 仅加上对障碍的判断即可，需要注意初始化时也会有障碍 |
| 1143. 最长公共子序列      | [LongestCommonSubsequence](leetcode6/LongestCommonSubsequence.java)                                       | [longest-common-subsequence](https://leetcode-cn.com/problems/longest-common-subsequence/) | 重点是得出状态转移方程 |
| 53. 最大子数组和         | [MaximumSubarray](leetcode6/MaximumSubarray.java)                                                         | [maximum-subarray](https://leetcode-cn.com/problems/maximum-subarray/) | 动态规划，仅需要取最大值，不需要保存全部状态值 |
| 120. 三角形最小路径和*     | [Triangle](leetcode6/Triangle.java)                                                                       | [triangle](https://leetcode-cn.com/problems/triangle/) | DFS、动态规划 |
| 152. 乘积最大子数组       | [MaximumProductSubarray](leetcode6/MaximumProductSubarray.java)                                           | [maximum-product-subarray](https://leetcode-cn.com/problems/maximum-product-subarray/) | 状态转移方程比较难理解 |
| 198. 打家劫舍          | [HouseRobber](leetcode6/HouseRobber.java)                                                                 | [house-robber](https://leetcode-cn.com/problems/house-robber/) | DP |
| 213. 打家劫舍 II       | [HouseRobberIi](leetcode6/HouseRobberIi.java)                                                             | [house-robber-ii](https://leetcode-cn.com/problems/house-robber-ii/) | DP |
| 121. 买卖股票的最佳时机*    | [BestTimeToBuyAndSellStock](leetcode6/BestTimeToBuyAndSellStock.java)                                     | [best-time-to-buy-and-sell-stock](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) | DP |
| 122. 买卖股票的最佳时机 II  | [BestTimeToBuyAndSellStockIi](leetcode6/BestTimeToBuyAndSellStockIi.java)                                 | [best-time-to-buy-and-sell-stock-ii](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) | 简单解法，DP可优化为简单解法 |
| 123. 买卖股票的最佳时机 III | [BestTimeToBuyAndSellStockIii](leetcode6/BestTimeToBuyAndSellStockIii.java)                               | [best-time-to-buy-and-sell-stock-iii](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) | DFS，DP |
| 309. 最佳买卖股票时机含冷冻期  | [BestTimeToBuyAndSellStockWithCooldown](leetcode6/BestTimeToBuyAndSellStockWithCooldown.java)             | [best-time-to-buy-and-sell-stock-with-cooldown](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) | DP |
| 714. 买卖股票的最佳时机含手续费 | [BestTimeToBuyAndSellStockWithTransactionFee](leetcode6/BestTimeToBuyAndSellStockWithTransactionFee.java) | [best-time-to-buy-and-sell-stock-with-transaction-fee](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) | DP，基本无特殊之处 |
| 188. 买卖股票的最佳时机 IV  | [BestTimeToBuyAndSellStockIv](leetcode6/BestTimeToBuyAndSellStockIv.java)                                 | [best-time-to-buy-and-sell-stock-iv](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) | 三维DP |

### 动态规划习题

| 题目               | 项目链接                                                          | leetcode | 心得 |
|------------------|---------------------------------------------------------------|---|---|
| 64. 最小路径和        | [MinimumPathSum](leetcode6/MinimumPathSum.java)               | [minimum-path-sum](https://leetcode-cn.com/problems/minimum-path-sum/) | DP |
| 91. 解码方法         | [DecodeWays](../section-9/leetcode9/DecodeWays.java)          | [decode-ways](https://leetcode-cn.com/problems/decode-ways/) | DP |
| 221. 最大正方形*      | [MaximalSquare](leetcode6/MaximalSquare.java)                 | [maximal-square](https://leetcode-cn.com/problems/maximal-square/) | DP |
| 621. 任务调度器       | [TaskScheduler](leetcode6/TaskScheduler.java)                 | [task-scheduler](https://leetcode-cn.com/problems/task-scheduler/) | 桶思想 |
| 647. 回文子串        | [PalindromicSubstrings](leetcode6/PalindromicSubstrings.java) | [palindromic-substrings](https://leetcode-cn.com/problems/palindromic-substrings/) | dp、暴力 |
| --困难难度--         ||||
| 矩形区域不超过 K 的最大数值和 | [todo]()                                                      | [max-sum-of-rectangle-no-larger-than-k](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/) | |
