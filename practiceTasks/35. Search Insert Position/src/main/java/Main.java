public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int searchInsert(int [] nums, int target){
        int mid;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums [mid] == target) {
                return mid;
            } else if (nums [mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

