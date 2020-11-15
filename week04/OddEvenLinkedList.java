/**
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 * 328奇偶链表
 * 时间复杂度O(n)，空间复杂度O(1)
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        //维护两个节点分别拼接奇数链表和偶数链表，最后把偶数链表拼接到奇数链表尾部即可
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            //odd.next指向的下一个Node的next，也就是跳开一个
            odd.next = even.next;
            odd = odd.next;
            //这时的odd指向的已经是even的下一个Node
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
