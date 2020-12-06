/**
 * https://leetcode-cn.com/problems/valid-sudoku/
 * 36 有效的数独
 */
public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        //分别记录每一行、每一列、每一个块中数字出现过没有,出现过就置为1，块的索引：(i / 3) * 3 + j / 3
        int[][] columns = new int[9][10];
        int[][] rows = new int[9][10];
        int[][] blocks = new int[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (columns[j][num] == 1) {
                        return false;
                    }
                    if (rows[i][num] == 1) {
                        return false;
                    }
                    if (blocks[(i / 3) * 3 + j / 3][num] == 1) {
                        return false;
                    }
                    columns[j][num] = 1;
                    rows[i][num] = 1;
                    blocks[(i / 3) * 3 + j / 3][num] = 1;
                }
            }
        }
        return true;
    }
}
