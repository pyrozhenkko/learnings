package org.example;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.countBits(9)));
        System.out.println(Arrays.toString(solution.countBits(3)));
    }
}
class Solution{
    private int countOnes(String num){
        int count = 0;
        for(int  i = 0; i < num.length(); i++){
            if(num.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        ans[0] = 0;
        for(int i = 1; i <= n; i++){
            String binary = Integer.toBinaryString(i);
            int oneCount = countOnes(binary);
            ans[i] = oneCount;
        }
        return ans;
    }

}
