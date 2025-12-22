package org.ccpc;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }
}
class Solution {
    public boolean checkIfSentenceIsPangram(String sentence) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < sentence.length(); i++){
            set.add(sentence.charAt(i));
        }
        return sentence.length() == 26;
    }
}