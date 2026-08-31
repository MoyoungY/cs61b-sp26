package disc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.google.common.truth.Truth.assertThat;

public class DiscTest {
    
    @Test
    public void planetTest(){
        Planet p1 = new Planet(5, 10, 100);
        Planet p2 = new Planet(1, 2, 200);
        double distance = p1.distanceTo(p2);
        double mass = Planet.totalMass(new Planet[]{p1, p2});

        assertThat(distance).isEqualTo(Math.sqrt(80));
        assertThat(mass).isEqualTo(300);
    }

    @Test
    public void commonTest() {
        List<Integer> list1 = Arrays.asList(1, 2, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(2, 4, 4, 6);

        List<Integer> result = ListExercise.common(list1, list2);

        assertThat(result).containsExactly(2, 4).inOrder();
    }

    @Test
    public void capitalizeTest() {
        List<String> words = new ArrayList<>(Arrays.asList("hello", "cs61b", "Java"));

        ListExercise.capitalize(words);

        assertThat(words).containsExactly("HELLO", "CS61B", "JAVA").inOrder();
    }

    @Test
    public void buildLessThanMapTest() {
        List<Integer> numbers = Arrays.asList(3, 1, 2, 1, -1, 3);
        Map<Integer, List<Integer>> expected = new TreeMap<>();
        expected.put(-1, Collections.emptyList());
        expected.put(1, Arrays.asList(-1));
        expected.put(2, Arrays.asList(1, -1));
        expected.put(3, Arrays.asList(1, 2, -1));

        Map<Integer, List<Integer>> result = MapExercise.buildLessThanMap(numbers);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void buildLessThanMapEmptyListTest() {
        Map<Integer, List<Integer>> result =
                MapExercise.buildLessThanMap(Collections.emptyList());

        assertThat(result).isEmpty();
    }

    @Test
    public void filterPositiveTest() {
        List<Integer> numbers = Arrays.asList(-3, 0, 4, -1, 2, 4);

        int[] result = PositiveFilter.filterPositive(numbers);

        assertThat(result).isEqualTo(new int[]{4, 2, 4});
    }

    @Test
    public void filterPositiveNoPositiveNumbersTest() {
        List<Integer> numbers = Arrays.asList(-3, 0, -1);

        int[] result = PositiveFilter.filterPositive(numbers);

        assertThat(result).isEmpty();
    }
}
