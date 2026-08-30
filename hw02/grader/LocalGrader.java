import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/** Dependency-free local correctness grader for HW02. */
public class LocalGrader {
    private static final double POINTS_PER_SECTION = 25.0;
    private static final List<Section> SECTIONS = new ArrayList<>();

    public static void main(String[] args) {
        testStarTriangle5();
        testStarTriangleN();
        testPrintIndexed();
        testDoubleUp();

        double score = 0.0;
        System.out.println();
        System.out.println("========== HW02 local grading report ==========");
        for (Section section : SECTIONS) {
            double earned = POINTS_PER_SECTION * section.passed / section.total;
            score += earned;
            System.out.printf("%-18s %2d/%-2d tests   %5.1f / 25.0%n",
                    section.name, section.passed, section.total, earned);
            for (String failure : section.failures) {
                System.out.println("  - " + failure);
            }
        }
        System.out.println("------------------------------------------------");
        System.out.printf("Score: %.1f / 100.0%n", score);
        System.out.println("Note: this is an unofficial local estimate, not a Gradescope score.");

        if (score < 100.0) {
            System.exit(1);
        }
    }

    private static void testStarTriangle5() {
        Section section = new Section("StarTriangle5");
        checkOutput(section, "five rows", "    *\n   **\n  ***\n ****\n*****\n",
                StarTriangle5::starTriangle5);
        SECTIONS.add(section);
    }

    private static void testStarTriangleN() {
        Section section = new Section("StarTriangleN");
        checkOutput(section, "N = 0", "", () -> StarTriangleN.starTriangle(0));
        checkOutput(section, "N = 1", "*\n", () -> StarTriangleN.starTriangle(1));
        checkOutput(section, "N = 3", "  *\n **\n***\n",
                () -> StarTriangleN.starTriangle(3));
        checkOutput(section, "N = 6", "     *\n    **\n   ***\n  ****\n *****\n******\n",
                () -> StarTriangleN.starTriangle(6));
        SECTIONS.add(section);
    }

    private static void testPrintIndexed() {
        Section section = new Section("PrintIndexed");
        checkOutput(section, "empty string", "\n", () -> PrintIndexed.printIndexed(""));
        checkOutput(section, "one character", "x0\n", () -> PrintIndexed.printIndexed("x"));
        checkOutput(section, "example: cat", "c2a1t0\n", () -> PrintIndexed.printIndexed("cat"));
        checkOutput(section, "example: hello", "h4e3l2l1o0\n",
                () -> PrintIndexed.printIndexed("hello"));
        checkOutput(section, "spaces and punctuation", "a2 1!0\n",
                () -> PrintIndexed.printIndexed("a !"));
        checkOutput(section, "multi-digit reverse indexes",
                "a11b10c9d8e7f6g5h4i3j2k1l0\n",
                () -> PrintIndexed.printIndexed("abcdefghijkl"));
        SECTIONS.add(section);
    }

    private static void testDoubleUp() {
        Section section = new Section("DoubleUp");
        checkValue(section, "empty string", "", DoubleUp.doubleUp(""));
        checkValue(section, "one character", "zz", DoubleUp.doubleUp("z"));
        checkValue(section, "example: cat", "ccaatt", DoubleUp.doubleUp("cat"));
        checkValue(section, "example: hello", "hheelllloo", DoubleUp.doubleUp("hello"));
        checkValue(section, "spaces and punctuation", "AA  !!", DoubleUp.doubleUp("A !"));
        checkValue(section, "non-ASCII characters", "你你好好", DoubleUp.doubleUp("你好"));
        SECTIONS.add(section);
    }

    private static void checkOutput(Section section, String testName, String expected,
                                    Runnable action) {
        section.total += 1;
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(bytes, true, StandardCharsets.UTF_8));
            action.run();
            String actual = bytes.toString(StandardCharsets.UTF_8);
            if (expected.equals(actual)) {
                section.passed += 1;
            } else {
                section.failures.add(testName + ": expected " + visible(expected)
                        + ", got " + visible(actual));
            }
        } catch (Throwable error) {
            section.failures.add(testName + ": threw " + describe(error));
        } finally {
            System.setOut(original);
        }
    }

    private static void checkValue(Section section, String testName,
                                   String expected, String actual) {
        section.total += 1;
        if (expected.equals(actual)) {
            section.passed += 1;
        } else {
            section.failures.add(testName + ": expected " + visible(expected)
                    + ", got " + visible(actual));
        }
    }

    private static String visible(String value) {
        if (value == null) {
            return "null";
        }
        return "\"" + value.replace("\\", "\\\\")
                .replace("\r", "\\r").replace("\n", "\\n") + "\"";
    }

    private static String describe(Throwable error) {
        String message = error.getMessage();
        return error.getClass().getSimpleName()
                + (message == null ? "" : " (" + message + ")");
    }

    private static class Section {
        private final String name;
        private int passed;
        private int total;
        private final List<String> failures = new ArrayList<>();

        Section(String name) {
            this.name = name;
        }
    }
}
