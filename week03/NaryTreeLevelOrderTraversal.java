import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * N叉树的层序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 */
public class NaryTreeLevelOrderTraversal {

    /**
     * dfs递归遍历，时间复杂度O(n)，空间复杂度O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> solution(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        levelTraversal(root, res, 0);
        return res;
    }

    private void levelTraversal(Node root, List<List<Integer>> res, int level) {
        if (level <= res.size()) {
            //level 小于res size的时候意味着是新的一层
            res.add(new ArrayList<>());
        }
        //current logic
        res.get(level).add(root.val);
        for (Node child : root.children) {
            //drill down
            levelTraversal(child, res, level + 1);
        }
    }

    /**
     * bfs 广度优先遍历，时间复杂度O(n)，空间复杂度O(n)
     * 层序遍历的模板
     * @param root
     * @return
     */
    public List<List<Integer>> solution2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                currentLevel.add(tmp.val);
                queue.addAll(tmp.children);
            }
            res.add(currentLevel);
        }
        return res;
    }

}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
