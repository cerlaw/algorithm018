#Week3学习笔记

## 递归

### 为什么树相关的题目一般使用递归去实现？

- 因为树本身的定义和节点的定义决定的
- 树的节点本身具有重复性和相似性

### 什么是递归

递归的本质是：*循环*，通过函数体来进行循环

### 递归代码的四层结构（牢记）

- Recursion Terminator：递归终结条件
- Current level logic： 处理当前层的逻辑
- drill down：下探到下一层
- reverse status：清理当前层（不是必须的）

### Java的递归代码模板
```java
public void recursion(int level, int param) {
    //terminator
    if (level >= MAX_LEVEL) {
        //process result
        return;    
    }
    
    //process current logic
    process(level, param);
    
    //drill down
    recursion(level + 1, param);
    
    //restore current status
}
```
### 问题
- 在使用模板过程出现的问题，当前层逻辑的实现有时难以找准，
导致没有完全找到所有的递归层级。如生成括号题目中的两个if 写成了一个if else if，
导致好多条件没有递归到。

- 有些参数传递的是引用的时候，要注意清理当前层逻辑以及在添加到结果的时候要新建对象存储，
否则在清理逻辑的时候影响到结果。


## 树的遍历

树的各种遍历的模板代码要熟记

## 分治

本质上还是*递归*，是递归中的一个分支。重点还是找**重复性**。

分治比普通的递归过了一步，在drill down和reverse status之间多了一步，
拼接成结果返回。

## 回溯
试错的思想，在每一层逻辑中不断的去试。

模板
```java
public int divide_conquer(Problem problem) {
    if (problem == null) {
        int res = process_last_result();
        return res;
    }
    subProblems = split_problem(problem);
    res0 = divide_conquer(subProblem[0]);
    res1 = divide_conquer(subProblem[1]);
    
    result = process_result(res0, res1);
    return result;
}
```