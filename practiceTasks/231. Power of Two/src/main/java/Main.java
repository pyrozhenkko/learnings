public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
