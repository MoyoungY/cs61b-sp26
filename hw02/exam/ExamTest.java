package exam;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class ExamTest {

    @Test
    public void rankedAboveAssignsCorrectRanks() {
        Player player500 = playerWithScore(500);
        Player player800 = playerWithScore(800);
        Player player1200 = playerWithScore(1200);
        Player player100 = playerWithScore(100);

        Map<Player, Integer> ranks = Player.rankedAbove(
                Arrays.asList(player500, player800, player1200, player100));

        assertThat(ranks).containsExactly(
                player500, 3,
                player800, 2,
                player1200, 1,
                player100, 4);
    }

    @Test
    public void rankedAboveWorksRegardlessOfInputOrder() {
        Player lowest = playerWithScore(-10);
        Player middle = playerWithScore(0.5);
        Player highest = playerWithScore(100);

        Map<Player, Integer> ranks = Player.rankedAbove(
                Arrays.asList(highest, lowest, middle));

        assertThat(ranks.get(highest)).isEqualTo(1);
        assertThat(ranks.get(middle)).isEqualTo(2);
        assertThat(ranks.get(lowest)).isEqualTo(3);
    }

    @Test
    public void rankedAboveSinglePlayerHasRankOne() {
        Player onlyPlayer = playerWithScore(42);

        Map<Player, Integer> ranks =
                Player.rankedAbove(Collections.singletonList(onlyPlayer));

        assertThat(ranks).containsExactly(onlyPlayer, 1);
    }

    @Test
    public void rankedAboveEmptyListReturnsEmptyMap() {
        Map<Player, Integer> ranks =
                Player.rankedAbove(Collections.emptyList());

        assertThat(ranks).isEmpty();
    }

    @Test
    public void countByCountryCountsStudentsInOneClub() {
        Country china = country("China");
        Country usa = country("USA");
        Club club = clubWith(
                student("Alice"), china,
                student("Bob"), china,
                student("Carol"), usa);

        Map<Country, Integer> counts =
                Club.countByCountry(Collections.singletonList(club));

        assertThat(counts).containsExactly(china, 2, usa, 1);
    }

    @Test
    public void countByCountryDoesNotCountStudentInMultipleClubsTwice() {
        Country china = country("China");
        Country usa = country("USA");
        Student alice = student("Alice");
        Student bob = student("Bob");
        Student carol = student("Carol");
        Club chessClub = clubWith(alice, china, bob, usa);
        Club musicClub = clubWith(alice, china, carol, china);

        Map<Country, Integer> counts =
                Club.countByCountry(Arrays.asList(chessClub, musicClub));

        assertThat(counts).containsExactly(china, 2, usa, 1);
    }

    @Test
    public void countByCountryOnlyIncludesCountriesThatAppear() {
        Country china = country("China");
        Country unusedCountry = country("Canada");
        Club club = clubWith(student("Alice"), china);

        Map<Country, Integer> counts =
                Club.countByCountry(Collections.singletonList(club));

        assertThat(counts).containsExactly(china, 1);
        assertThat(counts).doesNotContainKey(unusedCountry);
    }

    private static Player playerWithScore(double score) {
        Player player = new Player();
        player.score = score;
        return player;
    }

    private static Student student(String name) {
        Student student = new Student();
        student.studentName = name;
        return student;
    }

    private static Country country(String name) {
        Country country = new Country();
        country.countryName = name;
        return country;
    }

    private static Club clubWith(Object... studentCountryPairs) {
        Club club = new Club();
        club.countryMap = new HashMap<>();
        for (int i = 0; i < studentCountryPairs.length; i += 2) {
            Student student = (Student) studentCountryPairs[i];
            Country country = (Country) studentCountryPairs[i + 1];
            club.countryMap.put(student, country);
        }
        return club;
    }
}
