public class TwoSum {
    public int[] solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}