#!/usr/bin/env bash
set -u

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
LIB_DIR="$PROJECT_DIR/../../library-sp26"

if [[ ! -d "$LIB_DIR" ]]; then
    echo "Error: course libraries were not found at $LIB_DIR" >&2
    exit 2
fi

BUILD_DIR="$(mktemp -d "${TMPDIR:-/tmp}/hw01-grade.XXXXXX")"
trap 'rm -rf "$BUILD_DIR"' EXIT

JAVA_FILES=()
while IFS= read -r java_file; do
    JAVA_FILES+=("$java_file")
done < <(find "$PROJECT_DIR/src" "$PROJECT_DIR/tests" \
    "$PROJECT_DIR/grader" -type f -name '*.java' -print)

echo "Compiling ${#JAVA_FILES[@]} Java files..."
if ! javac -proc:none -cp "$LIB_DIR/*" -d "$BUILD_DIR" "${JAVA_FILES[@]}"; then
    echo
    echo "Score: 0.0 / 100.0 (compilation failed)"
    exit 1
fi

echo "Running JUnit tests..."
cd "$BUILD_DIR"
java -cp "$BUILD_DIR:$LIB_DIR/*" LocalGrader "$@"
