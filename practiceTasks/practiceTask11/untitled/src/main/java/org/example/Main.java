package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.wordPattern("abba", "dog cat cat dog")); // true
        System.out.println(sol.wordPattern("abba", "dog cat cat fish")); // false
        System.out.println(sol.wordPattern("aaaa", "dog cat cat dog")); // false
        System.out.println(sol.wordPattern("abba", "dog dog dog dog")); // false
    }
}

class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charMap = new HashMap<>();
        Map<String, Character> wordMap = new HashMap<>();

        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            // Якщо символ уже зустрічався
            if (charMap.containsKey(ch)) {
                // але слово не збігається з попереднім
                if (!charMap.get(ch).equals(word)) {
                    return false;
                }
            } else {
                // Якщо слово вже закріплено за іншим символом
                if (wordMap.containsKey(word)) {
                    return false;
                }

                // Створюємо нове відображення
                charMap.put(ch, word);
                wordMap.put(word, ch);
            }
        }

        return true;
    }
}
