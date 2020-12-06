/**
 * https://leetcode-cn.com/problems/friend-circles/
 * 547 朋友圈
 */
public class FindCircleNum {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int m = M.length, count = 0;
        boolean[] visited = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                dfs(M, visited, i, m);
                count++;
            }
        }
        return count;
    }

    /**
     * 通过dfs 找到一个同学所在朋友圈关联的所有同学
     * @param M
     * @param visited
     * @param i
     * @param m
     */
    public void dfs(int[][] M, boolean[] visited, int i, int m) {
        //这里不能j = i 这样的话会跳过某些同学
        for (int j = 0; j < m; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j, m);
            }
        }
    }


    /**
     * 通过并查集解决
     * @param M
     * @return
     */
    public int findCircleNumWithUnion(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int m = M.length;
        UnionFind unionFind = new UnionFind(m);
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getCount();
    }

    class UnionFind {
        int count;
        int[] parent;

        public UnionFind(int count) {
            this.count = count;
            parent = new int[count];
            for (int i = 0; i < count; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            if (p == parent[p]) {
                return p;
            }
            //路径压缩
            parent[p] = find(parent[p]);
            return parent[p];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            parent[rootP] = rootQ;
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
