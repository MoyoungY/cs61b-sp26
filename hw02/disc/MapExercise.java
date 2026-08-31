package disc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercise {
    /** Returns a map from each integer x in the list to a list (without duplicates)
    * of all integers in the list that are less than x. */
    public static Map<Integer, List<Integer>> buildLessThanMap(List<Integer> L) {
        Map<Integer, List<Integer>> result = new TreeMap<>();

        for (int e: L) {
            if (!result.containsKey(e)){
                result.put(e, new ArrayList<Integer>());

                for (int e2: L) {
                    if (e2 < e && !result.get(e).contains(e2)){
                        result.get(e).add(e2);
                    }
                }
            }

        }
        return result;
    }
}
