/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 34在排序数组中查找元素的第一个和最后一个位置
 */
public class SearchRange {
    //思路
    //1、一次二分，然后向两头找第一位和最后一位，时间复杂度最坏的情况下还是O(n)
    //2、两次二分，分别找到第一位和最后一位，时间复杂度是O(logn)
    //3、两次二分，分别找到target的第一位和target + 1的第一位 - 1，如果target + 1不存在的话实际上找到的是第一位大于target的数的下标，减一就是target的最后一位
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return ret;
        }
        int left = searchFirstPosition(nums, target);
        //检验一下返回的left，因为target有可能不存在与nums中，这种情况就是target == nums.length，也就是第一位大于target的数的下标
        if (left < nums.length && nums[left] == target) {
            int rigth = searchLastPosition(nums, target);
            ret[0] = left;
            ret[1] = rigth;
        }
        return ret;
    }

    public int searchFirstPosition(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                //当nums[mid] < target的时候，绝不可能找到target的开始位置
                left = mid + 1;
            }
        }
        return left;
    }

    public int searchLastPosition(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

}
