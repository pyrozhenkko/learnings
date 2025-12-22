public class Main {
    public static void main(String[] args) {

    }
}
class Solution {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int [] c1 = new int [26];
        int [] c2 = new int [26];
        for(char c: word1.toCharArray()){
            c1[c - 'a']++;
        }
        for(char c: word2.toCharArray()){
            c2[c - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if(Math.abs(c1[i] - c2[i]) > 3){
                return false;
            }
        }
        return true;
    }
}
