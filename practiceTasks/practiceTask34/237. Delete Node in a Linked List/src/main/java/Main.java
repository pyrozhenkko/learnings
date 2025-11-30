
class ListNode{
    int val;
    ListNode next;
    ListNode(int x){ val = x;}
}

public class Main {
    public static void main(String[] args) {
        // створимо список: 4 -> 5 -> 1 -> 9
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(9);
        n1.next = n2; n2.next = n3; n3.next = n4;

        Solution s = new Solution();
        s.deleteNode(n2); // видаляємо "5"

        // вивід: 4 1 9
        printList(n1);
    }

    static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }
}
