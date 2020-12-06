import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/
 * 433 最小基因变化
 */
public class MinMutation {
    //双向bfs
    public int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) {
            return -1;
        }
        List<String> bankList = Arrays.asList(bank);
        Set<String> bankSet = new HashSet<>(bankList);
        if (!bankSet.contains(end)) {
            return -1;
        }
        List<Character> geneSet = Arrays.asList('A', 'C', 'G', 'T');
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(start);
        endSet.add(end);
        bankSet.remove(start);
        bankSet.remove(end);
        int step = 0;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            Set<String> tempSet = new HashSet<>();
            for (String s : beginSet) {
                char[] cs = s.toCharArray();
                for (int i = 0; i < cs.length; i++) {
                    char old = cs[i];
                    for (Character ch : geneSet) {
                        if (old == ch) {
                            continue;
                        }
                        cs[i] = ch;
                        String temp = new String(cs);
                        if (endSet.contains(temp)) {
                            return step + 1;
                        }
                        if (bankSet.contains(temp)) {
                            tempSet.add(temp);
                            bankSet.remove(temp);
                        }
                    }
                    cs[i] = old;
                }
            }
            beginSet = tempSet;
            if (beginSet.size() > endSet.size()) {
                beginSet = endSet;
                endSet = tempSet;
            }
            step++;
        }
        return -1;
    }

    //单向bfs
    // public int minMutation(String start, String end, String[] bank) {
    //     if (bank == null || bank.length == 0) {
    //         return -1;
    //     }
    //     List<String> bankList = Arrays.asList(bank);
    //     Set<String> bankSet = new HashSet<>(bankList);
    //     List<Character> geneSet = Arrays.asList('A', 'C', 'G', 'T');
    //     Queue<String> queue = new LinkedList<>();
    //     queue.offer(start);
    //     int step = 0;
    //     while (!queue.isEmpty()) {
    //         int size = queue.size();
    //         for (int i = 0; i < size; i++) {
    //             String s = queue.poll();
    //             char[] cs = s.toCharArray();
    //             for (int j = 0; j < cs.length; j++) {
    //                 char old = cs[j];
    //                 for (Character ch : geneSet) {
    //                     if (old == ch) {
    //                         continue;
    //                     }
    //                     cs[j] = ch;
    //                     String temp = new String(cs);
    //                     if (bankSet.contains(temp)) {
    //                         if (end.equals(temp)) {
    //                             return step + 1;
    //                         } else {
    //                             queue.offer(temp);
    //                             bankSet.remove(temp);
    //                         }

    //                     }
    //                 }
    //                 cs[j] = old;
    //             }
    //         }
    //         step++;
    //     }
    //     return -1;
    // }
}
