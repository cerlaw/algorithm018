# week9学习笔记

## 动态规划、状态转移方程串讲

### 递归的重要三点

1. 人肉递归低效、很累
2. 找到最近最简方法，将其拆解成可重复解决的问题
3. 数学归纳法思维

本质：寻找重复性 -》 计算指令集（for while if-else）

### 动态规划

1. 将一个复杂的问题分解成为各个简单的子问题
2. 分治 + 最优子结构
3. 顺推形式：动态递推

#### DP 顺推模板

```java
class DP {
    int[][] dp = new int[m][n]; // 二维情况
    
    public int dp() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dpLogic();
            }        
        }
        return dp[m][n];
    }
    
}
```
#### 难点：
1. dp状态的定义（需要经验），把现实问题定义为数组，一维的，状态不够的定义二维，还不够就定义三维的
2. 状态转移方程，如fibonacci数列、最值等

本质：内功、逻辑思维、数学

#### 关键点

动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构）

两者的共性：找到重复性以及找到重复子问题
两者的差异性：最优子结构、中途可以淘汰次优解

#### 作业：不同路径2的状态转移方程

dp[i][j] = if (i, j上有障碍物) 0 else dp[i - 1][j] + dp[i][j - 1]

#### 关于两个字符串的dp问题

一般使用二维数组进行解决，一维代表一个字符串


### 字符串基础知识和引申题目


### 字符串匹配算法

#### 1.暴力法

##### 代码示例

```java
public class BruteForce {
    public static int forceSearch(String txt, String pat) {
        int M = txt.length();
        int N = pat.length();
        for (int i = 0; i <= M - N; i++) {
            int j;
            for (j = 0; j < N; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                break;
            }
            if (j == N) {
                return i;
            }
            // 更加聪明？         
            // 1. 预先判断 hash(txt.substring(i, M)) == hash(pat)     
            // 2. KMP 
        }
        return -1; 
    }
}
```

#### 2.Rabin-Karp算法
在朴素算法中，我们需要挨个比较所有字符，才知道目标字符串中是否包含子串。
那么，是否有别的方法可以用来判断目标字符村是否包含子串那？

答案是肯定的，确实存在一种更快的方法，为了避免挨个字符对目标字符串和子串进行比较，
我们可以尝试一次性判断两者是否相等。因此，我们需要一个好的哈希函数（hash function）。
通过哈希函数，我们可以算出子串的哈希值，然后它和目标字符串中的子串的哈希值进行比较。
这个新方法在速度上比暴力法有显著提升。

类似于滑动窗口的方法

##### 思想：
1、假设子串的长度为M（pat），目标字符串的长度为N（txt）
2、计算子串的hash之hash_pat
3、计算目标字符串txt中每个长度为M的子串的hash值（共需要计算N-M+1次）
4、比较hash值：如果hash值不同，字符串必然不匹配；如果hash值相同，还需要使用朴素算法再次比较

##### 代码示例

```java
public class RabinKarp {
    public final static int D = 256;
    public final static int Q = 9997;
    static int RabinKarpSerach(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int patHash = 0, txtHash = 0;
        for (i = 0; i < M; i++) {
            patHash = (D * patHash + pat.charAt(i)) % Q;
            txtHash = (D * txtHash + txt.charAt(i)) % Q;
        }
        int highestPow = 1;  // pow(256, M-1)
        for (i = 0; i < M - 1; i++)
            highestPow = (highestPow * D) % Q;
        for (i = 0; i <= N - M; i++) { // 枚举起点   
            if (patHash == txtHash) { 
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
               }
               if (j == M)
                   return i;
           }
           if (i < N - M) {
              txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;   
              if (txtHash < 0)
                    txtHash += Q;
           }
       }
       return -1;
    }
}
```

#### 3.KMP算法


##### 思想

当子串与目标字符串不匹配时，其实你已经知道了前面已经匹配成功的那一部分的字符
（包括子串和目标字符串）。设法利用这个已知信息，不要把“搜索位置”移回已经比较过的位置，
继续把它向后移，这样就提高了效率。