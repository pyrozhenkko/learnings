package org.ccpc;

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val=x;
        next=null;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // приклад списку без циклу
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        System.out.println(sol.hasCycle(head)); // false

        // приклад списку з циклом
        ListNode head2 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        head2.next = node2;
        node2.next = node3;
        node3.next = node2; // цикл
        System.out.println(sol.hasCycle(head2)); // true
    }
}
class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head.next;
        while(slow != fast){
            if(fast==null||fast.next==null){
                return false;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }
}