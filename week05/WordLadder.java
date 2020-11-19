import java.util.*;

/**
 * 127单词接龙
 * https://leetcode-cn.com/problems/word-ladder/
 * @author zhanghongjie
 * @date 2020/11/19
 */
public class WordLadder {

    /**
     * 单向广度遍历
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) {
            return 0;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int step = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] cs = word.toCharArray();
                for (int j = 0; j < cs.length; j++) {
                    char c =  cs[j];
                    //对每一位的char进行遍历看有没有在字典中存在的
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (c == k) {
                            //相同字符，跳过
                            continue;
                        }
                        cs[j] = k;
                        String tmp = new String(cs);
                        if (wordSet.contains(tmp) && !visited.contains(tmp)) {
                            if (endWord.equals(tmp)) {
                                //找到了，返回步数
                                return step + 1;
                            } else {
                                visited.add(tmp);
                                queue.add(tmp);
                            }
                        }
                    }
                    //恢复以进行下一次查找
                    cs[j] = c;
                }
            }
            step++;
        }
        return 0;
    }


    /**
     * 双向广度遍历
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) {
            return 0;
        }
        Set<String> wordSet = new HashSet<>(wordList);

        Set<String> visited = new HashSet<>();
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);

        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);
        int step = 1;

        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            //保证beginSet中的元素都是少的那一方
            if (beginVisited.size() > endVisited.size()) {
                Set<String> tmpSet = beginVisited;
                beginVisited = endVisited;
                endVisited = tmpSet;
            }

            //存储下一层访问的String
            Set<String> nextLevelVisited = new HashSet<>();
            for (String s : beginVisited) {
                char[] cs = s.toCharArray();
                for (int j = 0; j < cs.length; j++) {
                    char c =  cs[j];
                    //对每一位的char进行遍历看有没有在字典中存在的
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (c == k) {
                            continue;
                        }
                        cs[j] = k;
                        String tmp = new String(cs);
                        if (wordSet.contains(tmp)) {
                            if (endVisited.contains(tmp)) {
                                //找到了，返回步数
                                return step + 1;
                            }
                            if (!visited.contains(tmp)) {
                                visited.add(tmp);
                                nextLevelVisited.add(tmp);
                            }
                        }
                    }
                    //恢复以进行下一次查找
                    cs[j] = c;
                }
            }
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }
}
