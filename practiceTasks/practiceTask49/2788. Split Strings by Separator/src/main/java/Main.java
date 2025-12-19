import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}
class Solution{
    public List<String> splitWordsBySeparator(List<String> words, char separator){
        List<String> result = new ArrayList<>();
        for(int i =0; i<words.size(); i++){
            String wordsArray[] = words.get(i).split("[" + separator + "]");
            for(String word : wordsArray){
                if (!word.isEmpty()) {
                    result.add(word);
                }
            }
        }
        return result;
    }
}
