import java.util.Arrays;

/**
 * 169多数元素
 * https://leetcode-cn.com/problems/majority-element/
 */
public class MajorityElement {
//    public int majorityElement(int[] nums) {
//        //投票算法，时间复杂度为O(n)，空间复杂度为O(1)
//        int count = 0;
//        Integer candidate = null;
//        for (int i : nums) {
//            if (count == 0) {
//                candidate = i;
//            }
//            count += (candidate == i) ? +1 : -1;
//        }
//        return candidate;
//    }

    /**
     * 将数组进行排序，由于众数的话一定会位于排序后数组的中间，所以直接返回中间的元素即可
     * 时间复杂度：O(n\log n)O(nlogn)。将数组排序的时间复杂度为 O(n\log n)O(nlogn)。
     * 空间复杂度：O(\log n)O(logn)。如果使用语言自带的排序算法，需要使用 O(\log n)O(logn) 的栈空间。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
