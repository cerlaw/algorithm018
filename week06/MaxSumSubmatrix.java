/**
 * https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
 * 363矩形区域不超过K的最大数值和
 */
public class MaxSumSubmatrix {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        //固定左右两边边界，其实固定上下边界也一样，但是题目说明了行数远大于列数
        for(int i = 0; i < cols; i++) {
            int[] rowSum = new int[rows];
            for (int j = i; j < cols; j++) {
                for (int r = 0; r < rows; r++) {
                    rowSum[r] += matrix[r][j];
                }
                max = Math.max(max, dpMax(rowSum, k));
            }
        }
        return max;
    }

    //枚举rowSum中的元素，得出左上角到右下角的矩形的大小
    public int dpMax(int[] rowSum, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rowSum.length; i++) {
            int sum = 0;
            for (int r = i; r < rowSum.length; r++) {
                sum += rowSum[r];
                if (sum > max && sum <= k) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
