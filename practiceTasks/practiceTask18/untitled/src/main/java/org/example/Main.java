package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

    }
}
class Solution{
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(nums[mid]>nums[mid+1]){
                right = mid + 1;

            }else right = mid;

        }
        return left;
    }
}