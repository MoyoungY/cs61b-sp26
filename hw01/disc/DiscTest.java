package disc;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Local tests for the Java portion of the discussion exercises. */
public class DiscTest {
    @Test
    public void maxMinDiffUsesBothExtremes() {
        assertEquals(8, Disc.maxMinDiff(List.of(5, 1, 9, 4)));
    }

    @Test
    public void maxMinDiffHandlesNegativeValues() {
        assertEquals(9, Disc.maxMinDiff(List.of(-3, -10, -1, -7)));
    }

    @Test
    public void maxMinDiffOfSingletonIsZero() {
        assertEquals(0, Disc.maxMinDiff(List.of(42)));
    }

    @Test
    public void listOfFollowersMatchesDiscussionExample() {
        List<String> words = List.of(
                "I", "love", "java", "but", "I", "love", "python", "more");
        Map<String, List<String>> expected = Map.of(
                "I", List.of("love", "love"),
                "love", List.of("java", "python"),
                "java", List.of("but"),
                "but", List.of("I"),
                "python", List.of("more"));

        assertEquals(expected, Disc.listOfFollowers(words));
    }

    @Test
    public void listOfFollowersHandlesRepeatedWords() {
        assertEquals(
                Map.of("go", List.of("go", "stop")),
                Disc.listOfFollowers(List.of("go", "go", "stop")));
    }

    @Test
    public void listOfFollowersOmitsAWordWithNoFollower() {
        assertEquals(Map.of(), Disc.listOfFollowers(List.of("alone")));
    }

    @Test
    public void listOfFollowersHandlesEmptyList() {
        assertEquals(Map.of(), Disc.listOfFollowers(List.of()));
    }
}
