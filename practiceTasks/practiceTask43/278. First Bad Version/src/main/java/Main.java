public class Main {
    public static void main(String[] args) {

    }
}
class VersionControl {
    int bad = 4;

    boolean isBadVersion(int version) {
        return version >= bad;
    }
}
class Solution extends VersionControl{
    public int firstBadVersion(int n){
        int left = 0;
        int right = n;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left;
    }

}
