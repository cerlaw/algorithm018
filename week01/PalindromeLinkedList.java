public class PalindromeLinkedList {
    // ListNode frontPointer = null;

           // public boolean isPalindrome(ListNode head) {
               //将值复制到数组后然后使用双指针，时间复杂度O(n),空间复杂度是O(n)
           //     List<Integer> nums = new ArrayList<>();
           //     ListNode tmp = head;
           //     while (tmp != null) {
           //         nums.add(tmp.val);
           //         tmp = tmp.next;
           //     }

           //     int front = 0;
           //     int tail = nums.size() - 1;
           //     while (front < tail) {
           //         //这里要用equals，因为用的是包装类型，用==大于127和小于-128会出现意料之外的效果
           //         if (!nums.get(front).equals(nums.get(tail))) {
           //             return false;
           //         }
           //         front++;
           //         tail--;
           //     }
           //     return true;


               //使用递归,时间复杂度为O(n),空间复杂度为O(n)
               // frontPointer = head;
               // ListNode backPointer = head;
               // return recursiveCheck(backPointer);


           // }


           // public boolean recursiveCheck(ListNode head) {
           //     if (head != null) {
           //         if (!recursiveCheck(head.next)) {
           //             return false;
           //         }
           //         if (frontPointer.val != head.val) {
           //             return false;
           //         }
           //         frontPointer = frontPointer.next;
           //     }
           //     return true;
           // }

           public boolean isPalindrome(ListNode head) {
               if (head == null) {
                   return true;
               }
               //使用快慢指针，时间复杂度为O(n), 空间复杂度为O(1)
               ListNode firstHalfEnd = fastSlowNode(head);
               ListNode secondHalfStart = reverseNode(firstHalfEnd.next);

               boolean res = true;
               ListNode tmpHead = head;
               ListNode tmpSecondHead = secondHalfStart;
               while (res && tmpSecondHead != null) {
                   if (tmpHead.val != tmpSecondHead.val) {
                       res = false;
                   }
                   tmpHead = tmpHead.next;
                   tmpSecondHead = tmpSecondHead.next;
               }

               reverseNode(secondHalfStart);
               return res;

           }

           /**
            * 翻转链表
            */
           public ListNode reverseNode(ListNode head) {
               ListNode prev = null;
               ListNode currTmp = head;
               while (currTmp != null) {
                   ListNode tmpNext = currTmp.next;
                   currTmp.next = prev;
                   prev = currTmp;
                   currTmp = tmpNext;
               }
               return prev;
           }

           /**
            * 通过快慢指针找到中间节点
            */
           public ListNode fastSlowNode(ListNode head) {
               ListNode slow = head;
               ListNode fast = head;
               while (fast.next != null && fast.next.next != null) {
                   fast = fast.next.next;
                   slow = slow.next;
               }
               return slow;
           }
}