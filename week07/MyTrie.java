/**
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * 208 实现trie
 */
public class MyTrie {
    boolean isEnd;
    MyTrie[] nodes = new MyTrie[26];

    /** Initialize your data structure here. */
    public MyTrie() {
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        MyTrie node = this;
        for (char cs : word.toCharArray()) {
            if (node.nodes[cs - 'a'] == null) {
                node.nodes[cs - 'a'] = new MyTrie();
            }
            node = node.nodes[cs - 'a'];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        MyTrie node = this;
        for (char cs : word.toCharArray()) {
            if (node.nodes[cs - 'a'] != null) {
                node = node.nodes[cs - 'a'];
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        MyTrie node = this;
        for (char cs : prefix.toCharArray()) {
            if (node.nodes[cs - 'a'] != null) {
                node = node.nodes[cs - 'a'];
            } else {
                return false;
            }
        }
        return true;
    }
}
