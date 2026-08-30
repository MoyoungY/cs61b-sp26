package disc;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Disc {
    public static int maxMinDiff(List<Integer> L) {
        int min = L.get(0);
        int max = L.get(0);
        for (int i : L){
            if (i > max) {max = i;}
            else if (i < min) {min = i;}     
        }
        return max - min;
    }

    public static Map<String, List<String>> listOfFollowers(List<String> x) {
        Map<String, List<String>> map_result = new TreeMap<>();
        int i = 0;
        for (String word: x){
            if (map_result.containsKey(word)){
                if (i < x.size()-1){
                    map_result.get(word).add(x.get(i+1));
                }
            }
            else {
                if (i < x.size()-1){
                    List<String> l = new ArrayList<>();
                    l.add(x.get(i+1));
                    map_result.put(word, l);
                }
            }
            i += 1;
        }
        return map_result;
    }
}
