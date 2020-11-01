学习笔记

#HashMap的小总结（根据JDK 1.8j进行分析）

## 初始化

默认容量为16，默认的扩展因子是0.75

## put

1、首先通常调用put方法来向HashMap中添加元素
```java
public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
```

2、看到首先获取到key的hash值
```java
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
这里可以看到HashMap是可以传null的key的，再在计算null的hash值会返回0，
否则会将key的哈希code的前16位与后16位进行异或从而得出key的hash，
而进行这种异或操作应该是为了减少hash冲突。

3、putVal实现
```java
/**
     * Implements Map.put and related methods
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //判断table这个数组是否为null或者长度小于0，是的话初始化该数组
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            //如果当前位置没有node的话，直接把Node放入
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                //如果key相同，赋值给e
                e = p;
            else if (p instanceof TreeNode)
                //如果当前的node是TreeNode，并且key已经存在在Tree中的话会返回已存在的node，否则的话插入到树中并返回null
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                //循环的在链表或者树中查找key
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        //把新节点放到链表的尾部
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            //当链表的size超过8个的话，将链表转换为树提高搜索的效率
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            //e != null意味着e已经存在在Map中了，更新旧值并返回旧值
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            //检查容量是否超过阈值，是的话放大map的容量到原来的两倍
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

关于(n - 1) & hash == hash % n
因为n是数组的长度，而数组的长度n都是2的k次方，hash / n 相当于向右移k位，hash % n 相当于被移除的k位数，
这样的话，（n - 1） & hash在二进制看来就相当于取hash最后的k位。

##get
1、get(Object key)
```java
public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
```

可以看到get实际上调用的是getNode方法

2、getNode(int hash, Object key)
```java
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    //在树中查找节点
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                //链表中查找节点
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

## 总结

1、从put和get方法中可以看到，在1.8中HashMap做的一些优化的话就是在出现大量的哈希碰撞的时候，
会从链表优化成树形结构从而提高查询的效率
2、使用了一些位运算来代替取模运算，提高计算的速度

## 疑问
HashMap线程不安全的原因？



#有向有权图

![有向有权图](week02/有向有权图.jpg)