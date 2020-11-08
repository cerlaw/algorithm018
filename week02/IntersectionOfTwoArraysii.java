import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目：350两个数组的交集 II
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 */
public class IntersectionOfTwoArraysii {
    public int[] solution(int[] nums1, int[] nums2) {
        //1、使用HashMap,时间复杂度为O(m + n),空间复杂度为O(min(m,n))
        if (nums1.length > nums2.length) {
            //减少哈希表、结果数组的空间
            return solution(nums2, nums1);
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums1) {
            int count = map.getOrDefault(i, 0);
            map.put(i, ++count);
        }

        int[] intersectArray = new int[nums1.length];
        int index = 0;
        for (int i : nums2) {
            int count = map.getOrDefault(i, 0);
            if (count > 0) {
                intersectArray[index++] = i;
            }
            map.put(i, --count);
        }
        return Arrays.copyOfRange(intersectArray, 0, index);


        //2、排序后使用双指针，时间复杂度O(n + m),空间复杂度O(Math.min(m,n))
        // if (nums1 == null || nums2 == null) {
        //     return new int[0];
        // }
        // Arrays.sort(nums1);
        // Arrays.sort(nums2);

        // int i = 0, j = 0;
        // int length1 = nums1.length, length2 = nums2.length;
        // int[] res = new int[Math.min(length1, length2)];
        // int index = 0;
        // while (i < length1 && j < length2) {
        //     if (nums1[i] == nums2[j]) {
        //         res[index++] = nums1[i];
        //         i++;
        //         j++;
        //     } else if (nums1[i] > nums2[j]) {
        //         j++;
        //     } else {
        //         i++;
        //     }
        // }
        // return Arrays.copyOfRange(res, 0, index);
    }
}