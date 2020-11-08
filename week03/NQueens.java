import java.util.*;

/**
 *  N皇后
 *  https://leetcode-cn.com/problems/n-queens/
 *  回溯的练习
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }

        //放置queen的位置
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> xy_diagnoals = new HashSet<>();
        Set<Integer> yx_diagnoals = new HashSet<>();
        search(queens, n, 0, columns, xy_diagnoals, yx_diagnoals, res);
        return res;
    }

    public void search(int[] queens, int rows, int row, Set<Integer> columns, Set<Integer> xy_diagnoals, Set<Integer> yx_diagnoals, List<List<String>> res) {
        if (row == rows) {
            res.add(printBoard(queens));
            return;
        }
        for (int i = 0; i < rows; i++) {
            if (columns.contains(i)) {
                continue;
            }
            int xy_diagnoal = row + i;
            if (xy_diagnoals.contains(xy_diagnoal)) {
                continue;
            }

            int yx_diagnoal = row - i;
            if (yx_diagnoals.contains(yx_diagnoal)) {
                continue;
            }
            queens[row] = i;
            columns.add(i);
            xy_diagnoals.add(xy_diagnoal);
            yx_diagnoals.add(yx_diagnoal);
            search(queens, rows, row + 1, columns, xy_diagnoals, yx_diagnoals, res);
            queens[row] = -1;
            columns.remove(i);
            xy_diagnoals.remove(xy_diagnoal);
            yx_diagnoals.remove(yx_diagnoal);
        }
    }

    public List<String> printBoard(int[] queens) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            char[] cs = new char[queens.length];
            Arrays.fill(cs, '.');
            cs[queens[i]] = 'Q';
            res.add(new String(cs));
        }
        return res;
    }
}
