package disc;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {
    /** Returns a list containing the common items of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> result = new ArrayList<>();
        for (Integer e1: L1){
            if (L2.contains(e1) && !result.contains(e1)) {
                result.add(e1);
            }
        }
        return result;
    }

    /** Capitalizes all strings in the given list in place */
    public static void capitalize(List<String> L) {
        for (int i = 0; i < L.size(); i++){
            String s = L.get(i);
            L.set(i, s.toUpperCase());
        }
    }
}
