    package org.example;


    public class Main {
        static void main() {
            Solution solution = new Solution();
            System.out.println(solution.some(new int[]{-2,2,-4,4,1}));

        }
    }

    class Solution {
        public int some(int[] nums) {
            int sum = nums[0];
            int max = nums[0];

            for (int i = 1; i < nums.length; i++) {
                sum += nums[i];
                sum = Math.max(nums[i], sum); // або почати новий підмасив, або продовжити старий
                max = Math.max(max, sum);               // оновлюємо максимум
            }

            return max;
        }
    }


