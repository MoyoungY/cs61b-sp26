package exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    public double score;

    // Fill in rankedAbove, which takes in a list of Players and returns a map from each Player to their rank. The
    // player with the highest score has rank 1, the player with the next-highest score has rank 2, and so on. Assume
    // no two players have the same score. For example, if we have a list of players with scores of 500, 800, 1200,
    // and 100, then these players would have ranks 3, 2, 1, and 4, respectively, and rankedAbove would return the
    // following Map:
    // { Player with score 500 : 3, Player with score 800 : 2,
    // Player with score 1200 : 1, Player with score 100 : 4 }
    public static Map<Player, Integer> rankedAbove(List<Player> players) {
        Map<Player, Integer> results = new HashMap<>();
        for (Player p : players) {
            int rank = 1;
            for (Player other : players) {
                if (p.score < other.score) {
                    rank ++;
                }
            }
            results.put(p, rank);
        }
        return results;
    }
}
