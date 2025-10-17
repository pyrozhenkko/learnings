package org.example;

public class Main {
    static void main() {
        Solution s = new Solution();
        System.out.println(s.qwe(new int[]{1,2,0,3, 2, 3, 4}));

    }
}
class Solution {
    boolean qwe(int[] nums) {
        if (nums.length == 1) return true;
        int lastIndex = nums.length - 1;
        int maxReach = nums[0];
        for (int i = 1; maxReach >= i; i++) {
            System.out.println("i = " + i + ", maxReach = " + maxReach + ", nums[i] = " + nums[i]);
            if(maxReach >= lastIndex) return true;
            else{
                maxReach = Math.max(maxReach, nums[i] + i);
            }
        }
        return false;
    }
}
