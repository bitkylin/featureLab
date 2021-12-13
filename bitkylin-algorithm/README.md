# 算法 + LeetCode

## 章节目录

| 类别                                                            | 项目目录           |
| --------------------------------------------------------------- | ------------------ |
| 数组、链表、跳表、栈、队列                                      | [section-1](section-1) |
| 哈希表 Hash、映射 Map、集合 Set、二叉树、二叉搜索树、堆、二叉堆 | [section-2](section-2) |
| 泛型递归、树的递归、分治、回溯                                  | [section-3](section-3) |
| DFS、BFS、贪心算法、二分查找                                    | [section-4](section-4) |
| 动态规划                                                        | [section-6](section-6) |
| 字典树、并查集、剪枝、回溯、双向 BFS、AVL 树、红黑树            | [section-7](section-7) |
| 位运算、布隆过滤器、LRU 缓存、排序算法                          | [section-8](section-8) |
| 高级动态规划、字符串                                            | [section-9](section-9) |
| Top                                            | [Top](top) |

## IDEA 插件配置

### 1. 插件名称
LeetCode Editor

### 2. Code File Name

```
$!velocityTool.camelCaseName(${question.titleSlug})
```

### 3. Code Template

```
${question.content}
  
package editor.cn;

public class $!velocityTool.camelCaseName(${question.titleSlug}) {

    public static void main(String[] args) {
        Solution solution = new $!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
    }
    
    ${question.code}
}
```

### TempFilePath

配置为：`D:\develop\project\minebackup\algorithm`

此时会自动在其子目录 `\leetcode\editor\cn` 中创建题目模板

完整题目模板路径为：`D:\develop\project\minebackup\algorithm\leetcode\editor\cn`