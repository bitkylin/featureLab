/**
 * <p>给你一个目录信息列表&nbsp;<code>paths</code> ，包括目录路径，以及该目录中的所有文件及其内容，请你按路径返回文件系统中的所有重复文件。答案可按 <strong>任意顺序</strong> 返回。</p>
 *
 * <p>一组重复的文件至少包括 <strong>两个 </strong>具有完全相同内容的文件。</p>
 *
 * <p><strong>输入 </strong>列表中的单个目录信息字符串的格式如下：</p>
 *
 * <ul>
 * <li><code>"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"</code></li>
 * </ul>
 *
 * <p>这意味着，在目录&nbsp;<code>root/d1/d2/.../dm</code>&nbsp;下，有 <code>n</code> 个文件 ( <code>f1.txt</code>,&nbsp;<code>f2.txt</code>&nbsp;...&nbsp;<code>fn.txt</code> ) 的内容分别是 ( <code>f1_content</code>,&nbsp;<code>f2_content</code>&nbsp;...&nbsp;<code>fn_content</code> ) 。注意：<code>n &gt;= 1</code> 且 <code>m &gt;= 0</code> 。如果 <code>m = 0</code> ，则表示该目录是根目录。</p>
 *
 * <p><strong>输出 </strong>是由 <strong>重复文件路径组</strong> 构成的列表。其中每个组由所有具有相同内容文件的文件路径组成。文件路径是具有下列格式的字符串：</p>
 *
 * <ul>
 * <li><code>"directory_path/file_name.txt"</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
 * <strong>输出：</strong>[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
 * <strong>输出：</strong>[["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= paths[i].length &lt;= 3000</code></li>
 * <li><code>1 &lt;= sum(paths[i].length) &lt;= 5 * 10<sup>5</sup></code></li>
 * <li><code>paths[i]</code> 由英文字母、数字、字符 <code>'/'</code>、<code>'.'</code>、<code>'('</code>、<code>')'</code> 和 <code>' '</code> 组成</li>
 * <li>你可以假设在同一目录中没有任何文件或目录共享相同的名称。</li>
 * <li>你可以假设每个给定的目录信息代表一个唯一的目录。目录路径和文件信息用单个空格分隔。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>假设您有一个真正的文件系统，您将如何搜索文件？广度搜索还是宽度搜索？</li>
 * <li>如果文件内容非常大（GB级别），您将如何修改您的解决方案？</li>
 * <li>如果每次只能读取 1 kb 的文件，您将如何修改解决方案？</li>
 * <li>修改后的解决方案的时间复杂度是多少？其中最耗时的部分和消耗内存的部分是什么？如何优化？</li>
 * <li>如何确保您发现的重复文件不是误报？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 88</li><li>👎 0</li></div>
 */

package top1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFileInSystem {

    public static void main(String[] args) {
        Solution solution = new FindDuplicateFileInSystem().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> findDuplicate(String[] paths) {
            Map<String, List<String>> map = new HashMap<>();
            for (String path : paths) {
                String[] values = path.split(" ");
                for (int i = 1; i < values.length; i++) {
                    String[] nameArr = values[i].split("\\(");
                    nameArr[1] = nameArr[1].replace(")", "");
                    List<String> list = map.getOrDefault(nameArr[1], new ArrayList<>());
                    list.add(values[0] + "/" + nameArr[0]);
                    map.put(nameArr[1], list);
                }
            }
            List<List<String>> res = new ArrayList<>();
            for (String key : map.keySet()) {
                if (map.get(key).size() > 1)
                    res.add(map.get(key));
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
