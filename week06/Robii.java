import java.util.Arrays;

/**
 * 213、打家劫舍二
 * https://leetcode-cn.com/problems/house-robber-ii/
 *
 */
public class Robii {
    //这题的环形的房屋，可以分解成两种情况1、偷第一间房屋，那就不能偷最后一间2、不偷第一间，就可以偷最后一间
    //最后比较这两种情况下得到的最大值
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, length)));
    }

    //打家劫舍的解法
    public int myRob(int[] nums) {
        int pre = 0, cur = 0;
        for (int num : nums) {
            int tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}
