import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
 * 1091二进制矩阵中的最短路径
 * BFS
 * A*
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 */
public class ShortestPathBinaryMatrix {
    class Solution {
        class Node {
            int row;
            int col;
            int distance;
            int step;

            public Node(int row, int col, int distance, int step) {
                this.row = row;
                this.col = col;
                this.distance = distance;
                this.step = step;
            }
        }

        /**
         * 使用BFS
         */
//        public int shortestPathBinaryMatrix(int[][] grid) {
//            int length = grid.length;
//            if (grid[0][0] == 1 || grid[length - 1][length - 1] == 1) {
//                return -1;
//            }
//
//            //八连通向量
//            int[] horizon = new int[]{1, 0, -1, 0, 1, -1, 1, -1};
//            int[] vertical = new int[]{0, 1, 0, -1, 1, -1, -1, 1};
//
//            int step = 1;
//            //BFS使用队列
//            Queue<Node> queue = new LinkedList<>();
//            //A*使用优先队列
////            PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
//            queue.offer(new Node(0, 0, length - 1 + length - 1));
//            grid[0][0] = 1;
//            while (!queue.isEmpty()) {
//                int size = queue.size();
//                for (int i = 0; i < size; i++) {
//                    Node node = queue.poll();
//                    if (node.row == length - 1 && node.col == length - 1) {
//                        return step;
//                    }
//                    for (int j = 0; j < 8; j++) {
//                        int newRow = node.row + horizon[j];
//                        int newCol = node.col + vertical[j];
//                        if (newRow >= 0 && newCol >= 0 && newRow < length && newCol < length) {
//                            if (grid[newRow][newCol] == 0) {
//                                grid[newRow][newCol] = 1;
//                                queue.offer(new Node(newRow, newCol, (length - 1) * 2 - newRow - newCol));
//                            }
//                        }
//                    }
//                }
//                step++;
//            }
//            return -1;
//        }

        /**
         * 使用启发式搜索，但是一直通过不了，可以的话助教也帮我review一下
         *
         * 不通过测试用例[[0,1,0,0,0,0],[0,1,1,1,1,1],[0,0,0,0,1,1],[0,1,0,0,0,1],[1,0,0,1,0,1],[0,0,1,0,1,0]]
         *
         * @param grid
         * @return
         */
        public int shortestPathBinaryMatrix(int[][] grid) {
            int length = grid.length;
            if (grid[0][0] == 1 || grid[length - 1][length - 1] == 1) {
                return -1;
            }

            //八连通向量
            int[] horizon = new int[]{1, 0, -1, 0, 1, -1, 1, -1};
            int[] vertical = new int[]{0, 1, 0, -1, 1, -1, -1, 1};

            int step = 1;
            // Queue<Node> queue = new LinkedList<>();
            //优先取离终点近的点
            PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
            queue.offer(new Node(0, 0, length - 1 + length - 1, step));
            grid[0][0] = 1;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                System.out.println("row: " + node.row + " col: " + node.col);
                if (node.row == length - 1 && node.col == length - 1) {
                    return node.step;
                }
                for (int j = 0; j < 8; j++) {
                    int newRow = node.row + horizon[j];
                    int newCol = node.col + vertical[j];
                    if (newRow >= 0 && newCol >= 0 && newRow < length && newCol < length) {
                        if (grid[newRow][newCol] == 0) {
                            grid[newRow][newCol] = 1;
                            // System.out.println("newRow: " + newRow + " newCol: " + newCol);
                            //新的node都比现在的node的step多1
                            queue.offer(new Node(newRow, newCol,(length - 1) * 2 - newRow - newCol, node.step + 1));
                        }
                    }
                }
            }
            return -1;
        }
    }
}
