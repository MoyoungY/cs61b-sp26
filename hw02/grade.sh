#!/usr/bin/env bash

set -u

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
BUILD_DIR="$(mktemp -d "${TMPDIR:-/tmp}/hw02-grade.XXXXXX")"
trap 'rm -rf "$BUILD_DIR"' EXIT

SOURCES=(
    "$PROJECT_DIR/src/StarTriangle5.java"
    "$PROJECT_DIR/src/StarTriangleN.java"
    "$PROJECT_DIR/src/PrintIndexed.java"
    "$PROJECT_DIR/src/DoubleUp.java"
    "$PROJECT_DIR/grader/LocalGrader.java"
)

echo "Compiling homework..."
if ! javac -encoding UTF-8 -proc:none -d "$BUILD_DIR" "${SOURCES[@]}"; then
    echo
    echo "Score: 0.0 / 100.0 (compilation failed)"
    exit 1
fi

echo "Running local tests..."
java -cp "$BUILD_DIR" LocalGrader

