package org.example;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Solution s = new Solution();
        IO.println(s.majorityElement(new int[]{1,2,3,1,2,2,2,2}));

    }
}

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;  i<nums.length;i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        int majority = nums.length/2;
        int result = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > majority) {
                result = entry.getKey();
            }
        }
        return result;
    }
}
