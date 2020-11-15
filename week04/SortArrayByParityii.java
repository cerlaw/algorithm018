/**
 * 922按奇偶排序数组
 * https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityii {
    public int[] sortArrayByParityII(int[] array) {
        //暴力法
        // int[] odds = new int[array.length];
        // int[] evens = new int[array.length];
        // int odd = 0, even = 0;
        // for (int i = 0; i < array.length; i++) {
        //     if (array[i] % 2 == 0) {
        //         evens[even++] = array[i];
        //     } else {
        //         odds[odd++] = array[i];
        //     }
        // }
        // odds = Arrays.copyOfRange(odds, 0, odd);
        // evens = Arrays.copyOfRange(evens, 0, even);
        //看题解后这两步排序是没有必要，没有要求升序排列，对题目的理解不够
        // // Arrays.sort(odds);
        // // Arrays.sort(evens);

        // odd = 0;
        // even = 0;
        // for (int i = 0; i < array.length; i++) {
        //     if (i % 2 == 0) {
        //         array[i] = evens[even++];
        //     } else {
        //         array[i] = odds[odd++];
        //     }
        // }
        // return array;

        //使用双指针对原数组进行修改
        int n = array.length;
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (array[i] % 2 == 1) {
                while (array[j] % 2 == 1) {
                    j += 2;
                }
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        return array;
    }
}
