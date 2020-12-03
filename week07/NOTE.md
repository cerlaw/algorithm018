# Week7学习笔记

## Trie树的基本实现和特性

### 基本结构

字典树，即Trie树，又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串）。
所以经常被搜索引擎系统用于文本词频统计。

### 优点

最大限度地减少无畏的字符串比较，查询效率比哈希表高。

### 基本性质
1. 结点本身不存完整单词
2. 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串
3. 每个结点的所有子结点路径代表的字符都不相同

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

## 并查集

遇到这类题目，调用模板直接套上去即可

### 使用场景

判断两个个体是不是在一个集合当中
- 组团、配对问题
- Group or not

### 基本操作

- makeSet(s):建立一个新的并查集，其中包含s个元素集合
- unionSet(x, y):把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果系相交则不合并
- find(x):找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。

### 模板代码

```java
class UnionFind {
    //多少个独立的节点
    private int count = 0;
    private int[] parent;
    public UnionFind(int n) {
        count = n;
        parent = new Parent[n];
        for (int i = 0; i < count; i++) {
            //自己的父节点就是自己
            parent[i] = i;
        }
    }
    
    public int findParent(int p) {
        //带路径压缩的
        /*if (p == parent[p]) {
            return p;
        }
        parent[p] = findParent(parent[p]);
        return parent[p];*/
        //不带路径压缩
        return p == parent[p] ? p : findParent(parent[p]);
    }
    
    public void union(int p, int q) {
        int parentP = findParent(p);
        int parentQ = findParent(q);
        if (parentP == parentQ) {
            return;
        }
        //p指向q
        parent[parentP] = parentQ;
        count--;
    }
}
```

## 剪枝的实现和特性

### 初级搜索

1. 朴素搜索
2. 优化方式：1、不重复（fibonacci）2、剪枝（生成括号问题）
3. 搜索方向：DFS、BFS
- 双向搜索
- 启发式搜索（使用优先队列），又叫A*算法或者优先级搜索
