/**
 * <p>给你一个字符串 <code>path</code> ，表示指向某一文件或目录的 Unix 风格 <strong>绝对路径 </strong>（以 <code>'/'</code> 开头），请你将其转化为更加简洁的规范路径。</p>
 *
 * <p class="MachineTrans-lang-zh-CN">在 Unix 风格的文件系统中，一个点（<code>.</code>）表示当前目录本身；此外，两个点 （<code>..</code>） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，<code>'//'</code>）都被视为单个斜杠 <code>'/'</code> 。 对于此问题，任何其他格式的点（例如，<code>'...'</code>）均被视为文件/目录名称。</p>
 *
 * <p>请注意，返回的 <strong>规范路径</strong> 必须遵循下述格式：</p>
 *
 * <ul>
 * <li>始终以斜杠 <code>'/'</code> 开头。</li>
 * <li>两个目录名之间必须只有一个斜杠 <code>'/'</code> 。</li>
 * <li>最后一个目录名（如果存在）<strong>不能 </strong>以 <code>'/'</code> 结尾。</li>
 * <li>此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 <code>'.'</code> 或 <code>'..'</code>）。</li>
 * </ul>
 *
 * <p>返回简化后得到的 <strong>规范路径</strong> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>path = "/home/"
 * <strong>输出：</strong>"/home"
 * <strong>解释：</strong>注意，最后一个目录名后面没有斜杠。 </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>path = "/../"
 * <strong>输出：</strong>"/"
 * <strong>解释：</strong>从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>path = "/home//foo/"
 * <strong>输出：</strong>"/home/foo"
 * <strong>解释：</strong>在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>path = "/a/./b/../../c/"
 * <strong>输出：</strong>"/c"
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= path.length <= 3000</code></li>
 * <li><code>path</code> 由英文字母，数字，<code>'.'</code>，<code>'/'</code> 或 <code>'_'</code> 组成。</li>
 * <li><code>path</code> 是一个有效的 Unix 风格绝对路径。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>字符串</li></div></div><br><div><li>👍 331</li><li>👎 0</li></div>
 */

package leetcode1;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

    public static void main(String[] args) {
        Solution solution = new SimplifyPath().new Solution();
    }

    /**
     * 易理解的解法
     * 注：不要漏了场景
     */
    class Solution {
        public String simplifyPath(String path) {
            StringBuilder builder = new StringBuilder();
            ArrayDeque<String> stack = new ArrayDeque<>();
            String[] arr = path.split("/");
            for (String s : arr) {
                if ("..".equals(s)) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (".".equals(s) || "".equals(s)) {
                    continue;
                } else {
                    stack.push(s);
                }
            }
            while (!stack.isEmpty()) {
                builder.append("/").append(stack.removeLast());
            }
            return builder.length() == 0 ? "/" : builder.toString();
        }
    }

    class Solution2 {
        public String simplifyPath(String path) {
            Deque<String> deque = new ArrayDeque<>();
            StringBuilder builder = new StringBuilder();
            for (String s : path.split("/")) {
                if (!deque.isEmpty() && s.equals("..")) {
                    deque.removeLast();
                } else if (!"..".contains(s)) {
                    deque.addLast(s);
                }
            }
            for (String s : deque) {
                builder.append("/" + s);
            }
            return builder.length() == 0 ? "/" : builder.toString();
        }
    }

}