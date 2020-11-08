/**
 * 替换空格
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * 时间复杂度O(n)，空间复杂度O(1)
 */
public class ReplaceSpace {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        if (s == null) {
            return sb.toString();
        }
        char[] cs = s.toCharArray();
        for (char c: cs) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
