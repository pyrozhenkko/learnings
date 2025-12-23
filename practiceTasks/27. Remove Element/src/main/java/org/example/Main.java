package org.example;
public class Main {
    static void main() {
        Solution solution = new Solution();
        System.out.println(solution.removeElement(new int[]{1,2,3,4,5}, 3));

    }
}
class Solution{
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        while(i<len){
            if(nums[i]==val){
                nums[i]=nums[len-1];
                len--;
            }
            else{
                i++;
            }
        }
        return len;

    }
}
