import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhanghongjie
 * @date 2020/11/11
 */
public class Permutations {

    /**
     * 按照递归模板写
     * @param nums
     * @return
     */
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        permuteCore(res, nums, new ArrayList<>());
//        return res;
//    }
//
//    private void permuteCore(List<List<Integer>> res, int[] nums, ArrayList<Integer> level) {
//        if (level.size() == nums.length) {
//            //terminator
//            res.add(new ArrayList<>(level));
//            return;
//        }
//
//        //current logic
//        for (int i : nums) {
//            if (!level.contains(i)) {
//                level.add(i);
//                //drill down
//                permuteCore(res, nums, level);
//                //restore
//                level.remove(level.size() - 1);
//            }
//        }
//
//    }

    /**
     * 按照递归模板写，在国外站看到，使用ArrayList的contains的时间复杂度为O(n)，可以使用HashSet来优化
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        permuteCore(res, set, nums, new ArrayList<>());
        return res;
    }

    private void permuteCore(List<List<Integer>> res, Set<Integer> set, int[] nums, ArrayList<Integer> level) {
        if (level.size() == nums.length) {
            //terminator
            res.add(new ArrayList<>(level));
            return;
        }

        //current logic
        for (int i : nums) {
            if (!set.contains(i)) {
                set.add(i);
                level.add(i);
                //drill down
                permuteCore(res, set, nums, level);
                //restore
                level.remove(level.size() - 1);
                set.remove(i);
            }
        }

    }
}
