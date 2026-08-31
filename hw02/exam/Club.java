package exam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Avik wants to keep track of the students in UC Berkeley’s clubs. Each club is represented by the Club class
// below, which maps every student in that club to their home country.
// public class Club {
// public Map<Student, Country> countryMap;
// ...
// }

// public class Student { ... }
// public class Country { ... }
// On the next page, implement countByCountry, which takes in a list of Clubs, and returns a map from each
// Country to the number of unique students from that country. The map should only contain countries that
// appear in the countryMaps.
// If a Student is in multiple clubs, then each of those clubs will map that student to the same Country. Make
// sure to avoid counting the same Student twice if they are in multiple clubs.
// You may assume that there is at least one club, and each club has at least one student.
// Here is an example with 2 clubs and 3 total students:
public class Club {
    public Map<Student, Country> countryMap;
    public static Map<Country, Integer> countByCountry(List<Club> allClubs) {
        Map<Country, Integer> counts = new HashMap<>();
        Set<Student> studentRecord = new HashSet<>();

        for (Club club: allClubs) {
            for (Student s : club.countryMap.keySet()) {
                if (!studentRecord.contains(s)){
                    Country c = club.countryMap.get(s);
                    if (!counts.containsKey(c)){
                        counts.put(c, 1);
                    }
                    else {
                        counts.put(c, counts.get(c)+1);
                    }
                    studentRecord.add(s);
                }
            }
        }
        return counts;
    }
}

class Student { String studentName; }
class Country { String countryName; } 