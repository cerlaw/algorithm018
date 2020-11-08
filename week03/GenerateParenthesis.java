import java.util.ArrayList;
import java.util.List;

/**
 * 生成括号
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 递归练习第一题，套模板
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        generateParenthesisCore(n, res,  0, 0, "");
        return res;
    }

    private void generateParenthesisCore(int n, List<String> res, int left, int right, String s) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        if (left < n) {
            //drill down
            generateParenthesisCore(n, res, left + 1, right, s + "(");
        }
        if (right < left) {
            //drill down
            generateParenthesisCore(n ,res, left, right + 1, s + ")");
        }
    }
}
