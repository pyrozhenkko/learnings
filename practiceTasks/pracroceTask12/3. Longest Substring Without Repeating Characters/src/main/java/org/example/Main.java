package org.example;

import java.util.HashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));

    }
}
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int left = 0;
        for(int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            while(set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(ch);
            int len = right - left + 1;
            max = Math.max(max, len);
        }
        return max;

    }
}
