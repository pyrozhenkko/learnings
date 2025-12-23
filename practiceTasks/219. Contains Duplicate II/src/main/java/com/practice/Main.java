package com.practice;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(sol.containsNearbyDuplicate(nums, k)); // true
    }
}
class Solution{
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], i);
            }
            else{
                int diff = Math.abs(nums[i]-i);
                if(diff < k){
                    return true;
                }
                else{
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }
}