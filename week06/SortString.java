/**
 * https://leetcode-cn.com/problems/increasing-decreasing-string/
 * 1370上升下降字符串
 */
public class SortString {
    public String sortString(String s) {
        //桶计数，记录下每个字母出现的次数，然后按规则重新排序
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (nums[i] > 0) {
                    sb.append((char) ('a' + i));
                    nums[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if (nums[i] > 0) {
                    sb.append((char) ('a' + i));
                    nums[i]--;
                }
            }
        }
        return sb.toString();
    }
}
