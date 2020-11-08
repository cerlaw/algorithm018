import java.util.ArrayList;
import java.util.List;

/**
 * 从尾到头打印链表
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class ReversePrint {
    public int[] reversePrint(ListNode root) {
        List<Integer> list = new ArrayList<>();
        reversePrintCore(root, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void reversePrintCore(ListNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        reversePrintCore(node.next, list);
        list.add(node.val);
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
