/**
 * 153寻找旋转排序数组中的最小值
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * @author zhanghongjie
 * @date 2020/11/19
 */
public class FindMinimumInRotatedSortedArray {

    /**
     * 二分法查找，时间复杂度O(logn)，空间复杂度O(1)
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                //最小值一定在右侧
                left = mid + 1;
            } else {
                //最小值有可能在左侧或者是mid
                right = mid;
            }
        }
        return left;
    }

    /**
     * 两边逼近，可以找到最大值(left)和最小值(right)
     * 时间复杂度O(logn)，空间复杂度O(1)
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
}
