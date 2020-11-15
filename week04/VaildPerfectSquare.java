/**
 * https://leetcode-cn.com/problems/valid-perfect-square/
 * 有效的完全平方数
 */
public class VaildPerfectSquare {
    public boolean isPerfectSquare(int num) {
        //二分查找，套模板，时间复杂度O(logn)，空间复杂度O(1)
        if (num < 2) {
            return true;
        }
        long left = 2, right = num / 2, x, tmp;
        while (left <= right) {
            //防止溢出
            x = left + (right - left) / 2;
            tmp = x * x;
            if (tmp == num) {
                return true;
            }
            if (tmp > num) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }
        return false;
    }

//牛顿法，思想就是利用一阶泰勒展开式对结果进行逼近
//时间复杂度O(logn)，空间复杂度O(1)
// public boolean isPerfectSquare(int num) {
//     if(num < 2) {
//         return true;
//     }

//     long x = num / 2;
//     当x的平方小于等于num的时候就逼近到答案了
//     while (x * x > num) {
//         x = (x + num / x) / 2;
//     }
//     return x * x == num;
// }
}
