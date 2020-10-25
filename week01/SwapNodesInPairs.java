public class SwapNodesInPairs {
    public ListNode solution(ListNode head) {
        //递归
        // if (head == null || head.next == null) {
        //     return head;
        // }
        // ListNode newHead = head.next;
        // head.next = swapPairs(newHead.next);
        // newHead.next = head;
        // return newHead;

        //迭代
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode tmp = dummyHead;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode node1 = tmp.next;
            ListNode node2 = tmp.next.next;

            node1.next = node2.next;
            tmp.next = node2;
            node2.next = node1;
            tmp = node1;
        }
        return dummyHead.next;
    }
}