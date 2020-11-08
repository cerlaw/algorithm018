/**
 * 第N个泰波那契数
 * https://leetcode-cn.com/problems/n-th-tribonacci-number/
 * 斐波拉契数的变形，同样采用动态规划的方法解决，时间复杂度O(n),空间复杂度为O(1)
 */
public class Tribonacci {

    public int solution(int n) {
        if (n == 0) {
            return n;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int i = 0, j = 1, k = 1;
        int sum = 0;
        for (int z = 3; z < n; z++) {
            sum = i + j + k;
            i = j;
            j = k;
            k = sum;
        }
        return sum;
    }

}
