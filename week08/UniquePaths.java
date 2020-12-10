/**
 * @author zhanghongjie
 * @date 2020/12/10
 * 62.不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class UniquePaths {
    // public int uniquePaths(int m, int n) {
    //     int[][] dp = new int[m][n];
    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (i == 0 || j == 0) {
    //                 dp[i][j] = 1;
    //             } else {
    //                 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    //             }
    //         }
    //     }
    //     return dp[m - 1][n - 1];
    // }

    //用一维数组解决
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[m - 1];
    }
}
