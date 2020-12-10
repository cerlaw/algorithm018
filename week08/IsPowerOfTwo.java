/**
 * @author zhanghongjie
 * @date 2020/12/10
 */
public class IsPowerOfTwo {

    /**
     * 因为2的幂 有且仅有最高位为1，判断将最后一位1置为0后是否等于0就可以判断是不是2的幂
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
