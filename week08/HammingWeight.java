/**
 * @author zhanghongjie
 * @date 2020/12/10
 */
public class HammingWeight {
    /**
     * 每次都将最后一位1置为0，提高速度
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
