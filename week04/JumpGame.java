/**
 * 55跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 * 时间复杂度O(n），空间复杂度O(1)
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        //从后往前贪心
        // int n = nums.length;
        // int endReachMost = n - 1;
        // for (int i = n - 1; i >=0; i--) {
        //     if (nums[i] + i >= endReachMost) {
        //         endReachMost = i;
        //     }
        // }
        // return endReachMost == 0;

        //从前往后贪心
        int n = nums.length;
        int canReachMost = 0;
        for (int i = 0; i < n; i++) {
            if (i > canReachMost) {
                return false;
            }
            int canReach = nums[i] + i;
            canReachMost = Math.max(canReachMost, canReach);
        }
        return true;
    }
}
