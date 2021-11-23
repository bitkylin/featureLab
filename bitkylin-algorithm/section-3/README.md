# 泛型递归、树的递归、分治、回溯

## 总结
1. 树的面试题解法一般都是递归

## 递归模板
```java
public class Test {
    public void recur(int level, int param) {
        // terminator            递归终结条件
        if (level > MAX_LEVEL) {
            "process result";
            return;
        }

        // process current logic  处理当前层
        process(level, param);

        // drill down             下探到下层
        recur(level = level + 1, newParam);

        // restore current status 清理当前层
    }
}
```

## 递归
要点
1. 不要人肉进行递归（最大误区）
2. 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
3. 数学归纳法思维

## 分治、回溯

### 分治代码模板
```java
public class Test {
    public Integer recur(int level, int param) {
        // terminator            递归终结条件
        if (terminal(level)) {
            "process result";
            return null;
        }

        // process current logic  处理当前层
        process(level, param);

        //                        处理子问题
        int subRes1 = recur(subLevel1, newParam);
        int subRes2 = recur(subLevel2, newParam);
        int subRes3 = recur(subLevel3, newParam);

        //                        生成最终结果
        return processRes(subRes1, subRes2, subRes3);

        // restore current status 清理当前层
    }
}
```

### 回溯
回溯法采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。


## LeetCode

### 递归
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 22. 括号生成 | [GenerateParentheses](../section-7/leetcode7/GenerateParentheses.java) | [generate-parentheses](https://leetcode-cn.com/problems/generate-parentheses/) | 递归 + 剪枝  |
| 226. 翻转二叉树 | [InvertBinaryTree](leetcode3/InvertBinaryTree.java) | [invert-binary-tree](https://leetcode-cn.com/problems/invert-binary-tree/) | 递归模板实现即可，还可以使用DFS、BFS两者代码基本一致  |
| 98. 验证二叉搜索树 | [ValidateBinarySearchTree](leetcode3/ValidateBinarySearchTree.java) | [validate-binary-search-tree](https://leetcode-cn.com/problems/validate-binary-search-tree/) | 二叉搜索树的中序遍历是单调递增的 |
| 104. 二叉树的最大深度 | [MaximumDepthOfBinaryTree](leetcode3/MaximumDepthOfBinaryTree.java) | [maximum-depth-of-binary-tree](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) | 由逐层计算深度，由上到下、由下到上均可  |
| 111. 二叉树的最小深度 | [MinimumDepthOfBinaryTree](leetcode3/MinimumDepthOfBinaryTree.java) | [minimum-depth-of-binary-tree](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) |   |
| 297. 二叉树的序列化与反序列化 | [SerializeAndDeserializeBinaryTree](leetcode3/SerializeAndDeserializeBinaryTree.java) | [serialize-and-deserialize-binary-tree](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/) | 代码量较大，应该没有精简代码的解法 |
| 236. 二叉树的最近公共祖先 | [LowestCommonAncestorOfABinaryTree](leetcode3/LowestCommonAncestorOfABinaryTree.java) | [lowest-common-ancestor-of-a-binary-tree](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) | 后序遍历 DFS  |
| 105. 从前序与中序遍历序列构造二叉树 | [ConstructBinaryTreeFromPreorderAndInorderTraversal](leetcode3/ConstructBinaryTreeFromPreorderAndInorderTraversal.java) | [construct-binary-tree-from-preorder-and-inorder-traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | 递归 |
| 77. 组合 | [Combinations](leetcode3/Combinations.java) | [combinations](https://leetcode-cn.com/problems/combinations/) | 递归 |
| 46. 全排列 | [Permutations](leetcode3/Permutations.java) | [permutations](https://leetcode-cn.com/problems/permutations/) | 回溯 |
| 47. 全排列 II | [PermutationsIi](leetcode3/PermutationsIi.java) | [permutations-ii](https://leetcode-cn.com/problems/permutations-ii/) | 回溯+SET |

### 分治、回溯
| 题目 | 项目链接 | leetcode | 心得 |
|---|---|---|---|
| 50. Pow(x, n) | [PowxN](leetcode3/PowxN.java) | [powx-n](https://leetcode-cn.com/problems/powx-n/) | 递归 |
| 78. 子集 | [Subsets](leetcode3/Subsets.java) | [subsets](https://leetcode-cn.com/problems/subsets/) | 递归 |
| 169. 多数元素 | [MajorityElement](leetcode3/MajorityElement.java) | [majority-element](https://leetcode-cn.com/problems/majority-element/) | 多种解法 |
| 17. 电话号码的字母组合 | [LetterCombinationsOfAPhoneNumber](leetcode3/LetterCombinationsOfAPhoneNumber.java) | [letter-combinations-of-a-phone-number](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | 回溯算法 |
| 51. N 皇后 | [NQueens](leetcode3/NQueens.java) | [n-queens](https://leetcode-cn.com/problems/n-queens/) | 回溯 |
| 52. N皇后 II | [NQueensIi](leetcode3/NQueensIi.java) | [n-queens-ii](https://leetcode-cn.com/problems/n-queens-ii/) | 回溯，相比上一题简化 |
