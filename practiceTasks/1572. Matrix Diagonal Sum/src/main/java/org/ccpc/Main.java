package org.ccpc;


public class Main {
    public static void main(String[] args) {
    Solution sol = new Solution();
    int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
        System.out.println(sol.diagonalSum(matrix));
    }
}

class Solution {
    public int diagonalSum(int[][] mat) {
        int len = mat.length;
        int sum = 0;
        for(int i=0;i<len;i++){
            sum += mat[i][i];
            sum += mat[i][len - 1 - i];
        }
        if (len % 2 != 0) {
            sum -= mat[len / 2][len / 2];
        }

        return sum;
    }
}