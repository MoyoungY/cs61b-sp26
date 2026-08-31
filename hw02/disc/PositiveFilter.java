package disc;

import java.util.ArrayList;
import java.util.List;

public class PositiveFilter {
    /** Returns an array containing only the positive integers from the given list */
    public static int[] filterPositive(List<Integer> L) {
        List<Integer> result = new ArrayList<>();

        for (int e: L){
            if (e > 0){
                result.add(e);
            }
        }

        int[] out = new int[result.size()];
        for (int i=0; i < result.size(); i++) {
            out[i] = result.get(i);
        }
        return out;
    }
}
