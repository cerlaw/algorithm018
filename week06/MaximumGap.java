import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-gap/
 * 164最大间距
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        //这里使用的是Java的api排序，但是题目要求的是O(n)的时间复杂度，所以这里不满足要求，应该使用基数排序
        Arrays.sort(nums);
        int maxGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] > maxGap) {
                maxGap = nums[i + 1] - nums[i];
            }
        }
        return maxGap;
    }
}
