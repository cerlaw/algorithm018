#Week7学习笔记

### 核心思想

- Trie树的核心思想是空间换时间
- 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的
- 一次构建，多次查询

### 个人理解

Trie是一棵多叉树，每一叉都是代表着一个字母，以小写字母为例的话，Trie最多能有26叉。

### 代码
```java
//26个小写字母的Trie
public class Trie {
    
    boolean isEnd;
    Trie[] nodes = new Trie[26];
    
    public Trie() {}
    
    public void insert(String word) {
        Trie node = this;
        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            //如果当前节点没有被访问过
            if (node.nodes[c - 'a'] == null) {
                //为其新建一个新的节点
                node.nodes[c - 'a'] = new Trie();
            }
            //当前节点已经访问过了，返回该节点的引用
            node = node.nodes[c - 'a'];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        Trie node = this;
        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            if (node.nodes[c - 'a'] != null) {
                //当前的char被访问过，继续进入到下一层
                node = node.nodes[c - 'a'];
            } else {
                //当前节点没有存储记录，直接返回false
                return false;
            }
        }
        //最后判断是否是结尾
        return node.isEnd;
    }
    
    public boolean startWith(String prefix) {
        Trie node = this;
        char[] wordChars = word.toCharArray();
        for (char c : wordChars) {
            if (node.nodes[c - 'a'] != null) {
                node = node.nodes[c - 'a'];
            } else {
                return false;
            }
        }
        //判断前缀的话不需要判断是否到了结尾
        return true;
    }
    
}
```