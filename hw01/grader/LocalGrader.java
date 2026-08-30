import java.nio.file.Path;
import java.util.Set;

import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClasspathRoots;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;
import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;

/** A small command-line JUnit 5 grader for this homework repository. */
public class LocalGrader {

    public static void main(String[] args) throws Exception {
        LauncherDiscoveryRequestBuilder builder = request();
        if (args.length > 0) {
            DiscoverySelector[] selectors = new DiscoverySelector[args.length];
            for (int i = 0; i < args.length; i += 1) {
                selectors[i] = selectClass(args[i]);
            }
            builder.selectors(selectors);
        } else {
            builder.selectors(selectClasspathRoots(Set.of(Path.of("."))))
                    .filters(includeClassNamePatterns(".*Test"));
        }

        LauncherDiscoveryRequest request = builder.build();
        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(
                new TestExecutionListener[] {summaryListener});
        launcher.execute(request);

        var summary = summaryListener.getSummary();
        long total = summary.getTestsFoundCount();
        long passed = summary.getTestsSucceededCount();
        long failed = summary.getTestsFailedCount();
        long skipped = summary.getTestsSkippedCount();
        long aborted = summary.getTestsAbortedCount();
        double score = total == 0 ? 0.0 : 100.0 * passed / total;

        System.out.println();
        System.out.println("========== Local grading report ==========");
        System.out.printf("Tests:   %d passed, %d failed, %d skipped, %d aborted%n",
                passed, failed, skipped, aborted);
        System.out.printf("Score:   %.1f / 100.0%n", score);

        if (!summary.getFailures().isEmpty()) {
            System.out.println("Failures:");
            summary.getFailures().forEach(failure -> {
                String message = failure.getException().getMessage();
                System.out.printf("  - %s: %s%n",
                        failure.getTestIdentifier().getDisplayName(),
                        message == null ? failure.getException().getClass().getSimpleName()
                                : message.replace('\n', ' '));
            });
        }

        if (total == 0) {
            System.err.println("No tests were found.");
            System.exit(2);
        }
        if (passed != total) {
            System.exit(1);
        }
    }
}
