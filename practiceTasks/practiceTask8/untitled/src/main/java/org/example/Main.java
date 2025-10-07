package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));

        Solution solution = new Solution();
        boolean isPalindrome = solution.isPalindrome(head);

        IO.println("Is the linked list a palindrome? " + isPalindrome);

    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val){this.val = val; }
    ListNode(int val, ListNode next){ this.val = val; this.next = next; }
}

class Solution {
    public  boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null){
            list.add(current.val);
            current = current.next;
        }
        int left = 0, right = list.size()-1;
        while(left < right){
            if(list.get(left) != list.get(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
