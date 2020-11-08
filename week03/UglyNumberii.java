import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 丑数二
 * https://leetcode-cn.com/problems/ugly-number-ii/
 * 三指针动态规划
 * 最小堆
 */
public class UglyNumberii {
    /**
     * 三指针动态规划
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("param n is illegal");
        }
        int[] nums = new int[n];
        nums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = min;

            //判重
            if (nums[i2] * 2 == min) {
                i2++;
            }
            if (nums[i3] * 3 == min) {
                i3++;
            }
            if (nums[i5] * 5 == min) {
                i5++;
            }
        }
        return nums[n - 1];
    }

    public int nthUglyNumber2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("param n is illegal");
        }
        int[] primes = new int[]{2, 3, 5};
        Set<Integer> seen = new HashSet<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        seen.add(1);
        heap.add(1);

        int curUgly = 0, newUgly = 0;
        for (int i = 0; i < n; i++) {
            curUgly = heap.poll();
            for (int prime : primes) {
                newUgly = curUgly * prime;
                if (!seen.contains(newUgly)) {
                    seen.add(newUgly);
                    heap.add(newUgly);
                }
            }
        }
        return curUgly;
    }
}
