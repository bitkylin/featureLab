# 位运算、布隆过滤器、LRU缓存、排序算法

## 心得

### 位运算
1. 正数的原码、反码、补码都一样
2. 负数的反码 = 原码的符号位不变，其他位取反
3. 负数的补码 = 反码+1
4. 0的原码、反码、补码都是0
5. 计算机以补码进行运算
6. 加法运算中，符号位参与运算

## 位运算
| 操作 | 符号 | 示例 | 备注 |
|---|---|---|---|
|左移|000011 << 3 = 011000|1 << 3 == 8; 3 << 3 == 24| |
|右移，补符号位|01111 >> 2 = 00011|15 >> 2 == 3; Integer.MIN_VALUE >> 31 == -1| |
|无符号右移|1111 >> 2 = 0011|-1 >>> 30 == 3| |
|异或|x ^ 0 == x| 12 ^ 0 == 12 | |
|   |x ^ 1111 = ~x|(15 ^ ~0) == ~15 | |
|   |x ^ ~x == 1111| 13 ^ ~13 == -1 | |
|   |x ^ x == 0000|  13 ^ 13 == 0 | |
|---||||
|将x最右边的n位清零|x & (~0 << n)|15 & (~0 << 2) == 12||
|获取x的第n位值|(x >> n) & 1|9 >> 3 & 1 == 1||
|获取x的第n位的幂值|x & (1 << n)|15 & 1 << 3 == 8||
|仅将第n位置为1|x &#124; (1 << n)|8 &#124; (1 << 2) == 12||
|仅将第n位置为0|x & ~(1 << n)|12 & ~(1 << 2) == 8||
|将x最高位至第n位清零|x & ((1 << n) - 1)|15 & ((1 << 2) - 1) == 3||
|其他| ~0 == -1 |||
|---||||
|要点||||
|判断奇偶|x % 2  —> x & 1|||
|清零最低位的1|X = X & (X - 1)|||
|得到最低位的1，其余位置0|X & -X|||
|  其他  | x / 2 —> x >> 1|||
|       |X & ~X == 0|||


## 布隆过滤器和LRU缓存
todo

## 参考资料
1. [Java运算符及运算符的优先级](https://www.jianshu.com/p/9d2204712097)

## LeetCode

### 位运算
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 191. 位1的个数 | [NumberOf1Bits](leetcode8/NumberOf1Bits.java) | [number-of-1-bits](https://leetcode-cn.com/problems/number-of-1-bits/) | 解法很多 |
| 231. 2的幂 | [PowerOfTwo](leetcode8/PowerOfTwo.java) | [power-of-two](https://leetcode-cn.com/problems/power-of-two/) | 解法极简 |
| 190. 颠倒二进制位 | [ReverseBits](leetcode8/ReverseBits.java) | [reverse-bits](https://leetcode-cn.com/problems/reverse-bits/) | 逐位颠倒 |
|  | []() | []() |   |
|  | []() | []() |   |
|  |  |  |   |

### 布隆过滤器和LRU缓存
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
|  | []() | []() |   |
|  | []() | []() |   |
|  | []() | []() |   |
|  |  |  |   |

### 排序算法
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 经典排序算法总结 | [SortMain](leetcode8/sort) | [经典排序算法及其 Java 实现](https://www.jianshu.com/p/8e708994e123) | 总结了所有的经典排序算法，此链接为入口方法 |
| 1122. 数组的相对排序 | [RelativeSortArray](leetcode8/RelativeSortArray.java) | [relative-sort-array](https://leetcode-cn.com/problems/relative-sort-array/) | 计数排序 |
|  | []() | []() |   |
|  |  |  |   |
