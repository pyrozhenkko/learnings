package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Solution s = new Solution();
        System.out.println(s.lengthOfLastWord("Hello ceiwifnoew qwgrte"));

        
    }
}
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        String LastWord = words[words.length - 1];
        return LastWord.length();
    }
}
