#!/usr/bin/env bash
set -u

DISC_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(cd "$DISC_DIR/.." && pwd)"
LIB_DIR="$PROJECT_DIR/../../library-sp26"

if [[ ! -d "$LIB_DIR" ]]; then
    echo "Error: course libraries were not found at $LIB_DIR" >&2
    exit 2
fi

BUILD_DIR="$(mktemp -d "${TMPDIR:-/tmp}/hw01-disc-grade.XXXXXX")"
trap 'rm -rf "$BUILD_DIR"' EXIT

java_passed=0
python_passed=0

echo "Compiling the Java discussion exercises..."
if javac -proc:none -cp "$LIB_DIR/*" -d "$BUILD_DIR" \
        "$PROJECT_DIR/grader/LocalGrader.java" \
        "$DISC_DIR/Disc.java" "$DISC_DIR/DiscTest.java"; then
    echo "Running Java discussion tests..."
    if (cd "$BUILD_DIR" && java -cp "$BUILD_DIR:$LIB_DIR/*" LocalGrader disc.DiscTest); then
        java_passed=1
    fi
else
    echo "Java discussion score: 0.0 / 50.0 (compilation failed)"
fi

echo
echo "Running Python discussion tests..."
if (cd "$DISC_DIR" && python3 -m unittest -v test_disc.py); then
    python_passed=1
fi

score=$((50 * java_passed + 50 * python_passed))
echo
echo "========== Discussion grading report =========="
echo "Java:   $((50 * java_passed)).0 / 50.0"
echo "Python: $((50 * python_passed)).0 / 50.0"
echo "Score:  ${score}.0 / 100.0"

if ((java_passed == 1 && python_passed == 1)); then
    exit 0
fi
exit 1
