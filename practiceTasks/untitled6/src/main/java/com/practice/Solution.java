package com.practice;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        boolean check = true;
        check = isPalindrome(n);
        System.out.println(check ? "YES" : "NO");



    }
    private static boolean checkPalindrome(String num){
        int left = 0, right = num.length() - 1;
        while(left < right){
            if(num.charAt(left) != num.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    private static boolean checkPalindrome2(String num){
        String reverse = "";
        for(int i = num.length() - 1; i >= 0; i--){
            reverse = reverse + num.charAt(i);
        }
        return num.equals(reverse);
    }
    public static boolean isPalindrome(int x){
        if (x<0){
            return false;

        }else{
            String num = x + "";
            return checkPalindrome2(num);
        }

    }
}