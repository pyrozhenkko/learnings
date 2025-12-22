package Solution;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.numJewelsInStones("aA", "aAAbbbb")); // 3
    }
}

class Solution {
    public int numJewelsInStones(String J, String S) {
        int num_jewels = 0;

        for (int i = 0; i < S.length(); i++) {
            if (J.indexOf(S.charAt(i)) != -1) { // якщо камінь є у J  це jewel
                num_jewels++;
            }
        }

        return num_jewels;
    }
}
