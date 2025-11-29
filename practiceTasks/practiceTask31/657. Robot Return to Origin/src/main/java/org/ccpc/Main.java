package org.ccpc;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String moves = Arrays.toString(new String[]{"U, D, Rr"});
        System.out.println(moves);
        Solution solution = new Solution();
        System.out.println(solution.judgeCircle(moves));
    }
}

class Solution {
     public boolean judgeCircle(String moves) {
         int x = 0, y = 0;
         for (char move : moves.toCharArray()) {
             if (move == 'U') {
                 y++;
             }
             else if (move == 'D') {
                 y--;
             }
             else if (move == 'L') {
                 x--;
             }
             else if (move == 'R') {
                 x++;
             }
         }
         return x == 0 && y == 0;
     }
}