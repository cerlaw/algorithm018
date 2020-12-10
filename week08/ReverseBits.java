/**
 * @author zhanghongjie
 * @date 2020/12/10
 * 190 颠倒二进制位
 * https://leetcode-cn.com/problems/reverse-bits/
 */
public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += (n & 1);
            n >>= 1;
        }
        return res;
    }

    public int reverseBits2(int n) {
        int res = 0;
        //  java >>> 无符号右移，补的始终都是0
        for (int i = 31; n > 0; i--, n >>>= 1) {
            res += (n & 1) << i;
        }
        return res;
    }
}
