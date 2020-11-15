#Week4学习笔记

## 深度优先搜索、广度优先搜索

两者本质上就是一种暴力搜索，简单朴素的搜索

### 搜索 - 遍历

- 每个节点**都要**访问一次
- 每个节点**仅仅只要**访问一次
- 对于访问节点的顺序不同可分为
    - 深度优先搜索：Depth First Search(DFS)
    - 广度优先搜索：Breath First Search（BFS）

### 深度优先搜索代码模板(递归实现)
```java
public class LevelOrderWithDFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        travel(root, 0, allResults);
        return allResults;
    }
    
    public void travel(TreeNode root, int level, List<List<Integer>> allResults) {
        if (level == allResults.size()) {
            allResults.add(new ArrayList<>());
        }
        allResults.get(level).add(root.val);
        for(TreeNode node : root.children) {
            travel(children, level + 1, allResults);
        }
    }
}
```
如果不用递归的话，手动维护一个栈去模拟

### 广度优先搜索代码模板（使用队列）
对于广度优先搜索可以想象是一个水滴滴到湖水中间，向四周扩散出去的纹路
```java
public class LevelOrderWithBFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            results.add(level);
        } 
        return results;
    }
}
```

## 贪心算法

###贪心算法是什么

贪心算法是一种在每一步选择中都选取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法

贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，*不能回退*。动态规划则会*保存以前的运算结果*，
并根据以前的结果对当前进行选择，*有回退功能*。

- 贪心：当下做局部最优判断
- 回溯：能够回退
- 动态规划：最优判断 + 回退

贪心法一般用于解决一些最优化的问题，对于工程和生活中的问题，贪心法一般不能得到我们所要的答案。

一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求得的答案
比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。

###贪心算法使用场景

- 问题能够分解成子问题
- 子问题的最优解能够递推到最终问题的最优解

那么这种子问题最优解称为**最优子结构**

贪心算法重点是要证明为什么使用贪心可以获得最优解！比方说硬币问题，因为20,10,5,1呈倍数关系，
所以在组合的时候可以用贪心来取得局部的最优解。

贪心查找即可以从前往后、从后往前也可以从某个局部开始贪心。

```java
while(能够朝给定总目标前进一步) {
    利用可行的决策，求出一个可行解元素
}
由所有可解元素组合成问题的一个可行解
```

##二分查找

###二分查找的前提

- 目标函数单调性（单调递增或者递减）
- 存在上下界（bounded）
- 能够通过索引访问（index accessible）

###模板(牢记)
```java
public class BinarySearch {
    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (array[mid] == target) {
                //find the target,break or return
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

```

### 牛顿法 

在力扣69：x的平方根题目中可以使用牛顿法去逼近。