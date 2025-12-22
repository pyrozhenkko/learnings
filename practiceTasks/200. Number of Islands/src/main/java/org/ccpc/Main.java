package org.ccpc;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        char[][] grid = {
                {'1','1','0','0'},
                {'1','0','0','1'},
                {'0','0','1','1'}
        };

        System.out.println(solution.numIslands(grid)); // 2
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    callBFS(grid, i, j);
                }
            }
        }
        return count;
    }

    public void callBFS(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length ||
                j < 0 || j >= grid[i].length ||
                grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        callBFS(grid, i + 1, j); // вниз
        callBFS(grid, i - 1, j); // вверх
        callBFS(grid, i, j + 1); // вправо
        callBFS(grid, i, j - 1); // вліво
    }
}
