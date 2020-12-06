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


### 双向BFS代码模板
```java
public class TwoEndedBfs {
    
    public int twoEndedBfs(String begin, String end, String end) {
        
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        int step = 1;
        
        beginSet.add(begin);
        endSet.add(end);
        visited.add(begin);
        visited.add(end);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            int size = beginSet.size();
            Set<String> temp = new HashSet<>();
            for (String s : beginSet) {
                if (endSet.contains(s)) {
                    return step + 1;
                }
                //...
                tmp.add(new String());
                //...
            }
            beginSet = temp;
            if (beginSet.size() > endSet.size()) {
                swap(beginSet, endSet);
            }
            step++;
        }
        return 0;
    }
}
```

## 启发式搜索（Heuristic Search、A*）

一边搜索一边有思考在里面 --- 思考型搜索

基于BFS，把里面的queue替换成priority queue，这样的话，每次都先查找优先级高的结点，提高效率。

定义哪些结点优先级高的关键就是估价函数

### 估价函数

一种告知搜索方向的方法。它提供了一种明智的方法来预测哪个邻居结点会导向一个目标。

### A*模板
```java
//Java
	public class AStar
	{
		public final static int BAR = 1; // 障碍值
		public final static int PATH = 2; // 路径
		public final static int DIRECT_VALUE = 10; // 横竖移动代价
		public final static int OBLIQUE_VALUE = 14; // 斜移动代价
		
		Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
		List<Node> closeList = new ArrayList<Node>();
		
		/**
		 * 开始算法
		 */
		public void start(MapInfo mapInfo)
		{
			if(mapInfo==null) return;
			// clean
			openList.clear();
			closeList.clear();
			// 开始搜索
			openList.add(mapInfo.start);
			moveNodes(mapInfo);
		}
	

		/**
		 * 移动当前结点
		 */
		private void moveNodes(MapInfo mapInfo)
		{
			while (!openList.isEmpty())
			{
				Node current = openList.poll();
				closeList.add(current);
				addNeighborNodeInOpen(mapInfo,current);
				if (isCoordInClose(mapInfo.end.coord))
				{
					drawPath(mapInfo.maps, mapInfo.end);
					break;
				}
			}
		}
		
		/**
		 * 在二维数组中绘制路径
		 */
		private void drawPath(int[][] maps, Node end)
		{
			if(end==null||maps==null) return;
			System.out.println("总代价：" + end.G);
			while (end != null)
			{
				Coord c = end.coord;
				maps[c.y][c.x] = PATH;
				end = end.parent;
			}
		}
	

		/**
		 * 添加所有邻结点到open表
		 */
		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
		{
			int x = current.coord.x;
			int y = current.coord.y;
			// 左
			addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
			// 上
			addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
			// 右
			addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
			// 下
			addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
			// 左上
			addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
			// 右上
			addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
			// 右下
			addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
			// 左下
			addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
		}
	

		/**
		 * 添加一个邻结点到open表
		 */
		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
		{
			if (canAddNodeToOpen(mapInfo,x, y))
			{
				Node end=mapInfo.end;
				Coord coord = new Coord(x, y);
				int G = current.G + value; // 计算邻结点的G值
				Node child = findNodeInOpen(coord);
				if (child == null)
				{
					int H=calcH(end.coord,coord); // 计算H值
					if(isEndNode(end.coord,coord))
					{
						child=end;
						child.parent=current;
						child.G=G;
						child.H=H;
					}
					else
					{
						child = new Node(coord, current, G, H);
					}
					openList.add(child);
				}
				else if (child.G > G)
				{
					child.G = G;
					child.parent = current;
					openList.add(child);
				}
			}
		}
	

		/**
		 * 从Open列表中查找结点
		 */
		private Node findNodeInOpen(Coord coord)
		{
			if (coord == null || openList.isEmpty()) return null;
			for (Node node : openList)
			{
				if (node.coord.equals(coord))
				{
					return node;
				}
			}
			return null;
		}
	

	

		/**
		 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
		 */
		private int calcH(Coord end,Coord coord)
		{
			return Math.abs(end.x - coord.x)
					+ Math.abs(end.y - coord.y);
		}
		
		/**
		 * 判断结点是否是最终结点
		 */
		private boolean isEndNode(Coord end,Coord coord)
		{
			return coord != null && end.equals(coord);
		}
	

		/**
		 * 判断结点能否放入Open列表
		 */
		private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
		{
			// 是否在地图中
			if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
			// 判断是否是不可通过的结点
			if (mapInfo.maps[y][x] == BAR) return false;
			// 判断结点是否存在close表
			if (isCoordInClose(x, y)) return false;
	

			return true;
		}
	

		/**
		 * 判断坐标是否在close表中
		 */
		private boolean isCoordInClose(Coord coord)
		{
			return coord!=null&&isCoordInClose(coord.x, coord.y);
		}
	

		/**
		 * 判断坐标是否在close表中
		 */
		private boolean isCoordInClose(int x, int y)
		{
			if (closeList.isEmpty()) return false;
			for (Node node : closeList)
			{
				if (node.coord.x == x && node.coord.y == y)
				{
					return true;
				}
			}
			return false;
		}
	}
```

## AVL树和红黑树的实现和特性

二叉树有些特殊的情况下回退化成链表，失去了优化的意义。

### 保证性能的关键

1. 保证二维维度！ -> 左右子树结点平衡（recursively）
2. Balanced


### AVL树
1. 名字来源于他的发明者
2. Balance Factor （平衡因子）：是它的左子树的**高度**减去它的右子树的**高度**（有时相反） {-1,0,1}
3. 通过旋转操作来进行平衡（四种）
4. 平衡二叉树的一种实现
5. 不足：结点需要存储额外信息、且调整次数频繁

#### 旋转操作

根据不同的子树形态进行不同的旋转操作
1. 左旋： 右右子树
2. 右旋： 左左子树
3. 左右旋：左右子树
4. 右左旋：右左子树

### 红黑树

红黑树是一种**近似平衡**的二叉搜索树，它能够确保任何一个结点的左右子树的高度差**小于两倍**。
具体来说，红黑树是满足如下条件的二叉搜索树：
- 每个结点要么是红色，要么是黑色
- 根结点是黑色
- 每个叶结点（NIL结点，空结点）是黑色的。
- 不能有相邻接的两个红色结点
- 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点。

### 对比

- AVL trees provide **faster lookups** than Red Black Trees because they are 
**more strictly balanced**
- Red Black Trees provide **faster insertion and removal** operations than AVL
trees as fewer rotations are done due to relatively relaxed balancing.
- AVL trees stroe **balance factor or heights** with each node, thus requries storage
fir an integer per node whereas Red Black Tree requires only 1 bit of information per node
- Red Black Trees are used int most of the **language libraries like map, multimap,
multisetin C++** whereas AVL trees are used in **databases** where faster retrievals are required.