package org.ccpc;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] height = {5, 3, 8, 2, 9};
        int threshold = 4;

        List<Integer> result = sol.stableMountains(height, threshold);

        System.out.println(result);
    }
}
