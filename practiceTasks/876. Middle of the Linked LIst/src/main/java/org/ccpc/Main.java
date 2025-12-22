package org.ccpc;


class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val = val;
    }
}

public class Main {
    public static void main(String[] args) {


    }
}

class Solution {
    public ListNode MiddleNode(ListNode head) {
        ListNode  a_pointer = head;
        ListNode b_pointer = head;
        while (b_pointer.next != null && b_pointer.next.next != null){
            a_pointer = a_pointer.next;
            b_pointer = b_pointer.next.next;
        }
        return a_pointer;
    }
}