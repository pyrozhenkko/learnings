package org.ccpc;

public class Main {
    public static void main(String[] args) {
    }
}
class Solution {
    public boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            String curr = words[i];
            String next = words[(i + 1) % words.length]; // циклічність

            char last = curr.charAt(curr.length() - 1);
            char first = next.charAt(0);

            if (last != first) {
                return false;
            }
        }
        return true;
    }
}
