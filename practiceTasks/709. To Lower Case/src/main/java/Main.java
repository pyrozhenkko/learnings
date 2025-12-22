public class Main {
    public static void main(String[] args) {

    }
}
class Solution {
    public String toLowerCase(String str) {
    //    return str.toLowerCase();
        StringBuilder result = new StringBuilder();
        for(char c : str.toCharArray()){
            if(c >= 'A' && c <= 'Z'){
                result.append((char)(c-'A'+'a'));
            }
            else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
