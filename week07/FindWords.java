import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/word-search-ii/
 * 212单词搜索二
 * 助教可以帮我review一下，看看还有什么地方可以优化的吗？
 * 这里的时间在500ms左右，但是官方的时间在1ms，差距比较大。
 */
public class FindWords {

    //四联通向量
    int[] horizon = new int[]{1, 0, -1, 0};
    int[] vertical = new int[]{0, 1, 0, -1};
    public List<String> findWords(char[][] board, String[] words) {
        //1、构建字典树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        //去重
        Set<String> ret = new HashSet<>();
        //2、遍历board
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (trie.nodes[board[i][j] - 'a'] != null) {
                    dfs(board, i, j, "", trie, ret);
                }
            }
        }
        return new ArrayList<>(ret);
    }

    public void dfs(char[][] board, int i, int j, String word, Trie trie, Set<String> ret) {
        //terminator
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        }

        char tmp = board[i][j];
        word = word + tmp;
        if (trie.nodes[tmp - 'a'] != null) {
            if (trie.nodes[tmp - 'a'].isEnd) {
                trie.nodes[tmp - 'a'].isEnd = false;
                ret.add(word);
                return;
            }
            board[i][j] = '#';
            for (int k = 0; k < 4; k++) {
                dfs(board, i + horizon[k], j + vertical[k], word, trie.nodes[tmp - 'a'], ret);
            }
            board[i][j] = tmp;
        }
    }
}

/**
 * 前缀树
 */
class Trie {
    boolean isEnd;
    Trie[] nodes = new Trie[26];

    public Trie() {}

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (node.nodes[c - 'a'] == null) {
                node.nodes[c - 'a'] = new Trie();
            }
            node = node.nodes[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (node.nodes[c - 'a'] != null) {
                node = node.nodes[c - 'a'];
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    public boolean perfix(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (node.nodes[c - 'a'] != null) {
                node = node.nodes[c - 'a'];
            } else {
                return false;
            }
        }
        return true;
    }
}
