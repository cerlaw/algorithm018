/**
 * https://leetcode-cn.com/problems/house-robber/
 * 198打家劫舍
 */
public class Rob {
    //my solution
    // public int rob(int[] nums) {
    //     int length = nums.length;
    //     if (length <= 0) {
    //         return 0;
    //     }
    //     int[] steal = new int[length];
    //     int[] notSteal = new int[length];
    //     steal[0] = nums[0];

    //     for (int i = 1; i < length; i++) {
    //         if (i == 1) {
    //             steal[i] = notSteal[i - 1] + nums[i];
    //         } else {
    //             steal[i] = Math.max(notSteal[i - 1] + nums[i], steal[i - 2]);
    //         }
    //         notSteal[i] = Math.max(notSteal[i - 1], steal[i - 1]);
    //     }
    //     return Math.max(steal[length - 1], notSteal[length - 1]);
    // }

    //official solution
    // public int rob(int[] nums) {
    //     int length = nums.length;
    //     if (length <= 0) {
    //         return 0;
    //     }
    //     if (length == 1) {
    //         return nums[0];
    //     }
    //     int[] dp = new int[length];
    //     dp[0] = nums[0];
    //     dp[1] = Math.max(nums[0], nums[1]);

    //     for (int i = 2; i < length; i++) {
    //         dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    //     }
    //     return dp[length - 1];
    // }

    //official solution optimization
    // public int rob(int[] nums) {
    //     int length = nums.length;
    //     if (length <= 0) {
    //         return 0;
    //     }
    //     if (length == 1) {
    //         return nums[0];
    //     }
    //     int[] dp = new int[length];
    //     int pre = nums[0];
    //     int cur = Math.max(nums[0], nums[1]);

    //     for (int i = 2; i < length; i++) {
    //         int tmp = cur;
    //         cur = Math.max(pre + nums[i], cur);
    //         pre = tmp;

    //     }
    //     return cur;
    // }

    //simplify version
    public int rob(int[] nums) {
        int pre = 0, cur = 0;
        for (int num : nums) {
            int tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}
