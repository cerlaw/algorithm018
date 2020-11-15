/**
 * https://leetcode-cn.com/problems/number-of-islands/
 * 200岛屿数量
 * 思路：当我们找到一个1的时候，count++，然后利用dfs的思想把1相邻的所有1变为0，也就是沉岛
 * 时间复杂度O(n^2)，空间复杂度O（n）
 */
public class NumberOfIsland {
    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int islandNums = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    sinkIsland(grid, i, j);
                    islandNums++;
                }
            }
        }
        return islandNums;
    }

    public void sinkIsland(char[][] grid, int i, int j) {
        //terminator
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        //current logic
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            //drill down
            sinkIsland(grid, i - 1, j);
            sinkIsland(grid, i + 1, j);
            sinkIsland(grid, i, j + 1);
            sinkIsland(grid, i, j - 1);
        }
    }
}
