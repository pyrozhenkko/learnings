public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int titleToNumber(String columnTitle){
        int result = 0;
        char [] c = columnTitle.toCharArray();
        for(int j=0;j<c.length;j++){
        int num =  c[j] - 'A' + 1;
        result = result * 26 + num;
        }
        return result;
    }
}
